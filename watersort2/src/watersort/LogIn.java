package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LogIn extends JFrame{
	
	private final JLabel title = new JLabel("로그인");
	private final JLabel nickName = new JLabel("닉네임");
	private final JLabel password = new JLabel("비밀번호");
	private final JTextField inputNickName = new JTextField();
	private final JTextField inputPassword = new JTextField();
	private JButton logIn;
	private JButton join;
	private JButton home;
	private Font  font = new Font("Gothic", Font.BOLD, 15);

	private static int userId;
	
	public LogIn() {
		if (userId == 0) {	        
	        logInLayout();
		}
	}
	
	public void logInLayout() {
		setTitle("LogIn");
        setSize(500, 500);
        setLayout(null);
		
        logIn = makeUI("image/LogIn.png");
        join = makeUI("image/Sign Up.png");
        home = makeUI("image/Home.png");
        limpidity(logIn);
        limpidity(join);
        limpidity(home);
        
		title.setFont(new Font("Gothic", Font.BOLD, 50));
		nickName.setFont(font);
		password.setFont(font);
		
		title.setBounds(180, 100, 300, 50);
		nickName.setBounds(80, 200, 70, 30);
		password.setBounds(80, 240, 70, 30);
		inputNickName.setBounds(200, 200, 200, 30);
		inputPassword.setBounds(200, 240, 200, 30);
		logIn.setBounds(80, 300, 100, 50);
		join.setBounds(190, 300, 100, 50);
		home.setBounds(300, 300, 100, 50);
		
		add(title);
		add(nickName);
		add(password);
		add(inputNickName);
		add(inputPassword);
		add(logIn);
		add(join);
		add(home);
		
		setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
		
		logIn.addActionListener(new LogInListener());
		join.addActionListener(new JoinListener());
		home.addActionListener(new HomeListener());
		
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
	
	public int getUserId() {
		return userId;
	}
	
	class LogInListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String userName = inputNickName.getText();
			String userPassword = inputPassword.getText();
			
			if (validateUser(userName, userPassword)) {
				String message = "아이디와 비밀번호를 입력해주세요.";
				printError(message);
			} else {
				checkUserInfo(userName, userPassword);
				setVisible(false);
			}
			
		}
		
		private void checkUserInfo(String userName, String userPassword) {
			DataBase db = new DataBase();
			int id = db.checkLogIn(userName, userPassword);
			
			if (id != 0) {
				JOptionPane.showMessageDialog(null, "로그인에 성공하셨습니다.");
				userId = id;
				new Menu();
			} else {
				String message = "일치하는 계정 정보가 없습니다.";
				printError(message);
				new Join();
			}
		}
		
		private boolean validateUser(String userName, String userPassword) {
			return userName.equals("") || userPassword.equals("");
		}
		
		private void printError(String message) {
			JOptionPane.showMessageDialog(null, message, "로그인 실패", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	class JoinListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			new Join();
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