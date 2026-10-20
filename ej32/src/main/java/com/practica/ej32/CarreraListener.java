package com.practica.ej32;

/** INTERFAZ: lo que la carrera le avisa a la interfaz gráfica. */
public interface CarreraListener {

    void alCambiar(Corredor corredor);

    void alTerminar(Corredor ganador);
}
