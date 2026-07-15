package cl.ms_conversor.dto;

import java.math.BigDecimal;

public record ConversionResponseDTO(
    BigDecimal montoOriginal,
    String monedaOrigen,
    String monedaDestino,
    BigDecimal tasa,
    BigDecimal montoConvertido
) {}
