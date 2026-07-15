package cl.bff_web.controller;

import cl.bff_web.dto.RepuestoBffRequestDTO;
import cl.bff_web.dto.RepuestoBffResponseDTO;
import cl.bff_web.service.BffService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Expone ms-inventario a traves del BFF. */
@RestController
@RequestMapping("/bff/repuestos")
public class RepuestoBffController {

    private final BffService bffService;

    public RepuestoBffController(BffService bffService) {
        this.bffService = bffService;
    }

    @PostMapping
    public ResponseEntity<RepuestoBffResponseDTO> crear(
            @Valid @RequestBody RepuestoBffRequestDTO dto,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bffService.crearRepuesto(dto, token(authHeader)));
    }

    @GetMapping
    public List<RepuestoBffResponseDTO> listar(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return bffService.listarRepuestos(token(authHeader));
    }

    @GetMapping("/{sku}")
    public RepuestoBffResponseDTO obtener(
            @PathVariable String sku, @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return bffService.obtenerRepuesto(sku, token(authHeader));
    }

    @PutMapping("/{sku}")
    public RepuestoBffResponseDTO actualizar(
            @PathVariable String sku,
            @Valid @RequestBody RepuestoBffRequestDTO dto,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return bffService.actualizarRepuesto(sku, dto, token(authHeader));
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> eliminar(
            @PathVariable String sku, @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        bffService.eliminarRepuesto(sku, token(authHeader));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{sku}/stock")
    public RepuestoBffResponseDTO ajustarStock(
            @PathVariable String sku,
            @RequestParam int cantidad,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return bffService.ajustarStockRepuesto(sku, cantidad, token(authHeader));
    }

    private String token(String authHeader) {
        return authHeader.substring(7);
    }
}
