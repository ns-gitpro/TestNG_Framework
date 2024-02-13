package customVerificationUtilities;

import commonUtilities.CommonUtility;
import commonUtilities.PrintUtility;
import driverFactory.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static customVerificationUtilities.VerificationLibrary.verifyTrue;

public class CustomVerificationLibrary
{
    private CustomVerificationLibrary()
    {

    }

    /*
    *
    * @Author : Nikhil Singh
    * @Description : verify web element present on Web Page
    *
    *
    *
     */
    public static void verifyElementPresentOnPage(By locator)
    {
        long start = System.currentTimeMillis();
        try
        {
            WebElement element = DriverManager.getDriver().findElement(locator);
            if (element.isDisplayed())
            {
                CommonUtility.highLightElement(locator);
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                CommonUtility.unHighLightElement(element);
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyElementPresentOnPage(): ",totalTime, "Element found. Element-> " , locator));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyElementPresentOnPage(): ",totalTime, "Element NOT found. Element-> " , locator));
            }
        }
        catch (Exception var3)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyElementPresentOnPage(): ",totalTime, "Element NOT found. Element-> " , locator));
        }
    }

    /*
     *
     * @Author : Jaymar Bantay
     * @Description : return whether element is displayed or not
     *
     *
     *
     */
    public static boolean isElementOnPage(By locator) {
        try {
            WebElement element = DriverManager.getDriver().findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /*
     *
     * @Author : Nikhil Singh
     * @Description : verify web element present on Web Page with index
     *
     *
     *
     */
    public static void verifyElementPresentOnPage(By locator, int index)
    {
        long start = System.currentTimeMillis();
        try
        {
            WebElement element = (WebElement) DriverManager.getDriver().findElements(locator).get(index);
            if (element.isDisplayed())
            {
                CommonUtility.highLightElement(element);
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                CommonUtility.unHighLightElement(element);
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyElementPresentOnPage():",totalTime, "Element found. Element-> " ,locator));
            }
        } catch (NoSuchElementException var4)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyElementPresentOnPage():",totalTime, "Element NOT found. Element-> " ,locator));
        }
    }

    public static void verifyElementAbsentOnPage(By locator)
    {
        long start = System.currentTimeMillis();
        try
        {
            WebElement element = DriverManager.getDriver().findElement(locator);
            if (element.isDisplayed())
            {
                CommonUtility.highLightElement(element);
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                CommonUtility.unHighLightElement(element);
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyElementAbsentOnPage():",totalTime," Element found on the page when not expected. Element-> ",locator));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyElementAbsentOnPage():",totalTime, " Element found in the DOM but not displayed. ",locator));
            }
        }
        catch (NoSuchElementException var3)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("verifyElementAbsentOnPage():",totalTime," Element not found on the page as expected. Element-> ",locator));
        }

    }

    public static void verifyElementAbsentOnPage(By locator, int index)
    {
        long start = System.currentTimeMillis();
        try
        {
            WebElement element = (WebElement) DriverManager.getDriver().findElements(locator).get(index);
            if (element.isDisplayed())
            {
                CommonUtility.highLightElement(element);
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyElementAbsentOnPage():", totalTime, "Element found on the page when not expected. Element-> ",locator));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyElementAbsentOnPage():", totalTime, " Element found in the DOM but not displayed. Element-> ",locator));
            }
        }
        catch (NoSuchElementException var4)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("verifyElementAbsentOnPage():", totalTime, "Element not found on the page as expected. Element-> ",locator));
        }
        catch (IndexOutOfBoundsException var5)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("verifyElementAbsentOnPage():", totalTime, "Element not found on the page as expected. Element-> ",locator));
        }
        catch (Exception var6)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyElementAbsentOnPage():", totalTime, "Exception occurred, refer StackTrace. Element-> ",locator));
            var6.printStackTrace();
        }
    }



    public static void verifyElementOccurenceCount(By locator, int count)
    {
        long start = System.currentTimeMillis();
        try
        {
            List<WebElement> elements = DriverManager.getDriver().findElements(locator);
            if (VerificationLibrary.verifyEquals(elements.size(), count))
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyElementOccurenceCount():",totalTime," Element occurrence count is correct." + " Count: " + count,locator) );
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyElementOccurenceCount():",totalTime," Element occurrence count is INCORRECT." + " Expected count: " + count + ", Actual count: " + elements.size(), locator));
            }
        }
        catch (Exception var4)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyElementOccurenceCount():",totalTime, "Exception while finding elements. Element-> ",locator));
        }
    }

    /*
     *
     * @Author : Nikhil Singh
     * @Description : verify web element contains expected text
     *
     *
     */
    public static void verifyTextExistsOnElement(By locator, String verificationText)
    {
        long start = System.currentTimeMillis();

        try
        {
            String actualText = DriverManager.getDriver().findElement(locator).getText();
            if (actualText.contains(verificationText))
            {
                CommonUtility.highLightElement(locator);
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                CommonUtility.unHighLightElement(locator);
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyTextExistsOnElement():",totalTime, "Element-> " + " having Actual text: "
                        + actualText + ", contains expected text:" + verificationText,locator));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyTextExistsOnElement:",totalTime, "Element-> " +  " having text: "
                        + actualText + ",does not contains text:" + verificationText,locator));
            }
        }
        catch (NoSuchElementException var4)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyTextExistsOnElement():",totalTime," Element not found. Element-> " ,locator));
        }
        catch (Exception var5)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyTextExistsOnElement(): ",totalTime," Exception caught. Refer stacktrace."));
            var5.printStackTrace();
        }
    }

    public static void verifyIndexedTextExistsOnElement(By locator, String verificationText, int index)
    {
        long start = System.currentTimeMillis();
        try
        {
            String actualText = ((WebElement) DriverManager.getDriver().findElements(locator).get(index)).getText();
            if (actualText.contains(verificationText))
            {
                CommonUtility.highLightElement(locator);
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                CommonUtility.unHighLightElement(locator);
                PrintUtility.logPass(PrintUtility.printWaitTime( "verifyIndexedTextExistsOnElement():",totalTime,  " Element having text: " + actualText + ", contains text:" + verificationText, locator));
            }
            else
            {
                CommonUtility.unHighLightElement(locator);
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                CommonUtility.unHighLightElement(locator);
                PrintUtility.logFail(PrintUtility.printWaitTime( "verifyIndexedTextExistsOnElement():",totalTime,  " Element having text: "+ actualText + ",does not contains text:" + verificationText,locator));
            }
        }
        catch (NoSuchElementException var5)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime( "verifyIndexedTextExistsOnElement():",totalTime,  " Element not found." ,locator));
        }
        catch (Exception var6)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime( "verifyIndexedTextExistsOnElement():",totalTime,  " Exception caught. Refer stacktrace."));
        }

    }

    public static void verifyTextDoesNotExistOnElement(By locator, String verificationText)
    {
        boolean flag = false;
        String retrievedText = "";
        String derivedText = verificationText;
        long start = System.currentTimeMillis();
        try
        {
            List<WebElement> elements = DriverManager.getDriver().findElements(locator);
            if (elements.isEmpty())
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyTextDoesNotExistOnElement():", totalTime," The element does NOT exist on the page.",locator));
                return;
            }

            for (int i = 0; i < elements.size(); ++i)
            {
                if (((WebElement) elements.get(i)).isDisplayed())
                {
                    retrievedText = ((WebElement) elements.get(i)).getText().trim();
                    if (retrievedText.contains(derivedText.trim()))
                    {
                        CommonUtility.highLightElement((WebElement) elements.get(i));
                        long finish = System.currentTimeMillis();
                        long totalTime = finish - start;
                        PrintUtility.logFail(PrintUtility.printWaitTime("verifyTextDoesNotExistOnElement():", totalTime,"The text was found on the Element : "
                                        + " Parameter text: " + derivedText
                                        + ". Text on locator:" + retrievedText, locator));
                        return;
                    }

                    flag = true;
                }
            }

            if (flag)
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyTextDoesNotExistOnElement():", totalTime,"The text was not found on the Element-> "
                                 + ", Parameter text:" + derivedText + ". Text on locator:"+retrievedText, locator));
            }

            if (elements.size() > 1)
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logWarn(PrintUtility.printWaitTime("verifyTextDoesNotExistOnElement():",totalTime,"Warning: The locator matches to more than 1 element(i.e. " + elements.size() +"). Please either use the verification with index or make the locator specific."));
            }
        } catch (Exception var8)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyTextDoesNotExistOnElement():",totalTime, "Failed to locate element. " ,locator));
        }
    }


    public static void verifyTextNotOnElement(By locator, String verificationText)
    {
        long start = System.currentTimeMillis();
        boolean flag = false;
        String retrievedText = "";
        String derivedText = verificationText;
        try
        {
            List<WebElement> elements = DriverManager.getDriver().findElements(locator);
            if (elements.isEmpty())
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyTextNotOnElement():",totalTime," The element does NOT exist on the page.",locator));
                return;
            }

            for (int i = 0; i < elements.size(); ++i)
            {
                if (((WebElement) elements.get(i)).isDisplayed())
                {
                    retrievedText = ((WebElement) elements.get(i)).getText().trim();
                    if (retrievedText.equals(derivedText.trim()))
                    {
                        long finish = System.currentTimeMillis();
                        long totalTime = finish - start;
                        CommonUtility.highLightElement((WebElement) elements.get(i));
                        PrintUtility.logFail(PrintUtility.printWaitTime("verifyTextNotOnElement():",totalTime,"The text was found on the Element. " + " Parameter text: " + derivedText + ". Text on locator:" + retrievedText,locator));
                        return;
                    }

                    flag = true;
                }
            }

            if (flag)
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyTextNotOnElement():",totalTime,"The text was not found on the Element." + " Parameter text:" + derivedText + ". Text on locator:" + retrievedText,locator));
            }

            if (elements.size() > 1)
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logWarn(PrintUtility.printWaitTime("verifyTextNotOnElement():",totalTime,"Warning: The locator matches to more than 1 element(i.e. " + elements.size()
                                + "). Please either use the verification with index or make the locator specific."));
            }
        }
        catch (Exception var8)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyTextNotOnElement():",totalTime,"Failed to locate element." ,locator));
        }
    }

    public static void verifyTextOnElement(By locator, String verificationText)
    {
        long start = System.currentTimeMillis();
        try
        {
            String actualText = DriverManager.getDriver().findElement(locator).getText();
            if (actualText.equals(verificationText))
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyTextOnElement():",totalTime," having text: " + actualText
                                + ", matches text:" + verificationText,locator));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyTextOnElement():",totalTime," having text: " + actualText
                                + ",does not match text:" + verificationText,locator));
            }
        }
        catch (NoSuchElementException var4)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyTextOnElement():",totalTime," Element not found. Element-> " ,locator));
        }
        catch (Exception var5)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyTextOnElement():",totalTime,"Exception caught. Refer stacktrace."));
            var5.printStackTrace();
        }

    }


    public static void verifyAttributeValue(By locator, String attributeName, String value)
    {
        String actualValue = null;
        long start = System.currentTimeMillis();
        try
        {
            WebElement element = DriverManager.getDriver().findElement(locator);
            CommonUtility.highLightElement(element);
            actualValue = element.getAttribute(attributeName);
            if (actualValue.equals(value))
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyAttributeValue():", totalTime,"The value in the attribute equals the expected value."
                        + " attribute name: " + attributeName + ", expected value: "+ value +" attribute value: " + actualValue, locator));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyAttributeValue():", totalTime,"The value in the attribute does not equal the expected value. Element-> "
                                + " attribute name: " + attributeName + ", expected value: "+ value + ", actual value: " + actualValue,locator));
            }
        }
        catch (Exception var7)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyAttributeValue():", totalTime,"Element not found. " ,locator));
        }

    }

    public static void verifyAttributeValueContainsText(By locator, String attributeName, String value)
    {
        String actualValue = null;
        long start = System.currentTimeMillis();
        try
        {
            WebElement element = DriverManager.getDriver().findElement(locator);
            CommonUtility.highLightElement(element);
            actualValue = element.getAttribute(attributeName);
            if (actualValue.contains(value))
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyAttributeValue():",totalTime," The value in the attribute contains the expected text. "
                        + " attribute name: " + attributeName + ", attribute value: " + actualValue, locator));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyAttributeValue():",totalTime," The value in the attribute does not contain the expected text. "
                                 + "attribute name: " + attributeName + ", expected value: "+ value + ", actual value: " + actualValue,locator));
            }
        }
        catch (Exception var7)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("verifyAttributeValue():",totalTime," Element not found. "
                            + "attribute name: " + attributeName + ", Expected text: " + value, locator));
        }

    }

    public static void verifyAttributeValueContainsText(By locator, int index, String attributeName, String value)
    {
        String actualValue = null;
        long start = System.currentTimeMillis();
        try
        {
            WebElement element = (WebElement) DriverManager.getDriver().findElements(locator).get(index);
            CommonUtility.unHighLightElement(element);
            actualValue = element.getAttribute(attributeName).trim();
            if (actualValue.contains(value))
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyAttributeValue(): ", totalTime,"The value in the attribute contains the expected text. "
                        + " attribute name: " + attributeName
                        + ", attribute value: " + actualValue,locator));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyAttributeValue(): ", totalTime,"The value in the attribute does not contain the expected text. Element-> "
                        + " attribute name: " + attributeName + ", expected value: "
                        + value + ", actual value: " + actualValue,locator));
            }
        }
        catch (Exception var8)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyAttributeValue(): ", totalTime,"Element not found. "
                            + " attribute name: " + attributeName + ", Expected text: " + value,locator));
        }

    }


    public static void verifyTextInPageSouce(String expectedText)
    {
        long start = System.currentTimeMillis();
        try
        {
            Boolean result = DriverManager.getDriver().getPageSource().matches(expectedText);
            if (result)
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyTextInPageSouce():",totalTime," Text found. Text-> " + expectedText));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyTextInPageSouce():",totalTime," Text NOT found. Text-> " + expectedText));
            }
        }
        catch (Exception var3)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyTextInPageSouce():",totalTime," Text NOT found. Text-> " + expectedText));
        }
    }


    public static void verifyLinkExistsOnPage(By locator, String linkName)
    {
        long start = System.currentTimeMillis();
        try
        {
            WebElement element = DriverManager.getDriver().findElement(locator);
            if (element.getTagName().equals("a") && element.getText().trim().equals(linkName))
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyLinkExistsOnPage():",totalTime," The link exists on the page. "
                                + "Link text: " + linkName,locator));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyLinkExistsOnPage():",totalTime," The link do not exist on the page. "
                                 + "Link text: " + linkName,locator));
            }
        }
        catch (NoSuchElementException var4)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyLinkExistsOnPage():",totalTime," Element not found on the page.  " ,locator));
        }
        catch (Exception var5)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyLinkExistsOnPage():",totalTime," Exception occurred, refer StackTrace.",locator));
            var5.printStackTrace();
        }

    }



    public static void verifyIndexedLinkExistsOnPage(By locator, String linkName, int index)
    {
        long start = System.currentTimeMillis();
        try
        {
            WebElement element = (WebElement) DriverManager.getDriver().findElements(locator).get(index);
            if (element.getTagName().equals("a") && element.getText().trim().equals(linkName))
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyIndexedLinkExistsOnPage():",totalTime,"The link exists on the page." + " Link text: "
                                + linkName,locator));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyIndexedLinkExistsOnPage():",totalTime,"The link do not exist on the page. " + " Link text: "
                                + linkName,locator));
            }
        }
        catch (IndexOutOfBoundsException | NoSuchElementException var5)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyIndexedLinkExistsOnPage():",totalTime,"Element not found on the page. " ,locator));
        }
        catch (Exception var6)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyIndexedLinkExistsOnPage():",totalTime,"Exception occurred, refer StackTrace. " ,locator));
            var6.printStackTrace();
        }

    }


    public static void verifyLinkAbsenceOnPage(By locator, String linkName)
    {
        long start = System.currentTimeMillis();
        try
        {
            WebElement element = DriverManager.getDriver().findElement(locator);
            if (element.getTagName().equals("a") && element.getText().trim().equals(linkName))
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyLinkAbsenceOnPage():",totalTime,"The link exist on the page.  " + " Link text: "
                                + linkName,locator));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyLinkAbsenceOnPage():",totalTime,"The link do not exist on the page." + " Link text: "
                                + linkName,locator));
            }
        }
        catch (NoSuchElementException var4)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyLinkAbsenceOnPage():",totalTime,"Link Element not found on the page." ,locator));
        }
        catch (Exception var5)
        {
            var5.printStackTrace();
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyLinkAbsenceOnPage():",totalTime,"Exception occurred, refer StackTrace." ,locator));
        }

    }


    public static void verifyInputBoxValueLength(By locator, int expectedLength)
    {
        long start = System.currentTimeMillis();
        try
        {
            WebElement element = DriverManager.getDriver().findElement(locator);
            CommonUtility.unHighLightElement(element);
            String textActual = element.getAttribute("value");
            if (textActual.length() == expectedLength)
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyInputBoxValueLength():",totalTime," The value in the text box equals the expected length. "
                                 + " Value length: " + textActual.length(),locator));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyInputBoxValueLength():",totalTime," The value in the text box does not match the expected length."
                        + " Expected length: " + expectedLength
                        + ", actual length: " + textActual.length(),locator));
            }
        }
        catch (Exception var5)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyInputBoxValueLength():",totalTime,"Element not found." ,locator));
        }
    }


    public static void verifyTextBoxDefaultValue(By locator, String expectedDefaultValue)
    {
        long start = System.currentTimeMillis();
        String textActual = null;
        String derivedText = expectedDefaultValue;

        try
        {
            WebElement element = DriverManager.getDriver().findElement(locator);
            CommonUtility.highLightElement(element);
            textActual = element.getAttribute("value");
            if (textActual.equals(derivedText))
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyTextBoxDefaultValue():",totalTime,"The value in the text box equals the expected value."
                                + " Value: " + textActual,locator));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyTextBoxDefaultValue():",totalTime," The value in the text box does not match the expected value."
                        + " Expected value: " + derivedText + ", actual value: " + textActual,locator));
            }
        }
        catch (Exception var6)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyTextBoxDefaultValue():",totalTime," Element not found. "
                            + " Expected value: " + derivedText,locator));
        }
    }

    public static void verifyTextInsideTag(By locator, String value)
    {
        String actualValue = null;
        long start = System.currentTimeMillis();
        try
        {
            WebElement element = DriverManager.getDriver().findElement(locator);
            actualValue = element.getText();
            CommonUtility.highLightElement(element);
            if (value.equals(actualValue))
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyTextInsideTag():",totalTime," The value in the tag equals the expected value. "
                                 + "Value: " + actualValue,locator));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyTextInsideTag():",totalTime,"  The value in the tag does not equal the expected value. Element-> "
                                + "Expected value: " + value + ", actual value: "+ actualValue,locator));
            }
        }
        catch (Exception var6)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyTextInsideTag():",totalTime," Element not found. "
                            + "Expected value: " + value,locator));
        }
    }


    public static void verifyDropDownSelection(By locator, String selectedItem)
    {
        boolean isSelected = false;
        long start = System.currentTimeMillis();
        try
        {
            WebElement element = DriverManager.getDriver().findElement(locator);
            CommonUtility.highLightElement(element);
            Select comboBox = new Select(element);
            WebElement selections = comboBox.getFirstSelectedOption();
            if (selections.getText().equals(selectedItem))
            {
                isSelected = true;
            }

            if (isSelected)
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyDropDownSelection():",totalTime," '" + selectedItem
                        + "' is selected in: " ,locator));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyDropDownSelection():",totalTime," '"
                        + selectedItem + "' is NOT selected in: " ,locator));
            }
        }
        catch (Exception var7)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyDropDownSelection():",totalTime,"Element Not found." ,locator));
        }

    }

    public static void verifyDropDownSelectionIgnoreCase(By locator, String selectedItem)
    {
        long start = System.currentTimeMillis();
        boolean isSelected = false;

        try
        {
            WebElement element = DriverManager.getDriver().findElement(locator);
            CommonUtility.highLightElement(element);
            Select comboBox = new Select(element);
            WebElement selections = comboBox.getFirstSelectedOption();
            if (selections.getText().toLowerCase().equals(selectedItem.toLowerCase()))
            {
                isSelected = true;
            }

            if (isSelected)
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyDropDownSelectionIgnoreCase():",totalTime," '" + selectedItem
                        + "' is selected in:-> " ,locator));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyDropDownSelectionIgnoreCase():",totalTime," '"
                        + selectedItem + "' is NOT selected in:-> " ,locator));
            }
        }
        catch (Exception var7)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyDropDownSelectionIgnoreCase():",totalTime,"Element Not found. " ,locator));
        }

    }

    public static void verifyDropDownContainsSelection(By locator, String selectedItem)
    {
        long start = System.currentTimeMillis();
        boolean isSelected = false;

        try
        {
            WebElement element = DriverManager.getDriver().findElement(locator);
            CommonUtility.highLightElement(element);
            Select comboBox = new Select(element);
            WebElement selections = comboBox.getFirstSelectedOption();
            if (selections.getText().contains(selectedItem))
            {
                isSelected = true;
            }

            if (isSelected)
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyDropDownContainsSelection():",totalTime," '" + selectedItem
                        + "' is selected in:-> " ,locator));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyDropDownContainsSelection():",totalTime," '"
                        + selectedItem + "' is NOT selected in:-> " ,locator));
            }
        }
        catch (Exception var7)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyDropDownContainsSelection():",totalTime," Element Not found. " ,locator));
        }

    }

    public static void verifyDropDownContainsSelectionIgnoreCase(By locator, String selectedItem)
    {
        long start = System.currentTimeMillis();
        boolean isSelected = false;

        try
        {
            WebElement element = DriverManager.getDriver().findElement(locator);
            CommonUtility.highLightElement(element);
            Select comboBox = new Select(element);
            WebElement selections = comboBox.getFirstSelectedOption();
            if (selections.getText().toLowerCase().contains(selectedItem.toLowerCase())) {
                isSelected = true;
            }

            if (isSelected)
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyDropDownContainsSelectionIgnoreCase():",totalTime," '" + selectedItem
                        + "' is selected in:-> " ,locator));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyDropDownContainsSelectionIgnoreCase():",totalTime," '"
                        + selectedItem + "' is NOT selected in:-> " ,locator));
            }
        }
        catch (Exception var7)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyDropDownContainsSelectionIgnoreCase():",totalTime," Element Not found. " ,locator));
        }

    }

    public static void verifyFirstDropDownSelection(By locator, String selectedItem)
    {
        long start = System.currentTimeMillis();
        try
        {
            WebElement element = DriverManager.getDriver().findElement(locator);
            CommonUtility.highLightElement(element);
            Select select = new Select(element);
            WebElement selection = select.getFirstSelectedOption();
            if (selection.getText().trim().equals(selectedItem))
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyFirstDropDownSelection():",totalTime," '" + selectedItem
                        + "' is selected in:-> " ,locator));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyFirstDropDownSelection():",totalTime," '"
                        + selectedItem + "' is NOT selected in:-> " ,locator));
            }
        }
        catch (Exception var6)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyFirstDropDownSelection():",totalTime,"Element Not found.  " ,locator));
        }
    }

    public static void verifyOptionExistsOnDropDown(By locator, String item)
    {
        long start = System.currentTimeMillis();
        int count = 0;

        try
        {
            WebElement element = DriverManager.getDriver().findElement(locator);
            CommonUtility.highLightElement(element);
            Select select = new Select(element);
            List<WebElement> list = select.getOptions();
            Iterator var7 = list.iterator();

            while (var7.hasNext())
            {
                WebElement options = (WebElement) var7.next();
                if (options.getText().trim().equalsIgnoreCase(item))
                {
                    ++count;
                }
            }

            if (count > 0)
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyOptionExistsOnDropDown():",totalTime," '" + item
                        + "' exists in the drop-down list:-> " ,locator));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyOptionExistsOnDropDown():",totalTime," '"
                        + item + "' does NOT exist in the drop-down list:-> " ,locator));
            }
        }
        catch (Exception var9)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyOptionExistsOnDropDown():",totalTime," Element Not found. " ,locator));
        }

    }

    public static void verifyOptionDontExistsOnDropDown(By locator, String item)
    {
        long start = System.currentTimeMillis();
        int count = 0;

        try
        {
            WebElement element = DriverManager.getDriver().findElement(locator);
            CommonUtility.highLightElement(element);
            Select select = new Select(element);
            List<WebElement> list = select.getOptions();
            Iterator var7 = list.iterator();

            while (var7.hasNext())
            {
                WebElement options = (WebElement) var7.next();
                if (options.getText().equalsIgnoreCase(item))
                {
                    ++count;
                }
            }

            if (count > 0)
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyOptionDontExistsOnDropDown():",totalTime," '"
                        + item + "' does  exist in the drop-down list:-> " ,locator));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyOptionDontExistsOnDropDown():",totalTime," '" + item
                        + "' dont exist in the drop-down list:-> " ,locator));
            }
        }
        catch (Exception var9)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyOptionDontExistsOnDropDown():",totalTime," Element Not found.  " ,locator));
        }

    }

    public static void verifyValueInDropDownList(By locator, String[] valueToMatch)
    {
        long start = System.currentTimeMillis();
        boolean flag = false;
        WebElement selectElement = DriverManager.getDriver().findElement(locator);
        Select se = new Select(selectElement);
        List<WebElement> allSelectedOptions = se.getOptions();

        for (int i = 0; i < valueToMatch.length; ++i)
        {
            flag = false;
            Iterator var8 = allSelectedOptions.iterator();

            while (var8.hasNext())
            {
                WebElement webElement = (WebElement) var8.next();
                if (webElement.getText().replace("\n", "").trim().equals(valueToMatch[i]))
                {
                    flag = true;
                }
            }

            if (!flag)
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyValueInDropDownList():",totalTime," "
                        + valueToMatch[i] + " is not in list. " ,locator));
            }
        }

        if (flag)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("verifyValueInDropDownList():",totalTime," All values are included in the Drop Down List."
                            ,locator));
        }

    }

    public static void verifyRadioButtonIsSelected(By locator)
    {
        long start = System.currentTimeMillis();
        try
        {
            WebElement element = DriverManager.getDriver().findElement(locator);
            CommonUtility.highLightElement(element);
            if (element.isSelected())
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyRadioButtonIsSelected():",totalTime," The radio button is selected."
                                ,locator));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyRadioButtonIsSelected():",totalTime," The radio button is NOT selected."
                                ,locator));
            }
        }
        catch (NoSuchElementException var3)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyRadioButtonIsSelected():",totalTime," Element not found. " ,locator));
        }

    }

    public static void verifyRadioButtonIsSelected(By locator, int index)
    {
        long start = System.currentTimeMillis();
        try
        {
            WebElement element = (WebElement) DriverManager.getDriver().findElements(locator).get(index);
            CommonUtility.highLightElement(element);
            if (element.isSelected())
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyRadioButtonIsSelected():",totalTime," The radio button is selected. " + " Index: " + index,locator));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyRadioButtonIsSelected():",totalTime," The radio button is NOT selected. " + ", Index: " + index,locator));
            }
        }
        catch (NoSuchElementException var4)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyRadioButtonIsSelected():",totalTime," Element not found. "  + " Index: " + index,locator));
        }
    }

    public static void verifyRadioButtonIsNotSelected(By locator)
    {
        long start = System.currentTimeMillis();
        try
        {
            WebElement element = DriverManager.getDriver().findElement(locator);
            CommonUtility.highLightElement(element);
            if (!element.isSelected())
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyRadioButtonIsNotSelected():",totalTime," The radio button is NOT selected. "
                                ,locator));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyRadioButtonIsNotSelected():",totalTime," The radio button is selected. "
                                ,locator));
            }
        }
        catch (NoSuchElementException var3)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyRadioButtonIsNotSelected():",totalTime," Element not found. " ,locator));
        }
    }


    public static void verifyRadioButtonIsNotSelected(By locator, int index)
    {
        long start = System.currentTimeMillis();
        try 
        {
            WebElement element = (WebElement) DriverManager.getDriver().findElements(locator).get(index);
            CommonUtility.highLightElement(element);
            if (!element.isSelected()) 
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyRadioButtonIsNotSelected():",totalTime," The radio button is NOT selected."
                                +" Index: " + index, locator));
            } 
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyRadioButtonIsNotSelected():",totalTime," The radio button is selected. "
                                +  " Index: " + index,locator));
            }
        } catch (NoSuchElementException var4) 
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyRadioButtonIsNotSelected():",totalTime," Element not found.  " 
                            + " Index: " + index, locator));
        }

    }

    public static void verifyCheckboxIsChecked(By locator)
    {
        long start = System.currentTimeMillis();
        try
        {
            WebElement element = DriverManager.getDriver().findElement(locator);
            CommonUtility.highLightElement(element);
            if (element.isSelected()) 
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyCheckboxIsChecked():",totalTime," The checkbox is checked. " ,locator));
            } 
            else 
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyCheckboxIsChecked():",totalTime," The checkbox is NOT checked. " ,locator));
            }
        } 
        catch (NoSuchElementException var3) 
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyCheckboxIsChecked():",totalTime," Element not found." ,locator));
        }

    }

    public static void verifyCheckboxIsChecked(By locator, int index)
    {
        long start = System.currentTimeMillis();
        try
        {
            WebElement element = (WebElement) DriverManager.getDriver().findElements(locator).get(index);
            CommonUtility.highLightElement(element);
            if (element.isSelected())
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyCheckboxIsChecked():",totalTime,"  The checkbox is checked. "
                                + "Index: " + index,locator));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyCheckboxIsChecked():",totalTime,"  The checkbox is NOT checked.  "
                                + "Index: " + index,locator));
            }
        }
        catch (NoSuchElementException var4)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyCheckboxIsChecked():",totalTime," Element not found.  " ,locator));
        }

    }

    public static void verifyCheckboxIsNotChecked(By locator)
    {
        long start = System.currentTimeMillis();
        try
        {

            WebElement element = DriverManager.getDriver().findElement(locator);
            CommonUtility.highLightElement(element);
            if (!element.isSelected())
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyCheckboxIsNotChecked():",totalTime," The checkbox is NOT checked."
                                ,locator));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyCheckboxIsNotChecked():",totalTime," The checkbox is checked." ,locator));
            }
        }
        catch (NoSuchElementException var3)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyCheckboxIsNotChecked():",totalTime," Element not found. " ,locator));
        }

    }

    public static void verifyCheckboxIsNotChecked(By locator, int index)
    {
        long start = System.currentTimeMillis();
        try
        {
            WebElement element = (WebElement) DriverManager.getDriver().findElements(locator).get(index);
            CommonUtility.highLightElement(element);
            Boolean selectionStatus = element.isSelected();
            if (!selectionStatus)
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyCheckboxIsNotChecked():",totalTime,"  The checkbox is NOT checked."
                                + " Index: " + index,locator));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyCheckboxIsNotChecked():",totalTime,"  The checkbox is checked. "
                                + " Index: " + index,locator));
            }
        }
        catch (NoSuchElementException var5)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyCheckboxIsNotChecked():",totalTime,"  Element not found." ,locator));
        }
    }


    public static void verifyTextExist(String parentString, String subString)
    {
        long start = System.currentTimeMillis();
        if (parentString.toLowerCase().contains(subString.toLowerCase()))
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("verifyTextExist():",totalTime, subString +" was found in parentString : " + parentString));
        }
        else
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyTextExist():",totalTime, subString + " was NOT found in parentString : " + parentString));
        }

    }

    public static void verifyTextDoesNotExist(String parentString, String subString)
    {
        long start = System.currentTimeMillis();
        if (parentString.toLowerCase().contains(subString.toLowerCase()))
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyTextDoesNotExist():", totalTime,subString + " was found in parentString : " + parentString));
        }
        else
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("verifyTextDoesNotExist():",totalTime, subString + " was NOT found in parentString : " + parentString));
        }
    }

    public static void verifyText(String actual, String expected)
    {
        long start = System.currentTimeMillis();
        try
        {
            if (actual != null && expected != null)
            {
                if (actual.trim().equalsIgnoreCase(expected.trim()))
                {
                    long finish = System.currentTimeMillis();
                    long totalTime = finish - start;
                    PrintUtility.logPass(PrintUtility.printWaitTime("verifyText():",totalTime," The text matches the expected value. Text: " + expected));
                }
                else
                {
                    long finish = System.currentTimeMillis();
                    long totalTime = finish - start;
                    PrintUtility.logFail(PrintUtility.printWaitTime("verifyText():",totalTime," The text does NOT match the expected value. Actual: " + actual + ", Expected: "
                                    + expected));
                }
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyText():",totalTime, "The Actual/Expected value cannot be null. Actual: " + actual + ", Expected: "
                                + expected));
            }
        }
        catch (Exception var4)
        {

            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyText():",totalTime, "The text does NOT match the expected value. Actual: " + actual + ", Expected: "+ expected));
            var4.printStackTrace();
        }

    }

    public static void verifyElementPresenceInsideParentElement(By parentElementLocator, By childElementLocator)
    {
        long start = System.currentTimeMillis();
        WebElement parentElement = null;

        try
        {
            parentElement = DriverManager.getDriver().findElement(parentElementLocator);
            CommonUtility.highLightElement(parentElement);
        }
        catch (Exception var8)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyElementPresenceInsideParentElement():",totalTime, "Parent element not found.  ",parentElementLocator));
            return;
        }

        try {
            String locatorStr = childElementLocator.toString();
            WebElement child;
            if (locatorStr.contains("By.xpath: //")) {
                locatorStr = locatorStr.replace("By.xpath: //", ".//");
                By childElementLocatorRevised = By.xpath(locatorStr);
                child = parentElement.findElement(childElementLocatorRevised);
            } else {
                child = parentElement.findElement(childElementLocator);
            }

            CommonUtility.highLightElement(child);
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("verifyElementPresenceInsideParentElement():",totalTime, " Verified: childElementLocator is a descendant of: " ,parentElementLocator));
        }
        catch (NoSuchElementException var7)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyElementPresenceInsideParentElement():",totalTime, "childElementLocator is NOT a descendant of: ",parentElementLocator));
        }
    }

    public static void verifyElementAbsenceInsideParentElement(By parentElementLocator, By childElementLocator)
    {
        long start = System.currentTimeMillis();
        WebElement parentElement = null;

        try
        {
            parentElement = DriverManager.getDriver().findElement(parentElementLocator);
            CommonUtility.highLightElement(parentElement);
        }
        catch (Exception var8)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyElementAbsenceInsideParentElement():",totalTime, " Parent element not found.",parentElementLocator));
            return;
        }

        try {
            String locatorStr = childElementLocator.toString();
            WebElement child;
            if (locatorStr.contains("By.xpath: //"))
            {
                locatorStr = locatorStr.replace("By.xpath: //", ".//");
                By childElementLocatorRevised = By.xpath(locatorStr);
                child = parentElement.findElement(childElementLocatorRevised);
            }
            else
            {
                child = parentElement.findElement(childElementLocator);
            }

            CommonUtility.highLightElement(child);
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyElementAbsenceInsideParentElement():",totalTime, "Element-> childElementLocator  is a descendant of: " ,parentElementLocator));
        }
        catch (NoSuchElementException var7)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyElementAbsenceInsideParentElement():",totalTime, "Verified-> childElementLocator is a NOT a descendant of: " ,parentElementLocator));
        }
    }


    public static void verifyElementOccurenceCount(By parentElementLocator, By childElementLocator, int countForChildElement)
    {
        WebElement parentElement = null;
        long start = System.currentTimeMillis();
        try
        {
            parentElement = DriverManager.getDriver().findElement(parentElementLocator);
        }
        catch (NoSuchElementException var7)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyElementOccurenceCount():",totalTime, " Parent Element NOT Found. Parent Element-> ",parentElementLocator));
        }

        List<WebElement> childElement = parentElement.findElements(childElementLocator);
        int actualCount = childElement.size();
        if (actualCount != countForChildElement)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyElementOccurenceCount():",totalTime, " Found: " + Integer.toString(actualCount)
                            + " child elements.  Expect count: " + Integer.toString(countForChildElement)
                        + " Parent  ",parentElementLocator));
        }
        else
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("verifyElementOccurenceCount():",totalTime, " Found the expected number of child elements. Found count: "
                            + Integer.toString(actualCount)
                            + " Parent " ,parentElementLocator));
        }
    }

    public static void verifyCookieValue(String cookieName, String expected)
    {
        long start = System.currentTimeMillis();
        String cookieValue = null;

        try
        {
            cookieValue = DriverManager.getDriver().manage().getCookieNamed(cookieName).getValue();
            if (cookieValue.equals(expected))
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyCookieValue():",totalTime, " The value of the cookie matches the expected value. Cookie name: "
                                + cookieName + ", Value: " + expected));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyCookieValue():",totalTime, " The value of the cookie does NOT match the expected value. Cookie name: "
                                + cookieName + ", Expected value: " + expected + ", Actual value: " + cookieValue));
            }
        }
        catch (Exception var5)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyCookieValue():",totalTime, " Failed to retrieve the cookie value. Exception: " + var5.getMessage()
                            + ", Cookie name: " + cookieName + ", Value: " + expected));
        }

    }

    public static void verifyCookieExpiry(String CookieName, String expectedDaysToExpire)
    {
        long start = System.currentTimeMillis();
        try
        {
            Date expiryDate = DriverManager.getDriver().manage().getCookieNamed(CookieName).getExpiry();
            Calendar cal = Calendar.getInstance();
            cal.add(6, Integer.parseInt(expectedDaysToExpire));
            Date expectedDate = cal.getTime();
            verifyTrue(expiryDate.compareTo(expectedDate) <= 0);
            if (expiryDate.compareTo(expectedDate) <= 0)
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyCookieExpiry():",totalTime, " The cookie expiration is within the expected expiration date. Expected days till expration: "
                                + expectedDaysToExpire + ", Expected expiration date: " + expectedDate
                                + ", Cookie expiration date: " + expiryDate));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyCookieExpiry():",totalTime, " The cookie expiration is NOT within the expected expiration date. Expected days till expration: "
                                + expectedDaysToExpire + ", Expected expiration date: " + expectedDate
                                + ", Cookie expiration date: " + expiryDate));
            }
        }
        catch (Exception var6)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyCookieExpiry():",totalTime, " An exception occurred during the verification. Exception: "
                            + var6.getMessage()));
        }
    }




    public static void verifyCondition(boolean expression, String verificationDescription)
    {
        long start = System.currentTimeMillis();
        if (expression)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("verifyCondition():",totalTime, ": Passed " + verificationDescription));
        }
        else
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyCondition():",totalTime, ": Failed" + verificationDescription));
        }
    }

    public static void verifyGreater(String expectedGreater, String expectedLess, String verificationDescription)
    {
        long start = System.currentTimeMillis();
        String derivedGreater = expectedGreater;
        String derivedLess = expectedLess;

        double parsedLarger;
        double parsedSmaller;
        try
        {
            parsedLarger = Double.parseDouble(derivedGreater.trim().replaceAll("[^.0-9]", ""));
            parsedSmaller = Double.parseDouble(derivedLess.trim().replaceAll("[^.0-9]", ""));
        }
        catch (NumberFormatException var11)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyGreater():",totalTime, " One or more of the values does not contain numeric information ExpectedGreater value ='"
                            + expectedGreater + "'  ExpectedLess value = '" + expectedLess + "'"));
            return;
        }

        if (parsedLarger > parsedSmaller)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("verifyGreater():",totalTime,  verificationDescription
                    + ":  Verified that '" + parsedLarger + "'  is greater than '" + parsedSmaller + "'"));
        } else
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyGreater():",totalTime,   verificationDescription + ":  Verification Failed '" + parsedLarger
                            + "'  is NOT greater than '" + parsedSmaller + "'"));
        }

    }

    public static void verifyGreaterCount(int expectedGreaterCount, int expectedLess, String verificationDescription)
    {
        long start = System.currentTimeMillis();
        if (expectedGreaterCount >= expectedLess)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("verifyLesser():",totalTime, verificationDescription
                    + ": value: " + expectedGreaterCount + " is greater than value: " + expectedLess));
        }
        else
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyLesser():",totalTime, verificationDescription + ": value: " + expectedGreaterCount
                    + " is NOT greater value: " + expectedLess));
        }
    }

    public static void verifyEqualNumerics(String valueA, String valueB, String verificationDescription)
    {
        long start = System.currentTimeMillis();
        String derivedA = valueA;
        String derivedB = valueB;

        double parsedA;
        double parsedB;
        try
        {
            parsedA = Double.parseDouble(derivedA.trim().replaceAll("[^.0-9]", ""));
            parsedB = Double.parseDouble(derivedB.trim().replaceAll("[^.0-9]", ""));
        }
        catch (NumberFormatException var11)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyEqualNumerics():",totalTime," One or more of the values does not contain numeric information valueA value ='"
                            + valueA + "'  valueB value = '" + valueB + "'"));
            return;
        }

        if (parsedA == parsedB)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("verifyEqualNumerics():",totalTime,  verificationDescription
                    + ":  Verified that '" + parsedA + "'  is equal to '" + parsedB + "'"));
        }
        else
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyEqualNumerics():",totalTime,  verificationDescription
                    + ":  Verification failed,'" + parsedA + "'  is NOT equal to '" + parsedB + "'"));
        }

    }

    public static void verifyGreaterOrEqual(String expectedGreaterOrEqual, String expectedLessOrEqual, String verificationDescription)
    {
        long start = System.currentTimeMillis();
        String derivedGreaterOrEqual = expectedGreaterOrEqual;
        String derivedLessOrEqual = expectedLessOrEqual;

        double parsedA;
        double parsedB;
        try
        {
            parsedA = Double.parseDouble(derivedGreaterOrEqual.trim().replaceAll("[^.0-9]", ""));
            parsedB = Double.parseDouble(derivedLessOrEqual.trim().replaceAll("[^.0-9]", ""));
        }
        catch (NumberFormatException var11)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyGreaterOrEqual():",totalTime,  " One or more of the values does not contain numeric information, expectedGreaterOrEqual value ='"
                            + expectedGreaterOrEqual + "'  expectedLessOrEqual value = '" + expectedLessOrEqual + "'"));
            return;
        }

        if (parsedA >= parsedB)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("verifyGreaterOrEqual():",totalTime,verificationDescription
                    + ": Verified that '" + parsedA + "' is equal or greater than '" + parsedB + "'"));
        }
        else
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyGreaterOrEqual():",totalTime,   verificationDescription + ": Verifification failed, '" + parsedA
                            + "' is NOT equal or greater than '" + parsedB + "'"));
        }
    }

    public static void verifyGreater(int expectedGreater, int expectedLess, String verificationDescription)
    {
        long start = System.currentTimeMillis();
        if (expectedGreater > expectedLess)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("verifyGreater():",totalTime, verificationDescription
                    + ": value: " + expectedGreater + " is greater than value: " + expectedLess));
        }
        else
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyGreater():",totalTime, verificationDescription + ": value: " + expectedGreater
                            + " is NOT greater value: " + expectedLess));
        }
    }

    public static void verifyEqual(int valueA, int valueB, String verificationDescription)
    {
        long start = System.currentTimeMillis();
        if (valueA == valueB)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("verifyEqual():",totalTime, verificationDescription
                    + ": value '" + valueA + "' is equal to value '" + valueB + "'"));
        }
        else
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyEqual():",totalTime, verificationDescription
                    + ": value '" + valueA + "' is NOT equal to value: " + valueB + "'"));
        }
    }

    public static void verifyEqual(double valueA, double valueB, String verificationDescription)
    {
        long start = System.currentTimeMillis();
        if (valueA == valueB)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("verifyEqual():",totalTime, verificationDescription
                    + ": value '" + valueA + "' is equal to value '" + valueB + "'"));
        }
        else
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyEqual():",totalTime, verificationDescription
                    + ": value '" + valueA + "' is NOT equal to value: " + valueB + "'"));
        }

    }

    public static void verifyGreaterOrEqual(int expectedGreaterOrEqual, int expectedLessOrEqual, String verificationDescription)
    {
        long start = System.currentTimeMillis();
        if (expectedGreaterOrEqual >= expectedLessOrEqual)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("verifyGreaterOrEqual():",totalTime, verificationDescription + ": value '" + expectedGreaterOrEqual
                            + "' is equal to or greater than value '" + expectedLessOrEqual + "'"));
        }
        else
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyGreaterOrEqual():",totalTime, verificationDescription + ": value '" + expectedGreaterOrEqual
                            + "' is NOT equal or greater than value '" + expectedLessOrEqual + "'"));
        }
    }


    public static void verifyIsNotEqual(String valueA, String valueB, boolean enableCaseSensitiveVerification, String verificationDescription)
    {
        long start = System.currentTimeMillis();
        try
        {
            if (enableCaseSensitiveVerification)
            {
                if (!valueA.equals(valueB))
                {
                    long finish = System.currentTimeMillis();
                    long totalTime = finish - start;
                    PrintUtility.logPass(PrintUtility.printWaitTime("verifyIsNotEqual():",totalTime, verificationDescription + ": value '" + valueA
                                    + "' is NOT equal to value '" + valueB + "'"));
                }
                else
                {
                    long finish = System.currentTimeMillis();
                    long totalTime = finish - start;
                    PrintUtility.logFail(PrintUtility.printWaitTime("verifyIsNotEqual():",totalTime, verificationDescription + ": value '" + valueA + "' is equal to value '" + valueB + "'"));
                }
            }
            else if (!valueA.equalsIgnoreCase(valueB))
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyIsNotEqual():",totalTime, verificationDescription
                        + ": value '" + valueA + "' is NOT equal to value '" + valueB + "'"));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyIsNotEqual():",totalTime, verificationDescription + ": value '" + valueA + "' is equal to value '" + valueB + "'"));
            }
        }
        catch (Exception var6)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyGreaterOrEqual():",totalTime, "Unable to perform verification"));
        }

    }

    public static void verifyIsEqual(String valueA, String valueB, boolean enableCaseSensitiveVerification, String verificationDescription)
    {
        long start = System.currentTimeMillis();
        try
        {
            if (enableCaseSensitiveVerification)
            {
                if (valueA.equals(valueB))
                {
                    long finish = System.currentTimeMillis();
                    long totalTime = finish - start;
                    PrintUtility.logPass(PrintUtility.printWaitTime("verifyIsEqual():",totalTime, verificationDescription
                            + ": value '" + valueA + "' is equal to value '" + valueB + "'"));
                }
                else
                {
                    long finish = System.currentTimeMillis();
                    long totalTime = finish - start;
                    PrintUtility.logFail(PrintUtility.printWaitTime("verifyIsEqual():",totalTime, verificationDescription + ": value '" + valueA
                                    + "' is NOT equal to value '" + valueB + "'"));
                }
            }
            else if (valueA.equalsIgnoreCase(valueB))
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyIsEqual():",totalTime, verificationDescription
                        + ": value '" + valueA + "' is equal to value '" + valueB + "'"));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyIsEqual():",totalTime, verificationDescription + ": value '" + valueA + "' is NOT equal to value '" + valueB + "'"));
            }
        }
        catch (Exception var6)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyIsEqual():",totalTime, "Unable to perform verification"));
        }
    }

    public static void verifyIsNotEqual(int valueA, int valueB, String verificationDescription)
    {
        long start = System.currentTimeMillis();
        try
        {
            if (valueA != valueB)
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime("verifyIsNotEqual():",totalTime, verificationDescription
                        + ": value '" + valueA + "' is NOT equal to value '" + valueB + "'"));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime("verifyIsNotEqual():",totalTime, verificationDescription + ": value '" + valueA + "' is equal to value '" + valueB + "'"));
            }
        }
        catch (Exception var5)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("verifyIsNotEqual():",totalTime, "Unable to perform verification"));
        }
    }


    public static void verifyCurrentUrl(String URL)
    {
        long start = System.currentTimeMillis();
        if (DriverManager.getDriver().getCurrentUrl().trim().equalsIgnoreCase(URL.trim()))
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime( "verifyCurrentUrl(): ",totalTime, "Matched"));
        }
        else
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime( "verifyCurrentUrl(): ",totalTime,"Not matched"));
        }
    }

    public static void verifyPageTitle(String expectedTitle)
    {
        long start = System.currentTimeMillis();
        try
        {
            String actualTitle = DriverManager.getDriver().getTitle();
            if (actualTitle.equalsIgnoreCase(expectedTitle))
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime( "verifyPageTitle(): ",totalTime,"Title matches, Title = " + actualTitle));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime( "verifyPageTitle(): ",totalTime,"Title doesn't match: Expected Title = " + expectedTitle + ", Actual Title = " + actualTitle));
            }
        }
        catch (Exception var3)
        {
            var3.printStackTrace();
        }

    }

    public static void verifyCSSValue(By locator, String cssProp, String expectedValue)
    {
        String actualValue = null;
        long start = System.currentTimeMillis();
        try
        {
            WebElement element = DriverManager.getDriver().findElement(locator);
            CommonUtility.highLightElement(element);
            actualValue = element.getCssValue(cssProp);
            if (actualValue.equals(expectedValue))
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logPass(PrintUtility.printWaitTime( "verifyCSSValue(): ",totalTime,"The value in the CSS property equals the expected value. "
                                + " CSS Property name: " + cssProp + ", attribute value: "
                                + actualValue,locator));
            }
            else
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                PrintUtility.logFail(PrintUtility.printWaitTime( "verifyCSSValue(): ",totalTime,"The value in the CSS property does not equal the expected value. "
                        + " CSS Property name: " + cssProp + ", expected value: "
                        + expectedValue + ", actual value: " + actualValue,locator));
            }
        }
        catch (Exception var6)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime( "verifyCSSValue(): ",totalTime,"Element NOT found. " + " Attribute name: "
                            + cssProp + ", Expected value: " + expectedValue,locator));
        }
    }


}
