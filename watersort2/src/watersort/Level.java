package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//�ּ� �̵� ������� ���� ����

public class Level extends JFrame{
	
	WaterSort_main watersort;
	
	JButton timeRank;		//�ð� ��ũ
	JButton countRank;		//Ƚ�� ��ũ
	
	JTextArea rankArea;
	
	JComboBox levelBox;		//���� �޺��ڽ�(�������� ��ũ ������)

	public Level() {
		 
        this.setTitle("Level");
        JPanel jPanel = new JPanel();
        JButton btn1 = new JButton("Level1");
        JButton btn3 = new JButton("�ڷ�");
        
        setSize(500, 500);
        jPanel.add(btn1);
        jPanel.add(btn3);
        add(jPanel);
        
        setLocationRelativeTo(null);
 
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        
        String []level = {"1", "2", "3", "4", "5", "6"}; //���� ����� �ִ� �� �������� �ʾƼ� 6������ �ص�
        this.levelBox = new JComboBox(level);
        
        this.timeRank = new JButton("�ִ� �ð� ��ũ");
        this.countRank = new JButton("�ּ� Ƚ�� ��ũ");
        
        this.rankArea = new JTextArea(30, 15);
        
        add(this.levelBox);
        add(this.timeRank);
        add(this.countRank);
        add(this.rankArea);
        
        //����1 ȭ������
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	new Level1();
                
            }
        });
        
        //����2 ȭ������
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	new Main2();
                
            }
        });
    }

}
