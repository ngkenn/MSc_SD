package commandline;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * This class is responsible for writing a detailed log of the program's operations
 * when it is run in command line mode, in order to facilitate debugging.
 * It produces an output file called toptrumps.log in the same directory as the program is run in.
 */
public class LogWriter {

    /*
     * Instance variables of LogWriter class consisting of
     * a line separator String as well as Logger [and FileHandler] objects.
     */
    private static final Logger topTrumpsLogger = Logger.getLogger("commandline.game");
    private static FileHandler logFileHandler;
    private final String lineSeparator = "\r\n-----------------------------------------------------\r\n";

    /**
     * Constructor, should only be called when program is run in command line mode.
     * @try to instantiate FileHandler and SimpleFormatter objects
     * @catch security exception se
     * @catch IOException ioe (file cannot be found etc.)
     */
    public LogWriter() {

        try {

            // instantiate the FileHandler and define file storage location
            logFileHandler = new FileHandler("toptrumps.log");
            topTrumpsLogger.addHandler(logFileHandler);

            // needed to format the output as log
            SimpleFormatter formatter = new SimpleFormatter();
            logFileHandler.setFormatter(formatter);

            // this line disables the log output to the console.
            topTrumpsLogger.setUseParentHandlers(false);

        }

        catch (SecurityException se) {
            se.printStackTrace();
        }

        catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    /**
     * Write the contents of the current deck (initial OR shuffled) to the log
     * @param deck the (initial) deck of cards
     */
    public void writeDeckInfo(Deck deck) {

        //instantiate new StringBuilder
        String deckString = "\r\n\r\nThe current deck:\r\n";
        StringBuilder deckBuilder = new StringBuilder(deckString);

        //append each card in the deck
        for (int i = 0; i < Deck.deckSize; i++) {
            deckBuilder.append(writeCard(deck, deck.deckArray[i]));
        }

        //write them as an info message into the logfile
        topTrumpsLogger.info(deckBuilder.toString() + lineSeparator);

    }

    /**
     * Write the contents of a player's deck to the log
     * @param deck the (initial) deck of cards
     * @param game the game currently played
     */
    public void writePlayerDeckInfo(Deck deck, Game game) {

        //instantiate new StringBuilder
        String playerDeckString = "\r\n\r\nThe current deck contents are:\r\n";
        StringBuilder playerDeckBuilder = new StringBuilder(playerDeckString);

        //write each player's name
        for (int i = 0; i < game.players.size(); i++) {

            playerDeckBuilder.append("\r\nPlayer " + game.players.get(i).getPlayerName() + ": ");

            //and their according cards
            for (int j = 0; j < game.players.get(i).getNumOfCardsInDeck(); j++) {
                playerDeckBuilder.append(writeCard(deck, game.players.get(i).getCardAtIndex(j)));
            }

        }

        //write them as an info message into the logfile
        topTrumpsLogger.info(playerDeckBuilder.toString() + lineSeparator);

    }

    /**
     * Write the contents of the communal pile to the log
     * @param deck the (initial) deck of cards
     * @param communalPile the communal pile
     */
    public void writeCommunalPile(Deck deck, CommunalPile communalPile) {

        //instantiate new StringBuilder
        String pileString = "\r\n\r\nThe content of the communal pile is:\r\n";
        StringBuilder pileDeckBuilder = new StringBuilder(pileString);

        //write each separate card in the communal pile
        for (int i = 0; i < communalPile.getNumOfCardsInPile(); i++) {
            pileDeckBuilder.append(writeCard(deck, communalPile.getSpecificCard(i)));
        }

        //write them as an info message into the logfile
        topTrumpsLogger.info(pileDeckBuilder.toString() + lineSeparator);

    }

    /**
     * Write the contents of the current cards in play to the log
     * @param deck the (initial) deck of cards
     * @param game game currently played
     */
    public void writeCurrentCards(Deck deck, Game game) {

        //instantiate new StringBuilder
        String currentCards = "\r\n\r\nThe contents of the current cards in play:\r\n\r\n";
        StringBuilder currentCardsBuilder = new StringBuilder(currentCards);

        //write each card currently in the game separately
        for (int i = 0; i < game.players.size(); i++) {
            currentCardsBuilder.append(writeCard(deck, game.players.get(i).getFirstCard()));
        }

        //write them as an info message into the logfile
        topTrumpsLogger.info(currentCardsBuilder.toString() + lineSeparator);

    }

    /**
     * Write the category and corresponding values selected for the current round to the log
     * @param deck the (initial) deck of cards
     * @param game the game currently played
     */
    public void writeCategoryValues(Deck deck, Game game) {

        //instantiate new StringBuilder
        String currentCategoryValues = "\r\n\r\nThe corresponding values for the category " + game.chosenCategory + " are:\r\n\r\n";
        StringBuilder currentCategoryBuilder = new StringBuilder(currentCategoryValues);

        //obtain the relevant category number
        int categoryNum = deck.getCategoryIndex(game.chosenCategory);

        //write each player's name and value for the respective category
        for (int i = 0; i < game.players.size(); i++) {
            currentCategoryBuilder.append("\r\nPlayer " + game.players.get(i).getPlayerName() + ": ");
            currentCategoryBuilder.append(game.players.get(i).getFirstCard().getAtt(categoryNum) + "\r\n");
        }

        //write them as an info message into the logfile
        topTrumpsLogger.info(currentCategoryBuilder.toString() + lineSeparator);

    }

    /**
     * Write the game's winner to the log
     * @param winner the winner of the game (defined in Game class)
     */
    public void writeWinner(String winner) {

        //write them as an info message into the logfile
        topTrumpsLogger.info("\r\n\r\nThe winner of the game is: " + winner);

    }

    /**
     * Create a String containing the (formatted) contents of a single card
     * @param deck the generic deck of cards
     * @param card the card to be printed
     * @return the single card as a String
     */
    private String writeCard(Deck deck, Card card) {

        //instantiate new StringBuilder
        String singleCard = "\r\n---------------\r\n" + card.getDescription() + "\r\n---------------\r\n";
        StringBuilder cardBuilder = new StringBuilder(singleCard);

        //append each attribute name
        for (int i = 0; i < 5; i++) {
            cardBuilder.append(deck.getAttName(i) + ": " + card.getAtt(i) + "\r\n");
        }

        return cardBuilder.toString();
    }

    /**
     * Close the logFileHandler object after finishing writing to the toptrumps.log file.
     * Otherwise the old file might not be overwritten (apparently a bug in java 8).
     */
    public void closeFileHandler() {

        logFileHandler.close();

    }

}