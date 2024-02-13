package reportUtilities;

public final class ExtentUtilities
{
    private ExtentUtilities()
    {

    }
    public static void assignCategory(String category)
    {
        ExtentFactory.getExtent().assignCategory(category);
    }

    public static void assignAuthor(String authorName)
    {
        ExtentFactory.getExtent().assignAuthor(authorName);
    }

    public static void assignDevice(String deviceName)
    {
        ExtentFactory.getExtent().assignDevice(deviceName);
    }
}
