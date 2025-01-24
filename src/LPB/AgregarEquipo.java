
package LPB;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

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
	private EquiposTemporada equiposTemporadaFrame;
	private BotonRedondeado btnLogo;
	private JScrollPane scrollPane;
	private BotonRedondeado btnAgregarJugador;
	private BotonRedondeado btnGuardar;
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
	private String temporada, nombre, entrenador, estadio, equipoPath;
	private int fundacion;
	private JTable tablaJugadores;
	private DefaultTableModel tableModel;

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

		 String[] nombreColumnas = {"Foto", "Nombre", "Apellido", "Posición", "Dorsal"};
		    tableModel = new DefaultTableModel(nombreColumnas, 0) {
		        
				private static final long serialVersionUID = 4426702124875665016L;

				@Override
		        public Class<?> getColumnClass(int column) {
		            if (column == 0) {
		                return ImageIcon.class;
		            }
		            return String.class;
		        }

		        @Override
		        public boolean isCellEditable(int row, int column) {
		            return false;
		        }
		    };
		    tablaJugadores = new JTable(tableModel);
		    tablaJugadores.getTableHeader().setReorderingAllowed(false);
		    tablaJugadores.setFont(new Font("SansSerif", Font.PLAIN, 12));
		    tablaJugadores.setShowGrid(false);
		    tablaJugadores.setBackground(new Color(0xd9d9d9));
		    tablaJugadores.setRowHeight(50);
		    tablaJugadores.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
		    
		    
		    
		    DefaultTableCellRenderer centrarTexto = new DefaultTableCellRenderer();
		    centrarTexto.setHorizontalAlignment(SwingConstants.CENTER);

		    for (int i = 1; i < tablaJugadores.getColumnCount(); i++) {
		        tablaJugadores.getColumnModel().getColumn(i).setCellRenderer(centrarTexto);
		    }

			scrollPane = new JScrollPane(tablaJugadores);
			scrollPane.setBackground(new Color(0xd9d9d9));
			scrollPane.getViewport().setBackground(new Color(0xd9d9d9));
			scrollPane.setBounds(20, 60, 400, 400);
			panelDerecho.add(scrollPane);

		jugadores = new ArrayList<>();
	}

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
	    AgregarJugador dialog = new AgregarJugador(this);
	    dialog.setVisible(true);

	    if (dialog.isGuardado()) {
	        String nombre = dialog.getNombre();
	        String apellidos = dialog.getApellidos();
	        String posicion = dialog.getPosicion();
	        int dorsal = dialog.getDorsal();
	        File foto = dialog.getJugadorFoto();

	        ImageIcon icon = null;
	        if (foto != null) {
	            icon = new ImageIcon(new ImageIcon(foto.getAbsolutePath())
	                .getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
	        }

	        tableModel.addRow(new Object[]{icon, nombre, apellidos, posicion, dorsal});
	        jugadores.add(new Jugador(nombre, apellidos, posicion, dorsal, foto != null ? foto.getAbsolutePath() : null));
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
