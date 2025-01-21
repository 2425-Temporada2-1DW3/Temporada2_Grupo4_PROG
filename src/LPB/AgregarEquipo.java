
package LPB;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import LPBCLASES.Equipo;
import LPBCLASES.Fecha;
import LPBCLASES.Jugador;
import LPBCLASES.TextoRedondeado;
import LPBCLASES.BotonRedondeado;

public class AgregarEquipo extends JFrame {

	private static final long serialVersionUID = 4355224767970407050L;
	private JTextField nombreEquipoField;
	private JTextField nombreEntrenadorField;
	private JTextField estadioField;
	private JTextField diaFundField;
	private JTextField mesFundField;
	private JTextField anioFundField;
	private JLabel logoLabel;
	private JLabel entrenadorLabel;
	private File logoFile;
	private File entrenadorFile;
	private JPanel jugadoresPanel;
	private EquiposTemporada equiposTemporadaFrame;
	private BotonRedondeado btnLogo;
	private BotonRedondeado btnEntrenador;
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
	private JLabel fotoEntrenadorLabel;
	private JLabel nombreEntrenadorLabel;
	private JLabel estadioLabel;
	private JLabel fundacionLabel;
	private JLabel jugadoresLabel;
	private JLabel fundacionLabel_1;
	private JLabel fundacionLabel_2;
	private File selectedFile;
	private String jugadorNombre;
	private String jugadorPosicion;
	private int jugadorDorsal;
	private JLabel jugadorLabel;
	private JLabel jugadorIcon;
	private JPanel jugadorPanel;
	private String nombre, entrenador, estadio, jugadorPhotoPath, equipoPath, entrenadorPath;
	private int diaFund, mesFund, anioFund;

	public AgregarEquipo(EquiposTemporada equiposTemporadaFrame) {
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

		fotoEntrenadorLabel = new JLabel("Foto del entrenador:");
		fotoEntrenadorLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		fotoEntrenadorLabel.setBounds(20, 150, 200, 30);
		panelIzquierdo.add(fotoEntrenadorLabel);

		entrenadorLabel = new JLabel("Foto del entrenador:");
		entrenadorLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		entrenadorLabel.setBounds(20, 150, 200, 30);
		panelIzquierdo.add(entrenadorLabel);

		btnEntrenador = new BotonRedondeado("Cargar", null);
		btnEntrenador.setBounds(250, 150, 100, 30);
		btnEntrenador.setBackground(new Color(64, 64, 64));
		btnEntrenador.setForeground(Color.WHITE);
		btnEntrenador.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnEntrenador.addActionListener(_ -> seleccionarImagen(entrenadorLabel, false));
		panelIzquierdo.add(btnEntrenador);

		nombreEntrenadorLabel = new JLabel("Nombre del entrenador:");
		nombreEntrenadorLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		nombreEntrenadorLabel.setBounds(20, 200, 200, 30);
		panelIzquierdo.add(nombreEntrenadorLabel);

		nombreEntrenadorField = new TextoRedondeado(20);
		nombreEntrenadorField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		nombreEntrenadorField.setBounds(20, 230, 330, 30);
		panelIzquierdo.add(nombreEntrenadorField);

		estadioLabel = new JLabel("Estadio:");
		estadioLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		estadioLabel.setBounds(20, 280, 200, 30);
		panelIzquierdo.add(estadioLabel);

		estadioField = new TextoRedondeado(20);
		estadioField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		estadioField.setBounds(20, 310, 330, 30);
		panelIzquierdo.add(estadioField);

		fundacionLabel = new JLabel("Fecha de fundación:");
		fundacionLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		fundacionLabel.setBounds(20, 360, 200, 30);
		panelIzquierdo.add(fundacionLabel);

		diaFundField = new TextoRedondeado(20);
		diaFundField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		diaFundField.setBounds(20, 390, 82, 30);
		panelIzquierdo.add(diaFundField);
		
		fundacionLabel_1 = new JLabel("/");
		fundacionLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		fundacionLabel_1.setBounds(115, 389, 10, 30);
		panelIzquierdo.add(fundacionLabel_1);
		
		mesFundField = new TextoRedondeado(20);
		mesFundField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		mesFundField.setBounds(132, 390, 96, 30);
		panelIzquierdo.add(mesFundField);
		
		fundacionLabel_2 = new JLabel("/");
		fundacionLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		fundacionLabel_2.setBounds(238, 390, 10, 30);
		panelIzquierdo.add(fundacionLabel_2);
		
		anioFundField = new TextoRedondeado(20);
		anioFundField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		anioFundField.setBounds(254, 390, 96, 30);
		panelIzquierdo.add(anioFundField);

		btnAgregarJugador = new BotonRedondeado("Agregar Jugador", null);
		btnAgregarJugador.setBounds(20, 450, 155, 40);
		btnAgregarJugador.setBackground(new Color(0xf46b20));
		btnAgregarJugador.setForeground(Color.WHITE);
		btnAgregarJugador.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnAgregarJugador.addActionListener(_ -> agregarJugador());
		panelIzquierdo.add(btnAgregarJugador);

		btnGuardar = new BotonRedondeado("Guardar", null);
		btnGuardar.setBounds(200, 450, 155, 40);
		btnGuardar.setBackground(new Color(0x13427e));
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 16));
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

	private void seleccionarImagen(JLabel label, boolean isLogo) {
		fileChooser = new JFileChooser();
		imageFilter = new FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png", "gif");
		fileChooser.setFileFilter(imageFilter);
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			selectedFile = fileChooser.getSelectedFile();
			if (selectedFile.exists()) {
				label.setText(selectedFile.getName());
				if (isLogo)
					logoFile = selectedFile;
				else
					entrenadorFile = selectedFile;
			}
		}
	}

	private void agregarJugador() {
		jugadorNombre = JOptionPane.showInputDialog("Nombre del jugador:");
		if (jugadorNombre != null && !jugadorNombre.trim().isEmpty()) {
			jugadorPosicion = JOptionPane.showInputDialog("Posición del jugador (Alero, Base, Escolta, Ala-pívot, Pívot):");
			if (jugadorPosicion != null && !jugadorPosicion.trim().isEmpty()) {
				jugadorDorsal = Integer.valueOf(JOptionPane.showInputDialog("Dorsal del jugador:"));
				if (jugadorDorsal < 0) {
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
		
							jugadores.add(new Jugador(jugadorNombre, jugadorPosicion, jugadorDorsal, jugadorPhotoPath));
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
		diaFund = Integer.valueOf(diaFundField.getText());
		mesFund = Integer.valueOf(mesFundField.getText());
		anioFund = Integer.valueOf(anioFundField.getText());

		if (nombre.isEmpty() || entrenador.isEmpty() || estadio.isEmpty() || diaFundField.getText().isEmpty() || mesFundField.getText().isEmpty() || anioFundField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		nuevoEquipo = new Equipo(nombre, entrenador, jugadores, estadio, new Fecha(diaFund, mesFund, anioFund), equipoPath, entrenadorPath);
		equiposTemporadaFrame.agregarNuevoEquipoDesdeFormulario(nuevoEquipo);
		JOptionPane.showMessageDialog(this, "Equipo agregado correctamente");
		dispose();
	}
}
