
package LPB;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import LPBCLASES.Equipo;
import LPBCLASES.Jugador;
import LPBCLASES.Temporada;
import LPBCLASES.TextoRedondeado;
import LPBCLASES.logClase;
import jnafilechooser.api.JnaFileChooser;
import LPBCLASES.BotonRedondeado;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;

public class AgregarEquipo extends JFrame {

	private static final long serialVersionUID = 4355224767970407050L;
	private JTextField nombreEquipoField;
	private JTextField nombreEntrenadorField;
	private JTextField estadioField;
	private JTextField anioFundacion;
	private JLabel logoLabel;
	private File logoFile;
	private BotonRedondeado btnLogo;
	private JScrollPane scrollPane;
	private BotonRedondeado btnAgregarJugador;
	private BotonRedondeado btnGuardar;
	private BotonRedondeado btnEliminarJugador;
	private ArrayList<Jugador> jugadores;
	private JPanel panelIzquierdo;
	private JPanel panelDerecho;
	private JLabel nombreEquipoLabel;
	private JLabel nombreEntrenadorLabel;
	private JLabel estadioLabel;
	private JLabel fundacionLabel;
	private JLabel jugadoresLabel;
	private File selectedFile;
	private String selectedFileExtension;
	Temporada temporada;
	private String nombre, entrenador, estadio, rutaFoto;
	private int fundacion;
	private JTable tablaJugadores;
	private DefaultTableModel tableModel;

	
	public AgregarEquipo(Temporada temporada) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
		this.temporada = temporada;
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
		logoLabel.setBounds(61, 30, 200, 30);
		panelIzquierdo.add(logoLabel);

		btnLogo = new BotonRedondeado("Cargar", null);
		btnLogo.setBounds(291, 30, 100, 30);
		btnLogo.setBackground(new Color(64, 64, 64));
		btnLogo.setForeground(Color.WHITE);
		btnLogo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnLogo.setFocusPainted(false);
		btnLogo.addActionListener(_ -> seleccionarImagen(logoLabel, true));
		panelIzquierdo.add(btnLogo);

		nombreEquipoLabel = new JLabel("Nombre del equipo:");
		nombreEquipoLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		nombreEquipoLabel.setBounds(61, 80, 200, 30);
		panelIzquierdo.add(nombreEquipoLabel);

		nombreEquipoField = new TextoRedondeado(20);
		nombreEquipoField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		nombreEquipoField.setBounds(61, 110, 330, 30);
		panelIzquierdo.add(nombreEquipoField);

		nombreEntrenadorLabel = new JLabel("Nombre y apellidos del entrenador:");
		nombreEntrenadorLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		nombreEntrenadorLabel.setBounds(61, 162, 255, 30);
		panelIzquierdo.add(nombreEntrenadorLabel);

		nombreEntrenadorField = new TextoRedondeado(20);
		nombreEntrenadorField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		nombreEntrenadorField.setBounds(61, 192, 330, 30);
		panelIzquierdo.add(nombreEntrenadorField);

		estadioLabel = new JLabel("Estadio:");
		estadioLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		estadioLabel.setBounds(61, 242, 200, 30);
		panelIzquierdo.add(estadioLabel);

		estadioField = new TextoRedondeado(20);
		estadioField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		estadioField.setBounds(61, 272, 330, 30);
		panelIzquierdo.add(estadioField);

		fundacionLabel = new JLabel("Año de fundación:");
		fundacionLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		fundacionLabel.setBounds(61, 322, 200, 30);
		panelIzquierdo.add(fundacionLabel);

		anioFundacion = new TextoRedondeado(20);
		anioFundacion.setFont(new Font("SansSerif", Font.PLAIN, 16));
		anioFundacion.setBounds(61, 352, 108, 30);
		panelIzquierdo.add(anioFundacion);

		btnAgregarJugador = new BotonRedondeado("Agregar Jugador", null);
		btnAgregarJugador.setBounds(56, 450, 155, 40);
		btnAgregarJugador.setBackground(new Color(0xf46b20));
		btnAgregarJugador.setForeground(Color.WHITE);
		btnAgregarJugador.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnAgregarJugador.setFocusPainted(false);
		
		btnAgregarJugador.addActionListener(e -> {
		    AgregarJugador agregarJugador = new AgregarJugador(this);
		    agregarJugador.setVisible(true);
		    
		    agregarJugador.addWindowListener(new java.awt.event.WindowAdapter() {
		        @Override
		        public void windowClosed(java.awt.event.WindowEvent e) {
		            Jugador nuevoJugador = agregarJugador.getJugador();
		            
		            if (nuevoJugador != null) {
		                jugadores.add(nuevoJugador);

		                String rutaFoto = nuevoJugador.getRutaFoto();
		                ImageIcon fotoIcon = null;
		                
		                if (rutaFoto != null && !rutaFoto.isEmpty()) {
		                    // Cargar la imagen desde la ruta
			    			Image originalIcon = new ImageIcon(rutaFoto).getImage();
			    			
			    			int height = 40;
			    			int width = (int) (originalIcon.getWidth(null) * ((double) height / originalIcon.getHeight(null)));

			    			Image scaledPlayerImage = originalIcon.getScaledInstance(width, height, Image.SCALE_SMOOTH);

			    			fotoIcon = new ImageIcon(scaledPlayerImage);
		                } else {
		                    // Si no tiene foto, usar la imagen predeterminada
		                    fotoIcon = new ImageIcon("src/imagenes/user.png");
		                }
		                
		                tableModel.addRow(new Object[]{
		                    fotoIcon,
		                    nuevoJugador.getNombre() + " " + nuevoJugador.getApellidos(),
		                    nuevoJugador.getPosicion(),
		                    nuevoJugador.getDorsal()
		                });
		                
		            }
		        }
		    });
		});
		
		panelIzquierdo.add(btnAgregarJugador);

		btnGuardar = new BotonRedondeado("Guardar", null);
		btnGuardar.setBounds(236, 450, 155, 40);
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

		String[] nombreColumnas = {"", "Nombre", "Posición", "Dorsal"};
	    
		tableModel = new DefaultTableModel(nombreColumnas, 0) {

			private static final long serialVersionUID = -5930205779290303837L;

			@Override
	        public boolean isCellEditable(int row, int column) {
	           return false;
	        }
	    };
	    
	    tablaJugadores = new JTable(tableModel);
	    tablaJugadores.setRowHeight(50);
	    tablaJugadores.getTableHeader().setReorderingAllowed(false);
	    tablaJugadores.setFont(new Font("SansSerif", Font.PLAIN, 12));
	    tablaJugadores.setShowGrid(false);
	    tablaJugadores.setBackground(new Color(217, 217, 217));
	    tablaJugadores.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
	    tablaJugadores.getTableHeader().setPreferredSize(new Dimension(tablaJugadores.getTableHeader().getPreferredSize().width, 40));
	    
	    DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
	        private static final long serialVersionUID = 1L;

	        @Override
	        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	            label.setOpaque(true);
	            label.setBackground(new Color(112, 117, 126));
	            label.setForeground(Color.WHITE);
	            label.setFont(new Font("SansSerif", Font.BOLD, 16));
	            label.setHorizontalAlignment(SwingConstants.CENTER);
	            return label;
	        }
	    };

	    for (int i = 0; i < tablaJugadores.getColumnModel().getColumnCount(); i++) {
	        tablaJugadores.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
	    }
	    
	    DefaultTableCellRenderer centrarTexto = new DefaultTableCellRenderer();
	    centrarTexto.setHorizontalAlignment(SwingConstants.CENTER);

	    for (int i = 1; i < tablaJugadores.getColumnModel().getColumnCount(); i++) {
	        tablaJugadores.getColumnModel().getColumn(i).setCellRenderer(centrarTexto);
	    }

	    tablaJugadores.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
	        private static final long serialVersionUID = 3745127078281392738L;

	        @Override
	        protected void setValue(Object value) {
	            if (value instanceof ImageIcon) {
	                setIcon((ImageIcon) value);
	                setText("");
	            } else {
	                super.setValue(value);
	            }
	        }

	        @Override
	        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	            label.setHorizontalAlignment(SwingConstants.CENTER);
	            return label;
	        }
	    });

		scrollPane = new JScrollPane(tablaJugadores);
		scrollPane.setBackground(new Color(0xd9d9d9));
		scrollPane.getViewport().setBackground(new Color(0xd9d9d9));
		scrollPane.setBounds(20, 60, 400, 400);
		panelDerecho.add(scrollPane);
		
		btnEliminarJugador = new BotonRedondeado("Eliminar Jugador", (ImageIcon) null);
		btnEliminarJugador.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int[] selectedRows = tablaJugadores.getSelectedRows();
		        
		        if (selectedRows.length > 0) {
		            Arrays.sort(selectedRows);
		            for (int i = selectedRows.length - 1; i >= 0; i--) {
		                int selectedRow = selectedRows[i];
		                
		                tableModel.removeRow(selectedRow);
		                jugadores.remove(selectedRow);
		            }
		        } else {
		            JOptionPane.showMessageDialog(
		                null,
		                "Por favor, selecciona al menos un jugador para eliminarlo.",
		                "Advertencia",
		                JOptionPane.WARNING_MESSAGE
		            );
		        }
		        // 🔴 Log cuando se elimina un jugador
                logClase.logAction("Jugador " + nombre + " eliminado.");
		    }
		});
		btnEliminarJugador.setForeground(Color.WHITE);
		btnEliminarJugador.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnEliminarJugador.setFocusPainted(false);
		btnEliminarJugador.setBackground(new Color(228, 82, 39));
		btnEliminarJugador.setBounds(252, 486, 168, 40);
		panelDerecho.add(btnEliminarJugador);

		jugadores = new ArrayList<>();
	}

	// Método para seleccionar la imagen
	private void seleccionarImagen(JLabel label, boolean isLogo) {
		JnaFileChooser fileChooser = new JnaFileChooser();
		fileChooser.setTitle("Selecciona una foto");
		fileChooser.addFilter("Imágenes (*.jpg; *.jpeg; *.png; *.gif)", "jpg", "jpeg", "png", "gif");
		fileChooser.addFilter("Todos los Archivos", "*");

		if (fileChooser.showOpenDialog(this)) {
	        selectedFile = fileChooser.getSelectedFile();
	        if (selectedFile.exists()) {
	            try {
	                BufferedImage imagen = ImageIO.read(selectedFile);
	                if (imagen == null) {
	                    throw new IOException("Formato de imagen no soportado o corrupto");
	                }

	                label.setText(selectedFile.getName());

	                String fileName = selectedFile.getName();
	                int lastIndex = fileName.lastIndexOf('.');
	                selectedFileExtension = (lastIndex == -1) ? "" : fileName.substring(lastIndex + 1);

	                if (isLogo) {
	                    logoFile = selectedFile;
	                }
	            } catch (IOException e) {
	                JOptionPane.showMessageDialog(null, 
	                    "La imagen está corrupta, prueba con otra imagen.", 
	                    "Error", 
	                    JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    }
	}

	private void guardarEquipo() {
	    nombre = nombreEquipoField.getText();
	    entrenador = nombreEntrenadorField.getText();
	    estadio = estadioField.getText();
	    fundacion = Integer.valueOf(anioFundacion.getText());
	    String rutaOriginal = selectedFile.getAbsolutePath();
	    String extension = "";
	    int index = rutaOriginal.lastIndexOf('.');
	    if (index != -1) {
	        extension = rutaOriginal.substring(index);
	    }
	    
	    rutaFoto = "src/imagenes/temporadas/Temporada " + temporada.getPeriodo() + "/" + nombre + "/" + nombre + extension;

	    if (nombre.isEmpty() || entrenador.isEmpty() || estadio.isEmpty() || anioFundacion.getText().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    if (logoFile != null) {
	        try {
	            String logoDestinationPath = String.format("src/imagenes/temporadas/Temporada %s/%s/%s.%s",
	                this.temporada.getPeriodo(), nombre, nombre, selectedFileExtension);
	            File logoDestinationFile = new File(logoDestinationPath);

	            logoDestinationFile.getParentFile().mkdirs();

	            Files.copy(logoFile.toPath(), logoDestinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	            
	        } catch (IOException e) {
	            JOptionPane.showMessageDialog(this, "Error al mover la imagen del logo: " + e.getMessage(),
	                "Error", JOptionPane.ERROR_MESSAGE);
	            
	            // 🔴 Log del error al mover el logo
	            logClase.logError("Error al mover la imagen del logo: " + e.getMessage(), e);
	           
	            return;
	        }
	    }
	    
        try {
            String basePath = "src/imagenes/temporadas/Temporada " + temporada.getPeriodo() + "/" + nombre + "/";
            Files.createDirectories(Paths.get(basePath));

            for (Jugador jugador : jugadores) {
            	String rutaAbsoluta = jugador.getRutaFoto();
                String fotoPath = basePath + jugador.getNombre() + " " + jugador.getApellidos();
                String extensionJugador = rutaAbsoluta.substring(rutaAbsoluta.lastIndexOf("."));
                String nuevoPath = fotoPath + extensionJugador;

                Path source = Paths.get(rutaAbsoluta);
                Path destination = Paths.get(nuevoPath);

                Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                
                jugador.setRutaFoto(nuevoPath);
            }

        } catch (IOException e1) {
        	 JOptionPane.showMessageDialog(this,"Error al mover las fotos de los jugadores: " + e1.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
         
            // 🔴 Log del error al mover las fotos de los jugadores
            logClase.logError("Error al mover las fotos de los jugadores: " + e1.getMessage(), e1);
        
        }

	    Equipo nuevoEquipo = new Equipo(nombre, entrenador, jugadores, estadio, fundacion, rutaFoto);

	    try {
	        String temporadaFilePath = String.format("data/temporada_%s.ser", this.temporada.getPeriodo());
	        File temporadaFile = new File(temporadaFilePath);

	        if (temporadaFile.exists()) {
	            Temporada temporadaCargada = Temporada.cargarTemporada(this.temporada.getPeriodo());
	            if (temporadaCargada != null) {
	                boolean equipoExistente = false;
	                for (Equipo equipo : temporadaCargada.getEquipos()) {
	                    if (equipo.getNombre().equalsIgnoreCase(nombre)) {
	                        equipoExistente = true;
	                        break;
	                    }
	                }

	                if (equipoExistente) {
	                    JOptionPane.showMessageDialog(this, "Ya existe un equipo con ese nombre en la temporada.", "Error", JOptionPane.ERROR_MESSAGE);
	                } else {
	                    List<Equipo> listaEquipos = temporadaCargada.getEquipos();
	                    listaEquipos.add(nuevoEquipo);
	                    temporadaCargada.setEquipos(listaEquipos);
	                    temporadaCargada.guardarTemporada(temporadaCargada);
	                    JOptionPane.showMessageDialog(this, "El equipo ha sido guardado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	                    // 🔴 Log cuando se guarda el equipo correctamente
	                    logClase.logAction(String.format(
	                        "Equipo guardado: '%s', Entrenador: '%s', Estadio: '%s', Año de fundación: %d, Jugadores: %d",
	                        nombre, entrenador, estadio, fundacion, jugadores.size()
	                    ));
	                    dispose();
	                }
	            } else {
	                JOptionPane.showMessageDialog(this, "No se pudo cargar la temporada.", "Error", JOptionPane.ERROR_MESSAGE);
	                
	                // 🔴 Log del error al cargar la temporada
	                logClase.logError("No se pudo cargar la temporada: " + this.temporada.getPeriodo(), null);
	            }
	        } else {
	            JOptionPane.showMessageDialog(this, "No se encontró el archivo de la temporada.", "Error", JOptionPane.ERROR_MESSAGE);
	            
	            // 🔴 Log del error al no encontrar el archivo de temporada
	            logClase.logError("No se encontró el archivo de la temporada: " + temporadaFilePath, null);
	        }
	    } catch (IOException | ClassNotFoundException e) {
	        JOptionPane.showMessageDialog(this, "Error al guardar el equipo en la temporada: " + e.getMessage(),
	            "Error", JOptionPane.ERROR_MESSAGE);
	        
	        // 🔴 Log del error al guardar el equipo
	        logClase.logError("Error al guardar el equipo en la temporada: " + e.getMessage(), e);
	    }
	    
	}
}
