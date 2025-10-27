package modelo;

import java.io.Serializable;

public class Cliente extends Usuario implements Serializable {
    private static final long serialVersionUID = 4L;

    private String fechaCumpleanos;
    private int comprasRealizadas;

    public Cliente(String codigo, String nombre, String genero, String contrasenia, String fechaCumpleanos) {
        super(codigo, nombre, genero, contrasenia, "CLIENTE");
        this.fechaCumpleanos = fechaCumpleanos;
        this.comprasRealizadas = 0;
    }

    public String getFechaCumpleanos() { return fechaCumpleanos; }
    public int getComprasRealizadas() { return comprasRealizadas; }

    public void setFechaCumpleanos(String fechaCumpleanos) { this.fechaCumpleanos = fechaCumpleanos; }
    public void aumentarCompras() { this.comprasRealizadas++; }

    public String getInfoCompleta() {
        return "Codigo: " + getCodigo() + " | Nombre: " + getNombre() +
                " | Cumpleanos: " + fechaCumpleanos + " | Compras: " + comprasRealizadas;
    }
}