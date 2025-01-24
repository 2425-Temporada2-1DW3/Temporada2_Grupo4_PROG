package LPB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import LPBCLASES.BotonRedondeado;
import LPBCLASES.Equipo;
import LPBCLASES.Jugador;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerEquipo extends JFrame {

    private static final long serialVersionUID = 1L;

    public VerEquipo(Equipo equipo, String rol, String usuario) {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
        setTitle("LPB Basketball - Detalles del Equipo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        // Encabezado superior
        JPanel panelIzquierdo = new JPanel();
		panelIzquierdo.setBackground(new Color(255, 243, 205));
		panelIzquierdo.setBounds(0, 0, 500, 613);
		panelIzquierdo.setLayout(null);
		
		JLabel labelUsuario = new JLabel("Usuario: " + usuario);
        labelUsuario.setFont(new Font("SansSerif", Font.PLAIN, 16));
        labelUsuario.setForeground(new Color(0x545454));
        labelUsuario.setBounds(23, 569, 200, 20);
        panelIzquierdo.add(labelUsuario);

        JLabel nombreLabel = new JLabel("" + equipo.getNombre());
        nombreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nombreLabel.setFont(new Font("SansSerif", Font.BOLD, 35));
        nombreLabel.setBounds(10, 24, 480, 133);
        nombreLabel.setForeground(new Color(60, 60, 60));
        panelIzquierdo.add(nombreLabel);
        
        JLabel entrenadorLabel = new JLabel("Entrenador: " + equipo.getEntrenador());
        entrenadorLabel.setFont(new Font("SansSerif", Font.PLAIN, 21));
        entrenadorLabel.setBounds(52, 242, 277, 23);
        entrenadorLabel.setForeground(new Color(60, 60, 60));
        panelIzquierdo.add(entrenadorLabel);
        
        
        JLabel estadioLabel = new JLabel("Estadio: " + equipo.getEstadio());
        estadioLabel.setFont(new Font("SansSerif", Font.PLAIN, 21));
        estadioLabel.setBounds(52, 328, 277, 23);
        estadioLabel.setForeground(new Color(60, 60, 60));
        panelIzquierdo.add(estadioLabel);
        
        JLabel fundacionLabel = new JLabel("Fundación: " + equipo.getFundacion());
        fundacionLabel.setFont(new Font("SansSerif", Font.PLAIN, 21));
        fundacionLabel.setBounds(52, 422, 277, 23);
        fundacionLabel.setForeground(new Color(60, 60, 60));
        panelIzquierdo.add(fundacionLabel);

        

        getContentPane().add(panelIzquierdo);

        // Panel principal inferior
        JPanel panelDerecho = new JPanel();
		panelDerecho.setBackground(new Color(204, 153, 102));
		panelDerecho.setBounds(500, 0, 487, 613);
		panelDerecho.setLayout(null);
		
		JLabel titulo = new JLabel("Jugadores");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 45));
        titulo.setForeground(new Color(0xfef4c6));
        titulo.setBounds(23, 20, 306, 64);
        panelDerecho.add(titulo);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(23, 105, 442, 349);
        panelDerecho.add(scrollPane);

        String[] columnNames = {"Foto", "Nombre", "Apellido", "Posición", "Dorsal"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        List<Jugador> jugadores = equipo.getJugadores();
        for (Jugador jugador : jugadores) {
            Object[] row = {
            		jugador.getPhotoPath(),
            		jugador.getNombre(),
                jugador.getApellidos(),
                
                jugador.getPosicion(),
                jugador.getDorsal(),
            };
            tableModel.addRow(row);
        }

        JTable tablaJugadores = new JTable(tableModel);
        tablaJugadores.setRowHeight(30);
        tablaJugadores.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tablaJugadores.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        scrollPane.setViewportView(tablaJugadores);

        // Botones de acción
        JButton btnVolver = new BotonRedondeado("Volver a Equipos", null);
        btnVolver.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnVolver.setBackground(new Color(64, 64, 64));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBounds(285, 542, 170, 40);
        btnVolver.setFocusPainted(false);
        btnVolver.addActionListener(_ -> dispose());
        panelDerecho.add(btnVolver);

        getContentPane().add(panelDerecho);
        
                JButton addButton = new JButton("Añadir Jugador");
                addButton.setBounds(149, 464, 200, 40);
                panelDerecho.add(addButton);
                addButton.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                	}
                });
                addButton.setFont(new Font("SansSerif", Font.BOLD, 16));
                addButton.setBackground(new Color(19, 66, 126));
                addButton.setForeground(new Color(0, 0, 160));
    }
}
