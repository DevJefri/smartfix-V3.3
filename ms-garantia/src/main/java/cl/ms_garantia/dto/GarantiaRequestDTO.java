package cl.ms_garantia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record GarantiaRequestDTO(

    @NotNull(message = "La fecha de entrega es obligatoria")
    LocalDate fechaEntrega,

    @NotBlank(message = "El tipo de reparacion es obligatorio")
    String tipoReparacion
) {}
