package com.taller.depuracion;

import com.taller.poo.Estudiante;

public class PruebaEstudiante {
    public static void main(String[] args) {
        Estudiante estudiante1 = new Estudiante("1098765432", "Laura Martínez", 4.35, true);
        String reporte = estudiante1.obtenerReporteAcademicos();
        System.out.println(reporte);
    }
}