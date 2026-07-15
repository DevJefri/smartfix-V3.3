package cl.ms_factura.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FacturaResponseDTO(
    String folio,
    Long reparacionId,
    String rutCliente,
    BigDecimal monto,
    String estado,
    LocalDateTime fechaEmision
) {}
