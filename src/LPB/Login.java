package LPB;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

import LPBCLASES.BotonRedondeado;
import LPBCLASES.PasswordRedondeado;
import LPBCLASES.TextoRedondeado;
import LPBCLASES.Usuario;
import LPBCLASES.logClase;

public class Login extends JFrame {
    private static final long serialVersionUID = -410820418148204249L;
    private JPanel panel;
    private ImageIcon logo;
    private JLabel labelLogo, userLabel, passwordLabel, loadingLabel;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private BotonRedondeado btnIniciarSesion, btnInvitado;
    private JDialog loadingDialog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    try {
	    	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
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

	/**
	 * Create the frame.
	 */
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
		setSize(850, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("LPB Basketball - Inicio de Sesión");

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(255, 243, 204));
		getContentPane().add(panel);

		logo = new ImageIcon(getClass().getResource("/imagenes/logo220.png"));
		labelLogo = new JLabel(logo);
		labelLogo.setFont(new Font("Arial", Font.BOLD, 16));
		labelLogo.setBounds(317, 10, 220, 220);
		panel.add(labelLogo);

		userLabel = new JLabel("Usuario:");
		userLabel.setForeground(new Color(0x545454));
		userLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		userLabel.setBounds(282, 267, 66, 25);
		panel.add(userLabel);

		txtUsuario = new TextoRedondeado(20);
		txtUsuario.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtUsuario.setBounds(376, 262, 200, 35);
		panel.add(txtUsuario);

		passwordLabel = new JLabel("Contraseña:");
		passwordLabel.setForeground(new Color(0x545454));
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordLabel.setBounds(253, 317, 98, 25);
		panel.add(passwordLabel);

		txtPassword = new PasswordRedondeado(20);
		txtPassword.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtPassword.setBounds(376, 312, 200, 35);
		panel.add(txtPassword);

		btnIniciarSesion = new BotonRedondeado("Iniciar Sesión", null);
		btnIniciarSesion.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnIniciarSesion.setBounds(327, 395, 200, 40);
		btnIniciarSesion.setBackground(new Color(0x13427E));
		btnIniciarSesion.setForeground(Color.WHITE);
		btnIniciarSesion.setFocusPainted(false);
		panel.add(btnIniciarSesion);

		MenuUsuarios.cargarUsuarios();
		
		btnIniciarSesion.addActionListener(e -> {
		    String usuario = txtUsuario.getText().trim();
		    String contrasena = new String(txtPassword.getPassword()).trim();

		    try {
		        Usuario user = MenuUsuarios.validarUsuario(usuario, contrasena);
		        if (user != null) {
		            logClase.logAction("El usuario " + usuario + " ha iniciado sesión");

		            loadingDialog = new JDialog();
		            loadingDialog.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
		            loadingDialog.setSize(250, 100);
		            loadingDialog.setLocationRelativeTo(null);
		            loadingDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		            loadingDialog.setModal(false);

		            loadingLabel = new JLabel("Iniciando sesión...", SwingConstants.CENTER);
		            loadingLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

		            ImageIcon loadingGif = new ImageIcon(getClass().getResource("/imagenes/basketball.gif"));
		            ImageIcon scaledGif = new ImageIcon(loadingGif.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
		            JLabel gifLabel = new JLabel(scaledGif, SwingConstants.LEFT);

		            JPanel panel = new JPanel(new GridBagLayout());
		            panel.setBackground(new Color(255, 243, 204));
		            GridBagConstraints gbc = new GridBagConstraints();
		            gbc.gridx = 0;
		            gbc.gridy = 0;
		            gbc.insets = new Insets(5, 5, 5, 5);
		            panel.add(loadingLabel, gbc);
		            
		            gbc.gridx = 1;
		            gbc.insets = new Insets(5, 5, 5, 10);
		            panel.add(gifLabel, gbc);

		            loadingDialog.add(panel);
		            loadingDialog.setVisible(true);

		            SwingWorker<Void, Void> worker = new SwingWorker<>() {
		                @Override
		                protected Void doInBackground() throws Exception {
		                    Thread.sleep(3000);
		                    return null;
		                }

		                @Override
		                protected void done() {
		                    loadingDialog.dispose();
		                    new Menu(user.getRol(), user.getUsuario()).setVisible(true);
		                    dispose();
		                }
		            };
		            worker.execute();
		        } else {
		            logClase.logAction("Intento de inicio de sesión fallido para el usuario: " + usuario);
		            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    } catch (Exception ex) {
		        logClase.logError("Error durante la validación de usuario", ex);
		        JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado.", "Error", JOptionPane.ERROR_MESSAGE);
		    }
		});

        txtPassword.addActionListener(_ -> btnIniciarSesion.doClick());

        btnInvitado = new BotonRedondeado("Entrar como Invitado", null);
        btnInvitado.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnInvitado.setBounds(327, 445, 200, 40);
        btnInvitado.setBackground(new Color(0xf46b20));
        btnInvitado.setForeground(Color.WHITE);
        btnInvitado.setFocusPainted(false);
        panel.add(btnInvitado);

        btnInvitado.addActionListener(_ -> {
            logClase.logAction("Se ha iniciado sesión como invitado");

            loadingDialog = new JDialog();
            loadingDialog.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
            loadingDialog.setSize(250, 100);
            loadingDialog.setLocationRelativeTo(null);
            loadingDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            loadingDialog.setModal(false);

            loadingLabel = new JLabel("Iniciando sesión...", SwingConstants.CENTER);
            loadingLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

            ImageIcon loadingGif = new ImageIcon(getClass().getResource("/imagenes/basketball.gif"));
            ImageIcon scaledGif = new ImageIcon(loadingGif.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
            JLabel gifLabel = new JLabel(scaledGif, SwingConstants.LEFT);

            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBackground(new Color(255, 243, 204));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(5, 5, 5, 5);
            panel.add(loadingLabel, gbc);
            
            gbc.gridx = 1;
            gbc.insets = new Insets(5, 5, 5, 10);
            panel.add(gifLabel, gbc);

            loadingDialog.add(panel);
            loadingDialog.setVisible(true);

            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    Thread.sleep(3000);
                    return null;
                }

                @Override
                protected void done() {
                    loadingDialog.dispose();
                    new Menu("Usuario", "Invitado").setVisible(true);
                    dispose();
                }
            };
            worker.execute();
        });
    }
}
