package com.practica.ej32;

/**
 * Rápida (4% cada 100 ms), pero al llegar al 60% se confía y duerme 8 segundos.
 * Cuando despierta, la tortuga ya va casi en la meta.
 */
public class Liebre extends Corredor {

    private static final double PUNTO_SIESTA = 0.60;
    private static final int SIESTA_MS = 8000;

    private boolean yaDurmio = false;

    public Liebre() {
        super("La Liebre");
    }

    @Override
    protected void avanzar() throws InterruptedException {
        if (!yaDurmio && getProgreso() >= PUNTO_SIESTA) {
            yaDurmio = true;
            establecerEstado(Estado.DORMIDO);
            Thread.sleep(SIESTA_MS);              // se duerme solo el hilo de la liebre
            establecerEstado(Estado.CORRIENDO);
        } else {
            Thread.sleep(PASO_MS);
            mover(0.04);
        }
    }

    @Override
    public void reiniciar() {
        yaDurmio = false;
        super.reiniciar();
    }
}
