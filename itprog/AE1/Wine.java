
public class Wine {

	String wineName;
	int wineQuant;
	double winePrice;
	
	/*
	 * Wine constructor
	 */
	public Wine(String name, int quant, double price) {
		wineName = name;
		wineQuant = quant;
		winePrice = price;
	}
	
	/*
	 * Wine accessor methods
	 */
	
	public String getWineName() {
		return wineName;
	}
	
	public int getWineQuant() {
		return wineQuant;
	}
	
	public double getWinePrice() {
		return winePrice;
	}
	
}
