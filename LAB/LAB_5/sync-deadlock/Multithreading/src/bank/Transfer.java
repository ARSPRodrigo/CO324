package bank;

import java.net.ProtocolException;
import java.util.Scanner;

/** Transfer message format used between AccountClient and AccountServer */
public class Transfer  {
	
	final int fromID, toID, amount;

	/** Constructor for serialising transfer message  */
	public Transfer(int fromID, int toID, int amount) {
		this.fromID = fromID;
		this.toID = toID;
		this.amount = amount;
	}
	
	/** Deserialise a transfer message */
	public Transfer(Scanner sin) throws ProtocolException {
		if (!sin.hasNextInt()) 
			throw new ProtocolException("missing or malformed account ID");
		fromID = sin.nextInt();
		
		if (!sin.hasNextInt()) 
			throw new ProtocolException("missing or malformed account ID");
		toID = sin.nextInt();
		
		if (!sin.hasNextInt()) 
			throw new ProtocolException("missing or malformed amount");
		amount = sin.nextInt();
		
	}
	
	/** Execute the specified transfer */
	public void execute() {
		Account ac1 = Account.find(fromID);
		Account ac2 = Account.find(toID);
		if (ac1==null || ac2==null) 
			throw new IllegalArgumentException("invalid account ID");
		
		ac1.transfer(ac2, amount);
		System.err.println("Trasferred "+amount+" from a/c "+ac1+" to a/c "+ac2);		
	}
	
	@Override
	public String toString() {
		return String.format("%d %d %d\n", fromID, toID, amount);
	}

}
