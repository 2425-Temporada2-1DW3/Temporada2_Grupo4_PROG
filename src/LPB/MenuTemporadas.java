
package LPB;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import LPBCLASES.BackgroundFader;
import LPBCLASES.BotonRedondeado;
import LPBCLASES.Temporada;

public class MenuTemporadas extends JFrame implements MouseListener {
	private static final long serialVersionUID = -1200889095902166795L;
	private JPanel panelIzquierdo;
	private ImageIcon logo;
	private JLabel labelLogo;
	private JLabel labelUsuario;
	private JPanel panelDerecho;
    private JPanel panelContenido;
    private JScrollPane scrollPane;
	private JLabel titulo;
	private JLabel subtitulo;
	private List<Temporada> temporadas;
	private JButton btnTemporada;
	private JButton btnNuevaTemporada;
	private JButton btnVolverMenu;
	private BackgroundFader fader;
	private String rol;
	private String usuario;

	public MenuTemporadas(String rol, String usuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
		setTitle("LPB Basketball - Menú");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(850, 550);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
	    this.rol = rol;
	    this.usuario = usuario;

		fader = new BackgroundFader();
		
		panelIzquierdo = new JPanel();
		panelIzquierdo.setBackground(new Color(255, 243, 205));
		panelIzquierdo.setBounds(0, 0, 425, 513);
		panelIzquierdo.setLayout(null);

		logo = new ImageIcon(getClass().getResource("/imagenes/logo500.png"));
		labelLogo = new JLabel(logo);
		labelLogo.setBounds(10, 55, 400, 400);
		panelIzquierdo.add(labelLogo);
		getContentPane().add(panelIzquierdo);

		labelUsuario = new JLabel("Usuario: " + usuario);
		labelUsuario.setFont(new Font("SansSerif", Font.PLAIN, 16));
		labelUsuario.setForeground(new Color(0x545454));
		labelUsuario.setBounds(20, 470, 200, 20);
		panelIzquierdo.add(labelUsuario);

		panelDerecho = new JPanel();
		panelDerecho.setBackground(new Color(204, 153, 102));
		panelDerecho.setBounds(425, 0, 411, 513);
		panelDerecho.setLayout(null);

		titulo = new JLabel("Temporadas");
		titulo.setFont(new Font("SansSerif", Font.BOLD, 50));
		titulo.setForeground(new Color(255, 243, 205));
		titulo.setBounds(50, 40, 306, 47);
		panelDerecho.add(titulo);

		subtitulo = new JLabel("Seleccione una Temporada:");
		subtitulo.setForeground(Color.WHITE);
		subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 18));
		subtitulo.setBounds(50, 131, 234, 20);
		panelDerecho.add(subtitulo);
		
        panelContenido = new JPanel();
        panelContenido.setBorder(null);
        panelContenido.setLayout(null);
        panelContenido.setBackground(new Color(204, 153, 102));

        scrollPane = new JScrollPane(panelContenido);
        scrollPane.setBounds(50, 160, 300, 250);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panelDerecho.add(scrollPane);
		
		cargarTemporadas();
		
		btnVolverMenu = new BotonRedondeado("Volver al Menú", null);
		btnVolverMenu.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnVolverMenu.setBackground(new Color(64, 64, 64));
		btnVolverMenu.setForeground(Color.WHITE);
		btnVolverMenu.setBounds(250, 461, 140, 30);
		btnVolverMenu.setFocusPainted(false);
		btnVolverMenu.addMouseListener(this);
		panelDerecho.add(btnVolverMenu);

		btnVolverMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Menu(rol, usuario).setVisible(true);
				dispose();
			}
		});

		getContentPane().add(panelDerecho);
	}
	
	@SuppressWarnings("unchecked")
	private void cargarTemporadas() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("temporadas.ser"))) {
        	temporadas = (List<Temporada>) ois.readObject();
            int yPosition = 10;
            int buttonHeight = 40;
            int buttonSpacing = 20;
            
    		btnNuevaTemporada = new BotonRedondeado("Nueva Temporada", null);
    		btnNuevaTemporada.setForeground(Color.WHITE);
    		btnNuevaTemporada.setFont(new Font("SansSerif", Font.BOLD, 16));
    		btnNuevaTemporada.setBackground(new Color(0x545454));
    		btnNuevaTemporada.setBounds(0, yPosition, 220, 40);
    		btnNuevaTemporada.setFocusPainted(false);
    		btnNuevaTemporada.addMouseListener(this);
    		
    		btnNuevaTemporada.addActionListener(new ActionListener() {
    		    public void actionPerformed(ActionEvent e) {
    		        AgregarTemporada agregarTemporada = new AgregarTemporada();
    		        agregarTemporada.setVisible(true);
    		        
    		        agregarTemporada.addWindowListener(new java.awt.event.WindowAdapter() {
    		            @Override
    		            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
    		                cargarTemporadas();
    		            }
    		        });
    		    }
    		});
    		
    		if ("Administrador".equals(rol)) {
    			panelContenido.add(btnNuevaTemporada);
    			yPosition += buttonHeight + buttonSpacing;
    		}

            for (Temporada temporada : temporadas) {
            	JButton btnTemporada = new BotonRedondeado("Temporada " + temporada.getPeriodo(), null);
                btnTemporada.setFont(new Font("SansSerif", Font.BOLD, 16));
                btnTemporada.setBackground(new Color(0xf46b20));
                btnTemporada.setForeground(Color.WHITE);
                btnTemporada.setBounds(0, yPosition, 220, 40);
                btnTemporada.setFocusPainted(false);
                btnTemporada.addMouseListener(this);

                btnTemporada.addActionListener(e -> {
                	String temporadaSeleccionada = temporada.getPeriodo();
					new MenuJornadas(rol, usuario, temporadaSeleccionada).setVisible(true);
        	        dispose();
                });

                panelContenido.add(btnTemporada);
                yPosition += buttonHeight + buttonSpacing;
            }
    		
            panelContenido.setPreferredSize(new Dimension(300, yPosition + buttonHeight + buttonSpacing));
            panelContenido.revalidate();

        } catch (Exception e) {
            temporadas = List.of();
            
    		btnNuevaTemporada = new BotonRedondeado("Nueva Temporada", null);
    		btnNuevaTemporada.setForeground(Color.WHITE);
    		btnNuevaTemporada.setFont(new Font("SansSerif", Font.BOLD, 16));
    		btnNuevaTemporada.setBackground(new Color(0x545454));
    		btnNuevaTemporada.setBounds(0, 10, 220, 40);
    		btnNuevaTemporada.setFocusPainted(false);
    		btnNuevaTemporada.addMouseListener(this);
    		
    		btnNuevaTemporada.addActionListener(new ActionListener() {
    		    public void actionPerformed(ActionEvent e) {
    		        AgregarTemporada agregarTemporada = new AgregarTemporada();
    		        agregarTemporada.setVisible(true);
    		        
    		        agregarTemporada.addWindowListener(new java.awt.event.WindowAdapter() {
    		            @Override
    		            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
    		                cargarTemporadas();
    		            }
    		        });
    		    }
    		});
    		
    		if ("Administrador".equals(rol)) {
    			panelContenido.add(btnNuevaTemporada);
    		}
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

	@Override
	public void mouseEntered(MouseEvent ae) {
		Object o = ae.getSource();
		
		if (o == btnTemporada) {
			fader.fadeBackground(btnTemporada, btnTemporada.getBackground(), new Color(0xff7f50));
		} else if (o == btnNuevaTemporada) {
			fader.fadeBackground(btnNuevaTemporada, btnNuevaTemporada.getBackground(), new Color(0x6a6a6a));
		} else if (o == btnVolverMenu) {
			fader.fadeBackground(btnVolverMenu, btnVolverMenu.getBackground(), new Color(0x646464));
		}
		
	}

	@Override
	public void mouseExited(MouseEvent ae) {
		Object o = ae.getSource();

		if (o == btnTemporada) {
			fader.fadeBackground(btnTemporada, btnTemporada.getBackground(), new Color(0xf46b20));
		} else if (o == btnNuevaTemporada) {
			fader.fadeBackground(btnNuevaTemporada, btnNuevaTemporada.getBackground(), new Color(0x545454));
		} else if (o == btnVolverMenu) {
			fader.fadeBackground(btnVolverMenu, btnVolverMenu.getBackground(), new Color(0x404040));
		}
		
	}

	
}
