package mx.edu.utez.pacientes.controller;

import javafx.fxml.FXML;
import mx.edu.utez.pacientes.service.PacienteService;

public class MainController {
    private PacienteService service = new PacienteService();

    @FXML
    public void initialize() {
        System.out.println("Controlador principal listo.");
    }
}