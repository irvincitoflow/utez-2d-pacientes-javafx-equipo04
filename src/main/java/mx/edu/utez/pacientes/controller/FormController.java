package mx.edu.utez.pacientes.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mx.edu.utez.pacientes.model.Paciente;
import mx.edu.utez.pacientes.service.PacienteService;

public class FormController {

    @FXML private TextField txtCurp, txtNombre, txtEdad, txtTelefono;
    @FXML private TextArea txtAlergias;

    private PacienteService service = new PacienteService();

    // VARIABLE CLAVE: Si es null, estamos creando. Si tiene datos, estamos editando.
    private Paciente pacienteEdicion;

    /**
     * Este método lo llamaremos desde el MainController justo antes de abrir la ventana
     * cuando el usuario presione el botón "Editar".
     */
    public void cargarDatos(Paciente p) {
        this.pacienteEdicion = p;
        txtCurp.setText(p.getCurp());
        txtCurp.setEditable(false); // No permitimos editar el CURP (es la llave primaria)
        txtNombre.setText(p.getNombre());
        txtEdad.setText(String.valueOf(p.getEdad()));
        txtTelefono.setText(p.getTelefono());
        txtAlergias.setText(p.getAlergias());
    }

    @FXML
    public void onGuardar() {
        try {
            // 1. Validaciones básicas de campos vacíos antes de procesar
            if (txtCurp.getText().isBlank() || txtNombre.getText().isBlank() || txtEdad.getText().isBlank()) {
                mostrarAlerta("Error", "Los campos marcados son obligatorios", Alert.AlertType.WARNING);
                return;
            }

            int edad = Integer.parseInt(txtEdad.getText());

            if (pacienteEdicion == null) {
                // MODO: REGISTRO NUEVO
                Paciente nuevo = new Paciente(
                        txtCurp.getText(),
                        txtNombre.getText(),
                        edad,
                        txtTelefono.getText(),
                        txtAlergias.getText(),
                        "ACTIVO"
                );

                String respuesta = service.registrarPaciente(nuevo);

                if (respuesta.equals("OK")) {
                    mostrarAlerta("Éxito", "Paciente registrado correctamente", Alert.AlertType.INFORMATION);
                    cerrarVentana();
                } else {
                    mostrarAlerta("Error de Validación", respuesta, Alert.AlertType.WARNING);
                }
            } else {
                // MODO: EDICIÓN
                // Actualizamos los datos del objeto que ya está en la lista
                pacienteEdicion.setNombre(txtNombre.getText());
                pacienteEdicion.setEdad(edad);
                pacienteEdicion.setTelefono(txtTelefono.getText());
                pacienteEdicion.setAlergias(txtAlergias.getText());

                // Guardamos los cambios en el archivo CSV
                service.guardarCambios();

                mostrarAlerta("Éxito", "Datos actualizados correctamente", Alert.AlertType.INFORMATION);
                cerrarVentana();
            }

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "La edad debe ser un número válido", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void onCancelar() {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) txtCurp.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null); // Limpia el diseño de la alerta
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}