package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class Level extends JFrame{
	
	private final int MAX_LEVEL = 6;
	
	private final JPanel levelPanel = new JPanel();
	private final JLabel levelTitle = new JLabel("LEVEL");
	private JButton backBtn;
	private JButton[] levelBtn = new JButton[MAX_LEVEL];
	
	private final List<String> level = new ArrayList<>();
	
	//private Clear clear;
	private WaterSortGame waterSortGame;
	private int userLevel;
	
	public Level() {
		//clear = new Clear();
		waterSortGame = new WaterSortGame();
		
		setLevelLayout();
        
		levelAction();
    }

	private void setLevelLayout() {
		this.setTitle("Level");
        setLayout(null);
        
        for (int i = 1; i <= levelBtn.length; i++) {
        	//level.add("Level" + i);	
        	level.add("image/Level" + i + ".png");
        }
        
        for (int i = 0; i < levelBtn.length; i++) {
        	levelBtn[i] = makeUI(level.get(i));
        	limpidity(levelBtn[i]);
        	levelBtn[i].setEnabled(false);
        }
        
        int row = level.size() / 3;
        levelPanel.setLayout(new GridLayout(row, 3, 10, 10));
        levelPanel.setBounds(15, 100, 450, row * 60);
        
        levelTitle.setFont(new Font("Gothic", Font.BOLD, 40));
        levelTitle.setBounds(180, 20, 300, 40);
        
        backBtn = makeUI("image/Back.png");
        limpidity(backBtn);
        backBtn.setBounds(15, 400, 100, 50);
        
        for (int i = 0; i < levelBtn.length; i++) {
        	levelPanel.add(levelBtn[i]);
        }       
        
        add(levelTitle);
        add(levelPanel);
        add(backBtn);
        
        for(int i = 0; i < levelBtn.length; i++) {
        	if(getUserLevel() >= i) {
        		levelBtn[i].setEnabled(true);
        	}
        }
        
        setSize(500, 500);
        setLocationRelativeTo(null);
 
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
	}
	
	private int getUserLevel() {
		DataBase dataBase = new DataBase();
		LogIn login = new LogIn();
		int id = login.getUserId();
		return dataBase.getLevel(id);
	}
	
	private JButton makeUI(String name) {
		ImageIcon icon = new ImageIcon(name);
		Image image = icon.getImage();
		image = image.getScaledInstance(100, 100, Image.SCALE_FAST);
		return new JButton(new ImageIcon(image));
	}
	
	private void limpidity(JButton btn) {
		btn.setBorderPainted(false);
		btn.setContentAreaFilled(false);
		btn.setFocusPainted(false);
		btn.setOpaque(false);
	}
	
	private void levelAction() {
		chooseLevelAction();
		backAction();
	}
	
	private void chooseLevelAction() {
		for (int i = 0; i < levelBtn.length; i++) {
	        levelBtn[i].addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
//	            	String level = e.getActionCommand().toString();
//	            	WaterSortGame waterSortGame = new WaterSortGame();
//	        		waterSortGame.play(Integer.parseInt(level.split("")[5]));
//	            	setVisible(false);
	            	for(int i = 0; i < levelBtn.length; i++) {
	            		JButton click = (JButton) e.getSource();
		            	if (levelBtn[i] == click) {
		            		waterSortGame.play(i+1);
		            		setVisible(false);
		            	}
	            	}
	            }
	        });
        }
	}
	
	private void backAction() {
		backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	new Menu();
            }
        });
	}
}
