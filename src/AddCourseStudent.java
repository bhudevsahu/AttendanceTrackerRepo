


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AddCourseStudent extends JFrame implements ActionListener
{
	JButton addButton = new JButton("Add");
	
	JComboBox courseJb, studentJb;
	List<JTextField> fieldList = new ArrayList<JTextField>();
	String student, course;
	JLabel confirmation= new JLabel();
    JLabel error= new JLabel();

    //This function makes an add student window and adds a record.
	public void addCourseStudent() {
		JFrame frame = new JFrame("Add Student to Course");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);
        contentPane.setBackground(Color.white);
        contentPane.setLayout(null);
        String[] val;
        String course;
        String student;
        ArrayList<String> val2 = new ArrayList<String>();
        ArrayList<String> val1 = new ArrayList<String>();
        try
		{
			FileInputStream fstream = new FileInputStream("Course.csv");
	        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
	        String strLine;	
	        
	       while ((strLine = br.readLine()) != null)
	        {
	        	 val = strLine.split(",");
	        	 course= val[1]+" - "+val[0];	        			 
	        	 val2.add(course);
	        }
	       br.close();
	       br.close();
		}
        catch(IOException e)
        {
        	
        }
        courseJb= new JComboBox(val2.toArray());
        try
      		{
      			FileInputStream fstream = new FileInputStream("Students.csv");
      	        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
      	        String strLine;	
      	        
      	       while ((strLine = br.readLine()) != null)
      	        {
      	        	 val = strLine.split(",");
      	        	 student= val[2]+" - "+val[0]+" "+val[1];	        			 
      	        	 val1.add(student);
      	        }
      	       br.close();
      	       br.close();
      		}
              catch(IOException e)
              {
              	
              }
        studentJb= new JComboBox(val1.toArray());
        JLabel headLabel = new JLabel("Select Course and Student to add.");
        headLabel.setSize(200,30);
        headLabel.setLocation(170, 50);
      
        JLabel studentLabel = new JLabel("Student:");
        studentLabel.setSize(100,100);
        studentLabel.setLocation(120, 160);
        JLabel courseLabel = new JLabel("Course:");
        courseLabel.setSize(100,100);
        courseLabel.setLocation(120, 90);
        confirmation.setSize(300,30);
        confirmation.setLocation(130, 420);
        error.setSize(300,30);
        error.setLocation(120, 420);
        
        
       
        studentJb.setSize(150,20);
        studentJb.setLocation(200, 200);
        courseJb.setSize(150, 20);
        courseJb.setLocation(200,130);
        
        
        addButton.setSize(60, 30);
        addButton.setLocation(220, 280);      

        contentPane.add(headLabel);
        
        contentPane.add(studentLabel);
        
        contentPane.add(courseLabel);
        
        
        contentPane.add(studentJb);
        contentPane.add(addButton);
        contentPane.add(confirmation);
        contentPane.add(error);
        contentPane.add(courseJb);
        
        frame.setContentPane(contentPane);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(500, 600);
      
        error.setForeground(Color.red);
	
		addButton.addActionListener(this);    
		
	}
	//action of add button.
	public void actionPerformed(ActionEvent e)
    {
        System.out.println("You clicked the button");
        
        course=(String) courseJb.getSelectedItem();
        student=(String) studentJb.getSelectedItem();      
        
        FileWriter writer;        
        try
		{
        	writer = new FileWriter(course+".csv", true);
			FileInputStream fstream = new FileInputStream(course+".csv");
	        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
	        String strLine;		
	        //File file= new File(course+".csv");	        
	        writer.close();
	       while ((strLine = br.readLine()) != null)
	        {
	    	   
	        	String[] val = strLine.split(",");
	        	System.out.println(val[0]);
	        	System.out.println("not in if");
	        	
	        	if(val[0].equals(student))
	        	{
	        		System.out.println("in if");
	        		throw new FileNotFoundException();
	        	}
	        	
	        }

			br.close();
			br.close();
						
			  writer = new FileWriter(course+".csv", true);
				writer.append(student+"\n");				
				writer.close();
				writer.close();						
				
			error.setText("");
			confirmation.setForeground(new Color(0x06543E));
			confirmation.setText("Student added successfully to the course.");
		}
		catch(FileNotFoundException e1)
		{
	
			System.out.println("user already exists");
			error.setForeground(new Color(0x901023));
			confirmation.setText("");
			error.setText("Student is already registered for the course.");
		}
        catch(IOException e1)
		{
		}		
    }
	

}


