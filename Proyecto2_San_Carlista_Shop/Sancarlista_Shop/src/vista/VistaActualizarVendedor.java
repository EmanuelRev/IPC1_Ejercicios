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

    }

}
