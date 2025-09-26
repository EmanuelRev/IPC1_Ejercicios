import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Personaje> personajes = new ArrayList<>();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Arena ING USAC");
        mostrarMenuPrincipal();
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

    public static void agregarPersonaje() {
        String nombre = JOptionPane.showInputDialog((null,"Ingrese el nombre del Puchamon","Agregar Puchamon",JOptionPane.QUESTION_MESSAGE);

        if (nombre == null || nombre.trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"Debe Ingresar Nombre.", "Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        String arma = JOptionPane.showInputDialog(null,"Ingrese el arma del Puchamon: ","Agregar arma",JOptionPane.QUESTION_MESSAGE);

        if (arma == null || arma.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,"Debe ingresar un arma ","Error",JOptionPane.ERROR_MESSAGE);
            return;


        }

        int vida = NumeroRango("Vida (100-500) ","Agregando Vida",100,500);
        if (vida ==-1) return;

        int ataque = NumeroRango("Ataque (10-100)","Agregando Ataque",10,100);
        if (ataque == -1 )
                return;

        int velocidad NumeroRango("Velocidad (1-10) ","Agregando Velociad",1,10);
        if (velocidad == -1) return;

        int agilidad NumeroRango("Agilidad (1-10)","Agregando Agilidad", 1,10);
        if (agilidad == -1) return;

        int defensa NumeroRango("Defensa (1-50)","Agregando Defensa",1,50);
        if (defensa == -1 ) return;

        Personaje nuevoPerosnaje = new Personaje(nombre,arma,vida,ataque,velocidad,agilidad,defensa);
        personajes.add(nuevoPerosnaje);

        JOptionPane.showMessageDialog(null, "Puchamon Agregado a la Arena!\ID asginado: " + nuevoPerosnaje.getId(),"Exito",JOptionPane.INFORMATION_MESSAGE);
        //investigar personajer nuevo

        Personaje nuevoPersonaje = new Personaje(nombre, arma, vida, ataque, velocidad, agilidad, defensa);
        personajes.add(nuevoPersonaje);
        System.out.println("Puchamon Agregado a la Arena");
        System.out.println("Puchamon a la Arena: " + nuevoPersonaje.getId());

    }
// agregar case4
    public static void verPersonajes () {

        if (personajes.isEmpty()) {
            JOptionPane.showMessageDialog(null,"No hay Puchamones Registrados","Ver Puchamones",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder ListaPuchamones = new StringBuilder("--- Puchamones Registrados---\n\n");
        for (Personaje personaje : personajes) {
            System.out.println(personaje.toString());
        }

        JOptionPane.showMessageDialog(null,ListaPuchamones.toString(),"Lista de Puchamones",JOptionPane.INFORMATION_MESSAGE);
    }
    public static Personaje buscarPersonaje(int idBuscado){
        for (Personaje personaje : personajes){
            if (personaje.getId() == idBuscado) {
                return personaje;
            }
        }
        return  null;

    }
    public static void modificarPersonaje(){
        System.out.println("Modificar Puchamon");
        verPersonajes();
        if (personajes.isEmpty()){
            return;
        }
        System.out.println("Ingrese ID del Puchamos a Evolucionar: ");
        int idModificar = scanner.nextInt();
        scanner.nextLine();

        Personaje personajeAModificar = buscarPersonaje(idModificar);

        if (personajeAModificar == null ){
            System.out.println("No se Encontro Puchamon en la Arena: "+ idModificar);
            return;
        }
        System.out.println("Datos del Puchamon: ");
        System.out.println(personajeAModificar.toString());
        System.out.println("habilidad a cambiar..");
        System.out.println("1. nombre");
        System.out.println("2. arma");
        System.out.println("3. vida");
        System.out.println("4. ataque");
        System.out.println("5. velocidad");
        System.out.println("6. agilidad");
        System.out.println("7. defensa");
        System.out.println("8. cancelar");

        int opcionModificar = scanner.nextInt();
        scanner.nextLine();
        switch (opcionModificar){
            case 1:
                System.out.println("Nuevo nombre a su puchamon: ");
                String nuevoNombre = scanner.nextLine();
                personajeAModificar.setNombre(nuevoNombre);
                System.out.println("Nombre Modificado" + nuevoNombre);
                break;
            case 2:
                System.out.println("Nueva arama: ");
                String nuevaArma = scanner.nextLine();
                personajeAModificar.setArma(nuevaArma);
                System.out.println("Arma Modificada: " + nuevaArma);
                break;

            case 3:
                System.out.println("Nueva Vida(100-500): ");
                int nuevaVida = scanner.nextInt();
                if (nuevaVida >= 100 && nuevaVida <= 500){
                    personajeAModificar.setVida(nuevaVida);
                    System.out.println(" Vida Modificada.");

                }else {
                    System.out.println("La vuda debe ser entre(100-500)");
                }
                break;

            case 4:
                System.out.println("Nuevo Ataque: (10-100) ");
                int nuevoAtaque = scanner.nextInt();
                if (nuevoAtaque >= 10 && nuevoAtaque <= 100 ){
                    personajeAModificar.setAtaque(nuevoAtaque);
                    System.out.println("Ataque Modificado.");
                }else{
                    System.out.println("El Ataque debe estar entre (10-100) ");
                }
                break;
            case 5:
                System.out.println("Nueva Velocidad (1-10): ");
                int nuevaVelocidad = scanner.nextInt();
                if (nuevaVelocidad >= 1 && nuevaVelocidad <= 10 ){
                    personajeAModificar.setVelocidad(nuevaVelocidad);
                    System.out.println("Velocidad Modificada." + nuevaVelocidad);

                }else{
                    System.out.println("La velocidad debe estar entre (1-10) ");

                }
                break;
            case 6:
                System.out.println("Nueva Agilidad (1-10) ");
                int nuevaAgilidad = scanner.nextInt();
                if (nuevaAgilidad >= 1 && nuevaAgilidad <= 10 ){
                    personajeAModificar.setAgilidad(nuevaAgilidad);
                    System.out.println("Agilidad Cambiada. " + nuevaAgilidad);

                }else {
                    System.out.println("La Agilidad debe estar entre (1-10) ");
                }
                break;
            case 7:
                System.out.println("Nueva Defensa (1-50) ");
                int nuevaDefensa = scanner.nextInt();
                if (nuevaDefensa >= 1 && nuevaDefensa <= 50){
                    personajeAModificar.setDefensa(nuevaDefensa);
                    System.out.println("La Defensa Modifica" + nuevaDefensa);

                }else {
                    System.out.println("La Defensa debe estar entre (1-50) ");
                }
                break;
            case 8:
                System.out.println("Modificacion Cancelada...");
                break;
            default:
                System.out.println("Opcion Invalida");
        }

    }
    // aqui la interfaz con joption xD
    public static void MenuPrincipal(){
        while (true){

            String menu = "--Menu Principal--" + "1. Agregar Personaje\n" + "2. Ver Puchamones\n" + "3. Modificar Puchamon\n" + "4. Eliminar Puchamon\n" + "5. Salir de la  Arena" ;

            String opcionStr = JOptionPane.showInputDialog(null, menu, "Arena ING USAC", JOptionPane.QUESTION_MESSAGE);

            if (opcionStr == null){
                int confirmar = JOptionPane.showConfirmDialog(null,"¿Desea Abandonar Arena?","Confirmar Salida",JOptionPane.YES_NO_OPTION);

                if (confirmar == JOptionPane.YES_NO_OPTION) {
                    JOptionPane.showMessageDialog(null, "Nos vemos en otro Combate");
                    break;
                }else{
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
                        JOptionPane.showMessageDialog(null,"Nos Vemos en otro Combate!");
                        return;
                    default:
                        JOptionPane.showMessageDialog(null,"Opcion Invalida,Vuelva a Intentar.","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
// mover este metodo
        }
        public static  int NumeroRango(String mensaje,String titulo, int min, int max){
            while (true){
                String input = JOptionPane.showInputDialog(null,mensaje,titulo,JOptionPane.QUESTION_MESSAGE);

                if (input == null){
                    return -1 ;
                }
                try {
                    int numero = Integer.parseInt(input);
                    if (numero >= min && numero <= max){
                        return numero;
                    }else {
                        JOptionPane.showMessageDialog(null,"El numero debe ser"+ min " y " + max + ".","Error de Rango",JOptionPane.ERROR_MESSAGE);
                    }
                }catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(null,"Ingrese un Numero Valido.","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}


