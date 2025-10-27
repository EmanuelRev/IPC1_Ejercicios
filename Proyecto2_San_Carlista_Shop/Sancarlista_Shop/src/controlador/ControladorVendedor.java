package controlador;

import modelo.Usuario;
import modelo.Vendedor;
import modelo.Cliente;
import modelo.Pedido;
import modelo.Producto;
import vista.VistaVendedor;
import vista.VistaGestionStock;
import vista.VistaGestionClientes;
import vista.VistaPedidos;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVendedor {
    private VistaVendedor vistaVendedor;
    private VistaGestionStock vistaStock;
    private VistaGestionClientes vistaClientes;
    private VistaPedidos vistaPedidos;
    private vista.VistaHistorialIngresos vistaHistorial;
    private Vendedor vendedorActual;
    private Usuario[] usuarios;
    private int totalUsuarios;
    private Producto[] productos;
    private int totalProductos;
    private Pedido[] pedidos;
    private int totalPedidos;

    public ControladorVendedor(VistaVendedor vistaVendedor, Vendedor vendedor, Usuario[] usuarios, int totalUsuarios, Producto[] productos, int totalProductos) {
        this.vistaVendedor = vistaVendedor;
        this.vendedorActual = vendedor;
        this.usuarios = usuarios;
        this.totalUsuarios = totalUsuarios;
        this.productos = productos;
        this.totalProductos = totalProductos;
        this.pedidos = new Pedido[100];
        this.totalPedidos = 0;

        configurarVistaVendedor();
        cargarPedidosPendientes();
    }

    private void configurarVistaVendedor() {
        vistaVendedor.setInfoVendedor("Vendedor: " + vendedorActual.getNombre() + " | Ventas Confirmadas: " + vendedorActual.getVentasConfirmadas());

        vistaVendedor.setProductosListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirGestionStock();
            }
        });

        vistaVendedor.setClientesListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirGestionClientes();
            }
        });

        vistaVendedor.setPedidosListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirPedidosPendientes();
            }
        });

        vistaVendedor.setHistorialIngresosListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirHistorialIngresos();
            }
        });

        vistaVendedor.setCerrarSesionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });
    }

    private void abrirHistorialIngresos() {
        vistaHistorial = new vista.VistaHistorialIngresos();
        configurarVistaHistorial();
        cargarHistorialIngresos();
        vistaVendedor.ocultar();
        vistaHistorial.mostrar();
    }

    private void configurarVistaHistorial() {
        vistaHistorial.setGenerarCSVListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarReporteCSV();
            }
        });

        vistaHistorial.setRegresarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vistaHistorial.ocultar();
                vistaVendedor.mostrar();
            }
        });
    }

    private void cargarHistorialIngresos() {
        StringBuilder historial = new StringBuilder("HISTORIAL DE INGRESOS DE STOCK\n\n");

        historial.append("Fecha       | Hora     | Producto | Cantidad | Vendedor\n");
        historial.append("------------------------------------------------------\n");

        String[] registros = utilidades.Bitacora.obtenerRegistrosIngresos();
        if (registros.length == 0) {
            historial.append("No hay registros de ingresos\n");
        } else {
            for (String registro : registros) {
                historial.append(registro).append("\n");
            }
        }

        vistaHistorial.mostrarHistorial(historial.toString());
    }

    private void generarReporteCSV() {
        boolean exito = utilidades.Bitacora.exportarHistorialIngresos("historial_ingresos.csv");

        if (exito) {
            JOptionPane.showMessageDialog(null, "Reporte CSV generado exitosamente");
        } else {
            JOptionPane.showMessageDialog(null, "Error al generar reporte CSV");
        }
    }

    private void abrirGestionStock() {
        vistaStock = new VistaGestionStock();
        configurarVistaStock();
        actualizarListaProductosStock();
        vistaVendedor.ocultar();
        vistaStock.mostrar();
    }

    private void configurarVistaStock() {
        vistaStock.setAgregarStockListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarStock();
            }
        });

        vistaStock.setRegresarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarMenuStock();
            }
        });
    }

    private void agregarStock() {
        String codigoProducto = vistaStock.getCodigoProducto();
        String cantidadStr = vistaStock.getCantidad();

        if (codigoProducto.isEmpty() || cantidadStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos");
            return;
        }

        int cantidad;
        try {
            cantidad = Integer.parseInt(cantidadStr);
            if (cantidad <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Cantidad debe ser numero positivo");
            return;
        }

        Producto producto = buscarProducto(codigoProducto);
        if (producto == null) {
            JOptionPane.showMessageDialog(null, "Producto no encontrado");
            return;
        }

        producto.setStock(producto.getStock() + cantidad);
        vistaStock.limpiarFormulario();
        actualizarListaProductosStock();
        JOptionPane.showMessageDialog(null, "Stock actualizado: " + producto.getNombre() + " +" + cantidad);

        utilidades.Bitacora.registrarOperacion("VENDEDOR", vendedorActual.getCodigo(), "AGREGAR_STOCK", "EXITOSO",
                "Producto: " + codigoProducto + ", Cantidad: " + cantidad);
    }

    private void actualizarListaProductosStock() {
        StringBuilder lista = new StringBuilder("PRODUCTOS EN INVENTARIO\n\n");

        for (int i = 0; i < totalProductos; i++) {
            if (productos[i] != null) {
                lista.append(productos[i].getInfoBasica()).append("\n");
            }
        }

        if (totalProductos == 0) {
            lista.append("No hay productos en inventario");
        }

        vistaStock.mostrarProductos(lista.toString());
    }

    private void abrirGestionClientes() {
        vistaClientes = new VistaGestionClientes();
        configurarVistaClientes();
        actualizarListaClientes();
        vistaVendedor.ocultar();
        vistaClientes.mostrar();
    }

    private void configurarVistaClientes() {
        vistaClientes.setCrearListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearCliente();
            }
        });

        vistaClientes.setCargarCSVListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarClientesDesdeCSV();
            }
        });

        vistaClientes.setRegresarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarMenuClientes();
            }
        });
    }

    private void crearCliente() {
        String codigo = vistaClientes.getCodigo();
        String nombre = vistaClientes.getNombre();
        String genero = vistaClientes.getGenero();
        String cumpleanos = vistaClientes.getCumpleanos();
        String contrasenia = vistaClientes.getContrasenia();

        if (codigo.isEmpty() || nombre.isEmpty() || genero.isEmpty() || cumpleanos.isEmpty() || contrasenia.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos");
            return;
        }

        if (!genero.equals("M") && !genero.equals("F")) {
            JOptionPane.showMessageDialog(null, "Genero debe ser M o F");
            return;
        }

        if (buscarUsuario(codigo) != null) {
            JOptionPane.showMessageDialog(null, "El codigo ya existe");
            return;
        }

        Cliente nuevoCliente = new Cliente(codigo, nombre, genero, contrasenia, cumpleanos);
        usuarios[totalUsuarios] = nuevoCliente;
        totalUsuarios++;

        vistaClientes.limpiarFormulario();
        actualizarListaClientes();
        JOptionPane.showMessageDialog(null, "Cliente creado exitosamente");

        utilidades.Bitacora.registrarOperacion("VENDEDOR", vendedorActual.getCodigo(), "CREAR_CLIENTE", "EXITOSO",
                "Cliente: " + codigo);
    }

    private void actualizarListaClientes() {
        StringBuilder lista = new StringBuilder("CLIENTES REGISTRADOS\n\n");
        boolean hayClientes = false;

        for (int i = 0; i < totalUsuarios; i++) {
            if (usuarios[i] instanceof Cliente) {
                Cliente cliente = (Cliente) usuarios[i];
                lista.append(cliente.getInfoCompleta()).append("\n");
                hayClientes = true;
            }
        }

        if (!hayClientes) {
            lista.append("No hay clientes registrados");
        }

        vistaClientes.mostrarClientes(lista.toString());
    }

    private void abrirPedidosPendientes() {
        vistaPedidos = new VistaPedidos();
        configurarVistaPedidos();
        actualizarListaPedidos();
        vistaVendedor.ocultar();
        vistaPedidos.mostrar();
    }

    private void configurarVistaPedidos() {
        vistaPedidos.setConfirmarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmarPedido();
            }
        });

        vistaPedidos.setRegresarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarMenuPedidos();
            }
        });
    }

    private void actualizarListaPedidos() {
        StringBuilder lista = new StringBuilder("PEDIDOS PENDIENTES\n\n");

        for (int i = 0; i < totalPedidos; i++) {
            if (pedidos[i] != null && !pedidos[i].isConfirmado()) {
                lista.append(pedidos[i].getInfoBasica()).append("\n");
            }
        }

        if (totalPedidos == 0) {
            lista.append("No hay pedidos pendientes");
        }

        vistaPedidos.mostrarPedidos(lista.toString());
    }

    private void confirmarPedido() {
        String seleccion = JOptionPane.showInputDialog("Ingrese codigo del pedido a confirmar:");
        if (seleccion == null || seleccion.trim().isEmpty()) return;

        Pedido pedido = buscarPedido(seleccion.trim());
        if (pedido == null) {
            JOptionPane.showMessageDialog(null, "Pedido no encontrado");
            return;
        }

        if (pedido.isConfirmado()) {
            JOptionPane.showMessageDialog(null, "Pedido ya esta confirmado");
            return;
        }

        pedido.confirmar();
        vendedorActual.aumentarVentas();
        actualizarListaPedidos();
        JOptionPane.showMessageDialog(null, "Pedido confirmado exitosamente");

        utilidades.Bitacora.registrarOperacion("VENDEDOR", vendedorActual.getCodigo(), "CONFIRMAR_PEDIDO", "EXITOSO",
                "Pedido: " + pedido.getCodigo() + ", Cliente: " + pedido.getNombreCliente());
    }

    private void cargarPedidosPendientes() {
        modelo.Pedido[] pedidosCargados = utilidades.Serializador.cargarPedidos();
        if (pedidosCargados != null) {
            for (int i = 0; i < pedidosCargados.length && totalPedidos < pedidos.length; i++) {
                pedidos[totalPedidos] = pedidosCargados[i];
                totalPedidos++;
            }
            System.out.println("Pedidos cargados: " + pedidosCargados.length);
        }
    }

    private Producto buscarProducto(String codigo) {
        for (int i = 0; i < totalProductos; i++) {
            if (productos[i] != null && productos[i].getCodigo().equals(codigo)) {
                return productos[i];
            }
        }
        return null;
    }

    private Usuario buscarUsuario(String codigo) {
        for (int i = 0; i < totalUsuarios; i++) {
            if (usuarios[i] != null && usuarios[i].getCodigo().equals(codigo)) {
                return usuarios[i];
            }
        }
        return null;
    }

    private Pedido buscarPedido(String codigo) {
        for (int i = 0; i < totalPedidos; i++) {
            if (pedidos[i] != null && pedidos[i].getCodigo().equals(codigo)) {
                return pedidos[i];
            }
        }
        return null;
    }

    private void regresarMenuStock() {
        vistaStock.ocultar();
        vistaVendedor.mostrar();
    }

    private void regresarMenuClientes() {
        vistaClientes.ocultar();
        vistaVendedor.mostrar();
    }

    private void regresarMenuPedidos() {
        vistaPedidos.ocultar();
        vistaVendedor.mostrar();
    }

    private void cerrarSesion() {
        utilidades.Serializador.guardarPedidos(pedidos, totalPedidos);
        vistaVendedor.ocultar();
        controlador.ControladorLogin.getInstance().volverALogin();
    }
    private void cargarClientesDesdeCSV() {
        String rutaArchivo = utilidades.ArchivosCSV.seleccionarArchivoCSV();
        if (rutaArchivo == null) return;

        modelo.Cliente[] clientesCargados = utilidades.ArchivosCSV.cargarClientesDesdeCSV(rutaArchivo);

        int agregados = 0;
        for (int i = 0; i < clientesCargados.length; i++) {
            if (clientesCargados[i] != null && buscarUsuario(clientesCargados[i].getCodigo()) == null) {
                if (totalUsuarios < usuarios.length) {
                    usuarios[totalUsuarios] = clientesCargados[i];
                    totalUsuarios++;
                    agregados++;
                }
            }
        }

        actualizarListaClientes();
        JOptionPane.showMessageDialog(null, "Se agregaron " + agregados + " clientes desde CSV");
        utilidades.Bitacora.registrarOperacion("VENDEDOR", vendedorActual.getCodigo(), "CARGAR_CSV_CLIENTES", "EXITOSO", "Clientes cargados: " + agregados);
    }

}