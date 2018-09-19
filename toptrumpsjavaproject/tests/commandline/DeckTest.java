package commandline;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class DeckTest {

    private Deck testDeck;


    //Before each test, create a deck object to be tested
    @Before
    public void beforeEachTest(){
        testDeck = new Deck("HarryPotterDeck.txt");
    }


    @Test
    public void shuffleDeck() {
        assertEquals("HarryPotter",testDeck.deckArray[0].getDescription());
        testDeck.shuffleDeck();
        assertNotEquals("HarryPotter",testDeck.deckArray[0].getDescription());

    }

    @Test
    public void setCategoryNames() {
        testDeck.setCategoryNames("desc cat1 cat2 cat3 cat4 cat5");
        assertEquals("cat1", testDeck.getCategoryArray()[0]);
        assertEquals("cat3", testDeck.getAttName(2));
        assertNotEquals("courage", testDeck.getCategoryArray()[1]);
    }

    @Test
    public void getAttName() {
       assertEquals("magic", testDeck.getAttName(0));
       assertEquals("courage", testDeck.getAttName(1));
       assertEquals("wisdom", testDeck.getAttName(2));
       assertEquals("temper", testDeck.getAttName(3));
       assertEquals("cunning", testDeck.getAttName(4));
       assertEquals("Category does not exist", testDeck.getAttName(8));
    }

    @Test
    public void getCategoryArray() {
        String[] expectedArray = {"magic","courage","wisdom","temper","cunning"};
        assertEquals(expectedArray, testDeck.getCategoryArray());
    }

    @Test
    public void getCategoryIndex() {
        assertEquals(0, testDeck.getCategoryIndex("magic"));
        assertNotEquals(9, testDeck.getCategoryIndex("temper"));
    }
}