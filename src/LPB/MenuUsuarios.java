package LPB;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import LPBCLASES.Usuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class MenuUsuarios extends JFrame implements ActionListener, Serializable {

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
	private JLabel lblMenuUsuarios;
	private JList<Usuario> listUsuarios;
	private DefaultListModel<Usuario> dlm;
	private JPasswordField passwordField;
	private JButton btnEliminar;
	private boolean datosModificados = false;

	

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
		setTitle("Menú Usuarios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1147, 577);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelUsuarios = new JPanel();
		fl_panelUsuarios = (FlowLayout) panelUsuarios.getLayout();
		fl_panelUsuarios.setHgap(300);
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
		panelCampos.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblUsuario = new JLabel("Usuario");
		panel.add(lblUsuario);
		
		textUsuario = new JTextField();
		panel.add(textUsuario);
		textUsuario.setColumns(10);
		
		panel_1 = new JPanel();
		panelCampos.add(panel_1);
		
		lblContrasena = new JLabel("Contraseña");
		panel_1.add(lblContrasena);
		
		passwordField = new JPasswordField();
		panel_1.add(passwordField);
		
		panel_2 = new JPanel();
		panelCampos.add(panel_2);
		
		lblRol = new JLabel("Rol");
		panel_2.add(lblRol);
		
		comboBoxRol = new JComboBox<String>();
		panel_2.add(comboBoxRol);
		
		// añado elementos al combobox
		comboBoxRol.addItem(" Administrador");
		comboBoxRol.addItem(" Arbitro");
		
		panel_3 = new JPanel();
		panelCampos.add(panel_3);
		
		lblEquipo = new JLabel("Equipo");
		panel_3.add(lblEquipo);
		
		comboEquipo = new JComboBox<String>();
		panel_3.add(comboEquipo);
		
		// añado elementos al combobox
		comboEquipo.addItem(" Lakers");
		comboEquipo.addItem(" Bulls");
		comboEquipo.addItem(" Heat");
		comboEquipo.addItem(" Hawks");
		comboEquipo.addItem(" Warriors");
		comboEquipo.addItem(" Celtics");
		
		panel_4 = new JPanel();
		flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setHgap(140);
		panelCampos.add(panel_4);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(this);
		panel_4.add(btnGuardar);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(this);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		panel_4.add(btnEliminar);
		panel_4.add(btnVolver);
		
		panelTitulo = new JPanel();
		fl_panelTitulo = (FlowLayout) panelTitulo.getLayout();
		fl_panelTitulo.setVgap(40);
		contentPane.add(panelTitulo, BorderLayout.NORTH);
		
		lblMenuUsuarios = new JLabel("USUARIOS");
		panelTitulo.add(lblMenuUsuarios);
		
		
		
		// cargo los usuarios
		cargarUsuario();
	}

	public void actionPerformed(ActionEvent e) {
		// obtengo sobre que componente se ha realizado la accion
		Object o = e.getSource();
		
		if (o == btnGuardar) {
			if (dlm.getSize() == 0) {
			    JOptionPane.showMessageDialog(this, "No hay usuarios para guardar.", "Error", JOptionPane.WARNING_MESSAGE);
			    return;
			} else {
				guardarUsuario();
			}
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
				// actualizo datos modificados 
				datosModificados = true;
			}
		}
		
	}
	
	// Metodoss
	
	public void guardarUsuario(){
		// guardo los datos en usuarios.guardar
		FileOutputStream fos;
		ObjectOutputStream oos;
		try {
			// abro el fichero en modo escritura
			fos = new FileOutputStream("usuarios.guardar");
			oos = new ObjectOutputStream (fos);
			
			// recorro la lista con los datos
			  char[] passwordChars = passwordField.getPassword();
		        String password = new String(passwordChars);
			Usuario valor = new Usuario(textUsuario.getText(),password);
			for(int posicion = 0; posicion < dlm.getSize(); posicion ++) {
				// escribo el elemento
				valor = dlm.get(posicion);
				oos.writeObject(valor);
				dlm.addElement(valor);
			}
			
			// libero los recursos
			oos.close();
			fos.close();
			
		} catch (FileNotFoundException e) {
			// Si no encuentro el fichero
			JOptionPane.showMessageDialog(this,(String)"Fichero no encontrado","error",JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			// Si se produce una excepcion de entrada/salida
			JOptionPane.showMessageDialog(this,(String)"Error de Entrada/Salida","error",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	
	public void cargarUsuario() {
		// cargo los datos de usuarios.guardar
				FileInputStream fis;
				ObjectInputStream ois;
			    try {
			    	// abro el fichero en modo lectura
					fis =new FileInputStream("usuarios.guardar");
					ois = new ObjectInputStream(fis);
					
					// recorro el fichero con los datos
					// mientras que no sea el final del fichero leo los datos
					Usuario valor;
					// fis.available son los datos/objetos que hay en el fichero, el While entero está en los apuntes
					while(fis.available() > 0){
						valor = (Usuario)ois.readObject();
						// añado el objeto a la lista
						this.dlm.addElement(valor);
						} 
					
				}
			    catch (FileNotFoundException e) {
					// Cuando no se ha encontrado el archivo
			    	dlm.addElement(new Usuario("Admin", "admin123"));
			    	guardarUsuario();
			    	  return; // Salimos porque el archivo fue recién creado
				} catch (IOException e) {
					// Cuando hay un error de Entrada/Salida
					JOptionPane.showMessageDialog(this,(String)"Error de Entrada/Salida" + e.getMessage(),"error",JOptionPane.INFORMATION_MESSAGE);
				} catch (ClassNotFoundException e) {
					// Cuando no se encuentra la clase Racional (en este caso)
					JOptionPane.showMessageDialog(this,(String)"Clase no encontrada","error",JOptionPane.INFORMATION_MESSAGE);
				}
	}
	
	
	

}