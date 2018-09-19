package online.dwResources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import commandline.DBConnector;
import commandline.Game;
import online.configuration.TopTrumpsJSONConfiguration;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import commandline package - access classes needed to play a game

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands
 * to the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create
 * REST API methods in Dropwizard. You will need to replace these with
 * methods that allow a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();

	/**
	 * initialization of the game object
	 */
	Game game;

	/**
	 * Contructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {

	}

	/**
	 * set the game round winner by calling the compareValue method that compares the player's values to
	 * get the round winner
	 */
	private void getRoundResult() {
		//set winner of the round
		game.setRoundWinner(game.compareValue(game.getPlayers(), game.getDeck().getCategoryIndex(game.getChosenCategory())));
		game.incRoundCount(1);
	}

	/**
	 * starts a new game by creating a random number of players that is given to the new game object
	 * it starts the game by creating players, shuffling the deck and dealing the cards to the players
	 * it selects the first starting player
	 */
	@PUT
	@Path("/startGame")
	public void startGame() {
		int numberOfPlayers = 2 + (int)(Math.random() * ((5 - 2) + 1));
		game = new Game(numberOfPlayers);

		//starting a new game
		game.playGame();
		game.getDeck().shuffleDeck();
		game.setUpPlayers();
		game.dealCards();
		game.selectStartingPlayer();
		
	}

	/**
	 * get the current round count from the backend and returns it to the frontend
	 * @return listOfWords
	 * @throws IOException
	 */
	@GET
	@Path("/roundCount")
	public String roundCount() throws IOException {

		List<String> listOfWords = new ArrayList<String>();
		listOfWords.add(Integer.toString(game.getRoundCount()+1));

		return oWriter.writeValueAsString(listOfWords);
	}

	/**
	 * get the current number of players from the backend and returns it to the frontend
	 * @return game.getPlayers().size()
	 * @throws IOException
	 */
	@GET
	@Path("/numOfPlayers")
	public String numOfPlayers() throws IOException {
		return oWriter.writeValueAsString(Integer.toString(game.getPlayers().size()));
	}

	/**
	 * gets all category names from the backend and returns it to the frontend
	 * @return listAsJSONString
	 * @throws IOException
	 */
	@GET
	@Path("/cardCatNames")
	public String cardCatNames() throws IOException {

		List<String> listOfWords = new ArrayList<String>();
		for (int i=0; i<game.getDeck().getCategoryArray().length; i++) {
			listOfWords.add(game.getDeck().getAttName(i));
		}

		String listAsJSONString = oWriter.writeValueAsString(listOfWords);

		return listAsJSONString;
	}

	/**
	 * get the value of each category of the first cards of the user
	 * @return listAsJSONString
	 * @throws IOException
	 */
	@GET
	@Path("/getFirstCardValues")
	public String getFirstCardValues() throws IOException {

		List<String> listOfWords = new ArrayList<String>();
		for (int i=0; i<game.getDeck().getCategoryArray().length; i++) {
			listOfWords.add(Integer.toString(game.getPlayers().get(0).getFirstCard().getAtt(i)));
		}

		String listAsJSONString = oWriter.writeValueAsString(listOfWords);

		return listAsJSONString;
	}


	/*@GET
	@Path("/getNumOfCardsForEachPlayer")
	public String getNumOfCardsForEachPlayer() throws IOException {

		List<String> listOfWords = new ArrayList<String>();
		listOfWords.add(Integer.toString(game.getPlayers().size()));
		for(int i=0; i<game.getPlayers().size(); i++) {
			listOfWords.add(Integer.toString(game.getPlayers().get(i).getNumOfCardsInDeck()));
		}

		String listAsJSONString = oWriter.writeValueAsString(listOfWords);

		return listAsJSONString;
	}*/

	/**
	 *
	 * @return listAsJSONString
	 * @throws IOException
	 */
	@GET
	@Path("/namesOfPlayers")
	public String namesOfPlayers() throws IOException {

		List<String> listOfWords = new ArrayList<>();
		listOfWords.add(Integer.toString(game.getPlayers().size()));
		for(int i=0; i<game.getPlayers().size(); i++) {
			listOfWords.add(game.getPlayers().get(i).getPlayerName());
		}
		listOfWords.add(game.getActivePlayer().getPlayerName());
		String listAsJSONString = oWriter.writeValueAsString(listOfWords);

		return listAsJSONString;
	}

	/**
	 * gets name and number of cards in deck of each player and returns it together to the frontend
	 * @return json
	 * @throws IOException
	 */
	@GET
	@Path("/playerNamesAndNumOfCards")
	public String playerNamesAndNumOfCards() throws IOException {

		ArrayList<String> playersAndCards = new ArrayList<>();
		for(int i=0; i<game.getPlayers().size(); i++) {
			playersAndCards.add(game.getPlayers().get(i).getPlayerName());
			playersAndCards.add(Integer.toString(game.getPlayers().get(i).getNumOfCardsInDeck()));
		}
		String json = new ObjectMapper().writeValueAsString(playersAndCards);
		return json;
	}

	/**
	 * gets category value of each player in the chosen category
	 * deals cards accordingly to the round winner
	 * @return category values of each player
	 * @throws IOException
	 */
	@GET
	@Path("/catValuesOfPlayers")
	public String catValuesOfPlayers() throws IOException {

		List<String> listOfWords = new ArrayList<>();
		for(int i=0; i<game.getPlayers().size(); i++) {
			listOfWords.add(Integer.toString(game.getCategoryValueOfPlayer(game.getPlayers().get(i), game.getDeck().getCategoryIndex(game.getChosenCategory()))));
		}
		String listAsJSONString = oWriter.writeValueAsString(listOfWords);

		//deal cards according to game rules - round winner gets cards or communal pile if isDraw is true
		dealCardsAfterRound();

		return listAsJSONString;
	}

	/**
	 * deal cards according to game rules - round winner gets cards or communal pile if isDraw is true
	 */
	private void dealCardsAfterRound() {
		if (game.isDraw()) {
			game.incNumOfDraws(1);
			for (int i = 0; i < game.getPlayers().size(); i++) {
				game.getCommunalPile().giveCardsToPile(game.getPlayers().get(i).loseCard());
			}
			game.setDraw(false);
		} else {
			for (int i = 0; i < game.getPlayers().size(); i++) {
				game.getRoundWinner().receiveCard(game.getPlayers().get(i).loseCard());

				while (game.getCommunalPile().getNumOfCardsInPile() > 0) {
					game.getRoundWinner().receiveCard(game.getCommunalPile().getCardFormPile());
				}
			}
			game.setActivePlayer(game.getRoundWinner());
			game.getActivePlayer().increaseNumOfRoundsWon();
		}

		game.updatePlayer();

		if (game.getPlayers().size() < 2){
            game.setGameWinner(game.getPlayers().get(0));
			game.getRoundsWon().put(game.getGameWinner().getPlayerName(), game.getGameWinner().getNumOfRoundsWon());
        }

	}

	/**
	 * gets the current active player from the backend and returns it to the frontend
	 * @return activePlayer
	 * @throws IOException
	 */
	@PUT
	@Path ("/endGameWithoutHumanPlayer")
	public String endGameWithoutHumanPlayer() throws IOException {
		while (game.getPlayers().size() > 1) {
			if(game.getPlayers().size() == 1) {
				break;
			}
			game.setChosenCategory(game.getActivePlayer().chooseCategory(game.getDeck().getCategoryArray()));
			getRoundResult();
			dealCardsAfterRound();
		}
		game.setGameWinner(game.getPlayers().get(0));

		return oWriter.writeValueAsString(game.getGameWinner().getPlayerName());

	}

	/**
	 * gets the current active player from the backend and returns it to the frontend
	 * @return activePlayer
	 * @throws IOException
	 */
	@GET
	@Path ("/activePlayer")
	public String activePlayer() throws IOException {
		return oWriter.writeValueAsString(game.getActivePlayer().getPlayerName());
	}

	/**
	 * lets the backend set the chosen category by letting the current active Player choose a category
	 * @return chosenCat
	 * @throws IOException
	 */
	@PUT
	@Path ("/getAIchosenCategory")
	public String getAIchosenCategory() throws IOException {
		game.setChosenCategory(game.getActivePlayer().chooseCategory(game.getDeck().getCategoryArray()));
		String chosenCat = game.getChosenCategory();
		getRoundResult();
		
		return oWriter.writeValueAsString(chosenCat);
	}

	/**
	 * gets the chosen category from the frontend and lets the backend set the chosen category
	 * it returns the chosen category to the frontend
	 * @param category
	 * @return category
	 * @throws IOException
	 */
	@PUT
	@Path("/humanPlayerChosenCategory")
	public String humanPlayerChosenCategory(@QueryParam("category") String category) throws IOException {
		game.setChosenCategory(category);
		System.out.println(category);
		getRoundResult();
		System.out.println(game.getRoundWinner());
		return oWriter.writeValueAsString(category);
	}

	/**
	 * gets the description of the first cards in the user's deck
	 * @return first card description
	 * @throws IOException
	 */
	@GET
	@Path ("/getFirstCardDescription")
	public String getFirstCardDescription() throws IOException {
		return oWriter.writeValueAsString(game.getPlayers().get(0).getFirstCard().getDescription());
	}

	/**
	 * gets the round winner from the backend and returns it to the frontend
	 * @return round winner
	 * @throws IOException
	 */
	@GET
	@Path("/getRoundWinner")
	public String getRoundWinner() throws IOException {
		if (null != game.getRoundWinner()){
		return oWriter.writeValueAsString(game.getRoundWinner().getPlayerName());
		}
		else {
			return oWriter.writeValueAsString("none");
		}
	}

	/**
	 * gets the number of cards in the communal pile from backend and sends it to the frontend
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/numCardsInComPile")
	public String numCardsInComPIle() throws IOException {
		List<String> listOfWords = new ArrayList<>();
		listOfWords.add(Integer.toString(game.getCommunalPile().getNumOfCardsInPile()));

		String listAsJSONString = oWriter.writeValueAsString(listOfWords);

		return listAsJSONString;
	}

	/**
	 * gets the overall game winner and returns it to the frontend
	 * @return game winner
	 * @throws IOException
	 */
	@GET
	@Path("/getGameWinner")
	/**
	 *
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String getGameWinner () throws IOException, InterruptedException {
		Thread.sleep(1000);
		return oWriter.writeValueAsString(game.getGameWinner().getPlayerName());
	}


	//Write game statistics to Database

	/**
	 * calls the writeToDB method
	 */
	@PUT
	@Path("/writeToDB")
	public void writeToDB() {
		game.writeToDatabase();
	}

	//METHODS TO UPDATE STATISTICS VIEW

	@GET
	@Path("/getTotalGame")
	/**
	 * get the total number of games played from the database connection 
	 * @return total number of games played
	 * @throws IOException
	 */
	public String getTotalGame() throws IOException {

		DBConnector dBStats = new DBConnector("m_17_2341731l", "m_17_2341731l", "2341731l");
		dBStats.connect();
		HashMap statistics = dBStats.readFromDB();
		String str = String.valueOf(statistics.get("Number of games"));
		dBStats.closeConnection();

		return oWriter.writeValueAsString(str);
	}
	
	@GET
	@Path("/getAverageDraw")
	/**
	 * get the average number of draws displaying as String ((total number of draws) / (total number of games))
	 * @return average number of draws 
	 * @throws IOException
	 */
	public String getAverageDraw() throws IOException {

		DBConnector dBStats = new DBConnector("m_17_2341731l", "m_17_2341731l", "2341731l");
		dBStats.connect();
		HashMap statistics = dBStats.readFromDB();
		String str = String.valueOf(statistics.get("Avg. number of draws"));
		dBStats.closeConnection();

		return oWriter.writeValueAsString(str);
	}
	
	@GET
	@Path("/getHighestNumberOfRound")
	/**
	 * get the highest ever number of rounds in a single game 
	 * @return highest number of round
	 * @throws IOException
	 */
	public String getHighestNumberOfRound() throws IOException {

		DBConnector dBStats = new DBConnector("m_17_2341731l", "m_17_2341731l", "2341731l");
		dBStats.connect();
		HashMap statistics = dBStats.readFromDB();
		String str = String.valueOf(statistics.get("Max. number of rounds"));
		dBStats.closeConnection();

		return oWriter.writeValueAsString(str);
	}
	
	@GET
	@Path("/getHumanWin")
	/**
	 * get the total number of the human wins the game
	 * @return total number of human wins
	 * @throws IOException
	 */
	public String getHumanWin() throws IOException {

		DBConnector dBStats = new DBConnector("m_17_2341731l", "m_17_2341731l", "2341731l");
		dBStats.connect();
		HashMap statistics = dBStats.readFromDB();
		String str = String.valueOf(statistics.get("Games won by human"));
		dBStats.closeConnection();

		return oWriter.writeValueAsString(str);
	}
	
	@GET
	@Path("/getAIWin")
	/**
	 * get the total number of the AI wins the game
	 * @return total number of AI wins
	 * @throws IOException
	 */
	public String getAIWin() throws IOException {

		DBConnector dBStats = new DBConnector("m_17_2341731l", "m_17_2341731l", "2341731l");
		dBStats.connect();
		HashMap statistics = dBStats.readFromDB();
		String str = String.valueOf(statistics.get("Games won by AI"));
		dBStats.closeConnection();

		return oWriter.writeValueAsString(str);
	}
	
	
}
