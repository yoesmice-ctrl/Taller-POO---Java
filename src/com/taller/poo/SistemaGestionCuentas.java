package com.taller.poo;

import java.util.Locale;
import java.util.Scanner;

public class SistemaGestionCuentas {

    public static void main(String[] args) {
        // Para que Scanner y printf usen el punto como separador decimal (ej. 100.50)
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        // Arreglo de tamaño fijo pre-poblado
        CuentaBancaria[] cuentas = new CuentaBancaria[3];
        cuentas[0] = new CuentaBancaria("1001", "Carlos Pérez", 1000.00, 500.0);
        cuentas[1] = new CuentaBancaria("1002", "Ana Gómez");
        cuentas[2] = new CuentaBancaria();

        int opcion;
        do {
            System.out.printf("%n===== SISTEMA DE GESTIÓN DE CUENTAS =====%n");
            System.out.printf("1. Depositar%n");
            System.out.printf("2. Retirar%n");
            System.out.printf("3. Ver reporte consolidado%n");
            System.out.printf("4. Salir%n");
            System.out.printf("Seleccione una opción: ");
            opcion = leerEntero(scanner);

            switch (opcion) {
                case 1:
                    System.out.printf("Número de cuenta: ");
                    String numDeposito = scanner.nextLine().trim();
                    CuentaBancaria cuentaDeposito = buscarCuenta(cuentas, numDeposito);
                    if (cuentaDeposito == null) {
                        System.out.printf("Error: la cuenta %s no existe.%n", numDeposito);
                    } else {
                        System.out.printf("Monto a depositar: ");
                        double montoDeposito = leerDecimal(scanner);
                        if (montoDeposito > 0) {
                            cuentaDeposito.depositar(montoDeposito);
                            System.out.printf("Depósito exitoso. Nuevo saldo: %s%n",
                                    ConversorMoneda.formatearMoneda(cuentaDeposito.getSaldo()));
                        } else {
                            System.out.printf("Error: el monto debe ser mayor a cero.%n");
                        }
                    }
                    break;

                case 2:
                    System.out.printf("Número de cuenta: ");
                    String numRetiro = scanner.nextLine().trim();
                    CuentaBancaria cuentaRetiro = buscarCuenta(cuentas, numRetiro);
                    if (cuentaRetiro == null) {
                        System.out.printf("Error: la cuenta %s no existe.%n", numRetiro);
                    } else {
                        System.out.printf("Monto a retirar: ");
                        double montoRetiro = leerDecimal(scanner);
                        if (montoRetiro > 0) {
                            if (cuentaRetiro.retirar(montoRetiro)) {
                                System.out.printf("Retiro exitoso. Nuevo saldo: %s%n",
                                        ConversorMoneda.formatearMoneda(cuentaRetiro.getSaldo()));
                            } else {
                                System.out.printf("Error: fondos insuficientes (saldo + crédito).%n");
                            }
                        } else {
                            System.out.printf("Error: el monto debe ser mayor a cero.%n");
                        }
                    }
                    break;

                case 3:
                    StringBuilder reporte = new StringBuilder();
                    double saldoTotal = 0.0;
                    for (int i = 0; i < cuentas.length; i++) {
                        CuentaBancaria c = cuentas[i];
                        reporte.append(String.format("Cuenta: %s | Titular: %s | Saldo: %s | Crédito: %s%n",
                                c.getNumeroCuenta(), c.getTitular(),
                                ConversorMoneda.formatearMoneda(c.getSaldo()),
                                ConversorMoneda.formatearMoneda(c.getLimiteCredito())));
                        saldoTotal += c.getSaldo();
                    }
                    System.out.printf("%n=== REPORTE CONSOLIDADO ===%n");
                    System.out.printf("%s", reporte.toString());
                    System.out.printf("Cuentas registradas: %d%n", cuentas.length);
                    System.out.printf("Saldo total acumulado: %s%n", ConversorMoneda.formatearMoneda(saldoTotal));
                    break;

                case 4:
                    System.out.printf("Saliendo del sistema. ¡Hasta pronto!%n");
                    break;

                default:
                    System.out.printf("Opción inválida. Elija un número entre 1 y 4.%n");
            }
        } while (opcion != 4);

        scanner.close();
    }

    // Recorre el arreglo buscando por numeroCuenta; retorna null si no existe
    private static CuentaBancaria buscarCuenta(CuentaBancaria[] cuentas, String numeroCuenta) {
        for (int i = 0; i < cuentas.length; i++) {
            if (cuentas[i].getNumeroCuenta().equals(numeroCuenta)) {
                return cuentas[i];
            }
        }
        return null;
    }

    // Lee un entero; si el usuario escribe otra cosa, vuelve a pedirlo
    private static int leerEntero(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.printf("Entrada inválida. Ingrese un número entero: ");
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // limpia el salto de línea pendiente
        return valor;
    }

    // Lee un decimal; si el usuario escribe otra cosa, vuelve a pedirlo
    private static double leerDecimal(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            scanner.next();
            System.out.printf("Entrada inválida. Ingrese un número: ");
        }
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }
}