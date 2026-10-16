package com.taller.poo;

import java.util.Locale;

public class Principal {
    public static void main(String[] args) {
        // Fuerza el punto decimal (1250.75) sin importar la configuración regional del equipo
        Locale.setDefault(Locale.US);

        // ===== VEHÍCULOS =====
        Vehiculo vehiculo1 = new Vehiculo("Toyota", "Corolla", 15000.50, 180, true);
        Vehiculo vehiculo2 = new Vehiculo();
        Vehiculo vehiculo3 = new Vehiculo("Mazda", "CX-5", 32000.00, 210, false);
        Vehiculo[] vehiculos = {vehiculo1, vehiculo2, vehiculo3};

        // ===== ESTUDIANTES =====
        Estudiante estudiante1 = new Estudiante("1098765432", "Laura Martínez", 4.35, true);
        Estudiante estudiante2 = new Estudiante("1012345678", "Andrés Rojas", 3.80, true);
        Estudiante estudiante3 = new Estudiante();
        Estudiante[] estudiantes = {estudiante1, estudiante2, estudiante3};

        // ===== CUENTAS BANCARIAS (los 3 constructores) =====
        CuentaBancaria cuenta1 = new CuentaBancaria("1001", "Carlos Pérez", 1000.00, 500.0);
        CuentaBancaria cuenta2 = new CuentaBancaria("1002", "Ana Gómez");
        CuentaBancaria cuenta3 = new CuentaBancaria();

        // ===== MODIFICACIÓN DE ESTADO =====
        boolean deposito1 = cuenta1.depositar(250.75);
        boolean deposito2 = cuenta2.depositar(100.00);
        boolean retiro2 = cuenta2.retirar(50.00);
        boolean retiroInvalido = cuenta3.retirar(500.00); // supera saldo + crédito
        boolean depositoInvalido = cuenta3.depositar(-20.00); // monto no válido

        System.out.printf("=== OPERACIONES ===%n");
        System.out.printf("Depósito de $250.75 en 1001: %b%n", deposito1);
        System.out.printf("Depósito de $100.00 en 1002: %b%n", deposito2);
        System.out.printf("Retiro de $50.00 en 1002: %b%n", retiro2);
        System.out.printf("Retiro de $500.00 en 000000: %b%n", retiroInvalido);
        System.out.printf("Depósito de -$20.00 en 000000: %b%n%n", depositoInvalido);

        // ===== MÉTODOS ESTÁTICOS (sin new) =====
        double euros = ConversorMoneda.convertirDolaresAEuros(100.0, 0.92);
        System.out.printf("=== CONVERSOR ===%n");
        System.out.printf("100 USD a EUR (tasa 0.92): %s%n%n", ConversorMoneda.formatearMoneda(euros));

        // ===== REPORTES =====
        System.out.printf("=== REPORTE DE VEHÍCULOS ===%n");
        for (int i = 0; i < vehiculos.length; i++) {
            Vehiculo v = vehiculos[i];
            System.out.printf("[%d] Marca: %s | Modelo: %s | KM: %.2f | VelMax: %d km/h | Encendido: %b%n",
                    i + 1, v.getMarca(), v.getModelo(), v.getKilometraje(), v.getVelocidadMaxima(), v.isEncendido());
        }

        System.out.printf("%n=== REPORTE DE ESTUDIANTES ===%n");
        for (int i = 0; i < estudiantes.length; i++) {
            System.out.printf("[%d]%n%s%n", i + 1, estudiantes[i].obtenerReporteAcademicos());
        }

        System.out.printf("%n=== REPORTE DE CUENTAS BANCARIAS ===%n");
        CuentaBancaria[] cuentas = {cuenta1, cuenta2, cuenta3};
        for (int i = 0; i < cuentas.length; i++) {
            CuentaBancaria c = cuentas[i];
            System.out.printf("Cuenta: %s | Titular: %s | Saldo Final: %s | Crédito: %s%n",
                    c.getNumeroCuenta(), c.getTitular(),
                    ConversorMoneda.formatearMoneda(c.getSaldo()),
                    ConversorMoneda.formatearMoneda(c.getLimiteCredito()));
        }
    }
}