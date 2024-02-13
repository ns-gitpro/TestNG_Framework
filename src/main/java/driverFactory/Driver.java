package driverFactory;

import commonUtilities.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;

public final class Driver
{
    private Driver()
    {

    }

    static Configuration config = ConfigFactory.create(Configuration.class);

    public static void initDriver()
    {
        String browser = config.Browser();
        String runType = config.RunType();

        WebDriver driver = DriverFactory.getDriver(runType,browser);
        DriverManager.setDriver(driver);

    }

    public static void quitDriver()
    {
        DriverManager.quitDriver();
    }
}
