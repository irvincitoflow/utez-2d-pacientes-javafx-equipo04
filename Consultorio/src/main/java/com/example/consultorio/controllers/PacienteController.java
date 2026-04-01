package com.example.consultorio.controllers;

import com.example.consultorio.models.Paciente;
import com.example.consultorio.services.PacienteService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PacienteController {
    @FXML
    private TextField txtCurp;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCurptxtEdad;
    @FXML
    private TextField txtCurptxtTelefono;
    @FXML
    private TextField txtCurptxtAlergias;
    @FXML
    private Label lblMsg;
    @FXML
    private TableView<Paciente> tblPacientes;

    private PacienteService service = new PacienteService();
    private final ObservableList<Paciente> data = FXCollections.observableArrayList();

    public void initialize() {
        loadFromService();
        tblPacientes.setItems(data);
    }

}