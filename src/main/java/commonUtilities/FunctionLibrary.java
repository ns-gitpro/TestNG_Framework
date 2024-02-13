package commonUtilities;


import customVerificationUtilities.CustomSortingVerificationLibrary;
import customVerificationUtilities.CustomVerificationLibrary;
import customVerificationUtilities.VerificationLibrary;
import driverFactory.Driver;
import driverFactory.DriverManager;
import excelUtilities.ExcelLibrary;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.*;
import org.testng.asserts.SoftAssert;
import reportUtilities.ExtentUtilities;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Nikhil Singh
 *
 */
public class FunctionLibrary {

    static Configuration config = ConfigFactory.create(Configuration.class);
    public static SoftAssert softAssert = new SoftAssert();
    public static SoftAssertions softAssertions = new SoftAssertions();

    /********************* Driver Manager Class *********************/
    protected void launchBrowser()
    {
        Driver.initDriver();
    }

    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }


    protected void closeDriver() {
        DriverManager.closeDriver();
    }


    /********************* Action Engine Class *********************/

    /**
     *
     * Click this element. If this causes a new page to load, you should discard all references to this element and any further operations performed on this element will throw a StaleElementReferenceException.
     * There are some preconditions for an element to be clicked. The element must be visible, and it must have a height and width greater than 0.
     *
     * @param element
     */
    public void clickElement(By element) {
        ActionEngine.clickElement(element);
    }

    /**
     *
     * Click this element with index. If this causes a new page to load, you should discard all references to this element and any further operations performed on this element will throw a StaleElementReferenceException.
     * There are some preconditions for an element to be clicked. The element must be visible, and it must have a height and width greater than 0.
     *
     * @param element
     */
    protected void clickElement(By element, int index) {
        ActionEngine.clickElement(element, index);
    }

    /**
     *
     * If this current element is a form, or an element within a form, then this will be submitted to the remote server. If this causes the current page to change, then this method will block until the new page is loaded.
     *
     * @param locator
     */
    protected void submit(By locator) {
         ActionEngine.getText(locator);
    }

    /**
     *
     * Use this method to simulate typing into an element, which may set its value.
     *
     * @param locator
     * @param keysToSend
     */
    protected void sendKeys(By locator, String keysToSend) {
        ActionEngine.sendKeys(locator, keysToSend);
    }

    /**
     *
     * Use this method to simulate typing into an element with index, which may set its value.
     *
     * @param locator
     * @param keysToSend
     * @param index
     */
    protected void sendKeys(By locator, String keysToSend, int index) {
        ActionEngine.sendKeys(locator, keysToSend, index);
    }

    /**
     *
     * Use this method to simulate typing special keys into an element, which may set its value.
     *
     * @param locator
     * @param keys
     */
    protected void sendKeys(By locator, Keys keys) {
        ActionEngine.sendKeys(locator, keys);
    }

    /**
     *
     * Use this method to simulate typing special keys into an element with index, which may set its value.
     *
     * @param locator
     * @param keys
     * @param index
     */
    protected void sendKeys(By locator, Keys keys, int index) {
        ActionEngine.sendKeys(locator, keys, index);
    }

    /**
     *
     * If this element is a form entry element, this will reset its value.
     *
     * @param locator
     */
    protected void clear(By locator) {
         ActionEngine.clear(locator);
    }

    /**
     *
     * Get the tag name of this element. Not the value of the name attribute: will return "input" for the element <input name="foo" />.
     *
     * @param locator
     * @return
     */
    protected String getTagName(By locator) {
        return ActionEngine.getTagName(locator);
    }

    /**
     *
     * Get the value of the given attribute of the element. Will return the current value, even if this has been modified after the page has been loaded.
     * More exactly, this method will return the value of the property with the given name, if it exists. If it does not, then the value of the attribute with the given name is returned. If neither exists, null is returned.
     *
     * The "style" attribute is converted as best can be to a text representation with a trailing semicolon.
     *
     * The following are deemed to be "boolean" attributes, and will return either "true" or null:
     *
     * async, autofocus, autoplay, checked, compact, complete, controls, declare, defaultchecked, defaultselected, defer, disabled, draggable, ended, formnovalidate, hidden, indeterminate, iscontenteditable, ismap, itemscope, loop, multiple, muted, nohref, noresize, noshade, novalidate, nowrap, open, paused, pubdate, readonly, required, reversed, scoped, seamless, seeking, selected, truespeed, willvalidate
     *
     * Finally, the following commonly mis-capitalized attribute/property names are evaluated as expected:
     *
     * If the given name is "class", the "className" property is returned.
     * If the given name is "readonly", the "readOnly" property is returned.
     * Note: The reason for this behavior is that users frequently confuse attributes and properties. If you need to do something more precise, use getDomAttribute(String) or getDomProperty(String) to obtain the result you desire.
     *
     * @param locator
     * @param attributeName
     * @return
     */
    protected String getAttribute(By locator,String attributeName) {
        return ActionEngine.getAttribute(locator, attributeName);
    }

    /**
     *
     * Determine whether this element is selected or not. This operation only applies to input elements such as checkboxes, options in a select and radio buttons
     *
     * @param locator
     * @return
     */
    protected boolean isSelected(By locator) {
        return ActionEngine.isSelected(locator);
    }

    /**
     *
     * Is the element currently enabled or not? This will generally return true for everything but disabled input elements.
     *
     * @param locator
     * @return
     */
    public boolean isEnabled(By locator)
    {
        return ActionEngine.isEnabled(locator);
    }

    /**
     *
     * Get the visible (i.e. not hidden by CSS) text of this element, including sub-elements.
     *
     * @param locator
     * @return
     */
    protected String getText(By locator) {
        return ActionEngine.getText(locator);
    }

    protected String getText(By locator, int index) {
        return ActionEngine.getText(locator, index);
    }

    /**
     *
     * Is this element displayed or not? This method avoids the problem of having to parse an element's "style" attribute
     *
     * @param locator
     * @return
     */
    protected boolean isDisplayed(By locator) {
        return ActionEngine.isDisplayed(locator);
    }

    /**
     *
     * Where on the page is the top left-hand corner of the rendered element?
     *
     * @param locator
     * @return
     */
    protected Point getLocation(By locator)
    {
        return ActionEngine.getLocation(locator);
    }

    /**
     *
     * What is the width and height of the rendered element?
     *
     * @param locator
     * @return
     */
    protected Dimension getSize(By locator)
    {
        return ActionEngine.getSize(locator);
    }

    /**
     *
     * The location and size of the rendered element
     *
     * @param element
     * @return
     */
    public Rectangle getRect(By element)
    {
        return ActionEngine.getRect(element);
    }

    /**
     *
     * Get the size of the screen
     *
     * @return
     */
    public Dimension getScreenSize() { return CommonUtility.getScreenSize(); }

    /**
     *
     * Get the value of a given CSS property. Color values could be returned as rgba or rgb strings. This depends on whether the browser omits the implicit opacity value or not.
     *
     * @param locator
     * @param propertyName
     * @return
     */
    public String getCssValue(By locator,String propertyName)
    {
        return ActionEngine.getCssValue(locator,propertyName);
    }

    /**
     *
     * Select all options that display text matching the argument. That is, when given "Bar" this would select an option like:
     * <option value="foo">Bar</option>
     *
     * @param locator
     * @param option
     */
    public void selectDropdownOptionByText(By locator, String option)
    {
        ActionEngine.selectDropdownOptionByText(locator,option);
    }

    /**
     *
     * Perform click operation on web-element using javascript. Works directly on DOM
     * @param locator
     */
    public void jsClick(By locator)
    {
        ActionEngine.jsClick(locator);
    }


    /**
     *
     * Returns instances of web-element.
     *
     * @param locator
     * @return
     */
    public int getCount(By locator)
    {
        return ActionEngine.getCount(locator);
    }

    /**
     *
     * Select the option at the given index. This is done by examining the "index" attribute of an element, and not merely by counting.
     *
     * @param locator
     * @param option_index
     */
    protected static void selectDropdownOptionByIndex(By locator, int option_index)
    {
        ActionEngine.selectDropdownOptionByIndex(locator,option_index);
    }


    /********************* Common Utility Class *********************/

    protected void createDirectoryIfNotExists(String path, String DirectoryName) {
        CommonUtility.createDirectoryIfNotExists(path, DirectoryName);
    }

    protected void checkIfFolderExists(String folderName) {
        CommonUtility.checkIfFolderExists(folderName);
    }

    protected static String copyText(By locator)
    {
        return CommonUtility.copyText(locator);
    }
    protected  static void pasteText(By locator) {
        CommonUtility.pasteText(locator);
    }

    protected void copyAndPasteWithAdditionalText(By locator,String inputText) {
        CommonUtility.copyAndPasteWithAdditionalText(locator, inputText);
    }

    protected void copyAndPasteText(By source_locator,By destination_locator)
    {
        CommonUtility.copyAndPasteText(source_locator,destination_locator);
    }

    protected void copyAndPasteWithAdditionalText(By source_locator, By destination_locator,String inputText)
    {
        CommonUtility.copyAndPasteWithAdditionalText( source_locator,  destination_locator, inputText);
    }

    protected static void uploadFile(By locator,String filePath)
    {
        CommonUtility.uploadFile(locator, filePath);
    }

    protected static void downloadFile(By locator)
    {
        CommonUtility.downloadFile(locator);
    }

    protected static void verifyDownloadedFile(String filePath, String fileName)
    {
        CommonUtility.verifyDownloadedFile(filePath,fileName);
    }

    protected static void renameFile(String oldFileName, String newFileName, boolean overwriteExistingFile)
    {
        CommonUtility.renameFile(oldFileName, newFileName, overwriteExistingFile);
    }

    protected static void copyFile(String source, String destination, boolean overwrite)
    {
        CommonUtility.copyFile(source, destination, overwrite);
    }

    protected static void deleteFile(String filePath)
    {
        CommonUtility.deleteFile(filePath);
    }

    protected static boolean verifyPattern(String input, String regex)
    {
        return CommonUtility.verifyPattern(input,regex);
    }

    /**
     * Assigns category of test case Type. Example: Sanity/Functional
     * Testing/Regression
     */
    public void assignCategory(String category) {
        ExtentUtilities.assignCategory(category);
    }

    /**
     * Assigns author Name of test case.
     */
    public void assignAuthor(String authorName) {
        ExtentUtilities.assignAuthor(authorName);
    }


    /****************************** PrintUtilities Methods *****************************/
    public void logScenario(String scenario) {
        PrintUtility.logScenario(scenario);
    }

    /**
     * Prints Test case fail status in logger
     */
    public void logFail(String message) {
        PrintUtility.logFail(message);
    }


    /**
     * Prints Warning Message to the Console Log and Report
     */
    public void logWarn(String message) {
        PrintUtility.logWarn(message);
    }

    /**
     * Prints Skip event status in logger in console and report
     */
    public void logSkip(String message) {
        PrintUtility.logSkip(message);
    }

    /**
     * Prints Test case pass status in logger
     */
    public void logPass(String message) {
        PrintUtility.logPass(message);
    }


    /**
     * Prints Information in between steps or logs in Console
     */
    public void logInfo(String message) {
        PrintUtility.logInfo(message);
    }


    /**
     * Prints Test case fail status in logger
     */
    public void logStep(String message) {
        PrintUtility.logStep(message);
    }

    public void printWaitTime(String waitMethodType, long time) {
        PrintUtility.printWaitTime(waitMethodType, time);
    }


    /********************* AsynchronousWaitUtil Methods ************************/

    public WebElement waitForElementToAppear(WebElement element) {
        return AsynchronousWaitUtil.waitForElementToAppear(element);
    }


    public WebElement waitForElementToAppear(By element) {
        return AsynchronousWaitUtil.waitForElementToAppear(element);
    }

    public WebElement waitForElementToBeVisible(WebElement element) {
        return AsynchronousWaitUtil.waitForElementToBeVisible(element);
    }

    /**
     * hellow
     * @param element
     * @return
     */
    public WebElement waitForElementToBeVisible(By element) {
        return AsynchronousWaitUtil.waitForElementToBeVisible(element);
    }


    public List<WebElement> waitForElementToBeVisible(List<WebElement> element) {
        return AsynchronousWaitUtil.waitForElementToBeVisible(element);
    }

    public boolean waitForAttributeNotContains(WebElement element, String attribute, String attributeValue) {
        return AsynchronousWaitUtil.waitForAttributeNotContains(element, attribute, attributeValue);
    }


    public boolean waitForAttributeToChange(WebElement element, String attribute, String attributeValue) {
        return AsynchronousWaitUtil.waitForAttributeToChange(element, attribute, attributeValue);
    }

    public boolean waitForAttributeToChange(By element, String attribute, String attributeValue) {
        return AsynchronousWaitUtil.waitForAttributeToChange(element, attribute, attributeValue);
    }

    public boolean waitForAttributeToBe(By element, String attribute, String attributeValue) {
        return AsynchronousWaitUtil.waitForAttributeToBe(element, attribute, attributeValue);
    }

    public boolean waitForElementAttributeContainsString(WebElement element, String attribute, String expectedString) {
        return AsynchronousWaitUtil.waitForAttributeToChange(element, attribute, expectedString);
    }

    public boolean waitForElementAttributeContainsString(By locator, String attribute, String expectedString) {
        return AsynchronousWaitUtil.waitForAttributeToChange(locator, attribute, expectedString);
    }

    public WebElement waitForElementClickable(WebElement element) {
        return AsynchronousWaitUtil.waitForElementClickable(element);
    }

    public By waitForElementClickable(By element) {
        return AsynchronousWaitUtil.waitForElementClickable(element);
    }

    public WebElement waitForElementToBePresent(WebElement element) throws Exception {
        return AsynchronousWaitUtil.waitForElementToBePresent(element);
    }

    public WebElement waitForElementToBePresent(List<WebElement> element) {
        return AsynchronousWaitUtil.waitForElementToBePresent(element);
    }

    public boolean waitForTextToBePresentInElement(WebElement element, String expectedText) {
        return AsynchronousWaitUtil.waitForTextToBePresentInElement(element, expectedText);
    }

    public boolean waitForTextToBePresentInElement(By locator, String expectedText) {
        return AsynchronousWaitUtil.waitForTextToBePresentInElement(locator, expectedText);
    }

    public boolean waitForElementToBeInvisible(WebElement element) {
        return AsynchronousWaitUtil.waitForElementToBeInvisible(element);
    }

    public boolean waitForElementToBeInvisible(By locator) {
        return AsynchronousWaitUtil.waitForElementToBeInvisible(locator);
    }

    public void handleAsyncWait() {
        AsynchronousWaitUtil.handleAsyncWait();
    }

    public void addPaddedWait(int timeInSec) {
        AsynchronousWaitUtil.addPaddedWait(timeInSec);
    }

    /********************* Scroll Utilities Methods ************************/
    public static By scrollToElement(By locator)
    {
        return ScrollUtilities.scrollToElement( locator);
    }

    public static By scrollVerticallyInsideElement(By locator, int pixel)
    {
        return ScrollUtilities.scrollVerticallyInsideElement( locator,  pixel);
    }

    /**
     *
     * Scroll horizontally in an element
     *
     * @param locator
     * @param pixel
     * @return
     */
    public static By scrollHorizontallyInsideElement(By locator, int pixel)
    {
        return ScrollUtilities.scrollHorizontallyInsideElement( locator,  pixel);
    }

    /********************* VerificationUtility Methods ************************/


    public boolean verifyTrue(boolean condition)
    {
        return VerificationLibrary.verifyTrue(condition);
    }

    public static boolean verifyTrue(boolean condition, String message)
    {
        return VerificationLibrary.verifyTrue( condition,  message);
    }

    public static boolean verifyFalse(boolean condition)
    {
        return VerificationLibrary.verifyFalse( condition);
    }

    public static boolean verifyFalse(boolean condition, String message)
    {
        return VerificationLibrary.verifyFalse( condition,  message);
    }



    /********************* CustomVerificationLibrary Methods ************************/

    public void verifyElementPresentOnPage(By locator) {
        CustomVerificationLibrary.verifyElementPresentOnPage(locator);
    }

    public boolean isElementOnPage(By locator) {
        return CustomVerificationLibrary.isElementOnPage(locator);
    }

    public void verifyElementPresentOnPage(By locator, int index) {
        CustomVerificationLibrary.verifyElementPresentOnPage(locator, index);
    }

    public void verifyElementAbsentOnPage(By locator) {
        CustomVerificationLibrary.verifyElementAbsentOnPage(locator);
    }

    public void verifyElementAbsentOnPage(By locator, int index) {
        CustomVerificationLibrary.verifyElementAbsentOnPage(locator, index);
    }

    public void verifyElementOccurrenceCount(By locator, int count) {
        CustomVerificationLibrary.verifyElementOccurenceCount(locator, count);
    }

    public static void verifyTextExistsOnElement(By locator, String verificationText) {
        CustomVerificationLibrary.verifyTextExistsOnElement(locator, verificationText);
    }

    public void verifyIndexedTextExistsOnElement(By locator, String verificationText, int index) {
        CustomVerificationLibrary.verifyIndexedTextExistsOnElement(locator, verificationText, index);
    }

    public void verifyTextDoesNotExistOnElement(By locator, String verificationText) {
        CustomVerificationLibrary.verifyTextDoesNotExistOnElement(locator, verificationText);
    }

    public void verifyTextNotOnElement(By locator, String verificationText) {
        CustomVerificationLibrary.verifyTextNotOnElement(locator, verificationText);
    }

    public void verifyTextOnElement(By locator, String verificationText) {
        CustomVerificationLibrary.verifyTextOnElement(locator, verificationText);
    }

    public void verifyAttributeValue(By locator, String attributeName, String value) {
        CustomVerificationLibrary.verifyAttributeValue(locator, attributeName, value);
    }

    public void verifyAttributeValueContainsText(By locator, String attributeName, String value) {
        CustomVerificationLibrary.verifyAttributeValueContainsText(locator, attributeName, value);
    }

    public void verifyAttributeValueContainsText(By locator, int index, String attributeName, String value) {
        CustomVerificationLibrary.verifyAttributeValueContainsText(locator, index, attributeName, value);
    }

    public void verifyTextInPageSource(String expectedText) {
        CustomVerificationLibrary.verifyTextInPageSouce(expectedText);
    }

    public void verifyLinkExistsOnPage(By locator, String linkName) {
        CustomVerificationLibrary.verifyLinkExistsOnPage(locator, linkName);
    }

    public void verifyIndexedLinkExistsOnPage(By locator, String linkName, int index) {
        CustomVerificationLibrary.verifyIndexedLinkExistsOnPage(locator, linkName, index);
    }

    public void verifyLinkAbsenceOnPage(By locator, String linkName) {
        CustomVerificationLibrary.verifyLinkAbsenceOnPage(locator, linkName);
    }

    public void verifyInputBoxValueLength(By locator, int expectedLength) {
        CustomVerificationLibrary.verifyInputBoxValueLength(locator, expectedLength);
    }

    public void verifyTextBoxDefaultValue(By locator, String expectedDefaultValue) {
        CustomVerificationLibrary.verifyTextBoxDefaultValue(locator, expectedDefaultValue);
    }

    public void verifyTextInsideTag(By locator, String value) {
        CustomVerificationLibrary.verifyTextInsideTag(locator, value);
    }

    public void verifyDropDownSelection(By locator, String selectedItem) {
        CustomVerificationLibrary.verifyDropDownSelection(locator, selectedItem);
    }

    public void verifyDropDownSelectionIgnoreCase(By locator, String selectedItem) {
        CustomVerificationLibrary.verifyDropDownSelectionIgnoreCase(locator, selectedItem);
    }

    public void verifyDropDownContainsSelection(By locator, String selectedItem) {
        CustomVerificationLibrary.verifyDropDownContainsSelection(locator, selectedItem);
    }

    public void verifyDropDownContainsSelectionIgnoreCase(By locator, String selectedItem)
    {
        CustomVerificationLibrary.verifyDropDownContainsSelectionIgnoreCase( locator,  selectedItem);
    }

    public void verifyFirstDropDownSelection(By locator, String selectedItem)
    {
        CustomVerificationLibrary.verifyFirstDropDownSelection( locator,  selectedItem);
    }

    public void verifyOptionExistsOnDropDown(By locator, String item)
    {
        CustomVerificationLibrary.verifyOptionExistsOnDropDown( locator,  item);
    }

    public void verifyOptionDontExistsOnDropDown(By locator, String item)
    {
        CustomVerificationLibrary.verifyOptionDontExistsOnDropDown( locator,  item);
    }

    public void verifyValueInDropDownList(By locator, String[] valueToMatch)
    {
        CustomVerificationLibrary.verifyValueInDropDownList( locator,  valueToMatch);
    }

    public void verifyRadioButtonIsSelected(By locator)
    {
        CustomVerificationLibrary.verifyRadioButtonIsSelected( locator);
    }

    public void verifyRadioButtonIsSelected(By locator, int index)
    {
        CustomVerificationLibrary.verifyRadioButtonIsSelected( locator, index);
    }

    public void verifyRadioButtonIsNotSelected(By locator)
    {
        CustomVerificationLibrary.verifyRadioButtonIsNotSelected( locator);
    }

    public void verifyRadioButtonIsNotSelected(By locator, int index)
    {
        CustomVerificationLibrary.verifyRadioButtonIsNotSelected( locator,  index);
    }

    public void verifyCheckboxIsChecked(By locator)
    {
        CustomVerificationLibrary.verifyCheckboxIsChecked( locator);
    }

    public void verifyCheckboxIsChecked(By locator, int index)
    {
        CustomVerificationLibrary.verifyCheckboxIsChecked( locator, index);
    }

    public void verifyCheckboxIsNotChecked(By locator)
    {
        CustomVerificationLibrary.verifyCheckboxIsNotChecked( locator);
    }

    public void verifyCheckboxIsNotChecked(By locator, int index)
    {
        CustomVerificationLibrary.verifyCheckboxIsNotChecked( locator,  index);
    }

    public void verifyTextExist(String parentString, String subString)
    {
        CustomVerificationLibrary.verifyTextExist( parentString,  subString);
    }

    public void verifyTextDoesNotExist(String parentString, String subString)
    {
        CustomVerificationLibrary.verifyTextDoesNotExist( parentString,  subString);
    }

    public void verifyText(String actual, String expected)
    {
        CustomVerificationLibrary.verifyText( actual,  expected);
    }

    public void verifyElementPresenceInsideParentElement(By parentElementLocator, By childElementLocator)
    {
        CustomVerificationLibrary.verifyElementPresenceInsideParentElement( parentElementLocator,  childElementLocator);
    }

    public void verifyElementAbsenceInsideParentElement(By parentElementLocator, By childElementLocator)
    {
        CustomVerificationLibrary.verifyElementAbsenceInsideParentElement( parentElementLocator,  childElementLocator);
    }

    public void verifyElementOccurrenceCount(By parentElementLocator, By childElementLocator, int countForChildElement)
    {
        CustomVerificationLibrary.verifyElementOccurenceCount( parentElementLocator,  childElementLocator,  countForChildElement);
    }

    public void verifyCookieValue(String cookieName, String expected)
    {
        CustomVerificationLibrary.verifyCookieValue( cookieName,  expected);
    }

    public void verifyCookieExpiry(String CookieName, String expectedDaysToExpire)
    {
        CustomVerificationLibrary.verifyCookieExpiry( CookieName,  expectedDaysToExpire);
    }

    public void verifyCondition(boolean expression, String verificationDescription)
    {
        CustomVerificationLibrary.verifyCondition( expression,  verificationDescription);
    }

    public void verifyGreater(String expectedGreater, String expectedLess, String verificationDescription)
    {
        CustomVerificationLibrary.verifyGreater( expectedGreater,  expectedLess,  verificationDescription);
    }

    public void verifyEqualNumerics(String valueA, String valueB, String verificationDescription)
    {
        CustomVerificationLibrary.verifyEqualNumerics( valueA,  valueB,  verificationDescription);
    }

    public void verifyGreaterOrEqual(String expectedGreaterOrEqual, String expectedLessOrEqual, String verificationDescription)
    {
        CustomVerificationLibrary.verifyGreaterOrEqual( expectedGreaterOrEqual,  expectedLessOrEqual,  verificationDescription);
    }

    public void verifyGreater(int expectedGreater, int expectedLess, String verificationDescription)
    {
        CustomVerificationLibrary.verifyGreater( expectedGreater,  expectedLess,  verificationDescription);
    }

    public void verifyEqual(int valueA, int valueB, String verificationDescription)
    {
        CustomVerificationLibrary.verifyEqual( valueA,  valueB,  verificationDescription);
    }

    public void verifyEqual(double valueA, double valueB, String verificationDescription)
    {
        CustomVerificationLibrary.verifyEqual( valueA,  valueB,  verificationDescription);
    }

    public void verifyGreaterOrEqual(int expectedGreaterOrEqual, int expectedLessOrEqual, String verificationDescription)
    {
        CustomVerificationLibrary.verifyGreaterOrEqual( expectedGreaterOrEqual,  expectedLessOrEqual,  verificationDescription);
    }

    public void verifyIsNotEqual(String valueA, String valueB, boolean enableCaseSensitiveVerification, String verificationDescription)
    {
        CustomVerificationLibrary.verifyIsNotEqual( valueA,  valueB,  enableCaseSensitiveVerification,  verificationDescription);
    }

    public void verifyIsEqual(String valueA, String valueB, boolean enableCaseSensitiveVerification, String verificationDescription)
    {
        CustomVerificationLibrary.verifyIsEqual( valueA,  valueB,  enableCaseSensitiveVerification,  verificationDescription);
    }

    public void verifyIsNotEqual(int valueA, int valueB, String verificationDescription)
    {
        CustomVerificationLibrary.verifyIsNotEqual( valueA,  valueB,  verificationDescription);
    }

    public void verifyCurrentUrl(String URL)
    {
        CustomVerificationLibrary.verifyCurrentUrl( URL);
    }

    public void verifyPageTitle(String expectedTitle)
    {
        CustomVerificationLibrary.verifyPageTitle( expectedTitle);
    }

    public void verifyCSSValue(By locator, String cssProp, String expectedValue)
    {
        CustomVerificationLibrary.verifyCSSValue( locator,  cssProp,  expectedValue);
    }



    /***************************** Excel Utilities Methods *******************************/

    protected String readDataFromExcel(String Path, String SheetName, int rowNum, int colNum)
    {
        return ExcelLibrary.readDataFromExcel( Path,  SheetName,  rowNum,  colNum);
    }

    protected void writeDataToExcel(String Path, String SheetName, int rowNum, int colNum, String value)
    {
         ExcelLibrary.writeDataToExcel( Path,  SheetName,  rowNum,  colNum, value);
    }

    protected void writeMultipleDataToExcel(String filePath, String sheetName, int rowNum, int colNum, List<String> value)
    {
        ExcelLibrary.writeMultipleDataToExcel(filePath, sheetName,  rowNum, colNum, value);
    }

    protected int getLastRowNum(String Path, String SheetName)
    {
        return ExcelLibrary.getLastRowNum( Path,  SheetName);
    }

    protected void appendValueToColumn(String filePath, String sheetName, int column, String appendValue) {
        ExcelLibrary.appendValueToColumn(filePath, sheetName, column, appendValue);
    }

    protected boolean verifyExcelHeaders(String filePath, List<String> expectedHeaders, String sheetName)
    {
        return ExcelLibrary.verifyExcelHeaders(filePath,expectedHeaders,sheetName);
    }

    protected List<Map<String, String>> readExcelTable(String excelFilePath, String sheetName)
    {
        return ExcelLibrary.readExcelTable(excelFilePath, sheetName);
    }

    protected  Map<String, String> readExcelTable(String excelFilePath, String sheetName, boolean skipHeaders)
    {
        return ExcelLibrary.readExcelTable(excelFilePath, sheetName, skipHeaders);
    }

    protected void compareExcelDataAndList(List<Map<String, String>> excelData,  List<WebElement> fieldElement, List<WebElement> valueElement)
    {
        ExcelLibrary.compareExcelDataAndList(excelData,  fieldElement,valueElement);
    }

    protected Map<String, String> extractWebTableData(By locator, boolean skipheader)
    {
        return ExcelLibrary.extractWebTableData(locator, skipheader);
    }


    protected void compareExcelAndWebTable(Map<String, String> excelData, Map<String, String> webTableData)
    {
        ExcelLibrary.compareExcelAndWebTable(excelData, webTableData);
    }

    /***************************** CustomSortingVerificationLibrary **********************/

    public static void verifyListAscendingOrder(List<String> list)
    {
        CustomSortingVerificationLibrary.verifyListAscendingOrder(list);
    }

    public static void verifyListDescendingOrder(List<String> list)
    {
        CustomSortingVerificationLibrary.verifyListDescendingOrder(list);
    }
    public static void verifyListsEqualIgnoringOrder(List<String> list1, List<String> list2)
    {
        CustomSortingVerificationLibrary.verifyListsEqualIgnoringOrder(list1,list2);
    }

    /***************************** BrowserUtilities Library **********************/

    protected static void openNewTab()
    {
        BrowserUtilities.openNewTab();
    }

    protected static void openNewWindow()
    {
        BrowserUtilities.openNewWindow();
    }

    protected static void switchToTab(int tabIndexPosition)
    {
        BrowserUtilities.switchToTab(tabIndexPosition);
    }

    protected static void closeTab(int tabIndexPosition)
    {
        BrowserUtilities.closeTab(tabIndexPosition);
    }

    protected static void focusCurrentWindow()
    {
        BrowserUtilities.focusCurrentWindow();
    }

    @SneakyThrows
    protected static void refreshPage()
    {
        BrowserUtilities.refreshPage();
    }

    @SneakyThrows
    protected static void duplicateTab()
    {
        BrowserUtilities.duplicateTab();
    }

    protected static void deleteCookies()
    {
        //BrowserUtilities.deleteCookies();
    }

    protected void deleteCookie(Cookie cookie)
    {
        BrowserUtilities.deleteCookie(cookie);
    }

    protected Set<Cookie> getCookies()
    {
        return BrowserUtilities.getCookies();
    }

    protected Cookie getCookiesNamed(String cookie)
    {
        return BrowserUtilities.getCookiesNamed(cookie);
    }



    /***************************** RandomUtilities Library **********************/

    protected String generateRandomString(int length)
    {
        return RandomUtilities.generateRandomString(length);
    }

    protected int generateRandomNumber(int min, int max)
    {
        return RandomUtilities.generateRandomNumber(min, max);
    }

    protected static String generateAlphaNumeric(int minLengthInclusive, int maxLengthExclusive)
    {
        return RandomUtilities.generateAlphaNumeric(minLengthInclusive, maxLengthExclusive);
    }



    /********************* Common Utility Class *********************/

    protected static String getCurrentDate(String expectedFormat)
    {
        return DateLibrary.getCurrentDate(expectedFormat);
    }

    protected static Date convertStringToDate(String dateString, String expectedFormat)
    {
        return DateLibrary.convertStringToDate(dateString, expectedFormat);
    }

    protected static LocalDate convertStringToLocalDate(String dateString, String expectedFormat)
    {
        return DateLibrary.convertStringToLocalDate(dateString, expectedFormat);
    }

    protected static String convertDateToString(Date date, String format)
    {
        return DateLibrary.convertDateToString(date, format);
    }

    protected static boolean checkIfDatesAreEqual(Date date1, Date date2)
    {
        return DateLibrary.checkIfDatesAreEqual(date1, date2);
    }

    protected static boolean checkIfDatesAreEqual(String date1, String date2)
    {
        return DateLibrary.checkIfDatesAreEqual(date1, date2);
    }
    protected static long getDifferenceInDates(LocalDate date1, LocalDate date2)
    {
        return DateLibrary.getDifferenceInDates(date1, date2);
    }

    protected static long getDifferenceInDates(String date1, String date2)
    {
        return DateLibrary.getDifferenceInDates(date1, date2);
    }

}


