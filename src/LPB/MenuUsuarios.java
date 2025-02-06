package LPB;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import LPBCLASES.BotonRedondeado;
import LPBCLASES.Equipo;
import LPBCLASES.PasswordRedondeado;
import LPBCLASES.Temporada;
import LPBCLASES.TextoRedondeado;
import LPBCLASES.Usuario;
import LPBCLASES.logClase;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class MenuUsuarios extends JFrame implements ActionListener,Serializable {

    private static final long serialVersionUID = 1L;
    private JPanel panelSuperior;
    private JPanel panelInferior;
    private ImageIcon logo;
    private JLabel labelLogo;
    private JLabel titulo;
    private JTextField textUsuario;
    private JPasswordField passwordField;
    private JLabel lblEquipo;
    private JComboBox<String> comboBoxRol;
    private JComboBox<String> comboBoxEquipo;
    private BotonRedondeado btnGuardar, btnEliminar, btnVolver, btnLimpiar;
    private JTable tablaUsuarios;
    private DefaultTableModel dtm;
    private JTableHeader header;
    private ArrayList<Usuario> listaUsuarios;
    private JScrollPane scrollPane;
    private JLabel lblUsuario;
    private JLabel lblContrasena;
    private JLabel lblRol;
    private String usuario, contrasena, rol;
    private Equipo equipo;
    private Equipo equipoSeleccionado;
    private Usuario usuarioExistente;
    private Usuario nuevoUsuario;
    private int usuarioSeleccionadoIndex = -1;
    private static boolean actualizacion = false;

    private static final String ARCHIVO_USUARIOS = "data/usuarios.ser";

    public MenuUsuarios() {
    	
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/basketball.png")));
        setTitle("LPB Basketball - Menú de Usuarios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 550);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(255, 243, 205));
        panelSuperior.setBounds(0, 0, 836, 110);
        panelSuperior.setLayout(null);

        logo = new ImageIcon(getClass().getResource("/imagenes/logo150.png"));
        labelLogo = new JLabel(logo);
        labelLogo.setBounds(686, -20, 150, 150);
        panelSuperior.add(labelLogo);

        titulo = new JLabel("Usuarios");
        titulo.setBounds(10, 28, 306, 47);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 50));
        titulo.setForeground(new Color(0x13427e));
        panelSuperior.add(titulo);

        getContentPane().add(panelSuperior);

        panelInferior = new JPanel();
        panelInferior.setBackground(new Color(204, 153, 102));
        panelInferior.setBounds(0, 110, 836, 403);
        panelInferior.setLayout(null);

        dtm = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        dtm.addColumn("Usuario");
        dtm.addColumn("Rol");
        dtm.addColumn("Equipo");

        tablaUsuarios = new JTable(dtm);
        tablaUsuarios.setFont(new Font("SansSerif", Font.PLAIN, 16));
        tablaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaUsuarios.setForeground(Color.BLACK);
        tablaUsuarios.setBackground(new Color(217, 217, 217));
        tablaUsuarios.setRowHeight(30);
        tablaUsuarios.setBorder(null);
        tablaUsuarios.setShowGrid(false);
        
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        tablaUsuarios.setDefaultRenderer(Object.class, cellRenderer);
        
        tablaUsuarios.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if (value != null && !"N/A".equals(value.toString())) {
                    label.setToolTipText(value.toString());
                } else {
                    label.setToolTipText(null);
                }
                
                return label;
            }
        });
        
	    header = tablaUsuarios.getTableHeader();
	    header.setPreferredSize(new Dimension(header.getWidth(), 30));
	    header.setDefaultRenderer(new DefaultTableCellRenderer() {
	        private static final long serialVersionUID = 1L;

	        @Override
	        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	            label.setBackground(new Color(112, 117, 126));
	            label.setForeground(Color.WHITE);
	            label.setBorder(BorderFactory.createEmptyBorder());
	            return label;
	        }
	    });
	    
	    tablaUsuarios.getTableHeader().setReorderingAllowed(false);

        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = tablaUsuarios.getSelectedRow();
                if (selectedRow != -1) {
                    String usuarioSeleccionado = (String) dtm.getValueAt(selectedRow, 0);
                    
                    for (Usuario u : listaUsuarios) {
                        if (u.getUsuario().equals(usuarioSeleccionado)) {
                            textUsuario.setText(u.getUsuario());
                            passwordField.setText(u.getContrasena());
                            comboBoxRol.setSelectedItem(u.getRol());

                            if ("Entrenador".equals(u.getRol()) && u.getEquipo() != null) {
                                Equipo equipoSeleccionado = u.getEquipo();
                                String nombreEquipo = equipoSeleccionado.getNombre();
                                
                                boolean esNuevo = false;
                                for (File archivo : new File("data").listFiles((d, name) -> name.startsWith("temporada_") && name.endsWith(".ser"))) {
                                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                                        Temporada temporada = (Temporada) ois.readObject();
                                        if (temporada.getEstado().equals("En proceso")) {
                                            List<Equipo> equipos = temporada.getEquipos();
                                            for (Equipo eq : equipos) {
                                                if (eq.getNombre().equals(nombreEquipo)) {
                                                    esNuevo = true;
                                                    break;
                                                }
                                            }
                                        }
                                    } catch (IOException | ClassNotFoundException ex) {
                                        System.err.println("Error al cargar el archivo: " + ex.getMessage());
                                    }
                                    if (esNuevo) break;
                                }

                                if (esNuevo) {
                                    nombreEquipo += " (Nuevo)";
                                }

                                comboBoxEquipo.setSelectedItem(nombreEquipo);
                            }
                            
                            usuarioSeleccionadoIndex = listaUsuarios.indexOf(u);
                            break;
                        }
                    }
                }
            }
        });

        scrollPane = new JScrollPane(tablaUsuarios);
        scrollPane.setBounds(30, 30, 400, 300);
        scrollPane.getViewport().setBackground(new Color(204, 153, 102));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panelInferior.add(scrollPane);

        lblUsuario = new JLabel("Usuario:");
        lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
        lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblUsuario.setForeground(new Color(0x545454));
        lblUsuario.setBounds(460, 71, 100, 30);
        panelInferior.add(lblUsuario);

        textUsuario = new TextoRedondeado(20);
        textUsuario.setColumns(10);
        textUsuario.setFont(new Font("SansSerif", Font.PLAIN, 16));
        textUsuario.setBounds(570, 71, 200, 30);
        panelInferior.add(textUsuario);

        lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setHorizontalAlignment(SwingConstants.RIGHT);
        lblContrasena.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblContrasena.setForeground(new Color(0x545454));
        lblContrasena.setBounds(460, 121, 100, 30);
        panelInferior.add(lblContrasena);

        passwordField = new PasswordRedondeado(20);
        passwordField.setColumns(10);
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        passwordField.setBounds(570, 121, 200, 30);
        panelInferior.add(passwordField);

        lblRol = new JLabel("Rol:");
        lblRol.setHorizontalAlignment(SwingConstants.RIGHT);
        lblRol.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblRol.setForeground(new Color(0x545454));
        lblRol.setBounds(460, 171, 100, 30);
        panelInferior.add(lblRol);

        comboBoxRol = new JComboBox<>();
        String[] roles = {"Administrador", "Árbitro", "Entrenador", "Usuario"};
        for (String rol : roles) {
            comboBoxRol.addItem(rol);
        }
        comboBoxRol.setFont(new Font("SansSerif", Font.PLAIN, 16));
        comboBoxRol.setBounds(570, 171, 200, 30);
        
        comboBoxRol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rolSeleccionado = (String) comboBoxRol.getSelectedItem();
                
                if ("Entrenador".equals(rolSeleccionado)) {
                    lblEquipo.setVisible(true);
                    comboBoxEquipo.setVisible(true);
                } else {
                    lblEquipo.setVisible(false);
                    comboBoxEquipo.setVisible(false);
                }
            }
        });
        
        panelInferior.add(comboBoxRol);
        
        lblEquipo = new JLabel("Equipo:");
        lblEquipo.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEquipo.setForeground(new Color(84, 84, 84));
        lblEquipo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEquipo.setBounds(460, 223, 100, 30);
        panelInferior.add(lblEquipo);
        lblEquipo.setVisible(false);
        
        comboBoxEquipo = new JComboBox<String>();
        comboBoxEquipo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        comboBoxEquipo.setBounds(570, 223, 200, 30);
        panelInferior.add(comboBoxEquipo);
        comboBoxEquipo.setVisible(false);

        btnGuardar = new BotonRedondeado("Guardar", null);
        btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnGuardar.setBackground(new Color(0x13427E));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBounds(549, 275, 100, 40);
        btnGuardar.setFocusPainted(false);
        btnGuardar.addActionListener(this);
        panelInferior.add(btnGuardar);

        btnEliminar = new BotonRedondeado("Eliminar", null);
        btnEliminar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnEliminar.setBackground(new Color(0xf46b20));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setBounds(669, 275, 100, 40);
        btnEliminar.setFocusPainted(false);
        btnEliminar.addActionListener(this);
        panelInferior.add(btnEliminar);

        btnVolver = new BotonRedondeado("Volver", null);
        btnVolver.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnVolver.setBackground(new Color(0x404040));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBounds(669, 325, 100, 40);
        btnVolver.setFocusPainted(false);
		btnVolver.addActionListener(this);
		panelInferior.add(btnVolver);
		
        btnLimpiar = new BotonRedondeado("Limpiar", null);
        btnLimpiar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnLimpiar.setBackground(new Color(0x404040));
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setBounds(549, 325, 100, 40);
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.addActionListener(this);
        panelInferior.add(btnLimpiar);
		
        listaUsuarios = cargarUsuarios();
		actualizarTabla();
		cargarEquipos();

        getContentPane().add(panelInferior);
    }

    
    
    
    private void actualizarTabla() {
        dtm.setRowCount(0);
        
        for (Usuario usuario : listaUsuarios) {
        	dtm.addRow(new Object[]{usuario.getUsuario(), usuario.getRol(), usuario.getEquipo() != null ? usuario.getEquipo().getNombre() : "N/A"});
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Usuario> cargarUsuarios() {
        File archivo = new File(ARCHIVO_USUARIOS);
        
        // Si el archivo no existe, crear el usuario por defecto
        if (!archivo.exists()) {
            System.out.println("El archivo " + ARCHIVO_USUARIOS + " no existe. Creando usuario 'Admin' por defecto.");

            // Crear la lista con el usuario predeterminado
            ArrayList<Usuario> usuarios = new ArrayList<>();
            Usuario admin = new Usuario("Admin", "123", "Administrador", null);
            usuarios.add(admin);

            // Guardar el usuario en el archivo
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
                oos.writeObject(usuarios);
                System.out.println("Usuario 'Admin' creado y guardado correctamente.");
            } catch (IOException e) {
                System.err.println("Error al guardar el usuario predeterminado: " + e.getMessage());
            }

            return usuarios;
        }

        // Si el archivo existe, cargar los usuarios
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_USUARIOS))) {
            return (ArrayList<Usuario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar los usuarios: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    
    public static Usuario validarUsuario(String usuario, String contrasena) {
        ArrayList<Usuario> usuarios = cargarUsuarios(); // Cargar los usuarios desde el archivo
        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuario) && u.getContrasena().equals(contrasena)) {
                return u; // Usuario encontrado
            }
        }
        return null; // Usuario no encontrado
    }

    public static void guardarUsuarios(ArrayList<Usuario> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_USUARIOS))) {
            oos.writeObject(usuarios);
            if (actualizacion == false) {
            	JOptionPane.showMessageDialog(  null, "Usuario guardado correctamente. ", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
            actualizacion = false;
        } catch (IOException e) {
        	 JOptionPane.showMessageDialog(  null,"Error al guardar los usuarios: " + e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
        
        }
    }
    
    private void cargarEquipos() {
        File dir = new File("data");
        
        File[] archivos = dir.listFiles((d, name) -> name.startsWith("temporada_") && name.endsWith(".ser"));

        for (File archivo : archivos) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                Temporada temporada = (Temporada) ois.readObject();

                if ("Activa".equals(temporada.getEstado()) || "En proceso".equals(temporada.getEstado())) {
                    List<Equipo> equipos = temporada.getEquipos();

                    for (Equipo equipo : equipos) {
                        if ("En proceso".equals(temporada.getEstado())) {
                            comboBoxEquipo.addItem(equipo.getNombre() + " (Nuevo)");
                        } else {
                            comboBoxEquipo.addItem(equipo.getNombre());
                        }
                    }
                }

            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al cargar el archivo: " + archivo.getName());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	 Object source = e.getSource();

    	// === INICIO: LOGGING PARA GUARDAR USUARIO ===
    	    if (source == btnGuardar) {
    	        usuario = textUsuario.getText().trim();
    	        contrasena = new String(passwordField.getPassword());
    	        rol = (String) comboBoxRol.getSelectedItem();
    	        
    	        if ((String) comboBoxEquipo.getSelectedItem() != null) {
        	        String nombreEquipoSeleccionado = (String) comboBoxEquipo.getSelectedItem();
        	        
        	        if (nombreEquipoSeleccionado.endsWith(" (Nuevo)")) {
        	            nombreEquipoSeleccionado = nombreEquipoSeleccionado.substring(0, nombreEquipoSeleccionado.length() - 8);
        	        }
        	        
        	        if ("Entrenador".equals(rol)) {
        	            for (File archivo : new File("data").listFiles((d, name) -> name.startsWith("temporada_") && name.endsWith(".ser"))) {
        	                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
        	                    Temporada temporada = (Temporada) ois.readObject();
        	                    List<Equipo> equipos = temporada.getEquipos();

        	                    // Buscar el equipo que coincida con el nombre seleccionado
        	                    for (Equipo eq : equipos) {
        	                        if (eq.getNombre().equals(nombreEquipoSeleccionado)) {
        	                            equipoSeleccionado = eq;
        	                            break;
        	                        }
        	                    }
        	                } catch (IOException | ClassNotFoundException e1) {
        	                    System.err.println("Error al cargar el archivo: " + archivo.getName());
        	                }

        	                if (equipoSeleccionado != null) break;
        	            }

        	            // Si no se encontró el equipo, mostrar un mensaje de error
        	            if (equipoSeleccionado == null) {
        	                JOptionPane.showMessageDialog(this, "No se pudo encontrar el equipo seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
        	                return;
        	            }
        	        }
    	        }

    	        if (usuario.isEmpty() || contrasena.isEmpty()) {
    	            JOptionPane.showMessageDialog(this, "Por favor, rellena todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    	            return;
    	        }

    	        // Si hay un usuario seleccionado, actualizamos sus datos
    	        if (usuarioSeleccionadoIndex >= 0) {
    	            usuarioExistente = listaUsuarios.get(usuarioSeleccionadoIndex);
    	            usuarioExistente.setUsuario(usuario);
    	            usuarioExistente.setContrasena(contrasena);
    	            usuarioExistente.setRol(rol);
    	            usuarioExistente.setEquipo(equipoSeleccionado);
    	            equipoSeleccionado = null;

    	            // Actualizar la lista visual
    	            actualizarTabla();
    	            actualizacion = true;

    	            JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);

    	            // Registrar el evento en el log
    	            logClase.logAction("El usuario '" + usuario + "' ha sido modificado (Rol: " + rol + ", Equipo: " + equipoSeleccionado + ").");

    	        } else {
    	            // Si no hay un usuario seleccionado, se crea uno nuevo
    	            nuevoUsuario = new Usuario(usuario, contrasena, rol, equipoSeleccionado);
    	            listaUsuarios.add(nuevoUsuario);
    	            equipoSeleccionado = null;
    	            actualizarTabla();

    	            // Registrar el evento en el log
    	            logClase.logAction("Se ha agregado un nuevo usuario: '" + usuario + "' (Rol: " + rol + ", Equipo: " + equipo + ").");
    	        }

    	        // Guardar los cambios en el archivo
    	        guardarUsuarios(listaUsuarios);

    	        // Limpiar los campos y reiniciar el índice
    	        textUsuario.setText("");
    	        passwordField.setText("");
    	        comboBoxRol.setSelectedIndex(0);
    	        comboBoxEquipo.setSelectedIndex(0);
    	        usuarioSeleccionadoIndex = -1;
        	// === FIN: LOGGING PARA GUARDAR USUARIO ===
    	    } else if (source == btnEliminar) {
    	    // === INICIO: LOGGING PARA ELIMINAR USUARIO ===
    	        int index = tablaUsuarios.getSelectedRow();
    	        if (index >= 0) {
    	            Usuario usuarioEliminado = listaUsuarios.get(index);
    	            
    	            if (usuarioEliminado.getUsuario().equals("Admin")) {
    	                JOptionPane.showMessageDialog(this, "No se puede eliminar el usuario 'Admin'.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    	                return;
    	            }

    	            listaUsuarios.remove(index);

    	            // Actualizar la lista y guardar cambios
    	            actualizarTabla();
    	            guardarUsuarios(listaUsuarios);

    	            // Registrar el evento en el log
    	            logClase.logAction("Se ha eliminado el usuario: '" + usuarioEliminado.getUsuario() + "' (Rol: " + usuarioEliminado.getRol() + ", Equipo: " + usuarioEliminado.getEquipo() + ").");
    	        } else {
    	            JOptionPane.showMessageDialog(this, "Selecciona un usuario para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    	        }

    	        // Limpiar los campos y reiniciar el índice
    	        textUsuario.setText("");
    	        passwordField.setText("");
    	        comboBoxRol.setSelectedIndex(0);
    	        comboBoxEquipo.setSelectedIndex(0);
    	        usuarioSeleccionadoIndex = -1;
        	// === FIN: LOGGING PARA ELIMINAR USUARIO ===
    	    } else if (source == btnVolver) {
    	        new Menu("Administrador", "Admin").setVisible(true);
    	        this.dispose();
		    } else if (source == btnLimpiar) {
		    	tablaUsuarios.clearSelection();
    	        textUsuario.setText("");
    	        passwordField.setText("");
    	        comboBoxRol.setSelectedIndex(0);
    	        comboBoxEquipo.setSelectedIndex(0);
    	        usuarioSeleccionadoIndex = -1;
		    }
    }
}
