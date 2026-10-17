package co.edu.puj.rpg.modelo;

public class PersonajeDefectuoso {
    private int puntosVida;
    private boolean estaVivo;

    public PersonajeDefectuoso(int puntosVida) {

        this.puntosVida = -50;
        this.estaVivo = true;
    }

    public int getPuntosVida() {
        return puntosVida;
    }

    public boolean isEstaVivo() {
        return estaVivo;
    }
}