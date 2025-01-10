
package LPB;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import LPBCLASES.BotonRedondeado;

public class MenuTemporadas extends JFrame {
	private static final long serialVersionUID = -1200889095902166795L;

	public MenuTemporadas(String rol, String usuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
		setTitle("LPB Basketball - Menú");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(850, 550);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JPanel panelIzquierdo = new JPanel();
		panelIzquierdo.setBackground(new Color(255, 243, 205));
		panelIzquierdo.setBounds(0, 0, 425, 513);
		panelIzquierdo.setLayout(null);

		ImageIcon logo = new ImageIcon(getClass().getResource("/imagenes/logo500.png"));
		JLabel labelLogo = new JLabel(logo);
		labelLogo.setBounds(10, 55, 400, 400);
		panelIzquierdo.add(labelLogo);
		getContentPane().add(panelIzquierdo);

		JLabel labelUsuario = new JLabel("Usuario: " + usuario);
		labelUsuario.setFont(new Font("SansSerif", Font.PLAIN, 16));
		labelUsuario.setForeground(new Color(0x545454));
		labelUsuario.setBounds(20, 470, 200, 20);
		panelIzquierdo.add(labelUsuario);

		JPanel panelDerecho = new JPanel();
		panelDerecho.setBackground(new Color(204, 153, 102));
		panelDerecho.setBounds(425, 0, 411, 513);
		panelDerecho.setLayout(null);

		JLabel titulo = new JLabel("Temporadas");
		titulo.setFont(new Font("SansSerif", Font.BOLD, 50));
		titulo.setForeground(new Color(255, 243, 205));
		titulo.setBounds(50, 40, 306, 47);
		panelDerecho.add(titulo);

		JLabel subtitulo = new JLabel("Seleccione una Temporeda:");
		subtitulo.setForeground(Color.WHITE);
		subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 18));
		subtitulo.setBounds(50, 131, 234, 20);
		panelDerecho.add(subtitulo);

		JButton btnTemporadas1 = new BotonRedondeado("Temporada 23-24");
		btnTemporadas1.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnTemporadas1.setBackground(new Color(0xf46b20));
		btnTemporadas1.setForeground(Color.WHITE);
		btnTemporadas1.setBounds(50, 176, 200, 40);
		panelDerecho.add(btnTemporadas1);

		JButton btnTemporadas2 = new BotonRedondeado("Temporada 24-25");
		btnTemporadas2.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnTemporadas2.setBackground(new Color(0xf46b20));
		btnTemporadas2.setForeground(Color.WHITE);
		btnTemporadas2.setBounds(50, 236, 200, 40);
		panelDerecho.add(btnTemporadas2);

		JButton btnTemporadas3 = new BotonRedondeado("Temporada 25-26");
		btnTemporadas3.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnTemporadas3.setBackground(new Color(0xf46b20));
		btnTemporadas3.setForeground(Color.WHITE);
		btnTemporadas3.setBounds(50, 296, 200, 40);
		panelDerecho.add(btnTemporadas3);

		JButton btnNuevaTemporada = new BotonRedondeado("Nueva Temporada");
		btnNuevaTemporada.setForeground(Color.WHITE);
		btnNuevaTemporada.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnNuevaTemporada.setBackground(new Color(0x545454));
		btnNuevaTemporada.setBounds(50, 356, 200, 40);

		if (!"Administrador".equals(rol)) {
			btnNuevaTemporada.setEnabled(false);
		}
		panelDerecho.add(btnNuevaTemporada);

		JButton btnVolverMenu = new BotonRedondeado("Volver al Menú");
		btnVolverMenu.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnVolverMenu.setBackground(new Color(64, 64, 64));
		btnVolverMenu.setForeground(Color.WHITE);
		btnVolverMenu.setBounds(250, 461, 140, 30);
		panelDerecho.add(btnVolverMenu);

		btnVolverMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Menu(usuario, usuario).setVisible(true);
				dispose();
			}
		});

		getContentPane().add(panelDerecho);
	}

	
}
