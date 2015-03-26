package upload;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class WebServer {

    private static final int PORT = 8080;

	public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);

        /*Handler for GET requests.URL http://localhost:8080/public/filename 
         * maps to file MyHandler.ROOT/public/filename. */
        server.createContext("/public",  MyHandler.GET);
        
        /*Handler for PUT requests.URL http://localhost:8080/upload/filename 
         * maps to file MyHandler.ROOT/upload/filename. */
        server.createContext("/upload",  MyHandler.PUT);
        
        server.setExecutor(null); // creates a default executor
        server.start();
    }

}
