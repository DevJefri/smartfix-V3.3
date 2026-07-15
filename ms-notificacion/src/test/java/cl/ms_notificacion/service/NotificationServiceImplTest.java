package cl.ms_notificacion.service;

import cl.ms_notificacion.dto.NotificationRequestDTO;
import cl.ms_notificacion.dto.NotificationResponseDTO;
import cl.ms_notificacion.exception.CanalNoSoportadoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationServiceImplTest {

    private final NotificationServiceImpl service = new NotificationServiceImpl();

    @Test
    void enviar_shouldReturnEnviadoWhenCanalIsEmail() {
        NotificationRequestDTO dto = new NotificationRequestDTO(
                "cliente@correo.com", "Reparacion lista", "Tu equipo esta listo", "EMAIL");

        NotificationResponseDTO result = service.enviar(dto);

        assertNotNull(result.id());
        assertEquals("EMAIL", result.canal());
        assertEquals("ENVIADO", result.estado());
        assertEquals("cliente@correo.com", result.destinatario());
        assertNotNull(result.fechaEnvio());
    }

    @Test
    void enviar_shouldAcceptSmsCaseInsensitive() {
        NotificationRequestDTO dto = new NotificationRequestDTO(
                "+56912345678", "Aviso", "Tu equipo esta listo", "sms");

        NotificationResponseDTO result = service.enviar(dto);

        assertEquals("SMS", result.canal());
    }

    @Test
    void enviar_shouldThrowWhenCanalIsInvalid() {
        NotificationRequestDTO dto = new NotificationRequestDTO(
                "cliente@correo.com", "Aviso", "Mensaje", "WHATSAPP");

        assertThrows(CanalNoSoportadoException.class, () -> service.enviar(dto));
    }
}
