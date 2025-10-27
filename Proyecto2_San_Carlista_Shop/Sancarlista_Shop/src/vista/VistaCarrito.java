package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


// lo que se ira en el carrito agregado


public class VistaCarrito {
    private JFrame ventana;
    private JTextField txtCodigoProducto, txtNuevaCantidad;
    private JButton btnActualizarCantidad, btnEliminarProducto, btnRealizarPedido, btnRegresar;
    private JTextArea areaCarrito;

    public VistaCarrito() {
        crearVentana();
    }

    private void crearVentana() {
        ventana = new JFrame("Carrito de Compras");
        ventana.setSize(800, 500);
        ventana.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("MI CARRITO DE COMPRAS", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        ventana.add(titulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridLayout(2, 3, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Gestionar Carrito"));

        panelFormulario.add(new JLabel("Codigo Producto:"));
        txtCodigoProducto = new JTextField();
        panelFormulario.add(txtCodigoProducto);

        panelFormulario.add(new JLabel("Nueva Cantidad:"));
        txtNuevaCantidad = new JTextField();
        panelFormulario.add(txtNuevaCantidad);

        btnActualizarCantidad = new JButton("Actualizar Cantidad");
        panelFormulario.add(btnActualizarCantidad);

        btnEliminarProducto = new JButton("Eliminar Producto");
        panelFormulario.add(btnEliminarProducto);

        ventana.add(panelFormulario, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBorder(BorderFactory.createTitledBorder("Productos en Carrito"));

        areaCarrito = new JTextArea();
        areaCarrito.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaCarrito);
        panelCentral.add(scroll, BorderLayout.CENTER);

        ventana.add(panelCentral, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        btnRealizarPedido = new JButton("Realizar Pedido");
        btnRegresar = new JButton("Regresar");
        panelBotones.add(btnRealizarPedido);
        panelBotones.add(btnRegresar);

        ventana.add(panelBotones, BorderLayout.SOUTH);
        ventana.setLocationRelativeTo(null);
    }

    public String getCodigoProducto() { return txtCodigoProducto.getText().trim(); }
    public String getNuevaCantidad() { return txtNuevaCantidad.getText().trim(); }

    public void limpiarFormulario() {
        txtCodigoProducto.setText("");
        txtNuevaCantidad.setText("");
    }

    public void mostrarCarrito(String contenido) {
        areaCarrito.setText(contenido);
    }

    public void setActualizarCantidadListener(ActionListener listener) {
        btnActualizarCantidad.addActionListener(listener);
    }

    public void setEliminarProductoListener(ActionListener listener) {
        btnEliminarProducto.addActionListener(listener);
    }

    public void setRealizarPedidoListener(ActionListener listener) {
        btnRealizarPedido.addActionListener(listener);
    }

    public void setRegresarListener(ActionListener listener) {
        btnRegresar.addActionListener(listener);
    }

    public void mostrar() { ventana.setVisible(true); }
    public void ocultar() { ventana.setVisible(false); }
}