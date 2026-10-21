package arena;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final Random azar = new Random();

    public static void main(String[] args) {
        // 1. Inicialización
        List<Personaje> heroes = new ArrayList<>();
        heroes.add(new Guerrero("Thorin", 120, 15, 0, 0, 10));
        heroes.add(new Mago("Gandalf", 80, 25, 1, 1, 50));
        Personaje principal = heroes.get(0);

        List<Personaje> enemigos = new ArrayList<>();
        enemigos.add(new Enemigo("Orco", 60, 12, 0, 4));
        enemigos.add(new Enemigo("Trasgo", 50, 10, 4, 0));
        enemigos.add(new Enemigo("Troll", 90, 14, 4, 4));

        Inventario<Pocion> mochila = new Inventario<>();
        mochila.agregar(new Pocion("Pocion pequeña", 25));
        mochila.agregar(new Pocion("Pocion pequeña", 25));
        mochila.agregar(new Pocion("Pocion grande", 50));

        Tablero tablero = new Tablero(5, 2, 2);

        boolean victoria = false;
        boolean derrota = false;
        int turno = 0;

        // 2. Ciclo principal
        do {
            turno++;
            System.out.println("\n================ TURNO " + turno + " ================");
            tablero.actualizar(heroes, enemigos);
            tablero.mostrar();
            mostrarEstado(heroes, enemigos);

            // Turno del jugador
            for (Personaje h : heroes) {
                if (!h.estaVivo()) {
                    continue;
                }
                if (!hayVivos(enemigos)) {
                    break;
                }
                turnoHeroe(h, enemigos, heroes, mochila, tablero);

                if (principal.estaVivo() && tablero.esCofre(principal.getPosicionX(), principal.getPosicionY())) {
                    victoria = true;
                    break;
                }
            }

            // Turno de los enemigos
            if (!victoria && hayVivos(enemigos)) {
                System.out.println("\n--- Turno enemigo ---");
                for (Personaje e : enemigos) {
                    if (!e.estaVivo()) {
                        continue;
                    }
                    Personaje objetivo = elegirAlAzar(heroes);
                    if (objetivo == null) {
                        break;
                    }
                    e.realizarAtaqueEspecial(objetivo);
                }
            }

            // Limpieza de bajas
            heroes.removeIf(p -> !p.estaVivo());
            enemigos.removeIf(p -> !p.estaVivo());

            // Condiciones de fin
            int vidaTotal = 0;
            for (Personaje h : heroes) {
                vidaTotal += h.getVida();
            }
            if (heroes.isEmpty() || vidaTotal <= 0) {
                derrota = true;
            } else if (enemigos.isEmpty()) {
                victoria = true;
            }

        } while (!victoria && !derrota);

        tablero.actualizar(heroes, enemigos);
        System.out.println();
        tablero.mostrar();
        if (derrota) {
            System.out.println("\nDERROTA: el escuadrón cayó en la arena.");
        } else {
            System.out.println("\nVICTORIA: la arena es tuya.");
        }
    }

    private static void turnoHeroe(Personaje h, List<Personaje> enemigos, List<Personaje> heroes,
                                   Inventario<Pocion> mochila, Tablero tablero) {
        System.out.println("\n>> Turno de " + h.getNombre());
        System.out.println("1. Atacar");
        System.out.println("2. Usar inventario");
        System.out.println("3. Mover en tablero");
        int opcion = leerEntero("Elige una opción: ");

        switch (opcion) {
            case 1:
                atacar(h, enemigos);
                break;
            case 2:
                usarInventario(h, mochila);
                break;
            case 3:
                mover(h, tablero, heroes, enemigos);
                break;
            default:
                System.out.println("Opción inválida, pierdes el turno.");
        }
    }

    private static void atacar(Personaje h, List<Personaje> enemigos) {
        List<Personaje> vivos = new ArrayList<>();
        for (Personaje e : enemigos) {
            if (e.estaVivo()) {
                vivos.add(e);
            }
        }
        for (int i = 0; i < vivos.size(); i++) {
            System.out.println((i + 1) + ". " + vivos.get(i).getEstado());
        }
        int eleccion = leerEntero("¿A quién atacas? ");
        if (eleccion < 1 || eleccion > vivos.size()) {
            System.out.println("Ese enemigo no existe, pierdes el turno.");
            return;
        }
        h.realizarAtaqueEspecial(vivos.get(eleccion - 1));
    }

    private static void usarInventario(Personaje h, Inventario<Pocion> mochila) {
        if (mochila.estaVacio()) {
            System.out.println("El inventario está vacío.");
            return;
        }
        List<Pocion> pociones = mochila.getItems();
        for (int i = 0; i < pociones.size(); i++) {
            System.out.println((i + 1) + ". " + pociones.get(i).getNombre() + " (+" + pociones.get(i).getCuracion() + ")");
        }
        int eleccion = leerEntero("¿Cuál usas? ");
        if (eleccion < 1 || eleccion > mochila.cantidad()) {
            System.out.println("Esa poción no existe, pierdes el turno.");
            return;
        }
        // Gracias al genérico no hace falta casteo
        Pocion p = mochila.sacar(eleccion - 1);
        p.usar(h);
    }

    private static void mover(Personaje h, Tablero tablero, List<Personaje> heroes, List<Personaje> enemigos) {
        System.out.print("Dirección (W arriba, S abajo, A izquierda, D derecha): ");
        String dir = sc.nextLine().trim().toUpperCase();

        int nx = h.getPosicionX();
        int ny = h.getPosicionY();
        switch (dir) {
            case "W": nx--; break;
            case "S": nx++; break;
            case "A": ny--; break;
            case "D": ny++; break;
            default:
                System.out.println("Dirección inválida, pierdes el turno.");
                return;
        }

        if (!tablero.dentroDeLimites(nx, ny)) {
            System.out.println("Ahí termina el mapa, no puedes avanzar.");
            return;
        }
        if (celdaOcupada(nx, ny, heroes) || celdaOcupada(nx, ny, enemigos)) {
            System.out.println("Esa casilla ya está ocupada.");
            return;
        }
        h.setPosicion(nx, ny);
        tablero.actualizar(heroes, enemigos);
        System.out.println(h.getNombre() + " se mueve a (" + nx + ", " + ny + ")");
        tablero.mostrar();
    }

    private static boolean celdaOcupada(int x, int y, List<Personaje> lista) {
        for (Personaje p : lista) {
            if (p.estaVivo() && p.getPosicionX() == x && p.getPosicionY() == y) {
                return true;
            }
        }
        return false;
    }

    private static boolean hayVivos(List<Personaje> lista) {
        for (Personaje p : lista) {
            if (p.estaVivo()) {
                return true;
            }
        }
        return false;
    }

    private static Personaje elegirAlAzar(List<Personaje> lista) {
        List<Personaje> vivos = new ArrayList<>();
        for (Personaje p : lista) {
            if (p.estaVivo()) {
                vivos.add(p);
            }
        }
        if (vivos.isEmpty()) {
            return null;
        }
        return vivos.get(azar.nextInt(vivos.size()));
    }

    private static void mostrarEstado(List<Personaje> heroes, List<Personaje> enemigos) {
        System.out.println("\nHéroes:");
        for (Personaje h : heroes) {
            System.out.println("  " + h.getEstado());
        }
        System.out.println("Enemigos:");
        for (Personaje e : enemigos) {
            System.out.println("  " + e.getEstado());
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Escribe un número válido.");
            }
        }
    }
}