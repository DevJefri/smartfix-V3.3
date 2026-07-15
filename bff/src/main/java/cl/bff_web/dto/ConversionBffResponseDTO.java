package cl.bff_web.dto;

import java.math.BigDecimal;

public record ConversionBffResponseDTO(
    BigDecimal montoOriginal,
    String monedaOrigen,
    String monedaDestino,
    BigDecimal tasa,
    BigDecimal montoConvertido
) {}
