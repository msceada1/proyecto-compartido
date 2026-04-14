package aventura.exceptions;

/**
 * Excepción personalizada para la aplicación Aventura.
 * Es la clase base para todas las excepciones específicas de Aventura.
 */
public class AventuraException extends Exception {
    public AventuraException(String message) {
        super(message);
    }
}
