package LPB;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LoadingDialogTest {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoadingDialogTest().mostrarDialogo();
        });
    }

    public void mostrarDialogo() {
        JDialog loadingDialog = new JDialog();
        loadingDialog.setModal(true);
        loadingDialog.setResizable(false);
        loadingDialog.setTitle("Cargando...");
        loadingDialog.setSize(400, 100);
        loadingDialog.setLocationRelativeTo(null);
        loadingDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        JLabel loadingLabel = new JLabel("Iniciando sesión...", SwingConstants.CENTER);
        loadingLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

        // Cargar GIF
        ImageIcon originalGif = new ImageIcon(getClass().getResource("/imagenes/basketball.gif"));
        ImageIcon scaledGif = getScaledGif(originalGif, 60, 60); // Escalar con suavidad

        JLabel gifLabel = new JLabel(scaledGif);
        
        // Panel con FlowLayout centrado
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(new Color(255, 255, 255));
        panel.add(loadingLabel);
        panel.add(gifLabel);

        loadingDialog.add(panel);
        loadingDialog.pack(); // Ajustar tamaño al contenido
        loadingDialog.setVisible(true);

        // Cerrar después de 3 segundos
        new Timer(3000, e -> loadingDialog.dispose()).start();
    }
    
    private ImageIcon getScaledGif(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.drawImage(img, 0, 0, width, height, null);
        g2d.dispose();
        return new ImageIcon(bufferedImage);
    }	
}
