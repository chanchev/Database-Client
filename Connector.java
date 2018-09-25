package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.sql.ResultSet;
/**
 * 	Name: Chanchev M
 *	Date: July 28, 2018
 *	Purpose: Connection to SQL server
 */


public class Connector {
	Connection conn;
	private String db_url, db_name, db_user, db_pass, db_host, db_port, db_driver;
	Statement st;
	
	//Connector Constructor
	//takes the inputed properties and password as arguments
	public Connector(Properties props, String pass) {
		
		db_name=props.getProperty("db_name");
		db_user=props.getProperty("db_user","root");
		db_pass =pass;
		db_host= props.getProperty("db_host");
		db_port=props.getProperty("db_port");
		db_driver = "com.mysql.cj.jdbc.Driver";
		db_url = "jdbc:mysql://"+db_host+":"+db_port+"/"+db_name+"?serverTimezone=UTC";
		
		System.out.println(db_url);
	}
	
	//Creates connection to the database
	public boolean connect() {
		
		try {
			Class.forName(db_driver);
			conn= DriverManager.getConnection(db_url,  db_user,  db_pass);
			st= conn.createStatement();//refers to database
			
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			if(conn==null) {
				return false;
			}
		}
		
		System.out.println("Connected to the database");
		return true;
	}

	public ResultSet executeQuery(String query) throws SQLException {
		// TODO Auto-generated method stub
		return st.executeQuery(query);
	}
}
