package reportUtilities;

import com.aventstack.extentreports.Status;

public class ExtentLogEvents
{
    private ExtentLogEvents()
    {

    }
    /*************************** Log Events ******************************/
    public static void logFail(String message)
    {
        ExtentFactory.getExtent().log(Status.FAIL, "<p style='color:"+LogEventColor.FAIL_RADICAL_RED +"';>" + message + "</font>");
    }

    public static void logWarn(String message)
    {
        ExtentFactory.getExtent().log(Status.WARNING, "<p style='color:"+LogEventColor.WARN_TEXAS_ROSE +"';>" + message + "</font>");
    }

    public static void logSkip(String message)
    {
        ExtentFactory.getExtent().log(Status.SKIP, "<p style='color:"+LogEventColor.SKIP_ORANGE +"';>" + message + "</font>");
    }

    public static void logPass(String message)
    {
        ExtentFactory.getExtent().log(Status.PASS, "<p style='color:"+LogEventColor.PASS_GREEN +"';>" + message + "</p>");
    }

    public static void logInfo(String message)
    {
        ExtentFactory.getExtent().log(Status.INFO, "<p style='color:"+LogEventColor.INFO_VIVID_CYAN+"';>" + message + "</p>");
    }
}
