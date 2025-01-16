
package LPB;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import LPBCLASES.BotonRedondeado;

public class EquiposTemporada extends JFrame {

	private static final long serialVersionUID = -2296678961838970996L;
	private JPanel panelSuperior;
	private ImageIcon logo;
	private JLabel labelLogo;
	private JLabel labelUsuario;
	private JPanel panelInferior;
	private JLabel titulo;
	private JButton btnNuevo;
	private JButton btnVolverMenu;
	private JComboBox<String> SelectTemporadas;

	public EquiposTemporada(String rol, String usuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
		setTitle("LPB Basketball - Equipos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(850, 550);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		// Panel superior
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

		// Panel inferior
		panelInferior = new JPanel();
		panelInferior.setBackground(new Color(204, 153, 102));
		panelInferior.setBounds(0, 110, 836, 403);
		panelInferior.setLayout(null);

		labelUsuario = new JLabel("Usuario: " + usuario);
		labelUsuario.setFont(new Font("SansSerif", Font.PLAIN, 16));
		labelUsuario.setForeground(new Color(0x545454));
		labelUsuario.setBounds(20, 360, 200, 20);
		panelInferior.add(labelUsuario);

		SelectTemporadas = new JComboBox<>();
		SelectTemporadas.setBackground(new Color(0x13427e));
		SelectTemporadas.setForeground(Color.WHITE);
		SelectTemporadas.setFont(new Font("SansSerif", Font.PLAIN, 16));
		SelectTemporadas.setBounds(545, 27, 200, 40);
		panelInferior.add(SelectTemporadas);

		btnNuevo = new BotonRedondeado("+", null);
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnNuevo.setBackground(new Color(0x545454));
		btnNuevo.setBounds(755, 25, 45, 45);
		if ("Administrador".equals(rol)) {
			panelInferior.add(btnNuevo);
		}

		btnVolverMenu = new BotonRedondeado("Volver al Menú", null);
		btnVolverMenu.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnVolverMenu.setBackground(new Color(64, 64, 64));
		btnVolverMenu.setForeground(Color.WHITE);
		btnVolverMenu.setBounds(673, 351, 140, 30);
		panelInferior.add(btnVolverMenu);

		btnVolverMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Menu(usuario, rol).setVisible(true);
				dispose();
			}
		});

		// Panel de equipos con JScrollPane
		JPanel panelEquipos = new JPanel(new GridBagLayout());
		panelEquipos.setBorder(null);
		panelEquipos.setBackground(new Color(204, 153, 102));
		JScrollPane scrollPane = new JScrollPane(panelEquipos);
		scrollPane.setBounds(10, 200, 800, 220);
		scrollPane.setBorder(null);
		scrollPane.setVisible(true);

		String[] nombresEquipos = { "Chicago Bulls", "Golden State Warriors", "Miami Heat", "Boston Celtics",
				"Los Angeles Lakers", "Atlanta Hawks" };

		String[] rutaLogo = { "/imagenes/ChicagoBulls50.png", "/imagenes/GoldenStateWarriors50.png",
				"/imagenes/MiamiHeat50.png", "/imagenes/BostonCeltics50.png", "/imagenes/LosAngelesLakers50.png",
				"/imagenes/AtlantaHawks50.png" };

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		for (int i = 0; i < nombresEquipos.length; i++) {
			JPanel equipoPanel = new JPanel(new GridLayout(0, 2, 10, 10));
			equipoPanel.setBackground(new Color(204, 153, 102));

			JButton btnEquipo = new JButton(nombresEquipos[i]);
			btnEquipo.setIcon(new ImageIcon(getClass().getResource(rutaLogo[i])));
			btnEquipo.setFont(new Font("SansSerif", Font.PLAIN, 20));
			btnEquipo.setBackground(new Color(0xf46b20));
			btnEquipo.setForeground(Color.WHITE);
			equipoPanel.add(btnEquipo);

			JButton btnEliminar = new JButton("Eliminar");
			btnEliminar.setFont(new Font("SansSerif", Font.PLAIN, 16));
			btnEliminar.setBackground(new Color(0xff0000));
			btnEliminar.setForeground(Color.WHITE);
			btnEliminar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					panelEquipos.remove(equipoPanel);
					panelEquipos.revalidate();
					panelEquipos.repaint();
				}
			});
			equipoPanel.add(btnEliminar);

			panelEquipos.add(equipoPanel, gbc);
			gbc.gridy++;
		}

		getContentPane().add(scrollPane);
		getContentPane().add(panelInferior);
	}

	public static void main(String[] args) {
		new EquiposTemporada("Administrador", "Administrador").setVisible(true);
	}
}
