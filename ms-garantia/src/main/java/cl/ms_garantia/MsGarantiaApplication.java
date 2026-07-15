package cl.ms_garantia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de ms-garantia.
 * Microservicio simple (sin base de datos) que calcula la fecha de
 * vencimiento de la garantia de una reparacion segun el tipo de trabajo
 * realizado y la fecha de entrega del equipo.
 */
@SpringBootApplication
public class MsGarantiaApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsGarantiaApplication.class, args);
    }
}
