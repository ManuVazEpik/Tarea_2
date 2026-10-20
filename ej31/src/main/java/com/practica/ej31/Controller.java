package com.practica.ej31;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class Controller implements ProgresoListener {

    @FXML private TextField txtConteo;
    @FXML private ProgressBar pbEspera;
    @FXML private Button btnEsperar;

    private final ContadorClics contador = new ContadorClics();

    @FXML
    private void contar() {
        contador.incrementar();
        txtConteo.setText(String.valueOf(contador.getCuenta()));
    }

    @FXML
    private void esperar() {
        btnEsperar.setDisable(true);      // se deshabilita al iniciar
        pbEspera.setProgress(0);

        // El trabajo lento va en OTRO hilo; el hilo de la interfaz queda libre
        Thread hilo = new Thread(new TareaEspera(10, this), "hilo-espera");
        hilo.setDaemon(true);             // no impide cerrar la aplicación
        hilo.start();
    }

    // ---- Callbacks: se ejecutan en el hilo secundario ----
    // Los controles de JavaFX SOLO se tocan desde el hilo de la interfaz,
    // por eso cada actualización se manda con Platform.runLater.

    @Override
    public void alProgresar(double fraccion) {
        Platform.runLater(() -> pbEspera.setProgress(fraccion));
    }

    @Override
    public void alTerminar() {
        Platform.runLater(() -> btnEsperar.setDisable(false)); // se habilita al terminar
    }
}
