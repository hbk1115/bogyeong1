package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class Rank extends JFrame{
	
	private final JLabel title = new JLabel("Rank");
	
	private JButton timeRank;
	private JButton countRank;
	private JButton back;
	
	private final JTextArea rankArea = new JTextArea(30, 15);
	private JComboBox levelBox;
	
	private DataBase dataBase;
	private ResultSet result;
	
	private final List<String> level = new ArrayList<>();
	
	private int isCheck = -1; 	//버튼이 눌린 상태 표시(0은 시간, 1은 횟수, -1은 아직 버튼을 누르지 않은 상태(초기화면))
	private final int MAX_LEVEL = 6;
	
	public Rank() {
		setRankLayout();
		 
		rankAction();
    }
	
	private void setRankLayout() {
		this.setTitle("Rank");
        
        setSize(620, 650);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        
        dataBase = new DataBase();
        
        title.setFont(new Font("Gothic", Font.BOLD, 40));
        title.setBounds(257, 5, 150, 100);
        back = makeUI("image/Back.png", 100);
        limpidity(back);
        back.setBounds(255, 550, 100, 50);
        
        for (int i = 1; i <= MAX_LEVEL; i++) {
        	level.add("Level " + i);
        }
        
        levelBox = new JComboBox(level.toArray(new String[level.size()]));
        timeRank = makeUI("image/Short.png", 200);
        countRank = makeUI("image/Minimumt.png", 200);
        limpidity(timeRank);
        limpidity(countRank);
        
        rankArea.setEditable(false);
        levelBox.setBounds(150, 150, 310, 40);
        timeRank.setBounds(100, 90, 200, 50);
        countRank.setBounds(310, 90, 200, 50);
        rankArea.setBounds(150, 200, 310, 340);
        
        add(title);
        add(back);
        add(levelBox);
        add(timeRank);
        add(countRank);
        add(rankArea);
	}
	
	private JButton makeUI(String name, int width) {
		ImageIcon icon = new ImageIcon(name);
		Image image = icon.getImage();
		image = image.getScaledInstance(width, 100, Image.SCALE_FAST);
		return new JButton(new ImageIcon(image));
	}
	
	private void limpidity(JButton btn) {
		btn.setBorderPainted(false);
		btn.setContentAreaFilled(false);
		btn.setFocusPainted(false);
		btn.setOpaque(false);
	}
	
	private void rankAction() {
		backAction();
		countRankAction();
		timeRankAction();
		levelAction();
	}
	
	private void backAction() {
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	new Menu();
            }
        });
	}
	
	private void countRankAction() {
        countRank.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		countRank.setEnabled(false);
        		timeRank.setEnabled(true);
        		isCheck = 1;
        	}
        });
	}
	
	private void timeRankAction() {
        timeRank.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		countRank.setEnabled(true);
        		timeRank.setEnabled(false);
        		isCheck = 0;
        	}
        });
	}
	
	private void levelAction() {
        levelBox.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		JComboBox cb = (JComboBox)e.getSource();
        		int level = cb.getSelectedIndex() + 1;
        		
        		if(isCheck == 1) {
        			result = dataBase.scoreInquiryByMove(level);
    				printResult(rankArea, "이동횟수", result);
        		} else if(isCheck == 0) {
					result = dataBase.scoreInquiryByTime(level);
					printResult(rankArea, "시간", result);
        		}
        	}
        	
        	private void printResult(JTextArea rank, String standard, ResultSet result) {
        		int rankIndex = 1;
        		int beforeRank = 1;
        		
        		rank.setText("  순위	아이디\t     " + standard + "\n");
        		rank.append(" ---------------------------------------------------------------------------- \n");
        		
        		try {
					while (result.next()) {
						if (result.getInt(2) == beforeRank) {
							rankIndex--;
						}
						rank.append("   " + (rankIndex++) + "등\t" + result.getString("username") + "\t     " + result.getInt(2) + "\n");
						beforeRank = result.getInt(2);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("결과 받아오지 못함");
				}
        	}
        });
	}
}
