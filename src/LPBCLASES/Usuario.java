package LPBCLASES;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String usuario; 
	private String contrasena;
	
	// Constructor por defecto
	
	public Usuario() {
		this.usuario = "";
		this.contrasena = "";
	}
	
	// Constructor copia
	
	public Usuario(Usuario u) {
		this.usuario = u.usuario;
		this.contrasena = u.contrasena;
	}
	
	// Constructor personalizado
	
	public Usuario(String u, String c) {
		usuario = u;
		contrasena = c;
	}
	
	// Getters and Setters

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	// hashCode
	

	public int hashCode() {
		return Objects.hash(contrasena, usuario);
	}
	
	// equals


	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(contrasena, other.contrasena) && Objects.equals(usuario, other.usuario);
	}
	
	// toString


	public String toString() {
		return "Usuario [usuario=" + usuario + ", contrasena=" + contrasena + "]";
	}
	
	
	
}