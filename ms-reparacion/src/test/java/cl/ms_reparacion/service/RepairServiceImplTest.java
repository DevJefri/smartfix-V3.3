package cl.ms_reparacion.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.ms_reparacion.dto.RepairRequestDTO;
import cl.ms_reparacion.dto.RepairResponseDTO;
import cl.ms_reparacion.exception.ResourceNotFoundException;
import cl.ms_reparacion.model.Repair;
import cl.ms_reparacion.repository.RepairRepository;

// Integra Mockito con JUnit 5: los campos @Mock se inicializan solos antes de cada test.
@ExtendWith(MockitoExtension.class)
class RepairServiceImplTest {

    // Mock: reemplaza el repository real por un doble de prueba controlable.
    @Mock
    private RepairRepository repairRepository;

    // Mock: evita que el service llame de verdad a ms-cliente por HTTP durante el test.
    @Mock
    private CustomerVerificationService customerVerificationService;

    @Test
    void createRepair_shouldSaveAndReturnDto() {
        RepairServiceImpl repairService =
            new RepairServiceImpl(repairRepository, customerVerificationService);

        RepairRequestDTO dto = new RepairRequestDTO("12345678-9", "iPhone 13", "Broken screen");

        // El cliente "existe" (no lanza excepcion al verificar).
        doNothing().when(customerVerificationService).verifyCustomerExists("12345678-9");
        // Simulamos el comportamiento de JPA al asignar el id durante el save.
        // Repair no tiene setId() (el id lo asigna JPA via @GeneratedValue),
        // por eso usamos reflexion solo para simular ese efecto en el test.
        when(repairRepository.save(any(Repair.class))).thenAnswer(invocation -> {
            Repair toSave = invocation.getArgument(0);
            setIdViaReflection(toSave, 1L);
            return toSave;
        });

        RepairResponseDTO result = repairService.createRepair(dto);

        assertEquals(1L, result.id());
        assertEquals("RECEIVED", result.status());
        assertEquals("iPhone 13", result.model());
        verify(customerVerificationService).verifyCustomerExists("12345678-9");
    }

    @Test
    void updateStatus_shouldUpdateAndReturnDto() {
        RepairServiceImpl repairService =
            new RepairServiceImpl(repairRepository, customerVerificationService);

        Repair existing = new Repair();
        setIdViaReflection(existing, 5L);
        existing.setCustomerRut("12345678-9");
        existing.setModel("iPhone 13");
        existing.setDescription("Broken screen");
        existing.setStatus("RECEIVED");

        when(repairRepository.findById(5L)).thenReturn(Optional.of(existing));
        when(repairRepository.save(any(Repair.class))).thenAnswer(invocation -> invocation.getArgument(0));

        RepairResponseDTO result = repairService.updateStatus(5L, "READY");

        assertEquals("READY", result.status());
        assertEquals(5L, result.id());
    }

    @Test
    void updateStatus_shouldThrowWhenNotFound() {
        RepairServiceImpl repairService =
            new RepairServiceImpl(repairRepository, customerVerificationService);

        when(repairRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(
            ResourceNotFoundException.class,
            () -> repairService.updateStatus(999L, "READY")
        );
    }

    // Repair.id solo tiene getter (lo asigna JPA en runtime real).
    // Esta utilidad permite simular esa asignacion dentro del test sin
    // modificar la entidad de produccion.
    private static void setIdViaReflection(Repair repair, Long id) {
        try {
            var field = Repair.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(repair, id);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Could not assign id in test", e);
        }
    }
}
