package cl.ms_cliente.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.ms_cliente.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByRut(String rut);
    boolean existsByRut(String rut);
}
