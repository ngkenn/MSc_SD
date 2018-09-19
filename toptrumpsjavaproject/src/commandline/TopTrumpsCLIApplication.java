package commandline;
import java.util.*;
/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		boolean writeGameLogsToFile = false; // default is off; will be enabled when adding -t to the args
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application

		if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile = true; // Command line selection

		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {
			System.out.println(String.format("Do you want to%n%nPLAY A GAME%30s%nSEE STATISTICS%33s%nQUIT%37s", "-- enter \"play\" --", "-- enter \"statistics\" --", "-- enter \"quit\" --"));
			Scanner scan = new Scanner(System.in);
			String input = scan.next().toLowerCase();
			boolean userInput = false;
			while (!userInput) {
				if (input.equals("play") || input.equals("statistics") || input.equals("quit")) {
					userInput = true;
				} else {
					System.out.println("Input not recognised, please enter either: play, statistics, or quit");
					break;
				}
			}

			if (input.equals("play")) {

				// initialise logwriter
				LogWriter logger = null;

				// initialise new game; always play against 5 layers in command line
				Game game = new Game(5);

				System.out.printf(
						"%n--------------------------%n------- TOP TRUMPS -------%n--------------------------%n-------- NEW GAME --------%n--------------------------%n%n");

				// initialise game variables
				if (writeGameLogsToFile) { logger = new LogWriter(); }
				game.playGame();

				if (writeGameLogsToFile) { logger.writeDeckInfo(game.getDeck()); }

				game.getDeck().shuffleDeck(); //shuffle the deck
				if (writeGameLogsToFile) { logger.writeDeckInfo(game.getDeck()); }

				game.setUpPlayers(); //set up the players
				game.dealCards(); //deal cards to players

				if (writeGameLogsToFile) { logger.writePlayerDeckInfo(game.getDeck(), game); }
				game.selectStartingPlayer(); //select the first player

				// main game loop
				while (game.getPlayers().size() > 1) {
					System.out.printf("%n--------------------------%n---- ROUNDS NUMBER: %d ----%n-- NUMBER OF PLAYERS: %d --%n--------------------------%n%n",
							game.getRoundCount() + 1, game.getPlayers().size());

					for (int i = 0; i < game.getPlayers().size(); i++) {
						System.out.printf("%s: %d cards%n", game.getPlayers().get(i).getPlayerName(),
								game.getPlayers().get(i).getNumOfCardsInDeck());
					}



					// Round loop
					if (writeGameLogsToFile) { logger.writeCurrentCards(game.getDeck(), game); }

					System.out.printf("%nThe active player is: %s%n", game.getActivePlayer().getPlayerName());

					game.setChosenCategory(game.getActivePlayer().chooseCategory(game.getDeck().getCategoryArray())); //set active player of the round

					System.out.printf("%n%s chooses category %S on card %S%n%n", game.getActivePlayer(), game.getChosenCategory(),
							game.getActivePlayer().getCardAtIndex(0).getDescription());

					if (writeGameLogsToFile) { logger.writeCategoryValues(game.getDeck(), game); }

					//set winner of the round by comparing the card values of each player
					game.setRoundWinner(game.compareValue(game.getPlayers(), game.getDeck().getCategoryIndex(game.getChosenCategory())));

					if (!game.isDraw()) {
						System.out.printf("%n---- THE ROUND WINNER IS: %s with card %S ---- %n", game.getRoundWinner(),
								game.getRoundWinner().getCardAtIndex(0).getDescription());
					}

					if (game.isDraw()) { //handle a draw
						System.out.printf("%n---- DRAW ----%n");

						game.incNumOfDraws(1); //increase draw count

						for (int i = 0; i < game.getPlayers().size(); i++) {
							game.getCommunalPile().giveCardsToPile(game.getPlayers().get(i).loseCard()); //players lose cards to communal pile
						}

						if (writeGameLogsToFile) {
							logger.writeCommunalPile(game.getDeck(), game.getCommunalPile());
						}
						game.setDraw(false);

					} else { //if there was no draw, round winner receives cards
						for (int i = 0; i < game.getPlayers().size(); i++) {
							game.getRoundWinner().receiveCard(game.getPlayers().get(i).loseCard()); //round winner receives cards

							if (game.getCommunalPile().getNumOfCardsInPile() > 0) {
								if (writeGameLogsToFile) {
									logger.writeCommunalPile(game.getDeck(), game.getCommunalPile());
								}
							}

							while (game.getCommunalPile().getNumOfCardsInPile() > 0) {
								game.getRoundWinner().receiveCard(game.getCommunalPile().getCardFormPile()); //round winner receives cards in communal pile
							}
						}
						if (writeGameLogsToFile) {
							logger.writePlayerDeckInfo(game.getDeck(), game);
						}
						game.setActivePlayer(game.getRoundWinner()); //round winner is set as active player
						game.getActivePlayer().increaseNumOfRoundsWon(); //increase the number of rounds won for the active player
					}

					game.incRoundCount(1); //increase round count
					game.updatePlayer(); //update the player array; some players with 0 cards may be removed from the game

					//prompt ENTER when human player is still in the game
					if (game.getPlayers().get(0).getPlayerName().equals("Human Player")) {
						System.out.printf(
								"%n ---------------------------%n| Press \"ENTER\" to continue |%n ---------------------------%n");
						Scanner scanner = new Scanner(System.in);
						scanner.nextLine();
					}
				}

				game.setGameWinner(game.getPlayers().get(0)); //set game winner when only one player is left

				//write rounds won by yhe game winner to the hash map that is written to the database
				game.getRoundsWon().put(game.getGameWinner().getPlayerName(), game.getGameWinner().getNumOfRoundsWon());

				if (writeGameLogsToFile == true) {
					logger.writeWinner(game.getGameWinner().getPlayerName());
					logger.closeFileHandler();
				}

				System.out.printf("%n---- THE GAME WINNER IS %s ----%n", game.getGameWinner().getPlayerName());
				System.out.printf(
						"%n%n---------------------------%n------ GAME FINISHED ------%n---------------------------%n%n");

				//stats about the finished game
				System.out.printf("Human Player rounds won: %s%nAI Player 1 rounds won: %s%nAI Player 2 rounds won: %s%nAI Player 3 rounds won: %s%nAI Player 4 rounds won: %s%n%n", game.getRoundsWon().get("Human Player"), game.getRoundsWon().get("AI Player 1"), game.getRoundsWon().get("AI Player 2"), game.getRoundsWon().get("AI Player 3"), game.getRoundsWon().get("AI Player 4"));
				System.out.printf("-- Num draws: %d --%n", game.getNumOfDraws());
				System.out.printf("-- Round count: %d --%n", game.getRoundCount());

				// write game statistics to database
				game.writeToDatabase();

			} else if (input.equals("statistics")) { //show statistics to the user
				DBConnector dBStats = new DBConnector("m_17_2341731l", "m_17_2341731l", "2341731l");
				dBStats.connect();
				HashMap statistics = dBStats.readFromDB();
				printStatistics(statistics);
				dBStats.closeConnection();

			} else if (input.equals("quit")) { //quit the application
				userWantsToQuit = true;
				}
		}

		System.exit(0);
	}

	/**
	 * Prints out all statistical numbers at the end of each game
	 * @param statistics
	 */
	private static void printStatistics (HashMap statistics){
		String statsString = String.format("%n--------------------------%n------- TOP TRUMPS -------%n--------------------------%n------- STATISTICS -------%n--------------------------%n%n");
		StringBuilder createStats = new StringBuilder(statsString);
		
		createStats.append(String.format("--- Number of games played: %s ---%n%n", statistics.get("Number of games")));
		createStats.append(String.format("--- Max. number of rounds: %s ---%n%n", statistics.get("Max. number of rounds")));
		createStats.append(String.format("--- Avg. number of draws: %s ---%n%n", statistics.get("Avg. number of draws")));
		createStats.append(String.format("--- Games won by human player: %s ---%n%n", statistics.get("Games won by human")));
		createStats.append(String.format("--- Games won by AI players: %s ---%n%n", statistics.get("Games won by AI")));
		
		System.out.println(createStats.toString());
	}


}
