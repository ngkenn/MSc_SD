package commandline;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class represents the AI players in the game They choose a category based
 * on the highest numeric value on their current first card.
 */
public class Computer extends Player {

	/**
	 * Constructor for computer/ AI players
	 * 
	 * @param playerName
	 */
	public Computer(String playerName) {

		super(playerName);
	}

	/**
	 * Always returns the highest value of all categories of one card If the highest
	 * value is twice on one card the first category found is returned
	 * 
	 * @return categoryChosen
	 */
	public String chooseCategory(String[] categoryNames) {
		String categoryChosen = "noCategoryChosen";
		// get values of each category
		int att1 = this.getFirstCard().getAtt(0);
		int att2 = this.getFirstCard().getAtt(1);
		int att3 = this.getFirstCard().getAtt(2);
		int att4 = this.getFirstCard().getAtt(3);
		int att5 = this.getFirstCard().getAtt(4);

		int[] attributeValues = { att1, att2, att3, att4, att5 };

		// get the highest value in attributeValues array
		int maxValue = Arrays.stream(attributeValues).max().getAsInt(); // attention!!! Needs to have Java 1.8

		ArrayList<String> maxCategorys = new ArrayList<String>();

		// check where in the array the highest value is; if there is more than one,
		// gets the last one
		for (int i = 0; i < attributeValues.length; i++) {
			if (attributeValues[i] == maxValue) {
				maxCategorys.add(categoryNames[i]);
			}
		}

		categoryChosen = maxCategorys.get((int) (Math.random() * maxCategorys.size()));// categoryNames has
																						// corresponding indexe

		return categoryChosen;

	}
}