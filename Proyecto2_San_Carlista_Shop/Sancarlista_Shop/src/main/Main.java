package main;

import vista.Login;
import controlador.ControladorLogin;
import utilidades.HilosSistema;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando sistema...");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                iniciarAplicacion();
            }
        });
    }

    private static void iniciarAplicacion() {
        System.out.println("Iniciando interfaz...");

        HilosSistema.iniciarHilos();

        try {
            Login vistaLogin = new Login();
            ControladorLogin controlador = new ControladorLogin(vistaLogin);
            controlador.iniciar();

            System.out.println("Interfaz iniciada");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}