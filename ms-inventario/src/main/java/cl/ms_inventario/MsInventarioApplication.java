package cl.ms_inventario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de ms-inventario.
 * Microservicio con base de datos propia (Database per Service) que
 * administra el stock de repuestos usados en las reparaciones.
 */
@SpringBootApplication
public class MsInventarioApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsInventarioApplication.class, args);
    }
}
