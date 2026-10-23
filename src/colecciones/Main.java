package colecciones;

import colecciones.engine.Bestiario;
import colecciones.engine.Tienda;
import colecciones.exception.CapacidadExcedidaException;
import colecciones.exception.SinManaException;
import colecciones.model.PerfilJugador;
import colecciones.model.Personaje;
import colecciones.model.enums.TipoPersonaje;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static int lecturasNumericas = 0;

    public static void main(String[] args) {
        Personaje personaje = null;
        PerfilJugador perfil = new PerfilJugador("Jugador 1");
        Bestiario bestiario = new Bestiario();
        Tienda tienda = new Tienda();

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elige una opción: ");

            switch (opcion) {
                case 1:
                    personaje = crearPersonaje();
                    break;
                case 2:
                    if (hayPersonaje(personaje)) {
                        asignarHabilidad(personaje);
                    }
                    break;
                case 3:
                    registrarVictoria(bestiario, perfil);
                    break;
                case 4:
                    if (hayPersonaje(personaje)) {
                        ataqueEspecial(personaje);
                    }
                    break;
                case 5:
                    consultar(bestiario, perfil, tienda);
                    break;
                case 0:
                    System.out.println("Hasta la próxima, aventurero.");
                    break;
                default:
                    System.err.println("Esa opción no existe, intenta de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\n===== ARENA DE HÉROES =====");
        System.out.println("1. Crear personaje");
        System.out.println("2. Asignar habilidad única");
        System.out.println("3. Registrar victoria en bestiario");
        System.out.println("4. Ejecutar ataque especial");
        System.out.println("5. Consultar bestiario y logros");
        System.out.println("0. Salir");
    }

    // lectura numérica protegida: no se cae si escriben letras
    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                scanner.nextLine(); // limpia el búfer
                System.err.println("Debes escribir un número entero, intenta otra vez.");
            } finally {
                lecturasNumericas++;
            }
        }
    }

    private static boolean hayPersonaje(Personaje personaje) {
        if (personaje == null) {
            System.err.println("Primero debes crear un personaje (opción 1).");
            return false;
        }
        return true;
    }

    private static Personaje crearPersonaje() {
        System.out.print("Nombre del personaje: ");
        String nombre = scanner.nextLine().trim();

        TipoPersonaje[] tipos = TipoPersonaje.values();
        for (int i = 0; i < tipos.length; i++) {
            System.out.println((i + 1) + ". " + tipos[i]);
        }

        int eleccion;
        do {
            eleccion = leerEntero("Elige el tipo: ");
        } while (eleccion < 1 || eleccion > tipos.length);

        Personaje p = new Personaje(nombre, tipos[eleccion - 1]);
        System.out.println("Personaje creado -> " + p);
        return p;
    }

    private static void asignarHabilidad(Personaje personaje) {
        System.out.print("Nombre de la habilidad: ");
        String habilidad = scanner.nextLine().trim();
        try {
            if (personaje.aprenderHabilidad(habilidad)) {
                System.out.println("Habilidad aprendida: " + habilidad);
            } else {
                System.out.println("Esa habilidad ya está aprendida.");
            }
        } catch (CapacidadExcedidaException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void registrarVictoria(Bestiario bestiario, PerfilJugador perfil) {
        System.out.print("Nombre del enemigo derrotado: ");
        String enemigo = scanner.nextLine().trim();

        boolean primera = bestiario.registrarVictoria(enemigo);
        System.out.println("Victorias contra " + enemigo + ": " + bestiario.getVictorias(enemigo));

        if (primera) {
            String logro = "Primera victoria contra " + enemigo;
            perfil.registrarLogro(logro);
            System.out.println("¡Logro desbloqueado! " + logro);
        }
    }

    private static void ataqueEspecial(Personaje personaje) {
        int costo = leerEntero("Costo de maná del ataque: ");
        try {
            personaje.lanzarHechizo(costo);
            System.out.println("¡Ataque lanzado! Maná restante: " + personaje.getMana());
        } catch (SinManaException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void consultar(Bestiario bestiario, PerfilJugador perfil, Tienda tienda) {
        System.out.println("--- Bestiario ---");
        bestiario.mostrarReporte();
        System.out.println("--- Logros (orden alfabético) ---");
        if (perfil.listarLogrosOrdenados().isEmpty()) {
            System.out.println("  (sin logros todavía)");
        }
        for (String logro : perfil.listarLogrosOrdenados()) {
            System.out.println("  * " + logro);
        }
        System.out.println("--- Catálogo de la tienda ---");
        tienda.mostrarCatalogo();
    }
}