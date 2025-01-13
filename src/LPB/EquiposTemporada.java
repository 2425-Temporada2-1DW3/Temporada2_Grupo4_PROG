
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
import javax.swing.JComboBox;

public class EquiposTemporada extends JFrame implements MouseListener {
	private static final long serialVersionUID = -1200889095902166795L;
	private JPanel panelSuperior;
	private ImageIcon logo;
	private JLabel labelLogo;
	private JLabel labelUsuario;
	private JPanel panelInferior;
	private JLabel titulo;
	private JButton btnTemporadas1;
	private JButton btnTemporadas2;
	private JButton btnTemporadas3;
	private JButton btnNuevaTemporada;
	private JButton btnVolverMenu;
	private BackgroundFader fader;
	private JComboBox<Object> comboBox;
	private BotonRedondeado btnTemporadas1_1;

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
		labelLogo.setBounds(736, 0, 100, 100);
		panelSuperior.add(labelLogo);
		getContentPane().add(panelSuperior);

		labelUsuario = new JLabel("Usuario: " + usuario);
		labelUsuario.setFont(new Font("SansSerif", Font.PLAIN, 16));
		labelUsuario.setForeground(new Color(0x545454));
		labelUsuario.setBounds(20, 470, 200, 20);
		panelSuperior.add(labelUsuario);
		
				titulo = new JLabel("Equipos");
				titulo.setBounds(10, 28, 306, 47);
				panelSuperior.add(titulo);
				titulo.setFont(new Font("SansSerif", Font.BOLD, 50));
				titulo.setForeground(new Color(0x13427e));

		panelInferior = new JPanel();
		panelInferior.setBackground(new Color(204, 153, 102));
		panelInferior.setBounds(0, 110, 836, 403);
		panelInferior.setLayout(null);

		btnTemporadas1 = new BotonRedondeado("Temporada 23-24");
		btnTemporadas1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTemporadas1.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnTemporadas1.setBackground(new Color(0xf46b20));
		btnTemporadas1.setForeground(Color.WHITE);
		btnTemporadas1.setBounds(82, 138, 200, 40);
		btnTemporadas1.addMouseListener(this);
		panelInferior.add(btnTemporadas1);

		btnTemporadas2 = new BotonRedondeado("Temporada 24-25");
		btnTemporadas2.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnTemporadas2.setBackground(new Color(0xf46b20));
		btnTemporadas2.setForeground(Color.WHITE);
		btnTemporadas2.setBounds(82, 198, 200, 40);
		btnTemporadas2.addMouseListener(this);
		panelInferior.add(btnTemporadas2);

		btnTemporadas3 = new BotonRedondeado("Temporada 25-26");
		btnTemporadas3.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnTemporadas3.setBackground(new Color(0xf46b20));
		btnTemporadas3.setForeground(Color.WHITE);
		btnTemporadas3.setBounds(82, 258, 200, 40);
		btnTemporadas3.addMouseListener(this);
		panelInferior.add(btnTemporadas3);

		btnNuevaTemporada = new BotonRedondeado("Nueva Temporada");
		btnNuevaTemporada.setForeground(Color.WHITE);
		btnNuevaTemporada.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnNuevaTemporada.setBackground(new Color(0x545454));
		btnNuevaTemporada.setBounds(50, 356, 200, 40);
		btnNuevaTemporada.addMouseListener(this);

		if ("Administrador".equals(rol)) {
			panelInferior.add(btnNuevaTemporada);
		}
		
		btnVolverMenu = new BotonRedondeado("Volver al Menú");
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
		
		comboBox = new JComboBox<Object>();
		comboBox.setBounds(545, 27, 200, 40);
		panelInferior.add(comboBox);
		
		btnTemporadas1_1 = new BotonRedondeado("Temporada 25-26");
		btnTemporadas1_1.setForeground(Color.WHITE);
		btnTemporadas1_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnTemporadas1_1.setBackground(new Color(244, 107, 32));
		btnTemporadas1_1.setBounds(463, 138, 200, 40);
		panelInferior.add(btnTemporadas1_1);
		
		BotonRedondeado btnTemporadas2_1 = new BotonRedondeado("Temporada 24-25");
		btnTemporadas2_1.setForeground(Color.WHITE);
		btnTemporadas2_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnTemporadas2_1.setBackground(new Color(244, 107, 32));
		btnTemporadas2_1.setBounds(463, 198, 200, 40);
		panelInferior.add(btnTemporadas2_1);
		
		BotonRedondeado btnTemporadas3_1 = new BotonRedondeado("Temporada 25-26");
		btnTemporadas3_1.setForeground(Color.WHITE);
		btnTemporadas3_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnTemporadas3_1.setBackground(new Color(244, 107, 32));
		btnTemporadas3_1.setBounds(463, 258, 200, 40);
		panelInferior.add(btnTemporadas3_1);
		
		BotonRedondeado btnTemporadas1_1_1 = new BotonRedondeado("Temporada 23-24");
		btnTemporadas1_1_1.setText("-");
		btnTemporadas1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTemporadas1_1_1.setForeground(Color.WHITE);
		btnTemporadas1_1_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnTemporadas1_1_1.setBackground(new Color(244, 107, 32));
		btnTemporadas1_1_1.setBounds(292, 138, 40, 40);
		panelInferior.add(btnTemporadas1_1_1);
		
		BotonRedondeado btnTemporadas2_1_1 = new BotonRedondeado("Temporada 24-25");
		btnTemporadas2_1_1.setText("-");
		btnTemporadas2_1_1.setForeground(Color.WHITE);
		btnTemporadas2_1_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnTemporadas2_1_1.setBackground(new Color(244, 107, 32));
		btnTemporadas2_1_1.setBounds(292, 198, 40, 40);
		panelInferior.add(btnTemporadas2_1_1);
		
		BotonRedondeado btnTemporadas3_1_1 = new BotonRedondeado("Temporada 25-26");
		btnTemporadas3_1_1.setText("-");
		btnTemporadas3_1_1.setForeground(Color.WHITE);
		btnTemporadas3_1_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnTemporadas3_1_1.setBackground(new Color(244, 107, 32));
		btnTemporadas3_1_1.setBounds(292, 258, 40, 40);
		panelInferior.add(btnTemporadas3_1_1);
		
		BotonRedondeado btnTemporadas1_1_1_1 = new BotonRedondeado("Temporada 23-24");
		btnTemporadas1_1_1_1.setText("-");
		btnTemporadas1_1_1_1.setForeground(Color.WHITE);
		btnTemporadas1_1_1_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnTemporadas1_1_1_1.setBackground(new Color(244, 107, 32));
		btnTemporadas1_1_1_1.setBounds(673, 138, 40, 40);
		panelInferior.add(btnTemporadas1_1_1_1);
		
		BotonRedondeado btnTemporadas2_1_1_1 = new BotonRedondeado("Temporada 24-25");
		btnTemporadas2_1_1_1.setText("-");
		btnTemporadas2_1_1_1.setForeground(Color.WHITE);
		btnTemporadas2_1_1_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnTemporadas2_1_1_1.setBackground(new Color(244, 107, 32));
		btnTemporadas2_1_1_1.setBounds(673, 198, 40, 40);
		panelInferior.add(btnTemporadas2_1_1_1);
		
		BotonRedondeado btnTemporadas3_1_1_1 = new BotonRedondeado("Temporada 25-26");
		btnTemporadas3_1_1_1.setText("-");
		btnTemporadas3_1_1_1.setForeground(Color.WHITE);
		btnTemporadas3_1_1_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnTemporadas3_1_1_1.setBackground(new Color(244, 107, 32));
		btnTemporadas3_1_1_1.setBounds(673, 258, 40, 40);
		panelInferior.add(btnTemporadas3_1_1_1);
		
		BotonRedondeado btnTemporadas1_1_1_1_1 = new BotonRedondeado("Temporada 23-24");
		btnTemporadas1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTemporadas1_1_1_1_1.setText("+");
		btnTemporadas1_1_1_1_1.setForeground(Color.WHITE);
		btnTemporadas1_1_1_1_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnTemporadas1_1_1_1_1.setBackground(new Color(244, 107, 32));
		btnTemporadas1_1_1_1_1.setBounds(755, 27, 45, 40);
		panelInferior.add(btnTemporadas1_1_1_1_1);
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
