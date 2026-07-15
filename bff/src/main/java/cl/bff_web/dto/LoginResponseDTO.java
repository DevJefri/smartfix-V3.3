package cl.bff_web.dto;

public record LoginResponseDTO(
    String token,
    String username,
    String role
) {}
