import java.time.LocalDate;

public class Proyecto {

    // Estados
    public static final String PENDIENTE = "Pendiente";
    public static final String CONFIRMADO = "Confirmado";
    public static final String EN_CURSO = "En curso";
    public static final String FINALIZADO = "Finalizado";
    public static final String CANCELADO = "Cancelado";

    // Metodos de pago
    public static final String TARJETA_CREDITO = "Tarjeta de credito";
    public static final String TRANSFERENCIA = "Transferencia bancaria";
    public static final String EFECTIVO = "Efectivo";

    public static final double DESCUENTO_CLIENTE_FRECUENTE = 0.10;

    private static final int MAX_DESARROLLADORES = 20;
    private static final int MAX_SERVICIOS = 20;

    private String codigoProyecto;
    private LocalDate fechaSolicitud;
    private LocalDate fechaInicio;
    private LocalDate fechaEntrega;
    private String estado;
    private String metodoPago;
    private double valorTotal;
    private Cliente cliente;

    private Desarrollador[] listDesarrolladores;
    private int cantidadDesarrolladores;

    private Servicio[] listServicios;
    private int cantidadServicios;


    public Proyecto(String codigoProyecto, LocalDate fechaSolicitud,
                    LocalDate fechaInicio, LocalDate fechaEntrega,
                    String metodoPago) {

        this.codigoProyecto = codigoProyecto;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaInicio = fechaInicio;
        this.fechaEntrega = fechaEntrega;
        this.metodoPago = metodoPago;

        this.estado = PENDIENTE;
        this.valorTotal = 0;
        this.cliente = null;

        this.listDesarrolladores =
                new Desarrollador[MAX_DESARROLLADORES];

        this.cantidadDesarrolladores = 0;

        this.listServicios =
                new Servicio[MAX_SERVICIOS];

        this.cantidadServicios = 0;
    }


    public static boolean esEstadoValido(String estado) {

        return PENDIENTE.equals(estado)
                || CONFIRMADO.equals(estado)
                || EN_CURSO.equals(estado)
                || FINALIZADO.equals(estado)
                || CANCELADO.equals(estado);
    }


    public static boolean esMetodoPagoValido(String metodo) {

        return TARJETA_CREDITO.equals(metodo)
                || TRANSFERENCIA.equals(metodo)
                || EFECTIVO.equals(metodo);
    }


    public boolean estaActivo() {

        return estado.equals(CONFIRMADO)
                || estado.equals(EN_CURSO);
    }


    public boolean seSolapaCon(LocalDate inicio, LocalDate fin) {

        return !fechaInicio.isAfter(fin)
                && !inicio.isAfter(fechaEntrega);
    }


    public int calcularDiasDesarrollo() {

        return (int) (
                fechaEntrega.toEpochDay()
                        - fechaInicio.toEpochDay()
        ) + 1;
    }


    public double calcularValorTotal() {

        int dias = calcularDiasDesarrollo();
        double subtotal = 0;

        for (int i = 0; i < cantidadDesarrolladores; i++) {

            subtotal +=
                    listDesarrolladores[i]
                            .calcularTarifaDesarrollo(dias);
        }

        for (int i = 0; i < cantidadServicios; i++) {

            subtotal +=
                    listServicios[i]
                            .calcularCostoServicio();
        }

        double descuento = 0;

        if (cliente != null && cliente.esClienteFrecuente()) {

            descuento =
                    subtotal * DESCUENTO_CLIENTE_FRECUENTE;
        }

        valorTotal = subtotal - descuento;

        return valorTotal;
    }


    public void asignarDesarrollador(Desarrollador desarrollador) {

        if (desarrollador == null) {

            System.out.println("Desarrollador invalido.");
            return;
        }

        if (!estado.equals(PENDIENTE)) {

            System.out.println(
                    "Solo se pueden asignar desarrolladores "
                            + "a proyectos Pendientes."
            );

            return;
        }

        if (cantidadDesarrolladores == MAX_DESARROLLADORES) {

            System.out.println(
                    "El proyecto " + codigoProyecto
                            + " ya alcanzo el maximo de desarrolladores."
            );

            return;
        }

        for (int i = 0; i < cantidadDesarrolladores; i++) {

            if (listDesarrolladores[i] == desarrollador) {

                System.out.println(
                        "El desarrollador "
                                + desarrollador.getCodigoDesarrollador()
                                + " ya esta en el proyecto."
                );

                return;
            }
        }

        if (!desarrollador.verificarDisponibilidad(
                fechaInicio, fechaEntrega)) {

            System.out.println(
                    "El desarrollador "
                            + desarrollador.getCodigoDesarrollador()
                            + " no esta disponible entre "
                            + fechaInicio + " y " + fechaEntrega + "."
            );

            return;
        }

        listDesarrolladores[cantidadDesarrolladores] =
                desarrollador;

        cantidadDesarrolladores++;

        calcularValorTotal();

        System.out.println(
                "Desarrollador "
                        + desarrollador.getCodigoDesarrollador()
                        + " asignado al proyecto "
                        + codigoProyecto + "."
        );
    }


    public boolean validarDisponibilidad() {

        for (int i = 0; i < cantidadDesarrolladores; i++) {

            if (!listDesarrolladores[i].verificarDisponibilidad(
                    fechaInicio, fechaEntrega)) {

                return false;
            }
        }

        return true;
    }


    public void agregarServicio(Servicio servicio) {

        if (servicio == null) {

            System.out.println("Servicio invalido.");
            return;
        }

        if (estado.equals(FINALIZADO)
                || estado.equals(CANCELADO)) {

            System.out.println(
                    "No se pueden agregar servicios a un proyecto "
                            + estado + "."
            );

            return;
        }

        if (!servicio.verificarDisponibilidad()) {

            System.out.println(
                    "El servicio "
                            + servicio.getNombre()
                            + " no esta disponible."
            );

            return;
        }

        if (cantidadServicios == MAX_SERVICIOS) {

            System.out.println(
                    "El proyecto " + codigoProyecto
                            + " ya alcanzo el maximo de servicios."
            );

            return;
        }

        for (int i = 0; i < cantidadServicios; i++) {

            if (listServicios[i] == servicio) {

                System.out.println(
                        "El servicio "
                                + servicio.getNombre()
                                + " ya esta en el proyecto."
                );

                return;
            }
        }

        listServicios[cantidadServicios] = servicio;

        cantidadServicios++;

        calcularValorTotal();

        System.out.println(
                "Servicio "
                        + servicio.getNombre()
                        + " agregado al proyecto "
                        + codigoProyecto + "."
        );
    }


    public void actualizarEstado(String nuevoEstado) {

        if (!esEstadoValido(nuevoEstado)) {

            System.out.println(
                    "Estado invalido: " + nuevoEstado
            );

            return;
        }

        boolean permitido = false;

        if (estado.equals(PENDIENTE)
                && (nuevoEstado.equals(CONFIRMADO)
                || nuevoEstado.equals(CANCELADO))) {

            permitido = true;

        } else if (estado.equals(CONFIRMADO)
                && (nuevoEstado.equals(EN_CURSO)
                || nuevoEstado.equals(CANCELADO))) {

            permitido = true;

        } else if (estado.equals(EN_CURSO)
                && (nuevoEstado.equals(FINALIZADO)
                || nuevoEstado.equals(CANCELADO))) {

            permitido = true;
        }

        if (!permitido) {

            System.out.println(
                    "No se puede pasar de "
                            + estado + " a "
                            + nuevoEstado + "."
            );

            return;
        }


        if (nuevoEstado.equals(CONFIRMADO)) {

            if (cantidadDesarrolladores == 0) {

                System.out.println(
                        "No se puede confirmar un proyecto "
                                + "sin desarrolladores."
                );

                return;
            }

            if (!validarDisponibilidad()) {

                System.out.println(
                        "No se puede confirmar: algun "
                                + "desarrollador ya no esta disponible."
                );

                return;
            }

            estado = nuevoEstado;

            for (int i = 0; i < cantidadDesarrolladores; i++) {

                listDesarrolladores[i].asignarProyecto(this);
            }

        } else {

            estado = nuevoEstado;
        }


        for (int i = 0; i < cantidadDesarrolladores; i++) {

            listDesarrolladores[i].actualizarEstado();
        }


        System.out.println(
                "Proyecto " + codigoProyecto
                        + " ahora esta " + estado + "."
        );
    }


    public String getCodigoProyecto() {
        return codigoProyecto;
    }

    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getCantidadDesarrolladores() {
        return cantidadDesarrolladores;
    }

    public int getCantidadServicios() {
        return cantidadServicios;
    }


    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
}