
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

public class MenuTemporadas extends JFrame implements MouseListener {
	private static final long serialVersionUID = -1200889095902166795L;
	private JPanel panelIzquierdo;
	private ImageIcon logo;
	private JLabel labelLogo;
	private JLabel labelUsuario;
	private JPanel panelDerecho;
	private JLabel titulo;
	private JLabel subtitulo;
	private JButton btnTemporadas1;
	private JButton btnTemporadas2;
	private JButton btnTemporadas3;
	private JButton btnNuevaTemporada;
	private JButton btnVolverMenu;
	private BackgroundFader fader;

	public MenuTemporadas(String rol, String usuario) {
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

		subtitulo = new JLabel("Seleccione una Temporeda:");
		subtitulo.setForeground(Color.WHITE);
		subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 18));
		subtitulo.setBounds(50, 131, 234, 20);
		panelDerecho.add(subtitulo);

		btnTemporadas1 = new BotonRedondeado("Temporada 23-24", null);
		btnTemporadas1.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnTemporadas1.setBackground(new Color(0xf46b20));
		btnTemporadas1.setForeground(Color.WHITE);
		btnTemporadas1.setBounds(50, 176, 200, 40);
		btnTemporadas1.setFocusPainted(false);
		btnTemporadas1.addMouseListener(this);
		panelDerecho.add(btnTemporadas1);

		btnTemporadas2 = new BotonRedondeado("Temporada 24-25", null);
		btnTemporadas2.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnTemporadas2.setBackground(new Color(0xf46b20));
		btnTemporadas2.setForeground(Color.WHITE);
		btnTemporadas2.setBounds(50, 236, 200, 40);
		btnTemporadas2.setFocusPainted(false);
		btnTemporadas2.addMouseListener(this);
		panelDerecho.add(btnTemporadas2);

		btnTemporadas3 = new BotonRedondeado("Temporada 25-26", null);
		btnTemporadas3.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnTemporadas3.setBackground(new Color(0xf46b20));
		btnTemporadas3.setForeground(Color.WHITE);
		btnTemporadas3.setBounds(50, 296, 200, 40);
		btnTemporadas3.setFocusPainted(false);
		btnTemporadas3.addMouseListener(this);
		panelDerecho.add(btnTemporadas3);

		btnNuevaTemporada = new BotonRedondeado("Nueva Temporada", null);
		btnNuevaTemporada.setForeground(Color.WHITE);
		btnNuevaTemporada.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnNuevaTemporada.setBackground(new Color(0x545454));
		btnNuevaTemporada.setBounds(50, 356, 200, 40);
		btnNuevaTemporada.setFocusPainted(false);
		btnNuevaTemporada.addMouseListener(this);

		if ("Administrador".equals(rol)) {
			panelDerecho.add(btnNuevaTemporada);
		}
		
		btnVolverMenu = new BotonRedondeado("Volver al Menú", null);
		btnVolverMenu.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnVolverMenu.setBackground(new Color(64, 64, 64));
		btnVolverMenu.setForeground(Color.WHITE);
		btnVolverMenu.setBounds(250, 461, 140, 30);
		btnVolverMenu.setFocusPainted(false);
		btnVolverMenu.addMouseListener(this);
		panelDerecho.add(btnVolverMenu);

		btnVolverMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Menu(rol, usuario).setVisible(true);
				dispose();
			}
		});

		getContentPane().add(panelDerecho);
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
		
		if (o == btnTemporadas1) {
			fader.fadeBackground(btnTemporadas1, btnTemporadas1.getBackground(), new Color(0xff7f50));
		} else if (o == btnTemporadas2) {
			fader.fadeBackground(btnTemporadas2, btnTemporadas2.getBackground(), new Color(0xff7f50));
		} else if (o == btnTemporadas3) {
			fader.fadeBackground(btnTemporadas3, btnTemporadas3.getBackground(), new Color(0xff7f50));
		} else if (o == btnNuevaTemporada) {
			fader.fadeBackground(btnNuevaTemporada, btnNuevaTemporada.getBackground(), new Color(0x6a6a6a));
		} else if (o == btnVolverMenu) {
			fader.fadeBackground(btnVolverMenu, btnVolverMenu.getBackground(), new Color(0x646464));
		}
		
	}

	@Override
	public void mouseExited(MouseEvent ae) {
		Object o = ae.getSource();

		if (o == btnTemporadas1) {
			fader.fadeBackground(btnTemporadas1, btnTemporadas1.getBackground(), new Color(0xf46b20));
		} else if (o == btnTemporadas2) {
			fader.fadeBackground(btnTemporadas2, btnTemporadas2.getBackground(), new Color(0xf46b20));
		} else if (o == btnTemporadas3) {
			fader.fadeBackground(btnTemporadas3, btnTemporadas3.getBackground(), new Color(0xf46b20));
		} else if (o == btnNuevaTemporada) {
			fader.fadeBackground(btnNuevaTemporada, btnNuevaTemporada.getBackground(), new Color(0x545454));
		} else if (o == btnVolverMenu) {
			fader.fadeBackground(btnVolverMenu, btnVolverMenu.getBackground(), new Color(0x404040));
		}
		
	}

	
}
