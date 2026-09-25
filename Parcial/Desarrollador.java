import java.time.LocalDate;

public class Desarrollador {

    // Niveles
    public static final String JUNIOR = "Junior";
    public static final String SEMISENIOR = "Semisenior";
    public static final String SENIOR = "Senior";

    // Estados
    public static final String DISPONIBLE = "Disponible";
    public static final String ASIGNADO = "Asignado";
    public static final String OCUPADO = "Ocupado";
    public static final String EN_CAPACITACION = "En capacitacion";

    private static final int MAX_LISTA_PROYECTOS = 100;

    private String codigoDesarrollador;
    private String equipoTrabajo;
    private String nivel;
    private int maxProyectosSimultaneos;
    private double tarifaPorDia;
    private String estado;

    private Proyecto[] listProyectos;
    private int cantidadProyectos;

    public Desarrollador(String codigoDesarrollador, String equipoTrabajo, String nivel, int maxProyectosSimultaneos, double tarifaPorDia) {

        this.codigoDesarrollador = codigoDesarrollador;
        this.equipoTrabajo = equipoTrabajo;
        this.nivel = nivel;
        this.maxProyectosSimultaneos = maxProyectosSimultaneos;
        this.tarifaPorDia = tarifaPorDia;

        this.estado = DISPONIBLE;

        this.listProyectos = new Proyecto[MAX_LISTA_PROYECTOS];
        this.cantidadProyectos = 0;
    }

    // Para Empresa!
    public static boolean esNivelValido(String nivel) {
        return JUNIOR.equals(nivel) || SEMISENIOR.equals(nivel) || SENIOR.equals(nivel);
    }

    // Proyecto! no tocar!!!
    public boolean verificarDisponibilidad(LocalDate inicio, LocalDate fin) {

        if (estado.equals(EN_CAPACITACION)) {
            return false;
        }

        int solapados = 0;

        for (int i = 0; i < cantidadProyectos; i++) {

            Proyecto proyecto = listProyectos[i];

            if (proyecto.estaActivo()
                    && proyecto.seSolapaCon(inicio, fin)) {

                solapados++;
            }
        }

        return solapados < maxProyectosSimultaneos;
    }

    // Proyecto! no tocar!
    public void asignarProyecto(Proyecto proyecto) {

        if (proyecto == null) {
            return;
        }

        for (int i = 0; i < cantidadProyectos; i++) {

            if (listProyectos[i] == proyecto) {
                return;
            }
        }

        if (cantidadProyectos == MAX_LISTA_PROYECTOS) {
            return;
        }

        listProyectos[cantidadProyectos] = proyecto;
        cantidadProyectos++;
    }

    // Proyecto! no tocar
    public void actualizarEstado() {

        if (estado.equals(EN_CAPACITACION)) {
            return;
        }

        int activos = 0;

        for (int i = 0; i < cantidadProyectos; i++) {

            if (listProyectos[i].estaActivo()) {
                activos++;
            }
        }

        if (activos == 0) {
            estado = DISPONIBLE;

        } else if (activos >= maxProyectosSimultaneos) {
            estado = OCUPADO;

        } else {
            estado = ASIGNADO;
        }
    }

    // Para proyecto!!! No tocar
    public double calcularTarifaDesarrollo(int dias) {
        return tarifaPorDia * dias;
    }

    // Getters
    public String getCodigoDesarrollador() {
        return codigoDesarrollador;
    }
    public String getEquipoTrabajo() {
        return equipoTrabajo;
    }
    public String getNivel() {
        return nivel;
    }
    public int getMaxProyectosSimultaneos() {
        return maxProyectosSimultaneos;
    }
    public double getTarifaPorDia() {
        return tarifaPorDia;
    }
    public String getEstado() {
        return estado;
    }
    public int getCantidadProyectos() {
        return cantidadProyectos;
    }

    // Setters
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public void setTarifaPorDia(double tarifaPorDia) {
        this.tarifaPorDia = tarifaPorDia;
    }
}