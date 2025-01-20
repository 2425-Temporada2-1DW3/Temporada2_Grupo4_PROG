package LPB;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import LPBCLASES.BackgroundFader;
import LPBCLASES.BotonRedondeado;

public class Menu extends JFrame implements MouseListener {
    private static final long serialVersionUID = -1200889095902166795L;

    // Componentes de la interfaz
    private JButton btnTemporadas;
    private JButton btnEquipos;
    private JButton btnUsuarios;
    private JButton btnCerrarSesion;
    private JPanel panelIzquierdo;
    private JLabel labelLogo;
    private JLabel labelUsuario;
    private JPanel panelDerecho;
    private JLabel titulo;
    private JLabel subtitulo;
    private ImageIcon logo;
    private BackgroundFader fader;
    private BotonRedondeado boton;

    public Menu(String rol, String usuario) {
        configurarVentana(usuario);
        inicializarComponentes(rol, usuario);
    }

    private void configurarVentana(String usuario) {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
        setTitle("LPB Basketball - Menú");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 550);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        fader = new BackgroundFader();
    }

    private void inicializarComponentes(String rol, String usuario) {
        crearPanelIzquierdo(usuario);
        crearPanelDerecho(rol, usuario);
    }

    private void crearPanelIzquierdo(String usuario) {
        panelIzquierdo = new JPanel();
        panelIzquierdo.setBackground(new Color(255, 243, 205));
        panelIzquierdo.setBounds(0, 0, 425, 513);
        panelIzquierdo.setLayout(null);

        logo = new ImageIcon(getClass().getResource("/imagenes/logo500.png"));
        labelLogo = new JLabel(logo);
        labelLogo.setBounds(10, 55, 400, 400);
        panelIzquierdo.add(labelLogo);

        labelUsuario = new JLabel("Usuario: " + usuario);
        labelUsuario.setFont(new Font("SansSerif", Font.PLAIN, 16));
        labelUsuario.setForeground(new Color(0x545454));
        labelUsuario.setBounds(20, 470, 200, 20);
        panelIzquierdo.add(labelUsuario);

        getContentPane().add(panelIzquierdo);
    }

    private void crearPanelDerecho(String rol, String usuario) {
        panelDerecho = new JPanel();
        panelDerecho.setBackground(new Color(204, 153, 102));
        panelDerecho.setBounds(425, 0, 411, 513);
        panelDerecho.setLayout(null);

        crearTitulos();
        crearBotones(rol, usuario);

        getContentPane().add(panelDerecho);
    }

    private void crearTitulos() {
        titulo = new JLabel("Menú");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 50));
        titulo.setForeground(new Color(255, 243, 205));
        titulo.setBounds(50, 40, 200, 47);
        panelDerecho.add(titulo);

        subtitulo = new JLabel("Seleccione una opción:");
        subtitulo.setForeground(Color.WHITE);
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 18));
        subtitulo.setBounds(50, 146, 200, 20);
        panelDerecho.add(subtitulo);
    }

    private void crearBotones(String rol, String usuario) {
        btnTemporadas = crearBoton("Temporadas", new Color(0x13427E), 191, e -> abrirMenuTemporadas(usuario));
        btnEquipos = crearBoton("Equipos", new Color(0xf46b20), 251, e -> abrirEquiposTemporada(usuario));

        if ("Administrador".equals(rol)) {
            btnUsuarios = crearBoton("Usuarios", new Color(0x545454), 311, null);
            panelDerecho.add(btnUsuarios);
        }

        btnCerrarSesion = crearBoton("Cerrar Sesión", new Color(64, 64, 64), 461, e -> cerrarSesion());
    }

    private JButton crearBoton(String texto, Color color, int y, ActionListener accion) {
        boton = new BotonRedondeado(texto, null);
        boton.setFont(new Font("SansSerif", Font.BOLD, 16));
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setBounds(50, y, 200, 40);
        boton.addMouseListener(this);
        if (accion != null) {
            boton.addActionListener(accion);
        }
        panelDerecho.add(boton);
        return boton;
    }

    private void abrirMenuTemporadas(String usuario) {
        new MenuTemporadas(usuario, usuario).setVisible(true);
        dispose();
    }

    private void abrirEquiposTemporada(String usuario) {
        new EquiposTemporada(usuario, usuario).setVisible(true);
        dispose();
    }

    private void cerrarSesion() {
        new Login().setVisible(true);
        dispose();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent ae) {
        Object o = ae.getSource();

        if (o == btnTemporadas) {
            fader.fadeBackground(btnTemporadas, btnTemporadas.getBackground(), new Color(0x1a5bae));
        } else if (o == btnEquipos) {
            fader.fadeBackground(btnEquipos, btnEquipos.getBackground(), new Color(0xff7f50));
        } else if (o == btnUsuarios) {
            fader.fadeBackground(btnUsuarios, btnUsuarios.getBackground(), new Color(0x6a6a6a));
        } else if (o == btnCerrarSesion) {
            fader.fadeBackground(btnCerrarSesion, btnCerrarSesion.getBackground(), new Color(0x646464));
        }
    }

    @Override
    public void mouseExited(MouseEvent ae) {
        Object o = ae.getSource();

        if (o == btnTemporadas) {
            fader.fadeBackground(btnTemporadas, btnTemporadas.getBackground(), new Color(0x13427E));
        } else if (o == btnEquipos) {
            fader.fadeBackground(btnEquipos, btnEquipos.getBackground(), new Color(0xf46b20));
        } else if (o == btnUsuarios) {
            fader.fadeBackground(btnUsuarios, btnUsuarios.getBackground(), new Color(0x545454));
        } else if (o == btnCerrarSesion) {
            fader.fadeBackground(btnCerrarSesion, btnCerrarSesion.getBackground(), new Color(0x404040));
        }
    }
}
