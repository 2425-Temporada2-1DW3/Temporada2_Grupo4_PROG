
package LPB;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import LPBCLASES.BotonRedondeado;
import LPBCLASES.Equipo;
import LPBCLASES.Jugador;
import LPBCLASES.Temporada;
import LPBCLASES.logClase;

/**
 * Clase EquiposTemporada representa la interfaz gráfica que gestiona la visualización y manipulación de equipos dentro de una temporada.
 * 
 * Permite listar temporadas, agregar y eliminar equipos, así como cambiar entre diferentes temporadas seleccionadas.
 * Implementa funcionalidades específicas para usuarios con rol "Administrador". También permite visualizar los datos de un equipo en particular.
 * 
 * Extiende {@link JFrame} y maneja eventos relacionados con la gestión de temporadas y equipos.
 */
public class EquiposTemporada extends JFrame {

	private static final long serialVersionUID = -2296678961838970996L;
	private JPanel panelSuperior;
	private ImageIcon logo;
	private JLabel labelLogo;
	private JLabel labelUsuario;
	private JPanel panelInferior;
	private JLabel titulo;
	private JButton btnNuevo;
	private JScrollPane scrollPane;
	private JButton btnVolverMenu;
	private GridBagConstraints gbc;
	private JButton btnEquipo;
	private JPanel equipoPanel;
	private GridBagConstraints gbcBtnEliminar;
	private JButton btnEliminar;
	private VerEquipo VerEquipoFrame;
	private GridBagConstraints gbcBtnEquipo;
	private JComboBox<String> SelectTemporadas;
	private JPanel panelEquipos;
	private Boolean datosModificados = false;
	private Map<String, List<Equipo>> equiposPorTemporada;
	private String rol;
	private String usuario;
	private Temporada temporadaSeleccionada = null;
	private String temporadaActiva = null;
	private List<Equipo> equipos;

	/**
     * Constructor de la clase EquiposTemporada.
     * 
     * @param rol       Rol del usuario (Administrador, Usuario).
     * @param usuario   Nombre del usuario que ha iniciado sesión.
     * @param temporada Temporada seleccionada o activa.
     */
	public EquiposTemporada(String rol, String usuario, Temporada temporada) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
		setTitle("LPB Basketball - Equipos");
		setSize(850, 550);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
			    if (datosModificados) {
			        int opcion = JOptionPane.showConfirmDialog(EquiposTemporada.this, "Los datos han sido modificados. ¿Desea guardar antes de salir?", "Confirmar salida", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			        switch (opcion) {
			        case JOptionPane.YES_OPTION:
			            guardarDatos();
			            System.exit(0);
			            break;
			        case JOptionPane.NO_OPTION:
			            System.exit(0);
			            break;
			        case JOptionPane.CANCEL_OPTION:
			        case JOptionPane.CLOSED_OPTION:
			            return;
			        }
			    } else {
			        System.exit(0);
			    }
			}
		});

		getContentPane().setLayout(null);
		
		this.rol = rol;
		this.usuario = usuario;
		if (temporada != null) {
			this.temporadaSeleccionada = temporada;
		}

		panelSuperior = new JPanel();
		panelSuperior.setBackground(new Color(255, 243, 205));
		panelSuperior.setBounds(0, 0, 836, 110);
		panelSuperior.setLayout(null);

		logo = new ImageIcon(getClass().getResource("/imagenes/logo150.png"));
		labelLogo = new JLabel(logo);
		labelLogo.setBounds(686, -20, 150, 150);
		panelSuperior.add(labelLogo);

		getContentPane().add(panelSuperior);

		titulo = new JLabel("Equipos");
		titulo.setBounds(10, 28, 306, 47);
		titulo.setFont(new Font("SansSerif", Font.BOLD, 50));
		titulo.setForeground(new Color(0x13427e));
		panelSuperior.add(titulo);

		panelInferior = new JPanel();
		panelInferior.setBackground(new Color(204, 153, 102));
		panelInferior.setBounds(0, 110, 836, 403);
		panelInferior.setLayout(null);

		labelUsuario = new JLabel("Usuario: " + usuario);
		labelUsuario.setFont(new Font("SansSerif", Font.PLAIN, 16));
		labelUsuario.setForeground(new Color(0x545454));
		labelUsuario.setBounds(20, 360, 200, 20);
		panelInferior.add(labelUsuario);

	    SelectTemporadas = new JComboBox<String>();
	    SelectTemporadas.setBackground(new Color(0, 64, 128));
	    SelectTemporadas.setForeground(new Color(40, 40, 40));
	    SelectTemporadas.setFont(new Font("SansSerif", Font.PLAIN, 16));
	    SelectTemporadas.setBounds(545, 27, 200, 40);
	    panelInferior.add(SelectTemporadas);
	    
		ImageIcon originalImageIcon = new ImageIcon(getClass().getResource("/imagenes/plus.png"));
		Image originalImage = originalImageIcon.getImage();
		Image scaledImage = originalImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

		btnNuevo = new BotonRedondeado("", null);
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnNuevo.setBackground(new Color(0x545454));
		btnNuevo.setBounds(755, 22, 50, 50);
		btnNuevo.setFocusPainted(false);
		btnNuevo.setIcon(scaledImageIcon);
		
		btnNuevo.addActionListener(e -> {
            guardarDatos();
		    int opcion = JOptionPane.showConfirmDialog(null, "¿Deseas añadir un equipo de una temporada anterior?", "Añadir equipo", JOptionPane.YES_NO_OPTION);

		    if (opcion == JOptionPane.YES_OPTION) {
		        String periodoActual = temporadaSeleccionada.getPeriodo();

		        File carpeta = new File("data/");
		        File[] archivos = carpeta.listFiles((dir, name) -> name.startsWith("temporada_") && name.endsWith(".ser"));

		        if (archivos != null) {
		            Map<String, List<String>> equiposPorTemporada2 = new HashMap<>();
		            List<String> temporadas = new ArrayList<>();
		            Set<String> nombresEquiposActuales = temporadaSeleccionada.getEquipos().stream()
		                .map(Equipo::getNombre)
		                .collect(Collectors.toSet());
		            
		            List<String> equiposTempActuales = temporadaSeleccionada.getEquipos().stream()
		                    .map(Equipo::getNombre)
		                    .sorted()
		                    .collect(Collectors.toList());
		                equiposPorTemporada2.put(periodoActual, equiposTempActuales);

		            for (File archivo : archivos) {
		                String nombreArchivo = archivo.getName();
		                String periodo = nombreArchivo.replace("temporada_", "").replace(".ser", "");

		                if (periodo.equals(periodoActual)) {
		                    continue;
		                }

		                temporadas.add(periodo);

		                try {
		                    Temporada temp = Temporada.cargarTemporada(periodo);
		                    List<String> equiposTemp = temp.getEquipos().stream()
		                        .map(Equipo::getNombre)
		                        .filter(nombre -> !nombresEquiposActuales.contains(nombre))
		                        .map(nombre -> nombre + " (" + periodo + ")")
		                        .sorted()
		                        .collect(Collectors.toList());

		                    if (!equiposTemp.isEmpty()) {
		                        equiposPorTemporada2.put(periodo, equiposTemp);
		                    }
		                } catch (Exception ex) {
		                    System.out.println("No se pudo cargar la temporada " + periodo);
		                }
		            }

		            temporadas.sort(Comparator.comparing((String periodo) -> periodo.substring(0, 4)).reversed());

		            List<String> opcionesEquipos = new ArrayList<>();
		            for (String periodo : temporadas) {
		                opcionesEquipos.addAll(equiposPorTemporada2.getOrDefault(periodo, Collections.emptyList()));
		            }

		            if (!opcionesEquipos.isEmpty()) {
		                String equipoSeleccionado = (String) JOptionPane.showInputDialog(null, 
		                    "Selecciona un equipo:", "Seleccionar equipo", JOptionPane.QUESTION_MESSAGE, null, opcionesEquipos.toArray(), opcionesEquipos.get(0));

		                if (equipoSeleccionado != null) {
		                    String[] partes = equipoSeleccionado.split(" \\(");
		                    String nombreEquipo = partes[0];
		                    String periodoOrigen = partes[1].replace(")", "");

		                    try {
		                        Temporada tempOrigen = Temporada.cargarTemporada(periodoOrigen);
		                        Equipo equipo = tempOrigen.getEquipos().stream()
		                            .filter(eq -> eq.getNombre().equals(nombreEquipo))
		                            .findFirst()
		                            .orElse(null);

		                        if (equipo != null) {
		                        	String carpetaOrigen = "src/imagenes/temporadas/Temporada " + periodoOrigen + "/" + equipo.getNombre() + "/";
		                        	String carpetaDestino = "src/imagenes/temporadas/Temporada " + temporadaSeleccionada.getPeriodo() + "/" + equipo.getNombre() + "/";

		                        	File carpetaOrigenFile = new File(carpetaOrigen);
		                        	File carpetaDestinoFile = new File(carpetaDestino);
		                        	String rutaFotoEquipo = null;

		                        	if (carpetaOrigenFile.exists() && carpetaOrigenFile.isDirectory()) {
		                        	    if (!carpetaDestinoFile.exists()) {
		                        	        carpetaDestinoFile.mkdirs();
		                        	    }

		                        	    File[] archivosTemp = carpetaOrigenFile.listFiles();
		                        	    if (archivosTemp != null) {
		                        	        for (File archivo : archivosTemp) {
		                        	            File destino = new File(carpetaDestinoFile, archivo.getName());
		                        	            if (archivo.isDirectory()) {
		                        	                destino.mkdirs();
		                        	                File[] subArchivos = archivo.listFiles();
		                        	                if (subArchivos != null) {
		                        	                    for (File subArchivo : subArchivos) {
		                        	                        Files.copy(subArchivo.toPath(), new File(destino, subArchivo.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
		                        	                    }
		                        	                }
		                        	            } else {
		                        	                Files.copy(archivo.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);

		                        	                if (archivo.getName().startsWith(equipo.getNombre() + ".")) {
		                        	                    String extension = archivo.getName().substring(archivo.getName().lastIndexOf("."));
		                        	                    rutaFotoEquipo = carpetaDestino + equipo.getNombre() + extension;
		                        	                }
		                        	            }
		                        	        }
		                        	    }
		                        	}

		                        	if (rutaFotoEquipo == null) {
		                        	    rutaFotoEquipo = carpetaDestino + equipo.getNombre() + ".png";
		                        	}

		                            Equipo nuevoEquipo = new Equipo(
		                                equipo.getNombre(),
		                                equipo.getEntrenador(),
		                                equipo.getJugadores(),
		                                equipo.getEstadio(),
		                                equipo.getFundacion(),
		                                rutaFotoEquipo
		                            );

		                            nuevoEquipo.setRutaFoto(rutaFotoEquipo);

		                            for (Jugador jugador : nuevoEquipo.getJugadores()) {
		                                String rutaFotoJugador = null;

		                                File archivoFoto = new File(carpetaDestino, jugador.getNombre() + " " + jugador.getApellidos());

		                                String extension = "";
		                                if (archivoFoto.exists()) {
		                                    int posicionExtension = archivoFoto.getName().lastIndexOf(".");
		                                    if (posicionExtension != -1) {
		                                        extension = archivoFoto.getName().substring(posicionExtension);
		                                    }
		                                }

		                                rutaFotoJugador = carpetaDestino + jugador.getNombre() + " " + jugador.getApellidos() + extension;
		                                jugador.setRutaFoto(rutaFotoJugador);
		                            }


		                            List<Equipo> listaEquipos = temporadaSeleccionada.getEquipos();
		                            listaEquipos.add(nuevoEquipo);
		                            temporadaSeleccionada.setEquipos(listaEquipos);
		                            temporadaSeleccionada.guardarTemporada(temporadaSeleccionada);

		                            temporadaSeleccionada = Temporada.cargarTemporada(temporadaSeleccionada.getPeriodo());
		                            equipos = temporadaSeleccionada.getEquipos();
		                            equiposPorTemporada.put(temporadaSeleccionada.getPeriodo(), equipos);
		                            actualizarPanelEquipos(temporadaSeleccionada.getPeriodo());

		                            JOptionPane.showMessageDialog(null, "Equipo añadido correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		                        } else {
		                            System.out.println("No se encontró la carpeta del equipo.");
		                        }
		                    } catch (IOException e1) {
		                        System.out.println("Error al copiar la carpeta: " + e1.getMessage());
		                    } catch (Exception ex) {
		                        System.out.println("Error al cargar el equipo.");
		                    }
		                }
		            } else {
		                JOptionPane.showMessageDialog(null, 
		                    "No hay equipos disponibles en temporadas anteriores.", "Información", JOptionPane.INFORMATION_MESSAGE);
		            }
		        }
		    } else {
		        AgregarEquipo agregarEquipo = new AgregarEquipo(temporadaSeleccionada);
		        agregarEquipo.setVisible(true);

		        try {
		            temporadaSeleccionada.setEquipos(equipos);
		            temporadaSeleccionada.guardarTemporada(temporadaSeleccionada);
		        } catch (IOException e1) {
		            System.out.println("Error al guardar los datos de la temporada.");
		        }
		        
		        agregarEquipo.addWindowListener(new java.awt.event.WindowAdapter() {
		            @Override
		            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
		                try {
		                    String selectedTemporada = (String) SelectTemporadas.getSelectedItem();
		                    String periodo = selectedTemporada.replace("Temporada ", "");
		                    temporadaSeleccionada = Temporada.cargarTemporada(periodo);
		                    equipos = temporadaSeleccionada.getEquipos();
		                    actualizarPanelEquipos(periodo);
		                    
		                } catch (ClassNotFoundException e) {
		                    System.out.println("ERROR. No se ha encontrado la clase Temporada.");
		                } catch (IOException e) {
		                    System.out.println("ERROR. No se han encontrado los datos de la temporada.");
		                }
		            }
		        });
		    }
		});
		panelInferior.add(btnNuevo);
		btnNuevo.setVisible(false);

		btnVolverMenu = new BotonRedondeado("Volver al Menú", null);
		btnVolverMenu.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnVolverMenu.setBackground(new Color(64, 64, 64));
		btnVolverMenu.setForeground(Color.WHITE);
		btnVolverMenu.setBounds(663, 351, 150, 30);
		btnVolverMenu.setFocusPainted(false);
		panelInferior.add(btnVolverMenu);

		btnVolverMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    if (datosModificados) {
			        int opcion = JOptionPane.showConfirmDialog(getContentPane(), "Los datos han sido modificados. ¿Desea guardar antes de volver?", "Confirmar salida", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			        switch (opcion) {
				        case JOptionPane.YES_OPTION:
				            guardarDatos();
				            new Menu(rol, usuario).setVisible(true);
				            dispose();
				            break;
				        case JOptionPane.NO_OPTION:
				        	new Menu(rol, usuario).setVisible(true);
				        	dispose();
				            break;
				        case JOptionPane.CANCEL_OPTION:
				        case JOptionPane.CLOSED_OPTION:
				            return;
			        }
			    } else {
					new Menu(rol, usuario).setVisible(true);
					dispose();
			    }
			}
		});

		panelEquipos = new JPanel(new GridBagLayout());
		panelEquipos.setBorder(null);
		panelEquipos.setBackground(new Color(204, 153, 102));

		scrollPane = new JScrollPane(panelEquipos);
		scrollPane.setBounds(10, 200, 800, 220);
		scrollPane.setBorder(null);
		scrollPane.setVisible(true);

		getContentPane().add(scrollPane);
		getContentPane().add(panelInferior);

	    equiposPorTemporada = new HashMap<>();
	    
	    SelectTemporadas.removeActionListener(selectListener);
	    listarTemporadas();
	    SelectTemporadas.addActionListener(selectListener);

	    if (temporadaActiva != null) {
	        SelectTemporadas.setSelectedItem("Temporada " + temporadaActiva);
	        actualizarPanelEquipos(temporadaActiva);
	    }
	    
		if ("Administrador".equals(rol) && "En creación".equals(temporadaSeleccionada.getEstado())) {
			btnNuevo.setVisible(true);
		}
	}
	
	/**
	 * Método que lista las temporadas disponibles en el sistema.
	 * 
	 * Carga las temporadas desde archivos serializados y las agrega al JComboBox de temporadas.
	 * Si no se ha seleccionado una temporada, se busca la temporada activa y se selecciona automáticamente.
	 * 
	 * @return Lista de temporadas disponibles
	 */
	private List<String> listarTemporadas() {
	    File carpetaData = new File("data");
	    List<String> temporadas = new ArrayList<>();

	    SelectTemporadas.removeAllItems();

	    if (carpetaData.exists() && carpetaData.isDirectory()) {
	        for (File archivo : carpetaData.listFiles()) {
	            if (archivo.isFile() && archivo.getName().endsWith(".ser") && archivo.getName().startsWith("temporada_")) {
	                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
	                    Temporada temporada = (Temporada) ois.readObject();
	                    SelectTemporadas.addItem(temporada.getPeriodo()); 
	                    temporadas.add(temporada.getPeriodo());
	                } catch (IOException | ClassNotFoundException ex) {
	                    System.err.println("Error al deserializar el archivo: " + archivo.getName());
	                    ex.printStackTrace();
	                }
	            }
	        }
	    } else {
	        System.out.println("La carpeta 'data' no existe o está vacía.");
	    }

	    if (temporadaSeleccionada == null) {
		    for (String temporada : temporadas) {
		        try {
		            Temporada temp = Temporada.cargarTemporada(temporada);
		            if (temp != null && temp.getEstado().equals("Activa")) {
		                temporadaActiva = temporada;
		                break;
		            }
		        } catch (ClassNotFoundException | IOException e) {
		            System.out.println("Error al cargar los datos de la temporada: " + temporada);
		            e.printStackTrace();
		        }
		    }
	    } else {
	    	temporadaActiva = temporadaSeleccionada.getPeriodo();
	    }

	    SelectTemporadas.removeAllItems();
	    for (String temporada : temporadas) {
	        SelectTemporadas.addItem("Temporada " + temporada);
	    }

	    if (temporadaActiva != null) {
	        SelectTemporadas.setSelectedItem("Temporada " + temporadaActiva);
	    }

	    return temporadas;
	}
	
	/**
	 * Listener para el JComboBox de temporadas.
	 * 
	 * Carga los equipos de la temporada seleccionada y actualiza el panel de
	 * equipos.
	 * 
	 * Muestra el botón para agregar un nuevo equipo si el usuario tiene rol "Administrador".
	 * 
	 * Si el usuario tiene rol "Administrador" y la temporada está "En creación",
	 * muestra el botón para agregar un nuevo equipo.
	 * 
	 * @param e Evento de selección de temporada.
	 * 
	 * @see ActionListener
	 */
	private ActionListener selectListener = e -> {
	    if (datosModificados) {
	        int opcion = JOptionPane.showConfirmDialog(getContentPane(), "Los datos han sido modificados. ¿Desea guardar antes de volver?", "Confirmar salida", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	        switch (opcion) {
	            case JOptionPane.YES_OPTION:
	                guardarDatos();
	                break;
	            case JOptionPane.NO_OPTION:
	                break;
	            case JOptionPane.CANCEL_OPTION:
	            case JOptionPane.CLOSED_OPTION:
	                SelectTemporadas.setSelectedItem(temporadaSeleccionada);
	                return;
	        }
	    }
	    
	    String selectedTemporada = (String) SelectTemporadas.getSelectedItem();
	    if (selectedTemporada != null) {
	        String periodo = selectedTemporada.replace("Temporada ", "");
	        try {
	            temporadaSeleccionada = Temporada.cargarTemporada(periodo);
	            
	    		if ("Administrador".equals(rol) && "En creación".equals(temporadaSeleccionada.getEstado())) {
	    			btnNuevo.setVisible(true);
	    		} else {
	    			btnNuevo.setVisible(false);
	    		}
	            
	            if (temporadaSeleccionada != null) {
	                equipos = temporadaSeleccionada.getEquipos();
	                if (equipos != null) {
	                    equiposPorTemporada.put(periodo, equipos);
	                    actualizarPanelEquipos(periodo);
	                } else {
	                    JOptionPane.showMessageDialog(null, "No se encontraron equipos para la temporada seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            } else {
	                JOptionPane.showMessageDialog(null, "No se pudo cargar la temporada seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        } catch (ClassNotFoundException | IOException e1) {
	            e1.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error al cargar los datos de la temporada seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	};
	
	/**
	 * Método que actualiza el título de la ventana principal.
	 * 
	 * El título de la ventana se compone de "LPB Basketball - Equipos".
	 * 
	 * Si se han modificado los datos, se agrega un asterisco al título.
	 */
	private void actualizarTitulo() {
        String title = "LPB Basketball - Equipos";
        if (datosModificados) {
            title = "*" + title;
        }
        setTitle(title);
	}

	/**
	 * Método que actualiza el panel de equipos.
	 * 
	 * Elimina los componentes del panel de equipos y agrega los equipos de la
	 * temporada seleccionada. Para cada equipo, se crea un botón con el nombre y el escudo del equipo.
	 * 
	 * Si el usuario tiene rol "Administrador" y la temporada está "En creación", se agrega un botón para eliminar el equipo.
	 * 
	 * Se agrega un listener al botón de cada equipo para visualizar los datos del equipo.
	 * 
	 * Si no hay equipos disponibles, se muestra un mensaje de aviso.
	 * 
	 * @param temporada Temporada seleccionada
	 */
	private void actualizarPanelEquipos(String temporada) {
	    panelEquipos.removeAll();
	    equipos = equiposPorTemporada.get(temporada);

	    if (equipos == null || equipos.isEmpty()) {
	        JLabel aviso = new JLabel("No hay equipos disponibles para la temporada seleccionada.");
	        aviso.setFont(new Font("SansSerif", Font.PLAIN, 20));
	        aviso.setForeground(new Color(0x13427e));
	        panelEquipos.add(aviso);
	        panelEquipos.revalidate();
	        panelEquipos.repaint();
	        return;
	    }

	    gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.insets = new Insets(10, 10, 10, 10);
	    gbc.fill = GridBagConstraints.HORIZONTAL;

	    for (Equipo equipo : equipos) {
	        equipoPanel = new JPanel(new GridBagLayout());
	        equipoPanel.setBackground(new Color(204, 153, 102));

	        btnEquipo = new BotonRedondeado(equipo.getNombre(), null);
	        try {
	            ImageIcon escudoIcon = new ImageIcon(equipo.getRutaFoto());
	            Image originalImage = escudoIcon.getImage();

	            int nuevoAlto = 50;
	            int nuevoAncho = (int) ((double) originalImage.getWidth(null) / originalImage.getHeight(null) * nuevoAlto);

	            ImageIcon iconoEscalado = new ImageIcon(originalImage.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH));
	            btnEquipo.setIcon(iconoEscalado);
	        } catch (NullPointerException e) {
	            JOptionPane.showMessageDialog(null, "Error: Ruta de imagen nula para el equipo: " + equipo.getNombre(), "Error de Ruta", JOptionPane.ERROR_MESSAGE);
	            e.printStackTrace();
	            btnEquipo.setIcon(new ImageIcon(getClass().getResource("/imagenes/imagen_por_defecto.png")));
	        }
	    
	        btnEquipo.setFont(new Font("SansSerif", Font.PLAIN, 20));
	        btnEquipo.setBackground(new Color(0xf46b20));
	        btnEquipo.setHorizontalAlignment(SwingConstants.LEFT);
	        btnEquipo.setForeground(Color.WHITE);
	        btnEquipo.setFocusPainted(false);
	        btnEquipo.setIconTextGap(10);
	        
	        gbcBtnEquipo = new GridBagConstraints();
	        gbcBtnEquipo.gridx = 0;
	        gbcBtnEquipo.gridy = 0;
	        gbcBtnEquipo.insets = new Insets(5, 5, 5, 5);
	        btnEquipo.setPreferredSize(new Dimension(290, 60));
	        equipoPanel.add(btnEquipo, gbcBtnEquipo);

			btnEquipo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					VerEquipoFrame = new VerEquipo(temporadaSeleccionada, equipo, rol, usuario);
					VerEquipoFrame.setVisible(true);
					
					VerEquipoFrame.addWindowListener(new java.awt.event.WindowAdapter() {
				        @Override
				        public void windowClosed(java.awt.event.WindowEvent e) {
				        	actualizarPanelEquipos(temporada);
				        }
				    });
				}
			});

	        if ("Administrador".equals(rol) && "En creación".equals(temporadaSeleccionada.getEstado())) {
        		ImageIcon originalImageIcon = new ImageIcon(getClass().getResource("/imagenes/papelera.png"));
        		Image originalImage = originalImageIcon.getImage();
        		Image scaledImage = originalImage.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        		ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        		
	            btnEliminar = new BotonRedondeado("", null);
	            btnEliminar.setFont(new Font("SansSerif", Font.PLAIN, 20));
	            btnEliminar.setBackground(new Color(0x545454));
	            btnEliminar.setForeground(Color.WHITE);
	            btnEliminar.setFocusPainted(false);
	            btnEliminar.setIcon(scaledImageIcon);
	            
	            gbcBtnEliminar = new GridBagConstraints();
	            gbcBtnEliminar.gridx = 1;
	            gbcBtnEliminar.gridy = 0;
	            gbcBtnEliminar.insets = new Insets(5, 5, 5, 5);
	            btnEliminar.setPreferredSize(new Dimension(60, 60));
	            equipoPanel.add(btnEliminar, gbcBtnEliminar);

	            btnEliminar.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    if (equipos.remove(equipo)) {
	                    	 // === INICIO: LOGGING PARA ELIMINAR EQUIPO ===
	                        logClase.logAction("Equipo eliminado: '" + equipo.getNombre() + "' de la temporada " + temporadaSeleccionada.getPeriodo() + ".");
	                        // === FIN: LOGGING PARA ELIMINAR EQUIPO ===
	                        actualizarPanelEquipos(temporada);
	                        datosModificados = true;
	                        actualizarTitulo();
	                    } else {
	                        JOptionPane.showMessageDialog(null, "No se pudo eliminar el equipo. " + equipo.getNombre(), "Error" , JOptionPane.ERROR_MESSAGE);
	                        // === INICIO: LOGGING PARA ERROR EN ELIMINAR EQUIPO ===
	                        logClase.logError("Error: No se pudo eliminar el equipo: '" + equipo.getNombre() + "' de la temporada " + temporadaSeleccionada.getPeriodo() + ".", null);
	                        // === FIN: LOGGING PARA ERROR EN ELIMINAR EQUIPO ===
	                    }
	                }
	            });

	        }

	        panelEquipos.add(equipoPanel, gbc);

	        if (gbc.gridx == 0) {
	            gbc.gridx = 1;
	        } else {
	            gbc.gridx = 0;
	            gbc.gridy++;
	        }
	    }

	    panelEquipos.revalidate();
	    panelEquipos.repaint();
	}
	
	/**
	 * Método para eliminar una carpeta y todo su contenido
	 * 
	 * Elimina la carpeta de imágenes del equipo que se ha eliminado.
	 * 
	 * @param carpeta File a eliminar
	 */
	private void eliminarCarpeta(File carpeta) {
	    File[] archivos = carpeta.listFiles();
	    if (archivos != null) {
	        for (File archivo : archivos) {
	            if (archivo.isDirectory()) {
	                eliminarCarpeta(archivo);
	            } else {
	                archivo.delete();
	            }
	        }
	    }
	    carpeta.delete();
	}
	
	private void guardarDatos() {
        try {
            temporadaSeleccionada.setEquipos(equipos);
            temporadaSeleccionada.guardarTemporada(temporadaSeleccionada);
            
            List<Equipo> equiposActuales = new ArrayList<>(temporadaSeleccionada.getEquipos());

            String rutaTemporada = "src/imagenes/temporadas/Temporada " + temporadaSeleccionada.getPeriodo() + "/";
            File carpetaTemporada = new File(rutaTemporada);
            
            if (carpetaTemporada.exists() && carpetaTemporada.isDirectory()) {
                File[] carpetasEquipos = carpetaTemporada.listFiles(File::isDirectory);
                if (carpetasEquipos != null) {
                    for (File carpetaEquipo : carpetasEquipos) {
                        String nombreEquipo = carpetaEquipo.getName();
                        boolean equipoExiste = equiposActuales.stream()
                            .anyMatch(equipo -> equipo.getNombre().equals(nombreEquipo));
                        
                        if (!equipoExiste) {
                            eliminarCarpeta(carpetaEquipo);
                        }
                    }
                }
            }
            
            datosModificados = false;
            actualizarTitulo();
        } catch (IOException e1) {
            System.out.println("ERROR. No se han encontrado los datos de la temporada.");
        }
	}
}
