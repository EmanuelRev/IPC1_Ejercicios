package controlador;
import modelo.Usuario;
import vista.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// no olvidar colocar los import ajaja

public class ControladorLogin {

    private Login vista;
    private Usuario[] usuarios;

    public ControladorLogin(Login vista) {
        this.vista = vista;
        this.usuarios = new Usuario[10];

        configurarEventos();

        crearUsuarioAdmin();


    }
    private void configurarEventos() {
        vista.setActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarCredenciales();
            }
        });

    }
    private void crearUsuarioAdmin() {
        Usuario admin = new Usuario("admin", "Administrador", "IPC1C","ADMIN");
        usuarios[0] = admin;
    }

    private void verificarCredenciales() {
        String codigo = vista.getCodigo();
        String contrasenia = vista.getContrasenia();

        if (codigo.isEmpty() || contrasenia.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Complete Todos los Campos");
            return;
        }
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i] != null && usuarios[i].getCodigo().equals(codigo)) {

                if (usuarios[i].verificarContraseia(contrasenia)) {
                    JOptionPane.showMessageDialog(null, "Acceso Exitoso!");
                    return;
                }
            }

        }

        JOptionPane.showMessageDialog(null, "Usuario o Contraseña Incorrectos");

    }
}
