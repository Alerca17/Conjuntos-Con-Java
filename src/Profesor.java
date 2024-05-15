import java.util.Objects;

public class Profesor {

    private String nombre, cedula, fecha, sexo, facultad, titulo, asignaturas, horas;

    public Profesor(String asignaturas, String cedula, String facultad, String fecha, String horas, String nombre, String sexo, String titulo) {

        this.asignaturas = asignaturas;
        this.cedula = cedula;
        this.facultad = facultad;
        this.fecha = fecha;
        this.horas = horas;
        this.nombre = nombre;
        this.sexo = sexo;
        this.titulo = titulo;
    }

    public Profesor() {
    }

    public String getAsignaturas() {
        return asignaturas; // 1
    }

    public void setAsignaturas(String asignaturas) {
        this.asignaturas = asignaturas; // 1
    }

    public String getCedula() {
        return cedula; // 1
    }

    public void setCedula(String cedula) {
        this.cedula = cedula; // 1
    }

    public String getFacultad() {
        return facultad; // 1
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad; // 1
    }

    public String getFecha() {
        return fecha; // 1
    }

    public void setFecha(String fecha) {
        this.fecha = fecha; // 1
    }

    public String getHoras() {
        return horas; // 1
    }

    public void setHoras(String horas) {
        this.horas = horas; // 1
    }

    public String getNombre() {
        return nombre; // 1
    }

    public void setNombre(String nombre) {
        this.nombre = nombre; // 1
    }

    public String getTitulo() {
        return titulo; // 1
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo; // 1
    }

    public String getSexo() {
        return sexo; // 1
    }

    public void setSexo(String sexo) {
        this.sexo = sexo; // 1
    }

    @Override
    public String toString() {

        return "Nombre: " + nombre + " / " +
                " Cedula: " + cedula + " / " +
                " Fecha: " + fecha + " / " +
                " Sexo: " + sexo + " / " +
                " Facultad: " + facultad + " / " +
                " Titulo: " + titulo + " / " +
                " Asignaturas: " + asignaturas + " / " +
                " Horas: " + horas;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Profesor profesor = (Profesor) o;
        return Objects.equals(nombre, profesor.nombre) && Objects.equals(cedula, profesor.cedula) && Objects.equals(fecha, profesor.fecha) && Objects.equals(sexo, profesor.sexo) && Objects.equals(facultad, profesor.facultad) && Objects.equals(titulo, profesor.titulo) && Objects.equals(asignaturas, profesor.asignaturas) && Objects.equals(horas, profesor.horas);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nombre, cedula, fecha, sexo, facultad, titulo, asignaturas, horas);
    }
}
