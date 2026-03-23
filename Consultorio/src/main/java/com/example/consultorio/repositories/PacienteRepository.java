package com.example.consultorio.repositories;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

public class PacienteRepository {
    // Definimos la ruta tal como en tu ejemplo
    private final Path filePath = Paths.get("data", "pacientes.csv");

    private void ensureFileExists() throws IOException {
        if (Files.notExists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }
        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }
    }

    public List<String> readAllLines() throws IOException {
        ensureFileExists();
        return Files.readAllLines(filePath, StandardCharsets.UTF_8);
    }

    public void appendLine(String line) throws IOException {
        ensureFileExists();
        Files.writeString(filePath, line + System.lineSeparator(),
                StandardCharsets.UTF_8, StandardOpenOption.APPEND);
    }

    public void writeAllLines(List<String> lines) throws IOException {
        ensureFileExists();
        Files.write(filePath, lines, StandardCharsets.UTF_8);
    }
}