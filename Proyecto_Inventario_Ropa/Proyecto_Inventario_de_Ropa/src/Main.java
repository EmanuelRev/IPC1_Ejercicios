import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
public class Main {

    static Producto[] inventario = new Producto[100];
    static int contadorProductos = 0;


     public static void main(String[] args) {

         Scanner scanner = new Scanner(System.in);
         boolean salir = false;

         int opcion ;

         contadorProductos = cargarInventario(inventario); // para guardar los archivos....

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
                     int contador = contadorProductos;
                     contadorProductos = agregarProducto(inventario, contadorProductos);
                     guardarInventario(inventario, contadorProductos);
                     // LLaves en los else
                     if (contadorProductos > contador) {
                         registrarBitacora("Agregar_Producto", "Guardado", "Producto agregado:"+ inventario[contador].nombre);
                     } else {
                         registrarBitacora("Agregar_Producto", "Error", "Intentelo de nuevo");
                     }
                     break;
                 case 2:
                     buscarProducto(inventario, contadorProductos);
                     //
                     registrarBitacora("Buscar_Producto", "Procesando","Busqueda con exito");
                     break;

                 case 3:
                     contador = contadorProductos;
                     contadorProductos = eliminarProducto(inventario , contadorProductos);
                     guardarInventario(inventario, contadorProductos);
                     //
                     if (contadorProductos < contador) {
                         registrarBitacora("Eliminar_Producto","Realizado", "Producto Eliminado.");
                     } else {
                         registrarBitacora("Eliminar_Producto", "Error", "Producto no encontrado.");
                     }
                     break;

                 case 4:
                     registrarVenta(inventario, contadorProductos);
                     guardarInventario(inventario, contadorProductos);
                     //
                      registrarBitacora("Registrar_Venta", "Realizado", "Registro Completado.");
                     break;
                 case 5:
                     System.out.println("n-> Opcion: Generar Reportes");
                     //
                     registrarBitacora("Generar_Reportes", "Realizado", "Reportes Generados");
                     break;

                 case 6:
                    mostrarDatosEstudiante();

                     //
                     registrarBitacora("Datos_Estudiantes", "Realizado","Datos Mostrados: ");
                     break;

                 case 7:
                     System.out.println("n-> Opcion: Bitacora ");
                     //
                     mostrarBitacora();
                     break;

                 case 8:
                     salir = true ;
                     guardarInventario(inventario, contadorProductos);
                     registrarBitacora("Salir","Saliendo..", "Sistema Cerrado");
                     System.out.println("Cerrando del Sistenam");
                     break;
                 default:


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

                     case 3:
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
                  public static int cargarInventario(Producto[] inventario) {
                     int contador = 0;
                      File archivo = new File("inventario.txt");

                      if(!archivo.exists()){
                          System.out.println("No se encontro  ningun archivo del inventario, Se creara  un archivo. ");
                            return  0;

                      }
                      try (Scanner fileScanner = new Scanner(archivo)) {
                          while (fileScanner.hasNextLine() && contador < inventario.length) {
                              String linea = fileScanner.nextLine();
                              String[] datos = linea.split("//");

                              if (datos.length == 5) {
                                  String nombre = datos[0];
                                  String categoria = datos[1];
                                  double precio = Double.parseDouble(datos[2]);
                                  int cantidad = Integer.parseInt(datos[3]);
                                  String codigo = datos[4];

                                  inventario[contador] = new Producto(nombre, categoria , precio, cantidad, codigo);
                                  contador++;


                              }
                          }
                          System.out.print("Datos del Inventario creados exitosamente. " + contador + "Producots encontrados. ") ;

                      } catch (IOException e) {
                          System.out.println("Error al cargar datos del inventario: " + e.getMessage());

                      }
                      return  contador;

                  }
                  public static void guardarInventario(Producto[] inventario, int contadorProductos) {
                    try {
                        FileWriter writer = new FileWriter("inventario.txt");
                        for (int i = 0; i < contadorProductos; i++) {
                            if (inventario[i] != null) {
                                writer.write(inventario[i].nombre + "|" + inventario[i].categoria + "|" + inventario[i].precio + "|" + inventario[i].cantidad + "|" + inventario[i].codigounico + "/n");

                            }
                        }
                        writer.close();


                    }catch (IOException e) {
                         System.out.println("Error al guardar datos.");
                    }
                  }
                  public static  void buscarProductos(Producto[] inventario, int contadorProductos) {
                     Scanner scanner = new Scanner(System.in);
                     System.out.println("--- Buscar Producto ---");
                     System.out.println("Ingrese codigo o nombre del producto: ");
                     String busqueda = scanner.nextLine().toLowerCase();

                     boolean encontrado = false;
                     for (int i = 0; i < contadorProductos; i++) {
                         if (inventario[i] != null &&(inventario[i].codigounico.toLowerCase().equals(busqueda) || inventario[i].nombre.toLowerCase().contains(busqueda))) {
                             System.out.println(" Producto encontrado: ");
                             System.out.println("Nombre: " + inventario[i].nombre);
                             System.out.println("Categoria: " + inventario[i].categoria);
                             System.out.println("Precio: " + inventario[i].precio);
                             System.out.println("Cantidad: " + inventario[i].cantidad);
                             System.out.println("Codigo: " + inventario[i].codigounico);
                             encontrado = true;

                         }
                     }
                    if (!encontrado) {
                        System.out.println("No se encontraron Productos en los datos.");
                    }
                  }
                  public static void registrarVenta(Producto[] inventario, int contadorProductos) {
                   Scanner scanner = new Scanner(System.in);

                   System.out.println(" ----Resgistrar Venta--- ");
                   System.out.println("Ingrese el Producto: ");
                   String codigo = scanner.nextLine();

                   Producto producto = null;
                   int indice =-1;

                   for (int i = 0; i < contadorProductos; i++) {
                       if (inventario[i] != null && inventario[i].codigounico.equalsIgnoreCase(codigo)) {
                           producto = inventario[i];
                           indice = i;
                           break;
                       }
                      }
                   if (producto == null) {
                       System.out.println("Error: Producto no encontrado. ");
                       return;
                   }

                    System.out.println(" Producto encontrado: ");
                   System.out.println("Nombre: " + producto.nombre);
                   System.out.println("Precio: " + producto.precio);
                   System.out.println("Exixtencia: " + producto.cantidad);

                   int cantidadVender;
                   while (true)  {
                       System.out.println("Ingrese la cantidad de productos: ");
                       try {
                           cantidadVender = scanner.nextInt();
                           if (cantidadVender <= 0 ) {
                               System.out.println("Error: La cantidad debe ser un numero positivo. ");
                               continue;

                           }
                           if (cantidadVender > producto.cantidad) {
                               System.out.println("Error: Articulos Agostados. Articulos disponibles: " + producto.cantidad + "unidades- ");
                               continue;
                           }
                           break;

                           }catch (Exception e) {
                           System.out.println("Error: Ingrese un Valor numerico.");
                           scanner.nextLine();
                       }
                   }

                   double totalVenta = producto.precio * cantidadVender;

                   producto.cantidad -= cantidadVender;
                   inventario[indice] = producto;

                   guardarVenta(producto, cantidadVender, totalVenta);

                   System.out.println("Venta registrada exitosamente:");
                      System.out.println("Producto: " + producto.nombre);
                      System.out.println("Cantidad: " + cantidadVender);
                      System.out.println("Total: " + totalVenta);
                      System.out.println(" Nuevos Articulos Disponiulbes: " + producto.cantidad);
                  }
          public static  void guardarVenta(Producto producto, int cantidad, double total) {

          try (FileWriter writer = new FileWriter("DatosVentas.txt, true")) {

              writer.write((java.time.LocalDate.now()) + "|" + java.time.LocalTime.now() + "|" + producto.codigounico + "|" + producto.nombre + "|" +
                      cantidad + "|" + producto.precio + "|" + total + "\n");
          }catch (IOException e) {
              System.out.println("Error al guardar datos de venta: "+ e.getMessage());
          }
          }
          public static  void registrarBitacora(String accion, String resultado, String detalles) {
            try (FileWriter writer = new FileWriter("Bitacora.txt", true)) {
                String fechaHora = java.time.LocalDateTime.now().toString();
                writer.write(fechaHora + "|" + accion + "|" + resultado + "|" +
                        detalles + "\n");

            }catch (IOException e) {
                System.out.println("Error al registrar la bitacora: " + e.getMessage());
                
            }
          }
          public static void mostrarBitacora() {
         System.out.println("---Bitacora del Sistema---");
         File archivo = new File("bitacora.txt");

         if (!archivo.exists())  {
             System.out.println("No hay registros en la bitacora. ");
             return;
         }
         try (Scanner fileScanner = new Scanner(archivo)) {
             int contador2 = 0;
             while (fileScanner.hasNextLine()) {
                 String linea = fileScanner.nextLine();
                 String[] datos = linea.split("\\|");

                 if (datos.length >= 4 ) {
                     System.out.println("Fecha_Hora:" + datos[0]);
                     System.out.println("Accion: " + datos[1]);
                     System.out.println("Resultado: " + datos[2]);
                     System.out.println("Detalles: " + datos[3]);
                     contador2++;

                 }
             }
             System.out.println("Resultados de los registros: " + contador2);
         } catch (IOException e) {
             System.out.println("Error en la busqueda de la bictacora: " + e.getMessage());
         }
          }
          public static void mostrarDatosEstudiante() {
           System.out.println("---Datos del Estudiante---");
           System.out.println("Nombres: Kevin_Emanuel ");
           System.out.println("Apellidos: Ramirez_Revolorio");
           System.out.println("Carne: 201907719");
           System.out.println("Practica1_Inventario_de_Ropa. ");
           System.out.println("Seccion: C");
           System.out.println("Semestre_2_2025 ");
          }
}

