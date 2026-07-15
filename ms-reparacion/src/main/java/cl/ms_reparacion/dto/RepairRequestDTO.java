package cl.ms_reparacion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RepairRequestDTO(

    @NotBlank(message = "Customer RUT is required")
    @Pattern(regexp = "\\d{7,8}-[\\dkK]", message = "Invalid RUT format")
    String customerRut,

    @NotBlank(message = "Model is required")
    String model,

    @NotBlank(message = "Description is required")
    String description
) {}
