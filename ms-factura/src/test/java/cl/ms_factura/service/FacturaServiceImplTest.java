package cl.ms_factura.service;

import cl.ms_factura.dto.FacturaRequestDTO;
import cl.ms_factura.dto.FacturaResponseDTO;
import cl.ms_factura.exception.FacturaNotFoundException;
import cl.ms_factura.exception.FacturaYaAnuladaException;
import cl.ms_factura.model.Factura;
import cl.ms_factura.repository.FacturaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FacturaServiceImplTest {

    @Mock
    private FacturaRepository facturaRepository;

    @Test
    void emitir_shouldGenerateSequentialFolio() {
        FacturaServiceImpl service = new FacturaServiceImpl(facturaRepository);
        when(facturaRepository.count()).thenReturn(4L);
        when(facturaRepository.save(any(Factura.class))).thenAnswer(inv -> inv.getArgument(0));

        FacturaResponseDTO result = service.emitir(
                new FacturaRequestDTO(1L, "12345678-9", new BigDecimal("25000")));

        assertEquals("F-000005", result.folio());
        assertEquals("EMITIDA", result.estado());
    }

    @Test
    void anular_shouldChangeEstadoToAnulada() {
        FacturaServiceImpl service = new FacturaServiceImpl(facturaRepository);
        Factura factura = new Factura();
        factura.setFolio("F-000001");
        factura.setEstado("EMITIDA");
        factura.setFechaEmision(LocalDateTime.now());

        when(facturaRepository.findByFolio("F-000001")).thenReturn(Optional.of(factura));
        when(facturaRepository.save(any(Factura.class))).thenAnswer(inv -> inv.getArgument(0));

        FacturaResponseDTO result = service.anular("F-000001");

        assertEquals("ANULADA", result.estado());
    }

    @Test
    void anular_shouldThrowWhenAlreadyAnulada() {
        FacturaServiceImpl service = new FacturaServiceImpl(facturaRepository);
        Factura factura = new Factura();
        factura.setFolio("F-000002");
        factura.setEstado("ANULADA");

        when(facturaRepository.findByFolio("F-000002")).thenReturn(Optional.of(factura));

        assertThrows(FacturaYaAnuladaException.class, () -> service.anular("F-000002"));
    }

    @Test
    void obtenerPorFolio_shouldThrowWhenNotFound() {
        FacturaServiceImpl service = new FacturaServiceImpl(facturaRepository);
        when(facturaRepository.findByFolio("NO-EXISTE")).thenReturn(Optional.empty());

        assertThrows(FacturaNotFoundException.class, () -> service.obtenerPorFolio("NO-EXISTE"));
    }
}
