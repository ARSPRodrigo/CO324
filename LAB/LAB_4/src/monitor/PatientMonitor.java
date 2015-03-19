package monitor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

public class PatientMonitor {

	static final String LOGHOST = "localhost";
	
	public static void main(String[] args) 
			throws IOException,  InterruptedException {
		Random rand = new Random();		
		InetAddress address = InetAddress.getByName(LOGHOST);
		//long clientID = address.hashCode();
        //long clientID = 11111111; // client1
        //long clientID = 22222222; // client2
        long clientID = 33333333; // client3
		try (Socket socket = new Socket(address, DataLogger.PORT);
			DataOutputStream sout = new DataOutputStream(
					socket.getOutputStream());) { 
			while (true) {
				Reading p = new Reading(clientID, 
						System.currentTimeMillis(),
						60+rand.nextInt(40),
						30 + 10 *rand.nextFloat());
				
				sout.write(p.data());					
//				Thread.sleep(100);
			}
		}
	}
}
