package modelo.tipos;

import modelo.Reporte;
import modelo.Vendedor;
import modelo.Pedido;

public class ReportesVentasVendedor extends Reporte {
    private Vendedor[] vendedores;
    private int totalVendedores;
    private Pedido[] pedidos;
    private int totalPedidos;

    public ReportesVentasVendedor(Vendedor[] vendedores, int totalVendedores, Pedido[] pedidos, int totalPedidos) {
        super("Reporte de Ventas por Vendedor");
        this.vendedores = vendedores;
        this.totalVendedores = totalVendedores;
        this.pedidos = pedidos;
        this.totalPedidos = totalPedidos;
    }

    @Override
    public String generarContenido() {
        StringBuilder contenido = new StringBuilder();

        for (int i = 0; i < totalVendedores; i++) {
            if (vendedores[i] != null) {
                int pedidosConfirmados = 0;
                double totalVentas = 0;

                for (int j = 0; j < totalPedidos; j++) {
                    if (pedidos[j] != null && pedidos[j].isConfirmado()) {
                        pedidosConfirmados++;
                        totalVentas += pedidos[j].getTotal();
                    }
                }

                contenido.append("Vendedor: ").append(vendedores[i].getCodigo()).append(" - ").append(vendedores[i].getNombre())
                        .append("\nPedidos confirmados: ").append(pedidosConfirmados)
                        .append("\nTotal ventas: Q").append(totalVentas)
                        .append("\nPromedio diario: Q").append(String.format("%.2f", totalVentas / 30.0))
                        .append("\nComisiones: Q").append(String.format("%.2f", totalVentas * 0.05))
                        .append("\n\n");
            }
        }

        if (totalVendedores == 0) {
            contenido.append("No hay vendedores registrados\n");
        }

        return contenido.toString();
    }

    @Override
    public String getTipo() {
        return "Ventas_Vendedor";
    }
}