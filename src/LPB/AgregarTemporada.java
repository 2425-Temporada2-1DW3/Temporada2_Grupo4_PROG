package LPB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.*;

import LPBCLASES.TextoRedondeado;
import LPBCLASES.BotonRedondeado;
import LPBCLASES.Temporada;

public class AgregarTemporada extends JFrame {

    private static final long serialVersionUID = 1L;
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
        lblPeriodo.setBounds(21, 52, 200, 30);
        panel.add(lblPeriodo);

        periodoField = new TextoRedondeado(20);
        periodoField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        periodoField.setBounds(241, 52, 200, 30);
        panel.add(periodoField);

        // Etiqueta y combo box para el estado
        JLabel lblEstado = new JLabel("Estado de la temporada:");
        lblEstado.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblEstado.setBounds(21, 112, 200, 30);
        panel.add(lblEstado);

        estadoComboBox = new JComboBox<>(new String[]{"Activa", "Finalizada", "En proceso"});
        estadoComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
        estadoComboBox.setBounds(241, 112, 200, 30);
        panel.add(estadoComboBox);

        // Botón Guardar
        btnGuardar = new BotonRedondeado("Guardar", null);
        btnGuardar.setBounds(232, 200, 100, 40);
        btnGuardar.setBackground(new Color(0x13427e));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnGuardar.setFocusPainted(false);
        btnGuardar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ae) {
		    	try {
		    	    Temporada nuevaTemporada = new Temporada();
		    	    nuevaTemporada.setPeriodo(periodoField.getText());
		    	    nuevaTemporada.setEstado((String) estadoComboBox.getSelectedItem());
		    	    nuevaTemporada.setEquipos(new ArrayList<>());
		    	    nuevaTemporada.setJornadas(new ArrayList<>());

		    	    nuevaTemporada.guardarTemporada(nuevaTemporada);
		            
		    	    JOptionPane.showMessageDialog(getContentPane(), "Temporada agregada correctamente.");
		            dispose();
		    	} catch (IOException e) {
		    	    System.out.println("Error: " + e.getMessage());
		    	}
		    }
		});
        panel.add(btnGuardar);

        // Botón Cancelar
        btnCancelar = new BotonRedondeado("Cancelar", null);
        btnCancelar.setBounds(352, 200, 110, 40);
        btnCancelar.setBackground(new Color(0xf46b20));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(e -> dispose());
        panel.add(btnCancelar);
        
        
        
    }
}
