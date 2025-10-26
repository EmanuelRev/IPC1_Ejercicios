package modelo;

public class ProductoGeneral  extends  Producto {

    private String material;

    public ProductoGeneral(String codigo, String nombre, int stock, String material) {
        super(codigo,nombre,"General", stock);
        this.material = material;
    }

    public  String getMaterial() {return material;}
    public void setMaterial(String material) {
        this.material = material;

    }
    @Override
    public String getAtributoEspecifico() {
        return "Material: " + material;
    }
    @Override
    public void setAtributoEspecifico(String valor) {
        this.material = valor;
    }

}
