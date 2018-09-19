/**
 * Programming AE2
 * Processes report on letter frequencies
 */
public class LetterFrequencies
{
	/** Size of the alphabet */
	private final int SIZE = 26;
	
	/** Count for each letter */
	private int [] alphaCounts;
	
	/** The alphabet */
	private char [] alphabet; 
												 	
	/** Average frequency counts */
	private double [] avgCounts = {8.2, 1.5, 2.8, 4.3, 12.7, 2.2, 2.0, 6.1, 7.0,
							       0.2, 0.8, 4.0, 2.4, 6.7, 7.5, 1.9, 0.1, 6.0,  
								   6.3, 9.1, 2.8, 1.0, 2.4, 0.2, 2.0, 0.1};

	/** Character that occurs most frequently */
	private char maxCh;

	/** Total number of characters encrypted/decrypted */
	private int totChars;
	
	/**
	 * Instantiates a new letterFrequencies object.
	 */
	public LetterFrequencies()
	{
		//Creates an alphabet sized array of zeros
		alphaCounts = new int[SIZE];
		for(int i=0; i<SIZE; i++) {
			alphaCounts[i]=0;
		}
		
		//Creates alphabet array
		alphabet = new char[SIZE];
		for (int i=0; i<SIZE; i++) {
			alphabet[i] = (char) ('A'+i);
		}
	}
		
	/**
	 * Increases frequency details for given character
	 * @param ch the character just read
	 */
	public void addChar(char ch)
	{
		totChars++; //increment totChars every time a char is counted
		int i = ch-'A'; //the index of the character just read
		alphaCounts[i]++; //increases the count by 1 for each index
	}
	
	/**
	 * Gets the maximum frequency
	 * @return the maximum frequency
	 */
	private double getMaxPC()
        { 
		int maxOccur=0; //Max occurrences
        int maxIndex=0; //the index where the maximum occurring character is located
        
        //Finds the maximum occurring character and index
        for(int i=0; i<SIZE; i++) {
	        	if(alphaCounts[i]>=maxOccur) {
	        		maxOccur = alphaCounts[i];
	        		maxIndex = i;
	        	}
        }
        
        //Calculates max frequency, returns it and updates value of maxCh
        double maxFreq = ((double)maxOccur/totChars)*100;
        maxCh = alphabet[maxIndex];
	    return maxFreq;  
	}
	
	/**
	 * Returns a String consisting of the full frequency report
	 * @return the report
	 */
	public String getReport()
	{
		double maxFreq = getMaxPC(); //Finds maxFreq
		
		//Creates a new String builder
		StringBuilder freqTable = new StringBuilder("LETTER ANALYSIS");
		
		//Title
		freqTable.append(String.format("%n CHAR \t FREQ \t FREQ%% \t AVG%% \t DIFF %n"));
	
		//Populates freqTable with information on character frequencies
		for(int i=0; i<SIZE; i++) {
			double frequency = ((double)alphaCounts[i]/totChars)*100;	
			freqTable.append(String.format("   %c\t\t %d   \t %.02f \t %.02f \t %.02f %n",alphabet[i],alphaCounts[i],frequency,avgCounts[i],frequency-avgCounts[i]));
		}
		
		//Max Character summary at the bottom of freqTable
		freqTable.append(String.format("%n The most frequent letter was %c at %.02f%% Total Characters: %d", maxCh,maxFreq,totChars));
	  
		//turns freqTable into a string and returns it
		return freqTable.toString();  
	}
}
