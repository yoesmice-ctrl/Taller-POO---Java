package arena;

import java.util.List;

public class Tablero {

    private static final char VACIA = '.';
    private static final char COFRE = 'C';

    private final char[][] celdas;
    private final int cofreX;
    private final int cofreY;

    public Tablero(int tamano, int cofreX, int cofreY) {
        this.celdas = new char[tamano][tamano];
        this.cofreX = cofreX;
        this.cofreY = cofreY;
    }

    // Vuelve a dibujar la matriz según la posición actual de cada personaje vivo
    public void actualizar(List<Personaje> heroes, List<Personaje> enemigos) {
        for (int i = 0; i < celdas.length; i++) {
            for (int j = 0; j < celdas[i].length; j++) {
                celdas[i][j] = VACIA;
            }
        }
        celdas[cofreX][cofreY] = COFRE;

        for (Personaje e : enemigos) {
            if (e.estaVivo()) {
                celdas[e.getPosicionX()][e.getPosicionY()] = e.getSimbolo();
            }
        }
        for (Personaje h : heroes) {
            if (h.estaVivo()) {
                celdas[h.getPosicionX()][h.getPosicionY()] = h.getSimbolo();
            }
        }
    }

    public void mostrar() {
        mostrarTablero(celdas);
    }

    public void mostrarTablero(char[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("[" + matriz[i][j] + "]");
            }
            System.out.println();
        }
    }

    public boolean dentroDeLimites(int x, int y) {
        return x >= 0 && x < celdas.length && y >= 0 && y < celdas[0].length;
    }

    public boolean esCofre(int x, int y) {
        return x == cofreX && y == cofreY;
    }
}