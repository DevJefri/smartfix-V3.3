package cl.bff_web.controller;

import cl.bff_web.dto.RepairBffDTO;
import cl.bff_web.dto.RepairBffRequestDTO;
import cl.bff_web.service.BffService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Expone las operaciones de ms-reparacion a traves del BFF. */
@RestController
@RequestMapping("/bff/reparaciones")
public class ReparacionBffController {

    private final BffService bffService;

    public ReparacionBffController(BffService bffService) {
        this.bffService = bffService;
    }

    @PostMapping
    public ResponseEntity<RepairBffDTO> crear(
            @RequestBody RepairBffRequestDTO dto,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bffService.crearReparacion(dto, token(authHeader)));
    }

    @GetMapping
    public List<RepairBffDTO> listar(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return bffService.listarReparaciones(token(authHeader));
    }

    @GetMapping("/cliente/{rut}")
    public List<RepairBffDTO> listarPorCliente(
            @PathVariable String rut, @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return bffService.listarReparacionesPorCliente(rut, token(authHeader));
    }

    @PatchMapping("/{id}/estado")
    public RepairBffDTO actualizarEstado(
            @PathVariable Long id,
            @RequestParam String newStatus,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return bffService.actualizarEstadoReparacion(id, newStatus, token(authHeader));
    }

    private String token(String authHeader) {
        return authHeader.substring(7);
    }
}
