package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Join extends JFrame{
   
	JLabel joinLabel = new JLabel("ȸ������ ȭ��");
	
    JLabel id;                   
    JTextField idField;                  //id �Է�ĭ
    JButton idcheck;                     //id �ߺ�Ȯ�� ��ư
    boolean idcheckcheck = true;		 //id �ߺ�Ȯ�� �ߴ��� Ȯ��
 
    JLabel passCheck;				
    JPasswordField passField;            //password �Է�ĭ

    JLabel password;
    JPasswordField passCheckField;		 //password ���Ͽ���  

    JButton create;                      //���� ��ư Ŭ���� �����ͺ��̽��� ����   
    JButton back;                        //back ��ư Ŭ���� ����ȭ��(Main)���� �� 
    boolean overlapCheck = false;        //���̵� �ߺ��Ǿ����� üũ
   
    Connection conn;                     //DB Ŀ�ؼ� ���� ��ü
    String edit_ID = "root";             //DB ���� ���̵�(�ٸ� �����ͺ��̽� �� ��� ����)
    String edit_PASSWORD = "ghtjd020709!";   //��й�ȣ(�ٸ� �����ͺ��̽� �� ��� ����)
    String user_ID;                      //ȸ������ ID
    String user_PASSWORD;                //ȸ������ ��й�ȣ
    String user_PASSRE;					 //��й�ȣ Ȯ��
    String url = "jdbc:mysql://localhost:3306/gameuser?serverTimezone=UTC"; //URL(�ٸ� �����ͺ��̽� �� ��� ����)
    Statement stmt;
    ResultSet result;
   
    public Join() {
        
    	this.setTitle("Join");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setSize(600, 530);
        setLocationRelativeTo(null);
        
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, edit_ID, edit_PASSWORD);
            stmt = conn.createStatement();
        }catch(ClassNotFoundException e1) {
      	  System.out.println("JDBC �巯�̹� �ε� ����");
        }catch(SQLException e1) {
      	  System.out.println("DB ���� ����");
        }
        
        this.id = new JLabel("���̵�");
        this.idField = new JTextField(20);
        this.idcheck = new JButton("�ߺ� Ȯ��");
        
        this.joinLabel.setFont(new Font("Gothic", Font.BOLD, 20));
        this.joinLabel.setBounds(235, 50, 300, 50);
        this.id.setBounds(80, 200, 70, 40);
        this.idField.setBounds(200, 200, 200, 40);
        this.idcheck.setBounds(420, 200, 100, 40);
        
        add(this.joinLabel);
        add(this.id);           
        add(this.idField);
        add(this.idcheck);
        
        this.password = new JLabel("��й�ȣ");
        this.passField = new JPasswordField(20);
        this.passCheck = new JLabel("��й�ȣ Ȯ��");
        this.passCheckField = new JPasswordField(20);
        
        this.password.setBounds(80, 270, 70, 40);
        this.passField.setBounds(200, 270, 200, 40);
        this.passCheck.setBounds(80, 340, 100, 40);
        this.passCheckField.setBounds(200, 340, 200, 40);
        
        add(this.password);
        add(this.passField);
        add(this.passCheck);
        add(this.passCheckField);
        
        this.create = new JButton("����");
        this.back = new JButton("�ڷ�");
        
        this.create.setBounds(230, 410, 70, 40);
        this.back.setBounds(310, 410, 70, 40);
        
        add(this.create);        
        add(this.back);
        
        //id �ߺ�üũ ��ư(idcheck) �̺�Ʈ
        this.idcheck.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
              String s = idField.getText();
              try {
                result = stmt.executeQuery("select * from usertable"); //�ٸ� ���̺� �� ��� usertable �ٲٸ� ��
                
                while(result.next()) {
                     String user_ID = result.getString("id");
                     if(s.equals(user_ID)) {
                         overlapCheck = true; //�ߺ��� �� ���� ���
                     }
                  }
                //���̵� �Է� ������ ���(���� ���ϴ� ������ �ƴ�)
                if(s.equals("")) {
                	JOptionPane.showMessageDialog(null, "���̵� �Է����� �ʾҽ��ϴ�", "�Է� ����", JOptionPane.ERROR_MESSAGE);
                }
                //���̵� �ߺ��Ǿ��� ���
                else if(overlapCheck) {
               	 JOptionPane.showMessageDialog(null, "���̵� �ߺ��Ǿ����ϴ�", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
               	 overlapCheck = false;	  //false �� ������ �ٽ� ���̵� ��ģ ���� �ߺ�Ȯ���� ������ �˻縦 �ؾ��ϱ� ����
               }
                else {
                	JOptionPane.showMessageDialog(null, "�ߺ��� ���̵� �����ϴ�");
                	idField.setEditable(false);
                	idcheckcheck = false;
                }
              }catch(Exception e1) {}
           }
        });
        
        //ȸ������ ���� ��ư(create) �̺�Ʈ
        this.create.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
				user_ID = idField.getText();
				user_PASSWORD = new String(passField.getPassword());
				user_PASSRE = new String(passCheckField.getPassword());

				String sql = "insert into usertable values (?,?)";
				PreparedStatement pstmt = null;
				
				//��й�ȣ�� �������� ���� ���
				if (!user_PASSWORD.equals(user_PASSRE)) {													
					JOptionPane.showMessageDialog(null, "��й�ȣ�� ���� ���� �ʽ��ϴ�", "��й�ȣ ����", 1);	

				}
				//���� �Է� ����� ������ ���
				else if(user_ID.equals("") || user_PASSWORD.equals("")) {
					JOptionPane.showMessageDialog(null, "���� �Է��� ����� ���ּ���!", "�Է¿���", 1);
				}
				//id �ߺ�Ȯ���� ������ ���
				else if(idcheckcheck) {
					JOptionPane.showMessageDialog(null, "���̵� �ߺ�Ȯ���� ���ּ���!", "�ߺ�Ȯ��", 1);
				}
				else {																				
					try {
						pstmt = conn.prepareStatement(sql);												
		                pstmt.setString(1, user_ID);
		                pstmt.setString(2, user_PASSWORD);
		                pstmt.executeUpdate();
		                
						JOptionPane.showMessageDialog(null, "ȸ�� ���� �Ϸ�!", "ȸ������", 1);
																								
						setVisible(false);			//���� ȭ������ ���ư�
						new Main();
					} catch (SQLException e1) {}
				}
			}
		});
        //back ��ư �̺�Ʈ(Mainȭ������ ����)
        this.back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               setVisible(false);
               new Main();
            }
        });   
    }
}

