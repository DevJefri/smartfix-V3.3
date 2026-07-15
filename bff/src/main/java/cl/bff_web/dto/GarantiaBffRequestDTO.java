package cl.bff_web.dto;

import java.time.LocalDate;

public record GarantiaBffRequestDTO(
    LocalDate fechaEntrega,
    String tipoReparacion
) {}
