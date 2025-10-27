package modelo;

import java.io.Serializable;


// se almacena lo que ba comprar



public class Carrito implements Serializable {
    private static final long serialVersionUID = 7L;

    private ItemCarrito[] items;
    private int totalItems;

    public Carrito() {
        this.items = new ItemCarrito[50];
        this.totalItems = 0;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        for (int i = 0; i < totalItems; i++) {
            if (items[i].getProducto().getCodigo().equals(producto.getCodigo())) {
                items[i].setCantidad(items[i].getCantidad() + cantidad);
                return;
            }
        }

        if (totalItems < items.length) {
            items[totalItems] = new ItemCarrito(producto, cantidad);
            totalItems++;
        }
    }

    public void eliminarProducto(String codigoProducto) {
        for (int i = 0; i < totalItems; i++) {
            if (items[i].getProducto().getCodigo().equals(codigoProducto)) {
                for (int j = i; j < totalItems - 1; j++) {
                    items[j] = items[j + 1];
                }
                items[totalItems - 1] = null;
                totalItems--;
                return;
            }
        }
    }
// si se agrega mas producto actualizar el carro xxxxx


    public void actualizarCantidad(String codigoProducto, int nuevaCantidad) {
        for (int i = 0; i < totalItems; i++) {
            if (items[i].getProducto().getCodigo().equals(codigoProducto)) {
                if (nuevaCantidad <= 0) {
                    eliminarProducto(codigoProducto);
                } else {
                    items[i].setCantidad(nuevaCantidad);
                }
                return;
            }
        }
    }

    // total de todo lo que se va llevar


    public double getTotal() {
        double total = 0;
        for (int i = 0; i < totalItems; i++) {
            total += items[i].getSubtotal();
        }
        return total;
    }

    public int getTotalItems() { return totalItems; }
    public ItemCarrito[] getItems() { return items; }

    public void limpiarCarrito() {
        for (int i = 0; i < totalItems; i++) {
            items[i] = null;
        }
        totalItems = 0;
    }

    public String getResumenProductos() {
        StringBuilder resumen = new StringBuilder();
        for (int i = 0; i < totalItems; i++) {
            resumen.append(items[i].getProducto().getCodigo()).append(":")
                    .append(items[i].getCantidad()).append(",");
        }
        return resumen.toString();
    }
}