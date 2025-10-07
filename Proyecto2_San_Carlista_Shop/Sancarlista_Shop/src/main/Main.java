package main;

import vista.Login;
import controlador.ControladorLogin;


public class Main {
    public static  void main(String[] args) {
        System.out.println("Iniciando ING Shop...");

        Login login = new Login();

        ControladorLogin controlador = new ControladorLogin(login);

        controlador.iniciar();

        System.out.println("Sistema Iniciado");

    }
}
