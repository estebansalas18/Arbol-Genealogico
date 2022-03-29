package segundapractica.code;

public class Persona {
    private String nombre, apellido, ciudad, fechaNac, fechaFal;
    private char genero;
    private int edad, generacion;
    
    public Persona(String nombre, String apellido, String ciudad, char genero, String fechaNac, String fechaFal, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.ciudad = ciudad;
        this.genero = genero;
        this.fechaNac = fechaNac;
        this.fechaFal = fechaFal;
        this.edad = edad;
    }

            
    public boolean estaMuerto(){
        if(fechaFal.length() != 0){
            return true;
        }else{
            return false;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getFechaFal() {
        return fechaFal;
    }

    public void setFechaFal(String fechaFal) {
        this.fechaFal = fechaFal;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
    
    
}
