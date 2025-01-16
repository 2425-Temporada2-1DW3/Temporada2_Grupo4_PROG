
package LPBCLASES;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Equipo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String ciudad;
	private List<Jugador> jugadores;
	private String logoPath; // Añadir el atributo logoPath

	// Constructor por defecto
	public Equipo() {
		this.nombre = "";
		this.ciudad = "";
		this.jugadores = new ArrayList<>();
		this.logoPath = ""; // Inicializar logoPath
	}

	// Constructor personalizado
	public Equipo(String nombre, String ciudad, List<Jugador> jugadores, String logoPath) {
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.jugadores = new ArrayList<>(jugadores);
		this.logoPath = logoPath; // Inicializar logoPath
	}

	// Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public List<Jugador> getJugadores() {
		return new ArrayList<>(jugadores);
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = new ArrayList<>(jugadores);
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	@Override
	public String toString() {
		return "Equipo: " + "nombre= " + nombre + '\'' + ", ciudad= " + ciudad + '\'' + ", jugadores= " + jugadores
				+ ", logoPath= " + logoPath;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, ciudad, jugadores, logoPath);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Equipo equipo = (Equipo) obj;
		return Objects.equals(nombre, equipo.nombre) && Objects.equals(ciudad, equipo.ciudad)
				&& Objects.equals(jugadores, equipo.jugadores) && Objects.equals(logoPath, equipo.logoPath);
	}
}
