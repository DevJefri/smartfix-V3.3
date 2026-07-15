package cl.bff_web.dto;

import java.math.BigDecimal;

public record FacturaBffResponseDTO(
    String folio,
    Long reparacionId,
    String rutCliente,
    BigDecimal monto,
    String estado,
    String fechaEmision
) {}
