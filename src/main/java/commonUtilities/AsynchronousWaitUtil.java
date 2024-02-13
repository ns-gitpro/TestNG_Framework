package commonUtilities;


import constants.Framework_Constants;

import driverFactory.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import static commonUtilities.PrintUtility.printWaitTime;

/**
 * @author Nikhil Singh
 *
 */
public class AsynchronousWaitUtil
{
    private AsynchronousWaitUtil()
    {

    }

    

    public static WebElement waitForElementToAppear(WebElement element)
    {
        long start = System.currentTimeMillis();
        WebElement ele = null;
        try
        {
            handleAsyncWait();
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Framework_Constants.getTimeout()));
            ele = wait.until(ExpectedConditions.visibilityOf(element));
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("waitForElementToAppear()",totalTime,CommonUtility.getByLocator(element)));
        }
        catch (Exception e)
        {
            // TODO: handle exception

        }
        return ele;
    }


    public static WebElement waitForElementToAppear(By element)
    {
        long start = System.currentTimeMillis();
        WebElement ele = null;
        try
        {
            handleAsyncWait();
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Framework_Constants.getTimeout()));
            ele = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(printWaitTime("waitForElementToAppear()",totalTime,element));
        }
        catch (Exception e)
        {
            // TODO: handle exception

        }

        return ele;
    }

    public static WebElement waitForElementToBeVisible(WebElement element)
    {
        long start = System.currentTimeMillis();
        handleAsyncWait();
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Framework_Constants.getTimeout()));
        WebElement ele;
        ele = wait.until(ExpectedConditions.visibilityOf(element));
        long finish = System.currentTimeMillis();
        long totalTime = finish - start;
        PrintUtility.logPass(printWaitTime("waitForElementToBeVisible()",totalTime,CommonUtility.getByLocator(element)));
        return ele;
    }

    public static WebElement waitForElementToBeVisible(By element)
    {
        long start = System.currentTimeMillis();
        handleAsyncWait();
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Framework_Constants.getTimeout()));
        WebElement ele;
        ele = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        long finish = System.currentTimeMillis();
        long totalTime = finish - start;
        PrintUtility.logPass(printWaitTime("waitForElementToBeVisible()",totalTime,element));
        return ele;
    }


    public static List<WebElement> waitForElementToBeVisible(List<WebElement> element)
    {
        long start = System.currentTimeMillis();
        handleAsyncWait();
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Framework_Constants.getTimeout()));
        try
        {
            element = wait.until(ExpectedConditions.visibilityOfAllElements(element));
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(printWaitTime("waitForElementToBeVisible()",totalTime,CommonUtility.getByLocator(element.get(0))));
        }
        catch (Exception e)
        {
            // TODO: handle exception
            PrintUtility.logFail("waitForElementToBeVisible(): Element not found -> "+element);
        }
        return element;
    }

    public static boolean waitForAttributeNotContains(WebElement element, String attribute, String attributeValue)
    {
        boolean value = false;
        long start = System.currentTimeMillis();
        try
        {
            handleAsyncWait();
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Framework_Constants.getTimeoutMax()));
            value = wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(element, attribute, attributeValue)));
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(printWaitTime("waitForAttributeNotContains()",totalTime,CommonUtility.getByLocator(element)));
        }
        catch (Exception e)
        {
            PrintUtility.logFail("waitForAttributeNotContains(): Element not found -> "+ element + "\n"+ e);
        }
        return value;
    }


    public static boolean waitForAttributeToChange(WebElement element, String attribute, String attributeValue)
    {
        boolean value = false;
        try
        {
            long start = System.currentTimeMillis();
            handleAsyncWait();
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Framework_Constants.getTimeoutMax()));
            value = wait.until(ExpectedConditions.attributeContains(element, attribute, attributeValue));
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(printWaitTime("waitForAttributeToChange()",totalTime,CommonUtility.getByLocator(element)));
        }
        catch (Exception e)
        {
            PrintUtility.logFail("waitForAttributeToChange(): Element not found -> "+ element + "\n"+ e);
        }
        return value;
    }

    public static boolean waitForAttributeToChange(By element, String attribute, String attributeValue)
    {
        boolean value = false;
        try
        {
            long start = System.currentTimeMillis();
            handleAsyncWait();
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Framework_Constants.getTimeoutMax()));
            value = wait.until(ExpectedConditions.attributeContains(element, attribute, attributeValue));
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(printWaitTime("waitForAttributeToChange()",totalTime,element));
        }
        catch (Exception e)
        {
            PrintUtility.logFail("waitForAttributeToChange(): Element not found -> "+ element + "\n"+ e);
        }
        return value;

    }

    public static boolean waitForAttributeToBe(By element, String attribute, String attributeValue)
    {
        boolean value = false;
        try
        {
            long start = System.currentTimeMillis();
            handleAsyncWait();
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Framework_Constants.getTimeoutMax()));
            String attr = DriverManager.getDriver().findElement(element).getAttribute(attribute);
            value = wait.until(ExpectedConditions.attributeToBe(element, attribute, attributeValue));
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(printWaitTime("waitForAttributeToBe()",totalTime,element));
        }
        catch (Exception e)
        {
            PrintUtility.logFail("waitForAttributeToBe(): Element not found -> "+ element + "\n"+ e);
        }
        return value;
    }

    public static boolean waitForElementAttributeContainsString(WebElement element, String attribute, String expectedString)
    {
        boolean value = false;
        long start = System.currentTimeMillis();
        try {
            handleAsyncWait();
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Framework_Constants.getTimeoutMax()));
            ExpectedCondition<Boolean> elementAttributeContainsString ;
            elementAttributeContainsString = arg0 -> element.getAttribute(attribute).contains(expectedString);
            wait.until(elementAttributeContainsString);
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(printWaitTime("waitForElementAttributeContainsString()",totalTime,CommonUtility.getByLocator(element)));
        }
        catch (Exception e)
        {
            PrintUtility.logFail("waitForElementAttributeContainsString(): Element not found -> "+ CommonUtility.getByLocator(element) + "\n"+ e);
        }
        return value;
    }

    public static boolean waitForElementAttributeContainsString(By locator, String attribute, String expectedString)
    {
        boolean value = false;
        long start = System.currentTimeMillis();
        try {
            handleAsyncWait();
            WebElement element = DriverManager.getDriver().findElement(locator);
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Framework_Constants.getTimeoutMax()));
            ExpectedCondition<Boolean> elementAttributeContainsString ;
            elementAttributeContainsString = arg0 -> element.getAttribute(attribute).contains(expectedString);
            wait.until(elementAttributeContainsString);
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(printWaitTime("waitForElementAttributeContainsString()",totalTime,CommonUtility.getByLocator(element)));
        }
        catch (Exception e)
        {
            PrintUtility.logFail("waitForElementAttributeContainsString(): Element not found -> "+ locator + "\n"+ e);
        }
        return value;
    }

    public static WebElement waitForElementClickable(WebElement element)
    {
        WebElement element_clickable = null;
        try
        {
            long start = System.currentTimeMillis();
            handleAsyncWait();
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Framework_Constants.getTimeout()));
            element_clickable = wait.until(ExpectedConditions.elementToBeClickable(element));
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(printWaitTime("waitForElementClickable()",totalTime,CommonUtility.getByLocator(element)));
        }
        catch (Exception e)
        {
            PrintUtility.logFail("waitForElementClickable(): Element not found -> "+ CommonUtility.getByLocator(element) + "\n"+ e);
        }
        return element_clickable;
    }

    public static By waitForElementClickable(By locator)
    {
        WebElement element_clickable = null;
        try
        {
            long start = System.currentTimeMillis();
            handleAsyncWait();
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Framework_Constants.getTimeout()));
            element_clickable = wait.until(ExpectedConditions.elementToBeClickable(locator));
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(printWaitTime("waitForElementClickable()",totalTime,locator));
        }
        catch (Exception e)
        {
            PrintUtility.logFail("waitForElementClickable(): Element not found -> "+ locator + "\n"+ e);
        }
        return CommonUtility.getByLocator(element_clickable);
    }

    public static WebElement waitForElementToBePresent(WebElement element) throws Exception
    {
        WebElement element_present = null;
        try
        {
            long start = System.currentTimeMillis();
            handleAsyncWait();
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Framework_Constants.getTimeout()));
            element_present = wait.until(ExpectedConditions.presenceOfElementLocated(CommonUtility.getByLocator(element)));
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(printWaitTime("waitForElementToBePresent()", totalTime, CommonUtility.getByLocator(element)));
        }
        catch (Exception e)
        {
            PrintUtility.logFail("waitForElementToBePresent(): Element not found -> "+CommonUtility.getByLocator(element) + "\n"+ e);
        }
        return element_present;
    }

    public static WebElement waitForElementToBePresent(List<WebElement> element)
    {
        WebElement element_present = null;
        try
        {
            long start = System.currentTimeMillis();
            handleAsyncWait();
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Framework_Constants.getTimeout()));
            element_present = wait.until(ExpectedConditions.presenceOfElementLocated(CommonUtility.getByLocator(element.get(0))));
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(printWaitTime("waitForElementToBePresent()",totalTime,CommonUtility.getByLocator(element.get(0))));
        }
        catch (Exception e)
        {
            // TODO: handle exception
            PrintUtility.logWarn("waitForElementToBePresent(): Element not found -> "+CommonUtility.getByLocator(element.get(0)) + "\n"+ e);
        }
        return element_present;
    }


    public static boolean waitForTextToBePresentInElement(WebElement element, String expectedText)
    {
        boolean result=false;
        try
        {
            long start = System.currentTimeMillis();
            handleAsyncWait();
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Framework_Constants.getTimeout()), Duration.ofMillis(Framework_Constants.getPollTime()));
            result = wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(printWaitTime("waitForTextToBePresentInElement()",totalTime,CommonUtility.getByLocator(element)));
        }
        catch (Exception e)
        {
            // TODO: handle exception
            PrintUtility.logFail("waitForTextToBePresentInElement(): Element not found -> "+CommonUtility.getByLocator(element) + "\n"+ e);
        }
        return result;
    }

    public static boolean waitForTextToBePresentInElement(By locator, String expectedText)
    {
        boolean result=false;
        WebElement element = null;
        try
        {
            long start = System.currentTimeMillis();
            handleAsyncWait();
            element = DriverManager.getDriver().findElement(locator);
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Framework_Constants.getTimeout()), Duration.ofMillis(Framework_Constants.getPollTime()));
            result = wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(printWaitTime("waitForTextToBePresentInElement()",totalTime,CommonUtility.getByLocator(element)));
        }
        catch (Exception e)
        {
            // TODO: handle exception
            PrintUtility.logFail("waitForTextToBePresentInElement(): Element not found -> "+CommonUtility.getByLocator(element) + "\n"+ e);
        }
        return result;
    }

    public static boolean waitForElementToBeInvisible(WebElement element)
    {
        boolean value=false;
        try
        {
            long start = System.currentTimeMillis();
            handleAsyncWait();
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Framework_Constants.getTimeout()), Duration.ofMillis(Framework_Constants.getPollTime()));
            By byElement =	CommonUtility.getByLocator(element);
            value = wait.until(ExpectedConditions.invisibilityOfElementLocated(byElement));
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(printWaitTime("waitForElementToBeInvisible()",totalTime,CommonUtility.getByLocator(element)));
        }
        catch (Exception e)
        {
            // TODO: handle exception
            PrintUtility.logFail("waitForElementToBeInvisible(): Element not found -> "+ CommonUtility.getByLocator(element) + "\n"+ e);
        }
        return value;
    }

    public static boolean waitForElementToBeInvisible(By locator)
    {
        boolean value=false;
        try
        {
            long start = System.currentTimeMillis();
            handleAsyncWait();
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Framework_Constants.getTimeout()), Duration.ofMillis(Framework_Constants.getPollTime()));
            value = wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(printWaitTime("waitForElementToBeInvisible()",totalTime,locator));
        }
        catch (Exception e)
        {
            // TODO: handle exception
            PrintUtility.logFail("waitForElementToBeInvisible(): Element not found -> "+ locator + "\n"+ e);
        }
        return value;
    }


    public static void addPaddedWait(int timeInSec)
    {
        long start = System.currentTimeMillis();
        try
        {
            Thread.sleep(timeInSec*1000);
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("addPaddedWait():",totalTime,""));
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void handleAsyncWait()
    {
        //To-Do
    }

}
