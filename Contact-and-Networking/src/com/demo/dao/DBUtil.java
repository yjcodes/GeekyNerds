package com.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	static Connection conn=null;
	public static Connection getMyConnection() {
		if(conn==null) {
			try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "\"jdbc:mysql://localhost:3306/hsbctraining\", \"root\", \"\"";
	        String user = "root";
	        String password = "";
	        
			conn = DriverManager.getConnection(url, user, password);
			if (conn != null) {
		           System.out.println("Connected to database ");
		    }
			} catch (SQLException | ClassNotFoundException e) {
				System.out.println("Connection not done derby ");
				e.printStackTrace();
			}
			
		}
		return conn;
	}
	
	public static void closeMyConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
