package watersort;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.regex.Pattern;


public class Join extends JFrame{
   
	JLabel joinLabel = new JLabel("회원가입 화면");
	
    JLabel id;                   
    JTextField idField;                  //id 입력칸
    JButton idcheck;                     //id 중복확인 버튼
    boolean idcheckcheck = true;		 //id 중복확인 했는지 확인
 
    JLabel passCheck;				
    JPasswordField passField;            //password 입력칸

    JLabel password;
    JPasswordField passCheckField;		 //password 동일여부  

    JButton create;                      //생성 버튼 클릭시 데이터베이스에 저장   
    JButton back;                        //back 버튼 클릭시 메인화면(Main)으로 감 
    boolean overlapCheck = false;        //아이디 중복되었는지 체크
  
    String user_ID;                      //회원가입 ID
    String user_PASSWORD;                //회원가입 비밀번호
    String user_PASSRE;					 //비밀번호 확인
    ResultSet result;
    
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

    public Join() {
    	this.setTitle("Join");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setSize(600, 530);
        setLocationRelativeTo(null);
        
        DataBase db = new DataBase();
        
        idcheck = UI(idcheck, "Check.png");
        create = UI(create, "Create.png");
        back = UI(back, "Back.png");
        
        this.id = new JLabel("아이디");
        this.idField = new JTextField(20);
        
        this.joinLabel.setFont(new Font("Gothic", Font.BOLD, 20));
        this.joinLabel.setBounds(235, 50, 300, 50);
        this.id.setBounds(80, 200, 70, 40);
        this.idField.setBounds(200, 200, 200, 40);
        this.idcheck.setBounds(420, 195, 100, 50);
        //화면 위의 제목
        add(this.joinLabel);
        //아이디 관련
        add(this.id);           
        add(this.idField);
        add(this.idcheck);
     
        this.password = new JLabel("비밀번호");
        this.passField = new JPasswordField(20);
        this.passCheck = new JLabel("비밀번호 확인");
        this.passCheckField = new JPasswordField(20);
        
        this.password.setBounds(80, 270, 70, 40);
        this.passField.setBounds(200, 270, 200, 40);
        this.passCheck.setBounds(80, 340, 100, 40);
        this.passCheckField.setBounds(200, 340, 200, 40);
        //비밀번호 관련
        add(this.password);
        add(this.passField);
        add(this.passCheck);
        add(this.passCheckField);
        

        
        this.create.setBounds(190, 410, 100, 50);
        this.back.setBounds(310, 410, 100, 50);
        //부가 기능
        add(this.create);        
        add(this.back);
        
        //id 중복체크 버튼(idcheck) 이벤트 
        this.idcheck.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
              String s = idField.getText();
              String pattern = "^[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$"; // 공백 혹은 특수문자가 입력된 경우(자바 정규식 참고)
              try {
                result = db.getResult("select * from user"); //다른 테이블 쓸 경우 user 바꾸면 됨
                
                while(result.next()) {
                     String user_ID = result.getString("username");
                     if(s.equals(user_ID)) {
                         overlapCheck = true; //중복된 곳 있을 경우
                     }
                  }
                //아이디를 입력 안했을 경우
                if(s.replaceAll(" ", "").equals("")) {
                	JOptionPane.showMessageDialog(null, "공백만 입력되었습니다", "입력 오류", JOptionPane.ERROR_MESSAGE);
                }
                //아이디에 공백 혹은 트수문자가 포함된 경우
                else if(!Pattern.matches(pattern, s)) {
                	JOptionPane.showMessageDialog(null, "아이디에 공백 혹은 특수문자가 입력되었습니다", "입력 오류", JOptionPane.ERROR_MESSAGE);
                }
                //아이디 중복되었을 경우
                else if(overlapCheck) {
               	 JOptionPane.showMessageDialog(null, "아이디가 중복되었습니다", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
               	 overlapCheck = false;	  //false 한 이유는 다시 아이디를 고친 다음 중복확인을 눌러서 검사를 해야하기 때문
               }
                else {
                	JOptionPane.showMessageDialog(null, "중복된 아이디가 없습니다");
                	idcheck.setEnabled(false); 	//버튼 비활성화
                	idField.setEditable(false);
                	idcheckcheck = false;
                }
              }catch(Exception e1) {
            	  System.out.println("error");
              }
           }
        });
        
        //회원가입 생성 버튼(create) 이벤트
        this.create.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
				user_ID = idField.getText();
				user_PASSWORD = new String(passField.getPassword());
				user_PASSRE = new String(passCheckField.getPassword());
				
				//비밀번호가 동일하지 않을 경우
				if (!user_PASSWORD.equals(user_PASSRE)) {													
					JOptionPane.showMessageDialog(null, "비밀번호가 서로 맞지 않습니다", "비밀번호 오류", 1);	

				}
				//정보 입력 제대로 안했을 경우
				else if(user_ID.equals("") || user_PASSWORD.equals("")) {
					JOptionPane.showMessageDialog(null, "정보 입력을 제대로 해주세요!", "입력오류", 1);
				}
				//id 중복확인을 안했을 경우
				else if(idcheckcheck) {
					JOptionPane.showMessageDialog(null, "아이디 중복확인을 해주세요!", "중복확인", 1);
				}
				else {																				
					try {
						String sql = "insert into user values (?, ?, ?)";
						PreparedStatement pstmt;
						
						pstmt = db.getPstmt(sql);
						pstmt.setInt(1, 0);
		                pstmt.setString(2, user_ID);
		                pstmt.setString(3, user_PASSWORD);
		                pstmt.executeUpdate();
		                JOptionPane.showMessageDialog(null, "회원 가입 완료!", "회원가입", 1);
																								
		                setVisible(false);			//메인 화면으로 돌아감
		                Main main = new Main();
		        		main.start();
					} catch (SQLException e1) {
						System.out.println("오류");
					}
				}
			}
		});
        //back 버튼 이벤트(Main화면으로 가는)
        this.back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Main main = new Main();
        		main.start();
                setVisible(false);
            }
        });   
    }
}