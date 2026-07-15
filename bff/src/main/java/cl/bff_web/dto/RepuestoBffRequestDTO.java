package cl.bff_web.dto;

import java.math.BigDecimal;

public record RepuestoBffRequestDTO(
    String sku,
    String nombre,
    Integer stock,
    BigDecimal precioUnitario
) {}
