package cl.ms_factura.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record FacturaRequestDTO(

    @NotNull(message = "El id de la reparacion es obligatorio")
    Long reparacionId,

    @NotBlank(message = "El RUT del cliente es obligatorio")
    String rutCliente,

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor a 0")
    BigDecimal monto
) {}
