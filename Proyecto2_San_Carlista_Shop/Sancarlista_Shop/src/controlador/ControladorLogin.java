package controlador;

import modelo.Usuario;
import vista.Login;
import vista.VistaAdmin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorLogin {

    private Login vista;
    private Usuario[] usuarios;
    private int totalUsuarios;
    private modelo.Producto[] productos;
    private int totalProductos;

    public ControladorLogin(Login vista) {
        this.vista = vista;
        this.usuarios = new Usuario[100];
        this.totalUsuarios = 0;
        this.productos = new modelo.Producto[100];
        this.totalProductos = 0;

        configurarEventos();
        crearUsuarioAdmin();
        cargarUsuariosGuardados();
        cargarProductosGuardados();
    }

    private void crearUsuarioAdmin() {
        Usuario admin = new Usuario("admin","Administrador Principal","M","IPC1C","ADMIN");

        usuarios[totalUsuarios] = admin;
        totalUsuarios++;
        System.out.println("Usuario Creado Prueba ");
        cargarUsuariosGuardados();
    }

    private void cargarUsuariosGuardados() {
        modelo.Usuario[] usuariosCargados = utilidades.Serializador.cargarUsuario();
        if (usuariosCargados != null) {
            for (int i =0; i < usuariosCargados.length && totalUsuarios < usuarios.length; i++) {
                if(!usuariosCargados[i].getCodigo().equals("admin")) {
                    usuarios[totalUsuarios] = usuariosCargados[i];
                    totalUsuarios++;
                }
            }
            System.out.println("Datos" + usuariosCargados.length + "Usuarios Cargados de la Base de Datos");
        }
    }

    private void cargarProductosGuardados() {
        modelo.Producto[] productosCargados = utilidades.Serializador.cargarProductos();
        if (productosCargados != null) {
            for (int i = 0; i < productosCargados.length && totalProductos < productos.length; i++) {
                productos[totalProductos] = productosCargados[i];
                totalProductos++;
            }
            System.out.println("Productos" + productosCargados.length + "Productos Cargados");
        }
    }

    private void configurarEventos() {
        vista.accesoListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Boton Creado Prueba");
                validarLogin();
            }
        });
    }

    public void validarLogin(){
        String codigo = vista.getCodigo();
        String contrasenia = vista.getContrasenia();
        System.out.println("Validando: "+codigo + "/"+ contrasenia);

        if (codigo.isEmpty() || contrasenia.isEmpty()){
            vista.mensajesError("Complete todos los campos");
            utilidades.Bitacora.registrarOperacion("SISTEMA", codigo, "INICIAR_SESION", "FALLIDO", "Campos vacios");
            return;
        }
        Usuario usuarioEncontrado = buscarUsucario(codigo);
        if (usuarioEncontrado != null) {
            if (usuarioEncontrado.verificarContrasenia(contrasenia)) {
                vista.mensajeExito("Acceso Exitoso:" + usuarioEncontrado.getNombre());
                utilidades.Bitacora.registrarOperacion(usuarioEncontrado.getTipo(), usuarioEncontrado.getCodigo(), "INICIAR_SESION", "EXITOSO", "Usuario autenticado");
                segundoRol(usuarioEncontrado);
            }else {
                vista.mensajesError("Contraseña Incorrecta");
                utilidades.Bitacora.registrarOperacion("SISTEMA", codigo, "INICIAR_SESION", "FALLIDO", "Contraseña incorrecta");
                System.out.println("Contraseña Incorrecta: "+ codigo);
            }
        }else {
            vista.mensajesError("Usuario No Encontrado");
            utilidades.Bitacora.registrarOperacion("SISTEMA", codigo, "INICIAR_SESION", "FALLIDO", "Usuario no existe");
            System.out.println("Usuario No Encontrado: "+ codigo);
        }
    }

    private Usuario buscarUsucario(String codigo) {
        for (int i = 0; i < totalUsuarios; i++) {
            if (usuarios[i] != null && usuarios[i].getCodigo().equals(codigo)) {
                return usuarios[i];
            }
        }
        return null;
    }

    private void  segundoRol(Usuario usuario) {
        vista.ocultar();
        switch (usuario.getTipo()){
            case "ADMIN":
                VistaAdmin vistaAdmin = new VistaAdmin();
                ControladorAdmin controladorAdmin = new ControladorAdmin(vistaAdmin, usuarios, totalUsuarios);
                vistaAdmin.mostrar();
                break;

            case  "VENDEDOR" :
                vista.VistaVendedor vistaVendedor = new vista.VistaVendedor();
                controlador.ControladorVendedor controladorVendedor = new controlador.ControladorVendedor(vistaVendedor, (modelo.Vendedor) usuario, usuarios, totalUsuarios, productos, totalProductos);
                vistaVendedor.mostrar();
                JOptionPane.showMessageDialog(null, "Bienvenido Vendedor:" + usuario.getNombre());
                break;

            case "CLIENTE":
                vista.VistaCliente vistaCliente = new vista.VistaCliente();
                controlador.ControladorCliente controladorCliente = new controlador.ControladorCliente(vistaCliente,(modelo.Cliente) usuario, productos, totalProductos);
                vistaCliente.mostrar();
                JOptionPane.showMessageDialog(null,"Bievenido Cliente"+ usuario.getNombre());
                break;
        }
    }

    public void volverALogin() {
        vista.mostrar();
        vista.limpiarCampos();
        utilidades.Bitacora.registrarOperacion("SISTEMA", "SISTEMA", "LOGOUT", "EXITOSO", "Usuario regreso al login");
    }

    public static ControladorLogin getInstance() {
        return new ControladorLogin(new Login());
    }

    public void iniciar(){
        vista.mostrar();
    }
}