package cl.ms_auditoria.dto;

import java.time.LocalDateTime;

public record EventoAuditoriaResponseDTO(
    Long id,
    String servicioOrigen,
    String accion,
    String detalle,
    LocalDateTime fecha
) {}
