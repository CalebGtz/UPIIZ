package com.example.eje_hcgo_06.util;

public class Basicas {
    public static double sumar(double a, double b) { return a + b; }
    public static double restar(double a, double b) { return a - b; }
    public static double multiplicar(double a, double b) { return a * b; }
    public static double dividir(double a, double b) {
        if (b == 0.0) throw new IllegalArgumentException("División por cero");
        return a / b;
    }
}
