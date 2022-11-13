package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame{
	
	private WaterSort_main watersort;
	
	JPanel jPanel;
    JButton btn1;
    JButton btn2;
    JButton btn3;
	
	public Main() {
		Ma();
	}
	
	public void Ma() {
		
        setTitle("Main");
        setSize(500, 500);
        jPanel = new JPanel();
        btn1 = new JButton("로그인");
        btn2 = new JButton("회원가입");
        btn3 = new JButton("뒤로");
        
        jPanel.add(btn1);
        jPanel.add(btn2);
        jPanel.add(btn3);
        add(jPanel);
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        
 
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
                new Main2();
                
            }
        });
        
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
                new Join();
                
            }
        });
        
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.exit(0);
            }
        });
    }
	
	public static void main(String args[]) {
		new Main();
	}

}
