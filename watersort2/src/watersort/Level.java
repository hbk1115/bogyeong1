package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Level extends JFrame{
	
	public Level() {
		 
        this.setTitle("Level");
        JPanel jPanel = new JPanel();
        JButton btn1 = new JButton("Level1");
        JButton btn3 = new JButton("뒤로");
        
        setSize(500, 500);
        jPanel.add(btn1);
        jPanel.add(btn3);
        add(jPanel);
        
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
        
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	new Main2();
                
            }
        });
    }

}
