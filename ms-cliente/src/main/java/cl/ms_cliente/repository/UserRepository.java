package cl.ms_cliente.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.ms_cliente.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
