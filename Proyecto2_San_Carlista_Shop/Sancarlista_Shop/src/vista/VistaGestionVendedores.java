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

    // Botonesdelfrom
        bCrear = new JButton("Crear Vendedor");
        panelFormulario.add(bCrear);

        bCargarVentas = new JButton("Cargar Ventas");
        panelFormulario.add(bCargarVentas);

        //paneles
        JPanel panelLista = new JPanel(new BorderLayout());
        panelLista.setBorder(BorderFactory.createTitledBorder("Vendedores Registrados"));

        areaVendedores = new JTextArea();
        areaVendedores.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaVendedores);
        panelLista.add(scroll, BorderLayout.CENTER);

        //Botones de Gestion
        JPanel panelBotonesGestion = new JPanel(new GridLayout(1, 2));
        bActualizar = new JButton("Actualizar");
        bEliminar = new JButton("Eliminar");
        panelBotonesGestion.add(bActualizar);
        panelBotonesGestion.add(bEliminar);
        panelLista.add(panelBotonesGestion, BorderLayout.SOUTH);

        //Cobinacion de botones
        JPanel panelPrincipal = new JPanel(new GridLayout(1,2));
        panelPrincipal.add(panelFormulario);
        panelPrincipal.add(panelLista);
        ventana.add(panelPrincipal, BorderLayout.SOUTH);

        //PAra regresars
        bResgresar = new JButton("Regresar al Menu Principal");
        ventana.add(bResgresar, BorderLayout.SOUTH);
         ventana.setLocationRelativeTo(null);

    }
    public String getCodigo() {
        return txtCodigo.getText().trim();
    }
    public String getNombre(){
        return  txtNombre.getText().trim();
    }
    public String getGenero() {
        return  txtGenero.getText().trim().toUpperCase();

    }
    public String getContrasenia() {
        return  txtContrasenia.getText().trim();

    }
    //Limpiar las chivas
    public void limpiarFormulario() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtGenero.setText("");
        txtContrasenia.setText("");

    }
    public  void mostrarVendedores(String listaVendedores) {
      areaVendedores.setText(listaVendedores);
    }
    public void setCrearListener(ActionListener listener) {
        bCrear.addActionListener(listener);

    }
    public void setActualizarListener(ActionListener listener) {
        bActualizar.addActionListener(listener);
    }
    public void setEliminarListener(ActionListener listener){
        bEliminar.addActionListener(listener);
    }
    public void setCargarVenListener(ActionListener listener) {
        bCargarVentas.addActionListener(listener);
    }
    public void setRegresarListener(ActionListener listener) {
        bResgresar.addActionListener(listener);
    }
    public void mostrar(){
        ventana.setVisible(true);
    }
    public void ocultar() {
        ventana.setVisible(false);
    }
}
