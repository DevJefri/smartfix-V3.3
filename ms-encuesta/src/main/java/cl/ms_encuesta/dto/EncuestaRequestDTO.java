package cl.ms_encuesta.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EncuestaRequestDTO(

    @NotNull(message = "El id de la reparacion es obligatorio")
    Long reparacionId,

    @NotBlank(message = "El RUT del cliente es obligatorio")
    String rutCliente,

    @NotNull(message = "La puntuacion es obligatoria")
    @Min(value = 1, message = "La puntuacion minima es 1")
    @Max(value = 5, message = "La puntuacion maxima es 5")
    Integer puntuacion,

    @Size(max = 500, message = "El comentario no puede superar los 500 caracteres")
    String comentario
) {}
