package cl.ms_garantia.dto;

import java.time.LocalDate;

public record GarantiaResponseDTO(
    String tipoReparacion,
    LocalDate fechaEntrega,
    LocalDate fechaVencimiento,
    int diasGarantia
) {}
