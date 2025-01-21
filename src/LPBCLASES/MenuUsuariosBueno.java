package LPBCLASES;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;

public class MenuUsuariosBueno extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsuario;
	private JPanel panelUsuarios;
	private FlowLayout fl_panelUsuarios;
	private JPanel panelCampos;
	private JPanel panel;
	private JLabel lblUsuario;
	private JPanel panel_1;
	private JLabel lblContrasena;
	private JPanel panel_2;
	private JLabel lblRol;
	private JComboBox<String> comboBoxRol;
	private JPanel panel_3;
	private JLabel lblEquipo;
	private JComboBox<String> comboEquipo;
	private JPanel panel_4;
	private FlowLayout flowLayout;
	private JButton btnGuardar;
	private JButton btnVolver;
	private FlowLayout fl_panelTitulo;
	private JPanel panelTitulo;
	private JLabel lblLogo;
	private JList<Usuario> listUsuarios;
	private DefaultListModel<Usuario> dlm;
	
	private JPasswordField passwordField;
	private JButton btnEliminar;
	private ImageIcon logo;
	private BackgroundFader fader;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuUsuariosBueno frame = new MenuUsuariosBueno();
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
	public MenuUsuariosBueno() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
		setTitle("LPB Basketball - Menú de Usuarios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 908, 806);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 243, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		fader = new BackgroundFader();

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelUsuarios = new JPanel();
		panelUsuarios.setBackground(new Color(255, 243, 204));
		fl_panelUsuarios = (FlowLayout) panelUsuarios.getLayout();
		fl_panelUsuarios.setAlignOnBaseline(true);
		fl_panelUsuarios.setAlignment(FlowLayout.LEFT);
		fl_panelUsuarios.setHgap(160);
		contentPane.add(panelUsuarios, BorderLayout.WEST);
		
		// creo el modelo de datos de la lista
		dlm = new DefaultListModel<Usuario>();
		// creo la lista		
		listUsuarios = new JList<Usuario>();
		// asocio el modelo de datos a la lista
		listUsuarios.setModel(dlm);		
		
		panelUsuarios.add(listUsuarios);
		
		panelCampos = new JPanel();
		contentPane.add(panelCampos, BorderLayout.EAST);
		panelCampos.setLayout(new BoxLayout(panelCampos, BoxLayout.Y_AXIS));
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 243, 204));
		panelCampos.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
		
		lblUsuario = new JLabel("Usuario     ");
		panel.add(lblUsuario);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsuario.setForeground(new Color(0x545454));
		lblUsuario.setBounds(282, 267, 66, 25);
		
		textUsuario = new TextoRedondeado(20);
		panel.add(textUsuario);
		textUsuario.setColumns(10);
		textUsuario.setFont(new Font("SansSerif", Font.PLAIN, 16));	
		textUsuario.setBounds(376, 262, 200, 35);
		
		panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setHgap(20);
		panel_1.setBackground(new Color(255, 243, 204));
		panelCampos.add(panel_1);
		
		lblContrasena = new JLabel("Contraseña");
		panel_1.add(lblContrasena);
		lblContrasena.setForeground(new Color(0x545454));
		lblContrasena.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblContrasena.setBounds(253, 317, 98, 25);
		
		passwordField = new PasswordRedondeado(20);
		passwordField.setColumns(10);
		passwordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		panel_1.add(passwordField);
		passwordField.setBounds(376, 312, 200, 35);
		
		panel_2 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setHgap(20);
		panel_2.setBackground(new Color(255, 243, 204));
		panelCampos.add(panel_2);
		
		lblRol = new JLabel("Rol");
		panel_2.add(lblRol);
		lblRol.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRol.setForeground(new Color(0x545454));
		lblRol.setBounds(282, 267, 66, 25);
		
		comboBoxRol = new JComboBox<String>();
		panel_2.add(comboBoxRol);
		
		// añado elementos al combobox
		comboBoxRol.addItem(" Administrador");
		comboBoxRol.addItem(" Arbitro");
		comboBoxRol.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		panel_3 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_3.getLayout();
		flowLayout_3.setHgap(20);
		panel_3.setBackground(new Color(255, 243, 204));
		panelCampos.add(panel_3);
		
		lblEquipo = new JLabel("Equipo   ");
		panel_3.add(lblEquipo);
		lblEquipo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEquipo.setForeground(new Color(0x545454));
		lblEquipo.setBounds(282, 267, 66, 25);
		
		comboEquipo = new JComboBox<String>();
		panel_3.add(comboEquipo);
		
		// añado elementos al combobox
		comboEquipo.addItem(" Lakers");
		comboEquipo.addItem(" Bulls");
		comboEquipo.addItem(" Heat");
		comboEquipo.addItem(" Hawks");
		comboEquipo.addItem(" Warriors");
		comboEquipo.addItem(" Celtics");
		comboEquipo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 243, 204));
		flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setVgap(30);
		flowLayout.setHgap(80);
		panelCampos.add(panel_4);
		
		btnGuardar = new BotonRedondeado("Guardar");
		btnGuardar.addActionListener(this);
		panel_4.add(btnGuardar);
		btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnGuardar.setBounds(327, 395, 200, 40);
		btnGuardar.setBackground(new Color(0x13427E));
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFocusPainted(false);
		btnGuardar.addMouseListener(this);
		
		btnVolver = new BotonRedondeado("Volver");
		btnVolver.addActionListener(this);
		btnVolver.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnVolver.setBounds(327, 395, 200, 40);
		btnVolver.setBackground(new Color(0x13427E));
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setFocusPainted(false);
		btnVolver.addMouseListener(this);
		
		btnEliminar = new BotonRedondeado("Eliminar");
		btnEliminar.addActionListener(this);
		panel_4.add(btnEliminar);
		panel_4.add(btnVolver);
		btnEliminar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnEliminar.setBounds(327, 395, 200, 40);
		btnEliminar.setBackground(new Color(0x13427E));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFocusPainted(false);
		btnEliminar.addMouseListener(this);
		
		panelTitulo = new JPanel();
		panelTitulo.setBackground(new Color(255, 243, 204));
		fl_panelTitulo = (FlowLayout) panelTitulo.getLayout();
		fl_panelTitulo.setVgap(40);
		contentPane.add(panelTitulo, BorderLayout.NORTH);
		
		logo = new ImageIcon(getClass().getResource("/imagenes/logo220.png"));
		lblLogo = new JLabel(logo);
		panelTitulo.add(lblLogo);
		
		

	}

	public void actionPerformed(ActionEvent e) {
		// obtengo sobre que componente se ha realizado la accion
		Object o = e.getSource();
		
		if (o == btnGuardar) {
			guardarUsuarios();
		} else if (o == btnVolver) {
			 dispose(); // Cierra la ventana actual
		} else if (o == btnEliminar) {
			// si se ha pulsado btnEliminar
			// obtengo cuantas opciones hay seleccionadas en la lista
			int[]indices = this.listUsuarios.getSelectedIndices();
			int numeroOpciones = indices.length;
			if (numeroOpciones <= 0) {
				// si NO hay opciones seleccionadas
				JOptionPane.showMessageDialog(this,(String)"Error. No Hay Opciones Seleccionadas","Error",JOptionPane.ERROR_MESSAGE,null);
			}
			else {
				// si hay opciones seleccionadas
				// las borro empezando por la ultima
				for(int posicion=numeroOpciones-1;posicion>=0;posicion--) {
					// borro el elemento cuyo indice esta en esa posicion
					dlm.remove(indices[posicion]);
				}
			}
		}
		
	}
	
	// Metodoss
	/*
	
	public void añadirUsuario() {
		
		String NombreUsuario = textUsuario.getText();
		
		dlm.addElement(NombreUsuario);
	}
	
	*/

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	    Object o = e.getSource();

        if (o == btnGuardar) {
            fader.fadeBackground(btnGuardar, btnGuardar.getBackground(), new Color(0x1a5bae));
        } else if (o == btnEliminar) {
            fader.fadeBackground(btnEliminar, btnEliminar.getBackground(), new Color(0xfe9f2e));
        } else if (o == btnVolver) {
            fader.fadeBackground(btnVolver, btnVolver.getBackground(), new Color(0xfe9f2e));
        }
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	       Object o = e.getSource();

	        if (o == btnGuardar) {
	            fader.fadeBackground(btnGuardar, btnGuardar.getBackground(), new Color(0x13427E));
	        } else if (o == btnEliminar) {
	            fader.fadeBackground(btnEliminar, btnEliminar.getBackground(), new Color(0xf46b20));
	        }else if (o == btnVolver) {
	            fader.fadeBackground(btnVolver, btnVolver.getBackground(), new Color(0xf46b20));
	        }
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	} 
	
	
	
	

	
	
	

}