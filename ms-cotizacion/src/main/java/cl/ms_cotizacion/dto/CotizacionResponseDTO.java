package cl.ms_cotizacion.dto;

import java.math.BigDecimal;

public record CotizacionResponseDTO(
    String tipoDispositivo,
    String tipoFalla,
    boolean urgente,
    BigDecimal montoBase,
    BigDecimal recargoUrgencia,
    BigDecimal montoTotal
) {}
