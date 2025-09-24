import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Personaje> personajes = new ArrayList<>();

    private static Scanner scanner = new Scanner(System.in);

    public static  void main(String[]args ){
        System.out.println("Arena ING USAC");
        mostrarMenuPrincipal();
    }
    public static  void mostrarMenuPrincipal(){

        while (true){
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
                    System.out.println("Arena ING USAC");
                    return;
                default:
                    System.out.println("Error. Ingrese Nuevamente");
            }
        }

        public static void verPersonajes() {
            System.out.println("Puchamones en la Arena");

            if (personajes.isEmpty()){
                System.out.println("No hay Puchamones para luchar..");
                return;
            }
            for (Personaje personaje: personajes){
                System.out.println(personaje.toSring());
            }
        }

    }
    public static void agregarPersonaje(){
        System.out.println(" Agregar Puchamon");
        System.out.println("Nombre:");
        String nombre = scanner.nextLine();

        System.out.println("Arma: ");
        String arma = scanner.nextLine();

        System.out.println("Vida (100-500): ");
        int vida = scanner.nextInt();

        System.out.println("Ataque (10-100): ");
        int ataque = scanner.nextInt();

        System.out.println("Velocidad(1-10)");
        int velocidad = scanner.nextInt();

        System.out.println("Agilidad (1-10)");
        int agilidad = scanner.nextInt();

        System.out.println("Defensa(1-50): ");
        int defensa = scanner.nextInt();
        scanner.nextLine();

        //investigar personajer nuevo

        Personaje nuevoPersonaje = new Personaje(nombre, arma , vida, ataque, velocidad ,agilidad, defensa);
        personajes.add(nuevoPersonaje);
        System.out.println("Puchamon Agregado a la Arena");
        System.out.println("Puchamon a la Arena: " + nuevoPersonaje.getId());
    }
}
