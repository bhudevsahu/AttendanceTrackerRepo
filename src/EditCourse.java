

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

public class EditCourse extends JFrame implements  DocumentListener, KeyListener
{
	JTextField enterIdField = new JTextField();
	JTextField cnameField = new JTextField();
	JTextField cidField = new JTextField();
	JTextField creditField = new JTextField();
	JLabel cnameLabel = new JLabel("Course Name:");
	JLabel cidLabel = new JLabel("Course ID:");
	JLabel creditLabel = new JLabel("Credits:");
	JButton okButton = new JButton("Ok");
	JButton editButton = new JButton("Edit");
	List<JTextField> fieldList = new ArrayList<JTextField>();	
	JLabel confirmation= new JLabel();
	JLabel idError= new JLabel();
    JLabel error= new JLabel();
    
  //This function initializes the Edit-Course window.
	public void enterId()
	{
		JFrame frame = new JFrame("Edit Course");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);
        contentPane.setBackground(Color.white);
        contentPane.setLayout(null);
        
        JLabel headLabel = new JLabel("Enter Course Id");
        headLabel.setSize(100,30);
        headLabel.setLocation(210, 30);
        JLabel enterIdLabel = new JLabel("Course Id:"); 
        enterIdLabel.setSize(100,100);
        enterIdLabel.setLocation(120, 50);
        enterIdField.setSize(150,20);
        enterIdField.setLocation(200, 90);
        
        enterIdField.getDocument().addDocumentListener(this);
        String[] val = null;
    
        
        idError.setSize(300,30);
        idError.setLocation(180, 170);
        confirmation.setSize(300,30);
        confirmation.setLocation(150, 420);
        editButton.setSize(60, 30);
        editButton.setLocation(220, 360);
        
        okButton.setSize(60, 30);
        okButton.setLocation(220, 130);
        okButton.setEnabled(false);
        okButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) 
			{
				String[] val = null;		        
		        try
				{
					FileInputStream fstream = new FileInputStream("Course.csv");
			        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			        String strLine;	
			       while ((strLine = br.readLine()) != null)
			        {
			        	 val = strLine.split(",");
			        	 if(enterIdField.getText().equals(val[1]))
			        	 {
			        		 edit(val[0],val[1],val[2]);
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
        
         
        cnameLabel.setSize(100,100);
        cnameLabel.setLocation(120, 190);     
        cidLabel.setSize(100,100);
        cidLabel.setLocation(120, 230);
        creditLabel.setSize(100,100);
        creditLabel.setLocation(120, 270);       
        
        
        cnameField.setSize(150,20);
        cnameField.setLocation(200, 230);
        cidField.setSize(150,20);
        cidField.setLocation(200, 270);
        creditField.setSize(150,20);
        creditField.setLocation(200, 310);
        
        
               
        cnameLabel.setVisible(false);
        cidLabel.setVisible(false);
        creditLabel.setVisible(false);       
        cnameField.setVisible(false);
        cidField.setVisible(false);
        creditField.setVisible(false);       
        editButton.setVisible(false);
        confirmation.setVisible(false);
        //jb.setVisible(false);
        
        contentPane.add(headLabel);
        contentPane.add(enterIdLabel);
        contentPane.add(enterIdField);
        contentPane.add(okButton);
        contentPane.add(editButton);
        contentPane.add(idError);
        contentPane.add(cnameLabel);
        contentPane.add(cidLabel);
        contentPane.add(creditLabel);
        contentPane.add(cnameField);
        contentPane.add(cidField);
        contentPane.add(creditField);
               
        contentPane.add(confirmation);
        
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(500, 600);
        frame.setContentPane(contentPane);
	}
	
	//This function gets called when given id is valid and the values will get edited.
	public void edit(String cname, String cid, String credit) 
	{
		idError.setText("");
		System.out.println("id present");	
		cnameLabel.setVisible(true);
	    cidLabel.setVisible(true);
	    creditLabel.setVisible(true);	    	    
	    cnameField.setVisible(true);
	    cidField.setVisible(true);
	    cidField.setEnabled(false);
	    creditField.setVisible(true);
	    
	  
	    editButton.setVisible(true);	   
	    confirmation.setVisible(true);
	    confirmation.setText("");
	   // jb.setVisible(true);
	    //jb.setEnabled(false);
	    
	    
	    cnameField.setText(cname);
	    cidField.setText(cid);
	    creditField.setText(credit);
	   // jb.setSelectedItem(course);	    
	    	    
        creditField.addKeyListener(this);        
        fieldList.add(cnameField);
        fieldList.add(cidField);
        fieldList.add(creditField);        
        
        cnameField.getDocument().addDocumentListener(this);
        cidField.getDocument().addDocumentListener(this);
        creditField.getDocument().addDocumentListener(this);        
	    
	    editButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
			    try
				{
			    	FileWriter writer;
			    	ArrayList<String> tempCname= new ArrayList<String>();
				    ArrayList<String> tempCid= new ArrayList<String>();
				    ArrayList<String> tempCredit= new ArrayList<String>();
				   	FileInputStream fstream = new FileInputStream("Course.csv");
			        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			        String strLine;			     
			       while ((strLine = br.readLine()) != null)
			        {
			        	String[] val = strLine.split(",");
			        	System.out.println(val[2]);
			        	tempCname.add(val[0]);
			        	tempCid.add(val[1]);
			        	tempCredit.add(val[2]);	        				        	
			        	System.out.println("not in if");
			         }	   
			       System.out.println("clean up");
			      
			       br.close();
			       br.close();
			       
			      File file=new File("Course.csv");
			      System.out.println(file.delete());
		   	    writer= new FileWriter("Course.csv", true);
							   
			    for(int a=0;a<tempCname.size();a++) 
			    {
			    	
			    	
			    	if(tempCid.get(a).equals(cidField.getText()))
			    	{
			    		writer.append(cnameField.getText()+",");
			    		writer.append(cidField.getText()+",");
			    		writer.append(creditField.getText()+"\n");		    	
			    		continue;
				    }			   
			    	writer.append(tempCname.get(a)+",");
			    	writer.append(tempCid.get(a)+",");
			    	writer.append(tempCredit.get(a)+"\n");
			  				    	
			    }
			 writer.close();
			 confirmation.setForeground(new Color(0x06543E));
			 confirmation.setText("Course details edited successfully.");
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
		idError.setText("Course Id doesn't exist");
		cnameLabel.setVisible(false);
        cidLabel.setVisible(false);
        creditLabel.setVisible(false);       
        
        cnameField.setVisible(false);
        cidField.setVisible(false);
        creditField.setVisible(false);              
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


		// Function to input only digits.
		public void eraseVal(KeyEvent e) {
			char c = e.getKeyChar();
			if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) 
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


