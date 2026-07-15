package cl.bff_web.controller;

import cl.bff_web.dto.GarantiaBffRequestDTO;
import cl.bff_web.dto.GarantiaBffResponseDTO;
import cl.bff_web.service.BffService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

/** Expone ms-garantia a traves del BFF. */
@RestController
@RequestMapping("/bff/garantias")
public class GarantiaBffController {

    private final BffService bffService;

    public GarantiaBffController(BffService bffService) {
        this.bffService = bffService;
    }

    @PostMapping("/calcular")
    public GarantiaBffResponseDTO calcular(
            @Valid @RequestBody GarantiaBffRequestDTO dto,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return bffService.calcularGarantia(dto, authHeader.substring(7));
    }
}
