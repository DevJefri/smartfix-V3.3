package cl.ms_inventario.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record RepuestoRequestDTO(

    @NotBlank(message = "El SKU es obligatorio")
    String sku,

    @NotBlank(message = "El nombre es obligatorio")
    String nombre,

    @NotNull(message = "El stock inicial es obligatorio")
    @PositiveOrZero(message = "El stock no puede ser negativo")
    Integer stock,

    @NotNull(message = "El precio unitario es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El precio no puede ser negativo")
    BigDecimal precioUnitario
) {}
