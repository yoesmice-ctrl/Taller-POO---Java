package colecciones.model;

import colecciones.model.enums.RaridadItem;

public class Item {

    private final String nombre;
    private final RaridadItem raridad;
    private final int statBase;

    public Item(String nombre, RaridadItem raridad, int statBase) {
        this.nombre = nombre;
        this.raridad = raridad;
        this.statBase = statBase;
    }

    public String getNombre() {
        return nombre;
    }

    public RaridadItem getRaridad() {
        return raridad;
    }

    // el stat real depende de la rareza del ítem
    public double getStatFinal() {
        return statBase * raridad.getMultiplicadorStat();
    }

    @Override
    public String toString() {
        return nombre + " [" + raridad + "] stat=" + getStatFinal();
    }
}