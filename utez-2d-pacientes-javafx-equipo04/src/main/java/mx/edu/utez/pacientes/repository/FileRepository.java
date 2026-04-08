package mx.edu.utez.pacientes.repository;

import mx.edu.utez.pacientes.model.Paciente;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileRepository {
    private final String FILE_NAME = "pacientes.csv";

    public void escribirArchivo(List<Paciente> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Paciente p : lista) {
                bw.write(p.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
        }
    }

    public List<Paciente> leerArchivo() {
        List<Paciente> lista = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(",");
                if (d.length == 6) {
                    lista.add(new Paciente(d[0], d[1], Integer.parseInt(d[2]), d[3], d[4], d[5]));
                }
            }
        } catch (Exception e) {
            System.err.println("Error al leer: " + e.getMessage());
        }
        return lista;
    }
}