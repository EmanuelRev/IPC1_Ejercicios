package modelo;

public class ProductoTecnologia extends Producto {
    private int mesesGarantia;

    public ProductoTecnologia(String codigo, String nombre, int stock, int mesesGarantia) {
        super(codigo, nombre, "Tecnologia", stock);
        this.mesesGarantia = mesesGarantia;
    }
    public  int getMesesGarantia() {return mesesGarantia; }
    public void setMesesGarantia(int mesesGarantia) {
        this.mesesGarantia = mesesGarantia;
    }
    @Override
    public String getAtributoEspecifico() {
        return "Garantia:" + mesesGarantia + "Meses";
    }
    @Override
    public  void setAtributoEspecifico(String valor) {
        try{
            this.mesesGarantia = Integer.parseInt(valor);

        }catch (NumberFormatException e) {
            System.out.println("Error: Meses debe ser un valor numerico.");
        }
    }
}
