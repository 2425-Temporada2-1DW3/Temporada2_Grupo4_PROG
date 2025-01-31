package LPBCLASES;

import java.io.Serializable;
import java.util.Objects;

public class Hora implements Comparable<Hora>, Serializable {
	
	private static final long serialVersionUID = 3988696980297988958L;
	
	// Defino la clase Hora
	// Propiedades o Atributos
	private int hora;
	private int minuto;
	
	// Constructor por defecto
	public Hora() {
		hora = 0;
		minuto = 0;
	}
	
	// Constructor copia
	public Hora(Hora h) {
		this.hora = h.hora;
		this.minuto = h.minuto;
	}
	
	// Constructores personalizados
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
	public int getHora() {
		return hora;
	}
	
	public void setHora(int hora) {
		if (hora > 0 && hora < 24) {
			this.hora = hora;
		}
	}
	
	public int getMinuto() {
		return minuto;
	}
	
	public void setMinuto(int minuto) {
		if (minuto > 0 && minuto < 60) {
			this.minuto = minuto;
		}
	}
	
	// toString()
	@Override
	public String toString() {
		return String.format("%02d:%02d", hora, minuto);
	}

	// hashCode
	@Override
	public int hashCode() {
		return Objects.hash(hora, minuto);
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
		Hora other = (Hora) obj;
		
		return this.hora == other.hora && this.minuto == other.minuto;
	}

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
