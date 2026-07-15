package cl.bff_web.controller;

import cl.bff_web.dto.EncuestaBffRequestDTO;
import cl.bff_web.dto.EncuestaBffResponseDTO;
import cl.bff_web.dto.PromedioBffResponseDTO;
import cl.bff_web.service.BffService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** Expone ms-encuesta a traves del BFF. */
@RestController
@RequestMapping("/bff/encuestas")
public class EncuestaBffController {

    private final BffService bffService;

    public EncuestaBffController(BffService bffService) {
        this.bffService = bffService;
    }

    @PostMapping
    public ResponseEntity<EncuestaBffResponseDTO> registrar(
            @Valid @RequestBody EncuestaBffRequestDTO dto,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bffService.registrarEncuesta(dto, token(authHeader)));
    }

    @GetMapping("/reparacion/{reparacionId}")
    public EncuestaBffResponseDTO obtenerPorReparacion(
            @PathVariable Long reparacionId, @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return bffService.obtenerEncuestaPorReparacion(reparacionId, token(authHeader));
    }

    @GetMapping("/promedio")
    public PromedioBffResponseDTO promedio(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return bffService.promedioEncuestas(token(authHeader));
    }

    private String token(String authHeader) {
        return authHeader.substring(7);
    }
}
