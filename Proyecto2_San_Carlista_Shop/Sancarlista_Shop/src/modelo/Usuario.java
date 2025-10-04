package modelo;

public class Usuario {

    private String codigo;
    private String nombre;
    private String contrasenia;
    private String tipo;

    public Usuario(String codigo, String nombre, String contrasenia, String tipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.tipo = tipo;

    }
    public String getCodigo() {
        return codigo;
    }
    public String getNombre() {
        return  nombre;
    }
    public String getContraseia() {
        return  contrasenia;

    }
    public String getTipo() {
        return tipo;
    }
    public boolean verificarContraseia(String contraseniaIngresada) {
        return  this.contrasenia.equals(contraseniaIngresada) ;
    }
}
