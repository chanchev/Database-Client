package database;

import java.util.Properties;

import javax.swing.JFrame;
/**
 * 
 *  Name: Chanchev M
 *  Date: July 28, 2018
 *	Purpose: Initial display frame for the database
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame main = new JFrame("Database Client");
		//allows for client interface to open after input info interface is closed
		Properties databaseP = new Properties();
		
		ConnectorDialog inputDBInfo = new ConnectorDialog(main,"Database Info", databaseP );//creates the connector dialog
		inputDBInfo.setVisible(true);
		
		//if cancelled button is pressed close the window
		if(inputDBInfo.isCancelled) {
			System.exit(1);
		}
		//Creates the connector
		Connector con = new Connector(inputDBInfo.getProps(),inputDBInfo.pass.getText());
		if (!con.connect()) {
			System.exit(1);
		}
		
		//Creating the database panel passing in the connection
		DatabasePanel dbPanel= new DatabasePanel(con);
		main.add(dbPanel);
		main.setSize(800,600);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setVisible(true);
	}

}
