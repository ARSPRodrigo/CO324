package http;
import java.net.ProtocolException;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Request {
	public enum Method { HEAD, GET, PUT, POST, DELETE };
	
	static final Pattern request = Pattern.compile("(\\w+) (\\S+) HTTP/\\d", Pattern.CASE_INSENSITIVE)
            ,sep = Pattern.compile("\\s*:\\s*");

	Method method;
	String url;
	
	public Request(Scanner in) throws ProtocolException {
		String req1 = in.nextLine();
		Matcher matcher = request.matcher(req1);
		if (!matcher.find())
			throw new ProtocolException("Illegal request");

        if (matcher.group(1).equals("GET")) {
            method = Method.GET;
            url = matcher.group(2);
        }
        /*
        Match with available methods and set the method ( though many could be matched,only respond to GET requests here)
        set the url to return.
        */
		
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(method).append('\t').append(url);

		return buf.toString();
	}
}
