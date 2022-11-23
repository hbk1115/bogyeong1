package watersort;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Level2 {
	private int level2 = 2;
	
	public Level2() {
		WaterSortGame waterSortGame = new WaterSortGame();
		waterSortGame.play(level2);
	}
}