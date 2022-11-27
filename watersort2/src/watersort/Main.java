package watersort;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {
	
	private JLabel gameTitle;
	private JPanel jPanel;
	private JButton loginBtn;
	private JButton joinBtn;
	private JButton exitBtn;
	private ImageIcon Logo;

	public ImageIcon Labels(ImageIcon images, ImageIcon change) {
		
	    ImageIcon image = images;
		Image img;
	    Image changeImg;
	    
	    img = image.getImage();
	    changeImg = img.getScaledInstance(400, 400, Image.SCALE_FAST);
	    change = new ImageIcon(changeImg);
	    
	    return change;
	}
	
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
    
	public void start() {
		setMainLayout();
		clickAction();
    }
	
	private void setMainLayout() {
		setTitle("Main");
        setSize(500, 500);
        setLayout(null);
        
        loginBtn = UI(loginBtn, "LogIn.png");
        joinBtn = UI(joinBtn, "Sign Up.png");
        exitBtn = UI(exitBtn, "Exit.png");
        
        Logo = Labels(new ImageIcon("Logo.png"), Logo);
        gameTitle = new JLabel(Logo);
        gameTitle.setBounds(40, 0, 400, 400);
        
        loginBtn.setBounds(80, 350, 100, 50);
        joinBtn.setBounds(190, 350, 100, 50);
        exitBtn.setBounds(300, 350, 100, 50);
        
		add(gameTitle);
        add(loginBtn);
        add(joinBtn);
        add(exitBtn);
        
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
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
                new Login();
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