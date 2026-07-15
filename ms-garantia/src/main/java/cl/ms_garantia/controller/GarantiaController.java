package cl.ms_garantia.controller;

import cl.ms_garantia.dto.GarantiaRequestDTO;
import cl.ms_garantia.dto.GarantiaResponseDTO;
import cl.ms_garantia.service.GarantiaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/garantias")
public class GarantiaController {

    private final GarantiaService garantiaService;

    public GarantiaController(GarantiaService garantiaService) {
        this.garantiaService = garantiaService;
    }

    /** Calcula la fecha de vencimiento de garantia para una reparacion. */
    @PostMapping("/calcular")
    public ResponseEntity<GarantiaResponseDTO> calcular(@Valid @RequestBody GarantiaRequestDTO dto) {
        return ResponseEntity.ok(garantiaService.calcular(dto));
    }
}
