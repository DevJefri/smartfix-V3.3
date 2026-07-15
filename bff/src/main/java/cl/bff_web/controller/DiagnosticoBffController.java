package cl.bff_web.controller;

import cl.bff_web.dto.DiagnosticoBffRequestDTO;
import cl.bff_web.dto.DiagnosticoBffResponseDTO;
import cl.bff_web.service.BffService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

/** Expone ms-diagnostico a traves del BFF. */
@RestController
@RequestMapping("/bff/diagnostico")
public class DiagnosticoBffController {

    private final BffService bffService;

    public DiagnosticoBffController(BffService bffService) {
        this.bffService = bffService;
    }

    @PostMapping("/analizar")
    public DiagnosticoBffResponseDTO analizar(
            @Valid @RequestBody DiagnosticoBffRequestDTO dto,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return bffService.analizarDiagnostico(dto, authHeader.substring(7));
    }
}
