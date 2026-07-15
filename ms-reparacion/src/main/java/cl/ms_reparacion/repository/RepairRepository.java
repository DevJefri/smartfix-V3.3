package cl.ms_reparacion.repository;

import cl.ms_reparacion.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RepairRepository extends JpaRepository<Repair, Long> {
    List<Repair> findByCustomerRut(String customerRut);
}
