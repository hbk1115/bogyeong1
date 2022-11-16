package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame{
	
	JLabel jLabel;
	JPanel jPanel;
    JButton btn1;
    JButton btn2;
    JButton btn3;
	
	public Main() {
		Ma();
	}
	
	public void Ma() {
		
        setTitle("Main");
        setLayout(null);
        
        jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(3, 1, 10, 10));
        btn1 = new JButton("로그인");
        btn2 = new JButton("회원가입");
        btn3 = new JButton("게임 종료");
        
        jPanel.add(btn1);
        jPanel.add(btn2);
        jPanel.add(btn3);
        jPanel.setBounds(120, 180, 250, 200);
        
        jLabel = new JLabel("WaterSort Game!!");
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        jLabel.setFont(new Font("Gothic", Font.BOLD, 50));
        jLabel.setBounds(0, 50, 500, 100);
        
        add(jLabel);
        add(jPanel);
         
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
                //new Main2();
                new LogIn();
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