package cl.bff_web.controller;

import cl.bff_web.dto.ConversionBffResponseDTO;
import cl.bff_web.service.BffService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/** Expone ms-conversor a traves del BFF. */
@RestController
@RequestMapping("/bff/conversor")
public class ConversorBffController {

    private final BffService bffService;

    public ConversorBffController(BffService bffService) {
        this.bffService = bffService;
    }

    @GetMapping("/convertir")
    public ConversionBffResponseDTO convertir(
            @RequestParam BigDecimal monto,
            @RequestParam String moneda,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return bffService.convertirMoneda(monto, moneda, authHeader.substring(7));
    }
}
