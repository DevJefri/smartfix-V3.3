package cl.ms_encuesta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de ms-encuesta.
 * Microservicio con base de datos propia (Database per Service) que
 * recibe la encuesta de satisfaccion posterior a una reparacion.
 */
@SpringBootApplication
public class MsEncuestaApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsEncuestaApplication.class, args);
    }
}
