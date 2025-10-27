package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


// simepre los imports

// pedidos ya ajajaj
public class VistaPedidos {
    private JFrame ventana;
    private JButton btnConfirmar, btnRegresar;
    private JTextArea areaPedidos;

    public VistaPedidos() {
        crearVentana();
    }

    private void crearVentana() {
        ventana = new JFrame("Pedidos Pendientes");
        ventana.setSize(800, 500);
        ventana.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("PEDIDOS PENDIENTES DE CLIENTES", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        ventana.add(titulo, BorderLayout.NORTH);

        areaPedidos = new JTextArea();
        areaPedidos.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaPedidos);
        ventana.add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        btnConfirmar = new JButton("Confirmar Pedido Seleccionado");
        btnRegresar = new JButton("Regresar");
        panelBotones.add(btnConfirmar);
        panelBotones.add(btnRegresar);
        ventana.add(panelBotones, BorderLayout.SOUTH);

        ventana.setLocationRelativeTo(null);
    }

    public void mostrarPedidos(String lista) {
        areaPedidos.setText(lista);
    }

    public void setConfirmarListener(ActionListener listener) {
        btnConfirmar.addActionListener(listener);
    }

    public void setRegresarListener(ActionListener listener) {
        btnRegresar.addActionListener(listener);
    }
// qui van lo de mostrar cuando termine lo de stock


    public void mostrar() { ventana.setVisible(true); }
    public void ocultar() { ventana.setVisible(false); }
}