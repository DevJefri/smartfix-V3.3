package cl.bff_web.dto;

public record EventoAuditoriaBffRequestDTO(
    String servicioOrigen,
    String accion,
    String detalle
) {}
