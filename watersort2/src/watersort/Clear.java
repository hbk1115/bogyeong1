package watersort;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Clear {
	static int clear = 0;
	
	public Clear() {
	}
	public void run(int i) {
		if(clear < i) {
			clear = i;
		}
		
	}

}
