package mx.edu.utez.pacientes.repository;

import mx.edu.utez.pacientes.model.Paciente;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileRepository {
    private final String FILE_NAME = "pacientes.txt";

    public void guardar(List<Paciente> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Paciente p : lista) {
                pw.println(p.getCurp() + "|" + p.getNombre() + "|" + p.getEdad() + "|" + p.getEnfermedad());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Paciente> leer() {
        List<Paciente> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("\\|");
                lista.add(new Paciente(datos[0], datos[1], Integer.parseInt(datos[2]), datos[3]));
            }
        } catch (IOException e) {
            System.out.println("Archivo no encontrado, iniciando lista vacía.");
        }
        return lista;
    }
}