package LPBCLASES;

import java.io.Serializable;
import java.util.List;

public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nombre;
    private String entrenador;
    private List<PlayerWithImage> jugadores; // Almacena jugadores con nombres e imágenes
    private String logoPath;
    private String entrenadorPath;
    private String estadio;
    private String fundacion;

    public Equipo(String nombre, String entrenador, List<PlayerWithImage> jugadores, String logoPath, String entrenadorPath, String estadio, String fundacion) {
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

    public List<PlayerWithImage> getJugadores() { 
        return jugadores; 
    }

    public void setJugadores(List<PlayerWithImage> jugadores) { 
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

    public static class PlayerWithImage implements Serializable {
        private static final long serialVersionUID = 1L;
        private String name;
        private String imagePath;

        public PlayerWithImage(String name, String imagePath) {
            this.name = name;
            this.imagePath = imagePath;
        }

        public String getName() { 
            return name; 
        }

        public void setName(String name) { 
            this.name = name; 
        }

        public String getImagePath() { 
            return imagePath; 
        }

        public void setImagePath(String imagePath) { 
            this.imagePath = imagePath; 
        }

        @Override
        public String toString() { 
            return name; 
        }
    }
}
