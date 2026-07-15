package cl.ms_encuesta.dto;

public record PromedioResponseDTO(
    double promedio,
    long totalEncuestas
) {}
