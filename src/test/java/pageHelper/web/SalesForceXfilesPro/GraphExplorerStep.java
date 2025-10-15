package pageHelper.web.SalesForceXfilesPro;

import core.baseDriverHelper;
import core.webHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pageHelper.bddDriver;
import utils.DriverController;
import utils.xmlreader;

import java.time.Duration;

@Log4j
public class GraphExplorerStep {
    public webHelper webDriver;
    private bddDriver DriverInstance;
    public xmlreader xml;

    public GraphExplorerStep(bddDriver contextSteps, DriverController driverController) throws Exception {
        this.DriverInstance = contextSteps;
        System.out.println("GraphExplorerStep initialized with BDD driver");
        webDriver = new baseDriverHelper(bddDriver.getWebDriver());
        xml = new xmlreader("src\\test\\resources\\locators\\graph_explorer.xml");
    }

    public GraphExplorerStep(DriverController driverController) {
        webDriver = new baseDriverHelper(driverController.getDriver());
    }

    @Given("I navigate to Microsoft Graph Explorer")
    public void iNavigateToMicrosoftGraphExplorer() throws Exception {
        System.out.println("Navigating to Microsoft Graph Explorer");
        webDriver.GetURL("https://developer.microsoft.com/en-us/graph/graph-explorer");
        Thread.sleep(2000);
        System.out.println("Successfully navigated to Microsoft Graph Explorer");
    }

    @When("I sign in to Microsoft Graph Explorer")
    public void iSignInToMicrosoftGraphExplorer() throws Exception {
        System.out.println("Signing in to Microsoft Graph Explorer");

        // Wait for and click the sign-in button
        webDriver.waitforElementtobeclickable("//button[contains(text(), 'Sign in') or contains(@aria-label, 'Sign in')]");
        webDriver.Clickon(webDriver.getwebelement("//button[contains(text(), 'Sign in') or contains(@aria-label, 'Sign in')]"));

        // Wait for Microsoft login page to load
        Thread.sleep(2000);

        // Handle Microsoft login if needed (this might require additional steps based on your setup)
        System.out.println("Sign-in process initiated");
    }

    @And("I select {string} method and endpoint {string}")
    public void iSelectMethodAndEndpoint(String method, String endpoint) throws Exception {
        System.out.println("Selecting " + method + " method and setting endpoint: " + endpoint);

        // Select HTTP method
        webDriver.waitforElementtobeclickable("//select[@id='http-verb']");
        WebElement methodDropdown = webDriver.getwebelement("//select[@id='http-verb']");
        Select select = new Select(methodDropdown);
        select.selectByValue(method);

        // Set the endpoint URL
        webDriver.verifyElementToBePresent("//input[@id='request-url']");
        WebElement endpointInput = webDriver.getwebelement("//input[@id='request-url']");
        endpointInput.clear();
        endpointInput.sendKeys(endpoint);

        System.out.println("Method and endpoint configured successfully");
    }

    @And("I paste the following JSON in the request body:")
    public void iPasteTheFollowingJSONInTheRequestBody(String jsonBody) throws Exception {
        System.out.println("Pasting JSON in request body");

        // Find and click on the request body section
        webDriver.waitforElementtobeclickable("//div[contains(@class, 'request-body') or contains(@id, 'request-body')]");
        webDriver.Clickon(webDriver.getwebelement("//div[contains(@class, 'request-body') or contains(@id, 'request-body')]"));

        // Find the textarea or input for request body
        webDriver.verifyElementToBePresent("//textarea[contains(@id, 'request-body') or contains(@class, 'request-body')] | //div[@contenteditable='true']");
        WebElement bodyInput = webDriver.getwebelement("//textarea[contains(@id, 'request-body') or contains(@class, 'request-body')] | //div[@contenteditable='true']");

        // Clear existing content and paste JSON
        bodyInput.clear();
        bodyInput.sendKeys(jsonBody);

        System.out.println("JSON body pasted successfully");
    }

    @When("I click on {string} button")
    public void iClickOnButton(String buttonText) throws Exception {
        System.out.println("Clicking on button: " + buttonText);

        webDriver.waitforElementtobeclickable("//button[contains(text(), '" + buttonText + "') or contains(@aria-label, '" + buttonText + "')]");
        webDriver.Clickon(webDriver.getwebelement("//button[contains(text(), '" + buttonText + "') or contains(@aria-label, '" + buttonText + "')]"));

        // Wait for the request to complete
        Thread.sleep(3000);
        System.out.println("Button clicked successfully");
    }

    @Then("I should see a successful response from the API")
    public void iShouldSeeASuccessfulResponseFromTheAPI() throws Exception {
        System.out.println("Verifying successful API response");

        // Wait for response section to appear
        webDriver.verifyElementToBePresent("//div[contains(@class, 'response') or contains(@id, 'response')]");

        // Check for success indicators
        boolean hasSuccessIndicator = webDriver.isElementPresent(
                "//div[contains(@class, 'response')]//span[contains(text(), '200') or contains(text(), '201') or contains(text(), 'Success')]"
        );

        Assert.assertTrue(hasSuccessIndicator, "API response should indicate success (200/201 status)");
        System.out.println("Successfully verified API response");
    }

    @And("I should verify the response contains the expected data structure")
    public void iShouldVerifyTheResponseContainsTheExpectedDataStructure() throws Exception {
        System.out.println("Verifying response data structure");

        // Wait for response content to load
        webDriver.verifyElementToBePresent("//div[contains(@class, 'response')]//pre[contains(@class, 'json') or contains(@class, 'response-body')]");
        WebElement responseContent = webDriver.getwebelement("//div[contains(@class, 'response')]//pre[contains(@class, 'json') or contains(@class, 'response-body')]");

        String responseText = responseContent.getText();

        // Verify the response contains expected fields
        Assert.assertTrue(responseText.contains("roles"), "Response should contain 'roles' field");
        Assert.assertTrue(responseText.contains("grantedToIdentities"), "Response should contain 'grantedToIdentities' field");
        Assert.assertTrue(responseText.contains("application"), "Response should contain 'application' field");

        System.out.println("Response data structure verified successfully");
    }
}





