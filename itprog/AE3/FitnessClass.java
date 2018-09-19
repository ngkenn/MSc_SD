import java.util.*;

/** Defines an object representing a single fitness class
 */

public class FitnessClass implements Comparable<FitnessClass> {
   
/*
 * Instance variables 
*/
private int time = 0;		//Class start time
private String id = ""; 			//Class id
private String name = "";	//Class name
private String instructor = "";	//Class Instructor name
private int[] attendance;

	
	/*
	 * Default Constructor 
	 */
	public FitnessClass() {
	}


	/*
	 * Constructor using a string input
	 */
	public FitnessClass(String info) {
		//Create a scanner object to read the text input and set values of id etc
		Scanner scan = new Scanner(info);
		id = scan.next();
		name = scan.next();
		instructor = scan.next();
		time = scan.nextInt();
		
		//close the scanner
		scan.close(); 
	}
	
	
	/*
	 * Accessor methods
	 */
	public String getID() {
		return id;
	}	
	public String getName() {
		return name;
	}
	public String getInstructor() {
		return instructor;
	}
	public int getTime() {
		return time;
	}
	public int[] getAttendance() {
		return attendance;
	}
	
	/*
	 * Mutator methods
	 */
	public String setID(String newid) {
		id = newid;
		return id;
	}
	public String setName(String newName) {
		name = newName;
		return name;
	}
	public String setInstructor(String newInst) {
		instructor = newInst;
		return instructor;
	}
	public int setTime(int newTime) {
		time = newTime;
		return time;
	}
	public int[] setAttendance(int[] newAttendance) {
		attendance = newAttendance;
		return attendance;
	}
	
	/*
	 * Method which returns the attendance data formatted as a string
	 */
	public String attendanceReport() {
		
		//Create a StrinBuilder
		StringBuilder sb = new StringBuilder();
		
		//Loop through attendance array, append each piece of attendance data to string
		int[] attendArr = getAttendance();
		for(int i=0; i<attendArr.length; i++) {
			sb.append(String.format(" %3s  ",attendArr[i]));
		}
		
		//Append average attendance
		sb.append(String.format("\t    %.2f",averageAttendance()));
		
		return sb.toString();
	}
	
	/*
	 * Method to return the average attendance for a certain course
	 */
	public double averageAttendance() {
		double sum = 0.0;
		
		//Loop through attendances to calculate the sum
		for(int i=0; i<attendance.length; i++) {
			sum += attendance[i];
		}
		//Calculate & return the average
		double average = sum/attendance.length;
		return average;
	}
	
	/*
	 * Method to create a new reference for sorting (the implementation of Comparable<FitnessClass>
	 */
	public int compareTo(FitnessClass other) {
	  double av1 = this.averageAttendance();
	  double av2 = other.averageAttendance();
	  int ref=0;
	  
	  if(av1<av2) {
		  ref = 1;
	  }
	  else if(av1>av2) {
		  ref = -1;
	  }
	  else {
		  ref = 0;
	  }
	  return ref;
    }
}
