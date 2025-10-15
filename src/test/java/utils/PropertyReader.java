package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

	
	public String readproperty(String key) throws IOException
	{ String St=null;
	
	Properties pr= new Properties();
	//InputStream file= new FileInputStream("config.cyncly.properties");
        InputStream file= new FileInputStream("config.properties");
	pr.load(file);
	St=pr.getProperty(key);
	file.close();
	return St;
		
	}
	
	public String readProperyFromFile(String Filename,String key) throws IOException
	{ String St=null;
	
	Properties pr= new Properties();
	InputStream file= new FileInputStream("src/test/resources/NodePath/"+Filename);
	pr.load(file);
	St=pr.getProperty(key);
	file.close();
	return St;
		
	}
	public String returnthefilepath(String Filename,String key) throws IOException
	{ String St=null;

		Properties pr= new Properties();
		InputStream file= new FileInputStream(Filename);
		pr.load(file);
		St=pr.getProperty(key);
		file.close();
		return St;

	}
	
	public String readsessionproperty(String key) throws IOException
	{ String St=null;
	
	Properties pr= new Properties();
	InputStream file= new FileInputStream("src/Config/Debug.properties");
	pr.load(file);
	St=pr.getProperty(key);
	file.close();
	return St;
		
	}
	
	public void updateproprty(String key,String value) throws IOException {
		FileInputStream in = new FileInputStream("config.properties");
		Properties props = new Properties();
		props.load(in);
		in.close();

		FileOutputStream out = new FileOutputStream("config.properties");
		props.setProperty(key, value);
		props.store(out, null);
		out.close(); 
	}

	public static void main(String[] args) throws IOException
	{
		PropertyReader pr=new PropertyReader();
		pr.updateproprty("SessionID","abcd");
	}
	
}
