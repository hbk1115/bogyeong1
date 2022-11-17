package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//최소 이동 순서대로 순위 정렬

public class Level extends JFrame{
	
	WaterSort_main watersort;
	
	JButton timeRank;		//시간 랭크
	JButton countRank;		//횟수 랭크
	
	JTextArea rankArea;
	
	JComboBox levelBox;		//레벨 콤보박스(레벨별로 랭크 보여줌)

	public Level() {
		 
        this.setTitle("Level");
        JPanel jPanel = new JPanel();
        JButton btn1 = new JButton("Level1");
        JButton btn3 = new JButton("뒤로");
        
        setSize(500, 500);
        jPanel.add(btn1);
        jPanel.add(btn3);
        add(jPanel);
        
        setLocationRelativeTo(null);
 
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        
        String []level = {"1", "2", "3", "4", "5", "6"}; //레벨 몇까지 있는 지 결정되지 않아서 6까지만 해둠
        this.levelBox = new JComboBox(level);
        
        this.timeRank = new JButton("최단 시간 랭크");
        this.countRank = new JButton("최소 횟수 랭크");
        
        this.rankArea = new JTextArea(30, 15);
        
        add(this.levelBox);
        add(this.timeRank);
        add(this.countRank);
        add(this.rankArea);
        
        //레벨1 화면으로
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	new Level1();
                
            }
        });
        
        //메인2 화면으로
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	new Main2();
                
            }
        });
    }

}
