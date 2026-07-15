package cl.bff_web.dto;

public record CotizacionBffRequestDTO(
    String tipoDispositivo,
    String tipoFalla,
    Boolean urgente
) {}
