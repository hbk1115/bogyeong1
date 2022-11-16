package watersort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase extends JFrame{
	
	private Connection conn;
	private Statement stmt;
	private final String url = "jdbc:mysql://localhost:3306/watersort?serverTimezone=UTC";
	private final String id = "root";
	private final String pw = "angel21124";
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

	public boolean checkLogIn(String userName, String password) {
		try {
			String logInSql = "select password from user where username='" + userName + "';";
			
			ResultSet result = stmt.executeQuery(logInSql);
			
			while (result.next()) {
				if (password.equals(result.getString("password"))) {
					return true;
				}
			}
		} catch (SQLException e) {
			System.out.println("로그인 SQL 오류");
		}
		
		return false;
	}
	
	public ResultSet getResult(String sql) {
		ResultSet result = null;
		try {
			result = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("SQL 오류");
		}
		
		return result;
	}
}
