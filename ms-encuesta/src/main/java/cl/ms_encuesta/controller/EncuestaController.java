package cl.ms_encuesta.controller;

import cl.ms_encuesta.dto.EncuestaRequestDTO;
import cl.ms_encuesta.dto.EncuestaResponseDTO;
import cl.ms_encuesta.dto.PromedioResponseDTO;
import cl.ms_encuesta.service.EncuestaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/encuestas")
public class EncuestaController {

    private final EncuestaService encuestaService;

    public EncuestaController(EncuestaService encuestaService) {
        this.encuestaService = encuestaService;
    }

    @PostMapping
    public ResponseEntity<EncuestaResponseDTO> registrar(@Valid @RequestBody EncuestaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(encuestaService.registrar(dto));
    }

    @GetMapping("/reparacion/{reparacionId}")
    public ResponseEntity<EncuestaResponseDTO> obtenerPorReparacion(@PathVariable Long reparacionId) {
        return ResponseEntity.ok(encuestaService.obtenerPorReparacion(reparacionId));
    }

    @GetMapping("/promedio")
    public ResponseEntity<PromedioResponseDTO> promedio() {
        return ResponseEntity.ok(encuestaService.calcularPromedio());
    }
}
