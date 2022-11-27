package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Startgame extends JFrame{
	
	private final JButton backBtn = new JButton("뒤로");
	
	public Startgame() {
		 
		setStartGameLayout();
        
		setDimension();
       
        backAction();
    }

	private void setStartGameLayout() {
		this.setTitle("Level");
        JPanel jPanel = new JPanel();
        
        setSize(500, 500);
        jPanel.add(backBtn);
        add(jPanel);
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
	}
	
	private void setDimension() {
		Dimension frameSize = getSize();
        
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2);
	}
	
	private void backAction() {
		backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new Menu();
                setVisible(false);
            }
        });
	}
}
