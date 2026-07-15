package cl.bff_web.dto;

public record EventoAuditoriaBffResponseDTO(
    Long id,
    String servicioOrigen,
    String accion,
    String detalle,
    String fecha
) {}
