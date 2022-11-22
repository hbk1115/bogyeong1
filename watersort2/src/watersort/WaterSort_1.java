package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Stack;

//결과 창에서 다른 창으로 옮길 때 game 창 어케 닫는지 모르겠음

class WaterSort_main extends JFrame{
	
	public int Level;
	public int Colors[];
    public int maxColors;
    public int maxBottles;
    public int MoveCnt;
    public int To;
    public int From;
    public int count;
    public int backcount;
    public JLabel[] jb;
    public JLabel[] jjb;
    public JLabel[] bottlelabel;
    public Thread th2;
    public Thread th3;
    public ArrayList<Integer> bottles[];
    public Stack<Integer> colors;
    public Stack<Integer> Moves;
    public Stack<Integer> BackMovesCounter;
    public JButton btn3;
    public JLabel timelabel2;
    //public Timer runnable2;
    public Counter runnable3;
    public int rand_bottle;
    public int x1;
    public int count2;
    public int temp;
    public int x2;
    public int excount;
    public int excount2;
    public JLabel click;
    public int undoto;
    public int undofrom;
    public int peekcount;
    public JButton Undobutton;
    public int Xposition;
    public int Yposition;
    public ImageIcon image;
    public Image img;
    public Image changeImg;
    public ImageIcon changeIcon;
    public ImageIcon image2;
    public Image img2;
    public Image changeImg2;
    public ImageIcon changeIcon2;
    public int postbottle;
    
    public int second;
    public int millisecond;
    
    private boolean clearcheck = false; //true 되면 클리어 정보 화면으로
    private JLabel clearLabel;
	private JLabel moveLabel;
	private JLabel timeLabel;
	private JButton closeButton = new JButton("닫기");
	//private JButton exit = new JButton("레벨 선택");
	//private JButton rank = new JButton("순위 보기");
    class Counter extends JFrame implements Runnable{
    	Clear clear = new Clear();
    	public JLabel timel;
    	public int n2;
    	public int n;
    	
    	public Counter(JLabel timel) {
    		this.timel = timel;
    		this.n2 = 0;
    		this.n = 0;
    	}
    	
    	public void run() {	
    		while(true) {
    			try {
    				++n2;
    				if(n2 == 10) {
    					n2 = 0;
    					++n;
    				}
        			second = n;
            		millisecond = n2;
            		timel.setText(Integer.toString(n) +  " : " + Integer.toString(n2));
    				
    				operation(bottles, Moves, BackMovesCounter);
    				if(Solved(bottles) == true) {
    					
    					if(Level == 1) {
    		        		clear.run(1);
    		        		System.out.println("시간 = " + second + ":" + millisecond);
    		        		clearcheck = true;
    		        	}
    		        	else if(Level == 2) {
    		        		clear.run(2);
    		        		System.out.println("시간 = " + second + ":" + millisecond);
    		        		clearcheck = true;
    		        	}
    		        	else if(Level == 3) {
    		        		clear.run(3);
    		        		System.out.println("시간 = " + second + ":" + millisecond);
    		        		clearcheck = true;
    		        	}
    		        	else if(Level == 4) {
    		        		clear.run(4);
    		        		System.out.println("시간 = " + second + ":" + millisecond);
    		        		clearcheck = true;
    		        	}
    		        	th3.interrupt();
    		        	break;

    		        }
    				Thread.sleep(100);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    		}
    		if(clearcheck) {
    			new ShowClear();
    		}
    	}
    }
    //여기부터 수정함
    class ShowClear extends JFrame{
    	WaterSort_main game;
    	public ShowClear() {
			setTitle("결과 화면");
			setSize(400, 400);
			setLayout(null);
        	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	setVisible(true);
        
        	clearLabel = new JLabel("Level" + Level + " 클리어!");
        	moveLabel = new JLabel("이동 : " + MoveCnt + "번");
        	timeLabel = new JLabel("시간 : " + second + "." + millisecond + "초");
        
        	clearLabel.setFont(new Font("Gothic", Font.BOLD, 40));
        	moveLabel.setFont(new Font("Gothic", Font.BOLD, 22));
        	timeLabel.setFont(new Font("Gothic", Font.BOLD, 22));
        	
        	clearLabel.setBounds(55, 5, 300, 100);
        	moveLabel.setBounds(135, 80, 130, 100);
        	timeLabel.setBounds(135, 140, 200, 100);
        	closeButton.setBounds(122, 250, 140, 50);
        	//exit.setBounds(55, 250, 140, 50);
        	//rank.setBounds(190, 250, 140, 50);
        	/*
        	rank.addActionListener(new ActionListener() {
        		@Override
        		public void actionPerformed(ActionEvent e) {
        			dispose();
        			new Rank();
        		}
        	});
    
        	exit.addActionListener(new ActionListener() {
        		@Override
        		public void actionPerformed(ActionEvent e) {
        			setVisible(false);
        			new Level();
        		}
        	});
        	*/
        	
        	closeButton.addActionListener(new ActionListener() {
        		@Override
        		public void actionPerformed(ActionEvent e) {
        			setVisible(false);
        		}
        	});
        	
        	add(clearLabel);
        	add(moveLabel);
        	add(timeLabel);
        	add(closeButton);
        	//add(exit);
        	//add(rank);
        	
		}
    }
    public void Image() {
    	
    }
    
    public void Level1() {
    	
    	Level = 1;
    	maxColors = 4;
		maxBottles = 6;
    	
    	Colors = new int[maxColors];
    	for(int i = 0; i < maxColors; i++) {
    		Colors[i] = i;
    	}
    	
		jb = new JLabel[30];
		jjb = new JLabel[maxBottles];
		bottlelabel = new JLabel[maxBottles];
		
		bottles = new ArrayList[maxBottles];
		for(int i = 0; i < maxBottles; i++) {
			bottles[i] = new ArrayList<Integer>(maxColors);
		}
        
		for(int i = 0; i < maxBottles; i++) {
        	jjb[i] = new JLabel(changeIcon);
        }
        
        for(int i = 0; i < maxBottles; i++) {
        	bottlelabel[i] = new JLabel("");
        }
    }
    
    public void Level2() {
    	
    	Level = 2;
    	maxColors = 5;
		maxBottles = 7;
    	
    	Colors = new int[maxColors];
    	for(int i = 0; i < maxColors; i++) {
    		Colors[i] = i;
    	}
    	
		jb = new JLabel[40];
		jjb = new JLabel[maxBottles];
		bottlelabel = new JLabel[maxBottles];
		
		bottles = new ArrayList[maxBottles];
		for(int i = 0; i < maxBottles; i++) {
			bottles[i] = new ArrayList<Integer>(maxColors);
		}
        
		for(int i = 0; i < maxBottles; i++) {
        	jjb[i] = new JLabel(changeIcon);
        }
        
        for(int i = 0; i < maxBottles; i++) {
        	bottlelabel[i] = new JLabel("");
        }
    }
    
    public void Level3() {
    	
    	Level = 3;
    	maxColors = 6;
		maxBottles = 8;
    	
    	Colors = new int[maxColors];
    	for(int i = 0; i < maxColors; i++) {
    		Colors[i] = i;
    	}
    	
		jb = new JLabel[40];
		jjb = new JLabel[maxBottles];
		bottlelabel = new JLabel[maxBottles];
		
		bottles = new ArrayList[maxBottles];
		for(int i = 0; i < maxBottles; i++) {
			bottles[i] = new ArrayList<Integer>(maxColors);
		}
        
		for(int i = 0; i < maxBottles; i++) {
        	jjb[i] = new JLabel(changeIcon);
        }
        
        for(int i = 0; i < maxBottles; i++) {
        	bottlelabel[i] = new JLabel("");
        }
    }
    
    public void Level4() {
    	
    	Level = 4;
    	maxColors = 7;
		maxBottles = 9;
    	
    	Colors = new int[maxColors];
    	for(int i = 0; i < maxColors; i++) {
    		Colors[i] = i;
    	}
    	
		jb = new JLabel[40];
		jjb = new JLabel[maxBottles];
		bottlelabel = new JLabel[maxBottles];
		
		bottles = new ArrayList[maxBottles];
		for(int i = 0; i < maxBottles; i++) {
			bottles[i] = new ArrayList<Integer>(maxColors);
		}
        
		for(int i = 0; i < maxBottles; i++) {
        	jjb[i] = new JLabel(changeIcon);
        }
        
        for(int i = 0; i < maxBottles; i++) {
        	bottlelabel[i] = new JLabel("");
        }
    }
    
    public void BackButton() {
    	btn3 = new JButton("뒤로");
        btn3.setLocation(20, 20);
        btn3.setSize(100,50);
        btn3.setVisible(true);
        add(btn3);

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new Main2();
            	th3.interrupt();
            	setVisible(false);
            }
        });
    }
    
    public void UndoButton() {
    	Undobutton = new JButton("back");
        Undobutton.setLocation(480, 20);
        Undobutton.setVisible(true);
        Undobutton.setSize(100,50);
        Undobutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(backcount >= 0) {
					To = 10;
					From = 10;
				}
            }
        });
        add(Undobutton);
    }
    
    public void Timetable() {
    	timelabel2 = new JLabel();
        timelabel2.setLocation(420, 20);
        timelabel2.setSize(100,50);
        timelabel2.setVisible(true);
        timelabel2.setFont(new Font("Gothic", Font.ITALIC, 20));
        runnable3 = new Counter(timelabel2);
        th3 = new Thread(runnable3);
        add(timelabel2);
    }
    
    public void ClickPanel() {
    	for(int i = 0; i < maxBottles;i++) {
        	jjb[i].setLocation((600/(maxBottles+2)) + (600/(maxBottles+2))*i,137);
        	jjb[i].setSize(40,90);
        	jjb[i].setVisible(true);
        	add(jjb[i]);
        	jjb[i].addMouseListener(new MyMouseListener());
        }
    }

    
	public WaterSort_main(int i) {
		
		postbottle = 100;
		MoveCnt = 0;
		To = 100;
		From = 100;
		count = 0;
		backcount = 2;
        colors = new Stack<>();
        Moves = new Stack<>();
        BackMovesCounter = new Stack<>();
        
        image = new ImageIcon("bottle.png");
        img = image.getImage();
        changeImg = img.getScaledInstance(140, 170, Image.SCALE_FAST);
        changeIcon = new ImageIcon(changeImg);
        
        image2 = new ImageIcon("bottle3.png");
        img2 = image2.getImage();
        changeImg2 = img2.getScaledInstance(140, 170, Image.SCALE_FAST);
        changeIcon2 = new ImageIcon(changeImg2);
        
        
        
        if(i == 1) {
			Level1();
		}
		else if(i == 2) {
			Level2();
		}
		else if(i == 3) {
			Level3();
		}
		else if(i == 4) {
			Level4();
		}
        
        setTitle("WaterSort");
        setLayout(null);
    	setSize(600,500);
    	setLocationRelativeTo(null);
        
        BackButton();
        UndoButton();
        Timetable();
        ClickPanel();

        //runnable3 = new Counter(timelabel2);
        //th3 = new Thread(runnable3);
        	
        fillcolorstack(colors);

        for (int count = 0; count < 4*maxColors; count++)
        {
            fillBottles(bottles, colors);
        }

        showAll(bottles);
        //th2.start();
        th3.start();
	}
    public void showAll(ArrayList bottles[]) {
    	for(int i = 0; i< jb.length;i++) {
    		if(jb[i] != null) {
    			jb[i].setVisible(false);
    		}
    	}
    	int z = 0;
    	
        for(int i = 0; i < maxBottles;i++) {
        	for(int j = 0; j < bottles[i].size(); j++) {
        		if((int)bottles[i].get(j) == 0) {
        			Xposition = (600/(maxBottles+2)) + (600/(maxBottles+2))*i;
        			Yposition = 200-(j*20);
        			jb[z] = new JLabel("");
        			jb[z].setSize(40,20);
        			jb[z].setOpaque(true);
        			jb[z].setBackground(Color.red);
        			jb[z].setLocation(Xposition, Yposition);
        			add(jb[z]);
        			z++;
        		}
        		else if((int)bottles[i].get(j) == 1) {
        			Xposition = (600/(maxBottles+2)) + (600/(maxBottles+2))*i;
        			Yposition = 200-(j*20);
        			jb[z] = new JLabel("");
        			jb[z].setSize(40,20);
        			jb[z].setOpaque(true);
        			jb[z].setBackground(Color.blue);
        			jb[z].setLocation(Xposition, Yposition);
        			add(jb[z]);
        			z++;
        		}
        		else if((int)bottles[i].get(j) == 2) {
        			Xposition = (600/(maxBottles+2)) + (600/(maxBottles+2))*i;
        			Yposition = 200-(j*20);
        			jb[z] = new JLabel("");
        			jb[z].setSize(40,20);
        			jb[z].setOpaque(true);
        			jb[z].setBackground(Color.green);
        			jb[z].setLocation(Xposition, Yposition);
        			add(jb[z]);
        			z++;
        		}
        		else if((int)bottles[i].get(j) == 3) {
        			Xposition = (600/(maxBottles+2)) + (600/(maxBottles+2))*i;
        			Yposition = 200-(j*20);
        			jb[z] = new JLabel("");
        			jb[z].setSize(40,20);
        			jb[z].setOpaque(true);
        			jb[z].setBackground(Color.pink);
        			jb[z].setLocation(Xposition, Yposition);
        			add(jb[z]);
        			z++;
        		}
        		else if((int)bottles[i].get(j) == 4) {
        			Xposition = (600/(maxBottles+2)) + (600/(maxBottles+2))*i;
        			Yposition = 200-(j*20);
        			jb[z] = new JLabel("");
        			jb[z].setSize(40,20);
        			jb[z].setOpaque(true);
        			jb[z].setBackground(Color.cyan);
        			jb[z].setLocation(Xposition, Yposition);
        			add(jb[z]);
        			z++;
        		}
        		else if((int)bottles[i].get(j) == 5) {
        			Xposition = (600/(maxBottles+2)) + (600/(maxBottles+2))*i;
        			Yposition = 200-(j*20);
        			jb[z] = new JLabel("");
        			jb[z].setSize(40,20);
        			jb[z].setOpaque(true);
        			jb[z].setBackground(Color.gray);
        			jb[z].setLocation(Xposition, Yposition);
        			add(jb[z]);
        			z++;
        		}
        		else if((int)bottles[i].get(j) == 6) {
        			Xposition = (600/(maxBottles+2)) + (600/(maxBottles+2))*i;
        			Yposition = 200-(j*20);
        			jb[z] = new JLabel("");
        			jb[z].setSize(40,20);
        			jb[z].setOpaque(true);
        			jb[z].setBackground(Color.orange);
        			jb[z].setLocation(Xposition, Yposition);
        			add(jb[z]);
        			z++;
        		}
        		else {
        			jb[z] = new JLabel();
        		}
        	}
        }
        setVisible(true);
        paint(getGraphics());
    }

    public int generateRandom(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void fillBottles(ArrayList bottles[], Stack colors) {
        rand_bottle = generateRandom(0, maxBottles-2);
        if (bottles[rand_bottle].size() < 4)
        {
            bottles[rand_bottle].add(colors.pop());
        } else {
            fillBottles(bottles, colors);
        }
    }

    public void fillcolorstack(Stack colors) {
        for (int x = 0; x < 4; x++)
        {
        	for(int i = 0; i < maxColors;i++) {
        		colors.push(Colors[i]);
        	}
        }
    }
    
    public boolean checkUniform(ArrayList bottles[], int x) {
    	x1 = x;
    	count2 = 0;
    	
    	if (bottles[x1].size() == 0)
    		 return true;
    	else
    	{
          for (int i = 0; i < bottles[x1].size(); i++) {
            for(int j = 1; j < bottles[x1].size();j++) {
            	if(!bottles[x1].get(i).equals(bottles[x1].get(j))) {
            		count2++;
            	}
            }
            if(count2 > 2) {
            	return false;
            }
    	  }
          return true;
    	}
      }

    public boolean Solved(ArrayList bottles[]) { //물병 다 찼는지 검사

        for (int x = 0; x < maxBottles; x++) {
            if (checkUniform(bottles, x) && (bottles[x].size() == 4 || bottles[x].size() == 0)) {
            	showAll(bottles);
            } else {
                return false;
            }
        }
        return true;
    }
    
    public int peek(ArrayList bottles[], int x) {
    	temp = ' ';
    	x2 = x;
        if (bottles[x2].size() > 0)
            temp = (int) bottles[x2].get(bottles[x2].size() - 1);
        return temp;
    }
    public boolean isValidMove(ArrayList bottles[], Stack Moves, Stack BackMovesCounter, int from, int to) {
    	
        if (bottles[from].size() == 0) {
        	System.out.println("empty");
            return false;
        }
        else if (bottles[to].size() >= maxColors) {
        	System.out.println("fill");
            return false;
        }
        else if (from == to) {
        	System.out.println("same");
            return false;
        }

        else if (!bottles[from].get(bottles[from].size()-1).equals(null) && bottles[to].size() == 0) // to 물병의 크기가 0일때
        {
        	excount = 0;
            do {
            	if(bottles[to].size() <= 4) {
                	bottles[to].add(bottles[from].get(bottles[from].size()-1));
                    bottles[from].remove(bottles[from].size()-1);
                    Moves.push(from);
                    Moves.push(to);
                    excount++;
            	}
            	else {
            		break;
            	}

            } while (peek(bottles, from) == peek(bottles,to));
            BackMovesCounter.push(excount);
            MoveCnt++;
            System.out.println("moves : " + MoveCnt );
            return true;
        }

        else if ((!bottles[from].get(bottles[from].size()-1).equals(null) && !bottles[to].get(bottles[to].size()-1).equals(null))
                && bottles[from].get(bottles[from].size()-1).equals(bottles[to].get(bottles[to].size()-1))) {//to, from 물병의 크기가 0이 아니고, form물병의 최상단이
        	//to 물병의 최상단과 같을 때
        	excount2 = 0;
            do {
            	if(bottles[to].size() < 4) {
            		bottles[to].add(bottles[from].get(bottles[from].size()-1));
                    bottles[from].remove(bottles[from].size()-1);
                    Moves.push(from);
                    Moves.push(to);
                    excount2++;
            	}
            	else {
            		break;
            	}
                
                
            } while (peek(bottles, from) == peek(bottles,to));
            BackMovesCounter.push(excount2);
            System.out.println("backmove = " + BackMovesCounter.peek());
            MoveCnt++;
            System.out.println("moves : " + MoveCnt );
            return true;

        } else {
            return false;
        }
    }
    public int getMoveCnt() {
    	return this.MoveCnt;
    }
    public void Undo(ArrayList bottles[], Stack Moves, Stack BackMovesCounter) {
        if (Moves.peek() != null) {
        	if(BackMovesCounter.peek() != null) {
        		peekcount = (int)BackMovesCounter.peek();
        		if((int)BackMovesCounter.peek() == 1) {
        			undoto = (int) (Moves.pop());
                    undofrom = (int) (Moves.pop());
                    bottles[undofrom].add(bottles[undoto].get(bottles[undoto].size()-1));
                    bottles[undoto].remove(bottles[undoto].size()-1);
        		}
        		else {
        			while(peekcount > 0) {
            			undoto = (int) (Moves.pop());
                        undofrom = (int) (Moves.pop());
                        bottles[undofrom].add(bottles[undoto].get(bottles[undoto].size()-1));
                        bottles[undoto].remove(bottles[undoto].size()-1);
                        peekcount--;
                        
            		}
        		}
        		BackMovesCounter.pop();
        	}
        	else {
        		undoto = (int) (Moves.pop());
                undofrom = (int) (Moves.pop());
                bottles[undofrom].add(bottles[undoto].get(bottles[undoto].size()-1));
                bottles[undoto].remove(bottles[undoto].size()-1);
        	}
        	System.out.println("ssssssssssssss");
            showAll(bottles);
        }
    }

    public void operation(ArrayList bottles[], Stack Moves, Stack BackMovesCounter) {
    	
    	try {
			System.out.print("");
    		if (From == 10 && To == 10) {
    			Undo(bottles, Moves, BackMovesCounter);
    			System.out.println("backcount =" + backcount);
    			backcount--;
                From = 7;
                To = 7;
    		}
    		else if(From != 100 && To != 100){

                if (isValidMove(bottles, Moves, BackMovesCounter, From, To)) {
                    System.out.println("move");
                    showAll(bottles);
                    From = 100;
                    To = 100;
                } else {
                	System.out.println("move ex");
                    showAll(bottles);
                    From = 100;
                    To = 100;
                }
            }
    		else {
    			
    		}
		}catch (Exception e) {
        	System.out.println("catch");
	}
}
    
    class MyMouseListener implements MouseListener{
    	
		@Override
		public void mouseClicked(MouseEvent e) {
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			click = (JLabel)e.getSource();
			for(int i = 0; i < maxBottles; i++) {
				if(jjb[i] == click) {
					if(count == 1) {
						jjb[postbottle].setIcon(changeIcon);
						count--;
						To = i;
						postbottle = 100;
					}
					else {
						jjb[i].setIcon(changeIcon2);
						count++;
						From = i;
						postbottle = i;
					}
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}
    }
}

public class WaterSort_1 {
	public static void main(String args[]) {
		new WaterSort_main(1);
    }
}