
package LPB;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import org.imgscalr.Scalr;

import LPBCLASES.BotonRedondeado;
import LPBCLASES.Equipo;

public class EquiposTemporada extends JFrame implements WindowListener {

	private static final long serialVersionUID = -2296678961838970996L;
	private JPanel panelSuperior;
	private ImageIcon logo;
	private JLabel labelLogo;
	private JLabel labelUsuario;
	private JPanel panelInferior;
	private JLabel titulo;
	private JButton btnNuevo;
	private AgregarEquipo agregarEquipoFrame;
	private JScrollPane scrollPane;
	private JButton btnVolverMenu;
	private GridBagConstraints gbc;
	private JButton btnEquipo;
	private BufferedImage originalImage;
	private JPanel equipoPanel;
	private File logoFile;
	private GridBagConstraints gbcBtnEliminar;
	private JButton btnEliminar;
	private String temporadaSeleccionada;
	private EditarEquipo editarEquipoFrame;
	private GridBagConstraints gbcBtnEquipo;
	private BufferedImage scaledImage;
	private JComboBox<String> SelectTemporadas;
	private JPanel panelEquipos;
	private Boolean datosModificados;
	private Map<String, List<Equipo>> equiposPorTemporada;

	public EquiposTemporada(String rol, String usuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
		setTitle("LPB Basketball - Equipos");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		setSize(850, 550);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

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

		String[] temporadas = { "Temporada 2023-24", "Temporada 2024-25", "Temporada 2025-26" };

		SelectTemporadas = new JComboBox<>(temporadas);
		SelectTemporadas.setBackground(new Color(0x13427e));
		SelectTemporadas.setForeground(Color.WHITE);
		SelectTemporadas.setFont(new Font("SansSerif", Font.PLAIN, 16));
		SelectTemporadas.setBounds(545, 27, 200, 40);

		SelectTemporadas.setSelectedItem("Temporada 2024-25");

		panelInferior.add(SelectTemporadas);

		btnNuevo = new BotonRedondeado("+", null);
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnNuevo.setBackground(new Color(0x545454));
		btnNuevo.setBounds(755, 22, 50, 50);
		btnNuevo.setFocusPainted(false);
		if ("Administrador".equals(rol)) {
			panelInferior.add(btnNuevo);
		}

		btnVolverMenu = new BotonRedondeado("Volver al Menú", null);
		btnVolverMenu.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnVolverMenu.setBackground(new Color(64, 64, 64));
		btnVolverMenu.setForeground(Color.WHITE);
		btnVolverMenu.setBounds(673, 351, 140, 30);
		btnVolverMenu.setFocusPainted(false);
		panelInferior.add(btnVolverMenu);

		btnVolverMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Menu(rol, usuario).setVisible(true);
				dispose();
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

		SelectTemporadas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedTemporada = (String) SelectTemporadas.getSelectedItem();
				actualizarPanelEquipos(selectedTemporada, rol);
			}
		});

		btnNuevo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				agregarEquipoFrame = new AgregarEquipo(EquiposTemporada.this);
				agregarEquipoFrame.setVisible(true);
			}
		});

		datosModificados = false;
		equiposPorTemporada = new HashMap<>();
		for (String temporada : temporadas) {
			equiposPorTemporada.put(temporada, new ArrayList<>());
		}
		cargarDatos();
		datosModificados = false;
		actualizarPanelEquipos((String) SelectTemporadas.getSelectedItem(), rol);
	}

	private void actualizarPanelEquipos(String temporada, String rol) {
	    panelEquipos.removeAll();
	    List<Equipo> equipos = equiposPorTemporada.get(temporada);

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
	            logoFile = new File(equipo.getEquipoPath());
	            if (logoFile.exists() && !logoFile.isDirectory()) {
	                originalImage = ImageIO.read(logoFile);
	                if (originalImage != null) {
	                    scaledImage = Scalr.resize(originalImage, Scalr.Method.QUALITY, 50, 50);
	                    btnEquipo.setIcon(new ImageIcon(scaledImage));
	                } else {
	                    JOptionPane.showMessageDialog(null, "Error: No se pudo procesar la imagen del equipo: " + equipo.getNombre(), "Error de Imagen", JOptionPane.ERROR_MESSAGE);
	                    btnEquipo.setIcon(new ImageIcon(getClass().getResource("/imagenes/imagen_por_defecto.png")));
	                }
	            } else {
	                JOptionPane.showMessageDialog(null, "Advertencia: Archivo de imagen no encontrado para el equipo: " + equipo.getNombre(), "Archivo No Encontrado", JOptionPane.WARNING_MESSAGE);
	                btnEquipo.setIcon(new ImageIcon(getClass().getResource("/imagenes/imagen_por_defecto.png")));
	            }
	        } catch (IOException e) {
	            JOptionPane.showMessageDialog(null, "Error al cargar la imagen del equipo: " + equipo.getNombre() + " en la ruta: " + equipo.getEquipoPath(), "Error de Lectura", JOptionPane.ERROR_MESSAGE);
	            e.printStackTrace();
	            btnEquipo.setIcon(new ImageIcon(getClass().getResource("/imagenes/imagen_por_defecto.png")));
	        } catch (NullPointerException e) {
	            JOptionPane.showMessageDialog(null, "Error: Ruta de imagen nula para el equipo: " + equipo.getNombre(), "Error de Ruta", JOptionPane.ERROR_MESSAGE);
	            e.printStackTrace();
	            btnEquipo.setIcon(new ImageIcon(getClass().getResource("/imagenes/imagen_por_defecto.png")));
	        }
	    
	        btnEquipo.setFont(new Font("SansSerif", Font.PLAIN, 20));
	        btnEquipo.setBackground(new Color(0xf46b20));
	        btnEquipo.setHorizontalAlignment(SwingConstants.LEFT);
	        btnEquipo.setForeground(Color.WHITE);
	        gbcBtnEquipo = new GridBagConstraints();
	        gbcBtnEquipo.gridx = 0;
	        gbcBtnEquipo.gridy = 0;
	        gbcBtnEquipo.insets = new Insets(5, 5, 5, 5);
	        btnEquipo.setPreferredSize(new Dimension(290, 60));
	        equipoPanel.add(btnEquipo, gbcBtnEquipo);

	        btnEquipo.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                editarEquipoFrame = new EditarEquipo(equipo);
	                editarEquipoFrame.setVisible(true);
	            }
	        });

	        if ("Administrador".equals(rol)) {
	            btnEliminar = new BotonRedondeado("-", null);
	            btnEliminar.setFont(new Font("SansSerif", Font.PLAIN, 20));
	            btnEliminar.setBackground(new Color(0x545454));
	            btnEliminar.setForeground(Color.WHITE);
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
	                        actualizarPanelEquipos(temporada, rol);
	                        datosModificados = true;
	                    } else {
	                        JOptionPane.showMessageDialog(null, "No se pudo eliminar el equipo. " + equipo.getNombre(), "Error" , JOptionPane.ERROR_MESSAGE);
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

	public void agregarNuevoEquipoDesdeFormulario(Equipo nuevoEquipo) {
	    temporadaSeleccionada = (String) SelectTemporadas.getSelectedItem();
	    List<Equipo> equipos = equiposPorTemporada.get(temporadaSeleccionada);
	    if (equipos != null) {
	        equipos.add(nuevoEquipo);
	        actualizarPanelEquipos(temporadaSeleccionada, "Administrador");
	        datosModificados = true;
	    } else {
	        JOptionPane.showMessageDialog(null, "Error: No se encontraron equipos para la temporada seleccionada (" + temporadaSeleccionada + ").", "Error", JOptionPane.ERROR_MESSAGE
	        );
	    }
	}

	private void guardarDatos() {
		try (FileOutputStream fos = new FileOutputStream("equipos.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(equiposPorTemporada);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, "Error: fichero no encontrado", "Error", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error de entrada/salida", "Error", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@SuppressWarnings("unchecked")
	private void cargarDatos() {
	    try (FileInputStream fis = new FileInputStream("equipos.ser");
	         ObjectInputStream ois = new ObjectInputStream(fis)) {
	        Object obj = ois.readObject();
	        if (obj instanceof Map) {
	            equiposPorTemporada = (Map<String, List<Equipo>>) obj;
	        } else {
	            throw new ClassCastException("El archivo de datos tiene un formato incorrecto.");
	        }
	    } catch (FileNotFoundException e) {
	        JOptionPane.showMessageDialog(null, "No se encontró un archivo de datos existente. Se generará un nuevo archivo automáticamente cuando se guarden los datos.", "Información", JOptionPane.INFORMATION_MESSAGE);
	    } catch (IOException e) {
	        JOptionPane.showMessageDialog(null, "Hubo un problema al intentar cargar los datos. Por favor, asegúrate de que el archivo no esté dañado.", "Error al cargar datos", JOptionPane.ERROR_MESSAGE);
	    } catch (ClassNotFoundException e) {
	        JOptionPane.showMessageDialog(null, "Hubo un problema al cargar los datos porque faltan componentes necesarios en el sistema.", "Error al cargar datos", JOptionPane.ERROR_MESSAGE);
	    } catch (ClassCastException e) {
	        JOptionPane.showMessageDialog(null, "El archivo de datos tiene un formato incorrecto y no puede ser cargado.",  "Error al cargar datos", JOptionPane.ERROR_MESSAGE);
	    }
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

	public static void main(String[] args) {
		new EquiposTemporada("Administrador", "Administrador").setVisible(true);
	}
}
