package utilidades;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Bitacora {
    private static final String ARCHIVO_BITACORA = "datos/bitacora.csv";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static void registrarOperacion(String tipoUsuario, String codigoUsuario, String operacion, String estado, String descripcion) {
        String fechaHora = LocalDateTime.now().format(formatter);
        String registro = fechaHora + " | " + tipoUsuario + " | " + codigoUsuario + " | " + operacion + " | " + estado + " | " + descripcion;

        guardarEnCSV(registro);
        System.out.println("BITACORA: " + registro);
    }

    private static void guardarEnCSV(String registro) {
        try {
            File archivo = new File(ARCHIVO_BITACORA);
            archivo.getParentFile().mkdirs();

            FileWriter writer = new FileWriter(archivo, true);
            writer.write(registro + "\n");
            writer.close();

        } catch (IOException e) {
            System.err.println("Error bitacora: " + e.getMessage());
        }
    }

    public static String[] cargarBitacoraCompleta() {
        String[] registros = new String[1000];
        int totalRegistros = 0;

        try {
            File archivo = new File(ARCHIVO_BITACORA);
            if (!archivo.exists()) {
                return new String[0];
            }

            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            String linea;

            while ((linea = reader.readLine()) != null && totalRegistros < registros.length) {
                if (!linea.trim().isEmpty()) {
                    registros[totalRegistros] = linea;
                    totalRegistros++;
                }
            }
            reader.close();

            String[] resultado = new String[totalRegistros];
            for (int i = 0; i < totalRegistros; i++) {
                resultado[i] = registros[i];
            }

            return resultado;

        } catch (IOException e) {
            System.err.println("Error cargando bitacora: " + e.getMessage());
            return new String[0];
        }
    }

    public static String[] filtrarPorFecha(String fechaInicio, String fechaFin) {
        String[] todosRegistros = cargarBitacoraCompleta();
        String[] registrosFiltrados = new String[todosRegistros.length];
        int totalFiltrados = 0;

        for (int i = 0; i < todosRegistros.length; i++) {
            if (todosRegistros[i] != null) {
                String fechaRegistro = todosRegistros[i].split("\\|")[0].trim();
                if (fechaRegistro.compareTo(fechaInicio) >= 0 && fechaRegistro.compareTo(fechaFin) <= 0) {
                    registrosFiltrados[totalFiltrados] = todosRegistros[i];
                    totalFiltrados++;
                }
            }
        }

        String[] resultado = new String[totalFiltrados];
        for (int i = 0; i < totalFiltrados; i++) {
            resultado[i] = registrosFiltrados[i];
        }

        return resultado;
    }

    public static String[] filtrarPorUsuario(String tipoUsuario) {
        String[] todosRegistros = cargarBitacoraCompleta();
        String[] registrosFiltrados = new String[todosRegistros.length];
        int totalFiltrados = 0;

        for (int i = 0; i < todosRegistros.length; i++) {
            if (todosRegistros[i] != null && todosRegistros[i].contains("| " + tipoUsuario + " |")) {
                registrosFiltrados[totalFiltrados] = todosRegistros[i];
                totalFiltrados++;
            }
        }

        String[] resultado = new String[totalFiltrados];
        for (int i = 0; i < totalFiltrados; i++) {
            resultado[i] = registrosFiltrados[i];
        }

        return resultado;
    }

    public static boolean exportarBitacora(String rutaArchivo, String[] registros) {
        try {
            FileWriter writer = new FileWriter(rutaArchivo);
            writer.write("FECHA_HORA | TIPO_USUARIO | CODIGO_USUARIO | OPERACION | ESTADO | DESCRIPCION\n");

            for (int i = 0; i < registros.length && registros[i] != null; i++) {
                writer.write(registros[i] + "\n");
            }

            writer.close();
            return true;

        } catch (IOException e) {
            System.err.println("Error exportando bitacora: " + e.getMessage());
            return false;
        }
    }

    public static String[] obtenerRegistrosIngresos() {
        String[] registros = {
                "01/12/2024 | 10:30:00 | PR-001 | 50     | VE-001",
                "01/12/2024 | 11:15:00 | PR-002 | 25     | VE-001",
                "02/12/2024 | 09:45:00 | PR-003 | 30     | VE-002"
        };
        return registros;
    }

    public static boolean exportarHistorialIngresos(String nombreArchivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            String[] registros = obtenerRegistrosIngresos();
            writer.println("Fecha,Hora,Producto,Cantidad,Vendedor");
            for (String registro : registros) {
                String[] partes = registro.split(" \\| ");
                writer.println(partes[0] + "," + partes[1] + "," + partes[2] + "," + partes[3].trim() + "," + partes[4]);
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
