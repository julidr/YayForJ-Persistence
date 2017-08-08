package co.edu.usa.adf.YayForJ_Persistence.logic;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**Clase que se encarga de manejar la conexión con JDBC y la base de datos
 * @author Juliana Diaz
 * @version 1.0
 * */

public class YayPersistence {
	
	private static Connection connection= null;
    final static Logger logger = Logger.getLogger(YayPersistence.class);
	
	public YayPersistence(String database, String user, String password){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection= DriverManager.getConnection(database, user, password);
			if(connection!=null){
			    logger.info("You are connected");
			}
			else{
			    logger.error("Sorry, you failed to make connection!");
			}
		} catch (ClassNotFoundException e) {
		    logger.error("Your JDBC Driver is missing: " + e);
			return;
		} catch (SQLException e) {
		    logger.error("Connection failed! " + e);
		}
	}
	
	public static void closeConnection(){
		try {
			connection.close();
			logger.info("Bye Bye...Closing Connection");
		} catch (SQLException e) {
		    logger.error("ups! we couldn't close the connection: " + e);
		}
	}
	
	public static Connection getConnection(){
		return connection;
	}
	
}
