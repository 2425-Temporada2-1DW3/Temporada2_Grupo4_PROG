package LPB;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import LPBCLASES.Equipo;
import LPBCLASES.Jugador;
import LPBCLASES.Temporada;

public class TEST {

    public static void main(String[] args) {
        String filePath = "data/temporada_2023-2024.ser";
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            Temporada temporada = (Temporada) ois.readObject();
            
            for (Equipo equipo : temporada.getEquipos()) {
            	for(Jugador jugador : equipo.getJugadores()) {
            		jugador.setRutaFoto("src/imagenes/temporadas/Temporada 2023-2024/" + equipo.getNombre() + "/" + jugador.getNombre() + " " + jugador.getApellidos() + ".png");
                	System.out.println("Ruta de la foto: " + jugador.getRutaFoto() + "\n");
            	}
            }
            
            temporada.guardarTemporada(temporada);
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}