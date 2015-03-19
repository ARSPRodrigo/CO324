/*
 *  A datagram client to send and receive UDP packets in Java.
 *
 *  Instructions to run:
 *  Pass the host address and the port as command line arguments.
 *  Eg: >>javac UDPClient.java
 *      >>java UDPClient hostaddress portNo
 *
 *  Edited by: Rodrigo A.R.S.P — E/11/343 — 29.01.2015
 */

 //import network and input output packages
 import java.net.* ;


public class UDPClient
{
   //Give a standard packet size. 
   private final static int packetsize = 100 ;
   
   public static void main( String args[] )
   {
       // Check the whether the arguments are given
      if( args.length != 2 )
      {
         System.out.println( "usage: java DatagramClient host port" ) ;
         return ;
      }

      //Q1: Create a datagram socket object here
       DatagramSocket socket = null;
	  
      try
      {
          // Convert the arguments to ensure that they are valid
         InetAddress host = InetAddress.getByName( args[0] ) ;
         int port         = Integer.parseInt( args[1] ) ;

         //Q1: Construct the socket
         socket = new DatagramSocket();

         byte [] data = "The message watnts to pass".getBytes() ;
		 
         //Q2: Construct the datagram packet
         DatagramPacket packet = new DatagramPacket(data,data.length, host, port);

         // Send the packet
         socket.send( packet ) ;
        

         // Prepare the packet for receive
         packet.setData( new byte[packetsize] ) ;

         // Wait for a response from the server
         socket.receive( packet ) ;

         // Print the response
         System.out.println( new String(packet.getData()) ) ;

      }
      catch( Exception e )
      {
         System.out.println( e ) ;
      }
      finally {
          socket.close() ;
      }
     
   }
}