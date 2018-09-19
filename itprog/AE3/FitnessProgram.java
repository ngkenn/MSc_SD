import java.lang.reflect.Array;
import java.util.*;

/**
 * Maintains a list of Fitness Class objects
 * The list is initialised in order of start time
 * The methods allow objects to be added and deleted from the list
 * In addition an array can be returned in order of average attendance
 */
public class FitnessProgram {
	/*
	 * Instance Variables
	 */
	private final int maxClasses = 7, maxAttendances = 5;
	public FitnessClass[] classList;
	private int classCount = 0;
		
   
	/*
	 * Default Constructor
	 */
	public FitnessProgram() {
		classList = new FitnessClass[maxClasses];
		classCount = 0;
	}
	
	/*
	 * Adds a class to the list
	 */
	public void createList(String info) {
		//Create a new FitnessClass object
		FitnessClass newClass = new FitnessClass(info);
		int time = newClass.getTime();
		int timeIndex = time % 9; // orders the array based on time. (e.g. 9 would be at index 0, 15 at index 6)
		
		classList[timeIndex] = newClass;
		
		classCount++;
	}
	
	public void attendanceList(String info) {
		//Create a list of attendances
		int[] attendanceList = new int[maxAttendances];
		
		//Create a scanner to read the input line from AttendancesIn
		Scanner scan = new Scanner(info);
		
		String classID = scan.next(); //Class id is the 1st string in the AttendancesIn file
		
		//Read attendance numbers and add them to the attendancelist
		int i = 0;
		while(scan.hasNext()) {
			int attendance = scan.nextInt();
			attendanceList[i] = attendance;
			i++;
		}
		scan.close();
		
		//Link the attendanceList to the class using FitnessClass setAttendance method
		FitnessClass fitClass = idGetFitnessClass(classID);
		if(fitClass !=null) {
			
			fitClass.setAttendance(attendanceList);
		
		}
		else {System.err.println("Null FitnessCLass object");}
	}
	
	/*
	 * Gets the number of classes on the list
	 */
	public int getClassCount() {
		return classCount;
	}
	
	
	/*
	 * Returns a FitnessClass object from an ID input
	 */
	public FitnessClass idGetFitnessClass(String classid) {
		
		for (int i=0; i<classList.length; i++) {
			FitnessClass fitclass = classList[i];
			if(fitclass!=null) {
				String fitClassid = fitclass.getID();
				
				if(fitClassid.equals(classid)){
					return fitclass;
				}
			}
		}

		return null;
	}
	
	/*
	 * Returns a FitnessClass object from a start time input
	 */
	public FitnessClass timeGetFitnessClass(int time) {
		
	for (int i=0; i<classList.length; i++) {
		FitnessClass fitClass = classList[i];
		
			if(fitClass!=null) {
				int startTime = fitClass.getTime();
				if(startTime == time) {
					return fitClass;
				}
			}
		}
		return null;
	}
	
	/*
	 * Method to insert a FitnessClass Object to the list
	 */
	public void insertFitClass(FitnessClass fitcl) {
		
		//Create an empty attendance array for the new class
		int[] newAttendances = new int[maxAttendances];
		
		//Add the class to classList, set the attendances, increment classCount
		if(fitcl!=null) {
			int startTime = fitcl.getTime();
			int timeIndex = startTime % 9;
			classList[timeIndex] = fitcl;
			fitcl.setAttendance(newAttendances);
			classCount++;	
		}
	}
	
	/*
	 * Method to delete a FitnessClass object from the list
	 */
	public void deleteFitClass(FitnessClass fitcl) {
		if(fitcl!=null) {
			
			//Find the class in the classList array, delete by setting to null, decrement classCount
			for(int i=0;i<classList.length;i++) {
				if(classList[i]==fitcl) {
					classList[i]=null;
					classCount--;
				}
			}
		}
	}
	
	
	/*
	 * Method to return a sorted list of non-null classes based on average attendance
	 */
	public FitnessClass[] sortAttendance() {
		//Create a new array which takes the length of actual number of classes
		FitnessClass[] sortedClassList = new FitnessClass [classCount];
		
		//index for array with null values removed (incremented when non-null FitClass object is added)
		int sortIndex = 0;
		
		//loop through full classList and add non null objects to the sortedClassList
		for(int i=0; i<classList.length; i++) {
			FitnessClass fitcl = classList[i];
			if(fitcl != null) {
					sortedClassList[sortIndex] =fitcl;
					sortIndex++; //Increment index
			}
		}
		
		//Sort the array (uses sorting criteria defined in FitnessClass.compareTo()
		Arrays.sort(sortedClassList);
		return sortedClassList;
	}
	
	/*
	 * Method to return overall average attendance
	 */
	public double overallAverage() {
		
		double total = 0;
		//Loop through sorted array averages, total the average and then calculate overall average
		FitnessClass[] sortedArr = sortAttendance();
		for(int i=0; i<sortedArr.length; i++) {
			FitnessClass fc = sortedArr[i];
			double fcAv = fc.averageAttendance();
			total += fcAv;
		}
		return total/sortedArr.length;
	}
	
}
	
	
	
	
	
	

