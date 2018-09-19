package commandline;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {

	//the game instance variables

	public ArrayList<Player> players = new ArrayList<Player>();

	public String chosenCategory;
	private int roundCount;
	private HashMap<String, Integer> roundsWon;

	private int numOfDraws;

	private int numPlayers;
	private boolean isDraw;

	private CommunalPile communalPile;
	private Deck deck;
	private String deckTextFile = "StarCitizenDeck.txt";

	private Player activePlayer;
	private Player gameWinner;
	private Player roundWinner;

	public Game(int numberOfPlayers) {
		this.roundCount = 0;
		this.roundsWon = new HashMap<>();
		this.numOfDraws = 0;
		this.isDraw = false;
		this.numPlayers = numberOfPlayers;
	}

	// getters for instance variables

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public String getChosenCategory() {
		return chosenCategory;
	}

	public int getRoundCount() {
		return roundCount;
	}

	public HashMap<String, Integer> getRoundsWon() {
		return roundsWon;
	}

	public int getNumOfDraws() {
		return numOfDraws;
	}

	public int getNumPlayers() {
		return numPlayers;
	}

	public boolean isDraw() {
		return isDraw;
	}

	public CommunalPile getCommunalPile() {
		return communalPile;
	}

	public Deck getDeck() {
		return deck;
	}

	public Player getActivePlayer() {
		return activePlayer;
	}

	public Player getGameWinner() {
		return gameWinner;
	}


	// setters for instance variables

	public void incRoundCount(int roundCount) {
		this.roundCount += roundCount;
	}

	public void incNumOfDraws(int numOfDraws) {
		this.numOfDraws += numOfDraws;
	}

	public void setDraw(boolean draw) {
		isDraw = draw;
	}

	public void setActivePlayer(Player activePlayer) {
		this.activePlayer = activePlayer;
	}

	public void setGameWinner(Player gameWinner) {
		this.gameWinner = gameWinner;
	}

	public void setChosenCategory(String chosenCategory) {
		this.chosenCategory = chosenCategory;
	}

	public Player getRoundWinner() {
		return roundWinner;
	}

	public void setRoundWinner(Player roundWinner) {
		this.roundWinner = roundWinner;
	}

	/**
	 * initialises card deck and communal pile objects
	 */
	public void playGame() {

		deck = new Deck(deckTextFile);
		communalPile = new CommunalPile();

	}

	/**
	 * Setting up the players for the game
	 * there will be always have one human player and at least one AI player as opponent
	 * there could be up to 4 AI players
	 * 
	 * the player information will be stored at a player ArrayList called "players"
	 */
	public void setUpPlayers() {

		for (int i = 0; i < numPlayers; i++) {
			if (i == 0) {
				Human human = new Human("Human Player");
				players.add(human);
			}
			if (i == 1) {
				Computer AIplayer1 = new Computer("AI Player 1");
				players.add(AIplayer1);
			}
			if (i == 2) {
				Computer AIplayer2 = new Computer("AI Player 2");
				players.add(AIplayer2);
			}
			if (i == 3) {
				Computer AIplayer3 = new Computer("AI Player 3");
				players.add(AIplayer3);
			}
			if (i == 4) {
				Computer AIplayer4 = new Computer("AI Player 4");
				players.add(AIplayer4);
			}

		}
	}

	/**
	 * compare the value of the chosen category at the first card of each player's
	 * deck find the winner of the turn who have the biggest value returns the
	 * winner of the turn (if the game is draw, "isDraw" will be set as true)
	 *
	 * @param playersArray: all the player in the game
	 * @param category: chosen category
	 * @return the winner of the round
	 */
	public Player compareValue(ArrayList<Player> playersArray, int category) {
		int winner = 0;
		int max = 0;
		int maxCount = 0;

		// check which player has the highest value of one category
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			System.out.println(player.getPlayerName() + "'s card value: " + player.getFirstCard().getAtt(category));
			if (i == 0) {
				max = this.getCategoryValueOfPlayer(playersArray.get(i), category);
				winner = i;
				maxCount++;
			} else if (this.getCategoryValueOfPlayer(playersArray.get(i), category) == max) {
				maxCount++;
			} else if (this.getCategoryValueOfPlayer(playersArray.get(i), category) > max) {
				max = this.getCategoryValueOfPlayer(playersArray.get(i), category);
				winner = i;
				maxCount = 1;
			}
		}

		// check if there was draw -> the highest number appears at least twice in the array
		if (maxCount > 1) {
			isDraw = true;
			return null;
		}

		return playersArray.get(winner);
	}

	/**
	 * div the deck in to part for each player. players will have even card on their
	 * card deck
	 * if only 3 players in the game, the first player will receives the last extra card
	 */
	public void dealCards() {

		for (int i = 0; i < players.size(); i++) {

			for (int ia = 0; ia < (int) (deck.deckSize / numPlayers); ia++) {
				players.get(i).setPersonalDeck(deck.deckArray[(i * (int) (deck.deckSize / numPlayers) + ia)]);
			}
		}
		
		if(numPlayers == 3) {
			players.get(0).setPersonalDeck(deck.deckArray[39]);
		}
	}

	/**
	 * select a random player as the starting player
	 *
	 * @return one random player from x players (x is the number of players)
	 */
	public void selectStartingPlayer() {

		int playerNumber = (int) (Math.random() * (numPlayers)); // returns value between 0 and numPlayers exclusive
		activePlayer = players.get(playerNumber);

	}

	/**
	 * take the target player and chosen category return the value of the category
	 * of the first card of this player
	 * 
	 * @param player: the distinct player
	 * @param category: the index of category chosen
	 * @return the value the category of the first card in this player
	 */
	public int getCategoryValueOfPlayer(Player player, int category) {
		int value;

		value = player.getFirstCard().getAtt(category);

		return value;
	}

	/**
	 * update the players list. check the remaining cards for each player
	 * a player with 0 cards will be taken out of the game and removed from the player list
	 */
	public void updatePlayer() {
		int i = 0;
		while (i < players.size()) {
			if (players.get(i).getNumOfCardsInDeck() == 0) {
				System.out.printf("%n---- %s is out of the game ----%n", players.get(i).getPlayerName());
				roundsWon.put(players.get(i).getPlayerName(), players.get(i).getNumOfRoundsWon()); // remember the rounds this player has won
				players.remove(i);
			} else {
				i++;
			}
		}
	}

	/**
	 * writes game information to the database
	 * as the method in the DBConnector class can be overloaded, the number of parameters passed depends on
	 * the number of players in the game
	 */
	public void writeToDatabase() {
		DBConnector dB = new DBConnector("m_17_2341731l", "m_17_2341731l", "2341731l");
		dB.connect();
		if (numPlayers == 2) {
			dB.writeToDB(gameWinner.getPlayerName(), numOfDraws, roundCount, roundsWon.get("Human Player"),
					roundsWon.get("AI Player 1"));
		} else if (numPlayers == 3) {
			dB.writeToDB(gameWinner.getPlayerName(), numOfDraws, roundCount, roundsWon.get("Human Player"),
					roundsWon.get("AI Player 1"), roundsWon.get("AI Player 2"));
		} else if (numPlayers == 4) {
			dB.writeToDB(gameWinner.getPlayerName(), numOfDraws, roundCount, roundsWon.get("Human Player"),
					roundsWon.get("AI Player 1"), roundsWon.get("AI Player 2"), roundsWon.get("AI Player 3"));
		} else if (numPlayers == 5) {
			dB.writeToDB(gameWinner.getPlayerName(), numOfDraws, roundCount, roundsWon.get("Human Player"),
					roundsWon.get("AI Player 1"), roundsWon.get("AI Player 2"), roundsWon.get("AI Player 3"),
					roundsWon.get("AI Player 4"));
		}

		dB.closeConnection();
	}

}
