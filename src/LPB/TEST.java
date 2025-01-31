package LPB;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import LPBCLASES.Equipo;
import LPBCLASES.Temporada;

public class TEST {

    public static void main(String[] args) {
        String rutaArchivo = "data/temporada_2024-2025.ser";
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            Temporada temporada = (Temporada) ois.readObject();
            
            for (Equipo equipo : temporada.getEquipos()) {
            	System.out.println(equipo.getJugadores());
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
