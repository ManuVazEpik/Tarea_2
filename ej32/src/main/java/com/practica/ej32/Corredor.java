package com.practica.ej32;

/**
 * CLASE ABSTRACTA: lo que tienen en común todos los corredores.
 * Cada tipo de corredor decide CÓMO avanza (método abstracto avanzar()).
 */
public abstract class Corredor implements Runnable {

    protected static final int PASO_MS = 100; // duración de cada "paso" de la carrera

    private final String nombre;
    private volatile double progreso;          // 0.0 a 1.0 (volatile: se lee desde varios hilos)
    private volatile Estado estado = Estado.LISTO;
    private CorredorListener listener;

    protected Corredor(String nombre) {
        this.nombre = nombre;
    }

    /** Cómo se mueve este corredor en cada ciclo. Lo define cada subclase. */
    protected abstract void avanzar() throws InterruptedException;

    /** Método plantilla: el ciclo de vida es igual para todos los corredores. */
    @Override
    public final void run() {
        try {
            establecerEstado(Estado.CORRIENDO);
            while (progreso < 1.0) {
                avanzar();
            }
            listener.alLlegar(this);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void reiniciar() {
        progreso = 0;
        estado = Estado.LISTO;
        notificar();
    }

    protected void mover(double delta) {
        progreso = Math.min(1.0, progreso + delta);
        notificar();
    }

    protected void establecerEstado(Estado nuevo) {
        estado = nuevo;
        notificar();
    }

    private void notificar() {
        if (listener != null) {
            listener.alCambiar(this);
        }
    }

    public void setListener(CorredorListener listener) {
        this.listener = listener;
    }

    public String getNombre() {
        return nombre;
    }

    public double getProgreso() {
        return progreso;
    }

    public Estado getEstado() {
        return estado;
    }
}
