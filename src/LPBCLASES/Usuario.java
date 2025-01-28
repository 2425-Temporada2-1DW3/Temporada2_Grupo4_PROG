package LPBCLASES;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String ARCHIVO_USUARIOS = "data/usuarios.ser";

    private String usuario;
    private String contrasena;
    private String rol;
    private Equipo equipo;

    // Constructor por defecto
    public Usuario() {
        this.usuario = "";
        this.contrasena = "";
        this.rol = "Usuario"; // Por defecto, los usuarios son invitados
        this.equipo = new Equipo();
    }

    // Constructor copia
    public Usuario(Usuario u) {
        this.usuario = u.usuario;
        this.contrasena = u.contrasena;
        this.rol = u.rol;
        this.equipo = u.equipo;
    }

    // Constructor personalizado
    public Usuario(String u, String c) {
        this.usuario = u;
        this.contrasena = c;
        this.rol = "Usuario"; // Por defecto, el rol es Usuario
        this.equipo = new Equipo(); // Por defecto, el equipo es nulo
    }

    // Constructor personalizado con rol
    public Usuario(String u, String c, String r) {
        this.usuario = u;
        this.contrasena = c;
        this.rol = r;
        this.equipo = new Equipo();
    }
    
    // Constructor personalizado con equipo
    public Usuario(String u, String c, String r, Equipo e) {
        this.usuario = u;
        this.contrasena = c;
        this.rol = r;
        this.equipo = e;
    }

    // Getters y Setters
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(contrasena, usuario, rol);
    }

    // equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        return Objects.equals(contrasena, other.contrasena) && Objects.equals(usuario, other.usuario) && rol == other.rol;
    }

    // toString
    @Override
    public String toString() {
    	if (equipo == null) {
    		return "Usuario: " + usuario + " | Rol: " + rol;
    	} else {
    		return "Usuario: " + usuario + " | Rol: " + rol + " | Equipo: " + equipo;
    	}
    }
}
