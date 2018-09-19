import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

/**
 * Defines a GUI that displays details of a FitnessProgram object
 * and contains buttons enabling access to the required functionality.
 */
public class SportsCentreGUI extends JFrame implements ActionListener {
	
	/** GUI JButtons */
	private JButton closeButton, attendanceButton;
	private JButton addButton, deleteButton;

	/** GUI JTextFields */
	private JTextField idIn, classIn, tutorIn;

	/** Display of class timetable */
	private JTextArea display;

	/** Display of attendance information */
	private ReportFrame report;

	/** Names of input text files */
	private final String classesInFile = "ClassesIn.txt";
	private final String classesOutFile = "ClassesOut.txt";
	private final String attendancesFile = "AttendancesIn.txt";
	
	/** FitnessProgram and ReportFrame instance Variables */
	private FitnessProgram fitProg;
	private ReportFrame attReport;

	
	
	
	/**
	 * Constructor for AssEx3GUI class
	 */
	public SportsCentreGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Boyd-Orr Sports Centre");
		setSize(700, 300);
		display = new JTextArea();
		display.setFont(new Font("Courier", Font.PLAIN, 14));
		add(display, BorderLayout.CENTER);
		layoutTop();
		layoutBottom();
		initLadiesDay(); //Initialise the fitnessProgram
		updateDisplay(); //Update the display with the classes
		initAttendances(); //Initialise the attendance processing
		
	}

	/**
	 * Creates the FitnessProgram list ordered by start time
	 * using data from the file ClassesIn.txt
	 */
	public void initLadiesDay() {
		Scanner scan = null;
		fitProg = new FitnessProgram();
		String Line = "";
		
		//Read each line of the input file, create a classList with the line
		try {
			scan = new Scanner(new File(classesInFile));
			while(scan.hasNext()) {
				Line = scan.nextLine();
				fitProg.createList(Line);
			}			
			scan.close();				
		}
		catch(FileNotFoundException e) {
			System.err.println("File Not Found");
		}
	}

	/**
	 * Initialises the attendances using data
	 * from the file AttendancesIn.txt
	 */
	public void initAttendances() {
		Scanner scan = null;
		String attLine = "";
		
		//Read attendances file, create attendance list with each line
		try {	
			scan = new Scanner(new File(attendancesFile));
			while(scan.hasNextLine()) {
				attLine = scan.nextLine();
				fitProg.attendanceList(attLine);
		}
			scan.close();
		}
		catch(FileNotFoundException e) {
			System.err.println("File Not Found");
		}
	}

	/**
	 * Instantiates timetable display and adds it to GUI
	 */
	public void updateDisplay() {
		
		//Create StringBuilder and populate with headings
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%11s %11s %11s %11s %11s %11s %11s \n","9-10","10-11","11-12","12-13","13-14","14-15","15-16"));
		
		//Loop through class names and add to timetable
		for(int i=9; i<=15; i++) {
			FitnessClass fitClass = fitProg.timeGetFitnessClass(i);
	    
		    if(fitClass==null) {
		    		sb.append(String.format("%12s","Available"));
		    }
		    		
		    else {
		    		String cName = fitClass.getName();		    
		    		sb.append(String.format("%12s",cName));
		    }    
		}
		
		sb.append("\n");
		
		//Loop through class instructors and add to timetable
		for(int i=9; i<=15; i++) {
			FitnessClass fitClass = fitProg.timeGetFitnessClass(i);
			
			if(fitClass == null) {
				sb.append(String.format("%12s","N/A"));
			}
			
			else {
				String cInst = fitClass.getInstructor();	
				sb.append(String.format("%12s",cInst));
			}
		}
		
		String timetable = sb.toString();
		System.out.println(timetable);
		display.setText(timetable);
	}

	/**
	 * adds buttons to top of GUI
	 */
	public void layoutTop() {
		JPanel top = new JPanel();
		closeButton = new JButton("Save and Exit");
		closeButton.addActionListener(this);
		top.add(closeButton);
		attendanceButton = new JButton("View Attendances");
		attendanceButton.addActionListener(this);
		top.add(attendanceButton);
		add(top, BorderLayout.NORTH);
	}

	/**
	 * adds labels, text fields and buttons to bottom of GUI
	 */
	public void layoutBottom() {
		// instantiate panel for bottom of display
		JPanel bottom = new JPanel(new GridLayout(3, 3));

		// add upper label, text field and button
		JLabel idLabel = new JLabel("Enter Class Id");
		bottom.add(idLabel);
		idIn = new JTextField();
		bottom.add(idIn);
		JPanel panel1 = new JPanel();
		addButton = new JButton("Add");
		addButton.addActionListener(this);
		panel1.add(addButton);
		bottom.add(panel1);

		// add middle label, text field and button
		JLabel nmeLabel = new JLabel("Enter Class Name");
		bottom.add(nmeLabel);
		classIn = new JTextField();
		bottom.add(classIn);
		JPanel panel2 = new JPanel();
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);
		panel2.add(deleteButton);
		bottom.add(panel2);

		// add lower label text field and button
		JLabel tutLabel = new JLabel("Enter Tutor Name");
		bottom.add(tutLabel);
		tutorIn = new JTextField();
		bottom.add(tutorIn);

		add(bottom, BorderLayout.SOUTH);
	}

	/**
	 * Processes adding a class
	 */
	public void processAdding() {
		
		FitnessClass[] cList = fitProg.classList;
		int nClasses = fitProg.getClassCount();
		int maxClasses = fitProg.classList.length;
		int firstNullIndex = 0;
		
		//Gets info from the text fields
		String idField = idIn.getText().trim();
		String nameField = classIn.getText().trim();
		String tutorField = tutorIn.getText().trim();
		
		//Check if timetable has space, if the text entries are valid and if the id is unique
		if(nClasses < maxClasses) {
			if(checkValidText(idField,nameField,tutorField)) {
				if(!checkDuplicateID(idField)) {
		
					//Create the FitnessClass Object, set the attributes to the info from text fields
					FitnessClass addClass = new FitnessClass();
					addClass.setID(idField);				
					addClass.setName(nameField);
					addClass.setInstructor(tutorField);
					
				//Find the first null fitnessClass object in the list, add class to that slot
					for(int i=0; i<cList.length; i++) {
						if(cList[i] == null) {
							firstNullIndex = i;
							break;
						}
					}	
					addClass.setTime(firstNullIndex+9);
					fitProg.insertFitClass(addClass); //Inserting the new Class to the timetable
					JOptionPane.showMessageDialog(null, nameField +" ("+idField+") added    \nTimetable Updated","Class Added",JOptionPane.INFORMATION_MESSAGE);
					resetText();
				}
				
				else {
					JOptionPane.showMessageDialog(null, "Duplicate Class ID \nClass Not Added","Duplication Error",JOptionPane.ERROR_MESSAGE);
					resetText();
					return;
				}
			}
			
			else {
				JOptionPane.showMessageDialog(null, "Invalid text entry. \nPlease ensure text fields are not empty","Invalid Input",JOptionPane.ERROR_MESSAGE);
				resetText();
				return;
			}	
		}
		else {
			JOptionPane.showMessageDialog(null, "No available time slots","Max capacity reached",JOptionPane.ERROR_MESSAGE);
			resetText();
			return;
		}
	}

	
	/*
	 * Method to check for duplicate ID when adding a class (returns a boolean value)
	 */
	public boolean checkDuplicateID(String id) {
		FitnessClass[] sortedList = fitProg.sortAttendance();
		int i = 0;
		
		while(i<sortedList.length) {
			String cID = sortedList[i].getID();
			if(cID.equals(id)){
				return true;
			}
			else {
				i++;
			}
		}
		return false;
	}
	
	/*
	 * Method to check text inputs are valid
	 */
	public boolean checkValidText(String id,String name, String tutor) {
		if(id.equals("") || name.equals("") || tutor.equals("")) {
			return false;
		}
		else {
		return true;
		}
	}
	
	/*
	 * Resets text fields
	 */
	public void resetText() {
		idIn.setText("");
		classIn.setText("");
		tutorIn.setText("");
	}
	
	/**
	 * Method to process the deletion of a class
	 */
	public void processDeletion() {
		FitnessClass[] sortedList = fitProg.sortAttendance();
		FitnessClass fitClassDel=null;
		String id = idIn.getText().trim();
		int i=0; //Index for looping through sortedList array
	
		//Check if id is not empty and is non-null
		if(!id.equals("")) {
			while(i<sortedList.length) {
				String classID = sortedList[i].getID();
				if(id.equals(classID)) {
					fitClassDel = fitProg.idGetFitnessClass(id);
					fitProg.deleteFitClass(fitClassDel);
					JOptionPane.showMessageDialog(null, fitClassDel.getName()+" "+id+" deleted","Deletion Successful",JOptionPane.INFORMATION_MESSAGE);
					resetText();
					return;		
				}
				else {
					i++;
				}
			}
			JOptionPane.showMessageDialog(null, "Class Not Found \nPlease Ensure Class ID Exists","Not Found",JOptionPane.ERROR_MESSAGE);
			resetText();
		}
		
		else {
			JOptionPane.showMessageDialog(null, "ID Field Empty \nPlease Ensure Valid ID Entry","Input Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Instantiates a new window and displays the attendance report
	 */
	public void displayReport() {
	  attReport = new ReportFrame(fitProg); 
	}

	/**
	 * Writes lines to file representing class name, 
	 * tutor and start time and then exits from the program
	 */
	public void processSaveAndClose() {
	   
		FileWriter writer = null;
		try {
			writer = new FileWriter(classesOutFile);
			FitnessClass[] cList = fitProg.sortAttendance();
			
			//Loop through classList, find class information and append to stringBuilder
			for(int i=0; i<cList.length; i++) {
				FitnessClass fitClass = cList[i];
				String id = fitClass.getID();
				String name = fitClass.getName();
				String instructor = fitClass.getInstructor();
				int time = fitClass.getTime();
				
				writer.append(String.format("%5s \t %10s \t	%10s \t %d \n",id,name,instructor,time));
			}
			
			writer.close();
			JOptionPane.showMessageDialog(null, "Saved","Save successful",JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
		
		catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File Not Found","File Error",JOptionPane.ERROR_MESSAGE);
		}
		catch(IOException e) {
			JOptionPane.showMessageDialog(null, "IOException","IOException",JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Process button clicks.
	 * @param ae the ActionEvent
	 */
	public void actionPerformed(ActionEvent ae) {
	   if(ae.getSource() == attendanceButton) {
		   displayReport();
	   }
	   
	   else if(ae.getSource() == addButton) {
		   processAdding();
		   updateDisplay();
	   }
	   
	   else if(ae.getSource() == deleteButton) {
		   processDeletion();
		   updateDisplay();
	   }
	   
	   else if (ae.getSource() == closeButton) {
		   processSaveAndClose();
	   }
	}
}
