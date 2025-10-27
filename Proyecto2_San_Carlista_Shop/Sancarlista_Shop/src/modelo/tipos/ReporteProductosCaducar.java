package modelo.tipos;

import modelo.Reporte;
import modelo.Producto;

public class ReporteProductosCaducar extends Reporte {
    private Producto[] productos;
    private int totalProductos;

    public ReporteProductosCaducar(Producto[] productos, int totalProductos) {
        super("Reporte de Productos por Caducar");
        this.productos = productos;
        this.totalProductos = totalProductos;
    }

    @Override
    public String generarContenido() {
        StringBuilder contenido = new StringBuilder();

        contenido.append("PRODUCTOS ALIMENTICIOS POR CADUCAR\n");
        contenido.append("===================================\n");

        boolean hayProductos = false;
        for (int i = 0; i < totalProductos; i++) {
            if (productos[i] != null && productos[i].getCategoria().equals("Alimento")) {
                String fechaCaducidad = productos[i].getFechaCaducidad();
                if (fechaCaducidad != null && !fechaCaducidad.isEmpty()) {
                    int diasRestantes = calcularDiasRestantes(fechaCaducidad);

                    if (diasRestantes <= 7) {
                        String prioridad = "URGENTE";
                        if (diasRestantes <= 3) prioridad = "CRITICO";

                        double valorRiesgo = productos[i].getStock() * 100.0;

                        contenido.append("Producto: ").append(productos[i].getCodigo()).append(" - ").append(productos[i].getNombre())
                                .append("\nFecha caducidad: ").append(fechaCaducidad)
                                .append("\nDias restantes: ").append(diasRestantes)
                                .append("\nStock actual: ").append(productos[i].getStock())
                                .append("\nValor en riesgo: Q").append(valorRiesgo)
                                .append("\nPrioridad: ").append(prioridad)
                                .append("\nRecomendacion: Aplicar descuento inmediato\n\n");

                        hayProductos = true;
                    }
                }
            }
        }

        if (!hayProductos) {
            contenido.append("No hay productos alimenticios por caducar en los proximos 7 dias\n");
        }

        return contenido.toString();
    }

    private int calcularDiasRestantes(String fechaCaducidad) {
        try {
            java.time.LocalDate fechaCad = java.time.LocalDate.parse(fechaCaducidad, java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            java.time.LocalDate hoy = java.time.LocalDate.now();
            return (int) java.time.temporal.ChronoUnit.DAYS.between(hoy, fechaCad);
        } catch (Exception e) {
            return 999;
        }
    }

    @Override
    public String getTipo() {
        return "Productos_Caducar";
    }
}