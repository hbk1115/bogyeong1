package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {
	
	private JLabel gameTitle;
	private final JPanel jPanel = new JPanel();
	private JButton loginBtn;
	private JButton joinBtn;
	private JButton exitBtn;
	private ImageIcon Logo;
	
	public void start() {
		setMainLayout();
		clickAction();
    }
	
	private void setMainLayout() {
		setTitle("Main");
        setLayout(null);        
        
        setButtonImage();
        
        loginBtn.setBounds(80, 350, 100, 50);
        joinBtn.setBounds(190, 350, 100, 50);
        exitBtn.setBounds(300, 350, 100, 50);
        
        gameTitle = new JLabel(makeLogo(new ImageIcon("image/Logo.png")));
        gameTitle.setBounds(40, 0, 400, 400);
        
        add(gameTitle);
        add(loginBtn);
        add(joinBtn);
        add(exitBtn);
        
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
	}
	
	private void setButtonImage() {
		loginBtn = makeUI("image/LogIn.png");
        joinBtn = makeUI("image/Sign Up.png");
        exitBtn = makeUI("image/Exit.png");
        
        limpidity(loginBtn);
        limpidity(joinBtn);
        limpidity(exitBtn);
	}
	
	private ImageIcon makeLogo(ImageIcon image) {
	    Image img = image.getImage();
	    img = img.getScaledInstance(400, 400, Image.SCALE_FAST);	    
	    return new ImageIcon(img);
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
	
	private void clickAction() {
		loginAction();
        joinAction();
        exitAction();
	}
	
	private void loginAction() {
		loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
                new LogIn();
            }
        });
	}
	
	private void joinAction() {
		joinBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
                new Join();
                
            }
        });
	}
	
	private void exitAction() {
		exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.exit(0);
            }
        });
	}
}