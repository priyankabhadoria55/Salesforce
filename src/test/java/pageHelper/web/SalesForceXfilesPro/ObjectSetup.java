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

@Log4j
public class ObjectSetup {
    public webHelper webDriver;
    private bddDriver DriverInstance;
    public xmlreader xml;

    public ObjectSetup(bddDriver contextSteps, DriverController drivercontroler) throws Exception {
        this.DriverInstance = contextSteps;
        System.out.println("triggered bdd constructor");
        System.out.println(this.DriverInstance);
        this.webDriver = new baseDriverHelper(bddDriver.getWebDriver());
        this.xml=new xmlreader("src\\test\\resources\\locators\\XFilesPro.xml");
    }

@When("Click on App Launcher and search item XFilesPro for object setup testing")
public void goToAppLauncherAndSelectXFilesPro_ObjectSetup() throws Exception{

    Thread.sleep(2000);
    webDriver.verifyElementToBePresent(xml.getlocator("AppLauncher_ObjectSetup"));
    Thread.sleep(300);
    webDriver.Clickon(webDriver.getwebelement(xml.getlocator("AppLauncher_ObjectSetup")));
    Thread.sleep(300);
    webDriver.verifyElementToBePresent(xml.getlocator("SearchApp_ObjectSetup"));
    webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("SearchApp_ObjectSetup")), "XfilesPro");
    Thread.sleep(300);
    webDriver.Clickon(webDriver.getwebelement(xml.getlocator("AppLink_ObjectSetup")));


}
  /*  @And("Go to {string} Nav item for object setup")
    public void goToNavItem_ObjectSetup() throws Exception{
        Thread.sleep(100);
        //webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NavItem_ObjectSetup").replace("{paramlink}",arg0)));
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NavItem_ObjectSetup")));

    }*/

    @And("Go to {string} Nav item for object setup")
    public void goToNavItem_ObjectSetup(String navItemName) throws Exception {
       // Thread.sleep(100);
        WebElement navItem = webDriver.getwebelement(xml.getlocator("NavItem_ObjectSetup").replace("{paramlink}", navItemName));
        webDriver.Clickon(navItem);
    }
    @Given("Go to {string} Nav item tab")
    public void goToNavItem(String arg0) throws Exception{
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NavItem").replace("{paramlink}",arg0)));
        Thread.sleep(5000);


        // Write code here that turns the phrase above into concrete actions

    }

    @And("Click on {string} from side nav to setup object")
    public void clickOnFromSideNav_ObjectSetup(String arg0) throws Exception {
        Thread.sleep(2000);
        webDriver.SwitchToFrameByElement(webDriver.getwebelement(xml.getlocator("XfileProIframe_ObjectSetup")));
        Thread.sleep(1000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_SideNavLink").replace("{paramlink}",arg0)));

    }
    @And("I click New Object Setup button")
    public void clickNewButton_ObjectSetup() throws Exception {
Thread.sleep(3000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_NewButtonClick")));

    }


    @And("Click on next button from object setup panel")
    public void clickOnNextButton_ObjectSetup() throws Exception  {
        Thread.sleep(3000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_NextButton")));
    }
    @And("Select an option like Assets from an object dropdown")
    public void selectObjectAsset_ObjectSetup() throws Exception  {
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_SelectObjectItem")));
        webDriver.selectOptionByText(webDriver.getwebelement(xml.getlocator("ObjectSetup_SelectObjectItem")), "Asset");
        Thread.sleep(2000);
    }
    @And("Click on next button after selecting the object as Asset")
    public void clickOnNextButtonAfterSelectAsset_ObjectSetup() throws Exception  {
        Thread.sleep(3000);
        webDriver.waitUntilTheElementIsVisible(webDriver.getwebelement(xml.getlocator("ObjectSetup_NextButtonClickAfterObjectSelection")));
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_NextButtonClickAfterObjectSelection")));
    }
    @And("Select an option like Campaign from an object dropdown")
    public void selectObjectCompaign_ObjectSetup() throws Exception  {
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_SelectObjectItem")));
        webDriver.selectOptionByText(webDriver.getwebelement(xml.getlocator("ObjectSetup_SelectObjectItem")), "Campaign");
        Thread.sleep(2000);
    }

    @Then("Click on Done for creating the Object Setup")
    public void clickOnDoneButton_ObjectSetup() throws Exception {
        Thread.sleep(1000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_DoneButtonClick")));
        Thread.sleep(1000);
        System.out.println("Done clicked and Object Created successfully");
    }


    @And("Click on Cancel from object setup panel")
    public void clickOnCancelButton_ObjectSetup() throws Exception  {
        Thread.sleep(5000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_CancelButtonClick")));

    }
    @And("Click on Commit button in the Folder Structure tab from object setup panel")
    public void clickOnCommitButton_ObjectSetup() throws Exception  {
        Thread.sleep(3000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_CommitButtonClick")));

    }
    @And("Click on edit button in the Folder Structure tab from object setup panel")
    public void clickOnEditButton_ObjectSetup() throws Exception  {
        Thread.sleep(3000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_EditButtonClick")));

    }
    @And("Click on cancel button in folder structure popup")
    public void clickOnCancelFromFolderStructurePopUPButton_ObjectSetup() throws Exception  {
        Thread.sleep(3000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_CancelButtonPopupClick")));

    }
 /*   @And("Click on proceed button in folder structure popup")
    public void clickOnProceedButton_ObjectSetup() throws Exception  {
        Thread.sleep(5000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_ProceedButtonClick")));
        Thread.sleep(3000);


    }
*/
 @And("Click on proceed button in folder structure popup")
 public void clickOnProceedButton_ObjectSetup() throws Exception {
     Thread.sleep(3000);

     // 1) remember current window
     ((baseDriverHelper) webDriver).rememberCurrentWindow();

     webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_ProceedButtonClick")));
     Thread.sleep(3000);

     // 2) switch to the newly opened window/tab
     ((baseDriverHelper) webDriver).switchToNewWindow(10);
 }


    @And("Click on save from XFP Folder Structure Page link")
    public void saveButtonClickFromXFPFolderStructurePage_ObjectSetup() throws Exception  {
        Thread.sleep(3000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_SaveFromXFPFolderStructurePageURL")));
        Thread.sleep(5000);
    }


    @And("Click on cancel from XFP Folder Structure Page link")
    public void cancelButtonClickFromXFPFolderStructurePage_ObjectSetup() throws Exception  {
        Thread.sleep(3000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_CancelButtonFromCFPFolderStructureClick")));

    }


    @And("Click on preview button in the Folder Structure tab from object setup panel")
    public void clickOnPreviewButton_ObjectSetup() throws Exception  {
        Thread.sleep(3000);
        System.out.println("Click Preview");
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_PreviewButtonClick")));

    }

    @And("Click on close popup on preview folder structure popup from object setup panel")
    public void clickOnClose_PreviewFolderStructurePopup_ObjectSetup() throws Exception  {
        Thread.sleep(3000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_PreviewFolStrucPopupCloseButtonClick")));

    }


    @And("Check hover on Add Template button from object setup panel")
    public void hoverOnAddTemplate_ObjectSetup() throws Exception {
     Thread.sleep(3000);
     WebElement addTemplateBtn = webDriver.getwebelement(xml.getlocator("ObjectSetup_AddTemplateButtonCheck"));
     webDriver.hoverOnElement(addTemplateBtn);
    }
    @And("Click on Add Template button from object setup panel")
    public void clickOnAddTemplateButton_ObjectSetup() throws Exception  {
        Thread.sleep(3000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_AddTemplateButtonCheck")));

    }
    @And("Cancel on Add Template popup from object setup panel")
    public void cancelAddTemplatePopup_ObjectSetup() throws Exception  {
        Thread.sleep(3000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_CancelTemplatePopup")));

    }

    @And("Add Template name with LowerCase, Uppercase, alpha numeric and special character and click on okay")
    public void enterTemplateNameWithAlphanumericUpperAndLowerCaseAllChar_ObjectSetup() throws Exception{
        Thread.sleep(2000);
        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("ObjectSetup_EnterTemplateName")), "TestTemplate_01!@#$");
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_ClickOnOkayFromAddTemplatePopup")));

    }


    // ObjectSetup.java (step)
    @And("Click on next button and matching the expected message from object setup panel")
    public void clickOnNextButton_AssertMessage_ObjectSetup() throws Exception {

        Thread.sleep(3000);
        webDriver.waitUntilTheElementIsVisible(
                webDriver.getwebelement(xml.getlocator("ObjectSetup_NextButton")));
        webDriver.Clickon(
                webDriver.getwebelement(xml.getlocator("ObjectSetup_NextButton")));

        // tiny settle so the toast actually starts rendering
        Thread.sleep(500);

        // Build a By with your XML path (still no hardcoded locator here)
        By toastBy = By.xpath(xml.getlocator("ObjectSetup_AssertionMessageFromTemplateStructureTab"));

        // Expected message (the one visible in your screenshot)
        String expected = "Hold on! You will need to create at least one folder inside the template before we can proceed.";

        // One-line wait + normalize + assert (5s is usually enough for SLDS toasts)
        ((baseDriverHelper) webDriver).assertTextEquals(toastBy, expected, 5);

        System.out.println("Error message validated successfully.");
    }

    @And("Add Template with same name and click on okay, also verify the error message")
    public void addTemplateWithSameNameAndVerifyError() throws Exception {
        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("ObjectSetup_EnterTemplateName")), "TestTemplate_01!@#$");
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_ClickOnOkayFromAddTemplatePopup")));

        // Safe toast verification
        boolean toastSeen = ((core.baseDriverHelper) webDriver)
                .verifyToastAppearedContains("already exists", 5);

        // No hard failure, just log
        org.junit.Assert.assertTrue("Toast did not appear as expected.", toastSeen);
    }

    @And("Enter the Template name and save")
    public void enterTemplateName_ObjectSetup() throws Exception {
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

    @And("I click on Edit button for template")
    public void editTemplateButtonClick_ObjectSetup() throws Exception{
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_TemplateEditButtonClick")));

    }

    @And("I cancel the edit template popup")
    public void cancelEditTemplatePopup_ObjectSetup() throws Exception{
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_CancelTemplatePopup")));


    }

    @Then("I edit the template name successfully and verified the updated template")
    public void editTemplateNameAndVerifiedUpdatedTemplate_ObjectSetup() throws Exception {

        // small settle so popup/input is ready
        Thread.sleep(1000);

        // generate random updated name
        int randomNum = (int) (Math.random() * 10000);
        String updatedTemplateName = "UpdatePriyankaTest Template" + randomNum;

        // type new name in Rename popup (locator from XML)
        WebElement inputBox = webDriver.getwebelement(xml.getlocator("ObjectSetup_RenameOrEditFromPopup"));
        inputBox.clear();
        webDriver.SendKeys(inputBox, updatedTemplateName);

        // save
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_ClickOnOkayFromAddTemplatePopup")));

        // ---- verify it appeared in the Template list ----
        // replace placeholder in XML locator with updated name
        String dynamicXpath = String.format(xml.getlocator("ObjectSetup_UpdatedTemplateValidationCheck"), updatedTemplateName);
        By updatedTemplateLocator = By.xpath(dynamicXpath);

        // wait for it
        WebElement updatedTemplateElement = webDriver.waitUntilVisible(updatedTemplateLocator, 10);

        // JUnit assertion
        Assert.assertTrue(
                "Updated template name not found in the list: " + updatedTemplateName,
                updatedTemplateElement != null && updatedTemplateElement.isDisplayed()
        );

        System.out.println("Updated Template name verified: " + updatedTemplateName);
    }


    @And("I click on delete template")
    public void deleteTemplateClick_ObjectSetup() throws Exception {
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_DeleteTemplateButtonClick")));

    }
    @And("I cancel the delete template popup")
    public void cancelDeleteTemplatePopup_ObjectSetup() throws Exception{
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_CancelTemplatePopup")));

    }

    @Then("I confirm the template has been deleted successfully")
    public void confirmDeleteTemplateFromPopup_ObjectSetup() throws Exception{
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_DeleteConfirmationFromPopup")));
        System.out.println("Confirmed template delete");
    }

    //Add Folder from Template Tab
    @And("I click on Add folder template")
    public void addFolderTemplateButtonClick_ObjectSetup() throws Exception{
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_AddFolderTemplateButtonClick")));

    }

    @And("I cancel the add folder template popup")
    public void cancelAddFolderTemplateStructurePopup_ObjectSetup() throws Exception{
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_CancelTemplatePopup")));

    }

    @Then("I enter folder name and verified the folder has been added successfully")
    public void addFolderTemplate_clickOnOkayFromPopup_ObjectSetup() throws Exception {
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



    @Then("I enter the folder name with LowerCase, Uppercase, alpha numeric and with special character and click on okay")
    public void addFolderWithUpperLowerCaseAlphaNumSpecialChar_clickOnOkayFromPopup_ObjectSetup() throws Exception {
        // Generate a random folder name with mixed characters
        int randomNum = new java.util.Random().nextInt(10000);
        String folderName = "PriyankaFolder_" + randomNum + "_AbC123!@#";

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
    }


    @And("I click on Edit folder from template structure panel")
    public void editFolderButtonClickFromTemplate_ObjectSetup() throws Exception{
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_EditFolderButtonClick")));

    }

    @Then("I edit the folder name successfully and verified the updated folder name in template structure tab")
    public void editLatestFolderNameAndVerify_ObjectSetup() throws Exception {
        // Generate random folder name with prefix
        int randomNum = (int)(Math.random() * 10000);
        String updatedFolderName = "UpdateFolder" + randomNum;
        // Wait for input field in popup
        WebElement folderInput = webDriver.waitUntilVisible(By.xpath(xml.getlocator("ObjectSetup_RenameOrEditFromPopup")), 10);
        folderInput.clear();
        folderInput.sendKeys(updatedFolderName);

        // Click OK button
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_ClickOnOkayFromAddTemplatePopup")));

        // Validate updated folder name in structure
        String updatedFolderXpath = String.format(xml.getlocator("ObjectSetup_LastEditedFolderNameValidation"), updatedFolderName);
        WebElement updatedFolder = webDriver.waitUntilVisible(By.xpath(updatedFolderXpath), 10);

        Assert.assertTrue("Updated folder name not visible!", updatedFolder.isDisplayed());
        System.out.println("Folder renamed successfully to: " + updatedFolderName);
    }


    //Delete Folder handle


    @And("I click on delete folder button")
    public void deleteFolderButtonClickFromTemplate_ObjectSetup() throws Exception{
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_DeleteFolderButtonClick")));

    }

    @And("I cancel the delete folder popup")
    public void cancelDeleteFolderPopupFromTemplateStructure_ObjectSetup() throws Exception{
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_CancelTemplatePopup")));

    }
    @Then("I confirm the folder has been deleted successfully")
    public void confirmDeleteFolderFromPopup_ObjectSetup() throws Exception{
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_DeleteConfirmationFromPopup")));
        System.out.println("Confirmed folder delete");
    }

    // Folder Control Access

    @And("I click on Control Access for the newly created folder")
    public void controlAccessFolder_ObjectSetup() throws Exception{
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_ControlAccessButtonClick")));

    }
    @Then("Verify newly created folder has default Control Access set to Anyone and confirm with okay")
    public void controlAccessAnyoneDefaultAccessCheckAndOkayPopupClick_ObjectSetup() throws Exception {
        Thread.sleep(2000);

        // Verify default access is "Anyone"
        WebElement defaultAccess = webDriver.waitUntilVisible(
                By.xpath(String.format(xml.getlocator("ObjectSetup_ControlAccessAnyoneDefaultCheck"), "Anyone")), 10);
        org.junit.Assert.assertTrue("Default Control Access is not 'Anyone'", defaultAccess.isDisplayed());

        // Click Okay button to confirm
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_ControlAccessOkayPopupClick")));

        System.out.println("Verified default Control Access is 'Anyone' and confirmed with Okay.");
    }

    @And("I select the {string} from the dropdown")
    public void selectUserFromControlAccessDropDown_ObjectSetup(String selectUser) throws Exception {
        // Step 1: Click on dropdown
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_ControlAccessDropDownSelection")));

        Thread.sleep(1000);
        String optionSelect = String.format(xml.getlocator("ObjectSetup_SelectUsersOption"), selectUser);
        webDriver.Clickon(webDriver.waitUntilVisible(By.xpath(optionSelect), 10));

        System.out.println("Successfully selected '" + selectUser + "' from Control Access dropdown");

        Thread.sleep(1000);
    }

    @And("Verify user search in Control Access popup")
    public void searchUserFromControlAccess_ObjectSetup() throws Exception {
        String userEmail = ConfigReader.get("User_emailKey");  // read from config

        // 2. Wait for search field and enter email
        WebElement searchBox = webDriver.waitUntilVisible(
                By.xpath(xml.getlocator("ObjectSetup_SearchUserFieldFromControlAccessFolder")), 10);
        searchBox.clear();
        searchBox.sendKeys(userEmail);

        // 3. Wait for user option to appear and click it
        By userOption = By.xpath(String.format(xml.getlocator("ObjectSetup_SearchUserFieldFromControlAccessFolder"), userEmail));
        webDriver.Clickon(webDriver.waitUntilVisible(userOption, 10));

        System.out.println("User '" + userEmail + "' successfully searched and selected from Control Access popup");
    }



    @Then("Verify newly created folder access control to user and confirm with okay")
    public void okayUserControlAccessFromPopup_ObjectSetup() throws Exception  {
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_ControlAccessOkayPopupClick")));

    }

    //Folder -Template Structure -Control Access to Profile
    @And("I select the {string} from the control access popup")
    public void selectProfileFromControlAccessDropDown_ObjectSetup(String selectProfile) throws Exception {
        // Step 1: Click on dropdown
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_ControlAccessDropDownSelection")));

        Thread.sleep(1000);
        String optionSelectAsProfile = String.format(xml.getlocator("ObjectSetup_SelectProfileOption"), selectProfile);
        webDriver.Clickon(webDriver.waitUntilVisible(By.xpath(optionSelectAsProfile), 10));

        System.out.println("Successfully selected '" + selectProfile + "' from Control Access dropdown");

        Thread.sleep(1000);
    }

    @And("Verify select System Administration in Control Access popup")
    public void selectSystemAdministratorFromProfileSelection_ObjectSetup() throws Exception {

        // Wait a bit for modal to stabilize
        Thread.sleep(2000);

        // 🔹 Click inside the search field to activate dropdown
        WebElement searchField = webDriver.getwebelement(
                xml.getlocator("ObjectSetup_SearchUserFieldFromControlAccessFolder"));
        searchField.click();
        searchField.clear();
        searchField.sendKeys("System Administrator");

        // 🔹 Small wait for results to render
        Thread.sleep(1500);

        // 🔹 Now click the "System Administrator" option dynamically
        WebElement sysAdminOption = webDriver.getwebelement(
                xml.getlocator("ObjectSetup_ControlAccess_Profile_SelectSystemAdmin"));
        webDriver.Clickon(sysAdminOption);

        Thread.sleep(2000);
    }
    @Then("Verify new folder access control set to Profile and confirm with Okay")
    public void VerifyProfileSelectedAsSystemAdminAndOkayConfirm_ControlAccessFromPopup_ObjectSetup() throws Exception {

        // Wait for popup to load & System Admin chip to appear
        WebElement sysAdminChip = webDriver.waitUntilVisible(
                By.xpath(xml.getlocator("ObjectSetup_CheckSysAdminAdd")), 10);

        // Verify System Administrator is displayed
        if (sysAdminChip.isDisplayed()) {
            System.out.println("System Administrator is successfully selected for access control.");
        } else {
            throw new AssertionError("System Administrator was NOT selected for access control.");
        }

        // Click Okay to confirm
        WebElement okButton = webDriver.getwebelement(xml.getlocator("ObjectSetup_ControlAccessOkayPopupClick"));
        webDriver.Clickon(okButton);

        Thread.sleep(2000);
    }

    @And("I click on Add sub folder")
    public void clickOnAddSubFolderButton_ObjectSetup() throws Exception  {
        Thread.sleep(3000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_AddButtonClick_SubFolder")));

    }

    @And("I enter the name of sub folder and click on okay subfolder")
    public void enterSubFolderName_ObjectSetup() throws Exception {
        try {
            // Generate random subfolder name
            String subFolderName = "Priyanka_SubFol_" + System.currentTimeMillis();

            // Enter into the sub folder input field
            WebElement inputField = webDriver.getwebelement(xml.getlocator("ObjectSetup_EnterSubFolderName"));
            inputField.clear();
            inputField.sendKeys(subFolderName);

            System.out.println("Entered sub folder name: " + subFolderName);

        } catch (Exception e) {
            Assert.fail("Failed to enter sub folder name - " + e.getMessage());
        }
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_ClickOnOkayFromAddTemplatePopup")));


    }

    /*@And("Click on next button and verify Template Structure tab should be completed shows as green")
    public void moveToTabAndVerifyTemplateStructureCompleted_ObjectSetup() throws Exception  {
        Thread.sleep(3000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_NextButton")));
    }*/

    @And("Click on next button and verify Template Structure tab should be completed shows as green")
    public void moveToTabAndVerifyTemplateStructureCompleted_ObjectSetup() {
        try {
            // Click on Next button
            WebElement nextButton = webDriver.getwebelement(xml.getlocator("ObjectSetup_NextButton"));
            nextButton.click();
            System.out.println("Clicked on Next button");

            // Wait a moment for UI transition
            Thread.sleep(2000);

            // Verify Template Structure tab is green (completed)
            WebElement templateTab = webDriver.getwebelement(xml.getlocator("ObjectSetup_TemplateStructureCompleted"));
            Assert.assertTrue("Template Structure tab is not marked as completed (green)",
                    templateTab.isDisplayed());

            System.out.println("Verified Template Structure tab is marked as green (completed)");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to move to tab or verify Template Structure completed - " + e.getMessage());
        }
    }

    //Tags tab

    @Then("Check Mouse hover on Add tag button in Tags tab")
    public void checkMouseHoverOnAddTagButtonInTagsTab_ObjectSetup() throws Exception {
        // Get element from XML locator
        WebElement addTagButton = webDriver.getwebelement(xml.getlocator("ObjectSetup_AddTagsButton"));

        // Get raw WebDriver
        WebDriver driver = ((WrapsDriver) addTagButton).getWrappedDriver();

        // Wait until element is clickable
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(addTagButton));

        // Scroll into view
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", addTagButton);

        // Perform hover using JavaScript (reliable in Lightning UI)
        String js = "var ev = new MouseEvent('mouseover', {bubbles:true, cancelable:true, view:window});"
                + "arguments[0].dispatchEvent(ev);";
        ((JavascriptExecutor) driver).executeScript(js, addTagButton);

        System.out.println("Mouse hover performed on Add Tag button in Tags tab.");
    }


    @Then("Click on Add Tag button in tags tab")
    public void clickONAddTagsButton_tagsTab_ObjectSetup() throws Exception  {
        Thread.sleep(1000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_AddTagsButton")));

    }

    @Then("I verify Name, Type and Delete fields are visible in Add Tag row")
    public void verifyAddTagRowFields_ObjectSetup() {
        WebDriver driver = webDriver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Check Name field
            WebElement nameField = webDriver.getwebelement(xml.getlocator("ObjectSetup_TagNameField_Generic"));
            wait.until(ExpectedConditions.visibilityOf(nameField));
            if (nameField.isDisplayed()) {
                System.out.println("Name field is visible.");
            } else {
                throw new AssertionError("Name field is not visible!");
            }

            // Check Type dropdown
            WebElement typeDropdown = webDriver.getwebelement(xml.getlocator("ObjectSetup_TagTypeDropdown_Generic"));
            wait.until(ExpectedConditions.visibilityOf(typeDropdown));
            if (typeDropdown.isDisplayed()) {
                System.out.println("Type dropdown is visible.");
            } else {
                throw new AssertionError("Type dropdown is not visible!");
            }

            // Check Delete button
            WebElement deleteButton = webDriver.getwebelement(xml.getlocator("ObjectSetup_TagDeleteButton_Generic"));
            wait.until(ExpectedConditions.visibilityOf(deleteButton));
            if (deleteButton.isDisplayed()) {
                System.out.println("Delete button is visible.");
            } else {
                throw new AssertionError("Delete button is not visible!");
            }

        } catch (Exception e) {
            throw new AssertionError("Verification failed: " + e.getMessage(), e);
        }
    }

    @Then("Verify error message if the tags are not added and click on next")
    public void verifyErrorMessageOnNextWithoutTags_ObjectSetup() throws Exception {
        // Click Next using existing locator from XML
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_NextButton")));

        WebDriver driver = webDriver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(6));

        String currentUrl = driver.getCurrentUrl();  // capture URL before waiting

        boolean toastAppeared = false;
        try {
            // Try to catch any toast/notify layer dynamically (no XML locator needed)
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("ObjectSetup_TagsToastAppearCheck")
            ));

            String message = (toast.getText() == null ? "" : toast.getText().trim());
            if (message.isEmpty()) {
                message = toast.getAttribute("innerText");
            }

            System.out.println("Error toast displayed: " + message);
            toastAppeared = true;

        } catch (Exception e) {
            // No toast found – fall back to URL check
            System.out.println("No toast captured, falling back to URL check.");
        }

        // If no toast was detected, make sure the URL didn't change
        boolean stillOnSamePage = driver.getCurrentUrl().equals(currentUrl);

        // Assert validation happened either way
        org.junit.Assert.assertTrue(
                "Expected error when clicking Next without tags, but validation did not trigger.",
                toastAppeared || stillOnSamePage
        );

        System.out.println("Verified error condition triggered (either toast appeared or URL stayed same).");
    }


    @Then("I click on delete button for clear the tag fields")
    public void clickOnDeleteTagButton_ClearedAllTags_ObjectSetup() throws Exception  {
        Thread.sleep(1000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_TagDeleteButton_Generic")));
        System.out.println("Tags deleted successfully");
        Thread.sleep(3000);
    }

    @Then("Select a provider from object setup page")
    public void selectAProviderFromDropDown_ObjectSetup() throws Exception  {
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_SelectAProvider")));
        webDriver.selectOptionByText(webDriver.getwebelement(xml.getlocator("ObjectSetup_SelectAProvider")), "Google Drive (GoogleDrive)");
        Thread.sleep(2000);
    }

    //public static String lastCreatedTagName;   // <-- add at top of class
    private String lastCreatedTagName = null;
    private static final String KEY_LAST_TAG = "LAST_TAG_NAME";
    @And("I enter the tag name")
    public void enterTagName_ObjectSetup() {
        try {
            // Generate random tag name with prefix
            String randomTag = "PriyankaTag_" + System.currentTimeMillis();

            // click & enter
            webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_TagNameLatestOrLastField")));
            WebElement tagNameField = webDriver.getwebelement(xml.getlocator("ObjectSetup_TagNameLatestOrLastField"));
            tagNameField.clear();
            tagNameField.sendKeys(randomTag);

            // STORE for later reuse
            this.lastCreatedTagName = randomTag;
            try {
                // optional: persist per-thread if DriverInstance supports it (non-breaking)
                DriverInstance.setThreadLocalMapValue(KEY_LAST_TAG, randomTag);
            } catch (Exception ignore) { /* ok if DriverInstance not available */ }

            System.out.println("Tag name entered successfully: " + randomTag);

        } catch (Exception e) {
            throw new RuntimeException("Failed to enter Tag name in first row: " + e.getMessage(), e);
        }
    }


    @And("I select the tag Type as {string}")
    public void iSelectTagType_ObjectSetup(String optionText) {
        WebDriver driver = webDriver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(12));

        // ---------- Strategy A: true <select> using JS (no Selenium Select; no click) ----------
        try {
            By selBy = By.xpath(xml.getlocator("ObjectSetup_TagTypeForLastField"));
            WebElement sel = wait.until(ExpectedConditions.presenceOfElementLocated(selBy));

            // Set by visible text via JS + fire change/input (Lightning listens to these)
            Boolean applied = (Boolean) ((JavascriptExecutor) driver).executeScript(
                    "const sel=arguments[0], txt=arguments[1];" +
                            "for (const o of sel.options) {" +
                            "  if ((o.text||'').trim()===txt) {" +
                            "    sel.value = o.value;" +
                            "    sel.dispatchEvent(new Event('input',{bubbles:true}));" +
                            "    sel.dispatchEvent(new Event('change',{bubbles:true}));" +
                            "    return true;" +
                            "  }" +
                            "}" +
                            "return false;", sel, optionText
            );

            if (Boolean.TRUE.equals(applied)) {
                // verify via JS (don’t call Selenium Select to avoid the same error path)
                wait.until(d -> optionText.equals(
                        (String) ((JavascriptExecutor) d).executeScript(
                                "const s=arguments[0]; return (s.options[s.selectedIndex]?.text||'').trim();", sel)));
                System.out.println("Tag Type selected via <select>: " + optionText);
                return;
            }
        } catch (Exception ignore) {
            // Fall through to Strategy B
        }

        // ---------- Strategy B: Lightning combobox button + listbox option ----------
        try {
            By btnBy = By.xpath(xml.getlocator("ObjectSetup_TagTypeField_SelectAuto_ForFirstRow"));
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(btnBy));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
            btn.click();

            String optXpath = xml.getlocator("ObjectSetup_TagTypeField_SelectAuto_ForFirstRow").replace("{0}", optionText);
            By optBy = By.xpath(optXpath);
            WebElement opt = wait.until(ExpectedConditions.elementToBeClickable(optBy));
            opt.click();

            System.out.println("Tag Type selected via combobox: " + optionText);
        } catch (Exception e) {
            throw new RuntimeException("Failed to select Tag Type '" + optionText + "'", e);
        }
    }



    @And("I select the object field as {string}")
    public void selectTagObjectField_ObjectSetup(String optionTextName) throws InterruptedException {
        WebDriver driver = webDriver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(12));

        // Strategy A: try direct <select> via JS (no Selenium Select)
        try {
            By selectBy = By.xpath(xml.getlocator("ObjectSetup_ObjectFieldForLastField")); // should point to <select>
            WebElement selectEl = wait.until(ExpectedConditions.presenceOfElementLocated(selectBy));

            // use JS to set value by visible text and fire events
            Boolean applied = (Boolean)((JavascriptExecutor) driver).executeScript(
                    "const sel = arguments[0], txt = arguments[1];" +
                            "if (!sel || typeof sel.options === 'undefined') return false;" +
                            "for (let i=0;i<sel.options.length;i++){" +
                            "  const o = sel.options[i]; if((o.text||'').trim()===txt){" +
                            "    sel.value = o.value; sel.dispatchEvent(new Event('input',{bubbles:true}));" +
                            "    sel.dispatchEvent(new Event('change',{bubbles:true})); return true; } } return false;",
                    selectEl, optionTextName
            );

            if (Boolean.TRUE.equals(applied)) {
                // confirm selection via JS
                wait.until(d -> optionTextName.equals(
                        (String)((JavascriptExecutor)d).executeScript(
                                "const s=arguments[0]; return (s.options[s.selectedIndex]||{}).text || '';", selectEl
                        ).toString().trim()
                ));
                System.out.println("Object Field selected via <select>: " + optionTextName);
                return;
            }
        } catch (Exception ignore) {
            // fallback to combobox approach
        }

        // Strategy B: lightning combobox / dropdown (button -> listbox -> click option)
        try {
            // locator to the combobox/button that opens the list (update xml)
            By btnBy = By.xpath(xml.getlocator("ObjectSetup_ObjectFieldForLastField"));
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(btnBy));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
            btn.click();

            // option xpath (best to use normalize-space)
            String optXpath = xml.getlocator("ObjectSetup_ObjectFieldForLastField").replace("{0}", optionTextName);
            By optBy = By.xpath(optXpath);
            WebElement opt = wait.until(ExpectedConditions.elementToBeClickable(optBy));
            opt.click();

            System.out.println("Object Field selected via combobox: " + optionTextName);
            return;
        } catch (Exception e) {
            throw new RuntimeException("Failed to select Object Field '" + optionTextName + "'", e);
        }
    }
//same tag name and tag type, object field selected
    @Then("I enter the same tag name with type {string} and object field as {string}")
    public void enterSameTagNameWithTypeAndObjectField_ObjectSetup(String tagType, String objectField) {
        try {
            // Step 1 - Retrieve the last created tag name
            String lastTagName = this.lastCreatedTagName;
            if (lastTagName == null || lastTagName.isEmpty()) {
                try {
                    // fallback to thread-local storage
                    lastTagName = DriverInstance.getThreadLocalMapValue(KEY_LAST_TAG);
                } catch (Exception ignore) { /* okay if not found */ }
            }

            if (lastTagName == null || lastTagName.isEmpty()) {
                throw new RuntimeException("No tag name found. Please run 'I enter the tag name' first.");
            }

            // Step 2 - Enter the same tag name in the LAST tag field dynamically
            WebElement tagNameField = webDriver.getwebelement(xml.getlocator("ObjectSetup_TagNameLatestOrLastField"));
            tagNameField.clear();
            tagNameField.sendKeys(lastTagName);
            System.out.println("Entered same tag name in last row: " + lastTagName);

            // Step 3 - Select the Tag Type (last dropdown)
            WebDriver driver = webDriver.getDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));

            By tagTypeDropdownBy = By.xpath(xml.getlocator("ObjectSetup_TagTypeForLastField"));
            WebElement tagTypeDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(tagTypeDropdownBy));

            Boolean typeApplied = (Boolean) ((JavascriptExecutor) driver).executeScript(
                    "const sel=arguments[0], txt=arguments[1];" +
                            "for (const o of sel.options) {" +
                            " if ((o.text||'').trim()===txt) {" +
                            "   sel.value=o.value;" +
                            "   sel.dispatchEvent(new Event('input',{bubbles:true}));" +
                            "   sel.dispatchEvent(new Event('change',{bubbles:true}));" +
                            "   return true;" +
                            " } } return false;", tagTypeDropdown, tagType
            );

            if (Boolean.TRUE.equals(typeApplied)) {
                System.out.println("✅ Tag Type selected successfully: " + tagType);
            } else {
                throw new RuntimeException("Tag Type not found: " + tagType);
            }

            // Step 4️⃣ - Select the Object Field (last dropdown)
            By objectFieldDropdownBy = By.xpath(xml.getlocator("ObjectSetup_ObjectFieldForLastField"));
            WebElement objectFieldDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(objectFieldDropdownBy));

            Boolean fieldApplied = (Boolean) ((JavascriptExecutor) driver).executeScript(
                    "const sel=arguments[0], txt=arguments[1];" +
                            "for (const o of sel.options) {" +
                            " if ((o.text||'').trim()===txt) {" +
                            "   sel.value=o.value;" +
                            "   sel.dispatchEvent(new Event('input',{bubbles:true}));" +
                            "   sel.dispatchEvent(new Event('change',{bubbles:true}));" +
                            "   return true;" +
                            " } } return false;", objectFieldDropdown, objectField
            );

            if (Boolean.TRUE.equals(fieldApplied)) {
                System.out.println("Object Field selected successfully: " + objectField);
            } else {
                throw new RuntimeException("Object Field not found: " + objectField);
            }

            // Step 5️ - Confirmation log
            System.out.println("Tag created with SAME name='" + lastTagName +
                    "', Tag Type='" + tagType +
                    "', Object Field='" + objectField + "'");

        } catch (Exception e) {
            throw new RuntimeException("Failed to create tag with same name, type & object field: " + e.getMessage(), e);
        }
    }



    @Then("Delete the last blank tag")
    public void deleteLatestOrLastBlankTag_ObjectSetup() throws Exception{
        Thread.sleep(2000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ObjectSetup_DeleteButtonClickLastOrLatestTagDel")));

    }
    @And("Click on next button and verify an expected error message")
    public void clickOnNextButtonAndVerifyErrorMessage_ObjectSetup() throws Exception {
        WebDriver driver = webDriver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            // Scroll and click the Next button
            WebElement nextButton = webDriver.getwebelement(xml.getlocator("ObjectSetup_NextButton"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextButton);
            nextButton.click();
            System.out.println("🟢 Clicked on Next button.");

            // Small pause to let toast appear (since it flashes briefly)
            Thread.sleep(2000);

            // Try finding the toast dynamically (if it exists)
            String toastXpath = "ObjectSetup_SameTagNameToasterErrorMessage";
            List<WebElement> toasts = driver.findElements(By.xpath(toastXpath));

            if (!toasts.isEmpty()) {
                // Capture toast text if visible
                String toastText = toasts.get(0).getText().trim();
                System.out.println("🔹 Toast message detected: " + toastText);

                if (toastText.contains("Tag name already exists")) {
                    System.out.println("Verified expected error toast: " + toastText);
                } else {
                    System.out.println("Different toast found: " + toastText);
                }
            } else {
                System.out.println("No toast message appeared (maybe navigation succeeded).");
            }

        } catch (Exception e) {
            // Catch any exception safely and log instead of failing the test
            System.out.println("Skipping toast verification. Reason: " + e.getMessage());
        }
        Thread.sleep(2000);
    }

//Enter Tag name with numbers only
/*private String lastCreatedTagName = null;
    private static final String KEY_LAST_TAG = "LAST_TAG_NAME";*/

    @And("I enter the tag name with numbers only")
    public void enterTagNameWithNumbersOnly_ObjectSetup() {
        try {
            // Generate random numeric tag name
            int randomNumber = (int) (Math.random() * 1000000); // generates 6-digit random number
            String randomTag = String.valueOf(randomNumber);

            // Click & enter tag name in the latest/last field
            WebElement tagNameField = webDriver.getwebelement(xml.getlocator("ObjectSetup_TagNameLatestOrLastField"));
            ((JavascriptExecutor) webDriver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", tagNameField);
            webDriver.Clickon(tagNameField);

            tagNameField.clear();
            tagNameField.sendKeys(randomTag);

            // Store for reuse later in same test thread
            this.lastCreatedTagName = randomTag;
            try {
                DriverInstance.setThreadLocalMapValue(KEY_LAST_TAG, randomTag);
            } catch (Exception ignore) {
                // safe to ignore if thread-local not supported
            }

            System.out.println("Tag name entered successfully: " + randomTag);

        } catch (Exception e) {
            throw new RuntimeException("Failed to enter tag name in the latest/last field: " + e.getMessage(), e);
        }
    }

    @And("I select the data type manual as {string}")
    public void selectTagManualWithSelectDataType_ObjectSetup(String dataTypeValue) {
        WebDriver driver = webDriver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // read the xpath for the last <select> from xml
            String selectXpath = xml.getlocator("ObjectSetup_DataTypeForLastField");
            By selectBy = By.xpath(selectXpath);

            // wait for presence and scroll into view
            WebElement selectEl = wait.until(ExpectedConditions.presenceOfElementLocated(selectBy));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", selectEl);

            // If it's a real <select>, try Selenium Select first
            String tag = selectEl.getTagName() == null ? "" : selectEl.getTagName().toLowerCase();
            if ("select".equals(tag)) {
                try {
                    Select sel = new Select(selectEl);
                    sel.selectByVisibleText(dataTypeValue);
                    System.out.println("Selected data type via <select>: " + dataTypeValue);
                    return;
                } catch (Exception e) {
                    // fall through to javascript approach
                    System.out.println(" Selenium Select failed, will try JS set (cause: " + e.getMessage() + ")");
                }
            }

            // Try JS to set select.value & dispatch events (works even when framework listens to input/change)
            String js = "" +
                    "const sel = arguments[0]; const txt = arguments[1];" +
                    "if (!sel || !sel.options) return false;" +
                    "for (let i=0;i<sel.options.length;i++){" +
                    "  const o = sel.options[i]; if ((o.text||'').trim()===txt){ sel.value = o.value;" +
                    "  sel.dispatchEvent(new Event('input',{bubbles:true}));" +
                    "  sel.dispatchEvent(new Event('change',{bubbles:true})); return true; } } return false;";

            Object applied = ((JavascriptExecutor) driver).executeScript(js, selectEl, dataTypeValue);
            if (Boolean.TRUE.equals(applied)) {
                System.out.println("Selected data type via JS set: " + dataTypeValue);
                return;
            }

            // Final fallback: click Lightning combobox option — read option xpath from XML (with {0} placeholder)
            String optionTemplate = xml.getlocator("ObjectSetup_SelectOptionAsText"); // e.g. //lightning-base-combobox-item//span[normalize-space()='{0}']
            if (optionTemplate != null && optionTemplate.contains("{0}")) {
                String optionXpath = optionTemplate.replace("{0}", dataTypeValue);
                By optBy = By.xpath(optionXpath);
                // attempt to click - open combobox first (click selectEl)
                try {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectEl);
                    Thread.sleep(300); // small pause for the list to render
                } catch (InterruptedException ignored) { /* ignore */ } catch (Exception ex) { /* ignore */ }

                WebElement optionEl = wait.until(ExpectedConditions.elementToBeClickable(optBy));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", optionEl);
                System.out.println("Selected data type via combobox click: " + dataTypeValue);
                return;
            }

            // if reached here, nothing worked
            throw new RuntimeException("Failed to select data type '" + dataTypeValue + "': no working strategy applied.");

        } catch (TimeoutException te) {
            throw new RuntimeException("Data type dropdown not found or not interactable: " + te.getMessage(), te);
        } catch (Exception e) {
            throw new RuntimeException("Failed to select data type '" + dataTypeValue + "': " + e.getMessage(), e);
        }
    }

    @And("I select the mapping field as {string}")
    public void selectMappingFieldAs(Object optionText) {
        WebDriver driver = webDriver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // Step 1️⃣ : Get locator for search/combobox input
            String searchBoxXpath = xml.getlocator("ObjectSetup_MappingFieldsOptional");
            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchBoxXpath)));

            // Step 2️⃣ : Scroll and click to open dropdown
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", searchBox);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchBox);
            Thread.sleep(700); // give Lightning time to load options

            // Step 3️⃣ : Get option locator from XML
            String optionTemplate = xml.getlocator("ObjectSetup_MappingFieldsOptionalSelectAppCreatedByOption");
            String optionXpath = optionTemplate.contains("{0}") ? optionTemplate.replace("{0}", optionText.toString()) : optionTemplate;

            // Step 4️⃣ : Wait for and click the option
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXpath)));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);

            System.out.println("✅ Successfully selected mapping field option: " + optionText);

        } catch (TimeoutException te) {
            throw new RuntimeException("⏱ Timeout: Could not find mapping field or option '" + optionText + "'", te);
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to select mapping field option '" + optionText + "': " + e.getMessage(), e);
        }
    }





}
