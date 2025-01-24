package LPBCLASES;

import java.util.Objects;

public class Partido {
	 // Atributos de la clase Partido
  private Equipo equipoLocal; // Equipo local del partido
  private Equipo equipoVisitante; // Equipo visitante del partido
  private int puntosLocal; // Puntos anotados por el equipo local
  private int puntosVisitante; // Puntos anotados por el equipo visitante

  // Constructor por defecto
  public Partido() {
      // Inicializa los atributos con valores predeterminados
      this.equipoLocal = null;
      this.equipoVisitante = null;
      this.puntosLocal = 0;
      this.puntosVisitante = 0;
  }
  
  // Constructor personalizado
  public Partido(Equipo equipoLocal, Equipo equipoVisitante) {
      // Inicializa los atributos con valores proporcionados
      this.equipoLocal = equipoLocal;
      this.equipoVisitante = equipoVisitante;
  }

  // Constructor personalizado con resultado
  public Partido(Equipo equipoLocal, Equipo equipoVisitante, int puntosLocal, int puntosVisitante) {
      // Inicializa los atributos con valores proporcionados
      this.equipoLocal = equipoLocal;
      this.equipoVisitante = equipoVisitante;
      this.puntosLocal = puntosLocal;
      this.puntosVisitante = puntosVisitante;
  }

  // Constructor copia
  public Partido(Partido p) {
      // Copia los valores de otro partido
      this.equipoLocal = p.equipoLocal;
      this.equipoVisitante = p.equipoVisitante;
      this.puntosLocal = p.puntosLocal;
      this.puntosVisitante = p.puntosVisitante;
  }

  // Getters y Setters

  // Obtiene el equipo local
  public Equipo getEquipoLocal() {
      return equipoLocal;
  }

  // Establece el equipo local
  public void setEquipoLocal(Equipo equipoLocal) {
      this.equipoLocal = equipoLocal;
  }

  // Obtiene el equipo visitante
  public Equipo getEquipoVisitante() {
      return equipoVisitante;
  }

  // Establece el equipo visitante
  public void setEquipoVisitante(Equipo equipoVisitante) {
      this.equipoVisitante = equipoVisitante;
  }

  // Obtiene los puntos del equipo local
  public int getPuntosLocal() {
      return puntosLocal;
  }

  // Establece los puntos del equipo local
  public void setPuntosLocal(int puntosLocal) {
      this.puntosLocal = puntosLocal;
  }

  // Obtiene los puntos del equipo visitante
  public int getPuntosVisitante() {
      return puntosVisitante;
  }

  // Establece los puntos del equipo visitante
  public void setPuntosVisitante(int puntosVisitante) {
      this.puntosVisitante = puntosVisitante;
  }

  // Representación en formato de cadena (toString)
  @Override
  public String toString() {
      // Devuelve una representación de los atributos del partido como texto
      return "Partido: " +
              "equipoLocal= " + equipoLocal +
              "equipoVisitante= " + equipoVisitante +
              "puntosLocal= " + puntosLocal +
              "puntosVisitante= " + puntosVisitante;
  }

  // Método hashCode
  @Override
  public int hashCode() {
      // Calcula un código hash basado en los atributos del partido
      return Objects.hash(equipoLocal, equipoVisitante, puntosLocal, puntosVisitante);
  }

  // Método equals
  @Override
  public boolean equals(Object obj) {
      // Comprueba si dos partidos son iguales comparando sus atributos
      if (this == obj) return true;
      if (obj == null || getClass() != obj.getClass()) return false;
      Partido partido = (Partido) obj;
      return puntosLocal == partido.puntosLocal &&
              puntosVisitante == partido.puntosVisitante &&
              Objects.equals(equipoLocal, partido.equipoLocal) &&
              Objects.equals(equipoVisitante, partido.equipoVisitante);
  }
}
