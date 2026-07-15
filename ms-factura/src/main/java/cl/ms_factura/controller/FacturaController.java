package cl.ms_factura.controller;

import cl.ms_factura.dto.FacturaRequestDTO;
import cl.ms_factura.dto.FacturaResponseDTO;
import cl.ms_factura.service.FacturaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @PostMapping
    public ResponseEntity<FacturaResponseDTO> emitir(@Valid @RequestBody FacturaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(facturaService.emitir(dto));
    }

    @GetMapping("/{folio}")
    public ResponseEntity<FacturaResponseDTO> obtener(@PathVariable String folio) {
        return ResponseEntity.ok(facturaService.obtenerPorFolio(folio));
    }

    @GetMapping("/cliente/{rut}")
    public ResponseEntity<List<FacturaResponseDTO>> listarPorCliente(@PathVariable String rut) {
        return ResponseEntity.ok(facturaService.listarPorCliente(rut));
    }

    @PatchMapping("/{folio}/anular")
    public ResponseEntity<FacturaResponseDTO> anular(@PathVariable String folio) {
        return ResponseEntity.ok(facturaService.anular(folio));
    }
}
