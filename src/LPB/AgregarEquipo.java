package LPB;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList; // Importa ArrayList

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import LPBCLASES.Equipo;

public class AgregarEquipo extends JFrame {

    private static final long serialVersionUID = 4355224767970407050L;
    private JTextField nombreEquipoField;
    private JTextField nombreEntrenadorField;
    private JTextField estadioField;
    private JTextField fundacionField;
    private DefaultListModel<String> jugadoresModel;
    private JLabel logoLabel;
    private JLabel entrenadorLabel;
    private File logoFile;
    private File entrenadorFile;
    private EquiposTemporada equiposTemporadaFrame; // Referencia a EquiposTemporada

    public AgregarEquipo(EquiposTemporada equiposTemporadaFrame) {
        this.equiposTemporadaFrame = equiposTemporadaFrame;
        setTitle("Agregar Nuevo Equipo");
        setSize(815, 600);
        getContentPane().setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 5, 5));

        logoLabel = new JLabel("Seleccionar Logo");
        JButton btnLogo = new JButton("Cargar");
        btnLogo.addActionListener(_ -> seleccionarImagen(logoLabel, true));

        nombreEquipoField = new JTextField();
        nombreEntrenadorField = new JTextField();
        JButton btnEntrenador = new JButton("Cargar");

        estadioField = new JTextField();
        fundacionField = new JTextField();

        panel.add(new JLabel("Logo del Equipo:"));
        panel.add(btnLogo);
        panel.add(logoLabel);

        panel.add(new JLabel("Nombre del Equipo:"));
        
                JLabel label_1 = new JLabel("Entrenador (Nombre):");
                panel.add(label_1);
        panel.add(nombreEquipoField);
        
                JLabel label = new JLabel("Foto del Entrenador:");
                panel.add(label);
        panel.add(nombreEntrenadorField);
        
                entrenadorLabel = new JLabel("Seleccionar Foto Entrenador");
                btnEntrenador.addActionListener(_ -> seleccionarImagen(entrenadorLabel, false));
                panel.add(entrenadorLabel);
        panel.add(btnEntrenador);

        panel.add(new JLabel("Estadio:"));
        panel.add(estadioField);

        panel.add(new JLabel("Fundación:"));
        panel.add(fundacionField);

        getContentPane().add(panel, BorderLayout.CENTER);

        jugadoresModel = new DefaultListModel<>();
        JList<String> jugadoresList = new JList<>(jugadoresModel);
        JScrollPane scrollPane = new JScrollPane(jugadoresList);

        JPanel panelJugadores = new JPanel();
        panelJugadores.setLayout(new BorderLayout());
        panelJugadores.add(new JLabel("Lista de Jugadores"), BorderLayout.NORTH);
        panelJugadores.add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnAgregarJugador = new JButton("Agregar Jugador");
        btnAgregarJugador.addActionListener(_ -> agregarJugador());
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(_ -> guardarEquipo());

        panelBotones.add(btnAgregarJugador);
        panelBotones.add(btnGuardar);

        getContentPane().add(panelJugadores, BorderLayout.EAST);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);
    }

    private void seleccionarImagen(JLabel label, boolean isLogo) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            label.setText(selectedFile.getName());
            if (isLogo) {
                logoFile = selectedFile;
            } else {
                entrenadorFile = selectedFile;
            }
        }
    }

    private void agregarJugador() {
        String jugador = JOptionPane.showInputDialog("Nombre del jugador:");
        if (jugador != null && !jugador.trim().isEmpty()) {
            jugadoresModel.addElement(jugador);
        }
    }

    private void guardarEquipo() {
        String nombre = nombreEquipoField.getText();
        String entrenador = nombreEntrenadorField.getText();
        String estadio = estadioField.getText();
        String fundacion = fundacionField.getText();

        if (nombre.isEmpty() || entrenador.isEmpty() || estadio.isEmpty() || fundacion.isEmpty() || logoFile == null || entrenadorFile == null) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ArrayList<String> jugadores = new ArrayList<>();
        for (int i = 0; i < jugadoresModel.getSize(); i++) {
            jugadores.add(jugadoresModel.getElementAt(i));
        }

        Equipo nuevoEquipo = new Equipo(nombre, entrenador, jugadores, logoFile.getAbsolutePath(), entrenadorFile.getAbsolutePath(), estadio, fundacion);
        equiposTemporadaFrame.agregarNuevoEquipoDesdeFormulario(nuevoEquipo); // Llama al método en EquiposTemporada
        JOptionPane.showMessageDialog(this, "Equipo agregado correctamente");
        dispose();
    }
}