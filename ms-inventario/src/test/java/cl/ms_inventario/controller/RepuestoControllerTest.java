package cl.ms_inventario.controller;

import cl.ms_inventario.dto.RepuestoRequestDTO;
import cl.ms_inventario.dto.RepuestoResponseDTO;
import cl.ms_inventario.service.RepuestoService;
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
class RepuestoControllerTest {

    @Mock
    private RepuestoService repuestoService;

    @Test
    void crear_shouldReturnCreated() {
        RepuestoController controller = new RepuestoController(repuestoService);
        RepuestoResponseDTO responseDto = new RepuestoResponseDTO(1L, "SKU-1", "Pantalla", 10, new BigDecimal("15000"));
        when(repuestoService.crear(any())).thenReturn(responseDto);

        ResponseEntity<RepuestoResponseDTO> result = controller.crear(
                new RepuestoRequestDTO("SKU-1", "Pantalla", 10, new BigDecimal("15000")));

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    void ajustarStock_shouldReturnOk() {
        RepuestoController controller = new RepuestoController(repuestoService);
        RepuestoResponseDTO responseDto = new RepuestoResponseDTO(1L, "SKU-1", "Pantalla", 15, new BigDecimal("15000"));
        when(repuestoService.ajustarStock("SKU-1", 5)).thenReturn(responseDto);

        ResponseEntity<RepuestoResponseDTO> result = controller.ajustarStock("SKU-1", 5);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(15, result.getBody().stock());
    }
}
