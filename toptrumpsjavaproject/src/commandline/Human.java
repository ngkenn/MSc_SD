package commandline;

import java.util.Scanner;

public class Human extends Player{

    //create scanner object for user input over the command line
    Scanner scanner = new Scanner(System.in);

    /**
     * Constructor method for human players
     * @param playerName
     */
    public Human(String playerName) {
        super(playerName);
    }



    /**
     * Lets the human player choose a category by displaying the first card in his deck in the command line
     * @return categoryChosen
     */
   public String chooseCategory(String [] categoryNames) {
        String categoryChosen = "noCategoryChosen";

        System.out.printf("%n--------------------------%n%n--- Choose a category: ---%n");
        System.out.printf("%n--------------------------%nCard: %S%n--------------------------%n", this.getFirstCard().getDescription());

        for (int i=0; i<5; i++){
            System.out.printf("%s: %d%n", categoryNames[i], this.getFirstCard().getAtt(i));
        }

        boolean inputTrue = false;

        while (!inputTrue) { //retrieve user input and check if it is valid
            String userInput = scanner.next();
            userInput = userInput.toLowerCase();
            if (categoryNames[0].toLowerCase().equals(userInput) || categoryNames[1].toLowerCase().equals(userInput)
                    || categoryNames[2].toLowerCase().equals(userInput) || categoryNames[3].toLowerCase().equals(userInput)
                    || categoryNames[4].toLowerCase().equals(userInput)) {
                categoryChosen = userInput;

                inputTrue = true;

            } else {
                System.out.println("Wrong input! Try again");
            }
        }
        return categoryChosen;
    }


    /**
     *  additional method which takes a scanner input (required for the JUnit Test)
     * @param categoryNames
     * @param scan
     * @return
     */
    public String chooseCategory(String [] categoryNames, Scanner scan) {
        String categoryChosen = "noCategoryChosen";

        System.out.printf("%n--------------------------%n%n--- Choose a category: ---%n");
        System.out.printf("%n--------------------------%nCard: %S%n--------------------------%n", this.getFirstCard().getDescription());

        for (int i=0; i<5; i++){
            System.out.printf("%s: %d%n", categoryNames[i], this.getFirstCard().getAtt(i));
        }

        boolean inputTrue = false;

        while (!inputTrue) {
            String userInput = scan.next();
            userInput = userInput.toLowerCase();
            if (categoryNames[0].toLowerCase().equals(userInput) || categoryNames[1].toLowerCase().equals(userInput)
                    || categoryNames[2].toLowerCase().equals(userInput) || categoryNames[3].toLowerCase().equals(userInput)
                    || categoryNames[4].toLowerCase().equals(userInput)) {
                categoryChosen = userInput;

                inputTrue = true;

            } else {
                System.out.println("Wrong input! Try again");
            }
        }
        return categoryChosen;
    }


}