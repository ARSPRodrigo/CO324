package bank;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class AccountServer extends Thread {

	static final int PORT = 4321;
	
	final Socket socket;

	public AccountServer(Socket s) {
		this.socket = s;
	}
	
	@Override
	public void run() {
		try (Scanner sin = new Scanner(socket.getInputStream()))  {
			while(!interrupted())
				new Transfer(sin).execute();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {}
		}
	}
	
	public static void main(String[] args) throws IOException {
		//Create two test accounts for transfers.
		new Account(); new Account();
		
		try( ServerSocket ss = new ServerSocket(PORT)) {		
			while (true) 
				new AccountServer(
						ss.accept()).start();			 
		}
	}	
}
