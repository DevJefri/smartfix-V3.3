package cl.bff_web.exception;

public class MicroserviceUnavailableException extends RuntimeException {
    public MicroserviceUnavailableException(String service) {
        super("The microservice '" + service + "' is currently unavailable.");
    }
}
