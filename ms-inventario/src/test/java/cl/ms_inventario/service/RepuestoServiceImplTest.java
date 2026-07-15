package cl.ms_inventario.service;

import cl.ms_inventario.dto.RepuestoRequestDTO;
import cl.ms_inventario.dto.RepuestoResponseDTO;
import cl.ms_inventario.exception.RepuestoDuplicadoException;
import cl.ms_inventario.exception.RepuestoNotFoundException;
import cl.ms_inventario.exception.StockInsuficienteException;
import cl.ms_inventario.model.Repuesto;
import cl.ms_inventario.repository.RepuestoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RepuestoServiceImplTest {

    @Mock
    private RepuestoRepository repuestoRepository;

    @Test
    void crear_shouldThrowWhenSkuAlreadyExists() {
        RepuestoServiceImpl service = new RepuestoServiceImpl(repuestoRepository);
        when(repuestoRepository.existsBySku("SKU-1")).thenReturn(true);

        assertThrows(RepuestoDuplicadoException.class, () ->
                service.crear(new RepuestoRequestDTO("SKU-1", "Pantalla iPhone", 10, new BigDecimal("15000"))));
    }

    @Test
    void crear_shouldSaveWhenSkuIsNew() {
        RepuestoServiceImpl service = new RepuestoServiceImpl(repuestoRepository);
        when(repuestoRepository.existsBySku("SKU-2")).thenReturn(false);
        when(repuestoRepository.save(any(Repuesto.class))).thenAnswer(inv -> inv.getArgument(0));

        RepuestoResponseDTO result = service.crear(
                new RepuestoRequestDTO("SKU-2", "Bateria Samsung", 5, new BigDecimal("8000")));

        assertEquals("SKU-2", result.sku());
        assertEquals(5, result.stock());
    }

    @Test
    void ajustarStock_shouldIncreaseStock() {
        RepuestoServiceImpl service = new RepuestoServiceImpl(repuestoRepository);
        Repuesto existente = new Repuesto();
        existente.setSku("SKU-3");
        existente.setNombre("Cable USB-C");
        existente.setStock(10);
        existente.setPrecioUnitario(new BigDecimal("3000"));

        when(repuestoRepository.findBySku("SKU-3")).thenReturn(Optional.of(existente));
        when(repuestoRepository.save(any(Repuesto.class))).thenAnswer(inv -> inv.getArgument(0));

        RepuestoResponseDTO result = service.ajustarStock("SKU-3", 5);

        assertEquals(15, result.stock());
    }

    @Test
    void ajustarStock_shouldThrowWhenResultIsNegative() {
        RepuestoServiceImpl service = new RepuestoServiceImpl(repuestoRepository);
        Repuesto existente = new Repuesto();
        existente.setSku("SKU-4");
        existente.setStock(3);

        when(repuestoRepository.findBySku("SKU-4")).thenReturn(Optional.of(existente));

        assertThrows(StockInsuficienteException.class, () -> service.ajustarStock("SKU-4", -10));
    }

    @Test
    void obtenerPorSku_shouldThrowWhenNotFound() {
        RepuestoServiceImpl service = new RepuestoServiceImpl(repuestoRepository);
        when(repuestoRepository.findBySku("NO-EXISTE")).thenReturn(Optional.empty());

        assertThrows(RepuestoNotFoundException.class, () -> service.obtenerPorSku("NO-EXISTE"));
    }
}
