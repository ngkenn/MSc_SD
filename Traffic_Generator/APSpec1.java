/**
 * Class which contains the main method for spec 1.
 *
 */
public class APSpec1 {

	public static void main(String[] args) {

		// Create a grid of specified dimensions
		Grid testGrid = new Grid(10, 20);

		// Create a GridBuilder and CarBuilder and start them on a thread
		GridBuilder gBuilder = new GridBuilder(testGrid);
		CarBuilder carGen = new CarBuilder(testGrid, gBuilder);
		Thread t1 = new Thread(gBuilder);
		Thread t2 = new Thread(carGen);
		
		t1.start();
		t2.start();

		// When the threads are finished, exit the system
		try{
			t1.join();
			t2.join();
			System.exit(0);
		}
		catch (InterruptedException e){
			e.printStackTrace();
		}

		
	}

}
