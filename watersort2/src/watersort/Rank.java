package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class Rank extends JFrame{
	
	//���� ������ �� â�� ����� �� â�� ���� Ŭ���� ���� �ֱ�(�ϱ� ���� �ٸ� �е� �ҽ� �����;���)
	
	private JLabel title;
	
	private JButton timeRank;		//�ð� ��ũ
	private JButton countRank;		//Ƚ�� ��ũ
	private JButton back;
	
	private JTextArea rankArea;		//��ũ �����ִ� ����
	private JComboBox levelBox;		//���� �޺��ڽ�(�������� ��ũ �����ֱ� ����)
	
	private DataBase dataBase;
	private ResultSet result;
	
	private List<String> level;
	
	private int flag = -1; 	//��ư�� ���� ���� ǥ��(0�� �ð�, 1�� Ƚ��, -1�� ���� ��ư�� ������ ���� ����(�ʱ�ȭ��))
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
        back = new JButton("�ڷ�");
        
        title.setFont(new Font("Gothic", Font.BOLD, 40));
        title.setBounds(257, 5, 150, 100);
        back.setBounds(270, 550, 70, 40);
        
        level = new ArrayList<>();
        for (int i = 1; i <= MAX_LEVEL; i++) {
        	level.add("Level " + i);
        }
        
        levelBox = new JComboBox(level.toArray(new String[level.size()]));
        timeRank = new JButton("�ִ� �ð� ��ũ");
        countRank = new JButton("�ּ� Ƚ�� ��ũ");
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
        		int beforeInt;
        		//Ƚ�� ��ư�� Ŭ���� ������ ��
        		if(flag == 1) {
        			try {
        				String sql = "select username, move from user, game where user.id = game.user_id and level =" + index + " order by move";
        				result = dataBase.getResult(sql);
        				rankArea.setText("  ����	���̵�\t     �̵�Ƚ��\n");
        				rankArea.append(" ---------------------------------------------------------------------------- \n");
        				int rank = 1;
        				beforeInt = 0;
        				while(result.next()) {
        					if(result.getInt(2) == beforeInt)
        						rank--;
        					rankArea.append("   " + (rank++) + "��\t" + result.getString("username") + "\t     " + result.getInt(2) + "��\n");
        					beforeInt = result.getInt(2);
        				}
        			}catch(SQLException e1) {
        				System.out.println("DB ���� ����");
        			}
        		}
        		else if(flag == 0) {
        			try {
        				String sql = "select username, time from user, game where user.id = game.user_id and level =" + index + " order by time";
        				result = dataBase.getResult(sql);
        				rankArea.setText("  ����	���̵�\t     �ð�\n");
        				rankArea.append(" ---------------------------------------------------------------------------- \n");
        				int rank = 1;
        				beforeInt = 0;
        				while(result.next()) {
        					if(result.getInt(2) == beforeInt) {
        						rank--;
        					}
        					beforeInt = result.getInt(2);
        					rankArea.append("   " + (rank++) + "��\t" + result.getString("username") + "\t     " + result.getInt(2) + "��\n");
        				}
        			}catch(Exception e1) {
        				System.out.println("DB ���� ����");
        			}
        		}
        	}
        });
    }
}
