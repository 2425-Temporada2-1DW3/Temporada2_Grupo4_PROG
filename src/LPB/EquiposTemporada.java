
package LPB;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.SwingConstants;

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
	private JPanel panelEquipos;

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

		// Define las temporadas
		String[] temporadas = { "Temporada 2023-24", "Temporada 2024-25", "Temporada 2025-26" };

		// Inicializa el JComboBox con las temporadas
		SelectTemporadas = new JComboBox<>(temporadas);
		SelectTemporadas.setBackground(new Color(0x13427e));
		SelectTemporadas.setForeground(Color.WHITE);
		SelectTemporadas.setFont(new Font("SansSerif", Font.PLAIN, 16));
		SelectTemporadas.setBounds(545, 27, 200, 40);

		// Establece el elemento seleccionado a la temporada actual
		SelectTemporadas.setSelectedItem("Temporada 2024-25");

		// Añade el JComboBox al panel
		panelInferior.add(SelectTemporadas);

		btnNuevo = new BotonRedondeado("+", null);
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnNuevo.setBackground(new Color(0x545454));
		btnNuevo.setBounds(755, 22, 50, 50);
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
		panelEquipos = new JPanel(new GridBagLayout());
		panelEquipos.setBorder(null);
		panelEquipos.setBackground(new Color(204, 153, 102));

		JScrollPane scrollPane = new JScrollPane(panelEquipos);
		scrollPane.setBounds(10, 200, 800, 220);
		scrollPane.setBorder(null);
		scrollPane.setVisible(true);

		getContentPane().add(scrollPane);
		getContentPane().add(panelInferior);

		SelectTemporadas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedTemporada = (String) SelectTemporadas.getSelectedItem();
				actualizarPanelEquipos(selectedTemporada);
			}
		});

		actualizarPanelEquipos((String) SelectTemporadas.getSelectedItem());
	}

	private void actualizarPanelEquipos(String temporada) {
		panelEquipos.removeAll();

		String[] nombresEquipos;
		String[] rutaLogo;

		// Define equipos based on the selected season
		switch (temporada) {
		case "Temporada 2023-24":
			nombresEquipos = new String[] { "Brooklyn Nets", "Cleveland Cavaliers", "Detroit Pistons", "NewYork Knicks", "Philadelphia 76ers", "Toronto Raptors" };
			rutaLogo = new String[] { "/imagenes/BrooklynNets50.png", "/imagenes/ClevelandCavaliers50.png", "/imagenes/DetroitPistons50.png",
					"/imagenes/NewYorkKnicks50.png", "/imagenes/Philadelphia76ers50.png", "/imagenes/TorontoRaptors50.png" };
			break;
		case "Temporada 2024-25":
			nombresEquipos = new String[] { "Chicago Bulls", "Golden State Warriors", "Miami Heat", "Boston Celtics",
					"Los Angeles Lakers", "Atlanta Hawks" };
			rutaLogo = new String[] { "/imagenes/ChicagoBulls50.png", "/imagenes/GoldenStateWarriors50.png",
					"/imagenes/MiamiHeat50.png", "/imagenes/BostonCeltics50.png", "/imagenes/LosAngelesLakers50.png",
					"/imagenes/AtlantaHawks50.png" };
			break;
		case "Temporada 2025-26":
			JLabel aviso = new JLabel("La temporada 2025-26 no ha sido empezada.");
			aviso.setFont(new Font("SansSerif", Font.BOLD, 20));
			aviso.setForeground(Color.RED);
			panelEquipos.add(aviso);
			panelEquipos.revalidate();
			panelEquipos.repaint();
			return;
		default:
			nombresEquipos = new String[] {};
			rutaLogo = new String[] {};
			break;
		}

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		for (int i = 0; i < nombresEquipos.length; i++) {
			JPanel equipoPanel = new JPanel(new GridBagLayout());
			equipoPanel.setBackground(new Color(204, 153, 102));

			JButton btnEquipo = new BotonRedondeado(nombresEquipos[i], null);
			btnEquipo.setIcon(new ImageIcon(getClass().getResource(rutaLogo[i])));
			btnEquipo.setFont(new Font("SansSerif", Font.PLAIN, 20));
			btnEquipo.setBackground(new Color(0xf46b20));
			btnEquipo.setHorizontalAlignment(SwingConstants.LEFT);
			btnEquipo.setForeground(Color.WHITE);
			GridBagConstraints gbcBtnEquipo = new GridBagConstraints();
			gbcBtnEquipo.gridx = 0;
			gbcBtnEquipo.gridy = 0;
			gbcBtnEquipo.insets = new Insets(5, 5, 5, 5);
			btnEquipo.setPreferredSize(new Dimension(290, 60));
			equipoPanel.add(btnEquipo, gbcBtnEquipo);

			JButton btnEliminar = new BotonRedondeado("-", null);
			btnEliminar.setFont(new Font("SansSerif", Font.PLAIN, 20));
			btnEliminar.setBackground(new Color(0x545454));
			btnEliminar.setForeground(Color.WHITE);
			GridBagConstraints gbcBtnEliminar = new GridBagConstraints();
			gbcBtnEliminar.gridx = 1;
			gbcBtnEliminar.gridy = 0;
			gbcBtnEliminar.insets = new Insets(5, 5, 5, 5);
			btnEliminar.setPreferredSize(new Dimension(60, 60));
			equipoPanel.add(btnEliminar, gbcBtnEliminar);

			btnEliminar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					panelEquipos.remove(equipoPanel);
					panelEquipos.revalidate();
					panelEquipos.repaint();
					reorganizarEquipos(panelEquipos);
				}
			});

			panelEquipos.add(equipoPanel, gbc);

			if (gbc.gridx == 0) {
				gbc.gridx = 1;
			} else {
				gbc.gridx = 0;
				gbc.gridy++;
			}
		}

		panelEquipos.revalidate();
		panelEquipos.repaint();
	}

	private void reorganizarEquipos(JPanel panelEquipos) {
		Component[] components = panelEquipos.getComponents();
		panelEquipos.removeAll();

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		for (Component component : components) {
			panelEquipos.add(component, gbc);
			if (gbc.gridx == 0) {
				gbc.gridx = 1;
			} else {
				gbc.gridx = 0;
				gbc.gridy++;
			}
		}

		panelEquipos.revalidate();
		panelEquipos.repaint();
	}

	public static void main(String[] args) {
		new EquiposTemporada("Administrador", "Administrador").setVisible(true);
	}
}
