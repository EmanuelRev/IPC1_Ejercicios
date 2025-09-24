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

        Personaje nuevoPersonaje = new Personaje(nombre, arma, vida, ataque, velocidad, agilidad, defensa);
        personajes.add(nuevoPersonaje);
        System.out.println("Puchamon Agregado a la Arena");
        System.out.println("Puchamon a la Arena: " + nuevoPersonaje.getId());

    }
// agregar case4
    public static void verPersonajes () {
        System.out.println("Puchamones en la Arena");

        if (personajes.isEmpty()) {
            System.out.println("No hay Puchamones para luchar..");
            return;
        }
        for (Personaje personaje : personajes) {
            System.out.println(personaje.toSring());
        }
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
        System.out.println(personajeAModificar.toSring());
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
                personajeAModificar,setNombre(nuevoNombre);
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
}


