package cl.ms_notificacion.controller;

import cl.ms_notificacion.dto.NotificationRequestDTO;
import cl.ms_notificacion.dto.NotificationResponseDTO;
import cl.ms_notificacion.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /** Envia (simuladamente) una notificacion por EMAIL o SMS. */
    @PostMapping("/enviar")
    public ResponseEntity<NotificationResponseDTO> enviar(@Valid @RequestBody NotificationRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(notificationService.enviar(dto));
    }
}
