package LPBCLASES;


import java.io.Serializable;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Jornada implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Atributos de la clase Jornada
    private int numero; // Número de la jornada
    private List<Partido> partidos; // Lista de partidos en la jornada

    // Constructor por defecto
    public Jornada() {
        // Inicializa los atributos con valores predeterminados
        this.numero = 0;
        this.partidos = new ArrayList<>();
    }

    // Constructor personalizado
    public Jornada(int numero) {
        // Inicializa los atributos con valores proporcionados
        this.numero = numero;
    }
    
    // Constructor personalizado que incluye la lista de partidos
    public Jornada(int numero, List<Partido> partidos) {
        // Inicializa los atributos con valores proporcionados
        this.numero = numero;
        this.partidos = new ArrayList<>(partidos); // Crea una copia de la lista de partidos
    }

    // Constructor copia
    public Jornada(Jornada j) {
        // Copia los valores de otra jornada
        this.numero = j.numero;
        this.partidos = new ArrayList<>(j.partidos); // Crea una copia de la lista de partidos
    }

    // Getters y Setters

    // Obtiene el número de la jornada
    public int getNumero() {
        return numero;
    }

    // Establece el número de la jornada
    public void setNumero(int numero) {
        this.numero = numero;
    }

    // Obtiene la lista de partidos
    public List<Partido> getPartidos() {
        return partidos; // Devuelve una copia para evitar modificaciones externas
    }

    // Establece la lista de partidos
    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos; // Crea una copia de la lista
    }

    // Representación en formato de cadena (toString)
    @Override
    public String toString() {
        // Devuelve una representación de los atributos de la jornada como texto
        return "Jornada " + numero +
                ", Partidos = " + partidos ;
    }

    // Método hashCode
    @Override
    public int hashCode() {
        // Calcula un código hash basado en los atributos de la jornada
        return Objects.hash(numero, partidos);
    }

    // Método equals
    @Override
    public boolean equals(Object obj) {
        // Comprueba si dos jornadas son iguales comparando sus atributos
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Jornada jornada = (Jornada) obj;
        return numero == jornada.numero &&
                Objects.equals(partidos, jornada.partidos);
    }
    
    // toXML
	public String toXML() {
		String xml = "			<jornada>\r\n"
				+ "				<numero>" + numero + "</numero>\r\n"
				+ "				<partidos>\r\n";
				
				for (Partido partido : this.getPartidos()) {
					xml += partido.toXML();
				}
				
				xml += "				</partidos>\r\n"
				+ "			</jornada>\r\n";
		
		return xml;
	}
}
