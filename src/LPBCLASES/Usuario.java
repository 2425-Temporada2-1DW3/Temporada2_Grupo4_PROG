package LPBCLASES;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String ARCHIVO_USUARIOS = "usuarios.ser";

    private String usuario;
    private String contrasena;
    private String rol;

    // Constructor por defecto
    public Usuario() {
        this.usuario = "";
        this.contrasena = "";
        this.rol = "Usuario"; // Por defecto, los usuarios son invitados
    }

    // Constructor copia
    public Usuario(Usuario u) {
        this.usuario = u.usuario;
        this.contrasena = u.contrasena;
        this.rol = u.rol;
    }

    // Constructor personalizado
    public Usuario(String u, String c) {
        this.usuario = u;
        this.contrasena = c;
        this.rol = "Usuario"; // Por defecto, el rol es Usuario
    }

    // Constructor personalizado con rol
    public Usuario(String u, String c, String r) {
        this.usuario = u;
        this.contrasena = c;
        this.rol = r;
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
        return "Usuario: " + usuario + " | Rol: " + rol;
    }
}
