import java.util.ArrayList;


/**
 * Class to generate traffic going from East to West & vice versa
 * for part 2 of the assignment
 */

public class EWGenerator implements Runnable {
    /**
     * Instance Variables
     */
    private Grid grid;
    private ArrayList<Car> carArrayList; // ArrayList of Cars to keep track of all cars built
    private boolean isActive; // True if car is active, false if not
    private GridBuilder gridBuilder;

    // How many lanes would be occupied by cars coming from a certain direction
    // Set in the constructor
    private int ELanes;


    // The minimum and maximum speeds. This number corresponds to milliseconds
    // between the car moving cell (therefore max speed would be a low number)
    private final int maxSpeed = 200;
    private final int minSpeed = 500;


    /**
     * Constructor
     *
     * @param g - Grid
     */
    public EWGenerator(Grid g, GridBuilder gb) {
        this.grid = g;
        this.gridBuilder = gb;
        isActive = true;
        carArrayList = new ArrayList<>();
        ELanes = g.getRows() / 2;
    }


    /**
     * Method to create a car of random direction
     *
     * @return - the car object created
     */
    public Car createCar() {
        // Choose the direction and create a new car
        String direction = directionChooser();
        Car newCar = new Car(grid, direction, gridBuilder);
        carArrayList.add(newCar);

        // Initialise co-ordinates and marker
        int xPos = 0;
        int yPos = 0;
        String marker = "";

        // Choose a random speed between the max and min
        int speed = maxSpeed + (int) (Math.random() * ((minSpeed - maxSpeed) + 1));

        // Maximum possible y co-ordinate for the grid
        int yMax = grid.getRows() - 1;

        // Get the initial co-ordinates and marker
        // It is assumed that all lanes will be used
        if (direction.equals("W")) {
            xPos = 0;
            yPos = (int) (Math.random() * ELanes);
            marker = "-|";
        } else if (direction.equals("E")) {
            xPos = grid.getCols() - 1;
            yPos = ELanes + (int) (Math.random() * ((yMax - ELanes) + 1));
            marker = "*|";
        }

        // Set the initial co-ordinates, marker and speed for the new car
        newCar.setCoordinates(xPos, yPos);
        newCar.setMarker(marker);
        newCar.setSpeed(speed);

        return newCar;

    }

    /**
     * Select either North or South direction on a random basis
     * Method moved from Car Class to make direction choosing Generator specific for AESpec2
     *
     * @return - A string denoting the diretion of the car
     */
    public String directionChooser() {
        // Choose either 1 or 2 randomly
        // Integer r  corresponds to a direction
        int r = (int) (Math.random() * 2 + 1);
        String direction = "";

        if (r == 1) {
            direction = "E";
        } else if (r == 2) {
            direction = "W";
        }

        return direction;
    }

    /**
     * Method to stop carBuilder building cars (called when grid has finished drawing)
     */
    public void stopBuilding() {
        this.isActive = false;
    }


    /**
     * Run Method, keeps building cars while the builder is active
     */
    public void run() {
        // Keep generating cars while active. Deactivates when the Grid has stopped drawing.
        while (isActive) {

            if (this.gridBuilder.isFinished()) {
                stopBuilding();
            }

            try {
                // Use Thread.Sleep to slightly slow down the build rate of cars
                Thread.sleep(300);
                // Create a new car, add it to grid and start on a thread
                Car c = createCar();
                c.addCar();
                Thread t = new Thread(c);
                t.start();
            } catch (Exception e) {
                System.err.println("INTERRUPTED");
                e.printStackTrace();
            }
        }
    }

    /**
     * Getter for the car array list
     *
     * @return - the array list of cars created
     */
    public ArrayList<Car> getCarArrayList() {
        return carArrayList;
    }
}
