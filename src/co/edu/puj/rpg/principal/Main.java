package co.edu.puj.rpg.principal;

import co.edu.puj.rpg.modelo.Personaje;
import co.edu.puj.rpg.modelo.enemigo.Enemigo;

import java.util.Scanner;

public class Main {

    private static int pociones = 2;
    private static int pocionesGrandes = 0;
    private static final double PRECIO_POCION_GRANDE = 40.0;

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.println("=== ARENA DE HEROES ===");
        System.out.print("Escribe el nombre de tu heroe: ");
        String nombre = entrada.nextLine();
        Personaje heroe = new Personaje(nombre, 100, 12, 50.0);

        boolean seguir = true;

        while (seguir && heroe.isEstaVivo()) {
            Enemigo enemigo = crearEnemigo();
            StringBuilder historial = new StringBuilder();

            System.out.println("\nUn " + enemigo.getNombre() + " aparece en la arena!");
            historial.append("Comienza el combate contra ").append(enemigo.getNombre()).append("\n");

            combate:
            do {
                mostrarMenu();


                if (!entrada.hasNextInt()) {
                    entrada.next();
                    System.out.println("Opcion invalida, escribe un numero del menu.");
                    continue;
                }
                int opcion = entrada.nextInt();
                if (opcion < 1 || opcion > 5) {
                    System.out.println("Opcion invalida, elige entre 1 y 5.");
                    continue;
                }

                switch (opcion) {
                    case 1: {
                        int vidaAntes = enemigo.getPuntosVida();
                        heroe.atacar(enemigo);
                        int dano = vidaAntes - enemigo.getPuntosVida();

                        if (heroe.isUltimoGolpeCritico()) {
                            historial.append("GOLPE CRITICO! ");
                        }
                        historial.append(heroe.getNombre()).append(" ataca a ")
                                .append(enemigo.getNombre()).append(" y le quita ")
                                .append(dano).append(" de vida\n");

                        if (!enemigo.isEstaVivo()) {
                            historial.append(enemigo.getNombre()).append(" ha sido derrotado\n");
                        }
                        break;
                    }
                    case 2: {
                        int vidaAntes = heroe.getPuntosVida();
                        String tipo;
                        if (pocionesGrandes > 0) {
                            heroe.usarPocion(60);
                            pocionesGrandes--;
                            tipo = "Pocion Grande";
                        } else if (pociones > 0) {
                            heroe.usarPocion(25);
                            pociones--;
                            tipo = "Pocion";
                        } else {
                            System.out.println("No te quedan pociones.");
                            continue;
                        }
                        historial.append(heroe.getNombre()).append(" usa una ").append(tipo)
                                .append(" y recupera ").append(heroe.getPuntosVida() - vidaAntes)
                                .append(" de vida\n");
                        break;
                    }
                    case 3:
                        mostrarEstado(heroe, enemigo);
                        break;
                    case 4:
                        historial.append(heroe.getNombre()).append(" huye del combate\n");
                        System.out.println("Huiste de la batalla...");
                        break combate;
                    case 5:
                        tienda(heroe, entrada, historial);
                        break;
                    default:
                        System.out.println("Opcion invalida.");
                }


                if (opcion == 1 || opcion == 2) {
                    turnoEnemigo(enemigo, heroe, historial);
                    System.out.println("\n--- Historial de batalla ---");
                    System.out.println(historial);
                }

            } while (heroe.isEstaVivo() && enemigo.isEstaVivo());


            if (!enemigo.isEstaVivo()) {
                System.out.printf("Derrotaste a %s! Ganas %.2f de oro y %d de experiencia.%n",
                        enemigo.getNombre(), enemigo.getOro(), enemigo.getExperienciaOtorgada());
                heroe.setOro(heroe.getOro() + enemigo.getOro());
                boolean subio = heroe.ganarExperiencia(enemigo.getExperienciaOtorgada());
                if (subio) {
                    System.out.printf("SUBISTE DE NIVEL! Ahora eres nivel %d%n", heroe.getNivel());
                }
            } else if (!heroe.isEstaVivo()) {
                System.out.println("Has sido derrotado... GAME OVER");
            }

            if (heroe.isEstaVivo()) {
                System.out.print("Quieres otro combate? (s/n): ");
                String respuesta = entrada.next();
                seguir = respuesta.equalsIgnoreCase("s");
            }
        }

        System.out.println("\nGracias por jugar, " + heroe.getNombre() + ".");
        mostrarEstadoFinal(heroe);
        entrada.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n===== MENU DE COMBATE =====");
        System.out.println("1. Atacar");
        System.out.println("2. Usar Pocion");
        System.out.println("3. Ver Estado");
        System.out.println("4. Huir");
        System.out.println("5. Tienda");
        System.out.print("Elige una opcion: ");
    }

    private static void mostrarEstado(Personaje heroe, Enemigo enemigo) {
        System.out.println("\n----- ESTADO -----");
        System.out.printf("%s | Nivel: %d | Vida: %d/%d | Ataque: %d | Oro: %.2f%n",
                heroe.getNombre(), heroe.getNivel(), heroe.getPuntosVida(),
                heroe.getVidaMaxima(), heroe.getPuntosAtaque(), heroe.getOro());
        System.out.printf("Experiencia: %d | Pociones: %d | Pociones Grandes: %d%n",
                heroe.getExperiencia(), pociones, pocionesGrandes);
        System.out.printf("Enemigo: %s | Vida: %d/%d | Ataque: %d%n",
                enemigo.getNombre(), enemigo.getPuntosVida(),
                enemigo.getVidaMaxima(), enemigo.getPuntosAtaque());
    }

    private static void mostrarEstadoFinal(Personaje heroe) {
        System.out.printf("Resumen final -> Nivel: %d | Oro: %.2f | Vida: %d/%d%n",
                heroe.getNivel(), heroe.getOro(), heroe.getPuntosVida(), heroe.getVidaMaxima());
    }

    private static void turnoEnemigo(Enemigo enemigo, Personaje heroe, StringBuilder historial) {
        if (!enemigo.isEstaVivo()) {
            return;
        }
        int vidaAntes = heroe.getPuntosVida();
        enemigo.atacar(heroe);
        int dano = vidaAntes - heroe.getPuntosVida();

        historial.append(enemigo.getNombre()).append(" ataca a ")
                .append(heroe.getNombre()).append(" y le quita ")
                .append(dano).append(" de vida\n");

        if (!heroe.isEstaVivo()) {
            historial.append(heroe.getNombre()).append(" ha caido en combate\n");
        }
    }


    private static void tienda(Personaje heroe, Scanner entrada, StringBuilder historial) {
        System.out.println("\n===== TIENDA =====");
        System.out.printf("Oro disponible: %.2f%n", heroe.getOro());
        System.out.printf("1. Pocion Grande de Salud (%.2f oro, +60 vida)%n", PRECIO_POCION_GRANDE);
        System.out.println("2. Salir");
        System.out.print("Elige una opcion: ");

        if (!entrada.hasNextInt()) {
            entrada.next();
            System.out.println("Opcion invalida.");
            return;
        }
        int op = entrada.nextInt();

        if (op == 1) {
            double saldoAntes = heroe.getOro();
            heroe.setOro(saldoAntes - PRECIO_POCION_GRANDE);


            if (heroe.getOro() != saldoAntes) {
                pocionesGrandes++;
                System.out.printf("Compraste una Pocion Grande. Te quedan %.2f de oro.%n", heroe.getOro());
                historial.append("Compra de una Pocion Grande\n");
            } else {
                System.out.println("No se pudo completar la compra.");
            }
        } else if (op == 2) {
            System.out.println("Sales de la tienda.");
        } else {
            System.out.println("Opcion invalida.");
        }
    }

    private static Enemigo crearEnemigo() {
        int azar = (int) (Math.random() * 3);
        switch (azar) {
            case 0:
                return new Enemigo("Goblin", 40, 6, 20.0);
            case 1:
                return new Enemigo("Esqueleto", 55, 8, 30.0);
            default:
                return new Enemigo("Orco", 70, 10, 40.0);
        }
    }
}