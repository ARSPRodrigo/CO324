package upload;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

enum MyHandler implements HttpHandler {
	GET {
		public void handle(HttpExchange t) throws IOException {
	        String p = t.getRequestURI().getPath();
			Path path = Paths.get(ROOT, p);
	        
			try (InputStream in = Files.newInputStream(path);
			        OutputStream out = t.getResponseBody() ){
				//If we can determine the file's MIME type, set content-type
		        String type =  Files.probeContentType(path); 
				if (type!=null) {
                    List l = new ArrayList();
                    l.add(type);
                    t.getRequestHeaders().put("Content-Type", l);
                }
		        t.sendResponseHeaders(200, Files.size(path));
                copy(in, out);
			} catch (IOException e) {
		        t.sendResponseHeaders(400, -1);
				e.printStackTrace();
			}
	    }			
	},
	PUT {		
		public void handle(HttpExchange t) throws IOException {
            String p = t.getRequestURI().getPath();
            Path path = Paths.get(ROOT, p);

            try (InputStream in = t.getRequestBody();
                 OutputStream out = Files.newOutputStream(path) ){
                 t.sendResponseHeaders(200,-1);
                 copy(in, out);
            } catch (IOException e) {
                t.sendResponseHeaders(400, -1);
                e.printStackTrace();
            }
		}	
	};
	
	static final String ROOT ="C:/Users/Prasanna/OneDrive";
	
	static void copy(InputStream in, OutputStream out) throws IOException {
		int len; 
		byte[] buf = new byte[256];
		while (	(len = in.read(buf, 0, buf.length)) > 0)
			out.write(buf, 0, len);
	}
}