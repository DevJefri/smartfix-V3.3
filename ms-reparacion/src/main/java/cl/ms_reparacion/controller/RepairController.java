package cl.ms_reparacion.controller;

import cl.ms_reparacion.dto.RepairRequestDTO;
import cl.ms_reparacion.dto.RepairResponseDTO;
import cl.ms_reparacion.service.RepairService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reparaciones")
public class RepairController {

    private final RepairService repairService;

    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }

    @PostMapping
    public ResponseEntity<RepairResponseDTO> create(@Valid @RequestBody RepairRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repairService.createRepair(dto));
    }

    @GetMapping
    public ResponseEntity<List<RepairResponseDTO>> listAll() {
        return ResponseEntity.ok(repairService.listAll());
    }

    @GetMapping("/cliente/{rut}")
    public ResponseEntity<List<RepairResponseDTO>> listByRut(@PathVariable String rut) {
        return ResponseEntity.ok(repairService.listByRut(rut));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<RepairResponseDTO> updateStatus(
            @PathVariable Long id,
            @RequestParam String newStatus) {
        return ResponseEntity.ok(repairService.updateStatus(id, newStatus));
    }
}
