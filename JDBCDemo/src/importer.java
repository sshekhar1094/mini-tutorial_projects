import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;


public class importer {
	/**
	 * This class gives a demo on how to use JDBC.
	 * The JDBCConnection file contains functions for dealing with the database (register, connect, query)
	 * This file reads the credentials from a properties file and then logins into the db and does the execution
	 * @param args
	 */
	public static void main(String[] args) {
		importer obj = new importer();
		
		JDBCConnection jdbc = obj.setUpDb();
		String query = "SELECT * FROM test";
		ResultSet results = obj.getResults(jdbc, query);
		obj.printResults(results);
	}
	
	
	/**
	 * Sets up the JDBCConnection constructor using the credentials from config.properties file
	 * @return new JDBCConenction() 
	 */
	private JDBCConnection setUpDb() {
		importer obj = new importer();
		HashMap<String, String> properties = new HashMap<>();
		properties = obj.readProperties("config.properties");
		JDBCConnection jdbc = new JDBCConnection(properties.get("database"), 
												 properties.get("dbuser"),
												 properties.get("dbpassword"));
		return jdbc;
	}
	
	
	/**
	 * Registers, conencts and then queries the database
	 * @param jdbc object containg references to the db, username and password
	 * @return query results
	 */
	private ResultSet getResults(JDBCConnection jdbc, String query) {		
		ResultSet results = null;
		try {
			jdbc.register();
			Connection connection = jdbc.connect();
			results = jdbc.query(query, connection);
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}	
		return results; 
	}
	
	
	/**
	 * Print the query results
	 * @param results 
	 */
	private void printResults(ResultSet results) {
		try {
			ResultSetMetaData rsmd = results.getMetaData();
			while(results.next()) {
				for(int i=0; i<rsmd.getColumnCount(); i++)
					System.out.print(results.getArray(i+1) + " ");
				System.out.println("");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * reads a properties file to get project specific configuration properties
	 * @param filename = name of properties file
	 * @return = a dictionary of key-value pairs
	 */
	private HashMap<String, String> readProperties(String filename) {
		Properties prop = new Properties();
		InputStream input = null;
		HashMap<String, String> properties = new HashMap<>();
		
		try {
			input = getClass().getClassLoader().getResourceAsStream(filename);
			prop.load(input);
			Enumeration<?> e = prop.propertyNames();
			
			while(e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = prop.getProperty(key);
				properties.put(key, value);
			}
			
			input.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return properties;
	}

}
