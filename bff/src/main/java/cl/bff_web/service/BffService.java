package cl.bff_web.service;

import cl.bff_web.dto.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Contrato del BFF: cada metodo orquesta una o mas llamadas hacia los
 * microservicios de Smartfix. Es el unico componente autorizado a
 * hablar directamente con los microservicios; todo el resto del sistema
 * (incluido el API Gateway) pasa por aqui.
 */
public interface BffService {

    /** Autentica un usuario delegando en ms-cliente. */
    LoginResponseDTO login(LoginRequestDTO dto);

    /** Registra un nuevo usuario delegando en ms-cliente. */
    LoginResponseDTO register(LoginRequestDTO dto);

    /** Combina los datos de un cliente y sus reparaciones en una sola respuesta. */
    DashboardResponseDTO getDashboard(String rut, String token);

    /** Registra un cliente via ms-cliente. */
    CustomerBffDTO crearCliente(CustomerBffDTO dto, String token);

    /** Lista todos los clientes via ms-cliente. */
    List<CustomerBffDTO> listarClientes(String token);

    /** Obtiene un cliente por RUT via ms-cliente. */
    CustomerBffDTO obtenerCliente(String rut, String token);

    /** Actualiza un cliente via ms-cliente. */
    CustomerBffDTO actualizarCliente(String rut, CustomerBffDTO dto, String token);

    /** Elimina un cliente via ms-cliente. */
    void eliminarCliente(String rut, String token);

    /** Crea una reparacion via ms-reparacion. */
    RepairBffDTO crearReparacion(RepairBffRequestDTO dto, String token);

    /** Lista todas las reparaciones via ms-reparacion. */
    List<RepairBffDTO> listarReparaciones(String token);

    /** Lista las reparaciones de un cliente via ms-reparacion. */
    List<RepairBffDTO> listarReparacionesPorCliente(String rut, String token);

    /** Actualiza el estado de una reparacion via ms-reparacion. */
    RepairBffDTO actualizarEstadoReparacion(Long id, String nuevoEstado, String token);

    /** Envia una notificacion via ms-notificacion. */
    NotificationBffResponseDTO enviarNotificacion(NotificationBffRequestDTO dto, String token);

    /** Calcula el presupuesto de una reparacion via ms-cotizacion. */
    CotizacionBffResponseDTO calcularCotizacion(CotizacionBffRequestDTO dto, String token);

    /** Calcula la fecha de vencimiento de garantia via ms-garantia. */
    GarantiaBffResponseDTO calcularGarantia(GarantiaBffRequestDTO dto, String token);

    /** Convierte un monto en CLP a otra moneda via ms-conversor. */
    ConversionBffResponseDTO convertirMoneda(BigDecimal monto, String moneda, String token);

    /** Sugiere prioridad y tiempo estimado via ms-diagnostico. */
    DiagnosticoBffResponseDTO analizarDiagnostico(DiagnosticoBffRequestDTO dto, String token);

    /** Crea un repuesto en el inventario via ms-inventario. */
    RepuestoBffResponseDTO crearRepuesto(RepuestoBffRequestDTO dto, String token);

    /** Lista todos los repuestos via ms-inventario. */
    List<RepuestoBffResponseDTO> listarRepuestos(String token);

    /** Obtiene un repuesto por SKU via ms-inventario. */
    RepuestoBffResponseDTO obtenerRepuesto(String sku, String token);

    /** Actualiza un repuesto via ms-inventario. */
    RepuestoBffResponseDTO actualizarRepuesto(String sku, RepuestoBffRequestDTO dto, String token);

    /** Elimina un repuesto via ms-inventario. */
    void eliminarRepuesto(String sku, String token);

    /** Ajusta el stock de un repuesto via ms-inventario. */
    RepuestoBffResponseDTO ajustarStockRepuesto(String sku, int cantidad, String token);

    /** Emite una factura via ms-factura. */
    FacturaBffResponseDTO emitirFactura(FacturaBffRequestDTO dto, String token);

    /** Obtiene una factura por folio via ms-factura. */
    FacturaBffResponseDTO obtenerFactura(String folio, String token);

    /** Lista las facturas de un cliente via ms-factura. */
    List<FacturaBffResponseDTO> listarFacturasPorCliente(String rut, String token);

    /** Anula una factura via ms-factura. */
    FacturaBffResponseDTO anularFactura(String folio, String token);

    /** Registra la encuesta de satisfaccion de una reparacion via ms-encuesta. */
    EncuestaBffResponseDTO registrarEncuesta(EncuestaBffRequestDTO dto, String token);

    /** Obtiene la encuesta de una reparacion via ms-encuesta. */
    EncuestaBffResponseDTO obtenerEncuestaPorReparacion(Long reparacionId, String token);

    /** Calcula el promedio general de satisfaccion via ms-encuesta. */
    PromedioBffResponseDTO promedioEncuestas(String token);

    /** Registra un evento en la bitacora via ms-auditoria. */
    EventoAuditoriaBffResponseDTO registrarEventoAuditoria(EventoAuditoriaBffRequestDTO dto, String token);

    /** Lista todos los eventos de la bitacora via ms-auditoria. */
    List<EventoAuditoriaBffResponseDTO> listarAuditoria(String token);

    /** Lista los eventos de la bitacora de un servicio en particular via ms-auditoria. */
    List<EventoAuditoriaBffResponseDTO> listarAuditoriaPorServicio(String nombre, String token);
}
