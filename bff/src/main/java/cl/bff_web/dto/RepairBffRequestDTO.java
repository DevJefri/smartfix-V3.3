package cl.bff_web.dto;

public record RepairBffRequestDTO(
    String customerRut,
    String model,
    String description
) {}
