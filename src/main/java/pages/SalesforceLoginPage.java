package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import java.time.Duration;

/**
 * Page Object Model class for Salesforce Login Page
 * Contains all web elements and methods related to Salesforce login functionality
 */
public class SalesforceLoginPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Web Elements using @FindBy annotations
    @FindBy(id = "username")
    private WebElement usernameField;
    
    @FindBy(id = "password")
    private WebElement passwordField;
    
    @FindBy(id = "Login")
    private WebElement loginButton;
    
    @FindBy(xpath = "//div[@id='error']")
    private WebElement errorMessage;
    
    @FindBy(xpath = "//div[contains(@class,'loginError')]")
    private WebElement loginErrorMessage;
    
    @FindBy(xpath = "//div[@class='requiredInput']//div[@class='errorMsg']")
    private WebElement usernameValidationError;
    
    @FindBy(xpath = "//div[@class='requiredInput']//div[@class='errorMsg']")
    private WebElement passwordValidationError;
    
    @FindBy(xpath = "//span[@class='uiImage']")
    private WebElement salesforceAppLauncher;
    
    @FindBy(xpath = "//span[contains(@class,'slds-global-header__logo')]")
    private WebElement salesforceLogo;
    
    @FindBy(xpath = "//div[@class='setupGear']")
    private WebElement setupGear;
    
    @FindBy(xpath = "//a[@title='Home Tab']")
    private WebElement homeTab;
    
    // Constructor
    public SalesforceLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Navigate to Salesforce login page
     * @param url - Salesforce login URL
     */
    public void navigateToLoginPage(String url) {
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOf(usernameField));
    }
    
    /**
     * Enter username in the username field
     * @param username - Username to be entered
     */
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.clear();
        usernameField.sendKeys(username);
    }
    
    /**
     * Enter password in the password field
     * @param password - Password to be entered
     */
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    
    /**
     * Click on Login button
     */
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }
    
    /**
     * Perform complete login operation
     * @param username - Username
     * @param password - Password
     */
    public void performLogin(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
    
    /**
     * Check if login was successful by verifying dashboard elements
     * @return true if login successful, false otherwise
     */
    public boolean isLoginSuccessful() {
        try {
            // Wait for any of the dashboard elements to appear
            return wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOf(salesforceAppLauncher),
                ExpectedConditions.visibilityOf(setupGear),
                ExpectedConditions.visibilityOf(homeTab),
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='setupGear']")),
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'slds-global-header')]")),
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav[@role='navigation']"))
            )) != null;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Check if error message is displayed
     * @return true if error message is visible, false otherwise
     */
    public boolean isErrorMessageDisplayed() {
        try {
            return errorMessage.isDisplayed() || loginErrorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Get the error message text
     * @return Error message text
     */
    public String getErrorMessage() {
        try {
            if (errorMessage.isDisplayed()) {
                return errorMessage.getText();
            } else if (loginErrorMessage.isDisplayed()) {
                return loginErrorMessage.getText();
            }
        } catch (Exception e) {
            // Try alternative error message locators
            try {
                WebElement altError = driver.findElement(By.xpath("//div[contains(@class,'error') or contains(@class,'loginError')]"));
                if (altError.isDisplayed()) {
                    return altError.getText();
                }
            } catch (Exception ex) {
                return "Error message not found";
            }
        }
        return "No error message";
    }
    
    /**
     * Check if validation errors are displayed for empty fields
     * @return true if validation errors are visible, false otherwise
     */
    public boolean areValidationErrorsDisplayed() {
        try {
            return driver.findElements(By.xpath("//div[@class='errorMsg']")).size() > 0 ||
                   driver.findElements(By.xpath("//div[contains(@class,'error')]")).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Check if still on login page
     * @return true if on login page, false otherwise
     */
    public boolean isOnLoginPage() {
        try {
            return usernameField.isDisplayed() && passwordField.isDisplayed() && loginButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Clear username field
     */
    public void clearUsername() {
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.clear();
    }
    
    /**
     * Clear password field
     */
    public void clearPassword() {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
    }
    
    /**
     * Get page title
     * @return Page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    /**
     * Wait for page to load completely
     */
    public void waitForPageLoad() {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
    }
}