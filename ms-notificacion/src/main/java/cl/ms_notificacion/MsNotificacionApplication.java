package cl.ms_notificacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de ms-notificacion.
 * Microservicio simple (sin base de datos) que simula el envio de
 * notificaciones (EMAIL / SMS) a los clientes cuando cambia el estado
 * de una reparacion u ocurre otro evento relevante del sistema.
 */
@SpringBootApplication
public class MsNotificacionApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsNotificacionApplication.class, args);
    }
}
