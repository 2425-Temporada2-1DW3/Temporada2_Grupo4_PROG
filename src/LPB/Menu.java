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

	public Menu(String rol, String usuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/imagenes/basketball.png"));
		setTitle("LPB Basketball - Menú");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(850, 550);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JPanel panelIzquierdo = new JPanel();
		panelIzquierdo.setBackground(new Color(255, 243, 205));
		panelIzquierdo.setBounds(0, 0, 425, 513);
		panelIzquierdo.setLayout(null);

		ImageIcon logo = new ImageIcon("src/imagenes/logo500.png");
		JLabel labelLogo = new JLabel(logo);
		labelLogo.setBounds(10, 55, 400, 400);
		panelIzquierdo.add(labelLogo);
		getContentPane().add(panelIzquierdo);

		JLabel labelUsuario = new JLabel("Usuario: " + usuario);
		labelUsuario.setFont(new Font("SansSerif", Font.PLAIN, 16));
		labelUsuario.setForeground(new Color(0x545454));
		labelUsuario.setBounds(20, 470, 200, 20);
		panelIzquierdo.add(labelUsuario);

		JPanel panelDerecho = new JPanel();
		panelDerecho.setBackground(new Color(204, 153, 102));
		panelDerecho.setBounds(425, 0, 411, 513);
		panelDerecho.setLayout(null);

		JLabel titulo = new JLabel("Menú");
		titulo.setFont(new Font("SansSerif", Font.BOLD, 50));
		titulo.setForeground(new Color(255, 243, 205));
		titulo.setBounds(50, 40, 200, 47);
		panelDerecho.add(titulo);

		JLabel subtitulo = new JLabel("Seleccione una opción:");
		subtitulo.setForeground(Color.WHITE);
		subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 18));
		subtitulo.setBounds(50, 146, 200, 20);
		panelDerecho.add(subtitulo);

		JButton btnTemporadas = new BotonRedondeado("Temporadas");
		btnTemporadas.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnTemporadas.setBackground(new Color(0x13427E));
		btnTemporadas.setForeground(Color.WHITE);
		btnTemporadas.setBounds(50, 191, 200, 40);
		panelDerecho.add(btnTemporadas);

		btnTemporadas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MenuTemporadas(usuario, usuario).setVisible(true);
				dispose();
			}
		});

		JButton btnEquipos = new BotonRedondeado("Equipos");
		btnEquipos.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnEquipos.setBackground(new Color(0xf46b20));
		btnEquipos.setForeground(Color.WHITE);
		btnEquipos.setBounds(50, 251, 200, 40);
		panelDerecho.add(btnEquipos);

		JButton btnUsuarios = new BotonRedondeado("Usuarios");
		btnUsuarios.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnUsuarios.setBackground(new Color(0x545454));
		btnUsuarios.setForeground(Color.WHITE);
		btnUsuarios.setBounds(50, 311, 200, 40);

		if (!"Administrador".equals(rol)) {
			btnUsuarios.setEnabled(false);
		}

		panelDerecho.add(btnUsuarios);

		JButton btnCerrarSesion = new BotonRedondeado("Cerrar Sesión");
		btnCerrarSesion.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnCerrarSesion.setBackground(new Color(64, 64, 64));
		btnCerrarSesion.setForeground(Color.WHITE);
		btnCerrarSesion.setBounds(250, 461, 140, 30);
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
