package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class Rank extends JFrame{
	
	private JLabel title;
	
	private JButton timeRank;		//시간 랭크
	private JButton countRank;		//횟수 랭크
	private JButton back;
	
	private JTextArea rankArea;		//랭크 보여주는 공간
	private JComboBox levelBox;		//레벨 콤보박스(레벨별로 랭크 보여주기 위함)
	
	private DataBase dataBase;
	private ResultSet result;
	
	private List<String> level;
	
	private int flag = -1; 	//버튼이 눌린 상태 표시(0은 시간, 1은 횟수, -1은 아직 버튼을 누르지 않은 상태(초기화면))
	private final int MAX_LEVEL = 6;
	
	public Rank() {
		 
        this.setTitle("Rank");
        
        setSize(620, 650);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        
        dataBase = new DataBase();
        
        title = new JLabel("Rank");
        back = new JButton("뒤로");
        
        title.setFont(new Font("Gothic", Font.BOLD, 40));
        title.setBounds(257, 5, 150, 100);
        back.setBounds(270, 550, 70, 40);
        
        level = new ArrayList<>();
        for (int i = 1; i <= MAX_LEVEL; i++) {
        	level.add("Level " + i);
        }
        
        levelBox = new JComboBox(level.toArray(new String[level.size()]));
        timeRank = new JButton("최단 시간 랭크");
        countRank = new JButton("최소 횟수 랭크");
        rankArea = new JTextArea(30, 15);
        rankArea.setEditable(false);
        
        levelBox.setBounds(150, 150, 310, 40);
        timeRank.setBounds(150, 100, 150, 40);
        countRank.setBounds(310, 100, 150, 40);
        rankArea.setBounds(150, 200, 310, 340);
        
        add(title);
        add(back);
        
        add(levelBox);
        add(timeRank);
        add(countRank);
        add(rankArea);
        
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
        				String sql = "select username, move from user, game where user.id = game.user_id and level =" + index + " order by move";
        				result = dataBase.getResult(sql);
        				rankArea.setText("  순위	아이디\t     이동횟수\n");
        				rankArea.append(" ---------------------------------------------------------------------------- \n");
        				int rank = 1;
        				while(result.next()) {
        					rankArea.append("   " + (rank++) + "등\t" + result.getString("username") + "\t     " + result.getInt(2) + "번\n");
        				}
        			}catch(SQLException e1) {
        				System.out.println("DB 연결 오류");
        			}
        		}
        		else if(flag == 0) {
        			try {
        				String sql = "select username, time from user, game where user.id = game.user_id and level =" + index + " order by time";
        				result = dataBase.getResult(sql);
        				rankArea.setText("  순위	아이디\t     시간\n");
        				rankArea.append(" ---------------------------------------------------------------------------- \n");
        				int rank = 1;
        				while(result.next()) {
        					rankArea.append("   " + (rank++) + "등\t" + result.getString("username") + "\t     " + result.getInt(2) + "초\n");
        				}
        			}catch(Exception e1) {
        				System.out.println("DB 연결 오류");
        			}
        		}
        	}
        });
    }
}
