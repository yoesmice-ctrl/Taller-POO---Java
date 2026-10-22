package relaciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AplicacionArenaRPG {

    static Scanner scanner = new Scanner(System.in);
    static List<Personaje> personajes = new ArrayList<>();
    static List<Gremio> gremios = new ArrayList<>();
    static List<Escuadron> escuadrones = new ArrayList<>();

    public static void main(String[] args) {
        int opcion = 0;

        do {
            System.out.println("\n=== MENÚ ARENA DE HÉROES RPG ===");
            System.out.println("1. Crear Personaje");
            System.out.println("2. Crear Gremio");
            System.out.println("3. Reclutar Personaje a Escuadrón");
            System.out.println("4. Equipar Arma a Personaje");
            System.out.println("5. Mostrar Estado Detallado (Personaje y Corazón RPG)");
            System.out.println("6. Salir");
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    crearPersonaje();
                    break;
                case 2:
                    crearGremio();
                    break;
                case 3:
                    reclutarEnEscuadron();
                    break;
                case 4:
                    equiparArma();
                    break;
                case 5:
                    mostrarEstado();
                    break;
                case 6:
                    System.out.println("Cerrando la Arena de Héroes...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 6);
    }

    // ---------- Opción 1 ----------
    static void crearPersonaje() {
        System.out.print("Nombre del personaje: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }
        int nivel = leerEntero("Nivel (1 o más): ");
        if (nivel < 1) {
            System.out.println("Nivel inválido.");
            return;
        }
        Personaje p = new Personaje(nombre, nivel);
        personajes.add(p);
        System.out.println("Personaje creado con su CorazonRPG: " + p.getCorazon());

        // Asociación bidireccional: opcionalmente se une a un gremio
        if (!gremios.isEmpty()) {
            System.out.print("¿Unirlo a un gremio? (s/n): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("s")) {
                Gremio g = elegirGremio();
                if (g != null) {
                    p.unirseAGremio(g);
                    System.out.println(nombre + " ahora pertenece al gremio " + g.getNombre());
                }
            }
        }
    }

    // ---------- Opción 2 ----------
    static void crearGremio() {
        System.out.print("Nombre del gremio: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }
        gremios.add(new Gremio(nombre));
        System.out.println("Gremio " + nombre + " creado.");
    }

    // ---------- Opción 3 ----------
    static void reclutarEnEscuadron() {
        if (personajes.isEmpty()) {
            System.out.println("Primero debe crear al menos un personaje.");
            return;
        }
        Personaje p = elegirPersonaje();
        if (p == null) {
            return;
        }

        System.out.print("Nombre del escuadrón (se crea si no existe): ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }
        Escuadron escuadron = null;
        for (Escuadron e : escuadrones) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                escuadron = e;
            }
        }
        if (escuadron == null) {
            escuadron = new Escuadron(nombre);
            escuadrones.add(escuadron);
            System.out.println("Escuadrón " + nombre + " creado.");
        }
        escuadron.reclutarPersonaje(p);
    }

    // ---------- Opción 4 ----------
    static void equiparArma() {
        if (personajes.isEmpty()) {
            System.out.println("Primero debe crear al menos un personaje.");
            return;
        }
        Personaje p = elegirPersonaje();
        if (p == null) {
            return;
        }
        System.out.print("Nombre del arma: ");
        String nombreArma = scanner.nextLine().trim();
        int danio = leerEntero("Daño base del arma: ");
        if (nombreArma.isEmpty() || danio < 0) {
            System.out.println("Datos del arma inválidos.");
            return;
        }
        p.equiparArma(new Arma(nombreArma, danio));
    }

    // ---------- Opción 5 ----------
    static void mostrarEstado() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes registrados.");
            return;
        }

        StringBuilder reporte = new StringBuilder();
        reporte.append("\n----- REPORTE DE LA ARENA -----\n");
        for (Personaje p : personajes) {
            reporte.append(p).append("\n\n");
        }
        for (Gremio g : gremios) {
            reporte.append(g).append("\n");
        }
        for (Escuadron e : escuadrones) {
            reporte.append(e).append("\n");
        }
        System.out.println(reporte);

        System.out.printf("%-15s %-6s %-8s %-8s %-8s %-15s%n",
                "Nombre", "Nivel", "Salud", "Maná", "Ataque", "Gremio");
        for (Personaje p : personajes) {
            System.out.printf("%-15s %-6d %-8d %-8d %-8d %-15s%n",
                    p.getNombre(), p.getNivel(),
                    p.getCorazon().getSaludBase(), p.getCorazon().getManaBase(),
                    p.calcularAtaqueTotal(),
                    p.getGremio() != null ? p.getGremio().getNombre() : "-");
        }

        for (Escuadron e : escuadrones) {
            System.out.println();
            e.imprimirReporte();
        }
    }

    // ---------- Auxiliares ----------
    static Personaje elegirPersonaje() {
        System.out.println("Personajes disponibles:");
        for (int i = 0; i < personajes.size(); i++) {
            System.out.println((i + 1) + ". " + personajes.get(i).getNombre());
        }
        int num = leerEntero("Seleccione el número: ");
        if (num < 1 || num > personajes.size()) {
            System.out.println("Selección inválida.");
            return null;
        }
        return personajes.get(num - 1);
    }

    static Gremio elegirGremio() {
        System.out.println("Gremios disponibles:");
        for (int i = 0; i < gremios.size(); i++) {
            System.out.println((i + 1) + ". " + gremios.get(i).getNombre());
        }
        int num = leerEntero("Seleccione el número: ");
        if (num < 1 || num > gremios.size()) {
            System.out.println("Selección inválida.");
            return null;
        }
        return gremios.get(num - 1);
    }

    // Lee una línea completa y la convierte a int, así no se rompe el Scanner con letras
    static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        String linea = scanner.nextLine().trim();
        try {
            return Integer.parseInt(linea);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}