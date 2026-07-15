package cl.bff_web.dto;

import java.util.List;

public record DashboardResponseDTO(
    CustomerBffDTO customer,
    List<RepairBffDTO> repairs
) {}
