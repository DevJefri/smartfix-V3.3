package cl.ms_auditoria.service;

import cl.ms_auditoria.dto.EventoAuditoriaRequestDTO;
import cl.ms_auditoria.dto.EventoAuditoriaResponseDTO;
import cl.ms_auditoria.exception.ServicioOrigenInvalidoException;
import cl.ms_auditoria.model.EventoAuditoria;
import cl.ms_auditoria.repository.EventoAuditoriaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Bitacora centralizada: cualquier microservicio de Smartfix puede
 * registrar aqui un evento relevante (creacion de una reparacion, cambio
 * de estado, emision de una factura, etc.) para tener trazabilidad.
 */
@Service
public class AuditoriaServiceImpl implements AuditoriaService {

    private static final Logger log = LoggerFactory.getLogger(AuditoriaServiceImpl.class);

    private final EventoAuditoriaRepository eventoAuditoriaRepository;

    public AuditoriaServiceImpl(EventoAuditoriaRepository eventoAuditoriaRepository) {
        this.eventoAuditoriaRepository = eventoAuditoriaRepository;
    }

    @Override
    public EventoAuditoriaResponseDTO registrar(EventoAuditoriaRequestDTO dto) {
        EventoAuditoria evento = new EventoAuditoria();
        evento.setServicioOrigen(dto.servicioOrigen());
        evento.setAccion(dto.accion());
        evento.setDetalle(dto.detalle());
        evento.setFecha(LocalDateTime.now());

        EventoAuditoria guardado = eventoAuditoriaRepository.save(evento);
        // Se deja registro tambien en el log de consola (y por lo tanto en GlitchTip
        // si el nivel corresponde), que es lo que pide la regla de "logs por pantalla".
        log.info("[AUDITORIA] {} -> {} ({})", dto.servicioOrigen(), dto.accion(), dto.detalle());

        return mapToDto(guardado);
    }

    @Override
    public List<EventoAuditoriaResponseDTO> listarTodos() {
        return eventoAuditoriaRepository.findAllByOrderByFechaDesc()
                .stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<EventoAuditoriaResponseDTO> listarPorServicio(String servicioOrigen) {
        try {
            if (servicioOrigen == null || servicioOrigen.isBlank()) {
                throw new ServicioOrigenInvalidoException(servicioOrigen);
            }
            return eventoAuditoriaRepository.findByServicioOrigenOrderByFechaDesc(servicioOrigen)
                    .stream().map(this::mapToDto).collect(Collectors.toList());
        } catch (ServicioOrigenInvalidoException ex) {
            log.warn("Consulta de auditoria rechazada: {}", ex.getMessage());
            throw ex;
        }
    }

    private EventoAuditoriaResponseDTO mapToDto(EventoAuditoria e) {
        return new EventoAuditoriaResponseDTO(
                e.getId(), e.getServicioOrigen(), e.getAccion(), e.getDetalle(), e.getFecha());
    }
}
