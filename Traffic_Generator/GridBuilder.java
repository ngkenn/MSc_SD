/**
 * A GridBuilder object draws the grid as many times as defined
 */
public class GridBuilder implements Runnable {

    /**
     * Instance variables for this class
     */
    private Cell[][] cellArray;
    private int nRows, nCols;
    private int frequency = 20; // The 20 millisecond delay between each time the grid draws
    private int runTimes = 2000; // How many times the grid will be drawn
    private Car activeCar; // The car active in a specific cell
    private String[][] markerArray; //  array of strings with the marker of each cell
    private boolean finished; // Boolean value indicating if the grid has finished drawing


    /**
     * Constructor
     *
     * @param g - Grid object as a parameter
     */
    public GridBuilder(Grid g) {
        this.nCols = g.getCols();
        this.nRows = g.getRows();
        this.cellArray = g.getCellArray();
        this.finished = false;
    }


    /**
     * Method to draw grid
     */
    public void drawGrid() {

        // Print out a divider line each time
        System.out.println("----------------------------------------");

        // Iterate through rows and columns, printing each
        for (int y = 0; y < nRows; y++) {
            for (int x = 0; x < nCols; x++) {
                // Find the active car (if there is one) at cell x, y
                activeCar = cellArray[x][y].getCarInCell();
                // Create a new array of strings populated with the markers
                markerArray = new String[nCols][nRows];

                if (activeCar == null) {
                    markerArray[x][y] = " |";
                } else {
                    markerArray[x][y] = activeCar.getMarker();
                }
                // Print out the array
                System.out.print(markerArray[x][y]);
            }
            System.out.print("\n");
        }
    }


    /**
     * The run method for the GridBuilder
     */
    public void run() {

        // Run for as many times as defined
        for (int i = 0; i < runTimes; i++) {
            try {
                if (i == (runTimes - 1)) {
                    this.finished = true;
                }
                Thread.sleep(frequency);
                drawGrid();
            } catch (InterruptedException e) {
                System.err.println("INTERRUPTED");
                e.printStackTrace();
            }
        }
    }


    public boolean isFinished() {
        if (this.finished) {
            return true;
        } else {
            return false;
        }
    }
}
