package LPB;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import LPBCLASES.BackgroundFader;
import LPBCLASES.BotonRedondeado;
import LPBCLASES.Equipo;
import LPBCLASES.Jornada;
import LPBCLASES.LineaPanel;
import LPBCLASES.Partido;
import LPBCLASES.Temporada;
import LPBCLASES.TextoRedondeado;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuJornadas extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;

	// Componentes (variables) de la interfaz
	private JPanel panelIzquierdo;
	private JPanel panelPartido;
	private JPanel panelDerecho;
	private JTextField textPtsLocal;
	private JTextField textPtsVisitante;
	private BotonRedondeado btnAtras;
	private BotonRedondeado btnDelante;
	private JComboBox<String> comboBoxJornadas;
	private BotonRedondeado btnGuardar;
	private BotonRedondeado btnRestablecer;
	private BotonRedondeado btnVolverMenu;
	
	private JLabel lblTemporada;
	private JLabel lblAniosTemporada;
	private JLabel lblEstado;
	private JLabel lblEstado2;
	private JLabel lblNoPartidos;
	private BotonRedondeado btnActivarTemporada;
	private BotonRedondeado btnFinalizarTemporada;
	private ImageIcon logoLocal;
	private JLabel lblEquipoLocal;
	private LineaPanel linea;
	private JLabel lblVS;
	private LineaPanel linea_2;
	private JLabel lblEquipoVisitante;
	private ImageIcon logoVisitante;
	private JLabel lblClasificacion;
	
	private BackgroundFader fader;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuJornadas frame = new MenuJornadas("Administrador", "Administrador", null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param temporada 
	 */
	public MenuJornadas(String rol, String usuario, Temporada temporada) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
		setTitle("LPB Basketball - Menú Jornadas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(990, 600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		fader = new BackgroundFader();
		
		// Panel principal para los elementos superiores
		panelIzquierdo = new JPanel();
		panelIzquierdo.setBounds(0, 0, 488, 563);
		panelIzquierdo.setBackground(new Color(255, 243, 205));
		panelIzquierdo.setLayout(null);
		getContentPane().add(panelIzquierdo);
		
		lblTemporada = new JLabel("Temporada");
		lblTemporada.setForeground(new Color(218, 168, 129));
		lblTemporada.setLocation(60, 30);
		lblTemporada.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTemporada.setSize(170, 40);
		lblTemporada.setFont(new Font("SansSerif", Font.BOLD, 30));
		panelIzquierdo.add(lblTemporada);
		
		lblAniosTemporada = new JLabel(temporada.getPeriodo());
		lblAniosTemporada.setForeground(new Color(218, 168, 129));
		lblAniosTemporada.setLocation(240, 30);
		lblAniosTemporada.setSize(160, 40);
		lblAniosTemporada.setFont(new Font("SansSerif", Font.BOLD, 30));
		panelIzquierdo.add(lblAniosTemporada);
		
		lblEstado = new JLabel("Estado: ");
		lblEstado.setForeground(new Color(84, 84, 84));
		lblEstado.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblEstado.setBounds(15, 95, 62, 19);
		panelIzquierdo.add(lblEstado);
		
		lblEstado2 = new JLabel(temporada.getEstado());
		lblEstado2.setForeground(new Color(84, 84, 84));
		lblEstado2.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblEstado2.setBounds(70, 95, 80, 19);
		panelIzquierdo.add(lblEstado2);
		
		btnActivarTemporada = new BotonRedondeado("Iniciar temporada", (ImageIcon) null);
		btnActivarTemporada.setForeground(Color.WHITE);
		btnActivarTemporada.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnActivarTemporada.setFocusPainted(false);
		btnActivarTemporada.setBackground(new Color(244, 107, 32));
		btnActivarTemporada.setBounds(285, 90, 165, 30);
		btnActivarTemporada.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        generarJornadas(temporada);
		        comboBoxJornadas.removeAllItems();
		        
		        for (Jornada jornada : temporada.getJornadas()) {
		            comboBoxJornadas.addItem("Jornada " + jornada.getNumero());
		        }
		        
		        lblEstado2.setText("Activa");
		        temporada.setEstado("Activa");
		        cargarPartidos(temporada);
		        panelIzquierdo.remove(btnActivarTemporada);
		        panelIzquierdo.add(btnFinalizarTemporada);
		        guardarTemporada(temporada);
		    }
		});
		btnActivarTemporada.addMouseListener(this);
		
		if (rol.equals("Administrador") && temporada.getEstado().equals("En proceso")) {
			panelIzquierdo.add(btnActivarTemporada);
		}
		
		btnFinalizarTemporada = new BotonRedondeado("Finalizar temporada", (ImageIcon) null);
		btnFinalizarTemporada.setForeground(Color.WHITE);
		btnFinalizarTemporada.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnFinalizarTemporada.setFocusPainted(false);
		btnFinalizarTemporada.setBackground(new Color(244, 107, 32));
		btnFinalizarTemporada.setBounds(285, 90, 180, 30);
		btnFinalizarTemporada.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        lblEstado2.setText("Finalizada");
		        temporada.setEstado("Finalizada");
		        guardarTemporada(temporada);
		    }
		});
		btnFinalizarTemporada.addMouseListener(this);
		
		if (rol.equals("Administrador") && temporada.getEstado().equals("Activa")) {
			panelIzquierdo.add(btnFinalizarTemporada);
		}
		
		btnAtras = new BotonRedondeado("<", null);
		btnAtras.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnAtras.setBounds(60, 150, 50, 30);
		btnAtras.setBackground(new Color(0xf46b20));
		btnAtras.setForeground(Color.WHITE);
        btnAtras.setFocusPainted(false);
        btnAtras.addMouseListener(this);
        panelIzquierdo.add(btnAtras);
		
        comboBoxJornadas = new JComboBox<String>();
        comboBoxJornadas.setBounds(120, 150, 220, 30);

        if (temporada.getJornadas().isEmpty()) {
            comboBoxJornadas.addItem("No hay jornadas");
        } else {
            // Añado las jornadas al ComboBox
            for (Jornada jornada : temporada.getJornadas()) {
                comboBoxJornadas.addItem("Jornada " + jornada.getNumero());
            }
        }

        comboBoxJornadas.setFont(new Font("SansSerif", Font.PLAIN, 18));
				
		panelIzquierdo.add(comboBoxJornadas);
		
		btnDelante = new BotonRedondeado(">", null);
		btnDelante.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnDelante.setBounds(350, 150, 50, 30);
		btnDelante.setBackground(new Color(0xf46b20));
		btnDelante.setForeground(Color.WHITE);
		btnDelante.setFocusPainted(false);
		btnDelante.addMouseListener(this);
		panelIzquierdo.add(btnDelante);
		
	    comboBoxJornadas.addActionListener(e -> cargarPartidos(temporada));

	    if (temporada.getJornadas().size() > 0) {
	        cargarPartidos(temporada);
	    }
	    
		btnGuardar = new BotonRedondeado("Guardar", null);
		btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnGuardar.setBounds(100, 500, 120, 40);
		btnGuardar.setBackground(new Color(84, 84, 84));
		btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFocusPainted(false);
        btnGuardar.addMouseListener(this);
		panelIzquierdo.add(btnGuardar);
		
		btnRestablecer = new BotonRedondeado("Restablecer", null);
		btnRestablecer.setForeground(Color.WHITE);
		btnRestablecer.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnRestablecer.setFocusPainted(false);
		btnRestablecer.setBackground(new Color(84, 84, 84));
		btnRestablecer.setBounds(235, 500, 150, 40);
		btnRestablecer.addMouseListener(this);
		panelIzquierdo.add(btnRestablecer);
		
		// Panel para la clasificación
		panelDerecho = new JPanel();
		panelDerecho.setBounds(488, 0, 488, 563);
		panelDerecho.setBackground(new Color(218, 168, 129));
		panelDerecho.setLayout(null);
		getContentPane().add(panelDerecho);
		
		lblClasificacion = new JLabel("Clasificación");
		lblClasificacion.setLocation(30, 30);
		lblClasificacion.setSize(200, 40);
		lblClasificacion.setForeground(new Color(254, 244, 198));
		lblClasificacion.setFont(new Font("SansSerif", Font.BOLD, 30));
		panelDerecho.add(lblClasificacion);
		
		btnVolverMenu = new BotonRedondeado("Volver al Menú", (ImageIcon) null);
		btnVolverMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MenuTemporadas(rol, usuario).setVisible(true);
				dispose();
			}
		});
		btnVolverMenu.setForeground(Color.WHITE);
		btnVolverMenu.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnVolverMenu.setFocusPainted(false);
		btnVolverMenu.setBackground(new Color(84, 84, 84));
		btnVolverMenu.setBounds(300, 485, 150, 40);
		btnVolverMenu.addMouseListener(this);
		panelDerecho.add(btnVolverMenu);
	}
	
	// Método cargarPartidos()
	private void cargarPartidos(Temporada temporada) {
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setBounds(50, 200, 400, 300);
	    scrollPane.getVerticalScrollBar().setUnitIncrement(16);
	    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    panelIzquierdo.add(scrollPane);

	    JPanel panelPartidos = new JPanel();
	    panelPartidos.setLayout(new BoxLayout(panelPartidos, BoxLayout.Y_AXIS));
	    panelPartidos.setBackground(panelIzquierdo.getBackground());

	    int indice = comboBoxJornadas.getSelectedIndex();
	    if (indice < 0) return;

	    Jornada jornada = temporada.getJornadas().get(indice);
	    List<Partido> partidos = jornada.getPartidos();

	    if (partidos.isEmpty()) {
	        lblNoPartidos = new JLabel("No se han encontrado partidos para la jornada seleccionada");
	        lblNoPartidos.setFont(new Font("SansSerif", Font.PLAIN, 16));
	        lblNoPartidos.setForeground(new Color(84, 84, 84));
	        lblNoPartidos.setHorizontalAlignment(SwingConstants.CENTER);
	        panelPartidos.add(lblNoPartidos);
	    } else {
	        for (Partido partido : partidos) {
	            panelPartido = crearPanelPartido(partido, temporada);
	            panelPartidos.add(panelPartido);
	            panelPartidos.add(Box.createRigidArea(new Dimension(0, 20)));
	        }
	    }
	    scrollPane.setViewportView(panelPartidos);
	}
	
	private JPanel crearPanelPartido(Partido partido, Temporada temporada) {
	    panelPartido = new JPanel();
	    panelPartido.setLayout(null);
	    panelPartido.setPreferredSize(new Dimension(400, 100));
	    panelPartido.setBackground(new Color(255, 243, 205));

	    // Equipo local
	    lblEquipoLocal = new JLabel(partido.getEquipoLocal().getNombre());
	    lblEquipoLocal.setBounds(20, 20, 150, 30);
	    lblEquipoLocal.setFont(new Font("SansSerif", Font.PLAIN, 16));
	    lblEquipoLocal.setForeground(new Color(84, 84, 84));
	    panelPartido.add(lblEquipoLocal);

	    logoLocal = new ImageIcon(getClass().getResource("/imagenes/temporadas" + temporada.getPeriodo() + "/" + partido.getEquipoLocal().getNombre() + ".png"));
	    lblEquipoLocal.setIcon(logoLocal);

	    textPtsLocal = new TextoRedondeado(20);
	    textPtsLocal.setBounds(180, 20, 50, 30);
	    textPtsLocal.setText(String.valueOf(partido.getPuntosLocal()));
	    panelPartido.add(textPtsLocal);
	    
		linea = new LineaPanel();
		linea.setForeground(new Color(166, 166, 166));
		linea.setBounds(90, 262, 140, 4);
		linea.setBackground(new Color(255, 243, 205));
		panelIzquierdo.add(linea);

	    lblVS = new JLabel("vs");
	    lblVS.setBounds(240, 20, 30, 30);
	    lblVS.setFont(new Font("SansSerif", Font.BOLD, 16));
	    lblVS.setForeground(new Color(166, 166, 166));
	    panelPartido.add(lblVS);
	    
		linea_2 = new LineaPanel();
		linea_2.setForeground(new Color(166, 166, 166));
		linea_2.setBackground(new Color(255, 243, 205));
		linea_2.setBounds(269, 262, 100, 4);
		panelIzquierdo.add(linea_2);

	    lblEquipoVisitante = new JLabel(partido.getEquipoVisitante().getNombre());
	    lblEquipoVisitante.setBounds(280, 20, 150, 30);
	    lblEquipoVisitante.setFont(new Font("SansSerif", Font.PLAIN, 16));
	    lblEquipoVisitante.setForeground(new Color(84, 84, 84));
	    panelPartido.add(lblEquipoVisitante);

	    logoVisitante = new ImageIcon(getClass().getResource("/imagenes/temporadas" + temporada.getPeriodo() + "/" + partido.getEquipoVisitante().getNombre() + ".png"));
	    lblEquipoVisitante.setIcon(logoVisitante);

	    textPtsVisitante = new TextoRedondeado(20);
	    textPtsVisitante.setBounds(440, 20, 50, 30);
	    textPtsVisitante.setText(String.valueOf(partido.getPuntosVisitante()));
	    panelPartido.add(textPtsVisitante);

	    return panelPartido;
	}
	
	// Método para generar las jornadas a través del método RoundRobin
	private void generarJornadas(Temporada temporada) {
	    if (!temporada.getJornadas().isEmpty()) {
	        return;
	    }

	    // Generar las jornadas solo si no existen
	    List<Equipo> equipos = new ArrayList<>(temporada.getEquipos());
	    int numEquipos = equipos.size();
	    int numJornadas = numEquipos - 1;
	    int partidosPorJornada = numEquipos / 2;

	    for (int i = 0; i < numJornadas; i++) {
	        Jornada jornada = new Jornada(i + 1);

	        for (int j = 0; j < partidosPorJornada; j++) {
	            Equipo local = equipos.get(j);
	            Equipo visitante = equipos.get(equipos.size() - 1 - j);

	            if (local != null && visitante != null) {
	                Partido partido = new Partido(local, visitante);
	                jornada.getPartidos().add(partido);
	            }
	        }

	        Equipo ultimo = equipos.remove(equipos.size() - 1);
	        equipos.add(1, ultimo);

	        temporada.getJornadas().add(jornada);
	    }
	}

	
	public void guardarTemporada(Temporada temporada) {
	    List<Temporada> temporadas = cargarTemporadas();
	    
	    for (int i = 0; i < temporadas.size(); i++) {
	        if (temporadas.get(i).getPeriodo().equals(temporada.getPeriodo())) {
	            temporadas.set(i, temporada);
	            break;
	        }
	    }
	    
	    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("temporadas.ser"))) {
	        oos.writeObject(temporadas);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	
	@SuppressWarnings("unchecked")
	private List<Temporada> cargarTemporadas() {
	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("temporadas.ser"))) {
	        return (List<Temporada>) ois.readObject();
	    } catch (IOException | ClassNotFoundException e) {
	        return null;
	    }
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object o = e.getSource();
		
		if (o == btnGuardar) {
			fader.fadeBackground(btnGuardar, btnGuardar.getBackground(), new Color(0xa0a0a0));
		} else if (o == btnRestablecer) {
			fader.fadeBackground(btnRestablecer, btnRestablecer.getBackground(), new Color(0xa0a0a0));
		} else if (o == btnActivarTemporada) {
			fader.fadeBackground(btnActivarTemporada, btnActivarTemporada.getBackground(), new Color(0xff7f50));
		} else if (o == btnFinalizarTemporada) {
			fader.fadeBackground(btnFinalizarTemporada, btnFinalizarTemporada.getBackground(), new Color(0xff7f50));
		} else if (o == btnDelante) {
			fader.fadeBackground(btnDelante, btnDelante.getBackground(), new Color(0xff7f50));
		} else if (o == btnAtras) {
			fader.fadeBackground(btnAtras, btnAtras.getBackground(), new Color(0xff7f50));
		} else if (o == btnVolverMenu) {
			fader.fadeBackground(btnVolverMenu, btnVolverMenu.getBackground(), new Color(0xa0a0a0));
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object o = e.getSource();

		if (o == btnGuardar) {
			fader.fadeBackground(btnGuardar, btnGuardar.getBackground(), new Color(0x545454));
		} else if (o == btnRestablecer) {
			fader.fadeBackground(btnRestablecer, btnRestablecer.getBackground(), new Color(0x545454));
		} else if (o == btnActivarTemporada) {
			fader.fadeBackground(btnActivarTemporada, btnActivarTemporada.getBackground(), new Color(0xf46b20));
		} else if (o == btnFinalizarTemporada) {
			fader.fadeBackground(btnFinalizarTemporada, btnFinalizarTemporada.getBackground(), new Color(0xf46b20));
		} else if (o == btnDelante) {
			fader.fadeBackground(btnDelante, btnDelante.getBackground(), new Color(0xf46b20));
		} else if (o == btnAtras) {
			fader.fadeBackground(btnAtras, btnAtras.getBackground(), new Color(0xf46b20));
		} else if (o == btnVolverMenu) {
			fader.fadeBackground(btnVolverMenu, btnVolverMenu.getBackground(), new Color(0x545454));
		} 
	}
}
