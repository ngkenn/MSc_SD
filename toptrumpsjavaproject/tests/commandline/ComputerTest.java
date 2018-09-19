package commandline;

import org.junit.Test;

import static org.junit.Assert.*;

public class ComputerTest {

    @Test
    public void chooseCategory() {

        //Create the category array, and a computer object with a personal deck to test
        String[] categoryNames = {"att1","att2","att3","att4","att5"};
        Computer testComp = new Computer("AI Player 1");
        Card card1 = new Card("card1 1 2 3 4 5");
        testComp.setPersonalDeck(card1);

        // Since chooseCategory selects the highest number category for computer,
        //  and card1 is being tested - the selected category should be att5
        assertEquals("att5",testComp.chooseCategory(categoryNames));


    }
}