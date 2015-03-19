// UDPClient.java: A simple UDP client program.

import java.net.*;
import java.io.*;

public class UDPClient {

    static DatagramSocket aSocket = null;
    final static String hostAddress = "127.0.0.1";
    final static int serverPort = 1234;

    public static void main(String args[]) {

        try {

            // Create a UDP socket
            DatagramSocket aSocket = new DatagramSocket();

            InetAddress aHost = InetAddress.getByName(hostAddress);

            /***********************************/
            //Send many packets
            for(int i = 1;i<10001;i++){

                // String data to send
                String newData = i+"end";

                // Data array to send data
                byte[] newDataArray = newData.getBytes();

                // Create the Datagram packet
                DatagramPacket newRequestPacket = new DatagramPacket(newDataArray, newDataArray.length, aHost, serverPort);

                // Send Datagram packet
                aSocket.send(newRequestPacket);
            }

            /***********************************/

        } catch (SocketException e) {

            System.out.println("Socket: " + e.getMessage());

        } catch (IOException e) {

            System.out.println("IO: " + e.getMessage());

        } finally {

            if (aSocket != null) {

                // Close the UDP socket
                aSocket.close();

            }
        }
    }
}
