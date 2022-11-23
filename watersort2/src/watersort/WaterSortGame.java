package watersort;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;

class WaterSortGame extends JFrame{
	
	private int level;
	private int maxColors;
	private int maxBottles;
	private int[] colorType;
	private int MoveCnt;
	private int To;
	private int From;
	private int count;
	private int backcount;
	private JLabel[] jb;
	private JLabel[] jjb;
	private JLabel[] bottlelabel;
	private Thread th3;
	private ArrayList<Integer> bottles[];
	private Stack<Integer> colors;
	private Stack<Integer> Moves;
	private Stack<Integer> BackMovesCounter;
	private JButton btn3;
	private JLabel timelabel2;
	private Counter runnable3;
	private int rand_bottle;
    private int x1;
    private int count2;
    private int temp;
    private int x2;
    private int excount;
    private int excount2;
    private JLabel click;
    private int undoto;
    private int undofrom;
    private int peekcount;
    private JButton Undobutton;
    private int Xposition;
    private int Yposition;
    
    private ImageIcon fromBottle;
    private ImageIcon toBottle;
    private int postBottle;
    
    private int second;
    private int millisecond;
    
    public WaterSortGame() {
		MoveCnt = 0;
		To = 100;
		From = 100;
		count = 0;
		backcount = 2;
		postBottle = 100;
		
        colors = new Stack<>();
        Moves = new Stack<>();
        BackMovesCounter = new Stack<>();
        
        ImageIcon image = new ImageIcon("image/bottle.png");
        Image img = image.getImage();
        Image fromImg = img.getScaledInstance(140, 170, Image.SCALE_FAST);
        fromBottle = new ImageIcon(fromImg);
        
        image = new ImageIcon("image/bottle3.png");
        img = image.getImage();
        Image toImg = img.getScaledInstance(140, 170, Image.SCALE_FAST);
        toBottle = new ImageIcon(toImg);
	}
    
    public void play(int level) {
    	this.level = level;

    	setLevel(level);
    	
		Level();
		
    	setGameLayout();
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
    	
    	for(int i = 0; i < maxColors; i++) {
    		colorType[i] = i;
    	}
    	
		jb = new JLabel[30];
		jjb = new JLabel[maxBottles];
		bottlelabel = new JLabel[maxBottles];
		
		bottles = new ArrayList[maxBottles];
		for(int i = 0; i < maxBottles; i++) {
			bottles[i] = new ArrayList<Integer>(maxColors);
		}
        
        for(int i = 0; i < maxBottles; i++) {
        	jjb[i] = new JLabel(fromBottle);
        }
        
        for(int i = 0; i < maxBottles; i++) {
        	bottlelabel[i] = new JLabel("");
        }
    }
    
    private void setGameLayout() {
    	setTitle("WaterSort");
        setLayout(null);
    	setSize(600,500);
        
        BackButton();
        UndoButton();
        Timetable();
        ClickPanel();

        fillcolorstack(colors);

        for (int count = 0; count < 4 * maxColors; count++) {
            fillBottles(bottles, colors);
        }

        showAll(bottles);
        th3.start();
    }
    
    private void BackButton() {
    	btn3 = new JButton("뒤로");
        btn3.setLocation(20, 20);
        btn3.setSize(100,50);
        btn3.setVisible(true);
        add(btn3);

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	th3.interrupt();
            	setVisible(false);
            	new Menu();
            }
        });
    }
    
    private void UndoButton() {
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
    
    private void Timetable() {
    	timelabel2 = new JLabel();
        timelabel2.setLocation(420, 20);
        timelabel2.setSize(100,50);
        timelabel2.setVisible(true);
        timelabel2.setFont(new Font("Gothic", Font.ITALIC, 20));
        runnable3 = new Counter(timelabel2);
        th3 = new Thread(runnable3);
        add(timelabel2);
    }
    
    private void ClickPanel() {
    	for(int i = 0; i < maxBottles;i++) {
        	jjb[i].setLocation((600/(maxBottles+2)) + (600/(maxBottles+2))*i,140);
        	jjb[i].setSize(40,80);
        	jjb[i].setVisible(true);
        	add(jjb[i]);
        	jjb[i].addMouseListener(new MyMouseListener());
        }
    }
	
    private void showAll(ArrayList bottles[]) {
    	for(int i = 0; i< jb.length;i++) {
    		if(jb[i] != null) {
    			jb[i].setVisible(false);
    		}
    	}
    	int z = 0;
    	
        for(int i = 0; i < maxBottles;i++) {
        	for(int j = 0; j < bottles[i].size(); j++) {
        		int colorNumber = (int) bottles[i].get(j);
        		if (colorNumber >= 0 && colorNumber <= 6) {
	    			
	    			switch (colorNumber) {
		    			case 0:
		    				jb[z] = new JLabel("RED"); 
		        			jb[z].setBackground(Color.red); 
		        			break;
		    			case 1:
		    				jb[z] = new JLabel("BLUE");
		        			jb[z].setBackground(Color.blue);
		        			break;
		    			case 2:
		    				jb[z] = new JLabel("GREEN");
		        			jb[z].setBackground(Color.green);
		        			break;
		    			case 3:
		    				jb[z] = new JLabel("PINK");
		        			jb[z].setBackground(Color.pink);
		        			break;
		    			case 4:
		    				jb[z] = new JLabel("CYAN");
		        			jb[z].setBackground(Color.cyan);
		        			break;
		    			case 5:
		    				jb[z] = new JLabel("GRAY");
		        			jb[z].setBackground(Color.gray);
		    				break;
		    			case 6:
		    				jb[z] = new JLabel("ORANGE");
		        			jb[z].setBackground(Color.orange);
		    				break;
	    			}
	    			
	    			Xposition = (600/(maxBottles+2)) + (600/(maxBottles+2))*i;
	    			Yposition = 200-(j*20);
	    			jb[z].setSize(40,20);
	    			jb[z].setOpaque(true);
	    			jb[z].setLocation(Xposition, Yposition);
	    			add(jb[z]);
	    			z++;
        		} else {
        			jb[z] = new JLabel();
        		}
        	}
        }
        setVisible(true);
        paint(getGraphics());
    }
    
    
    class Counter extends JFrame implements Runnable {
    	
    	private Clear clear;
    	private JLabel timel;
    	private int n2;
    	private int n;
    	
    	public Counter(JLabel timel) {
    		clear = new Clear();
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
    					DataBase dataBase = new DataBase();
    					if(level == 1) {
    		        		clear.run(1);
    		        		System.out.println("시간 = " + second + ":" + millisecond);
    		        	}
    		        	else if(level == 2) {
    		        		clear.run(2);
    		        		System.out.println("시간 = " + second + ":" + millisecond);
    		        	}
    		        	else if(level == 3) {
    		        		clear.run(3);
    		        		System.out.println("시간 = " + second + ":" + millisecond);
    		        	}
    		        	else if(level == 4) {
    		        		clear.run(4);
    		        		System.out.println("시간 = " + second + ":" + millisecond);
    		        	}
    					
    					LogIn login = new LogIn();
    					int id = login.getUserId();
    					
    					String sql = "select * from game where user_id = " + id + ";";
    					
    					ResultSet result = dataBase.getResult(sql);
    					String timelabel = timelabel2.getText().toString();
    					System.out.println(timelabel);
    					int time = Integer.parseInt(timelabel.split(" : ")[0]);
    					
    					if (!result.next()) {
    						String insertSql = "insert into game (user_id, level, move, time) values (" + id + "," + level + "," + MoveCnt + "," + time + ");";
    						if(dataBase.updateResult(insertSql)) {
    							JOptionPane.showMessageDialog(null, "순위 등록 완료");
    						}
    					} else {
    						String updateSql = "update game set move=" + MoveCnt + ", time=" + time + " where user_id=" + id + " and level=" + level + ";";
    						if (dataBase.updateResult(updateSql)) {
    							JOptionPane.showMessageDialog(null, "순위 갱신 완료");
    						}
    					}
    		        	th3.interrupt();
    		        	break;

    		        }
    				Thread.sleep(100);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			} catch (SQLException e) {
					e.printStackTrace();
				}
    		}
    		setVisible(false);
			new Rank();	
    	}
    }

    private int generateRandom(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private void fillBottles(ArrayList bottles[], Stack colors) {
        rand_bottle = generateRandom(0, maxBottles-2);
        if (bottles[rand_bottle].size() < 4) {
            bottles[rand_bottle].add(colors.pop());
        } else {
            fillBottles(bottles, colors);
        }
    }

    private void fillcolorstack(Stack colors) {
        for (int x = 0; x < 4; x++) {
        	for(int i = 0; i < maxColors;i++) {
        		colors.push(colorType[i]);
        	}
        }
    }
    
    private boolean checkUniform(ArrayList bottles[], int x) {
    	x1 = x;
    	count2 = 0;
    	
    	if (bottles[x1].size() == 0) {
    		 return true;
    	} else {
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

    private boolean Solved(ArrayList bottles[]) { //물병 다 찼는지 검사

        for (int x = 0; x < maxBottles; x++) {
            if (checkUniform(bottles, x) && (bottles[x].size() == 4 || bottles[x].size() == 0)) {
            	showAll(bottles);
            } else {
                return false;
            }
        }
        return true;
    }
    
    private int peek(ArrayList bottles[], int x) {
    	temp = ' ';
    	x2 = x;
        if (bottles[x2].size() > 0) {
            temp = (int) bottles[x2].get(bottles[x2].size() - 1);
        }
        return temp;
    }

    private boolean isValidMove(ArrayList bottles[], Stack Moves, Stack BackMovesCounter, int from, int to) {
    	
        if (bottles[from].size() == 0) {
        	System.out.println("empty");
            return false;
        } else if (bottles[to].size() >= maxColors) {
        	System.out.println("fill");
            return false;
        } else if (from == to) {
        	System.out.println("same");
            return false;
        } else if (!bottles[from].get(bottles[from].size()-1).equals(null) && bottles[to].size() == 0) // to 물병의 크기가 0일때
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
            
        } else if ((!bottles[from].get(bottles[from].size()-1).equals(null) && !bottles[to].get(bottles[to].size()-1).equals(null))
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

    private void Undo(ArrayList bottles[], Stack Moves, Stack BackMovesCounter) {
        if (Moves.peek() != null) {
        	if(BackMovesCounter.peek() != null) {
        		peekcount = (int)BackMovesCounter.peek();
        		if((int)BackMovesCounter.peek() == 1) {
        			undoto = (int) (Moves.pop());
                    undofrom = (int) (Moves.pop());
                    bottles[undofrom].add(bottles[undoto].get(bottles[undoto].size()-1));
                    bottles[undoto].remove(bottles[undoto].size()-1);
        		} else {
        			while(peekcount > 0) {
            			undoto = (int) (Moves.pop());
                        undofrom = (int) (Moves.pop());
                        bottles[undofrom].add(bottles[undoto].get(bottles[undoto].size()-1));
                        bottles[undoto].remove(bottles[undoto].size()-1);
                        peekcount--;
                        
            		}
        		}
        		BackMovesCounter.pop();
        	} else {
        		undoto = (int) (Moves.pop());
                undofrom = (int) (Moves.pop());
                bottles[undofrom].add(bottles[undoto].get(bottles[undoto].size()-1));
                bottles[undoto].remove(bottles[undoto].size()-1);
        	}
            showAll(bottles);
        }
    }

    private void operation(ArrayList bottles[], Stack Moves, Stack BackMovesCounter) {
    	
    	try {
			System.out.print("");
    		if (From == 10 && To == 10) {
    			Undo(bottles, Moves, BackMovesCounter);
    			System.out.println("backcount =" + backcount);
    			backcount--;
                From = 7;
                To = 7;
    		} else if(From != 100 && To != 100){

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
		} catch (Exception e) {
        	System.out.println("catch");
		}	
    }
    
	class MyMouseListener implements MouseListener {
	    	
		@Override
		public void mouseClicked(MouseEvent e) {
			
		}
			
		@Override
		public void mousePressed(MouseEvent e) {
			click = (JLabel)e.getSource();
			for(int i = 0; i < maxBottles; i++) {
				if(jjb[i] == click) {
					if(count == 1) {
						jjb[postBottle].setIcon(fromBottle);
						count--;
						To = i;
						postBottle = 100;
					}
					else {
						jjb[i].setIcon(toBottle);
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
