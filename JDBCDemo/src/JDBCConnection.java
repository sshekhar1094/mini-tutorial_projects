import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnection {
	/*
	 * This class handles all the JDBC related issues of our project
	 */
	
	String dbname, username, password;
	
	
	public JDBCConnection(String dbname, String username, String password) {
		this.dbname = dbname;
		this.username = username;
		this.password = password;
	}
	
	
	void register() throws ClassNotFoundException{
		Class.forName("org.postgresql.Driver");
		System.out.println("PostgreSQL JDBC Driver Registered!");
	}
	
	
	Connection connect() throws SQLException{
		Connection connection = null;
		String dburl = "jdbc:postgresql:" + dbname;
		connection = DriverManager.getConnection(dburl, username, password);
		System.out.println("Successfully connected !!!");
		return connection;
	}
	
	
	ResultSet query(String query, Connection connection) throws SQLException{
		Statement stmt = connection.createStatement();
		ResultSet results = stmt.executeQuery(query);
		return results;
	}
	
	
	
}
