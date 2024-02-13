package commonUtilities;/*
package commonUtilities;
import org.assertj.core.api.SoftAssertions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.GlobalPage;

import static org.assertj.core.api.Assertions.*;


public final class VerificationUtility 
{

    private VerificationUtility()
    {

    }

    protected void verifyThat()
    {
        assertThat("a").contains("abc");
    }

    public void verifyHardTrue(boolean condition)
    {
        Assert.assertTrue(condition);
    }

    public void verifyHardTrue(boolean condition, String message)
    {
            Assert.assertTrue(condition, message);
    }

    public void verifyHardFalse(boolean condition)
    {
        Assert.assertFalse(condition);
    }

    public void verifyHardFalse(boolean condition, String message)
    {
        Assert.assertFalse(condition, message);
    }

    public void verifyHardEquals(boolean actual, boolean expected)
    {
        Assert.assertEquals(actual, expected);
    }

    public void verifyHardEquals(Object actual, Object expected)
    {
        Assert.assertEquals(actual, expected);
    }

    public void verifyHardEquals(String actual, String expected)
    {
        Assert.assertEquals(actual, expected);
    }

    public void verifyHardEquals(String actual, String expected, String message)
    {
        Assert.assertEquals(actual, expected, message);
    }

    public void verifyHardEquals(Object[] actual, Object[] expected)
    {
        Assert.assertEquals(actual, expected);
    }

    public void verifyHardFail(String message)
    {
        Assert.fail(message);
    }


    */
/********************************** Soft Assertion ******************************************//*


    public void verifySoftTrue(boolean condition)
    {
        SoftAssertFactory.getSoftAssert().assertTrue(condition);

    }

    public  static void verifySoftTrue(boolean condition, String message)
    {
        SoftAssertFactory.getSoftAssert().assertTrue(condition, message);
    }

    public void verifySoftFalse(boolean condition)
    {
        SoftAssertFactory.getSoftAssert().assertFalse(condition);
    }

    public void verifySoftFalse(boolean condition, String message)
    {
        SoftAssertFactory.getSoftAssert().assertFalse(condition, message);
    }

    public void verifySoftEquals(boolean actual, boolean expected)
    {

        SoftAssertFactory.getSoftAssert().assertEquals(actual, expected);

    }

    public void verifySoftEquals(Object actual, Object expected)
    {
        SoftAssertFactory.getSoftAssert().assertEquals(actual, expected);
    }

    public void verifySoftEquals(String actual, String expected)
    {
        SoftAssertFactory.getSoftAssert().assertEquals(actual, expected);
    }

    public void verifySoftEquals(Object[] actual, Object[] expected)
    {
        SoftAssertFactory.getSoftAssert().assertEquals(actual, expected);
    }

    public void verifySoftFail(String message)
    {
        SoftAssertFactory.getSoftAssert().fail(message);
    }
}
*/
