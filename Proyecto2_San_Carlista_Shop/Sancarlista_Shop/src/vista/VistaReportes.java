package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaReportes {
    private JFrame ventana;
    private JButton btnProductosMasVendidos, btnProductosMenosVendidos, btnInventario;
    private JButton btnVentasVendedor, btnClientesActivos, btnFinanciero, btnProductosCaducar;
    private JButton btnRegresar;

    public VistaReportes() {
        crearVentana();
    }

    private void crearVentana() {
        ventana = new JFrame("Generar Reportes");
        ventana.setSize(700, 500);
        ventana.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("SELECCIONE TIPO DE REPORTE", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        ventana.add(titulo, BorderLayout.NORTH);

        JPanel panelReportes = new JPanel(new GridLayout(4, 2, 10, 10));
        panelReportes.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        btnProductosMasVendidos = new JButton("Productos Mas Vendidos");
        btnProductosMenosVendidos = new JButton("Productos Menos Vendidos");
        btnInventario = new JButton("Reporte Inventario");
        btnVentasVendedor = new JButton("Ventas por Vendedor");
        btnClientesActivos = new JButton("Clientes Activos");
        btnFinanciero = new JButton("Reporte Financiero");
        btnProductosCaducar = new JButton("Productos por Caducar");

        panelReportes.add(btnProductosMasVendidos);
        panelReportes.add(btnProductosMenosVendidos);
        panelReportes.add(btnInventario);
        panelReportes.add(btnVentasVendedor);
        panelReportes.add(btnClientesActivos);
        panelReportes.add(btnFinanciero);
        panelReportes.add(btnProductosCaducar);

        ventana.add(panelReportes, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new FlowLayout());
        btnRegresar = new JButton("Regresar");
        panelInferior.add(btnRegresar);
        ventana.add(panelInferior, BorderLayout.SOUTH);

        ventana.setLocationRelativeTo(null);
    }

    public void setProductosMasVendidosListener(ActionListener listener) {
        btnProductosMasVendidos.addActionListener(listener);
    }

    public void setProductosMenosVendidosListener(ActionListener listener) {
        btnProductosMenosVendidos.addActionListener(listener);
    }

    public void setInventarioListener(ActionListener listener) {
        btnInventario.addActionListener(listener);
    }

    public void setVentasVendedorListener(ActionListener listener) {
        btnVentasVendedor.addActionListener(listener);
    }

    public void setClientesActivosListener(ActionListener listener) {
        btnClientesActivos.addActionListener(listener);
    }

    public void setFinancieroListener(ActionListener listener) {
        btnFinanciero.addActionListener(listener);
    }

    public void setProductosCaducarListener(ActionListener listener) {
        btnProductosCaducar.addActionListener(listener);
    }

    public void setRegresarListener(ActionListener listener) {
        btnRegresar.addActionListener(listener);
    }

    public void mostrar() { ventana.setVisible(true); }
    public void ocultar() { ventana.setVisible(false); }
}
