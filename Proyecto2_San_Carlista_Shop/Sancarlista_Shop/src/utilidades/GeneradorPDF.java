package utilidades;

import modelo.Reporte;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import java.io.IOException;

public class GeneradorPDF {

    public static boolean generarPDF(Reporte reporte) {
        String nombreArchivo = "reportes/" + reporte.getNombreArchivo();

        try {
            PdfWriter writer = new PdfWriter(nombreArchivo);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph(reporte.getTitulo()).setFontSize(16));
            document.add(new Paragraph("Fecha: " + reporte.getFechaGeneracion()));
            document.add(new Paragraph(" "));

            String contenido = reporte.generarContenido();
            document.add(new Paragraph(contenido));

            document.close();

            Bitacora.registrarOperacion("SISTEMA", "ADMIN", "GENERAR_REPORTE", "EXITOSO", "Reporte: " + nombreArchivo);
            return true;

        } catch (IOException e) {
            Bitacora.registrarOperacion("SISTEMA", "ADMIN", "GENERAR_REPORTE", "FALLIDO", "Error: " + e.getMessage());
            return false;
        }
    }
}