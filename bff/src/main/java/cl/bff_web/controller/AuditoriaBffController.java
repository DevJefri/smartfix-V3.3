package cl.bff_web.controller;

import cl.bff_web.dto.EventoAuditoriaBffRequestDTO;
import cl.bff_web.dto.EventoAuditoriaBffResponseDTO;
import cl.bff_web.service.BffService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Expone ms-auditoria a traves del BFF. */
@RestController
@RequestMapping("/bff/auditoria")
public class AuditoriaBffController {

    private final BffService bffService;

    public AuditoriaBffController(BffService bffService) {
        this.bffService = bffService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<EventoAuditoriaBffResponseDTO> registrar(
            @Valid @RequestBody EventoAuditoriaBffRequestDTO dto,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bffService.registrarEventoAuditoria(dto, token(authHeader)));
    }

    @GetMapping
    public List<EventoAuditoriaBffResponseDTO> listarTodos(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return bffService.listarAuditoria(token(authHeader));
    }

    @GetMapping("/servicio/{nombre}")
    public List<EventoAuditoriaBffResponseDTO> listarPorServicio(
            @PathVariable String nombre, @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return bffService.listarAuditoriaPorServicio(nombre, token(authHeader));
    }

    private String token(String authHeader) {
        return authHeader.substring(7);
    }
}
