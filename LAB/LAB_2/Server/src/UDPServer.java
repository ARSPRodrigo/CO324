// UDPServer.java: A simple UDP server program.

import java.net.*;
import java.io.*;

public class UDPServer {
    // Create a UDP socket
    private static DatagramSocket aSocket = null;
    final static int PORT = 1234;
    final static int BUFSIZE = 512;
    
    
    public static void main(String args[]) {

        int lost = 0;
        try {

            // // Initialize a UDP socket
            aSocket = new DatagramSocket(PORT);
			
            byte[] bufferRecieve = new byte[BUFSIZE];

            // Create a Datagram packet
            DatagramPacket recievePacket = new DatagramPacket(bufferRecieve, BUFSIZE);

            // This is for the count of packet lost
            String getData;
            String [] s;
            int i = 0,ii=0;

            for(;;) {

                // End socket if delay is 5000ms
                aSocket.setSoTimeout(5000);

                // Receives a datagram packet from  UDP socket created
                aSocket.receive(recievePacket);

                // Get the received packet as string
                getData = new String(recievePacket.getData());

                s = getData.split("end");

                // Get the received packet id
                ii = Integer.parseInt(s[0]);

                // Check whether the id equals to the next packet
                if((i+1) != ii){
                    lost += ii-(i+1);
                    i = ii;
                }
                else {
                    i = ii;
                }

            }


        } catch (SocketException e) {
            
            System.out.println("Socket: " + e.getMessage());
            
        } catch (IOException e) {
            
            System.out.println("IO: " + e.getMessage());
            
        } finally {

            if (aSocket != null) {

                // Printout the lost packet number
                System.out.println(lost);

                // Close the UDP socket
                aSocket.close();
                
            }
        }
    }
}
