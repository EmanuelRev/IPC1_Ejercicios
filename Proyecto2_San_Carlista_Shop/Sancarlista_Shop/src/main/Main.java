package main;

import vista.Login;
import controlador.ControladorLogin;
import utilidades.HilosSistema;



public class Main {

    public static controlador.ControladorLogin controladorGlobal;


    public static  void main(String[] args) {
        System.out.println("Iniciando ING Shop...");

        // aqui inicia todooo
        HilosSistema.iniciarHilos();

        vista.Login vistaLogin = new vista.Login();
        controladorGlobal = new controlador.ControladorLogin(vistaLogin);
        controladorGlobal.iniciar();

        System.out.println("Sistema Iniciado Correctamente");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Cerrando ING Shop...");
            HilosSistema.detenerHilos();

            if (controladorGlobal != null) {
                System.out.println("Guardando Datos...");
            }

      }));

    }
}
