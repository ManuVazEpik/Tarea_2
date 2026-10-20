package com.practica.ej32;

import java.util.ArrayList;
import java.util.List;

/**
 * Organiza la carrera: guarda los corredores en una COLECCIÓN (List),
 * lanza un Thread por cada uno y decide quién gana.
 */
public class Carrera implements CorredorListener {

    private final List<Corredor> corredores = new ArrayList<>();
    private final CarreraListener listener;

    private Corredor ganador;
    private int terminados;

    public Carrera(CarreraListener listener) {
        this.listener = listener;
    }

    public void agregar(Corredor corredor) {
        corredor.setListener(this);
        corredores.add(corredor);
    }

    public void iniciar() {
        ganador = null;
        terminados = 0;

        for (Corredor c : corredores) {
            c.reiniciar();
        }
        for (Corredor c : corredores) {
            Thread hilo = new Thread(c, "hilo-" + c.getNombre()); // un hilo por corredor
            hilo.setDaemon(true);
            hilo.start();
        }
    }

    @Override
    public void alCambiar(Corredor corredor) {
        listener.alCambiar(corredor);
    }

    /** synchronized: dos corredores no pueden "llegar primero" al mismo tiempo. */
    @Override
    public synchronized void alLlegar(Corredor corredor) {
        if (ganador == null) {
            ganador = corredor;
            corredor.establecerEstado(Estado.GANADOR);
        } else {
            corredor.establecerEstado(Estado.LLEGO);
        }

        terminados++;
        if (terminados == corredores.size()) {
            listener.alTerminar(ganador);
        }
    }
}
