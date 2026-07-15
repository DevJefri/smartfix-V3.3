package cl.ms_diagnostico.exception;

/** Se lanza cuando el tipo de dispositivo no tiene un factor de complejidad definido. */
public class DispositivoNoSoportadoException extends RuntimeException {
    public DispositivoNoSoportadoException(String tipoDispositivo) {
        super("Tipo de dispositivo no soportado para diagnostico: '" + tipoDispositivo + "'");
    }
}
