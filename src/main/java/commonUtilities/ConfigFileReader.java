package commonUtilities;

import constants.Framework_Constants;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.Objects;
import java.util.Properties;

/**
 * @author Nikhil Singh
 *
 */
public final class ConfigFileReader
{

	private ConfigFileReader()
	{

	}
	 private static Properties properties = new Properties();

	static
	{
		final String propertyFilePath= Framework_Constants.getProperties_FilePath();
		BufferedReader reader;
		try
		{
			reader = new BufferedReader(new FileReader(propertyFilePath));
			try
			{
				properties.load(reader);
				reader.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}
	}

	 public static String getTestDataFile()
	 {
		 String testDataFile = FunctionLibrary.config.testDataFile();//properties.getProperty("testDataFile");
		 if(testDataFile!=null)
			 return testDataFile; 
		 else
			 throw new RuntimeException("File not specified in the Configuration.properties file.");
	 }
	 
	 public static String getProperty(String propertyName)
	 {
		 String property_Name = String.valueOf(properties.getProperty(propertyName)).trim();
		 if(Objects.isNull(property_Name) || Objects.isNull(propertyName))
			 try {
				 throw new Exception("Property Name : "+property_Name+" is not specified in the Configuration.properties file.");
			 } catch (Exception e) {
				 throw new RuntimeException(e);
			 }
		 else
			 return  property_Name;
	 }
	 

	public static String getTestData(String testDatapath)
	{
		String name=getTestDataFile();
	   	File inputFile = new File(System.getProperty("user.dir")  + "/src/test/resources/configuration/" +name);
	   	SAXReader saxReader = new SAXReader();
		Document document = null;
		try {
			document = saxReader.read(inputFile);
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
		String testData = document.selectSingleNode("//"+(testDatapath.replace(".", "/"))).getText();
	   	return testData;
	}
	 
}
