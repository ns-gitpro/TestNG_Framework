package browserFactory;

import commonUtilities.Configuration;
import constants.Framework_Constants;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class ChromeBrowserOptions
{
    private ChromeBrowserOptions()
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
            //options.addArguments("--remote-allow-origins=*");
            if (Headless)
            {
                System.out.println("***** Executing Chrome In Headless mode *****");
                options.addArguments("--headless=new");
                options.addArguments("disable-infobars");
                options.addArguments("--disable-extensions");
                if (Platform.getCurrent().is(Platform.WINDOWS))
                    options.addArguments("--disable-gpu");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--no-sandbox");
            }

            options.setExperimentalOption("useAutomationExtension", false);
            //options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
            options.addArguments("--password-store=basic");
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("profile.default_content_settings.popups",0);
            prefs.put("download.default_directory", Framework_Constants.getDownloadFilepath());
            options.setExperimentalOption("prefs", prefs);

        }
        if (config.RunType().equalsIgnoreCase("REMOTE")){
            options = new org.openqa.selenium.chrome.ChromeOptions();
            //options.addArguments("--remote-allow-origins=*");
            options.setCapability("enableVNC",true);
            options.setCapability("enableVideo",true);
        }

        return options;
    }

    @SneakyThrows
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
            options = new org.openqa.selenium.chrome.ChromeOptions();
            //options.addArguments("--remote-allow-origins=*");
            options.setCapability("enableVNC",true);
            options.setCapability("enableVideo",true);
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
