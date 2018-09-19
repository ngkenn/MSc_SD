import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;




public class dbGUI extends JFrame implements ActionListener {

	private JPanel top,middle,bottom;
	private JTextField fNameField,sNameField,cNameField;
	private JLabel connlbl;
	private JButton clearButton,viewCourseButton,bookMemberButton,viewBookingsButton;
	private JTextArea textArea;
	
	
	public dbGUI() {
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setTitle("Gym Booking Query System");
	setSize(800,350);
	setLocation(100,100);
	
	setLayout();
	
	}
	
	
	
	private void setLayout() {
		
		//top
		top = new JPanel();
		fNameField = new JTextField("Forename",10);
		sNameField = new JTextField("Surname",10);
		cNameField = new JTextField("Course",10);
		bookMemberButton = new JButton("Book Member");
		bookMemberButton.addActionListener(this);
		top.add(fNameField);
		top.add(sNameField);
		top.add(cNameField);
		top.add(bookMemberButton);
		add(top,BorderLayout.NORTH);
		
		//Middle
		middle = new JPanel();
		
		clearButton = new JButton("Clear");
		clearButton.addActionListener(this);
		middle.add(clearButton);
		
		viewCourseButton = new JButton("View Courses");
		viewCourseButton.addActionListener(this);
		middle.add(viewCourseButton);
		
		viewBookingsButton = new JButton("View Bookings");
		viewBookingsButton.addActionListener(this);
		middle.add(viewBookingsButton);
		
		connlbl = new JLabel("");
		middle.add(connlbl);
		
		add(middle,BorderLayout.CENTER);
		
		//Bottom
		bottom = new JPanel();
		textArea = new JTextArea("");
		bottom.add(textArea);
		
		add(bottom,BorderLayout.SOUTH);
	}
	
	/*
	 *Method to deal with events from buttons being pressed
	 */
	public void actionPerformed(ActionEvent e) {
		
		//Calls reset text fields method
		if(e.getSource()== clearButton) {
			resetText();
		}
		
		//Executes an sql query to view course information
		else if(e.getSource() == viewCourseButton) {
			db.connectDB();
			connectionTest();
			textArea.setText(db.viewCoursesQuery());
			db.closeConnection();
		}
		
		//Executes an sql query to view booking info
		else if(e.getSource()==viewBookingsButton) {
			db.connectDB();
			connectionTest();
			textArea.setText(db.viewBookingsQuery());
			db.closeConnection();
		}
		
		//Executes sql command which inserts values into database to make a new booking
		else if(e.getSource()==bookMemberButton) {
			
			db.connectDB();
			connectionTest();
			
			//Gets fname, sname and coursename from text inputs, sets the first character to uppercase
			try {
				String fName1 = fNameField.getText().trim();
				String s1 = fName1.substring(0,1).toUpperCase();
				String fName = s1 + fName1.substring(1);
			
				String sName1 = sNameField.getText().trim();
				String s2 = sName1.substring(0, 1).toUpperCase();
				String sName = s2 + sName1.substring(1);
				
				
				String cName1 = cNameField.getText().trim();
				String s3 = cName1.substring(0,1);
				String cName = s3 + cName1.substring(1);
			
				textArea.setText(db.bookMemberQuery(fName, sName, cName));
				db.closeConnection();
				resetText();
			
			}
			
			//Catch a invalid inputs (including values not found in the database)
			catch(NumberFormatException nume) {
				JOptionPane.showMessageDialog(null, "Invalid text input. Make sure person is existing member and course exists","Invalid Inputs",JOptionPane.ERROR_MESSAGE);
				resetText();
			}
			
			catch(StringIndexOutOfBoundsException strex) {
				JOptionPane.showMessageDialog(null, "Invalid input","Invalid Inputs",JOptionPane.ERROR_MESSAGE);
				resetText();
			}
			
		}				
	}
	
	/*
	 * Method to reset text fields, labels & areas when clear button pressed
	 */
	public void resetText() {
		fNameField.setText("Forename");
		sNameField.setText("Surname");
		cNameField.setText("Course");
		connlbl.setText("");
		textArea.setText("");
	}
	
	
	/*
	 * Method checking if connection was successful, prints status to GUI label
	 */
	public void connectionTest() {
		if (db.connection != null) {
			connlbl.setText("Connection Successful");
		}
		
		else {
			connlbl.setText("Connection Failed. Try again.");
		}
	}
}

