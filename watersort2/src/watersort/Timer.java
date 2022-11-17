package watersort;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Timer extends JFrame implements Runnable{
	
	private JLabel timel;
	int n2;
	int n;
	
	public Timer(JLabel timel) {
		this.timel = timel;
		this.n2 = 0;
		this.n = 0;
	}
	
	public void run() {
	
		while(true) {
			timel.setText(Integer.toString(n) +  " : " + Integer.toString(n2));
			n2++;
			try {
				if(n2 == 10) {
					n2 = 0;
					n++;
				}
				Thread.sleep(100);
				
			}
			catch(InterruptedException e) {
				return;
			}
			
		}
		
	}

}