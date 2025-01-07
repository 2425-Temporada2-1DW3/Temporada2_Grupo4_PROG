package LPBCLASES;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Equipo {
//Atributos de la clase Equipo
  private String nombre; // Nombre del equipo
  private String ciudad; // Ciudad del equipo
  private List<Jugador> jugadores; // Lista de jugadores del equipo

  // Constructor por defecto
  public Equipo() {
      // Inicializa los atributos con valores predeterminados
      this.nombre = "";
      this.ciudad = "";
      this.jugadores = new ArrayList<>();
  }

  

  // Constructor copia
  public Equipo(Equipo otroEquipo) {
      // Copia los valores de otro equipo
      this.nombre = otroEquipo.nombre;
      this.ciudad = otroEquipo.ciudad;
      this.jugadores = new ArrayList<>(otroEquipo.jugadores); // Crea una copia de la lista de jugadores
  }
  
//Constructor personalizado
 public Equipo(String nombre, String ciudad, List<Jugador> jugadores) {
     // Inicializa los atributos con valores proporcionados
     this.nombre = nombre;
     this.ciudad = ciudad;
     this.jugadores = new ArrayList<>(jugadores); // Crea una copia de la lista de jugadores
 }

  // Getters y Setters

  // Obtiene el nombre del equipo
  public String getNombre() {
      return nombre;
  }

  // Establece el nombre del equipo
  public void setNombre(String nombre) {
      this.nombre = nombre;
  }

  // Obtiene la ciudad del equipo
  public String getCiudad() {
      return ciudad;
  }

  // Establece la ciudad del equipo
  public void setCiudad(String ciudad) {
      this.ciudad = ciudad;
  }

  // Obtiene la lista de jugadores del equipo
  public List<Jugador> getJugadores() {
      return new ArrayList<>(jugadores); // Devuelve una copia para evitar modificaciones externas
  }

  // Establece la lista de jugadores del equipo
  public void setJugadores(List<Jugador> jugadores) {
      this.jugadores = new ArrayList<>(jugadores); // Crea una copia de la lista
  }

  // Añade un jugador al equipo
  public void agregarJugador(Jugador jugador) {
      this.jugadores.add(jugador);
  }

  // Elimina un jugador del equipo
  public void eliminarJugador(Jugador jugador) {
      this.jugadores.remove(jugador);
  }

  // Representación en formato de cadena (toString)
  @Override
  public String toString() {
      // Devuelve una representación de los atributos del equipo como texto
      return "Equipo: " +
              "nombre= " + nombre + '\'' +
              ", ciudad= " + ciudad + '\'' +
              ", jugadores= " + jugadores;
  }

  // Método hashCode
  @Override
  public int hashCode() {
      // Calcula un código hash basado en los atributos del equipo
      return Objects.hash(nombre, ciudad, jugadores);
  }

  // Método equals
  @Override
  public boolean equals(Object obj) {
      // Comprueba si dos equipos son iguales comparando sus atributos
      if (this == obj) return true;
      if (obj == null || getClass() != obj.getClass()) return false;
      Equipo equipo = (Equipo) obj;
      return Objects.equals(nombre, equipo.nombre) &&
              Objects.equals(ciudad, equipo.ciudad) &&
              Objects.equals(jugadores, equipo.jugadores);
  }
}


