package colecciones.exception;

/**
 * Base de todas las excepciones verificadas del dominio del juego.
 */
public abstract class JuegoException extends Exception {

    public JuegoException(String mensaje) {
        super(mensaje);
    }

    public JuegoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}