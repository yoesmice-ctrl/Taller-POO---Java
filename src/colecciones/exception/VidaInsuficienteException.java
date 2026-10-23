package colecciones.exception;

/**
 * No verificada: no obliga a declarar throws ni a capturarla.
 */
public class VidaInsuficienteException extends RuntimeException {

    public VidaInsuficienteException(int vidaActual, int costoVida) {
        super("Vida insuficiente: tienes " + vidaActual + " y la acción cuesta " + costoVida);
    }

    public VidaInsuficienteException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}