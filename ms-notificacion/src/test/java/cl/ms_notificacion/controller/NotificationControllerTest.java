package cl.ms_notificacion.controller;

import cl.ms_notificacion.dto.NotificationRequestDTO;
import cl.ms_notificacion.dto.NotificationResponseDTO;
import cl.ms_notificacion.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotificationControllerTest {

    @Mock
    private NotificationService notificationService;

    @Test
    void enviar_shouldReturnCreated() {
        NotificationController controller = new NotificationController(notificationService);
        NotificationResponseDTO responseDto = new NotificationResponseDTO(
                "abc-123", "cliente@correo.com", "EMAIL", "ENVIADO", LocalDateTime.now());
        when(notificationService.enviar(any())).thenReturn(responseDto);

        ResponseEntity<NotificationResponseDTO> result = controller.enviar(
                new NotificationRequestDTO("cliente@correo.com", "Aviso", "Mensaje", "EMAIL"));

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("ENVIADO", result.getBody().estado());
    }
}
