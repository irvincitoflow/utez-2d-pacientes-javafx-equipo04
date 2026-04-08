package mx.edu.utez.pacientes.service;

import mx.edu.utez.pacientes.model.Paciente;
import mx.edu.utez.pacientes.repository.FileRepository;
import java.util.List;

public class PacienteService {
    private FileRepository repo = new FileRepository();
    private List<Paciente> listaPacientes;

    public PacienteService() {
        this.listaPacientes = repo.leer();
    }

    public void agregarPaciente(Paciente p) {
        listaPacientes.add(p);
        repo.guardar(listaPacientes);
    }

    public List<Paciente> obtenerTodos() {
        return listaPacientes;
    }
}