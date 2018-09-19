
public class CustomerAccount {

	private String customerName; //Name of customer
	private double customerBalance; //Current Customer Balance
	private final double  serviceCharge = 0.2 ; //Service Charge Factor
	
	/*
	 * Customer account constructor
	 */
	public CustomerAccount(String name, Double balance) {
		customerName=name;
		customerBalance=balance;
	}
	
	/*
	 * Customer account accessor methods
	 */
	public String getCustomerName() {
		return customerName;
	}
	public double getBalance() {
		return customerBalance;
	}
	
	/*
	 * Method to process wine sales. Updates balance
	 */
	public double wineSale(Wine w) {
		double winePrice=w.getWinePrice();
		int wineQuant=w.getWineQuant();
		double cost = winePrice*wineQuant;
		int costP=(int)Math.round(cost*100); //Cost in pence
		cost=costP/100.0;
		customerBalance+=cost;
		return cost;
		
	}
	
	/*
	 * Method to process wine Returns. Updates balance. Subtracts service charge
	 */
	public double wineReturn(Wine w) {
		double winePrice=w.getWinePrice();
		int wineQuant=w.getWineQuant();
		double refund = (winePrice*wineQuant)*(1-serviceCharge);
		int refundP=(int)Math.round(refund*100); //refund amount in pence
		refund=refundP/100.0;
		customerBalance-=refund;
		return refund;
	}
	
	
}
