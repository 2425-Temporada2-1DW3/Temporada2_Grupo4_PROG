package LPB;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import LPBCLASES.BotonRedondeado;
import LPBCLASES.Jugador;
import LPBCLASES.TextoRedondeado;
import LPBCLASES.logClase;
import jnafilechooser.api.JnaFileChooser;

import javax.swing.SwingConstants;

/**
 * Clase AgregarJugador permite registrar un nuevo jugador en el equipo de baloncesto.
 * Proporciona una interfaz gráfica para ingresar datos del jugador y cargar una imagen de perfil.
 * 
 * Extiende {@link JDialog} para ser una ventana emergente dentro de una aplicación principal.
 */
public class AgregarJugador extends JDialog {
	
	private static final long serialVersionUID = 5959789971456254669L;
	private JTextField nombreField;
    private JTextField apellidosField;
    private JComboBox<String> posicionComboBox;
    private JTextField dorsalField;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private JLabel lblSeleccioneUnaFoto;
    private JPanel panel;
    private JLabel nombreLabel;
    private JLabel apellidosLabel;
    private JLabel posicionLabel;
    private JLabel dorsalLabel;
    private JButton btnCargarFoto;
    private File selectedFile;
    private ImageIcon logo;
    private JLabel labelLogo;
    private Jugador jugador;
    private int dorsal;

    /**
     * Constructor de la clase AgregarJugador.
     * Inicializa la interfaz gráfica para agregar un nuevo jugador.
     * 
     * @param parent Ventana principal desde la cual se invoca el cuadro de diálogo.
     */
    public AgregarJugador(JFrame parent) {
        super(parent, "Agregar Jugador", true);
        getContentPane().setLayout(new BorderLayout());
        setSize(652, 507);
        setLocationRelativeTo(parent);

        panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(255, 243, 204));
		getContentPane().add(panel);
		
		logo = new ImageIcon(getClass().getResource("/imagenes/logo150.png"));
		labelLogo = new JLabel(logo);
		labelLogo.setFont(new Font("Arial", Font.BOLD, 16));
		labelLogo.setBounds(53, 310, 150, 150);
		panel.add(labelLogo);

        nombreLabel = new JLabel("Nombre:");
        nombreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        nombreLabel.setForeground(new Color(0x545454));
        nombreLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        nombreLabel.setBounds(93, 63, 180, 25);
		panel.add(nombreLabel);
        
        nombreField = new TextoRedondeado(20);
        nombreField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        nombreField.setBounds(310, 56, 200, 40);
		panel.add(nombreField);

        apellidosLabel = new JLabel("Apellidos:");
        apellidosLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        apellidosLabel.setForeground(new Color(0x545454));
        apellidosLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        apellidosLabel.setBounds(93, 113, 180, 25);
		panel.add(apellidosLabel);
        
        apellidosField = new TextoRedondeado(20);
        apellidosField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        apellidosField.setBounds(310, 106, 200, 40);
		panel.add(apellidosField);
        
        posicionLabel = new JLabel("Posición:");
        posicionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        posicionLabel.setForeground(new Color(0x545454));
        posicionLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        posicionLabel.setBounds(93, 161, 180, 25);
		panel.add(posicionLabel);
		
        String[] posiciones = {"Ala-pívot", "Alero", "Base", "Escolta", "Pívot"};
        posicionComboBox = new JComboBox<>(posiciones);
        posicionComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
        posicionComboBox.setSize(200, 40);
        posicionComboBox.setLocation(310, 156);
        panel.add(posicionComboBox);

        dorsalLabel = new JLabel("Dorsal:");
        dorsalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        dorsalLabel.setForeground(new Color(0x545454));
        dorsalLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        dorsalLabel.setBounds(93, 213, 180, 25);
		panel.add(dorsalLabel);
		
        dorsalField = new TextoRedondeado(20);
        dorsalField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        dorsalField.setBounds(310, 206, 200, 40);
		panel.add(dorsalField);

        btnCargarFoto = new BotonRedondeado("Cargar Foto", null);
        btnCargarFoto.setBackground(new Color(0xf46b20));
        btnCargarFoto.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnCargarFoto.setBounds(310, 260, 200, 40);
        btnCargarFoto.setBackground(new Color(0x13427E));
        btnCargarFoto.setForeground(Color.WHITE);
        btnCargarFoto.setFocusPainted(false);
        btnCargarFoto.addActionListener(e -> cargarFoto());
        panel.add(btnCargarFoto);
        
        lblSeleccioneUnaFoto = new JLabel("Seleccione una Foto:");
        lblSeleccioneUnaFoto.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSeleccioneUnaFoto.setForeground(new Color(0x545454));
        lblSeleccioneUnaFoto.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblSeleccioneUnaFoto.setBounds(10, 268, 263, 25);
		panel.add(lblSeleccioneUnaFoto);

        btnGuardar = new BotonRedondeado("Guardar", null);
        btnGuardar.setBackground(new Color(0xf46b20));
        btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnGuardar.setBounds(357, 406, 120, 40);
        btnGuardar.setBackground(new Color(0x13427E));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFocusPainted(false);
        btnGuardar.addActionListener(e -> guardarJugador());
        panel.add(btnGuardar);

        btnCancelar = new BotonRedondeado("Cancelar", null);
        btnCancelar.setBackground(new Color(0xf46b20));
        btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnCancelar.setBounds(487, 406, 120, 40);
        btnCancelar.setBackground(new Color(0x13427E));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(e -> dispose());
        panel.add(btnCancelar);
    }

	/**
	 * Método para cargar una foto de perfil del jugador. Muestra un cuadro de
	 * diálogo para seleccionar una imagen. Si la imagen es válida, se muestra el
	 * nombre del archivo seleccionado.
	 * 
	 * Si la imagen no es válida, se muestra un mensaje de error y se reinicia el campo.
	 * 
	 * @see JnaFileChooser
	 */
    private void cargarFoto() {
        JnaFileChooser fc = new JnaFileChooser();

        fc.setTitle("Selecciona una foto");
        fc.addFilter("Imágenes (*.jpg; *.jpeg; *.png; *.gif)", "jpg", "jpeg", "png", "gif");
        fc.addFilter("Todos los Archivos", "*");

        if (fc.showOpenDialog(this)) {
            selectedFile = fc.getSelectedFile();
            
            try {
                BufferedImage imagen = ImageIO.read(selectedFile);
                if (imagen == null) {
                    throw new IOException("Formato de imagen no soportado o corrupto");
                }

                lblSeleccioneUnaFoto.setText(selectedFile.getName());
                lblSeleccioneUnaFoto.setForeground(new Color(0x007B00));

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, 
                    "La imagen está corrupta, prueba con otra imagen.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);

                lblSeleccioneUnaFoto.setText("Imagen inválida.");
                lblSeleccioneUnaFoto.setForeground(Color.RED);
            }
        } else {
            lblSeleccioneUnaFoto.setText("No se seleccionó ninguna foto.");
            lblSeleccioneUnaFoto.setForeground(Color.RED);
        }
    }

	/**
	 * Método para guardar los datos del jugador. Valida que los campos no estén
	 * vacíos y que el dorsal sea un número válido entre 0 y 99.
	 * 
	 * Si los datos son correctos, se crea un nuevo objeto {@link Jugador} con los
	 * datos ingresados y se cierra la ventana.
	 * 
	 * Si falta algún dato, se muestra un mensaje de error.
	 */
    private void guardarJugador() {
    	if (nombreField.getText().isEmpty() || apellidosField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
    	} else {
	        String nombre = nombreField.getText();
	        String apellidos = apellidosField.getText();
	        String posicion = (String) posicionComboBox.getSelectedItem();
	
	        try {
	            dorsal = Integer.parseInt(dorsalField.getText());
	            if (dorsal < 0 || dorsal > 99) {
	                JOptionPane.showMessageDialog(this, "El dorsal debe estar entre 0 y 99.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(this, "El dorsal debe ser un número válido entre 0 y 99.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        
	        if (selectedFile == null) {
	        	JOptionPane.showMessageDialog(this, "Tienes que seleccionar una imagen.", "Error", JOptionPane.ERROR_MESSAGE);
	        } else {
	            String rutaFoto = selectedFile.getAbsolutePath();
	        	 // 🔴 Log cuando se agrega un jugador
	            logClase.logAction("Jugador agregado: " + nombre + ", Posición: " + posicion + ", Dorsal: " + dorsal);
	            jugador = new Jugador(nombre, apellidos, posicion, dorsal, rutaFoto);
	            dispose();
	        }
    	}
    }
    
	/**
	 * Método para obtener el jugador creado.
	 * 
	 * @return Jugador creado con los datos ingresados.
	 */
    public Jugador getJugador() {
        return jugador;
    }
}
