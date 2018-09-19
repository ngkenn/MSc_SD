/**
 * Programming AE2
 * Contains monoalphabetic cipher and methods to encode and decode a character.
 */
public class MonoCipher
{
	/** The size of the alphabet. */
	private final int SIZE = 26;

	/** The alphabet. */
	private char [] alphabet;
	
	/** The cipher array. */
	private char [] cipher;

	/**
	 * Instantiates a new mono cipher.
	 * @param keyword the cipher keyword
	 */
	public MonoCipher(String keyword)
	{
		//create alphabet
		alphabet = new char [SIZE];
		for (int i = 0; i < SIZE; i++)
			alphabet[i] = (char)('A' + i);
		
		// Create first part of cipher from keyword
		cipher = new char [SIZE];
		for(int i = 0; i<keyword.length(); i++) {
			cipher[i]=keyword.charAt(i);
		}
		
		// Create remainder of cipher from the remaining characters of the alphabet
		int charShift = keyword.length(); //index variable, increments with each loop. Starts with keyword length
		for(int i=0; i<SIZE;i++) {
			char c = (char)('Z'-i);
			if(!keyword.contains(""+c)) {
				cipher[charShift]=c;
				charShift++;
			}
		} 		
		//Prints cipher array in System.out console
		printTestArray();
	}

	/*
	 * Method to print out each element of the cipher array
	 */
	public void printTestArray() {
		for (int i=0; i<cipher.length;i++) {
			System.out.print(cipher[i]+",");
		}
		System.out.println("");
	}
	
	/**
	 * Encode a character
	 * @param ch the character to be encoded
	 * @return the encoded character
	 */
	public char encode(char ch)
	{
		//gets the index of the char in alphabet array and returns corresponding char from cipher
		return cipher[ch-'A']; 
	}

	/**
	 * Decode a character
	 * @param ch the character to be encoded
	 * @return the decoded character
	 */
	public char decode(char ch)
	{
		int cipherIndex = 0; //The index of the encoded character in cipher array
		
		//Loop through cipher array to find the index of character "ch", exits loop when cipherIndex is found
		for(int i =0; i<SIZE; i++) {
			if(cipher[i]==ch) {
				cipherIndex = i;
				break;
			}
		}
		//returns the corresponding character at that index in alphabet to decode
		return alphabet[cipherIndex]; 
	}
}
