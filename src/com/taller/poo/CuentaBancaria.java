package com.taller.poo;

public class CuentaBancaria {
    private String numeroCuenta;
    private String titular;
    private double saldo;
    private Double limiteCredito;

    /*
     * PREGUNTAS DE ANÁLISIS:
     * 1. ¿Qué diferencia ocurre a nivel de memoria y asignación por defecto al
     * declarar 'saldo' como double primitivo frente a 'limiteCredito' como Wrapper Double?
     *
     * RESPUESTA: 'saldo' (double primitivo) guarda el valor directamente dentro del
     * objeto, ocupa 8 bytes y, si no se inicializa, vale 0.0 por defecto.
     * 'limiteCredito' (Double) es una referencia a otro objeto en el heap: ocupa más
     * memoria (la referencia + el objeto Double con su cabecera) y, si no se inicializa,
     * su valor por defecto es null (no apunta a ningún objeto).
     *
     * 2. ¿Por qué una operación aritmética sobre un atributo no inicializado de
     * tipo Double provoca una NullPointerException, mientras que sobre un double
     * primitivo opera con 0.0?
     *
     * RESPUESTA: Para operar con un Double, Java hace "unboxing" automático, es decir,
     * llama internamente a limiteCredito.doubleValue(). Si la referencia es null no hay
     * objeto sobre el cual invocar ese método, y se lanza NullPointerException.
     * En cambio, el primitivo double siempre tiene un valor real (0.0 por defecto),
     * así que la operación se ejecuta normalmente.
     */

    // Constructor por defecto
    public CuentaBancaria() {
        this.numeroCuenta = "000000";
        this.titular = "Sin Nombre";
        this.saldo = 0.0;
        this.limiteCredito = 0.0;
    }

    // Constructor de 2 parámetros
    public CuentaBancaria(String numeroCuenta, String titular) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = 0.0;
        this.limiteCredito = 100.0;
    }

    // Constructor completo de 4 parámetros
    public CuentaBancaria(String numeroCuenta, String titular, double saldo, Double limiteCredito) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldo;
        this.limiteCredito = limiteCredito;
    }

    public boolean depositar(double monto) {
        if (monto > 0) {
            this.saldo += monto;
            return true;
        } else {
            return false;
        }
    }

    public boolean retirar(double monto) {
        // El crédito solo se suma si no es null (evita NullPointerException)
        double disponible = this.saldo;
        if (this.limiteCredito != null) {
            disponible += this.limiteCredito;
        }

        if (monto > 0) {
            if (disponible >= monto) {
                this.saldo -= monto;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public Double getLimiteCredito() {
        return limiteCredito;
    }
}