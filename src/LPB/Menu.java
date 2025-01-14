package LPB;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import LPBCLASES.BackgroundFader;
import LPBCLASES.BotonRedondeado;
import LPBCLASES.Usuario.Rol;

public class Menu extends JFrame implements MouseListener {
	private static final long serialVersionUID = -1200889095902166795L;
	private JButton btnTemporadas;
	private JButton btnEquipos;
	private JButton btnUsuarios;
	private JButton btnCerrarSesion;
	private JPanel panelIzquierdo;
	private JLabel labelLogo;
	private JLabel labelUsuario;
	private JPanel panelDerecho;
	private JLabel titulo;
	private JLabel subtitulo;
	private ImageIcon logo;
	private BackgroundFader fader;

	public Menu(Rol rol, String usuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
		setTitle("LPB Basketball - Menú");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(850, 550);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		fader = new BackgroundFader();

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

		btnTemporadas = new BotonRedondeado("Temporadas");
		btnTemporadas.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnTemporadas.setBackground(new Color(0x13427E));
		btnTemporadas.setForeground(Color.WHITE);
		btnTemporadas.setBounds(50, 191, 200, 40);
		btnTemporadas.addMouseListener(this);
		panelDerecho.add(btnTemporadas);
		
		btnTemporadas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MenuTemporadas(usuario, usuario).setVisible(true);
				dispose();
			}
		});

		btnEquipos = new BotonRedondeado("Equipos");
		btnEquipos.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnEquipos.setBackground(new Color(0xf46b20));
		btnEquipos.setForeground(Color.WHITE);
		btnEquipos.setBounds(50, 251, 200, 40);
		btnEquipos.addMouseListener(this);
		panelDerecho.add(btnEquipos);

		btnUsuarios = new BotonRedondeado("Usuarios");
		btnUsuarios.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnUsuarios.setBackground(new Color(0x545454));
		btnUsuarios.setForeground(Color.WHITE);
		btnUsuarios.setBounds(50, 311, 200, 40);
		btnUsuarios.addMouseListener(this);

		if ("Administrador".equals(rol)) {
			panelDerecho.add(btnUsuarios);
		}

		btnCerrarSesion = new BotonRedondeado("Cerrar Sesión");
		btnCerrarSesion.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnCerrarSesion.setBackground(new Color(64, 64, 64));
		btnCerrarSesion.setForeground(Color.WHITE);
		btnCerrarSesion.setBounds(250, 461, 140, 30);
		btnCerrarSesion.addMouseListener(this);
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

	public Menu(String string, String usuario) {
		// TODO Auto-generated constructor stub
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
	public void mouseEntered(MouseEvent ae) {
		Object o = ae.getSource();

		if (o == btnTemporadas) {
			fader.fadeBackground(btnTemporadas, btnTemporadas.getBackground(), new Color(0x1a5bae));
		} else if (o == btnEquipos) {
			fader.fadeBackground(btnEquipos, btnEquipos.getBackground(), new Color(0xff7f50));
		} else if (o == btnUsuarios) {
			fader.fadeBackground(btnUsuarios, btnUsuarios.getBackground(), new Color(0x6a6a6a));
		} else if (o == btnCerrarSesion) {
			fader.fadeBackground(btnCerrarSesion, btnCerrarSesion.getBackground(), new Color(0x646464));
		}
	}

	@Override
	public void mouseExited(MouseEvent ae) {
		Object o = ae.getSource();

		if (o == btnTemporadas) {
			fader.fadeBackground(btnTemporadas, btnTemporadas.getBackground(), new Color(0x13427E));
		} else if (o == btnEquipos) {
			fader.fadeBackground(btnEquipos, btnEquipos.getBackground(), new Color(0xf46b20));
		} else if (o == btnUsuarios) {
			fader.fadeBackground(btnUsuarios, btnUsuarios.getBackground(), new Color(0x545454));
		} else if (o == btnCerrarSesion) {
			fader.fadeBackground(btnCerrarSesion, btnCerrarSesion.getBackground(), new Color(0x404040));
		}
	}
}
