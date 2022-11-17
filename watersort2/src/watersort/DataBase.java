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
	private final String url = "jdbc:mysql://localhost:3306/watersort?serverTimezone=UTC";
	private final String id = "root";
	private final String pw = "ghtjd020709!";
	private PreparedStatement pstmt = null;
	public DataBase() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("DB����Ϸ�");
			
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC ����̹� �ε� ����");
		} catch (SQLException e) {
			System.out.println("DB ���� ����");
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
			System.out.println("�α��� SQL ����");
		}
		
		return 0;
	}
	
	public boolean updateResult(String sql) {
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("update SQL ����");
			return false;
		}
		
		return true;
	}
	
	public ResultSet getResult(String sql) {
		ResultSet result = null;
		try {
			result = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("get SQL ����");
		}
		
		return result;
	}
	public PreparedStatement getPstmt(String sql) {
		try {
			pstmt = conn.prepareStatement(sql);												
		}catch (SQLException e1) {
			System.out.println("getPstmt ����");
		}
		return pstmt;
	}
}