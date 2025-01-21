package LPB;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import LPBCLASES.BackgroundFader;
import LPBCLASES.BotonRedondeado;
import LPBCLASES.TextoRedondeado;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.BorderLayout;

public class MenuJornadas extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;

	// Componentes (variables) de la interfaz
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_2;
	private JPanel panel_4;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_9;
	private JPanel panel_10;
	private JPanel panel_1;
	private JPanel panel_3;
	private JPanel panel_5;
	
	private JTable table;
	private JTextField textPtsLocal1;
	private JTextField textPtsVisitante1;
	private JTextField textPtsLocal2;
	private JTextField textPtsVisitante2;
	private JTextField textPtsLocal3;
	private JTextField textPtsVisitante3;
	
	private JButton btnAtras;
	private JButton btnAlante;
	private JComboBox<String> comboBoxTemporadas;
	private JButton btnGuardar;
	
	private JLabel lblTemporada;
	private JLabel lblAniosTemporada;
	private JLabel lblLocal1;
	private JLabel lblVS;
	private JLabel lblEquipoVisitante;
	private JLabel lblEquipoLocal;
	private JLabel lblVS_1;
	private JLabel lblEquipoVisitante_1;
	private JLabel lblEquipoLocal_1;
	private JLabel lblVS_2;
	private JLabel lblEquipoVisitante_2;
	private JLabel lblClasificacion;
	private ImageIcon logo;
	
	private FlowLayout flowLayout;
	private FlowLayout flowLayout_1;
	private FlowLayout flowLayout_2;
	private FlowLayout flowLayout_3;
	private FlowLayout flowLayout_4;
	private FlowLayout flowLayout_5;
	private FlowLayout flowLayout_6;
	private FlowLayout flowLayout_7;
	
	private BackgroundFader fader;
	private JPanel panel_11;
	private JLabel labelLogo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuJornadas frame = new MenuJornadas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuJornadas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
		setTitle("LPB Basketball - Menú Jornadas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 992, 843);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 243, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		fader = new BackgroundFader();
		
		
		// Panel principal para los elementos superiores
		panel = new JPanel();
		panel.setBackground(new Color(255, 243, 205));
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		// Panel para la temporada
		panel_2 = new JPanel();
		flowLayout_6 = (FlowLayout) panel_2.getLayout();
		flowLayout_6.setVgap(60);
		flowLayout_6.setHgap(10);
		panel_2.setBackground(new Color(255, 243, 205));
		panel.add(panel_2);
		lblTemporada = new JLabel("Temporada");
		lblTemporada.setFont(new Font("SansSerif", Font.BOLD, 25));
		panel_2.add(lblTemporada);
		
		lblAniosTemporada = new JLabel("22/23");
		lblAniosTemporada.setFont(new Font("SansSerif", Font.BOLD, 25));
		panel_2.add(lblAniosTemporada);
		
		// Panel de navegación entre temporadas
		panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 243, 205));
		flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setHgap(20);
		flowLayout.setVgap(10);
		flowLayout.setAlignOnBaseline(true);
		panel.add(panel_4);
		
		btnAtras = new BotonRedondeado("<", null);
		btnAtras.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnAtras.setBounds(327, 445, 200, 40);
		btnAtras.setBackground(new Color(0xf46b20));
		btnAtras.setForeground(Color.WHITE);
        btnAtras.setFocusPainted(false);
        btnAtras.addMouseListener(this);
		panel_4.add(btnAtras);
		
		comboBoxTemporadas = new JComboBox<String>();
		
		// añado elementos al combobox
		comboBoxTemporadas.addItem(" Temporada 23/24");
		comboBoxTemporadas.addItem(" Temporada 24/25");
		comboBoxTemporadas.setFont(new Font("Tahoma", Font.PLAIN, 18));
				
		panel_4.add(comboBoxTemporadas);
		
		btnAlante = new BotonRedondeado(">", null);
		btnAlante.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnAlante.setBounds(327, 445, 200, 40);
		btnAlante.setBackground(new Color(0xf46b20));
		btnAlante.setForeground(Color.WHITE);
        btnAlante.setFocusPainted(false);
        btnAlante.addMouseListener(this);
		panel_4.add(btnAlante);
		
		// Panel para los puntos de los partidos
		panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 243, 205));
		panel.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));
		
		// Panel 1 (partido 1)
		panel_7 = new JPanel();
		flowLayout_2 = (FlowLayout) panel_7.getLayout();
		flowLayout_2.setAlignOnBaseline(true);
		flowLayout_2.setHgap(20);
		flowLayout_2.setVgap(1);
		panel_7.setBackground(new Color(255, 243, 205));
		panel_6.add(panel_7);
		textPtsLocal1 = new TextoRedondeado(20);
		panel_7.add(textPtsLocal1);
		textPtsLocal1.setColumns(2);
		
		lblLocal1 = new JLabel("Equipo Local 1");
		lblLocal1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		panel_7.add(lblLocal1);
		
		lblVS = new JLabel("vs");
		lblVS.setFont(new Font("SansSerif", Font.PLAIN, 16));
		panel_7.add(lblVS);
		
		lblEquipoVisitante = new JLabel("Equipo Visitante 1");
		lblEquipoVisitante.setFont(new Font("SansSerif", Font.PLAIN, 16));
		panel_7.add(lblEquipoVisitante);
		
		textPtsVisitante1 = new TextoRedondeado(20);
		textPtsVisitante1.setColumns(2);
		panel_7.add(textPtsVisitante1);
		
		// Panel 2 (partido 2)
		panel_8 = new JPanel();
		flowLayout_3 = (FlowLayout) panel_8.getLayout();
		flowLayout_3.setAlignOnBaseline(true);
		flowLayout_3.setHgap(20);
		flowLayout_3.setVgap(1);
		panel_8.setBackground(new Color(255, 243, 205));
		panel_6.add(panel_8);
		
		textPtsLocal2 = new TextoRedondeado(20);
		textPtsLocal2.setColumns(2);
		panel_8.add(textPtsLocal2);
		
		lblEquipoLocal = new JLabel("Equipo Local 2");
		lblEquipoLocal.setFont(new Font("SansSerif", Font.PLAIN, 16));
		panel_8.add(lblEquipoLocal);
		
		lblVS_1 = new JLabel("vs");
		lblVS_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		panel_8.add(lblVS_1);
		
		lblEquipoVisitante_1 = new JLabel("Equipo Visitante 2");
		lblEquipoVisitante_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		panel_8.add(lblEquipoVisitante_1);
		
		textPtsVisitante2 = new TextoRedondeado(20);
		textPtsVisitante2.setColumns(2);
		panel_8.add(textPtsVisitante2);
		
		// Panel 3 (partido 3)
		panel_9 = new JPanel();
		flowLayout_4 = (FlowLayout) panel_9.getLayout();
		flowLayout_4.setAlignOnBaseline(true);
		flowLayout_4.setHgap(20);
		flowLayout_4.setVgap(1);
		panel_9.setBackground(new Color(255, 243, 205));
		panel_6.add(panel_9);
		textPtsLocal3 = new TextoRedondeado(20);
		textPtsLocal3.setColumns(2);
		panel_9.add(textPtsLocal3);
		
		lblEquipoLocal_1 = new JLabel("Equipo Local 3");
		lblEquipoLocal_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		panel_9.add(lblEquipoLocal_1);
		
		lblVS_2 = new JLabel("vs");
		lblVS_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		panel_9.add(lblVS_2);
		
		lblEquipoVisitante_2 = new JLabel("Equipo Visitante 3");
		lblEquipoVisitante_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		panel_9.add(lblEquipoVisitante_2);
		
		textPtsVisitante3 = new TextoRedondeado(20);
		textPtsVisitante3.setColumns(2);
		panel_9.add(textPtsVisitante3);
		
		// Botón guardar
		panel_10 = new JPanel();
		flowLayout_5 = (FlowLayout) panel_10.getLayout();
		flowLayout_5.setVgap(60);
		flowLayout_5.setAlignOnBaseline(true);
		flowLayout_5.setHgap(1);
		panel_10.setBackground(new Color(255, 243, 205));
		panel.add(panel_10);
		btnGuardar = new BotonRedondeado("Guardar", null);
		btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnGuardar.setBounds(327, 445, 200, 40);
		btnGuardar.setBackground(new Color(0xf46b20));
		btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFocusPainted(false);
        btnGuardar.addMouseListener(this);
		panel_10.add(btnGuardar);
		
		// Panel para la clasificación
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 243, 205));
		contentPane.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		// Panel para mostrar el título de clasificación
		panel_3 = new JPanel();
		flowLayout_7 = (FlowLayout) panel_3.getLayout();
		flowLayout_7.setVgap(60);
		panel_3.setBackground(new Color(255, 243, 205));
		panel_1.add(panel_3);
		lblClasificacion = new JLabel("Clasificacion");
		lblClasificacion.setFont(new Font("SansSerif", Font.BOLD, 25));
		panel_3.add(lblClasificacion);
		
		// Panel para la tabla de clasificación
		panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 243, 205));
		flowLayout_1 = (FlowLayout) panel_5.getLayout();
		flowLayout_1.setVgap(200);
		panel_1.add(panel_5);
		
		table = new JTable();
		panel_5.add(table);
		
		panel_11 = new JPanel();
		panel_11.setBackground(new Color(255, 243, 205));
		panel_1.add(panel_11);
		
		logo = new ImageIcon(getClass().getResource("/imagenes/logo220.png"));
		panel_11.setLayout(new BorderLayout(0, 0));
		labelLogo = new JLabel(logo);
		labelLogo.setFont(new Font("Arial", Font.BOLD, 16));
		labelLogo.setBounds(317, 10, 220, 220);
		panel_11.add(labelLogo);
		

		
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
			fader.fadeBackground(btnGuardar, btnGuardar.getBackground(), new Color(0xff7f50));
		} else if (o == btnAlante) {
			fader.fadeBackground(btnGuardar, btnGuardar.getBackground(), new Color(0xff7f50));
		} else if (o == btnAtras) {
			fader.fadeBackground(btnGuardar, btnGuardar.getBackground(), new Color(0xff7f50));
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object o = e.getSource();

		if (o == btnGuardar) {
			fader.fadeBackground(btnGuardar, btnGuardar.getBackground(), new Color(0xf46b20));
		} else if (o == btnAlante) {
			fader.fadeBackground(btnGuardar, btnGuardar.getBackground(), new Color(0xf46b20));
		} else if (o == btnAtras) {
			fader.fadeBackground(btnGuardar, btnGuardar.getBackground(), new Color(0xf46b20));
		} 
	}
}
