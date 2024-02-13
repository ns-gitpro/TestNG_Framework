package driverFactory;

import org.openqa.selenium.WebDriver;

public class DriverFactory
{

    public static WebDriver getDriver(String runType, String browserName)
    {
        WebDriver driver = null;
        if (runType.equalsIgnoreCase("LOCAL"))
        {
            driver = LocalDriverFactory.getLocalDriver(browserName);
        }
        else if (runType.equalsIgnoreCase("REMOTE"))
        {
            driver = RemoteDriverFactory.getRemoteDriver(browserName);
        }
        return driver;
    }
}
