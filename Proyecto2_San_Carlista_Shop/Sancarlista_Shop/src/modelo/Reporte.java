package modelo;

import java.io.Serializable;

// para generar reportes pdf


public abstract class Reporte implements Serializable {
    private static final long serialVersionUID = 10L;

    protected String titulo;
    protected String fechaGeneracion;

    public Reporte(String titulo) {
        this.titulo = titulo;
        this.fechaGeneracion = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    public abstract String generarContenido();
    public abstract String getTipo();

    public String getTitulo() { return titulo; }
    public String getFechaGeneracion() { return fechaGeneracion; }

    public String getNombreArchivo() {
        String timestamp = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss"));
        return timestamp + "_" + getTipo() + ".pdf";
    }
}