package controlador;

import modelo.Reporte;
import utilidades.GeneradorPDF;
import javax.swing.JOptionPane;

// controla los reportes de las clases que se usaran recoradar lo de itex



public class ControladorReportes {

    public static boolean generarReporte(Reporte reporte) {
        boolean exito = GeneradorPDF.generarPDF(reporte);

        if (exito) {
            JOptionPane.showMessageDialog(null, "Reporte generado exitosamente: " + reporte.getNombreArchivo());
        } else {
            JOptionPane.showMessageDialog(null, "Error al generar reporte");
        }

        return exito;
    }
}