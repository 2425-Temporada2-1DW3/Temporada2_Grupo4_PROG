package LPB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import LPBCLASES.TextoRedondeado;
import LPBCLASES.BotonRedondeado;
import LPBCLASES.Equipo;
import LPBCLASES.Jugador;
import LPBCLASES.Temporada;

/**
 * Clase AgregarTemporada representa una ventana para crear y gestionar nuevas temporadas en la liga.
 * 
 * Permite definir el período de la temporada, su estado y, opcionalmente, importar equipos de temporadas anteriores.
 * 
 * Extiende {@link JFrame} para su representación gráfica.
 */
public class AgregarTemporada extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField periodoField;
    private JPanel panel;
    private JComboBox<String> estadoComboBox;
    private JLabel lblPeriodo, lblEstado;
    private BotonRedondeado btnGuardar, btnCancelar;

	/**
	 * Constructor de la clase AgregarTemporada.
	 * 
	 * Crea una nueva ventana para agregar una temporada a la liga.
	 */
    public AgregarTemporada() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
        setTitle("Agregar Temporada");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        // Configuración del panel izquierdo
        panel = new JPanel(null);
        panel.setBounds(0, 0, 500, 300);
        panel.setBackground(new Color(255, 243, 205));
        getContentPane().add(panel);

        // Etiqueta y campo para el período
        lblPeriodo = new JLabel("Período de la temporada:");
        lblPeriodo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblPeriodo.setBounds(21, 52, 200, 30);
        panel.add(lblPeriodo);

        periodoField = new TextoRedondeado(20);
        periodoField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        periodoField.setBounds(241, 52, 200, 30);
        panel.add(periodoField);

        // Etiqueta y combo box para el estado
        lblEstado = new JLabel("Estado de la temporada:");
        lblEstado.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblEstado.setBounds(21, 112, 200, 30);
        panel.add(lblEstado);

        estadoComboBox = new JComboBox<>(new String[]{"Activa", "Finalizada", "En creación"});
        estadoComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
        estadoComboBox.setBounds(241, 112, 200, 30);
        panel.add(estadoComboBox);
        estadoComboBox.setSelectedIndex(2);

        // Botón Guardar
        btnGuardar = new BotonRedondeado("Guardar", null);
        btnGuardar.setBounds(232, 200, 100, 40);
        btnGuardar.setBackground(new Color(0x13427e));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnGuardar.setFocusPainted(false);
        btnGuardar.addActionListener(new ActionListener() {
        	
			/**
			 * Método que se ejecuta al hacer clic en el botón Guardar.
			 * 
			 * Crea una nueva temporada con los datos ingresados y la guarda en un archivo.
			 * 
			 * Muestra un mensaje de confirmación y cierra la ventana.
			 * 
			 * Si se selecciona la opción de importar equipos de una temporada anterior, se muestra un diálogo para seleccionar la temporada de origen.
			 * 
			 * Si se selecciona una temporada, se cargan los equipos de la temporada seleccionada.
			 * 
			 * Si no hay temporadas disponibles para importar, se muestra un mensaje de advertencia.
			 * 
			 * Si ocurre un error al cargar la temporada seleccionada, se muestra un mensaje de error.
			 * 
			 * Si ocurre un error al guardar la temporada, se muestra un mensaje de error.
			 * 
			 * Finalmente, se muestra un mensaje de confirmación y se cierra la ventana.
			 * 
			 * @param ae evento de acción
			 */
		    public void actionPerformed(ActionEvent ae) {
		    	try {
		    	    Temporada nuevaTemporada = new Temporada();
		    	    nuevaTemporada.setPeriodo(periodoField.getText());
		    	    nuevaTemporada.setEstado((String) estadoComboBox.getSelectedItem());
		    	    nuevaTemporada.setEquipos(new ArrayList<>());
		    	    nuevaTemporada.setJornadas(new ArrayList<>());
		    	    
		    	    int opcion = JOptionPane.showConfirmDialog(getContentPane(), "¿Desea importar equipos de una temporada anterior?", "Importar Equipos", JOptionPane.YES_NO_OPTION);

		    	    if (opcion == JOptionPane.YES_OPTION) {
		    	    	File folder = new File("data");
		    	    	File[] files = folder.listFiles((dir, name) -> name.endsWith(".ser") && name.startsWith("temporada_"));

		    	    	if (files != null && files.length > 0) {
		    	    	    String[] opciones = new String[files.length];
		    	    	    for (int i = 0; i < files.length; i++) {
		    	    	        opciones[i] = "Temporada " + files[i].getName().replace("temporada_", "").replace(".ser", "");
		    	    	    }

		    	    	    String seleccion = (String) JOptionPane.showInputDialog(getContentPane(), "Seleccione la temporada de origen:", "Seleccionar Temporada", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

		    	    	    if (seleccion != null) {
		    	    	        String periodoSeleccionado = seleccion.replace("Temporada ", "");
		    	    	        File archivoSeleccionado = new File("data/temporada_" + periodoSeleccionado + ".ser");

		    	    	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoSeleccionado))) {
		    	    	            Temporada temporadaOrigen = (Temporada) ois.readObject();
		    	    	            nuevaTemporada.setEquipos(new ArrayList<>(temporadaOrigen.getEquipos()));

		    	    	            File carpetaOrigen = new File("src/imagenes/temporadas/Temporada " + periodoSeleccionado);
		    	    	            File carpetaDestino = new File("src/imagenes/temporadas/Temporada " + nuevaTemporada.getPeriodo());

		    	    	            if (!carpetaDestino.exists()) {
		    	    	                carpetaDestino.mkdirs();
		    	    	            }

		    	    	            File[] archivos = carpetaOrigen.listFiles();
		    	    	            if (archivos != null) {
		    	    	                for (File archivo : archivos) {
		    	    	                    Path archivoOrigen = archivo.toPath();
		    	    	                    Path archivoDestino = new File(carpetaDestino, archivo.getName()).toPath();

		    	    	                    if (archivo.isDirectory()) {
		    	    	                        Files.createDirectories(archivoDestino);
		    	    	                        try (Stream<Path> stream = Files.walk(archivoOrigen)) {
		    	    	                            stream.forEach(source -> {
		    	    	                                try {
		    	    	                                    Files.copy(source, archivoDestino.resolve(archivoOrigen.relativize(source)), StandardCopyOption.REPLACE_EXISTING);
		    	    	                                } catch (IOException e) {
		    	    	                                    e.printStackTrace();
		    	    	                                }
		    	    	                            });
		    	    	                        }
		    	    	                    } else {
		    	    	                        Files.copy(archivoOrigen, archivoDestino, StandardCopyOption.REPLACE_EXISTING);
		    	    	                    }
		    	    	                }
		    	    	            }

		    	    	            // Actualizar las rutas de las fotos de los equipos
		    	    	            for (Equipo equipo : nuevaTemporada.getEquipos()) {
		    	    	                File fotoArchivo = new File("src/imagenes/temporadas/Temporada " + periodoSeleccionado + "/" + equipo.getNombre() + "/" + equipo.getNombre() + ".png");
		    	    	                String nombreFoto = fotoArchivo.getName();
		    	    	                String extension = nombreFoto.substring(nombreFoto.lastIndexOf('.'));
		    	    	                String rutaFotoNueva = "src/imagenes/temporadas/Temporada " + nuevaTemporada.getPeriodo() + "/" + equipo.getNombre() + "/" + equipo.getNombre() + extension;
		    	    	                equipo.setRutaFoto(rutaFotoNueva);
		    	    	            }

		    	    	            // Actualizar las rutas de las fotos de los jugadores
		    	    	            for (Equipo equipo : nuevaTemporada.getEquipos()) {
		    	    	                for (Jugador jugador : equipo.getJugadores()) {
		    	    	                    File fotoJugadorArchivo = new File("src/imagenes/temporadas/Temporada " + periodoSeleccionado + "/" + equipo.getNombre() + "/" + jugador.getNombre() + " " + jugador.getApellidos() + ".png");
		    	    	                    String nombreFotoJugador = fotoJugadorArchivo.getName();
		    	    	                    String extensionJugador = nombreFotoJugador.substring(nombreFotoJugador.lastIndexOf('.'));
		    	    	                    String rutaFotoJugadorNueva = "src/imagenes/temporadas/Temporada " + nuevaTemporada.getPeriodo() + "/" + equipo.getNombre() + "/" + jugador.getNombre() + " " + jugador.getApellidos() + extensionJugador;
		    	    	                    jugador.setRutaFoto(rutaFotoJugadorNueva);
		    	    	                }
		    	    	            }

		    	    	        } catch (IOException | ClassNotFoundException e) {
		    	    	            System.out.println("Error al cargar la temporada seleccionada: " + e.getMessage());
		    	    	            e.printStackTrace();
		    	    	        }
		    	    	    }
		    	    	} else {
		    	    	    JOptionPane.showMessageDialog(getContentPane(), "No hay temporadas disponibles para importar.");
		    	    	}
		    	    }

		    	    nuevaTemporada.guardarTemporada(nuevaTemporada);

		    	    JOptionPane.showMessageDialog(getContentPane(), "Temporada agregada correctamente.");
		    	    dispose();
		    	} catch (IOException e) {
		    	    System.out.println("Error: " + e.getMessage());
		    	}
		    }
		});
        panel.add(btnGuardar);

        // Botón Cancelar
        btnCancelar = new BotonRedondeado("Cancelar", null);
        btnCancelar.setBounds(352, 200, 110, 40);
        btnCancelar.setBackground(new Color(0xf46b20));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(e -> dispose());
        panel.add(btnCancelar);
        
        
        
    }
}
