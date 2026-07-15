package cl.ms_reparacion.dto;

public record RepairResponseDTO(
    Long id,
    String customerRut,
    String model,
    String description,
    String status
) {}
