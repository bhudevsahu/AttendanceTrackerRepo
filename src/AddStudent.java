

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

public class AddStudent extends JFrame implements ActionListener, DocumentListener, KeyListener
{
	
	JTextField fnameField = new JTextField();
	JTextField lnameField = new JTextField();
	JTextField idField = new JTextField();
	JTextField majorField = new JTextField();
	JButton addButton = new JButton("Add");
	
	
	List<JTextField> fieldList = new ArrayList<JTextField>();
	String fname,lname,studentId, course,major;
	JLabel confirmation= new JLabel();
    JLabel error= new JLabel();

    //This function makes an add student window and adds a record.
	public void addStudent() {
		JFrame frame = new JFrame("Add Student");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);
        contentPane.setBackground(Color.white);
        contentPane.setLayout(null);
        String[] val = null;
        ArrayList<String> val2 = new ArrayList<String>();
      

        JLabel headLabel = new JLabel("Enter Student Details");
        headLabel.setSize(150,30);
        headLabel.setLocation(190, 50);
        JLabel fnameLabel = new JLabel("First Name:"); 
        fnameLabel.setSize(100,100);
        fnameLabel.setLocation(120, 90);
        JLabel lnameLabel = new JLabel("Last Name:");
        lnameLabel.setSize(100,100);
        lnameLabel.setLocation(120, 130);
        JLabel idLabel = new JLabel("Student Id:");
        idLabel.setSize(100,100);
        idLabel.setLocation(120, 170);
       // JLabel courseLabel = new JLabel("Course:");
        //courseLabel.setSize(100,100);
        //courseLabel.setLocation(120, 310);
        JLabel majorLabel = new JLabel("Major:");
        majorLabel.setSize(100,100);
        majorLabel.setLocation(120, 210);
        confirmation.setSize(200,30);
        confirmation.setLocation(170, 420);
        error.setSize(300,30);
        error.setLocation(140, 420);
        
        
        fnameField.setSize(150,20);
        fnameField.setLocation(200, 130);
        lnameField.setSize(150,20);
        lnameField.setLocation(200, 170);
        idField.setSize(150,20);
        idField.setLocation(200, 210);
        majorField.setSize(150,20);
        majorField.setLocation(200, 250);
       //jb.setSize(150, 20);
        //jb.setLocation(200,350);
        
        
        addButton.setSize(60, 30);
        addButton.setLocation(220, 300);
        addButton.setEnabled(false);

        contentPane.add(headLabel);
        contentPane.add(fnameLabel);
        contentPane.add(lnameLabel);
        contentPane.add(idLabel);
        //contentPane.add(courseLabel);
        contentPane.add(majorLabel);
        contentPane.add(fnameField);
        contentPane.add(lnameField);
        contentPane.add(idField);
        contentPane.add(majorField);
        contentPane.add(addButton);
        contentPane.add(confirmation);
        contentPane.add(error);
       // contentPane.add(jb);
        
        frame.setContentPane(contentPane);
       // frame.setSize(310, 125);
        fieldList.add(fnameField);
        fieldList.add(lnameField);
        fieldList.add(idField);
        fieldList.add(majorField);
        
        fnameField.getDocument().addDocumentListener(this);
        lnameField.getDocument().addDocumentListener(this);
        idField.getDocument().addDocumentListener(this);
        majorField.getDocument().addDocumentListener(this);
        
        fnameField.addKeyListener(this);
        lnameField.addKeyListener(this);
        majorField.addKeyListener(this);
        
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
        fname = fnameField.getText();
        lname = lnameField.getText();
        studentId = idField.getText();
        //course=(String) jb.getSelectedItem();
        major = majorField.getText();
        FileWriter writer;        
        try
		{
        	writer = new FileWriter("Students.csv", true);
			FileInputStream fstream = new FileInputStream("Students.csv");
	        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
	        String strLine;		
	        
			
	       while ((strLine = br.readLine()) != null)
	        {
	    	   
	        	String[] val = strLine.split(",");
	        	System.out.println(val[2]);
	        	System.out.println("not in if");
	        	if(val[2].equals(idField.getText()))
	        	{
	        		System.out.println("in if");
	        		throw new FileNotFoundException();
	        	}
	        	
	        }

			br.close();
			br.close();
	
	       
				
		
				writer.append(fname+",");
				writer.append(lname+",");
				writer.append(studentId+",");				
				writer.append(major+"\n");
				writer.close();
				//writer1.close();
				
			error.setText("");
			confirmation.setForeground(new Color(0x06543E));
			confirmation.setText("Student added successfully");
		}
		catch(FileNotFoundException e1)
		{
			e1.printStackTrace();
			System.out.println("user already exists");
			error.setForeground(new Color(0x901023));
			confirmation.setText("");
			error.setText("Student with same Id already exists.");
		}
        catch(IOException e1)
		{
		}		
    }
	// Function to check if textFields are not NULL
		public void checkFields() {
			for (JTextField field : fieldList) {
				if (field.getText().trim().isEmpty()) {
					addButton.setEnabled(false);
					return;
				}
			}
			addButton.setEnabled(true);
		}

		// Function to input only numeric
		public void eraseVal(KeyEvent e) {
			char c = e.getKeyChar();
			if ((c >= '0') && (c <= '9')) 
			{
				e.consume();
			}
		}
	
	public void changedUpdate(DocumentEvent e) {
		checkFields();
		
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		checkFields();
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
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


