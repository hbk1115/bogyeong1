package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame{
	
	private JLabel menu;
	private JButton startBtn, rankBtn, backBtn;
	
	public Menu() {
		setMenuLayout(); 
		
		menuAction();
    }

	private void setMenuLayout() {
		setTitle("Menu");
        setLayout(null);
        
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(3, 1, 10, 10));
        menu = new JLabel("MENU");
        startBtn = new JButton("게임시작");
        rankBtn = new JButton("순위");
        backBtn = new JButton("뒤로");
        
        jPanel.add(startBtn);
        jPanel.add(rankBtn);
        jPanel.add(backBtn);
        jPanel.setBounds(120, 180, 250, 200);
        
        menu.setHorizontalAlignment(JLabel.CENTER);
        menu.setFont(new Font("Gothic", Font.BOLD, 50));
        menu.setBounds(0, 50, 500, 100);
        
        add(menu);
        add(jPanel);
        
        setSize(500, 500);
        setLocationRelativeTo(null);
 
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
	}
	
	private void menuAction() {
		startAction();
		rankAction();
		backAction();
	}
	
	private void startAction() {
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Level();
                setVisible(false);
            }
        });
	}
	
	private void rankAction() {
        rankBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Rank();
                setVisible(false);
            }
        });
	}
	
	private void backAction() {
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Main();
                setVisible(false);
            }
        });
	}
}
