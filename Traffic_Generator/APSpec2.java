/**
 * Class which contains the main method for spec 2
 */

public class APSpec2 {


    public static void main(String[] args) {

        // Create a grid of specified dimensions
        Grid grid = new Grid(10, 20);

        // Create a GridBuilder and  the desired traffic builders
        GridBuilder gBuilder = new GridBuilder(grid);
        EWGenerator ewGen = new EWGenerator(grid, gBuilder);
        NSGenerator nsGen = new NSGenerator(grid, gBuilder);

        // Create a statistics object and input the generators
        Statistics stats = new Statistics(ewGen, nsGen);

        // Start the GridBuilder and traffic generators on threads
        Thread tG = new Thread(gBuilder);
        Thread tEW = new Thread(ewGen);
        Thread tNS = new Thread(nsGen);

        tEW.start();
        tNS.start();
        tG.start();


        // When the threads have finished, exit the system
        // and write the report to the console
        try {
            tEW.join();
            tNS.join();
            tG.join();
            stats.writeReport();
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



