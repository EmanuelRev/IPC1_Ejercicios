package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// los imports ajaajja
public class VistaGestionStock {
    private JFrame ventana;
    private JTextField txtCodigoProducto, txtCantidad;
    private JButton btnAgregarStock, btnCargarCSV, btnVerHistorial, btnRegresar;
    private JTextArea areaProductos;

    public VistaGestionStock() {
        crearVentana();
    }


    // todo sobre la ventana
    private void crearVentana() {
        ventana = new JFrame("Gestion de Stock");
        ventana.setSize(800, 500);
        ventana.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("GESTION DE INVENTARIO - STOCK", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        ventana.add(titulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Agregar Stock"));

        panelFormulario.add(new JLabel("Codigo Producto:"));
        txtCodigoProducto = new JTextField();
        panelFormulario.add(txtCodigoProducto);

        panelFormulario.add(new JLabel("Cantidad:"));
        txtCantidad = new JTextField();
        panelFormulario.add(txtCantidad);

        btnAgregarStock = new JButton("Agregar Stock");
        panelFormulario.add(btnAgregarStock);

        btnCargarCSV = new JButton("Cargar CSV");
        panelFormulario.add(btnCargarCSV);

        ventana.add(panelFormulario, BorderLayout.NORTH);

        JPanel panelLista = new JPanel(new BorderLayout());
        panelLista.setBorder(BorderFactory.createTitledBorder("Productos en Inventario"));

        areaProductos = new JTextArea();
        areaProductos.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaProductos);
        panelLista.add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        btnVerHistorial = new JButton("Ver Historial Ingresos");
        btnRegresar = new JButton("Regresar");
        panelBotones.add(btnVerHistorial);
        panelBotones.add(btnRegresar);
        panelLista.add(panelBotones, BorderLayout.SOUTH);

        ventana.add(panelLista, BorderLayout.CENTER);
        ventana.setLocationRelativeTo(null);
    }

    // para codigo de producto
    public String getCodigoProducto() { return txtCodigoProducto.getText().trim(); }
    public String getCantidad() { return txtCantidad.getText().trim(); }

    public void limpiarFormulario() {
        txtCodigoProducto.setText("");
        txtCantidad.setText("");
    }

    public void mostrarProductos(String lista) {
        areaProductos.setText(lista);
    }

    public void setAgregarStockListener(ActionListener listener) {
        btnAgregarStock.addActionListener(listener);
    }

    public void setCargarCSVListener(ActionListener listener) {
        btnCargarCSV.addActionListener(listener);
    }

    public void setVerHistorialListener(ActionListener listener) {
        btnVerHistorial.addActionListener(listener);
    }

    public void setRegresarListener(ActionListener listener) {
        btnRegresar.addActionListener(listener);
    }
// mostrar y ocultar
    public void mostrar() { ventana.setVisible(true); }
    public void ocultar() { ventana.setVisible(false); }
}