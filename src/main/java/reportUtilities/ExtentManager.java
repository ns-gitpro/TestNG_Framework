package reportUtilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import commonUtilities.CommonUtility;
import commonUtilities.FunctionLibrary;

import java.util.Objects;

/**
 * @author Nikhil Singh
 *
 */
public final class ExtentManager extends FunctionLibrary
{
	private ExtentManager()
	{

	}
	private static ExtentReports extent;
	
	public static ExtentReports createInstance(String fileName)
	{
		if(Objects.isNull(extent))
		{
			String directoryName = "ExtentReports";
			String path = System.getProperty("user.dir")+"\\test-output";
			CommonUtility.createDirectoryIfNotExists(path,directoryName);

			ExtentSparkReporter sparkReport = new ExtentSparkReporter(fileName);

			extent = new ExtentReports();
			extent.attachReporter(sparkReport);


			sparkReport.config().setEncoding("utf-8");
			sparkReport.config().setDocumentTitle("Automation Report");
			sparkReport.config().setReportName("Automation Report");
			sparkReport.config().setTheme(Theme.STANDARD);
			sparkReport.config().thumbnailForBase64(true);
			sparkReport.config().setCss(".r-img { width: 40%; position: relative; height: auto;}");


			extent.setSystemInfo("Organization", "IRRI | CIMMYT");
			extent.setSystemInfo("Project", "EBS Project");
			extent.setSystemInfo("OS", System.getProperty("os.name"));
			extent.setSystemInfo("Java", System.getProperty("java.specification.version"));

		}
		return extent;
	}

	public static void flushReports()
	{
		extent.flush();
	}
	

}
