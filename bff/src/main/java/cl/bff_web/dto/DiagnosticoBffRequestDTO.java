package cl.bff_web.dto;

import java.util.List;

public record DiagnosticoBffRequestDTO(
    List<String> sintomas,
    String tipoDispositivo
) {}
