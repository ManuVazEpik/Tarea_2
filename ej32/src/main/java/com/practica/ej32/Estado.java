package com.practica.ej32;

/** ENUM: los estados posibles de un corredor, cada uno con su mensaje. */
public enum Estado {
    LISTO("Listo para correr"),
    CORRIENDO("¡Está corriendo!"),
    DORMIDO("¡Se durmió!"),
    GANADOR("¡Ganó la carrera!"),
    LLEGO("Llegó a la meta");

    private final String mensaje;

    Estado(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
