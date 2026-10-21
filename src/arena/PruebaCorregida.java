package arena;

import java.util.ArrayList;
import java.util.List;

public class PruebaCorregida {

    public static void main(String[] args) {
        errorIndiceLista();
        errorIndiceMatriz();
        errorCasteo();
        errorTiposCrudos();
    }

    // CORRECCIÓN 1: validar el índice antes de acceder a la lista
    static void errorIndiceLista() {
        List<Personaje> escuadron = new ArrayList<>();
        escuadron.add(new Guerrero("Thorin", 120, 15, 0, 0, 10));
        escuadron.add(new Mago("Gandalf", 80, 25, 1, 1, 50));

        int indice = 5;
        if (indice >= 0 && indice < escuadron.size()) {
            System.out.println(escuadron.get(indice).getNombre());
        } else {
            System.out.println("Índice inválido: el escuadrón tiene " + escuadron.size() + " unidades");
        }
    }

    // CORRECCIÓN 2: validar los límites de la matriz
    static void errorIndiceMatriz() {
        char[][] tablero = new char[5][5];
        int x = 5;
        if (x >= 0 && x < tablero.length) {
            tablero[x][0] = 'G';
        } else {
            System.out.println("La fila " + x + " está fuera del tablero");
        }
    }

    // CORRECCIÓN 3: comprobar el tipo real con instanceof antes de castear
    static void errorCasteo() {
        Personaje p = new Guerrero("Thorin", 120, 15, 0, 0, 10);
        if (p instanceof Mago) {
            Mago m = (Mago) p;
            m.recuperarMana(10);
        } else {
            System.out.println(p.getNombre() + " no es un mago");
        }
    }

    // CORRECCIÓN 4: usar genéricos, sin casteo
    static void errorTiposCrudos() {
        Inventario<Pocion> inv = new Inventario<>();
        inv.agregar(new Pocion("Pocion pequeña", 25));
        Pocion p = inv.obtener(0);
        System.out.println(p.getNombre());
    }
}