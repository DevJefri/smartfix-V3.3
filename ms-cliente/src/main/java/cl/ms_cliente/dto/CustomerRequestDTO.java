package cl.ms_cliente.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CustomerRequestDTO(

    @NotBlank(message = "RUT is required")
    @Pattern(regexp = "\\d{7,8}-[\\dkK]", message = "Invalid RUT format. Example: 12345678-9")
    String rut,

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    String name,

    @Pattern(regexp = "^[+]?[0-9]{9,15}$", message = "Invalid phone number")
    String phone,

    @Email(message = "Invalid email")
    String email
) {}
