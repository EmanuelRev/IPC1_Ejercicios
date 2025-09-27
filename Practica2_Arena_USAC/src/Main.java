
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class Main {
    private static ArrayList<Personaje> personajes = new ArrayList<>();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
      MenuPrincipal();

        //System.out.println("Arena ING USAC");
      //  mostrarMenuPrincipal();
    }

    public static void mostrarMenuPrincipal() {

        while (true) {
            System.out.println(" Menu ARENA ING");
            System.out.println("1. Agregar personaje");
            System.out.println("2. Ver personaje");
            System.out.println("3. Salir");
            System.out.println("Selecione una opcion");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarPersonaje();
                    break;
                case 2:
                    verPersonajes();
                    break;
                case 3:
                    modificarPersonaje();
                    break;

                case 4:
                    System.out.println("Arena ING USAC");
                    return;
                default:
                    System.out.println("Error. Ingrese Nuevamente");
            }
        }

    }

    public static int NumeroRango(String mensaje, String titulo, int min, int max) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.QUESTION_MESSAGE);

            if (input == null) {
                return -1;
            }
            try {
                int numero = Integer.parseInt(input);
                if (numero >= min && numero <= max) {
                    return numero;
                } else {
                    JOptionPane.showMessageDialog(null, "El numero debe ser" + min + " y " + max + ".", "Error de Rango", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un Numero Valido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    public static void agregarPersonaje() {
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del Puchamon", "Agregar Puchamon", JOptionPane.QUESTION_MESSAGE);

        if (nombre == null || nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe Ingresar Nombre.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String arma = JOptionPane.showInputDialog(null, "Ingrese el arma del Puchamon: ", "Agregar arma", JOptionPane.QUESTION_MESSAGE);

        if (arma == null || arma.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un arma ", "Error", JOptionPane.ERROR_MESSAGE);
            return;


        }

        int vida = NumeroRango("Vida (100-500) ", "Agregando Vida", 100, 500);
        if (vida == -1) return;

        int ataque = NumeroRango("Ataque (10-100)", "Agregando Ataque", 10, 100);
        if (ataque == -1)
            return;

        int velocidad = NumeroRango("Velocidad (1-10) ", "Agregando Velocidad", 1, 10);
        if (velocidad == -1) return;

        int agilidad = NumeroRango("Agilidad (1-10)", "Agregando Agilidad", 1, 10);
        if (agilidad == -1) return;

        int defensa = NumeroRango("Defensa (1-50)", "Agregando Defensa", 1, 50);
        if (defensa == -1) return;

        Personaje nuevoPerosnaje = new Personaje(nombre, arma, vida, ataque, velocidad, agilidad, defensa);
        personajes.add(nuevoPerosnaje);

        JOptionPane.showMessageDialog(null, "Puchamon Agregado a la Arena!\n ID asginado: " + nuevoPerosnaje.getId(), "Exito", JOptionPane.INFORMATION_MESSAGE);
        //investigar personajer nuevo


    }

    // agregar case4
    public static void verPersonajes() {

        if (personajes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay Puchamones Registrados", "Ver Puchamones", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder ListaPuchamones = new StringBuilder("--- Puchamones Registrados---\n\n");
        for (Personaje personaje : personajes) {
            ListaPuchamones.append("ID: ").append(personaje.getId()).append("\n");
            ListaPuchamones.append("Nombre: ").append(personaje.getNombre()).append("\n");
            ListaPuchamones.append("Arma: ").append(personaje.getArma()).append("\n");
            ListaPuchamones.append("Vida: ").append(personaje.getVida()).append("\n");
            ListaPuchamones.append("Ataque: ").append(personaje.getAtaque()).append("\n");
            ListaPuchamones.append("Velocidad: ").append(personaje.getVelocidad()).append("\n");
            ListaPuchamones.append("Agilidad: ").append(personaje.getAgilidad()).append("\n");
            ListaPuchamones.append("Defensa: ").append(personaje.getDefensa()).append("\n");
            ListaPuchamones.append("----------------------------\n");
        }

        JOptionPane.showMessageDialog(null, ListaPuchamones.toString(), "Lista de Puchamones", JOptionPane.INFORMATION_MESSAGE);
    }

    public static Personaje buscarPersonaje(int idBuscado) {
        for (Personaje personaje : personajes) {
            if (personaje.getId() == idBuscado) {
                return personaje;
            }
        }
        return null;

    }

    public static  void modificarPersonaje() {
        if (personajes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay Puchamones para Modificar.", "Modificar Puchamon", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder Lista = new StringBuilder("--- Puchamones Registrados en la Arena---\n\n");
        for (Personaje personaje : personajes) {
            Lista.append(personaje.toString()).append("\n\n");
        }
        Lista.append("Ingrese ID del Puchamon a Modificar: ");

        String idStr = JOptionPane.showInputDialog(null, Lista.toString(), "Modificar Puchamon", JOptionPane.QUESTION_MESSAGE);
        if (idStr == null) return;

        try {
            int idModificar = Integer.parseInt(idStr);
            Personaje personajeModificar = buscarPersonaje(idModificar);
            if (personajeModificar == null) {
                JOptionPane.showMessageDialog(null, "No se encontro Puchamon con ID: " + idModificar, "Error", JOptionPane.ERROR_MESSAGE);
                return;

            }

            String opciones = "¿Que Cambios Desea Hacer al Puchamon?\n\n" + "1. Nombre\n" + "2. Arma\n" + "3. Vida\n" + "4. Ataque\n" + "5. Velocidad\n" + "6. Agilidad\n" + "7. Defensa\n" + "8. Cancelar\n" + "Puchamon Selecionado:\n" + persoanjeModificar.toString();

            String opcionStr = JOptionPane.showInputDialog(null, opciones, "Modificar " + personajeModificar.getNombre(), JOptionPane.QUESTION_MESSAGE);

            if (opcionStr == null) return;

            int opcion = Integer.parseInt(opcionStr);

            switch (opcion) {
                case 1:
                    String nuevoNombre = JOptionPane.showInputDialog(null, "Nuevo nombre: ", "Modificar nombre", JOptionPane.QUESTION_MESSAGE);
                    if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                        personajeModificar.setNombre(nuevoNombre.trim());
                        JOptionPane.showMessageDialog(null, "Nombre Modificado. ");

                    }
                    break;
                case 2:
                    String nuevaArma = JOptionPane.showInputDialog(null, "Nueva arma: ", "Modificar Arma", JOptionPane.QUESTION_MESSAGE);
                    if (nuevaArma != null && !nuevaArma.trim().isEmpty()) {
                        personajeModificar.setArma(nuevaArma.trim());
                        JOptionPane.showMessageDialog(null, "Arama Modificada.");

                    }
                    break;
                case 3:
                    int nuevaVida = NumeroRango("Nueva Vida(100-500)", "Modificar Vida", 100, 500);
                    if (nuevaVida != -1) {
                        personajeModificar.setVida(nuevaVida);
                        JOptionPane.showMessageDialog(null, "Vida Modificada.");
                    }
                    break;
                case 4:
                    int nuevoAtaque = NumeroRango("Nuevo Ataque (10-100) ", "Modificar Ataque", 10, 100);
                    if (nuevoAtaque != -1) {
                        personajeModificar.setAtaque(nuevoAtaque);
                        JOptionPane.showMessageDialog(null, "Ataque Modificado.");
                    }
                    break;
                case 5:
                    int nuevaVelocidad = NumeroRango("Nueva Velocidad (1-10) ", "Modificar Velocidad", 1, 10);
                    if (nuevaVelocidad != -1) {
                        personajeModificar.setVelocidad(nuevaVelocidad);
                        JOptionPane.showMessageDialog(null, "Velocidad Modificada.");
                    }
                    break;
                case 6:
                    int nuevaAgilidad = NumeroRango("Nueva Agilidad (1-10)", "Modificar Agilidad", 1, 10);
                    if (nuevaAgilidad != -1) {
                        personajeModificar.setAgilidad(nuevaAgilidad);
                        JOptionPane.showMessageDialog(null, "Agilidad Modificada.");
                    }
                    break;
                case 7:
                    int nuevaDefensa = NumeroRango("Nueva Defensa (1-50) ", "Modificar Defensa ", 1, 50);
                    if (nuevaDefensa != -1) {
                        personajeModificar.setDefensa(nuevaDefensa);
                        JOptionPane.showMessageDialog(null, "Defensa Modificada");
                    }
                    break;
                case 8:
                    JOptionPane.showMessageDialog(null, "Modificacion Cancelada.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion Invalida.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un Numero Valido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

        // ojo aqui lo empeze
        public static void eliminarPersonaje(){
            if (personajes.isEmpty()){
                JOptionPane.showMessageDialog(null,"No hay Puchamones para Eliminar","Eliminar Puchamon",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            StringBuilder Lista = new StringBuilder("---Puchamones Registrados---\n\n");
            for (Personaje personaje : personajes) {
                Lista.append(personaje.toString()).append("\n\n");
            }
            Lista.append("Ingrese el ID del Puchamon a Eliminar: ");

            String idStr = JOptionPane.showInputDialog(null,Lista.toString(),"Eliminar Puchamon",JOptionPane.QUESTION_MESSAGE);

            if (idStr == null) {
                return;
            }
            try {
                int idEliminar = Integer.parseInt(idStr);
                Personaje personajeEliminar = buscarPersonaje(idEliminar);

                if (personajeEliminar == null) {
                    JOptionPane.showMessageDialog(null,"No se Encontro Puchamones con ese ID."+ idEliminar,"Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int confirmacion = JOptionPane.showConfirmDialog(null,"Esta Seguro de Eliminar a este Puchamon?\n"+personajeEliminar.toString()+"\n\nEsta Desicion no se Pueder Regresar.","Confirmar Eliminacion",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);

                if (confirmacion == JOptionPane.YES_OPTION){
                    personajes.remove(personajeEliminar);
                    JOptionPane.showMessageDialog(null,"Puchamon Retirado de la Arena","Hecho",JOptionPane.INFORMATION_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null,"Eliminacion Cancelada.","Accion Cancelada",JOptionPane.INFORMATION_MESSAGE);
                }


            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,"Ingrese un ID Valido","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    public static void PersonajeNombre() {
        if (personajes.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No hay personajes registrados.",
                    "Buscar Personaje",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String nombreBuscado = JOptionPane.showInputDialog(null,
                "Ingrese el nombre del personaje a buscar:",
                "Buscar Personaje",
                JOptionPane.QUESTION_MESSAGE);

        if (nombreBuscado == null || nombreBuscado.trim().isEmpty()) {
            return;
        }

        nombreBuscado = nombreBuscado.trim().toLowerCase();
        boolean encontrado = false;

        StringBuilder resultado = new StringBuilder("=== RESULTADOS DE BÚSQUEDA ===\n\n");

        for (Personaje personaje : personajes) {
            if (personaje.getNombre().toLowerCase().contains(nombreBuscado)) {
                resultado.append(personaje.toString()).append("\n\n");
                encontrado = true;
            }
        }

        if (!encontrado) {
            resultado.append("No se encontraron personajes con el nombre: ").append(nombreBuscado);
        }

        JOptionPane.showMessageDialog(null,
                resultado.toString(),
                "Resultados de Búsqueda",
                JOptionPane.INFORMATION_MESSAGE);
    }
    }


    // aqui la interfaz con joption xD
    public static void MenuPrincipal() {
        while (true) {

            String menu = "-- Bienvenido a la Arena --\n\n" + "1. Agregar Personaje\n" + "2. Ver Puchamones\n" + "3. Modificar Puchamon\n" + "4. Eliminar Puchamon\n" + "5. Salir de la  Arena";

            String opcionStr = JOptionPane.showInputDialog(null, menu, "Arena ING USAC", JOptionPane.QUESTION_MESSAGE);

            if (opcionStr == null) {
                int confirmar = JOptionPane.showConfirmDialog(null, "¿Desea Abandonar Arena?", "Confirmar Salida", JOptionPane.YES_NO_OPTION);

                if (confirmar == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Nos vemos en otro Combate");
                    break;
                } else {
                    continue;
                }
            }
            try {
                int opcion = Integer.parseInt(opcionStr);

                switch (opcion) {
                    case 1:
                        agregarPersonaje();
                        break;
                    case 2:
                        verPersonajes();
                        break;
                    case 3:
                        modificarPersonaje();
                        break;

                    case 4:
                        eliminarPersonaje();
                        break;
                    case 5:
                        PersonajeNombre();
                        break;

                    case 6:
                        JOptionPane.showMessageDialog(null, "Nos Vemos en otro Combate!");
                        return;
                    default:
                        JOptionPane.showMessageDialog(null, "Opcion Invalida,Vuelva a Intentar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Porfavor ingrese un numero valido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
// mover este metodo
        }

    }
    }






