
package LPB;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

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
import LPBCLASES.Jugador;

public class EditarEquipo extends JFrame {

	private static final long serialVersionUID = -9149830621520815494L;
	private Equipo equipo;
	private JTextField nombreField;
	private JTextField entrenadorField;
	private JTextField estadioField;
	private JTextField fundacionField;

	public EditarEquipo(Equipo equipo) {
		this.equipo = equipo;

		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
		setTitle("Editar Equipo: " + equipo.getNombre());
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		JPanel infoPanel = new JPanel(new GridLayout(4, 2, 10, 10));

		JLabel logoLabel = new JLabel(new ImageIcon(equipo.getEquipoPath()));
		infoPanel.add(new JLabel("Logo:"));
		infoPanel.add(logoLabel);

		infoPanel.add(new JLabel("Nombre:"));
		nombreField = new JTextField(equipo.getNombre());
		infoPanel.add(nombreField);

		infoPanel.add(new JLabel("Entrenador:"));
		entrenadorField = new JTextField(equipo.getEntrenador());
		infoPanel.add(entrenadorField);

		infoPanel.add(new JLabel("Estadio:"));
		estadioField = new JTextField(equipo.getEstadio());
		infoPanel.add(estadioField);
		
		infoPanel.add(new JLabel("Año de fundación:"));
		fundacionField = new JTextField(equipo.getFundacion());		
		infoPanel.add(fundacionField);

		DefaultListModel<Jugador> jugadoresModel = new DefaultListModel<Jugador>();
		
		for (Jugador jugador : equipo.getJugadores()) {
			jugadoresModel.addElement(jugador);
		}
		
		JList<Jugador> jugadoresList = new JList<Jugador>(jugadoresModel);
		jugadoresList.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
			JPanel playerPanel = new JPanel(new BorderLayout());
			JLabel playerImage = new JLabel(new ImageIcon(value.getPhotoPath()));
			JLabel playerName = new JLabel(value.getNombre());
			playerPanel.add(playerImage, BorderLayout.WEST);
			playerPanel.add(playerName, BorderLayout.CENTER);
			return playerPanel;
		});

		JScrollPane scrollPane = new JScrollPane(jugadoresList);

		getContentPane().add(infoPanel, BorderLayout.CENTER);
		getContentPane().add(scrollPane, BorderLayout.EAST);

		JPanel buttonPanel = new JPanel();
		JButton guardarButton = new JButton("Guardar");
		JButton cancelarButton = new JButton("Cancelar");

		guardarButton.addActionListener(e -> {
			equipo.setNombre(nombreField.getText());
			equipo.setEntrenador(entrenadorField.getText());
			equipo.setEstadio(estadioField.getText());
			equipo.setFundacion(Integer.valueOf(fundacionField.getText()));
			JOptionPane.showMessageDialog(this, "Cambios guardados correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		});

		cancelarButton.addActionListener(e -> dispose());

		buttonPanel.add(guardarButton);
		buttonPanel.add(cancelarButton);

		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
	}
}
