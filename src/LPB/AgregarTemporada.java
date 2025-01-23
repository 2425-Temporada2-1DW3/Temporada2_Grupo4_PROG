package LPB;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

import LPBCLASES.TextoRedondeado;
import LPBCLASES.BotonRedondeado;
import LPBCLASES.Temporada;

public class AgregarTemporada extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final String FILE_NAME = "temporadas.ser";
    private JTextField periodoField;
    private JComboBox<String> estadoComboBox;
    private BotonRedondeado btnGuardar, btnCancelar;

    public AgregarTemporada() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
        setTitle("Agregar Temporada");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        // Configuración del panel izquierdo
        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, 500, 300);
        panel.setBackground(new Color(255, 243, 205));
        getContentPane().add(panel);

        // Etiqueta y campo para el período
        JLabel lblPeriodo = new JLabel("Período de la temporada:");
        lblPeriodo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblPeriodo.setBounds(20, 30, 200, 30);
        panel.add(lblPeriodo);

        periodoField = new TextoRedondeado(20);
        periodoField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        periodoField.setBounds(240, 30, 200, 30);
        panel.add(periodoField);

        // Etiqueta y combo box para el estado
        JLabel lblEstado = new JLabel("Estado de la temporada:");
        lblEstado.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblEstado.setBounds(20, 90, 200, 30);
        panel.add(lblEstado);

        estadoComboBox = new JComboBox<>(new String[]{"Activa", "Finalizada", "En proceso"});
        estadoComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
        estadoComboBox.setBounds(240, 90, 200, 30);
        panel.add(estadoComboBox);

        // Botón Guardar
        btnGuardar = new BotonRedondeado("Guardar", null);
        btnGuardar.setBounds(240, 180, 100, 40);
        btnGuardar.setBackground(new Color(0x13427e));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnGuardar.setFocusPainted(false);
        btnGuardar.addActionListener(e -> guardarTemporada());
        panel.add(btnGuardar);

        // Botón Cancelar
        btnCancelar = new BotonRedondeado("Cancelar", null);
        btnCancelar.setBounds(360, 180, 100, 40);
        btnCancelar.setBackground(new Color(0xf46b20));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("SansSerif", Font.PLAIN, 16));
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(e -> dispose());
        panel.add(btnCancelar);
    }

    @SuppressWarnings("unchecked")
    private List<Temporada> leerTemporadas() {
        List<Temporada> temporadas = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            temporadas = (List<Temporada>) ois.readObject();
        } catch (Exception e) {
            System.out.println("No se pudo leer el archivo temporadas.ser. Se creará uno nuevo.");
        }
        return temporadas;
    }

    private void guardarTemporadas(List<Temporada> temporadas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(temporadas);
            System.out.println("Temporadas guardadas correctamente.");
        } catch (Exception e) {
            System.err.println("Error al guardar las temporadas: " + e.getMessage());
        }
    }

    private void guardarTemporada() {
        String periodo = periodoField.getText().trim();
        String estado = (String) estadoComboBox.getSelectedItem();

        if (periodo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo de período no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Temporada> temporadas = leerTemporadas();
        Temporada nuevaTemporada = new Temporada(periodo, estado, new ArrayList<>());
        temporadas.add(nuevaTemporada);
        guardarTemporadas(temporadas);

        JOptionPane.showMessageDialog(this, "Temporada agregada correctamente.");
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AgregarTemporada frame = new AgregarTemporada();
            frame.setVisible(true);
        });
    }
}
