package controlador;


import modelo.Usuario;
import modelo.Cliente;
import modelo.Producto;
import modelo.Carrito;
import modelo.Pedido;
import vista.VistaCliente;
import vista.VistaCatalogo;
import vista.VistaCarrito;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



// arriba todos los imprts si no f





public class ControladorCliente {
    private VistaCliente vistaCliente;
    private VistaCatalogo vistaCatalogo;
    private VistaCarrito vistaCarrito;
    private Cliente clienteActual;
    private Producto[] productos;
    private int totalProductos;
    private Carrito carrito;
    private Pedido[] pedidos;
    private int totalPedidos;

    public ControladorCliente(VistaCliente vistaCliente, Cliente cliente, Producto[] productos, int totalProductos) {
        this.vistaCliente = vistaCliente;
        this.clienteActual = cliente;
        this.productos = productos;
        this.totalProductos = totalProductos;
        this.carrito = new Carrito();
        this.pedidos = new Pedido[100];
        this.totalPedidos = 0;

        modelo.Pedido[] pedidosCargados = utilidades.Serializador.cargarPedidos();
        if (pedidosCargados != null) {
            for (int i = 0; i < pedidosCargados.length && totalPedidos < pedidos.length; i++) {
                pedidos[totalPedidos] = pedidosCargados[i];
                totalPedidos++;
            }
        }


        configurarVistaCliente();
    }

    private void configurarVistaCliente() {
        vistaCliente.setInfoCliente("Cliente: " + clienteActual.getNombre() + " | Compras: " + clienteActual.getComprasRealizadas());

        vistaCliente.setCatalogoListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirCatalogo();
            }
        });

        vistaCliente.setCarritoListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirCarrito();
            }
        });

        vistaCliente.setHistorialListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarHistorial();
            }
        });

        vistaCliente.setCerrarSesionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });
    }

    private void abrirCatalogo() {
        vistaCatalogo = new VistaCatalogo();
        configurarVistaCatalogo();
        actualizarCatalogo();
        vistaCliente.ocultar();
        vistaCatalogo.mostrar();
    }

    private void configurarVistaCatalogo() {
        vistaCatalogo.setAgregarCarritoListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarAlCarrito();
            }
        });

        vistaCatalogo.setVerCarritoListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirCarritoDesdeCatalogo();
            }
        });

        vistaCatalogo.setRegresarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarMenuCatalogo();
            }
        });

        //Termina los contructoresxxxxxxxxxxxxxxxxx


    }

    private void actualizarCatalogo() {
        StringBuilder lista = new StringBuilder("PRODUCTOS DISPONIBLES\n\n");
        String[] nombresProductos = new String[totalProductos];

        for (int i = 0; i < totalProductos; i++) {
            if (productos[i] != null && productos[i].getStock() > 0) {
                lista.append(productos[i].getInfoBasica()).append("\n");
                nombresProductos[i] = productos[i].getCodigo() + " - " + productos[i].getNombre() + " (Stock: " + productos[i].getStock() + ")";
            }
        }

        if (totalProductos == 0) {
            lista.append("No hay productos disponibles");
        }

        vistaCatalogo.mostrarProductos(lista.toString());
        vistaCatalogo.setProductosCombo(nombresProductos);
    }

    private void agregarAlCarrito() {
        String productoSeleccionado = vistaCatalogo.getProductoSeleccionado();
        String cantidadStr = vistaCatalogo.getCantidad();

        if (productoSeleccionado.isEmpty() || cantidadStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione producto y cantidad");
            return;
        }

        String codigoProducto = productoSeleccionado.split(" - ")[0];
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

        if (producto.getStock() < cantidad) {
            JOptionPane.showMessageDialog(null, "Stock insuficiente. Disponible: " + producto.getStock());
            return;
        }

        carrito.agregarProducto(producto, cantidad);
        vistaCatalogo.limpiarFormulario();
        JOptionPane.showMessageDialog(null, "Producto agregado al carrito");

        utilidades.Bitacora.registrarOperacion("CLIENTE", clienteActual.getCodigo(), "AGREGAR_CARRITO", "EXITOSO",
                "Producto: " + codigoProducto + ", Cantidad: " + cantidad);
    }

    private void abrirCarrito() {
        vistaCarrito = new VistaCarrito();
        configurarVistaCarrito();
        actualizarVistaCarrito();
        vistaCliente.ocultar();
        vistaCarrito.mostrar();
    }

    private void abrirCarritoDesdeCatalogo() {
        vistaCatalogo.ocultar();
        abrirCarrito();
    }

    private void configurarVistaCarrito() {
        vistaCarrito.setActualizarCantidadListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCantidadCarrito();
            }
        });

        vistaCarrito.setEliminarProductoListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarDelCarrito();
            }
        });

        vistaCarrito.setRealizarPedidoListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarPedido();
            }
        });

        vistaCarrito.setRegresarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarMenuCarrito();
            }
        });
    }
// termina contructores y empieza joption


    private void actualizarVistaCarrito() {
        StringBuilder contenido = new StringBuilder("MI CARRITO DE COMPRAS\n\n");

        if (carrito.getTotalItems() == 0) {
            contenido.append("Carrito vacio\n");
        } else {
            modelo.ItemCarrito[] items = carrito.getItems();
            for (int i = 0; i < carrito.getTotalItems(); i++) {
                if (items[i] != null) {
                    contenido.append(items[i].getInfoItem()).append("\n");
                }
            }
            contenido.append("\nTOTAL: Q").append(carrito.getTotal());
        }

        vistaCarrito.mostrarCarrito(contenido.toString());
    }

    private void actualizarCantidadCarrito() {
        String codigoProducto = vistaCarrito.getCodigoProducto();
        String cantidadStr = vistaCarrito.getNuevaCantidad();

        if (codigoProducto.isEmpty() || cantidadStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Complete codigo y cantidad");
            return;
        }

        int nuevaCantidad;
        try {
            nuevaCantidad = Integer.parseInt(cantidadStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Cantidad debe ser numero");
            return;
        }

        Producto producto = buscarProducto(codigoProducto);
        if (producto == null) {
            JOptionPane.showMessageDialog(null, "Producto no encontrado");
            return;
        }

        if (producto.getStock() < nuevaCantidad) {
            JOptionPane.showMessageDialog(null, "Stock insuficiente. Disponible: " + producto.getStock());
            return;
        }

        carrito.actualizarCantidad(codigoProducto, nuevaCantidad);
        vistaCarrito.limpiarFormulario();
        actualizarVistaCarrito();
        JOptionPane.showMessageDialog(null, "Cantidad actualizada");
    }

    private void eliminarDelCarrito() {
        String codigoProducto = vistaCarrito.getCodigoProducto();

        if (codigoProducto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese codigo del producto");
            return;
        }

        carrito.eliminarProducto(codigoProducto);
        vistaCarrito.limpiarFormulario();
        actualizarVistaCarrito();
        JOptionPane.showMessageDialog(null, "Producto eliminado del carrito");
    }

    private void realizarPedido() {
        if (carrito.getTotalItems() == 0) {
            JOptionPane.showMessageDialog(null, "Carrito vacio");
            return;
        }

        String codigoPedido = "PED-" + (totalPedidos + 1);
        Pedido nuevoPedido = new Pedido(codigoPedido, clienteActual.getCodigo(), clienteActual.getNombre(),
                carrito.getResumenProductos(), carrito.getTotal());

        if (totalPedidos < pedidos.length) {
            pedidos[totalPedidos] = nuevoPedido;
            totalPedidos++;

            utilidades.Serializador.guardarPedidos(pedidos, totalPedidos);

        }

        carrito.limpiarCarrito();
        actualizarVistaCarrito();
        JOptionPane.showMessageDialog(null, "Pedido realizado exitosamente. Codigo: " + codigoPedido);

        utilidades.Bitacora.registrarOperacion("CLIENTE", clienteActual.getCodigo(), "REALIZAR_PEDIDO", "EXITOSO",
                "Pedido: " + codigoPedido + ", Total: Q" + nuevoPedido.getTotal());
    }

    private void mostrarHistorial() {
        StringBuilder historial = new StringBuilder("HISTORIAL DE COMPRAS\n\n");

        int comprasCount = 0;
        for (int i = 0; i < totalPedidos; i++) {
            if (pedidos[i] != null && pedidos[i].getCodigoCliente().equals(clienteActual.getCodigo())) {
                historial.append(pedidos[i].getInfoBasica()).append("\n");
                comprasCount++;
            }
        }

        if (comprasCount == 0) {
            historial.append("No hay compras realizadas");
        }

        JOptionPane.showMessageDialog(null, historial.toString(), "Historial de Compras", JOptionPane.INFORMATION_MESSAGE);
    }

    private Producto buscarProducto(String codigo) {
        for (int i = 0; i < totalProductos; i++) {
            if (productos[i] != null && productos[i].getCodigo().equals(codigo)) {
                return productos[i];
            }
        }
        return null;
    }

    private void regresarMenuCatalogo() {
        vistaCatalogo.ocultar();
        vistaCliente.mostrar();
    }

    private void regresarMenuCarrito() {
        vistaCarrito.ocultar();
        vistaCliente.mostrar();
    }

    private void cerrarSesion() {
        utilidades.Serializador.guardarPedidos(pedidos, totalPedidos);
        vistaCliente.ocultar();
        controlador.ControladorLogin.getInstance().volverALogin();
    }
}
