package LPBCLASES;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

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

    // Formateador personalizado
    private static class CustomLogFormatter extends Formatter {
        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("[dd/MMM/yyyy:HH:mm:ss Z]");

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

    // Métodos públicos para registrar logs
    public static void logAction(String message) {
        logger.info(message);
    }

    public static void logError(String message, Exception ex) {
        if (ex != null) {
            logger.severe(message + " | Detalle del error: " + ex.getMessage());
        } else {
            logger.severe(message);
        }
    }
}
