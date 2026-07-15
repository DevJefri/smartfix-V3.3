package cl.bff_web.controller;

import cl.bff_web.dto.CustomerBffDTO;
import cl.bff_web.service.BffService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Expone el CRUD de ms-cliente a traves del BFF. */
@RestController
@RequestMapping("/bff/clientes")
public class ClienteBffController {

    private final BffService bffService;

    public ClienteBffController(BffService bffService) {
        this.bffService = bffService;
    }

    @PostMapping
    public ResponseEntity<CustomerBffDTO> crear(
            @Valid @RequestBody CustomerBffDTO dto,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bffService.crearCliente(dto, token(authHeader)));
    }

    @GetMapping
    public List<CustomerBffDTO> listar(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return bffService.listarClientes(token(authHeader));
    }

    @GetMapping("/{rut}")
    public CustomerBffDTO obtener(
            @PathVariable String rut, @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return bffService.obtenerCliente(rut, token(authHeader));
    }

    @PutMapping("/{rut}")
    public CustomerBffDTO actualizar(
            @PathVariable String rut,
            @Valid @RequestBody CustomerBffDTO dto,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return bffService.actualizarCliente(rut, dto, token(authHeader));
    }

    @DeleteMapping("/{rut}")
    public ResponseEntity<Void> eliminar(
            @PathVariable String rut, @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        bffService.eliminarCliente(rut, token(authHeader));
        return ResponseEntity.noContent().build();
    }

    private String token(String authHeader) {
        return authHeader.substring(7);
    }
}
