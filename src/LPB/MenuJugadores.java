package LPB;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import LPBCLASES.BotonRedondeado;
import LPBCLASES.Equipo;
import LPBCLASES.TextoRedondeado;
import LPBCLASES.logClase;
import jnafilechooser.api.JnaFileChooser;
import LPBCLASES.Jugador;
import LPBCLASES.Temporada;

import javax.swing.SwingConstants;

import org.imgscalr.Scalr;

public class MenuJugadores extends JFrame implements ActionListener, WindowListener, Serializable {

    private static final long serialVersionUID = 1L;

    // Atributos de la clase
    private JPanel panelSuperior;
    private JPanel panelInferior;
    private JnaFileChooser selectorArchivo;
    private ImageIcon logo;
    private JLabel labelLogo;
    private JLabel titulo;
    private JTextField textNombre;
    private JTextField textDorsal;
    private JTextField textApellido;
    private JComboBox<String> comboBoxPosicion;
    private JComboBox<Equipo> comboBoxEquipos;
    private BotonRedondeado btnGuardar, btnEliminar, btnVolver, btnSeleccionarImagen;
    private DefaultListModel<Jugador> dlm;
    private JList<Jugador> listJugadores;
    private JScrollPane scrollPane;
    private JLabel lblNombre;
    private JLabel lblDorsal;
    private JLabel lblPosicion;
    private JLabel lblEquipo;
    private JLabel lblApellido;
    private JLabel lblFoto;
    private JLabel lblJugadoresTotales;
    private JLabel lblContador;
    
    private int contador = 0;
    private boolean datosModificados = false;
    private Temporada temporadaActiva;

	/**
	 * Create the frame.
	 */
    public MenuJugadores() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
        setTitle("LPB Basketball - Menú de Jugadores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
 
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
        panelInferior.setBounds(0, 110, 786, 453);
        panelInferior.setLayout(null);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 20, 407, 390);
        dlm = new DefaultListModel<>();
        
        listJugadores = new JList<>();
        listJugadores.setModel(dlm);
        listJugadores.setFont(new Font("SansSerif", Font.PLAIN, 16));
        scrollPane.setViewportView(listJugadores);
        listJugadores.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Jugador jugadorSeleccionado = listJugadores.getSelectedValue();
                if (jugadorSeleccionado != null) {
                    textNombre.setText(jugadorSeleccionado.getNombre());
                    textApellido.setText(jugadorSeleccionado.getApellidos());
                    textDorsal.setText(String.valueOf(jugadorSeleccionado.getDorsal()));
                    comboBoxPosicion.setSelectedItem(jugadorSeleccionado.getPosicion());
                    Equipo equipoJugador = null;
                    List<Equipo> equipos = temporadaActiva.getEquipos();
                    for (Equipo equipo : equipos) {
                        if (equipo.getJugadores().contains(jugadorSeleccionado)) {
                            equipoJugador = equipo;
                            break;
                        }
                    }

                    // Seleccionar el equipo en el ComboBox
                    if (equipoJugador != null) {
                        comboBoxEquipos.setSelectedItem(equipoJugador);
                    }

                    // Actualizar la foto del jugador
                    actualizarFoto(jugadorSeleccionado);
                }
            }
        });
        panelInferior.add(scrollPane);
        
        btnSeleccionarImagen = new BotonRedondeado("Seleccionar Imagen", null);
        btnSeleccionarImagen.setForeground(new Color(255, 255, 255));
        btnSeleccionarImagen.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnSeleccionarImagen.setBounds(574, 40, 187, 40);
        btnSeleccionarImagen.setBackground(new Color(0xf46b20));
        btnSeleccionarImagen.addActionListener(this);
        btnSeleccionarImagen.setFocusPainted(false);
        panelInferior.add(btnSeleccionarImagen);
        
        lblFoto = new JLabel("");
        lblFoto.setBounds(474, 25, 80, 80);
        lblFoto.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
        panelInferior.add(lblFoto);
        
        lblNombre = new JLabel("Nombre:");
        lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNombre.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblNombre.setForeground(new Color(0x545454));
        lblNombre.setBounds(474, 120, 70, 30);
        panelInferior.add(lblNombre);

        textNombre = new TextoRedondeado(20);
        textNombre.setColumns(10);
        textNombre.setFont(new Font("SansSerif", Font.PLAIN, 16));
        textNombre.setBounds(561, 120, 200, 30);
        panelInferior.add(textNombre);

        lblApellido = new JLabel("Apellidos:");
        lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
        lblApellido.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblApellido.setForeground(new Color(0x545454));
        lblApellido.setBounds(463, 170, 81, 30);
        panelInferior.add(lblApellido);

        textApellido = new TextoRedondeado(20);
        textApellido.setColumns(10);
        textApellido.setFont(new Font("SansSerif", Font.PLAIN, 16));
        textApellido.setBounds(561, 170, 200, 30);
        panelInferior.add(textApellido);

        lblDorsal = new JLabel("Dorsal:");
        lblDorsal.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDorsal.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblDorsal.setForeground(new Color(0x545454));
        lblDorsal.setBounds(474, 220, 70, 30);
        panelInferior.add(lblDorsal);

        textDorsal = new TextoRedondeado(20);
        textDorsal.setColumns(10);
        textDorsal.setFont(new Font("SansSerif", Font.PLAIN, 16));
        textDorsal.setBounds(561, 220, 200, 30);
        panelInferior.add(textDorsal);

        lblPosicion = new JLabel("Posición:");
        lblPosicion.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPosicion.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblPosicion.setForeground(new Color(0x545454));
        lblPosicion.setBounds(463, 270, 81, 30);
        panelInferior.add(lblPosicion);

        comboBoxPosicion = new JComboBox<>();
        String[] posiciones = {"Base", "Escolta", "Alero", "Ala-pívot", "Pívot"};
        for (String pos : posiciones) {
            comboBoxPosicion.addItem(pos);
        }
        comboBoxPosicion.setFont(new Font("SansSerif", Font.PLAIN, 16));
        comboBoxPosicion.setBounds(561, 270, 200, 30);
        panelInferior.add(comboBoxPosicion);
        
        lblEquipo = new JLabel("Equipo:");
        lblEquipo.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEquipo.setForeground(new Color(84, 84, 84));
        lblEquipo.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblEquipo.setBounds(463, 320, 81, 30);
        panelInferior.add(lblEquipo);
        
        comboBoxEquipos = new JComboBox<Equipo>();
        comboBoxEquipos.setFont(new Font("SansSerif", Font.PLAIN, 16));
        comboBoxEquipos.setBounds(561, 320, 200, 30);
        panelInferior.add(comboBoxEquipos);

        btnGuardar = new BotonRedondeado("Guardar", null);
        btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnGuardar.setBackground(new Color(0x13427E));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBounds(452, 369, 100, 40);
        btnGuardar.setFocusPainted(false);
        btnGuardar.addActionListener(this);
        panelInferior.add(btnGuardar);

        btnEliminar = new BotonRedondeado("Eliminar", null);
        btnEliminar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnEliminar.setBackground(new Color(0xf46b20));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setBounds(562, 369, 100, 40);
        btnEliminar.setFocusPainted(false);
        btnEliminar.addActionListener(this);
        panelInferior.add(btnEliminar);

        btnVolver = new BotonRedondeado("Volver", null);
        btnVolver.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnVolver.setBackground(new Color(0x404040));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBounds(671, 369, 90, 40);
        btnVolver.setFocusPainted(false);
        btnVolver.addActionListener(this);
        panelInferior.add(btnVolver);

        lblJugadoresTotales = new JLabel("Jugadores en la temporada:");
        lblJugadoresTotales.setLocation(20, 420);
        lblJugadoresTotales.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblJugadoresTotales.setSize(180, 20);
        panelInferior.add(lblJugadoresTotales);

        lblContador = new JLabel("" + contador);
        lblContador.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblContador.setSize(40, 20);
        lblContador.setLocation(205, 420);
        panelInferior.add(lblContador);
        getContentPane().add(panelInferior);
        
        actualizarFoto(null);
        cargarJugadores();
    }
    
	private void actualizarTitulo() {
        String title = "LPB Basketball - Menú de Jugadores";
        if (datosModificados) {
            title = "*" + title;
        }
        setTitle(title);
	}
    
	private void actualizarFoto(Jugador jugador) {
	    BufferedImage imagen = null;
	    ImageIcon fotoIcono = null;
	    
	    try {
	        imagen = ImageIO.read(new File("src/imagenes/user.png"));
	        fotoIcono = new ImageIcon(Scalr.resize(imagen, 70, 70));

	        if (jugador == null) {
	            lblFoto.setIcon(fotoIcono);
	            return;
	        }

	        String rutaFoto = jugador.getRutaFoto();
	        
	        if (rutaFoto != null && !rutaFoto.isEmpty()) {
	            File fotoFile = new File(rutaFoto);

	            if (fotoFile.exists() && fotoFile.isFile()) {
	                imagen = ImageIO.read(fotoFile);
	                fotoIcono = new ImageIcon(Scalr.resize(imagen, 80, 80));
	            } else {
	                fotoIcono = new ImageIcon(Scalr.resize(imagen, 80, 80));
	            }
	        } else {
	            fotoIcono = new ImageIcon(Scalr.resize(imagen, 80, 80));
	        }

	        lblFoto.setIcon(fotoIcono);

	    } catch (IOException ex) {
	        System.err.println("Error al cargar la foto del jugador: " + ex.getMessage());
	        lblFoto.setIcon(fotoIcono);
	    }
	}

    public void seleccionarImagen() {
    	selectorArchivo = new JnaFileChooser();
        selectorArchivo.setTitle("Selecciona una foto");
        selectorArchivo.addFilter("Imágenes (*.jpg; *.jpeg; *.png; *.gif)", "jpg", "jpeg", "png", "gif");
        selectorArchivo.addFilter("Todos los Archivos", "*");

        if (selectorArchivo.showOpenDialog(this)) {
            File archivoSeleccionado = selectorArchivo.getSelectedFile();
            try {
                BufferedImage imagenOriginal = ImageIO.read(archivoSeleccionado);

                if (imagenOriginal == null) {
                    JOptionPane.showMessageDialog(this, "El archivo seleccionado no es una imagen.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                BufferedImage imagenEscalada = Scalr.resize(imagenOriginal, Scalr.Method.QUALITY, 70, 70);

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

    // Método para cargas los jugadores al dlm
    private void cargarJugadores() {
        File carpetaTemporadas = new File("data");
        File[] archivosTemporadas = carpetaTemporadas.listFiles((dir, name) -> name.startsWith("temporada_") && name.endsWith(".ser"));

        if (archivosTemporadas == null || archivosTemporadas.length == 0) {
            System.out.println("No se han encontrado archivos de temporada.");
            return;
        }

        temporadaActiva = null;
        // Buscar la temporada activa
        for (File archivo : archivosTemporadas) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                Temporada temporada = (Temporada) ois.readObject();
                if ("Activa".equals(temporada.getEstado())) {
                    temporadaActiva = temporada;
                    break;
                }
            } catch (FileNotFoundException e) {
                System.err.println("Archivo no encontrado: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Error al leer el archivo de temporada: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                System.err.println("Error al cargar la temporada: " + e.getMessage());
            }
        }

        if (temporadaActiva == null) {
            System.out.println("No se ha encontrado una temporada activa.");
            return;
        }

        List<Equipo> equipos = temporadaActiva.getEquipos();
        List<Jugador> jugadoresTemp = new ArrayList<>();

        for (Equipo equipo : equipos) {
        	comboBoxEquipos.addItem(equipo);
            List<Jugador> jugadores = equipo.getJugadores();
            for (Jugador jugador : jugadores) {
                jugadoresTemp.add(jugador);
            }
        }

        jugadoresTemp.sort(Comparator.comparing(Jugador::getNombre));

        for (Jugador jugador : jugadoresTemp) {
            dlm.addElement(jugador);
        }
        
        contador = dlm.getSize();
        lblContador.setText(String.valueOf(contador));
    }

    private void guardarDatos() {
        if (temporadaActiva == null) {
            JOptionPane.showMessageDialog(this, "No hay una temporada activa. No se pueden guardar los jugadores.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener el jugador seleccionado de la lista
        Jugador jugadorSeleccionado = listJugadores.getSelectedValue();
        
        // Si no hay un jugador seleccionado, mostrar mensaje de error
        if (jugadorSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "No hay ningún jugador seleccionado para guardar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener los valores de los campos de texto para actualizar el jugador
        String nombre = textNombre.getText();
        String apellidos = textApellido.getText();
        int dorsal = Integer.parseInt(textDorsal.getText());
        String posicion = (String) comboBoxPosicion.getSelectedItem();
        String rutaFoto = null;

        // Si el jugador no existe en el modelo (dlm), crearlo y añadirlo
        boolean jugadorExiste = false;
        for (int i = 0; i < dlm.getSize(); i++) {
            Jugador jugador = dlm.get(i);
            if (jugador.equals(jugadorSeleccionado)) {
                // Actualizar los campos del jugador existente
                jugador.setNombre(nombre);
                jugador.setApellidos(apellidos);
                jugador.setDorsal(dorsal);
                jugador.setPosicion(posicion);
                jugadorExiste = true;
                break;
            }
        }

        if (!jugadorExiste) {
            // Crear un nuevo jugador si no existe
            Jugador nuevoJugador = new Jugador(nombre, apellidos, posicion, dorsal, rutaFoto);
            dlm.addElement(nuevoJugador);
            jugadorSeleccionado = nuevoJugador;
        }

        // Guardar los jugadores actualizados en el archivo de la temporada activa
        File archivoTemporadaActiva = new File("data/temporada_" + temporadaActiva.getPeriodo() + ".ser");

        // Comprobar si el archivo existe
        if (!archivoTemporadaActiva.exists()) {
            JOptionPane.showMessageDialog(this, "El archivo de la temporada activa no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoTemporadaActiva))) {
            // Guardar todos los jugadores en el archivo
            for (int i = 0; i < dlm.getSize(); i++) {
                oos.writeObject(dlm.get(i));
            }

            // 🔴 Log cuando se guardan los jugadores
            logClase.logAction("Datos de jugadores guardados correctamente. Total de jugadores: " + dlm.getSize());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error de Entrada/Salida al guardar el archivo: temporada_" + temporadaActiva.getPeriodo() + ".ser\n" + e.getMessage(), "Error de IO", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            
            // 🔴 Log del error al guardar los datos
            logClase.logError("Error al guardar los datos de los jugadores", e);
        }
    }

    private void eliminarJugador() {
        int[] Indice = listJugadores.getSelectedIndices();
        if (Indice.length > 0) {
            for (int i = Indice.length - 1; i >= 0; i--) {
            	
            	Jugador jugadorEliminado = dlm.get(Indice[i]);
            	// 🔴 Log cuando se elimina un jugador
                logClase.logAction("Jugador eliminado: " + jugadorEliminado.getNombre() + " " + jugadorEliminado.getApellidos());

                
                dlm.removeElementAt(Indice[i]);
                contador = dlm.getSize();
                lblContador.setText(String.valueOf(contador));
                datosModificados = true;
                actualizarTitulo();
            }
        }
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
    	Object o = e.getSource();

        if (o == btnEliminar) {
            eliminarJugador();
        } else if (o == btnGuardar) {
            guardarDatos();
            JOptionPane.showMessageDialog(this, "Datos guardados correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else if (o == btnSeleccionarImagen) {
            seleccionarImagen();
        } else if (o == btnVolver) {
        	if (datosModificados) {
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
        	datosModificados = false;
	        new Menu("Administrador", "Admin").setVisible(true);
			dispose();
        }
    }

    public void windowOpened(WindowEvent e) {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void windowClosing(WindowEvent e) {
        if (datosModificados) {
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
        System.exit(0);
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