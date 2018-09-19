package commandline;

import java.util.ArrayList;

public class CommunalPile {

	private ArrayList<Card> cards = new ArrayList<Card>(); // list of card which stored at Communal Pile after each draw

	public CommunalPile() {

	}

	/**
	 * returns the count of the numbers of card in the communal pile
	 * 
	 * @return count of the cards in the communal pile
	 */
	public int getNumOfCardsInPile() {

		int numCards = 0;

		numCards = cards.size();

		return numCards;

	}

	/**
	 * add the card to the end of the list in Communal Pile card array list
	 * 
	 * @param card
	 *            card that will be added to the communal pile
	 */
	public void giveCardsToPile(Card card) {

		cards.add(getNumOfCardsInPile(), card);

	}

	/**
	 * return the first card of Communal Pile and remove it from the communal pile
	 * card array list
	 * 
	 * @return the first card in the communal Pile
	 */
	public Card getCardFormPile() {

		Card firstCard;

		firstCard = cards.get(0);

		cards.remove(0);

		return firstCard;
	}

	/**
	 * Returns a single card object at a given index
	 * 
	 * @param i
	 *            the index of the required card
	 * @return the required card
	 */
	public Card getSpecificCard(int i) {

		Card requiredCard = cards.get(i);
		return requiredCard;

	}
}