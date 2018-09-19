package commandline;

import org.junit.Before;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTest {

    Human testHuman = null;
    Computer testComp = null;
    Card card1, card2;

    @Before
    public void beforeEachTest(){
        testHuman = new Human("Bob");
        testComp = new Computer("ai");

        card1 = new Card("card1 1 2 3 4 5");
        card2 = new Card("card2 6 7 8 9 10");

        testHuman.setPersonalDeck(card1);
        testHuman.setPersonalDeck(card2);
    }


    @org.junit.Test
    public void getPlayerName() {
        assertEquals("Bob", testHuman.getPlayerName());
        assertEquals("ai", testComp.getPlayerName());

    }

    @org.junit.Test
    public void setPersonalDeck() {

    }

    @org.junit.Test
    public void receiveCard() {
        testHuman.receiveCard(card1);
        assertEquals(card1, testHuman.getFirstCard());
    }

    @org.junit.Test
    public void increaseNumOfRoundsWon() {
        testHuman.increaseNumOfRoundsWon();
        assertEquals(1,testHuman.getNumOfRoundsWon());
    }

    @org.junit.Test
    public void loseCard() {
        //Original deck has 2 cards, after losing the 1st card, there should be 1 card (card2)
        testHuman.loseCard();
        assertEquals(1, testHuman.getNumOfCardsInDeck());
        assertEquals(card2, testHuman.getFirstCard());
    }

    @org.junit.Test
    public void getFirstCard() {
        assertEquals(card1, testHuman.getFirstCard());
    }

    @org.junit.Test
    public void getNumOfCardsInDeck() {
        assertEquals(2, testHuman.getNumOfCardsInDeck());
    }

    @org.junit.Test
    public void getNumOfRoundsWon() {
        assertEquals(0,testHuman.getNumOfRoundsWon());
    }

    @org.junit.Test
    public void getCardAtIndex() {
        assertEquals(card2,testHuman.getCardAtIndex(1));
        assertNotEquals(card2,testHuman.getCardAtIndex(0));
    }
}