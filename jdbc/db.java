import java.sql.*;
import org.postgresql.util.PSQLException;

import javax.swing.JOptionPane;

public class db {

	public static Connection connection = null;
	
	/*
	 * Method to connect to the database. Login credentials supplied
	 */
	public static void connectDB() {
		String dbName = "m_17_2025066k";
		String username = "m_17_2025066k";
		String password= "2025066k";
		
		try {
			connection=DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/"+
					dbName,username,password);
		}
		
		catch (SQLException e) {
			System.err.println("Connection Failed");
			e.printStackTrace();
			return;
		}
		
		if(connection!=null) {
			System.out.println("Connection Successful!");
		}
		else {
			System.err.println("Failed to connect!");
		}
		
	}
	
	/*
	 * Method to Close the connection
	 */
	public static void closeConnection() {
		try {
			connection.close();
			System.out.println("Connection closed");
		}
		
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Connection could not be closed - SQL exception!");
		}
	}
	
	
	/*
	 * method to view course information. Joins other tables to get booking and instructor info
	 */
	public static String viewCoursesQuery() {
	Statement stmt=null;
	String query = "SELECT \"Course\".\"Name\",\"Instructor\".\"fName\",\"Instructor\".\"sName\",\"Course\".\"Capacity\",\"foo\".\"count\"\r\n" + 
			"FROM \"Gym Bookings\".\"Course\"\r\n" + 
			"INNER JOIN \"Gym Bookings\".\"Instructor\"\r\n" + 
			"ON  \"Course\".\"InstructorID\" = \"Instructor\".\"InstructorID\"\r\n" + 
			"INNER JOIN (SELECT \"CourseID\",COUNT(\"BookingNumber\")\r\n" + 
			"     FROM \"Gym Bookings\".\"MemberCourse\" GROUP BY \"CourseID\") AS \"foo\"\r\n" + 
			"ON	\"foo\".\"CourseID\"=\"Course\".\"CourseID\";";
	
	//Creates a stringbuilder so info can be displayed in GUI
	StringBuilder SB = new StringBuilder();
	
	String course_name,instructor_fname,instructor_sname,course_capacity,booking_count;

			try {
				stmt=connection.createStatement();
				ResultSet rs=stmt.executeQuery(query);
				SB.append("COURSES \nName \tInstructor \tName \tCapacity \tCurrent Bookings	\r\n");
				while(rs.next()) {
					course_name=rs.getString("Name");
					instructor_fname = rs.getString("fName");
					instructor_sname = rs.getString("sName");
					course_capacity = rs.getString("Capacity");
					booking_count = rs.getString("count");
					
					SB.append(course_name+"\t"+instructor_fname+"\t"+instructor_sname+"\t"+course_capacity+"\t"+booking_count+"\r\n");
					System.out.println(SB.toString());
				}
			}
			
			catch(SQLException e) {
				e.printStackTrace();
				System.err.println("error executing query "+query);
				return query;
			}
			
			//Return the built string to be displayed in the GUI
			return SB.toString();			
	}
	
	/*
	 * Method to execute a query to view bookings and corresponding members
	 */
	public static String viewBookingsQuery() {
		Statement stmt=null;
		String query = "SELECT \"Member\".\"fName\",\"Member\".\"sName\",\"Course\".\"Name\",\"MemberCourse\".\"CourseID\",\"MemberCourse\".\"MemberID\""
				+ "FROM \"Gym Bookings\".\"MemberCourse\""
				+ "INNER JOIN \"Gym Bookings\".\"Member\""
				+ "ON \"Gym Bookings\".\"MemberCourse\".\"MemberID\" = \"Gym Bookings\".\"Member\".\"MemberID\""
				+ "INNER JOIN \"Gym Bookings\".\"Course\""
				+ "ON \"Gym Bookings\".\"MemberCourse\".\"CourseID\" = \"Gym Bookings\".\"Course\".\"CourseID\";";
		StringBuilder SB = new StringBuilder();
		String member_fname,member_sname,course_name,course_id,member_id;

		try {
			stmt=connection.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			SB.append("COURSE BOOKINGS \nForename \tSurname \tCourse \tCourse ID \tMember ID\r\n");
			while(rs.next()) {
				member_fname=rs.getString("fName");
				member_sname = rs.getString("sName");
				course_name = rs.getString("Name");
				course_id = rs.getString("CourseID");
				member_id = rs.getString("MemberID");
	
				SB.append(member_fname+"\t"+member_sname+"\t"+course_name+"\t"+course_id+"\t"+member_id +"\r\n");
				System.out.println(SB.toString());
			}	
		}
		
		catch(SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query "+query);
			return query;
		}
		//Return built string to be displayed in the GUI
		return SB.toString();		
	}
	
	
	/*
	 * Method to add a booking to the system. Inputs from JTextFields in GUI
	 */
	public static String bookMemberQuery(String forename, String surname, String coursename) {
		String fname=forename;
		String sname = surname;
		String cname = coursename;
		
		Statement stmt=null;
	
		/*
		 * Get the member ID from fname & sname
		 */
		String memberQuery = "SELECT \"MemberID\""
							+ " FROM \"Gym Bookings\".\"Member\""
							+ " WHERE \"fName\" = \'"+fname+"\' AND \"sName\" = \'"+sname+"\';";
		
							
		String member_id="";
		try {
			stmt=connection.createStatement();
			ResultSet rsid=stmt.executeQuery(memberQuery);
			while(rsid.next()) {
			member_id = rsid.getString("MemberID");
			System.out.println("member id is:"+"\"member_id\"");
			}
			if(member_id==null || member_id.equals("") || member_id.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Member Not Recognised","Input error",JOptionPane.ERROR_MESSAGE);
				closeConnection();
				System.exit(0);
				return "ERROR";
			}
			
		}
		
		catch(SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query "+memberQuery);
			return memberQuery;
		}
		
		/*
		 * Get course ID from course name
		 */
		String courseQuery = "SELECT \"CourseID\",\"Capacity\" FROM \"Gym Bookings\".\"Course\""
				+ " WHERE \"Name\" = \'"+cname+"\';";
		String course_id="",capacitystr="";
		int capacity=0;
		
		try {
			stmt=connection.createStatement();
			ResultSet rscourse=stmt.executeQuery(courseQuery);
			while(rscourse.next()) {
			course_id = rscourse.getString("CourseID");
			capacitystr=rscourse.getString("Capacity");
			System.err.println(course_id+" "+capacitystr);
			}
			if(course_id==null || course_id.equals("") || course_id.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Course Not Recognised","Input error",JOptionPane.ERROR_MESSAGE);
				closeConnection();
				System.exit(0);
				return "ERROR";
			}
			capacity = Integer.parseInt(capacitystr);
		}
		
		catch(SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query "+memberQuery);
			return memberQuery;
		}
		
		/*
		 * Get max booking number from member courses
		 * Create new booking number by adding 1 to max booking number
		 */
		String bookNumberstr="";
		int bookNumberint=0;
		String maxBookingQuery = "SELECT MAX(\"BookingNumber\") FROM \"Gym Bookings\".\"MemberCourse\"";
		
		try {
			stmt=connection.createStatement();
			ResultSet rsnumber=stmt.executeQuery(maxBookingQuery);
			while(rsnumber.next()) {
				bookNumberstr = rsnumber.getString("max");
				System.err.println(bookNumberstr);
			}
			bookNumberint = Integer.parseInt(bookNumberstr)+1;
		}
		
		catch(SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query "+maxBookingQuery);
			return maxBookingQuery;
		}
		
		/*
		 * Count the number of bookings already made for the course (for checking against capacity)
		 */
		String countBookingsQuery = "SELECT COUNT (\"BookingNumber\")"
									+ "FROM \"Gym Bookings\".\"MemberCourse\""
									+ " WHERE \"CourseID\"= \'"+course_id+"\';";
		
		String bookCountstr = "";
		int bookCount=0;
		
		try {
			stmt=connection.createStatement();
			ResultSet rscount=stmt.executeQuery(countBookingsQuery);
			while(rscount.next()) {
				bookCountstr = rscount.getString("count");
				System.err.println(bookCountstr);
			}
			bookCount = Integer.parseInt(bookCountstr);
		}
		
		catch(SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query "+countBookingsQuery);
			return countBookingsQuery;
		}
				
		/*
		 * Check if space in course is available then
		 * insert booking with member ID, course ID, & new booking number
		 */
		
		//Exit system if class is full
		if(bookCount>=capacity) {
			JOptionPane.showMessageDialog(null, "Course at capacity","Booking Error",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		//Else, insert new booking
		else{

		String insertQuery = "INSERT INTO \"Gym Bookings\".\"MemberCourse\""
				+ "(\"MemberID\",\"CourseID\",\"BookingNumber\")"
				+ " VALUES (\'"+member_id+"\',\'"+course_id+"\',\'"+bookNumberint+"\');";

		try {
			stmt=connection.createStatement();
			stmt.executeUpdate(insertQuery);

			}

		catch(SQLException e) {
			e.printStackTrace();
			System.err.println("error executing update "+insertQuery);
			JOptionPane.showMessageDialog(null, "Error executing query. Please ensure there is no duplication","Booking Error",JOptionPane.ERROR_MESSAGE);
			return insertQuery;
		}
		
		
		}
		JOptionPane.showMessageDialog(null, "Booking Successful","Booking",JOptionPane.INFORMATION_MESSAGE);
		return "Booking made!";
	
	}
}

