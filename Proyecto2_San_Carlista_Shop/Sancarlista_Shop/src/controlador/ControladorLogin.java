package controlador;


import modelo.Usuario;
import vista.Login;
import vista.VistaAdmin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// no olvidar colocar los import ajaja

public class ControladorLogin {

    private Login vista;
    private Usuario[] usuarios;
    private int totalUsuarios;

    public ControladorLogin(Login vista) {
        this.vista = vista;
        this.usuarios = new Usuario[100];
        this.totalUsuarios = 0;


        configurarEventos();

        crearUsuarioAdmin();

    }

    private void crearUsuarioAdmin() {
        Usuario admin = new Usuario("admin","Administrador Principal","M","IPC1C");
        usuarios[totalUsuarios] = admin;
        totalUsuarios++;
        System.out.println("Usuario Creado Prueba ");
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
            return;

        }
        Usuario usuarioEncontrado = buscarUsucario(codigo);
        if (usuarioEncontrado != null) {
            if (usuarioEncontrado.verificarContraseia(contrasenia)) {
                vista.mensajeExito("Acceso Exitoso:" + usuarioEncontrado.getNombre());
                segundoRol(usuarioEncontrado);
            }else {
                vista.mensajesError("Contraseña Incorrecta");
                System.out.println("Contraseña Incorrecta: "+ codigo);
            }
        }else {
            vista.mensajesError("Usuario No Encontrado");
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
                JOptionPane.showMessageDialog(null, "Bienvenido Vendedor:" + usuario.getNombre());
                break;

            case "Cliente":
                JOptionPane.showMessageDialog(null,"Bievenido Cliente"+ usuario.getNombre());
                break;
        }
    }
    public void iniciar(){
        vista.mostrar();
    }
    }