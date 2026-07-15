package cl.bff_web.service;

import cl.bff_web.client.*;
import cl.bff_web.dto.*;
import cl.bff_web.exception.MicroserviceUnavailableException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.math.BigDecimal;
import java.util.List;

/**
 * Implementacion del BFF: orquesta las llamadas a los 11 microservicios de
 * Smartfix (ms-cliente, ms-reparacion y los 9 microservicios adicionales)
 * y traduce cualquier falla de red hacia un microservicio en una
 * MicroserviceUnavailableException con un mensaje claro para el cliente.
 */
@Service
public class BffServiceImpl implements BffService {

    private final CustomerClient customerClient;
    private final RepairClient repairClient;
    private final NotificationClient notificationClient;
    private final CotizacionClient cotizacionClient;
    private final GarantiaClient garantiaClient;
    private final ConversorClient conversorClient;
    private final DiagnosticoClient diagnosticoClient;
    private final InventarioClient inventarioClient;
    private final FacturaClient facturaClient;
    private final EncuestaClient encuestaClient;
    private final AuditoriaClient auditoriaClient;

    public BffServiceImpl(CustomerClient customerClient,
                           RepairClient repairClient,
                           NotificationClient notificationClient,
                           CotizacionClient cotizacionClient,
                           GarantiaClient garantiaClient,
                           ConversorClient conversorClient,
                           DiagnosticoClient diagnosticoClient,
                           InventarioClient inventarioClient,
                           FacturaClient facturaClient,
                           EncuestaClient encuestaClient,
                           AuditoriaClient auditoriaClient) {
        this.customerClient = customerClient;
        this.repairClient = repairClient;
        this.notificationClient = notificationClient;
        this.cotizacionClient = cotizacionClient;
        this.garantiaClient = garantiaClient;
        this.conversorClient = conversorClient;
        this.diagnosticoClient = diagnosticoClient;
        this.inventarioClient = inventarioClient;
        this.facturaClient = facturaClient;
        this.encuestaClient = encuestaClient;
        this.auditoriaClient = auditoriaClient;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO dto) {
        return execute("ms-cliente", () -> customerClient.login(dto));
    }

    @Override
    public LoginResponseDTO register(LoginRequestDTO dto) {
        return execute("ms-cliente", () -> customerClient.register(dto));
    }

    @Override
    public DashboardResponseDTO getDashboard(String rut, String token) {
        return execute("ms-cliente/ms-reparacion", () -> {
            var customer = customerClient.getByRut(rut, token);
            var repairs = repairClient.listByRut(rut, token);
            return new DashboardResponseDTO(customer, repairs);
        });
    }

    @Override
    public CustomerBffDTO crearCliente(CustomerBffDTO dto, String token) {
        return execute("ms-cliente", () -> customerClient.crear(dto, token));
    }

    @Override
    public List<CustomerBffDTO> listarClientes(String token) {
        return execute("ms-cliente", () -> customerClient.listar(token));
    }

    @Override
    public CustomerBffDTO obtenerCliente(String rut, String token) {
        return execute("ms-cliente", () -> customerClient.getByRut(rut, token));
    }

    @Override
    public CustomerBffDTO actualizarCliente(String rut, CustomerBffDTO dto, String token) {
        return execute("ms-cliente", () -> customerClient.actualizar(rut, dto, token));
    }

    @Override
    public void eliminarCliente(String rut, String token) {
        execute("ms-cliente", () -> {
            customerClient.eliminar(rut, token);
            return null;
        });
    }

    @Override
    public RepairBffDTO crearReparacion(RepairBffRequestDTO dto, String token) {
        return execute("ms-reparacion", () -> repairClient.crear(dto, token));
    }

    @Override
    public List<RepairBffDTO> listarReparaciones(String token) {
        return execute("ms-reparacion", () -> repairClient.listarTodas(token));
    }

    @Override
    public List<RepairBffDTO> listarReparacionesPorCliente(String rut, String token) {
        return execute("ms-reparacion", () -> repairClient.listByRut(rut, token));
    }

    @Override
    public RepairBffDTO actualizarEstadoReparacion(Long id, String nuevoEstado, String token) {
        return execute("ms-reparacion", () -> repairClient.actualizarEstado(id, nuevoEstado, token));
    }

    @Override
    public NotificationBffResponseDTO enviarNotificacion(NotificationBffRequestDTO dto, String token) {
        return execute("ms-notificacion", () -> notificationClient.enviar(dto, token));
    }

    @Override
    public CotizacionBffResponseDTO calcularCotizacion(CotizacionBffRequestDTO dto, String token) {
        return execute("ms-cotizacion", () -> cotizacionClient.calcular(dto, token));
    }

    @Override
    public GarantiaBffResponseDTO calcularGarantia(GarantiaBffRequestDTO dto, String token) {
        return execute("ms-garantia", () -> garantiaClient.calcular(dto, token));
    }

    @Override
    public ConversionBffResponseDTO convertirMoneda(BigDecimal monto, String moneda, String token) {
        return execute("ms-conversor", () -> conversorClient.convertir(monto, moneda, token));
    }

    @Override
    public DiagnosticoBffResponseDTO analizarDiagnostico(DiagnosticoBffRequestDTO dto, String token) {
        return execute("ms-diagnostico", () -> diagnosticoClient.analizar(dto, token));
    }

    @Override
    public RepuestoBffResponseDTO crearRepuesto(RepuestoBffRequestDTO dto, String token) {
        return execute("ms-inventario", () -> inventarioClient.crear(dto, token));
    }

    @Override
    public List<RepuestoBffResponseDTO> listarRepuestos(String token) {
        return execute("ms-inventario", () -> inventarioClient.listar(token));
    }

    @Override
    public RepuestoBffResponseDTO obtenerRepuesto(String sku, String token) {
        return execute("ms-inventario", () -> inventarioClient.obtener(sku, token));
    }

    @Override
    public RepuestoBffResponseDTO actualizarRepuesto(String sku, RepuestoBffRequestDTO dto, String token) {
        return execute("ms-inventario", () -> inventarioClient.actualizar(sku, dto, token));
    }

    @Override
    public void eliminarRepuesto(String sku, String token) {
        execute("ms-inventario", () -> {
            inventarioClient.eliminar(sku, token);
            return null;
        });
    }

    @Override
    public RepuestoBffResponseDTO ajustarStockRepuesto(String sku, int cantidad, String token) {
        return execute("ms-inventario", () -> inventarioClient.ajustarStock(sku, cantidad, token));
    }

    @Override
    public FacturaBffResponseDTO emitirFactura(FacturaBffRequestDTO dto, String token) {
        return execute("ms-factura", () -> facturaClient.emitir(dto, token));
    }

    @Override
    public FacturaBffResponseDTO obtenerFactura(String folio, String token) {
        return execute("ms-factura", () -> facturaClient.obtener(folio, token));
    }

    @Override
    public List<FacturaBffResponseDTO> listarFacturasPorCliente(String rut, String token) {
        return execute("ms-factura", () -> facturaClient.listarPorCliente(rut, token));
    }

    @Override
    public FacturaBffResponseDTO anularFactura(String folio, String token) {
        return execute("ms-factura", () -> facturaClient.anular(folio, token));
    }

    @Override
    public EncuestaBffResponseDTO registrarEncuesta(EncuestaBffRequestDTO dto, String token) {
        return execute("ms-encuesta", () -> encuestaClient.registrar(dto, token));
    }

    @Override
    public EncuestaBffResponseDTO obtenerEncuestaPorReparacion(Long reparacionId, String token) {
        return execute("ms-encuesta", () -> encuestaClient.obtenerPorReparacion(reparacionId, token));
    }

    @Override
    public PromedioBffResponseDTO promedioEncuestas(String token) {
        return execute("ms-encuesta", () -> encuestaClient.promedio(token));
    }

    @Override
    public EventoAuditoriaBffResponseDTO registrarEventoAuditoria(EventoAuditoriaBffRequestDTO dto, String token) {
        return execute("ms-auditoria", () -> auditoriaClient.registrar(dto, token));
    }

    @Override
    public List<EventoAuditoriaBffResponseDTO> listarAuditoria(String token) {
        return execute("ms-auditoria", () -> auditoriaClient.listarTodos(token));
    }

    @Override
    public List<EventoAuditoriaBffResponseDTO> listarAuditoriaPorServicio(String nombre, String token) {
        return execute("ms-auditoria", () -> auditoriaClient.listarPorServicio(nombre, token));
    }

    /**
     * Ejecuta una llamada a un microservicio, centralizando el manejo de
     * errores: si el microservicio no responde (timeout, host caido, etc.)
     * se traduce a una MicroserviceUnavailableException con el nombre del
     * servicio afectado, en vez de dejar escapar la excepcion tecnica.
     */
    private <T> T execute(String serviceName, RemoteCall<T> call) {
        try {
            return call.run();
        } catch (ResourceAccessException e) {
            throw new MicroserviceUnavailableException(serviceName);
        }
    }

    @FunctionalInterface
    private interface RemoteCall<T> {
        T run();
    }
}
