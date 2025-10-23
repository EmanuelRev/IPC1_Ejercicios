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
        // hilo 222222 ssss
        hilo2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (hilosActivos) {
                    try {
                        String mensaje = "[Hilo-2] Pedidos Pendientes:" +pedidosPendientes + "| Hora:" + obtenerHoraActual();
                        System.out.println(mensaje);
                        Thread.sleep(8000);
                    }catch (InterruptedException e ) {
                        break;
                    }
                }
            }
        });
        // hilo 3333333
        hilo3 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (hilosActivos) {
                    try {
                        String mensaje = "[Hilo- 3] Ventas hoy:" + totalVentas + "| Hora:" + obtenerHoraActual();
                        System.out.println(mensaje);
                        Thread.sleep(15000);
                    }catch (InterruptedException e) {
                        break;
                    }
                }
            }
        });
        // PAra iniciar todo ;D
        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
    // detieniendo operaciones para no saturar jaaja
    public static void detenerHilos() {
        hilosActivos = false;
        if (hilo1 != null) hilo1.interrupt();
        if (hilo2 != null) hilo2.interrupt();
        if (hilo3 != null) hilo3.interrupt();
        System.out.println("Procesos Detenidos x_x");
    }

    public static void setHilosActivos(int cantidad) {
        sesionesActivas = cantidad;
    }
    public  static  void setPedidosPendientes(int cantidad) {
        pedidosPendientes = cantidad;
    }
    public  static void setTotalVentas(int cantidad) {
        totalVentas = cantidad;
    }

    private static String obtenerHoraActual() {
        return java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    }


