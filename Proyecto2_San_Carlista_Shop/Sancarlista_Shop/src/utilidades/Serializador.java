package utilidades;

import java.io.File;
import java.io.*;

public class Serializador {

    // para guardar datos
    public static boolean guardarDatos(String rutaArchivo, Object datos) {
        try {
            File archivo = new File(rutaArchivo);
            archivo.getParentFile().mkdirs();

            FileOutputStream fileOut = new FileOutputStream(archivo);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(datos);
            objectOut.close();
            fileOut.close();

            System.out.println("Datos Guardadso en:" + rutaArchivo);
            return true;
        } catch (IOException e) {
            System.out.println("Error al Guardar Datos:" + e.getMessage());
            return false;
        }
    }

    public static Object cargarDatos(String rutaArchivo) {
        try {
            File archivo = new File(rutaArchivo);
            if (!archivo.exists()) {
                System.out.println("No Existe Archivo:" + rutaArchivo);
                return null;
            }
            FileInputStream fileIn = new FileInputStream(archivo);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Object datos = objectIn.readObject();
            objectIn.close();
            fileIn.close();

            System.out.println("Datos Guardados desde:" + rutaArchivo);
            return datos;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al Cargar Datos: " + e.getMessage());
            return null;
        }
        // para guardar los usuarios ajaja
    }

    public static boolean guardarUsuarios(modelo.Usuario[] usuarios, int totalUsuarios) {
        modelo.Usuario[] usuariosGuardar = new modelo.Usuario[totalUsuarios];
        for (int i = 0; i < totalUsuarios; i++) {
            usuariosGuardar[i] = usuarios[i];
        }
        return guardarDatos("datos/usuarios.ser", usuariosGuardar);
    }

    // para cargarlos xdxdxd
    public static modelo.Usuario[] cargarUsuario() {
        Object datos = cargarDatos("datos/usuarios.ser");
        if (datos instanceof modelo.Usuario[]) {
            return (modelo.Usuario[]) datos;
        }
        return null;
    }

    // Guardar Productos
    public static boolean guardarProdcutos(modelo.Producto[] productos, int totalProductos) {
        modelo.Producto[] productosGuardar = new modelo.Producto[totalProductos];
        for (int i = 0; i < totalProductos; i++) {
            productosGuardar[i] = productos[i];
        }
        return guardarDatos("datos/Productos.ser", productosGuardar);
    }

    public static modelo.Producto[] cargarProductos() {
        Object datos = cargarDatos("datos/Productos.ser");
        if (datos instanceof modelo.Producto[]) {
            return (modelo.Producto[]) datos;
        }
        return null;
    }
}

