package watersort;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

class Counter extends JFrame implements Runnable {
	
	private Clear clear;
	private JLabel timer;
	private int millisecond;
	private int second;
	private int level;
	private int moveCnt;
	private Thread thread;
	private WaterSortGame waterSortGame;
	
	public Counter(JLabel timer, Thread thread, int level, int moveCnt) {
		waterSortGame = new WaterSortGame();
		clear = new Clear();
		this.timer = timer;
		this.millisecond = 0;
		this.second = 0;
		this.thread = thread;
		this.level = level;
		this.moveCnt = moveCnt;
	}
	
	public void run() {
	
		while(true) {
			try {
				++millisecond;
				if(millisecond == 10) {
					millisecond = 0;
					++second;
				}
//    			second = n;
//        		millisecond = n2;
				timer.setText(Integer.toString(second) +  " : " + Integer.toString(millisecond));
				
				waterSortGame.operation();
				if(waterSortGame.isSolved()) {
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
					
					new Result(level, moveCnt, second, millisecond);
					
		        	//thread.interrupt();
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
		
//		setVisible(false);
//		new Rank();	
	}
	
	private void moveRankPage() {
    	thread.interrupt();
    	this.setVisible(false);
    	new Rank();
    }
}
