package commonUtilities;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import constants.Framework_Constants;
import driverFactory.DriverManager;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import reportUtilities.ExtentFactory;

import java.io.*;
import java.nio.file.*;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.util.Map;
import java.util.regex.Pattern;

import static commonUtilities.AsynchronousWaitUtil.addPaddedWait;
import static commonUtilities.PrintUtility.*;

public final class CommonUtility
{
    private CommonUtility()
    {

    }

    public static void createDirectoryIfNotExists(String path, String DirectoryName) {
        String projectPath = path;
        String directoryName = DirectoryName;
        File testOutput = new File(projectPath);
        String separator = FileSystems.getDefault().getSeparator();
        File directory = new File(projectPath + separator + directoryName);
        if (!checkIfFolderExists(projectPath)) {
            testOutput.mkdir();
        }
        if (!checkIfFolderExists(directoryName)) {
            directory.mkdir();
        }
    }


    public static boolean checkIfFolderExists(String folderName) {
        boolean found = false;
        try {
            File file = new File(folderName);
            if (file.exists() && file.isDirectory()) {
                found = true;
            }
        } catch (Exception exp1) {
            exp1.printStackTrace();
        }
        return found;
    }

    public static boolean checkIfFileExists(String fileName) {
        boolean found = false;
        try {
            File file = new File(fileName);
            if (file.exists() && file.isFile()) {
                found = true;
            }
        } catch (Exception exp1) {
            found=false;
        }
        return found;
    }

    public static By getByLocator(WebElement we) {
        String[] data0;
        String locator;
        String term;
        if (we.toString().contains("DefaultElementLocator")) {
            data0 = we.toString().split("DefaultElementLocator");
            String data1 = data0[1];
            String data[] = replaceLast(data1, "[']", "").split(": ");
            String data_loc0 = data[0].replaceFirst("[']", "");
            String data_loc1[] = data_loc0.split("[.]");
            locator = data_loc1[1];
            term = data[1];
        } else {
            data0 = we.toString().split(" -> ");
            String data1 = data0[1];
            String data[] = replaceLast(data1, "]", "").split(": ");
            locator = data[0];
            term = data[1];
        }

        switch (locator) {
            case "xpath":
                return By.xpath(term);
            case "css selector":
                return By.cssSelector(term);
            case "id":
                return By.id(term);
            case "tag name":
                return By.tagName(term);
            case "name":
                return By.name(term);
            case "link text":
                return By.linkText(term);
            case "class name":
                return By.className(term);
        }
        return (By) we;
    }

    public static String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
    }

    public static void highLightElement(By locator)
    {
        try
        {
            WebElement element = (WebElement) DriverManager.getDriver().findElement(locator);
            JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getDriver();
            jse.executeScript("arguments[0].setAttribute('style','border:3px dashed Violet');", element);
        }
        catch (Exception e)
        {
            //logWarn("Not able to highlight Element : "+ element);
        }
    }

    public static void highLightElement(WebElement element)
    {
        try
        {
            JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getDriver();
            jse.executeScript("arguments[0].setAttribute('style','border:3px dashed Violet');", element);
        }
        catch (Exception e)
        {
            //logWarn("Not able to highlight Element : "+ element);
        }
    }

    public static void unHighLightElement(By locator)
    {
        try {
            WebElement element = (WebElement) DriverManager.getDriver().findElement(locator);
            JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getDriver();
            jse.executeScript("arguments[0].setAttribute('style','border:');", element);
        }
        catch (Exception e)
        {
            //logWarn("Not able to highlight Element : "+ element);
        }
    }


    public static void unHighLightElement(WebElement element)
    {
        try
        {
            JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getDriver();
            jse.executeScript("arguments[0].setAttribute('style','border:');", element);
        }
        catch (Exception e)
        {
            //logWarn("Not able to highlight Element : "+ element);
        }
    }

    public static void logScreenshot(String screenshotPath, String LogLevelScreenshot)
    {
        switch (LogLevelScreenshot)
        {
            case "ON" :
                ExtentFactory.getExtent().log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotPath).build());
                break;
            case "OFF" :
                break;
            default :
                PrintUtility.logFail("Invalid Property Value !");
                break;
        }
    }


    @SneakyThrows
    public static String copyText(By locator)
    {
        Keys cmdCtrl = Platform.getCurrent().is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;
        String selectAll = Keys.chord(cmdCtrl, "a");
        WebElement target_element = DriverManager.getDriver().findElement(locator);
        target_element.sendKeys(selectAll);

        String copyAll = Keys.chord(cmdCtrl,"c");
        target_element.sendKeys(copyAll);

        String data = (String) Toolkit.getDefaultToolkit()
                .getSystemClipboard().getData(DataFlavor.stringFlavor);
        return data;
    }

    public static void pasteText(By locator)
    {
        Keys cmdCtrl = Platform.getCurrent().is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;
        WebElement target_element = DriverManager.getDriver().findElement(locator);
        String pasteAll = Keys.chord(cmdCtrl,"v");
        target_element.sendKeys(pasteAll);
    }

    public static void copyAndPasteWithAdditionalText(By locator,String inputText)
    {
        Keys cmdCtrl = Platform.getCurrent().is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;
        String selectAll = Keys.chord(cmdCtrl, "a");
        WebElement target_element = DriverManager.getDriver().findElement(locator);
        target_element.sendKeys(selectAll);

        String copyAll = Keys.chord(cmdCtrl,"c");
        target_element.sendKeys(copyAll);

        String pasteAll = Keys.chord(cmdCtrl,"v");
        target_element.sendKeys(pasteAll+" "+inputText);
    }

    public static void copyAndPasteText(By source_locator, By destination_locator)
    {
        Keys cmdCtrl = Platform.getCurrent().is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;
        String selectAll = Keys.chord(cmdCtrl, "a");
        WebElement target_element = DriverManager.getDriver().findElement(source_locator);
        target_element.sendKeys(selectAll);

        String copyAll = Keys.chord(cmdCtrl,"c");
        WebElement destination_element = DriverManager.getDriver().findElement(destination_locator);
        destination_element.sendKeys(copyAll);

        String pasteAll = Keys.chord(cmdCtrl,"v");
        target_element.sendKeys(pasteAll);
    }

    public static void copyAndPasteWithAdditionalText(By source_locator, By destination_locator,String inputText)
    {
        Keys cmdCtrl = Platform.getCurrent().is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;
        String selectAll = Keys.chord(cmdCtrl, "a");
        WebElement target_element = DriverManager.getDriver().findElement(source_locator);
        target_element.sendKeys(selectAll);

        String copyAll = Keys.chord(cmdCtrl,"c");
        WebElement destination_element = DriverManager.getDriver().findElement(destination_locator);
        destination_element.sendKeys(copyAll);

        String pasteAll = Keys.chord(cmdCtrl,"v");
        destination_element.sendKeys(pasteAll+" "+inputText);
    }


    public static void uploadFile(By locator, String filePath)
    {
        try
        {
            addPaddedWait(1);
            ActionEngine.sendKeys(locator,filePath);
            logPass("File Uploaded Successfully");
        }
        catch (Exception e)
        {
            logFail("File failed to Upload !");
        }
    }

    public static void downloadFile(By locator)
    {
        createDirectoryIfNotExists(Framework_Constants.getResourcesFilepath(), Framework_Constants.getDownloadFolderName());
        AsynchronousWaitUtil.waitForElementToBeVisible(locator);
        ActionEngine.clickElement(locator);
    }

    public static void verifyDownloadedFile(String filePath, String fileName)
    {
        addPaddedWait(3);
        long start = System.currentTimeMillis();
        if(checkIfFileExists(filePath))
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logPass(PrintUtility.printWaitTime("verifyDownloadedFile():",totalTime,"File Found: " + fileName) );
        }
        else
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            PrintUtility.logWarn(PrintUtility.printWaitTime("verifyDownloadedFile():",totalTime," File Not Found: " + fileName) );
        }
    }

    /**
     *
     * Gets the size of the screen
     *
     * @return
     */
    public static Dimension getScreenSize() { return DriverManager.getDriver().manage().window().getSize(); }

    public static void renameFile(String oldFileName, String newFileName, boolean overwriteExistingFile)
    {
        long start = System.currentTimeMillis();
        try
        {
            overwriteExistingFile(oldFileName, newFileName, overwriteExistingFile);
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            logPass(printWaitTime("renameFile():",totalTime," Old File Name: " + oldFileName + "--> New File Name : "+ newFileName));
        }
        catch (IOException e)
        {
            // Handle the error gracefully.
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            logFail(printWaitTime("renameFile():",totalTime,"Error overwriting file: " + e.getMessage()));
        }
    }

    public static void overwriteExistingFile(String oldFileName, String newFileName, boolean overwrite) throws IOException
    {

        Path oldPath = Path.of(oldFileName);
        Path newPath = Path.of(newFileName);

        if (Files.exists(newPath) && !overwrite)
        {
            throw new IOException("File already exists. Please choose a different name or set the overwrite flag to true.");
        }
        Files.move(oldPath, newPath, StandardCopyOption.REPLACE_EXISTING);
    }

    public static void copyFile(String sourceFilePath, String destinationFilePath, boolean overwrite)
    {
        long start = System.currentTimeMillis();
        try
        {
            Path source = Path.of(sourceFilePath);
            Path destination = Path.of(destinationFilePath);
            if (Files.exists(destination) && !overwrite)
            {
                throw new IOException("File already exists: " + destination);
            }
            FileUtils.copyFile(source.toFile(), destination.toFile());
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            logPass(printWaitTime("copyFile():",totalTime,"File Copied Successfully. Copied File : "+destination));
        }
        catch (Exception e)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            logFail(printWaitTime("copyFile():",totalTime,"Error coping file: " + e.getMessage()));
        }
    }

    public static void deleteFile(String filePath)
    {
        long start = System.currentTimeMillis();
        File file = new File(filePath);
        if (file.exists())
        {
            try
            {
                FileUtils.forceDelete(file);
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                logPass(printWaitTime("deleteFile():",totalTime,"File deleted successfully. File Deleted: "+filePath));
            }
            catch (Exception e)
            {
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                logFail(printWaitTime("deleteFile():",totalTime,"An error occurred while deleting the file. File not deleted: "+filePath+"\n"+e.getMessage()));
            }
        }
        else
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            logInfo(printWaitTime("deleteFile():",totalTime,"File does not exist : "+filePath));
        }
    }


    public static boolean verifyPattern(String input, String regex)
    {
        long start = System.currentTimeMillis();
        Pattern pattern = Pattern.compile(regex);
        boolean result = pattern.matcher(input).matches();
        if (result)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            logPass(printWaitTime("verifyPattern():",totalTime,"The input string matches the regular expression."));
        }
        else
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            logFail(printWaitTime("verifyPattern():",totalTime,"The input string does not match the regular expression."));
        }
        return result;
    }

    public static void printProcessLogs(Process processName)
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(processName.getInputStream()));
        String line;
        try
        {
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        }
        catch (Exception e)
        {
            System.out.println("Process logs Failed :"+ e.getMessage());
        }
    }

    public static String readLine(InputStream inputStream) throws IOException {
        StringBuilder line = new StringBuilder();
        int c;
        while ((c = inputStream.read()) != -1) {
            if (c == '\n') {
                break;
            }
            line.append((char) c);
        }
        return line.toString();
    }



}
