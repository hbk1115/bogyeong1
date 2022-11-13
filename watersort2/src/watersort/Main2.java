package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main2 extends JFrame{
	public Main2() {
		 
        this.setTitle("Main2");
        JPanel jPanel = new JPanel();
        JButton btn1 = new JButton("게임시작");
        JButton btn2 = new JButton("순위");
        JButton btn3 = new JButton("뒤로");
        setSize(500, 500);
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
