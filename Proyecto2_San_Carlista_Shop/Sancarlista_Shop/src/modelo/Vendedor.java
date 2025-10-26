package modelo;

import java.io.Serializable;
public class Vendedor extends Usuario implements  Serializable {

    private static  final long serialVersionUID = 2L;

    private int ventasConfirmadas;

    // aqui le quite vendodero en super
    public Vendedor(String codigo, String nombre, String genero, String contrasenia) {
        super(codigo, nombre, genero, contrasenia);
        this.ventasConfirmadas = 0;
    }

    public void aumentarVentas() {
        ventasConfirmadas++;
    }

    public int getVentasConfirmadas() {
        return ventasConfirmadas;
    }

    public String getInfoCompleta() {
        return "Codigo: " + getCodigo() +
                "| Nombre:v" + getNombre() +
                "| Genero: " + getGenero() +
                "| Ventas: " + ventasConfirmadas;
    }
}
