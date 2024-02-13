package reportUtilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import commonUtilities.ConsoleColors;
import commonUtilities.ScreenshotUtilities;
import driverFactory.DriverManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

/**
 * @author Nikhil Singh
 *
 */
public class ExtentListener implements ITestListener
{
	static Date d = new Date();
	//static String fileName = "Report_" + d.toString().replace(":", "_") + ".html";
	static String fileName = "Report.html";
	private static ExtentReports report ;
	ExtentTest test;
	
	public void onTestStart(ITestResult result) 
	{
		/*String text[] = result.getTestClass().getName().split("[.]")[1];
		String tc_name = text[1];*/
		test = report.createTest(result.getTestClass().getName().split("[.]")[1]);
		ExtentFactory.setExtent(test);
	}
	
	public void onTestSuccess(ITestResult result)
	{
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>"+methodName+"() : PASSED"+"</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		ExtentFactory.getExtent().pass(m);
	}
	
	public void onTestFailure(ITestResult result) 
	{
		String methodName = result.getMethod().getMethodName();
		String screenshotPath = ScreenshotUtilities.captureScreenshotBase64();
		ExtentFactory.getExtent().fail("<b><font color=red>" + "Failure URL: " + "</font></b>" +
						  "<a style='#F0F8FF' href='"+ DriverManager.getDriver().getCurrentUrl()+"'>"+DriverManager.getDriver().getCurrentUrl()+"</a>");
		
		try 
		{
			ExtentFactory.getExtent().fail("<b><font color=red>" + "Failure Screeshot: " + "</font></b>",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotPath).build());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		ExtentFactory.getExtent().fail("<details>" + "<summary>" + "<b>" + "<font color="+"red>" + "Exception Occurred: Click to see"
				+ "</font>" + "</b>" + "</summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details>" + "\n");
		
		String logText = "<b>"+methodName+"() : FAILED"+"</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		ExtentFactory.getExtent().log(Status.FAIL,m);
		
		System.out.println(ConsoleColors.RED+"\n\nTest Case Failed:\n"+ConsoleColors.RESET);
		result.getThrowable().printStackTrace();
	}
	
	public void onTestSkipped(ITestResult result) 
	{
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>"+methodName+"() : SKIPPED"+"</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
		ExtentFactory.getExtent().skip(m);
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>"+methodName+"() : WARNING"+"</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
		ExtentFactory.getExtent().warning(m);
	}

	public void onStart(ITestContext context) 
	{
		String filePath = System.getProperty("user.dir")+"/test-output/ExtentReports/"+fileName;
		try 
		{
			 report = ExtentManager.createInstance(filePath);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void onFinish(ITestContext context) 
	{
		Iterator<ITestResult> skippedTestCases = context.getSkippedTests().getAllResults().iterator();
        while (skippedTestCases.hasNext())
        {
            ITestResult skippedTestCase = skippedTestCases.next();
            ITestNGMethod method = skippedTestCase.getMethod();           
            int skip_size = context.getSkippedTests().getResults(method).size();
            if ( skip_size > 1)
            {
            	skippedTestCases.remove();
            	//report.removeTest(test);
            }
            else
            {
            	int pass_size = context.getPassedTests().getResults(method).size();
                if (pass_size > 0)
                {
                	skippedTestCases.remove();
                	//report.removeTest(test);
                }                         
            }           
        }
		// TODO Auto-generated method stub
		if(report!=null)
		{
			report.flush();
		}
	}

}
