package com.pace.library.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//helper class to handle dataconnection in mysql
public class DbConnector {
	private static Connection connection=null;
	//opening coonection with mysql database
	public static Connection createConnection() throws ClassNotFoundException,SQLException{
		String url,userId,passWord;
		url="jdbc:mysql://localhost:3306/library";
		userId="root";
		passWord="Ashok@123";
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection=DriverManager.getConnection(url,userId,passWord);
		
			
		return connection;
	}
	// Closing connection
	public static void closeConnection() throws SQLException{
		connection.close();
	}

}
