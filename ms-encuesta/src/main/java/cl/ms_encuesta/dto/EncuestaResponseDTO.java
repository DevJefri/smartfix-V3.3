package cl.ms_encuesta.dto;

import java.time.LocalDateTime;

public record EncuestaResponseDTO(
    Long reparacionId,
    String rutCliente,
    Integer puntuacion,
    String comentario,
    LocalDateTime fecha
) {}
