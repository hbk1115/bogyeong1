package watersort;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase extends JFrame{
	
	private Connection conn;
	private Statement stmt;
	private final String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
	private final String id = "root";
	private final String pw = "k5322096";
	private PreparedStatement pstmt = null;
	private static int userLevel;
	
	public DataBase() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("DB연결완료");
			
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 오류");
		} catch (SQLException e) {
			System.out.println("DB 연결 오류");
		}

    }

	public int checkLogIn(String userName, String password) {
		try {
			String logInSql = "select id, password from user where username='" + userName + "';";
			
			ResultSet result = stmt.executeQuery(logInSql);
			
			while (result.next()) {
				if (password.equals(result.getString("password"))) {
					return result.getInt(1);
				}
			}
		} catch (SQLException e) {
			System.out.println("로그인 SQL 오류dd");
		}
		
		return 0;
	}
	
	public int checklevel(int user_id, int level) {
		try {
			String logInSql = "select level from game where user_id='" + user_id + "';";
			
			ResultSet result = stmt.executeQuery(logInSql);
			
			while (result.next()) {
				if(result.getInt(1) == level) {
					return result.getInt(1);
				}
				
			}
			
		} catch (SQLException e) {
			System.out.println("레벨 SQL 오류");
		}
		
		return 0;
	}
	
	public int getLevel(int user_id) {
		int countLevel = 0;
		try {
			String logInSql = "select level from game where user_id='" + user_id + "';";
			
			ResultSet result = stmt.executeQuery(logInSql);
			
			while (result.next()) {
				if(result.getInt(1) >= countLevel) {
					countLevel = result.getInt(1);
				}
			}
			
		} catch (SQLException e) {
			System.out.println("레벨 SQL 오류a");
		}
		
		return countLevel;
	}
	
	public int getTime(int user_id, int level) {
		int Level = level;
		int countTime = 0;
		try {
			String logInSql = "select level, time from game where user_id='" + user_id + "';";
			
			ResultSet result = stmt.executeQuery(logInSql);
			
			while (result.next()) {
				if(result.getInt(1) == Level) {
					countTime = result.getInt(2);
				}
			}
			
		} catch (SQLException e) {
			System.out.println("레벨 SQL 오류a");
		}
		
		return countTime;
	}
	
	public boolean updateResult(String sql) {
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("update SQL 오류");
			return false;
		}
		
		return true;
	}
	
	public ResultSet getResult(String sql) {
		ResultSet result = null;
		try {
			result = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("get SQL 오류");
		}
		
		return result;
	}
	
	public PreparedStatement getPstmt(String sql) {
		try {
			pstmt = conn.prepareStatement(sql);												
		}catch (SQLException e1) {
			System.out.println("getPstmt 오류");
		}
		return pstmt;
	}
}