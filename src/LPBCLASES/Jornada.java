package LPBCLASES;


import java.io.Serializable;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * La clase Jornada representa una jornada dentro de una temporada.
 * 
 * Contiene un número de jornada y una lista de partidos asociados a la misma.
 * 
 * Implementa {@link Serializable} para permitir la serialización del objeto.
 */
public class Jornada implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** Número de la jornada dentro de la temporada. */
	// Atributos de la clase Jornada
    private int numero; // Número de la jornada
    
    /** Lista de partidos programados para la jornada. */
    private List<Partido> partidos; // Lista de partidos en la jornada

    /**
     * Constructor por defecto.
     * Inicializa la jornada con número 0 y una lista vacía de partidos.
     */
    public Jornada() {
        // Inicializa los atributos con valores predeterminados
        this.numero = 0;
        this.partidos = new ArrayList<>();
    }

    /**
     * Constructor personalizado
     * 
     * @param numero Número de la jornada.
     */
    public Jornada(int numero) {
        // Inicializa los atributos con valores proporcionados
        this.numero = numero;
    }
    
    /**
     * Constructor personalizado con el número de jornada y una lista de partidos.
     * 
     * @param numero   Número de la jornada.
     * @param partidos Lista de partidos que forman parte de la jornada.
     */
    public Jornada(int numero, List<Partido> partidos) {
        // Inicializa los atributos con valores proporcionados
        this.numero = numero;
        this.partidos = new ArrayList<>(partidos); // Crea una copia de la lista de partidos
    }

    /**
     * Constructor de copia.
     * Crea una nueva instancia copiando los datos de otra jornada existente.
     * 
     * @param j Jornada a copiar.
     */
    public Jornada(Jornada j) {
        // Copia los valores de otra jornada
        this.numero = j.numero;
        this.partidos = new ArrayList<>(j.partidos); // Crea una copia de la lista de partidos
    }

    // Getters y Setters

    /**
     * Obtiene el número de la jornada.
     * 
     * @return Número de la jornada.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Establece el número de la jornada.
     * 
     * @param numero Nuevo número de jornada.
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Obtiene la lista de partidos de la jornada.
     * 
     * @return Lista de partidos.
     */
    public List<Partido> getPartidos() {
        return partidos; // Devuelve una copia para evitar modificaciones externas
    }

    /**
     * Establece la lista de partidos de la jornada.
     * 
     * @param partidos Nueva lista de partidos.
     */
    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos; // Crea una copia de la lista
    }

    /**
     * Representación en formato de cadena de la jornada.
     * 
     * @return Cadena que describe la jornada y sus partidos.
     */
    @Override
    public String toString() {
        // Devuelve una representación de los atributos de la jornada como texto
        return "Jornada " + numero +
                ", Partidos = " + partidos ;
    }

    /**
     * Calcula el código hash de la jornada basado en sus atributos.
     * 
     * @return Código hash de la jornada.
     */
    @Override
    public int hashCode() {
        // Calcula un código hash basado en los atributos de la jornada
        return Objects.hash(numero, partidos);
    }

    /**
     * Compara si dos jornadas son iguales en base a su número y lista de partidos.
     * 
     * @param obj Objeto a comparar con la instancia actual.
     * @return true si ambas jornadas son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        // Comprueba si dos jornadas son iguales comparando sus atributos
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Jornada jornada = (Jornada) obj;
        return numero == jornada.numero &&
                Objects.equals(partidos, jornada.partidos);
    }
    
    /**
     * Convierte la jornada a formato XML.
     * 
     * @return Representación XML de la jornada.
     */
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
