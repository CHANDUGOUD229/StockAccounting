package utilites;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtils 
{

	public static String getValueForKey(String Key) throws IOException
	{
		
	    	Properties p=new Properties();
		    FileInputStream fi=new  FileInputStream(System.getProperty("user.dir")+"\\PrapertiesFile\\Enveronment.properties");
		    p.load(fi);
	        return p.getProperty(Key);
		
	}
	
	
	
	
	
	
	
	
	
}
