package LPBCLASES;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    private String usuario;
    private String contrasena;
    private Rol rol; // Ahora usamos un atributo de tipo Rol en lugar de booleano

    // Enum para los roles
    public enum Rol {
        ADMINISTRADOR, ENTRENADOR, ARBITRO, INVITADO
    }

    // Constructor por defecto
    public Usuario() {
        this.usuario = "";
        this.contrasena = "";
        this.rol = Rol.INVITADO; // Por defecto, los usuarios son invitados
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
        this.rol = Rol.INVITADO; // Por defecto, el rol es INVITADO
    }

    // Constructor personalizado con rol
    public Usuario(String u, String c, Rol rol) {
        this.usuario = u;
        this.contrasena = c;
        this.rol = rol;
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

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
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
        return Objects.equals(contrasena, other.contrasena) 
                && Objects.equals(usuario, other.usuario) 
                && rol == other.rol;
    }

    // toString
    @Override
    public String toString() {
        return "Usuario [usuario=" + usuario + ", contrasena=" + contrasena + ", rol=" + rol + "]";
    }
}
