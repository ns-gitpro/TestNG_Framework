package commonUtilities;

import org.testng.asserts.SoftAssert;

public class SoftAssertFactory
{
    private SoftAssertFactory()
    {

    }
    private static ThreadLocal<SoftAssert> softAssert = new ThreadLocal<>();

    public static SoftAssert getSoftAssert()
    {
        return softAssert.get();
    }

    public static void setSoftAssert(SoftAssert softAssertParam)
    {
        softAssert.set(softAssertParam);
    }

    public static void unload()
    {
        softAssert.remove();
    }
}