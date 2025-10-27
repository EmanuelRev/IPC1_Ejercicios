package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


// todo lo que el cliente ba poder hacer



public class VistaCliente {
    private JFrame ventana;
    private JButton btnCatalogo, btnCarrito, btnHistorial, btnCerrarSesion;
    private JLabel lblInfoCliente;

    public VistaCliente() {
        crearVentana();
    }
// aqui inicia la botonera}}


    private void crearVentana() {
        ventana = new JFrame("Modulo Cliente");
        ventana.setSize(600, 400);
        ventana.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("MODULO CLIENTE", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        ventana.add(titulo, BorderLayout.NORTH);

        lblInfoCliente = new JLabel("", JLabel.CENTER);
        ventana.add(lblInfoCliente, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new GridLayout(4, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        btnCatalogo = new JButton("Catalogo de Productos");
        btnCarrito = new JButton("Mi Carrito de Compras");
        btnHistorial = new JButton("Historial de Compras");
        btnCerrarSesion = new JButton("Cerrar Sesion");

        Font fuenteBotones = new Font("Arial", Font.BOLD, 16);
        btnCatalogo.setFont(fuenteBotones);
        btnCarrito.setFont(fuenteBotones);
        btnHistorial.setFont(fuenteBotones);
        btnCerrarSesion.setFont(fuenteBotones);

        panelBotones.add(btnCatalogo);
        panelBotones.add(btnCarrito);
        panelBotones.add(btnHistorial);
        panelBotones.add(btnCerrarSesion);

        ventana.add(panelBotones, BorderLayout.CENTER);
        ventana.setLocationRelativeTo(null);
    }


    // los set y  no olvidar mostar y ocultar al finalxxx



    public void setInfoCliente(String info) {
        lblInfoCliente.setText(info);
    }

    public void setCatalogoListener(ActionListener listener) {
        btnCatalogo.addActionListener(listener);
    }

    public void setCarritoListener(ActionListener listener) {
        btnCarrito.addActionListener(listener);
    }

    public void setHistorialListener(ActionListener listener) {
        btnHistorial.addActionListener(listener);
    }

    public void setCerrarSesionListener(ActionListener listener) {
        btnCerrarSesion.addActionListener(listener);
    }

    public void mostrar() { ventana.setVisible(true); }
    public void ocultar() { ventana.setVisible(false); }
}