package cl.ms_inventario.controller;

import cl.ms_inventario.dto.RepuestoRequestDTO;
import cl.ms_inventario.dto.RepuestoResponseDTO;
import cl.ms_inventario.service.RepuestoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repuestos")
public class RepuestoController {

    private final RepuestoService repuestoService;

    public RepuestoController(RepuestoService repuestoService) {
        this.repuestoService = repuestoService;
    }

    @PostMapping
    public ResponseEntity<RepuestoResponseDTO> crear(@Valid @RequestBody RepuestoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repuestoService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<RepuestoResponseDTO>> listar() {
        return ResponseEntity.ok(repuestoService.listarTodos());
    }

    @GetMapping("/{sku}")
    public ResponseEntity<RepuestoResponseDTO> obtener(@PathVariable String sku) {
        return ResponseEntity.ok(repuestoService.obtenerPorSku(sku));
    }

    @PutMapping("/{sku}")
    public ResponseEntity<RepuestoResponseDTO> actualizar(
            @PathVariable String sku, @Valid @RequestBody RepuestoRequestDTO dto) {
        return ResponseEntity.ok(repuestoService.actualizar(sku, dto));
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> eliminar(@PathVariable String sku) {
        repuestoService.eliminar(sku);
        return ResponseEntity.noContent().build();
    }

    /** Ajusta el stock de un repuesto (usar valores negativos para descontar). */
    @PatchMapping("/{sku}/stock")
    public ResponseEntity<RepuestoResponseDTO> ajustarStock(
            @PathVariable String sku, @RequestParam int cantidad) {
        return ResponseEntity.ok(repuestoService.ajustarStock(sku, cantidad));
    }
}
