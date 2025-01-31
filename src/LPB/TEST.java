package LPB;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import LPBCLASES.Equipo;
import LPBCLASES.Jugador;
import LPBCLASES.Temporada;

public class TEST {

    public static void main(String[] args) {
        String rutaArchivo = "data/temporada_2023-2024.ser";
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            Temporada temporada = (Temporada) ois.readObject();
            
            for (Equipo equipo : temporada.getEquipos()) {
            	for (Jugador jugador : equipo.getJugadores()) {
                    //String nombreEquipo = equipo.getNombre();
                    //String nuevaRutaFoto = "src/imagenes/temporadas/Temporada " + temporada.getPeriodo() + "/" + nombreEquipo + "/" + jugador.getNombre() + " " + jugador.getApellidos() + ".png";
                    
                    //jugador.setRutaFoto(nuevaRutaFoto);
                    System.out.println("Ruta de la foto: " + jugador.getRutaFoto());
            	}
            }
            
            //temporada.guardarTemporada(temporada);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
