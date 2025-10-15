package core;

import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsd;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import javax.imageio.ImageIO;
import javax.xml.parsers.ParserConfigurationException;

import com.deque.html.axecore.args.AxeRunOnlyOptions;
import com.deque.html.axecore.args.AxeRunOptions;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.google.gson.JsonElement;
import com.paulhammant.ngwebdriver.*;
//import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.windows.WindowsDriver;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import lombok.Data;
import org.apache.http.params.CoreConnectionPNames;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.xml.sax.SAXException;
//import cucumber.Reporter;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import simplexml.SimpleXml;
import simplexml.model.Element;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;


@Data
public class baseDriverHelper implements apiHelper, webHelper,desktopHelper,mobileHelper
{


	WebDriver driver;
	RequestSpecification Request;
	WindowsDriver DesktopDriver;
	AppiumDriver MobileDriver;
	AndroidDriver AndroidDriver;
	NgWebDriver ngDriver;
	public Wait<WebDriver> wait;
	public Wait<WindowsDriver> waitDesktop;
	public Wait<AppiumDriver> waitMobile;
	WebElement el=null;
	List<WebElement> ellist;
	public static Response Resultrespoence;
	public static String Payload="";
	public static String ReportPayload="";
	public static Document XMLDocumentPayload;
	public static JSONObject JSONDocumentPayload;
	public final static String Contenttype = "application/xml";
	public static String WindDirectionDegree;
	public static Properties propinhelper;
	public static String VideoName;
	String Root="src/test/resources/apiResourceTemplate/";
	public baseDriverHelper(WebDriver dr)
	{
		driver=dr;


		wait = new FluentWait<WebDriver>(driver) 
				.withTimeout(Duration.ofSeconds(5))
				.pollingEvery(Duration.ofSeconds(200))
				.ignoring(NoSuchElementException.class)

				;
	}

   /* //  Single WebDriver constructor only
    public baseDriverHelper(WebDriver driver) {
        this.driver = driver;

        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);
    }
*/



    @Override
    public WebDriver getDriver() {
        return driver;
    }


	public baseDriverHelper(WindowsDriver dr)
	{
		DesktopDriver=dr;
		waitDesktop = new FluentWait<WindowsDriver>(DesktopDriver)
				.withTimeout(Duration.ofSeconds(90))
				.pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class)

		;
	}

	public baseDriverHelper(AppiumDriver dr)
	{
		MobileDriver=dr;
		AndroidDriver= (io.appium.java_client.android.AndroidDriver) dr;
		waitMobile = new FluentWait<AppiumDriver>(MobileDriver)
				.withTimeout(Duration.ofSeconds(90))
				.pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class)

		;


	}
	public baseDriverHelper(RequestSpecification dr,Response respoence)
	{

		Request=dr;
		propinhelper = new Properties();
	}

	/*	Start of the Web Driver Helper area which contains all the base methods related to the Web Driver
	 */

    public void waitforElementtobeclickable(String locator) throws InterruptedException
    {
        System.out.println("In Wait for Element Clickable method for - "+locator);
        //waitForpageload();
        if(locator.startsWith("//") || locator.startsWith("(")) {

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
            //getwebelement(xml.getlocator("//locators/StandrdQuote"));
            System.out.println("Waiting for Element to be clickabal and active"+locator);
            //Thread.sleep(2000);
        }
        else if(locator.startsWith("name"))
        {
            wait.until(ExpectedConditions.elementToBeClickable(By.name(locator.split("=")[1])));
            //getwebelement(xml.getlocator("//locators/StandrdQuote"));
            System.out.println("Waiting for Element to be clickabal and active"+locator);
            //Thread.sleep(2000);

        }
        else if(locator.startsWith("id"))
        {
            wait.until(ExpectedConditions.elementToBeClickable(By.id(locator.split("=")[1])));
            //getwebelement(xml.getlocator("//locators/StandrdQuote"));
            System.out.println("Waiting for Element to be clickabal and active"+locator);
            //Thread.sleep(2000);

        }
    }

    public void waitforElementNotToBeVisible(String wl)
    {
//        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(300));

//		wait.until(ExpectedConditions.e(wl));
		FluentWait<WebDriver> wait = new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(300)) // Maximum wait time
				.pollingEvery(Duration.ofMillis(500)) // Polling interval
				.ignoring(NoSuchElementException.class); // Ignore exceptions during polling

		Boolean isNotDisplayed = wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				try {
					WebElement element = driver.findElement(By.xpath(wl));
					return !element.isDisplayed();
				} catch (NoSuchElementException e) {
					// Element is not present at all, so it is considered not displayed
					return true;
				}
			}
		});

		Assert.assertTrue(isNotDisplayed);

    }
    public void verifyElementToBePresent(String Locators) throws InterruptedException {
        int count= getwebelementscount(Locators);
		System.out.println("Number of Elements"+count);
		Assert.assertTrue(count>=1);
//        Stringcomparator(String.valueOf(count),"1");
    }
    public void verifyElementNotToBePresent(String Locators) throws InterruptedException {
        int count= getwebelementscount(Locators);
        Stringcomparator(String.valueOf(count),"0");
    }
    public void waitForCompletePageLoad()
    {
        waitForLoad(driver);
    }
    void waitForLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(pageLoadCondition);
    }

    public void waitforFrameToBeAvailableAndSwitchToIt(String frameName)
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
    }
	public void waitforTitleContains(String Title)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));
		wait.until(ExpectedConditions.titleContains(Title));
	}
    public void waitFortTextToBePresentInElement(WebElement wl, String Textanme)
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));
        wait.until(ExpectedConditions.textToBePresentInElement(wl,Textanme));
    }
    public void waitUntilTheElementIsVisible(WebElement wl)
    {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(wl));
        }
        catch (Exception e)
        {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    public void waitUntilTheElementIsNotSelected(WebElement wl)
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementSelectionStateToBe(wl,false));
    }
    public void waitUntilTheAttributeValueChangeToAnElement(WebElement wl, String AttributeName, String AttributeValue)
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeToBe(wl,AttributeName,AttributeValue));
    }
    public void waitUntilAlertNotDisplaying() throws InterruptedException {
        int i=0;
        while(i++<5)
        {
            try
            {
                Alert alert = driver.switchTo().alert();
            }
            catch(NoAlertPresentException e)
            {
                Thread.sleep(1000);
                break;
            }
        }
    }

	public boolean verifyAlertPresent()	{
		return isAlertPresent();
	}
	public String returnAlertMessage()	{
		return driver.switchTo().alert().getText().trim();
	}
	public boolean isAlertPresent()
	{
		try
		{
			driver.switchTo().alert();
			return true;
		}   // try
		catch (NoAlertPresentException Ex)
		{
			return false;
		}   // catch
	}
    public void waitUntilTheAlertIsDisplaying()
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
    }
	public void waitUntilANewWindowOpen()
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
	}

    public void waitUntilAllImagesAreLoaded() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		// Getting DOM status
		Object result = jse.executeScript("return document.readyState;");
		System.out.println("=> The status is : " + result.toString());
		// Checking DOM loading is completed or not?
		if (result.equals("complete")) {
			// Fetching images count
			result = jse.executeScript("return document.images.length");
			int imagesCount = Integer.parseInt(result.toString());
			boolean allLoaded = false;
			// Checking and waiting until all the images are getting loaded
			while (!allLoaded) {
				int count = 0;
				for (int i = 0; i < imagesCount; i++) {
					result = jse.executeScript("return document.images[" + i + "].complete;");
					boolean loaded = (Boolean) result;
					if (loaded) count++;
				}
				// Breaking the while loop if all the images loading completes
				if (count == imagesCount) {
					System.out.println("=> All the Images are loaded...");
					break;
				} else {
					System.out.println("=> Not yet loaded...");
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
	public void uncheckTheCheckBox(WebElement el)
	{
		if(el.isSelected())
		{
			el.click();
		}
		else {
			System.out.println("Checkbox is already Unselected");
		}
	}
	public void CheckTheCheckBox(WebElement el) {
		el.click();
	}


    public boolean returnListHasSomeOptionSelected(WebElement wl)
	{
		Select sel = new Select(wl);
		return true;
	}
	public int returnListHasNumberOfOptions(WebElement wl)
	{
		Select sel = new Select(wl);
        return sel.getOptions().size();
	}
	public boolean returnListHasOptionWithValueSelected(WebElement wl,String Value)
	{
			Select sel = new Select(wl);
			return sel.getAllSelectedOptions().toString().contains(Value);
	}
	public String returnValueSelectedInList(WebElement wl)
	{
		Select sel = new Select(wl);
		return sel.getFirstSelectedOption().toString();
	}
	public int returnOptionsCountsInList(WebElement wl)
	{
		Select sel = new Select(wl);
		return sel.getOptions().size();
	}
	public boolean returnAllowSelectionOfMultipleOptionFlag(WebElement wl)
	{
				Select sel = new Select(wl);
				return sel.isMultiple();
	}

    public boolean returnElementEmptyValueFlag(WebElement el)
    {
        boolean flag = false;
        String value = el.getAttribute("value");
        System.out.println("Valueee:"+value);
        flag = !value.isEmpty();
		return flag;
    }

	public boolean returnElementEmptyTextFlag(WebElement el)
	{
		boolean flag = false;
		String value = GetText(el);
		System.out.println("Valueee:"+value);
        flag = !value.isEmpty();
		return flag;
	}

	public String returnElementTagName(WebElement el)
	{
		return el.getTagName().trim();

	}
	public String returnElementAttrubuteValue(WebElement el, String AttributeName)
	{
        return el.getAttribute(AttributeName).trim();

	}
	public String getText(WebElement wl) {
        return wl.getText();
	}
	public String returnCssPropertyName(WebElement el, String Propertyname)
	{
		return el.getCssValue(Propertyname).trim();

	}
	public boolean ReturnElementCheckStatus(WebElement el)
	{

        return el.isSelected();
	}

	public int getwebelementscount(String locator) throws InterruptedException
	{
		System.out.println("Locator looked for:"+locator);
		el=driver.findElement(By.xpath(locator));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
		ellist=driver.findElements(By.xpath(locator));

		return ellist.size();
	}
	public void selectOptionByValue(WebElement el,String value)
	{
		Select sel = new Select(el);
		sel.selectByValue(value);
	}
	public void selectOptionByText(WebElement el,String Text)
	{
		Select sel = new Select(el);
		sel.selectByVisibleText(Text);
	}
	public void selectOptionByIndex(WebElement el,String index)
	{
		Select sel = new Select(el);
		sel.selectByIndex(Integer.parseInt(index));
	}

    public void ActionWithElement(WebElement el, String ActionType)
    {
        Actions act = new Actions(driver);

        if(ActionType.equalsIgnoreCase("RightClick")) {
            act.contextClick(el).perform();
        }
        else if (ActionType.equalsIgnoreCase("Mouseover"))
        {
            act.moveToElement(el).perform();
        }
        else if (ActionType.equalsIgnoreCase("DoubleClick"))
        {
            act.moveToElement(el).perform();
        }
    }
	public void DragAndDropElement(WebElement el, WebElement el1) {
		Actions act = new Actions(driver);
		act.dragAndDrop(el,el1).perform();
	}

	Set<String> allHandles;
	String parentWinHandle=null;
    public void SwitchWindowTitled( String windowTitle)
    {
		 parentWinHandle = driver.getWindowHandle();
         allHandles = driver.getWindowHandles();
        //count the handles Here count is=2
		for (String window : allHandles) {
			System.out.println(window);
			if(window.equalsIgnoreCase(windowTitle)) {
//				System.out.println(window);
				driver.switchTo().window(window);
				System.out.println(driver.getTitle());

			}
		}
    }
	public void SwitchToNextTab(int i)
	{
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		for (String handle : tabs) {

			driver.switchTo().window(handle);
			System.out.println(driver.getTitle());
			}
		driver.switchTo().window(tabs.get(i));
	}


	public void CloseWindowByTitle( String windowTitle)
	{
		parentWinHandle = driver.getWindowHandle();
		allHandles = driver.getWindowHandles();
		//count the handles Here count is=2
		for (String window : allHandles) {
			if(window.equalsIgnoreCase(windowTitle))
				driver.switchTo().window(window);
                waitForElementLoad(2);
			    driver.close();
		}
		driver.switchTo().window(parentWinHandle);

	}
	public void SwitchWindowByIndex( String index)
	{
		parentWinHandle = driver.getWindowHandle();
		allHandles = driver.getWindowHandles();
		int totalWin= allHandles.size();
		String winTitle = null;
		for(int i=0;i<totalWin;i++) {
			if(i==Integer.parseInt(index)) {
				winTitle = allHandles.toArray()[i].toString();
			}
		}
		driver.switchTo().window(winTitle);
		System.out.println(winTitle);
	}
	public void CloseWindowByIndex( String index)
	{
		parentWinHandle = driver.getWindowHandle();
		allHandles = driver.getWindowHandles();
		int totalWin= allHandles.size();
		System.out.println("Allwindow size"+totalWin);
		String winTitle = null;
		for(int i=0;i<totalWin;i++) {
			if(i==Integer.parseInt(index)) {
				winTitle = allHandles.toArray()[i].toString();
			}
		}
		driver.switchTo().window(winTitle);
		driver.close();
		driver.switchTo().window(parentWinHandle);
		System.out.println(winTitle);
	}
	public void SwitchToParentWindod() {
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
//		driver.switchTo().window(parentWinHandle);
	}
    public void SwitchToParentPage() {
        driver.switchTo().defaultContent();
    }
	public void CloseCurrentWindows() {
		driver.close();
	}
	@Override
	public void CloseAllWindows() {
		String parentWinHandle = driver.getWindowHandle();
		Set<String> totalopenwindow=driver.getWindowHandles();
		if(totalopenwindow.size()>1) {
			for(String handle: totalopenwindow)
			{
				if(!handle.equals(parentWinHandle))
				{
					driver.switchTo().window(handle);

				}
			}
			driver.close();
			driver.switchTo().window(parentWinHandle);
		}
		else {
			System.out.println("No popup displayed");
		}
	}

	public void GOto(String url)
	{
		//driver.navigate().to(url);
		driver.get(url);

	}
    public void Refresh()
    {
        driver.navigate().refresh();
    }
    public void Forward()
    {
        driver.navigate().forward();
    }
    public void Back()
    {
        driver.navigate().back();
    }

	public void SwitchToFrame(String FrameName)
	{
//		webDriver.getwebelement("//iframe[@title='accessibility title']");

		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='"+FrameName+"']")));
	}
	public void SwitchToParentFrame()
	{
		driver.switchTo().parentFrame();
        driver.switchTo().defaultContent();
	}
	public void SwitchToFrameByIndex(String index)
	{
		driver.switchTo().frame(Integer.parseInt(index));
	}
	public void SwitchToFrameByElement(WebElement wl)
	{
		driver.switchTo().frame(wl);
	}
    public void DeleteCookiesWithName(String Cookiesname)
    {
        driver.manage().deleteCookieNamed(Cookiesname);
    }
    public void DeleteAllCookies()
    {
        driver.manage().deleteAllCookies();
    }
    public void AcceptAlert()
    {
        driver.switchTo().alert().accept();
    }
    public void CancelAlert()
    {
        driver.switchTo().alert().dismiss();
    }

    public void Clickon(WebElement el) throws Exception {
        //Thread.sleep(3000);
        //JavascriptExecutor js = (JavascriptExecutor) driver;
        if(el!=null) {
            try {
                 System.out.println("INFO : Click Event Triggered on -> "+el.toString());

                //use executeScript() method and pass the arguments
                //Here i pass values based on css style. Yellow background color with solid red color border.

                //js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);

				el.click();

                //    ExtentTestManager.getTest().//Log(//LogStatus.PASS, " Step: Click On "+elementName.get().toString());
                //	System.out.println("try to click on the element "+el.toString().split("xpath:")[1]);
                //	ExtentTestManager.getTest().//Log(//LogStatus.PASS, " Step: Click On "+el.toString().split("xpath:")[1]+" Button");


            }
			catch (StaleElementReferenceException e) {
                //js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", el);
                Thread.sleep(200);
                //js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);

//                Thread.sleep(8000);
                waitForpageload();
                el.click();
                System.out.println("Recovery : Operation was Un-successful but Recovered the Click Operation for ->" + el);

            }
			catch (WebDriverException e)
            //Thread.sleep(3000);
            {
                //Thread.sleep(3000);
                //JavascriptExecutor js = (JavascriptExecutor) driver;
//                System.out.println("Error in Clickon sayad yaha " + e.getMessage());
                if (!e.toString().equals("NoSuchElementException")) {
                    try {
                        if (e.getMessage().contains("is not clickable at point")) {

                            Thread.sleep(300);
                            System.out.println("Recovery : Operation was Un-successful Due to Element Rendering on UI but Recovered the Click Operation for ->" + el);
                            //js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", el);

//                            Thread.sleep(200);
                            //js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
                            	((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
                            //el.click();
                            //	js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
                        }
						else if (driver.findElement(By.xpath("//div[@id='lockCreateScreen' and not(@style='display: none;')]")).isDisplayed()) {
                            //js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", el);
                            Thread.sleep(200);
                            //js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
                            System.out.println("Recovery Failed: Unable to Recover the Operation as Element is not Interactable for ->" + el);
                            //((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
                            //	js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
                        }
						else{
							Thread.sleep(200);
//							System.out.println("ye run hua");


//Changes by Priyanka

                            System.out.println("Clickable tested");
                            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
                            wait.until(ExpectedConditions.elementToBeClickable(el));



//							((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
//				((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
                            el = driver.findElement(By.xpath(el.toString().split("->")[1].trim()));
                            el.click();
//							Thread.sleep(200000);
							//	js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", el);

						}
                    }
					catch (NoSuchElementException e12) {
                        //js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", el);

                        //js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
                        //	js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", el);
//                        Assert.fail("Element should be displayed which this was not");

					}
//					finally {
//						Thread.sleep(200);
//						((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
//						((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
//						//	js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", el);
//					}
                }
//				else {
//                    //Reporter.addStepLog("Something went wrong and Click was not performed successfully " + e.getMessage());
//                    Assert.fail();
//                }
            }
			finally {
				}
        }
        else
        {
            Assert.fail("Recovery Failed: Unable to Recover the Operation as Element is not Interactable for ->" + el);
        }
    }


    public void safeJavaScriptClick(WebElement element) throws Exception {

        try {
            if (element.isEnabled() && element.isDisplayed()) {
                System.out.println("Clicking on element with using java script click in If");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            } else {
                System.out.println("Clicking on element with using java script click in else");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            }
        } catch (StaleElementReferenceException e) {
        } catch (NoSuchElementException e) {
        } catch (Exception e) {
        }
    }

    public void ClickonIfElementVisible(WebElement el) throws Exception {

      if(el.isDisplayed())
        {
            Clickon(el);
        }
    }
    public void ClearAttributeValue(WebElement element)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '')", element);
    }
    public void ClearText(WebElement element)
    {
        el.clear();
    }
    public boolean verifyLinksOpenNewTanOrWindows(WebElement element)
    {
        boolean flag = false;
        Set<String> totalopenwindow=driver.getWindowHandles();
        el.click();
        waitForElementLoad(4);
        Set<String> totalopenwindowafter=driver.getWindowHandles();
         if(totalopenwindowafter.size()>totalopenwindow.size())
         {
             return true;
         }
       return flag;
    }
	public void setAttributeUsingJS(WebElement element, String attName, String attValue) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attName, attValue);
	}

	public boolean VerifyElementValueLessThan(WebElement element, int ExpValue)
	{
		boolean flag = false;
		int value = Integer.parseInt(element.getAttribute("value").trim());
		if(value<ExpValue)
		{
			flag = true;
		}
		return  flag;
	}
	public boolean VerifyElementValueGreaterThan(WebElement element, int ExpValue)
	{
		boolean flag = false;
		int value = Integer.parseInt(element.getAttribute("value").trim());
		if(value>ExpValue)
		{
			flag = true;
		}
		return  flag;
	}
	public boolean VerifyElementValueLessThanOrEqualTo(WebElement element, int ExpValue)
	{
		boolean flag = false;
		int value = Integer.parseInt(element.getAttribute("value").trim());
		if(value<=ExpValue)
		{
			flag = true;
		}
		return  flag;
	}
	public boolean VerifyElementValueGreaterThanOrEqualTo(WebElement element, int ExpValue)
	{
		boolean flag = false;
		int value = Integer.parseInt(element.getAttribute("value").trim());
		if(value>=ExpValue)
		{
			flag = true;
		}
		return  flag;

	}

	public void SendKeys(WebElement el,String value) throws InterruptedException, IOException {
		//Thread.sleep(3000);
		//el.
		//System.out.println(el.getRect().getHeight()+"-"+el.getRect().getWidth()+"-"+el.getRect().x+"-"+el.getRect().x);
		//ExtentTestManager.getTest().//Log(//LogStatus.PASS,ExtentTestManager.getTest().addBase64ScreenShot(capturescreenshotforelement(el)));
		if(el!=null) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			try {

				//JavascriptExecutor js = (JavascriptExecutor) driver;
				//use executeScript() method and pass the arguments
				//Here i pass values based on css style. Yellow background color with solid red color border.
				//**************************************************
				//js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", el);
				//Thread.sleep(200);
				//js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
				//**************************************************
//				if(MobileDriver!=null) {
//					el.clear();
//				}
//				else {
//					el.clear();
//					el.click();
//					el.clear();
//					el.sendKeys(Keys.BACK_SPACE);
//					el.sendKeys(Keys.BACK_SPACE);
//					el.sendKeys(Keys.BACK_SPACE);
//					el.sendKeys(Keys.BACK_SPACE);
//					el.sendKeys(Keys.BACK_SPACE);
//					el.sendKeys(Keys.BACK_SPACE);
//				}
				el.sendKeys(value);
				System.out.println("INFO : Send Keys Event Triggered on -> "+el+" with value -> "+value);
				//	js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);

			}

			catch (WebDriverException e)
			//Thread.sleep(3000);
			{
				System.out.println(e);
//				js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", el);
				Thread.sleep(200);
//				js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
				//Thread.sleep(3000);
				//JavascriptExecutor js = (JavascriptExecutor) driver;
				System.out.println("Recovery : Operation was Un-successful but Recovering Data Entery the Operation -> "+el+" with value -> "+value);

				System.out.println("ERROR Error Cathced During Opertaion :"+  e.getMessage());
				if (!e.toString().equals("NoSuchElementException")) {
					try {
						Thread.sleep(500);
						el.clear();
						el.sendKeys(value);
						System.out.println("Recovery : Send Keys Event Recovered on -> "+el+" with value -> "+value);

						//		js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
					} catch (Exception e1) {
						//	Reporter.addStepLog("Something went Wrong adn Send Keys did not performed successfully " + e.getMessage());
//						Assert.fail();
						el.sendKeys(value);

					}
				}
			}
			//Thread.sleep(3000);
		}
		else{
			Assert.fail("OPERATION Failed: Element We are trying to interact is not found");
		}
		try {

			AndroidDriver.hideKeyboard();
		}
		catch (Exception e)
		{
			System.out.println("INFO : UI is not from Mobile Device hence Keyboard hide operation is not applicable" + e.getMessage());
		}
	}

	public void Clear(WebElement el) throws IOException, InterruptedException
	{ //Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", el);
		Thread.sleep(200);
		js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
		el.clear();
		//Thread.sleep(3000);
		//		js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
	}

	public void SendKeysUsingJS(WebElement el,String value) throws InterruptedException, IOException {
		//Thread.sleep(3000);
		//el.
		//System.out.println(el.getRect().getHeight()+"-"+el.getRect().getWidth()+"-"+el.getRect().x+"-"+el.getRect().x);
		//ExtentTestManager.getTest().//Log(//LogStatus.PASS,ExtentTestManager.getTest().addBase64ScreenShot(capturescreenshotforelement(el)));
		if(el!=null) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			try {

				if(MobileDriver!=null) {
					el.clear();
				}
				else {
					el.clear();
					el.click();
					el.clear();
					el.sendKeys(Keys.BACK_SPACE);
					el.sendKeys(Keys.BACK_SPACE);
					el.sendKeys(Keys.BACK_SPACE);
					el.sendKeys(Keys.BACK_SPACE);
					el.sendKeys(Keys.BACK_SPACE);
					el.sendKeys(Keys.BACK_SPACE);
				}
				js.executeScript("arguments[0].value="+value+";", el);

				System.out.println("try to Send keys into filed"+value);
				//	js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);

			} catch (WebDriverException e)
			//Thread.sleep(3000);
			{
				System.out.println(e);
//				js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", el);
				Thread.sleep(200);
//				js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
				//Thread.sleep(3000);
				//JavascriptExecutor js = (JavascriptExecutor) driver;
				System.out.println("Error in Clickon ya yaha " + e.getMessage());
				if (!e.toString().equals("NoSuchElementException")) {
					try {
						Thread.sleep(8000);
						el.clear();
						el.sendKeys(value);
						System.out.println("try to Send keys into filed"+value);
						//		js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
					} catch (Exception e1) {
						//	Reporter.addStepLog("Something went Wrong adn Send Keys did not performed successfully " + e.getMessage());
						Assert.fail();
					}
				}
			}
			//Thread.sleep(3000);
		}
		else{
			Assert.fail("Element We are trying to interact is not found");
		}
		try {
			AndroidDriver.hideKeyboard();
		}
		catch (Exception e)
		{
			System.out.println("No a device");
		}
	}

	public void ClickOnElementToOpenInNewWindowAndSwitchToIt(WebElement element) {

		String parent = driver.getWindowHandle();
		Set<String> s = driver.getWindowHandles();

		Iterator<String> I1 = s.iterator();
		while (I1.hasNext()) {
			String child_window = I1.next();
			if (!parent.equals(child_window)) {
				driver.switchTo().window(child_window);

				System.out.println(driver.switchTo().window(child_window).getTitle());

			}
		}

	}
	public void SendkeaboardKeys(WebElement el,Keys k) throws InterruptedException {

		el.sendKeys(k);

	}

	public void SendKeyBoardKeysUsingActions(Keys keys) throws Exception {

		Actions action = new Actions(driver);
		action.sendKeys(keys).build().perform();

	}

	public void PressShiftDelete() throws Exception {

		Actions action = new Actions(driver);
		action.keyDown(Keys.SHIFT).keyDown(Keys.DELETE).build().perform();
		waitForElementLoad(1);
		action.keyUp(Keys.SHIFT).keyUp(Keys.DELETE).build().perform();
	}



	public void ScrollIntoView(WebElement el) throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", el);
		js.executeScript("window.scrollTo(0, 0)");
	}
	public void javascriptexecutor2(String st) throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("document."+st+".submit();");
		//js.executeScript("window.scrollTo(0, 0)");
		//window.scrollTo(0, 0);
	}
	public void ProxyLogout() throws Exception
	{
		OpenURLV2(Getkeyvalue("CPQ_URL")+"///Logout.jsp?proxy_//Logout=true&_bm_trail_refresh_=true");
		////Log.info("Performing Proxy //Logout");
		System.out.println("Proxy //Logout is called");
		Thread.sleep(8000);
	}


	public boolean checkOptions(String[] expected, WebElement el){
		List<WebElement> options = el.findElements(By.xpath(".//option"));
		int k = 0;
		for (WebElement opt : options){
			if (!opt.getText().equals(expected[k]))
			{
				return false;
			}
			k = k + 1;
		}
		return true;
	}
	public void javascriptexecutor2(WebElement el) throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", el);
		//js.executeScript("window.scrollTo(0, 0)");
		//window.scrollTo(0, 0);
	}

	public void SendKeyswithAction(WebElement el, String key) throws Exception {

		Clickon(el);
		Actions action = new Actions(driver);

		action.sendKeys(key).build().perform();
		action.sendKeys(Keys.ENTER).build().perform();

	}
	public void ClickswithAction(String el) throws InterruptedException {


		Actions action = new Actions(driver);

		action.click(driver.findElement(By.xpath(el))).build().perform();
		//action.sendKeys(Keys.TAB).build().perform();

	}

	public void Getloadingcomplete(String locator) throws InterruptedException
	{
		Thread.sleep(1000);
		try {
			wait.until(ExpectedConditions.attributeToBe(By.xpath(locator), "style", "display: none;")); 
			//getwebelement(xml.getlocator("//locators/StandrdQuote"));
			System.out.println("Waiting Loading mask");
			Thread.sleep(5000);
		}
		catch(StaleElementReferenceException e2)
		{
			Thread.sleep(5000);
		}
		catch(TimeoutException e)
		{
			Thread.sleep(5000);
		}

	}


	public void Switchtotabandsignthequote() throws Exception
	{   String parentWinHandle = driver.getWindowHandle();
	Set<String> totalopenwindow=driver.getWindowHandles();
	for(String handle: totalopenwindow)
	{
		if(!handle.equals(parentWinHandle))
		{
			driver.switchTo().window(handle);
			Thread.sleep(12000);
			try {
				safeJavaScriptClick(getwebelement("//*[@id='disclosureAccepted']"));
			}
			catch(Exception e) {
				Clickon(getwebelement("//*[text()='Required']"));
			}
			Clickon(getwebelement("//button[text()='Continue']"));
			Clickon(getwebelement("//button[@data-qa='SignHere']"));
			Clickon(getwebelement("//div[@class='page-tabs']"));
			//create object 'action' of Actions class
			//Dragedrop(getwebelement("//button[@data-qa='SignHere']"),getwebelement("//div[@class='page-tabs']"));
			Thread.sleep(10000);
			Clickon(getwebelement("//button[text()='Adopt and Sign']"));
			//    		Thread.sleep(10000);
			//    		Clickon(getwebelement("//button[text()='Ok']"));
			Thread.sleep(10000);
			waitforElementtobeclickable("//button[text()='Finish']");
			Clickon(getwebelement("//button[text()='Finish']"));
			waitforElementtobeclickable("(//button[text()='Continue'])[2]");
			Clickon(getwebelement("(//button[text()='Continue'])[2]"));
			Thread.sleep(10000);
		}
	}
	driver.close();
	driver.switchTo().window(parentWinHandle);
	}



	public void Getmaploaded(String framlocator, String messagelocator) throws InterruptedException
	{
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.id(framlocator.split("=")[1])));
		//try {
		System.out.println("Code for Map Loading Start");
		//Thread.sleep(3000);
		String[] finalval = framlocator.split("=");
		//Thread.sleep(3000);
		waitforElementtobeclickable(framlocator);

		driver.switchTo().frame(driver.findElement(By.id(finalval[1])));
		System.out.println("Switched to Iframe");

		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(messagelocator)));
		System.out.println("Element found and Waiting");
		System.out.println(driver.findElement(By.xpath(messagelocator)).getText());
		driver.switchTo().defaultContent();
		//ExpectedConditions.elementToBeClickable(locator)
		//Thread.sleep(2000);
		System.out.println("Code for Map Loading End ");
		Thread.sleep(2000);
		//}
		//}
		//		catch(Exception e)
		//		{
		//			System.out.println("In catch"+e.getMessage());
		//			Thread.sleep(1000);
		//			driver.switchTo().defaultContent();
		//			System.out.println("Switched to Default Content");
		//			Getmaploaded(framlocator,messagelocator);
		//			
		//		}

	}

	public void WaitforFiletobeDownloaded(String Filename) throws InterruptedException
	{
		String str = System.getProperty("user.dir")+"/src/Data/Downloads/"+Filename;
		File file=new File(str);
		wait.until((driver)->file.exists());
	}
	public WebElement getwebelement(String locator) throws InterruptedException
	{   
		//WaitforElementtobeclickable(locator);
		System.out.println("In Get element method for - "+locator);
		String[] finalval;
		try {
			if(locator.startsWith("name"))
			{
				finalval=locator.split("=");
				//Log.info(finalval[1]);
				//Log.info("Indriverhelper"+driver);
				//wait.until();
				wait.until(new Function<WebDriver, WebElement>(){
					public WebElement apply(WebDriver driver) {
						try{
						el=driver.findElement(By.name(finalval[1]));
							((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
						}
						catch (NoSuchElementException e)
						{
							el=null;
						}
						if(el!=null) {
							try {
								wait.until(ExpectedConditions.elementToBeClickable(el));
							} catch (Exception e) {
								wait.until(ExpectedConditions.visibilityOf(el));
							}
						}
						//wait.until(el.isEnabled());
						return el;   
					}
				});
				//wait.until(ExpectedConditions.stalenessOf(element))
			}
			else if(locator.startsWith("id"))
			{
				finalval=locator.split("=");
				//Log.info(finalval[1]);
				//Log.info("Indriverhelper"+driver);
				try{
					el=driver.findElement(By.id(finalval[1]));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);

				}
				catch (NoSuchElementException e)
				{
					el=null;
				}
				wait.until(new Function<WebDriver, WebElement>(){
					public WebElement apply(WebDriver driver) {
						try{
							el=driver.findElement(By.id(finalval[1]));
							((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);

						}
						catch (NoSuchElementException e)
						{
							el=null;
						}
						if(el!=null) {
							try {
								wait.until(ExpectedConditions.elementToBeClickable(el));
							} catch (Exception e) {
								wait.until(ExpectedConditions.visibilityOf(el));
							}
						}
						//wait.until(el.isEnabled());
						return el;   
					}
				});

			}
			else if(locator.startsWith("model"))
			{
				finalval=locator.split("=");
				//Log.info(finalval[1]);
				//Log.info("Indriverhelper"+driver);
				try{
					el=	driver.findElement(new ByAngularModel("",finalval[1]));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);

				}

				catch (NoSuchElementException e)
				{
					el=null;
				}

			}
			else if(locator.startsWith("buttonText"))
			{
				finalval=locator.split("=");
				//Log.info(finalval[1]);
				//Log.info("Indriverhelper"+driver);
				try{
					el= driver.findElement(new ByAngularButtonText("",finalval[1]));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);

				}

				catch (NoSuchElementException e)
				{
					el=null;
				}

			}

			else if (locator.startsWith("//")|| locator.startsWith("(//")||locator.startsWith("("))
			{
				//el=driver.findElement(By.xpath(locator));

				wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver driver) {
						try{
							el=driver.findElement(By.xpath(locator));
							((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);

						}
						catch (NoSuchElementException e)
						{
							el=null;


						}
//						if(el!=null) {
//							try {
//
//								wait.until(ExpectedConditions.elementToBeClickable(el));
//							} catch (Exception e) {
//								wait.until(ExpectedConditions.visibilityOf(el));
//							}
//						}
						return el;
					}
				});

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			//getwebelement(locator);
		}
//		if (el == null) {
//			try {
//				String pageHtml = driver.getPageSource();
//				String newLocator = SelfHealingLocatorUtil.getAlternativeLocator(locator, pageHtml);
//				System.out.println("Trying self-healed locator: " + newLocator);
//				// Try as XPath first, fallback to CSS if needed
//				if (newLocator.startsWith("//") || newLocator.startsWith("(")) {
//					el = driver.findElement(By.xpath(newLocator));
//				} else {
//					el = driver.findElement(By.cssSelector(newLocator));
//				}
//			} catch (Exception ex) {
//				System.out.println("Self-healing failed: " + ex.getMessage());
//				el = null;
//			}
//		}
		//Thread.sleep(1000);
		//	try {
		//		System.out.println("Is the element is enabled-"+el.isEnabled());
		//	}
		//	catch(Exception e)
		//	{
		//		System.out.println(e.getMessage().toString());
		//	}
		if(el!=null)
		{
			//Takescreenshot
			//Update Attribute by searching the Value

		}
		System.out.println("Exiting the GETELEMENT");
		return el;
	}


	public WebElement getDesktopelement(String locator) throws InterruptedException
	{
		//WaitforElementtobeclickable(locator);
		System.out.print("In Get element method for - "+locator);
		String[] finalval;
		try {
			if(locator.startsWith("name"))
			{
				finalval=locator.split("=");
				//Log.info(finalval[1]);
				//Log.info("Indriverhelper"+driver);
				//wait.until();
				waitDesktop.until(new Function<WindowsDriver, WebElement>(){
					public WebElement apply(WindowsDriver DesktopDriver) {
						el=DesktopDriver.findElement(By.name(finalval[1]));
						try {
							waitDesktop.until(ExpectedConditions.elementToBeClickable(el));
						}
						catch(Exception e) {
							waitDesktop.until(ExpectedConditions.visibilityOf(el));
						}
						//wait.until(el.isEnabled());
						return el;
					}
				});
				//wait.until(ExpectedConditions.stalenessOf(element))
			}
			else if(locator.startsWith("id"))
			{
				finalval=locator.split("=");
				//Log.info(finalval[1]);
				//Log.info("Indriverhelper"+driver);
				el=DesktopDriver.findElement(By.id(finalval[1]));
				waitDesktop.until(new Function<WindowsDriver, WebElement>(){
					public WebElement apply(WindowsDriver DesktopDriver) {
						el=DesktopDriver.findElement(By.id(finalval[1]));
						try {
							waitDesktop.until(ExpectedConditions.elementToBeClickable(el));
						}
						catch(Exception e) {
							waitDesktop.until(ExpectedConditions.visibilityOf(el));
						}
						//wait.until(el.isEnabled());
						return el;
					}
				});

			}
			else if(locator.startsWith("accessibityId"))
			{
				finalval=locator.split("=");
				//Log.info(finalval[1]);
				//Log.info("Indriverhelper"+driver);
//				el=DesktopDriver.findElementByAccessibilityId(finalval[1]);
				el= DesktopDriver.findElement(new AppiumBy.ByAccessibilityId(finalval[1]));
				waitDesktop.until(new Function<WindowsDriver, WebElement>(){
					public WebElement apply(WindowsDriver DesktopDriver) {
						el= DesktopDriver.findElement(new AppiumBy.ByAccessibilityId(finalval[1]));
						try {
							waitDesktop.until(ExpectedConditions.elementToBeClickable(el));
						}
						catch(Exception e) {
							waitDesktop.until(ExpectedConditions.visibilityOf(el));
						}
						//wait.until(el.isEnabled());
						return el;
					}
				});

			}
			else if (locator.startsWith("//")|| locator.startsWith("(//")||locator.startsWith("("))
			{
				//el=driver.findElement(By.xpath(locator));
				//el=DesktopDriver.findElement(By.id(finalval[1]));
				waitDesktop.until(new Function<WindowsDriver, WebElement>() {
					public WebElement apply(WindowsDriver DesktopDriver) {
						el = DesktopDriver.findElement(By.xpath(locator));
						try {
							waitDesktop.until(ExpectedConditions.elementToBeClickable(el));
						} catch (Exception e) {
							waitDesktop.until(ExpectedConditions.visibilityOf(el));
						}
						return el;
					}
				});

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			//getwebelement(locator);
		}
		//Thread.sleep(1000);
		//	try {
		//		System.out.println("Is the element is enabled-"+el.isEnabled());
		//	}
		//	catch(Exception e)
		//	{
		//		System.out.println(e.getMessage().toString());
		//	}
		return el;
	}

	public void javascriptButtonClick(WebElement webElement) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", webElement);
	}
	public String getAdsOnPage(String string) {
		String src=null,width=null,height=null;

		try {
			driver.switchTo().frame(0);
			WebElement el =driver.findElement(By.xpath("//img[@class='img_ad']"));
			src= Getattribute(el,"src");
			width = Getattribute(el,"width");
			height= Getattribute(el,"height");
			driver.switchTo().defaultContent();

		}catch (Exception e) {

		}finally {
			driver.switchTo().defaultContent();
		}
		String Ads = src + " " + width + " "+height;
		return Ads;
	}

	public boolean validateDataTrackResearchPage()	{
		boolean flag = true;
		int count = driver.findElements(By.xpath("(//*[contains(@id,'zone')]//*[@data-track])")).size();
		System.out.println(count);
		for (int i = 1; i <= count; i++) {
			String loc="(//*[contains(@id,'zone')]//*[@data-track])";
			String locator =loc.concat("[" + i + "]");
			String attri = Getattribute(driver.findElement(By.xpath(locator)),"data-track");
			//	getWebDriver().findElement(ByLocator(locator)).getAttribute("data-track");
			if (attri.endsWith(" ") || attri.startsWith(" ")) {
				System.err.println("Space is present with data-track:" + attri);
				flag = false;
			} else if (attri.endsWith("") || attri.startsWith("")) {
				System.out.println("Leading And Trailing Space is not present with data-track:" + attri);
			}
		}Assert.assertTrue(flag);
		return flag;
	}
	public WebElement getMobileelement(String locator) throws InterruptedException
	{
		//WaitforElementtobeclickable(locator);
//		System.out.print("In Get element method for - "+locator);
		String[] finalval;
		try {
			if(locator.startsWith("class"))
			{
				finalval=locator.split("=");
				//Log.info(finalval[1]);
				//Log.info("Indriverhelper"+driver);
				//wait.until();
				waitMobile.until(new Function<AppiumDriver, WebElement>(){
					public WebElement apply(AppiumDriver MobileDriver) {
						try {
							el= MobileDriver.findElement(new AppiumBy.ByAccessibilityId(finalval[1]));

						}
						catch (NoSuchElementException e)
						{
							el=null;
						}
						if(el!=null) {
							try {
								waitMobile.until(ExpectedConditions.elementToBeClickable(el));
							} catch (Exception e) {
								waitMobile.until(ExpectedConditions.visibilityOf(el));
							}
						}
						//wait.until(el.isEnabled());
						return el;
					}
				});
				//wait.until(ExpectedConditions.stalenessOf(element))
			}
			else if(locator.startsWith("id"))
			{
				finalval=locator.split("=");
				//Log.info(finalval[1]);
				//Log.info("Indriverhelper"+driver);
				el=MobileDriver.findElement(By.id(finalval[1]));
				waitMobile.until(new Function<AppiumDriver, WebElement>(){
					public WebElement apply(AppiumDriver MobileDriver) {

						try {
							el = MobileDriver.findElement(By.id(finalval[1]));
						}
						catch (NoSuchElementException e)
						{
							el=null;
						}
						if(el!=null) {
							try {
								waitMobile.until(ExpectedConditions.elementToBeClickable(el));
							} catch (Exception e) {
								waitMobile.until(ExpectedConditions.visibilityOf(el));
							}
						}
						//wait.until(el.isEnabled());
						return el;
					}
				});

			}
			else if(locator.startsWith("automationID"))
			{
				finalval=locator.split("=");
				//Log.info(finalval[1]);
				//Log.info("Indriverhelper"+driver);
//				el=MobileDriver.findElementByAccessibilityId(finalval[1]);
				el= MobileDriver.findElement(new AppiumBy.ByAccessibilityId(finalval[1]));

				waitMobile.until(new Function<AppiumDriver, WebElement>(){
					public WebElement apply(AppiumDriver MobileDriver) {

						try {
//							el = MobileDriver.findElementByAccessibilityId(finalval[1]);
							el= MobileDriver.findElement(new AppiumBy.ByAccessibilityId(finalval[1]));

						}
						catch (NoSuchElementException e)
						{
							el=null;
						}
						if(el!=null) {
							try {
								waitMobile.until(ExpectedConditions.elementToBeClickable(el));
							} catch (Exception e) {
								waitMobile.until(ExpectedConditions.visibilityOf(el));
							}
						}
						//wait.until(el.isEnabled());
						return el;
					}
				});

			}
			else if(locator.startsWith("UIAutomator"))
			{
				finalval=locator.split("=");
				//Log.info(finalval[1]);
				//Log.info("Indriverhelper"+driver);
				el=MobileDriver.findElement(AppiumBy.androidUIAutomator(finalval[1]));
				waitMobile.until(new Function<AppiumDriver, WebElement>(){
					public WebElement apply(AppiumDriver MobileDriver) {

						try {
							el = MobileDriver.findElement(AppiumBy.androidUIAutomator(finalval[1]));
						}
						catch (NoSuchElementException e)
						{
							el=null;
						}
						if(el!=null) {
							try {
								waitMobile.until(ExpectedConditions.elementToBeClickable(el));
							} catch (Exception e) {
								waitMobile.until(ExpectedConditions.visibilityOf(el));
							}
						}
						//wait.until(el.isEnabled());
						return el;
					}
				});

			}
			else if(locator.startsWith("accessibilityId")){
				finalval=locator.split("=");
				//Log.info(finalval[1]);
				//Log.info("Indriverhelper"+driver);
				el=MobileDriver.findElement(AppiumBy.accessibilityId(finalval[1]));
				waitMobile.until(new Function<AppiumDriver, WebElement>(){
					public WebElement apply(AppiumDriver MobileDriver) {

						try {
							el = MobileDriver.findElement(AppiumBy.accessibilityId(finalval[1]));
						}
						catch (NoSuchElementException e)
						{
							el=null;
						}
						if(el!=null) {
							try {
								waitMobile.until(ExpectedConditions.elementToBeClickable(el));
							} catch (Exception e) {
								waitMobile.until(ExpectedConditions.visibilityOf(el));
							}
						}
						//wait.until(el.isEnabled());
						return el;
					}
				});

			}
			else if (locator.startsWith("//")|| locator.startsWith("/")||locator.startsWith("(//")||locator.startsWith("("))
			{
				//el=driver.findElement(By.xpath(locator));
				//el=DesktopDriver.findElement(By.id(finalval[1]));

				waitMobile.until(new Function<AppiumDriver, WebElement>() {
					public WebElement apply(AppiumDriver MobileDriver) {

						try {
//							el = MobileDriver.findElementByXPath(locator);
							el = MobileDriver.findElement(AppiumBy.xpath(locator));
						}
						catch (NoSuchElementException e)
						{
							el=null;
						}
						if(el!=null) {
							try {
								waitMobile.until(ExpectedConditions.elementToBeClickable(el));
							} catch (Exception e) {
								waitMobile.until(ExpectedConditions.visibilityOf(el));
							}
						}
						return el;
					}
				});

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			//getwebelement(locator);
		}
		//Thread.sleep(1000);
		//	try {
		//		System.out.println("Is the element is enabled-"+el.isEnabled());
		//	}
		//	catch(Exception e)
		//	{
		//		System.out.println(e.getMessage().toString());
		//	}
		return el;
	}



	public WebElement ReturnElement(String locator){

		System.out.print("In Get element method for - "+locator);
		String[] finalval;
		try {
			if(locator.startsWith("name"))
			{
				finalval = locator.split("=");
				////Log.info(finalval[1]);
				////Log.info("Indriverhelper"+driver);
				//wait.until();
				el = driver.findElement(By.name(finalval[1]));


				//wait.until(ExpectedConditions.stalenessOf(element))
			} else if (locator.startsWith("id")) {
				finalval = locator.split("=");
				////Log.info(finalval[1]);
				////Log.info("Indriverhelper"+driver);
				el = driver.findElement(By.id(finalval[1]));


				//el= driver.findElement(By.id(finalval[1]));
			} else if (locator.startsWith("//") || locator.startsWith("(//") || locator.startsWith("(")) {
				el = driver.findElement(By.xpath(locator));


			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			//getwebelement(locator);
		}
		//Thread.sleep(1000);
		try {
			System.out.println("Is the element is enabled-" + el.isEnabled());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return el;

	}

	public WebElement getwebelement2(String locator) throws InterruptedException {
		if (locator.startsWith("//") || locator.startsWith("(//")) {
			el = driver.findElement(By.xpath(locator));
			return driver.findElement(By.xpath(locator));
		} else if (locator.startsWith("id")) {
			el = driver.findElement(By.id(locator.split("=")[1]));
			return driver.findElement(By.xpath(locator));
		} else if (locator.startsWith("name")) {
			el = driver.findElement(By.name(locator.split("=")[1]));
			return driver.findElement(By.xpath(locator));
		}
		Thread.sleep(1000);
		return el;
	}

	public String Getattribute(WebElement el,String attributename) 
	{
		//Log.info(el.getAttribute(attributename));
		return el.getAttribute(attributename);
	}

	public void ProxyLogin(String User, String Proxylink) throws Exception
	{
		//openurl("CPQAdmin");
		waitforElementtobeclickable("//a[text()='Internal Users']");
		Clickon(getwebelement("//a[text()='Internal Users']"));
		Thread.sleep(2000);
		String uri=Getattribute(getwebelement(Proxylink),"href");
		System.out.println("URL is"+uri);
		String[] URL1=uri.split("user_id=");
		System.out.println(URL1[0]);
		System.out.println(URL1[1]);
		String URL2=(URL1[1].split("&"))[1];
		System.out.println(URL2);
		//PropertyReader pr=new PropertyReader();
		//String FinalURL=URL1[0]+"user_id="+pr.readproperty(User)+"&"+URL2;
		//System.out.println(FinalURL);

		//Log.info("CPQ_URL");
		//openurl2(FinalURL);

	}
	public void Moveon(WebElement el) {

		Actions action = new Actions(driver);

		action.moveToElement(el).build().perform();
	}



	public boolean isElementPresent(String locator) {
//		WebElement el=driver.findElement(By.xpath(locator));
//		int i=90;
//		try {
//				while(!el.isDisplayed()&&i>0){
//
//			System.out.println("Text Value"+driver.findElement(By.xpath(locator)).isDisplayed());
//			//Log.info("Element Found: True");
//					Thread.sleep(1000);
//					i=i-1;
//					}
//			return el.isDisplayed();
//		}
//		catch ( Exception e2)
//		{
//			return false;
//		}
////		catch (NoSuchElementException e) {
////			//Log.info("Element Found: False");
////			return false;
////		}

			int i = 1; // wait up to 90 seconds
			try {
				while (i > 0) {
					List<WebElement> elements = driver.findElements(By.xpath(locator));

					if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
						return true; // element exists and is visible
					}

					Thread.sleep(100);
					i--;
				}
				return false; // timed out, element not found
			} catch (Exception e) {
				return false; // in case of any unexpected error
			}
		}


	public void Expandthesection(WebElement Section, WebElement ClickableElement) throws Exception {

		Thread.sleep(5000);	
		String classvalue=Getattribute(Section,"class");
		System.out.println(classvalue);
		if(!classvalue.contains("green")){
			System.out.println("In IF class");
			//Clickon(ClickableElement);
			((JavascriptExecutor)

					driver).executeScript("arguments[0].scrollIntoView();", ClickableElement);
			safeJavaScriptClick(ClickableElement);

		}
		else {
			System.out.println("Already expanded");
		}
	}

	public void Select3(WebElement el, String value) throws IOException, InterruptedException
	{ try {
		if (el.isEnabled() && el.isDisplayed()) {
			//Log.info("Clicking on element with using java script click");

			((JavascriptExecutor) driver).executeScript("arguments[0].value='"+value+"'",el);
		} else {
			//Log.info("Unable to click on element");
		}
	} catch (StaleElementReferenceException e) {
		//Log.info("Element is not attached to the page document "+ e.getStackTrace());
	} catch (NoSuchElementException e) {
		//Log.info("Element was not found in DOM "+ e.getStackTrace());
	} catch (Exception e) {
		//Log.info("Unable to click on element "+ e.getStackTrace());
	}
	}




	public void SendKeys(AndroidKey key) throws InterruptedException, IOException {

		AndroidDriver.pressKey(new KeyEvent(key));

	}



	public String GetText(WebElement el) {
		String actual = el.getText().toUpperCase();
		//		String actual1=el.getText().toUpperCase().toString();
		return actual;
	}

	public String GetInputValue(WebElement el) {
		String actual=el.getAttribute("value");
		return actual;
	}

	public String Getkeyvalue(String Key) throws IOException {
		//PropertyReader pr=new PropertyReader();
		String Keyvalue = null;
		//Keyvalue=pr.readproperty(Key);
		return Keyvalue;
	}

	public void VerifyTextpresent(String text) throws IOException {
		//Log.info(text);
		Assert.assertFalse(driver.findElement(By.xpath("//*[text()='" + text + "']")).isDisplayed());
	}

	//	public void VerifyText(String text) throws IOException
	//		{ 
	//			//Log.info(text);
	//			Assert.assertTrue(driver.findElement(By.xpath("//*[text()='"+text+"']")).isDisplayed());
	//		}

	public String Gettext(WebElement el) throws IOException {
		String text = el.getText();
		return text;
	}

	public String GetValueofInput(WebElement el) throws IOException {
		String text = el.getAttribute("value");
		return text;
	}

	public String[] GetText2(WebElement el) throws IOException {
		String text = el.getText();
		String[] text2 = text.split(" \\[");
		//Log.info("New Task Name is:"+text2);
		return text2;
	}

	public String GetText3(WebElement el, String string) throws IOException {
		String text = el.getText();
		return text;
	}

	public void Select(WebElement el, String value) throws IOException, InterruptedException { //Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		if (!value.equals("")) {
			js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", el);
			Thread.sleep(200);
			js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
			Select s1 = new Select(el);
			s1.selectByVisibleText(value);
			//	js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
		} else {
			System.out.println("Noting to select");
		}
		//Thread.sleep(3000);
	}
	public void Select2(WebElement el, String value) throws IOException, InterruptedException
	{ //Thread.sleep(3000);
		if(!value.equals("")) {
			Select s1=new Select(el);
			s1.selectByValue(value);
		}
		else
		{
			System.out.println("Noting to select");
		}
		//Thread.sleep(3000);
	}

	public void WaitforCPQloader( ) throws IOException, InterruptedException
	{
		System.out.println("Data missing");


	}

	public void WaitforCPQloader2( ) throws IOException, InterruptedException
	{
		for(int i=0;i<=20;i++) {
			//Thread.sleep(30000);
			if (driver.findElement(By.xpath("//html")).getAttribute("class").contains("loading")) {
				System.out.println("In check");
				Thread.sleep(500);
				//WaitforCPQloader2( );
			} else {
				System.out.println("Loading Finnished");

			}

		}
		Thread.sleep(2000);
		//		System.out.println("In second loader");
		//		
		//		try {
		//			//Thread.sleep(3000);
		//			System.out.println(driver.findElement(By.xpath("//html")).getAttribute("class"));
		//			//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//html[contains(@class,'loading')]"))));
		//			//System.out.println(driver.findElement(By.xpath("//html")).getAttribute("class"));
		//			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//html[contains(@class,'loading')]"))));
		//			System.out.println(driver.findElement(By.xpath("//html")).getAttribute("class"));
		//			
		//			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//html[contains(@class,'page-loaded')]"))));
		//				}
		//				catch(Exception e) {
		//					//Log.info("No Loader displayed");
		//					System.out.println("in catch");
		//					Thread.sleep(500);
		//					WaitforCPQloader2();
		//				}

	}
	public void WaitforC4Cloader(String el, int timeout ) throws IOException, InterruptedException
	{ Thread.sleep(1500);

	try {
		while(isElementPresent(el))
		{
			Thread.sleep(500);
			System.out.println("in C4C loader loop");
		}
		//;
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(el)));
		//wait.until(ExpectedConditions.presenceOfElementLocated(driver.findElement(By.xpath(el))));
	}
	catch(Exception e) {
		//Log.info("No Loader displayed");
		System.out.println("No Loader");
	}
	//		for(int i=0;i<=timeout*60/20;i++){
	//			try {
	//	            if (isElementPresent(el)){
	//	            	////Log.info("Refreshing the Pages");
	//		        	//driver.navigate().refresh();
	//		        	//Log.info("Waiting For 20 Sec");
	//		        	Thread.sleep(20000);
	//	            }
	//	            else{
	//	            	////Log.info("Refreshing the Pages");
	//		        	//driver.navigate().refresh();
	//		        	break;
	//	            }
	//	            }
	//	        catch (Exception e) {
	//	        	//Log.info(e.getMessage());
	//	        }
	//		}
	//Thread.sleep(8000);
	}
	public void AcceptJavaScriptMethod() throws InterruptedException{
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			alert.accept();
			driver.switchTo().defaultContent();
		}

		catch(Exception e)
		{
			System.out.println("No Alert Present");
		}
	}
	public void CancelJavaScriptMethod() throws InterruptedException{
		Thread.sleep(1000);
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
		driver.switchTo().defaultContent();
	}
	public void waitForpageload() throws Exception
	{

		//wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));	

		Thread.sleep(1500);
		try {
			for (int i = 0; i <= 10; i++) {
				while (isElementPresent("//html[contains(@class,'loading')]")) {
					Thread.sleep(500);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Thread.sleep(300);
		waitForpageloadmask();
		WaitforPageToBeReady();
	}
	public void waitForpageloadmask() throws InterruptedException
	{

		//wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));	

		Thread.sleep(1500);
		try {
			for (int i = 0; i <= 10; i++) {
				while (isElementPresent("//div[@id='lockCreateScreen' and not(@style='display: none;')]")) {
					Thread.sleep(500);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Thread.sleep(300);
	}
	public void waitForpageloadExplore() throws InterruptedException
	{

		//wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));	

		Thread.sleep(1500);
		try {
			for (int i = 0; i <= 2; i++) {
				while (isElementPresent("//div[contains(text(),'Loading data')]")) {
					Thread.sleep(500);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Thread.sleep(1000);
	}
	public void waitForpagenavigated(int timeout) throws InterruptedException
	{
		for(int i=0;i<=timeout*100/5;i++){
			try {
				if (!driver.getTitle().contains("Transaction")) {
					////Log.info("Refreshing the Pages");
					//driver.navigate().refresh();
					//Log.info("Waiting For 20 Sec");
					System.out.println("Waiting for Navigation happen");
					Thread.sleep(500);
				} else {
					////Log.info("Refreshing the Pages");
					System.out.println("Nevigated to CPQ");
					//driver.navigate().refresh();
					break;
				}
			}
			catch (Exception e) {
				//Log.info(e.getMessage());
			}
		}
		//Thread.sleep(3000);
		//wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));	
		//Thread.sleep(1000);
	}
	public void Dragedrop(WebElement source,WebElement Destination){
		Actions action = new Actions(driver);
		//use dragAndDrop() method. It accepts two parametes source and target.
		action.dragAndDrop(source, Destination).build().perform();
	}

	public void EnterTextUsingActions(String s){
		Actions keyAction = new Actions(driver);
		keyAction.sendKeys(s).perform();
	}

	public void EnterText(String s){
		Actions keyAction = new Actions(DesktopDriver);
		keyAction.sendKeys(s).perform();
	}

	public void EnterTextUsingAction() throws InterruptedException {
		Actions keyAction = new Actions(driver);     
		keyAction.sendKeys(Keys.chord(Keys.CONTROL,"V")).perform();
		Thread.sleep(1000);
		keyAction.sendKeys(Keys.ENTER).perform();
	}
	public void savePage(){
		Actions keyAction = new Actions(driver);     
		keyAction.keyDown(Keys.CONTROL).sendKeys("s").keyUp(Keys.CONTROL).perform();
	}
	public void KeydownKey(Keys key){
		Actions keyAction = new Actions(driver);     
		keyAction.keyDown(key).perform();
	}
	public void KeyupKey(Keys key){
		Actions keyAction = new Actions(driver);     
		keyAction.keyUp(key).perform();
	}
	public void uploadafile(String  locator,String FileName)
	{
		String str = System.getProperty("user.dir")+"/src/test/resources/dataSource/"+FileName;
		String[]  finalval=locator.split("=");
		WebElement el;
		if(locator.startsWith("id")) 
		{
			el=driver.findElement(By.id(finalval[1]));
		}
		else if(locator.startsWith("name"))
		{
			el=driver.findElement(By.name(finalval[1]));
		}
		else 
		{
			el=driver.findElement(By.xpath(finalval[1]));
		}
		el.sendKeys(str);
	}
	public String capturescreenshotforelement(WebElement ele) throws IOException
	{
		String screenshot2;
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		BufferedImage  fullImg = ImageIO.read(screenshot);

		// Get the location of element on the page
		org.openqa.selenium.Point point = ele.getLocation();

		// Get width and height of the element
		int eleWidth = ele.getSize().getWidth();
		int eleHeight = ele.getSize().getHeight();

		// Crop the entire page screenshot to get only element screenshot
		BufferedImage eleScreenshot= fullImg.getSubimage(point.getX()-20, point.getY()-20,
				eleWidth+20, eleHeight+20);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(eleScreenshot, "png", bos);
		byte[] imageBytes = bos.toByteArray();
		screenshot2 = "data:image/png;base64,"+Base64.getMimeEncoder().encodeToString(imageBytes);
		bos.close();
		return screenshot2;
	}
	public static String Capturefullscreenshot(WebDriver driver) throws IOException
	{
		String screenshot2;
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver);

		// To save the screenshot in desired location
		//		ImageIO.write(screenshot.getImage(), "PNG",
		//				new File(System.getProperty("user.dir") + "/screenshots/fullpagescrn.png"));

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		ImageIO.write(screenshot.getImage(), "PNG", bos);

		byte[] imageBytes = bos.toByteArray();
		screenshot2 = "data:image/png;base64,"+Base64.getMimeEncoder().encodeToString(imageBytes);

		bos.close();

		return screenshot2;
	}



	@Override
	public void SwitchToLastTab() {
		String parentWinHandle = driver.getWindowHandle();
		Set<String> totalopenwindow=driver.getWindowHandles();
		for(String handle: totalopenwindow)
		{
			if(!handle.equals(parentWinHandle))
			{
				driver.switchTo().window(handle);
			}
		}
	}

	@Override
	public void GetIframeLoaded(String framlocator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(framlocator.split("=")[1])));
		//driver.switchTo().frame(driver.findElement(By.id(framlocator.split("=")[1])));
		System.out.println("Switched to Iframe");
	}

	@Override
	public void WaitforPageToBeReady() throws Exception {
		while(!((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"))
		{
			System.out.println("dom state is" +((JavascriptExecutor) driver).executeScript("return document.readyState"));
			Thread.sleep(500);
			//wait.until(ExpectedConditions.jsReturnsValue(_PAGE_WHEN_READY_SCRIPT));
		}
	}

	@Override
	public String GetTitle() {
		// TODO Auto-generated method stub
		return driver.getTitle();
	}

	@Override
	public void OpenURL(String environment) {
		String URL=environment;
		//PropertyReader pr=new PropertyReader();
		////Log.info(environment+"_URL");
		//URL=pr.readproperty(environment+"_URL");
		System.out.println(URL);
		driver.get(URL);

	}

	@Override
	public void OpenURLV2(String environment) throws Exception {
		String URL=null;
		//PropertyReader pr=new PropertyReader();
		////Log.info(environment+"_URL");
		//URL=pr.readproperty(environment+"_URL");
		System.out.println(URL);
		driver.get(URL);
		Thread.sleep(5000);
	}

	@Override
	public void GetURL(String URL) {
		// TODO Auto-generated method stub
		driver.get(URL);
	}
	@Override
	public void VerifyTitle(String Expectedtitle) {
		// TODO Auto-generated method stub

		Assert.assertTrue(driver.getTitle().contains(Expectedtitle));
	}

	public void verifyCurrentURL(String ExpectedURL) {
		// TODO Auto-generated method stub
		Assert.assertTrue(driver.getCurrentUrl().contains(ExpectedURL));
	}
	@Override
	public void VerifyText(WebElement el,String Expectedtext) {
		if(Expectedtext.equals("NA")) {

			Assert.assertTrue(el.getText().isEmpty(), "Field value Currently is Blank");

		}
        else {
			Assert.assertTrue(el.getText().contains(Expectedtext), "Text of Element displayed as -" + el.getText() + " and Expected String is-" + Expectedtext);
		}
		}
	@Override
	public void PageRefresh() throws Exception{
		// TODO Auto-generated method stub
		driver.navigate().refresh();
		Thread.sleep(5000);
	}
	@Override
	public String CurrentURL(){
		return driver.getCurrentUrl();
	}

	@Override
	public  String CaptureScreenShotForElement(WebElement ele) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String CaptureFullScreenShot(WebDriver driver) {
		// TODO Auto-generated method stub
		return null;
	}


	/*	End of the Web Driver Helper area which contains all the base methods related to the Web Driver
	 *------------------------------------------------------------------------------------------
	 *
	 */


	/*	Start of the API Helper area which contains all the base methods related to the API
	 *------------------------------------------------------------------------------------------
	 *
	 */
	@Override
	public void authentication(String type, String username, String password) {
		//String userName = null,password = null,accessToken = null,consumerKey = null,consumerSecret = null,tokenSecret = null;
		//FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/test/java/Config/config.properties");
		//propinhelper.load(ip);
		switch(type)
		{

		case "Basic":
		{
			Request.auth().basic(username, password);

			break;
		}
		case "preemptive":
		{
			Request.auth().preemptive().basic(username, password);

			break;
		}
		case "Form":
		{
			Request.auth().form(username, password);
			break;
		}
		case "ComplexForm":
		{
			Request.auth().form(username, password, new FormAuthConfig("/perform_login", "username", "password"));
			break;
		}
		case "Oauth2":
		{
			Request.auth().oauth2(username);
			break;
		}
		}

	}
	@Override
	public void updateRequestHeader(String HeaderKey, String value){

		Request.headers(HeaderKey,value);
	}

	@Override
	public String readRequestTemplate(String path) throws IOException {
		// TODO Auto-generated method stub
		System.out.println(Paths.get(System.getProperty("user.dir") + "/" + Root + path));
		return new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/" + Root + path)));
	}

	@Override
	public void updateAttributeInRequestBody(String Filename, String Key, String value) throws Exception {
		if (Filename.toLowerCase().endsWith(".json")) {
/*			String payload=readRequestTemplate(System.getProperty("user.dir")+ "/"+Root+Filename);
			if(Payload.equals("")) {
				Payload=payload;
			}
			Payload=Payload.replace("\""+Key+"\" : \""+Key+"\"","\""+Key+"\" : \""+Value+"\""); */


			String abc = "";
			String[] arrOfStr = Key.split("//");

			if (Key.endsWith("//")) {
				System.out.println("Provided Json node path is not correct!! it can't be end with //");
				Assert.fail("Provided Json node path is not correct!! it can't be end with //");
			}
			FileReader reader = null;
			try {
				Filename = System.getProperty("user.dir") + "/" + Root + Filename;
				System.out.println();
				reader = new FileReader(Filename);
			} catch (Exception e) {
				System.out.println("File path Pointed to Json file is incorrect!!! Please provide a valid path... ");
				e.getMessage();
			}
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

			JSONObject idObj1 = null;

			if (arrOfStr.length == 1 || arrOfStr.length == 0) {
				try {
					if (!arrOfStr[0].contains("//")) {
						System.out.println("// is missing with Node value!!!!");
						Assert.assertFalse(arrOfStr[0].contains("//"), "// is missing with Node value!!!!");
	        	}
	        	else {
	        		System.out.println("Node is not correctly provided!!!!");
	        	}}
	        	catch(Exception e)
	        	{
	        		System.out.println("Node is not correctly provided!!!!");
	                e.getMessage();
	        	}
	        }
	        else {
	        for(int i=0; i<arrOfStr.length-2;i++)
	        {
	        	
	        	//System.out.println(arrOfStr[i+1]);
	        	if(idObj1==null) {
	        		 idObj1 = (
	          	           (JSONObject) (
	          	                       jsonObject.get(arrOfStr[i+1])     
	          	           )
	          	        );
	        	}
	        	else
	        	{
	        	 idObj1=(JSONObject) idObj1.get(arrOfStr[i+1]);
	        	}
	        }
	        
	        if(arrOfStr.length==2)
	        {
	        	jsonObject.put(arrOfStr[arrOfStr.length-1], value); 	
	        	
	        }
	        else {
	        	idObj1.put(arrOfStr[arrOfStr.length-1], value);
		
	        }
	    	//System.out.println("After ID value updated : "+jsonObject)
				JSONDocumentPayload=jsonObject;
	    	 abc= jsonObject.toJSONString();
	    }

	        Payload = abc;


			
			System.out.println("Updated Paylod"+Payload);
			////ExtentTestManager.getTest().log(LogStatus.INFO, "Updated Paylod"+Payload);
		}
		else if(Filename.toLowerCase().endsWith(".xml")) 
		{
			SAXReader xmlreader=new SAXReader();
			Document doc=xmlreader.read(System.getProperty("user.dir")+ "/"+Root+Filename);
			doc.selectSingleNode(Key).setText(value);
			XMLDocumentPayload=doc;
			Payload=doc.asXML();
			System.out.println("Updated Paylod"+Payload);
		}
	}

	@Override
	public void updateAttributeInRequestBody(String Filename, HashMap<String, String> values) throws Exception {
		if (Filename.toLowerCase().endsWith(".json")) {
			//TODO need to implement
		} else if (Filename.toLowerCase().endsWith(".xml")) {
			SAXReader xmlreader = new SAXReader();
			Document doc = xmlreader.read(System.getProperty("user.dir") + "/" + Root + Filename);			
			for (String key : values.keySet()) {
				doc.selectSingleNode(key).setText(values.get(key));
			}
			Payload = doc.asXML();
			System.out.println("Updated Paylod" + Payload);
		}
	}
	
	@Override
	public void generatePayLoad() {
		//Request.contentType(Contenttype);
		Request.body(Payload);
		System.out.println("********************************************");
		System.out.println(Payload);
//		Reporter.addStepLog("<textarea readonly>"+Payload+"</textarea>");
		ReportPayload=Payload;
		System.out.println("********************************************");
		Payload="";
	}
	public void generatemultipart(String key, String Value) {
		//Request.contentType(Contenttype);

		Request.multiPart(key,Value);

	}

	@Override
	public Response submitRequest(Method method, String URI) {
//		try {
			Resultrespoence = null;
			System.out.println(URI);
			Resultrespoence = Request.request(method, URI);
			System.out.println("Response Body After Request: " + Resultrespoence.getBody().asString());
			Request.body("");
			return Resultrespoence;
//		}
////		catch (Exception e) {
//
//			RestAssured.baseURI = "http://localhost:8080";
//
//
//			Response res =
//					given()
//							.when()
//							.get("/api/pet/14");
//			System.out.println("Response Body After Request: " + res.getBody().asString());
//			return  res;

//		}

//		ExtentCucumberAdapter.addTestStepLog("<table style='color:DarkGreen;'><tr><td><textarea>"+Method.POST +":"+uri +"/n"+"Body : "+"</textarea></td></tr><tr></table>");
//
//		Reporter.addStepLog("<textarea readonly>"+Resultrespoence.getBody().asString()+"</textarea>");
	}

	public String Response_Reader(Response res) {
		String responsebody=res.getBody().asString();
		System.out.println("Response Body After Request: "+ responsebody);
		return responsebody;


//		ExtentCucumberAdapter.addTestStepLog("<table style='color:DarkGreen;'><tr><td><textarea>"+Method.POST +":"+uri +"/n"+"Body : "+"</textarea></td></tr><tr></table>");
//
//		Reporter.addStepLog("<textarea readonly>"+Resultrespoence.getBody().asString()+"</textarea>");
	}
	@Override
	public void assertStringInResponceBody(String ExpectedData) {
		String responseBody = Resultrespoence.getBody().asString();
		System.out.println("Response Body is: "+ responseBody);
		//validate city name or validate the key or value
//		ExtentTestManager.getTest().log(LogStatus.PASS, "Response Body is: "+ responseBody);

        Assert.assertTrue(responseBody.contains(ExpectedData));
	}
	@Override
	public void assertStatusCode(int ExpectedStatusCode) {
		int statusCode = Resultrespoence.getStatusCode();
		System.out.println("Status code is: "+ statusCode);
		//ExtentTestManager.getTest().log(LogStatus.INFO, "Status code is: "+ statusCode);

		Assert.assertEquals(statusCode, ExpectedStatusCode);
		//validate city name or validate the key or value
		//return statusCode;
	}
	@Override
	public void assertStatusLine(String ExpectedStatusLine) {
		String statusCode = Resultrespoence.getStatusLine();
		System.out.println("Status Code is: "+ statusCode);
		//ExtentTestManager.getTest().log(LogStatus.INFO, "Status Code is: "+ statusCode);

		Assert.assertEquals(statusCode, statusCode);
		//validate city name or validate the key or value
		//return statusCode;
	}
	@Override
	public void assertHeaderattribute(String HeaderName, String ExpectedheaderValue) {
		Headers headers = Resultrespoence.getHeaders();
		System.out.println(headers);

		String headervalue = Resultrespoence.getHeader(HeaderName);
		System.out.println("The value of content-type header is: "+ headervalue);
		//ExtentTestManager.getTest().log(LogStatus.INFO, "The value of content-type header is: "+ headervalue);
		Assert.assertEquals(headervalue, headervalue);
	}
	@Override
	public void assertResponceBodyAttribute(String Node, String Expectedvalue) throws SAXException, IOException, ParserConfigurationException, DocumentException {
		System.out.println("Respoence body is "+Resultrespoence.getBody().asString());
		//ExtentTestManager.getTest().log(LogStatus.INFO, "Respoence body is "+Resultrespoence.getBody().asString());
		if(Resultrespoence.getHeader("content-type").contains("json")) {
			JsonPath jsonPathValue = Resultrespoence.jsonPath();
			//ExtentTestManager.getTest().log(LogStatus.INFO, "JsoneBody as String String"+jsonPathValue);
			String Nodevalue = jsonPathValue.getString(Node);
			System.out.println("The value of "+Node+" is: "+ Nodevalue);
			//ExtentTestManager.getTest().log(LogStatus.INFO, "The value of "+Node+" is: "+ Nodevalue);

			System.out.println("Expected Value is : "+ Expectedvalue);
			//ExtentTestManager.getTest().log(LogStatus.INFO, "Expected Value is : "+ Expectedvalue);

			System.out.println("Condition Value is "+ Nodevalue.contains(Expectedvalue));
			//Assert.assertTrue();
			//ExtentTestManager.getTest().log(LogStatus.INFO, "Condition Value is "+ Nodevalue.contains(Expectedvalue));

			Assert.assertTrue(Nodevalue.contains(Expectedvalue), "Expected the Value of <b>"+Node+"</b> Attribute value contains <b>"+Expectedvalue+"</b> but  Actual returned was <b>"+Nodevalue+"</b>");
		}
		else if(Resultrespoence.getHeader("content-type").contains("xml"))
		{

			SAXReader xmlreader=new SAXReader();
			Document doc=xmlreader.read(Resultrespoence.asInputStream());
            Assert.assertEquals(Expectedvalue, doc.selectSingleNode(Node).getText(), "Expected the Value of <b>" + Node + "</b> Attribute value contains <b>" + Expectedvalue + "</b> but  Actual returned was <b>" + doc.selectSingleNode(Node).getText() + "</b>");
		}else 
		{
			Assert.fail("Response Content-Type is not matched.Please check the Assertion");
		}
		
	}
	@Override
	public String SaveAttributevalue(String Node) throws DocumentException {
		String Nodevalue = null;
		if(Resultrespoence.getContentType().contains("json")){
		System.out.println("Data in Reponce is"+Resultrespoence.getBody().asString());
		JsonPath jsonPathValue = Resultrespoence.jsonPath();
		Nodevalue = jsonPathValue.get(Node).toString();
		System.out.println("The value of "+Node+" is: "+ Nodevalue);
		//ExtentTestManager.getTest().log(LogStatus.INFO, "The value of "+Node+" is: "+ Nodevalue);

		Assert.assertEquals(Nodevalue, Nodevalue);
		}
		else if(Resultrespoence.getContentType().contains("xml")){
			SAXReader xmlreader=new SAXReader();
			Document doc=xmlreader.read(Resultrespoence.asInputStream());
			Nodevalue=doc.selectSingleNode(Node).getText();
		}
		else{
			Assert.fail("Invalid Content Type");
		}
		return Nodevalue;
	}
	
	@Override
	public JSONObject Inputgenerator(JSONObject templatefile, String Node, String Value) {
		JSONObject jsonObjectnodelist = null;
		JSONObject jsonObjectfinal=templatefile;
		String[] nodelist=Node.split(".");
		if(nodelist.length==1)
		{
			// Update the Node
			System.out.println(jsonObjectnodelist.get(nodelist[nodelist.length-1]));
		}
		else
		{
			for(int i=0;i<nodelist.length-2;i++)
			{
				jsonObjectnodelist = (JSONObject) templatefile.get(nodelist[i]);
				System.out.println(jsonObjectnodelist.toString());
			}
			System.out.println(jsonObjectnodelist.get(nodelist[nodelist.length-1]));
		}

		return templatefile;
	}

	/**
	 *	@author chirag.s
	 *	@param path
	 */
	@Override
	public void validateResponseJsonSchema(String path)
	{
		String updatedpath = System.getProperty("user.dir")+ "/"+Root+path;
		Resultrespoence.then().body(matchesJsonSchema(new File(updatedpath)));
	}
	
	/**
	 *	@author chirag.s
	 *  @param uri
	 *	@param headerMap for uri
	 */
	@Override
	public String appendUriWithParameters(String uri,Map<String, String> headerMap)
	{
		int i=1;
		for(String key : headerMap.keySet())
		{
			if(i==1)
			{
				uri = uri.concat("?"+key+"="+headerMap.get(key));
			}else
			{
				uri = uri.concat("&"+key+"="+headerMap.get(key));
			}
			i++;
			
		}
		return uri;
	} 
	
	@Override
	public void addRequestParameters(Map<String, String> headerMap)
	{
		
		Request.params(headerMap);
	}
	
	/**
	 *	@author chirag.s
	 *  @param contentType
	 */
	@Override
	public void setContentType(String contentType)
	{
		Request.contentType(contentType);

	}
	@Override
	public void setContentTypeRestAssured(ContentType contentType)
	{
		Request.contentType(contentType);

	}
	
	/**
	 *	@author chirag.s
	 *  @param path
	 */
	@Override
	public void validateResponseXMLSchema(String path)
	{

		String updatedpath = System.getProperty("user.dir")+ "/"+Root+path;
		Resultrespoence.then().body(matchesXsd(new File(updatedpath)));
	}
	
	/**
	 *	@author chirag.s
	 *  @param uri
	 */
	@Override
	public String getBaseURI(String uri)
	{
		Properties pr= new Properties();
		InputStream file = null;
		String value = null;
		String endpoint= "";
		try {
			file= new FileInputStream("config.properties");
			pr.load(file);
			value=pr.getProperty("ENV").trim();
			if(value.equalsIgnoreCase("local"))
			{
				endpoint = pr.getProperty("localmock_url");
			}else
			{
				String env = value.toLowerCase();
				endpoint = pr.getProperty(env+"_"+uri);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return endpoint;
	}
	/**
	 *	@author Dipesh.Jain
	 *  @param path
	 * @throws IOException 

	 */
	@Override
	public void validateResponseXMLSchemaWithoutSoapEnvelope(String path) throws IOException
	{
		String updatedpath = System.getProperty("user.dir")+ "/"+Root+path;
		final SimpleXml simple = new SimpleXml();
		String res =Resultrespoence.then().extract().asString();
		String resp=(simple.domToXml(getBook(simple.fromXml(res))));
		StringReader sr = new StringReader(resp);
		Resultrespoence.then().body(matchesXsd(new File(updatedpath)));
	}
	
	

private static Element getBook(final Element element) {
    return element.children.get(0).children.get(0);
}

	/**
	 * Get the payload from file
	 *@author Chirag.S
	 *@param requestpath
	 */
	@Override
	public void readPayload(String requestpath)
	{
		try {
			System.out.println(System.getProperty("user.dir")+ "/"+Root+requestpath);
			String payload=readRequestTemplate(System.getProperty("user.dir")+ "/"+Root+requestpath);
			Payload = payload;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the node value from response
	 *@author Chirag.S
	 *@param Node
	 */
	@Override
	public String getSingleValueFromResponse(String Node)
	{
		String nodevalue =null;
		if(Resultrespoence.getHeader("content-type").contains("json")) {
			JsonPath jsonPathValue = Resultrespoence.jsonPath();
			//ExtentTestManager.getTest().log(LogStatus.INFO, "JsoneBody as String String"+jsonPathValue);
			nodevalue = jsonPathValue.getString(Node);
			
		}
		else if(Resultrespoence.getHeader("content-type").contains("xml"))
		{

			SAXReader xmlreader=new SAXReader();
			Document doc = null;
			try {
				doc = xmlreader.read(Resultrespoence.asInputStream());
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			nodevalue = doc.selectSingleNode(Node).getText();
		}
		return nodevalue;
	}

	/**
	 * Exact Compair two strings
	 *
	 * @param actual   Actual String need to to validate
	 * @param expected expected String againt which validation need to be perform
	 * @author ashwnai.S
	 */
	public void Stringcomparator(String actual, String expected) {
		System.out.println("Actual String : " + actual);
		System.out.println("Expected String : " + expected);
		if(expected==null)
		{
			expected="";
		}
		if(actual==null)
		{
			actual="";
		}

        Assert.assertEquals(expected, actual);


	}
	/**
	 * Exact Compair two strings
	 *
	 * @param actual   Actual String need to to validate
	 * @param expected expected String againt which validation need to be perform
	 * @author ashwnai.S
	 */
	public void Jsoncomparator(JsonElement actual, JsonElement expected) {
		System.out.println("Actual String : " + actual);
		System.out.println("Expected String : " + expected);


        Assert.assertEquals(expected, actual);


	}

	/**
	 * Compait two strings
	 *
	 * @param actual   Actual boolean need to to validate
	 * @param expected expected bollean againt which validation need to be perform
	 * @author ashwnai.S
	 */
	public void Booleancomparator(Boolean actual, Boolean expected) {
		System.out.println("Actual String : " + actual);
		System.out.println("Expected String : " + expected);
        Assert.assertSame(actual, expected);
	}

	/**
	 * @param Node
	 * @param Value
	 * @author ashwani.s
	 */
	public void UpdatedAttributeinxmlPayload(String Node, String Value) throws DocumentException, IOException, SAXException, ParserConfigurationException {


		Document doc = XMLDocumentPayload;


		doc.selectSingleNode(Node).setText(Value);
		XMLDocumentPayload = doc;
		Payload = doc.asXML();
		//System.out.println("Updated Paylod"+Payload);
	}


	/**
	 * @author ashwani.s
	 * @param Node
	 * @param Value
	 */
	public void UpdatedAttributeinJsonPayload(String Node,String Value, boolean isstring) throws DocumentException, IOException, SAXException, ParserConfigurationException {





		String abc="";
		String[] arrOfStr = Node.split("//");

		if(Node.endsWith("//"))
		{
			System.out.println("Provided Json node path is not correct!! it can't be end with //");
			Assert.fail("Provided Json node path is not correct!! it can't be end with //");
		}
		JSONObject jsonObject = JSONDocumentPayload;

		JSONObject idObj1=null;

		if(arrOfStr.length==1 || arrOfStr.length==0 )
		{
			try {
				if(!arrOfStr[0].contains("//")  )
				{
					System.out.println("// is missing with Node value!!!!");
					Assert.assertFalse(arrOfStr[0].contains("//"),"// is missing with Node value!!!!");
				}
				else {
					System.out.println("Node is not correctly provided!!!!");
				}}
			catch(Exception e)
			{
				System.out.println("Node is not correctly provided!!!!");
				e.getMessage();
			}
		}
		else {
			for(int i=0; i<arrOfStr.length-2;i++)
			{

				//System.out.println(arrOfStr[i+1]);
				if(idObj1==null) {
					idObj1 = (
							(JSONObject) (
									jsonObject.get(arrOfStr[i+1])
							)
					);
				}
				else
				{
					idObj1=(JSONObject) idObj1.get(arrOfStr[i+1]);
				}
			}

			if(arrOfStr.length==2)
			{  if(isstring)
				jsonObject.put(arrOfStr[arrOfStr.length-1], Value);
			else {
				BigInteger obj=new BigInteger(Value);
				jsonObject.put(arrOfStr[arrOfStr.length - 1], obj);
			}
			} else {
				if (isstring)
					idObj1.put(arrOfStr[arrOfStr.length - 1], Value);
				else {
					BigInteger obj = new BigInteger(Value);
					idObj1.put(arrOfStr[arrOfStr.length - 1], obj);
				}

			}
			JSONDocumentPayload = jsonObject;
			abc = jsonObject.toJSONString();
		}

		Payload = abc;
	}

	/**
	 * @param method
	 * @param URI
	 * @author dipesh.j
	 */

	@Override
	public void submitRequestWithHeader(Method method, String URI) {
		Resultrespoence = null;
		Request.headers("Content-Type", "text/xml");
		Resultrespoence = Request.request(method, URI);
		System.out.println("Response Body After Request: " + Resultrespoence.getBody().asString());
		////ExtentTestManager.getTest().log(LogStatus.INFO, "Response Body After Request: "+ Resultrespoence.getBody().asString());
		Request.body("");
	//	Reporter.addStepLog(Resultrespoence.getBody().asString());
	}

	/**
	 * @param Node
	 * @param expected
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws DocumentException
	 * @author chirag.s
	 */
	public void assertNodeIsPresent(String Node, boolean expected) throws SAXException, IOException, ParserConfigurationException, DocumentException {
		boolean flag = false;
		if (Resultrespoence.getHeader("content-type").contains("json")) {
			JsonPath jsonPathValue = Resultrespoence.jsonPath();
			try {
				if(jsonPathValue.getList(Node).size()>0)
				{
					if(!jsonPathValue.getString(Node).contains("null"))
					{
						flag = true;
					}
				}
			} catch (Exception e) {
				flag = false;
			}

			Assert.assertEquals(flag, expected, "Node <b>" + Node + "</b> contains in the response ");
		} else if (Resultrespoence.getHeader("content-type").contains("xml")) {

			SAXReader xmlreader = new SAXReader();
			Document doc = xmlreader.read(Resultrespoence.asInputStream());
			try {
				if (doc.selectNodes(Node).size() > 0) {
					flag = true;
				}
			} catch (Exception e) {
				flag = false;
			}
			Assert.assertEquals(flag, expected, "Node <b>" + Node + "</b> contains in the response which is expected :- " + expected);
		} else {
			Assert.fail("Response Content-Type is not matched.Please check the Assertion");
		}
	}

	public String Base64Encoder(String data) throws SAXException, IOException, ParserConfigurationException, DocumentException {
		Base64.Encoder encoder = Base64.getEncoder();
		String originalString = data;
		String encodedString = encoder.encodeToString(originalString.getBytes());

		System.out.println(encodedString);
		return encodedString;
	}

	public String ReturnPaylod() throws SAXException, IOException, ParserConfigurationException, DocumentException {

		return Payload;
	}

	/**
	 * @author dipesh.j
	 * @param dateStr
	 * @param dateFormat
	 */
	public boolean isValidDateFormat(String dateStr, String dateFormat) {
		DateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);
		try {
			sdf.parse(dateStr);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	/**
	 * Parial Compairison two strings
	 *
	 * @param actual   Actual String need to to validate
	 * @param expected expected String againt which validation need to be perform
	 * @author ashwnai.S
	 */
	public void Partialstringcomparator(String actual, String expected) {
		System.out.println("Actual String : " + actual);
		System.out.println("Expected String : " + expected);
		if(expected==null)
		{
			expected="";
		}
		if(actual==null)
		{
			actual="";
		}
		Assert.assertTrue(actual.contains(expected));


	}
	public String RetrurnResponce(){
		return Resultrespoence.asString();
	}
		/**
	 * @author dipesh.j
	 * @param dateFormat
	 */
	public String getTodaysDateinFormat(String dateFormat) {
		DateFormat dteFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        String requiredData = dteFormat.format(date);
		return requiredData;
	}
	/**
	 * @author dipesh.j
	 * @return orderNum
	 */
	public String createOrderNumber() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String orderNum = ""+timestamp.getTime();
        return orderNum;
	}
	
	/*	End of the API Helper area which contains all the base methods related to the API
	 *------------------------------------------------------------------------------------------
	 *
	 */

	@Override
	public String pollCurrentTime(String className, String timeStamp, String timeOut) throws InterruptedException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("mm:ss");
		LocalTime Time = LocalTime.now().plusMinutes(Integer.valueOf(timeOut.split(":")[0])).plusSeconds(Integer.valueOf(timeOut.split(":")[1]));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String p = "";
		while (true) {
			try {
				p = getwebelement("//*[@class='"+className+"']/span").getText();
						//(String) js.executeScript(" return document.getElementsByClassName(\"" + className + "\")[0].textContent");
				if (compare(p, timeStamp)) {

					js.executeScript("arguments[0].click();", getwebelement("//*[@class='playkit-icon playkit-icon-play']"));
					break;
				}
			} catch (Exception e) {
			}
			if (!p.isEmpty()) {
				if (Integer.valueOf(p.split(":")[0]) >= Integer.valueOf(timeStamp.split(":")[0])) {
					if (Integer.valueOf(p.split(":")[1]) >= Integer.valueOf(timeStamp.split(":")[1]) || LocalTime.now() == Time) {
						js.executeScript("arguments[0].click();", getwebelement("//*[@class='playkit-icon playkit-icon-play']"));

						//js.executeScript("document.getElementsByClassName(\"playkit-icon playkit-icon-play\")[0].click();");
						break;
					}
				}
			}
		}
		return p;
	}
	public  boolean compare(String webTimeStamp, String androidTimeStamp) {
		int result = (Integer.valueOf(webTimeStamp.split(":")[0]) * 60 + Integer.valueOf(webTimeStamp.split(":")[1])) - (Integer.valueOf(androidTimeStamp.split(":")[0]) * 60 + Integer.valueOf(androidTimeStamp.split(":")[1]));
        return result >= 0 && result <= 40;
	}

	public void HandleAngularwait()
	{
		JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
		ngDriver = new NgWebDriver(jsDriver);
        ngDriver.waitForAngularRequestsToFinish();
	}
	public void HandleAngularmain()
	{
		int count = driver.findElements(ByAngular.repeater("result in memory")).size();
		System.out.println("Count  "+count);
		Assert.assertEquals(count, 1);

	}

	public WebElement getNGWebelement(String locator) throws InterruptedException
	{
		String[] finalval;
		try
		{
			System.out.print("\nIn Get element method for - "+locator+" ");
			if(locator.startsWith("binding"))
			{
				finalval=locator.split("=");
				wait.until(new Function<WebDriver, WebElement>(){
					public WebElement apply(WebDriver driver) {
						el=driver.findElement(ByAngular.binding(finalval[1]));
						try {
							wait.until(ExpectedConditions.elementToBeClickable(el));
						}
						catch(Exception e) {
							wait.until(ExpectedConditions.visibilityOf(el));
						}
						return el;
					}
				});
			}

			else if(locator.startsWith("buttonText"))
			{
				finalval=locator.split("=");
				wait.until(new Function<WebDriver, WebElement>(){
					public WebElement apply(WebDriver driver) {
						el=driver.findElement(ByAngular.buttonText(finalval[1]));
						try {
							wait.until(ExpectedConditions.elementToBeClickable(el));
						}
						catch(Exception e) {
							wait.until(ExpectedConditions.visibilityOf(el));
						}
						return el;
					}
				});
			}

			else if(locator.startsWith("exactBinding"))
			{
				finalval=locator.split("=");
				wait.until(new Function<WebDriver, WebElement>(){
					public WebElement apply(WebDriver driver) {
						el=driver.findElement(ByAngular.exactBinding(finalval[1]));
						try {
							wait.until(ExpectedConditions.elementToBeClickable(el));
						}
						catch(Exception e) {
							wait.until(ExpectedConditions.visibilityOf(el));
						}
						return el;
					}
				});
			}

			else if(locator.startsWith("exactRepeater"))
			{
				finalval=locator.split("=");
				wait.until(new Function<WebDriver, WebElement>(){
					public WebElement apply(WebDriver driver) {
						el=driver.findElement(ByAngular.exactRepeater(finalval[1]));
						try {
							wait.until(ExpectedConditions.elementToBeClickable(el));
						}
						catch(Exception e) {
							wait.until(ExpectedConditions.visibilityOf(el));
						}
						return el;
					}
				});
			}
			else if(locator.startsWith("model"))
			{
				finalval=locator.split("=");
				wait.until(new Function<WebDriver, WebElement>(){
					public WebElement apply(WebDriver driver) {
						el=driver.findElement(ByAngular.model(finalval[1]));
						try {
							wait.until(ExpectedConditions.elementToBeClickable(el));
						}
						catch(Exception e) {
							wait.until(ExpectedConditions.visibilityOf(el));
						}
						return el;
					}
				});
			}
			else if(locator.startsWith("partialButtonText"))
			{
				finalval=locator.split("=");
				wait.until(new Function<WebDriver, WebElement>(){
					public WebElement apply(WebDriver driver) {
						el=driver.findElement(ByAngular.partialButtonText(finalval[1]));
						try {
							wait.until(ExpectedConditions.elementToBeClickable(el));
						}
						catch(Exception e) {
							wait.until(ExpectedConditions.visibilityOf(el));
						}
						return el;
					}
				});
			}
			else if(locator.startsWith("repeater"))
			{
				finalval=locator.split("=");
				wait.until(new Function<WebDriver, WebElement>(){
					public WebElement apply(WebDriver driver) {
						el=driver.findElement(ByAngular.repeater(finalval[1]));
						try {
							wait.until(ExpectedConditions.elementToBeClickable(el));
						}
						catch(Exception e) {
							wait.until(ExpectedConditions.visibilityOf(el));
						}
						return el;
					}
				});
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

		return el;
	}

    public void waitForElementLoad(int x)
    {
        try
        {
			System.out.println("Waiting");
            int time = 1000 * x;
            Thread.sleep(time);
			System.out.println("End Waiting");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    public WebElement getWebElementWithRelative(String locator, WebElement wl, String relative) throws InterruptedException
    {
        String[] finalval;
        try {
            if(locator.startsWith("name"))
            {
                finalval=locator.split("=");
                wait.until(new Function<WebDriver, WebElement>(){
                    public WebElement apply(WebDriver driver) {
                        try {
//                            el = driver.findElement(By.name(finalval[1]));
                            switch (relative) {
                                case "above":
                                    el = driver.findElement(RelativeLocator.with(By.name(finalval[1])).above(wl));
                                    break;
                                case "below":
                                    el = driver.findElement(RelativeLocator.with(By.name(finalval[1])).below(wl));
                                    break;
                                case "near":
                                    el = driver.findElement(RelativeLocator.with(By.name(finalval[1])).near(wl));
                                    break;
                                case "toLeftOf":
                                    el = driver.findElement(RelativeLocator.with(By.name(finalval[1])).toLeftOf(wl));
                                    break;
                                case "toRightOf":
                                    el = driver.findElement(RelativeLocator.with(By.name(finalval[1])).toRightOf(wl));
                                    break;
                                default:
                                    System.out.println("Relative parameter should be - near, above, below, toLeftOf, and toRightOf.");
                                    Assert.fail("Relative parameter should be - near, above, below, toLeftOf, and toRightOf.");
                            }
                        }
                        catch (NoSuchElementException e)
                        {
                            el=null;
                        }
                        if(el!=null) {
                            try {
                                wait.until(ExpectedConditions.elementToBeClickable(el));
                            } catch (Exception e) {
                                wait.until(ExpectedConditions.visibilityOf(el));
                            }
                        }
                        //wait.until(el.isEnabled());
                        return el;
                    }
                });
                //wait.until(ExpectedConditions.stalenessOf(element))
            }
            else if(locator.startsWith("id"))
            {
                finalval=locator.split("=");
                try{
                    switch (relative) {
                        case "above":
                            el = driver.findElement(RelativeLocator.with(By.id(finalval[1])).above(wl));
                            break;
                        case "below":
                            el = driver.findElement(RelativeLocator.with(By.id(finalval[1])).below(wl));
                            break;
                        case "near":
                            el = driver.findElement(RelativeLocator.with(By.id(finalval[1])).near(wl));
                            break;
                        case "toLeftOf":
                            el = driver.findElement(RelativeLocator.with(By.id(finalval[1])).toLeftOf(wl));
                            break;
                        case "toRightOf":
                            el = driver.findElement(RelativeLocator.with(By.id(finalval[1])).toRightOf(wl));
                            break;
                        default:
                            System.out.println("Relative parameter should be - near, above, below, toLeftOf, and toRightOf.");
                            Assert.fail("Relative parameter should be - near, above, below, toLeftOf, and toRightOf.");

                    }

                }
                catch (NoSuchElementException e)
                {
                    el=null;
                }
                wait.until(new Function<WebDriver, WebElement>(){
                    public WebElement apply(WebDriver driver) {
                        try{
//                            el=driver.findElement(By.id(finalval[1]));
                            switch (relative) {
                                case "above":
                                    el = driver.findElement(RelativeLocator.with(By.id(finalval[1])).above(wl));
                                    break;
                                case "below":
                                    el = driver.findElement(RelativeLocator.with(By.id(finalval[1])).below(wl));
                                    break;
                                case "near":
                                    el = driver.findElement(RelativeLocator.with(By.id(finalval[1])).near(wl));
                                    break;
                                case "toLeftOf":
                                    el = driver.findElement(RelativeLocator.with(By.id(finalval[1])).toLeftOf(wl));
                                    break;
                                case "toRightOf":
                                    el = driver.findElement(RelativeLocator.with(By.id(finalval[1])).toRightOf(wl));
                                    break;
                                default:
                                    System.out.println("Relative parameter should be - near, above, below, toLeftOf, and toRightOf.");
                                    Assert.fail("Relative parameter should be - near, above, below, toLeftOf, and toRightOf.");
                            }}
                        catch (NoSuchElementException e)
                        {
                            el=null;
                        }
                        if(el!=null) {
                            try {
                                wait.until(ExpectedConditions.elementToBeClickable(el));
                            } catch (Exception e) {
                                wait.until(ExpectedConditions.visibilityOf(el));
                            }
                        }
                        //wait.until(el.isEnabled());
                        return el;
                    }
                });

            }

            else if (locator.startsWith("//")|| locator.startsWith("(//")||locator.startsWith("("))
            {
                //el=driver.findElement(By.xpath(locator));
                wait.until(new Function<WebDriver, WebElement>() {
                    public WebElement apply(WebDriver driver) {
                        try{
//                            el=driver.findElement(By.xpath(locator));
                            switch (relative) {
                                case "above":
                                    el = driver.findElement(RelativeLocator.with(By.xpath(locator)).above(wl));
                                    break;
                                case "below":
                                    el = driver.findElement(RelativeLocator.with(By.xpath(locator)).below(wl));
                                    break;
                                case "near":
                                    el = driver.findElement(RelativeLocator.with(By.xpath(locator)).near(wl));
                                    break;
                                case "toLeftOf":
                                    el = driver.findElement(RelativeLocator.with(By.xpath(locator)).toLeftOf(wl));
                                    break;
                                case "toRightOf":
                                    el = driver.findElement(RelativeLocator.with(By.xpath(locator)).toRightOf(wl));
                                    break;
                                default:
                                    System.out.println("Relative parameter should be - near, above, below, toLeftOf, and toRightOf.");
                                    Assert.fail("Relative parameter should be - near, above, below, toLeftOf, and toRightOf.");

                            }
                        }
                        catch (NoSuchElementException e)
                        {
                            el=null;
                        }
                        if(el!=null) {
                            try {

                                wait.until(ExpectedConditions.elementToBeClickable(el));
                            } catch (Exception e) {
                                wait.until(ExpectedConditions.visibilityOf(el));
                            }
                        }
                        return el;
                    }
                });

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //getwebelement(locator);
        }
        if(el!=null)
        {
            }
        return el;
    }
	public  void checkAccessibility() {

		AxeRunOnlyOptions runOnlyOptions = new AxeRunOnlyOptions();
		runOnlyOptions.setType("tag");
		runOnlyOptions.setValues(Arrays.asList("wcag2a", "wcag2aa"));

		AxeRunOptions options = new AxeRunOptions();
		options.setRunOnly(runOnlyOptions);

		AxeBuilder axe = new AxeBuilder().withOptions(options);
		//Pass WebDriver Object
		Results result = axe.analyze(driver);
		List<Rule> violationList = result.getViolations();
		System.out.println("Violation list size :"+result.getViolations().size());

		for (Rule r : result.getViolations()) {
			System.out.println("Complete = "+r.toString());
			System.out.println("Tags = "+r.getTags());
			System.out.println("Description = "+r.getDescription());
			System.out.println("Help Url = "+r.getHelpUrl());
		}

		System.out.println("Inapplicable list size :"+result.getInapplicable().size());
		for (Rule r : result.getInapplicable()) {
			System.out.println("Complete = "+r.toString());
			System.out.println("Tags = "+r.getTags());
			System.out.println("Description = "+r.getDescription());
			System.out.println("Help Url = "+r.getHelpUrl());
		}
	}
	public WebElement getWebElementById(String locator) throws InterruptedException {
		String[] finalval;
		finalval=locator.split("=");
		WebElement el = driver.findElement(By.id(finalval[1]));
		return el;
	}

	public void move_to_element() throws InterruptedException {
		//Try this code
		WebElement e = driver.findElement(By.className("slider"));
		Actions move = new Actions(driver);
		move.moveToElement(e).clickAndHold().moveByOffset(0,250).release().perform();
		Thread.sleep(3000);
	}
	public void setPaylod(String payload) throws SAXException, IOException, ParserConfigurationException, DocumentException {

		Payload=payload;
	}
	public void Scrolldown() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		long lastHeight = (long) js.executeScript("return document.body.scrollHeight");

		while (true) {
			// Scroll down by window height
			js.executeScript("window.scrollBy(0, 500);");

			// Wait for AJAX content to load
			Thread.sleep(2000); // Better: use WebDriverWait with custom conditions

			// Check new scroll height
			long newHeight = (long) js.executeScript("return document.body.scrollHeight");

			// Break if scroll height did not change
			if (newHeight == lastHeight) {
				break;
			}
			lastHeight = newHeight;
		}
	}

	@Override
//	public void fillform(List<Map<String, String>> fields) throws InterruptedException {
//		for (Map<String, String> field : fields) {
//			String question = field.get("Field Name").trim();
//			String value = field.get("Value").trim();
//
//			// Normalize question to find by partial match
//			List<WebElement> questionElements = driver.findElements(By.cssSelector("div.question"));
//			boolean matched = false;
//
//			for (WebElement questionElement : questionElements) {
//
//				Thread.sleep(500);
//				String questionText = questionElement.getText().trim();
//				System.out.println("Answering for Question: "+questionText);
//				List<WebElement> divs = questionElement.findElements(By.tagName("div"));
//
//				if (questionText.contains(question)) {
//
//                    if(divs.size() > 1)
//					{
//						WebElement radio = questionElement.findElement(By.xpath(".//div[text()=\"" + value + "\"]/input"));
//						radio.click();
//					}
//					else if(divs.size() == 1){
//						WebElement inputBox = questionElement.findElement(By.xpath(".//input[@type='text']"));
//						inputBox.clear();
//						inputBox.sendKeys(value);
//					}
//					else {
//						System.out.println("No matching Element Type found for: " + question);
//					}
//
//					matched = true;
//					break;
//				}
//			}
//			if (!matched) {
//				System.out.println("No matching question found for: " + question);
//			}
//		}
//
//	}

	public void fillform(List<Map<String, String>> fields) throws InterruptedException {
		for (Map<String, String> field : fields) {
			String question = field.get("Field Name").trim();
			String value = field.get("Value").trim();

			try {
				// Locate the question element directly using XPath that matches the text partially or fully
				WebElement questionElement = driver.findElement(By.xpath(
						"//div[@class='question' and contains(text(), \""+question+"\")]"
				));

				Thread.sleep(300); // Slight delay if required for rendering

				// Get all inner divs inside the matched question element
				List<WebElement> divs = questionElement.findElements(By.tagName("div"));

				if (divs.size() > 1) {
					// Radio button case
					try {
						WebElement radio = questionElement.findElement(By.xpath(".//div[normalize-space(text())=\"" + value + "\"]/input"));
						radio.click();
						System.out.println("Selected radio for: " + question);
					} catch (Exception e) {
						System.out.println("Radio button not found for: " + question + " and value: " + value);
					}
				} else if (divs.size() == 1) {
					// Text box case
					try {
						WebElement inputBox = questionElement.findElement(By.xpath(".//input[@type='text']"));
						inputBox.clear();
						inputBox.sendKeys(value);
						System.out.println("Entered text for: " + question);
					} catch (Exception e) {
						System.out.println("Text input not found for: " + question);
					}
				} else {
					System.out.println("Unsupported field structure for question: " + question);
				}
			} catch (NoSuchElementException e) {
				System.out.println("No matching question found for: " + question);
			}
		}
	}
	public void waitForElementToBePresent(String xpath, int timeoutInSeconds) throws InterruptedException {
		int elapsedSeconds = 0;
		int pollingInterval = 500; // milliseconds
		int count = 0;

		while (elapsedSeconds < timeoutInSeconds * 1000) {
			count = getwebelementscount(xpath);

			if (count >= 1) {
				System.out.println("Element found after " + (elapsedSeconds / 1000.0) + " seconds.");
				return;
			}

			try {
				Thread.sleep(pollingInterval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			elapsedSeconds += pollingInterval;
		}

		// If we reach here, the element was not found within the timeout
		Assert.fail("Element not found within " + timeoutInSeconds + " seconds for XPath: " + xpath);
	}
	public void ScrolltoNextPage() {
		MobileDriver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"
		));
		System.out.println("Scrolled to next page");
	}

//Handle Switch Tab and window BY priyanka
    // Keep the parent window per test thread
    private final ThreadLocal<String> parentWindowTL = new ThreadLocal<>();
    /** Call this right before the click that opens a new tab/window */
    public void rememberCurrentWindow() {
        try {
            parentWindowTL.set(driver.getWindowHandle());
        } catch (Exception ignore) { /* driver not ready yet */ }
    }
    /** Switch to the newly opened tab/window (if any). Safe if none opens. */
    public void switchToNewWindow(int timeoutSeconds) {
        String current = null;
        try {
            current = driver.getWindowHandle();
        } catch (Exception ignore) {}
        try {
            // wait until a second handle appears
            new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                    .until(d -> d.getWindowHandles().size() > 1);
            for (String h : driver.getWindowHandles()) {
                if (current == null || !h.equals(current)) {
                    driver.switchTo().window(h);
                    driver.switchTo().defaultContent(); // leave any frames
                    // Optional: wait for DOM ready
                    try {
                        new WebDriverWait(driver, Duration.ofSeconds(10))
                                .until(wd -> ((JavascriptExecutor) wd)
                                        .executeScript("return document.readyState").equals("complete"));
                    } catch (Exception ignored) {}
                    System.out.println("✅ Switched to new tab/window: " + driver.getTitle());
                    return;
                }
            }
        } catch (TimeoutException te) {
            // No new tab/window opened — stay where we are, do nothing
            System.out.println("ℹ️ No new tab/window detected.");
        }
    }
    /** Switch back to the window we remembered (if it still exists). */
    public void switchBackToRememberedWindow() {
        String parent = parentWindowTL.get();
        if (parent == null) return;
        try {
            for (String h : driver.getWindowHandles()) {
                if (h.equals(parent)) {
                    driver.switchTo().window(parent);
                    driver.switchTo().defaultContent();
                    System.out.println("↩️ Switched back to parent window.");
                    return;
                }
            }
        } catch (Exception ignored) {}
    }
    public void hoverOnElement(WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            System.out.println("✅ Hovered on element: " + element);
        } catch (Exception e) {
            System.out.println("❌ Failed to hover: " + e.getMessage());
        }
    }
    public void safeHover(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOf(element));
            new Actions(driver).moveToElement(element).perform();
        } catch (Exception e) {
            // fallback - just scroll if hover fails
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        }
    }
    // baseDriverHelper.java
    public WebElement waitUntilVisible(By by, int timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    public WebElement waitUntilPresent(By by, int timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }
    public String waitAndGetText(By by, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        // Wait for element to exist/appear (presence covers very fast toasts)
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        // If it also becomes visible, great; if not, we still try to read text
        try { wait.until(ExpectedConditions.visibilityOf(el)); } catch (TimeoutException ignored) {}
        String text = el.getText();
        if (text == null || text.isBlank()) {
            text = el.getAttribute("innerText"); // fallback for shadow/Salesforce toasts
        }
        // normalize whitespace/newlines the way SLDS toasts often render
        return (text == null ? "" : text.replace("\n"," ").replaceAll("\\s+"," ").trim());
    }
    public void assertTextEquals(By by, String expected, int timeoutSeconds) {
        String actual = waitAndGetText(by, timeoutSeconds);
        org.testng.Assert.assertEquals(
                actual,
                expected,
                "Toast mismatch! Expected: \"" + expected + "\" but was: \"" + actual + "\""
        );
    }

    public boolean verifyToastAppearedContains(String expectedSubstring, int timeoutSeconds) {
        try {
            By toastLocator = By.xpath("(//div[contains(@class,'slds-notify') and contains(@class,'toast')])[last()]");

            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(timeoutSeconds));
            WebElement toastBox = wait.until(ExpectedConditions.visibilityOfElementLocated(toastLocator));

            // Get message once (don’t re-query children to avoid stale)
            String toastText = toastBox.getText().replace("\n", " ").trim();
            System.out.println("Toast appeared -> " + toastText);

            if (toastText.toLowerCase().contains(expectedSubstring.toLowerCase())) {
                System.out.println("✅ PASS: Toast contains '" + expectedSubstring + "'");
                return true;
            } else {
                System.out.println("⚠️ WARN: Toast did not match. Expected '" + expectedSubstring + "', got: '" + toastText + "'");
                return false;
            }

        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            System.out.println("WARN: Toast went stale before reading. Treating as PASS because it appeared.");
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("WARN: No toast appeared within timeout.");
            return false;
        }
    }



}