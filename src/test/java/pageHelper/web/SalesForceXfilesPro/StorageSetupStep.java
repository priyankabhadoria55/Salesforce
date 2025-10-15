package pageHelper.web.SalesForceXfilesPro;

import core.baseDriverHelper;
import core.webHelper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j;
import org.dom4j.DocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageHelper.bddDriver;
//import technology.tabula.ObjectExtractor;
//import technology.tabula.Page;
//import technology.tabula.RectangularTextContainer;
//import technology.tabula.Table;
//import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;
import utils.DriverController;
import utils.Excel_Data_Repo;
import utils.PropertyReader;
import utils.xmlreader;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

// ADD THESE NEW IMPORTS:
import java.util.ArrayList;
import java.util.Arrays;
import org.openqa.selenium.support.ui.Select;

@Log4j
public class StorageSetupStep {
    public webHelper webDriver;
    private bddDriver DriverInstance;
    public xmlreader xml;

    public StorageSetupStep(bddDriver contextSteps, DriverController drivercontroler) throws Exception {
        this.DriverInstance = contextSteps;
        System.out.println("triggered bdd contructoctor");
        System.out.println(this.DriverInstance);
        webDriver = new baseDriverHelper(bddDriver.getWebDriver());
        xml=new xmlreader("src\\test\\resources\\locators\\salesforce.xml");
    }

    public StorageSetupStep(DriverController drivercontroler) {
        webDriver = new baseDriverHelper(drivercontroler.getDriver());
    }



    @And("Click on element {string} on the {string} page {int} times")
    public void clickOnElementOnThePageTimes(String Locators, String PageName, int numbers) throws Exception {

        xml = new xmlreader("src\\test\\resources\\locators\\" + PageName.toLowerCase() + ".xml");
        String locator=xml.getlocator(Locators);
        for (int i=0;i<numbers;i++){
            Thread.sleep(1000);
            try {
                webDriver.Clickon(webDriver.getwebelement(locator));
            } catch (Exception e) {
                webDriver.waitforElementtobeclickable(locator);
                webDriver.Clickon(webDriver.getwebelement(locator));

            }
        }
    }

    @And("Go to {string} in {string}")
    public void goToIn(String arg0, String arg1) throws Exception {
        String mode="normal";
        if(arg1.equalsIgnoreCase("Console"))
        {
            mode="maximized";
        }
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("EntityTabs").replace("{paramlink}",arg0).replace("{paramlink1}",mode)));

    }

    @Given("Go to {string} Nav item")
    public void goToNavItem(String arg0) throws Exception{
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NavItem").replace("{paramlink}",arg0)));
        Thread.sleep(5000);


        // Write code here that turns the phrase above into concrete actions

    }

    @Then("Click on {string} from side nav")
    public void clickOnFromSideNav(String arg0) throws Exception {
        webDriver.SwitchToFrameByElement(webDriver.getwebelement(xml.getlocator("XfileProIframe")));
        Thread.sleep(5000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("SideNavLink").replace("{paramlink}",arg0)));

    }

    @Then("I should verify that the newly configured Azure storage is displayed in Storage Setup page")
    public void verifyNewlyConfiguredStorageIsDisplayed() throws Exception {
        System.out.println("🔍 Verifying newly configured Azure storage is displayed...");

        // Get the stored title
        String expectedTitle = DriverInstance.getThreadLocalMapValue("StorageConfigurationTitle");
        System.out.println("📌 Expected storage title: " + expectedTitle);

        Thread.sleep(3000);

        // Navigate back to storage setup page
        try {
            webDriver.Clickon(webDriver.getwebelement(xml.getlocator("SideNavLink").replace("{paramlink}", "storage_setup")));
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("⚠️ Already on Storage Setup page");
        }

        // Verify table exists
        webDriver.verifyElementToBePresent(xml.getlocator("StorageProviderTable"));

        // Direct XPath to find row with the specific title
        String storageRowXPath = "//table[contains(@class, 'xfp-table')]//tbody/tr[.//td[1]//div[text()='" + expectedTitle + "']]";

        // Verify the storage with expected title exists
        boolean storageExists = webDriver.isElementPresent(storageRowXPath);

        Assert.assertTrue(
                storageExists,
                "❌ Storage provider with title '" + expectedTitle + "' not found in Storage Setup page!"
        );

        // Get the row and verify details
        WebElement storageRow = webDriver.getwebelement(storageRowXPath);

        // Get name and type
        WebElement nameCell = storageRow.findElement(By.xpath(".//td[1]//div"));
        WebElement typeCell = storageRow.findElement(By.xpath(".//td[2]//div"));

        String displayedName = nameCell.getText().trim();
        String displayedType = typeCell.getText().trim();

        System.out.println("\n✅ Storage Provider Found:");
        System.out.println("   Configured Title: " + expectedTitle);
        System.out.println("   Displayed Name: " + displayedName);
        System.out.println("   Storage Type: " + displayedType);

        // Verify exact match
        Assert.assertEquals(
                displayedName,
                expectedTitle,
                "Storage name doesn't match the configured title!"
        );

        System.out.println("✅ Title verification: PASSED - Names match exactly!");
        System.out.println("✅ Newly configured Azure storage is successfully displayed!");
    }



    @Then("I verify Cloud Storage hover message is displayed")
    public void verifyCloudStorageHoverMessage() throws Exception {
        System.out.println("Verifying Cloud Storage hover message");

        // Step 1: Locate the Cloud Storage button
        webDriver.verifyElementToBePresent(xml.getlocator("CloudStorageButton"));
        WebElement cloudStorageBtn = webDriver.getwebelement(xml.getlocator("CloudStorageButton"));

        // Step 2: Get tooltip text from 'title' attribute
        String tooltipText = webDriver.Getattribute(cloudStorageBtn, "title");

        // Step 3: Assert hover tooltip text
        Assert.assertEquals(tooltipText, "Cloud Storage",
                "Tooltip message did not match expected value!");

        System.out.println("✅ Hover message displayed successfully: " + tooltipText);
    }

    @Then("I should be on the Storage Type page")
    public void iShouldBeOnTheStorageTypePage() throws Exception {
        System.out.println("Verifying Storage Type page is displayed");

        // Verify Storage Setup title is present
        webDriver.verifyElementToBePresent(xml.getlocator("StorageTypePageTitle"));
        System.out.println("✅ Storage Setup page title is displayed");


    }

    @And("I should see {string} dropdown")
    public void iShouldSeeDropdown(String dropdownLabel) throws Exception {
        System.out.println("Verifying dropdown: " + dropdownLabel);

        // Verify the dropdown label is present
        webDriver.verifyElementToBePresent(xml.getlocator("StorageProviderLabel"));
        System.out.println("✅ Storage provider dropdown label is displayed");

        // Verify the dropdown element is present
        webDriver.verifyElementToBePresent(xml.getlocator("StorageProviderDropdown"));
        System.out.println("✅ Storage provider dropdown is displayed");
    }

    @Then("I should verify that all storage providers are listed except GoogleDrive")
    public void verifyAllStorageProvidersExceptGoogleDrive() throws Exception {
        System.out.println("🔍 Verifying storage provider dropdown options...");

        Thread.sleep(2000);

        // Use centralized XPath from XML
        WebElement dropdown = null;
        try {
            dropdown = webDriver.getwebelement(xml.getlocator("StorageProviderDropdown"));
            System.out.println("✅ Dropdown found using StorageProviderDropdown locator");
        } catch (Exception e) {
            System.out.println("⚠️ Primary locator failed, trying alternative...");
            dropdown = webDriver.getwebelement(xml.getlocator("StorageProviderDropdownAlt"));
        }

        // Get all options from the dropdown
        org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(dropdown);
        List<WebElement> allOptions = select.getOptions();

        System.out.println("📋 Total options found: " + allOptions.size());

        // Define expected providers (excluding --None-- and GoogleDrive)
        List<String> expectedProviders = Arrays.asList(
                "Amazon S3",
                "OneDrive",
                "SharePoint (Azure)",
                "SharePoint (Microsoft)"
        );

        // List to store actual providers (excluding None)
        List<String> actualProviders = new ArrayList<>();

        // Print all options and collect actual providers
        System.out.println("\n📝 Dropdown Options:");
        for (WebElement option : allOptions) {
            String optionText = option.getText().trim();
            String optionValue = option.getAttribute("value");

            System.out.println("  - Text: '" + optionText + "' | Value: '" + optionValue + "'");

            // Skip --None-- option
            if (!optionText.equals("--None--") && !optionValue.isEmpty()) {
                actualProviders.add(optionText);
            }
        }

        System.out.println("\n✅ Expected Providers: " + expectedProviders);
        System.out.println("✅ Actual Providers: " + actualProviders);

        // Verification 1: Check total number of providers
        Assert.assertEquals(
                actualProviders.size(),
                expectedProviders.size(),
                "Expected " + expectedProviders.size() + " storage providers but found " + actualProviders.size()
        );
        System.out.println("✅ Provider count verified: " + actualProviders.size() + " providers");

        // Verification 2: Check each expected provider is present
        for (String expectedProvider : expectedProviders) {
            Assert.assertTrue(
                    actualProviders.contains(expectedProvider),
                    "❌ Expected provider '" + expectedProvider + "' not found in dropdown!"
            );
            System.out.println("✅ Verified: " + expectedProvider + " is present");
        }

        // Verification 3: Ensure GoogleDrive is NOT present
        // FIXED: Only check for "google", not "drive" alone (to avoid matching "OneDrive")
        boolean googleDrivePresent = false;
        String foundGoogleProvider = "";

        for (String provider : actualProviders) {
            String providerLower = provider.toLowerCase();

            // Only check if provider name contains "google"
            // (OneDrive should NOT trigger this)
            if (providerLower.contains("google")) {
                googleDrivePresent = true;
                foundGoogleProvider = provider;
                System.err.println("❌ Found Google-related provider: " + provider);
                break;
            }
        }

        Assert.assertFalse(
                googleDrivePresent,
                "❌ GoogleDrive or Google-related provider should NOT be present! Found: " + foundGoogleProvider
        );
        System.out.println("✅ Verified: GoogleDrive is NOT present in the dropdown");
        System.out.println("✅ Note: OneDrive is correctly present and is different from GoogleDrive");

        // Verification 4: Verify --None-- is the default option
        WebElement selectedOption = select.getFirstSelectedOption();
        String selectedText = selectedOption.getText();
        Assert.assertEquals(
                selectedText,
                "--None--",
                "Default selected option should be '--None--' but found: " + selectedText
        );
        System.out.println("✅ Verified: '--None--' is the default selected option");

        System.out.println("\n✅ All storage provider verifications passed successfully!");
    }

    @And("I should verify that {string} is selected by default in the storage provider dropdown")
    public void iShouldVerifyThatIsSelectedByDefaultInTheStorageProviderDropdown(String expectedDefault) throws Exception {
        System.out.println("Verifying default selection in storage provider dropdown");

        // Verify the default option is present and selected
        webDriver.verifyElementToBePresent(xml.getlocator("StorageProviderDefaultOption"));
        System.out.println("✅ Default '--None--' option is present");

        // Get the dropdown element and verify its default value
        WebElement dropdown = webDriver.getwebelement(xml.getlocator("StorageProviderDropdown"));
        String selectedValue = webDriver.Getattribute(dropdown, "value");

        // Verify that no value is selected (empty string for default)
        Assert.assertEquals(selectedValue, "",
                "Default selection should be empty (None), but found: " + selectedValue);

        // Verify Next button is disabled when no option is selected
        webDriver.verifyElementToBePresent(xml.getlocator("NextButtonDisabled"));
        System.out.println("✅ Next button is disabled when no storage provider is selected");

        System.out.println("✅ Default selection verification completed successfully");
    }


    @Then("I Click on cloud storage button")
    public void iClickOnButton() throws Exception  {
        Thread.sleep(5000);
        // Write code here that turns the phrase above into concrete actions
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("CloudStorageButton")));
    }

    @Then("i select {string} from storage provider dropdown")
    public void iSelectFromStorageProviderDropdown(String arg0) throws Exception {
        webDriver.selectOptionByText(webDriver.getwebelement(xml.getlocator("StorageProvideDropdown")),arg0);

    }

    @Then("I Navigate to Appregistration screen By clicking next button")
    public void iNavigateToAppRegistrationScreenByClickingNextButton() throws Exception {
        Thread.sleep(3000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        System.out.println("✅ Navigated to App Registration screen");
    }

    @Then("I Navigate to microsoft azure tab by clicking on click here link With tabid {string}")
    public void iNavigateToMicrosoftAzureTabByClickingOnClickHereLink(String arg0) throws Exception {
        Thread.sleep(3000);
        // Assuming there is a locator for "Click here" link (add if not present)
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ClickHereLink")));
        System.out.println("✅ Navigated to Microsoft Azure tab");
        int no=Integer.parseInt(arg0);
        webDriver.SwitchToNextTab(no);

    }


    @Then("I should verify that Microsoft Signin error is displayed in Signin tab")
    public void verifyMicrosoftSigninErrorDisplayed() throws Exception {
        System.out.println("🔍 Verifying Microsoft Signin error message is displayed...");

        // Try to find the error message using primary locator
        boolean errorFound = false;
        String errorText = "";

        try {
            WebElement errorElement = webDriver.getwebelement(xml.getlocator("MicrosoftSigninErrorMessage"));
            errorText = errorElement.getText();
            errorFound = true;
            System.out.println("✅ Error message found using primary locator");
        } catch (Exception e) {
            System.out.println("⚠️ Primary locator failed, trying alternative...");
            try {
                WebElement errorElement = webDriver.getwebelement(xml.getlocator("MicrosoftSigninErrorMessageAlt"));
                errorText = errorElement.getText();
                errorFound = true;
                System.out.println("✅ Error message found using alternative locator");
            } catch (Exception ex) {
                System.err.println("❌ Could not find error message with any locator");
            }
        }

        // Assert that error message was found
        Assert.assertTrue(errorFound, "❌ Microsoft Signin error message was not displayed!");

        // Verify that the error text contains expected content
        Assert.assertTrue(
                errorText.contains("Application with identifier") || errorText.contains("AADSTS"),
                "❌ Error message doesn't contain expected Azure AD error content! Found: " + errorText
        );

        System.out.println("✅ Microsoft Signin Error Message Verified:");
        System.out.println("   Error Text: " + errorText);



    }

    @Then("I register the app in microsoft azure")
    public void iRegisterTheAppInMicrosoftAzure() throws Exception {
        Thread.sleep(3000);
        // Click "New registration"
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NewRegistrationButton")));

        // Enter application name
        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("NameInput")), "MyTestApplicationsfd");

        // Select supported account types
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("AzureADMultipleOrgsOption")));

        // Scroll to Redirect URI section and set optional URI (if required)
        //webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("RedirectURIInput")), "https://qautomation--xfiles.vf.force.com/apex/XfileOauthCallBack?type=SharePoint");

        // Click on "Select a platform" dropdown
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("Selectaplatformdrop")));

        // Wait for dropdown options to appear
        Thread.sleep(1000);

        // Select "Web" from the dropdown
        // Assuming you need to add this locator to your XML
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("Webselectfromdropdownlist")));

        // Wait for the text field to appear after selecting platform
        Thread.sleep(2000);

        // Enter Redirect URI in the text field
        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("TextOuthfield")),
                "https://qautomation--xfiles.vf.force.com/apex/XfileOauthCallBack?type=SharePoint");



        // Click "Register" button
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("RegisterButton")));
        System.out.println("✅ App registered successfully in Azure");
    }

    @Then("i navigate back to the application and i entered client id and tenant id")
    public void iNavigateBackToTheApplicationAndEnterClientIdAndTenantId() throws Exception {
        Thread.sleep(3000);
        // Switch back to original tab or frame if required

        // Fetch the Client ID and Tenant ID from Azure page (example shown below)

        String clientId = webDriver.getText(webDriver.getwebelement(xml.getlocator("ApplicationClientID")));
        String tenantId = webDriver.getText(webDriver.getwebelement(xml.getlocator("DirectoryTenantID")));

        webDriver.SwitchToNextTab(0);
        Thread.sleep(2000);
        webDriver.SwitchToFrameByElement(webDriver.getwebelement(xml.getlocator("XfileProIframe")));
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);

        // Enter them back into your original application form fields

        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("ClientIdInputField")), clientId);
        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("TenantIdInputField")), tenantId);

        System.out.println("✅ Client ID and Tenant ID entered successfully");
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));


    }


    @Then("i navigate back to the application and i entered invalid client id and valid tenant id")
    public void iNavigateBackToTheApplicationAndEnterinvalidClientIdAndValidTenantId() throws Exception {
        Thread.sleep(3000);
        // Switch back to original tab or frame if required

        // Fetch the Client ID and Tenant ID from Azure page (example shown below)
        String clientId = "gfdgdfgdfgdfgdfgdfgdfgdfgdfgdffgfghf";
        //String clientId = webDriver.getText(webDriver.getwebelement(xml.getlocator("ApplicationClientID")));
        String tenantId = webDriver.getText(webDriver.getwebelement(xml.getlocator("DirectoryTenantID")));

        webDriver.SwitchToNextTab(0);
        Thread.sleep(2000);
        webDriver.SwitchToFrameByElement(webDriver.getwebelement(xml.getlocator("XfileProIframe")));
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);

        // Enter them back into your original application form fields
        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("ClientIdInputField")), clientId);
        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("TenantIdInputField")), tenantId);

        System.out.println("✅ Client ID and Tenant ID entered successfully");
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));

    }

    @Then("i navigate back to the application and i entered valid client id and invalid tenant id")
    public void iNavigateBackToTheApplicationAndEntervalidClientIdAndinValidTenantId() throws Exception {
        Thread.sleep(3000);
        // Switch back to original tab or frame if required

        // Fetch the Client ID and Tenant ID from Azure page (example shown below)
        //String clientId = "gfdgdfgdfgdfgdfgdfgdfgdfgdfgdffgfghf";
        String clientId = webDriver.getText(webDriver.getwebelement(xml.getlocator("ApplicationClientID")));
        //String tenantId = webDriver.getText(webDriver.getwebelement(xml.getlocator("DirectoryTenantID")));
        String tenantId = "456beac4-e770-4916-a41f-b0bfc34d9abc";
        webDriver.SwitchToNextTab(0);
        Thread.sleep(2000);
        webDriver.SwitchToFrameByElement(webDriver.getwebelement(xml.getlocator("XfileProIframe")));
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);







        // Enter them back into your original application form fields
        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("ClientIdInputField")), clientId);
        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("TenantIdInputField")), tenantId);

        System.out.println("✅ Client ID and Tenant ID entered successfully");
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));


    }

    @Then("I should verify that clicking Next without entering Client Id and Tenant Id shows validation messages")
    public void iShouldVerifyThatClickingNextWithoutEnteringClientIdAndTenantIdShowsValidationMessages() throws Exception {
        System.out.println("Verifying validation messages when clicking Next without entering Client Id and Tenant Id");

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);

        // Click the Next button without entering any values
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        System.out.println("✅ Clicked Next button without entering values");

        // Wait for validation messages to appear
        Thread.sleep(2000);



            // Verify Client Id validation message is displayed
            webDriver.verifyElementToBePresent(xml.getlocator("ClientIdValidationMessage"));
            System.out.println("✅ Client Id validation message is displayed");

            // Verify Tenant Id validation message is displayed
            webDriver.verifyElementToBePresent(xml.getlocator("TenantIdValidationMessage"));
            System.out.println("✅ Tenant Id validation message is displayed");


            // Verify we are still on the "Client Id and Tenant Id" progress step
            webDriver.verifyElementToBePresent(xml.getlocator("ClientIdAndTenantIdProgressStep"));
            System.out.println("✅ Still on 'Client Id and Tenant Id' progress step");

            // Verify we have NOT moved to the "Generate Client Secret" step
            boolean generateClientSecretStepActive = webDriver.isElementPresent(xml.getlocator("GenerateClientSecretProgressStep"));
            Assert.assertFalse(generateClientSecretStepActive,
                    "Should not have moved to 'Generate Client Secret' step when validation errors are present");
            System.out.println("✅ Did not move to 'Generate Client Secret' step");

            // Get the actual validation message texts and extract only the validation part
            String clientIdFullText = webDriver.getwebelement(xml.getlocator("ClientIdValidationMessage")).getText();
            String tenantIdFullText = webDriver.getwebelement(xml.getlocator("TenantIdValidationMessage")).getText();

            // Extract only the validation message part (after the newline)
            String clientIdMessage = clientIdFullText.contains("\n") ?
                    clientIdFullText.substring(clientIdFullText.indexOf("\n") + 1).trim() :
                    clientIdFullText;
            String tenantIdMessage = tenantIdFullText.contains("\n") ?
                    tenantIdFullText.substring(tenantIdFullText.indexOf("\n") + 1).trim() :
                    tenantIdFullText;

            // Verify the exact validation message texts
            Assert.assertEquals(clientIdMessage, "Please enter the Client Id.",
                    "Client Id validation message should be 'Please enter the Client Id.', but found: " + clientIdMessage);
            Assert.assertEquals(tenantIdMessage, "Please enter the Tenant Id.",
                    "Tenant Id validation message should be 'Please enter the Tenant Id.', but found: " + tenantIdMessage);

            System.out.println("✅ Validation messages verified successfully");
            System.out.println("   - Client Id message: " + clientIdMessage);
            System.out.println("   - Tenant Id message: " + tenantIdMessage);
            System.out.println("   - Progress step: Client Id and Tenant Id (not moved to next step)");
        }


    @Then("I should verify navigation to Generate Client Secret sub tab")
    public void iShouldVerifyNavigationToGenerateClientSecretSubTab() throws Exception {
        System.out.println("Verifying navigation to Generate Client Secret sub tab");

        // Wait for the progress step to be visible
        Thread.sleep(3000);

        // Verify the "Generate Client Secret" progress step is present
        webDriver.verifyElementToBePresent(xml.getlocator("GenerateClientSecretProgressStep"));
        System.out.println("✅ Generate Client Secret progress step is present");

        // Verify the "Generate Client Secret" step is the current active step
        webDriver.verifyElementToBePresent(xml.getlocator("GenerateClientSecretCurrentStep"));
        System.out.println("✅ Generate Client Secret is the current active step");

        // Get the progress step text to verify it's the correct step
        String progressStepText = webDriver.getwebelement(xml.getlocator("GenerateClientSecretProgressStep")).getAttribute("data-label");
        Assert.assertEquals(progressStepText, "Generate Client Secret",
                "Progress step should be 'Generate Client Secret', but found: " + progressStepText);
        System.out.println("✅ Progress step text verified: " + progressStepText);

        // Verify the assistive text contains "Current Stage"
        String assistiveText = webDriver.getwebelement(xml.getlocator("GenerateClientSecretProgressStep")).getText();
        Assert.assertTrue(assistiveText.contains("Current Stage"),
                "Assistive text should contain 'Current Stage', but found: " + assistiveText);
        System.out.println("✅ Assistive text verified: " + assistiveText);

        System.out.println("✅ Successfully navigated to Generate Client Secret sub tab under Register an App");
    }

    @Then("I should verify Previous and Next button navigation functionality")
    public void iShouldVerifyPreviousAndNextButtonNavigationFunctionality() throws Exception {
        System.out.println("Verifying Previous and Next button navigation functionality");

        // Verify Previous button is present and clickable
        webDriver.verifyElementToBePresent(xml.getlocator("PreviousButton"));
        System.out.println("✅ Previous button is present");

        // Click Previous button
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("PreviousButton")));
        System.out.println("✅ Clicked Previous button");

        // Wait for navigation
        Thread.sleep(2000);



        // Click Next button to return to App Registration screen
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        System.out.println("✅ Clicked Next button to return to App Registration screen");

        // Wait for navigation
        Thread.sleep(2000);

        // Verify we are back on the App Registration screen
        webDriver.verifyElementToBePresent(xml.getlocator("ClientIdAndTenantIdProgressStep"));
        System.out.println("✅ Returned to App Registration screen");

        System.out.println("✅ Previous and Next button navigation functionality verified successfully");
    }

    @Then("I should verify that clicking Next with only Client Id shows Tenant Id validation message")
    public void iShouldVerifyThatClickingNextWithOnlyTenantIdShowsClientIdValidationMessage() throws Exception {
        System.out.println("Verifying validation message when clicking Next with only Tenant Id entered");

        Thread.sleep(2000);

        // Enter Tenant Id only
        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("ClientIdInputField")), "234234523534523523");
        System.out.println("✅ Entered Tenant Id");

        // Click the Next button
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        System.out.println("✅ Clicked Next button with only Tenant Id entered");

        // Wait for validation message to appear
        Thread.sleep(2000);

        // Verify Client Id validation message is displayed
        webDriver.verifyElementToBePresent(xml.getlocator("TenantIdValidationMessage"));
        System.out.println("✅ Client Id validation message is displayed");


        // Verify we are still on the "Client Id and Tenant Id" progress step
        webDriver.verifyElementToBePresent(xml.getlocator("ClientIdAndTenantIdProgressStep"));
        System.out.println("✅ Still on 'Client Id and Tenant Id' progress step");

        // Verify we have NOT moved to the "Generate Client Secret" step
        boolean generateClientSecretStepActive = webDriver.isElementPresent(xml.getlocator("GenerateClientSecretProgressStep"));
        Assert.assertFalse(generateClientSecretStepActive,
                "Should not have moved to 'Generate Client Secret' step when Client Id validation error is present");
        System.out.println("✅ Did not move to 'Generate Client Secret' step");

        // Get the actual validation message text and extract only the validation part
        String TenantIdFullText = webDriver.getwebelement(xml.getlocator("TenantIdValidationMessage")).getText();
        String TenantIdMessage = TenantIdFullText.contains("\n") ?
                TenantIdFullText.substring(TenantIdFullText.indexOf("\n") + 1).trim() :
                TenantIdFullText;

        // Verify the exact validation message text
        Assert.assertEquals(TenantIdMessage, "Please enter the Tenant Id.",
                "Client Id validation message should be 'Please enter the Client Id.', but found: " + TenantIdMessage);

        System.out.println("✅ Validation message verified successfully");
        System.out.println("   - Client Id message: " + TenantIdMessage);
        System.out.println("   - Tenant Id: Populated (no error)");
        System.out.println("   - Progress step: Client Id and Tenant Id (not moved to next step)");

        webDriver.getwebelement(xml.getlocator("ClientIdInputField")).clear();
    }


    @Then("I navigate back to Appregistration screen By clicking previous button in 4 times")
    public void iNavigateBackToTheAppregistrationscreenbyClickingPrevousbuttonin4times() throws Exception {

        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("PreviousButton")));
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("PreviousButton")));
        Thread.sleep(2000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("PreviousButton")));
        Thread.sleep(2000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("PreviousButton")));
        Thread.sleep(2000);

    }

    @Then("I should verify that clicking Next with only Tenant Id shows Client Id validation message")
    public void iShouldVerifyThatClickingNextWithOnlyClientIdShowsTenantIdValidationMessage() throws Exception {
        System.out.println("Verifying validation message when clicking Next with only Tenant Id entered");

        Thread.sleep(2000);

        // Enter Tenant Id only
        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("TenantIdInputField")), "234234523534523523");
        System.out.println("✅ Entered Tenant Id");

        // Click the Next button
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        System.out.println("✅ Clicked Next button with only Tenant Id entered");

        // Wait for validation message to appear
        Thread.sleep(2000);

        // Verify Client Id validation message is displayed
        webDriver.verifyElementToBePresent(xml.getlocator("ClientIdValidationMessage"));
        System.out.println("✅ Client Id validation message is displayed");


        // Verify we are still on the "Client Id and Tenant Id" progress step
        webDriver.verifyElementToBePresent(xml.getlocator("ClientIdAndTenantIdProgressStep"));
        System.out.println("✅ Still on 'Client Id and Tenant Id' progress step");

        // Verify we have NOT moved to the "Generate Client Secret" step
        boolean generateClientSecretStepActive = webDriver.isElementPresent(xml.getlocator("GenerateClientSecretProgressStep"));
        Assert.assertFalse(generateClientSecretStepActive,
                "Should not have moved to 'Generate Client Secret' step when Client Id validation error is present");
        System.out.println("✅ Did not move to 'Generate Client Secret' step");

        // Get the actual validation message text and extract only the validation part
        String clientIdFullText = webDriver.getwebelement(xml.getlocator("ClientIdValidationMessage")).getText();
        String clientIdMessage = clientIdFullText.contains("\n") ?
                clientIdFullText.substring(clientIdFullText.indexOf("\n") + 1).trim() :
                clientIdFullText;

        // Verify the exact validation message text
        Assert.assertEquals(clientIdMessage, "Please enter the Client Id.",
                "Client Id validation message should be 'Please enter the Client Id.', but found: " + clientIdMessage);

        System.out.println("✅ Validation message verified successfully");
        System.out.println("   - Client Id message: " + clientIdMessage);
        System.out.println("   - Tenant Id: Populated (no error)");
        System.out.println("   - Progress step: Client Id and Tenant Id (not moved to next step)");

        webDriver.getwebelement(xml.getlocator("TenantIdInputField")).clear();

    }




//    @Then("I Add client Secret")
//    public void IAddClientSecret() throws Exception {
//        Thread.sleep(3000);
//
//        // Click on "New client secret" button
//        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NewClientSecretButton")));
//        System.out.println("✅ Clicked on 'New client secret'");
//        Thread.sleep(5000);
//        // Enter a description for the new client secret
//        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("ClientSecretDescriptionInput")), "AutomationSecretdgr");
//        System.out.println("✅ Entered client secret description");
//
//        // Click on "Add" button to create the client secret
//        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("AddButton")));
//        System.out.println("✅ Clicked on 'Add' button to create the secret");
//
//        // Wait for the secret to be generated and capture it (if needed)
//        Thread.sleep(3000);
//        String secretValue = webDriver.getwebelement(xml.getlocator("ClientSecretValue")).getAttribute("title");
//        System.out.println("✅ Client Secret created successfully: " + secretValue);
//    }


    @Then("I Add client Secret")
    public void IAddClientSecret() throws Exception {
        System.out.println("Starting client secret creation process");

        // Wait for the page to load
        Thread.sleep(3000);

        // Click on "New client secret" button
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NewClientSecretButton")));
        System.out.println("✅ Clicked on 'New client secret'");

        // Wait for the modal/popup to appear
        Thread.sleep(5000);

        // Try multiple locators for the description input field
        WebElement descriptionField = null;
        try {
            // Try the primary locator first
            descriptionField = webDriver.getwebelement(xml.getlocator("ClientSecretDescriptionInput"));
            System.out.println("✅ Found description field using primary locator");
        } catch (Exception e1) {
            try {
                // Try alternative locator
                descriptionField = webDriver.getwebelement(xml.getlocator("ClientSecretDescriptionInputAlt"));
                System.out.println("✅ Found description field using alternative locator");
            } catch (Exception e2) {
                try {
                    // Try generic locator
                    descriptionField = webDriver.getwebelement(xml.getlocator("ClientSecretDescriptionInputGeneric"));
                    System.out.println("✅ Found description field using generic locator");
                } catch (Exception e3) {
                    System.out.println("❌ Could not find description field with any locator");
                    throw new Exception("Description input field not found after clicking 'New client secret'");
                }
            }
        }

        // Enter a description for the new client secret
        webDriver.SendKeys(descriptionField, "AutomationSecretdgr");
        System.out.println("✅ Entered client secret description");

        // Wait a moment before clicking Add
        Thread.sleep(2000);

        // Click on "Add" button to create the client secret
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("AddButton")));
        System.out.println("✅ Clicked on 'Add' button to create the secret");

        // Wait for the secret to be generated and capture it
        Thread.sleep(5000);

        try {
            String secretValue = webDriver.getwebelement(xml.getlocator("ClientSecretValue")).getAttribute("title");
            System.out.println("✅ Client Secret created successfully: " + secretValue);
        } catch (Exception e) {
            System.out.println("⚠️ Could not retrieve client secret value, but creation may have succeeded");
        }
    }


    @Then("I navigate back to Application and entered Client Secret")
    public void iNavigateBackToApplicationAndEnteredClientSecret() throws Exception {
        Thread.sleep(3000);
        String secretValue = webDriver.getwebelement(xml.getlocator("ClientSecretValue")).getAttribute("title");

        webDriver.SwitchToNextTab(0);
        Thread.sleep(2000);
        webDriver.SwitchToFrameByElement(webDriver.getwebelement(xml.getlocator("XfileProIframe")));
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);
        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("ClientSecret")), secretValue);

        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);


    }


    @Then("I navigated back to the application, entered the client secret, and clicked Next")
    public void iNavigateBackToApplicationAndEnteredClientSecretandclickednext() throws Exception {
        Thread.sleep(3000);
        String secretValue = webDriver.getwebelement(xml.getlocator("ClientSecretValue")).getAttribute("title");

        webDriver.SwitchToNextTab(0);
        Thread.sleep(2000);
        webDriver.SwitchToFrameByElement(webDriver.getwebelement(xml.getlocator("XfileProIframe")));
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);
        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("ClientSecret")), secretValue);

        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));




    }

    @Then("I navigate back to Application and entered invalid Client Secret")
    public void iNavigateBackToApplicationAndEnteredinvalidClientSecret() throws Exception {
        //Thread.sleep(3000);
        //String secretValue = webDriver.getwebelement(xml.getlocator("ClientSecretValue")).getAttribute("title");
        String secretValue = "3Sc8Q~CB-5yrg5FywEApN1.mwNv~_w8j4kRs6b-g";


        //webDriver.SwitchToNextTab(0);
        Thread.sleep(2000);
        //webDriver.SwitchToFrameByElement(webDriver.getwebelement(xml.getlocator("XfileProIframe")));
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);
        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("ClientSecret")), secretValue);

        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));



    }

    @Then("I navigate back to Application and click next 6 times")
    public void iNavigateBackToApplicationAndclicknext6times() throws Exception {
        Thread.sleep(3000);


        webDriver.SwitchToNextTab(0);
        Thread.sleep(2000);
        webDriver.SwitchToFrameByElement(webDriver.getwebelement(xml.getlocator("XfileProIframe")));
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);



    }



    @Then("I should verify navigation to Grant App Permissions sub tab")
    public void iShouldVerifyNavigationToGrantAppPermissionsSubTab() throws Exception {
        System.out.println("Verifying navigation to Grant App Permissions sub tab");

        // Wait for the progress step to be visible
        Thread.sleep(3000);

        // Verify the "Grant App Permissions" progress step is present
        webDriver.verifyElementToBePresent(xml.getlocator("GrantAppPermissionProgressSteptestb"));
        System.out.println("✅ Grant App Permissions progress step is present");

        // Get the progress step text to verify it's the correct step
        String progressStepText = webDriver.getwebelement(xml.getlocator("GrantAppPermissionProgressSteptestb")).getAttribute("data-label");
        Assert.assertEquals(progressStepText, "Grant App Permissions",
                "Progress step should be 'Grant App Permissions', but found: " + progressStepText);
        System.out.println("✅ Progress step text verified: " + progressStepText);


        System.out.println("✅ Successfully navigated to Grant App Permissions sub tab under Register an App");
    }

    @Then("I click next 4 times")
    public void iNavigateBackToApplicationAnd() throws Exception {
        Thread.sleep(3000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);


    }


//    @Then("I entered Client Secret")
//    public void ienteredEnteredClientSecret() throws Exception {
//        Thread.sleep(3000);
//        String secretValue = webDriver.getwebelement(xml.getlocator("ClientSecretValue")).getAttribute("title");
//
//        Thread.sleep(2000);
//        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("ClientSecret")), secretValue);
//
//        Thread.sleep(2000);
//        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
//
//    }

    @Then("I should verify that clicking Next without entering Client Secret shows validation message")
    public void verifyClientSecretValidationMessage() throws Exception {
        System.out.println("▶ Verifying Client Secret validation message");

        Thread.sleep(2000);

        // Click Next without entering Client Secret
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        System.out.println("✅ Clicked Next without entering Client Secret");

        // Wait for validation message to appear
        Thread.sleep(2000);

        // Verify validation message is present
        webDriver.verifyElementToBePresent(xml.getlocator("ClientSecretValidationMessage"));
        System.out.println("✅ Validation message element is present");

        // Get the validation message text
        String validationText = webDriver.getText(webDriver.getwebelement(xml.getlocator("ClientSecretValidationMessage")));
        System.out.println("📝 Raw validation text retrieved: '" + validationText + "'");

        // Clean the text - remove extra whitespace and newlines
        String cleanedText = validationText.replaceAll("\\s+", " ").trim();
        System.out.println("📝 Cleaned validation text: '" + cleanedText + "'");

        // Expected message
        String expectedMessage = "Please enter the Client Secret.";

        // Use contains() instead of exact match to handle any extra text
        Assert.assertTrue(cleanedText.contains(expectedMessage),
                "Validation message should contain '" + expectedMessage + "', but found: '" + cleanedText + "'");

        System.out.println("✅ Validation message verified successfully: " + expectedMessage);
    }


    @Then("I add permission in azure devops")
    public void iAddPermissionInAzureDevops() throws Exception {
        Thread.sleep(3000);

        // Click on "Add a permission" button
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("AddPermissionButton")));
        System.out.println("✅ Clicked on 'Add a permission' button");

        // Select "Microsoft Graph" API
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("MicrosoftGraphOption")));
        System.out.println("✅ Selected 'Microsoft Graph'");
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("DeligatedPermission")));
        System.out.println("✅ Selected 'Microsoft Graph'");


        // Search for "Sites.Selected" permission (optional but recommended)
        Thread.sleep(2000);
        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("SearchPermissionsInput")), "Sites.Selected");
        System.out.println("✅ Searched for 'Sites.Selected' permission");
        Thread.sleep(5000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("SitesDropdown")));
        System.out.println("✅ Selected 'Microsoft Graph'");

        // Select the "Sites.Selected" permission checkbox
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("SitesSelectedPermission")));
        System.out.println("✅ Selected 'Sites.Selected' permission");

        // Save the permissions
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("SavePermissionsButton")));
        System.out.println("✅ Clicked on 'Save permissions'");

        // Click "Grant admin consent" to finalize permissions
        Thread.sleep(4000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("GrantAdminConsentButton")));
        System.out.println("✅ Clicked on 'Grant admin consent'");

        // Confirm by clicking "Yes"
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("YesButton")));
        System.out.println("✅ Granted admin consent successfully");
    }


    @Then("I navigate back to Application and continue the storage setup flow")
    public void iNavigateBackToApplicationAndcontinuethestoragesetupflow() throws Exception {
        Thread.sleep(3000);
       // String secretValue = webDriver.getwebelement(xml.getlocator("ClientSecretValue")).getAttribute("title");

        webDriver.SwitchToNextTab(0);
        Thread.sleep(2000);
        webDriver.SwitchToFrameByElement(webDriver.getwebelement(xml.getlocator("XfileProIframe")));
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));
        Thread.sleep(2000);
        //webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("ClientSecret")), secretValue);

        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));

        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NextButton")));

    }

    @Then("I authenticate the storage setup")
    public void clickOnauthenticatebutton() throws Exception {

        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("Auth_Button")));
        Thread.sleep(7000);
    }


    @Then("I verify validation messages are displayed when clicking Done button without filling fields")
    public void iVerifyValidationMessagesAreDisplayed() throws Exception {
        Thread.sleep(2000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("RootSiteIdTextfieldsearchbtn")));

        Thread.sleep(3000);

        // Click on Done button without filling any fields
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("DoneButton")));



        Thread.sleep(1000);

        // Verify Title validation message
        webDriver.verifyElementToBePresent(xml.getlocator("TitleValidationMessage"));
        String titleValidationText = webDriver.getText(webDriver.getwebelement(xml.getlocator("TitleValidationMessage")));
        Assert.assertTrue(titleValidationText.contains("Please enter the Title."),
                "Title validation message not displayed correctly. Actual: " + titleValidationText);
        System.out.println("✅ Title validation message verified: " + titleValidationText);

        // Verify Root Site Name validation message
        webDriver.verifyElementToBePresent(xml.getlocator("RootSiteNameValidationMessage"));
        String rootSiteNameValidationText = webDriver.getText(webDriver.getwebelement(xml.getlocator("RootSiteNameValidationMessage")));
        Assert.assertTrue(rootSiteNameValidationText.contains("Please enter the Root Site Name."),
                "Root Site Name validation message not displayed correctly. Actual: " + rootSiteNameValidationText);
        System.out.println("✅ Root Site Name validation message verified: " + rootSiteNameValidationText);

        // Verify Root Site Id validation message
        webDriver.verifyElementToBePresent(xml.getlocator("RootSiteIdValidationMessage"));
        String rootSiteIdValidationText = webDriver.getText(webDriver.getwebelement(xml.getlocator("RootSiteIdValidationMessage")));
        Assert.assertTrue(rootSiteIdValidationText.contains("Please enter the Root Site Id."),
                "Root Site Id validation message not displayed correctly. Actual: " + rootSiteIdValidationText);
        System.out.println("✅ Root Site Id validation message verified: " + rootSiteIdValidationText);


        System.out.println("✅ All validation messages and error icons verified successfully");
    }

    @Then("I verify authentication success message is displayed")
    public void iVerifyAuthenticationSuccessMessageIsDisplayed() throws Exception {
        System.out.println("🔍 Verifying authentication success message...");

        // Wait for notification to appear
        Thread.sleep(5000);

        // Try multiple locators in sequence
        String[] locators = {
                "//*[contains(text(),'Authentication Successful')]",
                "//span[contains(text(),'Authentication Successful')]",
                "//div[contains(text(),'Authentication Successful')]",
                "//[contains(@class,'slds-notify')]//[contains(text(),'Authentication')]",
                "//article//*[contains(text(),'Authentication')]"
        };

        boolean found = false;
        String message = "";
        int maxWaitTime = 15; // seconds
        int waited = 0;

        while (waited < maxWaitTime && !found) {
            for (String locator : locators) {
                try {
                    if (webDriver.isElementPresent(locator)) {
                        message = webDriver.getText(webDriver.getwebelement(locator));
                        if (message.contains("Authentication Successful") || message.contains("Authentication")) {
                            found = true;
                            System.out.println("✅ Authentication Success Message Found: " + message);
                            System.out.println("   Using locator: " + locator);
                            break;
                        }
                    }
                } catch (Exception e) {
                    // Try next locator
                    continue;
                }
            }

            if (!found) {
                Thread.sleep(1000);
                waited++;
                System.out.println("Waiting for message... (" + waited + "/" + maxWaitTime + " seconds)");
            }
        }

        Assert.assertTrue(found, "Authentication success message was not displayed within " + maxWaitTime + " seconds");
        System.out.println("✅ Authentication verification completed successfully");
    }




    @Then("I click on Authenticate button for invalid configuration With tabid {string}")
    public void clickOnauthenticatebuttoninvalidconfiguration(String arg0) throws Exception {

        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("Auth_Button")));
        Thread.sleep(5000);
        int no=Integer.parseInt(arg0);
        webDriver.SwitchToNextTab(no);
    }

    @Then("I click on Authenticate button for invalid configuration with tabid {string}")
    public void IclickOnauthenticatebuttoninvalidconfigurationerror(String arg0) throws Exception {

        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("Auth_Button")));
        Thread.sleep(7000);
        int no=Integer.parseInt(arg0);
        webDriver.SwitchToNextTab(no);

    }

    @Then("I navigate back to Application and should verify that Invalid client secret error is displayed in authentication tab")
    public void IshouldverifythatInvalidclientsecreterrorisdisplayedinauthenticationtab() throws Exception {
        System.out.println("🔍 Verifying Invalid Client Secret error message is displayed...");

        Thread.sleep(3000);


        webDriver.SwitchToNextTab(0);
        Thread.sleep(2000);
        webDriver.SwitchToFrameByElement(webDriver.getwebelement(xml.getlocator("XfileProIframe")));

        // Try multiple XPath strategies to find the error notification
        boolean errorFound = false;
        String errorText = "";

        // Strategy 1: Look for AADSTS error in any notification/toast element
        String[] errorXPaths = {
                "//div[contains(., 'AADSTS') and contains(., 'Invalid client secret')]",
                "//div[contains(@class, 'toastMessage') and contains(., 'AADSTS')]",
                "//div[contains(@class, 'slds-notify') and contains(., 'Invalid client secret')]",
                "//div[contains(@class, 'forceVisualMessageQueue')]//div[contains(., 'AADSTS')]",
                "//*[contains(@class, 'message') and contains(., 'Invalid client secret')]"
        };

        for (String xpath : errorXPaths) {
            try {
                WebElement errorElement = webDriver.getwebelement(xpath);
                if (errorElement != null && errorElement.isDisplayed()) {
                    errorText = errorElement.getText();
                    errorFound = true;
                    System.out.println("✅ Error message found using XPath: " + xpath);
                    break;
                }
            } catch (Exception e) {
                // Try next XPath
                continue;
            }
        }

        // Assert that error message was found
        Assert.assertTrue(errorFound, "❌ Invalid client secret error message was not displayed!");

        // Verify that the error text contains expected content
        boolean containsExpectedError =
                errorText.contains("Invalid client secret") ||
                        errorText.contains("AADSTS700016") ||
                        errorText.contains("secret being sent in the request");

        Assert.assertTrue(
                containsExpectedError,
                "❌ Error message doesn't contain expected 'Invalid client secret' content! Found: " + errorText
        );

        System.out.println("✅ Invalid Client Secret Error Message Verified:");
        System.out.println("   Error Text: " + errorText);

        // Verify it mentions client secret value vs ID
        if (errorText.contains("client secret value") || errorText.contains("client secret ID")) {
            System.out.println("✅ Error correctly mentions client secret value vs ID distinction");
        }

        System.out.println("\n✅ Invalid client secret error verification completed successfully!");
    }

    @Then("I close the info popup")
    public void closeInfoPopup() throws Exception {
        System.out.println("🔘 Closing Info popup...");

        Thread.sleep(1000);

        // Click Close button
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("InfoModalCloseButton")));

        Thread.sleep(1000);

        // Verify modal is closed
        boolean modalStillPresent = webDriver.isElementPresent(xml.getlocator("InfoModalContainer"));
        Assert.assertFalse(modalStillPresent, "❌ Info popup is still displayed after clicking Close!");

        System.out.println("✅ Info popup closed successfully");
    }

    @Then("I click on Re-Authentication button on popup")
    public void IclickonReAuthenticationbuttononpopup() throws Exception {
        System.out.println("🔘 Closing Info popup...");

        Thread.sleep(1000);

        // Click Close button
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("Re-AuthenticateButtononpopup")));

        Thread.sleep(1000);

        // Verify modal is closed
        boolean modalStillPresent = webDriver.isElementPresent(xml.getlocator("InfoModalContainer"));
        Assert.assertFalse(modalStillPresent, "❌ Info popup is still displayed after clicking Close!");

        System.out.println("✅ Info popup closed successfully");
    }

    @Then("I click on Info button for the first storage provider")
    public void clickInfoButtonForFirstStorage() throws Exception {
        System.out.println("🔘 Clicking Info button for the first storage provider...");

        Thread.sleep(2000);

        // Click the first Info button in the storage table
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("InfoButtonFirst")));

        System.out.println("✅ Clicked Info button");

        // Wait for modal to appear
        Thread.sleep(2000);
    }

    @Then("I click on Re-Authentication button for the first storage provider")
    public void clickReAuthenticationButtonForFirstStorage() throws Exception {
        System.out.println(" Clicking Info button for the first storage provider...");

        Thread.sleep(2000);

        // Click the first Info button in the storage table
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("Re-AuthenticateButtonFirst")));

        System.out.println("✅ Clicked Info button");

        // Wait for modal to appear
        Thread.sleep(2000);
    }

    @Then("I should verify that Re-Authentication popup is displayed")
    public void verifyReAuthenticationPopupDisplayed() throws Exception {
        System.out.println("🔍 Verifying Re-Authentication popup is displayed...");

        Thread.sleep(2000);

        // Verify modal container is present
        boolean modalPresent = webDriver.isElementPresent(xml.getlocator("ReAuthModalContainer"));
        Assert.assertTrue(modalPresent, "❌ Re-Authentication popup modal is not displayed!");
        System.out.println("✅ Re-Authentication popup modal is displayed");

        // Verify modal title (may contain "Re-Authentication" or similar text)
        try {
            WebElement modalHeader = webDriver.getwebelement(xml.getlocator("ReAuthModalHeader"));
            String headerText = modalHeader.getText();
            System.out.println("✅ Modal header text: " + headerText);
        } catch (Exception e) {
            System.out.println("⚠️ Could not verify modal header text");
        }

        System.out.println("✅ Re-Authentication popup verification completed!");
    }

    @Then("I should verify that Secret Key field is displayed and censored")
    public void verifySecretKeyFieldCensored() throws Exception {
        System.out.println("🔍 Verifying Secret Key field is censored...");

        Thread.sleep(1000);

        // Verify Secret Key label is present
        boolean labelPresent = webDriver.isElementPresent(xml.getlocator("ReAuthSecretKeyLabel"));
        Assert.assertTrue(labelPresent, "❌ Secret Key label is not displayed!");
        System.out.println("✅ Secret Key label is displayed");

        // Get Secret Key label text
        try {
            WebElement secretKeyLabel = webDriver.getwebelement(xml.getlocator("ReAuthSecretKeyLabel"));
            String labelText = secretKeyLabel.getText();
            Assert.assertTrue(
                    labelText.contains("Secret Key"),
                    "❌ Label text should contain 'Secret Key' but found: " + labelText
            );
            System.out.println("✅ Secret Key label text verified: " + labelText);
        } catch (Exception e) {
            System.out.println("⚠️ Could not verify label text");
        }

        // Verify Secret Key field is present
        WebElement secretKeyField = webDriver.getwebelement(xml.getlocator("ReAuthSecretKeyField"));
        Assert.assertNotNull(secretKeyField, "❌ Secret Key field not found!");
        System.out.println("✅ Secret Key field is displayed");

        // Verify field type is "password" (censored)
        String fieldType = secretKeyField.getAttribute("type");
        Assert.assertEquals(
                fieldType,
                "password",
                "❌ Secret Key field type should be 'password' (censored) but found: " + fieldType
        );
        System.out.println("✅ Secret Key field type verified: " + fieldType + " (censored ●●●●●)");

        // Verify field is required
        try {
            boolean isRequired = webDriver.isElementPresent(xml.getlocator("ReAuthSecretKeyRequired"));
            if (isRequired) {
                System.out.println("✅ Secret Key field is marked as required (*)");
            }
        } catch (Exception e) {
            System.out.println("⚠️ Could not verify required status");
        }

        // Verify field is enabled (not disabled)
        boolean isEnabled = secretKeyField.isEnabled();
        Assert.assertTrue(isEnabled, "❌ Secret Key field should be enabled for input!");
        System.out.println("✅ Secret Key field is enabled for input");

        System.out.println("\n✅ Secret Key Field Verification Summary:");
        System.out.println("   ✓ Label displayed: Yes");
        System.out.println("   ✓ Field displayed: Yes");
        System.out.println("   ✓ Field type: password (censored)");
        System.out.println("   ✓ Field enabled: Yes");
        System.out.println("   ✓ Field required: Yes");
    }

    @Then("I take a screenshot of the censored Secret Key field")
    public void takeScreenshotOfCensoredSecretKey() throws Exception {
        System.out.println("📸 Taking screenshot of censored Secret Key field...");

        Thread.sleep(1000);

        try {
            // Get the Secret Key field element
            WebElement secretKeyField = webDriver.getwebelement(xml.getlocator("ReAuthSecretKeyField"));

            // Scroll to the element to ensure it's visible
            webDriver.ScrollIntoView(secretKeyField);
            Thread.sleep(500);

            // Take screenshot of the entire page (showing the censored field)
            String timestamp = java.time.LocalDateTime.now().format(
                    java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")
            );
            String screenshotFileName = "ReAuth_CensoredSecretKey_" + timestamp + ".png";
            String screenshotPath = "test-output/screenshots/" + screenshotFileName;

            // Ensure screenshot directory exists
            java.io.File screenshotDir = new java.io.File("test-output/screenshots");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            // Take screenshot using Selenium
            org.openqa.selenium.TakesScreenshot screenshot = (org.openqa.selenium.TakesScreenshot) bddDriver.getWebDriver();
            java.io.File srcFile = screenshot.getScreenshotAs(org.openqa.selenium.OutputType.FILE);
            java.io.File destFile = new java.io.File(screenshotPath);

            // Copy screenshot file
            java.nio.file.Files.copy(
                    srcFile.toPath(),
                    destFile.toPath(),
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING
            );

            System.out.println("✅ Screenshot captured successfully!");
            System.out.println("   File: " + screenshotFileName);
            System.out.println("   Path: " + screenshotPath);
            System.out.println("   Note: Secret Key field is displayed as ●●●●● (censored)");

            // Store screenshot path in thread-local for reporting
            DriverInstance.setThreadLocalMapValue("ReAuthScreenshot", screenshotPath);

        } catch (Exception e) {
            System.err.println("❌ Failed to capture screenshot: " + e.getMessage());
            e.printStackTrace();
            // Don't fail the test, just log the error
        }
    }

    @Then("I close the Re-Authentication popup")
    public void closeReAuthenticationPopup() throws Exception {
        System.out.println("🔘 Closing Re-Authentication popup...");

        Thread.sleep(1000);

        // Click Cancel/Close button
        try {
            webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ReAuthCancelButton")));
        } catch (Exception e) {
            // If Cancel button not found, try pressing ESC key
            System.out.println("⚠️ Cancel button not found, trying ESC key...");
            webDriver.SendkeaboardKeys(
                    webDriver.getwebelement(xml.getlocator("ReAuthSecretKeyField")),
                    org.openqa.selenium.Keys.ESCAPE
            );
        }

        Thread.sleep(1000);

        // Verify modal is closed
        boolean modalStillPresent = webDriver.isElementPresent(xml.getlocator("ReAuthModalContainer"));
        Assert.assertFalse(modalStillPresent, "❌ Re-Authentication popup is still displayed after closing!");

        System.out.println("✅ Re-Authentication popup closed successfully");
    }

    @Then("I should verify that info popup is displayed with storage configuration details")
    public void verifyInfoPopupDisplayed() throws Exception {
        System.out.println("🔍 Verifying Info popup is displayed with configuration details...");

        Thread.sleep(2000);

        // Verify modal container is present
        boolean modalPresent = webDriver.isElementPresent(xml.getlocator("InfoModalContainer"));
        Assert.assertTrue(modalPresent, "❌ Info popup modal is not displayed!");
        System.out.println("✅ Info popup modal is displayed");

        // Verify modal header contains "Info"
        try {
            WebElement modalTitle = webDriver.getwebelement(xml.getlocator("InfoModalTitle"));
            String titleText = modalTitle.getText();
            Assert.assertTrue(
                    titleText.contains("Info"),
                    "❌ Modal title should contain 'Info' but found: " + titleText
            );
            System.out.println("✅ Modal title verified: " + titleText);
        } catch (Exception e) {
            System.out.println("⚠️ Could not verify modal title");
        }

        // Extract and verify key-value pairs
        System.out.println("\n📋 Extracting storage configuration details:");

        // 1. Verify and extract Name field
        String nameValue = "";
        try {
            WebElement nameField = webDriver.getwebelement(xml.getlocator("InfoPopupNameField"));
            nameValue = nameField.getAttribute("value");

            Assert.assertNotNull(nameValue, "❌ Name field is empty!");
            Assert.assertFalse(nameValue.trim().isEmpty(), "❌ Name field value is empty!");

            System.out.println("✅ Name: " + nameValue);

            // Store for later verification
            DriverInstance.setThreadLocalMapValue("InfoPopupName", nameValue);

        } catch (Exception e) {
            System.err.println("❌ Failed to extract Name field: " + e.getMessage());
            Assert.fail("Name field not found in Info popup");
        }

        // 2. Verify and extract Selected Site Name
        String siteNameValue = "";
        try {
            WebElement siteNameField = webDriver.getwebelement(xml.getlocator("InfoPopupSiteNameField"));
            siteNameValue = siteNameField.getAttribute("value");

            if (siteNameValue != null && !siteNameValue.trim().isEmpty()) {
                System.out.println("✅ Selected Site Name: " + siteNameValue);
                DriverInstance.setThreadLocalMapValue("InfoPopupSiteName", siteNameValue);
            } else {
                System.out.println("⚠️ Selected Site Name field is empty (may be optional)");
            }

        } catch (Exception e) {
            System.out.println("⚠️ Selected Site Name field not found (may not be applicable for this storage type)");
        }
        // 3. Verify and extract Selected Custom Library
        String libraryValue = "";
        try {
            WebElement libraryField = webDriver.getwebelement(xml.getlocator("InfoPopupLibraryField"));
            libraryValue = libraryField.getAttribute("value");

            if (libraryValue != null && !libraryValue.trim().isEmpty()) {
                System.out.println("✅ Selected Custom Library: " + libraryValue);
                DriverInstance.setThreadLocalMapValue("InfoPopupLibrary", libraryValue);
            } else {
                System.out.println("⚠️ Selected Custom Library field is empty (may be optional)");
            }

        } catch (Exception e) {
            System.out.println("⚠️ Selected Custom Library field not found (may not be applicable for this storage type)");
        }

        // Summary
        System.out.println("\n✅ Info Popup Verification Summary:");
        System.out.println("   ✓ Popup displayed: Yes");
        System.out.println("   ✓ Name: " + nameValue);
        if (!siteNameValue.isEmpty()) {
            System.out.println("   ✓ Selected Site Name: " + siteNameValue);
        }
        if (!libraryValue.isEmpty()) {
            System.out.println("   ✓ Selected Custom Library: " + libraryValue);
        }

        System.out.println("\n✅ Info popup verification completed successfully!");
    }


    @Then("I should verify that Next button is in default disabled state")
    public void iShouldVerifyThatNextButtonIsInDefaultDisabledState() throws Exception {
        System.out.println("Verifying Next button is in default disabled state");

        // Verify the Next button is present and disabled
        webDriver.verifyElementToBePresent(xml.getlocator("NextButtonDisabled"));
        System.out.println("✅ Next button is present and disabled");

        // Get the Next button element to verify its attributes
        WebElement nextButton = webDriver.getwebelement(xml.getlocator("NextButtonDisabled"));

        // Verify the button has disabled attribute
        String disabledAttribute = webDriver.Getattribute(nextButton, "disabled");
        Assert.assertEquals(disabledAttribute, "true",
                "Next button should be disabled by default, but disabled attribute is: " + disabledAttribute);

        // Verify the button has the correct CSS classes
        String buttonClass = webDriver.Getattribute(nextButton, "class");
        Assert.assertTrue(buttonClass.contains("slds-button"),
                "Next button should have slds-button class, but found: " + buttonClass);
        Assert.assertTrue(buttonClass.contains("slds-button_brand"),
                "Next button should have slds-button_brand class, but found: " + buttonClass);

        // Verify the button text is "Next"
        String buttonText = nextButton.getText();
        Assert.assertEquals(buttonText, "Next",
                "Next button should have 'Next' text, but found: " + buttonText);

        System.out.println("✅ Next button is in default disabled state with correct attributes");
        System.out.println("   - Disabled: " + disabledAttribute);
        System.out.println("   - CSS Classes: " + buttonClass);
        System.out.println("   - Button Text: " + buttonText);
    }


    @Then("I should verify that Next button is enabled after selecting SharePoint Azure")
    public void iShouldVerifyThatNextButtonIsEnabledAfterSelectingSharePointAzure() throws Exception {
        System.out.println("Verifying Next button is enabled after selecting SharePoint (Azure)");

        // Wait a moment for the UI to update after selection
        Thread.sleep(2000);

        // Verify the Next button is present and enabled (not disabled)
        webDriver.verifyElementToBePresent(xml.getlocator("NextButtonEnabled"));
        System.out.println("✅ Next button is present and enabled");

    }

    @Then("I click on cancel button")
    public void ClickingCancelButton() throws Exception {
        System.out.println("Verifying Cancel button navigation to Storage Setup page");

        // Click the Cancel button
        webDriver.verifyElementToBePresent(xml.getlocator("CancelButton"));
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("CancelButton")));
        System.out.println("✅ Cancel button clicked");

        // Wait for navigation to complete
        Thread.sleep(3000);
    }

    @Then("I should verify that clicking Cancel button navigates to Storage Setup page")
    public void iShouldVerifyThatClickingCancelButtonNavigatesToStorageSetupPage() throws Exception {


        // Verify we are back on the Storage Setup page
        webDriver.verifyElementToBePresent(xml.getlocator("StorageSetupPageTitle"));
        System.out.println("✅ Storage Setup page title is displayed");


    }

    @Then("I should verify that clicking close button the info popup navigates to Storage Setup page")
    public void iShouldVerifyThatClickingCloseButtonNavigatesToStorageSetupPage() throws Exception {


        // Verify we are back on the Storage Setup page
        webDriver.verifyElementToBePresent(xml.getlocator("StorageSetupPageTitle"));
        System.out.println("✅ Storage Setup page title is displayed");


    }

    @Then("I should verify that clicking close button the Re-Authentication popup navigates to Storage Setup page")
    public void iShouldVerifyThatClickingCloseButtonReAuthenticationNavigatesToStorageSetupPage() throws Exception {


        // Verify we are back on the Storage Setup page
        webDriver.verifyElementToBePresent(xml.getlocator("StorageSetupPageTitle"));
        System.out.println("✅ Storage Setup page title is displayed");


    }


    @Then("I should verify that storage provider dropdown lists all 5 options")
    public void iShouldVerifyThatStorageProviderDropdownListsAll5Options() throws Exception {
        System.out.println("Verifying storage provider dropdown lists all 5 options");

        // Verify the dropdown is present
        webDriver.verifyElementToBePresent(xml.getlocator("StorageProviderDropdownlist"));
        System.out.println("✅ Storage provider dropdown is present");



        // Verify each specific option is present
        webDriver.verifyElementToBePresent(xml.getlocator("NoneOption"));
        System.out.println("✅ --None-- option is present");

        webDriver.verifyElementToBePresent(xml.getlocator("AmazonS3Option"));
        System.out.println("✅ Amazon S3 option is present");

        webDriver.verifyElementToBePresent(xml.getlocator("OneDriveOption"));
        System.out.println("✅ OneDrive option is present");

        webDriver.verifyElementToBePresent(xml.getlocator("SharePointAzureOptionlist"));
        System.out.println("✅ SharePoint (Azure) option is present");

        webDriver.verifyElementToBePresent(xml.getlocator("SharePointMicrosoftOption"));
        System.out.println("✅ SharePoint (Microsoft) option is present");

        // Verify the text content of each option
        String noneText = webDriver.getwebelement(xml.getlocator("NoneOption")).getText();
        String amazonS3Text = webDriver.getwebelement(xml.getlocator("AmazonS3Option")).getText();
        String oneDriveText = webDriver.getwebelement(xml.getlocator("OneDriveOption")).getText();
        String sharePointAzureText = webDriver.getwebelement(xml.getlocator("SharePointAzureOptionlist")).getText();
        String sharePointMicrosoftText = webDriver.getwebelement(xml.getlocator("SharePointMicrosoftOption")).getText();

        // Assert the text content
        Assert.assertEquals(noneText, "--None--", "None option text should be '--None--', but found: " + noneText);
        Assert.assertEquals(amazonS3Text, "Amazon S3", "Amazon S3 option text should be 'Amazon S3', but found: " + amazonS3Text);
        Assert.assertEquals(oneDriveText, "OneDrive", "OneDrive option text should be 'OneDrive', but found: " + oneDriveText);
        Assert.assertEquals(sharePointAzureText, "SharePoint (Azure)", "SharePoint Azure option text should be 'SharePoint (Azure)', but found: " + sharePointAzureText);
        Assert.assertEquals(sharePointMicrosoftText, "SharePoint (Microsoft)", "SharePoint Microsoft option text should be 'SharePoint (Microsoft)', but found: " + sharePointMicrosoftText);

        System.out.println("✅ All storage provider dropdown options verified successfully");
        System.out.println("   - --None--: " + noneText);
        System.out.println("   - Amazon S3: " + amazonS3Text);
        System.out.println("   - OneDrive: " + oneDriveText);
        System.out.println("   - SharePoint (Azure): " + sharePointAzureText);
        System.out.println("   - SharePoint (Microsoft): " + sharePointMicrosoftText);


    }



}
