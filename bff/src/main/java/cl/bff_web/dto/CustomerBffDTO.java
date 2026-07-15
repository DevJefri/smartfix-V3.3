package cl.bff_web.dto;

public record CustomerBffDTO(
    String rut,
    String name,
    String phone,
    String email
) {}
