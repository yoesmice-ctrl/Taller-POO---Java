package colecciones.exception;

public class SinManaException extends JuegoException {

    private final int manaActual;
    private final int costo;

    public SinManaException(int manaActual, int costo) {
        super("Maná insuficiente: tienes " + manaActual + " y el ataque cuesta " + costo);
        this.manaActual = manaActual;
        this.costo = costo;
    }

    public int getManaActual() {
        return manaActual;
    }

    public int getCosto() {
        return costo;
    }
}