package cl.ms_auditoria.controller;

import cl.ms_auditoria.dto.EventoAuditoriaRequestDTO;
import cl.ms_auditoria.dto.EventoAuditoriaResponseDTO;
import cl.ms_auditoria.service.AuditoriaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuditoriaControllerTest {

    @Mock
    private AuditoriaService auditoriaService;

    @Test
    void registrar_shouldReturnCreated() {
        AuditoriaController controller = new AuditoriaController(auditoriaService);
        EventoAuditoriaResponseDTO responseDto = new EventoAuditoriaResponseDTO(
                1L, "ms-reparacion", "CREAR_REPARACION", "id=1", LocalDateTime.now());
        when(auditoriaService.registrar(any())).thenReturn(responseDto);

        ResponseEntity<EventoAuditoriaResponseDTO> result = controller.registrar(
                new EventoAuditoriaRequestDTO("ms-reparacion", "CREAR_REPARACION", "id=1"));

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    void listarPorServicio_shouldReturnOk() {
        AuditoriaController controller = new AuditoriaController(auditoriaService);
        when(auditoriaService.listarPorServicio("ms-factura")).thenReturn(List.of());

        ResponseEntity<List<EventoAuditoriaResponseDTO>> result = controller.listarPorServicio("ms-factura");

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}
