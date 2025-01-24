package LPB;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import LPBCLASES.BackgroundFader;
import LPBCLASES.BotonRedondeado;
import LPBCLASES.TextoRedondeado;
import LPBCLASES.Jugador;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.imgscalr.Scalr;

public class MenuJugador extends JFrame implements ActionListener, MouseListener, WindowListener, Serializable {

    private static final long serialVersionUID = 1L;
    private static final String ARCHIVO_JUGADORES = "jugadores.ser";

    // Atributos de la clase
    private JPanel panelSuperior;
    private JPanel panelInferior;
    private ImageIcon logo;
    private JLabel labelLogo;
    private JLabel titulo;
    private JTextField textNombre;
    private JTextField textDorsal;
    private JTextField textApellido;
    private JComboBox<String> comboBoxPosicion;
    private JButton btnGuardar, btnEliminar, btnVolver, btnSeleccionarImagen, btnAgregar, btnSeleccionar, btnGuardarSeleccion;
    private DefaultListModel<Jugador> dlm;
    private JList<Jugador> listJugadores;
    private BackgroundFader fader;
    private JScrollPane scrollPane;
    private JLabel lblNombre;
    private JLabel lblDorsal;
    private JLabel lblPosicion;
    private JLabel lblApellido;
    private JLabel lblFoto;
    private JLabel lblJugadoresTotales;
    private JLabel lblContador;
    
    private int contador = 0;
    private boolean datosmodificados = false;
    private boolean datosguardados = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    try {
	    	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
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
        setTitle("LPB Basketball - Menú de Jugadores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1202, 550);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        fader = new BackgroundFader();
 
        panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(255, 243, 205));
        panelSuperior.setBounds(0, 0, 1188, 110);
        panelSuperior.setLayout(null);

        logo = new ImageIcon(getClass().getResource("/imagenes/logo150.png"));
        labelLogo = new JLabel(logo);
        labelLogo.setBounds(1038, -22, 150, 150);
        panelSuperior.add(labelLogo);

        titulo = new JLabel("Jugadores");
        titulo.setBounds(10, 22, 306, 64);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 50));
        titulo.setForeground(new Color(0x13427e));
        panelSuperior.add(titulo);
        getContentPane().add(panelSuperior);

        panelInferior = new JPanel();
        panelInferior.setBackground(new Color(204, 153, 102));
        panelInferior.setBounds(0, 110, 1188, 403);
        panelInferior.setLayout(null);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 20, 430, 360);
        dlm = new DefaultListModel<>();
        cargarDatos();
        cargarJugadores();
        
        listJugadores = new JList<>();
        listJugadores.setModel(dlm);
        listJugadores.setFont(new Font("SansSerif", Font.PLAIN, 16));
        scrollPane.setViewportView(listJugadores);
        panelInferior.add(scrollPane);
        
        lblNombre = new JLabel("Nombre:");
        lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNombre.setForeground(new Color(0x545454));
        lblNombre.setBounds(476, 193, 100, 30);
        panelInferior.add(lblNombre);

        textNombre = new TextoRedondeado(20);
        textNombre.setColumns(10);
        textNombre.setFont(new Font("SansSerif", Font.PLAIN, 16));
        textNombre.setBounds(586, 193, 200, 30);
        panelInferior.add(textNombre);

        lblApellido = new JLabel("Apellido:");
        lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
        lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblApellido.setForeground(new Color(0x545454));
        lblApellido.setBounds(476, 243, 100, 30);
        panelInferior.add(lblApellido);

        textApellido = new TextoRedondeado(20);
        textApellido.setColumns(10);
        textApellido.setFont(new Font("SansSerif", Font.PLAIN, 16));
        textApellido.setBounds(586, 243, 200, 30);
        panelInferior.add(textApellido);

        lblDorsal = new JLabel("Dorsal:");
        lblDorsal.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDorsal.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblDorsal.setForeground(new Color(0x545454));
        lblDorsal.setBounds(808, 193, 100, 30);
        panelInferior.add(lblDorsal);

        textDorsal = new TextoRedondeado(20);
        textDorsal.setColumns(10);
        textDorsal.setFont(new Font("SansSerif", Font.PLAIN, 16));
        textDorsal.setBounds(918, 193, 200, 30);
        panelInferior.add(textDorsal);

        lblPosicion = new JLabel("Posición:");
        lblPosicion.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPosicion.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblPosicion.setForeground(new Color(0x545454));
        lblPosicion.setBounds(808, 243, 100, 30);
        panelInferior.add(lblPosicion);

        comboBoxPosicion = new JComboBox<>();
        String[] posiciones = {"Base", "Escolta", "Alero", "Ala Pivot", "Pivot"};
        for (String pos : posiciones) {
            comboBoxPosicion.addItem(pos);
        }
        comboBoxPosicion.setFont(new Font("SansSerif", Font.PLAIN, 16));
        comboBoxPosicion.setBounds(918, 243, 200, 30);
        panelInferior.add(comboBoxPosicion);

        lblFoto = new JLabel("Foto del jugador");
        lblFoto.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
        lblFoto.setBounds(787, 20, 150, 150);
        panelInferior.add(lblFoto);
    
        btnSeleccionarImagen = new BotonRedondeado("Seleccionar Imagen", null);
        btnSeleccionarImagen.setForeground(new Color(255, 255, 255));
        btnSeleccionarImagen.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnSeleccionarImagen.setBounds(566, 74, 187, 40);
        btnSeleccionarImagen.setBackground(new Color(0xf46b20));
        btnSeleccionarImagen.addActionListener(this);
        btnSeleccionarImagen.setFocusPainted(false);
        btnSeleccionarImagen.addMouseListener(this);
        panelInferior.add(btnSeleccionarImagen);

        btnGuardar = new BotonRedondeado("Guardar", null);
        btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnGuardar.setBackground(new Color(0x13427E));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBounds(777, 307, 100, 40);
        btnGuardar.setFocusPainted(false);
        btnGuardar.addMouseListener(this);
        btnGuardar.addActionListener(this);
        panelInferior.add(btnGuardar);

        btnAgregar = new BotonRedondeado("Agregar", null);
        btnAgregar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnAgregar.setBackground(new Color(0x13427E));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setBounds(661, 307, 100, 40);
        btnAgregar.setFocusPainted(false);
        btnAgregar.addMouseListener(this);
        btnAgregar.addActionListener(this);
        panelInferior.add(btnAgregar);

        btnEliminar = new BotonRedondeado("Eliminar", null);
        btnEliminar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnEliminar.setBackground(new Color(0xf46b20));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setBounds(897, 307, 100, 40);
        btnEliminar.setFocusPainted(false);
        btnEliminar.addMouseListener(this);
        btnEliminar.addActionListener(this);
        panelInferior.add(btnEliminar);

        btnVolver = new BotonRedondeado("Volver", null);
        btnVolver.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnVolver.setBackground(new Color(0x404040));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBounds(1017, 307, 100, 40);
        btnVolver.setFocusPainted(false);
        btnVolver.addMouseListener(this);
        btnVolver.addActionListener(this);
        panelInferior.add(btnVolver);

        btnSeleccionar = new BotonRedondeado("Seleccionar", null);
        btnSeleccionar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnSeleccionar.setBackground(new Color(0x404040));
        btnSeleccionar.setForeground(Color.WHITE);
        btnSeleccionar.setBounds(512, 307, 139, 40);
        btnSeleccionar.setFocusPainted(false);
        btnSeleccionar.addMouseListener(this);
        btnSeleccionar.addActionListener(this);
        panelInferior.add(btnSeleccionar);

        btnGuardarSeleccion = new BotonRedondeado("Guardar Selección", null);
        btnGuardarSeleccion.setForeground(Color.WHITE);
        btnGuardarSeleccion.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnGuardarSeleccion.setFocusPainted(false);
        btnGuardarSeleccion.setBackground(new Color(244, 107, 32));
        btnGuardarSeleccion.addActionListener(this);
        panelInferior.add(btnGuardarSeleccion);

        lblJugadoresTotales = new JLabel("Jugadores en la Liga:");
        panelInferior.add(lblJugadoresTotales);

        contador = dlm.getSize();
        lblContador = new JLabel("" + contador);
        panelInferior.add(lblContador);
        getContentPane().add(panelInferior);
}

    // Métodos de funcionalidad
    public void agregarJugador() {
        String nombre = "" + textNombre.getText();
        String apellidos = "" + textApellido.getText();
        String posicion = (String) comboBoxPosicion.getSelectedItem();
        int dorsal = Integer.parseInt(textDorsal.getText());
        String photoPath = "";

        Jugador nuevoJugador = new Jugador(nombre, apellidos, posicion, dorsal, photoPath);

        if (dlm.contains(nuevoJugador)) {
            JOptionPane.showMessageDialog(this, "Error. El jugador " + nuevoJugador + " ya está en la lista", "Error", JOptionPane.ERROR_MESSAGE, null);
        } else {
            int posiciondlm = 0;
            int numeroElementos = dlm.getSize();
            Jugador valorLista;
            while (posiciondlm < numeroElementos) {
                valorLista = dlm.get(posiciondlm);
                if (nuevoJugador.compareTo(valorLista) < 0) {
                    break;
                }
                posiciondlm++;
            }
            dlm.add(posiciondlm, nuevoJugador);
            contador++;
            lblContador.setText("" + contador);
            datosmodificados = true;
        }
    }

    public void seleccionarImagen() {
        JFileChooser selectorArchivo = new JFileChooser();
        selectorArchivo.setDialogTitle("Seleccionar Imagen del Jugador");
        selectorArchivo.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes JPG & PNG", "jpg", "png"));

        int resultado = selectorArchivo.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = selectorArchivo.getSelectedFile();
            try {
                BufferedImage imagenOriginal = ImageIO.read(archivoSeleccionado);

                if (imagenOriginal == null) {
                    JOptionPane.showMessageDialog(this, "El archivo seleccionado no es una imagen.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                BufferedImage imagenEscalada = Scalr.resize(imagenOriginal, Scalr.Method.QUALITY, 150, 150);

                // Obtener el nombre y apellido del jugador
                String nombre = textNombre.getText().trim();
                String apellido = textApellido.getText().trim();

                // Asegurarse de que los campos no estén vacíos
                if (nombre.isEmpty() || apellido.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor ingrese el nombre y el apellido antes de seleccionar la imagen.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Crear el nombre del archivo con el formato "jugador_Nombre_Apellido"
                String nombreArchivo = nombre + "_" + apellido + ".png";
                File archivoDestino = new File("resources/imagenes/" + nombreArchivo);
                archivoDestino.getParentFile().mkdirs();
                ImageIO.write(imagenEscalada, "png", archivoDestino);

                lblFoto.setIcon(new ImageIcon(imagenEscalada));
                lblFoto.setText("");

                JOptionPane.showMessageDialog(null, "Imagen guardada correctamente en " + archivoDestino.getAbsolutePath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al procesar la imagen: " + e.getMessage());
            }
        }
    }



    private void cargarJugadores() {
        File archivo = new File(ARCHIVO_JUGADORES);
        if (!archivo.exists()) {
            System.out.println("El archivo " + ARCHIVO_JUGADORES + " no existe. Creando una nueva lista vacía.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_JUGADORES))) {
            while (true) {
                try {
                    Jugador jugador = (Jugador) ois.readObject();
                    dlm.addElement(jugador);
                } catch (EOFException eof) {
                    // Fin del archivo alcanzado, salir del bucle
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar los datos del jugador: " + e.getMessage());
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
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Archivo no encontrado: jugadores.ser", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o == btnAgregar) {
            if (textNombre.getText().isEmpty() && textDorsal.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nombre y Dorsal no introducidos");
            } else if (textNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nombre no introducido");
            } else if (textDorsal.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Dorsal no introducido");
            } else {
                try {
                    int dorsal = Integer.parseInt(textDorsal.getText());
                    if (dorsal < 1 || dorsal > 99) {
                        JOptionPane.showMessageDialog(null, "El número de Dorsal tiene que estar entre 1 y 99");
                    } else {
                        agregarJugador(); // Llama al método que agrega al jugador si todos los datos son válidos
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El campo Dorsal debe contener solo números.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (o == btnEliminar) {
            eliminarJugador();
        } else if (o == btnSeleccionar) {
            seleccionarJugador();
        } else if (o == btnGuardar) {
            guardarDatos();
            JOptionPane.showMessageDialog(this, "Datos guardados correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else if (o == btnSeleccionarImagen) {
            seleccionarImagen();
        }else if (o == btnGuardarSeleccion) {
        editarSeleccion();
    } else if (o == btnVolver) {
        if (datosmodificados) {
            int opcion = JOptionPane.showConfirmDialog(this, "Los datos han sido modificados, ¿Desea guardarlos?", "Info", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);
            switch (opcion) {
                case JOptionPane.YES_OPTION:
                    guardarDatos();
                    break;
                case JOptionPane.CANCEL_OPTION:
                case JOptionPane.CLOSED_OPTION:
                    return;
            }
        }
        datosguardados = true;
        System.exit(0);
    }}


    private void editarSeleccion() {
        int Indice = listJugadores.getSelectedIndex();
        String nombre = "" + textNombre.getText();
        String apellidos = "" + textApellido.getText();
        String posicion = (String) comboBoxPosicion.getSelectedItem();
        int dorsal = Integer.parseInt(textDorsal.getText());
        String photoPath = "";

        Jugador nuevoJugador = new Jugador(nombre, apellidos, posicion, dorsal, photoPath);
        dlm.set(Indice, nuevoJugador);
    }

    private void guardarDatos() {
        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            fos = new FileOutputStream("jugadores.ser");
            oos = new ObjectOutputStream(fos);

            Jugador nuevoJugador;
            for (int posicion = 0; posicion < dlm.getSize(); posicion++) {
                nuevoJugador = dlm.get(posicion);
                oos.writeObject(nuevoJugador);
                contador = dlm.getSize();
            }

            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Fichero no encontrado", "Error", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error de Entrada/Salida al guardar el archivo: jugadores.ser\n" + e.getMessage(), "Error de IO", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void seleccionarJugador() {
        int Indice = listJugadores.getSelectedIndex();
        
        if (Indice == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un jugador de la lista.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Jugador jugadorSeleccionado = dlm.get(Indice);
        String nombreSelectCompleto = "" + jugadorSeleccionado.getNombre();
        String[] partes = nombreSelectCompleto.split(" ");
        String nombreSelect = partes[0];
        String apellidosSelect = partes[1];
        String posicionSelect = jugadorSeleccionado.getPosicion();
        int dorsalSelect = jugadorSeleccionado.getDorsal();

        textNombre.setText(nombreSelect);
        textApellido.setText(apellidosSelect);
        comboBoxPosicion.setSelectedItem(posicionSelect);
        String dorsalSelectString = String.valueOf(dorsalSelect);
        textDorsal.setText(dorsalSelectString);
    }

    private void eliminarJugador() {
        int[] Indice = listJugadores.getSelectedIndices();
        if (Indice.length > 0) {
            for (int i = Indice.length - 1; i >= 0; i--) {
                dlm.removeElementAt(Indice[i]);
                contador = dlm.getSize();
                lblContador.setText(String.valueOf(contador));
                datosmodificados = true;
            }
        }
    }

    public void windowOpened(WindowEvent e) {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void windowClosing(WindowEvent e) {
        if (datosmodificados) {
            int opcion = JOptionPane.showConfirmDialog(this, "Los datos han sido modificados, ¿Desea guardarlos?", "Info", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);
            switch (opcion) {
                case JOptionPane.YES_OPTION:
                    guardarDatos();
                    break;
                case JOptionPane.CANCEL_OPTION:
                case JOptionPane.CLOSED_OPTION:
                    return;
            }
        } else if (datosguardados) {
            System.exit(0);
        }
        System.exit(0);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        Object source = e.getSource();
        if (source == btnGuardar) {
            fader.fadeBackground(btnGuardar, btnGuardar.getBackground(), new Color(0x1a5bae));
        } else if (source == btnEliminar) {
            fader.fadeBackground(btnEliminar, btnEliminar.getBackground(), new Color(0xfe9f2e));
        } else if (source == btnVolver) {
            fader.fadeBackground(btnVolver, btnVolver.getBackground(), new Color(0x646464));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object source = e.getSource();
        if (source == btnGuardar) {
            fader.fadeBackground(btnGuardar, btnGuardar.getBackground(), new Color(0x13427E));
        } else if (source == btnEliminar) {
            fader.fadeBackground(btnEliminar, btnEliminar.getBackground(), new Color(0xf46b20));
        } else if (source == btnVolver) {
            fader.fadeBackground(btnVolver, btnVolver.getBackground(), new Color(0x404040));
        }
    }

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}
}