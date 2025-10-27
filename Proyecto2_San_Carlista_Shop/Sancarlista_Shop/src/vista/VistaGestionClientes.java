package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// imports


public class VistaGestionClientes {
    private JFrame ventana;
    private JTextField txtCodigo, txtNombre, txtGenero, txtCumpleanos, txtContrasenia;
    private JButton btnCrear, btnActualizar, btnEliminar, btnCargarCSV, btnRegresar;
    private JTextArea areaClientes;

    public VistaGestionClientes() {
        crearVentana();
    }

    // ventana con todos los botontes x_x


    private void crearVentana() {
        ventana = new JFrame("Gestion de Clientes");
        ventana.setSize(800, 500);
        ventana.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("GESTION DE CLIENTES", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        ventana.add(titulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Crear Cliente"));

        panelFormulario.add(new JLabel("Codigo:"));
        txtCodigo = new JTextField();
        panelFormulario.add(txtCodigo);

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Genero (M/F):"));
        txtGenero = new JTextField();
        panelFormulario.add(txtGenero);

        panelFormulario.add(new JLabel("Cumpleanos (dd/MM/yyyy):"));
        txtCumpleanos = new JTextField();
        panelFormulario.add(txtCumpleanos);

        panelFormulario.add(new JLabel("Contraseña:"));
        txtContrasenia = new JTextField();
        panelFormulario.add(txtContrasenia);

        btnCrear = new JButton("Crear Cliente");
        panelFormulario.add(btnCrear);

        btnCargarCSV = new JButton("Cargar CSV");
        panelFormulario.add(btnCargarCSV);

        ventana.add(panelFormulario, BorderLayout.NORTH);

        JPanel panelLista = new JPanel(new BorderLayout());
        panelLista.setBorder(BorderFactory.createTitledBorder("Clientes Registrados"));

        areaClientes = new JTextArea();
        areaClientes.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaClientes);
        panelLista.add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new GridLayout(1, 2));
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelLista.add(panelBotones, BorderLayout.SOUTH);

        ventana.add(panelLista, BorderLayout.CENTER);

        btnRegresar = new JButton("Regresar al Menu Vendedor");
        ventana.add(btnRegresar, BorderLayout.SOUTH);
        ventana.setLocationRelativeTo(null);
    }
// poner los variables que faltan geero cumple


    public String getCodigo() { return txtCodigo.getText().trim(); }
    public String getNombre() { return txtNombre.getText().trim(); }
    public String getGenero() { return txtGenero.getText().trim().toUpperCase(); }
    public String getCumpleanos() { return txtCumpleanos.getText().trim(); }
    public String getContrasenia() { return txtContrasenia.getText().trim(); }

    public void limpiarFormulario() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtGenero.setText("");
        txtCumpleanos.setText("");
        txtContrasenia.setText("");
    }

    public void mostrarClientes(String lista) {
        areaClientes.setText(lista);
    }

    public void setCrearListener(ActionListener listener) {
        btnCrear.addActionListener(listener);
    }

    public void setActualizarListener(ActionListener listener) {
        btnActualizar.addActionListener(listener);
    }

    public void setEliminarListener(ActionListener listener) {
        btnEliminar.addActionListener(listener);
    }

    public void setCargarCSVListener(ActionListener listener) {
        btnCargarCSV.addActionListener(listener);
    }

    public void setRegresarListener(ActionListener listener) {
        btnRegresar.addActionListener(listener);
    }
// para mostrar la ventana no olvidar colocar




    public void mostrar() { ventana.setVisible(true); }
    public void ocultar() { ventana.setVisible(false); }
}