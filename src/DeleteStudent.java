

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DeleteStudent extends JFrame implements  DocumentListener
{
	JTextField enterIdField = new JTextField();
	JTextField fnameField = new JTextField();
	JTextField lnameField = new JTextField();
	JTextField idField = new JTextField();
	JTextField majorField = new JTextField();
	JLabel fnameLabel = new JLabel("First Name:");
	JLabel lnameLabel = new JLabel("Last Name:");
	JLabel idLabel = new JLabel("Student ID:");	
	JLabel majorLabel = new JLabel("Major:");	
	JButton okButton = new JButton("Ok");
	JButton deleteButton = new JButton("Delete");
	List<JTextField> fieldList = new ArrayList<JTextField>();
	String fname,lname,studentId, course,major;
	JLabel confirmation= new JLabel();
	JLabel idError= new JLabel();
    JLabel error= new JLabel();
    
  //This function initializes the Delete-Student window.
	public void enterIdToDelete()
	{
		JFrame frame = new JFrame("Delete Student");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);
        contentPane.setBackground(Color.white);
        contentPane.setLayout(null);
        
        JLabel headLabel = new JLabel("Enter Student Id");
        headLabel.setSize(100,30);
        headLabel.setLocation(210, 30);
        JLabel enterIdLabel = new JLabel("Student Id:"); 
        enterIdLabel.setSize(100,100);
        enterIdLabel.setLocation(120, 50);
        enterIdField.setSize(150,20);
        enterIdField.setLocation(200, 90);
        
        enterIdField.getDocument().addDocumentListener(this);
        String[] val = null;
    
        
        idError.setSize(300,30);
        idError.setLocation(180, 170);
        confirmation.setSize(300,30);
        confirmation.setLocation(150, 460);
        deleteButton.setSize(70, 30);
        deleteButton.setLocation(220, 400);
        
        okButton.setSize(60, 30);
        okButton.setLocation(220, 130);
        okButton.setEnabled(false);
        okButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) 
			{
				String[] val1 = null;		        
		        try
				{
					FileInputStream fstream = new FileInputStream("Students.csv");
			        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			        String strLine;	
			       while ((strLine = br.readLine()) != null)
			        {
			        	 val1 = strLine.split(",");
			        	 //val2.add(val[2]);
			        	 if(enterIdField.getText().equals(val1[2]))
			        	 {
			        		 delete(val1[0],val1[1],val1[2],val1[3]);
			        		 br.close();
			        		 br.close();
			        		 br.close();
			        		 break;
			        	 }
			        	 else
			        	 {
			        		 canNotDelete();
			        	 }
			        	
			        
			        }
			       System.out.println("loop exited");
				}
		        catch(IOException e)
		        {
		        	
		        }
				
			}
		});
        
         
        fnameLabel.setSize(100,100);
        fnameLabel.setLocation(120, 190);     
        lnameLabel.setSize(100,100);
        lnameLabel.setLocation(120, 230);
        idLabel.setSize(100,100);
        idLabel.setLocation(120, 270);
        //courseLabel.setSize(100,100);
        //courseLabel.setLocation(120, 310);
        majorLabel.setSize(100,100);
        majorLabel.setLocation(120, 310);
        
        fnameField.setSize(150,20);
        fnameField.setLocation(200, 230);
        lnameField.setSize(150,20);
        lnameField.setLocation(200, 270);
        idField.setSize(150,20);
        idField.setLocation(200, 310);
        majorField.setSize(150,20);
        majorField.setLocation(200, 350);
        //jb.setSize(150, 20);
        //jb.setLocation(200,350);
        
               
        fnameLabel.setVisible(false);
        lnameLabel.setVisible(false);
        idLabel.setVisible(false);        
        majorLabel.setVisible(false);
        fnameField.setVisible(false);
        lnameField.setVisible(false);
        idField.setVisible(false);
        majorField.setVisible(false);
        deleteButton.setVisible(false);
        confirmation.setVisible(false);
        //jb.setVisible(false);
        
        contentPane.add(headLabel);
        contentPane.add(enterIdLabel);
        contentPane.add(enterIdField);
        contentPane.add(okButton);
        contentPane.add(deleteButton);
        contentPane.add(idError);
        contentPane.add(fnameLabel);
        contentPane.add(lnameLabel);
        contentPane.add(idLabel);
        contentPane.add(majorLabel);
        contentPane.add(fnameField);
        contentPane.add(lnameField);
        contentPane.add(idField);
        contentPane.add(majorField);
        //contentPane.add(jb);
        contentPane.add(confirmation);
        
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(500, 600);
        frame.setContentPane(contentPane);
	}
	
	//This function gets called when given id is valid and the values will get edited.
	public void delete(String fname, String lname, String id, String major) 
	{
		idError.setText("");
		//System.out.println("id present");	
		fnameLabel.setVisible(true);
	    lnameLabel.setVisible(true);
	    idLabel.setVisible(true);  
	    majorLabel.setVisible(true);
	    fnameField.setVisible(true);
	    lnameField.setVisible(true);
	    idField.setVisible(true);	   
	    majorField.setVisible(true);
	    fnameField.setEnabled(false);
	    lnameField.setEnabled(false);
	    idField.setEnabled(false);
	    majorField.setEnabled(false);
	    deleteButton.setVisible(true);
	    confirmation.setVisible(true);
	    confirmation.setText("");
	   // jb.setVisible(true);
	    //jb.setEnabled(false);
	    
	    
	    fnameField.setText(fname);
	    lnameField.setText(lname);
	    idField.setText(id);
	   // jb.setSelectedItem(course);
	    majorField.setText(major);
	    
	    deleteButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
			    try
				{
			    	FileWriter writer,writer1;
			    	ArrayList<String> tempFname= new ArrayList<String>();
				    ArrayList<String> tempLname= new ArrayList<String>();
				    ArrayList<String> tempId= new ArrayList<String>();
				    //ArrayList<String> tempCourse= new ArrayList<String>();
				    ArrayList<String> tempMajor= new ArrayList<String>();
				    
				    String newStudent=  idField.getText()+" - "+fnameField.getText()+" "+lnameField.getText();
			        System.out.println(newStudent);
				    
				    FileInputStream fstream1 = new FileInputStream("Course.csv");
			        BufferedReader br1 = new BufferedReader(new InputStreamReader(fstream1));
			        String strLine1;
			        while ((strLine1 = br1.readLine()) != null)
			        {
			        	ArrayList<String> tempStudent= new ArrayList<String>();
			        	String[] val = strLine1.split(",");
			        	String newFile = val[1]+" - "+val[0];
			        	System.out.println(newFile);
			        	FileInputStream fstream2 = new FileInputStream(newFile+".csv");
				        BufferedReader br2 = new BufferedReader(new InputStreamReader(fstream2));
				        String strLine2;
				        while ((strLine2 = br2.readLine()) != null)
				        {
				        	System.out.println(strLine2);
				        	tempStudent.add(strLine2);
				        }
				        br2.close();
					    br2.close();   		
					    
					    File file1=new File(newFile+".csv");
					      System.out.println(file1.delete());
				   	    writer1= new FileWriter(newFile+".csv", true);
				   	 for(int a=0;a<tempStudent.size();a++) 
					    {
					    	System.out.println(tempStudent.get(a)+"----");
					    	System.out.println(newStudent+"----");
					    	if(tempStudent.get(a).equals(newStudent))
					    	{			    			   
					    		System.out.println("in if condition");
				    		continue;
					    	
					    	}
					    				    		
					    		writer1.append(tempStudent.get(a)+"\n");
					    		
					    	
					    }
					 writer1.close();
			         }	
			        br1.close();
			        br1.close();
				    
					FileInputStream fstream = new FileInputStream("Students.csv");				
			        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			        
			        
			        String strLine;			     
			       while ((strLine = br.readLine()) != null)
			        {
			        	String[] val = strLine.split(",");
			        	System.out.println(val[2]);
			        	tempFname.add(val[0]);
			        	tempLname.add(val[1]);
			        	tempId.add(val[2]);	        	
			        	tempMajor.add(val[3]);
			        	//System.out.println("not in if");
			         }	   
			       System.out.println("clean up");
			       //fstream.close();
			       br.close();
			       br.close();
			       br.close();
			       
			      File file=new File("Students.csv");
			      System.out.println(file.delete());
		   	    writer= new FileWriter("Students.csv", true);
							   
			    for(int a=0;a<tempFname.size();a++) 
			    {
			    	//System.out.println(tempId.get(a));
			    	System.out.println(tempFname.get(a)+", "+tempLname.get(a)+", "+tempId.get(a)+", "+tempMajor.get(a)+"\n");
			    	if(tempId.get(a).equals(idField.getText()))
			    	{			    			   
			    	
		    		continue;
			    	
			    	}
			    	
			    	
			    		writer.append(tempFname.get(a)+",");
			    		writer.append(tempLname.get(a)+",");
			    		writer.append(tempId.get(a)+",");			    		
			    		writer.append(tempMajor.get(a)+"\n");
			    		
			    	
			    }
			 writer.close();
			 
			 
		        
		        
			 confirmation.setForeground(new Color(0x06543E));
			 confirmation.setText("Student details deleted successfully.");
			}
			    catch(IOException e1)
			    {}
			    
				
			}
		});
	
	}
	//This function gets called when given id is not valid.
	public void canNotDelete()
	{
		//System.out.println("id not present");
		idError.setForeground(new Color(0x901023));		
		idError.setText("Student Id doesn't exist");
		fnameLabel.setVisible(false);
        lnameLabel.setVisible(false);
        idLabel.setVisible(false);        
        majorLabel.setVisible(false);
        fnameField.setVisible(false);
        lnameField.setVisible(false);
        idField.setVisible(false);
        majorField.setVisible(false);
        //jb.setVisible(false);
        deleteButton.setVisible(false);
        confirmation.setVisible(false);
        
	}
	
    	

	// Function to check if id field is not null.
		public void checkId() {
			
				if (enterIdField.getText().trim().isEmpty()) {
					okButton.setEnabled(false);
					return;
				}
			
			okButton.setEnabled(true);
		}

	@Override
	public void changedUpdate(DocumentEvent e) {
		checkId();
		
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		checkId();
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		checkId();
		
	}

}


