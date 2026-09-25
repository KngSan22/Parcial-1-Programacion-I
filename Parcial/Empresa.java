import javax.swing.*;
import java.time.LocalDate;

public class Empresa {

    private static final int CAPACIDAD = 100;

    private String nombreComercial;
    private String nit;
    private String direccion;
    private long telefono;
    private String paginaWeb;

    private Cliente[] listClientes;
    private int cantidadClientes;

    private Proyecto[] listProyectos;
    private int cantidadProyectos;

    private Desarrollador[] listDesarrolladores;
    private int cantidadDesarrolladores;

    private Servicio[] listServicios;
    private int cantidadServicios;

    public Empresa(String nombreComercial, String nit, String direccion, long telefono, String paginaWeb) {

        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.paginaWeb = paginaWeb;

        this.listClientes = new Cliente[CAPACIDAD];
        this.listProyectos = new Proyecto[CAPACIDAD];
        this.listDesarrolladores = new Desarrollador[CAPACIDAD];
        this.listServicios = new Servicio[CAPACIDAD];

        this.cantidadClientes = 0;
        this.cantidadProyectos = 0;
        this.cantidadDesarrolladores = 0;
        this.cantidadServicios = 0;
    }

    // Buscar cliente
    public Cliente buscarCliente(String nitODocumento) {

        for (int i = 0; i < cantidadClientes; i++) {

            if (listClientes[i].getNitODocumento().equals(nitODocumento)) {
                return listClientes[i];
            }
        }

        return null;
    }

    // Buscar proyecto
    public Proyecto buscarProyecto(String codigo) {

        for (int i = 0; i < cantidadProyectos; i++) {

            if (listProyectos[i].getCodigoProyecto().equals(codigo)) {
                return listProyectos[i];
            }
        }

        return null;
    }

    // Buscar desarrollador
    public Desarrollador buscarDesarrollador(String codigo) {

        for (int i = 0; i < cantidadDesarrolladores; i++) {

            if (listDesarrolladores[i].getCodigoDesarrollador().equals(codigo)) {
                return listDesarrolladores[i];
            }
        }

        return null;
    }

    // Buscar servicio
    public Servicio buscarServicio(String codigo) {

        for (int i = 0; i < cantidadServicios; i++) {

            if (listServicios[i].getCodigo().equals(codigo)) {
                return listServicios[i];
            }
        }

        return null;
    }

    // Verifica si el cliente ya esta registrado
    private boolean clienteRegistrado(Cliente cliente) {

        for (int i = 0; i < cantidadClientes; i++) {

            if (listClientes[i] == cliente) {
                return true;
            }
        }

        return false;
    }

    // Registrar cliente
    public boolean registrarCliente(Cliente cliente) {
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente invalido.");
            return false;
        }

        if (cantidadClientes == CAPACIDAD) {
            JOptionPane.showMessageDialog(null, "No hay espacio para mas clientes.");
            return false;
        }

        if (buscarCliente(cliente.getNitODocumento()) != null) {
            JOptionPane.showMessageDialog(null, "Ya existe un cliente con documento/NIT " + cliente.getNitODocumento() + ".");
            return false;
        }

        listClientes[cantidadClientes] = cliente;
        cantidadClientes++;

        return true;
    }

    // Registrar proyecto
    public boolean registrarProyecto(Proyecto proyecto) {
        if (proyecto == null) {
            JOptionPane.showMessageDialog(null, "Proyecto invalido.");
            return false;
        }

        if (cantidadProyectos == CAPACIDAD) {
            JOptionPane.showMessageDialog(null, "No hay espacio para mas proyectos.");
            return false;
        }

        if (buscarProyecto(proyecto.getCodigoProyecto()) != null) {
            JOptionPane.showMessageDialog(null, "Ya existe un proyecto con codigo " + proyecto.getCodigoProyecto() + ".");
            return false;
        }

        if (proyecto.getCliente() == null || !clienteRegistrado(proyecto.getCliente())) {

            JOptionPane.showMessageDialog(null, "El proyecto debe estar contratado por un cliente registrado.");
            return false;
        }

        if (!Proyecto.esMetodoPagoValido(proyecto.getMetodoPago())) {
            JOptionPane.showMessageDialog(null, "Metodo de pago invalido: " + proyecto.getMetodoPago());
            return false;
        }

        if (proyecto.getFechaInicio().isBefore(proyecto.getFechaSolicitud()) || proyecto.getFechaEntrega().isBefore(proyecto.getFechaInicio())) {

            JOptionPane.showMessageDialog(null, "Fechas invalidas (solicitud <= inicio <= entrega).");
            return false;
        }

        proyecto.calcularValorTotal();

        listProyectos[cantidadProyectos] = proyecto;
        cantidadProyectos++;

        return true;
    }

    // Registrar desarrollador
    public boolean registrarDesarrollador(Desarrollador desarrollador) {
        if (desarrollador == null) {
            JOptionPane.showMessageDialog(null, "Desarrollador invalido.");
            return false;
        }

        if (cantidadDesarrolladores == CAPACIDAD) {
            JOptionPane.showMessageDialog(null, "No hay espacio para mas desarrolladores.");
            return false;
        }

        if (buscarDesarrollador(
                desarrollador.getCodigoDesarrollador()) != null) {

            JOptionPane.showMessageDialog(null, "Ya existe un desarrollador con codigo " + desarrollador.getCodigoDesarrollador() + ".");
            return false;
        }

        if (!Desarrollador.esNivelValido(
                desarrollador.getNivel())) {

            JOptionPane.showMessageDialog(null, "Nivel invalido: " + desarrollador.getNivel());
            return false;
        }

        listDesarrolladores[cantidadDesarrolladores] = desarrollador;
        cantidadDesarrolladores++;

        return true;
    }

    // Registrar servicio
    public boolean registrarServicio(Servicio servicio) {
        if (servicio == null) {
            JOptionPane.showMessageDialog(null, "Servicio invalido.");
            return false;
        }

        if (cantidadServicios == CAPACIDAD) {
            JOptionPane.showMessageDialog(null, "No hay espacio para mas servicios.");
            return false;
        }

        if (buscarServicio(servicio.getCodigo()) != null) {
            JOptionPane.showMessageDialog(null, "Ya existe un servicio con codigo " + servicio.getCodigo() + ".");
            return false;
        }

        listServicios[cantidadServicios] = servicio;
        cantidadServicios++;

        return true;
    }

    // Consultar cliente por telefono
    public Cliente consultarClientePorTelefono(long telefono) {

        for (int i = 0; i < cantidadClientes; i++) {
            if (listClientes[i].getTelefono() == telefono) {
                return listClientes[i];
            }
        }

        return null;
    }

    // Calcular ingreso por fecha
    public double calcularIngresoPorFecha(LocalDate fecha) {

        double acumulado = 0;

        if (fecha == null) {
            return acumulado;
        }

        for (int i = 0; i < cantidadProyectos; i++) {
            if (listProyectos[i].getFechaSolicitud().equals(fecha)) {
                acumulado += listProyectos[i].getValorTotal();
            }
        }

        return acumulado;
    }

    // Listar clientes
    public void listarClientes() {

        JOptionPane.showMessageDialog(null,"--- Clientes (" + cantidadClientes + ") ---");

        for (int i = 0; i < cantidadClientes; i++) {
            JOptionPane.showMessageDialog(null, (i + 1) + ". " + listClientes[i].getNombreCompletoORazonSocial());
        }
    }

    // Listar proyectos
    public void listarProyectos() {

        JOptionPane.showMessageDialog(null, "--- Proyectos (" + cantidadProyectos + ") ---");

        for (int i = 0; i < cantidadProyectos; i++) {
            JOptionPane.showMessageDialog(null,(i + 1) + ". " + listProyectos[i].getCodigoProyecto());
        }
    }

    //Listar desarrolladores
    public void listarDesarrolladores() {

        JOptionPane.showMessageDialog(null, "--- Desarrolladores (" + cantidadDesarrolladores + ") ---");

        for (int i = 0; i < cantidadDesarrolladores; i++) {
            JOptionPane.showMessageDialog(null,(i + 1) + ". " + listDesarrolladores[i].getCodigoDesarrollador());
        }
    }

    //Listar servicios
    public void listarServicios() {

        JOptionPane.showMessageDialog(null, "--- Servicios (" + cantidadServicios + ") ---");

        for (int i = 0; i < cantidadServicios; i++) {
            JOptionPane.showMessageDialog(null, (i + 1) + ". " + listServicios[i].getNombre());
        }
    }

    // Getters
    public String getNombreComercial() {
        return nombreComercial;
    }
    public String getNit() {
        return nit;
    }
    public String getDireccion() {
        return direccion;
    }
    public long getTelefono() {
        return telefono;
    }
    public String getPaginaWeb() {
        return paginaWeb;
    }
}
