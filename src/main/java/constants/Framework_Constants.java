package constants;
/**
 * @author Nikhil Singh
 *
 */
public final class Framework_Constants
{
	private Framework_Constants()
	{

	}
	//************************* Dynamic Waits *************************************************//
	private static final int timeout = 40;
	private static final int timeoutMax = 180;
	private static final int pollTime = 500;

	public  static int getTimeout()
	{
		return timeout;
	}

	public  static int getTimeoutMax()
	{
		return timeoutMax;
	}

	public  static int getPollTime()
	{
		return pollTime;
	}




	//************************* File Paths *************************************************//

	private static final String RESOURCES_FILEPATH = System.getProperty("user.dir")+ "/src/test/resources/";
	public  static String getResourcesFilepath()
	{
		return RESOURCES_FILEPATH;
	}
	private static final String DOWNLOAD_FOLDER_NAME = "downloads";
	public  static String getDownloadFolderName()
	{
		return DOWNLOAD_FOLDER_NAME;
	}
	private static final String DOWNLOAD_FILEPATH = System.getProperty("user.dir")+ "/src/test/resources/downloads/";
	public  static String getDownloadFilepath()
	{
		return DOWNLOAD_FILEPATH;
	}

	private static final String PROPERTIES_FILEPATH = System.getProperty("user.dir")+ "/src/test/resources/configuration/Configuration.properties";
	public  static String getProperties_FilePath()
	{
		return PROPERTIES_FILEPATH;
	}

	public static final String LINUX_CHROMEDRIVER_FILEPATH = System.getProperty("user.dir")+"/src/main/resources/driver/linux/chrome/chromeDriver/chromedriver";
	public  static String getLinuxChromedriverFilepath()
	{
		return LINUX_CHROMEDRIVER_FILEPATH;
	}
	public static final String LINUX_CHROMEBINARY_FILEPATH = System.getProperty("user.dir")+"/src/main/resources/driver/linux/chrome/binary/chrome";
	public  static String getLinuxChromebinaryFilepath()
	{
		return LINUX_CHROMEBINARY_FILEPATH;
	}
}
