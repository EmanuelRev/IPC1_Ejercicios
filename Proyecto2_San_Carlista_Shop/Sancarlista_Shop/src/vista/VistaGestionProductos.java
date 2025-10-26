package vista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;




public class VistaGestionProductos {
    private JFrame ventana;
    private JTextField txtCodigo, txtNombre, txtStock, txtAtributo;
    private JComboBox<String> comboCategoria;
    private JButton btnCrear, btnActualizar, btnEliminar, btnCargarCSV, btnVerDetalle, btnRegresar;
    private JTextArea areaProductos;

    public VistaGestionProductos() {
        crearVentana();

    }
    private void crearVentana() {
        ventana = new JFrame("Gestion de Productos");
        ventana.setSize(900, 500);
        ventana.setLayout(new BorderLayout());


        // TOdo para la ventana
        JLabel titulo = new JLabel("Gestion de Productos", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        ventana.add(titulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridLayout(7,2,5,5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Crear Producto"));

        // Formulario
        panelFormulario.add(new JLabel("Codigo:"));
        txtCodigo = new JTextField();
        panelFormulario.add(txtCodigo);

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Categoria:"));
        comboCategoria = new JComboBox<>(new String[]{"Tecnologia", "Alimento", "General"});
        panelFormulario.add(comboCategoria);

        panelFormulario.add(new JLabel("Stock:"));
        txtStock = new JTextField();
        panelFormulario.add(txtStock);

        panelFormulario.add(new JLabel("Atributo Espesifico:"));
        txtAtributo = new JTextField();
        panelFormulario.add(txtAtributo);

        // Botoneraaaaaaaaaaaaaaaaa

        btnCrear = new JButton("Crear Producto");
        panelFormulario.add(btnCrear);

        btnCargarCSV = new JButton("Cargar CSV");
        panelFormulario.add(btnCargarCSV);

        JPanel panelLista = new JPanel(new BorderLayout());
        panelLista.setBorder(BorderFactory.createTitledBorder("Productos Registrados"));

        areaProductos = new JTextArea();
        areaProductos.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaProductos);
        panelLista.add(scroll, BorderLayout.CENTER);

        //Botonera Gestion
        JPanel panelBotonesGestion = new JPanel(new GridLayout(1, 3));
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnVerDetalle = new JButton("Ver Detalle");
        panelBotonesGestion.add(btnActualizar);
        panelBotonesGestion.add(btnEliminar);
        panelBotonesGestion.add(btnVerDetalle);
        panelLista.add(panelBotonesGestion, BorderLayout.SOUTH);

        JPanel panelPrincipal = new JPanel(new GridLayout(1, 2));
        panelPrincipal.add(panelFormulario);
        panelPrincipal.add(panelLista);
        ventana.add(panelPrincipal, BorderLayout.CENTER);

        btnRegresar = new JButton("Regresar al Menu Pricipal");
        ventana.add(btnRegresar, BorderLayout.SOUTH);

        ventana.setLocationRelativeTo(null);


    }

    //  Metodos para todos los datos que se colocan jijiji
    public  String getCodigo() {return txtCodigo.getText().trim(); }
    public String getNombre() {return txtNombre.getText().trim(); }
    public String getCategoria() {return (String) comboCategoria.getSelectedItem(); }
    public String getStock() {return txtStock.getText().trim(); }
    public String getAtributo() {return txtAtributo.getText().trim(); }

    // limpiar datos x:x

    public void limpiarFormulario() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtStock.setText("");
        txtAtributo.setText("");
        comboCategoria.setSelectedIndex(0);
    }

    // mostrar lo que ya hay :d

    public void mostrarProductos(String lista) {
        areaProductos.setText(lista);
    }
    public void setCrearListener(ActionListener listener) {
        btnCrear.addActionListener(listener);
    }
    public void setActualizarListener(ActionListener listener) {
        btnActualizar.addActionListener(listener);
    }
    public void setEliminarListener(ActionListener listener) {
        btnEliminar.addActionListener(listener);
    }
    public void setCargarCSVListener(ActionListener listener) {
        btnCargarCSV.addActionListener(listener);
    }
    public void setVerDetalleListener(ActionListener listener) {
        btnVerDetalle.addActionListener(listener);
    }
    public void setRegresarListener(ActionListener listener) {
        btnRegresar.addActionListener(listener);
    }
    public void mostrar() {ventana.setVisible(true);}
    public void ocultar() {ventana.setVisible(false);}

}
