package utilidades;

import javax.swing.JOptionPane;
//los imports jajaj
public class Validaciones {
    // formularios

    public static  boolean validarCodigoVendedor(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return  false;

        }
        return  codigo.matches("VE-\\d{3}");
    }
    // validaciones para cada clase ojo

    public  static  boolean validarGenero(String genero) {
        return  genero != null && (genero.equalsIgnoreCase("M") || genero.equalsIgnoreCase("F"));

    }
    // Validando espacios vacios
    public static boolean validarCampoVacio(String campo, String nombreCampo) {
        if (campo == null || campo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El Campo " + nombreCampo+ "Debe estar completo");
            return  false;
        }
        return true;
    }
    // Validar Contraseña xxxxxx

    public static  boolean validarContrasenia(String contrasenia) {
        if (contrasenia == null || contrasenia.length() < 4 ) {
            JOptionPane.showMessageDialog(null, "La Contraseña debe tener al menos 4 Caracteres");
            return  false;
        }
        return  true;

    }
    // Mensajes XD lleva void
    public static void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(null, "Exito" + mensaje);
    }
    public static  void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null,"Error" + mensaje);

    }
    public static  boolean confirmarEliminacion(String elemento) {
        int respuesta = JOptionPane.showConfirmDialog(null,"¿Seguro que desea Eliminar: " + elemento + "?", "Confirmar Eliminacion", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        return respuesta == JOptionPane.YES_OPTION;
    }

}
