package cl.ms_reparacion.service;

import cl.ms_reparacion.dto.RepairRequestDTO;
import cl.ms_reparacion.dto.RepairResponseDTO;
import java.util.List;

public interface RepairService {

    /** Crea una reparacion, verificando primero que el cliente exista en ms-cliente. */
    RepairResponseDTO createRepair(RepairRequestDTO dto);

    /** Lista las reparaciones asociadas a un cliente. */
    List<RepairResponseDTO> listByRut(String customerRut);

    /** Lista todas las reparaciones registradas. */
    List<RepairResponseDTO> listAll();

    /** Actualiza el estado de una reparacion (RECIBIDO, EN_PROCESO, LISTO, etc.). */
    RepairResponseDTO updateStatus(Long id, String newStatus);
}
