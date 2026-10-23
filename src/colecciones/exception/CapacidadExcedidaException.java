package colecciones.exception;

public class CapacidadExcedidaException extends JuegoException {

    public CapacidadExcedidaException(String contenedor, int capacidadMaxima) {
        super("El " + contenedor + " está lleno (máximo " + capacidadMaxima + ")");
    }

    public CapacidadExcedidaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}