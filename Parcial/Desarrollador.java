public class Desarrollador {
    // Atributos
    private String codigoDesarrollador;
    private String equipoTrabajo;
    private String nivel; // Junior, Semisenior, Senior
    private int maxProyectosSimultaneos;
    private double tarifaPorDia;
    private String estado; // Disponible, Asignado, Ocupado, En capacitación

    // Constructor
    public Desarrollador(String codigoDesarrollador, String equipoTrabajo, String nivel,
                         int maxProyectosSimultaneos, double tarifaPorDia, String estado) {
        this.codigoDesarrollador = codigoDesarrollador;
        this.equipoTrabajo = equipoTrabajo;
        this.nivel = nivel;
        this.maxProyectosSimultaneos = maxProyectosSimultaneos;
        this.tarifaPorDia = tarifaPorDia;
        this.estado = estado;
    }

    // Getters y Setters
    public String getCodigoDesarrollador() {
        return codigoDesarrollador;
    }

    public void setCodigoDesarrollador(String codigoDesarrollador) {
        this.codigoDesarrollador = codigoDesarrollador;
    }

    public String getEquipoTrabajo() {
        return equipoTrabajo;
    }

    public void setEquipoTrabajo(String equipoTrabajo) {
        this.equipoTrabajo = equipoTrabajo;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getMaxProyectosSimultaneos() {
        return maxProyectosSimultaneos;
    }

    public void setMaxProyectosSimultaneos(int maxProyectosSimultaneos) {
        this.maxProyectosSimultaneos = maxProyectosSimultaneos;
    }

    public double getTarifaPorDia() {
        return tarifaPorDia;
    }

    public void setTarifaPorDia(double tarifaPorDia) {
        this.tarifaPorDia = tarifaPorDia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Métodos del UML
    public boolean verificarDisponibilidad() {
        return estado.equalsIgnoreCase("Disponible");
    }

    public void asignarProyecto() {
        if (verificarDisponibilidad()) {
            estado = "Asignado";
            System.out.println("El desarrollador " + codigoDesarrollador + " ha sido asignado a un proyecto.");
        } else {
            System.out.println("El desarrollador " + codigoDesarrollador + " no está disponible.");
        }
    }

    public void actualizarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
        System.out.println("El estado del desarrollador " + codigoDesarrollador + " cambió a: " + nuevoEstado);
    }

    public double calcularTarifaDesarrollo(int dias) {
        return tarifaPorDia * dias;
    }
}

