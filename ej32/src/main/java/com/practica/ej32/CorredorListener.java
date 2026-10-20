package com.practica.ej32;

/** INTERFAZ: lo que un corredor le avisa a la carrera. */
public interface CorredorListener {

    /** El corredor avanzó o cambió de estado. */
    void alCambiar(Corredor corredor);

    /** El corredor cruzó la meta. */
    void alLlegar(Corredor corredor);
}
