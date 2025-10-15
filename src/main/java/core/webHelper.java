package core;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

/** <p>Three category of the Methods are included into this Interface
 * <ol><b>Browser Window</b>
 * <li>public void CloseAllWindows()</li>
 * <li>public void SwitchToNextTab()</li>
 * <li>public void GetIframeLoaded(String framlocator, String l1)</li>
 * <li>public void WaitforPageToBeReady()</li>
 * <li>public String GetTitle()</li>
 * <li>public void OpenURL(String environment)</li>
 * <li>public void OpenURLV2(String environment)</li>
 * <li>public void GetURL(String URL)</li>
 * <li>public void PageRefresh()</li>
 * <li>public void CurrentURL()</li>
 * <li>public String CaptureScreenShotForElement(WebElement ele)</li>
 * <li>public static String CaptureFullScreenShot(WebDriver driver)</li></ol>
 * <ol><b>Elements</b>
 * <li>public void WaitForElementToBeClickable(String locator)</li>
 * <li>public WebElement GetWebelement(String locator)</li>
 * <li>public WebElement GetWebelementV3(String locator)</li>
 * <li>public WebElement GetWebelementV2(String locator)</li>
 * <li>public String GetAttribute(WebElement el,String attributename) </li>
 * <li>public int GetWebElementsCount(String locator)</li>
 * </ol>
 * <ol><b>Waits</b>
 * <li>public void WaitAndClickForWorkItemsPresent(String locator, int timeout)</li>
 * <li>public void WaitAndClickForOrderCompleted(String locator, int timeout)</li>
 * <li>public void WaitAndClickForOrderStarted(String locator, int timeout)</li>
 * <li>public void WaitAndForElementDisplayed(String locator)</li>
 * <li>public void WaitAndForElementDisplay(String locator, int timeout)</li>
 * <li>public void WaitTillElementDisapear(String locator, int timeout)</li>
 * <li>public void WaitandForElementDisplayV2(String locator, int timeout)</li>
 * <li>public void WaitForpageload()</li>
 * <li>public void WaitForpageloadmask()</li>
 * <li>public void WaitForpageloadExplore()</li>
 * <li>public void WaitForpagenavigated(int timeout)</li>
 * </ol>
 * <ol><b>Actions</b>
 * <li></li></ol>
 * <ol><b>Application Specific</b>
 * <li></li></ol>
 * </p>
*/


public interface webHelper {


    WebDriver getDriver();
    /** Close all the Child Windows if any open excepts the parent Window/Default window.
	 * Nothing to be returned here.
	*/
    void CloseAllWindows();
	/** Switch to the last open browser tab. If no addition tab opened, Driver will be remain on the Default Tab
	 * Nothing to be returned here.
	*/


    void SwitchToLastTab();
	/** Wait till IFrame/Frame on the page get loaded
	 * Nothing to be returned here.
	 * @param framelocator Web element locator for the iframe/frame. For example: CSS selector/XPATH etc.
	*/
    void GetIframeLoaded(String framelocator);
	/** Waiting for the Page to be ready. This method is looking of the Ready State of the Current Page using Java script commands.
	 * Nothing to be returned here.
	 * @throws Exception 
	*/
    void WaitforPageToBeReady() throws Exception;
	/** Get the Title of the current opened page.
	 * @return Page title as String.
	*/
    String GetTitle();
	/** Open the URL using get method of WebDriver. The URL read from Configuration file as Key value pair.
	 * @param environment Name of the Environment mentioned into the Configuration file.
	 * Nothing to be returned here.
	*/
    void OpenURL(String environment);
	/** Open the URL using get method of WebDriver. This is the Updated version OpenURL which includes timeout for the page to be loaded
	 * @param environment Name of the Environment mentioned into the Configuration file.
	 * Nothing to be returned here.
	 * @throws Exception 
	*/
    void OpenURLV2(String environment) throws Exception;
	/** Open the URL using get method of WebDriver. Here the URL can be passed as string at the time of calling this function
	 * @param URL Complete Web address(URL)
	 * Nothing to be returned here.
	*/
    void GetURL(String URL);
	/** Refreshes/Reload the current page
	 * Nothing to be returned here.
	 * @throws Exception 
	*/
    void PageRefresh() throws Exception;
	/**This method returns the Current Page URL
	 * @return the URL of the Current page as String
	*/
    String CurrentURL();
	/** Capture the Screenshot of Web Element and Returned as Base64 String.
	 * @param ele Web Element which Screenshot needs to be captured
	 * @return the URL of the Current page as String
	*/
    String CaptureScreenShotForElement(WebElement ele);
	/** Capture the Screenshot ofFull screen even with Scroll on the page and Returned as Base64 String.
	 * @param driver WebDriver instance active.
	 * @return the URL of the Current page as String
	*/
    String CaptureFullScreenShot(WebDriver driver);
	/** Click on an Web element. 
	 * @param ele Web element instance need to be clicked on
	 * Nothing to be returned here.
	 * @throws Exception 
	*/
    void Clickon(WebElement ele) throws Exception;
	
	/** Enter the value in an input field. 
	 * @param el Web element instance need to be be enter
	 * @param value is the value which needs to be entered i the input field
	 * Nothing to be returned here.
	 * @throws IOException 
	 * @throws InterruptedException 
	 *
	*/
    void SendKeys(WebElement el, String value) throws InterruptedException, IOException;
	/** Verify the page title
	 * @param Expectedtitle expected title should be of the current web page
	 * Nothing to be returned here.
	 * @throws IOException 
	 * @throws InterruptedException 
	 *
	*/
    void VerifyTitle(String Expectedtitle);
	/** Verify the visual text for the element
	 * @param el Webelement under test
	 * @param Expectedtext expected text should displayed
	 * Nothing to be returned here.
	 * @throws IOException 
	 * @throws InterruptedException 
	 *
	*/
    void VerifyText(WebElement el, String Expectedtext);
	/** Get the Web elemment based on the locator
	 * @param locator Locotor of the element
	 * @return Webelement instance
	 * Nothing to be returned here.
	 * @throws IOException 
	 * @throws InterruptedException 
	 *
	*/
    WebElement getwebelement(String locator) throws InterruptedException;

	String getAdsOnPage(String string);
	String Getattribute(WebElement el, String attributename);
	boolean validateDataTrackResearchPage();
	String getText(WebElement wl);
	void javascriptButtonClick(WebElement webElement);
	boolean isElementPresent(String locator);
	void SendkeaboardKeys(WebElement el, Keys k) throws InterruptedException;
	String pollCurrentTime(String className, String timeStamp, String timeOut) throws InterruptedException;
	void Moveon(WebElement el);
	void HandleAngularwait();
	void HandleAngularmain();

	void waitforElementtobeclickable(String locator) throws InterruptedException;
	void verifyElementToBePresent(String Locators) throws InterruptedException;
	void verifyElementNotToBePresent(String Locators) throws InterruptedException;
	void waitForCompletePageLoad();
	void waitForElementLoad(int x);
	void waitforElementNotToBeVisible(String wl);
	void waitforFrameToBeAvailableAndSwitchToIt(String frameName);
	void waitFortTextToBePresentInElement(WebElement wl, String Textanme);
	void waitUntilTheElementIsVisible(WebElement wl);
	void waitUntilTheElementIsNotSelected(WebElement wl);
	void waitUntilTheAttributeValueChangeToAnElement(WebElement wl, String AttributeName, String AttributeValue);
	void waitUntilAlertNotDisplaying() throws InterruptedException;
	void waitUntilTheAlertIsDisplaying();
	void waitUntilANewWindowOpen();
	void uncheckTheCheckBox(WebElement el);
	void verifyCurrentURL(String ExpectedURL);
	boolean verifyAlertPresent();
	String returnAlertMessage();
	void waitUntilAllImagesAreLoaded() ;
	boolean returnListHasSomeOptionSelected(WebElement wl);
	int returnListHasNumberOfOptions(WebElement wl);
	boolean returnListHasOptionWithValueSelected(WebElement wl, String Value);
	boolean returnAllowSelectionOfMultipleOptionFlag(WebElement wl);
	boolean returnElementEmptyValueFlag(WebElement el);
	String returnElementTagName(WebElement el);

	String returnElementAttrubuteValue(WebElement el, String AttributeName);
	String returnCssPropertyName(WebElement el, String Propertyname);
	boolean ReturnElementCheckStatus(WebElement el);
	String returnValueSelectedInList(WebElement wl);
	int returnOptionsCountsInList(WebElement wl);
	int getwebelementscount(String locator) throws InterruptedException;
	void selectOptionByValue(WebElement el, String value);
	void selectOptionByText(WebElement el, String Text);
	void selectOptionByIndex(WebElement el, String index);

    void ActionWithElement(WebElement el, String ActionType);
	void DragAndDropElement(WebElement el, WebElement el1);
	void SwitchWindowTitled(String windowTitle);
	void SwitchWindowByIndex(String index);
	void SwitchToParentWindod();

	void SwitchToParentPage();
	void CloseCurrentWindows();
	void CloseWindowByTitle(String windowTitle);
	void CloseWindowByIndex(String index);
	void GOto(String url);
	void SwitchToParentFrame();
	void SwitchToFrame(String FrameName);
	void SwitchToFrameByIndex(String index);
	void SwitchToFrameByElement(WebElement wl);

	void DeleteCookiesWithName(String Cookiesname);
	void DeleteAllCookies();
	void Refresh();
	void Forward();
	void Back();
	void AcceptAlert();
	void CancelAlert();
	void ClickonIfElementVisible(WebElement el) throws Exception;
	void ClearAttributeValue(WebElement element);
	void ClearText(WebElement element);
	boolean verifyLinksOpenNewTanOrWindows(WebElement element);

	void setAttributeUsingJS(WebElement element, String attName, String attValue);
	boolean VerifyElementValueLessThan(WebElement element, int ExpValue);
	boolean VerifyElementValueGreaterThan(WebElement element, int ExpValue);
	boolean VerifyElementValueLessThanOrEqualTo(WebElement element, int ExpValue);
	boolean VerifyElementValueGreaterThanOrEqualTo(WebElement element, int ExpValue);
	void SendKeysUsingJS(WebElement el, String value) throws InterruptedException, IOException;

	void ClickOnElementToOpenInNewWindowAndSwitchToIt(WebElement element);
	void SendKeyBoardKeysUsingActions(Keys key) throws Exception ;
	void PressShiftDelete() throws Exception;
	void CheckTheCheckBox(WebElement el);
	WebElement getWebElementWithRelative(String locator, WebElement wl, String relative) throws InterruptedException;

	void ScrollIntoView(WebElement el) throws InterruptedException;
	boolean returnElementEmptyTextFlag(WebElement el);
	void waitforTitleContains(String Title);

	void safeJavaScriptClick(WebElement element) throws Exception;
	WebElement getWebElementById(String locator) throws InterruptedException;
	void SwitchToNextTab(int i);
	void EnterTextUsingAction() throws InterruptedException;
	public void EnterTextUsingActions(String s);
	public void move_to_element() throws InterruptedException;



	public void waitForElementToBePresent(String xpath, int timeoutInSeconds) throws InterruptedException;
	void Scrolldown() throws InterruptedException;

	public void fillform(List<Map<String, String>> fields) throws InterruptedException;

    void hoverOnElement(WebElement element);
        // ...your existing methods...
        // New, non-breaking helpers
        WebElement waitUntilVisible(By by, int timeoutSeconds);
        WebElement waitUntilPresent(By by, int timeoutSeconds);
        String waitAndGetText(By by, int timeoutSeconds);
        void assertTextEquals(By by, String expected, int timeoutSeconds);

}


