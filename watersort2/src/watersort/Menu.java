package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame{
	
	private final JPanel jPanel = new JPanel();
	private final JLabel menu = new JLabel("MENU");
	private JButton startBtn;
	private JButton rankBtn;
	private JButton backBtn;
	
	public Menu() {
		setMenuLayout(); 
		
		menuAction();
    }

	private void setMenuLayout() {
		setTitle("Menu");
        setLayout(null);
        
        jPanel.setLayout(new GridLayout(3, 1, 10, 10));
        
        startBtn = makeUI("image/Start.png");
        rankBtn = makeUI("image/Rank.png");
        backBtn = makeUI("image/Back.png");
        limpidity(startBtn);
        limpidity(rankBtn);
        limpidity(backBtn);
        
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
	
	private JButton makeUI(String name) {
		ImageIcon icon = new ImageIcon(name);
		Image image = icon.getImage();
		image = image.getScaledInstance(100, 100, Image.SCALE_FAST);
		return new JButton(new ImageIcon(image));
	}
	
	private void limpidity(JButton btn) {
		btn.setBorderPainted(false);
		btn.setContentAreaFilled(false);
		btn.setFocusPainted(false);
		btn.setOpaque(false);
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
