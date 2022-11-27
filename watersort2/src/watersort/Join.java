package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class Join extends JFrame{
	
	private final JLabel joinLabel = new JLabel("회원가입");
    private final JLabel id = new JLabel("아이디");     
    private final JTextField inputId = new JTextField(20);
    private final JLabel password = new JLabel("비밀번호");
    private final JPasswordField inputPassword2 = new JPasswordField(20);
    private final JLabel checkPassword = new JLabel("비밀번호 확인");
    private final JPasswordField inputPassword = new JPasswordField(20);
    private JButton duplicateBtn;
    private JButton createBtn;
    private JButton backBtn;
    boolean isDuplicateId = true;
    boolean overlapCheck = false;        //아이디 중복되었는지 체크
    
    public Join() {
    	setJoinLayout();        
        
    	btnAction();
    }
    
    private void setJoinLayout() {
    	this.setTitle("Join");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setSize(600, 530);
        setLocationRelativeTo(null);
        
        duplicateBtn = makeUI("image/Check.png");
        createBtn = makeUI("image/Create.png");
        backBtn = makeUI("image/Back.png");
        limpidity(duplicateBtn);
        limpidity(createBtn);
        limpidity(backBtn);
        
        joinLabel.setFont(new Font("Gothic", Font.BOLD, 50));
        joinLabel.setBounds(200, 80, 300, 50);
        id.setBounds(80, 200, 70, 40);
        inputId.setBounds(200, 200, 200, 40);
        duplicateBtn.setBounds(420, 195, 100, 50);  
        password.setBounds(80, 270, 70, 40);
        inputPassword.setBounds(200, 270, 200, 40);
        checkPassword.setBounds(80, 340, 100, 40);
        inputPassword2.setBounds(200, 340, 200, 40);
        createBtn.setBounds(190, 410, 100, 50);
        backBtn.setBounds(310, 410, 100, 50);
        
        add(joinLabel);
        add(id);           
        add(inputId);
        add(duplicateBtn);
        add(password);
        add(inputPassword);
        add(checkPassword);
        add(inputPassword2);   
        add(createBtn);        
        add(backBtn);
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
	
    private void btnAction() {
    	duplicateAction();
    	createAction();
    	backAction();
    }
    
    private void duplicateAction() {
    	DataBase dataBase = new DataBase();
    	
    	duplicateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String id = inputId.getText();
               
               validateId(id);
               
               try {
                 ResultSet result = dataBase.selectResult();
                 
                 while(result.next()) {
                      String user_ID = result.getString("username");
                      if(id.equals(user_ID)) {
                          overlapCheck = true; //중복된 곳 있을 경우
                      }
                   }
                 
                 if(overlapCheck) { // 아이디 중복되었을 경우
                	 JOptionPane.showMessageDialog(null, "아이디가 중복되었습니다", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                	 overlapCheck = false;
                } else {
                 	JOptionPane.showMessageDialog(null, "중복된 아이디가 없습니다");
                 	duplicateBtn.setEnabled(false);
                 	inputId.setEditable(false);
                 	isDuplicateId = false;
                 }
               } catch (Exception e1) {}
            }
            
            private void validateId(String id) {
            	if (idIsEmpty(id)) {
            		JOptionPane.showMessageDialog(null, "공백만 입력되었습니다", "입력 오류", JOptionPane.ERROR_MESSAGE);
            	}
            	if (validateIdType(id)) {
            		JOptionPane.showMessageDialog(null, "아이디에 공백 혹은 특수문자가 입력되었습니다", "입력 오류", JOptionPane.ERROR_MESSAGE);
            	}
            }
            
            private boolean idIsEmpty(String id) {
            	return id.replaceAll(" ", "").equals("");
            }
            
            private boolean validateIdType(String id) {
            	String pattern = "^[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-핳]*$"; // 공백 혹은 특수문자가 입력된 경우(자바 정규식 참고)
            	return !Pattern.matches(pattern, id);
            }
            
         });
    }
    
    private void createAction() {
        createBtn.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
				String user_ID = inputId.getText();
				String user_PASSWORD = new String(inputPassword.getPassword());
				String user_PASSRE = new String(inputPassword2.getPassword());
				
				if (!user_PASSWORD.equals(user_PASSRE)) { // 비밀번호가 동일하지 않을 경우									
					JOptionPane.showMessageDialog(null, "비밀번호가 서로 맞지 않습니다", "비밀번호 오류", 1);	
				} else if(user_ID.equals("") || user_PASSWORD.equals("")) { // 정보 입력 제대로 안했을 경우
					JOptionPane.showMessageDialog(null, "정보 입력을 제대로 해주세요!", "입력오류", 1);
				} else if(isDuplicateId) { // id 중복확인을 안했을 경우
					JOptionPane.showMessageDialog(null, "아이디 중복확인을 해주세요!", "중복확인", 1);
				} else {																				
					DataBase dataBase = new DataBase();
					
					if (dataBase.insertUser(user_ID, user_PASSWORD)) {
						JOptionPane.showMessageDialog(null, "회원 가입 완료!", "회원가입", 1);
					    setVisible(false);
					    Main main = new Main();
					    main.start();
					}
				}
			}
		});
    }
    
    private void backAction() {
    	backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               setVisible(false);
               Main main = new Main();
               main.start();
            }
        });   
    }
}
