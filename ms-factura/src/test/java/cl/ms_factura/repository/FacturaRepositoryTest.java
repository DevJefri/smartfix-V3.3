package cl.ms_factura.repository;

import cl.ms_factura.model.Factura;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class FacturaRepositoryTest {

    @Autowired
    private FacturaRepository facturaRepository;

    @Test
    void findByRutCliente_shouldReturnOnlyMatchingFacturas() {
        Factura f1 = new Factura();
        f1.setFolio("F-100001");
        f1.setReparacionId(1L);
        f1.setRutCliente("11111111-1");
        f1.setMonto(new BigDecimal("10000"));
        f1.setEstado("EMITIDA");
        f1.setFechaEmision(LocalDateTime.now());
        facturaRepository.save(f1);

        Factura f2 = new Factura();
        f2.setFolio("F-100002");
        f2.setReparacionId(2L);
        f2.setRutCliente("22222222-2");
        f2.setMonto(new BigDecimal("20000"));
        f2.setEstado("EMITIDA");
        f2.setFechaEmision(LocalDateTime.now());
        facturaRepository.save(f2);

        var result = facturaRepository.findByRutCliente("11111111-1");

        assertEquals(1, result.size());
        assertEquals("F-100001", result.get(0).getFolio());
    }
}
