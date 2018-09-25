package database;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 	Name: Chanchev M
 *	Date: July 28, 2018
 *	Purpose: Enter information to access database
 */

public class ConnectorDialog extends JDialog implements ActionListener{
	
	Properties dbProperties;
	JTextField host = new JTextField();
	JLabel hostLabel= new JLabel("Host");
	
	JTextField port = new JTextField();
	JLabel portLabel= new JLabel("Port");
	
	JTextField dbName = new JTextField();
	JLabel dbNameLabel= new JLabel("DB Name");
	
	JTextField user = new JTextField();
	JLabel userLabel= new JLabel("User");
	 
	JTextField pass = new JTextField();
	JLabel passLabel= new JLabel("Password");
	
	JButton submit = new JButton("submit");
	JButton cancel= new JButton("cancel");
	
	boolean isCancelled= false;
	
	//Connector dialog constructor utilizes JDialog
	public ConnectorDialog(JFrame owner, String title, Properties p) {
		super(owner, title,true);
		
		setSize(400,400);
		dbProperties =p;
		
		//call actions
		submit.addActionListener(this);
		cancel.addActionListener(this);
		
		JPanel dataPanel = new JPanel(new GridLayout(5,2));
		
		//addition of components to panel
		dataPanel.add(hostLabel);
		dataPanel.add(host);
		
		dataPanel.add(portLabel);
		dataPanel.add(port);
		
		dataPanel.add(dbNameLabel);
		dataPanel.add(dbName);
		
		dataPanel.add(userLabel);
		dataPanel.add(user);
		
		dataPanel.add(passLabel);
		dataPanel.add(pass);
		
		JPanel buttonPanel= new JPanel();
		buttonPanel.add(submit);
		buttonPanel.add(cancel);
		add(dataPanel,BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH); 
	}

	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cancel) {
			this.isCancelled=true;
		}
		dispose();
	}
	
	//returns the properties the user enters
	public Properties getProps() {
		dbProperties.setProperty("db_name", dbName.getText());
		dbProperties.setProperty("db_host", host.getText());
		dbProperties.setProperty("db_port", port.getText());
		dbProperties.setProperty("user_name", user.getText());
		return dbProperties;
		
	}

}
