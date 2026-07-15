package cl.ms_encuesta.repository;

import cl.ms_encuesta.model.Encuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EncuestaRepository extends JpaRepository<Encuesta, Long> {
    Optional<Encuesta> findByReparacionId(Long reparacionId);
    boolean existsByReparacionId(Long reparacionId);

    @Query("SELECT AVG(e.puntuacion) FROM Encuesta e")
    Double calcularPromedioPuntuacion();
}
