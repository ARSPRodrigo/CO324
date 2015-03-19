package bank;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

public class AccountClient {

	public static void main(String[] args) 
			throws IOException {
		InetAddress address = InetAddress.getByName("localhost");
		Random rand = new Random();
		
		try (Socket socket = new Socket(address, AccountServer.PORT);
				PrintStream sout = new PrintStream(socket.getOutputStream() )) {
			
			int fromID = Integer.parseInt(args[0]),
				toID = Integer.parseInt(args[1]);
			
			for (int i=0; i<1000; i++) 
			try {
				int amount = rand.nextInt(100) - 50;
				sout.println(new Transfer(fromID, toID, amount));
				Thread.sleep(rand.nextInt(100));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
	}
}
