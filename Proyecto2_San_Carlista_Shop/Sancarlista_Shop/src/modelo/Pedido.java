package modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pedido implements Serializable {
    private static final long serialVersionUID = 11L;

    private String codigo;
    private String codigoCliente;
    private String nombreCliente;
    private String productos;
    private double total;
    private String fechaGeneracion;
    private boolean confirmado;

    public Pedido(String codigo, String codigoCliente, String nombreCliente, String productos, double total) {
        this.codigo = codigo;
        this.codigoCliente = codigoCliente;
        this.nombreCliente = nombreCliente;
        this.productos = productos;
        this.total = total;
        this.fechaGeneracion = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        this.confirmado = false;
    }

    public String getCodigo() { return codigo; }
    public String getCodigoCliente() { return codigoCliente; }
    public String getNombreCliente() { return nombreCliente; }
    public String getProductos() { return productos; }
    public double getTotal() { return total; }
    public String getFechaGeneracion() { return fechaGeneracion; }
    public boolean isConfirmado() { return confirmado; }


    public String getResumenProductos() {
        return productos;
    }

    public void confirmar() { this.confirmado = true; }

    public String getInfoBasica() {
        return codigo + " | " + fechaGeneracion + " | " + nombreCliente + " | Q" + total +
                " | " + (confirmado ? "CONFIRMADO" : "PENDIENTE");
    }
}