package co.edu.usa.adf.YayForJ_Persistence.logic;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class YayPersistence {
	
	private static Connection connection= null;
	
	public YayPersistence(String database, String user, String password){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection= DriverManager.getConnection(database, user, password);
			if(connection!=null){
				System.out.println("You are connected");
			}
			else{
				System.out.println("Sorry, you failed to make connection!");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Your JDBC Driver is missing");
			e.printStackTrace();
			return;
		} catch (SQLException e) {
			System.out.println("Connection failed!");
			e.printStackTrace();
		}
	}
	
	public static void closeConnection(){
		try {
			connection.close();
			System.out.println("Bye Bye");
		} catch (SQLException e) {
			System.out.println("ups! we couldn't close the connection");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		return connection;
	}
	
}
