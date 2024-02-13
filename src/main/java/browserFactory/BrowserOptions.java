package browserFactory;

import commonUtilities.Configuration;
import constants.Framework_Constants;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.safari.SafariOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class BrowserOptions
{
    private BrowserOptions()
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

    @SneakyThrows
    public static ChromeOptions chromeOptions()
    {
        ChromeOptions options = new ChromeOptions();
        if (config.RunType().equalsIgnoreCase("LOCAL"))
        {
            options.setBrowserVersion(config.ChromeVersion());
            options.addArguments("--remote-allow-origins=*");
            if (Headless) {
                options.addArguments("--headless", "--window-size=1920,1200", "--disable-extensions", "--no-sandbox", "--disable-dev-shm-usage");
            }
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
            options.addArguments("--password-store=basic");
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("profile.default_content_settings.popups",0);
            prefs.put("download.default_directory", Framework_Constants.getDownloadFilepath());
            options.setExperimentalOption("prefs", prefs);

        }
        if (config.RunType().equalsIgnoreCase("REMOTE")){
            options = new ChromeOptions();
            //options.addArguments("--remote-allow-origins=*");
            options.setCapability("enableVNC",true);
            options.setCapability("enableVideo",true);
        }

        return options;
    }


    public static FirefoxOptions firefoxOptions()
    {
        FirefoxOptions options = new FirefoxOptions();
        if (config.RunType().equalsIgnoreCase("LOCAL"))
        {
            //options.setBrowserVersion(config.FirefoxVersion());
            if (Headless)
            {
                options.addArguments("-headless");
            }

            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.download.folderList", 2);
            profile.setPreference("browser.download.dir", Framework_Constants.getDownloadFilepath());
            profile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/csv,application/zip");

            options.setProfile(profile);
        }

        if (config.RunType().equalsIgnoreCase("REMOTE")){
            options = new FirefoxOptions();
            options.setCapability("browserName", "firefox");
            options.setCapability("browserVersion", "latest");
            options.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
        }
        return options;
    }

    public static EdgeOptions edgeOptions()
    {
        EdgeOptions options = new EdgeOptions();
        if(Headless)
        {
            options.addArguments("--headless", "--window-size=1920,1200","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
        }
        options.addArguments("start-maximized");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        return options;
    }

    public static SafariOptions safariOptions()
    {
        SafariOptions options = new SafariOptions();
        if (config.RunType().equalsIgnoreCase("LOCAL"))
        {

        }
        return options;
    } @SneakyThrows
    public static ChromeOptions chromeOptionsForManualBrowser()
    {
        ChromeOptions options = new ChromeOptions();
        if (config.RunType().equalsIgnoreCase("LOCAL"))
        {

            System.setProperty("webdriver.chrome.driver",getDriverPath());
            //options.setBinary(getBinaryPath());

            options.setBrowserVersion(config.ChromeVersion());
            options.addArguments("--remote-allow-origins=*");
            if (Headless) {
                options.addArguments("--headless", "--window-size=1920,1200", "--disable-extensions", "--no-sandbox", "--disable-dev-shm-usage");
            }
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
            options.addArguments("--password-store=basic");
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("profile.default_content_settings.popups",0);
            prefs.put("download.default_directory", Framework_Constants.getDownloadFilepath());
            options.setExperimentalOption("prefs", prefs);

        }
        if (config.RunType().equalsIgnoreCase("REMOTE")){
            options = new ChromeOptions();
            //options.addArguments("--remote-allow-origins=*");
            options.setCapability("enableVNC",true);
            options.setCapability("enableVideo",true);
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
                options.addArguments("-headless");
            }

            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.download.folderList", 2);
            profile.setPreference("browser.download.dir", Framework_Constants.getDownloadFilepath());
            profile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/csv,application/zip");

            options.setProfile(profile);
        }

        if (config.RunType().equalsIgnoreCase("REMOTE")){
            options = new FirefoxOptions();
            options.setCapability("browserName", "firefox");
            options.setCapability("browserVersion", "latest");
            options.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
        }
        return options;
    }

    public static EdgeOptions edgeOptionsForManualBrowser()
    {
        EdgeOptions options = new EdgeOptions();
        System.setProperty("webdriver.edge.driver",getDriverPath());
        options.setBinary(getBinaryPath());

        if(Headless)
        {
            options.addArguments("--headless", "--window-size=1920,1200","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
        }
        options.addArguments("start-maximized");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        return options;
    }

    public static SafariOptions safariOptionsForManualBrowser()
    {
        SafariOptions options = new SafariOptions();
        if (config.RunType().equalsIgnoreCase("LOCAL"))
        {

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

