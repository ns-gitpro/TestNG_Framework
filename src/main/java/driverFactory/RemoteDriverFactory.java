package driverFactory;

import browserFactory.BrowserOptions;
import commonUtilities.Configuration;
import constants.Browsers;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;


public final class RemoteDriverFactory
{
    private RemoteDriverFactory()
    {

    }
    @SneakyThrows
    public static WebDriver getRemoteDriver(String browser)
    {
        WebDriver driver = null;
        Configuration config = ConfigFactory.create(Configuration.class);
        Browsers browserName = Browsers.valueOf(browser.toUpperCase());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        switch (browserName)
        {
            case CHROME:
                capabilities.setBrowserName("chrome");
                driver = new RemoteWebDriver(new URL(config.RemoteURL()), BrowserOptions.chromeOptions());
                break;
            case FIREFOX:
                capabilities.setBrowserName("firefox");
                driver = new RemoteWebDriver(new URL(config.RemoteURL()), BrowserOptions.firefoxOptions());
                break;
            case EDGE:
                capabilities.setBrowserName("edge");
                driver = new RemoteWebDriver(new URL(config.RemoteURL()), BrowserOptions.edgeOptions());
                break;
            case SAFARI:
                capabilities.setBrowserName("safari");
                driver = new RemoteWebDriver(new URL(config.RemoteURL()), BrowserOptions.safariOptions());
                break;
            default:
                driver = null;
                break;
        }
        //driver = new RemoteWebDriver(new URL(config.RemoteURL()), capabilities);
        return  driver;
    }
}
