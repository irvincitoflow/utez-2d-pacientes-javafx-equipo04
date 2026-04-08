package mx.edu.utez.pacientes.model;

public class Paciente {
    private String curp;
    private String nombre;
    private int edad;
    private String enfermedad; // O el dato que necesites

    public Paciente() {}

    public Paciente(String curp, String nombre, int edad, String enfermedad) {
        this.curp = curp;
        this.nombre = nombre;
        this.edad = edad;
        this.enfermedad = enfermedad;
    }

    // Getters y Setters (Obligatorios para que la tabla de JavaFX los lea)
    public String getCurp() { return curp; }
    public void setCurp(String curp) { this.curp = curp; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public String getEnfermedad() { return enfermedad; }
    public void setEnfermedad(String enfermedad) { this.enfermedad = enfermedad; }
}