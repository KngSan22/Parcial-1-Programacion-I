public class Servicio {

    // Atributos
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private boolean disponibilidad;

    // Constructor
    public Servicio(String codigo, String nombre, String descripcion, double precio, boolean disponibilidad) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public double getPrecio() {
        return precio;
    }
    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    // Setters
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    // Proyecto no tocar
    public boolean verificarDisponibilidad() {
        return disponibilidad;
    }

    public double calcularCostoServicio() {
        if (disponibilidad) {
            return precio;
        }

        return 0;
    }
}