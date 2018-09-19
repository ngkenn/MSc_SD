import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

/** 
 * Programming AE2
 * Class to display cipher GUI and listen for events
 */
public class CipherGUI extends JFrame implements ActionListener
{
	//instance variables which are the components
	private JPanel top, bottom, middle;
	private JButton monoButton, vigenereButton;
	private JTextField keyField, messageField;
	private JLabel keyLabel, messageLabel;

	//application instance variables
	//including the 'core' part of the textfile filename
	//some way of indicating whether encoding or decoding is to be done
	private MonoCipher mcipher;
	private VCipher vcipher;
	private String fileName, keyName;
	private LetterFrequencies freq;
	
	/**
	 * The constructor adds all the components to the frame
	 */
	public CipherGUI()
	{
		this.setSize(400,150);
		this.setLocation(100,100);
		this.setTitle("Cipher GUI");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.layoutComponents();
	}
	
	/**
	 * Helper method to add components to the frame
	 */
	public void layoutComponents()
	{
		//top panel is yellow and contains a text field of 10 characters
		top = new JPanel();
		top.setBackground(Color.yellow);
		keyLabel = new JLabel("Keyword : ");
		top.add(keyLabel);
		keyField = new JTextField(10);
		top.add(keyField);
		this.add(top,BorderLayout.NORTH);
		
		//middle panel is yellow and contains a text field of 10 characters
		middle = new JPanel();
		middle.setBackground(Color.yellow);
		messageLabel = new JLabel("Message file : ");
		middle.add(messageLabel);
		messageField = new JTextField(10);
		middle.add(messageField);
		this.add(middle,BorderLayout.CENTER);
		
		//bottom panel is green and contains 2 buttons
		
		bottom = new JPanel();
		bottom.setBackground(Color.green);
		//create mono button and add it to the top panel
		monoButton = new JButton("Process Mono Cipher");
		monoButton.addActionListener(this);
		bottom.add(monoButton);
		//create vigenere button and add it to the top panel
		vigenereButton = new JButton("Process Vigenere Cipher");
		vigenereButton.addActionListener(this);
		bottom.add(vigenereButton);
		//add the top panel
		this.add(bottom,BorderLayout.SOUTH);
	}
	
	/**
	 * Listen for and react to button press events
	 * (use helper methods below)
	 * @param e the event
	 */
	public void actionPerformed(ActionEvent e)
	{
		//Check if getKeyword and processFileName functions return true (validity check), if not, display error messages
		if(getKeyword() && processFileName()) {
			
			//Processes a monoCipher action if mono cipher button is pressed with valid inputs
			if(e.getSource() == monoButton) {
				mcipher =new MonoCipher(keyName); //Creates a new MonoCipher object	
				processFile(false); 
				writeFrequencies();
			}
			else if(e.getSource()==vigenereButton) {
				vcipher = new VCipher(keyName); //Creates a new VCipher object
				processFile(true);
				writeFrequencies();
			}
				
			//Once an encryption/decryption is performed, system terminates
			JOptionPane.showMessageDialog(null,"Successful encryption/decryption, Program Terminated","Success",JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
		
		//Keyword valid but file name is invalid
		else if(getKeyword()) {
			JOptionPane.showMessageDialog(null,"Invalid file name, Please Try Again","File Error",JOptionPane.ERROR_MESSAGE);
			resetTextFields();
		}	
		
		//File name valid, but keyword invalid
		else if(processFileName()) {
			JOptionPane.showMessageDialog(null, "Invalid Keyword, Please Try Again","Keyword Error",JOptionPane.ERROR_MESSAGE);
			resetTextFields();
		}
		
		//Both inputs invalid
		else {
			JOptionPane.showMessageDialog(null, "Invalid Keyword & FileName, Please Try Again","Input Error",JOptionPane.ERROR_MESSAGE);
			resetTextFields();
		}
	}
	

	
	/** 
	 * Obtains cipher keyword
	 * If the keyword is invalid, a message is produced
	 * @return whether a valid keyword was entered
	 */
	private boolean getKeyword()
	{
		//Gets the key string from the text field and removes any spaces
		keyName = keyField.getText().trim();
		
		boolean validKey = false; //boolean value which is true if keyName is valid, false if not
		int [] referenceArray = new int [26]; //Empty array for checking if a character has been duplicated
	
		//Invalid if keyField is empty or null
		if(keyName.isEmpty() || keyName.equals(null)) 
			validKey=false;	
		
		//Invalid if keyName is longer than 26 letters
		if(keyName.length()>26) 
			validKey=false;
		   
		//Otherwise, loop through keyName to make sure all capitals and no duplicate characters
		else {
			for(int i=0; i<=keyName.length()-1; i++) {
				
				char keyCh = keyName.charAt(i); //Returns each character in the key for checking
				int keyChIndex = keyCh - 'A';
					
					//Then checks if characters are all capital letters
					if(!(keyCh >= 'A' && keyCh <= 'Z')) {
						validKey=false;
						break;
					}
					
					//Checks if any characters are duplicated			
					if(referenceArray[keyChIndex]!=0) {
						validKey=false;
						break;}
					
					//Otherwise, records if a character has appeared by incrementing its index in reference array
					else {
						referenceArray[keyChIndex]++;
						}
					
					//Valid keyword
					validKey=true;
					}	
			}								
		
		//Returns either a true or false value (Valid/Invalid)
		return validKey;
	 
	 }
	 
	
	
	/** 
	 * Obtains filename from GUI
	 * The details of the filename and the type of coding are extracted
	 * If the filename is invalid, a message is produced 
	 * The details obtained from the filename must be remembered
	 * @return whether a valid filename was entered
	 */
	private boolean processFileName()
	{
		String fileNameInput = messageField.getText().trim(); //Gets filename from input, removes whitespace
		fileName = fileNameInput+".txt"; //Creates the real filename by adding '.txt'
		boolean validFile = false; //Initialise a boolean for checking if filname is valid
		char lastChar = fileNameInput.charAt(fileNameInput.length()-1); //Gets the last character of file input for checking
		
		//False if empty or null filename input
	   if(fileName.isEmpty() || fileName.equals(null)) {
		   validFile = false;
	   }
	   //Filename only valid in this instance if it ends with "P" or "C"
	   else if(lastChar == 'P' || lastChar == 'C') {
		   validFile=true;
	   }
	   
	   //Otherwise, file is invalid
	   else {
		   validFile=false;
	   }
		 return validFile;
	}
	
	/** 
	 * Reads the input text file character by character
	 * Each character is encoded or decoded as appropriate
	 * and written to the output text file
	 * @param vigenere whether the encoding is Vigenere (true) or Mono (false)
	 * @return whether the I/O operations were successful
	 */
	private boolean processFile(boolean vigenere)
	{
		//Creates reader and writer
		FileReader reader=null;
		FileWriter writer = null;
		
		int next=0; //Int for storing the next character as integer
		char c; //Char for storing the next character as char
		boolean finished=false; //Boolean for checking if end of input file has been reached
		String outputFile = ""; //Determines which file to write to, based on input file
		freq = new LetterFrequencies(); //Creates a new LetterFrequencies object for writing frequency table
		
		//Updates outputFile based on input
		if(fileName.equals("messageC.txt")) {
			outputFile = "messageD.txt";
		}
		else if(fileName.equals("messageP.txt")) {
			outputFile = "messageC.txt";
		}
		
		//Try block for reading/wrting. IOException and FileNotFound exceptions dealt with in catch blocks
		try {
			reader = new FileReader(fileName);
			writer = new FileWriter(outputFile);
			
			//Keeps reading/writing until end of input file is reached
			while(!finished) {
				next = reader.read();
				if(next==-1) finished=true; //Exits the loop when next is -1 (end of input file)
				
				//Otherwise, if input file chars are capitals, processes, writes and updates frequency table
				else {
					c = (char) next;
					if(c >= 'A' && c <= 'Z') {
						char processedChar = processChar(c,vigenere);
						writer.append(processedChar);
						freq.addChar(processedChar);
					}
					
					//If the character is non alphabetic (e.g. a space, comma or number) it is left uncoded
					else if(c >= 32 && c <= 64) {
						writer.append(c);
					}
					
					//If a character is lowercase, error message appears and program terminates
					else if (c >= 'a' && c <= 'z') {
						JOptionPane.showMessageDialog(null,"Lower case letters in text file. Please try again with uppercase only","lower case error",JOptionPane.ERROR_MESSAGE);
						System.exit(0);
					}
				}				
			}
			//Closes the reader and writer
			reader.close();
			writer.close();
		}
		catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File Not Found","File Error",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		catch (IOException e) {
			System.err.println("CAUGHT IOEXCEPTION");
		}
		return true;		
	}
	
	/*
	 * Method to process the encoding and decoding of characters and output
	 */
	private char processChar(char ch, boolean vigenere) {
		
		char characterCode = 0; //int of coded/decoded char
		
		//Monoalphabetic if vigener boolean is false
		if(!vigenere) {
			//Encodes
			if(fileName.equals("messageP.txt")) {
				characterCode = mcipher.encode(ch);
				return characterCode;
			}
			//Decodes
			else {
				characterCode = mcipher.decode(ch);
				return characterCode;//DECODE
			}
		}
		
		//Vigenere is vigenere boolean is true
		else {
			//Encodes
			if(fileName.equals("messageP.txt")) {
				characterCode = vcipher.encode(ch);
				return characterCode;
			}
			//Decodes
			else {
				characterCode = vcipher.decode(ch);
				return characterCode;
			}
		}
		
	}
	
	/*
	 * Method to reset text fields to empty
	 */
	private void resetTextFields() {
		keyField.setText("");
		messageField.setText("");
	}

	/*
	 * Method to write frequency table (separated from other methods for readability)
	 */
	private void writeFrequencies() {
		
		FileWriter writer=null; //Create writer
		
		//Tries to write frequency table using "freq" object created in process file
		//Deals with IOException and FileNotFound exceptions in catch blocks
		try {
			writer = new FileWriter("messageF.txt");
			writer.append(freq.getReport());
			writer.close();
		}
		catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Unable to find write file","File Not Found",JOptionPane.ERROR_MESSAGE);
		}
		catch(IOException e) {
			JOptionPane.showMessageDialog(null, "IOException","ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
}
