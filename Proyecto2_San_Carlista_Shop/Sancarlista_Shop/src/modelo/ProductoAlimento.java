package modelo;

public class ProductoAlimento extends Producto {
    private String fechaCaducidad;

    public ProductoAlimento(String codigo, String nombre, int stock, String fechaCaducidad) {
        super(codigo, nombre, "Alimento", stock);
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getFechaCaducidad() { return fechaCaducidad; }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    @Override
    public String getAtributoEspecifico() {
        return "Caduca: " + fechaCaducidad;
    }

    @Override
    public void setAtributoEspecifico(String valor) {
        this.fechaCaducidad = valor;
    }

    public boolean caducaPronto() {
        return fechaCaducidad != null && fechaCaducidad.contains("2025");
    }
}
