import javax.swing.JOptionPane;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        Empresa empresa = new Empresa("DevPlus", "900123456", "Cra. 27 #48-00, Armenia, Quindio",
                606323133, "www.devplus.com");

        int opcion = 0;

        while (opcion != 9) {

            String menu =
                    "===== DEVPLUS =====\n\n"
                            + "Seleccione una opcion:\n\n"
                            + "1. Registrar cliente\n"
                            + "2. Registrar desarrollador\n"
                            + "3. Registrar servicio\n"
                            + "4. Registrar proyecto\n"
                            + "5. Consultar cliente\n"
                            + "6. Consultar proyecto\n"
                            + "7. Cambiar estado de proyecto\n"
                            + "8. Mostrar informacion\n"
                            + "9. Salir";


            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));

            // 1. Registrar cliente
            if (opcion == 1) {

                String nombre = JOptionPane.showInputDialog("Nombre completo o razon social:");

                String documento = JOptionPane.showInputDialog("NIT o documento:");

                long telefono = Long.parseLong(JOptionPane.showInputDialog("Telefono:"));

                String correo = JOptionPane.showInputDialog("Correo:");

                String pais = JOptionPane.showInputDialog("Pais:");

                Cliente cliente = new Cliente(nombre, documento, telefono, correo, pais);

                empresa.registrarCliente(cliente);

                JOptionPane.showMessageDialog(null, "Cliente registrado correctamente.");
            }

            // 2. Registrar desarrollador
            else if (opcion == 2) {

                String codigo = JOptionPane.showInputDialog("Codigo del desarrollador:");

                String equipo = JOptionPane.showInputDialog("Equipo de trabajo:");

                String nivel = JOptionPane.showInputDialog("Nivel:\n" + "1. Junior\n" + "2. Semisenior\n" + "3. Senior");

                if (nivel.equals("1")) {
                    nivel = Desarrollador.JUNIOR;

                } else if (nivel.equals("2")) {
                    nivel = Desarrollador.SEMISENIOR;

                } else if (nivel.equals("3")) {
                    nivel = Desarrollador.SENIOR;

                } else {JOptionPane.showMessageDialog(null, "Nivel invalido.");
                    continue;
                }

                int maxProyectos = Integer.parseInt(JOptionPane.showInputDialog("Maximo de proyectos simultaneos:"));

                double tarifa = Double.parseDouble(JOptionPane.showInputDialog("Tarifa por dia:"));

                Desarrollador desarrollador = new Desarrollador(codigo, equipo, nivel, maxProyectos, tarifa);

                empresa.registrarDesarrollador(desarrollador);

                JOptionPane.showMessageDialog(null, "Desarrollador registrado correctamente.");
            }

            // 3. REGISTRAR SERVICIO
            else if (opcion == 3) {

                String codigo = JOptionPane.showInputDialog("Codigo del servicio:");

                String nombre = JOptionPane.showInputDialog("Nombre del servicio:");

                String descripcion = JOptionPane.showInputDialog("Descripcion:");

                double costo = Double.parseDouble(JOptionPane.showInputDialog("Costo del servicio:"));

                String disponible = JOptionPane.showInputDialog("¿El servicio esta disponible?\n" + "1. Si\n" + "2. No");

                boolean estaDisponible;

                if (disponible.equals("1")) {
                    estaDisponible = true;
                } else {
                    estaDisponible = false;
                }

                Servicio servicio = new Servicio(codigo, nombre, descripcion, costo, estaDisponible);

                empresa.registrarServicio(servicio);

                JOptionPane.showMessageDialog(null, "Servicio registrado correctamente.");
            }

            // 4. Registrar proyecto
            else if (opcion == 4) {

                String codigo = JOptionPane.showInputDialog("Codigo del proyecto:");

                String documentoCliente = JOptionPane.showInputDialog("NIT o documento del cliente:");

                Cliente cliente = empresa.buscarCliente(documentoCliente);

                if (cliente == null) {

                    JOptionPane.showMessageDialog(null, "No existe un cliente con ese documento.");

                } else {

                    String fechaSolicitudTexto = JOptionPane.showInputDialog("Fecha de solicitud\n" + "Formato: AAAA-MM-DD");

                    String fechaInicioTexto = JOptionPane.showInputDialog("Fecha de inicio\n" + "Formato: AAAA-MM-DD");

                    String fechaEntregaTexto = JOptionPane.showInputDialog("Fecha de entrega\n" + "Formato: AAAA-MM-DD");

                    LocalDate fechaSolicitud = LocalDate.parse(fechaSolicitudTexto);

                    LocalDate fechaInicio = LocalDate.parse(fechaInicioTexto);

                    LocalDate fechaEntrega = LocalDate.parse(fechaEntregaTexto);

                    String pago = JOptionPane.showInputDialog(
                            "Metodo de pago:\n" + "1. Tarjeta de credito\n" + "2. Transferencia bancaria\n" + "3. Efectivo");

                    if (pago.equals("1")) {
                        pago = Proyecto.TARJETA_CREDITO;

                    } else if (pago.equals("2")) {
                        pago = Proyecto.TRANSFERENCIA;

                    } else if (pago.equals("3")) {
                        pago = Proyecto.EFECTIVO;

                    } else {

                        JOptionPane.showMessageDialog(null, "Metodo de pago invalido.");
                        continue;
                    }

                    Proyecto proyecto = new Proyecto(codigo, fechaSolicitud, fechaInicio, fechaEntrega, pago);

                    cliente.contratarProyecto(proyecto);

                    empresa.registrarProyecto(proyecto);

                    JOptionPane.showMessageDialog(null, "Proyecto registrado correctamente.");
                }
            }

            // 5. CONSULTAR CLIENTE
            else if (opcion == 5) {

                String documento = JOptionPane.showInputDialog("Ingrese NIT o documento:");

                Cliente cliente = empresa.buscarCliente(documento);

                if (cliente == null) {

                    JOptionPane.showMessageDialog(null, "Cliente no encontrado.");

                } else {

                    String informacion =
                            "=== CLIENTE ===\n\n"
                                    + "Nombre: " + cliente.getNombreCompletoORazonSocial() + "\n"
                                    + "Documento/NIT: " + cliente.getNitODocumento() + "\n"
                                    + "Telefono: " + cliente.getTelefono() + "\n"
                                    + "Correo: " + cliente.getCorreo() + "\n"
                                    + "Pais: " + cliente.getPais() + "\n"
                                    + "Proyectos: " + cliente.getCantidadProyectos() + "\n"
                                    + "Cliente frecuente: ";

                    if (cliente.esClienteFrecuente()) {
                        informacion += "Si";
                    } else {
                        informacion += "No";
                    }

                    JOptionPane.showMessageDialog(null, informacion);
                }
            }

            // 6. CONSULTAR PROYECTO
            else if (opcion == 6) {

                String codigo = JOptionPane.showInputDialog("Codigo del proyecto:");

                Proyecto proyecto = empresa.buscarProyecto(codigo);

                if (proyecto == null) {
                    JOptionPane.showMessageDialog(null, "Proyecto no encontrado.");
                } else {

                    String nombreCliente;

                    if (proyecto.getCliente() == null) {
                        nombreCliente = "Sin cliente";
                    } else {
                        nombreCliente = proyecto.getCliente().getNombreCompletoORazonSocial();
                    }

                    String informacion = "=== PROYECTO ===\n\n"
                            + "Codigo: " + proyecto.getCodigoProyecto() + "\n"
                            + "Cliente: " + nombreCliente + "\n"
                            + "Fecha solicitud: " + proyecto.getFechaSolicitud() + "\n"
                            + "Fecha inicio: " + proyecto.getFechaInicio() + "\n"
                            + "Fecha entrega: " + proyecto.getFechaEntrega() + "\n"
                            + "Estado: " + proyecto.getEstado() + "\n"
                            + "Metodo de pago: " + proyecto.getMetodoPago() + "\n"
                            + "Desarrolladores: " + proyecto.getCantidadDesarrolladores() + "\n"
                            + "Servicios: " + proyecto.getCantidadServicios() + "\n"
                            + "Dias de desarrollo: " + proyecto.calcularDiasDesarrollo() + "\n"
                            + "Valor total: $" + proyecto.calcularValorTotal();
                    JOptionPane.showMessageDialog(null, informacion);
                }
            }

            // 7. Cambiar estado
            else if (opcion == 7) {

                String codigo =
                        JOptionPane.showInputDialog("Codigo del proyecto:");

                Proyecto proyecto = empresa.buscarProyecto(codigo);


                if (proyecto == null) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Proyecto no encontrado."
                    );

                } else {

                    String estado = JOptionPane.showInputDialog("Estado nuevo:\n" + "1. Confirmado\n" + "2. En curso\n" + "3. Finalizado\n" + "4. Cancelado");

                    if (estado.equals("1")) {

                        proyecto.actualizarEstado(
                                Proyecto.CONFIRMADO
                        );

                    } else if (estado.equals("2")) {

                        proyecto.actualizarEstado(
                                Proyecto.EN_CURSO
                        );

                    } else if (estado.equals("3")) {

                        proyecto.actualizarEstado(
                                Proyecto.FINALIZADO
                        );

                    } else if (estado.equals("4")) {

                        proyecto.actualizarEstado(
                                Proyecto.CANCELADO
                        );

                    } else {
                        JOptionPane.showMessageDialog(null, "Opcion invalida."
                        );
                    }
                }
            }
            // 8. Mostrar información
            else if (opcion == 8) {
                String informacion =
                        "=== INFORMACION DEVPLUS ===\n\n"
                                + "Empresa: " + empresa.getNombreComercial() + "\n"
                                + "NIT: " + empresa.getNit() + "\n"
                                + "Direccion: " + empresa.getDireccion() + "\n"
                                + "Telefono: " + empresa.getTelefono() + "\n"
                                + "Pagina web: " + empresa.getPaginaWeb() + "\n\n"
                                + "Clientes registrados: " + "\n"
                                + "Desarrolladores registrados: " + "\n"
                                + "Servicios registrados: " + "\n"
                                + "Proyectos registrados: ";
                JOptionPane.showMessageDialog(null, informacion);

                empresa.listarClientes();
                empresa.listarDesarrolladores();
                empresa.listarServicios();
                empresa.listarProyectos();
            }

            // 9. Salir
            else if (opcion == 9) {
                JOptionPane.showMessageDialog(null, "Gracias por usar DevPlus.");
            }
            // Invalida
            else {
                JOptionPane.showMessageDialog(null, "Opcion invalida.");
            }
        }
    }
}