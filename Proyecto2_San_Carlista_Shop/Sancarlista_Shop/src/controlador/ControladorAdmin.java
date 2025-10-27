package controlador;

import modelo.Usuario;
import modelo.Vendedor;
import modelo.tipos.ReportesVentasVendedor;
import vista.VistaActualizarVendedor;
import vista.VistaAdmin;
import vista.VistaGestionVendedores;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorAdmin {

    private vista.VistaGestionProductos vistaProductos;
    private modelo.Producto[] productos;
    private int totalProductos;

    private VistaAdmin vistaAdmin;
    private VistaGestionVendedores vistaVendedores;
    private VistaActualizarVendedor vistaActualizarVendedor;
    private vista.VistaBitacora vistaBitacora;
    private Usuario[] usuarios;
    private int totalUsuarios;

    public ControladorAdmin(vista.VistaAdmin vistaAdmin, modelo.Usuario[] usuarios, int totalUsuarios) {
        this.vistaAdmin = vistaAdmin;
        this.usuarios = usuarios;
        this.totalUsuarios = totalUsuarios;
        configurarListenerAdmin();

        this.productos = new modelo.Producto[100];
        this.totalProductos = 0;

        cargarDatos();
        configurarListenerAdmin();
    }

    public void configurarListenerAdmin() {
        vistaAdmin.setGestionVendedoresL(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirGestionVendedores();
            }
        });

        vistaAdmin.setCerrarSesionL(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });

        vistaAdmin.setGestionProductosL(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirGestionProductos();
            }
        });

        vistaAdmin.setReportesL(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirReportes();
            }
        });
    }

    private void abrirReportes() {
        vista.VistaReportes vistaReportes = new vista.VistaReportes();
        configurarVistaReportes(vistaReportes);
        vistaAdmin.ocultar();
        vistaReportes.mostrar();
    }

    private void configurarVistaReportes(vista.VistaReportes vistaReportes) {
        vistaReportes.setProductosMasVendidosListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarReporteProductosMasVendidos();
            }
        });

        vistaReportes.setProductosMenosVendidosListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarReporteProductosMenosVendidos();
            }
        });

        vistaReportes.setInventarioListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarReporteInventario();
            }
        });

        vistaReportes.setVentasVendedorListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarReporteVentasVendedor();
            }
        });

        vistaReportes.setFinancieroListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarReporteFinanciero();
            }
        });

        vistaReportes.setProductosCaducarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarReporteProductosCaducar();
            }
        });

        vistaReportes.setRegresarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vistaReportes.ocultar();
                vistaAdmin.mostrar();
            }
        });
    }

    private void generarReporteProductosMasVendidos() {
        modelo.Pedido[] pedidosVacios = new modelo.Pedido[0];
        modelo.tipos.ReporteProductosMasVendidos reporte = new modelo.tipos.ReporteProductosMasVendidos(productos, totalProductos, pedidosVacios, 0);
        controlador.ControladorReportes.generarReporte(reporte);
    }

    private void generarReporteProductosMenosVendidos() {
        modelo.Pedido[] pedidosVacios = new modelo.Pedido[0];
        modelo.tipos.ReporteProductosMenosVendidos reporte = new modelo.tipos.ReporteProductosMenosVendidos(productos, totalProductos, pedidosVacios, 0);
        controlador.ControladorReportes.generarReporte(reporte);
    }

    private void generarReporteInventario() {
        modelo.tipos.ReporteInventario reporte = new modelo.tipos.ReporteInventario(productos, totalProductos);
        controlador.ControladorReportes.generarReporte(reporte);
    }

    private void generarReporteVentasVendedor() {
        modelo.Vendedor[] vendedoresVacios = new modelo.Vendedor[0];
        modelo.Pedido[] pedidosVacios = new modelo.Pedido[0];
        ReportesVentasVendedor reporte = new ReportesVentasVendedor(vendedoresVacios, 0, pedidosVacios, 0);
        controlador.ControladorReportes.generarReporte(reporte);
    }

    private void generarReporteFinanciero() {
        modelo.Pedido[] pedidosVacios = new modelo.Pedido[0];
        modelo.tipos.ReporteFinanciero reporte = new modelo.tipos.ReporteFinanciero(productos, totalProductos, pedidosVacios, 0);
        controlador.ControladorReportes.generarReporte(reporte);
    }

    private void generarReporteProductosCaducar() {
        modelo.tipos.ReporteProductosCaducar reporte = new modelo.tipos.ReporteProductosCaducar(productos, totalProductos);
        controlador.ControladorReportes.generarReporte(reporte);
    }

    private void abrirBitacora() {
        vistaBitacora = new vista.VistaBitacora();
        configurarListenersBitacora();
        cargarBitacoraCompleta();
        vistaAdmin.ocultar();
        vistaBitacora.mostrar();
    }

    private void configurarListenersBitacora() {
        vistaBitacora.setCargarCompletaListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarBitacoraCompleta();
            }
        });

        vistaBitacora.setFiltrarFechaListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarBitacoraPorFecha();
            }
        });

        vistaBitacora.setFiltrarUsuarioListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarBitacoraPorUsuario();
            }
        });

        vistaBitacora.setExportarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportarBitacora();
            }
        });

        vistaBitacora.setRegresarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarAlMenuDesdeBitacora();
            }
        });
    }

    private void cargarBitacoraCompleta() {
        String[] registros = utilidades.Bitacora.cargarBitacoraCompleta();
        mostrarRegistrosEnVista(registros, "BITACORA COMPLETA DEL SISTEMA");
    }

    private void filtrarBitacoraPorFecha() {
        String fechaInicio = vistaBitacora.getFechaInicio();
        String fechaFin = vistaBitacora.getFechaFin();

        if (fechaInicio.isEmpty() || fechaFin.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese ambas fechas para filtrar");
            return;
        }

        String[] registros = utilidades.Bitacora.filtrarPorFecha(fechaInicio, fechaFin);
        mostrarRegistrosEnVista(registros, "BITACORA FILTRADA POR FECHA: " + fechaInicio + " - " + fechaFin);
    }

    private void filtrarBitacoraPorUsuario() {
        String tipoUsuario = vistaBitacora.getTipoUsuario();

        if (tipoUsuario.equals("TODOS")) {
            cargarBitacoraCompleta();
            return;
        }

        String[] registros = utilidades.Bitacora.filtrarPorUsuario(tipoUsuario);
        mostrarRegistrosEnVista(registros, "BITACORA FILTRADA POR USUARIO: " + tipoUsuario);
    }

    private void mostrarRegistrosEnVista(String[] registros, String titulo) {
        StringBuilder contenido = new StringBuilder("=== " + titulo + " ===\n\n");

        if (registros.length == 0) {
            contenido.append("No se encontraron registros.\n");
        } else {
            for (int i = 0; i < registros.length; i++) {
                contenido.append(registros[i]).append("\n");
            }
            contenido.append("\nTotal de registros: ").append(registros.length);
        }

        vistaBitacora.mostrarBitacora(contenido.toString());
    }

    private void exportarBitacora() {
        String[] registros = utilidades.Bitacora.cargarBitacoraCompleta();

        if (registros.length == 0) {
            JOptionPane.showMessageDialog(null, "No hay registros para exportar");
            return;
        }

        boolean exportado = utilidades.Bitacora.exportarBitacora("datos/bitacora_exportada.csv", registros);

        if (exportado) {
            JOptionPane.showMessageDialog(null, "Bitacora exportada exitosamente");
        } else {
            JOptionPane.showMessageDialog(null, "Error al exportar bitacora");
        }
    }

    private void regresarAlMenuDesdeBitacora() {
        vistaBitacora.ocultar();
        vistaAdmin.mostrar();
    }

    private void abrirGestionProductos() {
        System.out.println("Abriendo Gestion de Productos...");

        vistaProductos = new vista.VistaGestionProductos();
        configurarListenerProductos();
        actualizarListaProductos();

        vistaAdmin.ocultar();
        vistaProductos.mostrar();
    }

    private void configurarListenerProductos() {
        vistaProductos.setCrearListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearProducto();
            }
        });

        vistaProductos.setActualizarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProducto();
            }
        });

        vistaProductos.setEliminarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });

        vistaProductos.setVerDetalleListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verDetalleProducto();
            }
        });

        vistaProductos.setRegresarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarAlMenuProductos();
            }
        });
    }

    private void actualizarProducto() {
        String codigo = JOptionPane.showInputDialog("Ingrese codigo del producto a actualizar:");
        if (codigo == null || codigo.trim().isEmpty()) return;

        modelo.Producto producto = buscarProducto(codigo);
        if (producto == null) {
            utilidades.Validaciones.mostrarError("Producto no encontrado");
            return;
        }

        String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre:", producto.getNombre());
        if (nuevoNombre == null) return;

        String nuevoStockStr = JOptionPane.showInputDialog("Nuevo stock:", producto.getStock());
        if (nuevoStockStr == null) return;

        String nuevoAtributo = JOptionPane.showInputDialog("Nuevo atributo:", producto.getAtributoEspecifico());
        if (nuevoAtributo == null) return;

        int nuevoStock;
        try {
            nuevoStock = Integer.parseInt(nuevoStockStr);
            if (nuevoStock < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            utilidades.Validaciones.mostrarError("Stock debe ser numero positivo");
            return;
        }

        producto.setNombre(nuevoNombre);
        producto.setStock(nuevoStock);
        producto.setAtributoEspecifico(nuevoAtributo);

        actualizarListaProductos();
        utilidades.Validaciones.mostrarExito("Producto actualizado exitosamente");
        utilidades.Bitacora.registrarOperacion("ADMIN", "admin", "ACTUALIZAR_PRODUCTO", "EXITOSO", "Producto: " + codigo);
    }

    private void eliminarProducto() {
        String codigo = JOptionPane.showInputDialog("Ingrese codigo del producto a eliminar:");
        if (codigo == null || codigo.trim().isEmpty()) return;

        modelo.Producto producto = buscarProducto(codigo);
        if (producto == null) {
            utilidades.Validaciones.mostrarError("Producto no encontrado");
            return;
        }

        if (utilidades.Validaciones.confirmarEliminacion(producto.getNombre())) {
            for (int i = 0; i < totalProductos; i++) {
                if (productos[i] != null && productos[i].getCodigo().equals(codigo)) {
                    for (int j = i; j < totalProductos - 1; j++) {
                        productos[j] = productos[j + 1];
                    }
                    productos[totalProductos - 1] = null;
                    totalProductos--;
                    actualizarListaProductos();
                    utilidades.Validaciones.mostrarExito("Producto eliminado exitosamente");
                    utilidades.Bitacora.registrarOperacion("ADMIN", "admin", "ELIMINAR_PRODUCTO", "EXITOSO", "Producto: " + codigo);
                    return;
                }
            }
        }
    }

    private void verDetalleProducto() {
        String codigo = JOptionPane.showInputDialog("Ingrese codigo del producto para ver detalle:");
        if (codigo == null || codigo.trim().isEmpty()) return;

        modelo.Producto producto = buscarProducto(codigo);
        if (producto == null) {
            utilidades.Validaciones.mostrarError("Producto no encontrado");
            return;
        }

        String mensaje = "Detalle del Producto:\n" +
                "Codigo: " + producto.getCodigo() + "\n" +
                "Nombre: " + producto.getNombre() + "\n" +
                "Categoria: " + producto.getCategoria() + "\n" +
                "Stock: " + producto.getStock() + "\n" +
                "Atributo: " + producto.getAtributoEspecifico();

        JOptionPane.showMessageDialog(null, mensaje, "Detalle Producto", JOptionPane.INFORMATION_MESSAGE);
    }

    private void crearProducto() {
        String codigo = vistaProductos.getCodigo();
        String nombre = vistaProductos.getNombre();
        String categoria = vistaProductos.getCategoria();
        String stockStr = vistaProductos.getStock();
        String atributo = vistaProductos.getAtributo();

        if (codigo.isEmpty() || nombre.isEmpty() || stockStr.isEmpty() || atributo.isEmpty()) {
            utilidades.Validaciones.mostrarError("Todos los campos son Obligatorios");
            return;
        }

        int stock;
        try {
            stock = Integer.parseInt(stockStr);
            if (stock < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            utilidades.Validaciones.mostrarError("Stock debe ser un valor numerico positivo");
            return;
        }

        if (buscarProducto(codigo) != null) {
            utilidades.Validaciones.mostrarError("El codigo ya Existe");
            return;
        }

        modelo.Producto nuevoProducto = null;

        if (categoria.equals("Tecnologia")) {
            try {
                int meses = Integer.parseInt(atributo);
                nuevoProducto = new modelo.ProductoTecnologia(codigo, nombre, stock, meses);
            } catch (NumberFormatException e) {
                utilidades.Validaciones.mostrarError("Para Tecnologia, Ingrese Meses de Garantia (valor Numerico)");
                return;
            }
        } else if (categoria.equals("Alimento")) {
            nuevoProducto = new modelo.ProductoGeneral(codigo, nombre, stock, atributo);
        } else if (categoria.equals("General")) {
            nuevoProducto = new modelo.ProductoGeneral(codigo, nombre, stock, atributo);
        }

        if (nuevoProducto != null) {
            productos[totalProductos] = nuevoProducto;
            totalProductos++;

            vistaProductos.limpiarFormulario();
            actualizarListaProductos();
            utilidades.Validaciones.mostrarExito("Producto Creado Exitosamente");

            nuevoProducto.crear();
        }
    }

    private void actualizarListaProductos() {
        StringBuilder lista = new StringBuilder("--Productos Registrados--\n\n");

        for (int i = 0; i < totalProductos; i++) {
            if (productos[i] != null) {
                lista.append(productos[i].getInfoBasica()).append("\n");
            }
        }

        if (totalProductos == 0) {
            lista.append("No hay Productos registrados. Use el Formulario para Ingresar Productos");
        }

        vistaProductos.mostrarProductos(lista.toString());
    }

    private modelo.Producto buscarProducto(String codigo) {
        for (int i = 0; i < totalProductos; i++) {
            if (productos[i] != null && productos[i].getCodigo().equals(codigo)) {
                return productos[i];
            }
        }
        return null;
    }

    private void regresarAlMenuProductos() {
        vistaProductos.ocultar();
        vistaAdmin.mostrar();
    }

    private void abrirGestionVendedores() {
        System.out.println("Abriendo Operaciones de Vendedores...");
        vistaVendedores = new VistaGestionVendedores();
        configurarListenerVendedores();
        actualizarListaVendedores();
        vistaAdmin.ocultar();
        vistaVendedores.mostrar();
    }

    private void configurarListenerVendedores() {
        vistaVendedores.setCrearListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearVendedor();
            }
        });

        vistaVendedores.setRegresarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarMenu();
            }
        });

        vistaVendedores.setActualizarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirActualizarVendedor();
            }
        });

        vistaVendedores.setEliminarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarVendedor();
            }
        });
    }

    private void abrirActualizarVendedor() {
        vistaActualizarVendedor = new VistaActualizarVendedor();
        configurarListenerActualizarVendedor();
        vistaActualizarVendedor.mostar();
    }

    private void configurarListenerActualizarVendedor() {
        vistaActualizarVendedor.setBuscarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarVendedorParaActualizar();
            }
        });
        vistaActualizarVendedor.setActualizarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarVendedor();
            }
        });
        vistaActualizarVendedor.setCancelarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vistaActualizarVendedor.ocultar();
            }
        });
    }

    private void buscarVendedorParaActualizar() {
        String codigo = vistaActualizarVendedor.getCodigoBuscar();
        if (!utilidades.Validaciones.validarCampoVacio(codigo, "Codigo")) {
            return;
        }
        Usuario usuario = buscarUsuario(codigo);
        if (usuario != null && usuario instanceof Vendedor) {
            Vendedor vendedor = (Vendedor) usuario;
            vistaActualizarVendedor.setDatosVendedor(vendedor.getCodigo(),
                    vendedor.getNombre(),
                    vendedor.getGenero(),
                    vendedor.getContrasenia());
            utilidades.Validaciones.mostrarExito("Vendedor Encontrado, puede añadir o cambiar datos.");
        } else {
            utilidades.Validaciones.mostrarError("No se encontro un vendedor con ese codigo intente de nuevo.");
            vistaActualizarVendedor.limpiarFormulario();
        }
    }

    private void actualizarVendedor() {
        String codigoOriginal = vistaActualizarVendedor.getCodigoOriginal();
        String nuevoNombre = vistaActualizarVendedor.getNuevoNombre();
        String nuevoGenero = vistaActualizarVendedor.getNuevoGenero();
        String nuevaContrasenia = vistaActualizarVendedor.getNuevaContrasenia();

        if (!utilidades.Validaciones.validarCampoVacio(nuevoNombre, "nombre") ||
                !utilidades.Validaciones.validarCampoVacio(nuevoGenero, "genero") ||
                !utilidades.Validaciones.validarCampoVacio(nuevaContrasenia, "Contrasenia")) {
            return;
        }
        if (!utilidades.Validaciones.validarGenero(nuevoGenero)) {
            return;
        }
        if (!utilidades.Validaciones.validarContrasenia(nuevaContrasenia)) {
            return;
        }

        Usuario usuario = buscarUsuario(codigoOriginal);
        if (usuario != null && usuario instanceof Vendedor) {
            Vendedor vendedor = (Vendedor) usuario;
            vendedor.setNombre(nuevoNombre);
            vendedor.setGenero(nuevoGenero);
            vendedor.setContrasenia(nuevaContrasenia);
            utilidades.Validaciones.mostrarExito("Vendedor Actualizado Correctamente");
            vistaActualizarVendedor.ocultar();
            actualizarListaVendedores();
        }
    }

    private void eliminarVendedor() {
        String codigo = JOptionPane.showInputDialog("Ingrese el Codigo del vendedor que desea eliminar: ");
        if (codigo == null || codigo.trim().isEmpty()) {
            return;
        }
        codigo = codigo.trim();
        Usuario usuario = buscarUsuario(codigo);
        if (usuario != null && usuario instanceof Vendedor) {
            Vendedor vendedor = (Vendedor) usuario;
            if (utilidades.Validaciones.confirmarEliminacion(vendedor.getNombre())) {
                for (int i = 0; i < totalUsuarios; i++) {
                    if (usuarios[i] != null && usuarios[i].getCodigo().equals(codigo)) {
                        for (int j = i; j < totalUsuarios - 1; j++) {
                            usuarios[j] = usuarios[j + 1];
                        }
                        usuarios[totalUsuarios - 1] = null;
                        totalUsuarios--;
                        utilidades.Validaciones.mostrarExito("Vendedor eliminado Exitosamente");
                        actualizarListaVendedores();
                        return;
                    }
                }
            }
        } else {
            utilidades.Validaciones.mostrarError("No se encontro un vendedor con ese codigo.");
        }
    }

    private void crearVendedor() {
        String codigo = vistaVendedores.getCodigo();
        String nombre = vistaVendedores.getNombre();
        String genero = vistaVendedores.getGenero();
        String contrasenia = vistaVendedores.getContrasenia();
        System.out.println("Creando Vendedor..." + codigo);
        if (codigo.isEmpty() || nombre.isEmpty() || genero.isEmpty() || contrasenia.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Completar Todos los campos");
            return;
        }
        if (!genero.equals("M") && !genero.equals("F")) {
            JOptionPane.showMessageDialog(null, "Genero debe ser Masculino o Femenino");
            return;
        }
        if (buscarUsuario(codigo) != null) {
            JOptionPane.showMessageDialog(null, "El Codigo " + codigo + " ya Existe");
            return;
        }
        Vendedor nuevoVendedor = new Vendedor(codigo, nombre, genero, contrasenia);
        usuarios[totalUsuarios] = nuevoVendedor;
        totalUsuarios++;

        utilidades.Bitacora.registrarOperacion("ADMIN","admin","Crear_Vendedor", "Exitos","Vendedor:" + codigo);

        vistaVendedores.limpiarFormulario();
        actualizarListaVendedores();
        JOptionPane.showMessageDialog(null, "Vendedor Creado Exitosamente:\n" + "Codigo: " + codigo + "\n" + "Nombre: " + nombre);
        System.out.println("Vendedor Creado: " + codigo);
    }

    private void actualizarListaVendedores() {
        StringBuilder lista = new StringBuilder("---Vendedores Registrados----\n\n");
        boolean hayVendedores = false;
        for (int i = 0; i < totalUsuarios; i++) {
            if (usuarios[i] instanceof Vendedor) {
                Vendedor v = (Vendedor) usuarios[i];
                lista.append("Código: ").append(v.getCodigo())
                        .append(" | Nombre: ").append(v.getNombre())
                        .append(" | Género: ").append(v.getGenero())
                        .append("\n");
                hayVendedores = true;
            }
        }
        if (!hayVendedores) {
            lista.append("No hay Vendedores Registrados :c\n");
            lista.append("Para Crear Usuario de Vendedor llenar Formulario.");
        }
        vistaVendedores.mostrarVendedores(lista.toString());
    }

    private Usuario buscarUsuario(String codigo) {
        for (int i = 0; i < totalUsuarios; i++) {
            if (usuarios[i] != null && usuarios[i].getCodigo().equals(codigo)) {
                return usuarios[i];
            }
        }
        return null;
    }

    private void regresarMenu() {
        System.out.println("Regresando al Menu Principal...");
        vistaVendedores.ocultar();
        vistaAdmin.mostrar();
    }

    private void cerrarSesion() {
        System.out.println("Cerrando Sesion del Administrador..");
        guarDAtos();

        vistaAdmin.ocultar();
        controlador.ControladorLogin.getInstance().volverALogin();
    }

    private void cargarVendedoresDesdeCSV() {
        String rutaArchivo = utilidades.ArchivosCSV.seleccionarArchivoCSV();
        if (rutaArchivo == null) return;

        modelo.Vendedor[] vendedoresCargados = utilidades.ArchivosCSV.cargarVendedorDesdeCSV(rutaArchivo);

        int agregados = 0;
        for (int i = 0; i < vendedoresCargados.length; i++) {
            if (vendedoresCargados[i] != null && buscarUsuario(vendedoresCargados[i].getCodigo()) == null) {

                if (totalUsuarios < usuarios.length) {
                    usuarios[totalUsuarios] = vendedoresCargados[i];
                    totalUsuarios++;
                    agregados++;
                }
            }
        }
        actualizarListaVendedores();
        JOptionPane.showMessageDialog(null, "Se Agregaron " + agregados + " Vendedores desde CSV");
    }

    private void guarDAtos() {
        boolean usuariosGuardados = utilidades.Serializador.guardarUsuarios(usuarios,totalUsuarios);

        boolean productosGuardados = utilidades.Serializador.guardarProdcutos(productos, totalProductos);

        if (usuariosGuardados && productosGuardados) {
            System.out.println("Todos los Datos Guardados Exitosamenete.");
        }else {
            System.out.println("Algunos Datos No se Guardaron Exitosamente");
        }
    }

    private void cargarDatos() {
        modelo.Usuario[] usuariosCargados = utilidades.Serializador.cargarUsuario();
        if ( usuariosCargados != null) {
            for (int i = 0; i < usuariosCargados.length && totalUsuarios < usuarios.length; i++) {
                usuarios[totalUsuarios] = usuariosCargados[i];
                totalUsuarios++;
            }
            System.out.println("Usuarios"+ usuariosCargados.length + "Usuarios Cargados.");
        }

        modelo.Producto[] productosCargados = utilidades.Serializador.cargarProductos();
        if (productosCargados != null) {
            for (int i = 0 ; i < productosCargados.length && totalProductos < productos.length; i++) {
                productos[totalProductos] = productosCargados[i];
                totalProductos++;
            }
            System.out.println("Productos" + productosCargados.length + "Productos Cargados");
        }
    }
}




