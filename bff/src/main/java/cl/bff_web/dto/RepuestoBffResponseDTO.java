package cl.bff_web.dto;

import java.math.BigDecimal;

public record RepuestoBffResponseDTO(
    Long id,
    String sku,
    String nombre,
    Integer stock,
    BigDecimal precioUnitario
) {}
