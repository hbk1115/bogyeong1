package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Stack;


class WaterSort_1 extends JFrame {
		
	    private Character red;
	    private Character blue;
	    private Character green;
	    private Character pink;
	    private int maxColours;
	    private int maxBottles;
	    private int MovesMade;
	    private int To;
	    private int From;
	    private int count;
	    private int backcount;
	    private JLabel[] jb;
	    private JLabel[] jjb;
	    private JLabel[] bottlelabel;
	    private Thread th2;
	    private ArrayList<Character> bottles[];
	    private Stack<Character> colours;
	    private Stack<Character> Moves;
	    private JButton btn3;
	    private JLabel timelabel2;
	    private timer2 runnable2;
	    private int rand_bottle;
	    private int x1;
	    private int count2;
	    private char temp;
	    private int x2;
	    private int excount;
	    private int excount2;
	    private JLabel click;
	    private int undoto;
	    private int undofrom;
	
	    
		public WaterSort_1() {
			
			red = new Character('r');
			blue = new Character('b');
			green = new Character('g');
			pink = new Character('p');
			
			maxColours = 4;
			maxBottles = 6;
			MovesMade = 0;
			
			To = 7;
			From = 7;
			
			count = 0;
			backcount = 2;
			
			jb = new JLabel[30];
			jjb = new JLabel[7];
			bottlelabel = new JLabel[6];
			
			bottles = new ArrayList[6];
	        bottles[0] = new ArrayList<Character>(maxColours);
	        bottles[1] = new ArrayList<Character>(maxColours);
	        bottles[2] = new ArrayList<Character>(maxColours);
	        bottles[3] = new ArrayList<Character>(maxColours);
	        bottles[4] = new ArrayList<Character>(maxColours);
	        bottles[5] = new ArrayList<Character>(maxColours);
	        
	        colours = new Stack<>();
	        Moves = new Stack<>();
	
	        jjb[0] = new JLabel("");
	        jjb[1] = new JLabel("");
	        jjb[2] = new JLabel("");
	        jjb[3] = new JLabel("");
	        jjb[4] = new JLabel("");
	        jjb[5] = new JLabel("");
	        jjb[6] = new JLabel("back");
	        
	        bottlelabel[0] = new JLabel("");
	        bottlelabel[1] = new JLabel("");
	        bottlelabel[2] = new JLabel("");
	        bottlelabel[3] = new JLabel("");
	        bottlelabel[4] = new JLabel("");
	        bottlelabel[5] = new JLabel("");
	        
	        setTitle("WaterSort");
	        setLayout(null);
	    	setSize(700,500);
	        
	        
	        btn3 = new JButton("뒤로");
	        btn3.setLocation(500, 150);
	        btn3.setSize(100,50);
	        btn3.setVisible(true);
	        add(btn3);
	
	        btn3.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	new Main2();
	            	th2.interrupt();
	            	setVisible(false);
	            }
	        });
	
	        for(int i = 0; i < 7;i++) {
	        	jjb[i].setLocation(i*100,40);
	        	jjb[i].setSize(80,80);
	        	jjb[i].setVisible(true);
	        	add(jjb[i]);
	        	jjb[i].addMouseListener(new MyMouseListener());
	        	//jjb[i].addActionListener(this);
	        }
	        
	        timelabel2 = new JLabel();
	        
	        timelabel2.setLocation(300, 150);
	        timelabel2.setSize(100,50);
	        timelabel2.setVisible(true);
	        timelabel2.setFont(new Font("Gothic", Font.ITALIC, 20));
	
	        runnable2 = new timer2(timelabel2);
	        th2 = new Thread(runnable2);
	        add(timelabel2);
	        
	    	setLocationRelativeTo(null);
	        fillColourStack(colours);
	
	        for (int count = 0; count < 16; count++)
	        {
	            fillBottles(bottles, colours);
	        }
	
	
	        showAll(bottles);
	        th2.start();
	        operation(bottles, Moves);//이 부분이 실질적으로 실행되는 부분인데, 아까 멈춘 화면 보니까 물병들 채우고 배치까지는 된 것 같거든요
	        th2.interrupt();
		}
		
	    public void showAll(ArrayList bottles[]) {
	    	for(int i = 0; i< jb.length;i++) {
	    		if(jb[i] != null) {
	    			jb[i].setVisible(false);
	    		}
	    	}
	    	int z = 0;
	    	
	        for(int i = 0; i < 6;i++) {
	        	for(int j = 0; j < bottles[i].size(); j++) {
	        		if(bottles[i].get(j).equals(red)) {
	        			jb[z] = new JLabel("RED");
	        			jb[z].setSize(80,20);
	        			jb[z].setOpaque(true);
	        			jb[z].setBackground(Color.red);
	        			jb[z].setLocation(i*100,100-(j*20));
	        			add(jb[z]);
	        			z++;
	        		}
	        		else if(bottles[i].get(j).equals(blue)) {
	        			jb[z] = new JLabel("BLUE");
	        			jb[z].setSize(80,20);
	        			jb[z].setOpaque(true);
	        			jb[z].setBackground(Color.blue);
	        			jb[z].setLocation(i*100,100-(j*20));
	        			add(jb[z]);
	        			z++;
	        		}
	        		else if(bottles[i].get(j).equals(green)) {
	        			jb[z] = new JLabel("GREEN");
	        			jb[z].setSize(80,20);
	        			jb[z].setOpaque(true);
	        			jb[z].setBackground(Color.green);
	        			jb[z].setLocation(i*100,100-(j*20));
	        			add(jb[z]);
	        			z++;
	        		}
	        		else if(bottles[i].get(j).equals(pink)) {
	        			jb[z] = new JLabel("PINK");
	        			jb[z].setSize(80,20);
	        			jb[z].setOpaque(true);
	        			jb[z].setBackground(Color.pink);
	        			jb[z].setLocation(i*100,100-(j*20));
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
	
	    public void fillBottles(ArrayList bottles[], Stack colours) {
	        rand_bottle = generateRandom(0, maxBottles-2);
	        if (bottles[rand_bottle].size() < maxColours)
	        {
	            bottles[rand_bottle].add(colours.pop());
	        } else {
	            fillBottles(bottles, colours);
	        }
	
	    }
	
	    public void fillColourStack(Stack colours) {
	        for (int x = maxColours; x > 0; x--)
	        {
	            colours.push(red);
	            colours.push(blue);
	            colours.push(green);
	            colours.push(pink);
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
	    
	    public char peek(ArrayList bottles[], int x) {
	    	temp = ' ';
	    	x2 = x;
	        if (bottles[x2].size() > 0)
	            temp = (char) bottles[x2].get(bottles[x2].size() - 1);
	        return temp;
	    }
	
	    public boolean isValidMove(ArrayList bottles[], Stack Moves, int from, int to) {
	
	        if (bottles[from].size() == 0) {
	        	System.out.println("empty");
	            return false;
	        }
	        else if (bottles[to].size() >= maxColours) {
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
	            	excount++;
	            	System.out.println(bottles[from].size());
	            	bottles[to].add(bottles[from].get(bottles[from].size()-1));
	                bottles[from].remove(bottles[from].size()-1);
	                Moves.push(from);
	                Moves.push(to);
	
	            } while (peek(bottles, from) == peek(bottles,to));
	            if(bottles[to].size() > maxColours) {
	            	for(int i = 0; i < excount;i++) {
	            		Undo(bottles, Moves);
	            		System.out.println("undo");
	            	}
	            }
	            
	            MovesMade++;
	            System.out.println("moves : " + MovesMade );
	            return true;
	        }
	
	        else if ((!bottles[from].get(bottles[from].size()-1).equals(null) && !bottles[to].get(bottles[to].size()-1).equals(null))
	                && bottles[from].get(bottles[from].size()-1).equals(bottles[to].get(bottles[to].size()-1))) {//to, from 물병의 크기가 0이 아니고, form물병의 최상단이
	        	//to 물병의 최상단과 같을 때
	        	excount2 = 0;
	            do {
	                excount2++;
	                bottles[to].add(bottles[from].get(bottles[from].size()-1));
	                bottles[from].remove(bottles[from].size()-1);
	                Moves.push(from);
	                Moves.push(to);
	                
	            } while (peek(bottles, from) == peek(bottles,to));
	            if(bottles[to].size() > maxColours) {
	            	for(int i = 0; i < excount2;i++) {
	            		Undo(bottles, Moves);
	            		System.out.println("undo");
	            	}
	            }
	            MovesMade++;
	            System.out.println("moves : " + MovesMade );
	            return true;
	
	        } else {
	            return false;
	        }
	
	    }
	/*
	    public boolean validateInput(char val) {
	        boolean result = false;
	        switch (val) {
	        case '0':
	            result = true;
	            break;
	        case '1':
	            result = true;
	            break;
	        case '2':
	            result = true;
	            break;
	        case '3':
	            result = true;
	            break;
	        case '4':
	            result = true;
	            break;
	        case '5':
	            result = true;
	            break;
	
	        case 'b':
	            result = true;
	            break;
	        case 'B':
	            result = true;
	            break;
	        default:
	            result = false;
	            break;
	        }
	        return result;
	    }
	*/
	    void Undo(ArrayList bottles[], Stack Moves) {
	    	
	        if (Moves.peek() != null) {
	            undoto = (int) (Moves.pop());
	            undofrom = (int) (Moves.pop());
	            bottles[undofrom].add(bottles[undoto].get(bottles[undoto].size()-1));
	            bottles[undoto].remove(bottles[undoto].size()-1);
	            showAll(bottles);
	
	        }
	    }
	    
	    void operation(ArrayList bottles[], Stack Moves) {
	    	while(!Solved(bottles)) {
	    		System.out.println(" ");
	    		if (From == 10 && To == 10) {
	                Undo(bottles, Moves);
	                From = 7;
	                To = 7;
	    		}
	    		else if(From != 7 && To != 7){
	
	                if (isValidMove(bottles, Moves, From, To)) {
	                    System.out.println("move");
	                    showAll(bottles);
	                    From = 7;
	                    To = 7;
	
	                } else {
	                	System.out.println("move ex");
	                    showAll(bottles);
	                    From = 7;
	                    To = 7;
	                }
	
	            }
	    		else {
	    			
	    		}
	    		
	    	}
	    }
	/*
	    void operation(ArrayList bottles[], Stack Moves) {
	    	
	        while (!Solved(bottles)) { 
	            try 
	            {
	            	System.out.println("");
	                while(true) {
	                	if(to != '7') {
	                		break;
	                	}	
	                }
	
	                if (from == ('B') && to == ('B')) {
	                    Undo(bottles, Moves);
	                    from = '7';
	                    to = '7';
	                } else if (validateInput(from)) {
	                	while(true) {
	                    	if(from != '7')
	                    		break;
	                    }
	
	                    if (validateInput(to)) {
	                    	 To = Character.getNumericValue(to);
	                         From = Character.getNumericValue(from);
	
	                        if (isValidMove(bottles, Moves, From, To)) {
	                            System.out.println("move");
	                            showAll(bottles);
	                            to = '7';
	                            from = '7';
	
	                        } else {
	                        	System.out.println("move ex");
	                            showAll(bottles);
	                            to = '7';
	                            from = '7';
	                        }
	                    }
	
	                } else {
	
	                }
	            } catch (Exception e) {
	            	System.out.println("catch");
	                validateInput('n');
	            }
	
	        }
	    }
	    */
	    class MyMouseListener implements MouseListener{
	
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	
			@Override
			public void mousePressed(MouseEvent e) {
				click = (JLabel)e.getSource();
				if(jjb[0] == click) {
					if(count == 1) {
						count--;
						To = 0;
					}
					else {
						count++;
						From = 0;
					}
					
				}
				else if(jjb[1] == click){
					if(count == 1) {
						count--;
						To = 1;
					}
					else {
						count++;
						From = 1;
					}
				}
				else if(jjb[2] == click){
					if(count == 1) {
						count--;
						To = 2;
					}
					else {
						count++;
						From = 2;
					}
				}
				else if(jjb[3] == click){
					if(count == 1) {
						count--;
						To = 3;
					}
					else {
						count++;
						From = 3;
					}
				}
				else if(jjb[4] == click){
					if(count == 1) {
						count--;
						To = 4;
					}
					else {
						count++;
						From = 4;
					}
				}
				else if(jjb[5] == click){
					if(count == 1) {
						count--;
						To = 5;
					}
					else {
						count++;
						From = 5;
					}
				}
				else if(jjb[6] == click){
					if(backcount >= 0) {
						To = 10;
						From = 10;
						backcount--;
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
	
	
		/*
		public void actionPerformed(ActionEvent e) {
			
			if(e.getActionCommand() == "Button1") {
				if(count == 1) {
					count--;
					to = '0';
				}
				else {
					count++;
					from = '0';
				}
				
			}
			else if(e.getActionCommand() == "Button2"){
				if(count == 1) {
					count--;
					to = '1';
				}
				else {
					count++;
					from = '1';
				}
			}
			else if(e.getActionCommand() == "Button3"){
				if(count == 1) {
					count--;
					to = '2';
				}
				else {
					count++;
					from = '2';
				}
			}
			else if(e.getActionCommand() == "Button4"){
				if(count == 1) {
					count--;
					to = '3';
				}
				else {
					count++;
					from = '3';
				}
			}
			else if(e.getActionCommand() == "Button5"){
				if(count == 1) {
					count--;
					to = '4';
				}
				else {
					count++;
					from = '4';
				}
			}
			else if(e.getActionCommand() == "Button6"){
				if(count == 1) {
					count--;
					to = '5';
				}
				else {
					count++;
					from = '5';
				}
			}
			else if(e.getActionCommand() == "back"){
				if(backcount >= 0) {
					to = 'B';
					from = 'B';
					backcount--;
				}
				
			}
	
		
	}
	*/
	   
    

}


class timer2 extends JFrame implements Runnable{
	
	private JLabel timel;
	int n2;
	int n;
	
	public timer2(JLabel timel) {
		this.timel = timel;
		this.n2 = 0;
		this.n = 0;
	}
	
	public void run() {
	
		while(true) {
			timel.setText(Integer.toString(n) +  " : " + Integer.toString(n2));
			n2++;
			try {
				if(n2 == 10) {
					n2 = 0;
					n++;
				}
				Thread.sleep(100);
				
			}
			catch(InterruptedException e) {
				return;
			}
			
		}
		
	}

}
