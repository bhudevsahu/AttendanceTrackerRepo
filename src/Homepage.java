

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Homepage 
{
	
	JButton addCourseButton = new JButton("Add Course");
	JButton editCourseButton = new JButton("Modify Course");
	JButton deleteCourseButton = new JButton("Delete Course");
	JButton addStudentButton = new JButton("Add Student");
	JButton editStudentButton = new JButton("Modify Student");
	JButton deleteStudentButton = new JButton("Delete Student");
	JButton addCSButton = new JButton("Add Student");
	JButton removeCSButton = new JButton("Remove Student");
	JButton takeAttendanceButton = new JButton("Take Attendance");
	JButton graphButton = new JButton("Chart and Histogram");
	
	
	void startUI()
	{
		JFrame frame = new JFrame("Homepage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);
        contentPane.setBackground(Color.white);
        contentPane.setLayout(null);
        String[] val = null;
        
        JLabel headLabel = new JLabel("Attendance System Homepage");
        headLabel.setSize(200,30);
        headLabel.setLocation(150, 30);
        JLabel courseLabel = new JLabel("1) Courses");
        courseLabel.setSize(100,30);
        courseLabel.setLocation(20, 100);
        JLabel studentLabel = new JLabel("2) Students");
        studentLabel.setSize(100,30);
        studentLabel.setLocation(20, 180);
        JLabel courseStudentLabel = new JLabel("3) Course-Student");
        courseStudentLabel.setSize(150,30);
        courseStudentLabel.setLocation(20, 260);
        JLabel attendanceLabel = new JLabel("4) Attendance");
        attendanceLabel.setSize(150,30);
        attendanceLabel.setLocation(20, 340);
        JLabel graphLabel = new JLabel("5) Chart and Histrogram");
        graphLabel.setSize(150,30);
        graphLabel.setLocation(20, 420);
        
        addCourseButton.setSize(120, 30);
        addCourseButton.setLocation(30, 140);
        editCourseButton.setSize(120, 30);
        editCourseButton.setLocation(180, 140);
        deleteCourseButton.setSize(120, 30);
        deleteCourseButton.setLocation(330, 140);
        addStudentButton.setSize(120, 30);
        addStudentButton.setLocation(30, 220);
        editStudentButton.setSize(120, 30);
        editStudentButton.setLocation(180, 220);
        deleteStudentButton.setSize(120, 30);
        deleteStudentButton.setLocation(330, 220);
        addCSButton.setSize(120, 30);
        addCSButton.setLocation(90, 300);
        removeCSButton.setSize(130, 30);
        removeCSButton.setLocation(270, 300);
        takeAttendanceButton.setSize(130, 30);
        takeAttendanceButton.setLocation(180, 380);
        graphButton.setSize(200, 30);
        graphButton.setLocation(140, 460);

        contentPane.add(headLabel);
        contentPane.add(courseLabel);
        contentPane.add(studentLabel);
        contentPane.add(addCourseButton);
        contentPane.add(editCourseButton);
        contentPane.add(deleteCourseButton);
        contentPane.add(addStudentButton);
        contentPane.add(editStudentButton);
        contentPane.add(deleteStudentButton);
        contentPane.add(courseStudentLabel);
        contentPane.add(addCSButton);
        contentPane.add(removeCSButton);
        contentPane.add(attendanceLabel);
        contentPane.add(takeAttendanceButton);
        contentPane.add(graphButton);
        contentPane.add(graphLabel);
        
        frame.setContentPane(contentPane);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(500, 600);
        
        addCourseButton.addActionListener(new ActionListener() {				
     			public void actionPerformed(ActionEvent arg0) 
     			{			
     				new AddCourse().addCourse();
     			}
     		});
        editCourseButton.addActionListener(new ActionListener() {				
 			public void actionPerformed(ActionEvent arg0) 
 			{			
 				new EditCourse().enterId();
 			}
 		});
        deleteCourseButton.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent arg0) 
			{			
				new DeleteCourse().enterIdToDelete();
			}
		});
        
        addStudentButton.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent arg0) 
			{			
				new AddStudent().addStudent();
			}
		});
        editStudentButton.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent arg0) 
			{			
				new EditStudent().enterId();
			}
		});
        deleteStudentButton.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent arg0) 
			{			
				new DeleteStudent().enterIdToDelete();
			}
		});
        addCSButton.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent arg0) 
			{			
				new AddCourseStudent().addCourseStudent();
			}
		});
        removeCSButton.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent arg0) 
			{			
				new RemoveCourseStudent().removeCourseStudent();
			}
		});
        takeAttendanceButton.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent arg0) 
			{			
				new TakeAttendance().takeAttendance();
			}
		});
        graphButton.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent arg0) 
			{			
				new ChartGraph().showContent();
			}
		});
	}
	public static void main(String[] args) 
	{
		
		Homepage hp= new Homepage();
		hp.startUI();

	}

}
