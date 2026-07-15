package cl.bff_web.dto;

public record RepairBffDTO(
    Long id,
    String customerRut,
    String model,
    String description,
    String status
) {}
