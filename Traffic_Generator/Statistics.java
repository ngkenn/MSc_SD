import java.util.ArrayList;

/**
 * A statistics object gets the information on cars for each generator,
 * and creates a statistics report which is printed to the console.
 */
public class Statistics {

    private EWGenerator EWGen;
    private NSGenerator NSGen;

    /**
     * Constructor
     *
     * @param ew - an EWGenerator object
     * @param ns - a NSGenerator object
     */
    public Statistics(EWGenerator ew, NSGenerator ns) {
        this.EWGen = ew;
        this.NSGen = ns;
    }


    /**
     * Method to produce a string report for the East West Generator
     *
     * @param gen - the EWGenerator object
     * @return - the report as a string
     */
    public String EWGenReport(EWGenerator gen) {
        ArrayList<Car> carArrayList = gen.getCarArrayList();
        StringBuilder sb = new StringBuilder();
        sb.append("East West Car Report");

        long maxTime = 0;
        long minTime = 0;
        long meanTime = 0;
        long totalTime = 0;
        long variance = 0;

        for (int i = 0; i < carArrayList.size(); i++) {

            Car c = carArrayList.get(i);
            long timeOnGrid = c.getTimeOnGrid();
            totalTime += timeOnGrid;

            if (i == 0) {
                maxTime = timeOnGrid;
                minTime = timeOnGrid;
            }

            // Check for new max or min
            else if (timeOnGrid > maxTime) {
                maxTime = timeOnGrid;
            } else if (timeOnGrid < minTime) {
                minTime = timeOnGrid;
            }

            // Calculate the mean time and the variance
            meanTime = (totalTime / carArrayList.size());
            variance = maxTime - minTime;

            // Build the name of the car based on generator type and index
            String carNumberStr = String.valueOf(i);
            String genName = "EWGenerator Car ";
            String carName = genName + carNumberStr;


            //Append the information
            sb.append("\n" + carName + ": " + timeOnGrid + " ms");

        }

        // Append the final statistics for this generator
        sb.append("\n" + "Max Time: " + maxTime + "ms,  Min Time: " + minTime + "ms,  Mean Time: " + meanTime + "ms,  Variance: " + variance + "ms");


        // Return the report as a string
        return sb.toString();


    }

    /**
     * Method to produce a string report for the North South Generator
     *
     * @param gen - the NSGenerator object
     * @return - the string report
     */
    public String NSGenReport(NSGenerator gen) {
        ArrayList<Car> carArrayList = gen.getCarArrayList();
        StringBuilder sb = new StringBuilder();
        sb.append("North South Car Report");

        long maxTime = 0;
        long minTime = 0;
        long meanTime = 0;
        long totalTime = 0;
        long variance = 0;

        for (int i = 0; i < carArrayList.size(); i++) {

            Car c = carArrayList.get(i);
            long timeOnGrid = c.getTimeOnGrid();
            totalTime += timeOnGrid;

            if (i == 0) {
                maxTime = timeOnGrid;
                minTime = timeOnGrid;
            }

            // Check for new max or min
            else if (timeOnGrid > maxTime) {
                maxTime = timeOnGrid;
            } else if (timeOnGrid < minTime) {
                minTime = timeOnGrid;
            }

            // Calculate the mean time and the variance
            meanTime = (totalTime / carArrayList.size());
            variance = maxTime - minTime;

            // Build the name of the car based on generator type and index
            String carNumberStr = String.valueOf(i);
            String genName = "NSGenerator Car ";
            String carName = genName + carNumberStr;


            //Append the information
            sb.append("\n" + carName + ": " + timeOnGrid + " ms");

        }

        // Append the final statistics for this generator
        sb.append("\n" + "Max Time: " + maxTime + "ms,  Min Time: " + minTime + "ms,  Mean Time: " + meanTime + "ms,  Variance: " + variance + "ms");


        // Return the report as a string
        return sb.toString();


    }

    /**
     * Prints the EW and NS reports to the console
     */
    public void writeReport() {

        String EW = EWGenReport(EWGen);
        String NS = NSGenReport(NSGen);
        System.out.println(EW);
        System.out.println("---------------------------");
        System.out.println(NS);


    }

}
