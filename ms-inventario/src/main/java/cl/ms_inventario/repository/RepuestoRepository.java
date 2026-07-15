package cl.ms_inventario.repository;

import cl.ms_inventario.model.Repuesto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepuestoRepository extends JpaRepository<Repuesto, Long> {
    Optional<Repuesto> findBySku(String sku);
    boolean existsBySku(String sku);
    void deleteBySku(String sku);
}
