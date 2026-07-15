package cl.ms_auditoria.exception;

/** Se lanza cuando se consulta la bitacora con un nombre de servicio vacio o invalido. */
public class ServicioOrigenInvalidoException extends RuntimeException {
    public ServicioOrigenInvalidoException(String servicioOrigen) {
        super("Nombre de servicio de origen invalido: '" + servicioOrigen + "'");
    }
}
