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
... (216 líneas restantes)