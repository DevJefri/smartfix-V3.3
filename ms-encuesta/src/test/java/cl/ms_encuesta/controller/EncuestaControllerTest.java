package cl.ms_encuesta.controller;

import cl.ms_encuesta.dto.EncuestaRequestDTO;
import cl.ms_encuesta.dto.EncuestaResponseDTO;
import cl.ms_encuesta.dto.PromedioResponseDTO;
import cl.ms_encuesta.service.EncuestaService;
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
class EncuestaControllerTest {

    @Mock
    private EncuestaService encuestaService;

    @Test
    void registrar_shouldReturnCreated() {
        EncuestaController controller = new EncuestaController(encuestaService);
        EncuestaResponseDTO responseDto = new EncuestaResponseDTO(1L, "12345678-9", 5, "Excelente", LocalDateTime.now());
        when(encuestaService.registrar(any())).thenReturn(responseDto);

        ResponseEntity<EncuestaResponseDTO> result = controller.registrar(
                new EncuestaRequestDTO(1L, "12345678-9", 5, "Excelente"));

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(5, result.getBody().puntuacion());
    }

    @Test
    void promedio_shouldReturnOk() {
        EncuestaController controller = new EncuestaController(encuestaService);
        when(encuestaService.calcularPromedio()).thenReturn(new PromedioResponseDTO(4.5, 2));

        ResponseEntity<PromedioResponseDTO> result = controller.promedio();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(4.5, result.getBody().promedio());
    }
}
