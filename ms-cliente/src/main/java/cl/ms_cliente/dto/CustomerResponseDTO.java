package cl.ms_cliente.dto;

public record CustomerResponseDTO(
        String rut,
        String name,
        String phone,
        String email
) {
}
