package cl.ms_conversor.controller;

import cl.ms_conversor.dto.ConversionResponseDTO;
import cl.ms_conversor.service.ConversorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/conversor")
public class ConversorController {

    private final ConversorService conversorService;

    public ConversorController(ConversorService conversorService) {
        this.conversorService = conversorService;
    }

    /** Convierte un monto en CLP a la moneda indicada (USD, EUR o ARS). */
    @GetMapping("/convertir")
    public ResponseEntity<ConversionResponseDTO> convertir(
            @RequestParam BigDecimal monto,
            @RequestParam String moneda) {
        return ResponseEntity.ok(conversorService.convertir(monto, moneda));
    }
}
