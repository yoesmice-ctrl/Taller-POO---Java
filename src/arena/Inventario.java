package arena;

import java.util.ArrayList;
import java.util.List;

/**
 * Contenedor genérico: solo guarda objetos del tipo T con el que se declare.
 * Ejemplo: Inventario<Pocion> mochila = new Inventario<>();
 */
public class Inventario<T> {

    private List<T> items = new ArrayList<>();

    public void agregar(T item) {
        items.add(item);
    }

    public T obtener(int index) {
        return items.get(index);
    }

    // Saca el elemento de la lista y lo devuelve (sirve para consumir pociones)
    public T sacar(int index) {
        return items.remove(index);
    }

    public int cantidad() {
        return items.size();
    }

    public boolean estaVacio() {
        return items.isEmpty();
    }

    public List<T> getItems() {
        return items;
    }
}