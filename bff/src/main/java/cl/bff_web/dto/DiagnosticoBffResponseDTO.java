package cl.bff_web.dto;

public record DiagnosticoBffResponseDTO(
    String tipoDispositivo,
    String prioridad,
    int tiempoEstimadoHoras,
    String recomendacion
) {}
