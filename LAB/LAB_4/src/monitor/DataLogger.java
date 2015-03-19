package monitor;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

import monitor.Reading;

public class DataLogger extends Thread {
	static final int PORT = 12345;
    final Socket socket;

    static Object lock = new Object();

    public DataLogger(Socket s){
        this.socket = s;
    }

    @Override
    public void run(){

        while (true)
            try (DataInputStream sin = new DataInputStream(
                    socket.getInputStream()); ) {
                //This should run in a separate thread
                while (true){
                    Reading r;
                    synchronized (lock) {
                        r = new Reading(sin);
                    }
                    System.out.println(r);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
    }
	
	public static void main(String[] args) throws IOException {
		try(ServerSocket ss = new ServerSocket(PORT)){
            while (true){
                Socket socket1 = ss.accept();
                new DataLogger(socket1).start();
            }
        }
	}	
}
