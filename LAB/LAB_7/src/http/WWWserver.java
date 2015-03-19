package http;
import http.Response.Code;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class WWWserver extends Thread {

	static final int PORT = 4321;

	final Socket socket;

	public WWWserver(Socket s) {
		this.socket = s;
	}
	
	@Override
	public void run() {
		try (Scanner sin = new Scanner(socket.getInputStream() ); 
			PrintStream sout = new PrintStream(socket.getOutputStream()) )  {
			Request r= new Request(sin);
			System.err.println("Got request:\n" + r);

			/* create a new response according to the request. 
			and send it back to the client. Use the methods provided in FileOp class to get the file status info */
            Response new_response;

            FileOp new_fileOp = new FileOp();
            if(!new_fileOp.file_exists(r.url)) {
                new_response = new Response(Code.NOTFOUND,"File not found!");
            }else if (!new_fileOp.file_readable(r.url)){
                new_response = new Response(Code.FORBIDDEN,"File not readable");
            }else {
                new_response = new Response(Code.OK,new_fileOp.get_content(r.url));
            }
            System.out.println(new_response.toString());
			sout.print(new_response.toString());

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {}
		}
	}
	
	public static void main(String[] args) throws IOException {		
		try( ServerSocket ss = new ServerSocket(PORT)) {		
			while (true) {
				Socket socket = ss.accept(); 
				new WWWserver(socket).start();			 
			}
		}
	}
	
}
