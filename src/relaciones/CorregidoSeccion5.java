package relaciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Sección 5 - Versión CORREGIDA.
 * Cambie el valor de 'caso' (1 a 4) para ejecutar cada solución por separado.
 */
public class CorregidoSeccion5 {

    static int caso = 1;

    public static void main(String[] args) {
        switch (caso) {
            case 1:
                corregidoNullPointer();
                break;
            case 2:
                corregidoStackOverflow();
                break;
            case 3:
                corregidoDesincronizacion();
                break;
            case 4:
                corregidoConcurrentModification();
                break;
            default:
                System.out.println("Caso inválido");
        }
    }

    // ---------- CASO 1: se verifica null antes de usar el arma ----------
    static void corregidoNullPointer() {
        Personaje p = new Personaje("Arthas", 3);
        if (p.getArmaEquipada() != null) {
            int danio = p.getArmaEquipada().getDanioBase();
            System.out.println("Daño del arma: " + danio);
        } else {
            System.out.println("Arthas está desarmado. Ataque total: " + p.calcularAtaqueTotal());
        }
    }

    // ---------- CASO 2: Gremio.toString() solo imprime nombres ----------
    static void corregidoStackOverflow() {
        Gremio g = new Gremio("Alba");
        Personaje p = new Personaje("Jaina", 5);
        p.unirseAGremio(g);

        System.out.println(p);
        System.out.println(g);
    }

    // ---------- CASO 3: sincronización en ambos sentidos ----------
    static void corregidoDesincronizacion() {
        Gremio g = new Gremio("Vigilantes");
        Personaje p = new Personaje("Thrall", 4);

        p.unirseAGremio(g);
        System.out.println("Gremio del personaje: " + p.getGremio().getNombre());   // Vigilantes
        System.out.println("Miembros del gremio: " + g.getMiembros().size());       // 1 -> sincronizado
    }

    // ---------- CASO 4: se usa Iterator para eliminar mientras se recorre ----------
    static void corregidoConcurrentModification() {
        List<Personaje> lista = new ArrayList<>();
        lista.add(new Personaje("A", 1));
        lista.add(new Personaje("B", 2));
        lista.add(new Personaje("C", 3));

        Iterator<Personaje> it = lista.iterator();
        while (it.hasNext()) {
            Personaje p = it.next();
            if (p.getNivel() < 3) {
                it.remove();
            }
        }
        System.out.println("Quedan " + lista.size() + " personaje(s).");
    }
}