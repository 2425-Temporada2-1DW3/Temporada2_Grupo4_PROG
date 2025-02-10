package LPBCLASES;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * La clase BotonRedondeado extiende {@link JButton} para proporcionar un botón
 * con bordes redondeados. Permite la personalización de texto e icono y mejora
 * la apariencia visual con suavizado de bordes.
 * 
 * @see JButton
 */
public class BotonRedondeado extends JButton {
	private static final long serialVersionUID = 5137357109195317030L;

	/**
     * Constructor que inicializa un botón redondeado con texto e icono opcional.
     * 
     * @param text Texto a mostrar en el botón.
     * @param icon Icono a mostrar en el botón (puede ser {@code null}).
     */
	public BotonRedondeado(String text, ImageIcon icon) {
		super(text, icon != null ? icon : new ImageIcon());
		setContentAreaFilled(false);
		setHorizontalTextPosition(JButton.RIGHT); // Texto a la derecha del icono
        setVerticalTextPosition(JButton.CENTER); // Alinear verticalmente el texto
        setIconTextGap(4); // Espacio entre icono y texto
	}

	/**
     * Sobrescribe el método {@code paintComponent} para personalizar el fondo del botón.
     * Se aplica un efecto de suavizado y se crea un rectángulo redondeado.
     * 
     * @param g Objeto {@link Graphics} utilizado para el dibujo del componente.
     */
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBackground());
		g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));
		super.paintComponent(g2);
		g2.dispose();
	}

	/**
     * Sobrescribe el método {@code paintBorder} para evitar la pintura del borde predeterminado.
     * Esto garantiza que el botón mantenga su apariencia redondeada sin bordes visibles.
     * 
     * @param g Objeto {@link Graphics} utilizado para el dibujo del componente.
     */
	@Override
	protected void paintBorder(Graphics g) {
	}
}