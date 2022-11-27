package watersort;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;

class WaterSortGame extends JFrame {
	
	private int level;
	private int maxColors;
	private int maxBottles;
	private int[] colorType;
	private int moveCnt;
	private int To;
	private int From;
	private int count;
	private int backLimit;
	private JLabel[] bottle;
	private JLabel[] bottleBorder;
	private Thread thread;
	private ArrayList<Integer> bottles[];
	private Stack<Integer> colors = new Stack<>();
	private Stack<Integer> moves = new Stack<>();
	private Stack<Integer> backMovesCounter = new Stack<>();
	private JLabel timer;
	private Counter counter;
    
    private ImageIcon fromBottle;
    private ImageIcon toBottle;
    private int postBottle;
    
    private int second;
    private int millisecond;
    
    private Color[] waterColor = {Color.red, Color.blue, Color.green, Color.pink, Color.cyan, Color.gray, Color.orange};
	
    public WaterSortGame() {
    	moveCnt = 0;
		To = 100;
		From = 100;
		count = 0;
		backLimit = 2;
		postBottle = 100;
	}
    
    public void play(int level) {  
    	this.level = level;
		setBottleImage();
    	setLevel(level);
		Level();
    	setGameLayout();
    }
    
    private void setBottleImage() {
    	ImageIcon image = new ImageIcon("image/bottle.png");
        Image img = image.getImage();
        Image fromImg = img.getScaledInstance(140, 170, Image.SCALE_FAST);
        fromBottle = new ImageIcon(fromImg);
        
        image = new ImageIcon("image/bottle3.png");
        img = image.getImage();
        Image toImg = img.getScaledInstance(140, 170, Image.SCALE_FAST);
        toBottle = new ImageIcon(toImg);	
    }
    
    private void setLevel(int level) {
    	if(level == 1) {
			maxColors = 4;
			maxBottles = 6;
		} else if(level == 2) {
			maxColors = 5;
			maxBottles = 7;
		} else if(level == 3) {
			maxColors = 6;
			maxBottles = 8;
		} else if(level == 4) {
			maxColors = 7;
			maxBottles = 9;
		}
    }
    
    private void Level() {    	
    	colorType = new int[maxColors];
    	bottle = new JLabel[30];
    	bottleBorder = new JLabel[maxBottles];
		//JLabel[] bottleLabel = new JLabel[maxBottles];
		bottles = new ArrayList[maxBottles];
		
    	for(int i = 0; i < maxColors; i++) {
    		colorType[i] = i;
    	}
    	
		for(int i = 0; i < maxBottles; i++) {
			bottles[i] = new ArrayList<Integer>(maxColors);
		}
        
        for(int i = 0; i < maxBottles; i++) {
        	bottleBorder[i] = new JLabel(fromBottle);
        }
        
//        for(int i = 0; i < maxBottles; i++) {
//        	bottleLabel[i] = new JLabel("");
//        }
    }
    
    private void setGameLayout() {
    	setTitle("WaterSort");
        setLayout(null);
    	setSize(600,500);
        
    	outAction();
    	undoAction();
    	timerAction();
    	clickBottleAction();
        
        fillColor(colors);

        for (int count = 0; count < 4 * maxColors; count++) {
            fillBottles();
        }

        showAll();
        thread.start();
    }
    
    private void outAction() {
    	JButton outBtn = new JButton("나가기");
    	outBtn.setLocation(20, 20);
    	outBtn.setSize(100, 50);
    	outBtn.setVisible(true);
        add(outBtn);

        outBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	thread.interrupt();
            	setVisible(false);
            	new Menu();
            }
        });
    }
    
    private void undoAction() {
    	JButton undoBtn = new JButton("뒤로");
    	undoBtn.setLocation(480, 20);
    	undoBtn.setVisible(true);
    	undoBtn.setSize(100,50);
    	undoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(backLimit >= 0) {
					To = 10;
					From = 10;
				}
            }
        });
        add(undoBtn);
    }
    
    private void timerAction() {
    	timer = new JLabel();
    	timer.setLocation(420, 20);
    	timer.setSize(100,50);
    	timer.setVisible(true);
    	timer.setFont(new Font("Gothic", Font.ITALIC, 20));
        counter = new Counter(timer);
        thread = new Thread(counter);
        add(timer);
    }
    
    private void clickBottleAction() {
    	for(int i = 0; i < maxBottles;i++) {
    		bottleBorder[i].setLocation((600 / (maxBottles + 2)) + (600 / (maxBottles + 2)) * i,140);
    		bottleBorder[i].setSize(40, 80);
    		bottleBorder[i].setVisible(true);
        	add(bottleBorder[i]);
        	bottleBorder[i].addMouseListener(new MyMouseListener());
        }
    }
    
    private void showAll() {
    	for(int i = 0; i< bottle.length; i++) {
    		if(bottle[i] != null) {
    			bottle[i].setVisible(false);
    		}
    	}
    	int z = 0;
    	
        for(int i = 0; i < maxBottles;i++) {
        	for(int j = 0; j < bottles[i].size(); j++) {
        		int colorNumber = (int) bottles[i].get(j);
        		bottle[z] = new JLabel();
        		if (colorNumber >= 0 && colorNumber <= 6) {	    			
        			int xPosition = (600 / (maxBottles + 2)) + (600 / (maxBottles + 2)) * i;
	    			int yPosition = 200 - (j * 20);
	    			bottle[z].setSize(40,20);
	    			bottle[z].setOpaque(true);
	    			bottle[z].setLocation(xPosition, yPosition);
	    			bottle[z].setBackground(waterColor[colorNumber]);
	    			add(bottle[z]);
	    			z++;
        		}
        	}
        }
        setVisible(true);
        paint(getGraphics());
    }
    
    private void fillBottles() {
        int index = generateRandom(0, maxBottles - 2);
        if (bottles[index].size() < 4) {
            bottles[index].add(colors.pop());
        } else {
            fillBottles();
        }
    }

    private void fillColor(Stack<Integer> colors) {
        for (int x = 0; x < 4; x++) {
        	for(int i = 0; i < maxColors;i++) {
        		colors.push(colorType[i]);
        	}
        }
    }
    
    private int generateRandom(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }   
    
    
    //------------------------------------------여기까지 레이아웃------------------------------------------
    
    class ShowClear extends JFrame{
    	private boolean clearcheck = false; //true 되면 클리어 정보 화면으로
        private JLabel clearLabel;
    	private JLabel moveLabel;
    	private JLabel timeLabel;
    	private JButton closeBtn;
    	private JButton exitBtn;
    	private JButton rankBtn;
    	
    	public ShowClear() {
			setTitle("결과 화면");
			setSize(400, 400);
			setLayout(null);
        	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	setVisible(true);
        	setLocationRelativeTo(null);
        
        	clearLabel = new JLabel("Level" + level + " 클리어!");
        	moveLabel = new JLabel("이동 : " + moveCnt + "번");
        	timeLabel = new JLabel("시간 : " + second + "." + millisecond + "초");
        
        	clearLabel.setFont(new Font("Gothic", Font.BOLD, 40));
        	moveLabel.setFont(new Font("Gothic", Font.BOLD, 22));
        	timeLabel.setFont(new Font("Gothic", Font.BOLD, 22));
        	
        	closeBtn = makeUI("image/Home.png");
        	exitBtn = makeUI("image/Select.png");
        	rankBtn = makeUI("image/Rank.png");
        	limpidity(closeBtn);
        	limpidity(exitBtn);
        	limpidity(rankBtn);
        	
        	clearLabel.setBounds(55, 5, 300, 100);
        	moveLabel.setBounds(135, 80, 130, 100);
        	timeLabel.setBounds(135, 140, 200, 100);
        	closeBtn.setBounds(145, 250, 100, 50);
        	exitBtn.setBounds(35, 250, 100, 50);
        	rankBtn.setBounds(255, 250, 100, 50);

        	rankBtn.addActionListener(new ActionListener() {
        		@Override
        		public void actionPerformed(ActionEvent e) {
        			dispose();
        			new Rank();
        			Back();
        		}
        	});
    
        	exitBtn.addActionListener(new ActionListener() {
        		@Override
        		public void actionPerformed(ActionEvent e) {
        			setVisible(false);
        			new Level();
        			Back();
        		}
        	});
        	
        	
        	closeBtn.addActionListener(new ActionListener() {
        		@Override
        		public void actionPerformed(ActionEvent e) {
        			setVisible(false);
        			new Menu();
        			Back();
        		}
        	});
        	
        	add(clearLabel);
        	add(moveLabel);
        	add(timeLabel);
        	
        	add(exitBtn);
        	add(rankBtn);
        	add(closeBtn);
        	paint(getGraphics());
		}
    	
    	private JButton makeUI(String name) {
    		ImageIcon icon = new ImageIcon(name);
    		Image image = icon.getImage();
    		image = image.getScaledInstance(100, 100, Image.SCALE_FAST);
    		return new JButton(new ImageIcon(image));
    	}
    	
    	private void limpidity(JButton btn) {
    		btn.setBorderPainted(false);
    		btn.setContentAreaFilled(false);
    		btn.setFocusPainted(false);
    		btn.setOpaque(false);
    	}
    	
    	public void Back() {
        	this.setVisible(false);
        }
    }
    
    //-------------------------------------- 여기까지 랭크 화면 --------------------------------
    class Counter extends JFrame implements Runnable {
    	
    	private Clear clear;
    	private JLabel timel;
    	
    	public Counter(JLabel timel) {
    		clear = new Clear();
    		this.timel = timel;
    	}
    	
    	public void run() {
    	
    		while(true) {
    			try {
    				++millisecond;
    				if(millisecond == 10) {
    					millisecond = 0;
    					++second;
    				}
    				
            		timel.setText(Integer.toString(second) +  " : " + Integer.toString(millisecond));
    				
    				operation();
    				if(isSolved()) {
    					DataBase dataBase = new DataBase();
    					
    					clear.run(level);
    					System.out.println("시간 = " + second + ":" + millisecond);
    					
    					LogIn login = new LogIn();
    					int id = login.getUserId();
    					int userlevel = dataBase.checklevel(id, level);
    					int userTime = dataBase.getTime(id, level);
    					
    					ResultSet result = dataBase.getResult(id);
    					String timelabel = timer.getText().toString();
    					System.out.println(timelabel);
    					int time = Integer.parseInt(timelabel.split(" : ")[0]);
    					
    					if (!result.next()) {
    						String insertSql = "insert into game (user_id, level, move, time) values (" + id + "," + level + "," + moveCnt + "," + time + ");";
    						if(dataBase.updateResult(insertSql)) {
    							JOptionPane.showMessageDialog(null, "순위 등록 완료");
    						}
    					} else {
    						if(userlevel < level) {
    							String insertSql = "insert into game (use"
        								+ "r_id, level, move, time) values (" + id + "," + level + "," + moveCnt + "," + time + ");";
        						if(dataBase.updateResult(insertSql)) {
        							JOptionPane.showMessageDialog(null, "레벨 순위 등록 완료");
        						}
    						} else if(userTime > time) {
								String updateSql = "update game set move=" + moveCnt + ", time=" + time + " where user_id=" + id + " and level=" + level + ";";
        						if (dataBase.updateResult(updateSql)) {
        							JOptionPane.showMessageDialog(null, "순위 갱신 완료");
        						}
    						}
    					}
    					new ShowClear();
    					thread.interrupt();
    		        	break;

    		        }
    				Thread.sleep(100);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			} catch (SQLException e) {
					e.printStackTrace();
				}
    		}
    		
    		//moveRankPage();
    		
//    		setVisible(false);
//			new Rank();	
    	}
    	
//    	private void moveRankPage() {
//    		thread.interrupt();
//        	setVisible(false);
//        	new Rank();
//        }
    }

 // ----------------------------------------------- 여기까지 스레드 부분 -------------------------------------
    private boolean isSolved() { 
        for (int x = 0; x < maxBottles; x++) {
            if (!checkPaintType(x) || !checkBottleSize(x)) {
            	return false;
            }
            showAll();
        }
        return true;
    }
    
    private boolean checkPaintType(int x) { 
    	int count = 0;
    	
    	if (bottles[x].size() == 0) {
    		 return true;
    	} 
    	
    	for (int i = 0; i < bottles[x].size(); i++) {
    		for (int j = 1; j < bottles[x].size(); j++) {
    			if (!bottles[x].get(i).equals(bottles[x].get(j))) {
    				count++;
    			}
    		}
    		if (count > 2) {
    			return false;
    		}
    	}
    	return true;
    }
    
    private boolean checkBottleSize(int x) {
    	return bottles[x].size() == 4 || bottles[x].size() == 0;
    }
    
    private int peek(int x) {
    	int temp = ' ';
    	
        if (bottles[x].size() > 0) {
            temp = (int) bottles[x].get(bottles[x].size() - 1);
        }
        return temp;
    }

    private boolean isValidMove(int from, int to) {
    	
        if (bottles[from].size() == 0) { // from bottle이 0인 경우
        	System.out.println("empty");
            return false;
        }  
        if (bottles[to].size() >= maxColors) { // to bottle의 maxsize를 초과하는 경우
        	System.out.println("fill");
            return false;
        } 
        if (from == to) { // to, from 같은 위치를 클릭한 경우
        	System.out.println("same");
            return false;
        }
        if (!bottles[from].get(bottles[from].size()-1).equals(null) && bottles[to].size() == 0) { // to 물병의 크기가 0일때
        	int excount = 0;
            do {
            	if(bottles[to].size() <= 4) {
                	bottles[to].add(bottles[from].get(bottles[from].size()-1));
                    bottles[from].remove(bottles[from].size()-1);
                    moves.push(from);
                    moves.push(to);
                    excount++;
            	}
            	else {
            		break;
            	}

            } while (peek(from) == peek(to));
            backMovesCounter.push(excount);
            moveCnt++;
            System.out.println("moves : " + moveCnt );
            return true;
            
        } 
        if ((!bottles[from].get(bottles[from].size()-1).equals(null) && !bottles[to].get(bottles[to].size()-1).equals(null))
                && bottles[from].get(bottles[from].size()-1).equals(bottles[to].get(bottles[to].size()-1))) {//to, from 물병의 크기가 0이 아니고, form물병의 최상단이
        	//to 물병의 최상단과 같을 때
        	int excount2 = 0;
            do {
            	if(bottles[to].size() < 4) {
            		bottles[to].add(bottles[from].get(bottles[from].size()-1));
                    bottles[from].remove(bottles[from].size()-1);
                    moves.push(from);
                    moves.push(to);
                    excount2++;
            	}
            	else {
            		break;
            	}
                
                
            } while (peek(from) == peek(to));
            
            backMovesCounter.push(excount2);
            System.out.println("backmove = " + backMovesCounter.peek());
            moveCnt++;
            System.out.println("moves : " + moveCnt );
            return true;

        } 
        return false;
    }

    private void undo() { // 뒤로 가기 액션
    	int undoTo = 0;
    	int undoFrom = 0;
    	int peekCount = 0;
    	
        if (moves.peek() != null) {
        	if(backMovesCounter.peek() != null) {
        		peekCount = (int)backMovesCounter.peek();
        		if((int) backMovesCounter.peek() == 1) {
        			undoTo = (int) (moves.pop());
                    undoFrom = (int) (moves.pop());
                    bottles[undoFrom].add(bottles[undoTo].get(bottles[undoTo].size()-1));
                    bottles[undoTo].remove(bottles[undoTo].size()-1);
        		} else {
        			while(peekCount > 0) {
        				undoTo = (int) (moves.pop());
        				undoFrom = (int) (moves.pop());
                        bottles[undoFrom].add(bottles[undoTo].get(bottles[undoTo].size()-1));
                        bottles[undoTo].remove(bottles[undoTo].size()-1);
                        peekCount--;
                        
            		}
        		}
        		backMovesCounter.pop();
        	} else {
        		undoTo = (int) (moves.pop());
        		undoFrom = (int) (moves.pop());
                bottles[undoFrom].add(bottles[undoTo].get(bottles[undoTo].size()-1));
                bottles[undoTo].remove(bottles[undoTo].size()-1);
        	}
            showAll();
        }
    }

    public void operation() {    	
    	try {
    		if (From == 10 && To == 10) {
    			undo();
    			System.out.println("backcount =" + backLimit);
    			backLimit--;
                From = 7;
                To = 7;
    		} else if (From != 100 && To != 100) {

                if (isValidMove(From, To)) {
                    System.out.println("move");
                    showAll();
                    From = 100;
                    To = 100;
                } else {
                	System.out.println("move ex");
                    showAll();
                    From = 100;
                    To = 100;
                }
            }
		} catch (Exception e) {
        	System.out.println("catch");
        	return;
		}	
    }
    
	class MyMouseListener implements MouseListener {
	    	
		@Override
		public void mouseClicked(MouseEvent e) {
			
		}
			
		@Override
		public void mousePressed(MouseEvent e) {
			JLabel click = (JLabel)e.getSource();
			for(int i = 0; i < maxBottles; i++) {
				if(bottleBorder[i] == click) {
					if(count == 1) {
						bottleBorder[postBottle].setIcon(fromBottle);
						count--;
						To = i;
						postBottle = 100;
					}
					else {
						bottleBorder[i].setIcon(toBottle);
						count++;
						From = i;
						postBottle = i;
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
