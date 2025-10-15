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

@Log4j
public class UploadStep {
    public webHelper webDriver;
    private bddDriver DriverInstance;
    public xmlreader xml;

    public UploadStep(bddDriver contextSteps, DriverController drivercontroler) throws Exception {
        this.DriverInstance = contextSteps;
        System.out.println("triggered bdd contructoctor");
        System.out.println(this.DriverInstance);
        webDriver = new baseDriverHelper(bddDriver.getWebDriver());
        xml=new xmlreader("src\\test\\resources\\locators\\salesforce.xml");
    }

    public UploadStep(DriverController drivercontroler) {
        webDriver = new baseDriverHelper(drivercontroler.getDriver());
    }




    @Given("I navigate to the {string} module")
    public void openFromAppLaucheruploadmodule(String arg0) throws Exception {
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


    @Then("I search for account {string}")
    public void searchTheRecordInListViewunderuploadmodule(String arg0) throws InterruptedException, IOException, DocumentException {
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

    @And("Open Searched Record under upload module")
    public void openSearchedRecorduploadmodule() throws Exception {
//        webDriver.waitforElementNotToBeVisible(xml.getlocator("RecordGridLink"));
        webDriver.verifyElementToBePresent(xml.getlocator("RecordGridLink"));

        webDriver.safeJavaScriptClick(webDriver.getwebelement(xml.getlocator("RecordGridLink")));
        Thread.sleep(5000);


    }


//    @And("Go to {string} in {string} under upload module")
//    public void goToInuploadmodule(String arg0, String arg1) throws Exception {
//        String mode="normal";
//        if(arg1.equalsIgnoreCase("Console"))
//        {
//            mode="maximized";
//        }
//        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("EntityTabs").replace("{paramlink}",arg0).replace("{paramlink1}",mode)));
//
//    }

    @Given("Go to Details tab under upload module")
    public void clickondetailstabunderuploadmodule() throws Exception {
        webDriver.verifyElementToBePresent(xml.getlocator("Details_tab"));
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("Details_tab")));
        Thread.sleep(2000);

    }



    @Then("Upload the {string} file in {string} section By {string} under upload module")
    public void uploadTheFileInSectionuploadmodule(String arg0, String arg1,String arg2) throws Exception {
        String projectDir = System.getProperty("user.dir");
        String filePath = projectDir + File.separator + "lib"
                + File.separator + "XfileProData"
                + File.separator + arg0;
        WebElement fileelement=webDriver.getwebelement("//*[text()='"+arg1+"']/parent::*/parent::*/input[@type='file']");

        webDriver.SendKeys(fileelement,filePath);
        System.out.println("Just After Upload");
        Thread.sleep(1000);
        if(webDriver.isElementPresent(xml.getlocator("CustomFormFields").replace("{paramlink}","Replace")))
        {
            System.out.println("In If Condition");
            if(arg2.equalsIgnoreCase("True")) {
                webDriver.Clickon(webDriver.getwebelement(xml.getlocator("CustomFormFields").replace("{paramlink}", "Replace")));
            }
            else {
                webDriver.Clickon(webDriver.getwebelement(xml.getlocator("CustomFormFields").replace("{paramlink}", "Keep All")));

            }
        }

        System.out.println("After Verify The Replance Link");
        webDriver.verifyElementToBePresent("//*[text()='"+arg0+"']");
    }

    @Then("Click on {string} Button in normal mode under upload module")
    public void clickOnButtonuploadmodule(String arg0) throws Exception {
//        webDriver.waitForElementToBePresent("//*[@icon-name='utility:success']", 10);
        String locator = xml.getlocator("CustomButton").replace("{paramlink}",arg0);
        webDriver.safeJavaScriptClick(webDriver.getwebelement(locator));
    }

    //    @Then("I verify Cloud Storage hover message is displayed")
//    public void verifyCloudStorageHoverMessage() throws Exception {
//        // Step 1: Locate the Cloud Storage button
//        WebElement cloudStorageBtn = webDriver.getwebelement(xml.getlocator("CloudStorageButton"));
//
//        // Step 2: Hover using Actions (replace with your actual driver instance)
//        Actions actions = new Actions(webDriver.driver);   // <<-- adjust here
//        actions.moveToElement(cloudStorageBtn).perform();
//
//        // Step 3: Get tooltip text from 'title'
//        String tooltipText = cloudStorageBtn.getAttribute("title");
//
//        // Step 4: Assert hover tooltip text
//        Assert.assertEquals(tooltipText, "Cloud Storage",
//                "Tooltip message did not match expected value!");
//
//        System.out.println("✅ Hover message displayed successfully: " + tooltipText);
//    }



//
//    @When("Verify the PDF data")
//    public void verifyTehPDFData() throws IOException {
//        File pdfFile = new File("C:\\Users\\ashwanis\\OneDrive - 360 Logica Software Testing Company\\Desktop\\11-B672N2-328-26544580-01072021.pdf");
//        PDDocument document = Loader.loadPDF(pdfFile);
//        try {
//            int totalPages = document.getNumberOfPages();
//
//            ObjectExtractor extractor = new ObjectExtractor(document);
//            SpreadsheetExtractionAlgorithm sea = new SpreadsheetExtractionAlgorithm();
//            PDFTextStripper textStripper = new PDFTextStripper();
//
//            for (int pageNum = 1; pageNum <= totalPages; pageNum++) {
//                System.out.println("\n================== PAGE " + pageNum + " ==================\n");
//
//                // Try extracting with Tabula
//                Page tabulaPage = extractor.extract(pageNum);
//                List<Table> tables = sea.extract(tabulaPage);
//
//                if (tables != null && !tables.isEmpty()) {
//                    System.out.println("[📊 Table Detected - Extracted Table Data]");
//                    for (Table table : tables) {
//                        for (List<RectangularTextContainer> row : table.getRows()) {
//                            for (RectangularTextContainer cell : row) {
//                                System.out.print(cell.getText().trim() + " | ");
//                            }
//                            System.out.println();
//                        }
//                    }
//                } else {
//                    // Fallback: Extract raw text with spacing preserved
//                    System.out.println("[📄 No Table Detected - Extracting Full Text]");
//                    textStripper.setStartPage(pageNum);
//                    textStripper.setEndPage(pageNum);
//                    textStripper.setSortByPosition(true); // maintain formatting
//                    String rawText = textStripper.getText(document);
//                    System.out.println(rawText);
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    @Then("^Upload the multiple files \"([^\"]*)\" in \"([^\"]*)\" section By \"([^\"]*)\" under upload module$")
    public void uploadMultipleFilesSimultaneously(String filenames, String arg1, String replace) throws Exception {
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



}

