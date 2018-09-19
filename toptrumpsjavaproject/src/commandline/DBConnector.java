package commandline;

import java.sql.*;
import java.util.HashMap;

/**
 * This class is responsible for writing  and reading statistics about games
 * which have been played into or from a PostgreSQL database.
 * It contains methods to establish / close a database connection and methods
 * containing SQL queries which write in and read out from the database.
 */

public class DBConnector {

    /*
     * Instance variables containing information necessary to
     * connect with the database.
     */
    private String dBName;
    private String userName;
    private String password;

    //set the connection to null
    private Connection connection = null;

    /**
     * Constructor for creating DBConnection objects.
     * @param dBName the name of the database
     * @param userName the username
     * @param password the relevant password
     */
    public DBConnector (String dBName, String userName, String password) {

        this.dBName=dBName;
        this.userName=userName;
        this.password=password;

    }

    /**
     * Method to establish database connection.
     * @try to get a connection with the database at the specific URL
     * @catch the failed connection exception
     */
    public void connect() {

        try {
            //specify the connection to be used subsequently
            connection =
                    DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/"+dBName,userName,password);
        }

        catch (SQLException e) {
            System.err.println("Connection Failed!");
            e.printStackTrace();
            return;
        }
        //print success message
        if (connection != null) {
          //  System.out.println("Connection successful!");
        }

        else {
            System.err.println("Failed to make connection!"); //report error
        }
    }

    /**
     * Method to close database connection.
     * @try to close the current connection
     * @catch the SQL exception occurring if closing the connection is not possible
     */
    public void closeConnection() {

        try {
            connection.close();
           // System.out.println("Connection closed");
        }

        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DB connection could not be closed."); //report error
        }

    }

    /**
     * Method to write the information about a past game in the database.
     * Can be overloaded depending on the number of opponents (this: 1 opponent).
     * @param winner the winner of the current game
     * @param draws the number of draws in the current game
     * @param rounds rounds the number of rounds played in the current game
     * @param humanRounds the number of rounds won by the human player
     * @param ai1Rounds the number of rounds won by the AI1 opponent
     * @try to execute the specified SQL query
     * @catch handle errors concerning the SQL query
     */
    public void writeToDB(String winner, int draws, int rounds, int humanRounds, int ai1Rounds) {

        Statement stmt;
        //the SQL query used to insert the required information
        String query = "BEGIN;" +
                "INSERT INTO toptrumps.game(winner, numdraws, numrounds) values ('"+winner+"',"+draws+","+rounds+");" +
                "INSERT INTO toptrumps.rounds(gameid, human, ai1) values ((SELECT max(game.gameid) FROM toptrumps.game),"+humanRounds+", "+ai1Rounds+");" +
                "COMMIT;";

        try {
            //pass to and execute the statement in the database
            stmt = connection.createStatement();
            stmt.executeUpdate(query);

        }

        catch (SQLException e ) { //handle error
            e.printStackTrace();
            System.out.println("Error executing query.");

        }

    }

    /**
     * Overloaded method to write the information about a past game in the database (2 opponents).
     * @param winner the winner of the current game
     * @param draws the number of draws in the current game
     * @param rounds rounds the number of rounds played in the current game
     * @param humanRounds the number of rounds won by the human player
     * @param ai1Rounds the number of rounds won by the AI1 opponent
     * @param ai2Rounds the number of rounds won by the AI2 opponent
     * @try to execute the specified SQL query
     * @catch handle errors concerning the SQL query
     */
    public void writeToDB(String winner, int draws, int rounds, int humanRounds, int ai1Rounds, int ai2Rounds) {

        Statement stmt;
        //the SQL query used to insert the required information
        String query = "BEGIN;" +
                "INSERT INTO toptrumps.game(winner, numdraws, numrounds) values ('"+winner+"',"+draws+","+rounds+");" +
                "INSERT INTO toptrumps.rounds(gameid, human, ai1, ai2) values ((SELECT max(game.gameid) FROM toptrumps.game),"+humanRounds+", "+ai1Rounds+","+ai2Rounds+");" +
                "COMMIT;";

        try {
            //pass to and execute the statement in the database
            stmt = connection.createStatement();
            stmt.executeUpdate(query);

        }

        catch (SQLException e ) { //handle error
            e.printStackTrace();
            System.out.println("Error executing query.");

        }

    }

    /**
     * Overloaded method to write the information about a past game in the database (3 opponents).
     * @param winner the winner of the current game
     * @param draws the number of draws in the current game
     * @param rounds rounds the number of rounds played in the current game
     * @param humanRounds the number of rounds won by the human player
     * @param ai1Rounds the number of rounds won by the AI1 opponent
     * @param ai2Rounds the number of rounds won by the AI2 opponent
     * @param ai3Rounds the number of rounds won by the AI3 opponent
     * @try to execute the specified SQL query
     * @catch handle errors concerning the SQL query
     */
    public void writeToDB(String winner, int draws, int rounds, int humanRounds, int ai1Rounds, int ai2Rounds, int ai3Rounds) {

        Statement stmt;
        //the SQL query used to insert the required information
        String query = "BEGIN;" +
                "INSERT INTO toptrumps.game(winner, numdraws, numrounds) values ('"+winner+"',"+draws+","+rounds+");" +
                "INSERT INTO toptrumps.rounds(gameid, human, ai1, ai2, ai3) values ((SELECT max(game.gameid) FROM toptrumps.game),"+humanRounds+", "+ai1Rounds+","+ai2Rounds+", "+ai3Rounds+");" +
                "COMMIT;";

        try {
            //pass to and execute the statement in the database
            stmt = connection.createStatement();
            stmt.executeUpdate(query);

        }

        catch (SQLException e ) { //handle error
            e.printStackTrace();
            System.out.println("Error executing query.");

        }

    }

    /**
     * Overloaded method to write the information about a past game in the database (4 opponents).
     * @param winner the winner of the current game
     * @param draws the number of draws in the current game
     * @param rounds rounds the number of rounds played in the current game
     * @param humanRounds the number of rounds won by the human player
     * @param ai1Rounds the number of rounds won by the AI1 opponent
     * @param ai2Rounds the number of rounds won by the AI2 opponent
     * @param ai3Rounds the number of rounds won by the AI3 opponent
     * @param ai4Rounds the number of rounds won by the AI4 opponent
     * @try to execute the specified SQL query
     * @catch handle errors concerning the SQL query
     */
    public void writeToDB(String winner, int draws, int rounds, int humanRounds, int ai1Rounds, int ai2Rounds, int ai3Rounds, int ai4Rounds) {

        Statement stmt;
        //the SQL query used to insert the required information
        String query = "BEGIN;" +
                "INSERT INTO toptrumps.game(winner, numdraws, numrounds) values ('"+winner+"',"+draws+","+rounds+");" +
                "INSERT INTO toptrumps.rounds(gameid, human, ai1, ai2, ai3, ai4) values ((SELECT max(game.gameid) FROM toptrumps.game),"+humanRounds+", "+ai1Rounds+","+ai2Rounds+", "+ai3Rounds+", "+ai4Rounds+");" +
                "COMMIT;";

        try {
            //pass to and execute the statement in the database
            stmt = connection.createStatement();
            stmt.executeUpdate(query);

        }

        catch (SQLException e ) { //handle error
            e.printStackTrace();
            System.out.println("Error executing query.");

        }

    }

    /**
     * A method to return information about past games to the TopTrumps class.
     * @return a HashMap of the relevant statistical data
     * @try to execute the SQL query and iterate through the result set
     * @catch handle errors concerning the SQL query
     * @try to execute the second SQL query and iterate through the result set
     * @catch handle errors concerning the second SQL query
     */
    public HashMap readFromDB() {

        Statement stmt;

        /*
         * A new HashMap object to contain the obtained data.
         * Stores the information type as a String and its value as an Integer.
         */
        HashMap<String, Integer> statistics = new HashMap<>();
        /*
         * The SQL query used to obtain the required information.
         * Information contained: game number, maximum number of rounds played,
         * the average number of draws.
         */
        String query = "SELECT count(game.gameid), max(game.numrounds), avg(game.numdraws) from toptrumps.game;";

        try {
            //execute query
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            //iterate through the results, get the individual data and put the data into the HashMap
            while (rs.next()) {
                int game_count = rs.getInt("count");
                statistics.put("Number of games", game_count);
                int max_rounds = rs.getInt("max");
                statistics.put("Max. number of rounds", max_rounds);
                double avg_draws = rs.getDouble("avg");
                int avg_draws_int = (int)Math.round(avg_draws); //average will be rounded to integer
                statistics.put("Avg. number of draws", avg_draws_int);
            }

        }

        catch (SQLException e ) { //handle error
            e.printStackTrace();
            System.out.println("Error executing query.");
        }


        Statement stmt2 = null;
        /*
         * The SQL query used to obtain the rest of the required information.
         * Information contained: number of wins by the Human as well as its opponents.
         */
        String query2 = "SELECT sum(case when winner = 'Human Player' then 1 else 0 end) HumanCount, sum(case when winner <> 'Human Player' then 1 else 0 end) AICount from toptrumps.game;";

        try {
            //execute query
            stmt2 = connection.createStatement();
            ResultSet rs = stmt2.executeQuery(query2);

            //iterate through the results, get the individual data and put the data into the HashMap
            while (rs.next()) {
                int human_wins = rs.getInt("humancount");
                statistics.put("Games won by human", human_wins);
                int ai_wins = rs.getInt("aicount");
                statistics.put("Games won by AI", ai_wins);
            }

        }

        catch (SQLException e ) { //handle error
            e.printStackTrace();
            System.out.println("Error executing query.");
        }

        return statistics;

    }

}