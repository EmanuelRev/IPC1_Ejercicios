package utilidades;

import modelo.Vendedor;
import modelo.Cliente;
import modelo.Producto;
import modelo.ProductoTecnologia;
import modelo.ProductoGeneral;
import javax.swing.*;
import java.io.*;

public class ArchivosCSV {

    public static Vendedor[] cargarVendedorDesdeCSV(String rutaArchivo) {
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

    public static Cliente[] cargarClientesDesdeCSV(String rutaArchivo) {
        Cliente[] clientes = new Cliente[50];
        int totalCargados = 0;

        try {
            BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo));
            String linea;
            int numeroLinea = 0;

            while ((linea = lector.readLine()) != null && totalCargados < clientes.length) {
                numeroLinea++;
                if (numeroLinea == 1) continue;
                if (linea.trim().isEmpty()) continue;

                String[] datos = linea.split(",");
                if (datos.length != 5) continue;

                String codigo = datos[0].trim();
                String nombre = datos[1].trim();
                String genero = datos[2].trim().toUpperCase();
                String cumpleanos = datos[3].trim();
                String contrasenia = datos[4].trim();

                if (codigo.isEmpty() || nombre.isEmpty() || (!genero.equals("M") && !genero.equals("F")) ||
                        cumpleanos.isEmpty() || contrasenia.length() < 3) {
                    continue;
                }

                clientes[totalCargados] = new Cliente(codigo, nombre, genero, contrasenia, cumpleanos);
                totalCargados++;
            }

            lector.close();

            Cliente[] resultado = new Cliente[totalCargados];
            for (int i = 0; i < totalCargados; i++) {
                resultado[i] = clientes[i];
            }

            return resultado;

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error leyendo archivo clientes: " + e.getMessage());
            return new Cliente[0];
        }
    }

    public static Producto[] cargarProductosDesdeCSV(String rutaArchivo) {
        Producto[] productos = new Producto[100];
        int totalCargados = 0;

        try {
            BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo));
            String linea;
            int numeroLinea = 0;

            while ((linea = lector.readLine()) != null && totalCargados < productos.length) {
                numeroLinea++;
                if (numeroLinea == 1) continue;
                if (linea.trim().isEmpty()) continue;

                String[] datos = linea.split(",");
                if (datos.length != 5) continue;

                String codigo = datos[0].trim();
                String nombre = datos[1].trim();
                String categoria = datos[2].trim();
                String stockStr = datos[3].trim();
                String atributo = datos[4].trim();

                if (codigo.isEmpty() || nombre.isEmpty() || categoria.isEmpty() || stockStr.isEmpty() || atributo.isEmpty()) {
                    continue;
                }

                int stock;
                try {
                    stock = Integer.parseInt(stockStr);
                    if (stock < 0) continue;
                } catch (NumberFormatException e) {
                    continue;
                }

                Producto producto = null;
                if (categoria.equals("Tecnologia")) {
                    try {
                        int meses = Integer.parseInt(atributo);
                        producto = new ProductoTecnologia(codigo, nombre, stock, meses);
                    } catch (NumberFormatException e) {
                        continue;
                    }
                } else if (categoria.equals("Alimento") || categoria.equals("General")) {
                    producto = new ProductoGeneral(codigo, nombre, stock, atributo);
                }

                if (producto != null) {
                    productos[totalCargados] = producto;
                    totalCargados++;
                }
            }

            lector.close();

            Producto[] resultado = new Producto[totalCargados];
            for (int i = 0; i < totalCargados; i++) {
                resultado[i] = productos[i];
            }

            return resultado;

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error leyendo archivo productos: " + e.getMessage());
            return new Producto[0];
        }
    }

    public static String seleccionarArchivoCSV() {
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Seleccionar Archivo");
        selector.setCurrentDirectory(new File("datos"));

        int resultado = selector.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            return selector.getSelectedFile().getAbsolutePath();
        }
        return null;
    }
}