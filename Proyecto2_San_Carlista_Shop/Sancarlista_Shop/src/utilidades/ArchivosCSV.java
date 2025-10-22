package utilidades;

import modelo.Vendedor;
import javax.swing.*;
import java.io.*;

// colocar los demas imports dino F


public class ArchivosCSV {

    public static  Vendedor[] cargarVendedorDesdeCSV(String rutaArchivo) {
        Vendedor[] vendedores = new Vendedor[50];
        int totalCargados = 0;

        try {
            BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo));
            String linea;
            int numeroLinea = 0;

            while ((linea = lector.readLine()) != null && totalCargados < vendedores.length) {
                numeroLinea++;

                if (numeroLinea == 1) continue;
                if (linea.trim().isEmpty()) continue;

                String[] datos = linea.split(",");
                if (datos.length != 4) continue;

                String codigo = datos[0].trim();
                String nombre = datos[1].trim();
                String genero = datos[2].trim().toUpperCase();
                String contrasenia = datos[3].trim();


                if (codigo.isEmpty() || nombre.isEmpty() || (!genero.equals("M") && !genero.equals("F")) || contrasenia.length() < 4) {
                    continue;
                }

                // creando perfiles con los listas xxxxxxxxx

                vendedores[totalCargados] = new Vendedor(codigo, nombre, genero, contrasenia);
                totalCargados++;
            }
            lector.close();

            Vendedor[] resultado = new Vendedor[totalCargados];
            for (int i = 0; i < totalCargados; i++) {
                resultado[i] = vendedores[i];
            }
            return resultado;


        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error Verificando Archivo:" + e.getMessage());
            return new Vendedor[0];
        }

    }

    public  static  String seleccionarArchivoCSV() {
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Seleccionar Archivo");
        selector.setCurrentDirectory(new File("datos"));

        int resultado = selector.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION)  {
            return selector.getSelectedFile().getAbsolutePath();
        }
        return null;
    }
}
