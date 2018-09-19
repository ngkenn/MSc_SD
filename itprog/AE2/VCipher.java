
/**
 * Programming AE2
 * Class contains Vigenere cipher and methods to encode and decode a character
 */
public class VCipher
{
	private char [] alphabet;   //the letters of the alphabet
	private final int SIZE = 26; 
	private char [][] cipher; //2D cipher array
	private int encodeCount,decodeCount; //counters
    
	
	/** 
	 * The constructor generates the cipher
	 * @param keyword the cipher keyword
	 */
	public VCipher(String keyword)
	{ 
		alphabet = new char[SIZE];
		
		//Initialises cipher array at key length x array size
		cipher = new char[keyword.length()][SIZE];
		
		//creates alphabet array
		for(int i=0; i<SIZE; i++) {	
			alphabet[i] = (char)('A'+i);
		}
		
		//Nested Loops to create VCipher array
		for(int i=0; i<keyword.length(); i++) {
			for(int j=1; j<SIZE; j++) {
				
				cipher[i][0] = keyword.charAt(i); //Creates first part of cipher array with keyword
				
				//Restarts the alphabet after Z
				if(cipher[i][j-1]=='Z') {
					cipher[i][j]='A';
				}
				//Fills next index with next letter of alphabet
				else {
					cipher[i][j]=(char)(cipher[i][j-1]+1);
				}
			
			}
		}
		//Counters for encode/decode to determine which part of the array to use to encode/decode 
		encodeCount=-1;
		decodeCount=-1;
		
		//Prints cipher array in System.out console
		printTestArray();
	
	}
	
	/*
	 * Method to print out each element of the cipher array
	 */
	public void printTestArray() {
		for(int i=0; i<cipher.length; i++) {
			for(int j=0; j<SIZE; j++) {
			System.out.print(cipher[i][j]+",");
			}
			System.out.println();
		}
	}
			
	/**
	 * Encode a character
	 * @param ch the character to be encoded
	 * @return the encoded character
	 */	
	public char encode(char ch)
	{
		int charIndex = ch-'A'; //Index of character in alphabet array
		int codedChar = 0; //Integer of the encoded character
		int keyLength = cipher.length; //Length of 1st part of 2D cipher array
		
		//Increment encodeCount after each encoding
		encodeCount++;
		
		//When encodeCount reaches keyLength, reset to zero to go through arrays again
		if(encodeCount == keyLength) {
			encodeCount = 0;
		}
		
		//Returns int of coded char at the correct 2D index
		codedChar = cipher[encodeCount][charIndex];
		
	    return (char)(codedChar);  // Returns codedChar and casts to a character
	}
	
	/**
	 * Decode a character
	 * @param ch the character to be decoded
	 * @return the decoded character
	 */  
	public char decode(char ch)
	{
		int decodedChar = 0;
		int keyLength = cipher.length;
		
		//Increment decodeCount after each time decode is called
		decodeCount ++;
		
		//Resets decodeCount to 0 when keyLength is reached
		if(decodeCount == keyLength) {
			decodeCount = 0;
		}
		
		//Loop through cipher array and returns corresponding character at index i in alphabet array
		for(int i=0; i<SIZE; i++) {
			if(ch==cipher[decodeCount][i]) {
				decodedChar = alphabet[i];
			}
		}
	    return (char)decodedChar;  // Returns decodedChar and casts to a character
	}
	
}
