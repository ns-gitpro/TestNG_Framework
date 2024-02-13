package reportUtilities;

import com.aventstack.extentreports.ExtentTest;

public class ExtentFactory 
{
	private ExtentFactory()
	{
		
	}

	private static ThreadLocal<ExtentTest> extent = new ThreadLocal<>();

	public static ExtentTest getExtent()
	{
		return extent.get();
	}
	
	public static void setExtent(ExtentTest extentTestObject)
	{
		extent.set(extentTestObject);
	}
	
	public static void removeExtentObject() {
		extent.remove();
	}
}
