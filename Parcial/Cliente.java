public class Cliente {

    // Cantidad de proyectos para ser considerado cliente frecuente
    public static final int PROYECTOS_CLIENTE_FRECUENTE = 3;

    private String nombreCompletoORazonSocial;
    private String nitODocumento;
    private long telefono;
    private String correo;
    private String pais;
    private int cantidadProyectos;

    public Cliente(String nombreCompletoORazonSocial, String nitODocumento,
                   long telefono, String correo, String pais) {

        this.nombreCompletoORazonSocial = nombreCompletoORazonSocial;
        this.nitODocumento = nitODocumento;
        this.telefono = telefono;
        this.correo = correo;
        this.pais = pais;
        this.cantidadProyectos = 0;
    }

    // El cliente contrata un proyecto
    public void contratarProyecto(Proyecto proyecto) {

        if (proyecto == null) {
            System.out.println("Proyecto invalido.");
            return;
        }

        if (proyecto.getCliente() != null) {

            System.out.println(
                    "El proyecto " + proyecto.getCodigoProyecto()
                            + " ya tiene un cliente asignado."
            );

            return;
        }

        proyecto.setCliente(this);
        cantidadProyectos++;

        proyecto.calcularValorTotal();
    }

    public boolean esClienteFrecuente() {
        return cantidadProyectos >= PROYECTOS_CLIENTE_FRECUENTE;
    }

    // Consulta si el telefono es un numero perfecto
    public boolean consultarNumeroPerfecto() {

        long n = telefono;

        if (n < 2) {
            return false;
        }

        long suma = 1;

        for (long i = 2; i * i <= n; i++) {

            if (n % i == 0) {

                suma += i;

                if (i != n / i) {
                    suma += n / i;
                }
            }
        }

        return suma == n;
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

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}