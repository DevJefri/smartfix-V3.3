package cl.ms_encuesta.repository;

import cl.ms_encuesta.model.Encuesta;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class EncuestaRepositoryTest {

    @Autowired
    private EncuestaRepository encuestaRepository;

    @Test
    void calcularPromedioPuntuacion_shouldAverageAllScores() {
        Encuesta e1 = new Encuesta();
        e1.setReparacionId(500L);
        e1.setRutCliente("11111111-1");
        e1.setPuntuacion(4);
        e1.setFecha(LocalDateTime.now());
        encuestaRepository.save(e1);

        Encuesta e2 = new Encuesta();
        e2.setReparacionId(501L);
        e2.setRutCliente("22222222-2");
        e2.setPuntuacion(2);
        e2.setFecha(LocalDateTime.now());
        encuestaRepository.save(e2);

        Double promedio = encuestaRepository.calcularPromedioPuntuacion();

        assertEquals(3.0, promedio);
    }
}
