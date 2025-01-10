package LPB;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import LPBCLASES.BotonRedondeado;

public class Login extends JFrame {
	private static final long serialVersionUID = -410820418148204249L;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/imagenes/basketball.png"));
		setSize(850, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("LPB Basketball - Inicio de Sesión");

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(255, 243, 204));
		getContentPane().add(panel);

		ImageIcon logo = new ImageIcon("src/imagenes/logo220.png");
		JLabel labelLogo = new JLabel(logo);
		labelLogo.setFont(new Font("Arial", Font.BOLD, 16));
		labelLogo.setBounds(317, 10, 220, 220);
		panel.add(labelLogo);

		JLabel userLabel = new JLabel("Usuario:");
		userLabel.setForeground(new Color(0x545454));
		userLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		userLabel.setBounds(282, 267, 66, 25);
		panel.add(userLabel);

		JTextField txtUsuario = new JTextField(20);
		txtUsuario.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtUsuario.setBounds(376, 262, 200, 35);
		panel.add(txtUsuario);

		JLabel passwordLabel = new JLabel("Contraseña:");
		passwordLabel.setForeground(new Color(0x545454));
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordLabel.setBounds(253, 317, 98, 25);
		panel.add(passwordLabel);

		JPasswordField txtPassword = new JPasswordField(20);
		txtPassword.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtPassword.setBounds(376, 312, 200, 35);
		panel.add(txtPassword);

		JButton btnIniciarSesion = new BotonRedondeado("Iniciar Sesión");
		btnIniciarSesion.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnIniciarSesion.setBounds(327, 395, 200, 40);
		btnIniciarSesion.setBackground(new Color(0x13427E));
		btnIniciarSesion.setForeground(Color.WHITE);
		panel.add(btnIniciarSesion);

		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = txtUsuario.getText().trim();
				String password = new String(txtPassword.getPassword()).trim();
				String rol = "";

				if ("Administrador".equals(usuario) && "1234".equals(password)) {
					rol = "Administrador";
					JOptionPane.showMessageDialog(null, "Bienvenido Administrador.");
				} else if ("Arbitro".equals(usuario) && "1234".equals(password)) {
					rol = "Arbitro";
					JOptionPane.showMessageDialog(null, "Bienvenido Árbitro.");
				} else if ("Entrenador".equals(usuario) && "1234".equals(password)) {
					rol = "Entrenador";
					JOptionPane.showMessageDialog(null, "Bienvenido Entrenador.");
				} else {
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
					return;
				}

				new Menu(rol, usuario).setVisible(true);
				dispose();
			}
		});

		txtPassword.addActionListener(_ -> btnIniciarSesion.doClick());

		JButton btnInvitado = new BotonRedondeado("Entrar como Invitado");
		btnInvitado.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnInvitado.setBounds(327, 445, 200, 40);
		btnInvitado.setBackground(new Color(0xf46b20));
		btnInvitado.setForeground(Color.WHITE);
		panel.add(btnInvitado);

		btnInvitado.addActionListener(_ -> {
			JOptionPane.showMessageDialog(null, "Bienvenido Invitado.");
			new Menu("invitado", "Invitado").setVisible(true);
			dispose();
		});

		setVisible(true);
	}
}
