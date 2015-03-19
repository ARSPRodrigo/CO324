/*
 *  A datagram client to send and receive UDP packets in Java.
 *
 *  Instructions to run:
 *  Pass the port as command line arguments.
 *  Eg: >>javac UDPClient portNo
 *
 *  Edited by: Rodrigo A.R.S.P — E/11/343 — 29.01.2015
 */

 import java.net.* ;
public class UDPServer
{
   //Give a standard packet size
   private final static int packetsize = 100 ;

   public static void main( String args[] )
   {
      // Check the whether the arguments are given
      if( args.length != 1 )
      {
         System.out.println( "usage: DatagramServer port" ) ;
         return ;
      }

      try
      {
         // Convert the argument to ensure that is it valid
         int port = Integer.parseInt( args[0] ) ;

         // Construct the socket
         DatagramSocket socket = new DatagramSocket( port ) ;

         System.out.println( "The server is ready..." ) ;
		
		 // Create a packet
         DatagramPacket packet = new DatagramPacket( new byte[packetsize], packetsize ) ;


         for( ;; )
         {
            
            // Receive a packet (blocking)
            socket.receive( packet ) ;

            // Print the packet
            System.out.println( packet.getAddress() + " " + packet.getPort() + ": " + new String(packet.getData()) ) ;

            // Return the packet to the sender
            socket.send( packet ) ;
        }  
     }
     catch( Exception e )
     {
        System.out.println( e ) ;
		
     }
  }
}