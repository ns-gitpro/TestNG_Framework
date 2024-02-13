package driverFactory;

import browserFactory.*;
import commonUtilities.Configuration;
import constants.Browsers;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import static commonUtilities.BrowserUtilities.installGoogleChrome;
import static commonUtilities.BrowserUtilities.isGoogleChromeInstalled;

public final class LocalDriverFactory
{
    private LocalDriverFactory()
    {

    }

    public static WebDriver getLocalDriver(String browser)
    {
        Configuration config = ConfigFactory.create(Configuration.class);
        Browsers browserName = Browsers.valueOf(browser.toUpperCase());

        WebDriver driver = null;
        try
        {
             switch (browserName)
            {
               case CHROME:
                    driver = new ChromeDriver(ChromeBrowserOptions.chromeOptions());
                    driver.manage().window().maximize();
                    break;
                case FIREFOX:
                    driver = new FirefoxDriver(FirefoxBrowserOptions.firefoxOptions());
                    driver.manage().window().maximize();
                    break;
                case EDGE:
                    driver = new EdgeDriver(EdgeBrowserOptions.edgeOptions());
                    driver.manage().window().maximize();
                    break;
                case SAFARI:
                    driver = new SafariDriver(SafariBrowserOptions.safariOptions());
                    driver.manage().window().maximize();
                    break;
                default:
                    driver = null;
                    break;
            }
        }
        catch (Exception e)
        {
            System.out.println("Browser Launch Failed with Selenium Manager");
            System.out.println("\n***** BROWSER FAIL SAFE MECHANISM ACTIVATED *****");
            switch (browserName)
            {
                case CHROME:
                    if(Platform.getCurrent().is(Platform.LINUX))
                    {
                        if (!isGoogleChromeInstalled()) {
                            installGoogleChrome();
                        } else {
                            System.out.println("Google Chrome is already installed.");
                        }
                        driver = new ChromeDriver(ChromeBrowserOptions.chromeOptions());
                    }
                    else
                    {
                        driver = new ChromeDriver(ChromeBrowserOptions.chromeOptionsForManualBrowser());
                    }
                    driver.manage().window().maximize();
                    break;
                case FIREFOX:
                    driver = new FirefoxDriver(FirefoxBrowserOptions.firefoxOptionsForManualBrowser());
                    driver.manage().window().maximize();
                    break;
                case EDGE:
                    driver = new EdgeDriver(EdgeBrowserOptions.edgeOptionsForManualBrowser());
                    driver.manage().window().maximize();
                    break;
                case SAFARI:
                    driver = new SafariDriver(SafariBrowserOptions.safariOptionsForManualBrowser());
                    driver.manage().window().maximize();
                    break;
                default:
                    driver = null;
                    break;
            }
        }
        return driver;
    }
}
