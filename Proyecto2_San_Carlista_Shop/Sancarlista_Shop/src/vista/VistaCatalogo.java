package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


// todo lo que el lciente puede comprar



public class VistaCatalogo {
    private JFrame ventana;
    private JTextField txtCantidad;
    private JButton btnAgregarCarrito, btnVerCarrito, btnRegresar;
    private JTextArea areaProductos;
    private JComboBox<String> comboProductos;

    public VistaCatalogo() {
        crearVentana();
    }
// para la interfaz y botnotes



    private void crearVentana() {
        ventana = new JFrame("Catalogo de Productos");
        ventana.setSize(800, 500);
        ventana.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("CATALOGO DE PRODUCTOS DISPONIBLES", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        ventana.add(titulo, BorderLayout.NORTH);

        JPanel panelSuperior = new JPanel(new GridLayout(2, 2, 5, 5));
        panelSuperior.setBorder(BorderFactory.createTitledBorder("Seleccionar Producto"));

        panelSuperior.add(new JLabel("Producto:"));
        comboProductos = new JComboBox<>();
        panelSuperior.add(comboProductos);

        panelSuperior.add(new JLabel("Cantidad:"));
        txtCantidad = new JTextField();
        panelSuperior.add(txtCantidad);

        ventana.add(panelSuperior, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBorder(BorderFactory.createTitledBorder("Productos en Stock"));

        areaProductos = new JTextArea();
        areaProductos.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaProductos);
        panelCentral.add(scroll, BorderLayout.CENTER);

        ventana.add(panelCentral, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        btnAgregarCarrito = new JButton("Agregar al Carrito");
        btnVerCarrito = new JButton("Ver Carrito");
        btnRegresar = new JButton("Regresar");
        panelBotones.add(btnAgregarCarrito);
        panelBotones.add(btnVerCarrito);
        panelBotones.add(btnRegresar);

        ventana.add(panelBotones, BorderLayout.SOUTH);
        ventana.setLocationRelativeTo(null);
    }
// no olviadr mostara y ocultar los get


    public String getProductoSeleccionado() {
        return comboProductos.getSelectedItem() != null ? comboProductos.getSelectedItem().toString() : "";
    }
    public String getCantidad() { return txtCantidad.getText().trim(); }

    public void limpiarFormulario() {
        txtCantidad.setText("");
    }

    public void mostrarProductos(String lista) {
        areaProductos.setText(lista);
    }

    public void setProductosCombo(String[] productos) {
        comboProductos.removeAllItems();
        for (String producto : productos) {
            if (producto != null) comboProductos.addItem(producto);
        }
    }

    public void setAgregarCarritoListener(ActionListener listener) {
        btnAgregarCarrito.addActionListener(listener);
    }

    public void setVerCarritoListener(ActionListener listener) {
        btnVerCarrito.addActionListener(listener);
    }

    public void setRegresarListener(ActionListener listener) {
        btnRegresar.addActionListener(listener);
    }

    public void mostrar() { ventana.setVisible(true); }
    public void ocultar() { ventana.setVisible(false); }
}