package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// no olvidar imports

public class VistaGestionVendedores {
    private JFrame ventana;
    private JTextField txtCodigo, txtNombre, txtGenero, txtContrasenia;
    private JButton bCrear, bActualizar, bEliminar, bCargarVentas, bResgresar;
    private JTextArea areaVendedores;

    public VistaGestionVendedores() {
        crearVentana();
    }
    private void crearVentana() {
        ventana.setSize(800, 500);
        ventana.setLayout(new BorderLayout());

        // T
        JLabel titulo = new JLabel("Gestion de Vendedores", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20 ));
        ventana.add(titulo, BorderLayout.NORTH);

        //PIZ
        JPanel panelFormulario = new JPanel(new GridLayout(6,2,5,5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Crear Vendedor"));

        // LLEnar el formulario datos os
        panelFormulario.add(new JLabel("Codigo:"));
        txtCodigo = new JTextField();
        panelFormulario.add(txtCodigo);

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Genero: "));
        txtGenero = new JTextField();
        panelFormulario.add(txtGenero);

        panelFormulario.add(new JLabel("Contrasenia"));
        txtContrasenia = new JTextField();
        panelFormulario.add(txtContrasenia);


    }
}
