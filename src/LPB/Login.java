package LPB;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import LPBCLASES.BotonRedondeado;
import LPBCLASES.TextoRedondeado;
import LPBCLASES.PasswordRedondeado;

public class Login extends JFrame implements MouseListener {
	private static final long serialVersionUID = -410820418148204249L;
	private JPanel panel;
	private ImageIcon logo;
	private JLabel labelLogo;
	private JLabel userLabel;
	private JLabel passwordLabel;
	private JTextField  txtUsuario;
	private JPasswordField txtPassword;
	private JButton btnIniciarSesion;
    private JButton btnInvitado;
    private int step;
    private static final int FADE_DURATION = 300;
    private static final int FADE_STEPS = 10;

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

		btnIniciarSesion = new BotonRedondeado("Iniciar Sesión");
		btnIniciarSesion.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnIniciarSesion.setBounds(327, 395, 200, 40);
		btnIniciarSesion.setBackground(new Color(0x13427E));
		btnIniciarSesion.setForeground(Color.WHITE);
		btnIniciarSesion.setFocusPainted(false);
		btnIniciarSesion.addMouseListener(this);
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

		btnInvitado = new BotonRedondeado("Entrar como Invitado");
		btnInvitado.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnInvitado.setBounds(327, 445, 200, 40);
		btnInvitado.setBackground(new Color(0xf46b20));
		btnInvitado.setForeground(Color.WHITE);
		btnInvitado.setFocusPainted(false);
		btnInvitado.addMouseListener(this);
		panel.add(btnInvitado);

		btnInvitado.addActionListener(_ -> {
			JOptionPane.showMessageDialog(null, "Bienvenido Invitado.");
			new Menu("invitado", "Invitado").setVisible(true);
			dispose();
		});
	}

	private void fadeBackground(JButton button, Color startColor, Color endColor) {
	    Timer timer = new Timer();
	    step = 0;

	    TimerTask task = new TimerTask() {
	        @Override
	        public void run() {
	            if (step >= FADE_STEPS) {
	                timer.cancel();
	                return;
	            }

	            float ratio = (float) step / FADE_STEPS;
	            int red = (int) (startColor.getRed() + ratio * (endColor.getRed() - startColor.getRed()));
	            int green = (int) (startColor.getGreen() + ratio * (endColor.getGreen() - startColor.getGreen()));
	            int blue = (int) (startColor.getBlue() + ratio * (endColor.getBlue() - startColor.getBlue()));

	            button.setBackground(new Color(red, green, blue));
	            step++;
	        }
	    };

	    timer.scheduleAtFixedRate(task, 0, FADE_DURATION / FADE_STEPS);
	}

    @Override
    public void mouseEntered(MouseEvent ae) {
        Object o = ae.getSource();

        if (o == btnIniciarSesion) {
            fadeBackground(btnIniciarSesion, btnIniciarSesion.getBackground(), btnIniciarSesion.getBackground().brighter());
        } else if (o == btnInvitado) {
            fadeBackground(btnInvitado, btnInvitado.getBackground(), btnInvitado.getBackground().brighter());
        }
    }

    @Override
    public void mouseExited(MouseEvent ae) {
        Object o = ae.getSource();

        if (o == btnIniciarSesion) {
            fadeBackground(btnIniciarSesion, btnIniciarSesion.getBackground(), new Color(0x13427E));
        } else if (o == btnInvitado) {
            fadeBackground(btnInvitado, btnInvitado.getBackground(), new Color(0xf46b20));
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
