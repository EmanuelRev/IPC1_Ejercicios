package controlador;

import modelo.Usuario;
import modelo.Vendedor;
import vista.VistaAdmin;
import vista.VistaGestionVendedores;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



//los import jajaja
public class ControladorAdmin {

    private VistaAdmin vistaAdmin;
    private VistaGestionVendedores vendedores;
    private Usuario[] usuarios;
    private  int totalUsuarios;

    public ControladorAdmin(VistaAdmin vistaAdmin, Usuario[] usuarios, int totalUsuarios) {
        this.vistaAdmin =vistaAdmin;
        this.usuarios = usuarios;
        this.totalUsuarios = totalUsuarios;

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
    }
    private void abrirGestionVendedores() {
        System.out.println("Abreindo Operaciones de Vendedores...");

    }

}
