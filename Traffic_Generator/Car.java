/**
 * The car class allows the functionality of a single car.
 * Multiple car objects can be created in a traffic generator.
 * A car object is flexible and can have various attributes such as direction as set in the traffic generator
 */

public class Car implements Runnable {

    /**
     * Instance variables
     */
    private int xPos, yPos; // The co-ordinates
    private String marker; // The character to be printed on the grid
    private String dir; // The direction. N means FROM the North etc
    private Grid grid;  // A grid object is passed so the car object has rows and cols information
    private int speed; // Randomly generated speed
    private int nRows, nCols;
    public boolean isActive; // Boolean to check if car is on the grid or not
    private GridBuilder gridBuilder; // GridBuilder object to allow cars to be deactivated when GridBuilder is finished


    // The start time and end time for each car is recorded so that
    // time on grid can be calculated
    private long startTime;
    private long endTime;
    private long timeOnGrid;


    /**
     * Constructor
     */
    public Car(Grid g, String direction, GridBuilder gb) {
        this.grid = g;
        this.gridBuilder = gb;
        this.nRows = g.getRows();
        this.nCols = g.getCols();
        this.dir = direction;
    }


    /**
     * Method which moves the car to the next cell based on direction
     */
    public void driveCar() {
        //If the car has fallen off the grid, remove it from Grid
        if (this.checkIfFallenOff() == true) {
            grid.getCellAt(xPos, yPos).clearCell();
            deactivateCar();
            // Print out if a car has fallen off (for testing)
          //  System.err.println("Car at: "+xPos+", "+yPos+"  has fallen off the grid");
        }

        // Otherwise Increment or decrement the co-ordinate based on the direction
        else {

            if (this.dir.equals("N")) {
                grid.getCellAt(xPos, yPos+1).enterCell(this);
                grid.getCellAt(xPos, yPos).clearCell();
                this.yPos++;

            } else if (this.dir.equals("W")) {
                grid.getCellAt(xPos+1, yPos).enterCell(this);
                grid.getCellAt(xPos, yPos).clearCell();
                this.xPos++;


            } else if (this.dir.equals("S")) {
                grid.getCellAt(xPos, yPos-1).enterCell(this);
                grid.getCellAt(xPos, yPos).clearCell();
                this.yPos--;

            } else if (this.dir.equals("E")) {
                grid.getCellAt(xPos-1, yPos).enterCell(this);
                grid.getCellAt(xPos, yPos).clearCell();
                this.xPos--;

            }
        }
    }

    /**
     * Method to check if car has reached the end of the grid
     *
     * @return true if the car has reached the end of the grid, false if still on grid
     */
    public boolean checkIfFallenOff() {
        if (dir.equals("N")) {
            if (yPos >= nRows - 1) {
                return true;
            }
        } else if (dir.equals("W")) {
            if (xPos >= nCols - 1) {
                return true;
            }
        } else if (dir.equals("S")) {
            if (yPos <= 0) {
                return true;
            }
        } else if (dir.equals("E")) {
            if (xPos <= 0) {
                return true;
            }
        }
        return false;
    }


    /**
     * Method called to initialise the car by placing on the grid
     */
    public void addCar() {
        grid.getCellAt(xPos, yPos).enterCell(this);
        this.isActive = true;
        startTime = System.currentTimeMillis();

    }

    /**
     * Method to deactivate the car and record the time
     */
    public void deactivateCar() {
        this.isActive = false;
        endTime = System.currentTimeMillis();
        timeOnGrid = endTime - startTime;
    }


    /**
     * Run Method
     */
    public void run() {
        // Run as long as the car is active

        while (isActive) {
            if (this.gridBuilder.isFinished()) {
                deactivateCar();
            }
            try {
                Thread.sleep(speed);
                driveCar();

            } catch (InterruptedException e) {
                System.err.println("INTERRUPTED");
                e.printStackTrace();

            }
        }

        // Once a car is no longer active, calculate the timeOnGrid
        endTime = System.currentTimeMillis();
        timeOnGrid = endTime - startTime;
    }


    /**
     * Setter for x and y co-ordinates
     * Required to allow cars to be created from different generators/builders with different
     * numbers of lanes.
     * Part of AESpec2
     *
     * @param x
     * @param y
     */
    public void setCoordinates(int x, int y) {
        this.xPos = x;
        this.yPos = y;
    }

    /**
     * Setter for marker - avoids code duplication
     *
     * @param m
     */
    public void setMarker(String m) {
        this.marker = m;
    }

    /**
     * Setter for speed - for extendability
     *
     * @param s
     */
    public void setSpeed(int s) {
        this.speed = s;
    }


    /**
     * Getter for marker character
     *
     * @return the character to be drawn on the grid
     */
    public String getMarker() {
        return this.marker;
    }


    /**
     * Method to return the time a car was active
     *
     * @return
     */
    public long getTimeOnGrid() {
        return timeOnGrid;
    }

    /**
     * Getters for the co-ordinates, used for testing in System.outs
     * @return
     */
    public int getXPosition(){
        return this.xPos;
    }
    public int getYPosition(){
        return this.yPos;
    }

}



