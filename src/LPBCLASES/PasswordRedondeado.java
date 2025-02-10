package LPBCLASES;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JPasswordField;
/**
 * Clase PasswordRedondeado que extiende JPasswordField para crear un campo de contraseña
 * con bordes redondeados y efecto de enfoque. Se cambia el color del borde cuando el campo
 * gana o pierde el foco.
 */
public class PasswordRedondeado extends JPasswordField {
    private static final long serialVersionUID = 1L;
    /**
     * Constructor que crea un campo de contraseña con el número de columnas especificado.
     * @param columns Número de columnas (anchura del campo en caracteres).
     */
    public PasswordRedondeado(int columns) {
        super(columns);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
        
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                repaint();
            }
        });
    }
    /**
     * Sobrescribe el método paintComponent para dibujar un campo de contraseña con bordes redondeados.
     * @param g Objeto Graphics utilizado para dibujar el campo.
     */
    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();

        g.setColor(getBackground());
        g.fillRoundRect(0, 0, width, height, 8, 8);

        Graphics2D g2d = (Graphics2D) g;

        int borderWidth = 1;
        if (hasFocus()) {
            g2d.setColor(Color.BLACK);
            borderWidth = 2;
        } else {
            g2d.setColor(new Color(169, 169, 169));
        }

        int borderX = 1;
        int borderY = 1;
        int borderWidthCorrected = width - 2;
        int borderHeightCorrected = height - 2;

        g2d.setStroke(new BasicStroke(borderWidth));
        g2d.drawRoundRect(borderX, borderY, borderWidthCorrected, borderHeightCorrected, 8, 8);

        super.paintComponent(g);
    }
}
