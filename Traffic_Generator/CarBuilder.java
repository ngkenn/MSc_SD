import java.util.ArrayList;

/**
 * The CarBuilder class is the traffic generator for AESpec1.
 * Cars are generated going in one of 2 directions:
 * From West to East
 * From North to South
 */
public class CarBuilder implements Runnable {

    /**
     * Instance Variables
     */
    private Grid grid;
    private ArrayList<Car> carArrayList; // ArrayList of Cars to keep track of all cars built (for the reporting)
    private boolean isActive; // True if car is active, false if not
    private GridBuilder gridBuilder;

    // The minimum and maximum speeds. This number corresponds to milliseconds
    // between the car moving cell (therefore max speed would be a low number)
    private final int maxSpeed = 100;
    private final int minSpeed = 400;

    /**
     * Constructor
     *
     * @param g - Grid
     */
    public CarBuilder(Grid g, GridBuilder gb) {
        this.grid = g;
        this.gridBuilder = gb;
        isActive = true;
        carArrayList = new ArrayList<>();
    }


    /**
     * Method to create a car of random direction
     *
     * @return - The new car object
     */
    public Car createCar() {
        // Choose the direction
        String direction = directionChooser();
        // Create new car from the random direction
        Car newCar = new Car(grid, direction, gridBuilder);
        // Add the car to the arraylist to keep track
        carArrayList.add(newCar);

        // Initialise the co-ordinates, generate a random speed
        int xPos = 0;
        int yPos = 0;
        String marker = "";
        int speed = maxSpeed + (int) (Math.random() * ((minSpeed - maxSpeed) + 1));

        // Based on the direction, get initial co-ordinates and marker
        if (direction.equals("N")) {
            xPos = (int) (Math.random() * (grid.getCols() - 1));
            yPos = 0;
            marker = "o|";
        } else if (direction.equals("W")) {
            xPos = 0;
            yPos = (int) (Math.random() * (grid.getRows() - 1));
            marker = "-|";
        }

        // Set the co-ordinates, speed and marker for the new car
        newCar.setCoordinates(xPos, yPos);
        newCar.setMarker(marker);
        newCar.setSpeed(speed);

        return newCar;

    }

    /**
     * Select either North or South direction on a random basis
     * Method moved from Car Class to make direction choosing Generator specific for AESpec2
     *
     * @return - String denoting the direction
     */
    public String directionChooser() {
        // Choose either 1 or 2 randomly
        // Integer r  corresponds to a direction
        int r = (int) (Math.random() * 2 + 1);
        String direction = "";

        if (r == 1) {
            direction = "N";
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

        while (isActive) {

            if (this.gridBuilder.isFinished()) {
                stopBuilding();
            }
            try {
                // Use Thread.Sleep to slightly slow down the build rate of cars
                Thread.sleep(100);

                // Generate new cars, add to grid and start them on a thread
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
}
