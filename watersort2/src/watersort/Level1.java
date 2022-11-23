package watersort;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Level1 {
	private int level1 = 1;
	
	public Level1() {
		WaterSortGame waterSortGame = new WaterSortGame();
		waterSortGame.play(level1);
	}

}