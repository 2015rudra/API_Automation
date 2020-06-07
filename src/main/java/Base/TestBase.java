package Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase 
{

	public static Properties prop;
	
	public static int response_code_200 = 200;
	int response_code_404 = 404;
	int response_code_401 = 401;
	
	
	public TestBase() throws IOException 
	{

		try 
		{
			prop = new Properties();
			FileInputStream ip = new FileInputStream("D:\\Practise\\SalesSuite_API_Automation\\src\\main\\java\\Configuration\\config.properties");
			prop.load(ip);
			

		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch(IOException e) 
		{
			e.printStackTrace();
		}


	}
	
}
