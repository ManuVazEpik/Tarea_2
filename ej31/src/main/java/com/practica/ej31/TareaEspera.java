package com.practica.ej31;

/**
 * Tarea que "espera" N segundos avisando su avance.
 * Implementa Runnable para poder ejecutarse en un Thread aparte.
 */
public class TareaEspera implements Runnable {

    private static final int PASO_MS = 50; // cada cuánto se actualiza la barra

    private final int segundos;
    private final ProgresoListener listener;

    public TareaEspera(int segundos, ProgresoListener listener) {
        this.segundos = segundos;
        this.listener = listener;
    }

    @Override
    public void run() {
        int totalPasos = segundos * 1000 / PASO_MS;
        try {
            for (int paso = 1; paso <= totalPasos; paso++) {
                Thread.sleep(PASO_MS);                       // duerme SOLO este hilo
                listener.alProgresar((double) paso / totalPasos);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            listener.alTerminar();
        }
    }
}
