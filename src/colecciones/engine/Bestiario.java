package colecciones.engine;

import java.util.HashMap;
import java.util.Map;

public class Bestiario {

    private final Map<String, Integer> derrotados = new HashMap<>();

    /**
     * Suma una victoria contra el enemigo.
     * @return true si es la primera vez que lo derrota
     */
    public boolean registrarVictoria(String enemigo) {
        boolean primera = !derrotados.containsKey(enemigo);
        derrotados.put(enemigo, derrotados.getOrDefault(enemigo, 0) + 1);
        return primera;
    }

    public int getVictorias(String enemigo) {
        return derrotados.getOrDefault(enemigo, 0);
    }

    public boolean contiene(String enemigo) {
        return derrotados.containsKey(enemigo);
    }

    public void mostrarReporte() {
        if (derrotados.isEmpty()) {
            System.out.println("  (el bestiario está vacío)");
            return;
        }
        for (Map.Entry<String, Integer> entry : derrotados.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue() + " victoria(s)");
        }
    }
}