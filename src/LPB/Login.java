package LPB;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

public class Login extends JFrame {
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

    public static void main(String[] args) {
        try {
            // Establecer el look and feel del sistema operativo
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(() -> {
            try {
                Login frame = new Login();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

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
        panel.add(btnIniciarSesion);

        btnIniciarSesion.addActionListener(e -> iniciarSesion());
        txtPassword.addActionListener(e -> btnIniciarSesion.doClick());

        btnInvitado = new BotonRedondeado("Entrar como Invitado", null);
        btnInvitado.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnInvitado.setBounds(327, 445, 200, 40);
        btnInvitado.setBackground(new Color(0xf46b20));
        btnInvitado.setForeground(Color.WHITE);
        btnInvitado.setFocusPainted(false);
        panel.add(btnInvitado);

        btnInvitado.addActionListener(e -> entrarComoInvitado());

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (e.getSource() == btnIniciarSesion) {
                    fader.fadeBackground(btnIniciarSesion, btnIniciarSesion.getBackground(), new Color(0x1a5bae));
                } else if (e.getSource() == btnInvitado) {
                    fader.fadeBackground(btnInvitado, btnInvitado.getBackground(), new Color(0xfe9f2e));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (e.getSource() == btnIniciarSesion) {
                    fader.fadeBackground(btnIniciarSesion, btnIniciarSesion.getBackground(), new Color(0x13427E));
                } else if (e.getSource() == btnInvitado) {
                    fader.fadeBackground(btnInvitado, btnInvitado.getBackground(), new Color(0xf46b20));
                }
            }
        };

        btnIniciarSesion.addMouseListener(mouseAdapter);
        btnInvitado.addMouseListener(mouseAdapter);
    }

    private void iniciarSesion() {
        String usuario = txtUsuario.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (usuario.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String rol = "";
        if ("Administrador".equals(usuario) && "1234".equals(password)) {
            rol = "Administrador";
        } else if ("Arbitro".equals(usuario) && "1234".equals(password)) {
            rol = "Arbitro";
        } else if ("Entrenador".equals(usuario) && "1234".equals(password)) {
            rol = "Entrenador";
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Bienvenido " + rol + ".");
        new Menu(rol, usuario).setVisible(true);
        dispose();
    }

    private void entrarComoInvitado() {
        JOptionPane.showMessageDialog(this, "Bienvenido Invitado.");
        new Menu("invitado", "Invitado").setVisible(true);
        dispose();
    }
}
