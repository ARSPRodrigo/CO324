// UDPServer.java: A simple UDP server program.

import java.net.*;
import java.io.*;
import java.util.*;

import static java.lang.System.currentTimeMillis;

public class UDPServerEx3 {
    private static DatagramSocket aSocket = null;
    final static int PORT = 1234;
    final static int BUFSIZE = 512;
    
    
    public static void main(String args[]) {

        double throughput = 0;
        int lost = 0;
        long stime = 0;
        long etime = 0;

        try {
            
            aSocket = new DatagramSocket(PORT);
			
            byte[] bufferRecieve = new byte[BUFSIZE];
            
            DatagramPacket recievePacket = new DatagramPacket(bufferRecieve, BUFSIZE);

            String getData;
            int count = 0;
            String [] s;
            int i = 0,ii=0;

            for(;;) {
                if (count == 0){
                    stime = currentTimeMillis();
                }

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
                    count++;
                }
                else {
                    i = ii;
                    count++;
                }
                if (count == 1000){
                    etime = currentTimeMillis();
                }

            }

        } catch (SocketException e) {
            
            System.out.println("Socket: " + e.getMessage());
            
        } catch (IOException e) {
            
            System.out.println("IO: " + e.getMessage());
            
        } finally {
            
            if (aSocket != null) {
                throughput = 1000/new Double(etime - stime);
                System.out.println("Throughput: "+throughput);
                aSocket.close();
                
            }
        }
    }
}
