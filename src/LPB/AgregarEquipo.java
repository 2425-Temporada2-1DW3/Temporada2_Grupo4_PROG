
package LPB;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;

import LPBCLASES.Equipo;
import LPBCLASES.Equipo.PlayerWithImage;

public class AgregarEquipo extends JFrame {

	private static final long serialVersionUID = 4355224767970407050L;
	private JTextField nombreEquipoField;
	private JTextField nombreEntrenadorField;
	private JTextField estadioField;
	private JTextField fundacionField;
	private DefaultListModel<PlayerWithImage> jugadoresModel;
	private JLabel logoLabel;
	private JLabel entrenadorLabel;
	private File logoFile;
	private File entrenadorFile;
	private EquiposTemporada equiposTemporadaFrame;

	public AgregarEquipo(EquiposTemporada equiposTemporadaFrame) {
		this.equiposTemporadaFrame = equiposTemporadaFrame;
		setTitle("Agregar Nuevo Equipo");
		setSize(815, 600);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(8, 2, 5, 5));

		logoLabel = new JLabel("Seleccionar Logo");
		JButton btnLogo = new JButton("Cargar");
		btnLogo.addActionListener(_ -> seleccionarImagen(logoLabel, true));

		nombreEquipoField = new JTextField();
		nombreEntrenadorField = new JTextField();
		JButton btnEntrenador = new JButton("Cargar");

		estadioField = new JTextField();
		fundacionField = new JTextField();

		panel.add(new JLabel("Logo del Equipo:"));
		panel.add(btnLogo);
		panel.add(logoLabel);

		panel.add(new JLabel("Nombre del Equipo:"));
		panel.add(nombreEquipoField);

		panel.add(new JLabel("Entrenador (Nombre):"));
		panel.add(nombreEntrenadorField);

		panel.add(new JLabel("Foto del Entrenador:"));
		panel.add(btnEntrenador);

		entrenadorLabel = new JLabel("Seleccionar Foto Entrenador");
		btnEntrenador.addActionListener(_ -> seleccionarImagen(entrenadorLabel, false));
		panel.add(entrenadorLabel);

		panel.add(new JLabel("Estadio:"));
		panel.add(estadioField);

		panel.add(new JLabel("Fundación:"));
		panel.add(fundacionField);

		getContentPane().add(panel, BorderLayout.CENTER);

		jugadoresModel = new DefaultListModel<>();
		JList<PlayerWithImage> jugadoresList = new JList<>(jugadoresModel);
		jugadoresList.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
			JPanel cellPanel = new JPanel(new BorderLayout());
			JLabel imageLabel = new JLabel();
			imageLabel.setIcon(new ImageIcon(
					new ImageIcon(value.getImagePath()).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
			JLabel nameLabel = new JLabel(value.getName());
			cellPanel.add(imageLabel, BorderLayout.WEST);
			cellPanel.add(nameLabel, BorderLayout.CENTER);
			return cellPanel;
		});

		JScrollPane scrollPane = new JScrollPane(jugadoresList);

		JPanel panelJugadores = new JPanel();
		panelJugadores.setLayout(new BorderLayout());
		panelJugadores.add(new JLabel("Lista de Jugadores"), BorderLayout.NORTH);
		panelJugadores.add(scrollPane, BorderLayout.CENTER);

		JPanel panelBotones = new JPanel();
		JButton btnAgregarJugador = new JButton("Agregar Jugador");
		btnAgregarJugador.addActionListener(_ -> agregarJugador());
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(_ -> guardarEquipo());

		panelBotones.add(btnAgregarJugador);
		panelBotones.add(btnGuardar);

		getContentPane().add(panelJugadores, BorderLayout.EAST);
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
	}

	private void seleccionarImagen(JLabel label, boolean isLogo) {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png", "gif");
		fileChooser.setFileFilter(imageFilter);
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();

			if (selectedFile.exists() && isValidImage(selectedFile)) {
				label.setText(selectedFile.getName());
				if (isLogo) {
					logoFile = selectedFile;
				} else {
					entrenadorFile = selectedFile;
				}
			} else {
				JOptionPane.showMessageDialog(this, "El archivo seleccionado no es válido.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private boolean isValidImage(File file) {
		String fileName = file.getName().toLowerCase();
		return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")
				|| fileName.endsWith(".gif");
	}

	private void agregarJugador() {
		String jugador = JOptionPane.showInputDialog("Nombre del jugador:");
		if (jugador != null && !jugador.trim().isEmpty()) {
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png", "gif");
			fileChooser.setFileFilter(imageFilter);
			int result = fileChooser.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				if (isValidImage(selectedFile)) {
					jugadoresModel.addElement(new PlayerWithImage(jugador, selectedFile.getAbsolutePath()));
				} else {
					JOptionPane.showMessageDialog(this, "El archivo seleccionado no es válido.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	private void guardarEquipo() {
		String nombre = nombreEquipoField.getText();
		String entrenador = nombreEntrenadorField.getText();
		String estadio = estadioField.getText();
		String fundacion = fundacionField.getText();

		if (nombre.isEmpty() || entrenador.isEmpty() || estadio.isEmpty() || fundacion.isEmpty() || logoFile == null
				|| entrenadorFile == null) {
			JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		ArrayList<PlayerWithImage> jugadores = new ArrayList<>();
		for (int i = 0; i < jugadoresModel.getSize(); i++) {
			jugadores.add(jugadoresModel.getElementAt(i));
		}

		Equipo nuevoEquipo = new Equipo(nombre, entrenador, jugadores, logoFile.getAbsolutePath(),
				entrenadorFile.getAbsolutePath(), estadio, fundacion);
		equiposTemporadaFrame.agregarNuevoEquipoDesdeFormulario(nuevoEquipo);
		JOptionPane.showMessageDialog(this, "Equipo agregado correctamente");
		dispose();
	}
}
