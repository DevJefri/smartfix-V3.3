package cl.bff_web.controller;

import cl.bff_web.dto.CotizacionBffRequestDTO;
import cl.bff_web.dto.CotizacionBffResponseDTO;
import cl.bff_web.service.BffService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

/** Expone ms-cotizacion a traves del BFF. */
@RestController
@RequestMapping("/bff/cotizaciones")
public class CotizacionBffController {

    private final BffService bffService;

    public CotizacionBffController(BffService bffService) {
        this.bffService = bffService;
    }

    @PostMapping("/calcular")
    public CotizacionBffResponseDTO calcular(
            @Valid @RequestBody CotizacionBffRequestDTO dto,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return bffService.calcularCotizacion(dto, authHeader.substring(7));
    }
}
