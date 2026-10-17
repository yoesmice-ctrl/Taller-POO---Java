package co.edu.puj.rpg.modelo;


public class PruebaLimites {
    public static void main(String[] args) {
        int[] valores = {-10, 0, 100000};

        for (int v : valores) {
            Personaje p = new Personaje("Prueba", 100, 10, 50.0);
            System.out.println("=== Valor de prueba: " + v + " ===");

            p.setPuntosVida(v);
            System.out.println("setPuntosVida -> vida: " + p.getPuntosVida()
                    + " | vivo: " + p.isEstaVivo());

            p.setOro(v);
            System.out.println("setOro        -> oro: " + p.getOro());

            p.setNivel(v);
            System.out.println("setNivel      -> nivel: " + p.getNivel());
            System.out.println();
        }
    }
}