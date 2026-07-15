package cl.ms_reparacion.service;

import cl.ms_reparacion.dto.RepairRequestDTO;
import cl.ms_reparacion.dto.RepairResponseDTO;
import cl.ms_reparacion.exception.ResourceNotFoundException;
import cl.ms_reparacion.model.Repair;
import cl.ms_reparacion.repository.RepairRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepairServiceImpl implements RepairService {

    private final RepairRepository repairRepository;
    private final CustomerVerificationService customerVerificationService;

    public RepairServiceImpl(RepairRepository repairRepository,
        CustomerVerificationService customerVerificationService) {
        this.repairRepository = repairRepository;
        this.customerVerificationService = customerVerificationService;
    }

    @Override
    public RepairResponseDTO createRepair(RepairRequestDTO dto) {
        customerVerificationService.verifyCustomerExists(dto.customerRut());

        Repair r = new Repair();
        r.setCustomerRut(dto.customerRut());
        r.setModel(dto.model());
        r.setDescription(dto.description());
        r.setStatus("RECEIVED");
        return mapToDto(repairRepository.save(r));
    }

    @Override
    public List<RepairResponseDTO> listByRut(String customerRut) {
        return repairRepository.findByCustomerRut(customerRut)
                .stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<RepairResponseDTO> listAll() {
        return repairRepository.findAll()
                .stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public RepairResponseDTO updateStatus(Long id, String newStatus) {
        Repair r = repairRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Repair not found with id: " + id));
        r.setStatus(newStatus);
        return mapToDto(repairRepository.save(r));
    }

    private RepairResponseDTO mapToDto(Repair r) {
        return new RepairResponseDTO(
            r.getId(), r.getCustomerRut(), r.getModel(), r.getDescription(), r.getStatus());
    }
}
