package LPB;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import LPBCLASES.BotonRedondeado;
import LPBCLASES.Equipo;
import LPBCLASES.Jugador;

public class VerEquipo extends JFrame {
	private JPanel panelIzquierdo;
	private JLabel labelUsuario;
	private JLabel nombreLabel;
	private JLabel entrenadorLabel;
	private JLabel estadioLabel;
	private JLabel fundacionLabel;
	private JPanel panelDerecho;
	private JLabel titulo;
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private List<Jugador> jugadores;
	private JTable tablaJugadores;
	private BotonRedondeado btnVolver;
	private JButton btnAñadir;

	private static final long serialVersionUID = 1L;

	public VerEquipo(Equipo equipo, String rol, String usuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
		setTitle("LPB Basketball - Detalles del Equipo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000, 650);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		panelIzquierdo = new JPanel();
		panelIzquierdo.setBackground(new Color(255, 243, 205));
		panelIzquierdo.setBounds(0, 0, 500, 613);
		panelIzquierdo.setLayout(null);

		labelUsuario = new JLabel("Usuario: " + usuario);
		labelUsuario.setFont(new Font("SansSerif", Font.PLAIN, 16));
		labelUsuario.setForeground(new Color(0x545454));
		labelUsuario.setBounds(23, 569, 200, 20);
		panelIzquierdo.add(labelUsuario);

		nombreLabel = new JLabel("" + equipo.getNombre());
		nombreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nombreLabel.setFont(new Font("SansSerif", Font.BOLD, 35));
		nombreLabel.setBounds(10, 24, 480, 133);
		nombreLabel.setForeground(new Color(60, 60, 60));
		panelIzquierdo.add(nombreLabel);

		entrenadorLabel = new JLabel("Entrenador: " + equipo.getEntrenador());
		entrenadorLabel.setFont(new Font("SansSerif", Font.PLAIN, 21));
		entrenadorLabel.setBounds(52, 242, 277, 23);
		entrenadorLabel.setForeground(new Color(60, 60, 60));
		panelIzquierdo.add(entrenadorLabel);

		estadioLabel = new JLabel("Estadio: " + equipo.getEstadio());
		estadioLabel.setFont(new Font("SansSerif", Font.PLAIN, 21));
		estadioLabel.setBounds(52, 328, 277, 23);
		estadioLabel.setForeground(new Color(60, 60, 60));
		panelIzquierdo.add(estadioLabel);

		fundacionLabel = new JLabel("Fundación: " + equipo.getFundacion());
		fundacionLabel.setFont(new Font("SansSerif", Font.PLAIN, 21));
		fundacionLabel.setBounds(52, 422, 277, 23);
		fundacionLabel.setForeground(new Color(60, 60, 60));
		panelIzquierdo.add(fundacionLabel);

		getContentPane().add(panelIzquierdo);

		panelDerecho = new JPanel();
		panelDerecho.setBackground(new Color(204, 153, 102));
		panelDerecho.setBounds(500, 0, 487, 613);
		panelDerecho.setLayout(null);

		titulo = new JLabel("Jugadores");
		titulo.setFont(new Font("SansSerif", Font.BOLD, 45));
		titulo.setForeground(new Color(0xfef4c6));
		titulo.setBounds(23, 20, 306, 64);
		panelDerecho.add(titulo);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 105, 442, 349);
		panelDerecho.add(scrollPane);

		String[] nombreColumnas = { "Foto", "Nombre", "Apellido", "Posición", "Dorsal" };
		tableModel = new DefaultTableModel(nombreColumnas, 0);

		jugadores = equipo.getJugadores();
		for (Jugador jugador : jugadores) {
			Object[] row = { jugador.getPhotoPath(), jugador.getNombre(), jugador.getApellidos(),

					jugador.getPosicion(), jugador.getDorsal(), };
			tableModel.addRow(row);
		}

		tablaJugadores = new JTable(tableModel);
		tablaJugadores.setRowHeight(30);
		tablaJugadores.getTableHeader().setReorderingAllowed(false);
		tablaJugadores.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tablaJugadores.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
		scrollPane.setViewportView(tablaJugadores);

		btnVolver = new BotonRedondeado("Volver a Equipos", null);
		btnVolver.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnVolver.setBackground(new Color(64, 64, 64));
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setBounds(285, 542, 170, 40);
		btnVolver.setFocusPainted(false);
		btnVolver.addActionListener(_ -> dispose());
		panelDerecho.add(btnVolver);

		getContentPane().add(panelDerecho);

		btnAñadir = new JButton("Añadir Jugador");
		btnAñadir.setBounds(149, 464, 200, 40);
		panelDerecho.add(btnAñadir);
		btnAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAñadir.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnAñadir.setBackground(new Color(19, 66, 126));
		btnAñadir.setForeground(new Color(0, 0, 160));
	}
}
