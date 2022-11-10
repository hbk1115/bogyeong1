package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Startgame extends JFrame{
	public Startgame() {
		 
        this.setTitle("Level");
        JPanel jPanel = new JPanel();
        JButton btn3 = new JButton("뒤로");
        
        setSize(500, 500);
        jPanel.add(btn3);
        add(jPanel);
        
        Dimension frameSize = getSize();
        
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2);
 
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new Main2();
                setVisible(false);
            }
        });
    }

}
