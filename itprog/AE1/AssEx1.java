import javax.swing.JOptionPane;

/*
 * Main method calling the program
 */
public class AssEx1 {
	public static void main(String [] args) {
		
		//Variable initialisation
		String customerName="";
		String customerBalancetxt="";
		double customerBalance=0;
		
		//Getting the customer name
		customerName = JOptionPane.showInputDialog("Please enter the account holder's name");	
		
		//Exiting the system if cancel is pressed
		if(customerName==null) {
			JOptionPane.showMessageDialog(null,"System closed.","Please try again",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		//Looping until valid customer name is input
		while(customerName!=null) {
			
			if(customerName.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Customer Name must be input", "Customer Name Error",
						JOptionPane.ERROR_MESSAGE);	
				customerName = JOptionPane.showInputDialog("Please enter the account holder's name");
				
				//Exits the system if cancel is pressed after initial invalid data input
				if(customerName==null) {
					JOptionPane.showMessageDialog(null,"System closed.","Please try again",JOptionPane.ERROR_MESSAGE);
					System.exit(0);
				}
			}
			
			else {break;}
		}
		
		//Getting the customer account balance		
		customerBalancetxt = JOptionPane.showInputDialog("Please enter the current balance (£)");
		
		//Exiting the system if cancel is pressed 
		if(customerBalancetxt==null) {
		JOptionPane.showMessageDialog(null,"System closed.","Please try again",JOptionPane.ERROR_MESSAGE);
		System.exit(0);}
		
		//Looping until valid balance data is input, system exit if cancel is pressed
		while(customerBalancetxt!=null)
			try {
				customerBalance = Double.parseDouble(customerBalancetxt.trim());
				break;
				}
			catch(NumberFormatException balException) {
				JOptionPane.showMessageDialog(null, "Balance must be a number", "Number error",
							JOptionPane.ERROR_MESSAGE);
				customerBalancetxt = JOptionPane.showInputDialog("Please enter the current balance (£)");

				if(customerBalancetxt==null) {
					JOptionPane.showMessageDialog(null,"System closed.","Please try again",JOptionPane.ERROR_MESSAGE);
					System.exit(0);}
				}
		
		//Creating a new customer object with data from the JOptionPanes
		CustomerAccount customer = new CustomerAccount(customerName,customerBalance);
		
		//Creating LWMGUI object with customer object as argument
		LWMGUI frame = new LWMGUI(customer);
		frame.setVisible(true);
	}
}
