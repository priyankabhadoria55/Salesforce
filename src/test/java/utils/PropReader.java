package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.Properties;

public class PropReader {

	
	public static  String readproperty(String key) throws IOException
	{ 
		InputStream file;
    	String St=null;
//    	DriverTestcase dri =  new DriverTestcase();	
	Properties pr= new Properties();
		file= new FileInputStream(".\\config.properties");
	pr.load(file);
	St=pr.getProperty(key);
	file.close();
	return St;
		
	}
	
//	public static String readconfig(String key, String Role) throws IOException
//	{
//		
//		InputStream file;
//    	String St=null;
////    	DriverTestcase dri =  new DriverTestcase();	
//	Properties pr= new Properties();
//	if(Role.equalsIgnoreCase("mom"))
//	{
//		 file= new FileInputStream("src\\Config\\Config_mom.properties");
//	}
//	else if(Role.equalsIgnoreCase("myself"))
//	{
//		file= new FileInputStream("src\\Config\\Confif_myself.properties");
//	}
//	
//	
//	else {
//		file= new FileInputStream("src\\\\Config\\\\Config_Mom.properties");
//	}
//	pr.load(file);
//	St=pr.getProperty(key);
//	file.close();
//	return St;
//	}
//	
//	public static  String readscore(String key) throws IOException
//	{ 
//		InputStream file;
//    	String St=null;
////    	DriverTestcase dri =  new DriverTestcase();	
//	Properties pr= new Properties();
//		file= new FileInputStream(".\\config_Score.properties");
//	pr.load(file);
//	St=pr.getProperty(key);
//	file.close();
//	return St;
//		
//	}
	
	
	public static void updateproprty(String key,String value) throws IOException {
		FileInputStream in = new FileInputStream(".\\config.properties");
		Properties props = new Properties();
		props.load(in);
		in.close();

		FileOutputStream out = new FileOutputStream(".\\config.properties");
		props.setProperty(key, value);
		props.store(out, null);
		out.close(); 
	}
	public static void main(String[] agrs) throws IOException
	{
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");  
		   LocalDateTime now = LocalDateTime.now();  
		   DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd");  
		   LocalDateTime now1 = LocalDateTime.now();  
		   Calendar c = Calendar.getInstance();
	
		    
		    String final1= "Created: "+c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH )+" "+dtf1.format(now1)+", "+dtf.format(now);
		
		    System.out.println(final1);
	}
}
