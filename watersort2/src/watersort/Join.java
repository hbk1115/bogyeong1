package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.regex.Pattern;


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
  
    String user_ID;                      //ȸ������ ID
    String user_PASSWORD;                //ȸ������ ��й�ȣ
    String user_PASSRE;					 //��й�ȣ Ȯ��
    ResultSet result;
    public Join() {
        
    	this.setTitle("Join");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setSize(600, 530);
        setLocationRelativeTo(null);
        
        DataBase db = new DataBase();
        
        this.id = new JLabel("���̵�");
        this.idField = new JTextField(20);
        this.idcheck = new JButton("�ߺ� Ȯ��");
        
        this.joinLabel.setFont(new Font("Gothic", Font.BOLD, 20));
        this.joinLabel.setBounds(235, 50, 300, 50);
        this.id.setBounds(80, 200, 70, 40);
        this.idField.setBounds(200, 200, 200, 40);
        this.idcheck.setBounds(420, 200, 100, 40);
        //ȭ�� ���� ����
        add(this.joinLabel);
        //���̵� ����
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
        //��й�ȣ ����
        add(this.password);
        add(this.passField);
        add(this.passCheck);
        add(this.passCheckField);
        
        this.create = new JButton("����");
        this.back = new JButton("�ڷ�");
        
        this.create.setBounds(230, 410, 70, 40);
        this.back.setBounds(310, 410, 70, 40);
        //�ΰ� ���
        add(this.create);        
        add(this.back);
        
        //id �ߺ�üũ ��ư(idcheck) �̺�Ʈ 
        this.idcheck.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
              String s = idField.getText();
              String pattern = "^[0-9|a-z|A-Z|��-��|��-��|��-�R]*$"; // ���� Ȥ�� Ư�����ڰ� �Էµ� ���(�ڹ� ���Խ� ����)
              try {
                result = db.getResult("select * from user"); //�ٸ� ���̺� �� ��� user �ٲٸ� ��
                
                while(result.next()) {
                     String user_ID = result.getString("username");
                     if(s.equals(user_ID)) {
                         overlapCheck = true; //�ߺ��� �� ���� ���
                     }
                  }
                //���̵� �Է� ������ ���
                if(s.replaceAll(" ", "").equals("")) {
                	JOptionPane.showMessageDialog(null, "���鸸 �ԷµǾ����ϴ�", "�Է� ����", JOptionPane.ERROR_MESSAGE);
                }
                //���̵� ���� Ȥ�� Ʈ�����ڰ� ���Ե� ���
                else if(!Pattern.matches(pattern, s)) {
                	JOptionPane.showMessageDialog(null, "���̵� ���� Ȥ�� Ư�����ڰ� �ԷµǾ����ϴ�", "�Է� ����", JOptionPane.ERROR_MESSAGE);
                }
                //���̵� �ߺ��Ǿ��� ���
                else if(overlapCheck) {
               	 JOptionPane.showMessageDialog(null, "���̵� �ߺ��Ǿ����ϴ�", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
               	 overlapCheck = false;	  //false �� ������ �ٽ� ���̵� ��ģ ���� �ߺ�Ȯ���� ������ �˻縦 �ؾ��ϱ� ����
               }
                else {
                	JOptionPane.showMessageDialog(null, "�ߺ��� ���̵� �����ϴ�");
                	idcheck.setEnabled(false); 	//��ư ��Ȱ��ȭ
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
						String sql = "insert into user values (?, ?, ?)";
						PreparedStatement pstmt;
						
						pstmt = db.getPstmt(sql);
						pstmt.setInt(1, 0);
		                pstmt.setString(2, user_ID);
		                pstmt.setString(3, user_PASSWORD);
		                pstmt.executeUpdate();
		                JOptionPane.showMessageDialog(null, "ȸ�� ���� �Ϸ�!", "ȸ������", 1);
																								
		                setVisible(false);			//���� ȭ������ ���ư�
		                new Main();
					} catch (SQLException e1) {
						System.out.println("����");
					}
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

