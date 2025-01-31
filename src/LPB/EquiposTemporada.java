
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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import LPBCLASES.Temporada;
import LPBCLASES.logClase;

public class EquiposTemporada extends JFrame implements WindowListener {

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
	private Temporada temporadaSeleccionada;
	private List<Equipo> equipos;

	/**
	 * Create the frame.
	 */
	public EquiposTemporada(String rol, String usuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
		setTitle("LPB Basketball - Equipos");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		setSize(850, 550);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		this.rol = rol;
		this.usuario = usuario;

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

	    SelectTemporadas = new JComboBox<>();
	    SelectTemporadas.setBackground(new Color(0, 64, 128));
	    SelectTemporadas.setForeground(new Color(40, 40, 40));
	    SelectTemporadas.setFont(new Font("SansSerif", Font.PLAIN, 16));
	    SelectTemporadas.setBounds(545, 27, 200, 40);
	    
	    SelectTemporadas.addActionListener(e -> {
	        String selectedTemporada = (String) SelectTemporadas.getSelectedItem();
	        if (selectedTemporada != null) {
	            String periodo = selectedTemporada.replace("Temporada ", "");
	            try {
	                temporadaSeleccionada = Temporada.cargarTemporada(periodo);
	                
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
	    });
	    
	    panelInferior.add(SelectTemporadas);

		btnNuevo = new BotonRedondeado("+", null);
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnNuevo.setBackground(new Color(0x545454));
		btnNuevo.setBounds(755, 22, 50, 50);
		btnNuevo.setFocusPainted(false);
		
		btnNuevo.addActionListener(e -> {
        	AgregarEquipo agregarEquipo = new AgregarEquipo(temporadaSeleccionada);
        	agregarEquipo.setVisible(true);
            
            agregarEquipo.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    try {
                    	String selectedTemporada = (String) SelectTemporadas.getSelectedItem();
                    	String periodo = selectedTemporada.replace("Temporada ", "");
                    	temporadaSeleccionada = Temporada.cargarTemporada(periodo);
                        equipos = temporadaSeleccionada.getEquipos();
                        equiposPorTemporada.put(periodo, equipos);
						actualizarPanelEquipos(periodo);
						
					} catch (ClassNotFoundException e) {
						System.out.println("ERROR. No se ha encontrado la clase Temporada.");
					} catch (IOException e) {
						System.out.println("ERROR. No se han encontrado los datos de la temporada.");
					}
                }
            });
		});

		
		if ("Administrador".equals(rol)) {
			panelInferior.add(btnNuevo);
		}

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
			                try {
		                        temporadaSeleccionada.setEquipos(equipos);
		                        temporadaSeleccionada.guardarTemporada(temporadaSeleccionada);
							} catch (IOException e1) {
								System.out.println("ERROR. No se han encontrado los datos de la temporada.");
							}
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
	    listarTemporadas();
	    
	    if (SelectTemporadas.getItemCount() > 0) {
	        String temporadaActiva = (String) SelectTemporadas.getSelectedItem();
	        actualizarPanelEquipos(temporadaActiva.replace("Temporada ", ""));
	    }
	}
	
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

	    String temporadaActiva = null;
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

	    SelectTemporadas.removeAllItems();
	    for (String temporada : temporadas) {
	        SelectTemporadas.addItem("Temporada " + temporada);
	    }

	    if (temporadaActiva != null) {
	        SelectTemporadas.setSelectedItem("Temporada " + temporadaActiva);
	    }

	    return temporadas;
	}
	
	private void actualizarTitulo() {
        String title = "LPB Basketball - Equipos";
        if (datosModificados) {
            title = "*" + title;
        }
        setTitle(title);
	}

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

	        if ("Administrador".equals(rol)) {
	            btnEliminar = new BotonRedondeado("-", null);
	            btnEliminar.setFont(new Font("SansSerif", Font.PLAIN, 20));
	            btnEliminar.setBackground(new Color(0x545454));
	            btnEliminar.setForeground(Color.WHITE);
	            btnEliminar.setFocusPainted(false);
	            
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

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
	    if (datosModificados) {
	        int opcion = JOptionPane.showConfirmDialog(this, "Los datos han sido modificados. ¿Desea guardar antes de salir?", "Confirmar salida", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	        switch (opcion) {
	        case JOptionPane.YES_OPTION:
	            System.exit(0);
                try {
                	temporadaSeleccionada.setEquipos(equipos);
					temporadaSeleccionada.guardarTemporada(temporadaSeleccionada);
				} catch (IOException e1) {
					System.out.println("ERROR. No se han encontrado los datos de la temporada.");
				}
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


	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}
}
