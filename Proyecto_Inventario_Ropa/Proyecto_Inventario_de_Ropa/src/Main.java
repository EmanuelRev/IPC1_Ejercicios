import java.util.Scanner;
public class Main {
    Scanner scanner = new Scanner(System.in);
    boolean salir = false;
    int opcion;

    Producto[] inventario =new Producto[100];
    int contadorProductos = 0;

     public static void main(String[] args) {

         Scanner scanner = new Scanner(System.in);
         boolean salir = false;

         int opcion ;
         while (!salir) {
         //Solo Menu
             System.out.println("----Sistema de Inventario de Ropa TK----");
             System.out.println("---Menu Principal---");
             System.out.println("SELECCIONE UNA OPCION PARA INICIAR");
             System.out.println("1. Agregar Producto");
             System.out.println("2. Buscar Producto");
             System.out.println("3. Eliminar Producto");
             System.out.println("4. Registrar Venta");
             System.out.println("5. Generar Reportes");
             System.out.println("6. Datos del Estudiante");
             System.out.println("7. Bitacora");
             System.out.println("8. Salir");


             opcion = scanner.nextInt();
             scanner.nextLine();
             switch (opcion) {
                 case 1:
                     System.out.println("n-> Opcion: Agregar Producto");
                             //
                     break;
                 case 2:
                     System.out.println("n-> Opcion: Buscar Producto");
                     //
                     break;
                 case 3:
                     System.out.println("n-> Opcion: Eliminar Producto");
                     //
                     break;
                 case 4:
                     System.out.println("n-> Opcion: Registrar Venta");
                     //
                     break;
                 case 5:
                     System.out.println("n-> Opcion: Generar Reportes");
                     //
                     break;
                 case 6:
                     System.out.println("n-> Opcion: Ver Datos del Estudiante");
                     //
                     break;
                 case 7:
                     System.out.println("n-> Opcion: Bitacora ");
                     //
                     break;
                 case 8:
                     salir = true ;
                     System.out.println("Cerrando del Sistenam");
                     break;
                 default:
                     System.out.println("Operacion no  Valida,Por favor Elija Nuevamente");

             }


         }
             scanner.close();

         }


}



