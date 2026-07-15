package cl.ms_notificacion.exception;

/** Se lanza cuando el canal solicitado no es EMAIL ni SMS. */
public class CanalNoSoportadoException extends RuntimeException {
    public CanalNoSoportadoException(String canal) {
        super("Canal no soportado: '" + canal + "'. Los canales validos son EMAIL y SMS.");
    }
}
