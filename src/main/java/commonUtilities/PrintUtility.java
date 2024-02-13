package commonUtilities;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import reportUtilities.ExtentLogEvents;

import java.util.concurrent.TimeUnit;

public final class PrintUtility
{
	private PrintUtility()
	{
		
	}

	public static final String INFO = "[INFO] ";
	public static final String PASS = "[PASS] ";
	public static final String WARN = "[WARN] ";
	public static final String SKIP = "[SKIP] ";
	public static final String FAIL = "[FAIL] ";

	static Configuration config = ConfigFactory.create(Configuration.class);
	public static final String ConsoleLogs = config.ConsoleLogs();
	public static final String ReportLogs = config.ReportLogs();

	String waitColors = ConsoleColors.GREEN;
	
	/****************************** Extent-Report Methods ****************************************/

	public static void logScenario(String scenario)
	{
		logInfo("Scenario: " + scenario);
	}

	public static void logStep(String stepInfo)
	{
		logInfo(stepInfo);
	}

	/****** The Originals *********/
	public static void logFail(String message) 
	{
		switch (ConsoleLogs)
		{
			case "ON" :
				System.out.println(ConsoleColors.RED + "\n"+FAIL+"Error Occurred: " + message + ConsoleColors.RESET);
				break;
			case "OFF" :
				/*** No Logs will be printed. ***/
				break;
			default:
				logWarn("Invalid Property defined !");
				break;
		}

		switch (ReportLogs)
		{
			case "ON" :
				ExtentLogEvents.logFail("\nError Occurred: " + message);
				break;
			case "OFF" :
				/*** No Logs will be printed. ***/
				break;
			default:
				ExtentLogEvents.logWarn("Invalid Property defined !");
				break;
		}
		FunctionLibrary.softAssert.assertTrue(false, message);
	}

	public static void logWarn(String message) 
	{
		switch (ConsoleLogs)
		{
			case "ON" :
				System.out.println(ConsoleColors.WARN_TEXAS_ROSE + "\n"+WARN+"Warning: " + message + ConsoleColors.RESET);
				break;
			case "OFF" :
				/*** No Logs will be printed. ***/
				break;
			default:
				System.out.println(ConsoleColors.WARN_TEXAS_ROSE + "\n"+WARN+"Invalid Property defined !" + ConsoleColors.RESET);
				break;
		}

		switch (ReportLogs)
		{
			case "ON" :
				ExtentLogEvents.logWarn("\nWarning: " + message);
				break;
			case "OFF" :
				/*** No Logs will be printed. ***/
				break;
			default:
				ExtentLogEvents.logWarn("Invalid Property defined !");
				break;
		}
		//softAssert.assertTrue(false, message);
	}

	public static void logSkip(String message) {
		switch (ConsoleLogs)
		{
			case "ON" :
				System.out.println(ConsoleColors.SKIP_ORANGE + "\n"+SKIP+"Skipped: " + message + ConsoleColors.RESET);
				break;
			case "OFF" :
				/*** No Logs will be printed. ***/
				break;
			default:
				logWarn("Invalid Property defined !");
				break;
		}

		switch (ReportLogs)
		{
			case "ON" :
				ExtentLogEvents.logSkip("\nSkipped: " + message);
				break;
			case "OFF" :
				break;
				/*** No Logs will be printed. ***/
			default:
				ExtentLogEvents.logWarn("Invalid Property defined !");
				break;
		}
	}

	public static void logPass(String message)
	{
		switch (ConsoleLogs)
		{
			case "ON" :
				System.out.println(ConsoleColors.PASS_GREEN + "\n"+ PASS + message + ConsoleColors.RESET);
				break;
			case "OFF" :
				/*** No Logs will be printed. ***/
				break;
			default:
				ExtentLogEvents.logWarn("Invalid Property defined !");
				break;
		}

		switch (ReportLogs)
		{
			case "ON" :
				ExtentLogEvents.logPass("\n"+message);
				break;
			case "OFF" :
				/*** No Logs will be printed. ***/
				break;
			default:
				ExtentLogEvents.logWarn("Invalid Property defined !");
				break;
		}
	}

	public static void logInfo(String message)
	{
		switch (ConsoleLogs)
		{
			case "ON" :
				System.out.println(ConsoleColors.BLUE + "\n"+INFO+message + ConsoleColors.RESET);
				break;
			case "OFF" :
				/*** No Logs will be printed. ***/
				break;
			default:
				System.out.println(ConsoleColors.WARN_TEXAS_ROSE + "\n"+WARN+"Invalid Property defined !" + ConsoleColors.RESET);
				break;
		}

		switch (ReportLogs)
		{
			case "ON" :
				ExtentLogEvents.logInfo("\n" + message);
				break;
			case "OFF" :
				/*** No Logs will be printed. ***/
				break;
			default:
				ExtentLogEvents.logWarn("Invalid Property defined !");
				break;
		}
	}


	public static String printWaitTime(String waitMethodType, long time, By locator)
	 {
		 String printWaitTime = "";
		 long millis = TimeUnit.MILLISECONDS.toMillis(time);
		 long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
		 if(seconds>0)
		 {
			 printWaitTime = waitMethodType+" -> "+ seconds+" sec"+" Element -> "+ locator;
		 }
		 else
		 {
			 printWaitTime = waitMethodType+" -> "+ millis+" ms"+" Element -> "+ locator;
		 }
		 return printWaitTime;
	 }

	public static String printWaitTime(String waitMethodType, long time, String msg, By locator)
	 {
		 String printWaitTime = "";
		 long millis = TimeUnit.MILLISECONDS.toMillis(time);
		 long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
		 if(seconds>0)
		 {
			 printWaitTime = (waitMethodType+" -> "+ seconds+" sec"+"  "+ msg +" Element -> "+locator);
		 }
		 else
		 {
			 printWaitTime = (waitMethodType+" -> "+ millis+" ms"+"  "+ msg +" Element -> "+locator);
		 }
		 return printWaitTime;
	 }

	public static String printWaitTime(String waitMethodType, long time, String msg)
	{
		String printWaitTime = "";
		long millis = TimeUnit.MILLISECONDS.toMillis(time);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
		if(seconds>0)
		{
			printWaitTime = (waitMethodType+" -> "+ seconds+" sec"+"  "+ msg);
		}
		else
		{
			printWaitTime = (waitMethodType+" -> "+ millis+" ms"+"  "+ msg);
		}
		return printWaitTime;
	}

	public static void printWaitTime(String waitMethodType, long time)
	 {
		 long millis = TimeUnit.MILLISECONDS.toMillis(time);
		 long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
		 if(seconds>0)
		 {
			 ExtentLogEvents.logPass(waitMethodType+" -> "+ seconds+" sec"+ ConsoleColors.RESET);
		 }
		 else
		 {
			 ExtentLogEvents.logPass(waitMethodType+" -> "+ millis+" ms"+ ConsoleColors.RESET);
		 }
	 }
}
