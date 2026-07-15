package cl.bff_web.dto;

import java.math.BigDecimal;

public record CotizacionBffResponseDTO(
    String tipoDispositivo,
    String tipoFalla,
    boolean urgente,
    BigDecimal montoBase,
    BigDecimal recargoUrgencia,
    BigDecimal montoTotal
) {}
