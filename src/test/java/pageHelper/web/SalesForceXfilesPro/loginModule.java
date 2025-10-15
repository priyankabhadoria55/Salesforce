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
 * loginModule - Step definitions for Salesforce login functionality
 * This class contains step definitions for the files.feature scenarios
 */
public class loginModule {

    private webHelper webDriver;
    private xmlreader xml;
    private bddDriver DriverInstance;
    private WebDriverWait wait;
    private PropertyReader propertyReader;

    /**
     * Constructor for BDD context
     */
    public loginModule(bddDriver contextSteps) throws Exception {
        this.DriverInstance = contextSteps;
        this.webDriver = new baseDriverHelper(bddDriver.getWebDriver());
        this.xml = new xmlreader("src\\test\\resources\\locators\\salesforce.xml");
        this.wait = new WebDriverWait(bddDriver.getWebDriver(), Duration.ofSeconds(30));
        this.propertyReader = new PropertyReader();
    }

    /**
     * Constructor with existing webHelper and xmlreader
     */
    public loginModule(webHelper webDriver, xmlreader xml) {
        this.webDriver = webDriver;
        this.xml = xml;
        this.wait = new WebDriverWait(webDriver.getWebDriver(), Duration.ofSeconds(30));
        this.propertyReader = new PropertyReader();
    }

    /**
     * Navigate to a specific URL
     * 
     * @param url The URL to navigate to
     * @throws Exception if navigation fails
     */
    @Given("I navigate to {string}")
    public void iNavigateTo(String url) throws Exception {
        System.out.println("🌐 Navigating to URL: " + url);
        
        try {
            // Navigate to the specified URL
            webDriver.navigateTo(url);
            
            // Wait for page to load completely
            webDriver.waitForPageLoad();
            
            // Additional wait to ensure page is fully loaded
            Thread.sleep(2000);
            
            System.out.println("✅ Successfully navigated to: " + url);
            
            // Log current page title and URL for verification
            String currentUrl = webDriver.getWebDriver().getCurrentUrl();
            String pageTitle = webDriver.getWebDriver().getTitle();
            System.out.println("   Current URL: " + currentUrl);
            System.out.println("   Page Title: " + pageTitle);
            
        } catch (Exception e) {
            System.out.println("❌ Failed to navigate to: " + url);
            System.out.println("   Error: " + e.getMessage());
            throw new Exception("Navigation failed: " + e.getMessage());
        }
    }

    /**
     * Enter login credentials (username and password)
     * 
     * @param username The username/email for login
     * @param password The password for login
     * @throws Exception if credential entry fails
     */
    @When("I enter the Login Id as {string} and password {string}")
    public void iEnterTheLoginIdAndPassword(String username, String password) throws Exception {
        System.out.println("🔐 Entering login credentials");
        System.out.println("   Username: " + username);
        System.out.println("   Password: " + "******");
        
        try {
            // Wait for username field to be present and visible
            WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
            wait.until(ExpectedConditions.elementToBeClickable(usernameField));
            
            // Clear any existing text and enter username
            usernameField.clear();
            usernameField.sendKeys(username);
            System.out.println("✅ Username entered successfully");
            
            // Wait for password field to be present and visible
            WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
            wait.until(ExpectedConditions.elementToBeClickable(passwordField));
            
            // Clear any existing text and enter password
            passwordField.clear();
            passwordField.sendKeys(password);
            System.out.println("✅ Password entered successfully");
            
        } catch (Exception e) {
            System.out.println("❌ Failed to enter credentials");
            System.out.println("   Error: " + e.getMessage());
            throw new Exception("Credential entry failed: " + e.getMessage());
        }
    }

    /**
     * Click on login button and verify successful login
     * 
     * @throws Exception if login fails
     */
    @Then("Click on login button and user should be able to login successfully")
    public void clickOnLoginButtonAndVerifySuccess() throws Exception {
        System.out.println("🔘 Clicking on login button and verifying success");
        
        try {
            // Wait for login button to be present and clickable
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("Login")));
            
            // Click the login button
            loginButton.click();
            System.out.println("✅ Login button clicked successfully");
            
            // Wait for page to load after login attempt
            Thread.sleep(3000);
            
            // Verify successful login by checking for Salesforce home page elements
            boolean loginSuccess = verifyLoginSuccess();
            
            if (loginSuccess) {
                System.out.println("✅ User logged in successfully to Salesforce");
                
                // Log current page information
                String currentUrl = webDriver.getWebDriver().getCurrentUrl();
                String pageTitle = webDriver.getWebDriver().getTitle();
                System.out.println("   Current URL: " + currentUrl);
                System.out.println("   Page Title: " + pageTitle);
                
            } else {
                System.out.println("❌ Login failed - user could not login successfully");
                throw new Exception("Login verification failed");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Error during login process");
            System.out.println("   Error: " + e.getMessage());
            throw new Exception("Login process failed: " + e.getMessage());
        }
    }

    /**
     * Verify that login form elements are present
     * 
     * @throws Exception if form elements are not found
     */
    @Then("I should see the login form with username and password fields")
    public void iShouldSeeTheLoginFormWithFields() throws Exception {
        System.out.println("🔍 Verifying login form elements are present");
        
        try {
            // Check for username field
            WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
            if (!usernameField.isDisplayed()) {
                throw new Exception("Username field is not visible");
            }
            System.out.println("✅ Username field is present and visible");
            
            // Check for password field
            WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
            if (!passwordField.isDisplayed()) {
                throw new Exception("Password field is not visible");
            }
            System.out.println("✅ Password field is present and visible");
            
        } catch (Exception e) {
            System.out.println("❌ Login form verification failed");
            System.out.println("   Error: " + e.getMessage());
            throw new Exception("Login form verification failed: " + e.getMessage());
        }
    }

    /**
     * Verify that login button is present
     * 
     * @throws Exception if login button is not found
     */
    @And("I should see the login button")
    public void iShouldSeeTheLoginButton() throws Exception {
        System.out.println("🔍 Verifying login button is present");
        
        try {
            WebElement loginButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Login")));
            if (!loginButton.isDisplayed()) {
                throw new Exception("Login button is not visible");
            }
            System.out.println("✅ Login button is present and visible");
            
        } catch (Exception e) {
            System.out.println("❌ Login button verification failed");
            System.out.println("   Error: " + e.getMessage());
            throw new Exception("Login button verification failed: " + e.getMessage());
        }
    }

    /**
     * Verify that user is redirected to Salesforce home page
     * 
     * @throws Exception if redirection verification fails
     */
    @And("I should be redirected to Salesforce home page")
    public void iShouldBeRedirectedToSalesforceHomePage() throws Exception {
        System.out.println("🏠 Verifying redirection to Salesforce home page");
        
        try {
            // Wait for Salesforce home page elements
            WebElement homePage = wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'slds-global-header')]")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'appLauncher')]")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'slds-page-header')]")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(), 'Home')]")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'slds-context-bar')]"))
            ));
            
            if (homePage != null && homePage.isDisplayed()) {
                System.out.println("✅ Successfully redirected to Salesforce home page");
                
                // Log home page information
                String currentUrl = webDriver.getWebDriver().getCurrentUrl();
                String pageTitle = webDriver.getWebDriver().getTitle();
                System.out.println("   Home Page URL: " + currentUrl);
                System.out.println("   Home Page Title: " + pageTitle);
                
            } else {
                throw new Exception("Home page elements not found");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Redirection to home page verification failed");
            System.out.println("   Error: " + e.getMessage());
            throw new Exception("Home page redirection verification failed: " + e.getMessage());
        }
    }

    /**
     * Verify if login was successful by checking for Salesforce home page elements
     * 
     * @return true if login successful, false otherwise
     */
    private boolean verifyLoginSuccess() {
        try {
            // Look for Salesforce home page indicators
            WebElement homePage = wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'slds-global-header')]")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'appLauncher')]")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'slds-page-header')]")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(), 'Home')]")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'slds-context-bar')]"))
            ));
            
            return homePage != null;
            
        } catch (Exception e) {
            // Check for error messages
            try {
                WebElement errorMessage = webDriver.getWebDriver().findElement(By.xpath("//div[contains(@class, 'error') or contains(@class, 'loginError') or contains(@class, 'errorMsg')]"));
                if (errorMessage.isDisplayed()) {
                    String errorText = errorMessage.getText();
                    System.out.println("❌ Login error detected: " + errorText);
                }
            } catch (Exception ex) {
                // No error message found, but login verification failed
                System.out.println("⚠️ Could not verify login status - no home page elements found");
            }
            return false;
        }
    }

    /**
     * Generic method to perform complete login process
     * 
     * @param url Salesforce login URL
     * @param username Username for login
     * @param password Password for login
     * @return true if login successful, false otherwise
     * @throws Exception if login fails
     */
    public boolean performLogin(String url, String username, String password) throws Exception {
        System.out.println("🚀 Starting complete login process");
        
        try {
            // Navigate to login page
            iNavigateTo(url);
            
            // Enter credentials
            iEnterTheLoginIdAndPassword(username, password);
            
            // Click login and verify success
            clickOnLoginButtonAndVerifySuccess();
            
            System.out.println("✅ Complete login process finished successfully");
            return true;
            
        } catch (Exception e) {
            System.out.println("❌ Complete login process failed");
            System.out.println("   Error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Check if user is currently logged into Salesforce
     * 
     * @return true if logged in, false otherwise
     */
    public boolean isLoggedIn() {
        try {
            // Check for Salesforce home page elements
            WebElement homePage = webDriver.getWebDriver().findElement(By.xpath("//div[contains(@class, 'slds-global-header') or contains(@class, 'appLauncher')]"));
            return homePage != null && homePage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get current page URL
     * 
     * @return current URL
     */
    public String getCurrentUrl() {
        return webDriver.getWebDriver().getCurrentUrl();
    }

    /**
     * Get current page title
     * 
     * @return page title
     */
    public String getPageTitle() {
        return webDriver.getWebDriver().getTitle();
    }

    /**
     * Take screenshot of current page
     * 
     * @param fileName Screenshot file name
     * @throws Exception if screenshot fails
     */
    public void takeScreenshot(String fileName) throws Exception {
        webDriver.takeScreenshot(fileName);
    }
}
