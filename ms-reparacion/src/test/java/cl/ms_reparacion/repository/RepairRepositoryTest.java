package cl.ms_reparacion.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import cl.ms_reparacion.model.Repair;

@SpringBootTest
// Perfil test usa H2 en memoria para aislar la DB real de desarrollo.
@ActiveProfiles("test")
class RepairRepositoryTest {

    // En test de integracion, Spring inyecta el bean real del repository.
    @Autowired
    private RepairRepository repairRepository;

    @Test
    void findByCustomerRut_shouldReturnOnlyMatchingRepairs() {
        Repair r1 = new Repair();
        r1.setCustomerRut("12345678-9");
        r1.setModel("iPhone 13");
        r1.setDescription("Broken screen");
        r1.setStatus("RECEIVED");
        repairRepository.save(r1);

        Repair r2 = new Repair();
        r2.setCustomerRut("98765432-1");
        r2.setModel("Samsung S22");
        r2.setDescription("Not charging");
        r2.setStatus("RECEIVED");
        repairRepository.save(r2);

        var result = repairRepository.findByCustomerRut("12345678-9");

        assertEquals(1, result.size());
        assertTrue(result.get(0).getModel().equals("iPhone 13"));
    }
}
