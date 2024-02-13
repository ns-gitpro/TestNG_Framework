package driverFactory;

import browserFactory.BrowserFactory;
import commonUtilities.Configuration;
import commonUtilities.PrintUtility;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public final class DriverManager
{
    private DriverManager()
    {

    }

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver()
    {
        return driver.get();
    }

    public static void setDriver(WebDriver driverParam)
    {
        driver.set(driverParam);
    }

    public static void unload()
    {
        driver.remove();
    }

    public static void quitDriver()
    {
        if(Objects.nonNull(DriverManager.getDriver()))
        {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }

    public static void closeDriver()
    {
        Configuration config = ConfigFactory.create(Configuration.class);
        if(Objects.nonNull(DriverManager.getDriver()))
        {
            String DEBUG_MODE = config.DebugMode();
            switch (DEBUG_MODE)
            {
                case "ON" :
                    PrintUtility.logInfo("Browser will remain Open");
                    break;
                case "OFF" :
                    DriverManager.getDriver().close();
                    DriverManager.unload();
                    break;
            }

        }
    }

}
