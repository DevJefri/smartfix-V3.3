package cl.ms_inventario.dto;

import java.math.BigDecimal;

public record RepuestoResponseDTO(
    Long id,
    String sku,
    String nombre,
    Integer stock,
    BigDecimal precioUnitario
) {}
