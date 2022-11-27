package watersort;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class Level extends JFrame{
	
	private JPanel levelPanel;
	private JLabel levelTitle;
	private JButton backBtn;
	private JButton[] levelBtn;
	WaterSortGame waterSortGame;
	private final int MAX_LEVEL = 6;
	DataBase db;
	
	Clear clearcheck;
	
	public ImageIcon[] LevelButton;
	
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
	
	public Level() {
		db = new DataBase();
		waterSortGame = new WaterSortGame();
		clearcheck = new Clear();
		String[] img = {"Level1.png", "Level2.png", "Level3.png", "Level4.png", "Level5.png", "Level6.png"};
		ImageIcon[] image = new ImageIcon[MAX_LEVEL];
		Login login = new Login();
		int id = login.getUserId();
		
		int userlevel = db.getLevel(id);
		
        this.setTitle("Level");
        setLayout(null);
        
        levelPanel = new JPanel();
        levelTitle = new JLabel("LEVEL");
        levelBtn = new JButton[MAX_LEVEL];
        
        backBtn = UI(backBtn, "Back.png");
        
        for(int i = 0; i < levelBtn.length; i++) {
			image[i] = Buttons(new ImageIcon(img[i]), image[i]);
		}
        
        for (int i = 0; i < levelBtn.length; i++) {
        	levelBtn[i] = new JButton(image[i]);
        	limpidity(levelBtn[i]);
        	levelBtn[i].setEnabled(false);
        }
        
        int row = MAX_LEVEL / 3;
        levelPanel.setLayout(new GridLayout(row,3,10,10));
        levelPanel.setBounds(15, 100, 450, row * 60);
        
        levelTitle.setFont(new Font("Gothic", Font.BOLD, 40));
        levelTitle.setBounds(180, 20, 300, 40);
        
        backBtn.setBounds(15, 400, 100, 50);
        
        for (int i = 0; i < levelBtn.length; i++) {
        	levelPanel.add(levelBtn[i]);
        }       
        
        add(levelTitle);
        add(levelPanel);
        add(backBtn);
        
        for(int i = 0; i < levelBtn.length; i++) {
        	if(userlevel >= i) {
        		levelBtn[i].setEnabled(true);
        	}
        }
        
        setSize(500, 500);
        setLocationRelativeTo(null);
 
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        
        for (int i = 0; i < levelBtn.length; i++) {
	        levelBtn[i].addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	for(int i = 0; i < levelBtn.length; i++) {
	            		JButton click = (JButton)e.getSource();
		            	if (levelBtn[i] == click) {
		            		waterSortGame.play(i+1);
		            		setVisible(false);
		            	}
	            	}
	            }
	        });
        }
        
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	new Menu();
                
            }
        });
    }

}