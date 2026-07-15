package cl.ms_cotizacion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CotizacionRequestDTO(

    @NotBlank(message = "El tipo de dispositivo es obligatorio")
    String tipoDispositivo,

    @NotBlank(message = "El tipo de falla es obligatorio")
    String tipoFalla,

    @NotNull(message = "Debe indicar si la atencion es urgente")
    Boolean urgente
) {}
