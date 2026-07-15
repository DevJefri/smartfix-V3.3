package cl.ms_auditoria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de ms-auditoria.
 * Microservicio con base de datos propia (Database per Service) que
 * actua como bitacora central: registra eventos relevantes ocurridos en
 * cualquiera de los demas microservicios de Smartfix.
 */
@SpringBootApplication
public class MsAuditoriaApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsAuditoriaApplication.class, args);
    }
}
