package modelo.tipos;

import modelo.Reporte;
import modelo.Producto;
import modelo.Pedido;

public class ReporteProductosMasVendidos extends Reporte {
    private Producto[] productos;
    private int totalProductos;
    private Pedido[] pedidos;
    private int totalPedidos;

    public ReporteProductosMasVendidos(Producto[] productos, int totalProductos, Pedido[] pedidos, int totalPedidos) {
        super("Top 5 Productos Mas Vendidos");
        this.productos = productos;
        this.totalProductos = totalProductos;
        this.pedidos = pedidos;
        this.totalPedidos = totalPedidos;
    }

    @Override
    public String generarContenido() {
        int[] ventasPorProducto = new int[totalProductos];

        for (int i = 0; i < totalPedidos; i++) {
            if (pedidos[i] != null && pedidos[i].isConfirmado()) {
                String[] items = pedidos[i].getResumenProductos().split(",");
                for (String item : items) {
                    if (!item.isEmpty()) {
                        String[] partes = item.split(":");
                        if (partes.length == 2) {
                            String codigo = partes[0];
                            int cantidad = Integer.parseInt(partes[1]);

                            for (int j = 0; j < totalProductos; j++) {
                                if (productos[j] != null && productos[j].getCodigo().equals(codigo)) {
                                    ventasPorProducto[j] += cantidad;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        Producto[] productosOrdenados = new Producto[totalProductos];
        int[] ventasOrdenadas = new int[totalProductos];
        int totalValidos = 0;

        for (int i = 0; i < totalProductos; i++) {
            if (productos[i] != null && ventasPorProducto[i] > 0) {
                productosOrdenados[totalValidos] = productos[i];
                ventasOrdenadas[totalValidos] = ventasPorProducto[i];
                totalValidos++;
            }
        }

        for (int i = 0; i < totalValidos - 1; i++) {
            for (int j = i + 1; j < totalValidos; j++) {
                if (ventasOrdenadas[i] < ventasOrdenadas[j]) {
                    int tempVenta = ventasOrdenadas[i];
                    ventasOrdenadas[i] = ventasOrdenadas[j];
                    ventasOrdenadas[j] = tempVenta;

                    Producto tempProd = productosOrdenados[i];
                    productosOrdenados[i] = productosOrdenados[j];
                    productosOrdenados[j] = tempProd;
                }
            }
        }

        StringBuilder contenido = new StringBuilder();
        int limite = Math.min(5, totalValidos);

        for (int i = 0; i < limite; i++) {
            contenido.append((i + 1)).append(". ").append(productosOrdenados[i].getNombre())
                    .append("\n   Vendidos: ").append(ventasOrdenadas[i])
                    .append("\n   Categoria: ").append(productosOrdenados[i].getCategoria())
                    .append("\n   Ingresos: Q").append(ventasOrdenadas[i] * 100.0)
                    .append("\n\n");
        }

        if (limite == 0) {
            contenido.append("No hay datos suficientes para generar el reporte\n");
        }

        return contenido.toString();
    }

    @Override
    public String getTipo() {
        return "Productos_Mas_Vendidos";
    }
}