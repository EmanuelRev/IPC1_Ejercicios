package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaBitacora {
    private JFrame ventana;
    private JTextArea areaBitacora;
    private JButton btnCargarCompleta, btnFiltrarFecha, btnFiltrarUsuario, btnExportar, btnRegresar;
    private JTextField txtFechaInicio, txtFechaFin;
    private JComboBox<String> comboUsuario;

    public VistaBitacora() {
        crearVentana();
    }

    private void crearVentana() {
        ventana = new JFrame("Bitacora del Sistema");
        ventana.setSize(800, 600);
        ventana.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("BITACORA DEL SISTEMA - REGISTRO DE OPERACIONES", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        ventana.add(titulo, BorderLayout.NORTH);

        JPanel panelFiltros = new JPanel(new GridLayout(2, 3, 5, 5));
        panelFiltros.setBorder(BorderFactory.createTitledBorder("Filtros de Busqueda"));

        panelFiltros.add(new JLabel("Fecha Inicio (dd/MM/yyyy):"));
        txtFechaInicio = new JTextField();
        panelFiltros.add(txtFechaInicio);

        panelFiltros.add(new JLabel("Fecha Fin (dd/MM/yyyy):"));
        txtFechaFin = new JTextField();
        panelFiltros.add(txtFechaFin);

        panelFiltros.add(new JLabel("Tipo Usuario:"));
        comboUsuario = new JComboBox<>(new String[]{"TODOS", "ADMIN", "VENDEDOR", "CLIENTE"});
        panelFiltros.add(comboUsuario);

        ventana.add(panelFiltros, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBorder(BorderFactory.createTitledBorder("Registros de Bitacora"));

        areaBitacora = new JTextArea();
        areaBitacora.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaBitacora);
        panelCentral.add(scroll, BorderLayout.CENTER);

        ventana.add(panelCentral, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());

        btnCargarCompleta = new JButton("Cargar Bitacora Completa");
        btnFiltrarFecha = new JButton("Filtrar por Fecha");
        btnFiltrarUsuario = new JButton("Filtrar por Usuario");
        btnExportar = new JButton("Exportar a CSV");
        btnRegresar = new JButton("Regresar");

        panelBotones.add(btnCargarCompleta);
        panelBotones.add(btnFiltrarFecha);
        panelBotones.add(btnFiltrarUsuario);
        panelBotones.add(btnExportar);
        panelBotones.add(btnRegresar);

        ventana.add(panelBotones, BorderLayout.SOUTH);

        ventana.setLocationRelativeTo(null);
    }

    public String getFechaInicio() { return txtFechaInicio.getText().trim(); }
    public String getFechaFin() { return txtFechaFin.getText().trim(); }
    public String getTipoUsuario() { return (String) comboUsuario.getSelectedItem(); }

    public void mostrarBitacora(String contenido) {
        areaBitacora.setText(contenido);
    }

    public void limpiarFiltros() {
        txtFechaInicio.setText("");
        txtFechaFin.setText("");
        comboUsuario.setSelectedIndex(0);
    }

    public void setCargarCompletaListener(ActionListener listener) {
        btnCargarCompleta.addActionListener(listener);
    }

    public void setFiltrarFechaListener(ActionListener listener) {
        btnFiltrarFecha.addActionListener(listener);
    }

    public void setFiltrarUsuarioListener(ActionListener listener) {
        btnFiltrarUsuario.addActionListener(listener);
    }

    public void setExportarListener(ActionListener listener) {
        btnExportar.addActionListener(listener);
    }

    public void setRegresarListener(ActionListener listener) {
        btnRegresar.addActionListener(listener);
    }

    public void mostrar() { ventana.setVisible(true); }
    public void ocultar() { ventana.setVisible(false); }
}