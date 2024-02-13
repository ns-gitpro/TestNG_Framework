package commonUtilities;

import driverFactory.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ScreenshotUtilities
{
    public static String screenshotPath;
    public static String screenshotName;

    public static String captureScreenshot()
    {
        TakesScreenshot scrShot =((TakesScreenshot) DriverManager.getDriver());
        File srcFile=scrShot.getScreenshotAs(OutputType.FILE);
        Date d = new Date();
        screenshotName = d.toString().replace(":", "_").replace(" ", "_");
        screenshotPath = System.getProperty("user.dir")+"/screenshots/"+screenshotName;
        try
        {
            FileUtils.copyFile(srcFile, new File(screenshotPath));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return screenshotPath;
    }

    public static String captureScreenshotBase64()
    {
        String base64ScreenshotCode = ((TakesScreenshot)DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
        return base64ScreenshotCode;
    }
}
