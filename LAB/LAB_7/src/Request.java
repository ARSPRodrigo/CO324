package http;
import java.net.ProtocolException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Request {
	public enum Method { HEAD, GET, PUT, POST, DELETE };
	
	static final Pattern 
		request = Pattern.compile("(\\w+) (\\S+) HTTP/\\d", Pattern.CASE_INSENSITIVE),
		sep = Pattern.compile("\\s*:\\s*");

	final Method method;
	final String url;
	
	public Request(Scanner in) throws ProtocolException {
		String req1 = in.nextLine();
		Matcher matcher = request.matcher(req1);
		if (!matcher.find())
			throw new ProtocolException("Illegal request");

	/*
		Match with available methods and set the method ( though many could be matched,only respond to GET requests here)
		set the url to return.
		*/
		
		}
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(method).append('\t').append(url);
		
		for (Entry<String,String> e: headers.entrySet())
			buf.append('\n').append(e);
		
		return buf.toString();
	}
}
