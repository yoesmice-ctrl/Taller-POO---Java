package co.edu.puj.rpg.principal;

import co.edu.puj.rpg.modelo.Personaje;
import co.edu.puj.rpg.modelo.PersonajeDefectuoso;


public class CasoBC {
    public static void main(String[] args) {


        PersonajeDefectuoso malo = new PersonajeDefectuoso(100);
        System.out.println("Caso B (defectuoso) -> vida: " + malo.getPuntosVida()
                + " | vivo: " + malo.isEstaVivo());


        Personaje bueno = new Personaje("Aragorn", 100, 15, 50.0);
        System.out.println("Caso B (corregido)  -> vida: " + bueno.getPuntosVida()
                + " | vivo: " + bueno.isEstaVivo());


        ejecutarAccion(bueno);
    }

    public static void ejecutarAccion(Personaje heroe) {
        heroe.setPuntosVida(999999);
        System.out.println("Caso C (999999)     -> vida: " + heroe.getPuntosVida()
                + " de " + heroe.getVidaMaxima() + " | vivo: " + heroe.isEstaVivo());

        heroe.setPuntosVida(-100);
        System.out.println("Caso C (-100)       -> vida: " + heroe.getPuntosVida()
                + " | vivo: " + heroe.isEstaVivo());
    }
}