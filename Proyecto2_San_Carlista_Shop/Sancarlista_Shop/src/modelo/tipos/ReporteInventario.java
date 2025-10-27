package modelo.tipos;

import modelo.Reporte;
import modelo.Producto;

public class ReporteInventario extends Reporte {
    private Producto[] productos;
    private int totalProductos;

    public ReporteInventario(Producto[] productos, int totalProductos) {
        super("Reporte de Inventario - Stock Critico");
        this.productos = productos;
        this.totalProductos = totalProductos;
    }

    @Override
    public String generarContenido() {
        StringBuilder contenido = new StringBuilder();

        contenido.append("PRODUCTOS CON STOCK CRITICO (<10 unidades)\n");
        contenido.append("---------------\n");

        boolean hayCriticos = false;
        for (int i = 0; i < totalProductos; i++) {
            if (productos[i] != null && productos[i].getStock() < 10) {
                contenido.append("Codigo: ").append(productos[i].getCodigo())
                        .append("\nNombre: ").append(productos[i].getNombre())
                        .append("\nCategoria: ").append(productos[i].getCategoria())
                        .append("\nStock actual: ").append(productos[i].getStock())
                        .append("\nEstado: CRITICO")
                        .append("\nSugerencia: Reabastecer urgentemente\n\n");
                hayCriticos = true;
            }
        }

        if (!hayCriticos) {
            contenido.append("No hay productos con stock critico\n\n");
        }

        contenido.append("PRODUCTOS CON STOCK BAJO (10-20 unidades)\n");
        contenido.append("=========================================\n");

        boolean hayBajos = false;
        for (int i = 0; i < totalProductos; i++) {
            if (productos[i] != null && productos[i].getStock() >= 10 && productos[i].getStock() <= 20) {
                contenido.append("Codigo: ").append(productos[i].getCodigo())
                        .append("\nNombre: ").append(productos[i].getNombre())
                        .append("\nCategoria: ").append(productos[i].getCategoria())
                        .append("\nStock actual: ").append(productos[i].getStock())
                        .append("\nEstado: BAJO")
                        .append("\nSugerencia: Considerar reabastecimiento\n\n");
                hayBajos = true;
            }
        }

        if (!hayBajos) {
            contenido.append("No hay productos con stock bajo\n");
        }

        return contenido.toString();
    }

    @Override
    public String getTipo() {
        return "Reporte_Inventario";
    }
}