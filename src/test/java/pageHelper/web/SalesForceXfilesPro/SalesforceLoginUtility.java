package pageHelper.web.SalesForceXfilesPro;

import core.baseDriverHelper;
import core.webHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageHelper.bddDriver;
import utils.PropertyReader;

import java.time.Duration;

/**
 * SalesforceLoginUtility - Utility class for Salesforce login operations
 * This class provides reusable methods for Salesforce login automation
 */
public class SalesforceLoginUtility {

    private webHelper webDriver;
    private WebDriverWait wait;
    private PropertyReader propertyReader;

    /**
     * Constructor for BDD context
     */
    public SalesforceLoginUtility(bddDriver contextSteps) throws Exception {
        this.webDriver = new baseDriverHelper(bddDriver.getWebDriver());
        this.wait = new WebDriverWait(bddDriver.getWebDriver(), Duration.ofSeconds(30));
        this.propertyReader = new PropertyReader();
    }

    /**
     * Constructor with existing webHelper
     */
    public SalesforceLoginUtility(webHelper webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver.getWebDriver(), Duration.ofSeconds(30));
        this.propertyReader = new PropertyReader();
    }

    /**
     * Login to Salesforce using credentials from config.properties
     * 
     * @return true if login successful, false otherwise
     * @throws Exception if login fails
     */
    public boolean loginToSalesforceWithConfig() throws Exception {
        String url = propertyReader.readproperty("xFilesPro_application_url");
        String username = propertyReader.readproperty("XfilePro_user");
        String password = propertyReader.readproperty("XfilePro_password");
        
        return loginToSalesforce(url, username, password);
    }

    /**
     * Login to Salesforce with custom credentials
     * 
     * @param url Salesforce login URL
     * @param username Username for login
     * @param password Password for login
     * @return true if login successful, false otherwise
     * @throws Exception if login fails
     */
    public boolean loginToSalesforce(String url, String username, String password) throws Exception {
        try {
            System.out.println("🚀 Starting Salesforce login process");
            System.out.println("   URL: " + url);
            System.out.println("   Username: " + username);
            
            // Navigate to login page
            webDriver.navigateTo(url);
            webDriver.waitForPageLoad();
            
            // Wait for login form to be present
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
            
            // Enter username
            WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
            usernameField.clear();
            usernameField.sendKeys(username);
            
            // Enter password
            WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
            passwordField.clear();
            passwordField.sendKeys(password);
            
            // Click login button
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("Login")));
            loginButton.click();
            
            // Wait for page to load after login
            Thread.sleep(3000);
            
            // Verify successful login
            boolean loginSuccess = verifyLoginSuccess();
            
            if (loginSuccess) {
                System.out.println("✅ Salesforce login successful");
            } else {
                System.out.println("❌ Salesforce login failed");
            }
            
            return loginSuccess;
            
        } catch (Exception e) {
            System.out.println("❌ Error during Salesforce login: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Verify if login was successful by checking for Salesforce home page elements
     * 
     * @return true if login successful, false otherwise
     */
    public boolean verifyLoginSuccess() {
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
                    System.out.println("❌ Login error: " + errorText);
                }
            } catch (Exception ex) {
                // No error message found, but login verification failed
                System.out.println("⚠️ Could not verify login status");
            }
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
     * Logout from Salesforce
     * 
     * @throws Exception if logout fails
     */
    public void logoutFromSalesforce() throws Exception {
        try {
            System.out.println("🚪 Logging out from Salesforce");
            
            // Click on user menu (usually in top right corner)
            WebElement userMenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'slds-global-header')]//button[contains(@class, 'slds-button')]")
            ));
            userMenu.click();
            
            // Click on logout option
            WebElement logoutOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(), 'Log Out') or contains(text(), 'Logout')]")
            ));
            logoutOption.click();
            
            // Wait for logout to complete
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
            
            System.out.println("✅ Successfully logged out from Salesforce");
            
        } catch (Exception e) {
            System.out.println("❌ Error during logout: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Get current Salesforce URL
     * 
     * @return current URL
     */
    public String getCurrentUrl() {
        return webDriver.getWebDriver().getCurrentUrl();
    }

    /**
     * Get page title
     * 
     * @return page title
     */
    public String getPageTitle() {
        return webDriver.getWebDriver().getTitle();
    }

    /**
     * Wait for specific element to be present
     * 
     * @param locator Element locator
     * @param timeoutInSeconds Timeout in seconds
     * @return WebElement if found, null otherwise
     */
    public WebElement waitForElement(By locator, int timeoutInSeconds) {
        try {
            WebDriverWait customWait = new WebDriverWait(webDriver.getWebDriver(), Duration.ofSeconds(timeoutInSeconds));
            return customWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            return null;
        }
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
