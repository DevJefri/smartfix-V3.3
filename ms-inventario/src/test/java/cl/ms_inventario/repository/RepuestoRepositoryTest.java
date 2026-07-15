package cl.ms_inventario.repository;

import cl.ms_inventario.model.Repuesto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class RepuestoRepositoryTest {

    @Autowired
    private RepuestoRepository repuestoRepository;

    @Test
    void findBySku_shouldReturnMatchingRepuesto() {
        Repuesto r = new Repuesto();
        r.setSku("SKU-TEST-1");
        r.setNombre("Pantalla generica");
        r.setStock(20);
        r.setPrecioUnitario(new BigDecimal("12000"));
        repuestoRepository.save(r);

        var result = repuestoRepository.findBySku("SKU-TEST-1");

        assertTrue(result.isPresent());
        assertEquals("Pantalla generica", result.get().getNombre());
    }

    @Test
    void existsBySku_shouldReturnFalseForUnknownSku() {
        assertFalse(repuestoRepository.existsBySku("NO-EXISTE-XYZ"));
    }
}
