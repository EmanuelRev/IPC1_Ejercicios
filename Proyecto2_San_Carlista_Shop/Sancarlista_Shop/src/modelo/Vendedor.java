package modelo;

public class Vendedor extends  Usuario {
    private int ventasConfirmadas;

    public  Vendedor(String codigo, String nombre, String genero, String contrasenia) {
        super(codigo,nombre,genero,contrasenia, "VENDEDOR");
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

