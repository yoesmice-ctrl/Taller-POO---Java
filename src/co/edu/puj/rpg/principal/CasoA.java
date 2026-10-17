package co.edu.puj.rpg.principal;

import co.edu.puj.rpg.modelo.Personaje;

// Caso A: acceso directo a un atributo privado.
// Quita los // de las lineas marcadas, toma la captura del error
// y vuelve a comentarlas para que el proyecto compile.
public class CasoA {
    public static void main(String[] args) {
        Personaje heroe = new Personaje("Aragorn", 100, 15, 50.0);

        // heroe.puntosVida = 200;                                   // <- quitar // para ver el error
        // System.out.println("Vida: " + heroe.puntosVida);          // <- quitar // para ver el error

        // Forma correcta: usar el setter y el getter
        heroe.setPuntosVida(80);
        System.out.println("Vida: " + heroe.getPuntosVida());
    }
}