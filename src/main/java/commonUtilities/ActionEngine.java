package commonUtilities;

import driverFactory.DriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static commonUtilities.ScrollUtilities.scrollToElement;
import static commonUtilities.ScrollUtilities.scrollToElementAndClick;

/**
 * @author Nikhil Singh
 *
 */
public final class ActionEngine
{
    static String screenshotPath="";
    static Configuration config = ConfigFactory.create(Configuration.class);
    public static final String LogLevelScreenshot = config.LogLevelScreenshot();

    private ActionEngine()
    {

    }


    public static void clickElement(By locator)
    {
        long start = System.currentTimeMillis();

        try
        {
            WebElement webElement = DriverManager.getDriver().findElement(locator);
            scrollToElement(locator);
            CommonUtility.highLightElement(locator);
            screenshotPath=ScreenshotUtilities.captureScreenshotBase64();
            webElement.click();
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("clickElement()",totalTime,locator));
            CommonUtility.logScreenshot(screenshotPath, LogLevelScreenshot);
            CommonUtility.unHighLightElement(locator);
        }
        catch(ElementClickInterceptedException eci_exception)
        {
            CommonUtility.highLightElement(locator);
            scrollToElementAndClick(locator);
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("clickElement()",totalTime,locator));
            CommonUtility.unHighLightElement(locator);
        }
        catch (Exception e1)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("clickElement() : Failed ",totalTime,locator)+ "\nException:"+e1);
        }
    }

    public static void clickElement(By locator, int index)
    {
        long start = System.currentTimeMillis();
        List<WebElement> elements=null;
        try
        {
            elements = DriverManager.getDriver().findElements(locator);
            scrollToElement(CommonUtility.getByLocator(elements.get(index)));
            CommonUtility.highLightElement(elements.get(index));
            screenshotPath=ScreenshotUtilities.captureScreenshotBase64();
            elements.get(index).click();
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("clickElement()",totalTime, CommonUtility.getByLocator(elements.get(index))));
            CommonUtility.logScreenshot(screenshotPath, LogLevelScreenshot);
            CommonUtility.unHighLightElement(CommonUtility.getByLocator(elements.get(index)));
        }
        catch(ElementClickInterceptedException eci_exception)
        {
            CommonUtility.highLightElement(CommonUtility.getByLocator(elements.get(index)));
            scrollToElementAndClick(locator);
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("clickElement()",totalTime,CommonUtility.getByLocator(elements.get(index))));
            CommonUtility.unHighLightElement(locator);
        }
        catch (Exception e1)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logFail(PrintUtility.printWaitTime("clickElement() : Failed ",totalTime,CommonUtility.getByLocator(elements.get(index)))+ "\nException:"+e1);
        }
    }


    public void submit(By element)
    {
        DriverManager.getDriver().findElement(element).submit();
    }

    public static void sendKeys(By locator, String text)
    {
        long start = System.currentTimeMillis();
        WebElement webElement = DriverManager.getDriver().findElement(locator);
        try
        {
            CommonUtility.highLightElement(locator);
            webElement.sendKeys(text);
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("sendKeys()", totalTime, " value: "+text, locator));
            CommonUtility.unHighLightElement(locator);
        }
        catch (Exception e)
        {
            PrintUtility.logFail("sendKeys() Failed: "+locator+ "\nException:"+e);
        }
    }

    public static void sendKeys(By locator, String text, int index)
    {
        long start = System.currentTimeMillis();
        List<WebElement> webElement = DriverManager.getDriver().findElements(locator);
        try
        {
            CommonUtility.highLightElement(locator);
            webElement.get(index).sendKeys(text);
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("sendKeys()", totalTime, " value: "+text, CommonUtility.getByLocator(webElement.get(index))));
            CommonUtility.unHighLightElement(locator);
        }
        catch (Exception e)
        {
            PrintUtility.logFail("sendKeys() Failed: "+webElement.get(index)+ "\nException:"+e);
        }
    }

    public static void sendKeys(By locator, Keys keys)
    {
        long start = System.currentTimeMillis();
        WebElement webElement = DriverManager.getDriver().findElement(locator);
        try
        {
            CommonUtility.highLightElement(locator);
            webElement.sendKeys(keys);
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("sendKeys()", totalTime, " value: "+keys, locator));
            CommonUtility.unHighLightElement(locator);
        }
        catch (Exception e)
        {
            PrintUtility.logFail("sendKeys() Failed: "+locator+ "\nException:"+e);
        }
    }

    public static void sendKeys(By locator, Keys keys, int index)
    {
        long start = System.currentTimeMillis();
        List<WebElement> webElement = DriverManager.getDriver().findElements(locator);
        try
        {
            CommonUtility.highLightElement(locator);
            webElement.get(index).sendKeys(keys);
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("sendKeys()", totalTime, " value: "+keys, CommonUtility.getByLocator(webElement.get(index))));
            CommonUtility.unHighLightElement(locator);
        }
        catch (Exception e)
        {
            PrintUtility.logFail("sendKeys() Failed: "+webElement.get(index)+ "\nException:"+e);
        }
    }


    public static void clear(By locator)
    {
        long start = System.currentTimeMillis();
        WebElement webElement = DriverManager.getDriver().findElement(locator);
        try
        {
            CommonUtility.highLightElement(locator);
            webElement.clear();
            Thread.sleep(250);
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("clear()",totalTime, "Successfully Cleared Data",locator));
            CommonUtility.unHighLightElement(locator);
        }
        catch (Exception e)
        {
            PrintUtility.logFail("clear() -> Unable to clear Data on element: " +locator +"\nException: "+e);
        }
    }


    public static String getTagName(By locator)
    {
        long start = System.currentTimeMillis();
        String text="";
        try
        {
            CommonUtility.highLightElement(locator);
            WebElement webElement = DriverManager.getDriver().findElement(locator);
            text = webElement.getTagName();
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("getTagName()",totalTime, "Tag Name retrieved: " + text,locator));
            return text;
        }
        catch (Exception e)
        {
            System.out.println(ConsoleColors.RED + "\n"+PrintUtility.FAIL+"getTagName() Failed: Tage Name not retrieved from Element: " +locator+ "\nException: "+ e + ConsoleColors.RESET);
            throw e;
        }
    }



    public static String getAttribute(By locator, String attributeName)
    {
        long start = System.currentTimeMillis();
        String text = "";
        try
        {
            CommonUtility.highLightElement(locator);
            text = DriverManager.getDriver().findElement(locator).getAttribute(attributeName);
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("getAttribute()",totalTime, "Attribute retrived: "+ text,locator));
            return text;
        }
        catch (Exception e)
        {
            System.out.println(ConsoleColors.RED + "\n"+PrintUtility.FAIL+"getAttribute() Failed: Attribute not retrieved from Element: " +locator+ "\nException: "+ e + ConsoleColors.RESET);
            throw e;
        }
    }



    public static boolean isSelected(By locator)
    {
        long start = System.currentTimeMillis();
        boolean status;
        try
        {
            CommonUtility.highLightElement(locator);
            screenshotPath=ScreenshotUtilities.captureScreenshotBase64();
            WebElement webElement = DriverManager.getDriver().findElement(locator);
            status = webElement.isSelected();
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("isSelected()",totalTime, "Element selected: "+ status,locator));
            CommonUtility.logScreenshot(screenshotPath, LogLevelScreenshot);
            CommonUtility.unHighLightElement(locator);
            return status;
        }
        catch (Exception e)
        {
            PrintUtility.logFail("isSelected() Failed: Element not selected: " +locator+ "\nException: "+ e);
            throw e;
        }
    }


    public static boolean isEnabled(By locator)
    {
        long start = System.currentTimeMillis();
        boolean status;
        try
        {
            CommonUtility.highLightElement(locator);
            screenshotPath=ScreenshotUtilities.captureScreenshotBase64();
            WebElement webElement = DriverManager.getDriver().findElement(locator);
            status = webElement.isEnabled();
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("isEnabled()",totalTime, "Element enabled: "+ status,locator));
            CommonUtility.logScreenshot(screenshotPath, LogLevelScreenshot);
            CommonUtility.unHighLightElement(locator);
            return status;
        }
        catch (Exception e)
        {
            PrintUtility.logFail("isEnabled() Failed: Element not enabled: " +locator+ "\nException: "+ e);
            throw e;
        }
    }



    public static String getText(By locator)
    {
        long start = System.currentTimeMillis();
        String text = "";
        try
        {
            CommonUtility.highLightElement(locator);
            screenshotPath=ScreenshotUtilities.captureScreenshotBase64();
            text = DriverManager.getDriver().findElement(locator).getText();
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("getText()",totalTime, "Text retrieved: "+ text,locator));
            CommonUtility.logScreenshot(screenshotPath, LogLevelScreenshot);
            CommonUtility.unHighLightElement(locator);
            return text;
        }
        catch (Exception e)
        {
            PrintUtility.logFail("getText() Failed: Text not retrieved from Element: " +locator+ "\nException: "+ e);
            throw e;
        }
    }

    public static String getText(By locator, int index)
    {
        long start = System.currentTimeMillis();
        String text = "";
        List<WebElement> webElement = DriverManager.getDriver().findElements(locator);
        try
        {
            CommonUtility.highLightElement(locator);
            screenshotPath=ScreenshotUtilities.captureScreenshotBase64();
            text = webElement.get(index).getText();
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("getText()",totalTime, "Text retrieved from index-"+index+": "+ text,locator));
            CommonUtility.logScreenshot(screenshotPath, LogLevelScreenshot);
            CommonUtility.unHighLightElement(locator);
            return text;
        }
        catch (Exception e)
        {
            PrintUtility.logFail("getText() Failed: Text not retrieved from Element: " +locator+ "\nException: "+ e);
            throw e;
        }
    }

    public List<WebElement> findElements(By by)
    {
        return null;
    }


    public WebElement findElement(By locator)
    {
        return null;
    }



    public static boolean isDisplayed(By locator)
    {
        long start = System.currentTimeMillis();
        boolean flag = false;
        try {

            WebElement element = DriverManager.getDriver().findElement(locator);
            flag = element.isDisplayed();
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("isDisplayed()", totalTime, "Presence of field is: " + flag, locator));
            return flag;
        }
        catch (Exception e)
        {
            PrintUtility.logFail("isDisplayed() Failed: Checking for presence of Element: " +locator +"\nException: "+e);
            return flag;
        }
    }


    public static Point getLocation(By locator)
    {
        long start = System.currentTimeMillis();
        Point point;
        try
        {
            CommonUtility.highLightElement(locator);
            WebElement webElement = DriverManager.getDriver().findElement(locator);
            point = webElement.getLocation();
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.printWaitTime("getLocation()",totalTime, "Location retrieved: X coordinate : " + point.x + " Y coordinate: " + point.y,locator);
            return point;
        }
        catch (Exception e)
        {
            System.out.println(ConsoleColors.RED + "\n"+PrintUtility.FAIL+"getLocation() Failed: Location not retrieved from Element: " +locator+ "\nException: "+ e + ConsoleColors.RESET);
            throw e;
        }
    }



    public static Dimension getSize(By locator)
    {
        long start = System.currentTimeMillis();
        Dimension dimensions;
        try
        {
            CommonUtility.highLightElement(locator);
            WebElement webElement = DriverManager.getDriver().findElement(locator);
            dimensions = webElement.getSize();
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("getSize()",totalTime, "Location retrieved: Height : " + dimensions.height + " Width :  " + dimensions.width,locator));
            return dimensions;
        }
        catch (Exception e)
        {
            System.out.println(ConsoleColors.RED + "\n"+PrintUtility.FAIL+"getSize() Failed: Size not retrieved from Element: " +locator+ "\nException: "+ e + ConsoleColors.RESET);
            throw e;
        }
    }

    
    public static Rectangle getRect(By element) {
        return null;
    }

    
    public static String getCssValue(By locator, String propertyName) {
        long start = System.currentTimeMillis();
        String text = "";
        try
        {
            CommonUtility.highLightElement(locator);
            text = DriverManager.getDriver().findElement(locator).getCssValue(propertyName);
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("getAttribute()",totalTime, "Attribute retrieved: "+ text,locator));
            return text;
        }
        catch (Exception e)
        {
            System.out.println(ConsoleColors.RED + "\n"+PrintUtility.FAIL+"getAttribute() Failed: Attribute not retrieved from Element: " +locator+ "\nException: "+ e + ConsoleColors.RESET);
            throw e;
        }
    }

    public static void selectDropdownOptionByText(By locator, String option)
    {
        long start = System.currentTimeMillis();
        try
        {
            CommonUtility.highLightElement(locator);
            Select dropdown_generationResult = new Select(DriverManager.getDriver().findElement(locator));
            dropdown_generationResult.selectByVisibleText(option);
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("selectDropdownOptionByText()",totalTime, "Option Selected: "+ option,locator));
        }
        catch (Exception e)
        {
            PrintUtility.logFail("selectDropdownOptionByText() Failed : " +locator+ "\nException: "+ e);
            throw e;
        }
    }
    public static void selectDropdownOptionByText(By locator, String option, int index)
    {
        long start = System.currentTimeMillis();
        List<WebElement> element = null;
        try
        {
            element = DriverManager.getDriver().findElements(locator);
            CommonUtility.highLightElement(element.get(index));
            Select dropdown_generationResult = new Select(element.get(index));
            dropdown_generationResult.selectByVisibleText(option);
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("selectDropdownOptionByText()",totalTime, "Option Selected: "+ option,CommonUtility.getByLocator(element.get(index))));
        }
        catch (Exception e)
        {
            PrintUtility.logFail("selectDropdownOptionByText() Failed : " +CommonUtility.getByLocator(element.get(index))+ "\nException: "+ e);
            throw e;
        }
    }
    public static void selectDropdownOptionByValue(By locator, String option)
    {
        long start = System.currentTimeMillis();
        try
        {
            CommonUtility.highLightElement(locator);
            Select dropdown_generationResult = new Select(DriverManager.getDriver().findElement(locator));
            dropdown_generationResult.selectByValue(option);
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("selectDropdownOptionByValue()",totalTime, "Option Selected: "+ option,locator));
        }
        catch (Exception e)
        {
            PrintUtility.logFail("selectDropdownOptionByValue() Failed : " +locator+ "\nException: "+ e);
            throw e;
        }
    }
    public static void selectDropdownOptionByValue(By locator, String option, int index)
    {
        long start = System.currentTimeMillis();
        List<WebElement> element = null;
        try
        {
            element = DriverManager.getDriver().findElements(locator);
            CommonUtility.highLightElement(element.get(index));
            Select dropdown_generationResult = new Select(element.get(index));
            dropdown_generationResult.selectByValue(option);
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("selectDropdownOptionByValue()",totalTime, "Option Selected: "+ option,CommonUtility.getByLocator(element.get(index))));
        }
        catch (Exception e)
        {
            PrintUtility.logFail("selectDropdownOptionByValue() Failed : " +CommonUtility.getByLocator(element.get(index))+ "\nException: "+ e);
            throw e;
        }
    }
    public static void selectDropdownOptionByIndex(By locator, int option_index)
    {
        long start = System.currentTimeMillis();
        try
        {
            CommonUtility.highLightElement(locator);
            Select dropdown_generationResult = new Select(DriverManager.getDriver().findElement(locator));
            dropdown_generationResult.selectByIndex(option_index);
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("selectDropdownOptionByIndex()",totalTime, "Option Selected: "+ option_index,locator));
        }
        catch (Exception e)
        {
            PrintUtility.logFail("selectDropdownOptionByIndex() Failed : " +locator+ "\nException: "+ e);
            throw e;
        }
    }
    public static void selectDropdownOptionByIndex(By locator, int option_index, int index)
    {
        long start = System.currentTimeMillis();
        List<WebElement> element = null;
        try
        {
            element = DriverManager.getDriver().findElements(locator);
            CommonUtility.highLightElement(element.get(index));
            Select dropdown_generationResult = new Select(element.get(index));
            dropdown_generationResult.selectByIndex(option_index);
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("selectDropdownOptionByIndex()",totalTime, "Option Selected: "+ option_index,CommonUtility.getByLocator(element.get(index))));
        }
        catch (Exception e)
        {
            PrintUtility.logFail("selectDropdownOptionByIndex() Failed : " +CommonUtility.getByLocator(element.get(index))+ "\nException: "+ e);
            throw e;
        }
    }

    public static int getCount(By locator)
    {
        long start = System.currentTimeMillis();
        List<WebElement> element = null;
        int count = 0;
        try
        {
            element = DriverManager.getDriver().findElements(locator);
            count = element.size();
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("getCount(): ",totalTime, "Total Count : "+ count,locator));
        }
        catch (Exception e)
        {
            PrintUtility.logFail("getCount() Failed : " +locator+ "\nException: "+ e);
            throw e;
        }
        return count;
    }

    
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return null;
    }

    
    public boolean elementWired() {
        return false;
    }

    
    public WebElement getWrappedElement() {
        return null;
    }

    
    public Coordinates getCoordinates(By element) {
        return null;
    }

    public static void jsClick(By locator)
    {
        long start = System.currentTimeMillis();
        CommonUtility.highLightElement(locator);
        JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getDriver();
        WebElement element = DriverManager.getDriver().findElement(locator);
        jse.executeScript("arguments[0].click();", element);
        long finish = System.currentTimeMillis();
        long totalTime = finish - start;
        PrintUtility.logPass(PrintUtility.printWaitTime("jsClick()",totalTime, locator));
        CommonUtility.unHighLightElement(locator);
    }
}
