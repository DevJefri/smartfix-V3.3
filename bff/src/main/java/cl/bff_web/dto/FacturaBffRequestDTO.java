package cl.bff_web.dto;

import java.math.BigDecimal;

public record FacturaBffRequestDTO(
    Long reparacionId,
    String rutCliente,
    BigDecimal monto
) {}
