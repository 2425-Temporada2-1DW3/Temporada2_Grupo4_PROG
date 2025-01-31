package LPBCLASES;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Temporada")  // Define la raíz del XML
@XmlAccessorType(XmlAccessType.FIELD)  // Permite que JAXB acceda a los campos directamente
public class Temporada implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    // Atributos de la clase Temporada
	 @XmlElement(name = "Periodo")
    private String periodo; // Año de la temporada (20XX-20XX)7
	 @XmlElement(name = "Estado")
    private String estado; // Estado de la temporada (Activa, Finalizada, En proceso)
	 @XmlElementWrapper(name = "Jornadas")  // Agrupa todas las jornadas dentro de <Jornadas>
	    @XmlElement(name = "Jornada")
    private List<Jornada> jornadas; // Lista de jornadas de la temporada
	 @XmlElementWrapper(name = "Equipos")  // Agrupa los equipos dentro de <Equipos>
	    @XmlElement(name = "Equipo")
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
    public static Temporada cargarTemporada(String periodo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/temporada_" + periodo + ".ser"))) {
            return (Temporada) ois.readObject();
        }
    }
    
    // Genera nuevas jornadas usando el método Round Robin
    public int generarJornadas() {
        if (this.equipos == null || this.equipos.isEmpty()) {
            return 0;
        }

        List<Equipo> listaEquipos = new ArrayList<>(this.equipos);
        int numeroEquipos = listaEquipos.size();

        int partidosPorJornada = numeroEquipos / 2;
        int numeroJornadas = (numeroEquipos - 1) * 2;


        for (int ronda = 0; ronda < numeroJornadas; ronda++) {
            List<Partido> partidosJornada = new ArrayList<>();

            for (int partido = 0; partido < partidosPorJornada; partido++) {
                int localIndex = (ronda + partido) % (numeroEquipos - 1);
                int visitanteIndex = (numeroEquipos - 1 - partido + ronda) % (numeroEquipos - 1);

                if (partido == 0) {
                    visitanteIndex = numeroEquipos - 1;
                }

                Equipo local = listaEquipos.get(localIndex);
                Equipo visitante = listaEquipos.get(visitanteIndex);

                if (!local.equals(visitante)) {
                    Partido partidoGenerado;

                    if (ronda >= numeroJornadas / 2) {
                        partidoGenerado = new Partido(visitante, local);
                    } else {
                        partidoGenerado = new Partido(local, visitante);
                    }

                    partidosJornada.add(partidoGenerado);
                }
            }

            if (!partidosJornada.isEmpty()) {
                Jornada jornada = new Jornada(ronda + 1, partidosJornada);
                this.jornadas.add(jornada);
            }
        }

        return this.jornadas.size();
    }

    // Representación en formato de cadena (toString)
    @Override
    public String toString() {
        // Devuelve una representación de los atributos de la temporada como texto
        return "Temporada " + periodo + "\n" +
                "Estado = " + estado + "\n" +
                "Jornadas = " + jornadas + "\n" +
                "Equipos = " + equipos;
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
    public void exportarXML(String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("<temporada>\n");
            writer.write("\t<nombre>" + this.getPeriodo() + "</nombre>\n");
            writer.write("\t<estado>" + this.getEstado() + "</estado>\n");
            writer.write("\t<equipos>\n");
            for (Equipo equipo : this.getEquipos()) {
                writer.write("\t\t<equipo>\n");
                writer.write("\t\t\t<nombre>" + equipo.getNombre() + "</nombre>\n");
                // Agregar otros atributos del equipo...
                writer.write("\t\t</equipo>\n");
            }
            writer.write("\t</equipos>\n");
            writer.write("\t<jornadas>\n");
            for (Jornada jornada : this.getJornadas()) {
                writer.write("\t\t<jornada>\n");
                writer.write("\t\t\t<numero>" + jornada.getNumero() + "</numero>\n");
                writer.write("\t\t\t<partidos>\n");
                for (Partido partido : jornada.getPartidos()) {
                    writer.write("\t\t\t\t<partido>\n");
                    writer.write("\t\t\t\t\t<equipo1>" + partido.getEquipoLocal().getNombre() + "</equipo1>\n");
                    writer.write("\t\t\t\t\t<equipo2>" + partido.getEquipoVisitante().getNombre() + "</equipo2>\n");
                    writer.write("\t\t\t\t\t<resultado>" + partido.getPuntosLocal() + "-" + partido.getPuntosVisitante() + "</resultado>\n");
                    writer.write("\t\t\t\t</partido>\n");
                }
                writer.write("\t\t\t</partidos>\n");
                writer.write("\t\t</jornada>\n");
            }
            writer.write("\t</jornadas>\n");
            writer.write("</temporada>\n");
        }
    }
}

