package relaciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Sección 5 del taller: errores típicos al trabajar con relaciones.
 * Cada caso está en su propio método para ponerle breakpoints y depurar.
 * Descomente de a uno en main() para provocar el error y luego ver la versión corregida.
 */
public class PruebaErrores {

    public static void main(String[] args) {
        // 1) NullPointerException
        // casoNullPointer();
        casoNullPointerCorregido();

        // 2) StackOverflowError
        //casoStackOverflowCorregido();
        casoStackOverflowCorregido();

        // 3) Desincronización bidireccional
        casoDesincronizacion();

        // 4) ConcurrentModificationException
        // casoConcurrentModification();
        casoConcurrentModificationCorregido();
    }

    // ----- 1. NullPointerException -----
    static void casoNullPointer() {
        Personaje p = new Personaje("Arthas", 3);
        // armaEquipada es null y se llama a un método sobre ella -> NullPointerException
        System.out.println(p.getArmaEquipada().getDanioBase());
    }

    static void casoNullPointerCorregido() {
        Personaje p = new Personaje("Arthas", 3);
        if (p.getArmaEquipada() != null) {
            System.out.println(p.getArmaEquipada().getDanioBase());
        } else {
            System.out.println("[NPE evitado] Arthas está desarmado, ataque total: " + p.calcularAtaqueTotal());
        }
    }

    // ----- 2. StackOverflowError -----
    // Clases con toString() cruzado, solo para provocar el error
    static class PersonajeMalo {
        String nombre;
        GremioMalo gremio;

        PersonajeMalo(String nombre) {
            this.nombre = nombre;
        }

        @Override
        public String toString() {
            return "PersonajeMalo " + nombre + " en " + gremio; // llama a GremioMalo.toString()
        }
    }

    static class GremioMalo {
        String nombre;
        List<PersonajeMalo> miembros = new ArrayList<>();

        @Override
        public String toString() {
            return "GremioMalo " + nombre + " con " + miembros; // llama a PersonajeMalo.toString()
        }
    }

    static void casoStackOverflow() {
        GremioMalo g = new GremioMalo();
        g.nombre = "Alba";
        PersonajeMalo p = new PersonajeMalo("Jaina");
        p.gremio = g;
        g.miembros.add(p);
        System.out.println(p); // Personaje -> Gremio -> Personaje -> ... StackOverflowError
    }

    static void casoStackOverflowCorregido() {
        Gremio g = new Gremio("Alba");
        Personaje p = new Personaje("Jaina", 5);
        p.unirseAGremio(g);
        // Gremio.toString() solo imprime nombres, la cadena se corta
        System.out.println(p);
        System.out.println(g);
    }

    // ----- 3. Desincronización bidireccional -----
    static void casoDesincronizacion() {
        Gremio g = new Gremio("Vigilantes");
        Personaje p = new Personaje("Thrall", 4);

        p.unirseAGremio(g);
        System.out.println("Gremio del personaje: " + p.getGremio().getNombre());
        System.out.println("Miembros del gremio: " + g.getMiembros().size()); // 1, ambos lados coinciden

        Gremio g2 = new Gremio("Horda");
        p.unirseAGremio(g2); // debe salir del primero y entrar al segundo
        System.out.println("Miembros de Vigilantes: " + g.getMiembros().size()); // 0
        System.out.println("Miembros de Horda: " + g2.getMiembros().size());     // 1
    }

    // ----- 4. ConcurrentModificationException -----
    static void casoConcurrentModification() {
        Escuadron e = new Escuadron("Alfa");
        e.reclutarPersonaje(new Personaje("A", 1));
        e.reclutarPersonaje(new Personaje("B", 2));
        e.reclutarPersonaje(new Personaje("C", 3));

        // getMiembros() devuelve copia, así que se arma una lista propia para provocar el error
        List<Personaje> lista = new ArrayList<>(e.getMiembros());
        for (Personaje p : lista) {
            if (p.getNivel() < 3) {
                lista.remove(p); // modifica la lista mientras se recorre
            }
        }
    }

    static void casoConcurrentModificationCorregido() {
        List<Personaje> lista = new ArrayList<>();
        lista.add(new Personaje("A", 1));
        lista.add(new Personaje("B", 2));
        lista.add(new Personaje("C", 3));

        Iterator<Personaje> it = lista.iterator();
        while (it.hasNext()) {
            Personaje p = it.next();
            if (p.getNivel() < 3) {
                it.remove(); // forma segura de eliminar mientras se recorre
            }
        }
        System.out.println("[CME evitado] Quedan " + lista.size() + " personaje(s) en la lista.");
    }
}