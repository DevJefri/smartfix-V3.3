package cl.bff_web.dto;

public record EncuestaBffRequestDTO(
    Long reparacionId,
    String rutCliente,
    Integer puntuacion,
    String comentario
) {}
