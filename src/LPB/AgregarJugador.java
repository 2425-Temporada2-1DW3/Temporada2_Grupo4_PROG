package LPB;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import LPBCLASES.BotonRedondeado;
import LPBCLASES.Jugador;
import LPBCLASES.TextoRedondeado;
import javax.swing.SwingConstants;

public class AgregarJugador extends JDialog {
    /**
	 * 
	 */
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
    private JFileChooser fileChooser;
    private FileNameExtensionFilter imageFilter;
    private ImageIcon logo;
    private JLabel labelLogo;
    private Jugador jugador;
    private int dorsal;

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
		
        String[] posiciones = {"Base", "Escolta", "Alero", "Ala Pivot", "Pivot"};
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

    private void cargarFoto() {
        fileChooser = new JFileChooser();
        imageFilter = new FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(imageFilter);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            lblSeleccioneUnaFoto.setText(selectedFile.getName());
            lblSeleccioneUnaFoto.setForeground(new Color(0x007B00));
        } else {
            lblSeleccioneUnaFoto.setText("No se seleccionó ninguna foto.");
            lblSeleccioneUnaFoto.setForeground(Color.RED);
        }
    }


    private void guardarJugador() {
        String nombre = nombreField.getText();
        String apellidos = apellidosField.getText();
        String posicion = (String) posicionComboBox.getSelectedItem();
        
        if (nombre.isEmpty() || apellidos.isEmpty() || posicion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

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

        jugador = new Jugador(nombre, apellidos, posicion, dorsal, selectedFile.getAbsolutePath());
        dispose();
    }
    
    public Jugador getJugador() {
        return jugador;
    }
}
