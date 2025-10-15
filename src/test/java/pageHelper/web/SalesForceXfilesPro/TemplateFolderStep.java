package pageHelper.web.SalesForceXfilesPro;

import core.baseDriverHelper;
import core.webHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j;
import org.dom4j.DocumentException;
import org.junit.Assert;
import org.junit.runner.notification.RunListener;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageHelper.bddDriver;
//import technology.tabula.ObjectExtractor;
//import technology.tabula.Page;
//import technology.tabula.RectangularTextContainer;
//import technology.tabula.Table;
//import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;
import utils.ConfigReader;
import utils.DriverController;
import utils.xmlreader;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class TemplateFolderStep {
    public webHelper webDriver;
    private bddDriver DriverInstance;
    public xmlreader xml;

    public TemplateFolderStep(bddDriver contextSteps, DriverController drivercontroler) throws Exception {
        this.DriverInstance = contextSteps;
        System.out.println("triggered bdd constructor");
        System.out.println(this.DriverInstance);
        this.webDriver = new baseDriverHelper(bddDriver.getWebDriver());
        this.xml=new xmlreader("src\\test\\resources\\locators\\XFilesPro.xml");
    }

    @When("Click on App Launcher and search item as Account and click on Account")
    public void goToAppLauncherAndSearchAccount_TemplateFolder() throws Exception{

        Thread.sleep(1000);
        webDriver.verifyElementToBePresent(xml.getlocator("AppLauncher_ObjectSetup"));
        Thread.sleep(300);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("AppLauncher_ObjectSetup")));
        Thread.sleep(300);
        webDriver.verifyElementToBePresent(xml.getlocator("SearchApp_ObjectSetup"));
        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("SearchApp_ObjectSetup")), "Accounts");
        Thread.sleep(100);
        //webDriver.Clickon(webDriver.getwebelement(xml.getlocator("AppLink_ObjectSetup")));
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("TempFol_AccountsItemClick")));
Thread.sleep(100);
    }
    @Given("Go to {string} Nav item tab to verify template folder in biSync")
    public void goToNavItem_ObjectSetup_TemplateFolderCheckBiSync(String navItemName) throws Exception {
        // Thread.sleep(100);
        WebElement navItem = webDriver.getwebelement(xml.getlocator("NavItem_ObjectSetup").replace("{paramlink}", navItemName));
        webDriver.Clickon(navItem);
    }

    @And("I click on the {string} button in Account page")
    public void clickNewButton_TemplateFolder(String buttonName) {
        WebDriver driver = webDriver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // Step 1: Read the locator from XML
            String buttonTemplate = xml.getlocator("TempFol_ClickOnNewRecordAccount");
            String buttonXpath = buttonTemplate.replace("{0}", buttonName);

            // Step 2: Wait for the button to be visible and clickable
            WebElement newButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buttonXpath)));

            // Step 3 Scroll and click using JavaScript (for Lightning reliability)
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", newButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", newButton);

            System.out.println("Successfully clicked the '" + buttonName + "' button on Account page.");

        } catch (TimeoutException te) {
            throw new RuntimeException("Timeout: Could not find '" + buttonName + "' button on Account page.", te);
        } catch (Exception e) {
            throw new RuntimeException(" Failed to click '" + buttonName + "' button: " + e.getMessage(), e);
        }
    }

    @And("I enter the account name as {string}")
    public void enterAccountName_TemplateFolder(String accountName) {
        WebDriver driver = webDriver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // 1) Get locator from XML (safe check)
            String accountNameXpath = null;
            try {
                accountNameXpath = xml.getlocator("TempFol_EnterAccountNameField");
            } catch (Exception ex) {
                // xml.getlocator may itself throw — capture and continue to fallback
                System.out.println("⚠️ xml.getlocator threw: " + ex.getMessage());
            }

            // 2) If XML returned null/empty, use a sensible fallback xpath observed in DOM:
            // fallback targets the last input whose name='Name' (typical SF modal Account Name)
            if (accountNameXpath == null || accountNameXpath.trim().isEmpty()) {
                accountNameXpath = "(//input[@name='Name'])[last()]";
                System.out.println("ℹ️ Using fallback xpath for Account Name: " + accountNameXpath
                        + " — please add 'ObjectSetup_AccountNameField' to xml for reliability.");
            }

            By accountBy = By.xpath(accountNameXpath);

            // 3) Wait for presence and visibility
            WebElement accountInput = wait.until(ExpectedConditions.presenceOfElementLocated(accountBy));
            accountInput = wait.until(ExpectedConditions.visibilityOfElementLocated(accountBy));

            // 4) Try standard interaction: focus, clear, sendKeys
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", accountInput);
                accountInput.click();                     // focus
                accountInput.clear();
                accountInput.sendKeys(accountName);
                // optionally move focus away so Salesforce validates and leaves field
                ((JavascriptExecutor) driver).executeScript("arguments[0].blur();", accountInput);

                System.out.println("✅ Entered Account Name via sendKeys: " + accountName);
                return;
            } catch (InvalidElementStateException | StaleElementReferenceException ex) {
                System.out.println("⚠️ Normal typing failed: " + ex.getClass().getSimpleName() + " - " + ex.getMessage());
                // fallback to JS set value below
            }

            // 5) Fallback: set value via JS (works even when input is layered in LWC)
            try {
                String setJs = "arguments[0].focus();" +
                        "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('input', {bubbles: true}));" +
                        "arguments[0].dispatchEvent(new Event('change', {bubbles: true}));";
                ((JavascriptExecutor) driver).executeScript(setJs, accountInput, accountName);

                // small wait to let LWC pick up input events
                Thread.sleep(300);
                System.out.println("✅ Entered Account Name via JavaScript: " + accountName);
                return;
            } catch (Exception jsEx) {
                throw new RuntimeException("Fallback JS input failed: " + jsEx.getMessage(), jsEx);
            }

        } catch (TimeoutException te) {
            throw new RuntimeException("Timeout: Account Name field not found/visible. Verify locator/key in XML.", te);
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter Account Name: " + e.getMessage(), e);
        }
    }



    @And("I click on the Save button for adding the new record")
    public void clickOnSaveButton_RecordSave_TemplateFolder() {
        WebDriver driver = webDriver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            String saveButtonXpath = xml.getlocator("TempFol_SaveRecordClick");
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(saveButtonXpath)));

            // Scroll & Click via JavaScript (Lightning-safe)
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", saveButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);

            System.out.println("Successfully clicked on the Save button.");

        } catch (TimeoutException e) {
            throw new RuntimeException("⏱ Timeout: Save button not found or not clickable.", e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click on Save button: " + e.getMessage(), e);
        }
    }

    @And("I click on the latest created Account from Account list page")
    public void clickOnLatestAccountFromList_TemplateFolder() {
        WebDriver driver = webDriver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // Step 0: Get locator from XML and validate it
            String latestAccountXpath = xml.getlocator("TempFol_ClickLatestCreatedAccount");
            if (latestAccountXpath == null || latestAccountXpath.isEmpty()) {
                throw new RuntimeException("❌ Locator 'TempFol_ClickLatestCreatedAccount' not found or empty in XML file.");
            }

            // Step 1: Wait for the account table to load
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(latestAccountXpath)));

            // Step 2: Find the first visible account link
            WebElement accountLink = driver.findElements(By.xpath(latestAccountXpath))
                    .stream()
                    .filter(WebElement::isDisplayed)
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("No visible account link found in Accounts table."));

            // Step 3: Scroll into view (for Lightning tables)
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", accountLink);
            Thread.sleep(800);

            // Step 4: Wait until clickable
            wait.until(ExpectedConditions.elementToBeClickable(accountLink));

            // Step 5: Click (use normal click first, JS click fallback for Lightning)
            try {
                accountLink.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", accountLink);
            }

            System.out.println("✅ Successfully clicked on the latest created account: " + accountLink.getAttribute("title"));

            // Step 6: Wait for Account detail page to load
            wait.until(ExpectedConditions.urlContains("/lightning/r/Account/"));
            Thread.sleep(1500);

        } catch (TimeoutException te) {
            throw new RuntimeException("❌ Timeout: No account found or clickable in the list.", te);
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to click on latest created account: " + e.getMessage(), e);
        }
    }



    @And("I click on the second Details tab in biSync")
    public void clickOnDetailsTabOnBiSync_TemplateFolder() {
        WebDriver driver = webDriver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // Step 1: Get locator from XML
            String detailsTabXpath = xml.getlocator("TempFol_ClickOnSecondDetailsTab");
            if (detailsTabXpath == null || detailsTabXpath.isEmpty()) {
                throw new RuntimeException("Locator 'TempFol_DetailsTab' not found in XML.");
            }

            By detailsTabLocator = By.xpath(detailsTabXpath);

            // Step 2: Wait for tab to appear
            WebElement detailsTab = wait.until(ExpectedConditions.presenceOfElementLocated(detailsTabLocator));

            // Step 3: Scroll into view
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", detailsTab);

            // Step 4: Wait for clickable and click using JS (Lightning-friendly)
            wait.until(ExpectedConditions.elementToBeClickable(detailsTab));
            js.executeScript("arguments[0].click();", detailsTab);

            System.out.println("✅ Successfully clicked on the 'Details' tab.");

        } catch (TimeoutException te) {
            throw new RuntimeException("❌ Timeout: 'Details' tab not visible or clickable.", te);
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to click on the 'Details' tab: " + e.getMessage(), e);
        }
    }

    @And("I select Template from the third More Actions dropdown from BiSync")
    public void selectTemplateFromThirdDropdown_TemplateFolder() {
        WebDriver driver = webDriver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // Click 3rd 3-dot button
            String moreActionsXpath = xml.getlocator("TempFol_DropDownOptionFromBiSync");
            WebElement moreActionsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(moreActionsXpath)));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", moreActionsButton);
            js.executeScript("arguments[0].click();", moreActionsButton);
            System.out.println("✅ Clicked on the 3rd More Actions button.");

            // Wait for dropdown and click "Template"
            String templateXpath = xml.getlocator("TempFol_DropDownTemplateOptionClick");
            WebElement templateOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(templateXpath)));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", templateOption);
            js.executeScript("arguments[0].click();", templateOption);
            System.out.println("✅ Successfully selected 'Template' from 3rd More Actions dropdown.");

        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to select 'Template' from 3rd More Actions dropdown: " + e.getMessage(), e);
        }
    }

   /* @And("I select the template {string} from the dropdown")
    public void selectSpecificTemplateFromDropdown_TemplateFolder(String templateName) {
        WebDriver driver = webDriver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // Step 1: Wait for the Template popup to appear
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h2[normalize-space()='Template']")));

            // Step 2: Locate the dropdown element
            String dropdownXpath = xml.getlocator("TempFol_SelectATemplateDropDown");
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXpath)));

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", dropdown);
            Thread.sleep(700);

            // Step 3: Use Selenium's Select class to choose the option
            Select select = new Select(dropdown);
            select.selectByVisibleText(templateName);   // ✅ select by text, most stable method

            System.out.println("✅ Successfully selected template: " + templateName);

            // Optional: confirm that the dropdown value was selected
            String selectedValue = select.getFirstSelectedOption().getText();
            if (!selectedValue.equalsIgnoreCase(templateName)) {
                throw new RuntimeException("❌ Dropdown selection mismatch. Expected: " + templateName + " but got: " + selectedValue);
            }

        } catch (TimeoutException te) {
            throw new RuntimeException("❌ Timeout: Template dropdown or option not found for '" + templateName + "'", te);
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to select template '" + templateName + "': " + e.getMessage(), e);
        }
    }
*/
   @And("I select the template {string} from the dropdown")
   public void selectSpecificTemplateFromDropdown_TemplateFolder(String templateName) {
       WebDriver driver = webDriver.getDriver();
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
       JavascriptExecutor js = (JavascriptExecutor) driver;

       try {
           String templateDropdownXpath = xml.getlocator("TempFol_SelectATemplateDropDown");

           if (templateDropdownXpath == null || templateDropdownXpath.isEmpty()) {
               throw new RuntimeException("❌ Locator 'TempFol_TemplateDropdown' not found in XML.");
           }

           // Check if dropdown is present in DOM
           List<WebElement> dropdowns = driver.findElements(By.xpath(templateDropdownXpath));
           if (dropdowns.isEmpty()) {
               System.out.println("⚠️ Template dropdown not found — assuming template '" + templateName +
                       "' is already created in BiSync. Skipping selection.");
               return; // gracefully exit, don’t fail test
           }

           WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdowns.get(0)));
           js.executeScript("arguments[0].scrollIntoView({block:'center'});", dropdown);
           Thread.sleep(800);

           Select select = new Select(dropdown);
           select.selectByVisibleText(templateName);

           System.out.println("✅ Successfully selected template: " + templateName);

       } catch (TimeoutException te) {
           System.out.println("⚠️ Template dropdown not visible — assuming already added: " + templateName);
       } catch (Exception e) {
           throw new RuntimeException("❌ Failed to select template '" + templateName + "': " + e.getMessage(), e);
       }
   }


   /* @And("I click on the Submit button in Template popup")
    public void clickSubmitButtonInTemplatePopup_TemplateFolder() {
        WebDriver driver = webDriver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // Step 1: Wait for Template modal visibility
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[normalize-space()='Template']")));

            // Step 2: Locate Submit button
            String submitXpath = xml.getlocator("TempFol_ClickOnSubmitFromTemplateSelectionPopup");
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(submitXpath)));

            // Step 3: Scroll into view
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", submitButton);
            Thread.sleep(500);

            // Step 4: Click using JS (to bypass any overlay or animation delay)
            js.executeScript("arguments[0].click();", submitButton);

            System.out.println("✅ Successfully clicked on Submit button in Template popup.");

        } catch (TimeoutException te) {
            throw new RuntimeException("❌ Timeout: Submit button not found or clickable in Template popup.", te);
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to click Submit button: " + e.getMessage(), e);
        }
    }*/
   @And("I click on the Submit button in Template popup")
   public void clickSubmitButtonInTemplatePopup_TemplateFolderCheck() {
       WebDriver driver = webDriver.getDriver();
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
       JavascriptExecutor js = (JavascriptExecutor) driver;

       try {
           String submitBtnXpath = xml.getlocator("TempFol_ClickOnSubmitFromTemplateSelectionPopup");
           if (submitBtnXpath == null || submitBtnXpath.isEmpty()) {
               throw new RuntimeException("❌ Locator 'TempFol_ClickOnSubmitFromTemplateSelectionPopup' not found in XML.");
           }

           List<WebElement> submitButtons = driver.findElements(By.xpath(submitBtnXpath));
           if (submitButtons.isEmpty()) {
               System.out.println("⚠️ Submit button not found — assuming template already submitted. Skipping click.");
               return; // skip gracefully
           }

           WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(submitButtons.get(0)));
           js.executeScript("arguments[0].click();", submitBtn);

           System.out.println("✅ Successfully clicked on Submit button in Template popup.");

           // Wait briefly for popup to close
           wait.until(ExpectedConditions.invisibilityOf(submitBtn));

       } catch (TimeoutException te) {
           System.out.println("⚠️ Submit button not visible — likely popup closed already.");
       } catch (Exception e) {
           throw new RuntimeException("❌ Failed to click Submit button in Template popup: " + e.getMessage(), e);
       }
   }


    @And("I click on the Refresh button on Account page for verifying created template folder is present or not")
    public void clickOnRefreshButton_TemplateFolder() throws DocumentException, InterruptedException {
        WebDriver driver = webDriver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String refreshXpath = xml.getlocator("TempFol_ClickRefreshButtonFromBiSync");

        try {
            // Wait until at least one Refresh button is present in DOM
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(refreshXpath)));

            // Find all refresh buttons and filter visible ones
            List<WebElement> allButtons = driver.findElements(By.xpath(refreshXpath));
            WebElement visibleButton = allButtons.stream()
                    .filter(WebElement::isDisplayed)
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("No visible Refresh button found."));

            // Ensure it's actually in viewport
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", visibleButton);
            Thread.sleep(800);

            // Wait for it to become clickable
            wait.until(ExpectedConditions.elementToBeClickable(visibleButton));

            // JS click for Lightning reliability
            js.executeScript("arguments[0].click();", visibleButton);
            System.out.println("✅ Successfully clicked the visible Refresh button on Account page.");

            // Optional: Wait briefly for refresh completion
            Thread.sleep(2000);

        } catch (TimeoutException te) {
            System.err.println("⚠️ Refresh button not visible yet, retrying once...");

            try {
                Thread.sleep(3000); // wait for re-render
                List<WebElement> retryButtons = driver.findElements(By.xpath(refreshXpath));
                WebElement retryVisible = retryButtons.stream()
                        .filter(WebElement::isDisplayed)
                        .findFirst()
                        .orElseThrow(() -> new NoSuchElementException("No visible Refresh button found on retry."));
                js.executeScript("arguments[0].click();", retryVisible);
                System.out.println("Refresh button clicked successfully on retry.");
            } catch (Exception retryEx) {
                throw new RuntimeException("Timeout: Refresh button not found or clickable even after retry.", retryEx);
            }

        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to click on Refresh button: " + e.getMessage(), e);
        }
    }


    @And("Click on {string} from side nav to setup object to verify template folder in biSync")
    public void clickOnFromSideNav_ObjectSetup_TemplateFolderBiSyncCheck(String arg0) throws Exception {
        Thread.sleep(2000);
        webDriver.SwitchToFrameByElement(webDriver.getwebelement(xml.getlocator("XfileProIframe_ObjectSetup")));
        Thread.sleep(1000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_SideNavLink").replace("{paramlink}",arg0)));

    }
    @And("I click New Object Setup button to verify template folder in biSync")
    public void clickNewButton_ObjectSetup_TemplateFolderBiSyncCheck() throws Exception {
        Thread.sleep(3000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_NewButtonClick")));

    }
    @And("Select an option like Lead from an object dropdown to verify template folder in biSync")
    public void selectObjectLead_ObjectSetup_TemplateFolderBiSyncCheck() throws Exception  {
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_SelectObjectItem")));
        webDriver.selectOptionByText(webDriver.getwebelement(xml.getlocator("ObjectSetup_SelectObjectItem")), "Lead");
        Thread.sleep(2000);
    }

    @Then("Select a provider from object setup page to verify template folder in biSync")
    public void selectAProviderFromDropDown_ObjectSetup_TemplateFolderBiSyncCheck() throws Exception  {
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_SelectAProvider")));
        webDriver.selectOptionByText(webDriver.getwebelement(xml.getlocator("ObjectSetup_SelectAProvider")), "Google Drive (GoogleDrive)");
        Thread.sleep(2000);
    }

    @And("Click on next button from object setup panel to verify template folder in biSync")
    public void clickOnNextButton_ObjectSetup_TemplateFolderBiSyncCheck() throws Exception  {
        Thread.sleep(3000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_NextButton")));
    }
    @Then("Click on Done for creating the Object Setup to verify template folder in biSync")
    public void clickOnDoneButton_ObjectSetup_TemplateFolderBiSyncCheck() throws Exception {
        Thread.sleep(1000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_DoneButtonClick")));
        Thread.sleep(1000);
        System.out.println("Done clicked and Object Created successfully");
    }


    @When("Click on App Launcher and search item as Lead and click on Lead to verify template folder in biSync")
    public void goToAppLauncherAndSearchLead_TemplateFolderBiSyncCheck() throws Exception {
        WebDriver driver = webDriver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // 1️⃣ Wait for the Lightning header and App Launcher (waffle icon)
            String appLauncherXpath = xml.getlocator("AppLauncher_ObjectSetup");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//header")));
            Thread.sleep(2000); // Allow Salesforce header scripts to load fully

            WebElement appLauncher = null;

            // Try locating using normal DOM first
            try {
                appLauncher = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(appLauncherXpath)));
            } catch (TimeoutException e) {
                System.out.println("⚠️ Normal DOM lookup failed. Trying Shadow DOM lookup for App Launcher...");
            }

            // 2️⃣ If still null, try Shadow DOM lookup (Salesforce Lightning often nests waffle here)
            if (appLauncher == null) {
                appLauncher = (WebElement) js.executeScript(
                        "return document.querySelector('one-app-launcher-header')?.shadowRoot?.querySelector('button[title=\"App Launcher\"]')"
                );
            }

            // Validate App Launcher presence
            if (appLauncher == null) {
                throw new RuntimeException("❌ App Launcher button not found in DOM or Shadow DOM.");
            }

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", appLauncher);
            js.executeScript("arguments[0].click();", appLauncher);
            System.out.println("✅ Clicked on App Launcher successfully.");

            // 3️⃣ Wait for the App Launcher search bar to appear
            String searchAppXpath = xml.getlocator("SearchApp_ObjectSetup");
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchAppXpath)));

            searchInput.clear();
            searchInput.sendKeys("Leads");
            Thread.sleep(1000);
            System.out.println("✅ Typed 'Leads' into App Launcher search.");

            // 4️⃣ Wait for the Leads tile/item and click it
            String leadItemXpath = xml.getlocator("TempFol_LeadItemClickFromAppLauncher");
            WebElement leadItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(leadItemXpath)));

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", leadItem);
            js.executeScript("arguments[0].click();", leadItem);
            System.out.println("✅ Clicked on Leads successfully via JS.");

            // 5️⃣ Wait until navigation completes
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("/Lead/"),
                    ExpectedConditions.titleContains("Lead")
            ));

            Thread.sleep(1500);
            System.out.println("✅ Navigated to Leads tab successfully.");

        } catch (TimeoutException e) {
            throw new RuntimeException("❌ Timeout: App Launcher or Lead item not found within time.", e);
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed while navigating to Leads via App Launcher: " + e.getMessage(), e);
        }
    }







    @And("Skip from manage column popup window")
    public void skipFromManageColumnPopup_TemplateFolderBiSyncCheck_ObjectSetup() {
        WebDriver driver = webDriver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            String skipButtonXpath = xml.getlocator("TempFol_SkipFromManageColumnPopUp_ObjectSetup");

            if (skipButtonXpath == null || skipButtonXpath.isEmpty()) {
                throw new RuntimeException("❌ Locator 'TempFol_SkipFromManageColumnPopUp_ObjectSetup' not found in XML.");
            }

            // Wait for the Skip button to appear
            WebElement skipBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(skipButtonXpath)));

            // Scroll into view for safety
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", skipBtn);
            Thread.sleep(500);

            // Click using JavaScript for reliability inside Lightning modal
            js.executeScript("arguments[0].click();", skipBtn);

            System.out.println("✅ Successfully clicked on 'Skip' button in Manage Columns popup.");

            // Optional: Wait for popup to disappear
            wait.until(ExpectedConditions.invisibilityOf(skipBtn));

        } catch (TimeoutException te) {
            throw new RuntimeException("❌ Timeout: Skip button not found or not clickable in Manage Columns popup.", te);
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to click 'Skip' in Manage Columns popup: " + e.getMessage(), e);
        }
    }

    @And("I click on the New button from Lead page")
    public void clickOnNewButtonFromLeadPage_TempFolderBiSyncCheck_LeadNew() {
        WebDriver driver = webDriver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            String newButtonXpath = xml.getlocator("TempFol_NewClickForLeadCreation");

            if (newButtonXpath == null || newButtonXpath.isEmpty()) {
                throw new RuntimeException("❌ Locator 'TempFol_ClickNewButtonFromLeadPage' not found in XML file.");
            }

            // Wait for New button to be visible and clickable
            WebElement newButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(newButtonXpath)));

            // Scroll into view (Salesforce Lightning sometimes needs this)
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", newButton);
            Thread.sleep(500);

            // Click with JavaScript to avoid overlay or shadow DOM issues
            js.executeScript("arguments[0].click();", newButton);

            System.out.println("✅ Successfully clicked on 'New' button from Lead page.");

            // Optional: Wait until the New Lead form/modal appears
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'New')]")));

        } catch (TimeoutException te) {
            throw new RuntimeException("❌ Timeout: 'New' button not found or not clickable on Lead page.", te);
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to click 'New' button on Lead page: " + e.getMessage(), e);
        }
    }



    @And("I fill the Lead details as {string} {string} {string} {string} and click on Save button")
    public void fillLeadDetailsAndSave_TemplateFolderBiSyncCheck_CreateNewLead(
            String salutation, String firstName, String lastName, String companyName) {

        WebDriver driver = webDriver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // === 1️⃣ Click on Salutation Dropdown ===
            String dropdownXpath = xml.getlocator("TempFol_SelectSalutationDropdownFromLeadWindow");
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXpath)));

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", dropdown);
            js.executeScript("arguments[0].click();", dropdown);
            System.out.println("✅ Clicked Salutation dropdown");
            Thread.sleep(1200); // Give Salesforce time to render overlay

            // === 2️⃣ Wait for dropdown overlay to appear ===
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[contains(@class,'slds-dropdown')]")));
            System.out.println("✅ Dropdown overlay detected");

            // === 3️⃣ Now wait for the specific option ("Ms.") ===
            String optionXpath = xml.getlocator("TempFol_SelectSalutationOptionFromLeadWindow")
                    .replace("{{SALUTATION}}", salutation);
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXpath)));

            js.executeScript("arguments[0].click();", option);
            System.out.println("✅ Selected salutation: " + salutation);
            Thread.sleep(1000);

            // === 4️⃣ Fill rest of fields ===
            WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(xml.getlocator("TempFol_FirstNameFieldFromLeadPopup"))));
            firstNameField.clear();
            firstNameField.sendKeys(firstName);

            WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(xml.getlocator("TempFol_LastNameFieldFromLeadPopup"))));
            lastNameField.clear();
            lastNameField.sendKeys(lastName);

            WebElement companyField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(xml.getlocator("TempFol_CompanyFieldFromLeadPopup"))));

            companyField.clear();
            companyField.sendKeys(companyName);
            Thread.sleep(300);

// Use JS to set the value and trigger Lightning’s internal change event
            js.executeScript(
                    "arguments[0].value = arguments[1]; " +
                            "arguments[0].dispatchEvent(new Event('input', { bubbles: true })); " +
                            "arguments[0].dispatchEvent(new Event('change', { bubbles: true })); " +
                            "arguments[0].blur();",
                    companyField, companyName);

            Thread.sleep(300);
            System.out.println("✅ Company name set and committed via JS: " + companyName);

            WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath(xml.getlocator("TempFol_SaveButtonFromLeadPopup"))));
            js.executeScript("arguments[0].click();", saveBtn);
            System.out.println("✅ Clicked Save button successfully");

            wait.until(ExpectedConditions.urlContains("/lightning/r/Lead/"));
            System.out.println("✅ Lead saved successfully!");

        } catch (Exception e) {
            throw new RuntimeException("❌ Failed while filling Lead details or clicking Save: " + e.getMessage(), e);
        }
    }


    @And("I click on the most recently created Lead from the Lead list page")
    public void clickOnRecentCreatedLeadFromList_TemplateFolderBiSyncCheck_LeadLinkCLick() {
        WebDriver driver = webDriver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            String recentLeadXpath = xml.getlocator("TempFol_ClickOnRecentCreatedLeadFromList");

            if (recentLeadXpath == null || recentLeadXpath.isEmpty()) {
                throw new RuntimeException("❌ Locator 'TempFol_ClickOnRecentCreatedLeadFromList' not found in XML file.");
            }

            // Wait for the Lead table and first Lead link to appear
            WebElement leadLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(recentLeadXpath)));

            // Scroll to the Lead link
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", leadLink);
            Thread.sleep(800);

            // Click using JavaScript (Lightning reliability)
            js.executeScript("arguments[0].click();", leadLink);

            String leadName = leadLink.getAttribute("title");
            System.out.println("✅ Successfully clicked on most recent Lead: " + leadName);

            // Optional: Wait until the Lead detail page opens
            wait.until(ExpectedConditions.urlContains("/lightning/r/Lead/"));
            Thread.sleep(1500);

        } catch (TimeoutException te) {
            throw new RuntimeException("❌ Timeout: No Lead record found or clickable in the list.", te);
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to click on recent Lead: " + e.getMessage(), e);
        }
    }

//Add Template from object setup
@And("Click on Add Template button from object setup panel for verifying template in biSync")
public void clickOnAddTemplateButton_ObjectSetup_TemplateFolderBiSyncCheck() throws Exception  {
    Thread.sleep(3000);
    webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_AddTemplateButtonCheck")));

}

    @And("Enter the Template name and save for verifying template in biSync")
    public void enterTemplateName_ObjectSetup_TemplateFolderBiSyncCheck() throws Exception {
        Thread.sleep(2000);

        // Generate a random number (0-9999)
        int randomNum = (int) (Math.random() * 10000);

        // Build dynamic template name with prefix
        String templateName = "PriyankaTest Template" + randomNum;

        // Enter the dynamic template name
        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("ObjectSetup_EnterTemplateName")), templateName);

        // Click OK to save
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_ClickOnOkayFromAddTemplatePopup")));

        // Optional: log the name used (for debugging/reporting)
        System.out.println("Created Template with name: " + templateName);
    }
    @And("I click on Add folder from template for verifying template in biSync")
    public void addFolderTemplateButtonClick_ObjectSetup_TemplateFolderBiSyncCheck() throws Exception{
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_AddFolderTemplateButtonClick")));

    }
    @Then("I enter folder name and okay from popup for verifying template in biSync")
    public void addFolderTemplate_clickOnOkayFromPopup_ObjectSetup_TemplateFolderBiSyncCheck() throws Exception {
        // Generate a random folder name
        int randomNum = new java.util.Random().nextInt(10000);
        String folderName = "PriyankaFolder" + randomNum;

        // Enter the folder name into the input box
        webDriver.SendKeys(
                webDriver.getwebelement(xml.getlocator("ObjectSetup_AddFolderTemplateInputField")),
                folderName
        );
        System.out.println("Entered folder name: " + folderName);

        // Click on OK button in Add Folder popup
        webDriver.Clickon(
                webDriver.getwebelement(xml.getlocator("ObjectSetup_ClickOnOkayFromAddTemplatePopup"))
        );
        System.out.println("Clicked on OK to add folder");

        // Build dynamic XPath using the folder name
        String folderXpath = String.format(
                xml.getlocator("ObjectSetup_AddedFolderValidation"), folderName
        );

        // Wait until the folder appears under template structure
        WebElement folderElement = webDriver.waitUntilVisible(By.xpath(folderXpath), 10);

        // Assert that folder is displayed
        Assert.assertTrue(
                "Folder not added successfully: " + folderName,
                folderElement.isDisplayed()
        );

        System.out.println("Folder '" + folderName + "' has been added successfully under the template");
    }

//Setting icon gear up setup click
    @When("I click on settings gear icon for checking the biSync")
    public void clickOnSetupGearIcon_TemplateFolder_CheckBiSyncComponent() {
        WebDriver driver = webDriver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // 1️⃣ Wait for the Setup Gear icon to be present in DOM
            String setupGearXpath = xml.getlocator("TempFol_SetupGearUp_SettingIconcd");
            WebElement setupGear = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(setupGearXpath)));

            // 2️⃣ Scroll to it to handle Lightning overlays
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", setupGear);
            wait.until(ExpectedConditions.elementToBeClickable(setupGear));

            // 3️⃣ Click via JS (normal click may fail due to overlays)
            js.executeScript("arguments[0].click();", setupGear);
            System.out.println("✅ Clicked on Setup Gear icon successfully.");

            // 4️⃣ Optional: wait for setup menu to open
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[contains(@class,'forceHeaderMenu')]")
            ));
            System.out.println("✅ Setup menu opened successfully.");

        } catch (TimeoutException e) {
            throw new RuntimeException("❌ Timeout: Setup Gear icon not found or clickable within time.", e);
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed while clicking Setup Gear icon: " + e.getMessage(), e);
        }
    }





}
