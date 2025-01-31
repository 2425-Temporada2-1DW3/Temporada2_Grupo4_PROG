package LPB;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import LPBCLASES.BotonRedondeado;

public class Menu extends JFrame {
	private static final long serialVersionUID = -1200889095902166795L;
	private JButton btnTemporadas;
	private JButton btnEquipos;
	private JButton btnUsuarios;
	private JButton btnJugadores;
	private JButton btnCerrarSesion;
	private JPanel panelIzquierdo;
	private JLabel labelLogo;
	private JLabel labelUsuario;
	private JPanel panelDerecho;
	private JLabel titulo;
	private JLabel subtitulo;
	private ImageIcon logo;

	
	

	/**
	 * Create the frame.
	 */
	public Menu(String rol, String usuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
		setTitle("LPB Basketball - Menú");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(850, 550);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		panelIzquierdo = new JPanel();
		panelIzquierdo.setBackground(new Color(255, 243, 205));
		panelIzquierdo.setBounds(0, 0, 425, 513);
		panelIzquierdo.setLayout(null);

		logo = new ImageIcon(getClass().getResource("/imagenes/logo500.png"));
		labelLogo = new JLabel(logo);
		labelLogo.setBounds(10, 55, 400, 400);
		panelIzquierdo.add(labelLogo);

		labelUsuario = new JLabel("Usuario: " + usuario);
		labelUsuario.setFont(new Font("SansSerif", Font.PLAIN, 16));
		labelUsuario.setForeground(new Color(0x545454));
		labelUsuario.setBounds(20, 470, 200, 20);
		panelIzquierdo.add(labelUsuario);

		getContentPane().add(panelIzquierdo);

		panelDerecho = new JPanel();
		panelDerecho.setBackground(new Color(204, 153, 102));
		panelDerecho.setBounds(425, 0, 411, 513);
		panelDerecho.setLayout(null);

		titulo = new JLabel("Menú");
		titulo.setFont(new Font("SansSerif", Font.BOLD, 50));
		titulo.setForeground(new Color(255, 243, 205));
		titulo.setBounds(50, 40, 200, 47);
		panelDerecho.add(titulo);

		subtitulo = new JLabel("Seleccione una opción:");
		subtitulo.setForeground(Color.WHITE);
		subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 18));
		subtitulo.setBounds(50, 146, 200, 20);
		panelDerecho.add(subtitulo);

		btnTemporadas = new BotonRedondeado("Temporadas", null);
		btnTemporadas.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnTemporadas.setBackground(new Color(0x13427E));
		btnTemporadas.setForeground(Color.WHITE);
		btnTemporadas.setBounds(50, 191, 200, 40);
		btnTemporadas.setFocusPainted(false);
		panelDerecho.add(btnTemporadas);
		
		btnTemporadas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MenuTemporadas(rol, usuario).setVisible(true);
				dispose();
			}
		});

		btnEquipos = new BotonRedondeado("Equipos", null);
		btnEquipos.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnEquipos.setBackground(new Color(0xf46b20));
		btnEquipos.setForeground(Color.WHITE);
		btnEquipos.setBounds(50, 251, 200, 40);
		btnEquipos.setFocusPainted(false);
		panelDerecho.add(btnEquipos);
		
		btnEquipos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EquiposTemporada(rol, usuario).setVisible(true);
				dispose();
			}
		});
		
		btnJugadores = new BotonRedondeado("Jugadores", null);		
		btnJugadores.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnJugadores.setBackground(new Color(0xfff3cc));
		btnJugadores.setForeground(new Color(0x535353));
		btnJugadores.setBounds(50, 311, 200, 40);
		btnJugadores.setFocusPainted(false);
		
		btnJugadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MenuJugadores().setVisible(true);
				dispose();
			}
		});

		if (rol.equals("Administrador")) {
			panelDerecho.add(btnJugadores);
		}

		btnUsuarios = new BotonRedondeado("Usuarios", null);
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MenuUsuarios().setVisible(true);
				dispose();
			}
		});
		
		btnUsuarios.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnUsuarios.setBackground(new Color(0x545454));
		btnUsuarios.setForeground(Color.WHITE);
		btnUsuarios.setBounds(50, 371, 200, 40);
		btnUsuarios.setFocusPainted(false);

		if (rol.equals("Administrador")) {
			panelDerecho.add(btnUsuarios);
		}

		btnCerrarSesion = new BotonRedondeado("Cerrar Sesión", null);
		btnCerrarSesion.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnCerrarSesion.setBackground(new Color(64, 64, 64));
		btnCerrarSesion.setForeground(Color.WHITE);
		btnCerrarSesion.setBounds(250, 461, 140, 30);
		btnCerrarSesion.setFocusPainted(false);
		panelDerecho.add(btnCerrarSesion);

		btnCerrarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Login().setVisible(true);
				dispose();
			}
		});

		getContentPane().add(panelDerecho);
	}
}