
package LPB;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import LPBCLASES.Equipo;
import LPBCLASES.Equipo.PlayerWithImage;

public class EditarEquipo extends JFrame {

	private static final long serialVersionUID = -9149830621520815494L;
	private Equipo equipo;
	private JTextField nombreField;
	private JTextField entrenadorField;
	private JTextField estadioField;
	private JTextField fundacionField;

	public EditarEquipo(Equipo equipo) {
		this.equipo = equipo;

		setTitle("Editar Equipo: " + equipo.getNombre());
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());

		JPanel infoPanel = new JPanel(new GridLayout(4, 2, 10, 10));

		JLabel logoLabel = new JLabel(new ImageIcon(equipo.getLogoPath()));
		infoPanel.add(new JLabel("Logo:"));
		infoPanel.add(logoLabel);

		nombreField = new JTextField(equipo.getNombre());
		infoPanel.add(new JLabel("Nombre:"));
		infoPanel.add(nombreField);

		entrenadorField = new JTextField(equipo.getEntrenador());
		JLabel entrenadorLabel = new JLabel(new ImageIcon(equipo.getEntrenadorPath()));
		infoPanel.add(new JLabel("Entrenador:"));
		infoPanel.add(entrenadorField);
		infoPanel.add(new JLabel("Foto Entrenador:"));
		infoPanel.add(entrenadorLabel);

		estadioField = new JTextField(equipo.getEstadio());
		fundacionField = new JTextField(equipo.getFundacion());
		infoPanel.add(new JLabel("Estadio:"));
		infoPanel.add(estadioField);
		infoPanel.add(new JLabel("Fundación:"));
		infoPanel.add(fundacionField);

		DefaultListModel<PlayerWithImage> jugadoresModel = new DefaultListModel<>();
		for (PlayerWithImage jugador : equipo.getJugadores()) {
			jugadoresModel.addElement(jugador);
		}
		JList<PlayerWithImage> jugadoresList = new JList<>(jugadoresModel);
		jugadoresList.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
			JPanel playerPanel = new JPanel(new BorderLayout());
			JLabel playerImage = new JLabel(new ImageIcon(value.getImagePath()));
			JLabel playerName = new JLabel(value.getName());
			playerPanel.add(playerImage, BorderLayout.WEST);
			playerPanel.add(playerName, BorderLayout.CENTER);
			return playerPanel;
		});

		JScrollPane scrollPane = new JScrollPane(jugadoresList);

		add(infoPanel, BorderLayout.CENTER);
		add(scrollPane, BorderLayout.EAST);

		JPanel buttonPanel = new JPanel();
		JButton guardarButton = new JButton("Guardar");
		JButton cancelarButton = new JButton("Cancelar");

		guardarButton.addActionListener(e -> {
			equipo.setNombre(nombreField.getText());
			equipo.setEntrenador(entrenadorField.getText());
			equipo.setEstadio(estadioField.getText());
			equipo.setFundacion(fundacionField.getText());
			JOptionPane.showMessageDialog(this, "Cambios guardados correctamente.", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
		});

		cancelarButton.addActionListener(e -> dispose());

		buttonPanel.add(guardarButton);
		buttonPanel.add(cancelarButton);

		add(buttonPanel, BorderLayout.SOUTH);
	}
}
