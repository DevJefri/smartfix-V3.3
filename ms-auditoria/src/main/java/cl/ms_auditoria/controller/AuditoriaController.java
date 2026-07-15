package cl.ms_auditoria.controller;

import cl.ms_auditoria.dto.EventoAuditoriaRequestDTO;
import cl.ms_auditoria.dto.EventoAuditoriaResponseDTO;
import cl.ms_auditoria.service.AuditoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auditoria")
public class AuditoriaController {

    private final AuditoriaService auditoriaService;

    public AuditoriaController(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<EventoAuditoriaResponseDTO> registrar(@Valid @RequestBody EventoAuditoriaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(auditoriaService.registrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<EventoAuditoriaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(auditoriaService.listarTodos());
    }

    @GetMapping("/servicio/{nombre}")
    public ResponseEntity<List<EventoAuditoriaResponseDTO>> listarPorServicio(@PathVariable String nombre) {
        return ResponseEntity.ok(auditoriaService.listarPorServicio(nombre));
    }
}
