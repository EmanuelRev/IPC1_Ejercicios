public class Producto {
    String nombre;
    String categoria;
    double precio;
    int    cantidad;
    String codigounico;

    public Producto(String nombre, String categoria, double precio, int cantidad, String codigounico) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidad = cantidad;
        this.codigounico = codigounico;
  }
}