package utilidades;


// aqui van los hiloss xxxx

public class HilosSistema {
    private static boolean hilosActivos = false;

    private static  int sesionesActivas = 0;
    private static  int pedidosPendientes = 0;
    private static  int totalVentas = 0;

    //comienza los hilossss
    private  static Thread hilo1, hilo2, hilo3;

    public  static  void  iniciarHilos() {
        if (hilosActivos) return;;

        hilosActivos = true;
        System.out.println("Iniciando Hilos....");

        hilo1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (hilosActivos) {
                    try {
                        String mensaje = "[Hilo-1] Sessiones Activas:" + sesionesActivas+ "| Hora: " + obtenerHoraActual();
                        System.out.println(mensaje);
                        Thread.sleep(10000);
                    }catch (InterruptedException e) {
                        break;
                    }
                }
            }
        });
    }


}
