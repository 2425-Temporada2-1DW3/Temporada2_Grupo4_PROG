package LPBCLASES;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Temporada {
	
    // Atributos de la clase Temporada
    private int anio; // Año de la temporada
    private List<Jornada> jornadas; // Lista de jornadas de la temporada

    // Constructor por defecto
    public Temporada() {
        // Inicializa los atributos con valores predeterminados
        this.anio = 0;
        this.jornadas = new ArrayList<>();
    }

    // Constructor personalizado
    public Temporada(int anio, List<Jornada> jornadas) {
        // Inicializa los atributos con valores proporcionados
        this.anio = anio;
        this.jornadas = new ArrayList<>(jornadas); // Crea una copia de la lista de jornadas
    }

    // Constructor copia
    public Temporada(Temporada otraTemporada) {
        // Copia los valores de otra temporada
        this.anio = otraTemporada.anio;
        this.jornadas = new ArrayList<>(otraTemporada.jornadas); // Crea una copia de la lista de jornadas
    }

    // Getters y Setters

    // Obtiene el año de la temporada
    public int getAnio() {
        return anio;
    }

    // Establece el año de la temporada
    public void setAnio(int anio) {
        this.anio = anio;
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
        return " Temporada: " +
                " año= " + anio +
                " jornadas= " + jornadas ;
    }

    // Método hashCode
    @Override
    public int hashCode() {
        // Calcula un código hash basado en los atributos de la temporada
        return Objects.hash(anio, jornadas);
    }

    // Método equals
    @Override
    public boolean equals(Object obj) {
        // Comprueba si dos temporadas son iguales comparando sus atributos
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Temporada temporada = (Temporada) obj;
        return anio == temporada.anio &&
                Objects.equals(jornadas, temporada.jornadas);
    }
}

