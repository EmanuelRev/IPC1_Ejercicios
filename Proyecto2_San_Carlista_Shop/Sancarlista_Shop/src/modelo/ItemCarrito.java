package modelo;

import java.io.Serializable;

// aqui va todo lo que se ba comprar


public class ItemCarrito implements Serializable {
    private static final long serialVersionUID = 6L;

    private Producto producto;
    private int cantidad;

    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }

    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getSubtotal() {
        return cantidad * 100.0; // Precio simulado
    }

    public String getInfoItem() {
        return producto.getNombre() + " | Cantidad: " + cantidad + " | Subtotal: Q" + getSubtotal();
    }
}