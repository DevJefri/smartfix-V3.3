package cl.ms_conversor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de ms-conversor.
 * Microservicio simple (sin base de datos) que convierte montos en pesos
 * chilenos (CLP) a otras monedas usando una tabla de tasas de cambio
 * configurable.
 */
@SpringBootApplication
public class MsConversorApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsConversorApplication.class, args);
    }
}
