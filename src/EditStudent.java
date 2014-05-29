

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class EditStudent extends JFrame implements  DocumentListener, KeyListener
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
	JButton addButton = new JButton("Add");
	JButton okButton = new JButton("Ok");
	JButton editButton = new JButton("Edit");
	List<JTextField> fieldList = new ArrayList<JTextField>();
	String fname,lname,studentId, course,major;
	JLabel confirmation= new JLabel();
	JLabel idError= new JLabel();
    JLabel error= new JLabel();
    
  //This function initializes the Edit-Student window.
	public void enterId()
	{
		JFrame frame = new JFrame("Edit Student");
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
        editButton.setSize(60, 30);
        editButton.setLocation(220, 400);
        
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
			        		 edit(val1[0],val1[1],val1[2],val1[3]);
			        		 br.close();
			        		 br.close();
			        		 br.close();
			        		 break;
			        	 }
			        	 else
			        	 {
			        		 notEditable();
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
        editButton.setVisible(false);
        confirmation.setVisible(false);
        //jb.setVisible(false);
        
        contentPane.add(headLabel);
        contentPane.add(enterIdLabel);
        contentPane.add(enterIdField);
        contentPane.add(okButton);
        contentPane.add(editButton);
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
	public void edit(String fname, String lname, String id, String major) 
	{
		idError.setText("");
		System.out.println("id present");	
		fnameLabel.setVisible(true);
	    lnameLabel.setVisible(true);
	    idLabel.setVisible(true);	    
	    majorLabel.setVisible(true);
	    fnameField.setVisible(true);
	    lnameField.setVisible(true);
	    idField.setVisible(true);
	    idField.setEnabled(false);
	    majorField.setVisible(true);
	    editButton.setVisible(true);	   
	    confirmation.setVisible(true);
	    confirmation.setText("");
	   // jb.setVisible(true);
	    //jb.setEnabled(false);
	    
	    
	    fnameField.setText(fname);
	    lnameField.setText(lname);
	    idField.setText(id);
	   // jb.setSelectedItem(course);
	    majorField.setText(major);
	    
	    fnameField.addKeyListener(this);
        lnameField.addKeyListener(this);
        majorField.addKeyListener(this);
        fieldList.add(fnameField);
        fieldList.add(lnameField);
        fieldList.add(idField);
        fieldList.add(majorField);
        
        fnameField.getDocument().addDocumentListener(this);
        lnameField.getDocument().addDocumentListener(this);
        idField.getDocument().addDocumentListener(this);
        majorField.getDocument().addDocumentListener(this);
	    
	    editButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
			    try
				{
			    	FileWriter writer;
			    	ArrayList<String> tempFname= new ArrayList<String>();
				    ArrayList<String> tempLname= new ArrayList<String>();
				    ArrayList<String> tempId= new ArrayList<String>();
				    //ArrayList<String> tempCourse= new ArrayList<String>();
				    ArrayList<String> tempMajor= new ArrayList<String>();
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
			        	System.out.println("not in if");
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
			    		//System.out.println(tempFname.get(a)+", "+tempLname.get(a)+", "+tempId.get(a)+", "+tempCourse.get(a)+", "+tempMajor.get(a)+"\n");			    
			    	writer.append(fnameField.getText()+",");
		    		writer.append(lnameField.getText()+",");
		    		writer.append(idField.getText()+",");		    	
		    		writer.append(majorField.getText()+"\n");
		    		continue;
			    	//System.out.println(newLname+", "+temp.get(a-1)+", "+temp.get(a)+", "+temp.get(a+1));
			    	}
			    	//System.out.println("\n");
			    	//newFname=temp.get(a-2);
			    	//System.out.println(temp.get(a-2)+", "+temp.get(a-1)+", "+temp.get(a)+", "+temp.get(a+1));
			    	
			    		writer.append(tempFname.get(a)+",");
			    		writer.append(tempLname.get(a)+",");
			    		writer.append(tempId.get(a)+",");
			    		//writer.append(tempCourse.get(a)+",");
			    		writer.append(tempMajor.get(a)+"\n");
			    		
			    	
			    }
			 writer.close();
			 confirmation.setForeground(new Color(0x06543E));
			 confirmation.setText("Student details edited successfully.");
			}
			    catch(IOException e1)
			    {}
			    
				
			}
		});
	
	}
	//This function gets called when given id is not valid.
	public void notEditable()
	{
		System.out.println("id not present");
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
        editButton.setVisible(false);
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
		// Function to check if textFields are not NULL
		public void checkFields() {
			for (JTextField field : fieldList) {
				if (field.getText().trim().isEmpty()) {
					editButton.setEnabled(false);
					return;
				}
			}
			editButton.setEnabled(true);
		}


		// Function to input only alphabets.
		public void eraseVal(KeyEvent e) {
			char c = e.getKeyChar();
			if ((c >= '0') && (c <= '9')) 
			{
				e.consume();
			}
		}
	

	@Override
	public void changedUpdate(DocumentEvent e) {
		checkId();
		checkFields();
		
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		checkId();
		checkFields();
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		checkId();
		checkFields();
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		eraseVal(e);	
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		eraseVal(e);	
	}

	@Override
	public void keyTyped(KeyEvent e) {
		eraseVal(e);
		
	}
}


