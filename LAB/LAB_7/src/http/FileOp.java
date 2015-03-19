package http;

import java.io.BufferedReader;
import java.io.File;

public class FileOp
{
	boolean file_exists(String path)
	{
		File f = new File(path);
		return f.isFile();
	}
	
	boolean file_readable(String path)
	{
		File f = new File(path);
		return f.canRead();
	}

	String get_content(String path)
	{
		BufferedReader br = null;
		StringBuilder content = new StringBuilder();
		try 
		{
			String line;
			br = new BufferedReader(new FileReader(path));
			while ((line = br.readLine()) != null) 
			{
				content.append(line+"\n");
			}
			int length = content.length();
			content.deleteCharAt(length-1);
		}
		catch(Exception ex)
		{
			System.err.println("File exists and have read permission. So some serious I/O error. Need to shutdown");
			System.exit(1);
		}
		return content.toString();
	}
}
