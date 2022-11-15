package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Level extends JFrame{
	Clear clearcheck;
	
	public Level() {
		clearcheck = new Clear();
		
        this.setTitle("Level");
        JPanel jPanel = new JPanel();
        JButton btn1 = new JButton("Level1");
        JButton btn2 = new JButton("Level2");
        JButton btn3 = new JButton("Level3");
        JButton btn4 = new JButton("Level4");
        JButton btn5 = new JButton("뒤로");
        
        setSize(500, 500);
        jPanel.add(btn1);
        jPanel.add(btn2);
        jPanel.add(btn3);
        jPanel.add(btn4);
        jPanel.add(btn5);
        add(jPanel);

        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
        
        if(clearcheck.clear >= 1) {
			btn2.setEnabled(true);
			if(clearcheck.clear >= 2) {
				btn3.setEnabled(true);
				if(clearcheck.clear >= 3) {
					btn4.setEnabled(true);
				}
			}

		}
        
        setLocationRelativeTo(null);
 
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	new Level1();
                
            }
        });
        
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	new Level2();
                
            }
        });
        
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	new Level3();
                
            }
        });
        
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	new Level4();
                
            }
        });
        
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	new Main2();
                
            }
        });
    }

}
