package controlador;

import modelo.Usuario;
import modelo.Vendedor;
import vista.VistaActualizarVendedor;
import vista.VistaAdmin;
import vista.VistaGestionVendedores;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import utilidades.Validaciones;

//Actualizando metodos
public class ControladorAdmin {

    // aqui coloco los atributos nuevos
    private vista.VistaGestionProductos vistaProductos;
    private modelo.Producto[] productos;
    private int totalProductos;

    // aqui termino lo que estoy haciendo ajaja

    private VistaAdmin vistaAdmin;
    private VistaGestionVendedores vistaVendedores;
    private VistaActualizarVendedor vistaActualizarVendedor;
    private Usuario[] usuarios;
    private int totalUsuarios;

// constructor

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
    }

    // ------------------ PRODUCTOS ------------------

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
        vistaProductos.setRegresarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarAlMenuProductos();
            }
        });
    }

    private void crearProducto() {
        String codigo = vistaProductos.getCodigo();
        String nombre = vistaProductos.getNombre();
        String categoria = vistaProductos.getCategoria();
        String stockStr = vistaProductos.getStock();
        String atributo = vistaProductos.getAtributo();

        // validando todo
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

    // ------------------ VENDEDORES ------------------

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

        // actualizando metodos x_xx
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
        JOptionPane.showMessageDialog(null, "Sesion Cerrada-Datos Guardados :D");
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
    // guardando todos los datos

    private void guarDAtos() {
        boolean usuariosGuardados = utilidades.Serializador.guardarUsuarios(usuarios,totalUsuarios);

        boolean productosGuardados = utilidades.Serializador.guardarProdcutos(productos, totalProductos);

        if (usuariosGuardados && productosGuardados) {
            System.out.println("Todos los Datos Guardados Exitosamenete.");
        }else {
            System.out.println("Algunos Datos No se Guardaron Exitosamente");
        }
    }
    // para cargar datos ll

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
