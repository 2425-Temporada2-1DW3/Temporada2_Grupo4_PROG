package LPB;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import LPBCLASES.BackgroundFader;
import LPBCLASES.BotonRedondeado;
import LPBCLASES.Equipo;
import LPBCLASES.Jornada;
import LPBCLASES.LineaPanel;
import LPBCLASES.Partido;
import LPBCLASES.Temporada;
import LPBCLASES.TextoRedondeado;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import java.awt.event.ActionEvent;

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
	private JLabel lblClasificacion;
	private Jornada jornadaSeleccionada;
	private JTable tablaClasificacion;
	private JScrollPane scrollPaneClasificacion;
	private JTableHeader header;
	private JPanel panelPartidos;
	private List<Partido> partidos;
	private DefaultTableModel dtmClasificacion;
	
	private BackgroundFader fader;

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
		lblEstado.setBounds(15, 85, 52, 19);
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

		        int generacionJornadas = temporada.generarJornadas();
		        
		        if (generacionJornadas == 0) {
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
					} catch (IOException e1) {
						System.out.println("ERROR. No se han encontrado datos de la temporada.");
					}
			        cargarPartidos(temporada, jornadaSeleccionada);
			        panelIzquierdo.remove(btnActivarTemporada);
			        panelIzquierdo.add(btnFinalizarTemporada);
	        	}
		    }
		});
		btnActivarTemporada.addMouseListener(this);
		
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
					} catch (IOException e1) {
						System.out.println("ERROR. No se han encontrado datos de la temporada.");
					}
		    	}
		    }
		});
		btnFinalizarTemporada.addMouseListener(this);
		
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
        
        if (temporada.getEstado().equals("Activa")) {
    		panelIzquierdo.add(btnGuardar);
        }
		
		btnRestablecer = new BotonRedondeado("Restablecer", null);
		btnRestablecer.setForeground(Color.WHITE);
		btnRestablecer.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnRestablecer.setFocusPainted(false);
		btnRestablecer.setBackground(new Color(84, 84, 84));
		btnRestablecer.setBounds(235, 500, 150, 40);
		btnRestablecer.addMouseListener(this);
		
        if (temporada.getEstado().equals("Activa")) {
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
		btnVolverMenu.setBounds(300, 485, 150, 40);
		btnVolverMenu.addMouseListener(this);
		panelDerecho.add(btnVolverMenu);
		
		mostrarClasificacion(temporada);
	}
	
	// Método cargarPartidos()
	private void cargarPartidos(Temporada temporada, Jornada jornada) {		
	    if (scrollPane == null) {
	        scrollPane = new JScrollPane();
	        scrollPane.setBounds(50, 164, 400, 311);
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
	    lblEquipoLocal.setBounds(104, 15, 190, 30);
	    lblEquipoLocal.setFont(new Font("SansSerif", Font.PLAIN, 16));
	    lblEquipoLocal.setForeground(new Color(84, 84, 84));
	    panelPartido.add(lblEquipoLocal);

	    logoLocal = new ImageIcon(getClass().getResource("/imagenes/temporadas/Temporada " + temporada.getPeriodo() + "/" + partido.getEquipoLocal().getNombre() + "/" + partido.getEquipoLocal().getNombre() + ".png"));
	    imgLocal = logoLocal.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	    logoLocal = new ImageIcon(imgLocal);
	    
	    lblLogoLocal = new JLabel(logoLocal);
	    lblLogoLocal.setBounds(45, 11, 40, 40);
	    panelPartido.add(lblLogoLocal);

	    textPtsLocal = new TextoRedondeado(20);
	    textPtsLocal.setFont(new Font("SansSerif", Font.PLAIN, 14));
	    textPtsLocal.setHorizontalAlignment(SwingConstants.CENTER);
	    textPtsLocal.setBounds(304, 15, 50, 30);
	    textPtsLocal.setText(String.valueOf(partido.getPuntosLocal()));
	    panelPartido.add(textPtsLocal);
	    
		linea = new LineaPanel();
		linea.setForeground(new Color(166, 166, 166));
		linea.setBounds(45, 53, 130, 4);
		linea.setBackground(new Color(255, 243, 205));
		panelPartido.add(linea);

	    lblVS = new JLabel("vs");
	    lblVS.setVerticalAlignment(SwingConstants.TOP);
	    lblVS.setHorizontalAlignment(SwingConstants.CENTER);
	    lblVS.setBounds(189, 43, 18, 21);
	    lblVS.setFont(new Font("SansSerif", Font.BOLD, 16));
	    lblVS.setForeground(new Color(166, 166, 166));
	    panelPartido.add(lblVS);
	    
		linea_2 = new LineaPanel();
		linea_2.setForeground(new Color(166, 166, 166));
		linea_2.setBackground(new Color(255, 243, 205));
		linea_2.setBounds(223, 53, 130, 4);
		panelPartido.add(linea_2);

	    lblEquipoVisitante = new JLabel(partido.getEquipoVisitante().getNombre());
	    lblEquipoVisitante.setBounds(104, 64, 190, 30);
	    lblEquipoVisitante.setFont(new Font("SansSerif", Font.PLAIN, 16));
	    lblEquipoVisitante.setForeground(new Color(84, 84, 84));
	    panelPartido.add(lblEquipoVisitante);

	    logoVisitante = new ImageIcon(getClass().getResource("/imagenes/temporadas/Temporada " + temporada.getPeriodo() + "/" + partido.getEquipoVisitante().getNombre() + "/" + partido.getEquipoVisitante().getNombre() + ".png"));
	    imgVisitante = logoVisitante.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	    logoVisitante = new ImageIcon(imgVisitante);

	    lblLogoVisitante = new JLabel(logoVisitante);
	    lblLogoVisitante.setBounds(45, 60, 40, 40);
	    panelPartido.add(lblLogoVisitante);

	    textPtsVisitante = new TextoRedondeado(20);
	    textPtsVisitante.setFont(new Font("SansSerif", Font.PLAIN, 14));
	    textPtsVisitante.setHorizontalAlignment(SwingConstants.CENTER);
	    textPtsVisitante.setBounds(304, 65, 50, 30);
	    textPtsVisitante.setText(String.valueOf(partido.getPuntosVisitante()));
	    panelPartido.add(textPtsVisitante);

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

	    tablaClasificacion.getColumnModel().getColumn(1).setPreferredWidth(260);

	    for (int col = 0; col < tablaClasificacion.getColumnCount(); col++) {
	        if (col != 1) {
	            tablaClasificacion.getColumnModel().getColumn(col).setPreferredWidth(50);
	        }
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
	            return label;
	        }
	    });

	    scrollPaneClasificacion = new JScrollPane(tablaClasificacion);
	    scrollPaneClasificacion.setBounds(30, 100, 420, 374);
	    scrollPaneClasificacion.getViewport().setBackground(new Color(218, 168, 129));
	    scrollPaneClasificacion.setBorder(BorderFactory.createEmptyBorder());
	    panelDerecho.add(scrollPaneClasificacion);
	}
	
	private void guardarResultados() {
	    try {
	        int indiceJornada = comboBoxJornadas.getSelectedIndex();
	        if (indiceJornada < 0 || jornadaSeleccionada == null) {
	            JOptionPane.showMessageDialog(this, "No se ha seleccionado ninguna jornada.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        List<Partido> partidos = jornadaSeleccionada.getPartidos();
	        if (partidos.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "No hay partidos en la jornada seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        Component view = scrollPane.getViewport().getView();
	        if (view instanceof Container) {
	            Component[] componentes = ((Container) view).getComponents();
	            int index = 0;
	            for (Component componente : componentes) {
	                if (componente instanceof JPanel && index < partidos.size()) {
	                    JPanel panelPartido = (JPanel) componente;
	                    Partido partido = partidos.get(index);

	                    for (Component subComponente : panelPartido.getComponents()) {
	                        if (subComponente instanceof TextoRedondeado) {
	                            TextoRedondeado textField = (TextoRedondeado) subComponente;
	                            try {
	                                if (textField == textPtsLocal) {
	                                    int puntosLocal = Integer.parseInt(textField.getText());
	                                    partido.setPuntosLocal(puntosLocal);
	                                } else if (textField == textPtsVisitante) {
	                                    int puntosVisitante = Integer.parseInt(textField.getText());
	                                    partido.setPuntosVisitante(puntosVisitante);
	                                }
	                            } catch (NumberFormatException ex) {
	                                JOptionPane.showMessageDialog(this, "Error en el formato de los puntos. Por favor, introduce números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
	                                return;
	                            }
	                        }
	                    }
	                    index++;
	                }
	            }
	        }

	        JOptionPane.showMessageDialog(this, "Resultados guardados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(this, "Ocurrió un error al guardar los resultados: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        ex.printStackTrace();
	    }
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	    if (e.getSource() == btnAtras) {
	        int selectedIndex = comboBoxJornadas.getSelectedIndex();
	        if (selectedIndex > 0) {
	            comboBoxJornadas.setSelectedIndex(selectedIndex - 1);
	        }
	    } else if (e.getSource() == btnAdelante) {
	        int selectedIndex = comboBoxJornadas.getSelectedIndex();
	        if (selectedIndex < comboBoxJornadas.getItemCount() - 1) {
	            comboBoxJornadas.setSelectedIndex(selectedIndex + 1);
	        }
	    } else if (e.getSource() == btnGuardar) {
	        guardarResultados();
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
		Object o = e.getSource();
		
		if (o == btnGuardar) {
			fader.fadeBackground(btnGuardar, btnGuardar.getBackground(), new Color(0xa0a0a0));
		} else if (o == btnRestablecer) {
			fader.fadeBackground(btnRestablecer, btnRestablecer.getBackground(), new Color(0xa0a0a0));
		} else if (o == btnActivarTemporada) {
			fader.fadeBackground(btnActivarTemporada, btnActivarTemporada.getBackground(), new Color(0xff7f50));
		} else if (o == btnFinalizarTemporada) {
			fader.fadeBackground(btnFinalizarTemporada, btnFinalizarTemporada.getBackground(), new Color(0xff7f50));
		} else if (o == btnAdelante) {
			fader.fadeBackground(btnAdelante, btnAdelante.getBackground(), new Color(0xff7f50));
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
		} else if (o == btnAdelante) {
			fader.fadeBackground(btnAdelante, btnAdelante.getBackground(), new Color(0xf46b20));
		} else if (o == btnAtras) {
			fader.fadeBackground(btnAtras, btnAtras.getBackground(), new Color(0xf46b20));
		} else if (o == btnVolverMenu) {
			fader.fadeBackground(btnVolverMenu, btnVolverMenu.getBackground(), new Color(0x545454));
		} 
	}
}
