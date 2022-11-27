package watersort;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame{
	
	private JLabel menu;
	private JButton startBtn, rankBtn, backBtn;
	
	public ImageIcon Buttons(ImageIcon images, ImageIcon change) {
		ImageIcon image = images;
		Image img;
	    Image changeImg;
	    
	    img = image.getImage();
	    changeImg = img.getScaledInstance(100, 100, Image.SCALE_FAST);
	    change = new ImageIcon(changeImg);
	    
	    return change;
	}

	public void limpidity(JButton b) {
		b.setBorderPainted(false);
	    b.setContentAreaFilled(false);
	    b.setFocusPainted(false);
	    b.setOpaque(false);
	}
	
	public JButton UI(JButton jb, String a) {
		ImageIcon Ic = null;
		Ic = Buttons(new ImageIcon(a), Ic);
		jb = new JButton(Ic);
		limpidity(jb);
		return jb;
	}
	
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
        startBtn = UI(startBtn, "Start.png");
        rankBtn = UI(rankBtn, "Rank.png");
        backBtn = UI(backBtn, "Back.png");
        
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
            	Login lo = new Login();
            	lo.setUserId(0);
            	Main main = new Main();
        		main.start();
                setVisible(false);
            }
        });
	}
}