package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// colocar los import jajaa

public class VistaAdmin {
    private JFrame ventana;
    private JButton bGestionVendedores,bGestionProductos, bReportes,bCerrarSesion;

    public VistaAdmin() {
        crearVentana();
        // VVV
    }
    public void crearVentana() {
        ventana = new JFrame("Modulo Administrador - ING Shop ");
        ventana.setSize(600, 400);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new BorderLayout());

        //TTTTT
        JLabel titulo = new JLabel("Modulo Administrador", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(new Color(0, 100, 0));
        ventana.add(titulo, BorderLayout.NORTH);

        // PBP222
        JPanel panelBotones = new JPanel(new GridLayout(3,1 ,10,10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        bGestionVendedores = new JButton("Gestion de Vendedores");
        bGestionProductos = new JButton("Gestion de Productos");
        bReportes = new JButton("Generar Reportes");

        // ConfigB
        Font fuenteBotones = new Font("Arial", Font.BOLD, 16);
        bGestionVendedores.setFont(fuenteBotones);
        bGestionProductos.setFont(fuenteBotones);
        bReportes.setFont(fuenteBotones);

        // B panel XD

        panelBotones.add(bGestionVendedores);
        panelBotones.add(bGestionProductos);
        panelBotones.add(bReportes);

        ventana.add(panelBotones, BorderLayout.CENTER);

        //BCSesion

        bCerrarSesion = new JButton("Cerrar Sesion");
        ventana.add(bCerrarSesion, BorderLayout.SOUTH);

        ventana.setLocationRelativeTo(null);


    }
    // m
    public void setGestionVendedoresL(ActionListener listener) {
        bGestionVendedores.addActionListener(listener);
    }
    public void setGestionProductosL(ActionListener listener){
        bGestionProductos.addActionListener(listener);
    }
    public void setReportesL(ActionListener listener){
        bReportes.addActionListener(listener);

    }
    public void setCerrarSesionL(ActionListener listener){
        bCerrarSesion.addActionListener(listener);
    }
    // Enseña y esconde
    public void mostrar(){
        ventana.setVisible(true);
    }
    public void ocultar() {
        ventana.setVisible(false);
    }

}
