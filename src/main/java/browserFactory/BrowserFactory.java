package browserFactory;

import commonUtilities.Configuration;
import constants.Browsers;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public final class BrowserFactory
{
	private BrowserFactory()
	{

	}

	public static String runCommand(String command)
	{
		String line = null;
		try 
		{
			if(getOperatingSystem().equalsIgnoreCase("windows")) {
				ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command);
				builder.redirectErrorStream(true);
				Process process = builder.start();
				get(process);
			}
			else
			{
				String[] args = new String[] {"/bin/bash", "-c", command, "with", "args"};
				Process process = new ProcessBuilder(args).start();
				get(process);
			}
	    }
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return line;
	}
	
	
	public String getChromeDebugPort() throws InterruptedException
	{
		String command = "chrome-debug";
		String portNumber_Raw = runCommand(command);
		Thread.sleep(2);
		String portNumber_val = portNumber_Raw.split(":")[1];
		String portNumber = portNumber_val.trim();
		System.out.println("Chrome-debug PortNumber= "+portNumber);
		return portNumber;
	}

	public static void printResults(Process process) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line = "";
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
	}

	public static void get(Process process) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line = "";
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
	}

	public static String getOperatingSystem()
	{
		String os = System.getProperty("os.name");
		return os;
	}
	
}
