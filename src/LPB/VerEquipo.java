package LPB;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import LPBCLASES.BotonRedondeado;
import LPBCLASES.Equipo;
import LPBCLASES.Jugador;
import LPBCLASES.Temporada;
import LPBCLASES.TextoRedondeado;
import LPBCLASES.Usuario;
import jnafilechooser.api.JnaFileChooser;
import LPBCLASES.logClase;
/**
 * Clase VerEquipo que representa la interfaz gráfica para la visualización y gestión de un equipo en la aplicación LPB Basketball.
 * Permite ver detalles del equipo, editar información, agregar o eliminar jugadores y cambiar la foto del equipo.
 */
public class VerEquipo extends JFrame {
	private JPanel panelIzquierdo;
	private JLabel labelUsuario;
	private JLabel escudoLabel;
	private JLabel nombreLabel;
	private JLabel entrenadorLabel;
	private JLabel estadioLabel;
	private JLabel fundacionLabel;
	private TextoRedondeado entrenadorField;
	private TextoRedondeado estadioField;
	private TextoRedondeado fundacionField;
	private JPanel panelDerecho;
	private JLabel titulo;
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private List<Jugador> jugadores;
	private JTable tablaJugadores;
	private BotonRedondeado btnVolver;
	private BotonRedondeado btnEditar;
	private BotonRedondeado btnGuardar;
	private BotonRedondeado btnCancelar;
	private BotonRedondeado btnAñadir;
	private BotonRedondeado btnEliminar;
	private BotonRedondeado btnCambiarFoto;

	private static final long serialVersionUID = 1L;
	 /**
   * Constructor de la clase VerEquipo.
   * 
   * @param temporada La temporada en la que se encuentra el equipo.
   * @param equipo El equipo a visualizar.
   * @param rol El rol del usuario que accede a la vista.
   * @param usuario El nombre del usuario que accede.
   */
	public VerEquipo(Temporada temporada, Equipo equipo, String rol, String usuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
		setTitle("LPB Basketball - Detalles del Equipo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000, 650);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		jugadores = new ArrayList<>();

		panelIzquierdo = new JPanel();
		panelIzquierdo.setBackground(new Color(255, 243, 205));
		panelIzquierdo.setBounds(0, 0, 500, 613);
		panelIzquierdo.setLayout(null);

		labelUsuario = new JLabel("Usuario: " + usuario);
		labelUsuario.setFont(new Font("SansSerif", Font.PLAIN, 16));
		labelUsuario.setForeground(new Color(0x545454));
		labelUsuario.setBounds(23, 569, 200, 20);
		panelIzquierdo.add(labelUsuario);
		
		escudoLabel = new JLabel();
		escudoLabel.setBounds(50, 40, 100, 100);
		try {
		    String rutaLogo = equipo.getRutaFoto();

		    boolean logoCargado = false;

		    if (rutaLogo != null && !rutaLogo.isEmpty()) {

		        try {
		            ImageIcon escudoIcon = new ImageIcon(rutaLogo);
		            Image originalImage = escudoIcon.getImage();

		            int nuevoAlto = 100;
		            int nuevoAncho = (int) ((double) originalImage.getWidth(null) / originalImage.getHeight(null) * nuevoAlto);

		            escudoLabel.setIcon(new ImageIcon(originalImage.getScaledInstance(nuevoAncho, nuevoAlto, java.awt.Image.SCALE_SMOOTH)));
		            logoCargado = true;
		        } catch (Exception e) {
		            System.err.println("No se pudo cargar el logo desde: " + rutaLogo);
		        }
		    }

		    if (!logoCargado) {
		        escudoLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/imagen_por_defecto.png")));
		    }
		} catch (Exception e) {
		    System.err.println("No se pudo cargar el escudo para el equipo: " + equipo.getNombre());
		}
		panelIzquierdo.add(escudoLabel);

		nombreLabel = new JLabel(equipo.getNombre());
		nombreLabel.setHorizontalAlignment(SwingConstants.LEFT);
		nombreLabel.setFont(new Font("SansSerif", Font.BOLD, 35));
		nombreLabel.setBounds(167, 24, 323, 133);
		nombreLabel.setForeground(new Color(204, 153, 102));
		panelIzquierdo.add(nombreLabel);

		entrenadorLabel = new JLabel("Entrenador: " + equipo.getEntrenador());
		entrenadorLabel.setFont(new Font("SansSerif", Font.PLAIN, 21));
		entrenadorLabel.setBounds(52, 242, 277, 23);
		entrenadorLabel.setForeground(new Color(60, 60, 60));
		panelIzquierdo.add(entrenadorLabel);

		estadioLabel = new JLabel("Estadio: " + equipo.getEstadio());
		estadioLabel.setFont(new Font("SansSerif", Font.PLAIN, 21));
		estadioLabel.setBounds(52, 328, 277, 30);
		estadioLabel.setForeground(new Color(60, 60, 60));
		panelIzquierdo.add(estadioLabel);

		fundacionLabel = new JLabel("Fundación: " + equipo.getFundacion());
		fundacionLabel.setFont(new Font("SansSerif", Font.PLAIN, 21));
		fundacionLabel.setBounds(52, 422, 277, 23);
		fundacionLabel.setForeground(new Color(60, 60, 60));
		panelIzquierdo.add(fundacionLabel);

		getContentPane().add(panelIzquierdo);
		
		ImageIcon originalImageIcon = new ImageIcon(getClass().getResource("/imagenes/editar.png"));
		Image originalImage = originalImageIcon.getImage();
		Image scaledImage = originalImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
		
		btnEditar = new BotonRedondeado("", null);
		btnEditar.setBackground(new Color(64, 64, 64));
		btnEditar.setBounds(415, 541, 50, 50);
		btnEditar.setFocusPainted(false);
		btnEditar.setIcon(scaledImageIcon);
		
		btnEditar.addActionListener(e -> {
		    btnEditar.setVisible(false);
			btnAñadir.setVisible(true);
			btnEliminar.setVisible(true);
			btnCambiarFoto.setVisible(true);

		    entrenadorLabel.setText("Entrenador:");
		    estadioLabel.setText("Estadio:");
		    fundacionLabel.setText("Fundación:");

		    entrenadorField = new TextoRedondeado(20);
		    entrenadorField.setFont(new Font("SansSerif", Font.PLAIN, 18));
		    entrenadorField.setBounds(170, 238, 277, 30);
		    entrenadorField.setText(equipo.getEntrenador());
		    panelIzquierdo.add(entrenadorField);

		    estadioField = new TextoRedondeado(20);
		    estadioField.setFont(new Font("SansSerif", Font.PLAIN, 18));
		    estadioField.setBounds(170, 328, 277, 30);
		    estadioField.setText(equipo.getEstadio());
		    panelIzquierdo.add(estadioField);

		    fundacionField = new TextoRedondeado(20);
		    fundacionField.setFont(new Font("SansSerif", Font.PLAIN, 18));
		    fundacionField.setBounds(170, 418, 277, 30);
		    fundacionField.setText("" + equipo.getFundacion());
		    panelIzquierdo.add(fundacionField);

		    entrenadorField.setVisible(true);
		    estadioField.setVisible(true);
		    fundacionField.setVisible(true);

		    btnGuardar = new BotonRedondeado("Guardar", (ImageIcon) null);
		    btnGuardar.setForeground(Color.WHITE);
		    btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 16));
		    btnGuardar.setFocusPainted(false);
		    btnGuardar.setBackground(Color.DARK_GRAY);
		    btnGuardar.setBounds(240, 546, 107, 40);
		    panelIzquierdo.add(btnGuardar);
		    btnGuardar.setVisible(true);

			btnGuardar.addActionListener(save -> {
				equipo.setEntrenador(entrenadorField.getText());
				equipo.setEstadio(estadioField.getText());
				equipo.setFundacion(Integer.valueOf(fundacionField.getText()));
				equipo.setJugadores(jugadores);

				try {
					String basePath = "src/imagenes/temporadas/Temporada " + temporada.getPeriodo() + "/" + equipo.getNombre() + "/";
					Files.createDirectories(Paths.get(basePath));
					
					File selectedFile = new File(basePath + equipo.getNombre() + ".png");
	                if (selectedFile.exists()) {
	                    Path destinationPath = Paths.get(basePath + equipo.getNombre() + ".png");
	                    Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
	                    equipo.setRutaFoto(basePath + equipo.getNombre() + ".png");
	                }

	                for (Jugador jugador : jugadores) {

	                    File carpeta = new File(basePath);
	                    File fotoFile = null;

	                    if (carpeta.exists() && carpeta.isDirectory()) {
	                        for (File archivo : carpeta.listFiles()) {
	                            if (archivo.isFile() && archivo.getName().startsWith(jugador.getNombre() + " " + jugador.getApellidos())) {
	                                fotoFile = archivo;
	                                break;
	                            }
	                        }
	                    }

	                    if (fotoFile != null) {
	                        String extension = fotoFile.getName().substring(fotoFile.getName().lastIndexOf("."));
	                        String nuevoPath = basePath + jugador.getNombre() + " " + jugador.getApellidos() + extension;

	                        Path source = fotoFile.toPath();
	                        Path destination = Paths.get(nuevoPath);

	                        try {
	                            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
	                            jugador.setRutaFoto(nuevoPath);
	                        } catch (Exception e1) {
	                            e1.printStackTrace();
	                        }
	                    }
	                }
					
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(this, 
							"Error al mover las fotos de los jugadores o guardar el escudo: " + e1.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
					
					// 🔴 Log del error al mover fotos o escudo
			        logClase.logError("Error al mover las fotos o guardar el escudo del equipo: " + equipo.getNombre(), e1);
			    }

				entrenadorLabel.setText("Entrenador: " + equipo.getEntrenador());
				estadioLabel.setText("Estadio: " + equipo.getEstadio());
				fundacionLabel.setText("Fundación: " + equipo.getFundacion());

				entrenadorLabel.setVisible(true);
				estadioLabel.setVisible(true);
				fundacionLabel.setVisible(true);

				entrenadorField.setVisible(false);
				estadioField.setVisible(false);
				fundacionField.setVisible(false);

				btnGuardar.setVisible(false);
				btnCancelar.setVisible(false);
				btnAñadir.setVisible(false);
				btnEliminar.setVisible(false);
				btnEditar.setVisible(true);
				btnCambiarFoto.setVisible(false);

				try {
					temporada.guardarTemporada(temporada);
					
					 // 🔴 Log cuando se guarda el equipo editado
			        logClase.logAction("Equipo actualizado: " + equipo.getNombre() + ", Entrenador: " + equipo.getEntrenador() + ", Estadio: " + equipo.getEstadio() + ", Fundación: " + equipo.getFundacion());
			        
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(this, "ERROR. No se han encontrado los datos de la temporada.","Error", JOptionPane.ERROR_MESSAGE);
					
					// 🔴 Log del error al guardar la temporada
			        logClase.logError("Error al guardar los datos de la temporada después de actualizar el equipo " + equipo.getNombre(), e1);
			    }
			});

		    btnCancelar = new BotonRedondeado("Cancelar", (ImageIcon) null);
		    btnCancelar.setForeground(Color.WHITE);
		    btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 16));
		    btnCancelar.setFocusPainted(false);
		    btnCancelar.setBackground(Color.GRAY);
		    btnCancelar.setBounds(358, 546, 107, 40);
		    panelIzquierdo.add(btnCancelar);
		    btnCancelar.setVisible(true);

		    btnCancelar.addActionListener(cancel -> {
		    	entrenadorLabel.setText("Entrenador: " + equipo.getEntrenador());
		    	estadioLabel.setText("Estadio: " + equipo.getEstadio());
		    	fundacionLabel.setText("Fundación: " + equipo.getFundacion());

		        entrenadorField.setVisible(false);
		        estadioField.setVisible(false);
		        fundacionField.setVisible(false);

		        btnGuardar.setVisible(false);
		        btnCancelar.setVisible(false);
		        btnAñadir.setVisible(false);
		        btnEliminar.setVisible(false);
		        btnEditar.setVisible(true);
		        btnCambiarFoto.setVisible(false);
		    });
		});

		panelIzquierdo.add(btnEditar);
	    
	    if ("Administrador".equals(rol) && "En creación".equals(temporada.getEstado())) {
	        btnEditar.setVisible(true);
	    } else if ("Entrenador".equals(rol) && "En creación".equals(temporada.getEstado())) {
	        try {
	            btnEditar.setVisible(verificarEquipoEntrenador(usuario, equipo));
	        } catch (IOException | ClassNotFoundException e) {
	            btnEditar.setVisible(false);
	        }
	    } else {
	        btnEditar.setVisible(false);
	    }
		
		btnCambiarFoto = new BotonRedondeado("Cambiar Logo", null);
		btnCambiarFoto.setBounds(50, 167, 150, 40);
		btnCambiarFoto.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnCambiarFoto.setBackground(new Color(204, 153, 102));
		btnCambiarFoto.setForeground(new Color(255, 255, 255));
		btnCambiarFoto.setFocusPainted(false);
		
		btnCambiarFoto.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	JnaFileChooser fileChooser = new JnaFileChooser();
		    	fileChooser.setTitle("Selecciona una foto");
				fileChooser.addFilter("Imágenes (*.jpg; *.jpeg; *.png; *.gif)", "jpg", "jpeg", "png", "gif");
				fileChooser.addFilter("Todos los Archivos", "*");
		        
				if (fileChooser.showOpenDialog(null)) {
				    File selectedFile = fileChooser.getSelectedFile();
				    try {
				        // Verificar si la imagen está corrupta
				        BufferedImage imagen = ImageIO.read(selectedFile);
				        if (imagen == null) {
				            throw new IOException("Formato de imagen no soportado o archivo corrupto.");
				        }

				        // Cargar y escalar la imagen
				        ImageIcon newLogo = new ImageIcon(selectedFile.getAbsolutePath());
				        Image scaledImage = newLogo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				        escudoLabel.setIcon(new ImageIcon(scaledImage));

				        // Obtener la extensión del archivo original
				        String fileName = selectedFile.getName();
				        int lastIndex = fileName.lastIndexOf('.');
				        String extension = (lastIndex == -1) ? "" : fileName.substring(lastIndex);

				        // Ruta de destino con el nombre del equipo y la extensión original
				        String basePath = "src/imagenes/temporadas/Temporada " + temporada.getPeriodo() + "/" + equipo.getNombre() + extension;
				        Path destinationPath = Paths.get(basePath);

				        // Crear directorios si no existen y guardar la imagen
				        Files.createDirectories(destinationPath.getParent());
				        Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
				        equipo.setRutaFoto(basePath);

						// 🔴 Log cuando se cambia el logo del equipo
						logClase.logAction("Logo cambiado para el equipo " + equipo.getNombre() + ": " + selectedFile.getName());

				    } catch (IOException ex) {
				        JOptionPane.showMessageDialog(null, 
				            "La imagen está corrupta, prueba con otra imagen.", 
				            "Error", 
				            JOptionPane.ERROR_MESSAGE);
						// 🔴 Log del error al cambiar el logo
						logClase.logError("Error al cambiar el logo del equipo " + equipo.getNombre(), ex);
				    }
				}
		    }
		});
		
		panelIzquierdo.add(btnCambiarFoto);
		btnCambiarFoto.setVisible(false);

		panelDerecho = new JPanel();
		panelDerecho.setBackground(new Color(204, 153, 102));
		panelDerecho.setBounds(500, 0, 487, 613);
		panelDerecho.setLayout(null);

		titulo = new JLabel("Jugadores");
		titulo.setFont(new Font("SansSerif", Font.BOLD, 45));
		titulo.setForeground(new Color(0xfef4c6));
		titulo.setBounds(23, 20, 306, 64);
		panelDerecho.add(titulo);

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
		scrollPane.setBounds(23, 105, 442, 349);
		panelDerecho.add(scrollPane);

		jugadores = equipo.getJugadores();
		for (Jugador jugador : jugadores) {
            String rutaFoto = jugador.getRutaFoto();
            
            ImageIcon fotoIcon = null;
            File fotoFile = new File(rutaFoto);

            if (fotoFile.exists() && fotoFile.isFile()) {
                Image originalIcon = new ImageIcon(rutaFoto).getImage();
                int height = 40;
                int width = (int) (originalIcon.getWidth(null) * ((double) height / originalIcon.getHeight(null)));
                Image scaledPlayerImage = originalIcon.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                fotoIcon = new ImageIcon(scaledPlayerImage);
            } else {
            	Image originalIcon = new ImageIcon("src/imagenes/user.png").getImage();
            	Image scaledPlayerImage = originalIcon.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            	fotoIcon = new ImageIcon(scaledPlayerImage);
            }
		    
			Object[] row = { fotoIcon, jugador.getNombre() + " " + jugador.getApellidos(), jugador.getPosicion(), jugador.getDorsal(), };
			tableModel.addRow(row);
		}

		btnVolver = new BotonRedondeado("Volver a Equipos", null);
		btnVolver.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnVolver.setBackground(new Color(64, 64, 64));
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setBounds(285, 542, 170, 40);
		btnVolver.setFocusPainted(false);
		btnVolver.addActionListener(_ -> dispose());
		panelDerecho.add(btnVolver);

		getContentPane().add(panelDerecho);

		btnAñadir = new BotonRedondeado("Añadir Jugador", null);
		btnAñadir.setBounds(53, 465, 180, 40);
		btnAñadir.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnAñadir.setBackground(new Color(19, 66, 126));
		btnAñadir.setForeground(new Color(255, 255, 255));
		btnAñadir.setFocusPainted(false);
		
		btnAñadir.addActionListener(e -> {
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
		                    fotoIcon = new ImageIcon(rutaFoto);
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
		                
		                // 🔴 Log cuando se añade un jugador
		                logClase.logAction("Jugador agregado al equipo " + equipo.getNombre() + ": " + nuevoJugador.getNombre() + " " + nuevoJugador.getApellidos() + ", Posición: " + nuevoJugador.getPosicion() + ", Dorsal: " + nuevoJugador.getDorsal());
		            }
		        }
		    });
		});
		
		btnEliminar = new BotonRedondeado("Eliminar Jugador", null);
		btnEliminar.setBounds(255, 465, 180, 40);
		btnEliminar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnEliminar.setBackground(new Color(243, 70, 12));
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setFocusPainted(false);
		
		btnEliminar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int[] selectedRows = tablaJugadores.getSelectedRows();
		        
		        if (selectedRows.length > 0) {
		            Arrays.sort(selectedRows);
		            for (int i = selectedRows.length - 1; i >= 0; i--) {
		                int selectedRow = selectedRows[i];
		                
		                Jugador jugadorEliminado = jugadores.get(selectedRow);
		                
		                // 🔴 Log cuando se elimina un jugador
		                logClase.logAction("Jugador eliminado del equipo " + equipo.getNombre() + ": " + jugadorEliminado.getNombre() + " " + jugadorEliminado.getApellidos());

		                tableModel.removeRow(selectedRow);
		                jugadores.remove(selectedRow);
		            }

		            tableModel.setRowCount(0);
		            for (Jugador jugador : jugadores) {
		                String rutaFoto = jugador.getRutaFoto();
		                
		                ImageIcon fotoIcon = null;

		                if (rutaFoto != null && !rutaFoto.isEmpty()) {
	                        Image originalIcon = new ImageIcon(rutaFoto).getImage();
	                        int height = 40;
	                        int width = (int) (originalIcon.getWidth(null) * ((double) height / originalIcon.getHeight(null)));
	                        Image scaledPlayerImage = originalIcon.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	                        fotoIcon = new ImageIcon(scaledPlayerImage);
	                    } else {
	                    	Image originalIcon = new ImageIcon("src/imagenes/user.png").getImage();
	                    	Image scaledPlayerImage = originalIcon.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	                    	fotoIcon = new ImageIcon(scaledPlayerImage);
	                    }
					    
		                tableModel.addRow(new Object[]{
		                    fotoIcon,
		                    jugador.getNombre() + " " + jugador.getApellidos(),
		                    jugador.getPosicion(),
		                    jugador.getDorsal()
		                });
		            }
		        } else {
		            JOptionPane.showMessageDialog(
		                null,
		                "Por favor, selecciona al menos un jugador para eliminarlo.",
		                "Advertencia",
		                JOptionPane.WARNING_MESSAGE
		            );
		        }
		    }
		});
		
		panelDerecho.add(btnEliminar);
		btnEliminar.setVisible(false);
		
		panelDerecho.add(btnAñadir);
		btnAñadir.setVisible(false);
	}
	
	@SuppressWarnings("unchecked")
	private boolean verificarEquipoEntrenador(String usuario, Equipo equipo) throws IOException, ClassNotFoundException {
	    String archivoUsuarios = "data/usuarios.ser";
	    try (FileInputStream fis = new FileInputStream(archivoUsuarios);
	         ObjectInputStream ois = new ObjectInputStream(fis)) {

	        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) ois.readObject();

	        for (Usuario u : usuarios) {
	            if (u.getUsuario().equals(usuario)) {
	                return u.getEquipo().equals(equipo);
	            }
	        }
	    }

	    return false;
	}
}
