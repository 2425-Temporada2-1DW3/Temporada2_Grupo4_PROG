package LPBCLASES;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;
/**
 * Clase encargada de gestionar el registro de logs en la aplicación.
 * Guarda los registros en un archivo dentro de la carpeta "log" y 
 * permite registrar acciones e errores de manera sencilla.
 */
public class logClase {

    private static final Logger logger = Logger.getLogger("Applog");

    static {
        try {
            // Verificar si la carpeta "log" existe, si no, crearla
            File logDir = new File("log");
            if (!logDir.exists()) {
                if (logDir.mkdir()) {
                	
                } else {
                    System.err.println("Error al crear la carpeta 'log'.");
                }
            }

            // Configurar el FileHandler para guardar en "log/access.log"
            FileHandler fileHandler = new FileHandler("log/access.log", true);
            fileHandler.setFormatter(new CustomLogFormatter());
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false); // Evita que los logs se impriman en consola
        } catch (IOException e) {
            System.err.println("Error al configurar el logger: " + e.getMessage());
        }
    }

    /**
     * Formateador personalizado para los registros de logs.
     * Utiliza un formato con fecha y hora en el formato "[dd/MMM/yyyy:HH:mm:ss Z]".
     */
    private static class CustomLogFormatter extends Formatter {
        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("[dd/MMM/yyyy:HH:mm:ss Z]");

        /**
         * Formatea los registros de log con una fecha y el mensaje correspondiente.
         * @param record Registro de log que se va a formatear.
         * @return Cadena de texto con el log formateado.
         */
        @Override
        public String format(LogRecord record) {
            StringBuilder sb = new StringBuilder();
            sb.append(dateFormat.format(new Date(record.getMillis())))
              .append(" ")
              .append(formatMessage(record))
              .append(System.lineSeparator());
            return sb.toString();
        }
    }

    /**
     * Registra una acción en el archivo de logs.
     * @param message Mensaje que describe la acción a registrar.
     */
    public static void logAction(String message) {
        logger.info(message);
    }

    /**
     * Registra un error en el archivo de logs.
     * @param message Mensaje de error a registrar.
     * @param ex Excepción asociada al error (puede ser null si no hay excepción).
     */
    public static void logError(String message, Exception ex) {
        if (ex != null) {
            logger.severe(message + " | Detalle del error: " + ex.getMessage());
        } else {
            logger.severe(message);
        }
    }
}
