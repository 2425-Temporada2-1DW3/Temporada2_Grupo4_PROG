package LPBCLASES;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Equipo")  // Define la raíz del XML
@XmlAccessorType(XmlAccessType.FIELD)  // JAXB accede a los atributos directamente
public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @XmlElement(name = "Nombre")
    private String nombre;
    @XmlElement(name = "Entrenador")
    private String entrenador;
    @XmlElementWrapper(name = "Jugadores")  // Agrupa los jugadores dentro de <Jugadores>
    @XmlElement(name = "Jugador")
    private List<Jugador> jugadores;
    @XmlElement(name = "Estadio")
    private String estadio;
    @XmlElement(name = "Fundacion")
    private int fundacion;
<<<<<<< HEAD
    @XmlElement(name = "EquipoPath")
    private String equipoPath;
=======
    private String rutaFoto;
>>>>>>> main
    
    // Constructor por defecto
    public Equipo() {
        // Inicializa los atributos con valores predeterminados
        this.nombre = "";
        this.entrenador = "";
        this.jugadores = new ArrayList<>();
        this.estadio = "";
        this.fundacion = 1900;
        this.rutaFoto = "";
    }
    
  	// Constructor copia
  	public Equipo(Equipo e) {
        this.nombre = e.nombre;
        this.entrenador = e.entrenador;
        this.jugadores = e.jugadores;
        this.estadio = e.estadio;
        this.fundacion = e.fundacion;
        this.rutaFoto = e.rutaFoto;
  	}

    public Equipo(String nombre, String entrenador, List<Jugador> jugadores, String estadio, int fundacion, String rutaFoto) {
        this.nombre = nombre;
        this.entrenador = entrenador;
        this.jugadores = jugadores;
        this.estadio = estadio;
        this.fundacion = fundacion;
        this.rutaFoto = rutaFoto;
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

    public List<Jugador> getJugadores() { 
        return jugadores; 
    }

    public void setJugadores(List<Jugador> jugadores) { 
        this.jugadores = jugadores; 
    }

    public String getEstadio() { 
        return estadio; 
    }

    public void setEstadio(String estadio) { 
        this.estadio = estadio; 
    }

    public int getFundacion() { 
        return fundacion; 
    }

    public void setFundacion(int fundacion) { 
        this.fundacion = fundacion; 
    }
    
    public String getRutaFoto() { 
        return rutaFoto; 
    }

    public void setRutaFoto(String rutaFoto) { 
        this.rutaFoto = rutaFoto; 
    }

    // toString
    @Override
    public String toString() { 
        return nombre;
    }
    
    // hashCode
    @Override
	public int hashCode() {
		return Objects.hash(nombre, entrenador, jugadores, estadio, fundacion, rutaFoto);
	}
    
    // equals
    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipo other = (Equipo) obj;
		return nombre.equals(other.nombre);
	}
}
