package LPB;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import LPBCLASES.BackgroundFader;
import LPBCLASES.BotonRedondeado;
import LPBCLASES.PasswordRedondeado;
import LPBCLASES.TextoRedondeado;
import LPBCLASES.Usuario;
import LPBCLASES.logClase;

public class Login extends JFrame implements MouseListener {
    private static final long serialVersionUID = -410820418148204249L;
    private JPanel panel;
    private ImageIcon logo;
    private JLabel labelLogo;
    private JLabel userLabel;
    private JLabel passwordLabel;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnIniciarSesion;
    private JButton btnInvitado;
    private BackgroundFader fader;

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
		
		fader = new BackgroundFader();

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
		btnIniciarSesion.addMouseListener(this);
		panel.add(btnIniciarSesion);

		MenuUsuarios.cargarUsuarios();
		
        btnIniciarSesion.addActionListener(e -> {
            String usuario = txtUsuario.getText().trim();
            String contrasena = new String(txtPassword.getPassword()).trim();


            try {
                Usuario user = MenuUsuarios.validarUsuario(usuario, contrasena);
                if (user != null) {
                	logClase.logAction("El usuario " + usuario + " ha iniciado sesión");
                    JOptionPane.showMessageDialog(null, "Bienvenido/a, " + user.getUsuario() + ".");
                    new Menu(user.getRol(), user.getUsuario()).setVisible(true);
                    dispose();
                } else {
                	logClase.logAction("Intento de inicio de sesión fallido para el usuario: " + usuario);
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
                }
            } catch (Exception ex) {
            	logClase.logError("Error durante la validación de usuario", ex);
                JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado.");
            }
        });

        txtPassword.addActionListener(_ -> btnIniciarSesion.doClick());

        btnInvitado = new BotonRedondeado("Entrar como Invitado", null);
        btnInvitado.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnInvitado.setBounds(327, 445, 200, 40);
        btnInvitado.setBackground(new Color(0xf46b20));
        btnInvitado.setForeground(Color.WHITE);
        btnInvitado.setFocusPainted(false);
        btnInvitado.addMouseListener(this);
        panel.add(btnInvitado);

        btnInvitado.addActionListener(_ -> {
            JOptionPane.showMessageDialog(null, "Bienvenido Invitado.");
            new Menu("Invitado", "Invitado").setVisible(true);
            dispose();
        });
    }

    @Override
    public void mouseEntered(MouseEvent ae) {
        Object o = ae.getSource();

        if (o == btnIniciarSesion) {
            fader.fadeBackground(btnIniciarSesion, btnIniciarSesion.getBackground(), new Color(0x1a5bae));
        } else if (o == btnInvitado) {
            fader.fadeBackground(btnInvitado, btnInvitado.getBackground(), new Color(0xfe9f2e));
        }
    }
    
	@Override
	public void mouseExited(MouseEvent ae) {
        Object o = ae.getSource();

        if (o == btnIniciarSesion) {
            fader.fadeBackground(btnIniciarSesion, btnIniciarSesion.getBackground(), new Color(0x13427E));
        } else if (o == btnInvitado) {
            fader.fadeBackground(btnInvitado, btnInvitado.getBackground(), new Color(0xf46b20));
        }		
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
}