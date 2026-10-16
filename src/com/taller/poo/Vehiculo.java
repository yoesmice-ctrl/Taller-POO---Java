package com.taller.poo;

/*
 * ANÁLISIS: PRIMITIVOS vs WRAPPERS
 * - Memoria: un tipo primitivo (int, double, boolean) guarda el valor directamente
 *   y ocupa poco espacio. Un Wrapper (Integer, Double, Boolean) es un objeto en el
 *   heap: ocupa más memoria (cabecera del objeto + referencia) y es más lento.
 * - Nulos: un Wrapper puede valer null (útil para representar "dato desconocido"),
 *   pero si se opera con él sin validar lanza NullPointerException. Un primitivo
 *   nunca puede ser null y siempre tiene un valor por defecto (0, 0.0, false).
 */
public class Vehiculo {
    private String marca;
    private String modelo;
    // double primitivo: un vehículo siempre tiene kilometraje (nunca es "desconocido")
    // y se evita el costo de memoria y el riesgo de null del Wrapper.
    private double kilometraje;
    // int primitivo: la velocidad máxima siempre es un número válido; no necesitamos null.
    private int velocidadMaxima;
    // boolean primitivo: el motor solo está encendido o apagado (dos estados, sin "nulo").
    private boolean encendido;

    // Constructor por defecto
    public Vehiculo() {
        this.marca = "Genérico";
        this.modelo = "Sin Modelo";
        this.kilometraje = 0.0;
        this.velocidadMaxima = 120;
        this.encendido = false;
    }

    // Constructor parametrizado (los parámetros se llaman igual que los atributos)
    public Vehiculo(String marca, String modelo, double kilometraje, int velocidadMaxima, boolean encendido) {
        this.marca = marca;
        this.modelo = modelo;
        this.kilometraje = kilometraje;
        this.velocidadMaxima = velocidadMaxima;
        this.encendido = encendido;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public double getKilometraje() {
        return kilometraje;
    }

    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public boolean isEncendido() {
        return encendido;
    }
}