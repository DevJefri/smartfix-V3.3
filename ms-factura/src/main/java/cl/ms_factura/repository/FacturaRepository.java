package cl.ms_factura.repository;

import cl.ms_factura.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
    Optional<Factura> findByFolio(String folio);
    List<Factura> findByRutCliente(String rutCliente);
}
