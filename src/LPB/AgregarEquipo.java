
package LPB;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import LPBCLASES.Equipo;
import LPBCLASES.Jugador;
import LPBCLASES.TextoRedondeado;
import LPBCLASES.BotonRedondeado;

public class AgregarEquipo extends JFrame {

	private static final long serialVersionUID = 4355224767970407050L;
	private JTextField nombreEquipoField;
	private JTextField nombreEntrenadorField;
	private JTextField estadioField;
	private JTextField anioFundacion;
	private JLabel logoLabel;
	private File logoFile;
	private JPanel jugadoresPanel;
	private EquiposTemporada equiposTemporadaFrame;
	private BotonRedondeado btnLogo;
	private JScrollPane scrollPane;
	private BotonRedondeado btnAgregarJugador;
	private BotonRedondeado btnGuardar;
	private JFileChooser fileChooser;
	private FileNameExtensionFilter imageFilter;
	private ArrayList<Jugador> jugadores;
	private Equipo nuevoEquipo;
	private JPanel panelIzquierdo;
	private JPanel panelDerecho;
	private JLabel nombreEquipoLabel;
	private JLabel nombreEntrenadorLabel;
	private JLabel estadioLabel;
	private JLabel fundacionLabel;
	private JLabel jugadoresLabel;
	private File selectedFile;
	private String selectedFileExtension;
	private String jugadorNombre;
	private String jugadorApellidos;
	private String jugadorPosicion;
	private int jugadorDorsal;
	private JLabel jugadorLabel;
	private JLabel jugadorIcon;
	private JPanel jugadorPanel;
	private String temporada, nombre, entrenador, estadio, jugadorPhotoPath, equipoPath;
	private int fundacion;

	public AgregarEquipo(EquiposTemporada equiposTemporadaFrame, String temporada) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
		this.temporada = temporada;
		this.equiposTemporadaFrame = equiposTemporadaFrame;
		setTitle("Agregar Nuevo Equipo");
		setSize(900, 600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		panelIzquierdo = new JPanel(null);
		panelIzquierdo.setBounds(0, 0, 450, 600);
		panelIzquierdo.setBackground(new Color(255, 243, 205));
		getContentPane().add(panelIzquierdo);

		panelDerecho = new JPanel(null);
		panelDerecho.setBounds(450, 0, 450, 600);
		panelDerecho.setBackground(new Color(204, 153, 102));
		getContentPane().add(panelDerecho);

		logoLabel = new JLabel("Logo del equipo:");
		logoLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		logoLabel.setBounds(20, 20, 200, 30);
		panelIzquierdo.add(logoLabel);

		btnLogo = new BotonRedondeado("Cargar", null);
		btnLogo.setBounds(250, 20, 100, 30);
		btnLogo.setBackground(new Color(64, 64, 64));
		btnLogo.setForeground(Color.WHITE);
		btnLogo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnLogo.setFocusPainted(false);
		btnLogo.addActionListener(_ -> seleccionarImagen(logoLabel, true));
		panelIzquierdo.add(btnLogo);

		nombreEquipoLabel = new JLabel("Nombre del equipo:");
		nombreEquipoLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		nombreEquipoLabel.setBounds(20, 70, 200, 30);
		panelIzquierdo.add(nombreEquipoLabel);

		nombreEquipoField = new TextoRedondeado(20);
		nombreEquipoField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		nombreEquipoField.setBounds(20, 100, 330, 30);
		panelIzquierdo.add(nombreEquipoField);

		nombreEntrenadorLabel = new JLabel("Nombre y apellidos del entrenador:");
		nombreEntrenadorLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		nombreEntrenadorLabel.setBounds(20, 152, 255, 30);
		panelIzquierdo.add(nombreEntrenadorLabel);

		nombreEntrenadorField = new TextoRedondeado(20);
		nombreEntrenadorField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		nombreEntrenadorField.setBounds(20, 182, 330, 30);
		panelIzquierdo.add(nombreEntrenadorField);

		estadioLabel = new JLabel("Estadio:");
		estadioLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		estadioLabel.setBounds(20, 232, 200, 30);
		panelIzquierdo.add(estadioLabel);

		estadioField = new TextoRedondeado(20);
		estadioField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		estadioField.setBounds(20, 262, 330, 30);
		panelIzquierdo.add(estadioField);

		fundacionLabel = new JLabel("Año de fundación:");
		fundacionLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		fundacionLabel.setBounds(20, 312, 200, 30);
		panelIzquierdo.add(fundacionLabel);

		anioFundacion = new TextoRedondeado(20);
		anioFundacion.setFont(new Font("SansSerif", Font.PLAIN, 16));
		anioFundacion.setBounds(20, 342, 108, 30);
		panelIzquierdo.add(anioFundacion);

		btnAgregarJugador = new BotonRedondeado("Agregar Jugador", null);
		btnAgregarJugador.setBounds(20, 450, 155, 40);
		btnAgregarJugador.setBackground(new Color(0xf46b20));
		btnAgregarJugador.setForeground(Color.WHITE);
		btnAgregarJugador.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnAgregarJugador.setFocusPainted(false);
		btnAgregarJugador.addActionListener(_ -> agregarJugador());
		panelIzquierdo.add(btnAgregarJugador);

		btnGuardar = new BotonRedondeado("Guardar", null);
		btnGuardar.setBounds(200, 450, 155, 40);
		btnGuardar.setBackground(new Color(0x13427e));
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnGuardar.setFocusPainted(false);
		btnGuardar.addActionListener(_ -> guardarEquipo());
		panelIzquierdo.add(btnGuardar);

		jugadoresLabel = new JLabel("Jugadores");
		jugadoresLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
		jugadoresLabel.setForeground(new Color(255, 243, 205));
		jugadoresLabel.setBounds(20, 20, 200, 30);
		panelDerecho.add(jugadoresLabel);

		jugadoresPanel = new JPanel();
		jugadoresPanel.setLayout(new GridLayout(0, 1, 5, 5));
		jugadoresPanel.setBackground(Color.LIGHT_GRAY);

		scrollPane = new JScrollPane(jugadoresPanel);
		scrollPane.setBounds(20, 60, 400, 400);
		panelDerecho.add(scrollPane);

		jugadores = new ArrayList<>();
	}

	// Método para seleccionar la imagen
	private void seleccionarImagen(JLabel label, boolean isLogo) {
	    JFileChooser fileChooser = new JFileChooser();
	    FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png", "gif");
	    fileChooser.setFileFilter(imageFilter);

	    int result = fileChooser.showOpenDialog(this);
	    if (result == JFileChooser.APPROVE_OPTION) {
	        selectedFile = fileChooser.getSelectedFile();
	        if (selectedFile.exists()) {
	            label.setText(selectedFile.getName());

	            // Obtener extensión del archivo
	            String fileName = selectedFile.getName();
	            int lastIndex = fileName.lastIndexOf('.');
	            selectedFileExtension = (lastIndex == -1) ? "" : fileName.substring(lastIndex + 1);

	            if (isLogo) {
	                logoFile = selectedFile;
	            }
	        }
	    }
	}

	private void agregarJugador() {
		jugadorNombre = JOptionPane.showInputDialog("Nombre del jugador:");
		if (jugadorNombre != null && !jugadorNombre.trim().isEmpty()) {
			jugadorApellidos = JOptionPane.showInputDialog("Apellidos del jugador:");
			if (jugadorApellidos != null && !jugadorApellidos.trim().isEmpty()) {
				jugadorPosicion = JOptionPane.showInputDialog("Posición del jugador (Alero, Base, Escolta, Ala-pívot, Pívot):");
				if (jugadorPosicion != null && !jugadorPosicion.trim().isEmpty()) {
					jugadorDorsal = Integer.valueOf(JOptionPane.showInputDialog("Dorsal del jugador:"));
					if (jugadorDorsal >= 0) {
						fileChooser = new JFileChooser();
						fileChooser.setFileFilter(imageFilter);
						int result = fileChooser.showOpenDialog(this);
						if (result == JFileChooser.APPROVE_OPTION) {
							selectedFile = fileChooser.getSelectedFile();
							if (selectedFile.exists()) {
								jugadorLabel = new JLabel(jugadorNombre);
								jugadorIcon = new JLabel(new ImageIcon(new ImageIcon(selectedFile.getAbsolutePath()).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
			
								jugadorPanel = new JPanel(new BorderLayout());
								jugadorPanel.add(jugadorIcon, BorderLayout.WEST);
								jugadorPanel.add(jugadorLabel, BorderLayout.CENTER);
								jugadoresPanel.add(jugadorPanel);
								jugadoresPanel.revalidate();
								jugadoresPanel.repaint();
			
								jugadores.add(new Jugador(jugadorNombre, jugadorApellidos, jugadorPosicion, jugadorDorsal, jugadorPhotoPath));
							}
						}
					}
				}
			}
		}
	}

	private void guardarEquipo() {
	    nombre = nombreEquipoField.getText();
	    entrenador = nombreEntrenadorField.getText();
	    estadio = estadioField.getText();
	    fundacion = Integer.valueOf(anioFundacion.getText());

	    if (nombre.isEmpty() || entrenador.isEmpty() || estadio.isEmpty() || anioFundacion.getText().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    // Mover el archivo seleccionado al directorio de destino
	    if (logoFile != null) {
	        try {
	            // Construir la ruta de destino dentro de los recursos
	            String logoDestinationPath = String.format("src/imagenes/temporadas/%s/%s/%s.%s",
	                this.temporada, nombre, nombre, selectedFileExtension);
	            File logoDestinationFile = new File(logoDestinationPath);

	            // Crear directorios si no existen
	            logoDestinationFile.getParentFile().mkdirs();

	            // Copiar el archivo del logo
	            Files.copy(logoFile.toPath(), logoDestinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

	            // Mostrar mensaje de éxito (opcional)
	            System.out.println("El logo se ha guardado correctamente en: " + logoDestinationFile.getAbsolutePath());
	        } catch (IOException e) {
	            JOptionPane.showMessageDialog(this, "Error al mover la imagen del logo: " + e.getMessage(),
	                "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	    }

	    // Crear y guardar el equipo
	    nuevoEquipo = new Equipo(nombre, entrenador, jugadores, estadio, fundacion, equipoPath);
	    equiposTemporadaFrame.agregarNuevoEquipoDesdeFormulario(nuevoEquipo);
	    JOptionPane.showMessageDialog(this, "Equipo agregado correctamente");
	    dispose();
	}
}
