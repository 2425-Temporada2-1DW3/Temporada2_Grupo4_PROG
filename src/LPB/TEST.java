package LPB;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import LPBCLASES.Equipo;
import LPBCLASES.Jugador;
import LPBCLASES.Temporada;

public class TEST {

    public static void main(String[] args) {
        String rutaArchivo = "data/temporada_2025-2026.ser";
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            Temporada temporada = (Temporada) ois.readObject();
            String periodo = temporada.getPeriodo();
            
            for (Equipo equipo : temporada.getEquipos()) {
                String nombreEquipo = equipo.getNombre();
                String extension = ".png";
                //String nuevaRutaFoto = "src/imagenes/temporadas/Temporada " + periodo + "/" + nombreEquipo + "/" + nombreEquipo + extension;
                
                //equipo.setRutaFoto(nuevaRutaFoto);
                System.out.println("Ruta de la foto: " + equipo.getRutaFoto());
            	
            	for (Jugador jugador : equipo.getJugadores()) {
            		String nombreJugador = jugador.getNombre();
            		String apellidoJugador = jugador.getApellidos();
                    String nuevaRutaFotoJ = "src/imagenes/temporadas/Temporada " + periodo + "/" + nombreEquipo + "/" + nombreJugador + " " + apellidoJugador + extension;
                    
                    jugador.setRutaFoto(nuevaRutaFotoJ);
                    System.out.println("Ruta de la foto: " + jugador.getRutaFoto());
            	}
            }
            
            temporada.guardarTemporada(temporada);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}