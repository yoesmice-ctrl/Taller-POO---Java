package com.taller.poo;

public class Estudiante {
    // Atributos privados, SIN inicializar en la declaración
    private String identificacion;
    private String nombreCompleto;
    private double promedioCalificaciones;
    private boolean estaMatriculado;

    // Constructor por defecto: el estado inicial se asigna aquí
    public Estudiante() {
        this.identificacion = "Sin ID";
        this.nombreCompleto = "Sin Nombre";
        this.promedioCalificaciones = 0.0;
        this.estaMatriculado = false;
    }

    // Constructor completo
    public Estudiante(String identificacion, String nombreCompleto, double promedioCalificaciones, boolean estaMatriculado) {
        this.identificacion = identificacion;
        this.nombreCompleto = nombreCompleto;
        this.promedioCalificaciones = promedioCalificaciones;
        this.estaMatriculado = estaMatriculado;
    }

    /**
     * Construye y retorna el reporte. NO imprime en consola:
     * la impresión es responsabilidad de la clase ejecutable.
     */
    public String obtenerReporteAcademicos() {
        StringBuilder reporte = new StringBuilder();
        reporte.append(String.format("Identificación: %s%n", identificacion));
        reporte.append(String.format("Estudiante: %s%n", nombreCompleto));
        reporte.append(String.format("Promedio: %.2f%n", promedioCalificaciones));
        reporte.append(String.format("Matriculado: %b", estaMatriculado));
        return reporte.toString();
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public double getPromedioCalificaciones() {
        return promedioCalificaciones;
    }

    public boolean isEstaMatriculado() {
        return estaMatriculado;
    }
}