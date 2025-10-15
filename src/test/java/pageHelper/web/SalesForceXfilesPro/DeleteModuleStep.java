package pageHelper.web.SalesForceXfilesPro;

import core.baseDriverHelper;
import core.webHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j;
import org.dom4j.DocumentException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pageHelper.bddDriver;
import utils.DriverController;
import utils.xmlreader;

import java.io.File;
import java.io.IOException;

import core.baseDriverHelper;
import core.webHelper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j;
import org.dom4j.DocumentException;
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
import java.util.List;
import java.util.Map;
import java.util.Random;

@Log4j
    public class DeleteModuleStep {
        public webHelper webDriver;
        private bddDriver DriverInstance;
        public xmlreader xml;
        public xmlreader locators;

        public DeleteModuleStep(bddDriver contextSteps, DriverController drivercontroler) throws Exception {
            this.DriverInstance = contextSteps;
            System.out.println("triggered bdd contructoctor");
            System.out.println(this.DriverInstance);
            webDriver = new baseDriverHelper(bddDriver.getWebDriver());
            xml=new xmlreader("src\\test\\resources\\locators\\salesforce.xml");
        }

        public DeleteModuleStep(DriverController drivercontroler) {
            webDriver = new baseDriverHelper(drivercontroler.getDriver());
        }




        @Given("I navigate to the {string} record module")
        public void openFromAppLaucheruploadrecordmodule(String arg0) throws Exception {
            webDriver.verifyElementToBePresent(xml.getlocator("AppLauncher"));
            webDriver.Clickon(webDriver.getwebelement(xml.getlocator("AppLauncher")));
            Thread.sleep(300);
            webDriver.verifyElementToBePresent(xml.getlocator("SearchApp"));
            webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("SearchApp")), arg0);
            Thread.sleep(300);
            webDriver.verifyElementToBePresent(xml.getlocator("AppLink").replace("{paramlink}",arg0));
            webDriver.safeJavaScriptClick(webDriver.getwebelement(xml.getlocator("AppLink").replace("{paramlink}",arg0)));
            Thread.sleep(500);
//        webDriver.waitforElementNotToBeVisible(xml.getlocator("AppLink").replace("{paramlink}",arg0));
        }




        @Then("Perform {string} Operation for create new record")
        public void performtriggerNewOperationforcreatenewrecord(String arg0) throws Exception {

            webDriver.safeJavaScriptClick(webDriver.getwebelement(xml.getlocator("Click_New_btn").replace("{paramlink}", arg0)));
        }


    @And("Create new Account with fill all the fields with random generated data under delete module")
    public void createAccountWithRandomData() throws Exception {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║  🎲 CREATING RANDOM ACCOUNT 🎲     ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        Random random = new Random();

        // Generate all random data
        String[] prefixes = {"Tech", "Global", "Dynamic", "Smart", "Digital"};
        String[] suffixes = {"Corp", "Inc", "Solutions", "Systems", "Group"};
        String timestamp = LocalTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));
        String accountName = prefixes[random.nextInt(prefixes.length)] +
                suffixes[random.nextInt(suffixes.length)] + "_" + timestamp;

        String phone = String.valueOf(6 + random.nextInt(4)) + String.format("%09d", random.nextInt(1000000000));
        String fax = String.format("%010d", random.nextInt(1000000000));
        String website = "www.test" + random.nextInt(9999) + ".com";

        String[] types = {"Customer", "Partner", "Competitor"};
        String[] industries = {"Technology", "Banking", "Healthcare"};
        String type = types[random.nextInt(types.length)];
        String industry = industries[random.nextInt(industries.length)];

        String employees = String.valueOf(10 + random.nextInt(9990));
        String revenue = String.valueOf(100000 + random.nextInt(9900000));
        String description = "Auto-generated account - " + random.nextInt(9999);

        String[] cities = {"Mumbai", "Delhi", "Bangalore"};
        String billingStreet = (100 + random.nextInt(900)) + " Main St";
        String billingCity = cities[random.nextInt(cities.length)];

        System.out.println("🏢 Account: " + accountName);
        System.out.println("📞 Phone: " + phone);

        // Save for later use
        DriverInstance.setThreadLocalMapValue("CreatedAccountName", accountName);

        // Fill form
        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("AccountNameInput")), accountName);
        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("AccountPhoneInput")), phone);
        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("AccountFaxInput")), fax);
        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("AccountWebsiteInput")), website);

        Thread.sleep(1000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("AccountTypeDropdown")));
        Thread.sleep(1000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("AccountTypeOption").replace("{paramlink}", type)));

        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("AccountEmployeesInput")), employees);

        Thread.sleep(1000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("AccountIndustryDropdown")));
        Thread.sleep(1000);
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("AccountIndustryOption").replace("{paramlink}", industry)));

        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("AccountAnnualRevenueInput")), revenue);
        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("AccountDescriptionTextarea")), description);
        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("BillingStreetTextarea")), billingStreet);
        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("BillingCityInput")), billingCity);

        System.out.println("✅ Form filled!\n");
    }




    @Given("Go to Details tab under newly added account under delete module")
    public void clickondetailstabnewlyaddedaccountunderdeletemodule() throws Exception {
        webDriver.verifyElementToBePresent(xml.getlocator("Details_tab"));
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("Details_tab")));
        Thread.sleep(2000);

    }
//        @Then("Perform {string} Operation")
//        public void performOperation(String operation) throws Exception {
//            System.out.println("Performing operation: " + operation);
//            Thread.sleep(2000);
//
//            if (operation.equalsIgnoreCase("New")) {
//                // Click on New button
//                webDriver.Clickon(webDriver.getwebelement(xml.getlocator("EntityOperation").replace("{paramlink}", operation)));
//                Thread.sleep(3000);
//                System.out.println("✅ Clicked on 'New' button");
//            }
//        }


        @Then("{string} new Record")
        public void record(String arg0) throws Exception {
            String locator = xml.getlocator("Save_Record_Button").replace("{paramlink}",arg0);
            webDriver.Clickon(webDriver.getwebelement(locator));
        }

        @Then("I search for newly created account {string}")
        public void searchThenewlycreatedRecordInListViewunderdeletemodule(String arg0) throws InterruptedException, IOException, DocumentException {
            String Name=arg0;
            if(Name.equalsIgnoreCase("NewlyAddedTestRequirement"))
            {
                Name=DriverInstance.getThreadLocalMapValue("Training Requirement Name");
            }
            else if (Name.equalsIgnoreCase("Newly Added Opportunity"))
            {
                Name=DriverInstance.getThreadLocalMapValue("Opportunity Name");

            }
            webDriver.verifyElementToBePresent(xml.getlocator("GridSearch").replace("{paramlink}",Name));
            webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("GridSearch")), Name);
            webDriver.SendkeaboardKeys(webDriver.getwebelement(xml.getlocator("GridSearch").replace("{paramlink}",Name)), Keys.ENTER);

        }





//        @Given("Go to Details tab under upload module")
//        public void clickondetailstabunderuploadmodule() throws Exception {
//            webDriver.verifyElementToBePresent(xml.getlocator("Details_tab"));
//            webDriver.Clickon(webDriver.getwebelement(xml.getlocator("Details_tab")));
//            Thread.sleep(2000);
//
//        }



//        @Then("Upload the {string} file in {string} section By {string} under upload module")
//        public void uploadTheFileInSectionuploadmodule(String arg0, String arg1,String arg2) throws Exception {
//            String projectDir = System.getProperty("user.dir");
//            String filePath = projectDir + File.separator + "lib"
//                    + File.separator + "XfileProData"
//                    + File.separator + arg0;
//            WebElement fileelement=webDriver.getwebelement("//*[text()='"+arg1+"']/parent::*/parent::*/input[@type='file']");
//
//            webDriver.SendKeys(fileelement,filePath);
//            System.out.println("Just After Upload");
//            Thread.sleep(1000);
//            if(webDriver.isElementPresent(xml.getlocator("CustomFormFields").replace("{paramlink}","Replace")))
//            {
//                System.out.println("In If Condition");
//                if(arg2.equalsIgnoreCase("True")) {
//                    webDriver.Clickon(webDriver.getwebelement(xml.getlocator("CustomFormFields").replace("{paramlink}", "Replace")));
//                }
//                else {
//                    webDriver.Clickon(webDriver.getwebelement(xml.getlocator("CustomFormFields").replace("{paramlink}", "Keep All")));
//
//                }
//            }
//
//            System.out.println("After Verify The Replance Link");
//            webDriver.verifyElementToBePresent("//*[text()='"+arg0+"']");
//        }

        @Then("Click on {string} Button in normal mode under upload delete module")
        public void clickOnButtonuploaddeletemodule(String arg0) throws Exception {
//        webDriver.waitForElementToBePresent("//*[@icon-name='utility:success']", 10);
            String locator = xml.getlocator("CustomButton").replace("{paramlink}",arg0);
            webDriver.safeJavaScriptClick(webDriver.getwebelement(locator));
        }


        @Then("^Upload the multiple files \"([^\"]*)\" in \"([^\"]*)\" section By \"([^\"]*)\" under upload delete module$")
        public void uploadMultipleFilesSimultaneouslyuploaddeletemodule(String filenames, String arg1, String replace) throws Exception {
            Thread.sleep(3000);

            // Split comma-separated filenames
            String[] filesArray = filenames.split(",");

            // Build full file paths and join them with \n
            StringBuilder allFiles = new StringBuilder();
            String projectDir = System.getProperty("user.dir");
            for (String file : filesArray) {
                String filePath = projectDir + File.separator + "lib" + File.separator + "XfileProData" + File.separator + file.trim();
                allFiles.append(filePath).append("\n");
            }

            // Locate the upload input element
            WebElement uploadInput=webDriver.getwebelement("//*[text()='"+arg1+"']/parent::*/parent::*/input[@type='file']");




            // Send multiple file paths at once
            uploadInput.sendKeys(allFiles.toString().trim());

            System.out.println("✅ Successfully uploaded multiple files simultaneously: " + filenames);
        }


    @Then("^Verify text \"([^\"]*)\" is present on the \"([^\"]*)\" upload delete module page$")
    public void Verify_Text_Is_present_on_page(String Locators, String PageName) throws Throwable
    {
        try {
            locators = new xmlreader("src\\test\\resources\\locators\\" + PageName + ".xml");
            webDriver.waitforElementtobeclickable(locators.getlocator("TextPlaceholder").replace("{paramlink}", Locators));
            webDriver.verifyElementToBePresent(locators.getlocator("TextPlaceholder").replace("{paramlink}", Locators));
        }
        catch (Exception e)
        {
            System.out.println("Message was not able to get captured");
        }
    }


    @And("I Select a single file")
    public void selectSingleFile() throws Exception {
        System.out.println("📂 Selecting first file from list...");
        Thread.sleep(2000);

        try {
            // Wait for files to load
            webDriver.verifyElementToBePresent(xml.getlocator("select_Firstcheckbox"));

            // Click on the first file checkbox
            WebElement firstFileCheckbox = webDriver.getwebelement(xml.getlocator("select_Firstcheckbox"));
            webDriver.Clickon(firstFileCheckbox);

            Thread.sleep(1000);
            System.out.println("✅ First file selected successfully!");

        } catch (Exception e) {
            System.err.println("❌ Error selecting file: " + e.getMessage());
            throw e;
        }
    }


    @Then("Verify Delete button should be present")
    public void verifyDeleteButtonShouldBePresent() throws Exception {
        System.out.println("\n🔍 Verifying Delete button is present...");
        Thread.sleep(1000);

        try {
            // Check if Delete button exists
            boolean deleteButtonPresent = webDriver.isElementPresent(xml.getlocator("DeleteButton"));

            Assert.assertTrue(deleteButtonPresent,
                    "❌ Delete button is NOT present after selecting file!");

            System.out.println("✅ Delete button is present");

            // Get the button element for additional verification
            WebElement deleteButton = webDriver.getwebelement(xml.getlocator("DeleteButton"));

            // Verify button is visible
            boolean isVisible = deleteButton.isDisplayed();
            Assert.assertTrue(isVisible, "❌ Delete button is not visible!");
            System.out.println("✅ Delete button is visible");

            // Verify button title
            String title = deleteButton.getAttribute("title");
            Assert.assertEquals(title, "Delete",
                    "❌ Button title should be 'Delete' but found: " + title);
            System.out.println("✅ Delete button title verified: " + title);

            // Check if button is enabled
            boolean isEnabled = deleteButton.isEnabled();
            if (isEnabled) {
                System.out.println("✅ Delete button is ENABLED (clickable)");
            } else {
                System.out.println("⚠️ Delete button is DISABLED");
            }

            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║  ✅ DELETE BUTTON VERIFICATION PASSED  ║");
            System.out.println("╚════════════════════════════════════════╝\n");

        } catch (Exception e) {
            System.err.println("❌ Error verifying Delete button: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

}






