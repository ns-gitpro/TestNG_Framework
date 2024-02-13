package commonUtilities;

import driverFactory.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public final class ScrollUtilities extends FunctionLibrary
{
	private ScrollUtilities()
	{

	}

	static JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getDriver();
	public static void scroll(int pixel)
	{
		jse.executeScript("window.scrollTo(0, " + pixel + ")");
	}
	
	public static void scroll(String position)
	{
		long start = System.currentTimeMillis();
		if (position.equalsIgnoreCase("top"))
			jse.executeScript("window.scrollTo(0, 0)");
		else if (position.equalsIgnoreCase("bottom"))
			jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		try 
		{
			Thread.sleep(500);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		long finish = System.currentTimeMillis();
		long totalTime = finish - start; 
		PrintUtility.printWaitTime("scroll() - "+position,totalTime);
	}
	
	public static By scrollToElement(By locator)
	{
		long start = System.currentTimeMillis();
		WebElement refresh_element = DriverManager.getDriver().findElement(locator);
		String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
		jse = (JavascriptExecutor) DriverManager.getDriver();
		jse.executeScript(scrollElementIntoMiddle, refresh_element);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long finish = System.currentTimeMillis();
		long totalTime = finish - start; 
		PrintUtility.logPass(PrintUtility.printWaitTime("scrollToElement()",totalTime,locator));
		return locator;
	}
	
	
	/**
	 *
	 * scroll Horizontally Inside Element that includes it's own scroll bar. Provide
	 * pixel to scroll in integer.
	 *
	 * @since 04/05/2020
	 * @author Nikhil Singh
	 * @param WebElement, pixel
	 * 
	 */
	public static By scrollHorizontallyInsideElement(By locator, int pixel)
	{
		long start = System.currentTimeMillis();
		WebElement element = DriverManager.getDriver().findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		jse.executeScript("arguments[0].scrollLeft += " + String.valueOf(pixel), element);
		try 
		{
			Thread.sleep(500);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		long finish = System.currentTimeMillis();
		long totalTime = finish - start; 
		PrintUtility.printWaitTime("scrollHorizontallyInsideElement()",totalTime,locator);
		return locator;
	}
	
	/**
	 *
	 * scroll Vertically Inside Element that includes it's own scroll bar. Provide
	 * pixel to scroll in integer.
	 *
	 * @since 27/07/2020
	 * @author Nikhil Singh
	 * @param By, pixel
	 * 
	 */
	public static By scrollVerticallyInsideElement(By locator, int pixel)
	{
		long start = System.currentTimeMillis();
		WebElement element = DriverManager.getDriver().findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		js.executeScript("arguments[0].scrollTop += " + String.valueOf(pixel), element);
		try 
		{
			Thread.sleep(500);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		long finish = System.currentTimeMillis();
		long totalTime = finish - start; 
		PrintUtility.logPass(PrintUtility.printWaitTime("scrollVerticallyInsideElement()",totalTime,locator));
		return locator;
	}

	public static By scrollVerticallyInElement(By locator, int pixel)
	{
		long start = System.currentTimeMillis();
		WebElement element = DriverManager.getDriver().findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		js.executeScript("arguments[0].scrollTop += " + String.valueOf(pixel), element);
		try
		{
			Thread.sleep(500);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		long finish = System.currentTimeMillis();
		long totalTime = finish - start;
		PrintUtility.logPass(PrintUtility.printWaitTime("scrollVerticallyInElement()",totalTime,locator));
		return locator;
	}


	public static void scrollToElementAndClick(By locator)
	{
		boolean success=false;
		int attempts = 0;
		while(attempts < 2)
		{
			try
			{
				/******* TRY 1 ***********/
				try
				{
					//False (Scroll To Bottom section of element)
					WebElement refresh_element = DriverManager.getDriver().findElement(locator);
					jse = (JavascriptExecutor) DriverManager.getDriver();
					scroll("bottom");
					jse.executeScript("arguments[0].scrollIntoView(false);", refresh_element);
					refresh_element.click();
					success=true;
				}
				catch (Exception e)
				{

				}

				/******* TRY 2 ***********/
				try
				{
					WebElement refresh_element = DriverManager.getDriver().findElement(locator);
					jse = (JavascriptExecutor) DriverManager.getDriver();
					scroll("bottom");
					jse.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'end', inline: 'nearest'});", refresh_element);
					refresh_element.click();
					success=true;
				}
				catch (Exception e)
				{

				}

				/******* FINAL TRY 3 ***********/
				if(success==false)
				{
					//True (Scroll To Top section of element)

					WebElement refresh_element = DriverManager.getDriver().findElement(locator);
					jse = (JavascriptExecutor) DriverManager.getDriver();
					jse.executeScript("arguments[0].scrollIntoView(true);", refresh_element);
					refresh_element.click();
				}
				break;
			}
			catch(StaleElementReferenceException s)
			{
				System.out.println("StaleElementReferenceException: "+s);
			}
			catch (Exception e2)
			{
				System.out.println("StaleElementReferenceException: "+e2);
			}
			attempts++;
		}
	}
}
