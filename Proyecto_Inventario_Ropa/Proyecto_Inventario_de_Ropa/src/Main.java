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
                     contadorProductos = agregarProducto(inventario, contadorProductos);
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

         public static int agregarProducto(Producto[] inventario, int contadorProductos) {
         Scanner scanner = new Scanner(System.in);

         System.out.println(" --Agregar Nuevo Producto--");
         System.out.println("Ingrese el Nombre del Producto: ");
         String nombre = scanner.nextLine();

         System.out.println("Ingrese la categoria: ");
         String categoria = scanner.nextLine();

         double precio = 0;
         while (true) {
             System.out.print("Ingrese el precio:");
             try {
                 precio = scanner.nextDouble();
                 if (precio <= 0 ) {
                     System.out.println("Error: El precio debe ser positivo.");
                  continue;
                 }
                 break;
             }catch (Exception e) {
                 System.out.println("Error: Ingrese un valor numerico valido");
                 scanner.nextLine();
             }
         }
         int cantidad = 0;
         while (true){
             System.out.println("Ingrese la Cantidad en Alamacen: ");
             try{
                 cantidad = scanner.nextInt();
                 if (cantidad < 0 ) {
                     System.out.println("Error: La cantidad debe ser mayor a cero y un valor numerico.");
                     continue;

                 }
                 break;
             }catch (Exception e) {
                  System.out.println("Error: Ingrese un valor numerico valido.");
                  scanner.nextLine();
             }
         }
         scanner.nextLine();
         System.out.print("Ingrese el Codigo del Producto: ");
         String codigo = scanner.nextLine();

         for (int i = 0; i < contadorProductos; i++){
             System.out.println("Error: El codigo '" + codigo + "'ya existe. No se puede usar para el Producto: ");
             return contadorProductos;

         }
         } //aqui termina
           Producto nuevoProducto = new Producto(nombre, categoria, precio, cantidad, codigo);
           inventario[contadorProductos] = nuevoProducto;
           System.out.println("Producto '" + nombre +"' Producto Agregado");

           return contadorProductos + 1;

  }

}

