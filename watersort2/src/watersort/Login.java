package watersort;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame{
	
	private JLabel title, nickName, password;
	private JTextField inputNickName, inputPassword;
	private JButton logIn, signUp, home;
	private static int userId;
	
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
	
	public Login() {
		if (userId == 0) {	        
	        logInLayout();
		}
	}
	
	public void logInLayout() {
		setTitle("LogIn");
        setSize(500, 500);
        setLayout(null);
        
		Font font = new Font("Gothic", Font.BOLD, 15);
		
		title = new JLabel("로그인");
		nickName = new JLabel("닉네임");
		password = new JLabel("비밀번호");
		
		inputNickName = new JTextField();
		inputPassword = new JTextField();
		
		logIn = UI(logIn, "LogIn.png");
		signUp = UI(logIn, "Sign Up.png");
		home = UI(logIn, "Home.png");
		
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
		logIn.setBounds(80, 300, 100, 50);
		signUp.setBounds(190, 300, 100, 50);
		home.setBounds(300, 300, 100, 50);
		
		add(title);
		add(nickName);
		add(password);
		add(inputNickName);
		add(inputPassword);
		add(logIn);
		add(signUp);
		add(home);
		
		setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
		
		logIn.addActionListener(new LogInListener());
		signUp.addActionListener(new SignUpListener());
		home.addActionListener(new HomeListener());
		
	}
	
	public void setUserId(int id) {
		userId = id;
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
				setVisible(false);
			}
			
		}
		
		private boolean validateUser(String userName, String userPassword) {
			return userName.equals("") || userPassword.equals("");
		}
		
		private void printError(String message) {
			JOptionPane.showMessageDialog(null, message, "로그인 실패", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	class SignUpListener implements ActionListener {

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