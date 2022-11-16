package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LogIn extends JFrame{
	
	JLabel title, nickName, password;
	JTextField inputNickName, inputPassword;
	JButton logIn, signUp, home;
	
	public LogIn() {
		setTitle("LogIn");
        setSize(500, 500);
        setLayout(null);
        
        logInLayout();
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
	}
	
	public void logInLayout() {
		Font font = new Font("Gothic", Font.BOLD, 15);
		
		title = new JLabel("로그인");
		nickName = new JLabel("닉네임");
		password = new JLabel("비밀번호");
		
		inputNickName = new JTextField();
		inputPassword = new JTextField();
		
		logIn = new JButton("로그인");
		signUp = new JButton("회원가입");
		home = new JButton("홈");
		
		title.setFont(new Font("Gothic", Font.BOLD, 40));
		nickName.setFont(font);
		password.setFont(font);
		logIn.setFont(font);
		signUp.setFont(font);
		home.setFont(font);
		
		title.setBounds(180, 100, 300, 50);
		nickName.setBounds(80, 200, 70, 30);
		password.setBounds(80, 240, 70, 30);
		inputNickName.setBounds(200, 200, 200, 30);
		inputPassword.setBounds(200, 240, 200, 30);
		logIn.setBounds(80, 300, 100, 30);
		signUp.setBounds(190, 300, 100, 30);
		home.setBounds(300, 300, 100, 30);
		
		add(title);
		add(nickName);
		add(password);
		add(inputNickName);
		add(inputPassword);
		add(logIn);
		add(signUp);
		add(home);
		
		
		logIn.addActionListener(new LogInListener());
		signUp.addActionListener(new SignUpListener());
		home.addActionListener(new HomeListener());
		
	}
	
	class LogInListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String userName = inputNickName.getText();
			String userPassword = inputPassword.getText();
			
			if (userName.equals("") || userPassword.equals("")) {
				JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 입력해주세요.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
			} else {
				DataBase db = new DataBase();
				if (db.checkLogIn(userName, userPassword)) {
					JOptionPane.showMessageDialog(null, "로그인에 성공하셨습니다.");
					setVisible(false);
					new Main2();
				} else {
					JOptionPane.showMessageDialog(null, "일치하는 계정 정보가 없습니다.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
					//setVisible(false);
					//new SignUp();
				}
			}
			
		}
		
	}
	
	class SignUpListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			//new SignUp();
		}
		
	}
	
	class HomeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			new Main();
		}
		
	}
}