package mx.edu.utez.pacientes.repository;

import mx.edu.utez.pacientes.model.Paciente;
import java.util.ArrayList;
import java.util.List;

public class FileRepository {
    private final String FILE_NAME = "pacientes.csv";

    public boolean save(Paciente paciente) {
        System.out.println("Guardando en archivo: " + paciente);
        return true;
    }

    public List<Paciente> findAll() {
        return new ArrayList<>();
    }
}