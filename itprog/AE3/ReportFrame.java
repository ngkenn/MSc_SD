import java.awt.*;
import javax.swing.*;


/**
 * Class to define window in which attendance report is displayed.
 */
public class ReportFrame extends JFrame {
	private FitnessProgram fitProg;
	private JFrame rf;
	private JTextArea text;
	FitnessClass[] classArr;
	
	public ReportFrame(FitnessProgram fProg) {
		fitProg = fProg;
		//Create a JFrame, textArea
		rf = new JFrame();
		rf.setTitle("Attendance Report");
		rf.setSize(800,250);
		
		text = new JTextArea();
		rf.add(text,BorderLayout.CENTER);
		
		rf.setVisible(true);
		
		//Call viewReport method
		text.setText(viewReport());
	}
	
	
	public String viewReport() {
		
		//Create Stringbuilder and populate with headings & separator
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%5s  %14s \t %8s \t %20s \t %20s", "Id","Class","Tutor","Attendances","Average Attendance"));
		sb.append("\n ================================================================================= \n");
		
		//Retrieve sorted class array
		classArr = fitProg.sortAttendance();
		
		//Loop through sorted array, appending the attendance report for each FitnessClass object
		for(int i=0; i<classArr.length; i++) {
			FitnessClass fitClass = classArr[i];
			String attReport = fitClass.attendanceReport();
			String cID = fitClass.getID();
			String cName = fitClass.getName();
			String cInst = fitClass.getInstructor();
			
			sb.append(String.format("%5s  %17s \t %8s \t %20s \n", cID,cName,cInst,attReport));
			
		}
		
		//Append overall average
		sb.append(String.format("\n Overall Average: %.2f",fitProg.overallAverage()));
		
		return sb.toString();
	}
	
}
	
