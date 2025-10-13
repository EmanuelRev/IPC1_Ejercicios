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
    private VistaGestionVendedores vistaVendedores;
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
            JOptionPane.showMessageDialog(null, "El Codigo" + codigo+ "Ya Existe");
            return;
        }
        Vendedor nuevoVendedor = new Vendedor(codigo, nombre, genero, contrasenia);
        usuarios[totalUsuarios] = nuevoVendedor;
        totalUsuarios++;

        vistaVendedores.limpiarFormulario();
        actualizarListaVendedores();

        JOptionPane.showMessageDialog(null, "Vendedor Creado Exitosamente:\n"+"Codigo: "+codigo+ "\n" + "Nombre: " + nombre);

        System.out.println("Vendedor Creado: " + codigo);
        }

        private void actualizarListaVendedores() {
        StringBuilder lista = new StringBuilder("---Vendedores Regsitrados----\n\n");
        boolean hayVendedores = false;

        if (!hayVendedores) {
            lista.append("No hay Vendedores Registrados :c\n");
            lista.append("Para Crear Usuario de Vendedor llenar Formulario.");
        }
        vistaVendedores.mostrarVendedores(lista.toString());

        }

        private void buscarUsuario(String codigo) {
        for (int i = 0; i < totalUsuarios; i++){
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
        vistaAdmin.ocultar();
        JOptionPane.showMessageDialog(null,"Sesion Cerrada Exitosamente");
        }

    }


