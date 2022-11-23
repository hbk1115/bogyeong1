package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Join extends JFrame{
	
	private JButton backBtn;
	
	public Join() {
		setJoinLayout();
        
		backAction();
    }
	
	private void setJoinLayout() {
		this.setTitle("Join");
        JPanel jPanel = new JPanel();
        backBtn = new JButton("뒤로");
        
        setSize(500, 500);
        jPanel.add(backBtn);
        add(jPanel);
        
        setLocationRelativeTo(null);
 
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
	}

	private void backAction() {
		backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	new Main();
                
            }
        });
	}
}
