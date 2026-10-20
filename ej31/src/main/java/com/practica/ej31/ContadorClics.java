package com.practica.ej31;

/** Modelo sencillo: guarda cuántos clics se han dado. */
public class ContadorClics {

    private int cuenta;

    public void incrementar() {
        cuenta++;
    }

    public int getCuenta() {
        return cuenta;
    }
}
