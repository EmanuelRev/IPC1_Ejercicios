package modelo;

import interfaces.OperacionesCRUD;
import java.io.Serializable;

public abstract class Producto implements OperacionesCRUD , Serializable {

    private static final long serialVersionUID = 3L;

    protected String codigo;
    protected String nombre;
    protected String categoria;
    protected int stock;

    public Producto(String codigo, String nombre, String categoria, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.stock = stock;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public int getStock() { return stock; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setStock(int stock) { this.stock = stock; }

    public abstract String getAtributoEspecifico();
    public abstract void setAtributoEspecifico(String valor);

    // ✅ AGREGAR ESTE MÉTODO NUEVO:
    public String getFechaCaducidad() {
        if ("Alimento".equals(this.categoria)) {
            return getAtributoEspecifico();
        }
        return "N/A";
    }

    public String getInfoBasica() {
        return codigo + " | " + nombre + " | " + categoria + " | Stock: " + stock;
    }

    @Override
    public void crear() {
        System.out.println("Producto Creado: " + this.codigo);
    }

    @Override
    public void actualizar(String codigo) {
        System.out.println("Producto Actualizado: " + codigo);
    }

    @Override
    public void eliminar(String codigo) {
        System.out.println("Producto Eliminado: " + codigo);
    }

    @Override
    public void mostrarTodos() {
        System.out.println("Mostrar todos los productos");
    }

    @Override
    public Object buscarPorCodigo(String codigo) {
        if (this.codigo.equals(codigo)) {
            return this;
        }
        return null;
    }
}