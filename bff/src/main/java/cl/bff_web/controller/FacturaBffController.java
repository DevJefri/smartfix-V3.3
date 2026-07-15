package cl.bff_web.controller;

import cl.bff_web.dto.FacturaBffRequestDTO;
import cl.bff_web.dto.FacturaBffResponseDTO;
import cl.bff_web.service.BffService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Expone ms-factura a traves del BFF. */
@RestController
@RequestMapping("/bff/facturas")
public class FacturaBffController {

    private final BffService bffService;

    public FacturaBffController(BffService bffService) {
        this.bffService = bffService;
    }

    @PostMapping
    public ResponseEntity<FacturaBffResponseDTO> emitir(
            @Valid @RequestBody FacturaBffRequestDTO dto,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bffService.emitirFactura(dto, token(authHeader)));
    }

    @GetMapping("/{folio}")
    public FacturaBffResponseDTO obtener(
            @PathVariable String folio, @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return bffService.obtenerFactura(folio, token(authHeader));
    }

    @GetMapping("/cliente/{rut}")
    public List<FacturaBffResponseDTO> listarPorCliente(
            @PathVariable String rut, @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return bffService.listarFacturasPorCliente(rut, token(authHeader));
    }

    @PatchMapping("/{folio}/anular")
    public FacturaBffResponseDTO anular(
            @PathVariable String folio, @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return bffService.anularFactura(folio, token(authHeader));
    }

    private String token(String authHeader) {
        return authHeader.substring(7);
    }
}
