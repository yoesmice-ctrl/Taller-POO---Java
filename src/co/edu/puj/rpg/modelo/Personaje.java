package co.edu.puj.rpg.modelo;

import co.edu.puj.rpg.modelo.enemigo.Enemigo;

public class Personaje {


    private String nombre;
    private int puntosVida;
    private int vidaMaxima;
    private int puntosAtaque;
    private int nivel;
    private double oro;
    private boolean estaVivo;


    private int experiencia;
    private boolean ultimoGolpeCritico;


    public Personaje(String nombre, int vidaMaxima, int puntosAtaque, double oro) {
        this.nombre = nombre;
        this.vidaMaxima = vidaMaxima;
        this.puntosAtaque = puntosAtaque;
        this.nivel = 1;
        this.experiencia = 0;
        this.oro = 0.0;
        this.estaVivo = true;


        if (this.vidaMaxima < 1) {
            this.vidaMaxima = 1;
        }


        setPuntosVida(this.vidaMaxima);
        setOro(oro);
    }


    public Personaje(String nombre) {
        this(nombre, 100, 12, 50.0);
    }


    public void atacar(Enemigo enemigo) {
        ultimoGolpeCritico = false;

        if (!estaVivo || !enemigo.isEstaVivo()) {
            return;
        }

        int dano = puntosAtaque;


        if (Math.random() < 0.15) {
            dano = dano * 2;
            ultimoGolpeCritico = true;
        }

        enemigo.recibirDano(dano);
    }

    public void usarPocion(int cantidadRestaurada) {
        if (!estaVivo || cantidadRestaurada <= 0) {
            return;
        }
        setPuntosVida(puntosVida + cantidadRestaurada);
    }

    public void recibirDano(int cantidadDano) {
        if (cantidadDano < 0) {
            return;
        }
        setPuntosVida(puntosVida - cantidadDano);
    }


    public boolean ganarExperiencia(int puntos) {
        boolean subio = false;
        experiencia += puntos;
        int umbral = nivel * 50;

        while (experiencia >= umbral) {
            experiencia -= umbral;
            setNivel(nivel + 1);
            puntosAtaque += 2;
            vidaMaxima += 10;
            setPuntosVida(vidaMaxima);
            subio = true;
            umbral = nivel * 50;
        }
        return subio;
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

    public void setOro(double oro) {
        if (oro < 0.0) {
            System.out.println("Saldo insuficiente, la operacion fue rechazada.");
            return;
        }
        this.oro = oro;
    }


    protected void setNivel(int nivel) {
        if (nivel < 1) {
            return;
        }
        this.nivel = nivel;
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

    public int getExperiencia() {
        return experiencia;
    }

    public boolean isUltimoGolpeCritico() {
        return ultimoGolpeCritico;
    }
}