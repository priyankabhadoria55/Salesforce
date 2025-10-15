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
import utils.DriverController;
import utils.Excel_Data_Repo;
import utils.xmlreader;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Log4j
public class DemoStep {
    public webHelper webDriver;
    private bddDriver DriverInstance;
    public xmlreader xml;

    public DemoStep(bddDriver contextSteps, DriverController drivercontroler) throws Exception {
        this.DriverInstance = contextSteps;
        System.out.println("triggered bdd contructoctor");
        System.out.println(this.DriverInstance);
        webDriver = new baseDriverHelper(bddDriver.getWebDriver());
        xml=new xmlreader("src\\test\\resources\\locators\\salesforce.xml");
    }

    public DemoStep(DriverController drivercontroler) {
        webDriver = new baseDriverHelper(drivercontroler.getDriver());
    }

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

    @Given("Open {string} from App Launcher")
    public void openFromAppLaucher(String arg0) throws Exception {
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

    @Then("Perform {string} Operation")
    public void triggerOperation(String arg0) throws Exception {


//            webDriver.Clickon(webDriver.getwebelement(xml.getlocator("EntityOperation").replace("{paramlink}", arg0)));
        webDriver.safeJavaScriptClick(webDriver.getwebelement(xml.getlocator("EntityOperation").replace("{paramlink}", arg0)));


    }


    @And("Create a {string} Object with below details")
    public void fillTheFormWithBelowDetails(String arg0, DataTable dt) throws Exception {
        List<Map<String, String>> dataRows = dt.asMaps(String.class, String.class);
        ///Read from External source

        webDriver.verifyElementToBePresent(xml.getlocator("EntityNewModelPopup").replace("{paramlink}",arg0));
        if (!dataRows.isEmpty()) {
            Map<String, String> details = dataRows.get(0);

            System.out.println("Creating object of type: " + arg0);
            for (Map.Entry<String, String> entry : details.entrySet()) {

                System.out.println("Setting " + entry.getKey() + " to " + entry.getValue());
                LocalTime currentTime = LocalTime.now();
                String Value_to_Enter="N/A";
                if(entry.getValue().contains("Random"))
                {
                    Value_to_Enter=entry.getValue().replace("Random",currentTime.toString());
                }
                else if(entry.getValue().contains("Current"))
                {
                    int daysToAdd = 0;
                    if (entry.getValue().contains("+")) {
                        // Extract the number after '+'
                        String[] parts = entry.getValue().split("\\+");
                        daysToAdd = Integer.parseInt(parts[1].trim());
                    } else if (entry.getValue().contains("-")) {
                        // Extract the number after '-'
                        String[] parts = entry.getValue().split("-");
                        daysToAdd = -Integer.parseInt(parts[1].trim());
                    }

                    // Generate date in DD/MM/YYYY format
                    LocalDate calculatedDate = LocalDate.now().plusDays(daysToAdd);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    Value_to_Enter = calculatedDate.format(formatter);
                }
                else{
                    Value_to_Enter=entry.getValue();
                }
                DriverInstance.setThreadLocalMapValue(entry.getKey(),Value_to_Enter);
                String fieldname = webDriver.getwebelement(xml.getlocator("FormLabel").replace("{paramlink}",entry.getKey())).getAttribute("for");
                WebElement element = webDriver.getwebelement(xml.getlocator("FormFields").replace("{paramlink}",fieldname));
                webDriver.ScrollIntoView(element);
                String Tag = element.getTagName();
                String role = element.getAttribute("role");
                String type = element.getAttribute("type");
                String classvalue = element.getAttribute("class");
                if (Tag.equalsIgnoreCase("input") && type.equalsIgnoreCase("text")&& role==null) {
                    webDriver.SendKeys(element, DriverInstance.getThreadLocalMapValue(entry.getKey()));
                }
                else if (Tag.equalsIgnoreCase("input")  && type.equalsIgnoreCase("search")&& role==null) {
                    webDriver.SendKeys(element, DriverInstance.getThreadLocalMapValue(entry.getKey()));

                }
                else if (Tag.equalsIgnoreCase("input")  && type.equalsIgnoreCase("search")&& role==null) {
                    webDriver.Clickon(element);
                }
                else if (Tag.equalsIgnoreCase("input") && !type.equalsIgnoreCase("text")) {
                    webDriver.Clickon(element);
                }

                else if (Tag.equalsIgnoreCase("input") && type.equalsIgnoreCase("text") && role.equalsIgnoreCase("combobox")) {
                    webDriver.SendKeys(element, Value_to_Enter);
                    Thread.sleep(2500);

                    webDriver.Clickon(webDriver.getwebelement("//*[contains(@data-item-id,'"+fieldname+"-') and @data-value!='actionAdvancedSearch']"));

                }
                else if (Tag.equalsIgnoreCase("button") && role.equalsIgnoreCase("combobox")) {
                    webDriver.Clickon(element);

                    webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ComboBoxOption").replace("{paramlink1}",fieldname).replace("{paramlink2}",DriverInstance.getThreadLocalMapValue(entry.getKey()))));
                }
                else if (Tag.equalsIgnoreCase("textarea")) {
                    webDriver.SendKeys(element, DriverInstance.getThreadLocalMapValue(entry.getKey()));
                }
                else if (Tag.equalsIgnoreCase("select")) {
                    webDriver.selectOptionByValue(element, entry.getValue());
                }
            }

        } else {
            System.out.println("No data provided in the table.");
        }

    }

    @And("Fill a custom form with below details")
    public void CustomeFormFill(DataTable dt) throws Exception {
        List<Map<String, String>> dataRows = dt.asMaps(String.class, String.class);
        ///Read from External source
//        webDriver.verifyElementToBePresent(xml.getlocator("EntityNewModelPopup").replace("{paramlink}",arg0));

        if (!dataRows.isEmpty()) {
            Map<String, String> details = dataRows.get(0);


            for (Map.Entry<String, String> entry : details.entrySet()) {
                System.out.println("Setting " + entry.getKey() + " to " + entry.getValue());

                LocalTime currentTime = LocalTime.now();
                String Value_to_Enter="N/A";
                if(entry.getValue().contains("Random"))
                {
                    Value_to_Enter=entry.getValue().replace("Random",currentTime.toString());
                }
                else{
                    Value_to_Enter=entry.getValue();
                }
                DriverInstance.setThreadLocalMapValue(entry.getKey(),Value_to_Enter);
                String fieldname = webDriver.getwebelement(xml.getlocator("CustomFieldLebel").replace("{paramlink}",entry.getKey())).getAttribute("id");
                WebElement element = webDriver.getwebelement(xml.getlocator("CustomFormFields").replace("{paramlink}",fieldname));
                webDriver.ScrollIntoView(element);
                String Tag = element.getTagName();
                String role = element.getAttribute("role");
                String type = element.getAttribute("type");
                String classvalue = element.getAttribute("class");
                if (Tag.equalsIgnoreCase("input") && type.equalsIgnoreCase("text")) {
                    webDriver.SendKeys(element, DriverInstance.getThreadLocalMapValue(entry.getKey()));
                } else if (Tag.equalsIgnoreCase("input") && !type.equalsIgnoreCase("text")) {
                    webDriver.Clickon(element);
                } else if (Tag.equalsIgnoreCase("button") && role.equalsIgnoreCase("combobox")) {
                    webDriver.Clickon(element);

                    webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ComboBoxOption").replace("{paramlink1}",fieldname).replace("{paramlink2}",entry.getValue())));
                } else if (Tag.equalsIgnoreCase("textarea")) {
                    webDriver.SendKeys(element, DriverInstance.getThreadLocalMapValue(entry.getKey()));
                }
                else if (Tag.equalsIgnoreCase("select")) {
                    webDriver.selectOptionByText(element,DriverInstance.getThreadLocalMapValue(entry.getKey()));
//                    webDriver.SendKeys(element, DriverInstance.getThreadLocalMapValue(entry.getKey()));
                }
            }

        } else {
            System.out.println("No data provided in the table.");
        }

    }




    @Then("Go to {string} Object")
    public void goToModule(String arg0) throws Exception {

        webDriver.safeJavaScriptClick(webDriver.getwebelement(xml.getlocator("Entity").replace("{paramlink}",arg0)));

    }


    @Then("{string} Record")
    public void record(String arg0) throws Exception {
        String locator = xml.getlocator("EntityOperationButton").replace("{paramlink}",arg0);
        webDriver.Clickon(webDriver.getwebelement(locator));
    }


    @Then("Search the Record in List view with {string}")
    public void searchTheRecordInListView(String arg0) throws InterruptedException, IOException, DocumentException {
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

    @And("Open Searched Record")
    public void openSearchedRecord() throws Exception {
//        webDriver.waitforElementNotToBeVisible(xml.getlocator("RecordGridLink"));
        webDriver.verifyElementToBePresent(xml.getlocator("RecordGridLink"));

        webDriver.safeJavaScriptClick(webDriver.getwebelement(xml.getlocator("RecordGridLink")));

    }

    @Then("Verify the Record {string} in {string}")
    public void verifyTheRecordDetails(String arg0,String arg1, DataTable dt) throws Exception {
        String mode="normal";
        if(arg1.equalsIgnoreCase("Console"))
        {
            mode="maximized";
        }
        try{
            webDriver.verifyElementToBePresent(xml.getlocator("EntityDetailHeader").replace("{paramlink}",arg0).replace("{paramlink1}",mode));
        }
        catch (Exception e)
        {
            System.out.println("No Tab Available");
        }
//        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("EntityTabs").replace("{paramlink}",arg0).replace("{paramlink1}",mode)));
        List<Map<String, String>> dataRows = dt.asMaps(String.class, String.class);
        if (!dataRows.isEmpty()) {
            Map<String, String> details = dataRows.get(0);

            System.out.println("Creating object of type: " + arg0);
            for (Map.Entry<String, String> entry : details.entrySet()) {

                System.out.println("Setting " + entry.getKey() + " to " + entry.getValue());

                String Value_to_Enter="N/A";
                if(entry.getValue().contains("Random"))
                {
                    Value_to_Enter=DriverInstance.getThreadLocalMapValue(entry.getKey());
                }
                else{
                    Value_to_Enter=entry.getValue();
                }
//                WebElement element = webDriver.getwebelement("//*[@field-label='" + entry.getKey() + "']//dd");
//                webDriver.VerifyText(element, entry.getValue());
                webDriver.ScrollIntoView(webDriver.getwebelement(xml.getlocator("EntityAttributeDetail").replace("{paramlink}",entry.getKey()).replace("{paramlink1}",mode)));
                webDriver.VerifyText(webDriver.getwebelement(xml.getlocator("EntityAttributeDetail").replace("{paramlink}",entry.getKey()).replace("{paramlink1}",mode)),Value_to_Enter);
            }
        } else {
            System.out.println("No data provided in the table.");
        }


    }



    @Then("Verify the Record {string} in {string} for Access Control")
    public void verifyTheRecordDetailsAccessControl(String arg0,String arg1, DataTable dt) throws Exception {
        String mode="normal";
        if(arg1.equalsIgnoreCase("Console"))
        {
            mode="maximized";
        }
        try{
            webDriver.verifyElementToBePresent(xml.getlocator("EntityDetailHeader").replace("{paramlink}",arg0).replace("{paramlink1}",mode));
        }
        catch (Exception e)
        {
            System.out.println("No Tab Available");
        }
//        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("EntityTabs").replace("{paramlink}",arg0).replace("{paramlink1}",mode)));
        List<Map<String, String>> dataRows = dt.asMaps(String.class, String.class);
        if (!dataRows.isEmpty()) {
            Map<String, String> details = dataRows.get(0);

            System.out.println("Creating object of type: " + arg0);
            for (Map.Entry<String, String> entry : details.entrySet()) {
//                webDriver.ScrollIntoView(webDriver.getwebelement(xml.getlocator("EntityAttributeDetail").replace("{paramlink}",entry.getKey()).replace("{paramlink1}",mode)));
                if(entry.getValue().equalsIgnoreCase("Editable")) {
                    webDriver.verifyElementToBePresent(xml.getlocator("EditIcone").replace("{paramlink}", entry.getKey()));

                }
                else {
                    webDriver.verifyElementNotToBePresent(xml.getlocator("EditIcone").replace("{paramlink}", entry.getKey()));

                }
            }
        } else {
            System.out.println("No data provided in the table.");
        }


    }

    @Then("Change Status to {string}")
    public void covertStatusTo(String arg0) throws Exception {
        webDriver.safeJavaScriptClick(webDriver.getwebelement(xml.getlocator("EntityStatus").replace("{paramlink}",arg0)));
        webDriver.safeJavaScriptClick(webDriver.getwebelement(xml.getlocator("StatusChangeButton").replace("{paramlink}",arg0)));
    }


    @Then("Covert Entity to {string}")
    public void covertEntityTo(String arg0) throws Exception {
        webDriver.safeJavaScriptClick(webDriver.getwebelement(xml.getlocator("EntityStatus").replace("{paramlink}",arg0)));
        webDriver.safeJavaScriptClick(webDriver.getwebelement(xml.getlocator("EntityConvert").replace("{paramlink}",arg0)));

    }

    @And("Update the {string} with {string} Account Details on {string}")
    public void updateTheWithAccountDetailsOn(String sub, String type1, String form, DataTable dt) throws Exception {
        List<Map<String, String>> dataRows = dt.asMaps(String.class, String.class);
//        webDriver.verifyElementToBePresent(xml.getlocator("ModelTitle").replace("{paramlink}",form));
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("AttributeExpander").replace("{paramlink}",sub)));

        switch (type1) {
            case "New": {
                webDriver.safeJavaScriptClick(webDriver.getwebelement(xml.getlocator("AttributeTypeSelector").replace("{paramlink3}","Create").replace("{paramlink2}",sub).replace("{paramlink1}",type1)));

                if (!dataRows.isEmpty()) {
                    Map<String, String> details = dataRows.get(0);


                    for (Map.Entry<String, String> entry : details.entrySet()) {
                        LocalTime currentTime = LocalTime.now();
                        String Value_to_Enter="N/A";
                        if(entry.getValue().contains("Random"))
                        {
                            Value_to_Enter=entry.getValue().replace("Random",currentTime.toString());
                        }
                        else{
                            Value_to_Enter=entry.getValue();
                        }
                        DriverInstance.setThreadLocalMapValue(entry.getKey(),Value_to_Enter);
                        System.out.println("Setting " + entry.getKey() + " to " + entry.getValue());
                        String fieldname = webDriver.getwebelement(xml.getlocator("DataLabel").replace("{paramlink}",entry.getKey())).getAttribute("for");
                        WebElement element = webDriver.getwebelement(xml.getlocator("FormFields").replace("{paramlink}",fieldname));
                        webDriver.ScrollIntoView(element);
                        String Tag = element.getTagName();
                        String role = element.getAttribute("role");
                        String type = element.getAttribute("type");
                        String classvalue = element.getAttribute("class");
                        if (role == null) {
                            role = "none";
                        }
                        if (classvalue == null) {
                            classvalue = "none";
                        }
                        if (Tag.equalsIgnoreCase("input") && type.equalsIgnoreCase("text") && !role.equalsIgnoreCase("combobox")) {
                            webDriver.SendKeys(element, Value_to_Enter);
                        } else if (Tag.equalsIgnoreCase("input") && !type.equalsIgnoreCase("text") && !role.equalsIgnoreCase("combobox")) {
                            webDriver.Clickon(element);
                        } else if (Tag.equalsIgnoreCase("input") && type.equalsIgnoreCase("text") && role.equalsIgnoreCase("combobox")) {
                            webDriver.SendKeys(element, Value_to_Enter);
                            webDriver.Clickon(webDriver.getwebelement("//ul[@class='lookup__list  visible']//a[@role='option']"));

                        } else if (Tag.equalsIgnoreCase("button") && role.equalsIgnoreCase("combobox")) {
                            webDriver.Clickon(element);

                            webDriver.Clickon(webDriver.getwebelement("//*[contains(@data-item-id,'" + fieldname + "') and @data-value='" + entry.getValue() + "']"));
                        } else if (Tag.equalsIgnoreCase("textarea")) {
                            webDriver.SendKeys(element, Value_to_Enter);
                        }
                    }
                } else {
                    System.out.println("No data provided in the table.");
                }
                break;
            }
            case "Existing": {
                webDriver.safeJavaScriptClick(webDriver.getwebelement(xml.getlocator("AttributeTypeSelector").replace("{paramlink3}","Choose").replace("{paramlink2}",sub).replace("{paramlink1}",type1)));

                if (!dataRows.isEmpty()) {
                    Map<String, String> details = dataRows.get(0);


                    for (Map.Entry<String, String> entry : details.entrySet()) {
                        LocalTime currentTime = LocalTime.now();
                        String Value_to_Enter="N/A";
                        if(entry.getValue().contains("Random"))
                        {
                            Value_to_Enter=entry.getValue().replace("Random",currentTime.toString());
                        }
                        else{
                            Value_to_Enter=entry.getValue();
                        }
                        DriverInstance.setThreadLocalMapValue(entry.getKey(),Value_to_Enter);
                        System.out.println("Setting " + entry.getKey() + " to " + entry.getValue());
                        String fieldname = webDriver.getwebelement(xml.getlocator("DataLabel").replace("{paramlink}",entry.getKey())).getAttribute("for");
                        WebElement element = webDriver.getwebelement(xml.getlocator("FormFields").replace("{paramlink}",fieldname));
                        webDriver.ScrollIntoView(element);
                        String Tag = element.getTagName();
                        String role = element.getAttribute("role");
                        String type = element.getAttribute("type");
                        String classvalue = element.getAttribute("class");
                        if (Tag.equalsIgnoreCase("input") && type.equalsIgnoreCase("text") && !role.equalsIgnoreCase("combobox")) {
                            webDriver.SendKeys(element, Value_to_Enter);
                        } else if (Tag.equalsIgnoreCase("input") && !type.equalsIgnoreCase("text") && !role.equalsIgnoreCase("combobox")) {
                            webDriver.Clickon(element);
                        } else if (Tag.equalsIgnoreCase("input") && type.equalsIgnoreCase("text") && role.equalsIgnoreCase("combobox")) {
                            webDriver.SendKeys(element, Value_to_Enter);
                            webDriver.Clickon(webDriver.getwebelement("//ul[@class='lookup__list  visible']//a[@role='option']"));

                        } else if (Tag.equalsIgnoreCase("button") && role.equalsIgnoreCase("combobox")) {
                            webDriver.Clickon(element);

                            webDriver.Clickon(webDriver.getwebelement("//*[contains(@data-item-id,'" + fieldname + "') and @data-value='" + entry.getValue() + "']"));
                        } else if (Tag.equalsIgnoreCase("textarea")) {
                            webDriver.SendKeys(element, Value_to_Enter);
                        }
                    }
                } else {
                    System.out.println("No data provided in the table.");
                }
                break;
            }
        }
//        Thread.sleep(5000);

        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("AttributeExpander").replace("{paramlink}",sub)));

    }


    @And("Update Details on {string}")
    public void updateDetails(String form, DataTable dt) throws Exception {
        List<Map<String, String>> dataRows = dt.asMaps(String.class, String.class);
        webDriver.verifyElementToBePresent(xml.getlocator("ModelTitle").replace("{paramlink}",form));

//                webDriver.safeJavaScriptClick(webDriver.getwebelement(xml.getlocator("AttributeTypeSelector").replace("{paramlink3}","Choose").replace("{paramlink2}",sub).replace("{paramlink1}",type1)));

        if (!dataRows.isEmpty()) {
            Map<String, String> details = dataRows.get(0);


            for (Map.Entry<String, String> entry : details.entrySet()) {
                LocalTime currentTime = LocalTime.now();
                String Value_to_Enter="N/A";
                if(entry.getValue().contains("Random"))
                {
                    Value_to_Enter=entry.getValue().replace("Random",currentTime.toString());
                }
                else{
                    Value_to_Enter=entry.getValue();
                }
                DriverInstance.setThreadLocalMapValue(entry.getKey(),Value_to_Enter);
                System.out.println("Setting " + entry.getKey() + " to " + entry.getValue());
                String fieldname = webDriver.getwebelement(xml.getlocator("DataLabel").replace("{paramlink}",entry.getKey())).getAttribute("for");
                WebElement element = webDriver.getwebelement(xml.getlocator("FormFields").replace("{paramlink}",fieldname));
                webDriver.ScrollIntoView(element);
                String Tag = element.getTagName();
                String role = element.getAttribute("role");
                String type = element.getAttribute("type");
                String classvalue = element.getAttribute("class");
                if (Tag.equalsIgnoreCase("input") && type.equalsIgnoreCase("text") && !role.equalsIgnoreCase("combobox")) {
                    webDriver.SendKeys(element, Value_to_Enter);
                } else if (Tag.equalsIgnoreCase("input") && !type.equalsIgnoreCase("text") && !role.equalsIgnoreCase("combobox")) {
                    webDriver.Clickon(element);
                } else if (Tag.equalsIgnoreCase("input") && type.equalsIgnoreCase("text") && role.equalsIgnoreCase("combobox")) {
                    String ID=webDriver.Getattribute(element,"id");
                    webDriver.SendKeys(element, Value_to_Enter);

//                            webDriver.Clickon(webDriver.getwebelement("//ul[@class='lookup__list  visible']//a[@role='option']"));
                    webDriver.Clickon(webDriver.getwebelement("//*[@role='option' and contains(@data-item-id,'"+ID+"-1')]"));

                } else if (Tag.equalsIgnoreCase("button") && role.equalsIgnoreCase("combobox")) {
                    webDriver.Clickon(element);

                    webDriver.Clickon(webDriver.getwebelement("//*[contains(@data-item-id,'" + fieldname + "') and @data-value='" + entry.getValue() + "']"));
                } else if (Tag.equalsIgnoreCase("textarea")) {
                    webDriver.SendKeys(element, Value_to_Enter);
                }
            }
        }



    }

    @Then("Click on {string} Record in {string}")
    public void clickOnRecord(String arg0,String arg1) throws Exception {
        String mode="modal-footer slds-modal__footer";
        if(arg1.equalsIgnoreCase("Console"))
        {
            mode="footer slds-text-align_center";
        }
        String locator = xml.getlocator("RecordOperation").replace("{paramlink}",arg0).replace("modal-footer slds-modal__footer",mode);
        webDriver.Clickon(webDriver.getwebelement(locator));
    }


    @And("Verify Current Status of Entity is {string}")
    public void verifyCurrentStatusOfEntityIs(String arg0) throws Exception {
        String isTrue=webDriver.Getattribute(webDriver.getwebelement(xml.getlocator("EntityStatus").replace("{paramlink}",arg0)),"aria-current");
        Assert.assertTrue(isTrue.equalsIgnoreCase("true"));
    }

    @Then("Click on {string} Button in Console")
    public void clickOnButton1(String arg0) throws Exception {
        String locator = xml.getlocator("CustomButton1").replace("{paramlink}",arg0);
        webDriver.Clickon(webDriver.getwebelement(locator));
    }
    @Then("Click on {string} Button in normal mode")
    public void clickOnButton(String arg0) throws Exception {
//        webDriver.waitForElementToBePresent("//*[@icon-name='utility:success']", 10);
        String locator = xml.getlocator("CustomButton").replace("{paramlink}",arg0);
        webDriver.safeJavaScriptClick(webDriver.getwebelement(locator));
    }

    @And("Verify the Task {string} is created")
    public void verifyTheTaskIsCreated(String arg0) throws DocumentException, InterruptedException {
        String isTrue=webDriver.Getattribute(webDriver.getwebelement(xml.getlocator("EntityStatus").replace("{paramlink}",arg0)),"aria-current");
        Assert.assertTrue(isTrue.equalsIgnoreCase("true"));
    }

    @Then("Upload the {string} document")
    public void uploadTheDocument(String arg0) throws DocumentException, InterruptedException, IOException {

        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("UploadFilefield")),arg0);
    }

    @And("Upload the {string} document into {string} section")
    public void uploadTheDocumentIntoSection(String arg0, String arg1) throws Exception {
        String str = System.getProperty("user.dir")+"\\lib\\"+arg0;

        String id=webDriver.Getattribute(webDriver.getwebelement("//*[text()='"+arg1+"']"),"id");
        webDriver.SendKeys(webDriver.getwebelement("//*[contains(@aria-labelledby,'"+id+"')]"),str);
        Thread.sleep(6000);
        webDriver.Clickon(webDriver.getwebelement("//*[text()='Done']/parent::button"));
    }

    @And("Navigate to the {string} Section on the {string} Tab")
    public void navigateToTheSectionOnTheTab(String arg0, String arg1) throws Exception {
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("EntityTabs").replace("{paramlink}",arg1)));
//        Thread.sleep(4000);
        String[] section=arg0.split(">");
        for (String item : section) {
            webDriver.ScrollIntoView(webDriver.getwebelement(xml.getlocator("saleForceEntityGroups").replace("{paramlink}",item)));
            webDriver.Clickon(webDriver.getwebelement(xml.getlocator("saleForceEntityGroups").replace("{paramlink}",item)));

            String expanced=webDriver.Getattribute(webDriver.getwebelement(xml.getlocator("saleForceEntityGroups").replace("{paramlink}",item)),"aria-expanded");
            System.out.println("Status of "+item+" is "+expanced);
            if(!expanced.equalsIgnoreCase("true"))
            {
//               webDriver.ScrollIntoView(webDriver.getwebelement(xml.getlocator("saleForceEntityGroups").replace("{paramlink}",item)));
                webDriver.safeJavaScriptClick(webDriver.getwebelement(xml.getlocator("saleForceEntityGroups").replace("{paramlink}",item)));

            }
        }
    }

    @And("Update the Below Fields on StorageSetupStep Screen")
    public void updateTheBelowFieldsOnSaleforceScreen(DataTable dt) throws Exception {
        List<Map<String, String>> dataRows = dt.asMaps(String.class, String.class);
        ///Read from External source

        if (!dataRows.isEmpty()) {
            Map<String, String> details = dataRows.get(0);


            for (Map.Entry<String, String> entry : details.entrySet()) {

                System.out.println("Setting " + entry.getKey() + " to " + entry.getValue());
                LocalTime currentTime = LocalTime.now();
                String Value_to_Enter="N/A";
                if(entry.getValue().contains("Random"))
                {
                    Value_to_Enter=entry.getValue().replace("Random",currentTime.toString());
                }
                else{
                    Value_to_Enter=entry.getValue();
                }
                DriverInstance.setThreadLocalMapValue(entry.getKey(),Value_to_Enter);

//                webDriver.Clickon(webDriver.getwebelement(xml.getlocator("FormLabel").replace("{paramlink}",entry.getKey())));
                webDriver.safeJavaScriptClick(webDriver.getwebelement(xml.getlocator("EditIcone").replace("{paramlink}",entry.getKey())));
                String fieldname = webDriver.getwebelement(xml.getlocator("FormLabel").replace("{paramlink}",entry.getKey())).getAttribute("for");
//                webDriver.safeJavaScriptClick(webDriver.getwebelement(xml.getlocator("EditIcone").replace("{paramlink}",entry.getKey())));
                WebElement element = webDriver.getwebelement(xml.getlocator("FormFields").replace("{paramlink}",fieldname));
//                webDriver.ScrollIntoView(element);
                String Tag = element.getTagName();
                String role = element.getAttribute("role");
                String type = element.getAttribute("type");
                String classvalue = element.getAttribute("class");
//                String isDisabled=webDriver.Getattribute(element,"disabled");
//               if (isDisabled!=null)
//               {
//                   }
                if (Tag.equalsIgnoreCase("input") && type.equalsIgnoreCase("text")) {
                    webDriver.SendKeys(element, DriverInstance.getThreadLocalMapValue(entry.getKey()));
                } else if (Tag.equalsIgnoreCase("input") && !type.equalsIgnoreCase("text")) {
                    webDriver.Clickon(element);
                } else if (Tag.equalsIgnoreCase("button") && role.equalsIgnoreCase("combobox")) {
                    webDriver.Clickon(element);

                    webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ComboBoxOption").replace("{paramlink1}",fieldname).replace("{paramlink2}",DriverInstance.getThreadLocalMapValue(entry.getKey()))));
                } else if (Tag.equalsIgnoreCase("textarea")) {
                    webDriver.SendKeys(element, DriverInstance.getThreadLocalMapValue(entry.getKey()));
                }
            }

        } else {
            System.out.println("No data provided in the table.");
        }

    }

    @And("Store the Below values")
    public void storeTheBelowValues(DataTable dt) throws DocumentException, InterruptedException {

        List<Map<String, String>> dataRows = dt.asMaps(String.class, String.class);
        if (!dataRows.isEmpty()) {
            Map<String, String> details = dataRows.get(0);

//                System.out.println("Creating object of type: " + arg0);
            for (Map.Entry<String, String> entry : details.entrySet()) {

                System.out.println("Setting " + entry.getKey() + " to " + entry.getValue());

                String Value_to_Enter = "N/A";
                if (entry.getValue().contains("Random")) {
                    Value_to_Enter = DriverInstance.getThreadLocalMapValue(entry.getKey());
                } else {
                    Value_to_Enter = entry.getValue();
                }
//                WebElement element = webDriver.getwebelement("//*[@field-label='" + entry.getKey() + "']//dd");
//                webDriver.VerifyText(element, entry.getValue());
                webDriver.ScrollIntoView(webDriver.getwebelement(xml.getlocator("CustomeDetailsfields").replace("{paramlink}", entry.getKey())));
//                webDriver.VerifyText(webDriver.getwebelement(xml.getlocator("EntityAttributeDetail").replace("{paramlink}", entry.getKey())), Value_to_Enter);
                String Value=webDriver.getwebelement(xml.getlocator("CustomeDetailsfields").replace("{paramlink}", entry.getKey())).getText();
                String filePath = "src\\test\\resources\\dataSource\\LiveU_Data.xlsx";
                String sheetName = "Customer_Data";
                // Update cell data
                Excel_Data_Repo.updateData(filePath, sheetName, Integer.parseInt(DriverInstance.getRecord_id()), "Link inventory name in central", Value);
                System.out.println("Cell updated successfully.");

                System.out.println("Value for field "+entry.getKey()+" is "+webDriver.getwebelement(xml.getlocator("CustomeDetailsfields").replace("{paramlink}", entry.getKey())).getText()+ "$$$$");

            }
        } else {
            System.out.println("No data provided in the table.");
        }


    }


    @Then("Go to {string} Object in Console options")
    public void goToObjectInConsoleOptions(String arg0) throws Exception {
        //a[string()='Leads']
        webDriver.safeJavaScriptClick(webDriver.getwebelement("//button[string()='Show Navigation Menu']"));
        Thread.sleep(1000);
        webDriver.safeJavaScriptClick(webDriver.getwebelement(xml.getlocator("Entity_inConsole").replace("{paramlink}",arg0)));

    }

    @And("Select {string} from the Result Grid")
    public void selectFromTheResultGrid(String arg0) throws Exception {
//        webDriver.safeJavaScriptClick(webDriver.getwebelement("//button[string()='Show Navigation Menu']"));
        webDriver.safeJavaScriptClick(webDriver.getwebelement(xml.getlocator("CellDataRowSelection").replace("{paramlink}",arg0)));

    }

    @And("Update Below Dates")
    public void updateDateAs(DataTable dt) throws Exception {
        List<Map<String, String>> dataRows = dt.asMaps(String.class, String.class);
        ///Read from External source
//        webDriver.verifyElementToBePresent(xml.getlocator("EntityNewModelPopup").replace("{paramlink}",arg0));

        if (!dataRows.isEmpty()) {
            Map<String, String> details = dataRows.get(0);


            for (Map.Entry<String, String> entry : details.entrySet()) {
                System.out.println("Setting " + entry.getKey() + " to " + entry.getValue());

                LocalTime currentTime = LocalTime.now();
                String Value_to_Enter="N/A";
                if(entry.getValue().contains("Random"))
                {
                    Value_to_Enter=entry.getValue().replace("Random",currentTime.toString());
                }
                else{
                    Value_to_Enter=entry.getValue();
                }
                DriverInstance.setThreadLocalMapValue(entry.getKey(),Value_to_Enter);
                String fieldname = webDriver.getwebelement(xml.getlocator("CustomFieldLebel").replace("{paramlink}",entry.getKey())).getAttribute("id");
                System.out.println(" Date field ID"+fieldname);
                System.out.println("Actual Element locator"+xml.getlocator("CustomFormFields").replace("{paramlink}",fieldname));
                WebElement element = webDriver.getwebelement(xml.getlocator("CustomFormFields").replace("{paramlink}",fieldname));
                webDriver.ScrollIntoView(element);
                String Tag = element.getTagName();
                String role = element.getAttribute("role");
                String type = element.getAttribute("type");
                String classvalue = element.getAttribute("class");
                if (Tag.equalsIgnoreCase("input") && type.equalsIgnoreCase("text")) {
                    webDriver.Clickon(element);
                    webDriver.Clickon(webDriver.getwebelement("//*[@data-value='"+Value_to_Enter+"']/span[contains(@class, 'slds-day')]"));
//
                } else if (Tag.equalsIgnoreCase("input") && !type.equalsIgnoreCase("text")) {
                    webDriver.Clickon(element);
                } else if (Tag.equalsIgnoreCase("button") && role.equalsIgnoreCase("combobox")) {
                    webDriver.Clickon(element);

                    webDriver.Clickon(webDriver.getwebelement(xml.getlocator("ComboBoxOption").replace("{paramlink1}",fieldname).replace("{paramlink2}",entry.getValue())));
                } else if (Tag.equalsIgnoreCase("textarea")) {
                    webDriver.SendKeys(element, DriverInstance.getThreadLocalMapValue(entry.getKey()));
                }
            }

        } else {
            System.out.println("No data provided in the table.");
        }

    }

    @And("Select {string} Checkbox")
    public void selectCheckbox(String arg0) throws Exception {

        WebElement checkbox=webDriver.getwebelement( "//c-wizard-step[@is-active='true']//label[string()='"+arg0+"']/preceding-sibling::input/parent::*/parent::div");

        webDriver.Clickon(checkbox);
    }

    @When("Select Below Checkbox")
    public void selectBelowCheckbox(DataTable dt) throws Exception {
        List<String> checkboxLabels = dt.asList();

        for (String label : checkboxLabels) {
            WebElement Search=webDriver.getwebelement("//c-wizard-step[@is-active='true']//*[@placeholder='Search']");
//c-wizard-step[@is-active='true']//*[@placeholder='Search']
            String Jobfunction=label;
            if(Jobfunction.equalsIgnoreCase("NewlyAddedJobFunction"))
            {
                Jobfunction=DriverInstance.getThreadLocalMapValue("Job Function/Group Name");
            }
            webDriver.SendKeys(Search,Jobfunction);
            Thread.sleep(60000);
            WebElement Level = webDriver.getwebelement("//a[@title='"+Jobfunction+"']");
//            String DataLavel=Level.getAttribute("href");
            String DataLavel=webDriver.returnElementAttrubuteValue(Level,"href");
            DataLavel = DataLavel.substring(DataLavel.lastIndexOf("/") + 1);
            WebElement checkbox=webDriver.getwebelement("//*[@data-row-key-value='"+DataLavel+"']//input");
            if (!checkbox.isSelected()) {
                checkbox.click();
                webDriver.Clickon(checkbox);
            }
            Thread.sleep(20000);
        }
    }


    @Then("Add {string}")
    public void add(String arg0) throws Exception {
        String Trplan=arg0;
        if (arg0.equalsIgnoreCase("NewlyAddedTrainingPlan"))
        {
            Trplan=DriverInstance.getThreadLocalMapValue("Training Plan Name");
        }
        WebElement Search=webDriver.getwebelement("//c-wizard-step[@is-active='true']//*[@placeholder='Search by Name']");
        webDriver.SendKeys(Search,Trplan);
        WebElement Level = webDriver.getwebelement("//input[@name='"+Trplan+"']/ancestor::td");
//        Level.click();
        webDriver.Clickon(Level);
    }

    @Then("{string} Wizard")
    public void wizard(String arg0) throws Exception {
        WebElement Level = webDriver.getwebelement("//c-wizard-step[@is-active='true']//button[normalize-space()='"+arg0+"']");
//        Level.click();
        webDriver.Clickon(Level);
    }


    @Then("Upload the {string} file in {string} section By {string}")
    public void uploadTheFileInSection(String arg0, String arg1,String arg2) throws Exception {
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

    @And("Search for Record {string}")
    public void searchForRecord(String arg0) throws Exception {


        WebElement Search=webDriver.getwebelement("//c-wizard-step[@is-active='true']//*[@placeholder='Search']");
//c-wizard-step[@is-active='true']//*[@placeholder='Search']
        String Jobfunction=arg0;
        if(Jobfunction.equalsIgnoreCase("NewlyAddedJobFunction"))
        {
            Jobfunction=DriverInstance.getThreadLocalMapValue("Job Function/Group Name");
        }
        webDriver.SendKeys(Search,Jobfunction);
//            Thread.sleep(60000);
    }

    @And("Click on {string} link for the Article {string}")
    public void clickOnLinkForTheArtical(String arg0, String arg1) throws Exception {
        WebElement ArticalViewAll=webDriver.getwebelement("//article[@aria-label='"+arg1+"']//a/span[text()='"+arg0+"']");
        webDriver.safeJavaScriptClick(ArticalViewAll);
    }

    @And("Open Assignment for the user {string}")
    public void openAssignmentForTheUser(String arg0) throws Exception {
        WebElement Assignmentlink=webDriver.getwebelement("//*[text()='"+arg0+"']/ancestor::td/preceding-sibling::th//a");
        webDriver.safeJavaScriptClick(Assignmentlink);
    }

    @Then("Verify the Document Loaded")
    public void verifyTheDocumentLoaded() throws InterruptedException {

        webDriver.verifyElementToBePresent("//li/a[text()='MT test']/ancestor::div[@class='component']/iframe");
    }

    @And("Click on {string} tab")
    public void clickOnTab(String arg0) throws Exception {
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("EntityTabs").replace("{paramlink}",arg0).replace("{paramlink1}","normal")));


    }
    @Then("Submit the Exam")
    public void submitTheExam(DataTable dataTable) throws Exception {

        List<Map<String, String>> fields = dataTable.asMaps(String.class, String.class);
        webDriver.fillform(fields);

        WebElement submitButton = webDriver.getwebelement("//input[@type='button' and contains(@value, 'Submit Answers')]");
        webDriver.Clickon(submitButton);
    }

    @And("Click on {string} icon")
    public void clickOnIcon(String arg0) throws Exception {
        webDriver.safeJavaScriptClick(webDriver.getwebelement("//*[normalize-space(text())='"+arg0+"']/parent::*/parent::button"));

    }

    @And("Do the E-sign")
    public void doTheESign() throws Exception {
        webDriver.SendKeys(webDriver.getwebelement("id=eSigUserNameId"),"adminbetaprem.pritam@ceptes.com");
        webDriver.SendKeys(webDriver.getwebelement("id=eSigPasswordId"),"Demoauto@123");
        webDriver.safeJavaScriptClick(webDriver.getwebelement("//*[text()='Sign']/parent::button"));
    }

    @Then("Verify the Below Fields")
    public void verifyTheBelowFields(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : data) {
            for (Map.Entry<String, String> entry : row.entrySet()) {
                String fieldName = entry.getKey().trim();
                String expectedValue = entry.getValue().trim();

                // XPath for locating the value based on field label
                String xpath = "//*[text()='" + fieldName + "']/parent::*/following-sibling::*//*[@data-output-element-id='output-field']";

                // Find the element
                WebElement actualElement = webDriver.getwebelement(xpath);

                String actualValue = actualElement.getText().trim().toLowerCase();
                System.out.println("Value from Data Table"+expectedValue);
                System.out.println("Value from UI"+actualValue);

                // Assert the value
                Assert.assertEquals(actualValue, expectedValue.toLowerCase() ,"Mismatch for field: " + fieldName);
            }
        }
    }


}
