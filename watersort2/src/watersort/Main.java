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
    
	public void start() {
		setMainLayout();
		clickAction();
    }
	
	private void setMainLayout() {
		setTitle("Main");
        setLayout(null);
        
        jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(3, 1, 10, 10));
        loginBtn = new JButton("로그인");
        joinBtn = new JButton("회원가입");
        exitBtn = new JButton("게임 종료");
        
        jPanel.add(loginBtn);
        jPanel.add(joinBtn);
        jPanel.add(exitBtn);
        jPanel.setBounds(120, 180, 250, 200);
        
        gameTitle = new JLabel("WaterSort Game!!");
        gameTitle.setHorizontalAlignment(JLabel.CENTER);
        gameTitle.setFont(new Font("Gothic", Font.BOLD, 50));
        gameTitle.setBounds(0, 50, 500, 100);
        
        add(gameTitle);
        add(jPanel);
         
        setSize(500,500);
        setLocationRelativeTo(null);
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