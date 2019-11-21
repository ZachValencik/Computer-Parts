package finalProject;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class DBAbstract {
	private Connection connection;
	private int numQueries;
	private String DBName; 
	private String PWFilePath;
	private String PassW;
	private String UserId;
	public DBAbstract( String DBName, String PWFilePath,String id ){
		this.DBName = DBName;
		this.PWFilePath = PWFilePath;
		this.setPW( PWFilePath );
		UserId=id;
		this.setConnect();
	}
	
	private void setConnect(){
	      try {
	    	String dbInfo = "jdbc:mysql://" + this.DBName;
			connection = DriverManager.getConnection
					   ( dbInfo, this.UserId, this.PassW ); // make a way to get user id from main class.
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    		  
	}
	
	public ResultSet doQuery( String SQL ) throws SQLException {
	    Statement statement = connection.createStatement();

	    ResultSet resultSet = statement.executeQuery( SQL );
	    return resultSet;
	}
	public  void setPW( String fName ) {
		  System.out.println(System.getProperty( fName ));
	        File file = new File( fName );
	        try {
	            Scanner scnr = new Scanner(file);
	            int lineNumber = 1;
	            this.UserId = scnr.nextLine();
	             this.PassW = scnr.nextLine();
      
	        } catch (Exception e) {
	       
	        }      
	}

	public Connection getConnection() {
		return connection;
	}

	
}
