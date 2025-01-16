package LPB;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import LPBCLASES.BackgroundFader;
import LPBCLASES.BotonRedondeado;
import LPBCLASES.PasswordRedondeado;
import LPBCLASES.TextoRedondeado;
import LPBCLASES.Usuario;
import LPBCLASES.Usuario.Rol;

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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;


public class MenuUsuarios extends JFrame implements ActionListener, MouseListener,Serializable {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsuario;
	private JPanel panelUsuarios;
	private JPanel panelCampos;
	private JPanel panel;
	private JLabel lblUsuario;
	private JPanel panel_1;
	private JLabel lblContrasena;
	private JPanel panel_2;
	private JLabel lblRol;
	private JComboBox<String> comboBoxRol;
	private JPanel panel_4;
	private FlowLayout flowLayout;
	private JButton btnGuardar;
	private JButton btnVolver;
	private FlowLayout fl_panelTitulo;
	private JPanel panelTitulo;
	private JLabel lblLogo;
	private DefaultListModel<Usuario> dlm;
	private JList<Usuario> listUsuarios;
	private ArrayList<Usuario> listaUsuarios = new ArrayList<>();
	private JPasswordField passwordField;
	private JButton btnEliminar;
	private ImageIcon logo;
	private BackgroundFader fader;
	private JScrollPane scrollPane;

	
	
	//metodos de guardar y cargar usuarios
	private static final String ARCHIVO_USUARIOS = "usuarios.ser";
	
	
	
    public static void guardarUsuarios(ArrayList<Usuario> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_USUARIOS))) {
            oos.writeObject(usuarios);
            JOptionPane.showMessageDialog(  null, "Usuarios guardados correctamente en " + ARCHIVO_USUARIOS, "Información", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
        	 JOptionPane.showMessageDialog(  null,"Error al guardar los usuarios: " + e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
        
        }
    }

   
    @SuppressWarnings("unchecked")
	public static ArrayList<Usuario> cargarUsuarios() {
        File archivo = new File(ARCHIVO_USUARIOS);
        if (!archivo.exists()) {
            System.out.println("El archivo " + ARCHIVO_USUARIOS + " no existe. Creando una nueva lista vacía.");
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_USUARIOS))) {
            return (ArrayList<Usuario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar los usuarios: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    
    
    //Actualiza la JList en base al ArrayList<Usuario>. 
    private void actualizarLista() {
        dlm.clear();
        for (Usuario usuario : listaUsuarios) {
            dlm.addElement(usuario);
        }
    }
    
    //validarUsuario para el login
    public static Usuario validarUsuario(String usuario, String contrasena) {
        ArrayList<Usuario> usuarios = cargarUsuarios(); // Cargar los usuarios desde el archivo
        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuario) && u.getContrasena().equals(contrasena)) {
                return u; // Usuario encontrado
            }
        }
        return null; // Usuario no encontrado
    }
    
    
    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuUsuarios frame = new MenuUsuarios();
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
	public MenuUsuarios() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
		setTitle("LPB Basketball - Menú de Usuarios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 908, 806);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 243, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		
		fader = new BackgroundFader();

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelUsuarios = new JPanel();
		panelUsuarios.setBackground(new Color(255, 243, 204));
		contentPane.add(panelUsuarios, BorderLayout.WEST);
		
		// creo el modelo de datos de la lista
		dlm = new DefaultListModel<Usuario>();
		 // Cargar usuarios desde archivo
	    // Cargar usuarios desde archivo
	    listaUsuarios = cargarUsuarios();
	    actualizarLista();
	
		scrollPane = new JScrollPane();
		GroupLayout gl_panelUsuarios = new GroupLayout(panelUsuarios);
		gl_panelUsuarios.setHorizontalGroup(
			gl_panelUsuarios.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelUsuarios.createSequentialGroup()
					.addGap(19)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelUsuarios.setVerticalGroup(
			gl_panelUsuarios.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelUsuarios.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		listUsuarios = new JList<>();
		listUsuarios.setModel(dlm); // ASOCIAR EL MODELO A LA LISTA
		scrollPane.setViewportView(listUsuarios);
		panelUsuarios.setLayout(gl_panelUsuarios);
		
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
		
		// Generar opciones del ComboBox a partir del enum
        comboBoxRol = new JComboBox<>();
        for (Rol rol : Rol.values()) {
            comboBoxRol.addItem(rol.name());
        }
		panel_2.add(comboBoxRol);
		comboBoxRol.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
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
		
		
		actualizarLista();
	}


	
    

    @Override
    public void actionPerformed(ActionEvent e) {
    	 Object source = e.getSource();

    	    if (source == btnGuardar) {
    	        String usuario = textUsuario.getText().trim();
    	        String contrasena = new String(passwordField.getPassword());
    	        String rolNombre = (String) comboBoxRol.getSelectedItem();	

    	        if (usuario.isEmpty() || contrasena.isEmpty()) {
    	            JOptionPane.showMessageDialog(this, "Por favor, rellena todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    	            return;
    	        }

    	       
    	     // Crear el usuario y agregarlo a la lista
    	        Usuario nuevoUsuario = new Usuario(
    	            usuario,
    	            contrasena,
    	            Rol.valueOf(rolNombre.toUpperCase()) // Convertir a Rol
    	        );
    	        listaUsuarios.add(nuevoUsuario);
    	        
    	        
    	        // Actualizar la lista y guardar cambios
    	        actualizarLista();
    	        guardarUsuarios(listaUsuarios);

    	        JOptionPane.showMessageDialog(this, "Usuario guardado correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
    	    }
    	    if (source == btnEliminar) {
    	        int index = listUsuarios.getSelectedIndex();
    	        if (index >= 0) {
    	            listaUsuarios.remove(index);

    	            // Actualizar la lista y guardar cambios
    	            actualizarLista();
    	           guardarUsuarios(listaUsuarios);

    	            JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
    	        } else {
    	            JOptionPane.showMessageDialog(this, "Selecciona un usuario para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    	        }
    	    }

    }

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

