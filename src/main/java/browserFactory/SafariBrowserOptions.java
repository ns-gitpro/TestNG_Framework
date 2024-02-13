package browserFactory;

import commonUtilities.Configuration;
import constants.Framework_Constants;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Platform;

public class SafariBrowserOptions
{
    private SafariBrowserOptions()
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

    public static org.openqa.selenium.safari.SafariOptions safariOptions()
    {
        org.openqa.selenium.safari.SafariOptions options = new org.openqa.selenium.safari.SafariOptions();
        if (config.RunType().equalsIgnoreCase("LOCAL"))
        {

        }
        return options;
    }

    public static org.openqa.selenium.safari.SafariOptions safariOptionsForManualBrowser()
    {
        org.openqa.selenium.safari.SafariOptions options = new org.openqa.selenium.safari.SafariOptions();
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
