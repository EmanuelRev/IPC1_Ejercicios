package interfaces;

// para las interfaces
public interface OperacionesCRUD {

    void crear();
    void actualizar(String codigo);
    void eliminar(String codigo);
    void mostrarTodos();
    Object buscarPorCodigo(String codigo);
}
