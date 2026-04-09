package mx.edu.utez.pacientes.model;

public class Paciente {
    private String nombre;
    private String apellido;
    private int edad;
    private String curp;

    public Paciente() {
    }

    public Paciente(String nombre, String apellido, int edad, String curp) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.curp = curp;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getCurp() { return curp; }
    public void setCurp(String curp) { this.curp = curp; }

    @Override
    public String toString() {
        return "Paciente{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", curp='" + curp + '\'' +
                '}';
    }
}