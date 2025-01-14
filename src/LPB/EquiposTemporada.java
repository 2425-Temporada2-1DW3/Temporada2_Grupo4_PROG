
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import LPBCLASES.BackgroundFader;
import LPBCLASES.BotonRedondeado;

public class EquiposTemporada extends JFrame implements MouseListener {
	private static final long serialVersionUID = -1200889095902166795L;
	private JPanel panelSuperior;
	private ImageIcon logo;
	private JLabel labelLogo;
	private JLabel labelUsuario;
	private JPanel panelInferior;
	private JLabel titulo;
	private JButton btnEquipo1;
	private JButton btnEquipo2;
	private JButton btnEquipo3;
	private JButton btnEquipo4;
	private JButton btnEquipo5;
	private JButton btnEquipo6;
	private JButton btnEliminar1;
	private JButton btnEliminar2;
	private JButton btnEliminar3;
	private JButton btnEliminar4;
	private JButton btnEliminar5;
	private JButton btnEliminar6;
	private JButton btnNuevo;
	private JButton btnVolverMenu;
	private BackgroundFader fader;
	private JComboBox<Object> SelectTemporadas;

	public EquiposTemporada(String rol, String usuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
		setTitle("LPB Basketball - Equipos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(850, 550);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		fader = new BackgroundFader();

		panelSuperior = new JPanel();
		panelSuperior.setBackground(new Color(255, 243, 205));
		panelSuperior.setBounds(0, 0, 836, 110);
		panelSuperior.setLayout(null);

		logo = new ImageIcon(getClass().getResource("/imagenes/logo150.png"));
		labelLogo = new JLabel(logo);
		labelLogo.setBounds(686, -20, 150, 150);
		panelSuperior.add(labelLogo);
		getContentPane().add(panelSuperior);

		titulo = new JLabel("Equipos");
		titulo.setBounds(10, 28, 306, 47);
		titulo.setFont(new Font("SansSerif", Font.BOLD, 50));
		titulo.setForeground(new Color(0x13427e));
		panelSuperior.add(titulo);

		panelInferior = new JPanel();
		panelInferior.setBackground(new Color(204, 153, 102));
		panelInferior.setBounds(0, 110, 836, 403);
		panelInferior.setLayout(null);

		labelUsuario = new JLabel("Usuario: " + usuario);
		labelUsuario.setFont(new Font("SansSerif", Font.PLAIN, 16));
		labelUsuario.setForeground(new Color(0x545454));
		labelUsuario.setBounds(20, 360, 200, 20);
		panelInferior.add(labelUsuario);

		SelectTemporadas = new JComboBox<Object>();
		SelectTemporadas.setBackground(new Color(0x13427e));
		SelectTemporadas.setForeground(Color.black);
		SelectTemporadas.setFont(new Font("SansSerif", Font.PLAIN, 16));
		SelectTemporadas.setBounds(545, 27, 200, 40);
		SelectTemporadas.addItem("Temporada 23-24");
		SelectTemporadas.addItem("Temporada 24-25");
		SelectTemporadas.addItem("Temporada 25-26");
		panelInferior.add(SelectTemporadas);

		btnNuevo = new BotonRedondeado("+", null);
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnNuevo.setBackground(new Color(0x545454));
		btnNuevo.setBounds(755, 27, 45, 40);

		if ("Administrador".equals(rol)) {
			panelInferior.add(btnNuevo);
		}

		ImageIcon icon1 = new ImageIcon(getClass().getResource("/imagenes/BostonCeltics50.png"));
		btnEquipo1 = new BotonRedondeado("Boston Celtics", icon1);
		btnEquipo1.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnEquipo1.setBackground(new Color(0xf46b20));
		btnEquipo1.setForeground(Color.WHITE);
		btnEquipo1.setBounds(97, 118, 146, 50);
		btnEquipo1.addMouseListener(this);
		panelInferior.add(btnEquipo1);

		ImageIcon icon2 = new ImageIcon(getClass().getResource("/imagenes/AtlantaHawks50.png"));
		btnEquipo2 = new BotonRedondeado("Atlanta Hawks", icon2);
		btnEquipo2.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnEquipo2.setBackground(new Color(0xf46b20));
		btnEquipo2.setForeground(Color.WHITE);
		btnEquipo2.setBounds(97, 178, 255, 50);
		btnEquipo2.addMouseListener(this);
		panelInferior.add(btnEquipo2);

		ImageIcon icon3 = new ImageIcon(getClass().getResource("/imagenes/MiamiHeat50.png"));
		btnEquipo3 = new BotonRedondeado("Miami Heat", icon3);
		btnEquipo3.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnEquipo3.setBackground(new Color(0xf46b20));
		btnEquipo3.setForeground(Color.WHITE);
		btnEquipo3.setBounds(97, 238, 255, 50);
		btnEquipo3.addMouseListener(this);
		btnEquipo3.addMouseListener(this);
		panelInferior.add(btnEquipo3);

		ImageIcon icon4 = new ImageIcon(getClass().getResource("/imagenes/ChicagoBulls50.png"));
		btnEquipo4 = new BotonRedondeado("Chicago Bulls", icon4);
		btnEquipo4.setForeground(Color.WHITE);
		btnEquipo4.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnEquipo4.setBackground(new Color(244, 107, 32));
		btnEquipo4.setBounds(478, 118, 200, 50);
		btnEquipo4.addMouseListener(this);
		panelInferior.add(btnEquipo4);

		ImageIcon icon5 = new ImageIcon(getClass().getResource("/imagenes/GoldenStateWarriors50.png"));
		btnEquipo5 = new BotonRedondeado("Golden State Warriors", icon5);
		btnEquipo5.setForeground(Color.WHITE);
		btnEquipo5.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnEquipo5.setBackground(new Color(244, 107, 32));
		btnEquipo5.setBounds(478, 178, 255, 50);
		btnEquipo5.addMouseListener(this);
		panelInferior.add(btnEquipo5);

		ImageIcon icon6 = new ImageIcon(getClass().getResource("/imagenes/LosAngelesLakers50.png"));
		btnEquipo6 = new BotonRedondeado("Los Angeles Lakers", icon6);
		btnEquipo6.setForeground(Color.WHITE);
		btnEquipo6.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnEquipo6.setBackground(new Color(244, 107, 32));
		btnEquipo6.setBounds(478, 238, 200, 50);
		btnEquipo6.addMouseListener(this);
		panelInferior.add(btnEquipo6);

		btnEliminar1 = new BotonRedondeado("-", null);
		btnEliminar1.setForeground(Color.WHITE);
		btnEliminar1.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnEliminar1.setBackground(new Color(0x545454));
		btnEliminar1.setBounds(352, 118, 50, 50);
		btnEliminar1.addMouseListener(this);

//		if ("Administrador".equals(rol)) {
			panelInferior.add(btnEliminar1);
//		}

		btnEliminar2 = new BotonRedondeado("-", null);
		btnEliminar2.setForeground(Color.WHITE);
		btnEliminar2.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnEliminar2.setBackground(new Color(0x545454));
		btnEliminar2.setBounds(362, 208, 40, 40);
		btnEliminar2.addMouseListener(this);

//		if ("Administrador".equals(rol)) {
			panelInferior.add(btnEliminar2);
//		}

		btnEliminar3 = new BotonRedondeado("-", null);
		btnEliminar3.setForeground(Color.WHITE);
		btnEliminar3.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnEliminar3.setBackground(new Color(0x545454));
		btnEliminar3.setBounds(362, 273, 40, 40);
		btnEliminar3.addMouseListener(this);

//		if ("Administrador".equals(rol)) {
			panelInferior.add(btnEliminar3);
//		}

		btnEliminar4 = new BotonRedondeado("-", null);
		btnEliminar4.setForeground(Color.WHITE);
		btnEliminar4.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnEliminar4.setBackground(new Color(0x545454));
		btnEliminar4.setBounds(727, 118, 50, 50);
		btnEliminar4.addMouseListener(this);

//		if ("Administrador".equals(rol)) {
			panelInferior.add(btnEliminar4);
//		}

		btnEliminar5 = new BotonRedondeado("-", null);
		btnEliminar5.setForeground(Color.WHITE);
		btnEliminar5.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnEliminar5.setBackground(new Color(0x545454));
		btnEliminar5.setBounds(737, 208, 40, 40);
		btnEliminar5.addMouseListener(this);

//		if ("Administrador".equals(rol)) {
			panelInferior.add(btnEliminar5);
//		}

		btnEliminar6 = new BotonRedondeado("-", null);
		btnEliminar6.setForeground(Color.WHITE);
		btnEliminar6.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnEliminar6.setBackground(new Color(0x545454));
		btnEliminar6.setBounds(737, 273, 40, 40);
		btnEliminar6.addMouseListener(this);

//		if ("Administrador".equals(rol)) {
			panelInferior.add(btnEliminar6);
//		}

		btnVolverMenu = new BotonRedondeado("Volver al Menú", null);
		btnVolverMenu.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnVolverMenu.setBackground(new Color(64, 64, 64));
		btnVolverMenu.setForeground(Color.WHITE);
		btnVolverMenu.setBounds(673, 351, 140, 30);
		btnVolverMenu.addMouseListener(this);
		panelInferior.add(btnVolverMenu);

		btnVolverMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Menu(usuario, usuario).setVisible(true);
				dispose();
			}
		});

		getContentPane().add(panelInferior);
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

		if (o == btnNuevo) {
			fader.fadeBackground(btnNuevo, btnNuevo.getBackground(), new Color(0x6a6a6a));
		} else if (o == btnEquipo1) {
			fader.fadeBackground(btnEquipo1, btnEquipo1.getBackground(), new Color(0xff7f50));
		} else if (o == btnEquipo2) {
			fader.fadeBackground(btnEquipo2, btnEquipo2.getBackground(), new Color(0xff7f50));
		} else if (o == btnEquipo3) {
			fader.fadeBackground(btnEquipo3, btnEquipo3.getBackground(), new Color(0xff7f50));
		} else if (o == btnEquipo4) {
			fader.fadeBackground(btnEquipo4, btnEquipo4.getBackground(), new Color(0xff7f50));
		} else if (o == btnEquipo5) {
			fader.fadeBackground(btnEquipo5, btnEquipo5.getBackground(), new Color(0xff7f50));
		} else if (o == btnEquipo6) {
			fader.fadeBackground(btnEquipo6, btnEquipo6.getBackground(), new Color(0xff7f50));
		} else if (o == btnEliminar1) {
			fader.fadeBackground(btnEliminar1, btnEliminar1.getBackground(), new Color(0x6a6a6a));
		} else if (o == btnEliminar2) {
			fader.fadeBackground(btnEliminar2, btnEliminar2.getBackground(), new Color(0x6a6a6a));
		} else if (o == btnEliminar3) {
			fader.fadeBackground(btnEliminar3, btnEliminar3.getBackground(), new Color(0x6a6a6a));
		} else if (o == btnEliminar4) {
			fader.fadeBackground(btnEliminar4, btnEliminar4.getBackground(), new Color(0x6a6a6a));
		} else if (o == btnEliminar5) {
			fader.fadeBackground(btnEliminar5, btnEliminar5.getBackground(), new Color(0x6a6a6a));
		} else if (o == btnEliminar6) {
			fader.fadeBackground(btnEliminar6, btnEliminar6.getBackground(), new Color(0x6a6a6a));
		} else if (o == btnVolverMenu) {
			fader.fadeBackground(btnVolverMenu, btnVolverMenu.getBackground(), new Color(0x646464));
		}

	}

	@Override
	public void mouseExited(MouseEvent ae) {
		Object o = ae.getSource();

		if (o == btnNuevo) {
			fader.fadeBackground(btnNuevo, btnNuevo.getBackground(), new Color(0x545454));
		} else if (o == btnEquipo1) {
			fader.fadeBackground(btnEquipo1, btnEquipo1.getBackground(), new Color(0xf46b20));
		} else if (o == btnEquipo2) {
			fader.fadeBackground(btnEquipo2, btnEquipo2.getBackground(), new Color(0xf46b20));
		} else if (o == btnEquipo3) {
			fader.fadeBackground(btnEquipo3, btnEquipo3.getBackground(), new Color(0xf46b20));
		} else if (o == btnEquipo4) {
			fader.fadeBackground(btnEquipo4, btnEquipo4.getBackground(), new Color(0xf46b20));
		} else if (o == btnEquipo5) {
			fader.fadeBackground(btnEquipo5, btnEquipo5.getBackground(), new Color(0xf46b20));
		} else if (o == btnEquipo6) {
			fader.fadeBackground(btnEquipo6, btnEquipo6.getBackground(), new Color(0xf46b20));
		} else if (o == btnEliminar1) {
			fader.fadeBackground(btnEliminar1, btnEliminar1.getBackground(), new Color(0x545454));
		} else if (o == btnEliminar2) {
			fader.fadeBackground(btnEliminar2, btnEliminar2.getBackground(), new Color(0x545454));
		} else if (o == btnEliminar3) {
			fader.fadeBackground(btnEliminar3, btnEliminar3.getBackground(), new Color(0x545454));
		} else if (o == btnEliminar4) {
			fader.fadeBackground(btnEliminar4, btnEliminar4.getBackground(), new Color(0x545454));
		} else if (o == btnEliminar5) {
			fader.fadeBackground(btnEliminar5, btnEliminar5.getBackground(), new Color(0x545454));
		} else if (o == btnEliminar6) {
			fader.fadeBackground(btnEliminar6, btnEliminar6.getBackground(), new Color(0x545454));
		} else if (o == btnVolverMenu) {
			fader.fadeBackground(btnVolverMenu, btnVolverMenu.getBackground(), new Color(0x404040));
		}
	}
}
