package cl.bff_web.service;

import cl.bff_web.client.*;
import cl.bff_web.dto.*;
import cl.bff_web.exception.MicroserviceUnavailableException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.ResourceAccessException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Pruebas del BFF centradas en la orquestacion hacia los microservicios
 * nuevos: se verifica tanto el camino feliz como la traduccion de fallas
 * de red a MicroserviceUnavailableException.
 */
@ExtendWith(MockitoExtension.class)
class BffServiceImplTest {

    @Mock private CustomerClient customerClient;
    @Mock private RepairClient repairClient;
    @Mock private NotificationClient notificationClient;
    @Mock private CotizacionClient cotizacionClient;
    @Mock private GarantiaClient garantiaClient;
    @Mock private ConversorClient conversorClient;
    @Mock private DiagnosticoClient diagnosticoClient;
    @Mock private InventarioClient inventarioClient;
    @Mock private FacturaClient facturaClient;
    @Mock private EncuestaClient encuestaClient;
    @Mock private AuditoriaClient auditoriaClient;

    private BffServiceImpl buildService() {
        return new BffServiceImpl(customerClient, repairClient, notificationClient, cotizacionClient,
                garantiaClient, conversorClient, diagnosticoClient, inventarioClient,
                facturaClient, encuestaClient, auditoriaClient);
    }

    @Test
    void calcularCotizacion_shouldDelegateToClient() {
        BffServiceImpl service = buildService();
        CotizacionBffResponseDTO expected = new CotizacionBffResponseDTO(
                "CELULAR", "BATERIA", false, new BigDecimal("20000"), BigDecimal.ZERO, new BigDecimal("20000"));
        when(cotizacionClient.calcular(any(), any())).thenReturn(expected);

        CotizacionBffResponseDTO result = service.calcularCotizacion(
                new CotizacionBffRequestDTO("CELULAR", "BATERIA", false), "token123");

        assertEquals(expected, result);
    }

    @Test
    void calcularCotizacion_shouldThrowMicroserviceUnavailableOnNetworkFailure() {
        BffServiceImpl service = buildService();
        when(cotizacionClient.calcular(any(), any())).thenThrow(new ResourceAccessException("timeout"));

        assertThrows(MicroserviceUnavailableException.class, () ->
                service.calcularCotizacion(new CotizacionBffRequestDTO("CELULAR", "BATERIA", false), "token123"));
    }

    @Test
    void convertirMoneda_shouldDelegateToClient() {
        BffServiceImpl service = buildService();
        ConversionBffResponseDTO expected = new ConversionBffResponseDTO(
                new BigDecimal("1000"), "CLP", "USD", new BigDecimal("0.0011"), new BigDecimal("1.10"));
        when(conversorClient.convertir(any(), any(), any())).thenReturn(expected);

        ConversionBffResponseDTO result = service.convertirMoneda(new BigDecimal("1000"), "USD", "token123");

        assertEquals("USD", result.monedaDestino());
    }

    @Test
    void listarRepuestos_shouldThrowMicroserviceUnavailableWhenInventarioIsDown() {
        BffServiceImpl service = buildService();
        when(inventarioClient.listar(any())).thenThrow(new ResourceAccessException("connection refused"));

        assertThrows(MicroserviceUnavailableException.class, () -> service.listarRepuestos("token123"));
    }

    @Test
    void crearCliente_shouldDelegateToCustomerClient() {
        BffServiceImpl service = buildService();
        CustomerBffDTO expected = new CustomerBffDTO("12345678-9", "Juan Perez", "+56912345678", "juan@mail.com");
        when(customerClient.crear(any(), any())).thenReturn(expected);

        CustomerBffDTO result = service.crearCliente(expected, "token123");

        assertEquals("12345678-9", result.rut());
    }

    @Test
    void crearReparacion_shouldDelegateToRepairClient() {
        BffServiceImpl service = buildService();
        RepairBffDTO expected = new RepairBffDTO(1L, "12345678-9", "iPhone 13", "Pantalla rota", "RECIBIDO");
        when(repairClient.crear(any(), any())).thenReturn(expected);

        RepairBffDTO result = service.crearReparacion(
                new RepairBffRequestDTO("12345678-9", "iPhone 13", "Pantalla rota"), "token123");

        assertEquals("RECIBIDO", result.status());
    }

    @Test
    void listarReparaciones_shouldThrowMicroserviceUnavailableWhenReparacionIsDown() {
        BffServiceImpl service = buildService();
        when(repairClient.listarTodas(any())).thenThrow(new ResourceAccessException("timeout"));

        assertThrows(MicroserviceUnavailableException.class, () -> service.listarReparaciones("token123"));
    }
}
