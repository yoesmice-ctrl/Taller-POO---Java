package co.edu.puj.rpg.modelo.enemigo;

import co.edu.puj.rpg.modelo.Personaje;

public class Enemigo {

    private String nombre;
    private int puntosVida;
    private int vidaMaxima;
    private int puntosAtaque;
    private int nivel;
    private double oro;
    private boolean estaVivo;
    private int experienciaOtorgada;

    public Enemigo(String nombre, int vidaMaxima, int puntosAtaque, double oro) {
        this.nombre = nombre;
        this.vidaMaxima = vidaMaxima;
        this.puntosAtaque = puntosAtaque;
        this.nivel = 1;
        this.oro = oro;
        this.estaVivo = true;
        this.experienciaOtorgada = vidaMaxima / 2;
        setPuntosVida(vidaMaxima);
    }

    public void atacar(Personaje heroe) {
        if (!estaVivo) {
            return;
        }
        heroe.recibirDano(puntosAtaque);
    }

    public void recibirDano(int cantidadDano) {
        if (cantidadDano < 0) {
            return;
        }
        setPuntosVida(puntosVida - cantidadDano);
    }

    public void setPuntosVida(int vida) {
        if (vida <= 0) {
            this.puntosVida = 0;
            this.estaVivo = false;
        } else if (vida > vidaMaxima) {
            this.puntosVida = vidaMaxima;
            this.estaVivo = true;
        } else {
            this.puntosVida = vida;
            this.estaVivo = true;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntosVida() {
        return puntosVida;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getPuntosAtaque() {
        return puntosAtaque;
    }

    public int getNivel() {
        return nivel;
    }

    public double getOro() {
        return oro;
    }

    public boolean isEstaVivo() {
        return estaVivo;
    }

    public int getExperienciaOtorgada() {
        return experienciaOtorgada;
    }
}