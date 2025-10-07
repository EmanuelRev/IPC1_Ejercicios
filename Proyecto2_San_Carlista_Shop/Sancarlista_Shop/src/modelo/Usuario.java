package modelo;

public class Usuario {

    private String codigo;
    private String nombre;
    private String genero;
    private String contrasenia;
    private String tipo;
    private int ventasConfirmadas;

    public Usuario(String codigo, String nombre, String contrasenia, String tipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.tipo = tipo;
        this.ventasConfirmadas = 0;

    }
    public String getCodigo() {
        return codigo;
    }
    public String getNombre() {
        return  nombre;
    }
    public String getGenero() {return genero;}
    public String getContrasenia() {return contrasenia; }
    public String getTipo() {return  tipo; }
    public int getVentasConfirmadas() {return  ventasConfirmadas; }

    // todos los set
    public void setNombre(String nombre) {this.nombre = nombre; }
    public void setContrasenia(String contrasenia) {this.contrasenia = contrasenia; }
    public void setGenero(String genero) {this.genero = genero; }

    public void aumentarVentas() {
        this.ventasConfirmadas++;
    }

    public boolean verificarContraseia(String contraseniaIngresada) {
        return  this.contrasenia.equals(contraseniaIngresada) ;
    }
}
