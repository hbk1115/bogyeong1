package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

/*
 * 고민되는 부분(질문하기)
 * 1. 순위표시할 때 1등 xxx 2 이렇게 할건지 그냥 출력할건지
 * 2. 현재 플레이어의 최대 순위를 표시할건지 ex) 현재 xxx님의 순위는 x등입니다.
 * 3. 플레이어가 신기록을 갱신하면 (이동횟수 10번으로 레벨 1을 클리어했었는데 2번으로 기록을 갱신할경우) 전 기록(10번)을 지울건지
 */

public class Rank extends JFrame{
	
	JLabel title = new JLabel("Rank");
	
	JButton timeRank;		//시간 랭크
	JButton countRank;		//횟수 랭크
	
	JTextArea rankArea;		//랭크 보여주는 공간
	
	JComboBox levelBox;		//레벨 콤보박스(레벨별로 랭크 보여주기 위함)
	
	Connection conn;                     //DB 커넥션 연결 객체
	String edit_ID = "root";             //DB 연결 아이디(다른 데이터베이스 쓸 경우 수정)
	String edit_PASSWORD = "ghtjd020709!";   //비밀번호(다른 데이터베이스 쓸 경우 수정)
	String url = "jdbc:mysql://localhost:3306/gameuser?serverTimezone=UTC"; //URL(다른 데이터베이스 쓸 경우 수정)
	Statement stmt;
	ResultSet result;
	
	int flag = -1; 	//버튼이 눌린 상태 표시(0은 시간, 1은 횟수, -1은 아직 버튼을 누르지 않은 상태(초기화면))
	
	public Rank() {
		 
        this.setTitle("Rank");
        JButton back = new JButton("뒤로");
        
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
      	  System.out.println("JDBC 드러이버 로드 오류");
        }catch(SQLException e1) {
      	  System.out.println("DB 연결 오류");
        }
        
        back.setBounds(270, 550, 70, 40);
        this.title.setFont(new Font("Gothic", Font.BOLD, 40));
        this.title.setBounds(257, 5, 150, 100);
        add(this.title);
        add(back);
        
        String []levelSt = {"Level 1", "Level 2", "Level 3", "Level 4", "Level 5", "Level 6"}; //레벨 몇까지 있는 지 결정되지 않아서 6까지만 해둠
        this.levelBox = new JComboBox(levelSt);
        this.timeRank = new JButton("최단 시간 랭크");
        this.countRank = new JButton("최소 횟수 랭크");
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
        
        //메뉴화면으로
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	new Main2();
                
            }
        });
        //횟수 버튼 눌린 상태
        this.countRank.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		countRank.setEnabled(false);
        		timeRank.setEnabled(true);
        		flag = 1;
        	}
        });
        //시간 버튼 눌린 상태
        this.timeRank.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		countRank.setEnabled(true);
        		timeRank.setEnabled(false);
        		flag = 0;
        	}
        });
        //콤보박스(레벨) 선택에 따른 textArea 출력
        this.levelBox.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		JComboBox cb = (JComboBox)e.getSource();
        		int index = cb.getSelectedIndex() + 1;
        		//횟수 버튼이 클릭된 상태일 때
        		if(flag == 1) {
        			try {
        				result = stmt.executeQuery("select id, count from usertable where level =" + index +  " order by count");
        				rankArea.setText("아이디\t     이동횟수\n");
        				while(result.next()) {
        					rankArea.append(result.getString(1) + "\t     " + result.getInt(2) + "\n");
        				}
        			}catch(SQLException e1) {
        				System.out.println("DB 연결 오류");
        			}
        		}
        		else if(flag == 0) {
        			try {
        				result = stmt.executeQuery("select id, time from usertable where level =" + index +  " order by time");
        				rankArea.setText("아이디\t     시간\n");
        				while(result.next()) {
        					rankArea.append(result.getString(1) + "\t     " + result.getTime(2) + "\n");
        				}
        			}catch(Exception e1) {
        				System.out.println("DB 연결 오류");
        			}
        		}
        	}
        });
    }
	
}
