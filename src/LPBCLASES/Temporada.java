package LPBCLASES;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    private List<Equipo> equipos; // Lista de equipos de la temporada

    // Constructor por defecto
    public Temporada() {
        // Inicializa los atributos con valores predeterminados
        this.periodo = "";
        this.estado = "En proceso";
        this.jornadas = new ArrayList<>();
        this.equipos = new ArrayList<>();
    }
    
    // Constructor personalizado
    public Temporada(String periodo, String estado) {
        this.periodo = periodo;
        this.estado = estado;
        this.jornadas = new ArrayList<>();
        this.equipos = new ArrayList<>();
    }

    // Constructor personalizado con las jornadas y los equipos
    public Temporada(String periodo, String estado, List<Jornada> jornadas, List<Equipo> equipos) {
        this.periodo = periodo;
        this.estado = estado;
        this.jornadas = new ArrayList<>(jornadas);
        this.equipos = new ArrayList<>(equipos);
    }

    // Constructor copia
    public Temporada(Temporada t) {
        // Copia los valores de otra temporada
        this.periodo = t.periodo;
        this.estado = t.estado;
        this.jornadas = new ArrayList<>(t.jornadas);
        this.equipos = new ArrayList<>(t.equipos);
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
        return new ArrayList<>(jornadas);
    }

    // Establece la lista de jornadas
    public void setJornadas(List<Jornada> jornadas) {
        this.jornadas = new ArrayList<>(jornadas);
    }
    
    // Obtiene la lista de equipos
    public List<Equipo> getEquipos() {
        return new ArrayList<>(equipos);
    }

    // Establece la lista de equipos
    public void setEquipos(List<Equipo> equipos) {
        this.equipos = new ArrayList<>(equipos);
    }

    // Método para agregar un equipo
    public void agregarEquipo(String periodo, Equipo nuevoEquipo) throws IOException, ClassNotFoundException {
        // Cargar la temporada desde el archivo
        Temporada temporada = cargarTemporada(periodo);

        // Agregar el nuevo equipo a la lista de equipos
        List<Equipo> equipos = temporada.getEquipos();
        if (!equipos.contains(nuevoEquipo)) {
            equipos.add(nuevoEquipo);
            System.out.println("Equipo agregado: " + nuevoEquipo.getNombre());
        } else {
            System.out.println("El equipo ya existe en la temporada.");
        }

        // Guardar la temporada actualizada
        guardarTemporada(temporada);
    }
    
    // Método para agregar un jugador a un equipo
    public void agregarJugador(String periodo, String nombreEquipo, Jugador nuevoJugador) throws IOException, ClassNotFoundException {
        // Cargar la temporada desde el archivo
        Temporada temporada = cargarTemporada(periodo);

        // Buscar el equipo en la temporada
        Equipo equipoEncontrado = null;
        for (Equipo equipo : temporada.getEquipos()) {
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                equipoEncontrado = equipo;
                break;
            }
        }

        if (equipoEncontrado != null) {
            // Agregar el jugador al equipo si no existe ya
            List<Jugador> jugadores = equipoEncontrado.getJugadores();
            if (!jugadores.contains(nuevoJugador)) {
                jugadores.add(nuevoJugador);
                System.out.println("Jugador agregado: " + nuevoJugador.getNombre());
            } else {
                System.out.println("El jugador ya existe en el equipo.");
            }

            // Guardar la temporada actualizada
            guardarTemporada(temporada);
        } else {
            System.out.println("El equipo no existe en esta temporada.");
        }
    }
    
    // Guarda los datos de la temporada
    public void guardarTemporada(Temporada temporada) throws IOException {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/temporada_" + temporada.getPeriodo() + ".ser"))) {
            oos.writeObject(temporada);
        }
    }
    
    // Carga los datos de la temporada
    public Temporada cargarTemporada(String periodo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/temporada_" + periodo + ".ser"))) {
            return (Temporada) ois.readObject();
        }
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

