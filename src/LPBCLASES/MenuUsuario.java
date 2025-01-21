package LPBCLASES;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuUsuario extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4304154666906037389L;
	private GestorUsuarios gestorUsuarios;
    private JTextField tfUsuario;
    private JPasswordField tfContrasena;
    private JCheckBox cbEsAdmin;
    private JTextArea taUsuarios;

    public MenuUsuario() {
        gestorUsuarios = new GestorUsuarios();

        setTitle("Gestión de Usuarios");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel superior para agregar usuarios
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new GridLayout(3, 2, 10, 10));
        panelSuperior.setBorder(BorderFactory.createTitledBorder("Añadir Usuario"));

        tfUsuario = new JTextField();
        tfContrasena = new JPasswordField();
        cbEsAdmin = new JCheckBox("Es Administrador");

        panelSuperior.add(new JLabel("Usuario:"));
        panelSuperior.add(tfUsuario);
        panelSuperior.add(new JLabel("Contraseña:"));
        panelSuperior.add(tfContrasena);
        panelSuperior.add(new JLabel("Permisos:"));
        panelSuperior.add(cbEsAdmin);

        // Botón para agregar usuario
        JButton btnAgregar = new JButton("Agregar Usuario");
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarUsuario();
            }
        });

        // Panel central para listar usuarios
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        panelCentral.setBorder(BorderFactory.createTitledBorder("Usuarios Registrados"));

        taUsuarios = new JTextArea();
        taUsuarios.setEditable(false);
        JScrollPane scrollUsuarios = new JScrollPane(taUsuarios);
        panelCentral.add(scrollUsuarios, BorderLayout.CENTER);

        // Botón para eliminar usuario
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton btnEliminar = new JButton("Eliminar Usuario");
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();
            }
        });

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panelInferior.add(btnEliminar);
        panelInferior.add(btnCerrar);

        // Añadir componentes al frame
        add(panelSuperior, BorderLayout.NORTH);
        add(btnAgregar, BorderLayout.CENTER);
        add(panelCentral, BorderLayout.SOUTH);
        add(panelInferior, BorderLayout.PAGE_END);

        actualizarListaUsuarios();
    }

    private void agregarUsuario() {
        String usuario = tfUsuario.getText().trim();
        String contrasena = new String(tfContrasena.getPassword());
        boolean esAdmin = cbEsAdmin.isSelected();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El usuario y la contraseña no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        gestorUsuarios.agregarUsuario(usuario, contrasena, esAdmin);
        JOptionPane.showMessageDialog(this, "Usuario agregado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        tfUsuario.setText("");
        tfContrasena.setText("");
        cbEsAdmin.setSelected(false);

        actualizarListaUsuarios();
    }

    private void eliminarUsuario() {
        String usuario = JOptionPane.showInputDialog(this, "Ingrese el nombre del usuario a eliminar:");

        if (usuario == null || usuario.trim().isEmpty()) {
            return; // Si el usuario cancela o no ingresa nada, no hacer nada
        }

        boolean eliminado = gestorUsuarios.eliminarUsuario(usuario.trim());

        if (eliminado) {
            JOptionPane.showMessageDialog(this, "Usuario eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Usuario no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        actualizarListaUsuarios();
    }

    private void actualizarListaUsuarios() {
        StringBuilder sb = new StringBuilder();

        for (Usuario usuario : gestorUsuarios.getUsuarios()) {
            sb.append("Usuario: ").append(usuario.getUsuario());
        }

        taUsuarios.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuUsuario menu = new MenuUsuario();
            menu.setVisible(true);
        });
    }
}
