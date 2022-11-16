package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class Level extends JFrame{
	
	private JPanel levelPanel;
	private JLabel levelTitle;
	private JButton backBtn;
	private JButton[] levelBtn;
	
	private List<String> level;
	
	private final int MAX_LEVEL = 6;
	
	public Level() {
		 
        this.setTitle("Level");
        setLayout(null);
        
        levelPanel = new JPanel();
        levelTitle = new JLabel("LEVEL");
        level = new ArrayList<>();
        levelBtn = new JButton[MAX_LEVEL];
        backBtn = new JButton("뒤로");
        
        for (int i = 1; i <= levelBtn.length; i++) {
        	level.add("Level" + i);	
        }
        
        for (int i = 0; i < levelBtn.length; i++) {
        	levelBtn[i] = new JButton(level.get(i));
        }
        
        int row = level.size() / 3;
        levelPanel.setLayout(new GridLayout(row,3,10,10));
        levelPanel.setBounds(15, 100, 450, row * 50);
        
        levelTitle.setFont(new Font("Gothic", Font.BOLD, 40));
        levelTitle.setBounds(180, 20, 300, 40);
        
        backBtn.setBounds(15, 400, 100, 30);
        
        for (int i = 0; i < levelBtn.length; i++) {
        	levelPanel.add(levelBtn[i]);
        }       
        
        add(levelTitle);
        add(levelPanel);
        add(backBtn);
        
        setSize(500, 500);
        setLocationRelativeTo(null);
 
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        
        for (int i = 0; i < levelBtn.length; i++) {
	        levelBtn[i].addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	setVisible(false);
	            	new Level1();
	                
	            }
	        });
        }
        
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	new Main2();
                
            }
        });
    }

}
