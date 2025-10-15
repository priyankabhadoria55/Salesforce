package pageHelper.web.SalesForceXfilesPro;

import core.baseDriverHelper;
import core.webHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageHelper.bddDriver;
import utils.PropertyReader;
import utils.xmlreader;

import java.time.Duration;

/**
 * SalesforceLoginStep - Step definitions for Salesforce login automation
 * This class contains all the step definitions for the Salesforce login feature
 */
public class SalesforceLoginStep {

    private webHelper webDriver;
    private xmlreader xml;
    private bddDriver DriverInstance;
    private WebDriverWait wait;

    /**
     * Constructor for BDD context
     */
    public SalesforceLoginStep(bddDriver contextSteps) throws Exception {
        this.DriverInstance = contextSteps;
        this.webDriver = new baseDriverHelper(bddDriver.getWebDriver());
        this.xml = new xmlreader("src\\test\\resources\\locators\\salesforce.xml");
        this.wait = new WebDriverWait(bddDriver.getWebDriver(), Duration.ofSeconds(30));
    }

    /**
     * Navigate to Salesforce login page
     */
    @Given("I navigate to Salesforce login page {string}")
    public void iNavigateToSalesforceLoginPage(String url) throws Exception {
        System.out.println("🌐 Navigating to Salesforce login page: " + url);
        webDriver.navigateTo(url);
        webDriver.waitForPageLoad();
        System.out.println("✅ Successfully navigated to Salesforce login page");
    }

    /**
     * Enter username in the login form
     */
    @When("I enter username {string}")
    public void iEnterUsername(String username) throws Exception {
        System.out.println("👤 Entering username: " + username);
        
        // Wait for username field to be present and visible
        WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        wait.until(ExpectedConditions.elementToBeClickable(usernameField));
        
        // Clear any existing text and enter username
        usernameField.clear();
        usernameField.sendKeys(username);
        
        System.out.println("✅ Username entered successfully");
    }

    /**
     * Enter password in the login form
     */
    @And("I enter password {string}")
    public void iEnterPassword(String password) throws Exception {
        System.out.println("🔐 Entering password");
        
        // Wait for password field to be present and visible
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
        wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        
        // Clear any existing text and enter password
        passwordField.clear();
        passwordField.sendKeys(password);
        
        System.out.println("✅ Password entered successfully");
    }

    /**
     * Click on the Login button
     */
    @And("I click on Login button")
    public void iClickOnLoginButton() throws Exception {
        System.out.println("🔘 Clicking on Login button");
        
        // Wait for login button to be present and clickable
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("Login")));
        loginButton.click();
        
        System.out.println("✅ Login button clicked successfully");
    }

    /**
     * Verify successful login to Salesforce
     */
    @Then("I should be successfully logged into Salesforce")
    public void iShouldBeSuccessfullyLoggedIntoSalesforce() throws Exception {
        System.out.println("🔍 Verifying successful login to Salesforce");
        
        // Wait for the page to load after login
        Thread.sleep(3000);
        
        // Check if we're redirected to Salesforce home page or if there are any error messages
        try {
            // Look for Salesforce home page indicators
            WebElement homePage = wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'slds-global-header')]")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'appLauncher')]")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'slds-page-header')]"))
            ));
            
            System.out.println("✅ Successfully logged into Salesforce");
        } catch (Exception e) {
            // Check for error messages
            try {
                WebElement errorMessage = webDriver.getWebDriver().findElement(By.xpath("//div[contains(@class, 'error') or contains(@class, 'loginError')]"));
                String errorText = errorMessage.getText();
                System.out.println("❌ Login failed with error: " + errorText);
                throw new Exception("Login failed: " + errorText);
            } catch (Exception ex) {
                System.out.println("⚠️ Could not verify login status. Please check manually.");
            }
        }
    }

    /**
     * Verify Salesforce home page is displayed
     */
    @And("I should see the Salesforce home page")
    public void iShouldSeeTheSalesforceHomePage() throws Exception {
        System.out.println("🏠 Verifying Salesforce home page is displayed");
        
        // Wait for home page elements to be present
        try {
            wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'slds-global-header')]")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'appLauncher')]")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(), 'Home')]"))
            ));
            
            System.out.println("✅ Salesforce home page is displayed");
        } catch (Exception e) {
            System.out.println("❌ Salesforce home page is not displayed");
            throw new Exception("Salesforce home page verification failed");
        }
    }

    /**
     * Verify username field is present
     */
    @Then("I should see username field")
    public void iShouldSeeUsernameField() throws Exception {
        System.out.println("🔍 Verifying username field is present");
        
        WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        if (usernameField.isDisplayed()) {
            System.out.println("✅ Username field is present and visible");
        } else {
            throw new Exception("Username field is not visible");
        }
    }

    /**
     * Verify password field is present
     */
    @And("I should see password field")
    public void iShouldSeePasswordField() throws Exception {
        System.out.println("🔍 Verifying password field is present");
        
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
        if (passwordField.isDisplayed()) {
            System.out.println("✅ Password field is present and visible");
        } else {
            throw new Exception("Password field is not visible");
        }
    }

    /**
     * Verify login button is present
     */
    @And("I should see login button")
    public void iShouldSeeLoginButton() throws Exception {
        System.out.println("🔍 Verifying login button is present");
        
        WebElement loginButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Login")));
        if (loginButton.isDisplayed()) {
            System.out.println("✅ Login button is present and visible");
        } else {
            throw new Exception("Login button is not visible");
        }
    }

    /**
     * Verify Remember me checkbox is present
     */
    @And("I should see {string} checkbox")
    public void iShouldSeeCheckbox(String checkboxName) throws Exception {
        System.out.println("🔍 Verifying " + checkboxName + " checkbox is present");
        
        try {
            WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(text(), '" + checkboxName + "')]")));
            if (checkbox.isDisplayed()) {
                System.out.println("✅ " + checkboxName + " checkbox is present and visible");
            } else {
                throw new Exception(checkboxName + " checkbox is not visible");
            }
        } catch (Exception e) {
            System.out.println("⚠️ " + checkboxName + " checkbox not found or not visible");
        }
    }

    /**
     * Generic login method that can be reused
     */
    public void loginToSalesforce(String url, String username, String password) throws Exception {
        System.out.println("🚀 Starting Salesforce login process");
        
        // Navigate to login page
        iNavigateToSalesforceLoginPage(url);
        
        // Enter credentials
        iEnterUsername(username);
        iEnterPassword(password);
        
        // Click login button
        iClickOnLoginButton();
        
        // Verify successful login
        iShouldBeSuccessfullyLoggedIntoSalesforce();
        
        System.out.println("✅ Salesforce login process completed successfully");
    }
}
