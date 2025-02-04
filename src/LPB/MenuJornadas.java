package LPB;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import LPBCLASES.BotonRedondeado;
import LPBCLASES.Equipo;
import LPBCLASES.ExportarPDF;
import LPBCLASES.Jornada;
import LPBCLASES.LineaPanel;
import LPBCLASES.Partido;
import LPBCLASES.Temporada;
import LPBCLASES.TextoRedondeado;
import LPBCLASES.logClase;
import jnafilechooser.api.JnaFileChooser;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;



public class MenuJornadas extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;

	// Componentes (variables) de la interfaz
	private JPanel panelIzquierdo;
	private JPanel panelPartido;
	private JPanel panelDerecho;
	private JScrollPane scrollPane;
	private JTextField textPtsLocal;
	private JTextField textPtsVisitante;
	private BotonRedondeado btnAtras;
	private BotonRedondeado btnAdelante;
	private JComboBox<String> comboBoxJornadas;
	private BotonRedondeado btnGuardar;
	private BotonRedondeado btnRestablecer;
	private BotonRedondeado btnVolverMenu;
	private BotonRedondeado btnExportarPDF;
	
	private JLabel lblTemporada;
	private JLabel lblAniosTemporada;
	private JLabel lblEstado;
	private JLabel lblEstado2;
	private JLabel lblNoPartidos;
	private BotonRedondeado btnActivarTemporada;
	private BotonRedondeado btnFinalizarTemporada;
	private ImageIcon logoLocal;
	private JLabel lblEquipoLocal;
	private JLabel lblLogoLocal;
	private Image imgLocal;
	private LineaPanel linea;
	private JLabel lblVS;
	private LineaPanel linea_2;
	private JLabel lblEquipoVisitante;
	private Image imgVisitante;
	private JLabel lblLogoVisitante;
	private ImageIcon logoVisitante;
	private JLabel lblFecha;
	private JLabel lblHora;
	private JLabel lblClasificacion;
	private Jornada jornadaSeleccionada;
	private JTable tablaClasificacion;
	private JScrollPane scrollPaneClasificacion;
	private JTableHeader header;
	private JPanel panelPartidos;
	private List<Partido> partidos;
	private DefaultTableModel dtmClasificacion;
	private Temporada temporada;
	private String rol;
	private boolean datosModificados = false;

	/**
	 * Create the frame.
	 * @param temporada 
	 */
	public MenuJornadas(String rol, String usuario, Temporada temporada) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
			    if (datosModificados) {
			        int opcion = JOptionPane.showConfirmDialog(getContentPane(), "Los datos han sido modificados. ¿Desea guardar antes de salir?", "Confirmar salida", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

			        if (opcion == JOptionPane.YES_OPTION) {
			        	guardarResultados();
			        } else if (opcion == JOptionPane.CANCEL_OPTION) {
			        	return;
			        }
			        
			        System.exit(0);
			    } else {
			        System.exit(0);
			    }
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
		setTitle("LPB Basketball - Menú Jornadas");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(990, 600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		this.temporada = temporada;
		this.rol = rol;
		
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
		lblEstado.setBounds(15, 85, 55, 19);
		panelIzquierdo.add(lblEstado);
		
		lblEstado2 = new JLabel(temporada.getEstado());
		lblEstado2.setForeground(new Color(84, 84, 84));
		lblEstado2.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblEstado2.setBounds(70, 85, 80, 19);
		panelIzquierdo.add(lblEstado2);
		
		btnActivarTemporada = new BotonRedondeado("Iniciar temporada", null);
		btnActivarTemporada.setForeground(Color.WHITE);
		btnActivarTemporada.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnActivarTemporada.setFocusPainted(false);
		btnActivarTemporada.setBackground(new Color(244, 107, 32));
		btnActivarTemporada.setBounds(285, 85, 165, 30);
		btnActivarTemporada.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	 // === INICIO: LOGGING PARA ACTIVAR TEMPORADA ===
		        logClase.logAction("Intentando activar la temporada: " + temporada.getPeriodo());
		        // === FIN: LOGGING PARA ACTIVAR TEMPORADA ===
		        
		        int contador = 0;
		        for (int i = 0; i < temporada.getEquipos().size(); i++) {
		        	contador++;
		        }
		        
		        if (contador < 6) {
		            logClase.logError("No se puede activar la temporada: La cantidad de equipos es insuficiente.", null);
		            JOptionPane.showMessageDialog(getContentPane(), "No se puede iniciar la temporada porque debe haber mínimo 6 equipos.", "Error", JOptionPane.ERROR_MESSAGE);
		            return; // Detener la ejecución
		        }

		        int generacionJornadas = temporada.generarJornadas();
		        
		        if (generacionJornadas == 0) {
		        	// === INICIO: LOGGING PARA ERROR AL ACTIVAR TEMPORADA ===
		        	logClase.logError("No se puede activar la temporada: No hay equipos registrados.", null);
		            // === FIN: LOGGING PARA ERROR AL ACTIVAR TEMPORADA ===
		        	JOptionPane.showMessageDialog(getContentPane(), "No se puede iniciar la temporada porque no hay equipos registrados.", "Error", JOptionPane.ERROR_MESSAGE);
		        } else {
			        comboBoxJornadas.removeAllItems();
			        
			        for (Jornada jornada : temporada.getJornadas()) {
			            comboBoxJornadas.addItem("Jornada " + jornada.getNumero());
			        }
		
			        lblEstado2.setText("Activa");
			        temporada.setEstado("Activa");
			        try {
						temporada.guardarTemporada(temporada);
						 // === INICIO: LOGGING PARA TEMPORADA ACTIVADA ===
		                logClase.logAction("Temporada activada correctamente: " + temporada.getPeriodo() + ". Jornadas generadas: " + temporada.getJornadas().size());
		                // === FIN: LOGGING PARA TEMPORADA ACTIVADA ===
					} catch (IOException e1) {
						 // === INICIO: LOGGING PARA ERROR AL GUARDAR TEMPORADA ===
		                logClase.logError("Error al guardar la temporada después de activarla.", e1);
		                // === FIN: LOGGING PARA ERROR AL GUARDAR TEMPORADA ===
						System.out.println("ERROR. No se han encontrado datos de la temporada.");
					}
			        cargarPartidos(temporada, jornadaSeleccionada);
			        panelIzquierdo.remove(btnActivarTemporada);
			        panelIzquierdo.add(btnFinalizarTemporada);
	        	}
		    }
		});
		
		if (rol.equals("Administrador") && temporada.getEstado().equals("En proceso")) {
			panelIzquierdo.add(btnActivarTemporada);
		}
		
		btnFinalizarTemporada = new BotonRedondeado("Finalizar temporada", null);
		btnFinalizarTemporada.setForeground(Color.WHITE);
		btnFinalizarTemporada.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnFinalizarTemporada.setFocusPainted(false);
		btnFinalizarTemporada.setBackground(new Color(244, 107, 32));
		btnFinalizarTemporada.setBounds(285, 85, 180, 30);
		btnFinalizarTemporada.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	int dialogResult = JOptionPane.showConfirmDialog(null, "Vas a finalizar la temporada. No podrás modificar los resultados.", "Aviso", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
		    	if (dialogResult == JOptionPane.YES_OPTION) {
			        lblEstado2.setText("Finalizada");
			        temporada.setEstado("Finalizada");
			        btnFinalizarTemporada.setVisible(false);
			        btnGuardar.setVisible(false);
			        btnRestablecer.setVisible(false);
			        try {
						temporada.guardarTemporada(temporada);

		                // === INICIO: LOGGING PARA TEMPORADA FINALIZADA ===
		                logClase.logAction("La temporada ha sido finalizada: " + temporada.getPeriodo());
		                // === FIN: LOGGING PARA TEMPORADA FINALIZADA ===
					} catch (IOException e1) {
						 // === INICIO: LOGGING PARA ERROR AL FINALIZAR TEMPORADA ===
		                logClase.logError("Error al guardar la temporada al finalizarla.", e1);
		                // === FIN: LOGGING PARA ERROR AL FINALIZAR TEMPORADA ===
						System.out.println("ERROR. No se han encontrado datos de la temporada.");
					}
			        cargarPartidos(temporada, jornadaSeleccionada);
		    	}
		    }
		});
		btnFinalizarTemporada.setVisible(false);
		
		if (rol.equals("Administrador") && temporada.getEstado().equals("Activa")) {
			panelIzquierdo.add(btnFinalizarTemporada);
		}
		
		btnAtras = new BotonRedondeado("<", null);
		btnAtras.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnAtras.setBounds(60, 125, 50, 30);
		btnAtras.setBackground(new Color(0xf46b20));
		btnAtras.setForeground(Color.WHITE);
        btnAtras.setFocusPainted(false);
        btnAtras.addMouseListener(this);
        panelIzquierdo.add(btnAtras);
		
        comboBoxJornadas = new JComboBox<String>();
        comboBoxJornadas.setBounds(120, 125, 220, 30);

        if (temporada.getJornadas().isEmpty()) {
            comboBoxJornadas.addItem("No hay jornadas");
        } else {
            // Añado las jornadas al ComboBox
            for (Jornada jornada : temporada.getJornadas()) {
                comboBoxJornadas.addItem("Jornada " + jornada.getNumero());
            }
            jornadaSeleccionada = temporada.getJornadas().get(0);
        }

        comboBoxJornadas.setFont(new Font("SansSerif", Font.PLAIN, 18));
				
		panelIzquierdo.add(comboBoxJornadas);
		
		btnAdelante = new BotonRedondeado(">", null);
		btnAdelante.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnAdelante.setBounds(350, 125, 50, 30);
		btnAdelante.setBackground(new Color(0xf46b20));
		btnAdelante.setForeground(Color.WHITE);
		btnAdelante.setFocusPainted(false);
		btnAdelante.addMouseListener(this);
		panelIzquierdo.add(btnAdelante);
		
		comboBoxJornadas.addActionListener(e -> {
		    int indiceSeleccionado = comboBoxJornadas.getSelectedIndex();
		    if (indiceSeleccionado >= 0) {
		        jornadaSeleccionada = temporada.getJornadas().get(indiceSeleccionado);
		        cargarPartidos(temporada, jornadaSeleccionada);
		    }
		});

	    if (temporada.getJornadas().size() > 0) {
	        cargarPartidos(temporada, jornadaSeleccionada);
	    }
	    
		btnGuardar = new BotonRedondeado("Guardar", null);
		btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnGuardar.setBounds(100, 500, 120, 40);
		btnGuardar.setBackground(new Color(84, 84, 84));
		btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFocusPainted(false);
        btnGuardar.addMouseListener(this);
        
        if (temporada.getEstado().equals("Activa") && ("Administrador".equals(rol) || "Árbitro".equals(rol))) {
    		panelIzquierdo.add(btnGuardar);
        }
		
		btnRestablecer = new BotonRedondeado("Restablecer", null);
		btnRestablecer.setForeground(Color.WHITE);
		btnRestablecer.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnRestablecer.setFocusPainted(false);
		btnRestablecer.setBackground(new Color(84, 84, 84));
		btnRestablecer.setBounds(235, 500, 150, 40);
		
		btnRestablecer.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	  // === INICIO: LOGGING PARA RESTABLECER PUNTOS ===
		        logClase.logAction("Intentando restablecer los puntos de los partidos para la jornada: " + jornadaSeleccionada.getNumero());
		        // === FIN: LOGGING PARA RESTABLECER PUNTOS ===
		        if (jornadaSeleccionada != null) {
		        	Component view = scrollPane.getViewport().getView();
		        	
		        	panelPartidos = (JPanel) view;
		        	Component[] componentes = panelPartidos.getComponents();

		        	for (Component componente : componentes) {
		        	    if (componente instanceof JPanel) {
		        	        panelPartido = (JPanel) componente;

		        	        for (Partido partido : partidos) {
		        	            for (Component subComponente : panelPartido.getComponents()) {
		        	                if (subComponente instanceof TextoRedondeado) {
		        	                    TextoRedondeado textField = (TextoRedondeado) subComponente;

		        	                    if (textField.getName().equals("local_" + partido.getEquipoLocal().getNombre())) {
		        	                        textField.setText("0");
		        	                    } else if (textField.getName().equals("visitante_" + partido.getEquipoVisitante().getNombre())) {
		        	                        textField.setText("0");
		        	                    }
		        	                }
		        	            }
		        	        }
		        	    }
		        	}
		        	
		        	datosModificados = true;
		        	actualizarTitulo();

		            // === INICIO: LOGGING PARA PUNTOS RESTABLECIDOS ===
		            logClase.logAction("Los puntos de los partidos para la jornada " + jornadaSeleccionada.getNumero() + " han sido restablecidos a 0.");
		            // === FIN: LOGGING PARA PUNTOS RESTABLECIDOS ===
		        } else {
		            // === INICIO: LOGGING PARA ERROR AL RESTABLECER PUNTOS ===
		        	logClase.logError("No se puede restablecer los puntos: La jornada seleccionada es nula.", null);
		            // === FIN: LOGGING PARA ERROR AL RESTABLECER PUNTOS ===
		        }
		    }
		});
		
		if (temporada.getEstado().equals("Activa") && ("Administrador".equals(rol) || "Árbitro".equals(rol))) {
    		panelIzquierdo.add(btnRestablecer);
        }
		
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
		
		btnVolverMenu = new BotonRedondeado("Volver al Menú", null);
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
		btnVolverMenu.setBounds(300, 496, 150, 29);
		btnVolverMenu.addMouseListener(this);
		panelDerecho.add(btnVolverMenu);
		
		mostrarClasificacion(temporada);
		verificarPuntosCompletados();
	}
	
	// Método cargarPartidos()
	private void cargarPartidos(Temporada temporada, Jornada jornada) {		
	    if (scrollPane == null) {
	        scrollPane = new JScrollPane();
	        scrollPane.setBounds(30, 164, 430, 311);
	        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
	        scrollPane.setBorder(BorderFactory.createEmptyBorder());
	        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	        panelIzquierdo.add(scrollPane);
	    } else {
	        if (scrollPane.getViewport().getView() != null) {
	            ((JPanel) scrollPane.getViewport().getView()).removeAll();
	        }
	    }

	    panelPartidos = new JPanel();
	    panelPartidos.setLayout(new BoxLayout(panelPartidos, BoxLayout.Y_AXIS));
	    panelPartidos.setBackground(panelIzquierdo.getBackground());

	    int indice = comboBoxJornadas.getSelectedIndex();
	    if (indice < 0) return;

	    partidos = jornada.getPartidos();

	    if (partidos.isEmpty()) {
	        lblNoPartidos = new JLabel("<html>No se han encontrado partidos para la jornada<br>seleccionada</html>", SwingConstants.CENTER);
	        lblNoPartidos.setFont(new Font("SansSerif", Font.PLAIN, 16));
	        lblNoPartidos.setForeground(new Color(84, 84, 84));
	        panelPartidos.add(lblNoPartidos);
	    } else {
	        for (Partido partido : partidos) {
	            panelPartido = crearPanelPartido(partido, temporada);
	            panelPartidos.add(panelPartido);
	            panelPartidos.add(Box.createRigidArea(new Dimension(0, 2)));
	        }
	    }
	    scrollPane.setViewportView(panelPartidos);
	    
	    panelIzquierdo.revalidate();
	    panelIzquierdo.repaint();
	}
	
	private JPanel crearPanelPartido(Partido partido, Temporada temporada) {
	    panelPartido = new JPanel();
	    panelPartido.setLayout(null);
	    panelPartido.setPreferredSize(new Dimension(400, 100));
	    panelPartido.setBackground(new Color(255, 243, 205));

	    // Equipo local
	    lblEquipoLocal = new JLabel(partido.getEquipoLocal().getNombre());
	    lblEquipoLocal.setBounds(79, 15, 190, 30);
	    lblEquipoLocal.setFont(new Font("SansSerif", Font.PLAIN, 16));
	    lblEquipoLocal.setForeground(new Color(84, 84, 84));
	    panelPartido.add(lblEquipoLocal);

	    logoLocal = new ImageIcon(partido.getEquipoLocal().getRutaFoto());
	    Image originalImageLocal = logoLocal.getImage();

	    int nuevoAltoLocal = 40;
	    int nuevoAnchoLocal = (int) ((double) originalImageLocal.getWidth(null) / originalImageLocal.getHeight(null) * nuevoAltoLocal);

	    imgLocal = originalImageLocal.getScaledInstance(nuevoAnchoLocal, nuevoAltoLocal, Image.SCALE_SMOOTH);
	    logoLocal = new ImageIcon(imgLocal);

	    lblLogoLocal = new JLabel(logoLocal);
	    lblLogoLocal.setBounds(20, 11, 40, 40);
	    panelPartido.add(lblLogoLocal);

	    if (temporada.getEstado().equals("Activa") && ("Administrador".equals(rol) || "Árbitro".equals(rol))) {
	        textPtsLocal = new TextoRedondeado(20);
	        textPtsLocal.setFont(new Font("SansSerif", Font.PLAIN, 14));
	        textPtsLocal.setHorizontalAlignment(SwingConstants.CENTER);
	        textPtsLocal.setBounds(279, 15, 50, 30);
	        textPtsLocal.setText(String.valueOf(partido.getPuntosLocal()));
	        textPtsLocal.setName("local_" + partido.getEquipoLocal().getNombre());
	        textPtsLocal.addFocusListener(new FocusListener() {
	            @Override
	            public void focusGained(FocusEvent e) {
	                TextoRedondeado source = (TextoRedondeado) e.getSource();
	                source.select(0, source.getText().length());
	            }

	            @Override
	            public void focusLost(FocusEvent e) {
	                TextoRedondeado source = (TextoRedondeado) e.getSource();
	                source.select(0, 0);
	                datosModificados = true;
	                actualizarTitulo();
	            }
	        });
	        panelPartido.add(textPtsLocal);
	    } else {
	        JLabel lblPtsLocal = new JLabel(String.valueOf(partido.getPuntosLocal()));
	        lblPtsLocal.setFont(new Font("SansSerif", Font.BOLD, 16));
	        lblPtsLocal.setHorizontalAlignment(SwingConstants.CENTER);
	        lblPtsLocal.setBounds(279, 15, 50, 30);
	        lblPtsLocal.setForeground(new Color(84, 84, 84));
	        panelPartido.add(lblPtsLocal);
	    }

	    linea = new LineaPanel();
	    linea.setForeground(new Color(166, 166, 166));
	    linea.setBounds(20, 53, 130, 4);
	    linea.setBackground(new Color(255, 243, 205));
	    panelPartido.add(linea);

	    lblVS = new JLabel("vs");
	    lblVS.setVerticalAlignment(SwingConstants.TOP);
	    lblVS.setHorizontalAlignment(SwingConstants.CENTER);
	    lblVS.setBounds(164, 43, 18, 21);
	    lblVS.setFont(new Font("SansSerif", Font.BOLD, 16));
	    lblVS.setForeground(new Color(166, 166, 166));
	    panelPartido.add(lblVS);

	    linea_2 = new LineaPanel();
	    linea_2.setForeground(new Color(166, 166, 166));
	    linea_2.setBackground(new Color(255, 243, 205));
	    linea_2.setBounds(198, 53, 130, 4);
	    panelPartido.add(linea_2);

	    // Equipo visitante
	    lblEquipoVisitante = new JLabel(partido.getEquipoVisitante().getNombre());
	    lblEquipoVisitante.setBounds(79, 64, 190, 30);
	    lblEquipoVisitante.setFont(new Font("SansSerif", Font.PLAIN, 16));
	    lblEquipoVisitante.setForeground(new Color(84, 84, 84));
	    panelPartido.add(lblEquipoVisitante);
	    
	    logoVisitante = new ImageIcon(partido.getEquipoVisitante().getRutaFoto());
	    Image originalImageVisitante = logoVisitante.getImage();

	    int nuevoAltoVisitante = 40;
	    int nuevoAnchoVisitante = (int) ((double) originalImageVisitante.getWidth(null) / originalImageVisitante.getHeight(null) * nuevoAltoVisitante);

	    imgVisitante = originalImageVisitante.getScaledInstance(nuevoAnchoVisitante, nuevoAltoVisitante, Image.SCALE_SMOOTH);
	    logoVisitante = new ImageIcon(imgVisitante);

	    lblLogoVisitante = new JLabel(logoVisitante);
	    lblLogoVisitante.setBounds(20, 60, 40, 40);
	    panelPartido.add(lblLogoVisitante);

	    if (temporada.getEstado().equals("Activa") && ("Administrador".equals(rol) || "Árbitro".equals(rol))) {
	        textPtsVisitante = new TextoRedondeado(20);
	        textPtsVisitante.setFont(new Font("SansSerif", Font.PLAIN, 14));
	        textPtsVisitante.setHorizontalAlignment(SwingConstants.CENTER);
	        textPtsVisitante.setBounds(279, 65, 50, 30);
	        textPtsVisitante.setText(String.valueOf(partido.getPuntosVisitante()));
	        textPtsVisitante.setName("visitante_" + partido.getEquipoVisitante().getNombre());
	        textPtsVisitante.addFocusListener(new FocusListener() {
	            @Override
	            public void focusGained(FocusEvent e) {
	                TextoRedondeado source = (TextoRedondeado) e.getSource();
	                source.select(0, source.getText().length());
	            }

	            @Override
	            public void focusLost(FocusEvent e) {
	                TextoRedondeado source = (TextoRedondeado) e.getSource();
	                source.select(0, 0);
	                datosModificados = true;
	                actualizarTitulo();
	            }
	        });
	        panelPartido.add(textPtsVisitante);
	    } else {
	        JLabel lblPtsVisitante = new JLabel(String.valueOf(partido.getPuntosVisitante()));
	        lblPtsVisitante.setFont(new Font("SansSerif", Font.BOLD, 16));
	        lblPtsVisitante.setHorizontalAlignment(SwingConstants.CENTER);
	        lblPtsVisitante.setBounds(279, 65, 50, 30);
	        lblPtsVisitante.setForeground(new Color(84, 84, 84));
	        panelPartido.add(lblPtsVisitante);
	    }
	    
	    lblFecha = new JLabel(partido.getFecha().toString());
	    lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
	    lblFecha.setBounds(340, 30, 80, 30);
	    lblFecha.setFont(new Font("SansSerif", Font.BOLD, 16));
	    lblFecha.setForeground(new Color(84, 84, 84));
	    panelPartido.add(lblFecha);

	    lblHora = new JLabel(partido.getHora().toString());
	    lblHora.setHorizontalAlignment(SwingConstants.CENTER);
	    lblHora.setBounds(355, 60, 50, 30);
	    lblHora.setFont(new Font("SansSerif", Font.BOLD, 16));
	    lblHora.setForeground(new Color(84, 84, 84));
	    panelPartido.add(lblHora);

	    return panelPartido;
	}
	
	private void mostrarClasificacion(Temporada temporada) {
	    String[] columnNames = { "", "Equipo", "Ptos.", "PJ", "PG", "PP", "PF", "PC", "DP" };

	    dtmClasificacion = new DefaultTableModel(null, columnNames);

	    List<Object[]> filas = new ArrayList<>();

	    for (int i = 0; i < temporada.getEquipos().size(); i++) {
	        Equipo equipo = temporada.getEquipos().get(i);

	        int puntos = 0;
	        int partidosJugados = 0;
	        int partidosGanados = 0;
	        int partidosPerdidos = 0;
	        int puntosFavor = 0;
	        int puntosContra = 0;

	        for (Jornada jornada : temporada.getJornadas()) {
	            for (Partido partido : jornada.getPartidos()) {
	                if (partido.getEquipoLocal().equals(equipo) || partido.getEquipoVisitante().equals(equipo)) {
	                    if (partido.getPuntosLocal() > 0 || partido.getPuntosVisitante() > 0) {
	                        partidosJugados++;

	                        if (partido.getEquipoLocal().equals(equipo)) {
	                            puntosFavor += partido.getPuntosLocal();
	                            puntosContra += partido.getPuntosVisitante();
	                            if (partido.getPuntosLocal() > partido.getPuntosVisitante()) {
	                                puntos += 2;
	                                partidosGanados++;
	                            } else if (partido.getPuntosLocal() < partido.getPuntosVisitante()) {
	                                puntos += 1;
	                                partidosPerdidos++;
	                            } else {
	                                puntos += 1;
	                            }
	                        }

	                        if (partido.getEquipoVisitante().equals(equipo)) {
	                            puntosFavor += partido.getPuntosVisitante();
	                            puntosContra += partido.getPuntosLocal();
	                            if (partido.getPuntosVisitante() > partido.getPuntosLocal()) {
	                                puntos += 2;
	                                partidosGanados++;
	                            } else if (partido.getPuntosVisitante() < partido.getPuntosLocal()) {
	                                puntos += 1;
	                                partidosPerdidos++;
	                            } else {
	                                puntos += 1;
	                            }
	                        }
	                    }
	                }
	            }
	        }

	        int diferenciaPuntos = puntosFavor - puntosContra;

	        Object[] row = { i + 1, equipo.getNombre(), puntos, partidosJugados, partidosGanados, partidosPerdidos, puntosFavor, puntosContra, diferenciaPuntos };
	        filas.add(row);
	    }

	    // Ordenar los equipos
	    filas.sort((fila1, fila2) -> {
	        int puntos1 = (int) fila1[2];
	        int puntos2 = (int) fila2[2];

	        if (puntos1 != puntos2) {
	            return Integer.compare(puntos2, puntos1);
	        }

	        int diferencia1 = (int) fila1[8];
	        int diferencia2 = (int) fila2[8];

	        if (diferencia1 != diferencia2) {
	            return Integer.compare(diferencia2, diferencia1);
	        }

	        int ganados1 = (int) fila1[4];
	        int ganados2 = (int) fila2[4];

	        return Integer.compare(ganados2, ganados1);
	    });

	    for (int i = 0; i < filas.size(); i++) {
	        Object[] fila = filas.get(i);
	        fila[0] = i + 1;
	        dtmClasificacion.addRow(fila);
	    }

	    tablaClasificacion = new JTable(dtmClasificacion) {
	        private static final long serialVersionUID = 1L;

	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	    };

	    tablaClasificacion.setFont(new Font("SansSerif", Font.PLAIN, 14));
	    tablaClasificacion.setForeground(Color.BLACK);
	    tablaClasificacion.setBackground(new Color(217, 217, 217));
	    tablaClasificacion.setRowHeight(30);
	    tablaClasificacion.setBorder(null);
	    tablaClasificacion.setShowGrid(false);

	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    centerRenderer.setHorizontalAlignment(JLabel.CENTER);

	    for (int col = 0; col < tablaClasificacion.getColumnCount(); col++) {
	        if (col != 1) {
	            tablaClasificacion.getColumnModel().getColumn(col).setCellRenderer(centerRenderer);
	        }
	    }

	    tablaClasificacion.getColumnModel().getColumn(0).setPreferredWidth(30);
	    tablaClasificacion.getColumnModel().getColumn(1).setPreferredWidth(280);
	    
	    for (int col = 3; col < 6; col++) {
	        tablaClasificacion.getColumnModel().getColumn(col).setPreferredWidth(40);
	    }

	    for (int col = 6; col < 9; col++) {
	            tablaClasificacion.getColumnModel().getColumn(col).setPreferredWidth(70);
	    }

	    tablaClasificacion.setCellSelectionEnabled(false);
	    tablaClasificacion.setRowSelectionAllowed(false);
	    tablaClasificacion.setColumnSelectionAllowed(false);
	    tablaClasificacion.getTableHeader().setReorderingAllowed(false);

	    header = tablaClasificacion.getTableHeader();
	    header.setPreferredSize(new Dimension(header.getWidth(), 30));
	    header.setDefaultRenderer(new DefaultTableCellRenderer() {
	        private static final long serialVersionUID = 1L;

	        @Override
	        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	            label.setBackground(new Color(112, 117, 126));
	            label.setForeground(Color.WHITE);
	            label.setHorizontalAlignment(column == 1 ? JLabel.LEFT : JLabel.CENTER);
	            label.setBorder(BorderFactory.createEmptyBorder());
	            
	            if (value != null) {
	                String tooltipText = switch (column) {
	                    case 2 -> "Puntos";
	                    case 3 -> "Partidos jugados";
	                    case 4 -> "Partidos ganados";
	                    case 5 -> "Partidos perdidos";
	                    case 6 -> "Puntos a favor";
	                    case 7 -> "Puntos en contra";
	                    case 8 -> "Diferencia de puntos";
	                    default -> null;
	                };
	                label.setToolTipText(tooltipText);
	            } else {
	                label.setToolTipText(null);
	            }
	            
	            return label;
	        }
	    });

	    scrollPaneClasificacion = new JScrollPane(tablaClasificacion);
	    scrollPaneClasificacion.setBounds(30, 100, 420, 374);
	    scrollPaneClasificacion.getViewport().setBackground(new Color(218, 168, 129));
	    scrollPaneClasificacion.setBorder(BorderFactory.createEmptyBorder());
	    panelDerecho.add(scrollPaneClasificacion);
	    
	    btnExportarPDF = new BotonRedondeado("Volver al Menú", (ImageIcon) null);
	    btnExportarPDF.addActionListener(e -> {
	        if (tablaClasificacion.getRowCount() == 0) {
	            JOptionPane.showMessageDialog(null, "No hay datos en la tabla para exportar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
	            return;
	        }

	        // Seleccionar la ubicación para guardar el archivo
	        JnaFileChooser fileChooser = new JnaFileChooser();
	        fileChooser.setTitle("Guardar como...");
	        fileChooser.addFilter("PDF (*.pdf)", "pdf");
	        fileChooser.addFilter("Todos los Archivos", "*");
	        fileChooser.setDefaultFileName("Clasificación " + temporada.getPeriodo() + ".pdf");

	        if (fileChooser.showSaveDialog(this)) {
	            File archivo = fileChooser.getSelectedFile();
	            ExportarPDF exportador = new ExportarPDF();
	            exportador.exportar(tablaClasificacion, archivo.getAbsolutePath());
	        }
	    });

	  
	    btnExportarPDF.setText("Exportar PDF");
	    btnExportarPDF.setForeground(Color.WHITE);
	    btnExportarPDF.setFont(new Font("SansSerif", Font.BOLD, 16));
	    btnExportarPDF.setFocusPainted(false);
	    btnExportarPDF.setBackground(new Color(84, 84, 84));
	    btnExportarPDF.setBounds(140, 496, 145, 29);
	    panelDerecho.add(btnExportarPDF);
	}
	
	// Método para comprobar si todos los puntos están rellenados
	private void verificarPuntosCompletados() {
	    boolean todosLosPuntosRellenados = true;
	    for (Jornada jornada : temporada.getJornadas()) {
	        for (Partido partido : jornada.getPartidos()) {
	            if (partido.getPuntosLocal() == 0 || partido.getPuntosVisitante() == 0) {
	                todosLosPuntosRellenados = false;
	                break;
	            }
	        }

	        if (!todosLosPuntosRellenados) {
	            break;
	        }
	    }

	    if (todosLosPuntosRellenados) {
	        btnFinalizarTemporada.setVisible(true);
	    } else {
	        btnFinalizarTemporada.setVisible(false);
	    }
	}

	
	// Método para guardar los resultados de los partidos
	private void guardarResultados() {
	    try {
	        if (comboBoxJornadas.getSelectedIndex() < 0 || jornadaSeleccionada == null) {
	            JOptionPane.showMessageDialog(this, "No se ha seleccionado ninguna jornada.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        List<Partido> partidos = jornadaSeleccionada.getPartidos();
	        if (partidos.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "No hay partidos en la jornada seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        Component view = scrollPane.getViewport().getView();
	        if (!(view instanceof JPanel)) {
	            JOptionPane.showMessageDialog(this, "No se encontraron componentes para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        panelPartidos = (JPanel) view;
	        Component[] componentes = panelPartidos.getComponents();

	        for (Component componente : componentes) {
	            if (componente instanceof JPanel) {
	                panelPartido = (JPanel) componente;

	                for (Partido partido : partidos) {
	                    for (Component subComponente : panelPartido.getComponents()) {
	                        if (subComponente instanceof TextoRedondeado) {
	                            TextoRedondeado textField = (TextoRedondeado) subComponente;

	                            try {
	                                if (textField.getName().equals("local_" + partido.getEquipoLocal().getNombre())) {
	                                    partido.setPuntosLocal(Integer.parseInt(textField.getText()));
	                                } else if (textField.getName().equals("visitante_" + partido.getEquipoVisitante().getNombre())) {
	                                    partido.setPuntosVisitante(Integer.parseInt(textField.getText()));
	                                    
	                                }
	                            
	                            } catch (NumberFormatException ex) {
	                                JOptionPane.showMessageDialog(this, "Error en el formato de los puntos. Introduce números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
	                                return;
	                            }
	                        }
	                    }
	                }
	            }
	        }

	        temporada.guardarTemporada(temporada);
	        JOptionPane.showMessageDialog(this, "Resultados guardados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        mostrarClasificacion(temporada);
	        verificarPuntosCompletados();

	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(this, "Ocurrió un error al guardar los resultados: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        ex.printStackTrace();
	    }
	}
	
	private void actualizarTitulo() {
	    String titulo = "LPB Basketball - Menú Jornadas";
	    if (datosModificados) {
	        setTitle("*" + titulo);
	    } else {
	    	setTitle(titulo);
	    }
	}
	
    public static String clasificacionXML(Temporada temporada, Equipo equipo) {
    	String xml = "";
        
    	int puntos = 0;
        int partidosJugados = 0;
        int partidosGanados = 0;
        int partidosPerdidos = 0;
        int puntosFavor = 0;
        int puntosContra = 0;
        
        for (Jornada jornada : temporada.getJornadas()) {
            for (Partido partido : jornada.getPartidos()) {
                if (partido.getEquipoLocal().equals(equipo) || partido.getEquipoVisitante().equals(equipo)) {
                    if (partido.getPuntosLocal() > 0 || partido.getPuntosVisitante() > 0) {
                        partidosJugados++;

                        if (partido.getEquipoLocal().equals(equipo)) {
                            puntosFavor += partido.getPuntosLocal();
                            puntosContra += partido.getPuntosVisitante();
                            if (partido.getPuntosLocal() > partido.getPuntosVisitante()) {
                                puntos += 2;
                                partidosGanados++;
                            } else if (partido.getPuntosLocal() < partido.getPuntosVisitante()) {
                                puntos += 1;
                                partidosPerdidos++;
                            } else {
                                puntos += 1;
                            }
                        }

                        if (partido.getEquipoVisitante().equals(equipo)) {
                            puntosFavor += partido.getPuntosVisitante();
                            puntosContra += partido.getPuntosLocal();
                            if (partido.getPuntosVisitante() > partido.getPuntosLocal()) {
                                puntos += 2;
                                partidosGanados++;
                            } else if (partido.getPuntosVisitante() < partido.getPuntosLocal()) {
                                puntos += 1;
                                partidosPerdidos++;
                            } else {
                                puntos += 1;
                            }
                        }
                    }
                }
            }
        }

        int diferenciaPuntos = puntosFavor - puntosContra;

        // Agregamos la información del equipo en formato XML
        xml = "				<puntos>" + puntos + "</puntos>\r\n"
        		+ "				<partidosJugados>" + partidosJugados + "</partidosJugados>\r\n"
				+ "				<partidosGanados>" + partidosGanados + "</partidosGanados>\r\n"
				+ "				<partidosPerdidos>" + partidosPerdidos + "</partidosPerdidos>\r\n"
				+ "				<puntosFavor>" + puntosFavor + "</puntosFavor>\r\n"
				+ "				<puntosContra>" + puntosContra + "</puntosContra>\r\n"
				+ "				<diferenciaPuntos>" + diferenciaPuntos + "</diferenciaPuntos>\r\n";

        return xml;
    }

	@Override
	public void mouseClicked(MouseEvent e) {
	    if (e.getSource() == btnAtras) {
	    	if (datosModificados) {
	    	    int opcion = JOptionPane.showConfirmDialog(this, "Tienes cambios sin guardar, ¿deseas guardarlos ahora?", "Confirmar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
    	            
    	        if (opcion == JOptionPane.YES_OPTION) {
    	        	guardarResultados();
    	        } else if (opcion == JOptionPane.CANCEL_OPTION) {
    	        	return;
    	        }
    	        
	        	datosModificados = false;
	        	actualizarTitulo();
    	        
		        int selectedIndex = comboBoxJornadas.getSelectedIndex();
		        if (selectedIndex > 0) {
		            comboBoxJornadas.setSelectedIndex(selectedIndex - 1);
			        jornadaSeleccionada = temporada.getJornadas().get(selectedIndex - 1);
			        cargarPartidos(temporada, jornadaSeleccionada);
		        }
	    	} else {
		        int selectedIndex = comboBoxJornadas.getSelectedIndex();
		        if (selectedIndex > 0) {
		            comboBoxJornadas.setSelectedIndex(selectedIndex - 1);
			        jornadaSeleccionada = temporada.getJornadas().get(selectedIndex - 1);
			        cargarPartidos(temporada, jornadaSeleccionada);
		        }
	    	}
	    } else if (e.getSource() == btnAdelante) {
	    	if (datosModificados) {
	    	    int opcion = JOptionPane.showConfirmDialog(this, "Tienes cambios sin guardar, ¿deseas guardarlos ahora?", "Confirmar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
	            
    	        if (opcion == JOptionPane.YES_OPTION) {
    	        	guardarResultados();
    	        } else if (opcion == JOptionPane.CANCEL_OPTION) {
    	        	return;
    	        }
    	        
	        	datosModificados = false;
	        	actualizarTitulo();
    	        
		        int selectedIndex = comboBoxJornadas.getSelectedIndex();
		        if (selectedIndex < comboBoxJornadas.getItemCount() - 1) {
		            comboBoxJornadas.setSelectedIndex(selectedIndex + 1);
			        jornadaSeleccionada = temporada.getJornadas().get(selectedIndex + 1);
			        cargarPartidos(temporada, jornadaSeleccionada);
		        }
	    	} else {
		        int selectedIndex = comboBoxJornadas.getSelectedIndex();
		        if (selectedIndex < comboBoxJornadas.getItemCount() - 1) {
		            comboBoxJornadas.setSelectedIndex(selectedIndex + 1);
			        jornadaSeleccionada = temporada.getJornadas().get(selectedIndex + 1);
			        cargarPartidos(temporada, jornadaSeleccionada);
		        }
	    	}
	    } else if (e.getSource() == btnGuardar) {
	        guardarResultados();
	        // Aquí se asume que se guardan resultados de partidos
	        if (jornadaSeleccionada != null) {
	            for (Partido partido : jornadaSeleccionada.getPartidos()) {
	                // Simula guardar los resultados del partido actual
	                // === INICIO: LOGGING PARA GUARDAR RESULTADOS ===
	                logClase.logAction("Se han guardado los resultados del partido: " +
	                        partido.getEquipoLocal().getNombre() + " vs " + partido.getEquipoVisitante().getNombre() +
	                        " | Resultado: " + partido.getPuntosLocal() + " - " + partido.getPuntosVisitante());
	                // === FIN: LOGGING PARA GUARDAR RESULTADOS ===
	            }

	            JOptionPane.showMessageDialog(null, "Resultados guardados correctamente.");
	            datosModificados = false;
	            actualizarTitulo();
	        }
	     
	    }
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}
