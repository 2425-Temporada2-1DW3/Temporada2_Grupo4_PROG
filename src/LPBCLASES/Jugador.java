package LPBCLASES;

import java.util.Objects;

public class Jugador implements Comparable<Jugador> {
  // Atributos de la clase Jugador
  private String nombre; // Nombre del jugador
  private String posicion; // Posición en el campo (base, escolta, etc.)
  private int numeroCamiseta; // Número de camiseta del jugador
  private int puntosTotales; // Puntos totales acumulados por el jugador

  // Constructor por defecto
  public Jugador() {
      // Inicializa los atributos con valores predeterminados
      this.nombre = "";
      this.posicion = "";
      this.numeroCamiseta = 0;
      this.puntosTotales = 0;
  }


  // Constructor copia
  public Jugador(Jugador otroJugador) {
      // Copia los valores de otro jugador
      this.nombre = otroJugador.nombre;
      this.posicion = otroJugador.posicion;
      this.numeroCamiseta = otroJugador.numeroCamiseta;
      this.puntosTotales = otroJugador.puntosTotales;
  }

  
//Constructor personalizado
 public Jugador(String nombre, String posicion, int numeroCamiseta, int puntosTotales) {
     // Inicializa los atributos con valores proporcionados
     this.nombre = nombre;
     this.posicion = posicion;
     this.numeroCamiseta = numeroCamiseta;
     this.puntosTotales = puntosTotales;
 }
 
  // Getters y Setters

  // Obtiene el nombre del jugador
  public String getNombre() {
      return nombre;
  }

  // Establece el nombre del jugador
  public void setNombre(String nombre) {
      this.nombre = nombre;
  }

  // Obtiene la posición del jugador
  public String getPosicion() {
      return posicion;
  }

  // Establece la posición del jugador
  public void setPosicion(String posicion) {
      this.posicion = posicion;
  }

  // Obtiene el número de camiseta del jugador
  public int getNumeroCamiseta() {
      return numeroCamiseta;
  }

  // Establece el número de camiseta del jugador
  public void setNumeroCamiseta(int numeroCamiseta) {
      this.numeroCamiseta = numeroCamiseta;
  }

  // Obtiene los puntos totales del jugador
  public int getPuntosTotales() {
      return puntosTotales;
  }

  // Establece los puntos totales del jugador
  public void setPuntosTotales(int puntosTotales) {
      this.puntosTotales = puntosTotales;
  }

  // Representación en formato de cadena (toString)
  @Override
  public String toString() {
      // Devuelve una representación de los atributos del jugador como texto
      return "Jugador: " +
              "nombre= " + nombre + '\'' +
              ", posicion= " + posicion + '\'' +
              ", numeroCamiseta= " + numeroCamiseta +
              ", puntosTotales= " + puntosTotales ;
              
  }

  // Método hashCode
  @Override
  public int hashCode() {
      // Calcula un código hash basado en los atributos del jugador
      return Objects.hash(nombre, posicion, numeroCamiseta, puntosTotales);
  }

  // Método equals
  @Override
  public boolean equals(Object obj) {
      // Comprueba si dos jugadores son iguales comparando sus atributos
      if (this == obj) return true;
      if (obj == null || getClass() != obj.getClass()) return false;
      Jugador jugador = (Jugador) obj;
      return numeroCamiseta == jugador.numeroCamiseta &&
              puntosTotales == jugador.puntosTotales &&
              Objects.equals(nombre, jugador.nombre) &&
              Objects.equals(posicion, jugador.posicion);
  }

  // Método compareTo
  @Override
  public int compareTo(Jugador otroJugador) {
      // Compara jugadores en función de sus puntos totales
      return Integer.compare(this.puntosTotales, otroJugador.puntosTotales);
  }
}
