package cl.bff_web.dto;

import java.time.LocalDate;

public record GarantiaBffResponseDTO(
    String tipoReparacion,
    LocalDate fechaEntrega,
    LocalDate fechaVencimiento,
    int diasGarantia
) {}
