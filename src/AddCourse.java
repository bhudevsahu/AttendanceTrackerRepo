

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.BufferedReader;

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

public class AddCourse extends JFrame implements ActionListener, DocumentListener, KeyListener
{
	
	JTextField cnameField = new JTextField();
	JTextField cidField = new JTextField();
	JTextField creditField = new JTextField();	
	JButton addButton = new JButton("Add");
	
	JComboBox jb;
	List<JTextField> fieldList = new ArrayList<JTextField>();
	String cname,cid,credit;
	JLabel confirmation= new JLabel();
    JLabel error= new JLabel();

    // This function makes an add course window and adds a record.
	public void addCourse() {
		JFrame frame = new JFrame("Add Course");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);
        contentPane.setBackground(Color.white);
        contentPane.setLayout(null); 
        JLabel headLabel = new JLabel("Enter Course Details");
        headLabel.setSize(150,30);
        headLabel.setLocation(190, 50);
        JLabel cnameLabel = new JLabel("Course Name:"); 
        cnameLabel.setSize(100,100);
        cnameLabel.setLocation(100, 90);
        JLabel cidLabel = new JLabel("Course ID:");
        cidLabel.setSize(100,100);
        cidLabel.setLocation(120, 130);
        JLabel creditLabel = new JLabel("Credits:");
        creditLabel.setSize(100,100);
        creditLabel.setLocation(130, 170);
        
        confirmation.setSize(200,30);
        confirmation.setLocation(170, 420);
        error.setSize(300,30);
        error.setLocation(140, 420);
        
        
        cnameField.setSize(150,20);
        cnameField.setLocation(200, 130);
        cidField.setSize(150,20);
        cidField.setLocation(200, 170);
        creditField.setSize(150,20);
        creditField.setLocation(200, 210);
        //jb.setSize(150, 20);
        //jb.setLocation(200,350);
        
        
        addButton.setSize(60, 30);
        addButton.setLocation(220, 270);
        addButton.setEnabled(false);

        contentPane.add(headLabel);
        contentPane.add(cnameLabel);
        contentPane.add(cidLabel);
        contentPane.add(creditLabel);
           
        contentPane.add(cnameField);
        contentPane.add(cidField);
        contentPane.add(creditField);  
        contentPane.add(addButton);
        contentPane.add(confirmation);
        contentPane.add(error);
        //contentPane.add(jb);
        
        frame.setContentPane(contentPane);
       // frame.setSize(310, 125);
        fieldList.add(cnameField);
        fieldList.add(cidField);
        fieldList.add(creditField);
    
        
        cnameField.getDocument().addDocumentListener(this);
        cidField.getDocument().addDocumentListener(this);
        creditField.getDocument().addDocumentListener(this);
       
                
        creditField.addKeyListener(this);
       
        
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(500, 600);
        //frame.getContentPane().add(confirmation, BorderLayout.SOUTH);
        //frame.getContentPane().add(error, BorderLayout.SOUTH);
        
        error.setForeground(Color.red);
		//frame.getContentPane().add(panel);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//frame.setVisible(true);
		
		//frame.getContentPane().add(bottomLable, BorderLayout.SOUTH);
		//bottomLable.setForeground(Color.red);
		
		addButton.addActionListener(this);    
		
	}
	//action of add button.
	public void actionPerformed(ActionEvent e)
    {
        System.out.println("You clicked the button");
        cname = cnameField.getText();
        cid = cidField.getText();
        credit = creditField.getText();
        //course=(String) jb.getSelectedItem();        
        FileWriter writer;        
        try
		{
        	writer = new FileWriter("Course.csv", true);
			FileInputStream fstream = new FileInputStream("Course.csv");
	        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
	        String strLine;		
	        
			
	       while ((strLine = br.readLine()) != null)
	        {
	    	   
	        	String[] val = strLine.split(",");
	        	System.out.println(val[2]);
	        	System.out.println("not in if");
	        	if(val[1].equals(cidField.getText()))
	        	{
	        		System.out.println("in if");
	        		throw new FileNotFoundException();
	        	}
	        	
	        }
	       
				br.close();
				br.close();
		
				writer.append(cname+",");
				writer.append(cid+",");
				writer.append(credit+"\n");
				writer.close();
				//writer1.close();
				
			error.setText("");
			confirmation.setForeground(new Color(0x06543E));
			confirmation.setText("Course added successfully.");
		}
		catch(FileNotFoundException e1)
		{
			e1.printStackTrace();
			System.out.println("user already exists");
			error.setForeground(new Color(0x901023));
			confirmation.setText("");
			error.setText("Course with same Id already exists.");
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
			if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) 
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


