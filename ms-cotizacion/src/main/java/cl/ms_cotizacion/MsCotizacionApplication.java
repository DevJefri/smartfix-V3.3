package cl.ms_cotizacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de ms-cotizacion.
 * Microservicio simple (sin base de datos) que calcula un presupuesto
 * estimado de reparacion a partir del tipo de dispositivo, el tipo de
 * falla y si el cliente solicita atencion urgente.
 */
@SpringBootApplication
public class MsCotizacionApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsCotizacionApplication.class, args);
    }
}
