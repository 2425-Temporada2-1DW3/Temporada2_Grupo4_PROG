package LPBCLASES;

import java.io.Serializable;
import java.util.Objects;

/**
 * La clase Hora representa una hora en formato de 24 horas con horas y minutos.
 * Implementa la interfaz {@link Comparable} para permitir la comparación entre objetos Hora.
 * También implementa {@link Serializable} para permitir la serialización del objeto.
 */
public class Hora implements Comparable<Hora>, Serializable {
	
	private static final long serialVersionUID = 3988696980297988958L;
	
	// Defino la clase Hora
	// Propiedades o Atributos
	
	/** Atributo que representa la hora (0-23). */
	private int hora;
	
	/** Atributo que representa los minutos (0-59). */
	private int minuto;
	
	/**
     * Constructor por defecto.
     * Inicializa la hora en 00:00.
     */
	public Hora() {
		hora = 0;
		minuto = 0;
	}
	
	/**
     * Constructor de copia.
     * Crea una nueva instancia copiando los valores de otra Hora.
     * 
     * @param h Objeto Hora a copiar.
     */
	public Hora(Hora h) {
		this.hora = h.hora;
		this.minuto = h.minuto;
	}
	
	/**
     * Constructor con valores personalizados.
     * Establece la hora y los minutos validados.
     * 
     * @param h Hora a establecer (debe estar entre 0 y 23).
     * @param m Minutos a establecer (deben estar entre 0 y 59).
     */
	public Hora (int h, int m) {
		if (h >= 0 && h < 24) {
			this.hora = h;
		} else {
			this.hora = 1;
		}
		
		if (m >= 0 && m < 60) {
			this.minuto = m;
		} else {
			this.minuto = 1;
		}
	}
	
	// Getters and Setters
	
	/**
     * Obtiene la hora actual.
     * 
     * @return La hora en formato entero.
     */
	public int getHora() {
		return hora;
	}
	
	/**
     * Establece una nueva hora validando su rango.
     * 
     * @param hora Nueva hora (0-23).
     */
	public void setHora(int hora) {
		if (hora > 0 && hora < 24) {
			this.hora = hora;
		}
	}
	
	/**
	 * Obtiene los minutos actuales.
	 * 
	 * @return Los minutos en formato entero.
	 */
	public int getMinuto() {
		return minuto;
	}
	
	/**
	 * Establece unos nuevos minutos validando su rango.
	 * 
	 * @param minuto Nuevos minutos (0-59).
	 */
	public void setMinuto(int minuto) {
		if (minuto > 0 && minuto < 60) {
			this.minuto = minuto;
		}
	}
	
	// toString()
	
	/**
	 * Devuelve la hora en formato de cadena.
	 * 
	 * @return La hora en formato HH:MM.
	 */
	@Override
	public String toString() {
		return String.format("%02d:%02d", hora, minuto);
	}

	// hashCode
	
	/**
     * Calcula el código hash del objeto Hora basado en sus atributos.
     * 
     * @return Código hash de la instancia.
     */
	@Override
	public int hashCode() {
		return Objects.hash(hora, minuto);
	}

	// equals
	
	/**
     * Compara si dos objetos Hora son iguales basándose en sus atributos.
     * 
     * @param obj Objeto a comparar con la instancia actual.
     * @return true si ambas instancias tienen la misma hora y minutos, false en caso contrario.
     */
	@Override
	public boolean equals(Object obj) {
		// Si es el mismo objeto...
		if (this == obj)
			return true;
		// Si el objeto no está creado
		if (obj == null)
			return false;
		// Si son de clases diferentes
		if (getClass() != obj.getClass())
			return false;
		// Comparo las propiedades
		Hora other = (Hora) obj;
		
		return this.hora == other.hora && this.minuto == other.minuto;
	}

	/**
     * Compara dos objetos Hora para determinar cuál es mayor, menor o si son iguales.
     * Implementa la interfaz {@link Comparable}.
     * 
     * @param other Otra instancia de Hora a comparar.
     * @return -1 si la hora actual es menor, 1 si es mayor, 0 si son iguales.
     */
	@Override
	public int compareTo(Hora other) {
		// Comparo las propiedades
		
		// Si es mayor
		if (this.hora > other.hora || (this.hora == other.hora && this.minuto > other.minuto)) {
			return 1;
		// Si es menor
		} else if (this.hora < other.hora || (this.hora == other.hora && this.minuto < other.minuto)) {
			return -1;
		// Si son iguales
		} else {
			return 0;
		}
	}

}
