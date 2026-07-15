package cl.ms_diagnostico.controller;

import cl.ms_diagnostico.dto.DiagnosticoRequestDTO;
import cl.ms_diagnostico.dto.DiagnosticoResponseDTO;
import cl.ms_diagnostico.service.DiagnosticoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diagnostico")
public class DiagnosticoController {

    private final DiagnosticoService diagnosticoService;

    public DiagnosticoController(DiagnosticoService diagnosticoService) {
        this.diagnosticoService = diagnosticoService;
    }

    /** Analiza los sintomas reportados y sugiere prioridad y tiempo estimado. */
    @PostMapping("/analizar")
    public ResponseEntity<DiagnosticoResponseDTO> analizar(@Valid @RequestBody DiagnosticoRequestDTO dto) {
        return ResponseEntity.ok(diagnosticoService.analizar(dto));
    }
}
