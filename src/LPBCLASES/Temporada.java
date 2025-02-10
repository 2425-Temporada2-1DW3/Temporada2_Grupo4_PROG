package LPBCLASES;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * La clase Temporada representa una temporada de una liga deportiva, 
 * gestionando información sobre su período, estado, equipos y jornadas.
 * Permite generar jornadas de partidos con un método basado en el sistema Round Robin.
 */
public class Temporada implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    // Atributos de la clase Temporada
    private String periodo; // Año de la temporada (20XX-20XX)7
    private String estado; // Estado de la temporada (Activa, Finalizada, En creación)
    private List<Jornada> jornadas; // Lista de jornadas de la temporada
    private List<Equipo> equipos; // Lista de equipos de la temporada


    /**
     * Constructor por defecto que inicializa la temporada con valores predeterminados.
     */
    public Temporada() {
        // Inicializa los atributos con valores predeterminados
        this.periodo = "";
        this.estado = "En creación";
        this.jornadas = new ArrayList<>();
        this.equipos = new ArrayList<>();
    }
    
    /**
     * Constructor que permite definir el período y estado de la temporada.
     * @param periodo Año de la temporada (ejemplo: "2023-2024").
     * @param estado Estado de la temporada.
     */
    public Temporada(String periodo, String estado) {
        this.periodo = periodo;
        this.estado = estado;
        this.jornadas = new ArrayList<>();
        this.equipos = new ArrayList<>();
    }

    /**
     * Constructor que permite definir todos los atributos de la temporada.
     * @param periodo Año de la temporada.
     * @param estado Estado de la temporada.
     * @param jornadas Lista de jornadas de la temporada.
     * @param equipos Lista de equipos de la temporada.
     */
    public Temporada(String periodo, String estado, List<Jornada> jornadas, List<Equipo> equipos) {
        this.periodo = periodo;
        this.estado = estado;
        this.jornadas = new ArrayList<>(jornadas);
        this.equipos = new ArrayList<>(equipos);
    }

    /**
     * Constructor copia que crea una nueva temporada a partir de otra existente.
     * @param t Temporada a copiar.
     */
    public Temporada(Temporada t) {
        // Copia los valores de otra temporada
        this.periodo = t.periodo;
        this.estado = t.estado;
        this.jornadas = new ArrayList<>(t.jornadas);
        this.equipos = new ArrayList<>(t.equipos);
    }

    // Getters y Setters

    /** @return El período de la temporada. */
    public String getPeriodo() {
        return periodo;
    }

    /** @param periodo Nuevo período de la temporada. */
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    
    /** @return Estado actual de la temporada. */
    public String getEstado() {
        return estado;
    }

    /** @param estado Nuevo estado de la temporada. */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /** @return Lista de jornadas de la temporada. */
    public List<Jornada> getJornadas() {
        return new ArrayList<>(jornadas);
    }

    /** @param jornadas Nueva lista de jornadas de la temporada. */
    public void setJornadas(List<Jornada> jornadas) {
        this.jornadas = new ArrayList<>(jornadas);
    }
    
    /** @return Lista de equipos de la temporada. */
    public List<Equipo> getEquipos() {
        return new ArrayList<>(equipos);
    }

    /** @param equipos Nueva lista de equipos de la temporada. */
    public void setEquipos(List<Equipo> equipos) {
        this.equipos = new ArrayList<>(equipos);
    }
    
    /**
     * Guarda la temporada en un archivo serializado.
     * @param temporada La temporada a guardar.
     * @throws IOException Si ocurre un error al escribir el archivo.
     */
    public void guardarTemporada(Temporada temporada) throws IOException {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/temporada_" + temporada.getPeriodo() + ".ser"))) {
            oos.writeObject(temporada);
        }
    }
    
    /**
     * Carga una temporada desde un archivo serializado.
     * @param periodo Período de la temporada a cargar.
     * @return La temporada cargada.
     * @throws IOException Si ocurre un error al leer el archivo.
     * @throws ClassNotFoundException Si la clase no se encuentra.
     */
    public static Temporada cargarTemporada(String periodo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/temporada_" + periodo + ".ser"))) {
            return (Temporada) ois.readObject();
        }
    }
    
    /**
     * Genera nuevas jornadas para la temporada utilizando el método Round Robin.
     * @return Número total de jornadas generadas.
     */
    public int generarJornadas() {
        if (this.equipos == null || this.equipos.isEmpty()) {
            return 0;
        }

        List<Equipo> listaEquipos = new ArrayList<>(this.equipos);
        int numeroEquipos = listaEquipos.size();

        int partidosPorJornada = numeroEquipos / 2;
        int numeroJornadas = (numeroEquipos - 1) * 2;
        
        String[] partes = periodo.split("-");
        int inicioYear = Integer.parseInt(partes[0]);
        int finYear = Integer.parseInt(partes[1]);
        
        LocalDate fechaInicio = LocalDate.of(inicioYear, 9, 1);
        LocalDate fechaFin = LocalDate.of(finYear, 6, 30);
        
        Random rand = new Random();

        LocalDate fechaPartido = fechaInicio;

        // Ajuste según el número de equipos
        int diasEntreJornadas = 14;
        if (numeroEquipos > 12) {
            diasEntreJornadas = 7;
        } else if (numeroEquipos > 8) {
            diasEntreJornadas = 10;
        }

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
                    
                    fechaPartido = fechaPartido.plusDays(diasEntreJornadas + rand.nextInt(7));

                    while (fechaPartido.getDayOfWeek() == DayOfWeek.SATURDAY || fechaPartido.getDayOfWeek() == DayOfWeek.SUNDAY) {
                        fechaPartido = fechaPartido.plusDays(1); // Avanzamos al siguiente día
                    }

                    if (fechaPartido.isAfter(fechaFin)) {
                        fechaPartido = fechaFin;
                    }

                    int hora = rand.nextInt(4) + 17;
                    int minuto = (rand.nextInt(2) == 0) ? 0 : 30;

                    if (ronda >= numeroJornadas / 2) {
                        partidoGenerado = new Partido(visitante, local, new Fecha(fechaPartido.getDayOfMonth(), fechaPartido.getMonthValue(), fechaPartido.getYear()), new Hora(hora, minuto));
                    } else {
                        partidoGenerado = new Partido(local, visitante, new Fecha(fechaPartido.getDayOfMonth(), fechaPartido.getMonthValue(), fechaPartido.getYear()), new Hora(hora, minuto));
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
    /** @return Representación de la temporada en texto. */

    @Override
    public String toString() {
        // Devuelve una representación de los atributos de la temporada como texto
        return "Temporada " + periodo + "\n" +
                "Estado = " + estado + "\n" +
                "Jornadas = " + jornadas + "\n" +
                "Equipos = " + equipos;
    }

    // Método hashCode
    /** @return Código hash basado en los atributos de la temporada. */

    @Override
    public int hashCode() {
        // Calcula un código hash basado en los atributos de la temporada
        return Objects.hash(periodo, jornadas);
    }

    // Método equals
    /** @return true si dos temporadas tienen el mismo periodo. */

    @Override
    public boolean equals(Object obj) {
        // Comprueba si dos temporadas son iguales comparando sus atributos
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Temporada temporada = (Temporada) obj;
        return periodo == temporada.periodo;
    }
    
    // toXML
    /**
     * Convierte los datos de la temporada a formato XML.
     * 
     * @return Cadena con los datos de la temporada en formato XML.
     */
    public String toXML() {
        String xml = "	<temporada>\r\n"
                   + "		<nombre>" + periodo + "</nombre>\r\n"
                   + "		<estado>" + estado + "</estado>\r\n"
                   + "		<equipos>\r\n";

			        for (Equipo equipo : this.getEquipos()) {
			            try {
							xml += equipo.toXML(cargarTemporada(periodo), equipo);
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
			        }
			
			        xml += "		</equipos>\r\n"
			             + "		<jornadas>\r\n";
			
			        for (Jornada jornada : this.getJornadas()) {
			            xml += jornada.toXML();
			        }
			
			        xml += "		</jornadas>\r\n"
			             + "	</temporada>";

        return xml;
    }
}

