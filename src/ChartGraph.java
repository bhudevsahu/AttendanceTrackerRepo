

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChartGraph 

{
	JComboBox courseJb;
	String student, course, course2, course3;
	JButton showTableButton = new JButton("Show Chart");
	JButton showHistoButton = new JButton("Show Histogram");
	Histogram h = new Histogram();
    String status;
    JLabel error= new JLabel();
    JFrame frameHisto = new JFrame("Histogram");
    
    //This function makes an add student window and adds a record.
    public void showContent() 
    {
		JFrame frame = new JFrame("Chart and Graph");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        
        //frameHisto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameHisto.setLayout(new BorderLayout());
        
        
        final JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);
        contentPane.setBackground(Color.white);
        contentPane.setLayout(null);
        String[] val;
        String course;
        
        ArrayList<String> val2 = new ArrayList<String>();        
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
        showTableButton.addActionListener(new ActionListener() {	//actionListener for show chart button
			
			
			public void actionPerformed(ActionEvent e) 
			{
				course3= (String)courseJb.getSelectedItem();
				//ArrayList<String> val3 = new ArrayList<String>();
				 try
		      		{
					// System.out.println("show button clicked");
		      			FileInputStream fstream = new FileInputStream(course3+"-attendance.csv");
		      	        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		      	      ArrayList<String> student= new ArrayList<String>();
		      	        ArrayList<String> dates= new ArrayList<String>();
		      	      ArrayList<String> status= new ArrayList<String>();
		      	        String strLine;	
		      	        
		      	       while ((strLine = br.readLine()) != null)
		      	        {      	        	       	        	        		
		      	    	 String[] val = strLine.split(","); 
		      	    	 System.out.println(val[0]+", "+val[1]+", "+val[2]);
		      	    	 student.add(val[0]);
		      	    	 dates.add(val[1]);
		      	    	status.add(val[2]);
		      	        	 
		      	        }
		      	       br.close();
		      	     br.close();
		      	       DisplayChart dc = new DisplayChart();
		      	       dc.showChart(student, dates, status);
		      		}
				 catch(IOException e1)
				 {
					 error.setForeground(new Color(0x06543E));
			     	error.setText("No attendance record for tis course exists");
				 }
				 			
				
			}
		});
        showHistoButton.addActionListener(new ActionListener() {	//actionListener for show histrogram button
			
			
			public void actionPerformed(ActionEvent e) 
			{
				course3= (String)courseJb.getSelectedItem();
				
				 try
		      		{
				
		      			FileInputStream fstream = new FileInputStream(course3+"-attendance.csv");
		      	        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		      	      ArrayList<String> student= new ArrayList<String>();
		      	        ArrayList<String> dates= new ArrayList<String>();
		      	      ArrayList<String> status= new ArrayList<String>();
		      	    ArrayList<Integer> noOfPresent= new ArrayList<Integer>();
		      	        String strLine;	
		      	        int countDate=0;
		      	        
		      	        int i=0;
		      	       while ((strLine = br.readLine()) != null)
		      	        {      	        	       	        	        		
		      	    	 String[] val = strLine.split(","); 
		      	    	 
		      	    	 
		      	    	 student.add(val[0]);
		      	    	if(!(dates.contains(val[1])))	    
		      	    	 {
		      	    		dates.add(val[1]);
		      	    		countDate++;
		      	    	
		      	    		
		      	    	 }
		      	    	
		      	    	status.add(val[2]);
		      	        	 
		      	        }
		      	     br.close();
		      	     br.close();
		      	   
		      	          	        	       	        	        		
		      	 
		      	    	 	
		      	    	 	for(int i1=0;i1<dates.size();i1++)
		      	    	 	{
		      	    	 		FileInputStream fstream1 = new FileInputStream(course3+"-attendance.csv");
		     		      	   BufferedReader br1 = new BufferedReader(new InputStreamReader(fstream1));
		      	    	 		int countPresent=0;
		      	    	 		while ((strLine = br1.readLine()) != null)
		 		      	       {
		      	    	 			String[] val = strLine.split(",");
		      	    	 			if(dates.get(i1).equals(val[1]) && (val[2].equals("Present")))	
			      	    	 		{
			      	    	 			countPresent++;		      	    	 			
			      	    	 				System.out.println((i1+1)+")"+countPresent);	      	    	 			
			      	    	 		}	
		 		      	       }
		      	    	 		br1.close();
		   		      	     br1.close();
		      	    	 		System.out.println(countPresent);
		      	    	 		noOfPresent.add(countPresent);	
		      	    	 	}
		      	    	 	
		      	        
		      	       
		      	       
		      	    System.out.println(countDate); 
		      	  System.out.println(dates.get(0));
		      	   System.out.println(dates.get(1));
		      	     System.out.println(noOfPresent.get(0));
		      	   System.out.println(noOfPresent.get(1));    
		      	 
		      	 h.showHistogram(noOfPresent, dates);
		      	frameHisto.add(h);
		        frameHisto.pack();
		        frameHisto.setSize(800,320);		       
		        frameHisto.setLocationRelativeTo(null);
		        frameHisto.setVisible(true);
		      	      
		      		}
				 catch(IOException e1)
				 {
					 error.setForeground(new Color(0x06543E));
				     	error.setText("No attendance record for tis course exists");
				 }
				 			
				
			}
		});
        
        //studentJb= new JComboBox(val1.toArray());
        JLabel headLabel = new JLabel("Select Course to take attendance for today");
        headLabel.setSize(250,30);
        headLabel.setLocation(140, 50);
      
        error.setSize(300,30);
        error.setLocation(120, 390);
        contentPane.add(error);
        showTableButton.setSize(120, 30);
        showTableButton.setLocation(110, 180);
        showHistoButton.setSize(130, 30);
        showHistoButton.setLocation(270, 180);
        contentPane.add(showTableButton);
        contentPane.add(showHistoButton);
        //JLabel studentLabel = new JLabel("Checkmark present students");
        
        JLabel courseLabel = new JLabel("Course:");
        courseLabel.setSize(100,100);
        courseLabel.setLocation(120, 90);
        
        
        
        courseJb.setSize(150, 20);
        courseJb.setLocation(200,130);
        contentPane.add(headLabel);
        
       
        
        contentPane.add(courseLabel);
        
        
       
        contentPane.add(courseJb);        
        frame.setContentPane(contentPane);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(500, 600);
      
       
	
         
		
	}	
    

}
