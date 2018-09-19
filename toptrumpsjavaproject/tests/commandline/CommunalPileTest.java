package commandline;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CommunalPileTest {

    private CommunalPile  testPile;
    private Card card1, card2;

    @Before
    public void beforeEachTest(){
        testPile = new CommunalPile();
        card1 = new Card("card1 1 2 3 4 5");
        card2 = new Card("card2 6 7 8 9 10");

    }


    @Test
    public void getNumOfCardsInPile() {

    }

    @Test
    public void giveCardsToPile() {
        assertEquals(0, testPile.getNumOfCardsInPile());
        testPile.giveCardsToPile(card1);
        assertEquals(1, testPile.getNumOfCardsInPile());
        testPile.giveCardsToPile(card2);
        assertNotEquals(1, testPile.getNumOfCardsInPile());
        assertEquals(2, testPile.getNumOfCardsInPile());



    }

    @Test
    public void getCardFormPile() {
        testPile.giveCardsToPile(card1);
        testPile.giveCardsToPile(card2);
        assertEquals(card1, testPile.getCardFormPile());
        assertEquals(1,testPile.getNumOfCardsInPile());
        testPile.getCardFormPile();
        assertEquals(0,testPile.getNumOfCardsInPile());
    }

    @Test
    public void getSpecificCard() {
        testPile.giveCardsToPile(card1);
        testPile.giveCardsToPile(card2);

        assertEquals(card1, testPile.getSpecificCard(0));
        assertNotEquals(card1, testPile.getSpecificCard(1));
    }
}