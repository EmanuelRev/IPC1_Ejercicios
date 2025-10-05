package vista;
import javax.swing.*;
import java.awt.*;


public class Login {
    private JFrame ventana;
    private JTextField campoCodigo;
    private JPasswordField contrasenia;
    private JButton botonLogin;

    public Login() {
        Ventana();
    }public void Ventana() {
        ventana = new JFrame("ING Shop - Acceso");
        ventana.setSize(400,300);
        ventana.setLayout(new GridLayout(4 ,1));

        JLabel titulo = new JLabel("ING Shop", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        ventana.add(titulo);

        JPanel panelCodigo = new JPanel();
        panelCodigo.add(new JLabel("Codigo: "));
        campoCodigo = new JTextField(15);
        panelCodigo.add(campoCodigo);
        ventana.add(panelCodigo);

        botonLogin = new JButton("Iniciar Session");
        ventana.add(botonLogin);

        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    public String getCodigo() {
        return campoCodigo.getText();

    }

    public String getContrasenia() {
        return  new String(contrasenia.getPassword());

    }
    public void setActionListener(java.awt.event.ActionListener listener) {
        botonLogin.addActionListener(listener);
    }

}
