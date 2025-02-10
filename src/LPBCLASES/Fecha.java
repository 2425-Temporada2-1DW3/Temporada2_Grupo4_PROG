package LPBCLASES;

import java.io.Serializable;
import java.util.Objects;

/**
 * La clase Fecha representa una fecha con día, mes y año.
 * Implementa la interfaz {@link Comparable} para permitir la comparación entre objetos Fecha.
 * También implementa {@link Serializable} para permitir la serialización del objeto.
 */
public class Fecha implements Comparable<Fecha>, Serializable {
	
	private static final long serialVersionUID = 3988696980297988958L;
	
	// Defino la clase Fecha
	// Propiedades o Atributos
	
	/** Día del mes (1-31). */
	private int day;
	
	/** Mes del año (1-12). */
	private int month;
	
	/** Año. */
	private int year;
	
	/**
     * Constructor por defecto.
     * Inicializa la fecha con valores predeterminados (01/01/2001).
     */
	public Fecha() {
		day = 1;
		month = 1;
		year = 2001;
	}
	
	/**
	 * Constructor de copia. Inicializa la fecha con los valores de otra fecha.
	 * 
	 * @param f Fecha a copiar.
	 */
	public Fecha(Fecha f) {
		this.day = f.day;
		this.month = f.month;
		this.year = f.year;
	}
	
	/**
     * Constructor con valores personalizados.
     * Establece el día, mes y año validando sus rangos.
     * 
     * @param d Día (1-31).
     * @param m Mes (1-12).
     * @param y Año (debe ser mayor a 0).
     */
	public Fecha (int d, int m, int y) {
		if (d > 0 && d < 32) {
			this.day = d;
		} else {
			this.day = 1;
		}
		
		if (m > 0 && m < 13) {
			this.month = m;
		} else {
			this.month = 1;
		}
		
		if (y > 0) {
			this.year = y;
		} else {
			this.year = 2000;
		}
	}
	
	// Getters and Setters
	
	/**
     * Obtiene el día de la fecha.
     * 
     * @return Día en formato entero.
     */
	public int getDay() {
		return day;
	}
	
	/**
	 * Establece el día de la fecha.
	 * 
	 * @param day Día a establecer (1-31).
	 */
	public void setDay(int day) {
		if (day > 0 && day < 32) {
			this.day = day;
		}
	}
	
	/**
	 * Obtiene el mes de la fecha.
	 * 
	 * @return Mes en formato entero.
	 */
	public int getMonth() {
		return month;
	}
	
	public void setMonth(int month) {
		if (month > 0 && month < 13) {
			this.month = month;
		}
	}
	
	/**
     * Obtiene el año de la fecha.
     * 
     * @return Año en formato entero.
     */
	public int getYear() {
		return year;
	}
	
	/**
	 * Establece el año de la fecha.
	 * 
	 * @param year Año a establecer (mayor a 0).
	 */
	public void setYear(int year) {
		if (year > 0) {
			this.year = year;
		}
	}
	
	// toString()
	
	/**
     * Representación en formato de cadena de la fecha.
     * 
     * @return Cadena con el formato dd/MM/yyyy.
     */
	@Override
	public String toString() {
		return String.format("%02d/%02d/%d", day, month, year);
	}

	// hashCode
	
	/**
     * Calcula el código hash del objeto Fecha basado en sus atributos.
     * 
     * @return Código hash de la instancia.
     */
	@Override
	public int hashCode() {
		return Objects.hash(day, month, year);
	}

	// equals
	
	/**
     * Compara si dos objetos Fecha son iguales basándose en sus atributos.
     * 
     * @param obj Objeto a comparar con la instancia actual.
     * @return true si ambas instancias tienen el mismo día, mes y año, false en caso contrario.
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
		Fecha other = (Fecha) obj;
		
		return this.day == other.day && this.month == other.month && this.year == other.year;
	}

	/**
     * Compara dos objetos Fecha para determinar cuál es mayor, menor o si son iguales.
     * Implementa la interfaz {@link Comparable}.
     * 
     * @param other Otra instancia de Fecha a comparar.
     * @return -1 si la fecha actual es menor, 1 si es mayor, 0 si son iguales.
     */
	@Override
	public int compareTo(Fecha other) {
		// Comparo las propiedades
		
		// Si es mayor
		if (this.year > other.year || (this.year == other.year && this.month > other.month) || (this.year == other.year && this.month == other.month && this.day > other.day)) {
			return 1;
		// Si es menor
		} else if (this.year < other.year || (this.year == other.year && this.month < other.month) || (this.year == other.year && this.month == other.month && this.day < other.day)) {
			return -1;
		// Si son iguales
		} else {
			return 0;
		}
	}

}
