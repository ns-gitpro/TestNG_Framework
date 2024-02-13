package browserFactory;

import commonUtilities.Configuration;
import constants.Framework_Constants;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Platform;

import java.util.Collections;

public class EdgeBrowserOptions
{
    private EdgeBrowserOptions()
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

    public static org.openqa.selenium.edge.EdgeOptions edgeOptions()
    {
        org.openqa.selenium.edge.EdgeOptions options = new org.openqa.selenium.edge.EdgeOptions();
        if(Headless)
        {
            options.addArguments("--headless", "--window-size=1920,1200","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
        }
        options.addArguments("start-maximized");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        return options;
    }

    public static org.openqa.selenium.edge.EdgeOptions edgeOptionsForManualBrowser()
    {
        org.openqa.selenium.edge.EdgeOptions options = new org.openqa.selenium.edge.EdgeOptions();
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
