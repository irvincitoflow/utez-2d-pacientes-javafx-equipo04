module mx.edu.utez.pacientes {
    requires javafx.controls;
    requires javafx.fxml;

    opens mx.edu.utez.pacientes to javafx.fxml;
    opens mx.edu.utez.pacientes.controller to javafx.fxml;
    opens mx.edu.utez.pacientes.model to javafx.base;

    exports mx.edu.utez.pacientes;
}