package modelo.tipos;

import modelo.Reporte;
import modelo.Cliente;
import modelo.Pedido;

public class ReporteClientesActivos extends Reporte {
    private Cliente[] clientes;
    private int totalClientes;
    private Pedido[] pedidos;
    private int totalPedidos;

    public ReporteClientesActivos(Cliente[] clientes, int totalClientes, Pedido[] pedidos, int totalPedidos) {
        super("Reporte de Clientes Activos");
        this.clientes = clientes;
        this.totalClientes = totalClientes;
        this.pedidos = pedidos;
        this.totalPedidos = totalPedidos;
    }

    @Override
    public String generarContenido() {
        StringBuilder contenido = new StringBuilder();

        for (int i = 0; i < totalClientes; i++) {
            if (clientes[i] != null) {
                int comprasUltimos30Dias = 0;
                double totalGastado = 0;

                for (int j = 0; j < totalPedidos; j++) {
                    if (pedidos[j] != null && pedidos[j].isConfirmado() &&
                            pedidos[j].getCodigoCliente().equals(clientes[i].getCodigo())) {
                        comprasUltimos30Dias++;
                        totalGastado += pedidos[j].getTotal();
                    }
                }

                if (comprasUltimos30Dias > 0) {
                    String clasificacion = "NUEVO";
                    if (comprasUltimos30Dias >= 5) clasificacion = "FRECUENTE";
                    else if (comprasUltimos30Dias >= 2) clasificacion = "OCASIONAL";

                    contenido.append("Cliente: ").append(clientes[i].getCodigo()).append(" - ").append(clientes[i].getNombre())
                            .append("\nCompras ultimos 30 dias: ").append(comprasUltimos30Dias)
                            .append("\nTotal gastado: Q").append(totalGastado)
                            .append("\nClasificacion: ").append(clasificacion)
                            .append("\nFrecuencia promedio: ").append(String.format("%.1f", comprasUltimos30Dias / 30.0)).append(" compras/dia")
                            .append("\n\n");
                }
            }
        }

        return contenido.toString();
    }

    @Override
    public String getTipo() {
        return "Clientes_Activos";
    }
}