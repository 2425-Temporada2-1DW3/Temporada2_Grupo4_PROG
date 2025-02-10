package LPBCLASES;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * Clase LineaPanel que extiende JPanel y dibuja una línea horizontal
 * en el centro del panel. Utiliza Graphics2D para definir el grosor de la línea.
 */
public class LineaPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    /**
     * Sobrescribe el método paintComponent para dibujar una línea en el panel.
     * @param g Objeto Graphics que se usa para dibujar en el panel.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(2));

        g2d.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
    }
}

