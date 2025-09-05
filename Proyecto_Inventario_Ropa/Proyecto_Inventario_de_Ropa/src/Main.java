import java.util.Scanner;
public class Main {

    static Producto[] inventario = new Producto[100];
    static int contadorProductos = 0;


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
                     buscarProducto(inventario, contadorProductos);
                     //
                     break;
                 case 3:
                     contadorProductos = eliminarProducto(inventario , contadorProductos);
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


         //aqui termina
           Producto nuevoProducto = new Producto(nombre, categoria, precio, cantidad, codigo);
           inventario[contadorProductos] = nuevoProducto;
           System.out.println("Producto '" + nombre +"' Producto Agregado");

           return contadorProductos + 1;
         }
         public static void buscarProducto(Producto[] inventario, int contadorProductos){
             Scanner scanner = new Scanner(System.in);

             System.out.println("-- Buscar Producto --");
             System.out.println("1. Buscar por noimbre");
             System.out.println("2. Buscar por categoria");
             System.out.println("3. Buscar por codigo");
             System.out.print("Selecione una Opcion para inicias la busqueda: ");

             int criterio = scanner.nextInt();
             scanner.nextLine();

             System.out.print("Ingrese un termino para la busqueda: ");
             String termino = scanner.nextLine().toLowerCase();

             boolean encontrado = false;

             System.out.println("Resultados de Busqueda: ");

             for(int i = 0; i < contadorProductos; i++ ) {
                 if(inventario[i] != null ) {
                 boolean coincide = false;

                 switch (criterio) {
                     case 1:
                         coincide = inventario[i].nombre.toLowerCase().contains(termino);
                         break;

                     case 2:
                         coincide = inventario[i].categoria.toLowerCase().contains(termino);
                         break;

                      case 3;
                          coincide = inventario[i].codigounico.toLowerCase().contains(termino);
                          break;

                     default:
                         System.out.println("Opción de Busqueda no válida.");
                         return;
                 }
                 if (coincide) {
                     System.out.println("Nombre: " + inventario[i].nombre);
                     System.out.println("Nombre: " + inventario[i].categoria);
                     System.out.println("Nombre: " + inventario[i].precio);
                     System.out.println("Nombre: " + inventario[i].cantidad);
                     System.out.println("Nombre: " + inventario[i].codigounico);
                     encontrado = true;

                 }
                 }
             }
                  if (!encontrado) {
                      System.out.println("No se Encontraron Productos.");
                  }
         }
                  public static int eliminarProducto(Producto[] inventario, int contadorProductos){

                  Scanner scanner = new Scanner(System.in);

                  System.out.println("--Eliminar Productos");
                  System.out.println("Ingrese codigo de producto que desea eliminar: ");
                  String codigo = scanner.nextLine();

                  boolean encontrado = false;
                  int indice = -1;

                  for (int i = 0; i < contadorProductos; i++) {
                      if (inventario[i] != null && inventario[i].codigounico.equalsIgnoreCase(codigo)) {
                          encontrado = true;
                          indice = i;
                          break;
                      }
                  }
                  if (!encontrado) {
                      System.out.println("Error: Producto no encontrado. '" + codigo +"'.");
                      return  contadorProductos;

                  }
                   System.out.println("Producto Seleccionado");
                   System.out.println("Nombre: " + inventario[indice].nombre);
                   System.out.println("Categoria: " + inventario[indice].categoria);
                   System.out.println("Precio: " + inventario[indice].precio);
                   System.out.println("Cantidad: " + inventario[indice].cantidad);
                   System.out.println("Codigo: " + inventario[indice].codigounico);

                   System.out.println(" ¿Desea Eliminar el Producto del Sistema? ");
                   String confirmacion = scanner.nextLine();

                   if (confirmacion.equalsIgnoreCase("s")) {

                       for (int i = indice; i < contadorProductos - 1; i++) {
                           inventario[i] = inventario[i + 1];

                       }
                       inventario[contadorProductos -1] = null;

                       System.out.println("Producto Eliminado del Sistema Exitosamente.");
                       return  contadorProductos - 1;


                      }else {
                       System.out.println("Operacion Canelada. ");
                       return  contadorProductos;

                      }
                  }

}

