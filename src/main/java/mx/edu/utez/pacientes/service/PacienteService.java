package mx.edu.utez.pacientes.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mx.edu.utez.pacientes.model.Paciente;
import mx.edu.utez.pacientes.repository.FileRepository;
import java.util.List;

public class PacienteService {
    private FileRepository repository = new FileRepository();
    private static ObservableList<Paciente> listaPacientes;

    public PacienteService() {
        // Solo cargamos del archivo si la lista es nula (primera vez)
        if (listaPacientes == null) {
            this.listaPacientes = FXCollections.observableArrayList(repository.leerArchivo());
        }
    }

    // Lógica de Registro con Validaciones de Rúbrica
    public String registrarPaciente(Paciente p) {
        // 1. Validar campos vacíos
        if (p.getCurp().isBlank() || p.getNombre().isBlank() || p.getTelefono().isBlank()) {
            return "Todos los campos son obligatorios.";
        }
        // 2. Nombre min 5 caracteres
        if (p.getNombre().length() < 5) return "El nombre debe tener al menos 5 letras.";
        // 3. Edad 0-120
        if (p.getEdad() < 0 || p.getEdad() > 120) return "Edad no válida (0-120).";
        // 4. Teléfono 10 dígitos
        if (!p.getTelefono().matches("\\d{10}")) return "El teléfono debe ser de 10 dígitos.";
        // 5. Duplicados por CURP
        for (Paciente aux : listaPacientes) {
            if (aux.getCurp().equalsIgnoreCase(p.getCurp())) return "El CURP ya existe.";
        }

        listaPacientes.add(p);
        repository.escribirArchivo(listaPacientes);
        return "OK";
    }

    // Borrado lógico (Cambiar estatus)
    public void cambiarEstatus(Paciente p) {
        String nuevoEstatus = p.getEstatus().equals("ACTIVO") ? "INACTIVO" : "ACTIVO";
        p.setEstatus(nuevoEstatus);
        repository.escribirArchivo(listaPacientes);
    }

    // Métodos para el Resumen de pantalla
    public int getTotal() { return listaPacientes.size(); }

    public long getActivos() {
        return listaPacientes.stream().filter(p -> p.getEstatus().equals("ACTIVO")).count();
    }

    public long getInactivos() {
        return listaPacientes.stream().filter(p -> p.getEstatus().equals("INACTIVO")).count();
    }

    public ObservableList<Paciente> getListaPacientes() { return listaPacientes; }

    public void guardarCambios() {
        // Sobrescribe el archivo con la lista que ya tiene los datos editados
        repository.escribirArchivo(listaPacientes);
    }
}