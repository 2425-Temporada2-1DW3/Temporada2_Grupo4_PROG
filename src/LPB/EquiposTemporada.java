
package LPB;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
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
	private JButton btnNuevo;
	private JButton btnVolverMenu;
	private BackgroundFader fader;
	private JComboBox<String> SelectTemporadas;
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

		SelectTemporadas = new JComboBox<>();
		SelectTemporadas.setBackground(new Color(0x13427e));
		SelectTemporadas.setForeground(Color.black);
		SelectTemporadas.setFont(new Font("SansSerif", Font.PLAIN, 16));
		SelectTemporadas.setBounds(545, 27, 200, 40);
		cargarTemporadas();
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
		btnVolverMenu.addMouseListener(this);
		panelInferior.add(btnVolverMenu);

		btnVolverMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Menu(usuario, rol).setVisible(true);
				dispose();
			}
		});

		getContentPane().add(panelInferior);

		SelectTemporadas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String temporadaSeleccionada = (String) SelectTemporadas.getSelectedItem();
				generarEquiposPorTemporada(temporadaSeleccionada, rol);
			}
		});

		for (int i = 0; i < SelectTemporadas.getItemCount(); i++) {
			if ("Temporada 2024-25".equals(SelectTemporadas.getItemAt(i))) {
				SelectTemporadas.setSelectedIndex(i);
				generarEquiposPorTemporada((String) SelectTemporadas.getSelectedItem(), rol);
				break;
			}
		}
	}

	private void cargarTemporadas() {
		int[] años = { 2023, 2024, 2025 };
		for (int año : años) {
			String temporada = año + "-" + ((año + 1) % 100);
			SelectTemporadas.addItem("Temporada " + temporada);
		}
	}

	private void generarEquiposPorTemporada(String temporada, String rol) {
		panelInferior.removeAll();
		panelInferior.add(labelUsuario);
		panelInferior.add(SelectTemporadas);
		panelInferior.add(btnVolverMenu);
		if ("Administrador".equals(rol)) {
			panelInferior.add(btnNuevo);
		}

		if ("Temporada 2025-26".equals(temporada)) {
			JLabel proximamenteLabel = new JLabel("No hay equipos disponibles.");
			proximamenteLabel.setFont(new Font("SansSerif", Font.PLAIN, 21));
			proximamenteLabel.setForeground(new Color(0x13427e));
			proximamenteLabel.setBounds(290, 180, 500, 50);
			panelInferior.add(proximamenteLabel);
		} else {
			String[][] equipos = obtenerEquiposPorTemporada(temporada);
			for (int i = 0; i < equipos.length; i++) {
				ImageIcon icono = new ImageIcon(getClass().getResource(equipos[i][1]));
				JButton botonEquipo = crearBotonEquipo(equipos[i][0], icono, i);
				panelInferior.add(botonEquipo);

				if ("Administrador".equals(rol)) {
					JButton botonEliminar = crearBotonEliminar(i);
					panelInferior.add(botonEliminar);
				}
			}
		}

		panelInferior.revalidate();
		panelInferior.repaint();
	}

	private String[][] obtenerEquiposPorTemporada(String temporada) {
		switch (temporada) {
		case "Temporada 2023-24":
			return new String[][] { { "New York Knicks", "/imagenes/NewYorkKnicks50.png" },
					{ "Brooklyn Nets", "/imagenes/BrooklynNets50.png" },
					{ "Philadelphia 76ers", "/imagenes/Philadelphia76ers50.png" },
					{ "Toronto Raptors", "/imagenes/TorontoRaptors50.png" },
					{ "Cleveland Cavaliers", "/imagenes/ClevelandCavaliers50.png" },
					{ "Detroit Pistons", "/imagenes/DetroitPistons50.png" } };
		case "Temporada 2024-25":
			return new String[][] { { "Boston Celtics", "/imagenes/BostonCeltics50.png" },
					{ "Atlanta Hawks", "/imagenes/AtlantaHawks50.png" }, { "Miami Heat", "/imagenes/MiamiHeat50.png" },
					{ "Chicago Bulls", "/imagenes/ChicagoBulls50.png" },
					{ "Golden State Warriors", "/imagenes/GoldenStateWarriors50.png" },
					{ "Los Angeles Lakers", "/imagenes/LosAngelesLakers50.png" } };
		case "Temporada 2025-26":

		default:
			return new String[0][0];
		}
	}

	private JButton crearBotonEquipo(String nombreEquipo, ImageIcon icono, int indice) {
		JButton boton = new BotonRedondeado(nombreEquipo, icono);
		boton.setHorizontalAlignment(SwingConstants.LEFT);
		boton.setFont(new Font("SansSerif", Font.BOLD, 16));
		boton.setBackground(new Color(0xf46b20));
		boton.setForeground(Color.WHITE);
		int x = (indice % 2 == 0) ? 71 : 452;
		int y = 118 + (indice / 2) * 60;
		boton.setBounds(x, y, 255, 50);
		boton.addMouseListener(this);
		return boton;
	}

	private JButton crearBotonEliminar(int indice) {
		JButton boton = new BotonRedondeado("-", null);
		boton.setForeground(Color.WHITE);
		boton.setFont(new Font("SansSerif", Font.BOLD, 16));
		boton.setBackground(new Color(0x545454));
		int x = (indice % 2 == 0) ? 336 : 717;
		int y = 118 + (indice / 2) * 60;
		boton.setBounds(x, y, 50, 50);
		boton.addMouseListener(this);
		boton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				eliminarEquipo(indice, e);
			}
		});
		return boton;
	}

	private void eliminarEquipo(int indice, ActionEvent e) {
		// Lógica para eliminar el equipo de la temporada
		String equipoEliminado = ""; // Variable para almacenar el nombre del equipo eliminado
		int equipoX = (indice % 2 == 0) ? 71 : 452;
		int equipoY = 118 + (indice / 2) * 60;
		for (Component comp : panelInferior.getComponents()) {
			if (comp instanceof JButton) {
				JButton btn = (JButton) comp;
				if (btn.getBounds().x == equipoX && btn.getBounds().y == equipoY) {
					equipoEliminado = btn.getText(); // Obtener el nombre del equipo eliminado
					panelInferior.remove(btn);
					break;
				}
			}
		}

		// Eliminar el botón de eliminar
		panelInferior.remove((JButton) e.getSource());

		// Mostrar mensaje de alerta
		JOptionPane.showMessageDialog(null, "Equipo \"" + equipoEliminado + "\" ha sido eliminado.");

		// Actualizar la interfaz
		panelInferior.revalidate();
		panelInferior.repaint();
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
		if (o instanceof JButton) {
			JButton btn = (JButton) o;
			if (btn == btnNuevo || btn.getText().equals("-")) {
				fader.fadeBackground(btn, btn.getBackground(), new Color(0x6a6a6a));
			} else if (btn == btnVolverMenu) {
				fader.fadeBackground(btn, btn.getBackground(), new Color(0x646464));
			} else {
				fader.fadeBackground(btn, btn.getBackground(), new Color(0xff7f50));
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent ae) {
		Object o = ae.getSource();
		if (o instanceof JButton) {
			JButton btn = (JButton) o;
			if (btn == btnNuevo || btn.getText().equals("-")) {
				fader.fadeBackground(btn, btn.getBackground(), new Color(0x545454));
			} else if (btn == btnVolverMenu) {
				fader.fadeBackground(btn, btn.getBackground(), new Color(0x404040));
			} else {
				fader.fadeBackground(btn, btn.getBackground(), new Color(0xf46b20));
			}
		}
	}
}
