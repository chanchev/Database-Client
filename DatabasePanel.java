package database;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
/**
 * 
 * Name: Chanchev M
 *	Date: July 28, 2018
 *	Purpose: Interface of the database client
 */


public class DatabasePanel extends JPanel {
JTextField sqlIn = new JTextField();
	
	JButton executeBTN = new JButton("execute");
	
	JTable table = new JTable();
	DefaultTableModel model= (DefaultTableModel) table.getModel();
	Connector conn;

	public DatabasePanel(Connector conn) {
		this.conn=conn;
		setLayout(new GridLayout(3,1));
		add(sqlIn);

		sqlIn.setMinimumSize(new Dimension(300,20));
		executeBTN.addActionListener(this::execute);
		add(executeBTN);
		JScrollPane tableScrollPane= new JScrollPane(table);
		add(tableScrollPane);
	}
	private void resetTable() {
		model.setColumnCount(0);
		model.setRowCount(0);
	}
	private void execute(ActionEvent e) {
		resetTable();
		
		String query = sqlIn.getText();
		
		
		try {
			ResultSet resultSet = conn.executeQuery(query);
			ResultSetMetaData data = resultSet.getMetaData();
			
			for (int x=1; x<=data.getColumnCount();x++) {
				model.addColumn(data.getColumnName(x));
			}
			while(resultSet.next()) {
				String [] gottenData = new String [data.getColumnCount()];
				for (int x=0; x<data.getColumnCount();x++) {
					gottenData[x]=resultSet.getString(x+1);
				}
				model.addRow(gottenData);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	}
