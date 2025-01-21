package LPB;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
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


import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import LPB.Menu;
import LPBCLASES.BackgroundFader;
import LPBCLASES.BotonRedondeado;
import LPBCLASES.PasswordRedondeado;
import LPBCLASES.TextoRedondeado;
import LPBCLASES.Usuario;

public class MenuUsuarios extends JFrame implements ActionListener, MouseListener,Serializable {

    private static final long serialVersionUID = 1L;
    private JPanel panelSuperior;
    private JPanel panelInferior;
    private ImageIcon logo;
    private JLabel labelLogo;
    private JLabel titulo;
    private JTextField textUsuario;
    private JPasswordField passwordField;
    private JComboBox<String> comboBoxRol;
    private JButton btnGuardar, btnEliminar, btnVolver;
    private DefaultListModel<Usuario> dlm;
    private JList<Usuario> listUsuarios;
    private ArrayList<Usuario> listaUsuarios;
    private BackgroundFader fader;
    private JScrollPane scrollPane;
    private JLabel lblUsuario;
    private JLabel lblContrasena;
    private JLabel lblRol;
    private Object source;
    private String usuario, contrasena, rol;
    private Usuario usuarioExistente;
    private Usuario nuevoUsuario;
    private int usuarioSeleccionadoIndex = -1;

    private static final String ARCHIVO_USUARIOS = "usuarios.ser";
    
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

    public MenuUsuarios() {
    	
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
        setTitle("LPB Basketball - Menú de Usuarios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 550);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        fader = new BackgroundFader();

        panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(255, 243, 205));
        panelSuperior.setBounds(0, 0, 836, 110);
        panelSuperior.setLayout(null);

        logo = new ImageIcon(getClass().getResource("/imagenes/logo150.png"));
        labelLogo = new JLabel(logo);
        labelLogo.setBounds(686, -20, 150, 150);
        panelSuperior.add(labelLogo);

        titulo = new JLabel("Usuarios");
        titulo.setBounds(10, 28, 306, 47);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 50));
        titulo.setForeground(new Color(0x13427e));
        panelSuperior.add(titulo);

        getContentPane().add(panelSuperior);

        // Panel inferior
        panelInferior = new JPanel();
        panelInferior.setBackground(new Color(204, 153, 102));
        panelInferior.setBounds(0, 110, 836, 403);
        panelInferior.setLayout(null);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 20, 300, 300);
        dlm = new DefaultListModel<Usuario>();
        listaUsuarios = cargarUsuarios();
	    actualizarLista();

        listUsuarios = new JList<>();
        listUsuarios.setModel(dlm);
        listUsuarios.setFont(new Font("SansSerif", Font.PLAIN, 16));
        scrollPane.setViewportView(listUsuarios);
        panelInferior.add(scrollPane);

        listUsuarios.addListSelectionListener(e -> {
		    if (!e.getValueIsAdjusting()) { // Asegurarse de que no se actúe en eventos duplicados
		        Usuario usuarioSeleccionado = listUsuarios.getSelectedValue();
		        usuarioSeleccionadoIndex = listUsuarios.getSelectedIndex(); // Guardar el índice del usuario seleccionado
		        if (usuarioSeleccionado != null) {
		            textUsuario.setText(usuarioSeleccionado.getUsuario());
		            passwordField.setText(usuarioSeleccionado.getContrasena());
		            comboBoxRol.setSelectedItem(usuarioSeleccionado.getRol());
		        }
		    }
		});

        lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblUsuario.setBounds(350, 30, 100, 30);
        panelInferior.add(lblUsuario);

        textUsuario = new TextoRedondeado(20);
        textUsuario.setColumns(10);
        textUsuario.setFont(new Font("SansSerif", Font.PLAIN, 16));
        textUsuario.setBounds(450, 30, 200, 30);
        panelInferior.add(textUsuario);

        lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblContrasena.setBounds(350, 80, 100, 30);
        panelInferior.add(lblContrasena);

        passwordField = new PasswordRedondeado(20);
        passwordField.setColumns(10);
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        passwordField.setBounds(450, 80, 200, 30);
        panelInferior.add(passwordField);

        lblRol = new JLabel("Rol:");
        lblRol.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblRol.setBounds(350, 130, 100, 30);
        panelInferior.add(lblRol);

        comboBoxRol = new JComboBox<>();
        String[] roles = {"Administrador", "Árbitro", "Entrenador", "Usuario"};
        for (String rol : roles) {
            comboBoxRol.addItem(rol);
        }
        comboBoxRol.setFont(new Font("SansSerif", Font.PLAIN, 16));
        comboBoxRol.setBounds(450, 130, 200, 30);
        panelInferior.add(comboBoxRol);

        btnGuardar = new BotonRedondeado("Guardar", null);
        btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnGuardar.setBackground(new Color(0x13427E));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBounds(350, 200, 100, 40);
        btnGuardar.setFocusPainted(false);
        btnGuardar.addMouseListener(this);
        btnGuardar.addActionListener(this);
        panelInferior.add(btnGuardar);

        btnEliminar = new BotonRedondeado("Eliminar", null);
        btnEliminar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnEliminar.setBackground(new Color(0x13427E));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setBounds(470, 200, 100, 40);
        btnEliminar.setFocusPainted(false);
        btnEliminar.addMouseListener(this);
        btnEliminar.addActionListener(this);
        panelInferior.add(btnEliminar);

        btnVolver = new BotonRedondeado("Volver", null);
        btnVolver.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnVolver.setBackground(new Color(0x13427E));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBounds(590, 200, 100, 40);
        btnVolver.setFocusPainted(false);
		btnVolver.addMouseListener(this);
		panelInferior.add(btnVolver);
		
		actualizarLista();

        getContentPane().add(panelInferior); ////////////////////////////////////////
    }

    
    
    
    private void actualizarLista() {
        dlm.clear();
        for (Usuario usuario : listaUsuarios) {
            dlm.addElement(usuario);
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
    
    public static Usuario validarUsuario(String usuario, String contrasena) {
        ArrayList<Usuario> usuarios = cargarUsuarios(); // Cargar los usuarios desde el archivo
        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuario) && u.getContrasena().equals(contrasena)) {
                return u; // Usuario encontrado
            }
        }
        return null; // Usuario no encontrado
    }

    public static void guardarUsuarios(ArrayList<Usuario> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_USUARIOS))) {
            oos.writeObject(usuarios);
            JOptionPane.showMessageDialog(  null, "Usuario guardado correctamente. ", "Información", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
        	 JOptionPane.showMessageDialog(  null,"Error al guardar los usuarios: " + e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
        
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	 source = e.getSource();

    	 if (source == btnGuardar) {
    		    usuario = textUsuario.getText().trim();
    		    contrasena = new String(passwordField.getPassword());
    		    rol = (String) comboBoxRol.getSelectedItem();

    		    if (usuario.isEmpty() || contrasena.isEmpty()) {
    		        JOptionPane.showMessageDialog(this, "Por favor, rellena todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    		        return;
    		    }

    		    // Si hay un usuario seleccionado, actualizamos sus datos
    		    if (usuarioSeleccionadoIndex >= 0) {
    		        usuarioExistente = listaUsuarios.get(usuarioSeleccionadoIndex);
    		        usuarioExistente.setUsuario(usuario);
    		        usuarioExistente.setContrasena(contrasena);
    		        usuarioExistente.setRol(rol);

    		        // Actualizar la lista visual
    		        actualizarLista();

    		        JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);

    		    } else {
    		        // Si no hay un usuario seleccionado, se crea uno nuevo
    		        nuevoUsuario = new Usuario(usuario, contrasena, rol);
    		        listaUsuarios.add(nuevoUsuario);
    		        actualizarLista();
    		    }

    		    // Guardar los cambios en el archivo
    		    guardarUsuarios(listaUsuarios);

    		    // Limpiar los campos y reiniciar el índice
    		    textUsuario.setText("");
    		    passwordField.setText("");
    		    comboBoxRol.setSelectedIndex(0);
    		    usuarioSeleccionadoIndex = -1;
    		}

    	    if (source == btnEliminar) {
    	        int index = listUsuarios.getSelectedIndex();
    	        if (index >= 0) {
    	            listaUsuarios.remove(index);

    	            // Actualizar la lista y guardar cambios
    	            actualizarLista();
    	           guardarUsuarios(listaUsuarios);

    	        } else {
    	            JOptionPane.showMessageDialog(this, "Selecciona un usuario para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    	        }
    	        
    	     // Limpiar los campos y reiniciar el índice
    		    textUsuario.setText("");
    		    passwordField.setText("");
    		    comboBoxRol.setSelectedIndex(0);
    		    usuarioSeleccionadoIndex = -1;
    	    }
    	    
    	    if (source == btnVolver) {
    	    	System.out.println("Botón Volver presionado"); // Debugging
    	        new Menu("Administrador", "Admin").setVisible(true);
    	        this.dispose(); // Close the current window
    	    }
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object o = e.getSource();
		if (o == btnGuardar) {
			fader.fadeBackground(btnGuardar, btnGuardar.getBackground(), new Color(0xfe9f2e)); // Naranja
		} else if (o == btnEliminar) {
			fader.fadeBackground(btnEliminar, btnEliminar.getBackground(), new Color(0xfe9f2e)); // Naranja
		} else if (o == btnVolver) {
			fader.fadeBackground(btnVolver, btnVolver.getBackground(), new Color(0xfe9f2e)); // Naranja
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object o = e.getSource();
		if (o == btnGuardar) {
			fader.fadeBackground(btnGuardar, btnGuardar.getBackground(), new Color(0x13427E)); // Azul original
		} else if (o == btnEliminar) {
			fader.fadeBackground(btnEliminar, btnEliminar.getBackground(), new Color(0x13427E)); // Azul original
		} else if (o == btnVolver) {
			fader.fadeBackground(btnVolver, btnVolver.getBackground(), new Color(0x13427E)); // Azul original
		}
	}

    
}
