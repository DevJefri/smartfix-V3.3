package cl.bff_web.controller;

import cl.bff_web.dto.NotificationBffRequestDTO;
import cl.bff_web.dto.NotificationBffResponseDTO;
import cl.bff_web.service.BffService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** Expone ms-notificacion a traves del BFF. */
@RestController
@RequestMapping("/bff/notificaciones")
public class NotificationBffController {

    private final BffService bffService;

    public NotificationBffController(BffService bffService) {
        this.bffService = bffService;
    }

    @PostMapping("/enviar")
    public ResponseEntity<NotificationBffResponseDTO> enviar(
            @Valid @RequestBody NotificationBffRequestDTO dto,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bffService.enviarNotificacion(dto, token(authHeader)));
    }

    private String token(String authHeader) {
        return authHeader.substring(7);
    }
}
