package LPB;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import LPBCLASES.BotonRedondeado;
import LPBCLASES.Temporada;
import jnafilechooser.api.JnaFileChooser;

public class Menu extends JFrame {
	private static final long serialVersionUID = -1200889095902166795L;
	private BotonRedondeado btnTemporadas;
	private BotonRedondeado btnEquipos;
	private BotonRedondeado btnUsuarios;
	private BotonRedondeado btnJugadores;
	private BotonRedondeado btnCerrarSesion;
	private BotonRedondeado btnExportarXML;
	private JPanel panelIzquierdo;
	private JLabel labelLogo;
	private JLabel labelUsuario;
	private JPanel panelDerecho;
	private JLabel titulo;
	private JLabel subtitulo;
	private ImageIcon logo;

	/**
	 * Create the frame.
	 */
	public Menu(String rol, String usuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
		setTitle("LPB Basketball - Menú");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(850, 550);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

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

		panelDerecho = new JPanel();
		panelDerecho.setBackground(new Color(204, 153, 102));
		panelDerecho.setBounds(425, 0, 411, 513);
		panelDerecho.setLayout(null);

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

		btnTemporadas = new BotonRedondeado("Temporadas", null);
		btnTemporadas.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnTemporadas.setBackground(new Color(0x13427E));
		btnTemporadas.setForeground(Color.WHITE);
		btnTemporadas.setBounds(50, 191, 200, 40);
		btnTemporadas.setFocusPainted(false);
		panelDerecho.add(btnTemporadas);
		
		btnTemporadas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MenuTemporadas(rol, usuario).setVisible(true);
				dispose();
			}
		});

		btnEquipos = new BotonRedondeado("Equipos", null);
		btnEquipos.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnEquipos.setBackground(new Color(0xf46b20));
		btnEquipos.setForeground(Color.WHITE);
		btnEquipos.setBounds(50, 251, 200, 40);
		btnEquipos.setFocusPainted(false);
		panelDerecho.add(btnEquipos);
		
		btnEquipos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EquiposTemporada(rol, usuario, null).setVisible(true);
				dispose();
			}
		});
		
		btnJugadores = new BotonRedondeado("Jugadores", null);		
		btnJugadores.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnJugadores.setBackground(new Color(0xfff3cc));
		btnJugadores.setForeground(new Color(0x535353));
		btnJugadores.setBounds(50, 311, 200, 40);
		btnJugadores.setFocusPainted(false);
		
		btnJugadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MenuJugadores().setVisible(true);
				dispose();
			}
		});

		if (rol.equals("Administrador")) {
			panelDerecho.add(btnJugadores);
		}

		btnUsuarios = new BotonRedondeado("Usuarios", null);
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MenuUsuarios().setVisible(true);
				dispose();
			}
		});
		
		btnUsuarios.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnUsuarios.setBackground(new Color(0x545454));
		btnUsuarios.setForeground(Color.WHITE);
		btnUsuarios.setBounds(50, 371, 200, 40);
		btnUsuarios.setFocusPainted(false);

		if (rol.equals("Administrador")) {
			panelDerecho.add(btnUsuarios);
		}

		btnCerrarSesion = new BotonRedondeado("Cerrar Sesión", null);
		btnCerrarSesion.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnCerrarSesion.setBackground(new Color(64, 64, 64));
		btnCerrarSesion.setForeground(Color.WHITE);
		btnCerrarSesion.setBounds(250, 461, 140, 30);
		btnCerrarSesion.setFocusPainted(false);
		panelDerecho.add(btnCerrarSesion);

		btnCerrarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Login().setVisible(true);
				dispose();
			}
		});

		getContentPane().add(panelDerecho);
		
		btnExportarXML = new BotonRedondeado("Exportar datos", (ImageIcon) null);
		btnExportarXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarDatos();
			}
		});
		btnExportarXML.setForeground(Color.WHITE);
		btnExportarXML.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnExportarXML.setFocusPainted(false);
		btnExportarXML.setBackground(new Color(84, 84, 84));
		btnExportarXML.setBounds(48, 461, 150, 30);
		
		if ("Administrador".equals(rol)) {
			panelDerecho.add(btnExportarXML);
		}
	}
	
	private void exportarDatos() {
        JnaFileChooser fileChooser = new JnaFileChooser();
        fileChooser.setTitle("Guardar como...");
        fileChooser.addFilter("XML (*.xml)", "xml");
        fileChooser.setDefaultFileName("temporadas.xml");

        if (!fileChooser.showSaveDialog(this)) {
        	return;
        }

        File archivoSeleccionado = fileChooser.getSelectedFile();
        String ruta = archivoSeleccionado.getAbsolutePath();

        if (!ruta.toLowerCase().endsWith(".xml")) {
            archivoSeleccionado = new File(ruta + ".xml");
        }
	    
	    FileWriter fichero = null;
	    PrintWriter pw = null;
	    BufferedWriter bw = null;
	    
	    try {
	        File carpeta = new File("data/");
	        File[] archivos = carpeta.listFiles((dir, name) -> name.matches("temporada_\\d{4}-\\d{4}\\.ser"));

	        if (archivos == null || archivos.length == 0) {
	            JOptionPane.showMessageDialog(this, "No se encontraron archivos de temporada.", "Info", JOptionPane.WARNING_MESSAGE);
	            return;
	        }

	        // Crear el fichero XML
	        fichero = new FileWriter(archivoSeleccionado);
	        pw = new PrintWriter(fichero);
	        bw = new BufferedWriter(pw);
	        
	        bw.write("<?xml version='1.0' encoding='UTF-8'?>\r\n");
	        bw.write("<?xml-model href='temporadas.xsd'?>\r\n");
	        bw.write("<temporadas>\r\n");

	        for (File archivo : archivos) {
	            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
	                Temporada temporada = (Temporada) ois.readObject();
	                bw.write(temporada.toXML());
	                bw.newLine();
	            } catch (Exception e) {
	                System.err.println("Error al leer el archivo: " + archivo.getName());
	                e.printStackTrace();
	            }
	        }

	        bw.write("</temporadas>\r\n");

	        // Libero recursos
	        bw.close();
	        pw.close();
	        fichero.close();
	        
	        // Preguntar si se quieren exportar imágenes
	        int respuesta = JOptionPane.showConfirmDialog(this, "¿Deseas exportar las imágenes de las temporadas?", "Exportar imágenes", JOptionPane.YES_NO_OPTION);

	        if (respuesta == JOptionPane.YES_OPTION) {
	            JnaFileChooser imgChooser = new JnaFileChooser();
	            imgChooser.setTitle("Seleccionar carpeta de destino");
	            imgChooser.setMode(JnaFileChooser.Mode.Directories);

	            if (imgChooser.showOpenDialog(this)) {
	                File carpetaDestino = imgChooser.getSelectedFile();
	                File carpetaOrigen = new File("src/imagenes/temporadas");

	                if (!carpetaOrigen.exists() || !carpetaOrigen.isDirectory()) {
	                    JOptionPane.showMessageDialog(this, "No se encontró la carpeta de imágenes de temporadas.", "Error", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }

	                try {
	                    copiarCarpeta(carpetaOrigen, new File(carpetaDestino, "temporadas"));
	                } catch (IOException e) {
	                    JOptionPane.showMessageDialog(this, "Error al copiar las imágenes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        }

	        JOptionPane.showMessageDialog(this, "Los datos se han exportado correctamente.", "Info", JOptionPane.INFORMATION_MESSAGE);
	    } catch (IOException e) {
	    	JOptionPane.showMessageDialog(this, "Hubo un error al exportar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	private void copiarCarpeta(File origen, File destino) throws IOException {
	    if (!destino.exists()) {
	        destino.mkdirs();
	    }

	    for (File archivo : origen.listFiles()) {
	        File destinoArchivo = new File(destino, archivo.getName());

	        if (archivo.isDirectory()) {
	            copiarCarpeta(archivo, destinoArchivo);
	        } else {
	            Files.copy(archivo.toPath(), destinoArchivo.toPath(), StandardCopyOption.REPLACE_EXISTING);
	        }
	    }
	}
}