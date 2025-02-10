package LPBCLASES;

import java.io.Serializable;
import java.util.Objects;
/**
 * Representa un partido de baloncesto entre dos equipos.
 * Contiene información sobre los equipos participantes, el resultado,
 * la fecha y la hora en que se juega el partido.
 */
public class Partido implements Serializable {

	private static final long serialVersionUID = 1L;
	// Atributos de la clase Partido
	private Equipo equipoLocal; // Equipo local del partido
	private Equipo equipoVisitante; // Equipo visitante del partido
	private int puntosLocal; // Puntos anotados por el equipo local
	private int puntosVisitante; // Puntos anotados por el equipo visitante
	private Fecha fecha;
	private Hora hora;

	 /**
   * Constructor por defecto que inicializa los valores con datos predeterminados.
   */
	public Partido() {
		// Inicializa los atributos con valores predeterminados
		this.equipoLocal = null;
		this.equipoVisitante = null;
		this.puntosLocal = 0;
		this.puntosVisitante = 0;
		this.fecha = new Fecha();
		this.hora = new Hora();
	}
  
	/**
   * Constructor que inicializa un partido con los equipos, fecha y hora especificados.
   * El resultado se inicializa en 0-0.
   * 
   * @param equipoLocal Equipo que juega como local.
   * @param equipoVisitante Equipo que juega como visitante.
   * @param f Fecha del partido.
   * @param h Hora del partido.
   */
	public Partido(Equipo equipoLocal, Equipo equipoVisitante, Fecha f, Hora h) {
		// Inicializa los atributos con valores proporcionados
		this.equipoLocal = equipoLocal;
		this.equipoVisitante = equipoVisitante;
		this.puntosLocal = 0;
		this.puntosVisitante = 0;
		this.fecha = new Fecha(f);
		this.hora = new Hora(h);
	}

  /**
   * Constructor que inicializa un partido con los equipos, el resultado, la fecha y la hora.
   * 
   * @param equipoLocal Equipo que juega como local.
   * @param equipoVisitante Equipo que juega como visitante.
   * @param puntosLocal Puntos anotados por el equipo local.
   * @param puntosVisitante Puntos anotados por el equipo visitante.
   * @param f Fecha del partido.
   * @param h Hora del partido.
   */	public Partido(Equipo equipoLocal, Equipo equipoVisitante, int puntosLocal, int puntosVisitante, Fecha f, Hora h) {
		// Inicializa los atributos con valores proporcionados
		this.equipoLocal = equipoLocal;
		this.equipoVisitante = equipoVisitante;
		this.puntosLocal = puntosLocal;
		this.puntosVisitante = puntosVisitante;
		this.fecha = new Fecha(f);
		this.hora = new Hora(h);
	}

   /**
    * Constructor copia que crea un partido duplicando otro partido existente.
    * 
    * @param p Partido a copiar.
    */	public Partido(Partido p) {
		// Copia los valores de otro partido
		this.equipoLocal = p.equipoLocal;
		this.equipoVisitante = p.equipoVisitante;
		this.puntosLocal = p.puntosLocal;
		this.puntosVisitante = p.puntosVisitante;
		this.fecha = p.fecha;
		this.hora = p.hora;
	}

	// Getters y Setters


    /**
     * Obtiene el equipo local del partido.
     * 
     * @return El equipo local.
     */	public Equipo getEquipoLocal() {
		return equipoLocal;
	}

     /**
      * Establece el equipo local del partido.
      * 
      * @param equipoLocal Nuevo equipo local.
      */	public void setEquipoLocal(Equipo equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

      /**
       * Obtiene el equipo visitante del partido.
       * 
       * @return El equipo visitante.
       */	public Equipo getEquipoVisitante() {
		return equipoVisitante;
	}


       /**
        * Establece el equipo visitante del partido.
        * 
        * @param equipoVisitante Nuevo equipo visitante.
        */	public void setEquipoVisitante(Equipo equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}

        /**
         * Obtiene los puntos anotados por el equipo local.
         * 
         * @return Puntos del equipo local.
         */	public int getPuntosLocal() {
		return puntosLocal;
	}

         /**
          * Establece los puntos anotados por el equipo local.
          * 
          * @param puntosLocal Nuevos puntos del equipo local.
          */  	public void setPuntosLocal(int puntosLocal) {
		this.puntosLocal = puntosLocal;
  	}

          /**
           * Obtiene los puntos anotados por el equipo visitante.
           * 
           * @return Puntos del equipo visitante.
           */	public int getPuntosVisitante() {
		return puntosVisitante;
	}

           /**
            * Establece los puntos anotados por el equipo visitante.
            * 
            * @param puntosVisitante Nuevos puntos del equipo visitante.
            */	public void setPuntosVisitante(int puntosVisitante) {
		this.puntosVisitante = puntosVisitante;
	}
	
            /**
             * Obtiene la fecha del partido.
             * 
             * @return Fecha del partido.
             */	public Fecha getFecha() {
		return fecha;
	}
	
             /**
              * Establece la fecha del partido.
              * 
              * @param fecha Nueva fecha del partido.
              */	public void setFecha(Fecha fecha) {
		this.fecha = new Fecha(fecha);
	}
	
              /**
               * Obtiene la hora del partido.
               * 
               * @return Hora del partido.
               */	
   public Hora getHora() {
		return hora;
	}
	
   /**
   * Establece la hora del partido.
   * 
   * @param hora Nueva hora del partido.
   */	
   public void setHora(Hora hora) {
		this.hora = new Hora(hora);
	}

  /**
  * Representación en forma de texto del partido.
  * 
  * @return Cadena con la información del partido.
  */	@Override
	public String toString() {
		// Devuelve una representación de los atributos del partido como texto
		return "Partido: " +
			"Equipo Local = " + equipoLocal +
			"Equipo Visitante = " + equipoVisitante +
			"Puntos Local = " + puntosLocal +
			"Puntos Visitante = " + puntosVisitante +
			"Fecha = " + fecha +
			"Hora = " + hora;
			
	}

	// Método hashCode
  /**
  * Genera un código hash basado en los atributos del partido.
  * 
  * @return Código hash del partido.
  */
	@Override
	public int hashCode() {
		// Calcula un código hash basado en los atributos del partido
		return Objects.hash(equipoLocal, equipoVisitante, puntosLocal, puntosVisitante, fecha, hora);
	}

	// Método equals
	 /**
   * Compara si dos partidos son iguales en base a sus atributos principales.
   * 
   * @param obj Objeto a comparar.
   * @return true si los partidos son iguales, false en caso contrario.
   */
	@Override
	public boolean equals(Object obj) {
		// Comprueba si dos partidos son iguales comparando sus atributos
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Partido partido = (Partido) obj;
		return puntosLocal == partido.puntosLocal && puntosVisitante == partido.puntosVisitante && Objects.equals(equipoLocal, partido.equipoLocal) && Objects.equals(equipoVisitante, partido.equipoVisitante);
	}
	
    // toXML
	/**
   * Convierte los datos del partido a formato XML.
   * 
   * @return Cadena con los datos del partido en formato XML.
   */
	public String toXML() {
		String xml = "					<partido>\r\n"
				+ "						<fecha>" + fecha + "</fecha>\r\n"
				+ "						<hora>" + hora + "</hora>\r\n"
				+ "						<equipo1>" + equipoLocal.getNombre() + "</equipo1>\r\n"
				+ "						<equipo2>" + equipoVisitante.getNombre() + "</equipo2>\r\n"
				+ "						<resultado>" + puntosLocal + "-" + puntosVisitante + "</resultado>\r\n"
				+ "					</partido>\r\n";
		
		return xml;
	}
}
