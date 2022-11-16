package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main2 extends JFrame{
	public Main2() {
		 
        this.setTitle("Main2");
        setLayout(null);
        
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(3, 1, 10, 10));
        JLabel jLabel = new JLabel("MENU");
        JButton btn1 = new JButton("게임시작");
        JButton btn2 = new JButton("순위");
        JButton btn3 = new JButton("뒤로");
        
        jPanel.add(btn1);
        jPanel.add(btn2);
        jPanel.add(btn3);
        jPanel.setBounds(120, 180, 250, 200);
        
        
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        jLabel.setFont(new Font("Gothic", Font.BOLD, 50));
        jLabel.setBounds(0, 50, 500, 100);
        
        add(jLabel);
        add(jPanel);
        
        setSize(500, 500);
        setLocationRelativeTo(null);
 
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
 
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Level();
                setVisible(false);
            }
        });
        
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Rank();
                setVisible(false);
            }
        });
        
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Main();
                setVisible(false);
            }
        });
    }

}
