package commonUtilities;

import driverFactory.DriverManager;
import org.apache.commons.exec.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v117.network.Network;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;

import static commonUtilities.AsynchronousWaitUtil.addPaddedWait;

import static commonUtilities.PrintUtility.logInfo;
import static driverFactory.DriverManager.getDriver;
import com.google.common.io.Resources;

public final class BrowserUtilities
{
	public BrowserUtilities()
	{

	}

	/**
	 * 
	 * Creates a new browser tab and switches the focus for future commands of this driver to the new tab.
	 * 
	 * @since 11/06/2023
	 * @author Nikhil Singh
	 */
	public static void openNewTab()
	{
		getDriver().switchTo().newWindow(WindowType.TAB);
	}
	
	/**
	 * 
	 * Creates a new browser window and switches the focus for future commands of this driver to the new window.
	 * 
	 * @since 11/06/2023
	 * @author Nikhil Singh
	 * 
	 */
	public static void openNewWindow()
	{
		getDriver().switchTo().newWindow(WindowType.WINDOW);
	}
	
	/**
	 *
	 * Switch to tab with respect to index position of Opened tab (0th Index -> 1st Tab).
	 *
	 * @since 11/06/2023
	 * @author Nikhil Singh
	 * @return element
	 * @throws InterruptedException
	 *
	 */
	public static void switchToTab(int tabIndexPosition) {
		ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
		getDriver().switchTo().window(tabs.get(tabIndexPosition));
	}
	
	public static void closeTab(int tabIndexPosition)
	{
		ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
		getDriver().switchTo().window(tabs.get(tabIndexPosition)).close();
	}
	
	public static void focusCurrentWindow()
	{
		String current = getDriver().getWindowHandle();
		ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
		for (int i = 0; i < tabs.size(); i++) 
		{
			if (tabs.get(i).equals(current)) 
			{
				switchToTab(i);
				break;
			}
		}
	}
	
	public static void refreshPage() throws Exception
	{
		long start = System.currentTimeMillis();
		try {
			getDriver().navigate().refresh();
			addPaddedWait(2);
			long finish = System.currentTimeMillis();
			long totalTime = finish - start;
			PrintUtility.logPass(PrintUtility.printWaitTime("refreshScreen()",totalTime,""));
		}
		catch (Exception e)
		{
			long finish = System.currentTimeMillis();
			long totalTime = finish - start;
			PrintUtility.logFail(PrintUtility.printWaitTime("refreshScreen()",totalTime,""));
		}
	}
	
	
	public static String duplicateTab() throws Exception {

		String url = getDriver().getCurrentUrl();
		String currentWinHandle =  getDriver().getWindowHandle();
		((JavascriptExecutor) getDriver()).executeScript("window.open()");
		Set<String> winHandles =  getDriver().getWindowHandles();

		String Sub_window = "";
		for (String handle : winHandles) {
			if (!handle.equals(currentWinHandle)) {
				Sub_window = handle;
			}
		}
		getDriver().switchTo().window(Sub_window);
		getDriver().get(url);
		return currentWinHandle;
	}


	public static void deleteAllCookies()
	{
		long start = System.currentTimeMillis();
		getDriver().manage().deleteAllCookies();
		if(getCookies().isEmpty())
		{
			long finish = System.currentTimeMillis();
			long totalTime = finish - start;
			PrintUtility.logPass(PrintUtility.printWaitTime("deleteCookies()",totalTime,"All cookies deleted successfully."));
		}
		else
		{
			long finish = System.currentTimeMillis();
			long totalTime = finish - start;
			PrintUtility.logFail(PrintUtility.printWaitTime("deleteCookies()",totalTime,"All cookies not deleted successfully."));
		}
	}


	public static void deleteCookie(Cookie cookie)
	{
		long start = System.currentTimeMillis();
		getDriver().manage().deleteCookie(cookie);
		if(getCookies().contains(cookie))
		{
			long finish = System.currentTimeMillis();
			long totalTime = finish - start;
			PrintUtility.logPass(PrintUtility.printWaitTime("deleteCookie()",totalTime,"cookie deleted successfully."));
		}
		else
		{
			long finish = System.currentTimeMillis();
			long totalTime = finish - start;
			PrintUtility.logFail(PrintUtility.printWaitTime("deleteCookie()",totalTime,"cookie not deleted successfully."));
		}
	}

	public static Set<Cookie> getCookies()
	{
		return getDriver().manage().getCookies();
	}

	public static int getCookiesCount()
	{
		long start = System.currentTimeMillis();
		Set<Cookie> cookies=null;
		try
		{
			cookies = getCookies();
			int count = cookies.size();
			if(count>0)
			{
				long finish = System.currentTimeMillis();
				long totalTime = finish - start;
				PrintUtility.logPass(PrintUtility.printWaitTime("getCookiesCount()",totalTime,"Total Cookies Count : "+count));
			}
			else
			{
				long finish = System.currentTimeMillis();
				long totalTime = finish - start;
				PrintUtility.logPass(PrintUtility.printWaitTime("getCookiesCount()",totalTime,"Total Cookies Count : 0"));
			}
		}
		catch (Exception e)
		{
			long finish = System.currentTimeMillis();
			long totalTime = finish - start;
			PrintUtility.logFail(PrintUtility.printWaitTime("getCookiesCount()",totalTime,e.getMessage()));
		}
		return cookies.size();
	}

	public static void getCookiesInfo()
	{
		Set<Cookie> cookies = getCookies();

		for(Cookie cookie: cookies)
		{
			logInfo("Cookie Name : " + cookie.getName() + " : "+ cookie.getValue() + " : " + cookie.getDomain());
		}
	}

	public static Cookie getCookiesNamed(String cookie)
	{
		return getDriver().manage().getCookieNamed(cookie);
	}

	public static void clearBrowserCache()
	{
		ChromeDriver driver = (ChromeDriver) DriverManager.getDriver();
		driver.getDevTools().createSessionIfThereIsNotOne();
		driver.getDevTools().send(Network.clearBrowserCache());
		driver.getDevTools().send(Network.clearBrowserCookies());
	}

	public static boolean isGoogleChromeInstalled() {
		try {
			String command = "google-chrome --version";
			CommandLine cmdLine = CommandLine.parse(command);
			DefaultExecutor executor = new DefaultExecutor();

			// Create a PumpStreamHandler to capture the output of the command
			PumpStreamHandler handler = new PumpStreamHandler();
			executor.setStreamHandler(handler);

			int exitCode = executor.execute(cmdLine);
			return exitCode == 0;
		} catch (IOException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static void installGoogleChrome() {
		try {
			System.out.println("Downloading (Native) Google Chrome...");

			String wegetCommand = "wget -N https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb";
			CommandLine wegetCmdLine = CommandLine.parse(wegetCommand);
			DefaultExecutor wegetExecutor = new DefaultExecutor();
			wegetExecutor.execute(wegetCmdLine);

			String installCommand = "sudo apt install -y ./google-chrome-stable_current_amd64.deb";
			DefaultExecutor installExecutor = new DefaultExecutor();
			installExecutor.execute(CommandLine.parse(installCommand));

		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("Failed to install Google Chrome.");
		}
    }


}
