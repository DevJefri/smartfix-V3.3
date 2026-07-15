package cl.ms_diagnostico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record DiagnosticoRequestDTO(

    @NotEmpty(message = "Debe indicar al menos un sintoma")
    List<String> sintomas,

    @NotBlank(message = "El tipo de dispositivo es obligatorio")
    String tipoDispositivo
) {}
