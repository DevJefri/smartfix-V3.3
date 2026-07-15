package cl.ms_conversor.controller;

import cl.ms_conversor.dto.ConversionResponseDTO;
import cl.ms_conversor.service.ConversorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConversorControllerTest {

    @Mock
    private ConversorService conversorService;

    @Test
    void convertir_shouldReturnOk() {
        ConversorController controller = new ConversorController(conversorService);
        ConversionResponseDTO responseDto = new ConversionResponseDTO(
                new BigDecimal("100000"), "CLP", "USD", new BigDecimal("0.0011"), new BigDecimal("110.00"));
        when(conversorService.convertir(any(), any())).thenReturn(responseDto);

        ResponseEntity<ConversionResponseDTO> result = controller.convertir(new BigDecimal("100000"), "USD");

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("USD", result.getBody().monedaDestino());
    }
}
