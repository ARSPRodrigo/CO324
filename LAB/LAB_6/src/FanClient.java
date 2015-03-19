import java.io.Console;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class FanClient {

	public static void main(String[] args) 
			throws IOException {
		InetAddress address = InetAddress.getByName("localhost");
		
		try (Socket socket = new Socket(
				address, FanController.PORT);			 
			Scanner sin = new Scanner(
					socket.getInputStream() );
			PrintStream sout = new PrintStream (
					socket.getOutputStream() ); ) {
			
			Console console = System.console();
			String input;
			
			while ((input=console.readLine("%nEnter command: ")) != null) {
				sout.println(input);
				System.err.println("Request sent, awaiting result...");
				System.out.println("New state is "+sin.nextLine());
			}
		} 
	}
}
