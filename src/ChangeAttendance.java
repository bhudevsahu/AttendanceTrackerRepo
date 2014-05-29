


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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ChangeAttendance extends JFrame implements ActionListener
{
	
	
	JComboBox courseJb, studentJb;
	List<JTextField> fieldList = new ArrayList<JTextField>();
	String student, course, course2, course3;
	JLabel confirmation= new JLabel();
    JLabel error= new JLabel();
    JButton showButton = new JButton("Show");
    JButton presentButton = new JButton("Present");
    JButton absentButton = new JButton("Absent");
    ComboBoxModel[] model = new ComboBoxModel[1];
    JLabel studentLabel = new JLabel("Students:");
    DateFormat dateFormat = new SimpleDateFormat("MMM-dd");
    Date date = new Date();
    String dateAtt= dateFormat.format(date);
    String status;
    //This function makes an add student window and adds a record.
    
	public void changeAttendance() {
		JFrame frame = new JFrame("Change Attendance");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        final JPanel contentPane = new JPanel();
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
        course2= (String)courseJb.getSelectedItem();
        try
      		{
      			FileInputStream fstream = new FileInputStream(course2+"-attendance.csv");
      	        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
      	        String strLine;	
      	        String[] value;
      	       while ((strLine = br.readLine()) != null)
      	        {      	        	       	        	        		
      	        	value = strLine.split(",");
      	    	   val1.add(value[0]);
      	        }
      	 
      	       br.close();
      	       br.close();
      		}
              catch(IOException e)
              {
              	//System.out.println("file doesnt exist");
              }
        
        studentJb= new JComboBox(val1.toArray());
        
        showButton.addActionListener(new ActionListener() 
        {
			
        	
			public void actionPerformed(ActionEvent e) 
			{
				studentJb.setEnabled(true);
				String[] nothing={" "};
				course3= (String)courseJb.getSelectedItem();
				ArrayList<String> val3 = new ArrayList<String>();
				 try
		      		{
					// System.out.println("show button clicked");
		      			FileInputStream fstream = new FileInputStream(course3+"-attendance.csv");
		      	        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		      	        String strLine;	
		      	      String[] value;
		      	       while ((strLine = br.readLine()) != null)
		      	        {      	        	       	        	        		
		      	    	 value = strLine.split(",");
		      	    	 System.out.println("hello");
		      	    	 if(value[1].equals(dateAtt))
		      	    	 {
		      	    		 System.out.println("hello if");
		      	    	   val3.add(value[0]);
		      	    	 }
		      	        	 //System.out.println(strLine);
		      	        }
		      	   
		      	       br.close();
		      	       br.close();
		      	       model[0]= new DefaultComboBoxModel(val3.toArray());
		      		}
		              catch(IOException e2)
		              {
		            	  System.out.println("file doesnt exist");
		            	  studentJb.setEnabled(false);
		            	  error.setForeground(new Color(0x901023));
					     	error.setText("Attendance record doesn't exist for this course");
		              }
				 studentJb.setVisible(true);
				 studentJb.setModel(model[0]);
		        //studentJb= new JComboBox(val3.toArray());
				 studentLabel.setVisible(true);
				 studentJb.setVisible(true);
				 absentButton.setVisible(true);
				 presentButton.setVisible(true);
				 
				
			}
		});
        presentButton.addActionListener(new ActionListener() 
        {
			public void actionPerformed(ActionEvent arg0) 
			{
				String course1 = (String)courseJb.getSelectedItem();
		        try
		      		{		
		        		FileWriter writer;
					    System.out.println(course1);
		        		FileInputStream fstream = new FileInputStream(course1+"-attendance.csv");
		        		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		        		ArrayList<String> tempStudent= new ArrayList<String>();
					    ArrayList<String> tempDate= new ArrayList<String>();
					    ArrayList<String> tempStatus= new ArrayList<String>();
		        		String strLine;		        				        	
		        		while ((strLine = br.readLine()) != null)
				        {
				        	String[] val = strLine.split(",");
				        	//System.out.println(val[0]);				        	
				        	tempStudent.add(val[0]);
				        	tempDate.add(val[1]);
				        	tempStatus.add(val[2]);	  		        	      				        					       
				         }	 
		        				        		
		        		br.close();
		        		br.close();
		        		
		        		
		        		File file=new File(course1+"-attendance.csv");
					    System.out.println(file.delete());
		        		writer= new FileWriter(course1+"-attendance.csv",true);
		        		for(int a=0;a<tempStudent.size();a++) 
		        		{
		        			if((tempStudent.get(a).equals((String)studentJb.getSelectedItem())) && (tempDate.get(a).equals(dateAtt)))
					    	{
		        				System.out.println("match found");
		        				writer.append(studentJb.getSelectedItem()+",");
				    	  		writer.append(dateAtt+",");
				    	  		writer.append("Present"+"\n");		    	
					    		continue;
						    }			   
					    	writer.append(tempStudent.get(a)+",");
					    	writer.append(tempDate.get(a)+",");
					    	writer.append(tempStatus.get(a)+"\n");
		        		}
		        		writer.close();   
					    error.setText("");
						confirmation.setForeground(new Color(0x06543E));
						confirmation.setText("Student is marked as present.");
		      		}
		     	 catch(IOException e)
		         {
		     				     		
		         }
		        
			}
		});
        
        absentButton.addActionListener(new ActionListener() 
        {
			
			
        	public void actionPerformed(ActionEvent arg0) 
			{
				String course1 = (String)courseJb.getSelectedItem();
		        try
		      		{		
		        		FileWriter writer;
					    System.out.println(course1);
		        		FileInputStream fstream = new FileInputStream(course1+"-attendance.csv");
		        		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		        		ArrayList<String> tempStudent= new ArrayList<String>();
					    ArrayList<String> tempDate= new ArrayList<String>();
					    ArrayList<String> tempStatus= new ArrayList<String>();
		        		String strLine;		        				        	
		        		while ((strLine = br.readLine()) != null)
				        {
				        	String[] val = strLine.split(",");
				        	//System.out.println(val[0]);				        	
				        	tempStudent.add(val[0]);
				        	tempDate.add(val[1]);
				        	tempStatus.add(val[2]);	  		        	      				        					       
				         }	 
		        				        		
		        		br.close();
		        		br.close();
		        		
		        		
		        		File file=new File(course1+"-attendance.csv");
					    System.out.println(file.delete());
		        		writer= new FileWriter(course1+"-attendance.csv",true);
		        		for(int a=0;a<tempStudent.size();a++) 
		        		{
		        			if((tempStudent.get(a).equals((String)studentJb.getSelectedItem())) && (tempDate.get(a).equals(dateAtt)))
					    	{
		        				System.out.println("match found");
		        				writer.append(studentJb.getSelectedItem()+",");
				    	  		writer.append(dateAtt+",");
				    	  		writer.append("Absent"+"\n");		    	
					    		continue;
						    }			   
					    	writer.append(tempStudent.get(a)+",");
					    	writer.append(tempDate.get(a)+",");
					    	writer.append(tempStatus.get(a)+"\n");
		        		}
		        		writer.close();   
					    error.setText("");
						confirmation.setForeground(new Color(0x06543E));
						confirmation.setText("Student is marked as absent.");
		      		}
		     	 catch(IOException e)
		         {
		     				     		
		         }
		        
			}
		});
        //studentJb= new JComboBox(val1.toArray());
        JLabel headLabel = new JLabel("Select Course to take change the attendance for today.");
        headLabel.setSize(350,30);
        headLabel.setLocation(100, 50);
      
      
        showButton.setSize(80, 30);
        showButton.setLocation(210, 180);
        contentPane.add(showButton);
        //JLabel studentLabel = new JLabel("Checkmark present students");
        studentLabel.setVisible(false);
        studentLabel.setSize(100,100);
        studentLabel.setLocation(115, 210);
        JLabel courseLabel = new JLabel("Course:");
        courseLabel.setSize(100,100);
        courseLabel.setLocation(120, 90);
        confirmation.setSize(300,30);
        confirmation.setLocation(170, 390);
        error.setSize(300,30);
        error.setLocation(120, 390);
        
        studentJb.setVisible(false);
        studentJb.setSize(150,20);
        studentJb.setLocation(195, 250);
        courseJb.setSize(150, 20);
        courseJb.setLocation(200,130);
        
        presentButton.setVisible(false);
        presentButton.setSize(80, 30);
        presentButton.setLocation(155, 300);
        absentButton.setVisible(false);
        absentButton.setSize(80, 30);
        absentButton.setLocation(280, 300);      

        contentPane.add(headLabel);
        
        contentPane.add(studentLabel);
        
        contentPane.add(courseLabel);
        
        
        contentPane.add(studentJb);
        contentPane.add(presentButton);
        contentPane.add(absentButton);
        contentPane.add(confirmation);
        contentPane.add(error);
        contentPane.add(courseJb);
        
        frame.setContentPane(contentPane);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(500, 600);
      
        error.setForeground(Color.red);
	
         
		
	}

	
	//action of add button.
	public void actionPerformed(ActionEvent e)
    {
        System.out.println("You clicked the button");
        
        course=(String) courseJb.getSelectedItem();
              
        
        FileWriter writer;        
        try
		{
        	
			FileInputStream fstream = new FileInputStream(course+".csv");
	        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
	        String strLine;		
	        //File file= new File(course+".csv");	        
	        
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
				
			
		}
		catch(FileNotFoundException e1)
		{
	
			System.out.println("user already exists");
			
		}
        catch(IOException e1)
		{
		}		
    }
	

}


