package commandline;

import static org.junit.Assert.*;

public class CardTest {

    private Card testCard = new Card("cardy 1 2 3 4 5");
    @org.junit.Test
    public void getDescription() {
        assertEquals("cardy", testCard.getDescription());
    }

    @org.junit.Test
    public void getAtt() {
        assertEquals(1, testCard.getAtt(0));
        assertEquals(2, testCard.getAtt(1));
        assertEquals(3,testCard.getAtt(2));
        assertEquals(4,testCard.getAtt(3));
        assertEquals(5,testCard.getAtt(4));
    }

    @org.junit.Test
    public void getid() {
        testCard.setid(1);
        assertEquals(1,testCard.getid());

    }

    @org.junit.Test
    public void setid() {
        assertEquals(1,testCard.setid(1));
    }
}