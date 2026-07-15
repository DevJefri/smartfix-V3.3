package cl.ms_cotizacion.controller;

import cl.ms_cotizacion.dto.CotizacionRequestDTO;
import cl.ms_cotizacion.dto.CotizacionResponseDTO;
import cl.ms_cotizacion.service.CotizacionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cotizaciones")
public class CotizacionController {

    private final CotizacionService cotizacionService;

    public CotizacionController(CotizacionService cotizacionService) {
        this.cotizacionService = cotizacionService;
    }

    /** Calcula el presupuesto estimado para una reparacion. */
    @PostMapping("/calcular")
    public ResponseEntity<CotizacionResponseDTO> calcular(@Valid @RequestBody CotizacionRequestDTO dto) {
        return ResponseEntity.ok(cotizacionService.calcular(dto));
    }
}
