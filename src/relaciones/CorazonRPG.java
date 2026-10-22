package relaciones;

public class CorazonRPG {
    private int saludBase;
    private int manaBase;

    public CorazonRPG(int saludBase, int manaBase) {
        this.saludBase = saludBase;
        this.manaBase = manaBase;
    }

    public int getSaludBase() {
        return saludBase;
    }

    public int getManaBase() {
        return manaBase;
    }

    // Manipulación interna de vida y maná
    public void recibirDanio(int danio) {
        saludBase -= danio;
        if (saludBase < 0) {
            saludBase = 0;
        }
    }

    public void curar(int cantidad) {
        saludBase += cantidad;
    }

    public void gastarMana(int cantidad) {
        manaBase -= cantidad;
        if (manaBase < 0) {
            manaBase = 0;
        }
    }

    public void recuperarMana(int cantidad) {
        manaBase += cantidad;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Corazón[salud=").append(saludBase)
                .append(", maná=").append(manaBase).append("]");
        return sb.toString();
    }
}