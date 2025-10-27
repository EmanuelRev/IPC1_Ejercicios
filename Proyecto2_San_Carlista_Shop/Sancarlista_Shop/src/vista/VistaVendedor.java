package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaVendedor {
    private JFrame ventana;
    private JButton btnProductos, btnClientes, btnPedidos, btnHistorialIngresos, btnCerrarSesion;
    private JLabel lblInfoVendedor;

    public VistaVendedor() {
        crearVentana();
    }

    private void crearVentana() {
        ventana = new JFrame("Modulo Vendedor");
        ventana.setSize(600, 500); // ← Aumenté el tamaño
        ventana.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("MODULO VENDEDOR", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        ventana.add(titulo, BorderLayout.NORTH);

        lblInfoVendedor = new JLabel("", JLabel.CENTER);
        ventana.add(lblInfoVendedor, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new GridLayout(5, 1, 10, 10)); // ← Cambié a 5,1
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        btnProductos = new JButton("Gestion de Stock");
        btnClientes = new JButton("Gestion de Clientes");
        btnPedidos = new JButton("Pedidos Pendientes");
        btnHistorialIngresos = new JButton("Historial de Ingresos"); // ← BOTÓN NUEVO
        btnCerrarSesion = new JButton("Cerrar Sesion");

        Font fuenteBotones = new Font("Arial", Font.BOLD, 16);
        btnProductos.setFont(fuenteBotones);
        btnClientes.setFont(fuenteBotones);
        btnPedidos.setFont(fuenteBotones);
        btnHistorialIngresos.setFont(fuenteBotones); // ← FUENTE NUEVA
        btnCerrarSesion.setFont(fuenteBotones);

        panelBotones.add(btnProductos);
        panelBotones.add(btnClientes);
        panelBotones.add(btnPedidos);
        panelBotones.add(btnHistorialIngresos); // ← AGREGADO AL PANEL
        panelBotones.add(btnCerrarSesion);

        ventana.add(panelBotones, BorderLayout.CENTER);
        ventana.setLocationRelativeTo(null);
    }

    public void setInfoVendedor(String info) {
        lblInfoVendedor.setText(info);
    }

    public void setProductosListener(ActionListener listener) {
        btnProductos.addActionListener(listener);
    }

    public void setClientesListener(ActionListener listener) {
        btnClientes.addActionListener(listener);
    }

    public void setPedidosListener(ActionListener listener) {
        btnPedidos.addActionListener(listener);
    }

    public void setHistorialIngresosListener(ActionListener listener) {
        btnHistorialIngresos.addActionListener(listener);
    }

    public void setCerrarSesionListener(ActionListener listener) {
        btnCerrarSesion.addActionListener(listener);
    }

    public void mostrar() { ventana.setVisible(true); }
    public void ocultar() { ventana.setVisible(false); }
}