package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;


public class Rank extends JFrame{
	
	JLabel title = new JLabel("Rank");
	
	JButton timeRank;		//�ð� ��ũ
	JButton countRank;		//Ƚ�� ��ũ
	
	JTextArea rankArea;		//��ũ �����ִ� ����
	
	JComboBox levelBox;		//���� �޺��ڽ�(�������� ��ũ �����ֱ� ����)
	
	Connection conn;                     //DB Ŀ�ؼ� ���� ��ü
	String edit_ID = "root";             //DB ���� ���̵�(�ٸ� �����ͺ��̽� �� ��� ����)
	String edit_PASSWORD = "ghtjd020709!";   //��й�ȣ(�ٸ� �����ͺ��̽� �� ��� ����)
	String url = "jdbc:mysql://localhost:3306/gameuser?serverTimezone=UTC"; //URL(�ٸ� �����ͺ��̽� �� ��� ����)
	Statement stmt;
	ResultSet result;
	
	int flag = -1; 	//��ư�� ���� ���� ǥ��(0�� �ð�, 1�� Ƚ��, -1�� ���� ��ư�� ������ ���� ����(�ʱ�ȭ��))
	
	public Rank() {
		 
        this.setTitle("Rank");
        JButton back = new JButton("�ڷ�");
        
        setSize(620, 650);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, edit_ID, edit_PASSWORD);
            stmt = conn.createStatement();
        }catch(ClassNotFoundException e1) {
      	  System.out.println("JDBC �巯�̹� �ε� ����");
        }catch(SQLException e1) {
      	  System.out.println("DB ���� ����");
        }
        
        back.setBounds(270, 550, 70, 40);
        this.title.setFont(new Font("Gothic", Font.BOLD, 40));
        this.title.setBounds(257, 5, 150, 100);
        add(this.title);
        add(back);
        
        String []levelSt = {"Level 1", "Level 2", "Level 3", "Level 4", "Level 5", "Level 6"}; //���� ����� �ִ� �� �������� �ʾƼ� 6������ �ص�
        this.levelBox = new JComboBox(levelSt);
        this.timeRank = new JButton("�ִ� �ð� ��ũ");
        this.countRank = new JButton("�ּ� Ƚ�� ��ũ");
        this.rankArea = new JTextArea(30, 15);
        this.rankArea.setEditable(false);
        
        this.levelBox.setBounds(270, 150, 70, 40);
        this.timeRank.setBounds(150, 100, 150, 40);
        this.countRank.setBounds(310, 100, 150, 40);
        this.rankArea.setBounds(150, 200, 310, 340);
        
        add(this.levelBox);
        add(this.timeRank);
        add(this.countRank);
        add(this.rankArea);
        
        //�޴�ȭ������
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	new Main2();
                
            }
        });
        //Ƚ�� ��ư ���� ����
        this.countRank.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		countRank.setEnabled(false);
        		timeRank.setEnabled(true);
        		flag = 1;
        	}
        });
        //�ð� ��ư ���� ����
        this.timeRank.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		countRank.setEnabled(true);
        		timeRank.setEnabled(false);
        		flag = 0;
        	}
        });
        //�޺��ڽ�(����) ���ÿ� ���� textArea ���
        this.levelBox.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		JComboBox cb = (JComboBox)e.getSource();
        		int index = cb.getSelectedIndex() + 1;
        		//Ƚ�� ��ư�� Ŭ���� ������ ��
        		if(flag == 1) {
        			try {
        				result = stmt.executeQuery("select id, count from usertable where level =" + index +  " order by count");
        				rankArea.setText("���̵�\t     �̵�Ƚ��\n");
        				while(result.next()) {
        					rankArea.append(result.getString(1) + "\t     " + result.getInt(2) + "\n");
        				}
        			}catch(SQLException e1) {
        				System.out.println("DB ���� ����");
        			}
        		}
        		else if(flag == 0) {
        			try {
        				result = stmt.executeQuery("select id, time from usertable where level =" + index +  " order by time");
        				rankArea.setText("���̵�\t     �ð�\n");
        				while(result.next()) {
        					rankArea.append(result.getString(1) + "\t     " + result.getTime(2) + "\n");
        				}
        			}catch(Exception e1) {
        				System.out.println("DB ���� ����");
        			}
        		}
        	}
        });
    }
	
}
