package LPB;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Random;

import LPBCLASES.Fecha;
import LPBCLASES.Hora;
import LPBCLASES.Jornada;
import LPBCLASES.Partido;
import LPBCLASES.Temporada;

public class TEST {

    public static void main(String[] args) {
        String rutaArchivo = "data/temporada_2023-2024.ser";
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            Temporada temporada = (Temporada) ois.readObject();
            
            LocalDate fechaInicio = LocalDate.of(2023, 9, 1); // Fecha de inicio de la temporada
            Random rand = new Random();
            
            // Generamos fechas para cada jornada
            for (int i = 0; i < temporada.getJornadas().size(); i++) {
                Jornada jornada = temporada.getJornadas().get(i);
                LocalDate fechaJornada = fechaInicio.plusDays(rand.nextInt(7) + 7); // Agrega de 7 a 14 días por jornada
                
                // Encontramos un día de la semana que no sea sábado ni domingo
                while (fechaJornada.getDayOfWeek() == DayOfWeek.SATURDAY || fechaJornada.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    fechaJornada = fechaJornada.plusDays(1); // Avanzamos un día si cae en fin de semana
                }

                // Asignamos la fecha y hora a cada partido de la jornada
                for (Partido partido : jornada.getPartidos()) {
                    // Asignamos la fecha, cada partido tiene un día diferente en la misma semana
                    partido.setFecha(new Fecha(fechaJornada.getDayOfMonth(), fechaJornada.getMonthValue(), fechaJornada.getYear()));
                    
                    // Generación de hora aleatoria entre las 17:00 y las 20:00
                    int horaAleatoria = rand.nextInt(4) + 17;  // Genera una hora entre 17 y 20
                    int minutoAleatorio = (rand.nextInt(2) == 0) ? 0 : 30;  // 0 para en punto, 30 para media hora
                    
                    // Asignamos la hora aleatoria (entre 17:00 y 20:00)
                    partido.setHora(new Hora(horaAleatoria, minutoAleatorio));

                    // Avanzamos al siguiente día laborable dentro de la semana
                    fechaJornada = fechaJornada.plusDays(1);
                    while (fechaJornada.getDayOfWeek() == DayOfWeek.SATURDAY || fechaJornada.getDayOfWeek() == DayOfWeek.SUNDAY) {
                        fechaJornada = fechaJornada.plusDays(1); // Evita el fin de semana
                    }
                }

                // Actualiza la fecha de inicio para la siguiente jornada
                fechaInicio = fechaJornada.plusDays(rand.nextInt(7) + 7);
            }
            
            // Imprimir los resultados para verificar
            for (Jornada jornada : temporada.getJornadas()) {
                System.out.println("Jornada " + jornada.getNumero());
                for (Partido partido : jornada.getPartidos()) {
                    System.out.println(partido.getEquipoLocal() + " vs " + partido.getEquipoVisitante() + " - Fecha: " 
                                       + partido.getFecha() + " - Hora: " + partido.getHora());
                }
            }
            
            temporada.guardarTemporada(temporada);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
