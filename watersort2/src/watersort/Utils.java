package watersort;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Utils {
	
	public JButton makeUI(String name, int width) {
		ImageIcon icon = new ImageIcon(name);
		Image image = icon.getImage();
		image = image.getScaledInstance(width, 100, Image.SCALE_FAST);
		JButton btn = new JButton(new ImageIcon(image));
		limpidity(btn);
		return btn;
	}
	
	private void limpidity(JButton btn) {
		btn.setBorderPainted(false);
		btn.setContentAreaFilled(false);
		btn.setFocusPainted(false);
		btn.setOpaque(false);
	}

}
