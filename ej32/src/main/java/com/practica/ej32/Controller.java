package com.practica.ej32;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.util.HashMap;
import java.util.Map;

public class Controller implements CarreraListener {

    @FXML private Button btnIniciar;
    @FXML private Label lblLiebre, lblTortuga, lblResultado;
    @FXML private ProgressBar pbLiebre, pbTortuga;

    private final Carrera carrera = new Carrera(this);

    // COLECCIONES: asocian cada corredor con su barra y su etiqueta
    private final Map<Corredor, ProgressBar> barras = new HashMap<>();
    private final Map<Corredor, Label> etiquetas = new HashMap<>();

    @FXML
    private void initialize() {
        Corredor liebre = new Liebre();
        Corredor tortuga = new Tortuga();

        carrera.agregar(liebre);
        carrera.agregar(tortuga);

        barras.put(liebre, pbLiebre);
        barras.put(tortuga, pbTortuga);
        etiquetas.put(liebre, lblLiebre);
        etiquetas.put(tortuga, lblTortuga);
    }

    @FXML
    private void iniciar() {
        btnIniciar.setDisable(true);
        lblResultado.setText("");
        carrera.iniciar();
    }

    // ---- Callbacks: llegan desde los hilos de los corredores ----

    @Override
    public void alCambiar(Corredor corredor) {
        // Se toma una "foto" de los valores AHORA, antes de pasar al hilo de la interfaz
        double progreso = corredor.getProgreso();
        String texto = corredor.getNombre() + ": " + corredor.getEstado().getMensaje();

        Platform.runLater(() -> {
            barras.get(corredor).setProgress(progreso);
            etiquetas.get(corredor).setText(texto);
        });
    }

    @Override
    public void alTerminar(Corredor ganador) {
        Platform.runLater(() -> {
            lblResultado.setText("¡" + ganador.getNombre() + " ganó la carrera!");
            btnIniciar.setDisable(false);
        });
    }
}
