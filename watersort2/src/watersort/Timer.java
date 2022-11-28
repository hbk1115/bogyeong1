package watersort;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Timer extends JFrame implements Runnable {
	
	private Clear clear;
	private JLabel timel;
	private WaterSortGame waterSortGame;
	private int millisecond;
	private int second;
	
	public Timer(JLabel timel) {
		clear = new Clear();
		this.timel = timel;
		this.waterSortGame = new WaterSortGame();
		this.millisecond = 0;
		this.second = 0;
	}
	
	public void run() {
	
		while(true) {
			timel.setText(Integer.toString(second) +  " : " + Integer.toString(millisecond));
			++millisecond;
			waterSortGame.operation();
			try {
				if(millisecond == 10) {
					millisecond = 0;
					++second;
				}
				Thread.sleep(100);
				
			}
			catch(InterruptedException e) {
				return;
			}
			
		}
		
	}

}