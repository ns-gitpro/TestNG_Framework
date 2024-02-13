package browserFactory;

import commonUtilities.Configuration;
import constants.Framework_Constants;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Platform;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.Map;

public class FirefoxBrowserOptions
{
    private FirefoxBrowserOptions()
    {

    }

    static Configuration config = ConfigFactory.create(Configuration.class);

    public static final boolean Headless;

    static {
        try {
            Configuration config = ConfigFactory.create(Configuration.class);
            Headless = config.Headless();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static FirefoxOptions firefoxOptions()
    {
        FirefoxOptions options = new FirefoxOptions();
        if (config.RunType().equalsIgnoreCase("LOCAL"))
        {
            //options.setBrowserVersion(config.FirefoxVersion());
            if (Headless)
            {
                options.addArguments("--headless=new");
            }

            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.download.folderList", 2);
            profile.setPreference("browser.download.dir", Framework_Constants.getDownloadFilepath());
            profile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/csv,application/zip");

            options.setProfile(profile);
        }

        if (config.RunType().equalsIgnoreCase("REMOTE")){
            options = new org.openqa.selenium.firefox.FirefoxOptions();
            options.setCapability("browserName", "firefox");
            options.setCapability("browserVersion", "latest");
            options.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
        }
        return options;
    }

    public static FirefoxOptions firefoxOptionsForManualBrowser()
    {
        FirefoxOptions options = new FirefoxOptions();
        if (config.RunType().equalsIgnoreCase("LOCAL"))
        {
            System.setProperty("webdriver.firefox.driver",getDriverPath());
            options.setBinary(getBinaryPath());

            //options.setBrowserVersion(config.FirefoxVersion());
            if (Headless)
            {
                options.addArguments("--headless=new");
            }

            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.download.folderList", 2);
            profile.setPreference("browser.download.dir", Framework_Constants.getDownloadFilepath());
            profile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/csv,application/zip");

            options.setProfile(profile);
        }

        if (config.RunType().equalsIgnoreCase("REMOTE")){
            options = new org.openqa.selenium.firefox.FirefoxOptions();
            options.setCapability("browserName", "firefox");
            options.setCapability("browserVersion", "latest");
            options.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
        }
        return options;
    }

    public static String getBinaryPath()
    {
        String binaryPath = null;

        if (Platform.getCurrent().is(Platform.LINUX)) {
            binaryPath = Framework_Constants.getLinuxChromebinaryFilepath();
        } else if (Platform.getCurrent().is(Platform.MAC)) {
            //TO DO
        } else if (Platform.getCurrent().is(Platform.WINDOWS)) {
            //TO DO
        }
        return binaryPath;
    }

    public static String getDriverPath()
    {
        String driverPath = null;

        if (Platform.getCurrent().is(Platform.LINUX)) {
            driverPath = Framework_Constants.getLinuxChromedriverFilepath();
        } else if (Platform.getCurrent().is(Platform.MAC)) {
            //TO DO
        } else if (Platform.getCurrent().is(Platform.WINDOWS)) {
            //TO DO
        }
        return driverPath;
    }

}
