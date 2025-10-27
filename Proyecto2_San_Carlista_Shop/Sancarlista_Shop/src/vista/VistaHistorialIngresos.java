package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaHistorialIngresos {
    private JFrame ventana;
    private JButton btnGenerarCSV, btnRegresar;
    private JTextArea areaHistorial;

    public VistaHistorialIngresos() {
        crearVentana();
    }

    private void crearVentana() {
        ventana = new JFrame("Historial de Ingresos");
        ventana.setSize(700, 500);
        ventana.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("HISTORIAL DE INGRESOS DE STOCK", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        ventana.add(titulo, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBorder(BorderFactory.createTitledBorder("Registros de Ingresos"));

        areaHistorial = new JTextArea();
        areaHistorial.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaHistorial);
        panelCentral.add(scroll, BorderLayout.CENTER);

        ventana.add(panelCentral, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        btnGenerarCSV = new JButton("Generar Reporte CSV");
        btnRegresar = new JButton("Regresar");
        panelBotones.add(btnGenerarCSV);
        panelBotones.add(btnRegresar);

        ventana.add(panelBotones, BorderLayout.SOUTH);
        ventana.setLocationRelativeTo(null);
    }

    public void mostrarHistorial(String contenido) {
        areaHistorial.setText(contenido);
    }

    public void setGenerarCSVListener(ActionListener listener) {
        btnGenerarCSV.addActionListener(listener);
    }

    public void setRegresarListener(ActionListener listener) {
        btnRegresar.addActionListener(listener);
    }

    public void mostrar() { ventana.setVisible(true); }
    public void ocultar() { ventana.setVisible(false); }
}