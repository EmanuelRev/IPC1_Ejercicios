package vista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


//imports

public class VistaActualizarVendedor {
    private JFrame ventana;
    private JTextField txtCodigoBuscar, txtNombre, txtGenero, txtContrasenia;
    private JButton btnBuscar, btnActualizar, btnCancelar;
    private JLabel lblInfoVendedor;
    private String codigoOriginal;

    public VistaActualizarVendedor() {
        crearVentana();
    }
    private void crearVentana() {
        ventana = new JFrame("Actualizar Vendedor");
        ventana.setSize(500, 400);
        ventana.setLayout(new BorderLayout());

        //Titulos de la ventana cx
        JLabel titulo = new JLabel("Actualizar Informacion De Vendedor", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        ventana.add(titulo, BorderLayout.NORTH);

        //Panel
        JPanel panelFormulario = new JPanel(new GridLayout(6,2,10,10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        //Busqueda pro medio de Cudigo x_x
        panelFormulario.add(new JLabel("Codigo del Vendedor: "));
        JPanel panelBusqueda = new JPanel(new BorderLayout());
        txtCodigoBuscar = new JTextField();
        btnBuscar = new JButton("Buscar:");
        panelBusqueda.add(txtCodigoBuscar, BorderLayout.CENTER);
        panelBusqueda.add(btnBuscar, BorderLayout.EAST);
        panelFormulario.add(panelBusqueda);

        // Infor del los vendedores
        panelFormulario.add(new JLabel("Informacion Encontrada:"));
        lblInfoVendedor = new JLabel("Ingrese un codigo y presione para Buscar");
        lblInfoVendedor.setForeground(Color.BLUE);
        panelFormulario.add(lblInfoVendedor);

        panelFormulario.add(new JLabel("Nuevo Nombre:"));
        txtNombre = new JTextField();
        txtNombre.setEnabled(false);
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Nuevo Genero M/F "));
        txtGenero = new JTextField();
        txtGenero.setEnabled(false);
        panelFormulario.add(txtGenero);

        panelFormulario.add(new JLabel("Nueva Contraseña"));
        txtContrasenia = new JTextField();
        txtContrasenia.setEnabled(false);
        panelFormulario.add(txtContrasenia);

        ventana.add(panelFormulario, BorderLayout.CENTER);

        //PAneles para las partes de abajo

        JPanel panelBotones = new JPanel(new FlowLayout());
        btnActualizar = new JButton("Actualizar");
        btnActualizar.setEnabled(false);
        btnCancelar = new JButton("Cancelar");

        panelBotones.add(btnActualizar);
        panelBotones.add(btnCancelar);
        ventana.add(panelBotones, BorderLayout.SOUTH);
        ventana.setLocationRelativeTo(null);

    }

    //PAra Obteneer todos los datos

    public String getCodigoBuscar() {
        return txtCodigoBuscar.getText().trim();
    }
    public String getNuevoNombre() {
        return  txtNombre.getText().trim();
    }
    public String getNuevoGenero() {
        return txtGenero.getText().trim();
    }
    public String getNuevaContrasenia() {
        return txtContrasenia.getText().trim();
    }
    public String getCodigoOriginal() {
        return codigoOriginal;
    }
//configuraciones ventana

    public void setDatosVendedor(String codigo, String nombre, String genero, String contrasenia) {
        this.codigoOriginal = codigo;
        lblInfoVendedor.setText("Editando: " + codigo + " - "+ nombre);

        txtNombre.setText(nombre);
        txtGenero.setText(genero);
        txtContrasenia.setText(contrasenia);

        // Campos de edicion del que vende
        txtNombre.setEnabled(true);
        txtGenero.setEnabled(true);
        txtContrasenia.setEnabled(true);
        btnActualizar.setEnabled(true);

    }

    public void limpiarFormulario() {
        txtCodigoBuscar.setText("");
        txtNombre.setText("");
        txtGenero.setText("");
        txtContrasenia.setText("");
        lblInfoVendedor.setText("Ingrese un codigo y presione Buscar");

        txtNombre.setEnabled(false);
        txtGenero.setEnabled(false);
        txtContrasenia.setEnabled(false);
        btnActualizar.setEnabled(false);

        codigoOriginal = null;

    }
    //Mensajes de informacion xxx

    public void mostrarMensajes(String mensaje, boolean esError) {
        if (esError) {
            lblInfoVendedor.setForeground(Color.RED);
        } else {
            lblInfoVendedor.setForeground(Color.BLUE);
        }
        lblInfoVendedor.setText(mensaje);
    }
    public void setBuscarListener(ActionListener listener) {
        btnBuscar.addActionListener(listener);
    }
    public void setActualizarListener(ActionListener listener) {
        btnActualizar.addActionListener(listener);
    }
    public void setCancelarListener(ActionListener listener) {
        btnCancelar.addActionListener(listener);

    }
    // Mostarar y ocultar x:xxxx
    public void mostar() {
        ventana.setVisible(true);
    }

    public void ocultar() {
        ventana.setVisible(false);
    }

}



