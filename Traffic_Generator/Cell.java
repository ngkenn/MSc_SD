import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Each Cell of the grid is an object
 * This makes it easier to enforce the locks
 * A Grid object has a 2D array of Cell objects
 */
public class Cell {

    private Car carInCell; // The car currently in this cell, can be null
    private boolean isEmpty; // True if cell is empty, false if there is a car

    // Lock and condition to ensure cell is only accessed by 1 car
    private ReentrantLock lockCell = new ReentrantLock();
    private Condition cellEmptyCondition = lockCell.newCondition();

    /**
     * Constructor which builds an empty Cell
     */
    public Cell() {
        this.carInCell = null;
        this.isEmpty = true;
    }

    /**
     * Method to place a car in the current cell
     *
     * @param c - The car to occupy a cell
     */
    public void enterCell(Car c) {
        lockCell.lock();
        try {
            // if the cell is currently occupied, wait
            while (!isEmpty) {
                cellEmptyCondition.await();
                //Print out if a car is awaiting at certain co-ordinates (for testing)
             //   System.err.println("Car waiting at cell: "+ c.getXPosition()+", "+c.getYPosition());
            }
            // When the cell is clear, place this car in the cell
            this.isEmpty = false;
            this.carInCell = c;
        } catch (InterruptedException e) {
            System.err.println("INTERRUPTED");
            e.printStackTrace();
        } finally {
            lockCell.unlock();
        }
    }

    /**
     * Method to clear a cell once a car has moved on
     */
    public void clearCell() {
        lockCell.lock();
        try {
            // When clearCell is called, set isEmpty to true, carInCell to null
            // and signal that the cellEmptyCondition has been met so that
            // another car can enter the cell
            this.isEmpty = true;
            this.carInCell = null;
            cellEmptyCondition.signalAll();
        } catch (Exception e) {
            System.err.println("OTHER EXCEPTION");
            e.printStackTrace();
        } finally {
            lockCell.unlock();
        }
    }

    /**
     * Getter for returning the car in this cell
     *
     * @return The car currently in the cell
     */
    public Car getCarInCell() {
        return this.carInCell;
    }


}
