package LPBCLASES;

import java.util.Objects;

//import javax.swing.ImageIcon;

import java.io.Serializable;

public class Jugador implements Comparable<Jugador>, Serializable {

	private static final long serialVersionUID = -1748708748101586932L;
	
	// Atributos de la clase Jugador
	private String nombre; // Nombre del jugador
	private String apellidos; // Apellidos del jugador
	private String posicion; // Posición en el campo (base, escolta, etc.)
	private int dorsal; // Número de camiseta del jugador
	private String fotoRuta; // Ruta de la foto del jugador

  	// Constructor por defecto
  	public Jugador() {
      	// Inicializa los atributos con valores predeterminados
      	this.nombre = "";
      	this.apellidos = "";
      	this.posicion = "";
      	this.dorsal = 0;
      	this.fotoRuta = "";
  	}


  	// Constructor copia
  	public Jugador(Jugador j) {
      	// Copia los valores de otro jugador
      	this.nombre = j.nombre;
      	this.apellidos = j.apellidos;
      	this.posicion = j.posicion;
      	this.dorsal = j.dorsal;
      	this.fotoRuta = j.fotoRuta;
  	}

  
	// Constructor personalizado
  	public Jugador(String nombre, String apellidos, String posicion, int dorsal, String fotoRuta) {
     	// Inicializa los atributos con valores proporcionados
     	this.nombre = nombre;
     	this.apellidos = apellidos;
     	this.posicion = posicion;
     	this.dorsal = dorsal;
     	this.fotoRuta = fotoRuta;
 	}
 
  	// Getters y Setters

  	// Obtiene el nombre del jugador
  	public String getNombre() {
      	return nombre;
  	}

  	// Establece el nombre del jugador
  	public void setNombre(String nombre) {
      	this.nombre = nombre;
  	}
  	
  	// Obtiene el nombre del jugador
  	public String getApellidos() {
      	return apellidos;
  	}

  	// Establece el nombre del jugador
  	public void setApellidos(String apellidos) {
      	this.apellidos = apellidos;
  	}

  	// Obtiene la posición del jugador
  	public String getPosicion() {
      	return posicion;
  	}

  	// Establece la posición del jugador
  	public void setPosicion(String posicion) {
      	this.posicion = posicion;
  	}

  	// Obtiene el número de camiseta del jugador
  	public int getDorsal() {
      	return dorsal;
  	}

  	// Establece el número de camiseta del jugador
  	public void setDorsal(int dorsal) {
      	this.dorsal = dorsal;
  	}
  	
  	// Obtiene la ruta de la foto del jugador
    public String getRutaFoto() {
        return fotoRuta;
    }

    // Establece la ruta de la foto del jugador
    public void setRutaFoto(String fotoRuta) {
        this.fotoRuta = fotoRuta;
    }

  	// Representación en formato de cadena (toString)
  	@Override
  	public String toString() {
      	// Devuelve una representación de los atributos del jugador como texto
      	return nombre + " " + apellidos + ", Posicion: " + posicion + ", Dorsal: " + dorsal;         
  	}

  	// Método hashCode
  	@Override
  	public int hashCode() {
      	// Calcula un código hash basado en los atributos del jugador
      	return Objects.hash(nombre, apellidos, posicion, dorsal, fotoRuta);
  	}

  	// Método equals
  	@Override
  	public boolean equals(Object obj) {
  		// Comprueba si dos jugadores son iguales comparando sus atributos
  		if (this == obj) return true;
  		if (obj == null || getClass() != obj.getClass()) return false;
  		Jugador jugador = (Jugador) obj;
  		return dorsal == jugador.dorsal &&
  			Objects.equals(nombre, jugador.nombre) && Objects.equals(apellidos, jugador.apellidos) &&	Objects.equals(posicion, jugador.posicion);
  	}

	// Método compareTo
	@Override
	public int compareTo(Jugador otroJugador) {
		// Compara jugadores en función de sus puntos totales
		return this.nombre.compareToIgnoreCase(otroJugador.getNombre());
	}

	// toXML
	public String toXML() {
		return null;
	}
}