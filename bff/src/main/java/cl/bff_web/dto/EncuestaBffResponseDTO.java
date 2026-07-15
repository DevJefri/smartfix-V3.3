package cl.bff_web.dto;

public record EncuestaBffResponseDTO(
    Long reparacionId,
    String rutCliente,
    Integer puntuacion,
    String comentario,
    String fecha
) {}
