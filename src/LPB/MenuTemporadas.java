
package LPB;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import LPBCLASES.BotonRedondeado;
import LPBCLASES.Temporada;
import LPBCLASES.logClase;
/**
 * Clase MenuTemporadas que representa el menú de selección de temporadas en la aplicación LPB Basketball.
 * Permite listar temporadas existentes, crear nuevas temporadas y eliminar temporadas según el rol del usuario.
 */
public class MenuTemporadas extends JFrame {
	private static final long serialVersionUID = -1200889095902166795L;
	private JPanel panelIzquierdo;
	private ImageIcon logo;
	private JLabel labelLogo;
	private JLabel labelUsuario;
	private JPanel panelDerecho;
    private JPanel panelContenido;
    private JScrollPane scrollPane;
	private JLabel titulo;
	private JLabel subtitulo;
	private JLabel leyendaFinalizada;
	private JLabel leyendaActiva;
	private JLabel leyendaEnProceso;
	private BotonRedondeado btnTemporada;
	private BotonRedondeado btnNuevaTemporada;
	private BotonRedondeado btnEliminar;
	private BotonRedondeado btnVolverMenu;
	private String rol;
	private String usuario;

  /**
   * Constructor de la clase MenuTemporadas.
   * 
   * @param rol     Rol del usuario que accede al menú (Administrador o Usuario).
   * @param usuario Nombre del usuario que ha iniciado sesión.
   */
	public MenuTemporadas(String rol, String usuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
		setTitle("LPB Basketball - Menú");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(850, 550);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
	    this.rol = rol;
	    this.usuario = usuario;
		
		panelIzquierdo = new JPanel();
		panelIzquierdo.setBackground(new Color(255, 243, 205));
		panelIzquierdo.setBounds(0, 0, 425, 513);
		panelIzquierdo.setLayout(null);

		logo = new ImageIcon(getClass().getResource("/imagenes/logo500.png"));
		labelLogo = new JLabel(logo);
		labelLogo.setBounds(10, 55, 400, 400);
		panelIzquierdo.add(labelLogo);
		getContentPane().add(panelIzquierdo);

		labelUsuario = new JLabel("Usuario: " + usuario);
		labelUsuario.setFont(new Font("SansSerif", Font.PLAIN, 16));
		labelUsuario.setForeground(new Color(0x545454));
		labelUsuario.setBounds(20, 470, 200, 20);
		panelIzquierdo.add(labelUsuario);

		panelDerecho = new JPanel();
		panelDerecho.setBackground(new Color(204, 153, 102));
		panelDerecho.setBounds(425, 0, 411, 513);
		panelDerecho.setLayout(null);

		titulo = new JLabel("Temporadas");
		titulo.setFont(new Font("SansSerif", Font.BOLD, 50));
		titulo.setForeground(new Color(255, 243, 205));
		titulo.setBounds(50, 40, 306, 47);
		panelDerecho.add(titulo);

		subtitulo = new JLabel("Seleccione una Temporada:");
		subtitulo.setForeground(Color.WHITE);
		subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 18));
		subtitulo.setBounds(50, 131, 234, 20);
		panelDerecho.add(subtitulo);
		
        panelContenido = new JPanel();
        panelContenido.setBorder(null);
        panelContenido.setLayout(null);
        panelContenido.setBackground(new Color(204, 153, 102));

        scrollPane = new JScrollPane(panelContenido);
        scrollPane.setBounds(50, 160, 319, 250);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panelDerecho.add(scrollPane);
        
        listarTemporadas();
        
        leyendaFinalizada = new JLabel("●");
        leyendaFinalizada.setFont(new Font("SansSerif", Font.PLAIN, 14));
        leyendaFinalizada.setForeground(new Color(255, 140, 0));
        leyendaFinalizada.setBounds(37, 430, 15, 20);
        panelDerecho.add(leyendaFinalizada);
        
        leyendaActiva = new JLabel("●");
        leyendaActiva.setFont(new Font("SansSerif", Font.PLAIN, 14));
        leyendaActiva.setForeground(new Color(0, 200, 0));
        leyendaActiva.setBounds(37, 450, 15, 20);
        panelDerecho.add(leyendaActiva);

        leyendaEnProceso = new JLabel("●");
        leyendaEnProceso.setFont(new Font("SansSerif", Font.PLAIN, 14));
        leyendaEnProceso.setForeground(new Color(128, 128, 128));
        leyendaEnProceso.setBounds(37, 470, 15, 20);
        panelDerecho.add(leyendaEnProceso);
		
		btnVolverMenu = new BotonRedondeado("Volver al Menú", null);
		btnVolverMenu.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnVolverMenu.setBackground(new Color(64, 64, 64));
		btnVolverMenu.setForeground(Color.WHITE);
		btnVolverMenu.setBounds(250, 461, 140, 30);
		btnVolverMenu.setFocusPainted(false);
		panelDerecho.add(btnVolverMenu);

		btnVolverMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Menu(rol, usuario).setVisible(true);
				dispose();
			}
		});

		getContentPane().add(panelDerecho);
		
		JLabel lblTemporadaFinalizada = new JLabel("Temporada Finalizada");
		lblTemporadaFinalizada.setForeground(new Color(31, 31, 31));
		lblTemporadaFinalizada.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblTemporadaFinalizada.setBounds(52, 430, 150, 20);
		panelDerecho.add(lblTemporadaFinalizada);
		
		JLabel lblTemporadaActiva = new JLabel("Temporada Activa");
		lblTemporadaActiva.setForeground(new Color(31, 31, 31));
		lblTemporadaActiva.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblTemporadaActiva.setBounds(52, 450, 125, 20);
		panelDerecho.add(lblTemporadaActiva);
		
		JLabel lblTemporadaEnProceso = new JLabel("Temporada En Creación");
		lblTemporadaEnProceso.setForeground(new Color(31, 31, 31));
		lblTemporadaEnProceso.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblTemporadaEnProceso.setBounds(52, 470, 160, 20);
		panelDerecho.add(lblTemporadaEnProceso);
	}
	/**
   * Lista las temporadas disponibles y las muestra en el panel de contenido.
   * Si el usuario es administrador, permite agregar y eliminar temporadas.
   */
    private void listarTemporadas() {
    	panelContenido.removeAll();
    	
    	int yPosition = 10;
        int buttonHeight = 40;
        int buttonSpacing = 20;

        btnNuevaTemporada = new BotonRedondeado("Nueva Temporada", null);
        btnNuevaTemporada.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnNuevaTemporada.setBackground(new Color(0x545454));
        btnNuevaTemporada.setForeground(Color.WHITE);
        btnNuevaTemporada.setBounds(0, yPosition, 220, 40);
		btnNuevaTemporada.setFocusPainted(false);
		
        btnNuevaTemporada.addActionListener(e -> {
            AgregarTemporada agregarTemporada = new AgregarTemporada();
            agregarTemporada.setVisible(true);
            
            agregarTemporada.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                	  // === INICIO: LOGGING PARA CREACIÓN DE TEMPORADAS ===
                	logClase.logAction("El usuario '" + usuario + "' ha creado una nueva temporada.");
                    // === FIN: LOGGING PARA CREACIÓN DE TEMPORADAS ===

                    listarTemporadas();
                }
            });
        });
		
        if ("Administrador".equals(rol)) {
			panelContenido.add(btnNuevaTemporada);
			yPosition += buttonHeight + buttonSpacing;
		}

        File folder = new File("data");
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".ser") && name.startsWith("temporada_"));

        if (files != null) {
            for (File file : files) {
                String nombreTemporada = file.getName().replace("temporada_", "").replace(".ser", "");
                btnTemporada = new BotonRedondeado("Temporada " + nombreTemporada, null);
                btnTemporada.setFont(new Font("SansSerif", Font.BOLD, 16));
                btnTemporada.setForeground(Color.WHITE);
                btnTemporada.setBounds(0, yPosition, 220, 40);
                btnTemporada.setFocusPainted(false);
                
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    Temporada temporada = (Temporada) ois.readObject();
                    if ("Activa".equals(temporada.getEstado())) {
                    	btnTemporada.setBackground(new Color(0, 200, 0));
                    } else if ("Finalizada".equals(temporada.getEstado())) {
                    	btnTemporada.setBackground(new Color(255, 140, 0));
                    } else {
                    	btnTemporada.setBackground(new Color(128, 128, 128));
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    btnTemporada.setBackground(new Color(128, 128, 128));
                }
                
                btnTemporada.addActionListener(e -> {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                        Temporada temporada = (Temporada) ois.readObject();
                        new MenuJornadas(rol, usuario, temporada).setVisible(true);
                        dispose();
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(this, "No se pudo cargar la temporada: " + nombreTemporada, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });
                
        		ImageIcon originalImageIcon = new ImageIcon(getClass().getResource("/imagenes/papelera.png"));
        		Image originalImage = originalImageIcon.getImage();
        		Image scaledImage = originalImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        		ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
                
                btnEliminar = new BotonRedondeado("", null);
                btnEliminar.setFont(new Font("SansSerif", Font.BOLD, 16));
                btnEliminar.setBackground(Color.RED);
                btnEliminar.setForeground(Color.WHITE);
                btnEliminar.setBounds(230, yPosition, 45, 40);
                btnEliminar.setFocusPainted(false);
	            btnEliminar.setIcon(scaledImageIcon);

                btnEliminar.addActionListener(e -> {
                    int confirm = JOptionPane.showConfirmDialog(this, 
                        "¿Estás seguro de que deseas eliminar la temporada " + nombreTemporada + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        if (file.delete()) {
                            JOptionPane.showMessageDialog(this, "Temporada eliminada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            listarTemporadas();
                        } else {
                            JOptionPane.showMessageDialog(this, "No se pudo eliminar la temporada.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                
                panelContenido.add(btnTemporada);
                
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    Temporada temporada = (Temporada) ois.readObject();
                    if ("En creación".equals(temporada.getEstado()) && "Administrador".equals(rol)) {
                    	panelContenido.add(btnEliminar);
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                
                yPosition += buttonHeight + buttonSpacing;
            }
        }

        panelContenido.setPreferredSize(new Dimension(300, yPosition + buttonHeight + buttonSpacing));
        panelContenido.revalidate();
    }	
}
