package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
	
	private final String jdbc 	  = "jdbc:mysql://localhost/dar";
	private final String userName = "root";
	private final String password = "";
	
	Connection conn;
	
	public DataBase() {
		this.conn = null;
	}
	
	public void connectDB() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(jdbc, userName, password);
			System.out.println("Success!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
		
	public void disconnectDB() throws SQLException {
		if(conn != null) {
			conn.close();
		}
	}

	public String getJdbc() {
		return jdbc;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
	
	
}
