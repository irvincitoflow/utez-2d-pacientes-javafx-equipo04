module mx.edu.utez.pacientes {
    // 1. Requerimos los módulos base de JavaFX
    requires javafx.controls;
    requires javafx.fxml;

    // 2. Necesario para que la TableView pueda leer los atributos de la clase Paciente
    // Si no pones esto, la tabla saldrá vacía aunque tenga datos.
    opens mx.edu.utez.pacientes.model to javafx.base;

    // 3. Necesario para que el FXMLLoader pueda conectar la vista con el controlador
    opens mx.edu.utez.pacientes.controller to javafx.fxml;

    // 4. Permitimos que el sistema principal ejecute tu aplicación
    exports mx.edu.utez.pacientes;
}