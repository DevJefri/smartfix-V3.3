package cl.ms_diagnostico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de ms-diagnostico.
 * Microservicio simple (sin base de datos) que, a partir de una lista de
 * sintomas reportados y el tipo de dispositivo, sugiere una prioridad de
 * atencion y un tiempo estimado de reparacion.
 */
@SpringBootApplication
public class MsDiagnosticoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsDiagnosticoApplication.class, args);
    }
}
