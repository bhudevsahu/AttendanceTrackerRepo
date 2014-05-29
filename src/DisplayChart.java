

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DisplayChart{

	public void showChart(ArrayList<String> student, ArrayList<String> date, ArrayList<String> status)
	
	{

		JFrame frame = new JFrame();
		frame.setLocation(100, 300);
		JPanel panel = new JPanel();
		
		
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		   frame.setResizable(true);
	        frame.setSize(500, 400);
		frame.setVisible(true);

		// Table column name are defined here
		String[] columnNames = { "ID- Student Name", "Date", "Attendance"};

		// Creation of table using JTable and scroll is used
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		JTable table = new JTable();
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		// Computation of values to inserted into the table
		for (int i = 0; i < student.size(); i++) 
		{
			String stu= student.get(i);
			String dateVal= date.get(i);
			String attendance = status.get(i);
			
			model.addRow(new Object[] { stu, dateVal, attendance });
		}
		frame.getContentPane().add(scroll);
	
	}
	
}
