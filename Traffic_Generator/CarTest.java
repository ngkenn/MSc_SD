import org.junit.Test;

import static org.junit.Assert.*;

public class CarTest {

    Car car1, car2;
    Grid testGrid;
    GridBuilder testGridBuilder;
    Cell testCell;

    @org.junit.Before
    public void setUp() throws Exception {
        testGrid = new Grid(5,5);
        testGridBuilder = new GridBuilder(testGrid);
        car1 = new Car(testGrid, "W", testGridBuilder);
        car2 = new Car(testGrid, "N", testGridBuilder);

        testCell = testGrid.getCellAt(1,1);
    }

    @Test
    public void occupyTest(){
        // First, set car 1 to occupy testCell
        testCell.enterCell(car1);
        // Test that car1 is in testCell
        assertEquals(car1, testCell.getCarInCell());

        // Now try and set car 1 to occupy cell, should be invalid
       // testCell.enterCell(car2);
       // assertEquals(car1,testCell.getCarInCell());

        testCell.clearCell();
        assertNull(testCell.getCarInCell());

        testCell.enterCell(car2);
        assertEquals(car2,testCell.getCarInCell());


    }
}