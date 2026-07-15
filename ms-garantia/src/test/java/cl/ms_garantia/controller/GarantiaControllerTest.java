package cl.ms_garantia.controller;

import cl.ms_garantia.dto.GarantiaRequestDTO;
import cl.ms_garantia.dto.GarantiaResponseDTO;
import cl.ms_garantia.service.GarantiaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GarantiaControllerTest {

    @Mock
    private GarantiaService garantiaService;

    @Test
    void calcular_shouldReturnOk() {
        GarantiaController controller = new GarantiaController(garantiaService);
        LocalDate entrega = LocalDate.of(2026, 1, 1);
        GarantiaResponseDTO responseDto = new GarantiaResponseDTO(
                "PANTALLA", entrega, entrega.plusDays(90), 90);
        when(garantiaService.calcular(any())).thenReturn(responseDto);

        ResponseEntity<GarantiaResponseDTO> result = controller.calcular(
                new GarantiaRequestDTO(entrega, "PANTALLA"));

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(90, result.getBody().diasGarantia());
    }
}
