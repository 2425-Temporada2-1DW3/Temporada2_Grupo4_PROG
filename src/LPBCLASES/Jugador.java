package LPBCLASES;

import java.util.Objects;

import java.io.Serializable;


/**
 * Representa a un jugador de baloncesto con información básica
 * como su nombre, apellidos, posición en la cancha, dorsal y una foto.
 * Implementa Comparable para poder ordenar jugadores por nombre.
 * También es Serializable para permitir su almacenamiento.
 */

public class Jugador implements Comparable<Jugador>, Serializable {

	private static final long serialVersionUID = -1748708748101586932L;
	
	// Atributos de la clase Jugador
	private String nombre; // Nombre del jugador
	private String apellidos; // Apellidos del jugador
	private String posicion; // Posición en el campo (base, escolta, etc.)
	private int dorsal; // Número de camiseta del jugador
	private String fotoRuta; // Ruta de la foto del jugador

	 /**
   * Constructor por defecto que inicializa los atributos con valores vacíos o cero.
   */
	
	public Jugador() {
      	// Inicializa los atributos con valores predeterminados
      	this.nombre = "";
      	this.apellidos = "";
      	this.posicion = "";
      	this.dorsal = 0;
      	this.fotoRuta = "";
  	}

	 /**
   * Constructor copia que crea un nuevo jugador a partir de otro.
   * @param j Jugador del cual se copiarán los valores.
   */
  	public Jugador(Jugador j) {
      	// Copia los valores de otro jugador
      	this.nombre = j.nombre;
      	this.apellidos = j.apellidos;
      	this.posicion = j.posicion;
      	this.dorsal = j.dorsal;
      	this.fotoRuta = j.fotoRuta;
  	}

  	/**
     * Constructor con parámetros para inicializar un jugador con valores específicos.
     * @param nombre Nombre del jugador.
     * @param apellidos Apellidos del jugador.
     * @param posicion Posición en el campo.
     * @param dorsal Número de camiseta.
     * @param fotoRuta Ruta de la foto del jugador.
     */
  	public Jugador(String nombre, String apellidos, String posicion, int dorsal, String fotoRuta) {
     	// Inicializa los atributos con valores proporcionados
     	this.nombre = nombre;
     	this.apellidos = apellidos;
     	this.posicion = posicion;
     	this.dorsal = dorsal;
     	this.fotoRuta = fotoRuta;
 	}
 
  	// Getters y Setters

  	/**
     * Obtiene el nombre del jugador.
     * @return Nombre del jugador.
     */
  	public String getNombre() {
      	return nombre;
  	}
  	 /**
     * Establece el nombre del jugador.
     * @param nombre Nuevo nombre del jugador.
     */
  	public void setNombre(String nombre) {
      	this.nombre = nombre;
  	}
  	
  	/**
     * Obtiene los apellidos del jugador.
     * @return Apellidos del jugador.
     */
  	public String getApellidos() {
      	return apellidos;
  	}

  	/**
     * Establece los apellidos del jugador.
     * @param apellidos Nuevos apellidos del jugador.
     */
  	public void setApellidos(String apellidos) {
      	this.apellidos = apellidos;
  	}

  	 /**
     * Obtiene la posición del jugador en el campo.
     * @return Posición del jugador.
     */
  	public String getPosicion() {
      	return posicion;
  	}

  	 /**
     * Establece la posición del jugador en el campo.
     * @param posicion Nueva posición del jugador.
     */
  	public void setPosicion(String posicion) {
      	this.posicion = posicion;
  	}

    /**
     * Obtiene el número de camiseta del jugador.
     * @return Número de camiseta.
     */
  	public int getDorsal() {
      	return dorsal;
  	}

  	 /**
     * Establece el número de camiseta del jugador.
     * @param dorsal Nuevo número de camiseta.
     */
  	public void setDorsal(int dorsal) {
      	this.dorsal = dorsal;
  	}
  	
    /**
     * Obtiene la ruta de la foto del jugador.
     * @return Ruta de la foto.
     */
    public String getRutaFoto() {
        return fotoRuta;
    }

    /**
     * Establece la ruta de la foto del jugador.
     * @param fotoRuta Nueva ruta de la foto.
     */
    public void setRutaFoto(String fotoRuta) {
        this.fotoRuta = fotoRuta;
    }

    /**
     * Devuelve una representación en forma de texto del jugador.
     * @return Cadena con el nombre, apellidos, posición y dorsal del jugador.
     */
  	@Override
  	public String toString() {
      	// Devuelve una representación de los atributos del jugador como texto
      	return nombre + " " + apellidos + ", Posicion: " + posicion + ", Dorsal: " + dorsal;         
  	}
  	/**
     * Genera un código hash basado en los atributos del jugador.
     * @return Código hash del jugador.
     */
  	@Override
  	public int hashCode() {
      	// Calcula un código hash basado en los atributos del jugador
      	return Objects.hash(nombre, apellidos, posicion, dorsal, fotoRuta);
  	}

    /**
     * Compara si dos jugadores son iguales en base a sus atributos principales.
     * @param obj Objeto a comparar.
     * @return true si los jugadores son iguales, false en caso contrario.
     */
  	@Override
  	public boolean equals(Object obj) {
  		// Comprueba si dos jugadores son iguales comparando sus atributos
  		if (this == obj) return true;
  		if (obj == null || getClass() != obj.getClass()) return false;
  		Jugador jugador = (Jugador) obj;
  		return dorsal == jugador.dorsal &&
  			Objects.equals(nombre, jugador.nombre) && Objects.equals(apellidos, jugador.apellidos) &&	Objects.equals(posicion, jugador.posicion);
  	}

    /**
     * Compara este jugador con otro según su nombre, ignorando mayúsculas y minúsculas.
     * @param otroJugador Jugador con el que se compara.
     * @return Un valor negativo si este jugador es menor, cero si son iguales o positivo si es mayor.
     */
	@Override
	public int compareTo(Jugador otroJugador) {
		// Compara jugadores en función de sus puntos totales
		return this.nombre.compareToIgnoreCase(otroJugador.getNombre());
	}

	 /**
   * Convierte los datos del jugador a formato XML.
   * @return Cadena con los datos del jugador en formato XML.
   */
	public String toXML() {
		return ("					<jugador>\r\n"
				+ "						<nombre>" + nombre + "</nombre>\r\n"
				+ "						<apellidos>" + apellidos + "</apellidos>\r\n"
				+ "						<dorsal>" + dorsal + "</dorsal>\r\n"
				+ "						<posicion>" + posicion + "</posicion>\r\n"
				+ "					</jugador>\r\n");
	}
}
