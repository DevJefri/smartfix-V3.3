package cl.ms_diagnostico.service;

import cl.ms_diagnostico.dto.DiagnosticoRequestDTO;
import cl.ms_diagnostico.dto.DiagnosticoResponseDTO;

public interface DiagnosticoService {

    /**
     * Analiza los sintomas reportados y sugiere una prioridad de atencion
     * junto con un tiempo estimado de reparacion.
     *
     * @param dto lista de sintomas y tipo de dispositivo
     * @return prioridad sugerida, horas estimadas y recomendacion textual
     */
    DiagnosticoResponseDTO analizar(DiagnosticoRequestDTO dto);
}
