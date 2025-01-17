package LPBCLASES;

import java.io.Serializable;
import java.util.List;

public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nombre;
    private String entrenador;
    private List<String> jugadores;
    private String logoPath;
    private String entrenadorPath;
    private String estadio;
    private String fundacion;

    public Equipo(String nombre, String entrenador, List<String> jugadores, String logoPath, String entrenadorPath, String estadio, String fundacion) {
        this.nombre = nombre;
        this.entrenador = entrenador;
        this.jugadores = jugadores;
        this.logoPath = logoPath;
        this.entrenadorPath = entrenadorPath;
        this.estadio = estadio;
        this.fundacion = fundacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(String entrenador) {
        this.entrenador = entrenador;
    }

    public List<String> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<String> jugadores) {
        this.jugadores = jugadores;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getEntrenadorPath() {
        return entrenadorPath;
    }

    public void setEntrenadorPath(String entrenadorPath) {
        this.entrenadorPath = entrenadorPath;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getFundacion() {
        return fundacion;
    }

    public void setFundacion(String fundacion) {
        this.fundacion = fundacion;
    }

    @Override
    public String toString() {
        return nombre;
    }

    // Método equals() y hashCode() (Importantes para la comparación y eliminación en listas)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Equipo equipo = (Equipo) obj;
        return nombre.equals(equipo.nombre); // Comparar por nombre (puedes ajustar según tus necesidades)
    }

    @Override
    public int hashCode() {
        return nombre.hashCode(); // Hash basado en el nombre (debe ser consistente con equals())
    }
}