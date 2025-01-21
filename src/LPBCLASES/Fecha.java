package LPBCLASES;

import java.io.Serializable;
import java.util.Objects;

public class Fecha implements Comparable<Fecha>, Serializable {
	
	private static final long serialVersionUID = 3988696980297988958L;
	
	// Defino la clase Fecha
	// Propiedades o Atributos
	private int day;
	private int month;
	private int year;
	
	// Constructor por defecto
	public Fecha() {
		day = 1;
		month = 1;
		year = 2000;
	}
	
	// Constructor copia
	public Fecha(Fecha f) {
		this.day = f.day;
		this.month = f.month;
		this.year = f.year;
	}
	
	// Constructores personalizados
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
	public int getDay() {
		return day;
	}
	
	public void setDay(int day) {
		if (day > 0 && day < 32) {
			this.day = day;
		}
	}
	
	public int getMonth() {
		return month;
	}
	
	public void setMonth(int month) {
		if (month > 0 && month < 13) {
			this.month = month;
		}
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		if (year > 0) {
			this.year = year;
		}
	}
	
	// toString()
	@Override
	public String toString() {
		return (day + "/" + month + "/" + year);
	}

	// hashCode
	@Override
	public int hashCode() {
		return Objects.hash(day, month, year);
	}

	// equals
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
