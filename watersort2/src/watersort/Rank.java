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
	
	private int isCheck = -1; 	//버튼이 눌린 상태 표시(0은 시간, 1은 횟수, -1은 아직 버튼을 누르지 않은 상태(초기화면))
	private final int MAX_LEVEL = 6;
	
	public ImageIcon Buttons(ImageIcon images, ImageIcon change) {
		ImageIcon image = images;
		Image img;
	    Image changeImg;
	    
	    img = image.getImage();
	    changeImg = img.getScaledInstance(100, 100, Image.SCALE_FAST);
	    change = new ImageIcon(changeImg);
	    
	    return change;
	}
	
	public ImageIcon Buttonss(ImageIcon images, ImageIcon change) {
		ImageIcon image = images;
		Image img;
	    Image changeImg;
	    
	    img = image.getImage();
	    changeImg = img.getScaledInstance(200, 100, Image.SCALE_FAST);
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
	
	public JButton UII(JButton jb, String a) {
		ImageIcon Ic = null;
		Ic = Buttonss(new ImageIcon(a), Ic);
		jb = new JButton(Ic);
		limpidity(jb);
		return jb;
	}
	
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
        
        title = new JLabel("Rank");
        back = UI(back, "Back.png");
        
        title.setFont(new Font("Gothic", Font.BOLD, 40));
        title.setBounds(257, 5, 150, 100);
        back.setBounds(255, 550, 100, 50);
        
        level = new ArrayList<>();
        
        for (int i = 1; i <= MAX_LEVEL; i++) {
        	level.add("Level " + i);
        }
        
        levelBox = new JComboBox(level.toArray(new String[level.size()]));
        timeRank = UII(timeRank, "Short.png");
        countRank = UII(countRank, "Minimumt.png");
        
        rankArea = new JTextArea(30, 15);
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
        		int index = cb.getSelectedIndex() + 1;
        		
        		if(isCheck == 1) {
    				String sql = "select username, move from user, game where user.id = game.user_id and level =" + index + " order by move";
    				result = dataBase.getResult(sql);
    				printResult(rankArea, "이동횟수", result);
        		} else if(isCheck == 0) {
					String sql = "select username, time from user, game where user.id = game.user_id and level =" + index + " order by time";
					result = dataBase.getResult(sql);
					printResult(rankArea, "시간", result);
        		}
        	}
        	
        	private void printResult(JTextArea rank, String standard, ResultSet result) {
        		int rankIndex = 1;
        		
        		rank.setText("  순위	아이디\t     " + standard + "\n");
        		rank.append(" ---------------------------------------------------------------------------- \n");
        		
        		try {
					while (result.next()) {
						rank.append("   " + (rankIndex++) + "등\t" + result.getString("username") + "\t     " + result.getInt(2) + "\n");
					}
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("결과 받아오지 못함");
				}
				
        	}
        });
	}
}