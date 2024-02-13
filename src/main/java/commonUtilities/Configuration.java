package commonUtilities;

import org.aeonbits.owner.Config;

@Config.Sources(value = "file:${user.dir}/src/test/resources/configuration/Configuration.properties")
public interface Configuration extends Config
{
    String testDataFile();
    String RunType();
    String DebugMode();
    Integer RetryCount();
    String ConsoleLogs();
    String ReportLogs();
    String LogLevelScreenshot();
    String Browser();
    boolean Headless();
    String Incognito();
    String RemoteURL();
    String ChromeVersion();
    String FirefoxVersion();
}
