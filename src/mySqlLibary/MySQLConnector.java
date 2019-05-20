package mySqlLibary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnector {

	private static MySQLConnector instance;
	private final static String DBURL = "jdbc:mysql://localhost:3306/dawid";
	private final static String DBLOGIN = "root";
	private final static String DBPASSWORD = "";
	
	private Connection conn;
	
	private MySQLConnector() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DBURL, DBLOGIN, DBPASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("Po³¹czono z baz¹ danych!");
	}
	
	public static MySQLConnector getINSTANCE() {
		if(instance == null) {
			instance = new MySQLConnector();
		}
		return instance;
	}
	
	public Statement getStatement() {
		try {
			return conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public PreparedStatement getPreparedStatement(String sql) {
		try {
			return conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
