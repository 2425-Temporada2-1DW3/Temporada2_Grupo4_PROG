package LPB.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import LPB.MenuUsuarios;
import LPBCLASES.Usuario;

import java.io.*;
import java.util.ArrayList;

public class testUsuarios {

    private MenuUsuarios menuUsuarios;

    @BeforeEach
    public void setUp() {
        // inicializamos MenuUsuarios
        menuUsuarios = new MenuUsuarios();
    }

    @Test
    public void testGuardarUsuarioNuevo() {
        // datos de prueba
        String usuario = "testUsuario";
        String contrasena = "contrasena123";
        String rol = "Administrador";

        // creamos de un nuevo usuario
        Usuario nuevoUsuario = new Usuario(usuario, contrasena, rol, null);
        
        // obtenemos la lista de usuarios
        ArrayList<Usuario> listaUsuarios = MenuUsuarios.cargarUsuarios();
        
        // agregamos el nuevo usuario
        listaUsuarios.add(nuevoUsuario);
        MenuUsuarios.guardarUsuarios(listaUsuarios);

        // verificamos si el usuario se guardó correctamente
        ArrayList<Usuario> usuariosGuardados = MenuUsuarios.cargarUsuarios();
        assertTrue(usuariosGuardados.stream().anyMatch(u -> u.getUsuario().equals(usuario)));
    }

    @Test
    public void testEliminarUsuario() {
        // datos de prueba
    	String usuario = "testUsuario";
        String contrasena = "contrasena123";
        String rol = "Administrador";

        // creamos de un nuevo usuario
        Usuario nuevoUsuario = new Usuario(usuario, contrasena, rol, null);
        ArrayList<Usuario> listaUsuarios = MenuUsuarios.cargarUsuarios();
        listaUsuarios.add(nuevoUsuario);
        MenuUsuarios.guardarUsuarios(listaUsuarios);

        // eliminamos
        listaUsuarios.removeIf(u -> u.getUsuario().equals(usuario));
        MenuUsuarios.guardarUsuarios(listaUsuarios);

        // verificamos que el usuario haya sido creado
        ArrayList<Usuario> usuariosGuardados = MenuUsuarios.cargarUsuarios();
        assertFalse(usuariosGuardados.stream().anyMatch(u -> u.getUsuario().equals(usuario)));
    }

    @Test
    public void testActualizarUsuario() {
        // datos de prueba
    	String usuario = "testUsuario";
        String contrasena = "contrasena123";
        String rol = "Administrador";

        // creamos de un nuevo usuario
        Usuario nuevoUsuario = new Usuario(usuario, contrasena, rol, null);
        ArrayList<Usuario> listaUsuarios = MenuUsuarios.cargarUsuarios();
        listaUsuarios.add(nuevoUsuario);
        MenuUsuarios.guardarUsuarios(listaUsuarios);

        // actualizamos usuario
        Usuario usuarioExistente = listaUsuarios.stream().filter(u -> u.getUsuario().equals(usuario)).findFirst().orElse(null);
        if (usuarioExistente != null) {
            usuarioExistente.setContrasena("nuevaPassword");
            usuarioExistente.setRol("NuevoRol");
        }
        MenuUsuarios.guardarUsuarios(listaUsuarios);

        // verificamos que el usuario se actualizó correctamente
        ArrayList<Usuario> usuariosGuardados = MenuUsuarios.cargarUsuarios();
        Usuario usuarioActualizado = usuariosGuardados.stream()
                .filter(u -> u.getUsuario().equals(usuario))
                .findFirst()
                .orElse(null);
        assertNotNull(usuarioActualizado);
        assertEquals("nuevaPassword", usuarioActualizado.getContrasena());
        assertEquals("NuevoRol", usuarioActualizado.getRol());
    }

    @Test
    public void testValidarUsuario() {
    	// datos de prueba
    	String usuario = "testUsuario";
        String contrasena = "contrasena123";
        String rol = "Administrador";

        // creamos de un nuevo usuario
        Usuario nuevoUsuario = new Usuario(usuario, contrasena, rol, null);
        ArrayList<Usuario> listaUsuarios = MenuUsuarios.cargarUsuarios();
        listaUsuarios.add(nuevoUsuario);
        MenuUsuarios.guardarUsuarios(listaUsuarios);

        // intentamos validar el usuario con la contraseña CORRECTA
        Usuario usuarioValidado = MenuUsuarios.validarUsuario(usuario, contrasena);
        assertNotNull(usuarioValidado);
        assertEquals(usuario, usuarioValidado.getUsuario());

        // intentamos validar el usuario con la contraseña INCORRECTA
        Usuario usuarioInvalido = MenuUsuarios.validarUsuario(usuario, "contrasenaIncorrecta");
        assertNull(usuarioInvalido);
    }

    @Test
    public void testCargarUsuariosConArchivoVacío() {
        // simulamos que el archivo de usuarios está vacío
        File archivo = new File(MenuUsuarios.ARCHIVO_USUARIOS);
        if (archivo.exists()) {
            archivo.delete();  // Elimina el archivo si existe, para que el test funcione en un entorno limpio
        }

        // verificamos que al cargar los usuarios, se cree un usuario predeterminado
        ArrayList<Usuario> usuarios = MenuUsuarios.cargarUsuarios();
        assertEquals(1, usuarios.size());
        assertEquals("Admin", usuarios.get(0).getUsuario());
    }

    @Test
    public void testCargarEquipos() {
        // cargamos los equipos desde los archivos (en este caso simulamos la carga de equipos)
        menuUsuarios.cargarEquipos();

        // verificamos si la carga de equipos es correcta.
        assertTrue(menuUsuarios.comboBoxEquipo.getItemCount() > 0);
    }
    
    @AfterEach
    public void tearDown() {
        File archivo = new File(MenuUsuarios.ARCHIVO_USUARIOS);
        if (archivo.exists()) {
            archivo.delete(); // Elimina el archivo de prueba después de cada test
        }
    }
}