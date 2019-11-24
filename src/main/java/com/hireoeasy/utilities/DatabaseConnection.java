package com.hireoeasy.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	Connection con = null;
	private final String url = "jdbc:mysql://localhost:3306/hireoeasydb";
	private final String username = "root";
	private final String password = "root";
	
	public Connection getConnection() {
		try {
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Connected!!");
			return con;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
