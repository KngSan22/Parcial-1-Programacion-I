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

    public Empresa(String nombreComercial, String nit, String direccion,
                   long telefono, String paginaWeb) {

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

    // ---------------- Busquedas ----------------

    public Cliente buscarCliente(String nitODocumento) {

        for (int i = 0; i < cantidadClientes; i++) {

            if (listClientes[i].getNitODocumento().equals(nitODocumento)) {
                return listClientes[i];
            }
        }

        return null;
    }

    public Proyecto buscarProyecto(String codigo) {

        for (int i = 0; i < cantidadProyectos; i++) {

            if (listProyectos[i].getCodigoProyecto().equals(codigo)) {
                return listProyectos[i];
            }
        }

        return null;
    }

    public Desarrollador buscarDesarrollador(String codigo) {

        for (int i = 0; i < cantidadDesarrolladores; i++) {

            if (listDesarrolladores[i].getCodigoDesarrollador().equals(codigo)) {
                return listDesarrolladores[i];
            }
        }

        return null;
    }

    public Servicio buscarServicio(String codigo) {

        for (int i = 0; i < cantidadServicios; i++) {

            if (listServicios[i].getCodigo().equals(codigo)) {
                return listServicios[i];
            }
        }

        return null;
    }

    private boolean clienteRegistrado(Cliente cliente) {

        for (int i = 0; i < cantidadClientes; i++) {

            if (listClientes[i] == cliente) {
                return true;
            }
        }

        return false;
    }

    // ---------------- Registros ----------------

    public void registrarCliente(Cliente cliente) {

        if (cliente == null) {
            System.out.println("Cliente invalido.");
            return;
        }

        if (cantidadClientes == CAPACIDAD) {
            System.out.println("No hay espacio para mas clientes.");
            return;
        }

        if (buscarCliente(cliente.getNitODocumento()) != null) {

            System.out.println(
                    "Ya existe un cliente con documento/NIT "
                            + cliente.getNitODocumento() + "."
            );

            return;
        }

        listClientes[cantidadClientes] = cliente;
        cantidadClientes++;

        System.out.println(
                "Cliente registrado: "
                        + cliente.getNombreCompletoORazonSocial()
        );
    }

    public void registrarProyecto(Proyecto proyecto) {

        if (proyecto == null) {
            System.out.println("Proyecto invalido.");
            return;
        }

        if (cantidadProyectos == CAPACIDAD) {
            System.out.println("No hay espacio para mas proyectos.");
            return;
        }

        if (buscarProyecto(proyecto.getCodigoProyecto()) != null) {

            System.out.println(
                    "Ya existe un proyecto con codigo "
                            + proyecto.getCodigoProyecto() + "."
            );

            return;
        }

        if (proyecto.getCliente() == null ||
                !clienteRegistrado(proyecto.getCliente())) {

            System.out.println(
                    "El proyecto debe estar contratado por un cliente registrado."
            );

            return;
        }

        if (!Proyecto.esMetodoPagoValido(proyecto.getMetodoPago())) {

            System.out.println(
                    "Metodo de pago invalido: "
                            + proyecto.getMetodoPago()
            );

            return;
        }

        if (proyecto.getFechaInicio().isBefore(proyecto.getFechaSolicitud()) ||
                proyecto.getFechaEntrega().isBefore(proyecto.getFechaInicio())) {

            System.out.println(
                    "Fechas invalidas (solicitud <= inicio <= entrega)."
            );

            return;
        }

        proyecto.calcularValorTotal();

        listProyectos[cantidadProyectos] = proyecto;
        cantidadProyectos++;

        System.out.println(
                "Proyecto registrado: "
                        + proyecto.getCodigoProyecto()
        );
    }

    public void registrarDesarrollador(Desarrollador desarrollador) {

        if (desarrollador == null) {
            System.out.println("Desarrollador invalido.");
            return;
        }

        if (cantidadDesarrolladores == CAPACIDAD) {
            System.out.println("No hay espacio para mas desarrolladores.");
            return;
        }

        if (buscarDesarrollador(
                desarrollador.getCodigoDesarrollador()) != null) {

            System.out.println(
                    "Ya existe un desarrollador con codigo "
                            + desarrollador.getCodigoDesarrollador() + "."
            );

            return;
        }

        if (!Desarrollador.esNivelValido(desarrollador.getNivel())) {

            System.out.println(
                    "Nivel invalido: "
                            + desarrollador.getNivel()
            );

            return;
        }

        listDesarrolladores[cantidadDesarrolladores] = desarrollador;
        cantidadDesarrolladores++;

        System.out.println(
                "Desarrollador registrado: "
                        + desarrollador.getCodigoDesarrollador()
        );
    }

    public void registrarServicio(Servicio servicio) {

        if (servicio == null) {
            System.out.println("Servicio invalido.");
            return;
        }

        if (cantidadServicios == CAPACIDAD) {
            System.out.println("No hay espacio para mas servicios.");
            return;
        }

        if (buscarServicio(servicio.getCodigo()) != null) {

            System.out.println(
                    "Ya existe un servicio con codigo "
                            + servicio.getCodigo() + "."
            );

            return;
        }

        listServicios[cantidadServicios] = servicio;
        cantidadServicios++;

        System.out.println(
                "Servicio registrado: "
                        + servicio.getNombre()
        );
    }

    // ---------------- Consultas ----------------

    public Cliente consultarClientePorTelefono(long telefono) {

        for (int i = 0; i < cantidadClientes; i++) {

            if (listClientes[i].getTelefono() == telefono) {
                return listClientes[i];
            }
        }

        return null;
    }

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

    // ---------------- Listados ----------------

    public void listarClientes() {

        System.out.println(
                "--- Clientes (" + cantidadClientes + ") ---"
        );

        for (int i = 0; i < cantidadClientes; i++) {

            System.out.println(
                    (i + 1) + ". "
                            + listClientes[i].getNombreCompletoORazonSocial()
            );
        }
    }

    public void listarProyectos() {

        System.out.println(
                "--- Proyectos (" + cantidadProyectos + ") ---"
        );

        for (int i = 0; i < cantidadProyectos; i++) {

            System.out.println(
                    (i + 1) + ". "
                            + listProyectos[i].getCodigoProyecto()
            );
        }
    }

    public void listarDesarrolladores() {

        System.out.println(
                "--- Desarrolladores (" + cantidadDesarrolladores + ") ---"
        );

        for (int i = 0; i < cantidadDesarrolladores; i++) {

            System.out.println(
                    (i + 1) + ". "
                            + listDesarrolladores[i].getCodigoDesarrollador()
            );
        }
    }

    public void listarServicios() {

        System.out.println(
                "--- Servicios (" + cantidadServicios + ") ---"
        );

        for (int i = 0; i < cantidadServicios; i++) {

            System.out.println(
                    (i + 1) + ". "
                            + listServicios[i].getNombre()
            );
        }
    }

    // ---------------- Getters ----------------

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