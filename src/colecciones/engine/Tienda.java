package colecciones.engine;

import java.util.Map;
import java.util.TreeMap;

/**
 * Catálogo de precios; el TreeMap lo mantiene ordenado por nombre.
 */
public class Tienda {

    private final Map<String, Double> precios = new TreeMap<>();

    public Tienda() {
        precios.put("Poción de vida", 25.0);
        precios.put("Espada de hierro", 120.0);
        precios.put("Arco largo", 95.5);
        precios.put("Bastón arcano", 150.0);
    }

    public void mostrarCatalogo() {
        for (Map.Entry<String, Double> entry : precios.entrySet()) {
            System.out.printf("  %-18s $%.2f%n", entry.getKey(), entry.getValue());
        }
    }

    public boolean existe(String item) {
        return precios.containsKey(item);
    }
}