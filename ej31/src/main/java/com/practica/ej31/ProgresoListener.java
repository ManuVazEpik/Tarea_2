package com.practica.ej31;

/**
 * INTERFAZ: contrato de quien quiere enterarse del avance de una tarea.
 * La tarea no sabe nada de JavaFX; solo avisa a su listener.
 */
public interface ProgresoListener {

    /** @param fraccion avance entre 0.0 y 1.0 */
    void alProgresar(double fraccion);

    void alTerminar();
}
