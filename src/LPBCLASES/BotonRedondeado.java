package LPBCLASES;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

public class BotonRedondeado extends JButton {
	private static final long serialVersionUID = 5137357109195317030L;

	public BotonRedondeado(String text) {
		super(text);
		setContentAreaFilled(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBackground());
		g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));
		super.paintComponent(g2);
		g2.dispose();
	}

	@Override
	protected void paintBorder(Graphics g) {
	}
}