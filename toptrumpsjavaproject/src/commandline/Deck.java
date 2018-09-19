package commandline;

import java.util.*;
import java.io.*;

public class Deck {

	protected Card[] deckArray;
	protected static final int deckSize = 40; //Card deck size will always be 40
	private String att1Name, att2Name, att3Name, att4Name, att5Name; // Names of categories
	private String[] categoryArray; // Array of category names

	/**
	 * Constructor. Creates card object and adds to the deck
	 * 
	 * @param textFile
	 */
	public Deck(String textFile) {
		deckArray = new Card[deckSize];
		int index = 0; // Index for placing new card objects into deck array

		try {
			Scanner scan = new Scanner(new File(textFile));
			String headings = scan.nextLine();
			setCategoryNames(headings);
			while (scan.hasNextLine()) {
				String line = scan.nextLine(); // Line to build card object
				Card newCard = new Card(line);
				deckArray[index] = newCard;
				newCard.setid(index); // Sets the id to an integer between 1 and 40
				index++;
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.err.println("Input File Not Found");
			e.printStackTrace();
		}

	}

	/**
	 * Method to shuffle the deckArray
	 */
	public void shuffleDeck() {

		Random rnd = new Random();

		// Loop through the deck, create a random index and
		// swap cards from index i to random index.
		for (int i = deckSize - 1; i > 0; i--) {
			int rndIndex = rnd.nextInt(i + 1);

			// Get a random card from the array, swap it with card at index i
			Card rndCard = deckArray[rndIndex];
			deckArray[rndIndex] = deckArray[i];
			deckArray[i] = rndCard;
		}
	}

	/**
	 * Set the category names
	 * 
	 * @param lineIn
	 */
	public void setCategoryNames(String lineIn) {
		// Split the lineIn string into 2 (discarding the 1st word which will always be
		// description
		lineIn =  lineIn.toLowerCase();
		String parts[] = lineIn.split(" ", 2);
		// The actual category names string will be at index 1 of the parts array
		categoryArray = parts[1].split(" ");
		att1Name = categoryArray[0].toLowerCase();
		att2Name = categoryArray[1].toLowerCase();
		att3Name = categoryArray[2].toLowerCase();
		att4Name = categoryArray[3].toLowerCase();
		att5Name = categoryArray[4].toLowerCase();

	}

	/**
	 * Getters for category names
	 * 
	 * @param attNumber
	 * @return att
	 */

	public String getAttName(int attNumber) {
		switch (attNumber) {
		case 0:
			return att1Name;
		case 1:
			return att2Name;
		case 2:
			return att3Name;
		case 3:
			return att4Name;
		case 4:
			return att5Name;
		default:
			return "Category does not exist";
		}
	}

	/**
	 * Getter for whole category name array
	 * 
	 * @return categoryArray
	 */
	public String[] getCategoryArray() {
		return categoryArray;
	}


	/**
	 * get the index of a category based on the name input
	 * @param categoryName
	 * @return index
	 */
	public int getCategoryIndex(String categoryName) {
		int index = -1;

		for (int i = 0; i < this.getCategoryArray().length; i++) {
			if (this.getCategoryArray()[i].toLowerCase().equals(categoryName.toLowerCase())) {
				index = i;
			}
		}

		return index;
	}
}
