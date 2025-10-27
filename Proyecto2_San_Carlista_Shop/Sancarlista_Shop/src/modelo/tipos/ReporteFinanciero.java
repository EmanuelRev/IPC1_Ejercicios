package modelo.tipos;

import modelo.Reporte;
import modelo.Producto;
import modelo.Pedido;

public class ReporteFinanciero extends Reporte {
    private Producto[] productos;
    private int totalProductos;
    private Pedido[] pedidos;
    private int totalPedidos;

    public ReporteFinanciero(Producto[] productos, int totalProductos, Pedido[] pedidos, int totalPedidos) {
        super("Reporte Financiero por Categoria");
        this.productos = productos;
        this.totalProductos = totalProductos;
        this.pedidos = pedidos;
        this.totalPedidos = totalPedidos;
    }

    @Override
    public String generarContenido() {
        double totalGeneral = 0;
        int categorias = 3;
        String[] nombresCategorias = {"Tecnologia", "Alimento", "Generales"};
        double[] ventasPorCategoria = new double[categorias];
        int[] cantidadPorCategoria = new int[categorias];

        for (int i = 0; i < totalPedidos; i++) {
            if (pedidos[i] != null && pedidos[i].isConfirmado()) {
                totalGeneral += pedidos[i].getTotal();

                String[] items = pedidos[i].getResumenProductos().split(",");
                for (String item : items) {
                    if (!item.isEmpty()) {
                        String[] partes = item.split(":");
                        if (partes.length == 2) {
                            String codigo = partes[0];
                            int cantidad = Integer.parseInt(partes[1]);

                            for (int j = 0; j < totalProductos; j++) {
                                if (productos[j] != null && productos[j].getCodigo().equals(codigo)) {
                                    String categoria = productos[j].getCategoria();
                                    int index = 0;
                                    if (categoria.equals("Alimento")) index = 1;
                                    else if (categoria.equals("Generales")) index = 2;

                                    ventasPorCategoria[index] += cantidad * 100.0;
                                    cantidadPorCategoria[index] += cantidad;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        StringBuilder contenido = new StringBuilder();
        contenido.append("RESUMEN FINANCIERO GENERAL\n");
        contenido.append("===========================\n");
        contenido.append("Ingresos totales: Q").append(totalGeneral).append("\n\n");

        contenido.append("VENTAS POR CATEGORIA\n");
        contenido.append("====================\n");

        for (int i = 0; i < categorias; i++) {
            double porcentaje = totalGeneral > 0 ? (ventasPorCategoria[i] / totalGeneral) * 100 : 0;
            contenido.append(nombresCategorias[i]).append(":\n")
                    .append("  Productos vendidos: ").append(cantidadPorCategoria[i])
                    .append("\n  Ingresos: Q").append(ventasPorCategoria[i])
                    .append("\n  Participacion: ").append(String.format("%.1f", porcentaje)).append("%")
                    .append("\n  Precio promedio: Q").append(cantidadPorCategoria[i] > 0 ? String.format("%.2f", ventasPorCategoria[i] / cantidadPorCategoria[i]) : "0.00")
                    .append("\n\n");
        }

        return contenido.toString();
    }

    @Override
    public String getTipo() {
        return "Reporte_Financiero";
    }
}