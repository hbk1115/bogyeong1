package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Join extends JFrame{
	public Join() {
		 
        this.setTitle("Join");
        JPanel jPanel = new JPanel();
        JButton btn3 = new JButton("뒤로");
        
        setSize(500, 500);
        jPanel.add(btn3);
        add(jPanel);
        
        setLocationRelativeTo(null);
 
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	new Main();
                
            }
        });
    }

}
