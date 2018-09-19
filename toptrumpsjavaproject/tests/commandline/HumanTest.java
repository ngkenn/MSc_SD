package commandline;

import org.junit.Test;
import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.*;

public class HumanTest {

    @Test
    public void chooseCategory() {

        // Create mock objects for testing
        String [] categoryArray = {"magic","courage", "wisdom", "temper", "cunning"};

        Human testHuman = new Human("Human Player");
        Card card1 = new Card("card1 1 2 3 4 5");
        testHuman.setPersonalDeck(card1);


        // Create a scanner with a mock system.in
        String data = "courage";
        InputStream mockIn = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);
        System.setIn(mockIn);

        String result = testHuman.chooseCategory(categoryArray, scanner);

        // Run a test with the mock system.in "courage"
        assertEquals("courage", result);
        assertNotEquals("magic", result);

    }
}