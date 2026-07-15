package cl.ms_factura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de ms-factura.
 * Microservicio con base de datos propia (Database per Service) que
 * emite y consulta las facturas generadas a partir de reparaciones
 * finalizadas.
 */
@SpringBootApplication
public class MsFacturaApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsFacturaApplication.class, args);
    }
}
