package LPBCLASES;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios {

    private static final String ARCHIVO_USUARIOS = "usuarios.ser";
    private static ArrayList<Usuario> usuarios;

    // Constructor: carga los usuarios del archivo, si existen
    public GestorUsuarios() {
        // Asegurarse de que los usuarios sean cargados correctamente al instanciar la clase
        usuarios = (ArrayList<Usuario>) cargarUsuarios();

        if (usuarios == null) {
            // Si la lista está vacía, inicializarla y agregar el usuario admin por defecto
            usuarios = new ArrayList<>();
            usuarios.add(new Usuario("admin", "admin123")); // Usuario admin
            guardarUsuarios(); // Guardar la lista inicializada con el usuario admin
        }
    }

    // Método para guardar la lista de usuarios en el archivo
    public void guardarUsuarios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_USUARIOS))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar la lista de usuarios desde el archivo
    @SuppressWarnings("unchecked")
    public static List<Usuario> cargarUsuarios() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_USUARIOS))) {
            Object deserialized = ois.readObject();
            if (deserialized instanceof List<?>) {
                return (List<Usuario>) deserialized; // Si el archivo contiene una lista, retornarla
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(); // Si no se puede cargar, retornar una lista vacía
    }

    // Método para añadir un usuario a la lista
    public void agregarUsuario(String usuario, String contrasena, boolean esAdmin) {
        usuarios.add(new Usuario(usuario, contrasena));
        guardarUsuarios(); // Guardar la lista después de agregar el nuevo usuario
    }

    // Método para eliminar un usuario por su nombre de usuario
    public boolean eliminarUsuario(String nombreUsuario) {
        Usuario usuarioAEliminar = null;

        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(nombreUsuario)) {
                usuarioAEliminar = u;
                break;
            }
        }

        if (usuarioAEliminar != null) {
            usuarios.remove(usuarioAEliminar);
            guardarUsuarios(); // Guardar la lista después de eliminar el usuario
            return true; // Usuario eliminado con éxito
        }

        return false; // Usuario no encontrado
    }

    // Método para validar el inicio de sesión
    public static Usuario validarUsuario(String usuario, String contrasena) {
        if (usuarios == null) {
            usuarios = (ArrayList<Usuario>) cargarUsuarios(); // Asegurarse de que los usuarios estén cargados
        }

        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuario) && u.getContrasena().equals(contrasena)) {
                return u; // Retorna el usuario si coincide
            }
        }
        return null; // Retorna null si no coincide
    }

    // Método para obtener la lista de usuarios
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
