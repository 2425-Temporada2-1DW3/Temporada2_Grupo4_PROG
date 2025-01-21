package LPB;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import LPBCLASES.BackgroundFader;
import LPBCLASES.BotonRedondeado;
import LPBCLASES.TextoRedondeado;


import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

import java.awt.FlowLayout;
import java.awt.Font;

import LPBCLASES.Jugador;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;

public class MenuJugador extends JFrame implements ActionListener, MouseListener, WindowListener, Serializable {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textDorsal;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblNombre;
	private JPanel panel_4;
	private JLabel lblPosicion;
	private JComboBox<String> comboBox;
	private JPanel panel_5;
	private JLabel lblDorsal;
	private JPanel panel_6;
	private FlowLayout flowLayout_1;
	private JButton btnVolver;
	private BackgroundFader fader;
	private JPanel panel_3;
	private JPanel panel_7;
	private JButton btnSeleccionarImagen;
	private JLabel lblFoto;
	private JButton btnGuardar;
	private JPanel panel_9;
	private JPanel panel_8;
	private JPanel panel_10;
	private JLabel lblJugador;
	private ImageIcon logo;
	private JLabel labelLogo;
	private JPanel panel_11;
	private JList<Jugador> listJugadores;
	private DefaultListModel<Jugador> dlm;
	private JButton btnAgregar;
	
	private int contador = 0;
	private boolean datosmodificados = false;
	private boolean datosguardados = false;
	
	private JPanel panel_12;
	private JLabel lblJugadoresTotales;
	private JLabel lblContador;
	private JButton btnEliminar;
	private JLabel lblApellido;
	private JTextField textApellido;
	private BotonRedondeado btnSeleccionar;
	private BotonRedondeado btnGuardarSeleccion;
	private JPanel panel_13;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuJugador frame = new MenuJugador();
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
	public MenuJugador() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
		setTitle("LPB Basketball - Menú Jugadores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1168, 763);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 243, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		// ESTO HAY QUE PONERLO COÑO
		addWindowListener(this);
		
		fader = new BackgroundFader();

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		panel_9 = new JPanel();
		contentPane.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.X_AXIS));
		
		panel_8 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_8.getLayout();
		flowLayout.setHgap(10);
		flowLayout.setVgap(80);
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_9.add(panel_8);
		
		lblJugador = new JLabel("Jugadores");
		lblJugador.setFont(new Font("SansSerif", Font.BOLD, 40));
		panel_8.add(lblJugador);
		
		panel_10 = new JPanel();
		panel_9.add(panel_10);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 243, 205));
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 243, 205));
		panel.add(panel_3);
		
		panel_7 = new JPanel();
		panel_7.setBackground(new Color(255, 243, 205));
		panel.add(panel_7);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 243, 205));
		contentPane.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 243, 205));
		panel_1.add(panel_2);
		
		lblNombre = new JLabel("Nombre ");
		lblNombre.setFont(new Font("SansSerif", Font.PLAIN, 18));
		panel_2.add(lblNombre);
		
		textNombre = new TextoRedondeado(20);
		panel_2.add(textNombre);
		textNombre.setColumns(10);
		
		lblApellido = new JLabel("Apellidos");
		lblApellido.setFont(new Font("SansSerif", Font.PLAIN, 18));
		panel_2.add(lblApellido);
		
		textApellido = new TextoRedondeado(20);
		panel_2.add(textApellido);
		textApellido.setColumns(10);
		
		panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 243, 205));
		panel_1.add(panel_4);
		
		lblPosicion = new JLabel("Posicion");
		lblPosicion.setFont(new Font("SansSerif", Font.PLAIN, 18));
		panel_4.add(lblPosicion);
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("Base");
		comboBox.addItem("Escolta");
		comboBox.addItem("Alero");
		comboBox.addItem("Ala Pivot");
		comboBox.addItem("Pivot");
		panel_4.add(comboBox);
		
		panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 243, 205));
		panel_1.add(panel_5);
		
		lblDorsal = new JLabel("Dorsal");
		lblDorsal.setFont(new Font("SansSerif", Font.PLAIN, 18));
		panel_5.add(lblDorsal);
		
		textDorsal = new TextoRedondeado(20);
		panel_5.add(textDorsal);
		textDorsal.setColumns(2);
		
		panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 243, 205));
		flowLayout_1 = (FlowLayout) panel_6.getLayout();
		flowLayout_1.setHgap(40);
		flowLayout_1.setAlignment(FlowLayout.TRAILING);
		panel_1.add(panel_6);
		
		btnEliminar = new BotonRedondeado("Eliminar");
		btnEliminar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnEliminar.setBounds(327, 445, 200, 40);
		btnEliminar.setBackground(new Color(0xf46b20));
		btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        btnEliminar.addMouseListener(this);
		btnEliminar.addActionListener(this);
		
		btnSeleccionar = new BotonRedondeado("Eliminar");
		btnSeleccionar.addActionListener(this);
		
		btnGuardarSeleccion = new BotonRedondeado("Eliminar");
		btnGuardarSeleccion.addActionListener(this);
		
		btnGuardarSeleccion.setText("Guardar Seleccion");
		btnGuardarSeleccion.setForeground(Color.WHITE);
		btnGuardarSeleccion.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnGuardarSeleccion.setFocusPainted(false);
		btnGuardarSeleccion.setBackground(new Color(244, 107, 32));
		panel_6.add(btnGuardarSeleccion);
		
		btnSeleccionar.setText("Seleccionar");
		btnSeleccionar.setForeground(Color.WHITE);
		btnSeleccionar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnSeleccionar.setFocusPainted(false);
		btnSeleccionar.setBackground(new Color(244, 107, 32));
		
		panel_6.add(btnSeleccionar);
		panel_6.add(btnEliminar);
		
		btnGuardar = new BotonRedondeado("Guardar");
		btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnGuardar.setBounds(327, 445, 200, 40);
		btnGuardar.setBackground(new Color(0xf46b20));
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFocusPainted(false);
		btnGuardar.addMouseListener(this);
		btnGuardar.addActionListener(this);
		
		btnAgregar = new BotonRedondeado("Agregar");
		btnAgregar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnAgregar.setBounds(327, 445, 200, 40);
		btnAgregar.setBackground(new Color(0xf46b20));
		btnAgregar.setForeground(Color.WHITE);
		btnAgregar.setFocusPainted(false);
		btnAgregar.addMouseListener(this);
		btnAgregar.addActionListener(this);
		panel_6.add(btnAgregar);
		panel_6.add(btnGuardar);
		
		btnVolver = new BotonRedondeado("Volver");
		btnVolver.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnVolver.setBounds(327, 445, 200, 40);
		btnVolver.setBackground(new Color(0xf46b20));
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setFocusPainted(false);
		btnVolver.addMouseListener(this);
		btnVolver.addActionListener(this);
		panel_6.add(btnVolver);
		
		
		btnSeleccionarImagen = new BotonRedondeado("Seleccionar Imagen");
		btnSeleccionarImagen.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnSeleccionarImagen.setBounds(327, 445, 100, 20);
		btnSeleccionarImagen.setBackground(new Color(0xf46b20));
		btnSeleccionarImagen.setForeground(Color.WHITE);
		btnSeleccionarImagen.setFocusPainted(false);
		btnSeleccionarImagen.addMouseListener(this);
		btnSeleccionarImagen.addActionListener(this);
		panel_7.add(btnSeleccionarImagen);
		
		lblFoto = new JLabel();
        lblFoto.setBounds(317, 10, 120, 120);
        lblFoto.setHorizontalAlignment(JLabel.CENTER);
		panel_3.add(lblFoto);
		
		
		logo = new ImageIcon(getClass().getResource("/imagenes/logo220.png"));
		panel_10.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		labelLogo = new JLabel(logo);
		labelLogo.setFont(new Font("Arial", Font.BOLD, 16));
		labelLogo.setBounds(317, 10, 120, 120);
		panel_10.add(labelLogo);
		
		panel_11 = new JPanel();
		contentPane.add(panel_11);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		// Creo el modelo de datos de la lista
		
		dlm = new DefaultListModel<Jugador>();
		
		// Creo la lista		
		
		listJugadores = new JList<Jugador>();
		
		// Asocio el modelo de datos a la lista
		
		listJugadores.setModel(dlm);		
		
		JScrollPane scrollPane = new JScrollPane(listJugadores);
		
		panel_11.add(scrollPane, BorderLayout.WEST);
		
		panel_12 = new JPanel();
		panel_11.add(panel_12, BorderLayout.SOUTH);
		
		lblJugadoresTotales = new JLabel("Jugadores en la Liga:");
		panel_12.add(lblJugadoresTotales);
		

		cargarDatos();
		contador = dlm.getSize();
		
		lblContador = new JLabel(""+ contador);
		panel_12.add(lblContador);
		
		panel_13 = new JPanel();
		panel_11.add(panel_13, BorderLayout.EAST);
		panel_13.setLayout(new MigLayout("", "[]", "[]"));
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		Object o = e.getSource();
		
		if (o == btnGuardar) {
		
			JOptionPane.showMessageDialog(this,(String)"Datos guardados correctamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
			guardarDatos();
			datosguardados = true; // Para que no salga el mensaje de "¿Deseas guardar?"
			datosmodificados = false; 
		
		} else if (o == btnVolver) {
			
			
			
		} else if (o == btnSeleccionarImagen){
		
			seleccionarImagen();

		} else if (o == btnAgregar) {
			
			if (textNombre.getText().isEmpty() & textDorsal.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Nombre y Dorsal no introducidos");
			} else if (textNombre.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Nombre no introducido");
			} else if (textDorsal.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Dorsal no introducido");
			} else if (Integer.parseInt(textDorsal.getText()) >= 100 || Integer.parseInt(textDorsal.getText()) <= 0) {
				JOptionPane.showMessageDialog(null, "El numero de Dorsal tiene que estar entre los numeros 1 y 99");
			} else {
				agregarJugador();
			} 
		
		} else if (o == btnEliminar) {
			
			eliminarJugador();
		} else if (o == btnSeleccionar) {
			seleccionarJugador();
		} else if (o == btnGuardarSeleccion) {
			editarSeleccion();
		} 
		
        // Hacer visible la ventana
		
        setVisible(true);
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
		// TODO Auto-generated method stub
		Object o = e.getSource();
		
		if (o == btnGuardar) {
			fader.fadeBackground(btnGuardar, btnGuardar.getBackground(), new Color(0xff7f50));
		} else if (o == btnVolver) {
			fader.fadeBackground(btnVolver, btnVolver.getBackground(), new Color(0xff7f50));
		} else if (o == btnGuardarSeleccion) {
			fader.fadeBackground(btnGuardarSeleccion, btnGuardarSeleccion.getBackground(), new Color(0xff7f50));
		} else if (o == btnAgregar) {
			fader.fadeBackground(btnAgregar, btnAgregar.getBackground(), new Color(0xff7f50));
		} else if (o == btnSeleccionarImagen) {
			fader.fadeBackground(btnSeleccionarImagen, btnSeleccionarImagen.getBackground(), new Color(0xff7f50));
		} else if (o == btnEliminar) {
			fader.fadeBackground(btnEliminar, btnEliminar.getBackground(), new Color(0xff7f50));
		} else if (o == btnSeleccionar) {
			fader.fadeBackground(btnSeleccionar, btnSeleccionar.getBackground(), new Color(0xff7f50));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();

		if (o == btnGuardar) {
			fader.fadeBackground(btnGuardar, btnGuardar.getBackground(), new Color(0xf46b20));
		} else if (o == btnVolver) {
			fader.fadeBackground(btnVolver, btnVolver.getBackground(), new Color(0xf46b20));
		} else if (o == btnSeleccionarImagen) {
			fader.fadeBackground(btnSeleccionarImagen, btnSeleccionarImagen.getBackground(), new Color(0xf46b20));
		} else if (o == btnGuardarSeleccion) {
			fader.fadeBackground(btnGuardarSeleccion, btnGuardarSeleccion.getBackground(), new Color(0xf46b20));
		} else if (o == btnAgregar) {
			fader.fadeBackground(btnAgregar, btnAgregar.getBackground(), new Color(0xf46b20));
		} else if (o == btnSeleccionar) {
			fader.fadeBackground(btnSeleccionar, btnSeleccionar.getBackground(), new Color(0xf46b20));
		} else if (o == btnEliminar) {
			fader.fadeBackground(btnEliminar, btnEliminar.getBackground(), new Color(0xf46b20));
		} 
	}
	
	
	
	
	// --------------------------------- METODOS --------------------------------------- //
	
	
	
	
	public void agregarJugador() {
		
			// Cojemos los campos necesarios para crear un nuevo objeto Jugador
		
		String nombre = "" + textNombre.getText() + " " + textApellido.getText();
		String posicion = (String)comboBox.getSelectedItem();
		int dorsal = Integer.parseInt(textDorsal.getText());
		ImageIcon foto = (ImageIcon) lblFoto.getIcon();
	
		Jugador nuevoJugador = new Jugador (nombre , posicion , dorsal , 0, foto);
		
		if (dlm.contains(nuevoJugador)) {
			
			// Si ya esta en la lista
			
			JOptionPane.showMessageDialog(this,(String)"Error. El valor "+nuevoJugador+" ya esta en la lista","Error",JOptionPane.ERROR_MESSAGE,null);
		}
		else {
			
			// Si NO esta en la lista
			// Busco la posicion donde insertar el valor
			
			int posiciondlm = 0;
			int numeroElementos = dlm.getSize();
			Jugador valorLista;
			while(posiciondlm < numeroElementos) {
				
				// Compruebo si es la posicion a insertar
				
				valorLista = dlm.get(posiciondlm);
				if(nuevoJugador.compareTo(valorLista) < 0) {
					
					// Si he encontrado la posicion
					// Salgo del bucle
					
					break;
				}
				posiciondlm = posiciondlm + 1;
			}
			
			// Lo Inserto en su posicion
			
			dlm.add(posiciondlm, nuevoJugador);
			contador++; // Actualizamos el contador
			lblContador.setText("" + contador); // Actualizamos el JLabel
			datosmodificados = true; 
		}
	}
	
	
	public void seleccionarImagen() {
		
		 JFileChooser fileChooser = new JFileChooser();
         fileChooser.setDialogTitle("Seleccionar Imagen del Jugador");
         fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imagenes JPG & PNG", "jpg", "png"));
         
         int resultado = fileChooser.showOpenDialog(null);

         if (resultado == JFileChooser.APPROVE_OPTION) {
        	 
             // Obtener el archivo seleccionado
        	 
             String rutaArchivo = fileChooser.getSelectedFile().getAbsolutePath();

             // Crear un ImageIcon con la imagen seleccionada
             
             ImageIcon fotoSeleccionada = new ImageIcon(rutaArchivo);

             Jugador jugador = new Jugador();
             
			 // Actualizar la foto en el jugador
             
             jugador.setFoto(fotoSeleccionada);

             // Mostrar la foto en el JLabel
             
             lblFoto.setIcon(fotoSeleccionada);
         }
	}

	
	private void guardarDatos(){
		
		// Guardo los datos en jugadores.ser
		
		FileOutputStream fos;
		ObjectOutputStream oos;
		try {
			
			// Abro el fichero en modo escritura
			
			fos = new FileOutputStream("jugadores.ser");
			oos = new ObjectOutputStream (fos);
			
			// Recorro la lista con los datos
			
			Jugador nuevoJugador;
			for(int posicion = 0; posicion < dlm.getSize(); posicion ++) {
				
				// Escribo el elemento
				
				nuevoJugador = dlm.get(posicion);
				oos.writeObject(nuevoJugador);
				contador = dlm.getSize();
			}
			
			// Libero los recursos
			
			oos.close();
			fos.close();
			
		} catch (FileNotFoundException e) {
			
			// Si no encuentro el fichero
			
			JOptionPane.showMessageDialog(this,(String)"Fichero no encontrado","error",JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			
			// Si se produce una excepcion de entrada/salida
			
		       JOptionPane.showMessageDialog(this, "Error de Entrada/Salida al guardar el archivo: " + "jugadores.ser" + "\n" + e.getMessage(), "Error de IO", JOptionPane.ERROR_MESSAGE);
		        e.printStackTrace();
		}		
	}
	
	private void cargarDatos() {

		try (FileInputStream fis = new FileInputStream("jugadores.ser");
			     ObjectInputStream ois = new ObjectInputStream(fis)) {
			    while (true) {
			        try {
			            Jugador jugador = (Jugador) ois.readObject();
			            dlm.addElement(jugador);
			        } catch (EOFException eof) {
			        	
			            // Fin del archivo alcanzado, salimos del bucle
			        	
			            break;
			        }
			    }
			} catch (FileNotFoundException e) {
			    JOptionPane.showMessageDialog(this, "Archivo no encontrado: jugadores.ser", "Error", JOptionPane.ERROR_MESSAGE);
			} catch (IOException | ClassNotFoundException e) {
			    JOptionPane.showMessageDialog(this, "Error al cargar datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
}
	
	
	private void eliminarJugador() {
		
			// Seleccionamos el indice que queremos eliminar
		
		int[] Indice = listJugadores.getSelectedIndices();
		if (Indice.length > 0) {
			for (int i = Indice.length - 1; i >= 0; i--) {
				dlm.removeElementAt(Indice[i]);
				
					// Actualizacmos el contador
				
				contador = dlm.getSize();
				
					// Actualizamos el JLabel;
				
				lblContador.setText(String.valueOf(contador)); 
				datosmodificados = true;
			}
		}
	}
	
	
	private void seleccionarJugador() {
		
			// Seleccionamos el indice
		
		int Indice = listJugadores.getSelectedIndex();
		
			// Cojemos los datos necesarios para crear un nuevo 

		Jugador jugadorSeleccionado = dlm.get(Indice);
		String nombreSelectCompleto = "" + jugadorSeleccionado.getNombre();
		
			// Dividimos el nombre completo en Nombre y Apellidos
		
		 String[] partes = nombreSelectCompleto.split(" ");
		 
		 String nombreSelect = partes[0];   // El primer valor es el nombre
	     String apellidosSelect = partes[1]; // El segundo valor es el apellido
		 
		String posicionSelect = jugadorSeleccionado.getPosicion();
		int dorsalSelect = jugadorSeleccionado.getNumeroCamiseta();
		
			// Introducimos los datos en los campos necesarios
		
		textNombre.setText(nombreSelect);
		textApellido.setText(apellidosSelect);
		comboBox.setSelectedItem(posicionSelect);
		String dorsalSelectString = String.valueOf(dorsalSelect);
		textDorsal.setText(dorsalSelectString);
		
	}
	
	
	private void editarSeleccion() {
		int Indice = listJugadores.getSelectedIndex();

		String nombreSelect = "" + textNombre.getText();
		String apellidoSelect = "" + textApellido.getText();
		String nombre = "" + nombreSelect + " " + apellidoSelect;
		String posicion = (String)comboBox.getSelectedItem();
		int dorsal = Integer.parseInt(textDorsal.getText());
		ImageIcon foto = (ImageIcon) lblFoto.getIcon();
		
		Jugador nuevoJugador = new Jugador (nombre , posicion , dorsal , 0, foto);
		dlm.set(Indice, nuevoJugador);
		
	}
	
	
	

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		   setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
		// Compreubo si los datos no han sido modificados
		
		if(datosmodificados) {
			
			// Si los datos han sido modificados
			
			int opcion = JOptionPane.showConfirmDialog(this,(String)"Los datos han sido modificados, ¿Desea guardarlos?","Info",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null);
			switch (opcion) {
			case JOptionPane.YES_OPTION: // opcion "Si"
				
					// Guardo los datos
					// Implemento el metodo guardarDatos()
				
				guardarDatos();
				break;
				
				// case JOptionPane.NO_OPTION: // opcion "No"
				// break;
				
			case JOptionPane.CANCEL_OPTION: 
			case JOptionPane.CLOSED_OPTION:
				
				// Si pulsa Cancelar
				// Si cierra la ventana
				
				return;
			}
		} else if (datosguardados) {
			
			// Salgo de la aplicacion
			
			System.exit(0);
		}
		
		// Salgo de la aplicacion
		
		System.exit(0);
	}
	

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
