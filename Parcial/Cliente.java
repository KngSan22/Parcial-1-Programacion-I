public class Cliente {

    private String nombreCompletoORazonSocial;
    private String nitODocumento;
    private long telefono;
    private String correo;
    private String pais;
    private int cantidadProyectos;

    public Cliente(String nombreCompletoORazonSocial, String nitODocumento, long telefono, String correo, String pais) {

        this.nombreCompletoORazonSocial = nombreCompletoORazonSocial;
        this.nitODocumento = nitODocumento;
        this.telefono = telefono;
        this.correo = correo;
        this.pais = pais;
        this.cantidadProyectos = 0;
    }

    // El cliente contrata un proyecto
    public boolean contratarProyecto(Proyecto proyecto) {

        if (proyecto == null) {
            return false;
        }

        if (proyecto.getCliente() != null) {
            return false;
        }

        proyecto.setCliente(this);

        cantidadProyectos++;

        proyecto.calcularValorTotal();
        return false;
    }

    // Verifica si es cliente frecuente
    public boolean esClienteFrecuente() {
        return cantidadProyectos >= 3;
    }

    // Getters
    public String getNombreCompletoORazonSocial() {
        return nombreCompletoORazonSocial;
    }
    public String getNitODocumento() {
        return nitODocumento;
    }
    public long getTelefono() {
        return telefono;
    }
    public String getCorreo() {
        return correo;
    }
    public String getPais() {
        return pais;
    }
    public int getCantidadProyectos() {
        return cantidadProyectos;
    }

    // Setters
    public void setNombreCompletoORazonSocial(String nombre) {
        this.nombreCompletoORazonSocial = nombre;
    }
    public void setNitODocumento(String nitODocumento) {
        this.nitODocumento = nitODocumento;
    }
    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public void setCantidadProyectos(int cantidadProyectos) {
        this.cantidadProyectos = cantidadProyectos;
    }
}