package LPBCLASES;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Temporada implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    // Atributos de la clase Temporada
    private String periodo; // Año de la temporada (20XX-20XX)
    private String estado; // Estado de la temporada (Activa, Finalizada, En proceso)
    private List<Jornada> jornadas; // Lista de jornadas de la temporada

    // Constructor por defecto
    public Temporada() {
        // Inicializa los atributos con valores predeterminados
        this.periodo = "";
        this.estado = "En proceso";
        this.jornadas = new ArrayList<>();
    }

    // Constructor personalizado
    public Temporada(String periodo, String estado, List<Jornada> jornadas) {
        // Inicializa los atributos con valores proporcionados
        this.periodo = periodo;
        this.estado = estado;
        this.jornadas = new ArrayList<>(jornadas); // Crea una copia de la lista de jornadas
    }

    // Constructor copia
    public Temporada(Temporada t) {
        // Copia los valores de otra temporada
        this.periodo = t.periodo;
        this.estado = t.estado;
        this.jornadas = new ArrayList<>(t.jornadas); // Crea una copia de la lista de jornadas
    }

    // Getters y Setters

    // Obtiene el año de la temporada
    public String getPeriodo() {
        return periodo;
    }

    // Establece el año de la temporada
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    
    // Obtiene el estado de la temporada
    public String getEstado() {
        return estado;
    }

    // Establece el estado de la temporada
    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Obtiene la lista de jornadas
    public List<Jornada> getJornadas() {
        return new ArrayList<>(jornadas); // Devuelve una copia para evitar modificaciones externas
    }

    // Establece la lista de jornadas
    public void setJornadas(List<Jornada> jornadas) {
        this.jornadas = new ArrayList<>(jornadas); // Crea una copia de la lista
    }

    // Añade una jornada a la temporada
    public void agregarJornada(Jornada jornada) {
        this.jornadas.add(jornada);
    }

    // Elimina una jornada de la temporada
    public void eliminarJornada(Jornada jornada) {
        this.jornadas.remove(jornada);
    }

    // Representación en formato de cadena (toString)
    @Override
    public String toString() {
        // Devuelve una representación de los atributos de la temporada como texto
        return "Temporada: " +
                "Periodo = " + periodo +
                "Jornadas = " + jornadas ;
    }

    // Método hashCode
    @Override
    public int hashCode() {
        // Calcula un código hash basado en los atributos de la temporada
        return Objects.hash(periodo, jornadas);
    }

    // Método equals
    @Override
    public boolean equals(Object obj) {
        // Comprueba si dos temporadas son iguales comparando sus atributos
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Temporada temporada = (Temporada) obj;
        return periodo == temporada.periodo;
    }
}

