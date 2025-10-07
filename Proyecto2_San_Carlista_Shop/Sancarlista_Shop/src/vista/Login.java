package vista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Login {
    private JFrame ventana;
    private JTextField campoCodigo;
    private JPasswordField contrasenia;
    private JButton botonLogin;
    private JLabel campoMensaje;

    public Login() {
        Ventana();
    }
    private void Ventana() {
        ventana = new JFrame("ING Shop - Acceso");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(400,300);
        ventana.setLayout(new GridLayout(4 ,1));

        JLabel titulo = new JLabel("ING Shop", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.BLUE);
        ventana.add(titulo);

        JPanel panelCodigo = new JPanel();
        panelCodigo.add(new JLabel("Codigo: "));
        campoCodigo = new JTextField(15);
        panelCodigo.add(campoCodigo);
        ventana.add(panelCodigo);

        JPanel panelContrasenia = new JPanel(new FlowLayout());
        panelContrasenia.add(new JLabel("Contraseña: "));
        contrasenia = new JPasswordField(15);
        panelContrasenia.add(contrasenia);
        ventana.add(panelContrasenia);

        botonLogin = new JButton("Iniciar Sesion");
        ventana.add(botonLogin);

        campoMensaje = new JLabel(" ",JLabel.CENTER);
        campoMensaje.setForeground(Color.RED);
        ventana.add(campoMensaje);

        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    public String getCodigo() {
        return campoCodigo.getText().trim();

    }

    public String getContrasenia() {
        return  new String(contrasenia.getPassword());

    }
    public void mensajesError(String mensaje) {
        campoMensaje.setForeground(Color.RED);
        campoMensaje.setText(mensaje);
    }
    public void mensajeExito(String mensaje){
        campoMensaje.setForeground(Color.GREEN);
        campoMensaje.setText(mensaje);

    }

    public void accesoListener(ActionListener listener){
        botonLogin.addActionListener(listener);
    }
    public void mostrar(){
        ventana.setVisible(true);
    }

    public void ocultar(){
        ventana.setVisible(false);


    }
    public void limpiarCampos(){
        campoCodigo.setText("");
        contrasenia.setText("");
        campoMensaje.setText("");
    }
}
