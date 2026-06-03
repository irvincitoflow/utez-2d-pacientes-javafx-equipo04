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

        if (listaPacientes == null) {
            this.listaPacientes = FXCollections.observableArrayList(repository.leerArchivo());
        }
    }


    public String registrarPaciente(Paciente p) {

        if (p.getCurp().isBlank() || p.getNombre().isBlank() || p.getTelefono().isBlank()) {
            return "Todos los campos son obligatorios.";
        }

        if (p.getNombre().length() < 5){
            return "El nombre debe tener al menos 5 letras.";
        }

        if (p.getEdad() < 0 || p.getEdad() > 120) {
            return "Ingresa un edad real de 0-120";
        }

        if (p.getTelefono().length() != 10) {
            return "El teléfono debe tener exactamente 10 dígitos.";
        }

        for (Paciente aux : listaPacientes) {
            if (aux.getCurp().equalsIgnoreCase(p.getCurp())) return "El CURP ya existe.";
        }

        listaPacientes.add(p);
        repository.escribirArchivo(listaPacientes);
        return "Paciente Registrado con exito";
    }

    public void cambiarEstatus(Paciente p) {
        String nuevoEstatus = p.getEstatus().equals("ACTIVO") ? "INACTIVO" : "ACTIVO";
        p.setEstatus(nuevoEstatus);
        repository.escribirArchivo(listaPacientes);
    }

    public int getTotal() { return listaPacientes.size(); }

    public long getActivos() {
        return listaPacientes.stream().filter(p -> p.getEstatus().equals("ACTIVO")).count();
    }

    public long getInactivos() {
        return listaPacientes.stream().filter(p -> p.getEstatus().equals("INACTIVO")).count();
    }

    public ObservableList<Paciente> getListaPacientes() { return listaPacientes; }

    public void guardarCambios() {
        repository.escribirArchivo(listaPacientes);
    }
}