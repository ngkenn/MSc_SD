package commandline;

import java.util.*;

public class Card {

	private String description; //Name of card
	private int att1, att2, att3, att4, att5; // Integer value of each attribute
	private int id; // unique id of card

	/**
	 * Constructor for commandline.Card. Input is a line from the text file
	 * 
	 * @param lineIn
	 */
	public Card(String lineIn) {
		Scanner scan = new Scanner(lineIn);

		//Set the description & attributes to each element of lineIn
		while (scan.hasNext()) {
			description = scan.next();
			att1 = scan.nextInt();
			att2 = scan.nextInt();
			att3 = scan.nextInt();
			att4 = scan.nextInt();
			att5 = scan.nextInt();
		}
		scan.close();
	}

	/**
	 * Get Description
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Get Attribute values
	 * 
	 * @param attNumber
	 * @return att
	 */
	public int getAtt(int attNumber) {
		switch (attNumber) {
		case 0:
			return att1;
		case 1:
			return att2;
		case 2:
			return att3;
		case 3:
			return att4;
		case 4:
			return att5;
		default:
			return 0;
		}
	}

	public int getid() {
		return id;
	}

	/**
	 * Setter for the id
	 * 
	 * @param newid
	 * @return id
	 */
	public int setid(int newid) {
		id = newid;
		return id;
	}
}
