package util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBCConnection {

	/*
	 * We are going to maintain and observe a single Connection
	 * Object within this class. if no connection exists,
	 * we will create one and return it. If a connection does exist,
	 * just return it.
	 */
	
	private static Connection conn = null;
	
	public static Connection getConnection()
	{
		try 
		{
			if(conn == null) 
			{
				//Make a new connection
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				//To establish a connection we need 3 credentials
				//url (endpoint), username, password
				Properties props = new Properties();
				FileInputStream input = new FileInputStream(JDBCConnection.class.getClassLoader().getResource("connection.properties").getFile());
				props.load(input);
				
				String url= props.getProperty("url");
				String username = props.getProperty("username");
				String password = props.getProperty("password");
				
				//Establish our connection.
				conn = DriverManager.getConnection(url, username, password);
				return conn;
			}
			else
			{
				//Return the connection that already exists.
				return conn;
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
