public class Personaje {

    private static int contadorId = 1;

    private int id;
    private String nombre;
    private String arma;
    private int vida;
    private  int ataque;
    private int velocidad;
    private int agilidad;
    private int defensa;
    
   //esto es el construcctor para personajes

    public Personaje(String nombre, String arma, int vida, int ataque, int velocidad, int agilidad, int defensa) {
        this.id = contadorId;
        contadorId++;
        this.nombre = nombre;
        this.arma = arma;
        this.vida = vida;
        this.ataque = ataque;
        this.velocidad = velocidad;
        this.agilidad = agilidad;
        this.defensa = defensa;

    }
   public int getId(){
        return id;

   }
   public String getNombre(){
        return nombre;
   }
   public String getArma(){
       return arma;

   }
   public int getVida() {
        return  vida;

   }
   public int getAtaque(){
        return  ataque;
   }
   public int getVelocidad(){
        return velocidad;

   }
   public int getAgilidad(){
        return agilidad;

       }
       public int getDefensa(){
        return defensa;

   }
   public String toSring(){
        return  "ID" +id+
        "Nombre: " + nombre +
        "Vida "+ vida +
        "Ataque: " + ataque +
        "Defensa:" + defensa +
        "Arma: " + arma ;


   }
}
