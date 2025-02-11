package LPBCLASES;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import LPB.MenuJornadas;

/**
 * La clase Equipo representa un equipo de baloncesto con su información principal,
 * incluyendo su entrenador, jugadores, estadio, año de fundación y una imagen representativa.
 * 
 * Implementa {@link Serializable} para permitir la serialización del objeto.
 */
public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Nombre del equipo. */
    private String nombre;

    /** Nombre del entrenador del equipo. */
    private String entrenador;

    /** Lista de jugadores del equipo. */
    private List<Jugador> jugadores;

    /** Nombre del estadio del equipo. */
    private String estadio;

    /** Año de fundación del equipo. */
    private int fundacion;

    /** Ruta a la imagen del equipo. */
    private String rutaFoto;
    
    /**
     * Constructor por defecto.
     * Inicializa un equipo con valores predeterminados.
     */
    public Equipo() {
        // Inicializa los atributos con valores predeterminados
        this.nombre = "";
        this.entrenador = "";
        this.jugadores = new ArrayList<>();
        this.estadio = "";
        this.fundacion = 1900;
        this.rutaFoto = "";
    }
    
    /**
     * Constructor de copia.
     * Crea una nueva instancia copiando los valores de otro equipo.
     * 
     * @param e Objeto Equipo a copiar.
     */
  	public Equipo(Equipo e) {
        this.nombre = e.nombre;
        this.entrenador = e.entrenador;
        this.jugadores = e.jugadores;
        this.estadio = e.estadio;
        this.fundacion = e.fundacion;
        this.rutaFoto = e.rutaFoto;
  	}

  	/**
     * Constructor con valores personalizados.
     * 
     * @param nombre     Nombre del equipo.
     * @param entrenador Nombre del entrenador.
     * @param jugadores  Lista de jugadores.
     * @param estadio    Nombre del estadio.
     * @param fundacion  Año de fundación del equipo.
     * @param rutaFoto   Ruta de la imagen del equipo.
     */
    public Equipo(String nombre, String entrenador, List<Jugador> jugadores, String estadio, int fundacion, String rutaFoto) {
        this.nombre = nombre;
        this.entrenador = entrenador;
        this.jugadores = jugadores;
        this.estadio = estadio;
        this.fundacion = fundacion;
        this.rutaFoto = rutaFoto;
    }

    /**
     * Obtiene el nombre del equipo.
     * 
     * @return Nombre del equipo.
     */
    public String getNombre() { 
        return nombre; 
    }

	/**
	 * Establece el nombre del equipo.
	 * 
	 * @param nombre Nombre del equipo.
	 */
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    /**
     * Obtiene el nombre del entrenador.
     * 
     * @return Nombre del entrenador.
     */
    public String getEntrenador() { 
        return entrenador; 
    }

    /**
     * Establece un nuevo entrenador para el equipo.
     * 
     * @param entrenador Nuevo nombre del entrenador.
     */
    public void setEntrenador(String entrenador) { 
        this.entrenador = entrenador; 
    }

    /**
     * Obtiene la lista de jugadores del equipo.
     * 
     * @return Lista de jugadores.
     */
    public List<Jugador> getJugadores() { 
        return jugadores; 
    }

	/**
	 * Establece una nueva lista de jugadores para el equipo.
	 * 
	 * @param jugadores Nueva lista de jugadores.
	 */
    public void setJugadores(List<Jugador> jugadores) { 
        this.jugadores = jugadores; 
    }

	/**
	 * Obtiene el nombre del estadio del equipo.
	 * 
	 * @return Nombre del estadio.
	 */
    public String getEstadio() { 
        return estadio; 
    }

	/**
	 * Establece un nuevo estadio para el equipo.
	 * 
	 * @param estadio Nuevo nombre del estadio.
	 */
    public void setEstadio(String estadio) { 
        this.estadio = estadio; 
    }

    /**
	 * Obtiene el año de fundación del equipo.
	 * 
	 * @return Año de fundación.
	 */
    public int getFundacion() { 
        return fundacion; 
    }

    /**
     * Establece un nuevo año de fundación para el equipo.
     * 
     * @param fundacion Nuevo año de fundación.
     */
    public void setFundacion(int fundacion) { 
        this.fundacion = fundacion; 
    }
    
    /**
	 * Obtiene la ruta de la imagen del equipo.
	 * 
	 * @return Ruta de la imagen.
	 */
    public String getRutaFoto() { 
        return rutaFoto; 
    }

    /**
     * Establece una nueva ruta para la imagen del equipo.
     * 
     * @param rutaFoto Nueva ruta de la imagen.
     */
    public void setRutaFoto(String rutaFoto) { 
        this.rutaFoto = rutaFoto; 
    }

    // toString
    
    /**
     * Representación en formato de cadena del equipo.
     * 
     * @return Nombre del equipo.
     */
    @Override
    public String toString() { 
    	return nombre;  
    }
    
    // hashCode
    
    /**
     * Calcula el código hash del objeto Equipo basado en sus atributos.
     * 
     * @return Código hash de la instancia.
     */
    @Override
	public int hashCode() {
		return Objects.hash(nombre, entrenador, jugadores, estadio, fundacion, rutaFoto);
	}
    
    // equals
    

    /**
     * Compara si dos objetos Equipo son iguales basándose en el nombre del equipo.
     * 
     * @param obj Objeto a comparar con la instancia actual.
     * @return true si ambos equipos tienen el mismo nombre, false en caso contrario.
     */
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
    
    // toXML
    
    /**
     * Convierte la información del equipo a formato XML.
     * 
     * @param temporada Temporada en la que está registrado el equipo.
     * @param equipo    Equipo cuya información se exportará a XML.
     * @return Representación XML del equipo.
     */
	public String toXML(Temporada temporada, Equipo equipo) {
		String xml = "			<equipo>\r\n"
				+ "				<nombre>" + nombre + "</nombre>\r\n"
				+ "				<entrenador>" + entrenador + "</entrenador>\r\n"
				+ "				<estadio>" + estadio + "</estadio>\r\n";
				xml += MenuJornadas.clasificacionXML(temporada, equipo);
				xml += "				<jugadores>\r\n";
				
				for (Jugador jugador : this.getJugadores()) {
					xml += jugador.toXML();
				}
				
				xml += "				</jugadores>\r\n"
				+ "			</equipo>\r\n";
		
		return xml;
	}
}
