package cl.ms_notificacion.service;

import cl.ms_notificacion.dto.NotificationRequestDTO;
import cl.ms_notificacion.dto.NotificationResponseDTO;
import cl.ms_notificacion.exception.CanalNoSoportadoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Implementacion del envio de notificaciones.
 * Este microservicio no persiste datos: simula el envio (por ejemplo, a un
 * proveedor de EMAIL o SMS) y deja registro en el log de la aplicacion,
 * que es lo que en este proyecto se muestra por pantalla y se reporta a GlitchTip.
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);
    private static final List<String> CANALES_VALIDOS = List.of("EMAIL", "SMS");

    @Override
    public NotificationResponseDTO enviar(NotificationRequestDTO dto) {
        String canal = dto.canal().toUpperCase();

        if (!CANALES_VALIDOS.contains(canal)) {
            throw new CanalNoSoportadoException(dto.canal());
        }

        // Simulacion del envio: en un escenario real aqui se llamaria a un
        // proveedor externo (SMTP, Twilio, etc.). Se deja como try/catch para
        // manejar de forma explicita cualquier falla del "envio".
        try {
            log.info("Enviando notificacion via {} a {} - asunto: '{}'",
                    canal, dto.destinatario(), dto.asunto());
        } catch (Exception e) {
            log.error("Fallo simulado al enviar notificacion a {}", dto.destinatario(), e);
            throw e;
        }

        return new NotificationResponseDTO(
                UUID.randomUUID().toString(),
                dto.destinatario(),
                canal,
                "ENVIADO",
                LocalDateTime.now()
        );
    }
}
