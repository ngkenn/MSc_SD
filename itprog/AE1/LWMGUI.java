import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class LWMGUI extends JFrame implements ActionListener{
	//Instance CustomerAccount Variable
	private CustomerAccount customer; 
	//Initialising JButton,JLabel,JTextField & JPanel for the GUI
	private JButton saleButton,returnButton; 
	private JLabel namelbl,quantitylbl,pricelbl,translbl,balancelbl,winelbl;
	private JTextField nametxt,quantitytxt,pricetxt,transtxt,balancetxt;
	private JPanel top,mid,mid2,cntr,bottom;
	
	/* 
	 *LWMGUI Constructor 
	 */
	public LWMGUI(CustomerAccount c) {
		
		customer=c;
		
		//GUI 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,220);
		setLocation(100,100);
		setTitle("Lilybank Wine Merchants: "+ c.getCustomerName());
		
		//GUI Buttons
		saleButton=new JButton("Process Sale");
		saleButton.addActionListener(this);
		returnButton=new JButton("Process Return");
		returnButton.addActionListener(this);
		
		//GUI Labels
		namelbl=new JLabel("Name: ");
		quantitylbl=new JLabel("Quantity: ");
		pricelbl=new JLabel("Price: £");
		translbl = new JLabel("Transaction Amount: £");
		balancelbl = new JLabel("Current Balance: £");
		winelbl = new JLabel("");
		
		//GUI Text Fields
		nametxt = new JTextField("",15);
		quantitytxt = new JTextField("",5);
		pricetxt = new JTextField("",5);
		transtxt = new JTextField("",8);
		transtxt.setEditable(false);
		balancetxt = new JTextField("",8);
		setBalance();
		balancetxt.setEditable(false);
		
		//Setting the Layout
		layoutManager(); 
	}
	
	/*
	 * Layout Manager Method
	 */
	private void layoutManager() {
	
		//Top
		top=new JPanel();
		top.add(namelbl);
		top.add(nametxt);
		top.add(quantitylbl);
		top.add(quantitytxt);
		top.add(pricelbl);
		top.add(pricetxt);
		add(top,BorderLayout.NORTH);
		
		//Middle
		mid=new JPanel();
		mid.add(saleButton);
		mid.add(returnButton);	
		
		mid2=new JPanel();
		mid2.add(winelbl);
		
		//Bottom
		bottom=new JPanel();
		bottom.add(translbl);
		bottom.add(transtxt);
		bottom.add(balancelbl);
		bottom.add(balancetxt);
		add(bottom,BorderLayout.SOUTH);
		
		//Centre Panel
		cntr=new JPanel();			
		cntr.add(mid,BorderLayout.NORTH);
		cntr.add(mid2,BorderLayout.SOUTH);
		add(cntr,BorderLayout.CENTER);
		
	}
	
	/*
	 * Method to deal with the event of a sale or return using values computed in CustomerAccount & Wine classes
	 */
	public void actionPerformed(ActionEvent e)
	{
		Wine wine = newWine();
	
	//Checks if transaction is valid and processes sale/return or resets text fields if invalid
	if(wine!=null) {
		if (e.getSource() == saleButton) {
			transtxt.setText(String.format(" %.02f", customer.wineSale(wine)));
			setBalance();
			winelbl.setText(String.format("Sale processed: %d bottles of %s purchased at £%.02f per bottle",wine.wineQuant,wine.wineName,wine.winePrice));
			resetText();
		}
		
		else if (e.getSource()== returnButton) {
			transtxt.setText(String.format("%.02f",customer.wineReturn(wine)));
			setBalance();
			winelbl.setText(String.format("Return processed: %d bottles of %s returned at £%.02f per bottle (20 percent service Charge)",wine.wineQuant,wine.wineName,wine.winePrice));
			resetText();
		}
	}
	else{
		resetText();
		transtxt.setText("");
		winelbl.setText("");}
	}
	
	
	/*
	 * Method to check if the balance is positive or negative, then applies the correct format to the balance text field
	 */
	public void setBalance() {
		double balance=customer.getBalance();
		if(balance>=0) {
			balancetxt.setText(String.format("%.02f", customer.getBalance()));
		}
		else if(balance<0) {
			balancetxt.setText(String.format("%.02f CR", Math.abs(customer.getBalance())));
		}
	}
	
	/*
	 * Method which resets the text fields to blank when called
	 */
	public void resetText() {
		nametxt.setText("");
		quantitytxt.setText("");
		pricetxt.setText("");
	}
	
	/*
	 * Wine method which gets data from the text inputs, checks if valid and returns a wine object
	 */
	private Wine newWine() {
		
		String wineName = nametxt.getText();
		String wineQuantStr = quantitytxt.getText();
		String winePriceStr = pricetxt.getText();
		int wineQuant=0;
		double winePrice=0.0;

			
		//Checking if valid value is entered for wine name, returns error message if invalid
		if (wineName.isEmpty()) {
			JOptionPane.showMessageDialog(null,"Please ensure name is entered for wine","Invalid data entered",JOptionPane.ERROR_MESSAGE);
			return null;
			
		}
		
		//Checking if valid int entered for quantity, returns error message & null value if not
		try {
			wineQuant = Integer.parseInt(wineQuantStr.trim());
		}
		
		catch (NumberFormatException QuantE) {
			JOptionPane.showMessageDialog(null,"Please ensure wine quantity is an integer","Invalid data entered",JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		//Checking if valid number entered for wine price, returns error message & null value if not
		try {
			winePrice = Double.parseDouble(winePriceStr.trim());
		}
		
		catch (NumberFormatException PriceE) {
			JOptionPane.showMessageDialog(null,"Please ensure price is a valid number","Invalid data entered",JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		//Checks if wine quantity and price are positive numbers, returns error message & null if not
		if(wineQuant<0 || winePrice<0) {
			JOptionPane.showMessageDialog(null,"Please ensure price and quantity are positive numbers","Invalid data entered",JOptionPane.ERROR_MESSAGE);
			return null;}
		
		//Creating and returning a new wine object for use in other methods
		Wine wineCreated = new Wine(wineName,wineQuant,winePrice);
		return wineCreated;
	}
	
	
	
}
