package cl.ms_diagnostico.dto;

public record DiagnosticoResponseDTO(
    String tipoDispositivo,
    String prioridad,
    int tiempoEstimadoHoras,
    String recomendacion
) {}
