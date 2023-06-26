package CRUD;

public class Persona {
    private String nombre;
    private String identificacion;
    private int edad;
    private String sexo;
    private String correo;
    private String direccion;

    public Persona(String nombre, String identificacion, int edad, String sexo, String correo, String direccion) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.edad = edad;
        this.sexo = sexo;
        this.correo = correo;
        this.direccion = direccion;
    }

    // Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return nombre + ", " + identificacion + ", " + edad + ", " + sexo + ", " + correo + ", " + direccion;
    }
}
