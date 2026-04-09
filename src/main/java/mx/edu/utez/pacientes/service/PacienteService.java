package mx.edu.utez.pacientes.service;

import mx.edu.utez.pacientes.model.Paciente;
import mx.edu.utez.pacientes.repository.FileRepository;
import java.util.List;

public class PacienteService {
    private FileRepository repository = new FileRepository();

    public boolean registrarPaciente(Paciente p) {
        return repository.save(p);
    }

    public List<Paciente> obtenerTodos() {
        return repository.findAll();
    }
}