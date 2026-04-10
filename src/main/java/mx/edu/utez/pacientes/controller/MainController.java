package mx.edu.utez.pacientes.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mx.edu.utez.pacientes.model.Paciente;
import mx.edu.utez.pacientes.service.PacienteService;
import java.io.IOException;
import java.util.Optional;

public class MainController {

    @FXML private TableView<Paciente> tblPacientes;
    @FXML private TableColumn<Paciente, String> colCurp, colNombre, colTelefono, colEstatus;
    @FXML private TableColumn<Paciente, Integer> colEdad;
    @FXML private Label lblTotal, lblActivos, lblInactivos;
    @FXML private TableColumn<Paciente, String> colAlergias;

    private PacienteService service = new PacienteService();

    @FXML
    public void initialize() {

        colCurp.setCellValueFactory(new PropertyValueFactory<>("curp"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colEstatus.setCellValueFactory(new PropertyValueFactory<>("estatus"));
        colAlergias.setCellValueFactory(new PropertyValueFactory<>("alergias"));

        // Llenar tabla y actualizar contadores
        tblPacientes.setItems(service.getListaPacientes());
        actualizarResumen();
    }

    private void actualizarResumen() {
        lblTotal.setText("Total: " + service.getTotal());
        lblActivos.setText("Activos: " + service.getActivos());
        lblInactivos.setText("Inactivos: " + service.getInactivos());
    }

    @FXML
    public void onNuevo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mx/edu/utez/pacientes/FormView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Nuevo Paciente");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            tblPacientes.refresh();
            actualizarResumen();
        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo abrir el formulario: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void onCambiarEstatus() {
        Paciente seleccionado = tblPacientes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            service.cambiarEstatus(seleccionado);
            tblPacientes.refresh();
            actualizarResumen();
        } else {
            mostrarAlerta("Atención", "Selecciona un paciente de la tabla.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    public void onEliminar() {
        Paciente seleccionado = tblPacientes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirmar");
            confirm.setHeaderText(null);
            confirm.setContentText("¿Seguro que deseas eliminar a " + seleccionado.getNombre() + "?");

            Optional<ButtonType> result = confirm.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                service.getListaPacientes().remove(seleccionado);
                actualizarResumen();
            }
        } else {
            mostrarAlerta("Atención", "Selecciona un paciente para eliminar.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    public void onEditar() {
        Paciente seleccionado = tblPacientes.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/mx/edu/utez/pacientes/FormView.fxml"));
                Parent root = loader.load();

                FormController controller = loader.getController();

                controller.cargarDatos(seleccionado);

                Stage stage = new Stage();
                stage.setTitle("Editar Paciente: " + seleccionado.getNombre());
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();

                tblPacientes.refresh();
                actualizarResumen();

            } catch (IOException e) {
                mostrarAlerta("Error", "No se pudo abrir el editor", Alert.AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Atención", "Selecciona un paciente de la tabla para editar.", Alert.AlertType.WARNING);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}