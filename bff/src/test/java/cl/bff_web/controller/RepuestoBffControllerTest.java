package cl.bff_web.controller;

import cl.bff_web.dto.RepuestoBffRequestDTO;
import cl.bff_web.dto.RepuestoBffResponseDTO;
import cl.bff_web.service.BffService;
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
class RepuestoBffControllerTest {

    @Mock
    private BffService bffService;

    @Test
    void crear_shouldReturnCreated() {
        RepuestoBffController controller = new RepuestoBffController(bffService);
        RepuestoBffResponseDTO expected = new RepuestoBffResponseDTO(1L, "SKU-1", "Pantalla", 10, new BigDecimal("15000"));
        when(bffService.crearRepuesto(any(), any())).thenReturn(expected);

        ResponseEntity<RepuestoBffResponseDTO> result = controller.crear(
                new RepuestoBffRequestDTO("SKU-1", "Pantalla", 10, new BigDecimal("15000")), "Bearer token123");

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals("SKU-1", result.getBody().sku());
    }

    @Test
    void ajustarStock_shouldDelegateToBffService() {
        RepuestoBffController controller = new RepuestoBffController(bffService);
        RepuestoBffResponseDTO expected = new RepuestoBffResponseDTO(1L, "SKU-1", "Pantalla", 15, new BigDecimal("15000"));
        when(bffService.ajustarStockRepuesto("SKU-1", 5, "token123")).thenReturn(expected);

        RepuestoBffResponseDTO result = controller.ajustarStock("SKU-1", 5, "Bearer token123");

        assertEquals(15, result.stock());
    }
}
