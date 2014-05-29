


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

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class RemoveCourseStudent extends JFrame implements ActionListener
{
	JButton removeButton = new JButton("Remove");
	JButton showButton = new JButton("Show");
	JComboBox courseJb, studentJb;
	List<JTextField> fieldList = new ArrayList<JTextField>();
	String student, course, course3;
	JLabel confirmation= new JLabel();
    JLabel error= new JLabel();
    JLabel studentLabel = new JLabel("Student:");
    ComboBoxModel[] model = new ComboBoxModel[1];
    //This function makes an add student window and adds a record.
	public void removeCourseStudent() {
		JFrame frame = new JFrame("Remove Student from Course");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);
        contentPane.setBackground(Color.white);
        contentPane.setLayout(null);
        String[] val;
        String course, course2;
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
      			FileInputStream fstream = new FileInputStream(course2+".csv");
      	        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
      	        String strLine;	
      	        
      	       while ((strLine = br.readLine()) != null)
      	        {      	        	       	        	        		
      	        	 val1.add(strLine);
      	        }
      	 
      	       br.close();
      	       br.close();
      		}
              catch(IOException e)
              {
              	
              }
        studentJb= new JComboBox(val1.toArray());
        JLabel headLabel = new JLabel("Select Course and Student to remove.");
        headLabel.setSize(220,30);
        headLabel.setLocation(160, 50);
      
        
        studentLabel.setVisible(false);
        studentLabel.setSize(100,100);
        studentLabel.setLocation(115, 210);
        JLabel courseLabel = new JLabel("Course:");
        courseLabel.setSize(100,100);
        courseLabel.setLocation(120, 90);
        confirmation.setSize(300,30);
        confirmation.setLocation(110, 420);
        error.setSize(300,30);
        error.setLocation(120, 420);
        
        
        studentJb.setVisible(false);
        studentJb.setSize(150,20);
        studentJb.setLocation(195, 250);
        courseJb.setSize(150, 20);
        courseJb.setLocation(200,130);
        
        removeButton.setVisible(false);
        removeButton.setSize(80, 30);
        removeButton.setLocation(210, 300);
        showButton.setSize(80, 30);
        showButton.setLocation(210, 180);
        showButton.addActionListener(new ActionListener() 
        {
			
        	
			public void actionPerformed(ActionEvent e) 
			{
				course3= (String)courseJb.getSelectedItem();
				ArrayList<String> val3 = new ArrayList<String>();
				 try
		      		{
					// System.out.println("show button clicked");
		      			FileInputStream fstream = new FileInputStream(course3+".csv");
		      	        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		      	        String strLine;	
		      	        
		      	       while ((strLine = br.readLine()) != null)
		      	        {      	        	       	        	        		
		      	        	 val3.add(strLine);
		      	        	 //System.out.println(strLine);
		      	        }
		      	   
		      	       br.close();
		      	       br.close();
		      	       model[0]= new DefaultComboBoxModel(val3.toArray());
		      		}
		              catch(IOException e2)
		              {
		              	
		              }
				 studentJb.setVisible(true);
				 studentJb.setModel(model[0]);
		        //studentJb= new JComboBox(val3.toArray());
				 studentLabel.setVisible(true);
				 studentJb.setVisible(true);
				 removeButton.setVisible(true);
				 
				
			}
		});

        contentPane.add(headLabel);
        
        contentPane.add(studentLabel);
        
        contentPane.add(courseLabel);
        
        
        contentPane.add(studentJb);
        contentPane.add(removeButton);
        contentPane.add(confirmation);
        contentPane.add(error);
        contentPane.add(courseJb);
        contentPane.add(showButton);
        
        frame.setContentPane(contentPane);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(500, 600);
      
        error.setForeground(Color.red);
	
		removeButton.addActionListener(this);    
		
	}
	//action of add button.
	public void actionPerformed(ActionEvent e)
    {
        //System.out.println("You clicked the button");
        
        course=(String) courseJb.getSelectedItem();
        student=(String) studentJb.getSelectedItem();      
                
        FileWriter writer;
    	ArrayList<String> tempStudent= new ArrayList<String>();
        try
		{
        	//writer = new FileWriter(course+".csv", true);
			FileInputStream fstream = new FileInputStream(course+".csv");
	        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
	        String strLine;		
	       	 
	        
	       while ((strLine = br.readLine()) != null)
	        {
	    	   tempStudent.add(strLine);              	
	        }
	      
	       br.close();
	       br.close();
	       
	      File file=new File(course+".csv");
	      System.out.println(file.delete());
   	    writer= new FileWriter(course+".csv", true);
				
   	   for(int a=0;a<tempStudent.size();a++) 
	    {
	    	
	    	
	    	if(tempStudent.get(a).equals(student))
	    	{    	
	    		continue;
		    }			   
	    	
	    	writer.append(tempStudent.get(a)+"\n");
	  				    	
	    }
   	   	writer.close();
		writer.close();						
				
			error.setText("");
			confirmation.setForeground(new Color(0x06543E));
			confirmation.setText("Student removed successfully from the course.");
		}
		catch(FileNotFoundException e1)
		{
	
		}
        catch(IOException e1)
		{
		}		
    }
	
	
	
}


