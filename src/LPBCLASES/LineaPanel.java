package LPBCLASES;

import java.awt.Graphics;
import javax.swing.JPanel;

public class LineaPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
    }
}

