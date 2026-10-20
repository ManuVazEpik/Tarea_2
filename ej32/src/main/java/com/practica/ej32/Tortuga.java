package com.practica.ej32;

/** Lenta pero constante: 1% cada 100 ms (tarda unos 10 segundos). Nunca se duerme. */
public class Tortuga extends Corredor {

    public Tortuga() {
        super("La Tortuga");
    }

    @Override
    protected void avanzar() throws InterruptedException {
        Thread.sleep(PASO_MS);
        mover(0.01);
    }
}
